package com.br.srm.pagamento.api.listner;

import com.br.srm.pagamento.api.message.PaymentMessage;
import com.br.srm.pagamento.api.model.Loan;
import com.br.srm.pagamento.api.repository.LoanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Log4j2
@AllArgsConstructor
public class PaymentListener {

    private LoanRepository loanRepository;

    @KafkaListener(topics = "payment-topic", groupId = "payment-group", containerFactory = "jsonContainerFactory")
    public void consumeMessage(@Payload PaymentMessage paymentMessage){
        log.info("Reading payment: {}", paymentMessage);
        if(nonNull(paymentMessage)){
            Optional<Loan> loan = loanRepository.findById(paymentMessage.getLoanId());
            if(loan.isPresent()){
                loan.get().setPaymentStatus(paymentMessage.getStatusPayment());
                loanRepository.save(loan.get());
            }
        }
    }
}