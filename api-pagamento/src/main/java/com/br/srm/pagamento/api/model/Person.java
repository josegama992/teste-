package com.br.srm.pagamento.api.model;

import com.br.srm.pagamento.api.enumeration.TypeIdentifier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pessoa")
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "identificador", nullable = false)
    private String identifier;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate birthDate;

    @Column(name = "tipo_identificador", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeIdentifier typeIdentifier;

    @Column(name = "valor_min_mensal", nullable = false)
    private BigDecimal minimumMonthlyValue;

    @Column(name = "valor_max_emprestimo", nullable = false)
    private BigDecimal maximumLoanValue;
}
