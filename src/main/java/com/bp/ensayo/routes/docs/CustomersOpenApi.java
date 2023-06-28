package com.bp.ensayo.routes.docs;


import com.bp.ensayo.service.dto.CustomerDTO;
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
                path = "/customers",
                operation =
                @Operation(
                        description = "Crear un cliente.",
                        operationId = "create",
                        tags = "Clientes",
                        requestBody =
                        @RequestBody(
                                description = "Body para crear un cliente.",
                                required = true,
                                content = @Content(schema = @Schema(implementation = CustomerDTO.class,
                                        requiredProperties = {"identification", "name", "lastname", "identificationType"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cliente creado exitosamente.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = CustomerDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.PUT,
                path = "/customers/{id}",
                operation =
                @Operation(
                        description = "Actualizar un cliente.",
                        operationId = "update",
                        tags = "Clientes",
                        parameters = {
                                @Parameter(name = "id", in = ParameterIn.PATH)
                        },
                        requestBody =
                        @RequestBody(
                                description = ".",
                                required = true,
                                content = @Content(schema = @Schema(implementation = CustomerDTO.class,
                                        requiredProperties = {"identification", "name", "lastname", "identificationType"}))),
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cliente actualizado exitosamente.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = CustomerDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/customers",
                operation =
                @Operation(
                        description = "Obtener todos los clientes.",
                        operationId = "getAll",
                        tags = "Clientes",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Todos los clientes.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.GET,
                path = "/customers/{id}",
                operation =
                @Operation(
                        description = "Obtener un cliente por su ID.",
                        operationId = "getById",
                        tags = "Clientes",
                        parameters = {
                                @Parameter(name = "id", in = ParameterIn.PATH)
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "Cliente encontrado.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                        schema = @Schema(implementation = CustomerDTO.class))
                                        })
                        })),
        @RouterOperation(
                method = RequestMethod.DELETE,
                path = "/customers/{id}",
                operation =
                @Operation(
                        description = "Eliminar un cliente por su ID.",
                        operationId = "delete",
                        tags = "Clientes",
                        parameters = {
                                @Parameter(name = "id", in = ParameterIn.PATH)
                        },
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "cliente eliminado con éxito.",
                                        content = {
                                                @Content(
                                                        mediaType = MediaType.APPLICATION_JSON_VALUE
                                                )})
                        }))
})
public @interface CustomersOpenApi {
}
