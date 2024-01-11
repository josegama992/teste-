package com.br.srm.emprestimo.api.repository;

import com.br.srm.emprestimo.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonFilterRepository {

    Optional<Person> findByIdentifier(String identifier);
    Boolean existsByIdentifier(String identifier);
}
