package com.br.srm.pagamento.api.mapper;

import com.br.srm.pagamento.api.dto.request.PersonRequest;
import com.br.srm.pagamento.api.dto.response.PersonResponse;
import com.br.srm.pagamento.api.enumeration.TypeIdentifier;
import com.br.srm.pagamento.api.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public PersonResponse response (Person model){
        PersonResponse response = new PersonResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        response.setIdentifier(model.getIdentifier());
        response.setTypeIdentifier(model.getTypeIdentifier());
        response.setMaximumLoanValue(model.getMaximumLoanValue());
        response.setMinimumMonthlyValue(model.getMinimumMonthlyValue());
        response.setBirthDate(model.getBirthDate());
        return response;
    }
    public List<PersonResponse> response(List<Person> model){
        return model.stream().map(this::response).collect(Collectors.toList());
    }

    public Person create(PersonRequest request, TypeIdentifier typeIdentifier){
        Person model = new Person();
        model.setName(request.getName());
        model.setIdentifier(request.getIdentifier());
        model.setTypeIdentifier(typeIdentifier);
        model.setBirthDate(request.getBirthDate());
        return model;
    }

    public void update(PersonRequest request, Person model, TypeIdentifier typeIdentifier){
        model.setName(request.getName());
        model.setIdentifier(request.getIdentifier());
        model.setTypeIdentifier(typeIdentifier);
        model.setBirthDate(model.getBirthDate());
    }

}
