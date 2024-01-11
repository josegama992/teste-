package com.br.srm.emprestimo.api.service.impl;

import com.br.srm.emprestimo.api.dto.request.PersonRequest;
import com.br.srm.emprestimo.api.dto.response.PersonResponse;
import com.br.srm.emprestimo.api.enumeration.TypeIdentifier;
import com.br.srm.emprestimo.api.exception.BaseException;
import com.br.srm.emprestimo.api.filter.PersonFilter;
import com.br.srm.emprestimo.api.mapper.PersonMapper;
import com.br.srm.emprestimo.api.model.Person;
import com.br.srm.emprestimo.api.repository.PersonRepository;
import com.br.srm.emprestimo.api.service.PersonService;
import com.br.srm.emprestimo.api.utils.Validators;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;
    private PersonMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<PersonResponse> findAll(PersonFilter filter, Pageable pageable) {
        Page<Person> model = repository.findAll(filter, pageable);
        return model.map(mapper::response);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse findById(Long id) {
        Person model = find(id);
        return mapper.response(model);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse findByIdentifier(String identifier) {
        Validators.validateIdentifier(identifier);
        Optional<Person> model = repository.findByIdentifier(identifier);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Pessoa de identificador: %s não encontrada.", identifier));
        }
        return mapper.response(model.get());
    }

    @Override
    @Transactional
    public PersonResponse create(PersonRequest request) {
        if(repository.existsByIdentifier(request.getIdentifier())){
            throw new BaseException(HttpStatus.CONFLICT, "Pessoa já cadastrada.");
        }
        TypeIdentifier typeIdentifier = TypeIdentifier.getByIndentifier(request.getIdentifier());
        Validators.validateIdentifier(request.getIdentifier());
        Person model = mapper.create(request, typeIdentifier);
        setLoanValuesByTypeIdenfier(typeIdentifier, model);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    @Transactional
    public PersonResponse update(Long id, PersonRequest request) {

        Person model = find(id);
        TypeIdentifier typeIdentifier = TypeIdentifier.getByIndentifier(request.getIdentifier());
        Validators.validateIdentifier(request.getIdentifier());
        mapper.update(request, model, typeIdentifier);
        setLoanValuesByTypeIdenfier(typeIdentifier, model);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Person model = find(id);
        try {
            repository.delete(model);
            repository.flush();
        }
        catch (DataIntegrityViolationException e){
           throw new BaseException(HttpStatus.CONFLICT, "Pessoa em uso, não é possível remover.");
        }
    }

    public Person find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, String.format("Pessoa de id: %d não encontrada.", id)));
    }

    private void setLoanValuesByTypeIdenfier(TypeIdentifier typeIdentifier, Person model){
        switch (typeIdentifier){
            case PF:
                model.setMinimumMonthlyValue(new BigDecimal(300));
                model.setMaximumLoanValue(new BigDecimal(10000));
                break;
            case PJ:
                model.setMinimumMonthlyValue(new BigDecimal(1000));
                model.setMaximumLoanValue(new BigDecimal(100000));
                break;
            case EU:
                model.setMinimumMonthlyValue(new BigDecimal(100));
                model.setMaximumLoanValue(new BigDecimal(10000));
                break;
            case AP:
                model.setMinimumMonthlyValue(new BigDecimal(400));
                model.setMaximumLoanValue(new BigDecimal(25000));
                break;
        }
    }
}
