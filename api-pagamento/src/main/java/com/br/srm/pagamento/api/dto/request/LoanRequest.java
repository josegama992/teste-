package com.br.srm.pagamento.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanRequest {

    @NotNull
    private BigDecimal loanValue;
    @NotNull
    private Integer numberParcels;
}
