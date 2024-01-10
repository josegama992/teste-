package com.br.srm.emprestimo.api.controller;

import com.br.srm.emprestimo.api.controller.openapi.LoanControllerOpenApi;
import com.br.srm.emprestimo.api.dto.request.LoanRequest;
import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import com.br.srm.emprestimo.api.service.LoanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoanController implements LoanControllerOpenApi {

    private LoanService service;

    @Override
    @GetMapping("/pessoa/{identifier}/emprestimo")
    public ResponseEntity<Page<LoanResponse>> list(String identifier, Pageable pageable){
        Page<LoanResponse> response = service.findAllByPersonIdenfier(identifier, pageable);
        return ResponseEntity.ok(response);
    }
    @Override
    @PostMapping("/pessoa/{identifier}/emprestimo")
    public ResponseEntity<LoanResponse> makeLoan(String identifier, @Valid @RequestBody LoanRequest request){
        LoanResponse response = service.makeLoan(identifier, request);
        return ResponseEntity.ok(response);
    }
}
