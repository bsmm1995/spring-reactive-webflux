package com.bp.ensayo.routes;

import com.bp.ensayo.handler.AccountHandler;
import com.bp.ensayo.handler.CustomerHandler;
import com.bp.ensayo.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {
    @Bean
    @AccountsOpenApi
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
    @TransactionsOpenApi
    public RouterFunction<ServerResponse> transactionsRoutes(TransactionHandler handler) {
        return route().path("/transactions", builder -> builder
                .POST("/credit", handler::makeDeposit)
                .POST("/debit", handler::makeWithdrawal)
                .POST("/transfer", handler::makeTransfer)
                .GET("/summary/{account-number}", handler::getSummary)
        ).build();
    }
}
