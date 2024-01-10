package com.br.srm.emprestimo.api.service.impl;

import com.br.srm.emprestimo.api.dto.request.LoanRequest;
import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import com.br.srm.emprestimo.api.exception.BaseException;
import com.br.srm.emprestimo.api.mapper.LoanMapper;
import com.br.srm.emprestimo.api.message.PaymentMessage;
import com.br.srm.emprestimo.api.model.Loan;
import com.br.srm.emprestimo.api.model.Person;
import com.br.srm.emprestimo.api.repository.LoanRepository;
import com.br.srm.emprestimo.api.repository.PersonRepository;
import com.br.srm.emprestimo.api.service.LoanService;
import com.br.srm.emprestimo.api.utils.Validators;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository repository;
    private LoanMapper mapper;
    private PersonRepository personRepository;
    private KafkaTemplate<String, Serializable> kafkaTemplate;

    private static Integer MAX_ALLOWED_PARCELS = 24;

    @Override
    @Transactional(readOnly = true)
    public Page<LoanResponse> findAllByPersonIdenfier(String identifier, Pageable pageable){
        Page<Loan> model = repository.findAllByPersonIdentifier(identifier, pageable);
        return model.map(mapper::response);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public LoanResponse makeLoan(String identifier, LoanRequest request){
        Validators.validateIdentifier(identifier);
        Optional<Person> person = personRepository.findByIdentifier(identifier);
        if(person.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Pessoa de identificador: %s não encontrada.", identifier));
        }

        validateLoanValues(person.get().getMinimumMonthlyValue(), person.get().getMaximumLoanValue(), request.getLoanValue(), request.getNumberParcels());

        Loan model = mapper.create(request, person.get());
        model = repository.save(model);
        makePaymen(model.getId());
        return mapper.response(model);
    }

    private void validateLoanValues(BigDecimal minimumMonthlyValue, BigDecimal maximumLoanValue, BigDecimal loanValue, Integer numberParcels){
        if(numberParcels > MAX_ALLOWED_PARCELS){
            throw new BaseException(HttpStatus.CONFLICT, String.format("Numero de parcelas não pode ser maior que %d.", MAX_ALLOWED_PARCELS));
        }
        if(loanValue.compareTo(maximumLoanValue) == 1){
            throw new BaseException(HttpStatus.CONFLICT, "Valor do emprestimo é maior que o valor permitido para a pessoa.");
        }
        BigDecimal parcelValue = loanValue.divide(new BigDecimal(numberParcels), RoundingMode.HALF_EVEN);
        if(parcelValue.compareTo(minimumMonthlyValue) == -1){
            throw new BaseException(HttpStatus.CONFLICT, "Valor da parcela é menor que o valor minimo permitido para a pessoa.");
        }
    }

    private void makePaymen(Long loanId){
        PaymentMessage message = new PaymentMessage();
        message.setLoanId(loanId);
        message.setStatusPayment("PAGO");
        kafkaTemplate.send("payment-topic", message);
    }
}
