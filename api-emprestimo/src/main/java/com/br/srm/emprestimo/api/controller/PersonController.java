package com.br.srm.emprestimo.api.controller;

import com.br.srm.emprestimo.api.controller.openapi.PersonControllerOpenApi;
import com.br.srm.emprestimo.api.dto.request.PersonRequest;
import com.br.srm.emprestimo.api.dto.response.PersonResponse;
import com.br.srm.emprestimo.api.filter.PersonFilter;
import com.br.srm.emprestimo.api.service.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PersonController implements PersonControllerOpenApi {

    private PersonService service;
    @Override
    @GetMapping
    public ResponseEntity<Page<PersonResponse>> findAll(PersonFilter filter, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(filter, pageable));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody @Valid PersonRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable("id") Long id,
                                                 @RequestBody @Valid PersonRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
