package com.br.srm.emprestimo.api.filter;

import com.br.srm.emprestimo.api.enumeration.TypeIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonFilter {

    private String name;
    private TypeIdentifier typeIdentifier;
    private LocalDate birthDate;
}
