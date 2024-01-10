package com.br.srm.pagamento.api.service;

import com.br.srm.pagamento.api.dto.response.LoanResponse;
import org.springframework.data.domain.Page;

public interface PaymentService {

    LoanResponse makePayment(Long loanId);


}
