package com.br.srm.emprestimo.api.enumeration;

import com.br.srm.emprestimo.api.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TypeIdentifier {
    PF("Pessoa Física", 11),
    PJ("Pessoa Jurídica", 14),
    EU("Estudante Universitário", 8),
    AP("Aposentado", 10);

    String name;
    Integer sizeIndentifier;

    public static TypeIdentifier getByIndentifier(String identifier){
        return Arrays.stream(TypeIdentifier.values()).filter(ti -> ti.sizeIndentifier.equals(identifier.length()))
                .findAny()
                .orElseThrow(() -> new BaseException(HttpStatus.BAD_REQUEST, "Tamanho do identificador inválido."));
    }
}
