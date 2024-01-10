package com.br.srm.emprestimo.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String identifier;
    @NotNull
    private LocalDate birthDate;
}
