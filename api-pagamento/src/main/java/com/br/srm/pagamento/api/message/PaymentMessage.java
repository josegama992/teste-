package com.br.srm.pagamento.api.message;

import com.br.srm.pagamento.api.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentMessage implements Serializable {

    private Long loanId;
    private PaymentStatus statusPayment;
}
