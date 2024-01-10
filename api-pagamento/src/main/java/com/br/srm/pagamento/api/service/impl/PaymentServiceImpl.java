package com.br.srm.pagamento.api.service.impl;

import com.br.srm.pagamento.api.dto.response.LoanResponse;
import com.br.srm.pagamento.api.enumeration.PaymentStatus;
import com.br.srm.pagamento.api.exception.BaseException;
import com.br.srm.pagamento.api.mapper.LoanMapper;
import com.br.srm.pagamento.api.repository.LoanRepository;
import com.br.srm.pagamento.api.model.Loan;
import com.br.srm.pagamento.api.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private LoanRepository loanRepository;
    private LoanMapper loanMapper;

    @Override
    public LoanResponse makePayment(Long loanId) {
        System.out.println(loanId);
        Optional<Loan> loan = loanRepository.findById(loanId);
        if(loan.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Emprestimo de id: %s não encontrado.", loanId));
        }
        loan.get().setPaymentStatus(PaymentStatus.PAGO);
        loanRepository.save(loan.get());
        return loanMapper.response(loan.get());
    }
}
