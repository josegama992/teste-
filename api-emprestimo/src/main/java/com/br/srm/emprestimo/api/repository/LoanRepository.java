package com.br.srm.emprestimo.api.repository;

import com.br.srm.emprestimo.api.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Page<Loan> findAllByPersonIdentifier(String identifier, Pageable pageable);
}
