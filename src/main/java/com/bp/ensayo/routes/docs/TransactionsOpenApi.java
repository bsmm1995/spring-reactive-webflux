package com.bp.ensayo.routes.docs;


import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransferDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations({
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/transactions/credit",
                operation =
                @Operation(
                        description = "Realizar un depósito.",
                        operationId = "makeDeposit",
                        tags = "Transacciones",
                        requestBody =
                        @RequestBody(
                                description = "Body para realizar un depósito.",
                                required = true,
                                content = @Content(schema = @Schema(implementation = Transaction.class,
                                        requiredProperties = {"accountNumber", "amount"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Depósito realizado exitosamente.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Transaction.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/transactions/debit",
                operation =
                @Operation(
                        description = "Realizar un retiro.",
                        operationId = "makeWithdrawal",
                        tags = "Transacciones",
                        requestBody =
                        @RequestBody(
                                description = "Body para realizar un retiro.",
                                required = true,
                                content = @Content(schema = @Schema(implementation = Transaction.class,
                                        requiredProperties = {"accountNumber", "amount"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Retiro realizado de forma exitosa.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = Transaction.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.POST,
                path = "/transactions/transfer",
                operation =
                @Operation(
                        description = "Realizar una transferencia.",
                        operationId = "makeTransfer",
                        tags = "Transacciones",
                        requestBody =
                        @RequestBody(
                                description = "Body para realizar una transferencia.",
                                required = true,
                                content = @Content(schema = @Schema(implementation = TransferDTO.class,
                                        requiredProperties = {"accountNumberOrigin", "accountNumberDestination", "amount"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Transferencia realizada de forma exitosa.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = TransferDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/transactions/summary/{account-number}",
                operation =
                @Operation(
                        description = "Obtener el reporte de transacciones de una cuenta",
                        operationId = "getSummary",
                        tags = "Transacciones",
                        parameters = {
                                @Parameter(name = "account-number", in = ParameterIn.PATH)
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Todas las transacciones de una cuenta.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))
                                        })
                        }))
})
public @interface TransactionsOpenApi {
}
