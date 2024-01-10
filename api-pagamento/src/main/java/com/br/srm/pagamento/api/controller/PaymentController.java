package com.br.srm.pagamento.api.controller;

import com.br.srm.pagamento.api.dto.response.LoanResponse;
import com.br.srm.pagamento.api.controller.openapi.PaymentControllerOpenApi;
import com.br.srm.pagamento.api.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController implements PaymentControllerOpenApi {

    private PaymentService service;

    @Override
    @PutMapping("/emprestimo/{loanId}/realizar_pagamento")
    public ResponseEntity<LoanResponse> makePayment(@PathVariable Long loanId){
        LoanResponse response = service.makePayment(loanId);
        return ResponseEntity.ok(response);
    }
}
