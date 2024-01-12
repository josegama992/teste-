package com.br.srm.emprestimo.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

}