package com.br.srm.emprestimo.api.api;


import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

public interface PaymentApi {

    @RequestLine("PUT /emprestimo/{loanId}/realizar_pagamento")
    @Headers({ "Content-Type: application/json", "Authorization: Basic ZXF1aXBsYW5vOmVxdWlwbGFubw==" })
    ResponseEntity<LoanResponse> makePayment(@Param("loanId") Long loanId);

}
