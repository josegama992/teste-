package com.br.srm.pagamento.api.model;

import com.br.srm.pagamento.api.model.Person;
import com.br.srm.pagamento.api.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "emprestimo")
public class Loan {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", nullable = false)
    private Person person;

    @Column(name = "valor_emprestimo", nullable = false)
    private BigDecimal loanValue;

    @Column(name = "numero_parcelas", nullable = false)
    private Integer numberParcels;

    @Column(name = "status_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate createDate;
}
