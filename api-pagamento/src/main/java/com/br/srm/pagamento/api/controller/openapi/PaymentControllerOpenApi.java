package com.br.srm.pagamento.api.controller.openapi;

import com.br.srm.pagamento.api.dto.response.LoanResponse;
import com.br.srm.pagamento.api.dto.response.PersonResponse;
import com.br.srm.pagamento.api.exception.ExceptionProblem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentControllerOpenApi {

    @Operation(summary = "Realiza o pagamento de um emprestimo.", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionProblem.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<LoanResponse> makePayment(Long id);
}
