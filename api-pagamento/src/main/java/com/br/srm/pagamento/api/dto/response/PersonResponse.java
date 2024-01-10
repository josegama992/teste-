package com.br.srm.pagamento.api.dto.response;

import com.br.srm.pagamento.api.enumeration.TypeIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonResponse {

    private Long id;

    private String name;

    private String identifier;

    private LocalDate birthDate;

    private TypeIdentifier typeIdentifier;

    private BigDecimal minimumMonthlyValue;

    private BigDecimal maximumLoanValue;
}
