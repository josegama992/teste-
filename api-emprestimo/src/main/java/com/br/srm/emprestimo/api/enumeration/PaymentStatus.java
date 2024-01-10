package com.br.srm.emprestimo.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
    PAGO("Pago");

    private String name;
}
