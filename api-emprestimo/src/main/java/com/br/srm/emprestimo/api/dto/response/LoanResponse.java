package com.br.srm.emprestimo.api.dto.response;

import com.br.srm.emprestimo.api.enumeration.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanResponse {

    private Long id;

    private BigDecimal loanValue;

    private Integer numberParcels;

    private PaymentStatus paymentStatus;

}
