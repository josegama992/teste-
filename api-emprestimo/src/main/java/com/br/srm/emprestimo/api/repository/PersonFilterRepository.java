package com.br.srm.emprestimo.api.repository;

import com.br.srm.emprestimo.api.filter.PersonFilter;
import com.br.srm.emprestimo.api.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonFilterRepository {

    Page<Person> findAll(PersonFilter filter, Pageable pageable);
}
