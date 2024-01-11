package com.br.srm.pagamento.api.message;

import com.br.srm.pagamento.api.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PaymentMessage implements Serializable {

    private Long loanId;
    private PaymentStatus statusPayment;
}
