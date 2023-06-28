package com.bp.ensayo.routes.docs;


import com.bp.ensayo.service.dto.AccountDTO;
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
                path = "/accounts",
                operation =
                @Operation(
                        description = "Crear una cuenta.",
                        operationId = "create",
                        tags = "Accounts",
                        requestBody =
                        @RequestBody(
                                description = "Body para crear una cuenta.",
                                required = true,
                                content = @Content(schema = @Schema(implementation = AccountDTO.class,
                                        requiredProperties = {"accountNumber", "amount", "status", "type"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cuenta creada exitosamente.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = AccountDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/accounts/{id}",
                operation =
                @Operation(
                        description = "Actualizar una cuenta.",
                        operationId = "update",
                        tags = "Accounts",
                        requestBody =
                        @RequestBody(
                                description = ".",
                                required = true,
                                content = @Content(schema = @Schema(implementation = AccountDTO.class,
                                        requiredProperties = {"accountNumber", "amount", "status", "type"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cuenta actualizada exitosamente.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = AccountDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/accounts",
                operation =
                @Operation(
                        description = "Obtener todas las cuentas.",
                        operationId = "getAll",
                        tags = "Accounts",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Todas las cuentas.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class)))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/accounts/{id}",
                operation =
                @Operation(
                        description = "Obtener una cuenta por su ID.",
                        operationId = "getById",
                        tags = "Accounts",
                        parameters = {
                                @Parameter(name = "id", in = ParameterIn.PATH)
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cuenta encontrada.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = AccountDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.DELETE,
                path = "/accounts/{id}",
                operation =
                @Operation(
                        description = "Eliminar una cuenta por su ID.",
                        operationId = "delete",
                        tags = "Accounts",
                        parameters = {
                                @Parameter(name = "id", in = ParameterIn.PATH)
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cuenta eliminada con éxito.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE
                                                )})
                        }))
})
public @interface AccountsOpenApi {
}
