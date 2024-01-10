package com.br.srm.emprestimo.api.controller.openapi;

import com.br.srm.emprestimo.api.dto.request.LoanRequest;
import com.br.srm.emprestimo.api.dto.response.LoanResponse;
import com.br.srm.emprestimo.api.dto.response.PersonResponse;
import com.br.srm.emprestimo.api.exception.ExceptionProblem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LoanControllerOpenApi {

    @Operation(summary = "Lista emprestimos de uma pessoa.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<Page<LoanResponse>> list(String identifier, @ParameterObject Pageable pageable);

    @Operation(summary = "Realiza um emprestimo.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<LoanResponse> makeLoan(String identifier, LoanRequest request);
}
