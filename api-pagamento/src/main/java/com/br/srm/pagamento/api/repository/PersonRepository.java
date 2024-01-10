package com.br.srm.pagamento.api.repository;

import com.br.srm.pagamento.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByIdentifier(String identifier);
    Boolean existsByIdentifier(String identifier);
}
