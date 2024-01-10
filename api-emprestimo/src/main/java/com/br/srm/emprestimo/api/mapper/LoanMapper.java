package com.br.srm.emprestimo.api.mapper;

import com.br.srm.emprestimo.api.dto.request.LoanRequest;
import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import com.br.srm.emprestimo.api.enumeration.PaymentStatus;
import com.br.srm.emprestimo.api.model.Loan;
import com.br.srm.emprestimo.api.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public LoanResponse response(Loan model){
        LoanResponse response = new LoanResponse();
        response.setId(model.getId());
        response.setLoanValue(model.getLoanValue());
        response.setNumberParcels(model.getNumberParcels());
        response.setPaymentStatus(model.getPaymentStatus());
        return response;
    }
    public List<LoanResponse> response(List<Loan> model){
        return model.stream().map(this::response).collect(Collectors.toList());
    }

    public Loan create(LoanRequest request, Person person){
        Loan model = new Loan();
        model.setPerson(person);
        model.setLoanValue(request.getLoanValue());
        model.setNumberParcels(request.getNumberParcels());
        model.setCreateDate(LocalDate.now());
        model.setPaymentStatus(PaymentStatus.AGUARDANDO_PAGAMENTO);
        return model;
    }
}
