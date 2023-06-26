package com.bp.ensayo.routes;

import com.bp.ensayo.handler.AccountHandler;
import com.bp.ensayo.handler.CustomerHandler;
import com.bp.ensayo.handler.TransactionHandler;
import com.bp.ensayo.service.dto.AccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {
    @Bean
    @RouterOperations({@RouterOperation(path = "/get/AccountDTOs", produces = {
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = AccountHandler.class, beanMethod = "getAllAccountDTO",

            operation = @Operation(operationId = "getAllAccountDTO", responses = {
                    @ApiResponse(responseCode = "200", description = "get all AccountDTO successfully.", content = @Content(schema = @Schema(implementation = AccountDTO.class)))})),
            @RouterOperation(path = "/get/AccountDTOs/{AccountDTO_id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, beanClass = AccountHandler.class, beanMethod = "findById", operation = @Operation(operationId = "findById", responses = {
                    @ApiResponse(responseCode = "200", description = "get AccountDTO successfully.", content = @Content(schema = @Schema(implementation = AccountDTO.class))),
                    @ApiResponse(responseCode = "404", description = "AccountDTO not found by given id.")}, parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "AccountDTO_id")})),

            @RouterOperation(
                    path = "/add/AccountDTO",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.POST,
                    beanClass = AccountHandler.class,
                    beanMethod = "addAccountDTO",
                    operation = @Operation(
                            operationId = "addAccountDTO",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "successful operation",
                                            content = @Content(schema = @Schema(
                                                    implementation = String.class
                                            ))
                                    )
                            },
                            requestBody = @RequestBody(
                                    content = @Content(schema = @Schema(
                                            implementation = AccountDTO.class
                                    ))
                            )

                    )


            )


    })
    public RouterFunction<ServerResponse> accountsRoutes(AccountHandler handler) {
        return route().path("/accounts", builder -> builder
                .GET("", handler::getAll)
                .GET("/{id}", handler::getById)
                .POST("", handler::create)
                .PUT("/id", handler::update)
                .DELETE("/{id}", handler::deleteById)
        ).build();
    }

    @Bean
    @RouterOperations
    public RouterFunction<ServerResponse> customersRoutes(CustomerHandler handler) {
        return route().path("/customers", builder -> builder
                .GET("", handler::getAll)
                .GET("/{id}", handler::getById)
                .POST("", handler::create)
                .PUT("/id", handler::update)
                .DELETE("/{id}", handler::deleteById)
        ).build();
    }

    @Bean
    @RouterOperations
    public RouterFunction<ServerResponse> transactionsRoutes(TransactionHandler handler) {
        return route().path("/transactions", builder -> builder
                .POST("/credit", handler::makeDeposit)
                .POST("/debit", handler::makeWithdrawal)
                .POST("/transfer", handler::makeTransfer)
                .GET("/summary/{account-number}", handler::getSummary)
        ).build();
    }
}
