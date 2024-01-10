package com.br.srm.emprestimo.api.service;

import com.br.srm.emprestimo.api.dto.request.LoanRequest;
import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface LoanService {
    @Transactional(readOnly = true)
    Page<LoanResponse> findAllByPersonIdenfier(String identifier, Pageable pageable);

    @Transactional
    LoanResponse makeLoan(String identifier, LoanRequest request);
}
