package com.br.srm.emprestimo.api.utils;

import com.br.srm.emprestimo.api.enumeration.TypeIdentifier;
import com.br.srm.emprestimo.api.exception.BaseException;
import org.springframework.http.HttpStatus;

public class Validators {

    public static void validateIdentifier(String identifier){
        TypeIdentifier typeIdentifier = TypeIdentifier.getByIndentifier(identifier);
        switch (typeIdentifier){
            case AP:
                if(identifier.trim().length() != 10){
                    throw new BaseException(HttpStatus.BAD_REQUEST, "Idenficador invalido.");
                }
                String lastChar = identifier.substring(identifier.length() - 1);
                if(identifier.substring(0, identifier.length() - 2).contains(lastChar)){
                    throw new BaseException(HttpStatus.BAD_REQUEST, "Idenficador invalido.");
                }
                break;
            case EU:
                if(identifier.trim().length() != 8){
                    throw new BaseException(HttpStatus.BAD_REQUEST, "Idenficador invalido.");
                }
                break;
            case PF:
                break;
            case PJ:
                break;
        }
    }
}
