package com.br.srm.emprestimo.api.service;

import com.br.srm.emprestimo.api.dto.request.PersonRequest;
import com.br.srm.emprestimo.api.dto.response.PersonResponse;
import com.br.srm.emprestimo.api.filter.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    Page<PersonResponse> findAll(PersonFilter filter, Pageable pageable);

    PersonResponse findById(Long id);

    PersonResponse findByIdentifier(String identifier);

    PersonResponse create(PersonRequest request);

    PersonResponse update(Long id, PersonRequest request);

    void delete(Long id);

}
