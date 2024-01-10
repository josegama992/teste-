package com.br.srm.emprestimo.api.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentMessage implements Serializable {

    private Long loanId;
    private String statusPayment;
}
