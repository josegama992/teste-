package com.br.srm.emprestimo.api.controller.openapi;

import com.br.srm.emprestimo.api.dto.request.PersonRequest;
import com.br.srm.emprestimo.api.dto.response.PersonResponse;
import com.br.srm.emprestimo.api.exception.ExceptionProblem;
import com.br.srm.emprestimo.api.filter.PersonFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PersonControllerOpenApi {

    @Operation(summary = "Busca todas as pessoas.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<Page<PersonResponse>> findAll(@ParameterObject PersonFilter filter, @ParameterObject Pageable pageable);

    @Operation(summary = "Busca pessoa por id.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id);

    @Operation(summary = "Cria uma nova pessoa.", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> create(@RequestBody @Valid PersonRequest request);

    @Operation(summary = "Atualiza uma pessoa por id.", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> update(@PathVariable("id") Long id,
                                          @RequestBody @Valid PersonRequest request);

    @Operation(summary = "Deleta uma pessoa por id.", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    void delete(@PathVariable("id") Long id);
}
