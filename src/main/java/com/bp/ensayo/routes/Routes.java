package com.bp.ensayo.routes;

import com.bp.ensayo.handler.AccountHandler;
import com.bp.ensayo.handler.CustomerHandler;
import com.bp.ensayo.handler.TransactionHandler;
import org.springdoc.webflux.core.fn.SpringdocRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> accountsRoutes(AccountHandler handler) {
        return SpringdocRouteBuilder.route().GET("/accounts", accept(APPLICATION_JSON), handler::getAll, ops -> ops.beanClass(AccountHandler.class).beanMethod("getAll")).build()
                .and(SpringdocRouteBuilder.route().GET("/accounts/{id}", handler::getById, ops -> ops.beanClass(AccountHandler.class).beanMethod("getById")).build())
                .and(SpringdocRouteBuilder.route().POST("/accounts", handler::create, ops -> ops.beanClass(AccountHandler.class).beanMethod("create")).build())
                .and(SpringdocRouteBuilder.route().PUT("/accounts/{id}", handler::update, ops -> ops.beanClass(AccountHandler.class).beanMethod("update")).build())
                .and(SpringdocRouteBuilder.route().DELETE("/accounts/{id}", handler::deleteById, ops -> ops.beanClass(AccountHandler.class).beanMethod("deleteById")).build());
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
    @TransactionsApiInfo
    public RouterFunction<ServerResponse> transactionsRoutes(TransactionHandler handler) {
        return route().path("/transactions", builder -> builder
                .POST("/credit", handler::makeDeposit)
                .POST("/debit", handler::makeWithdrawal)
                .POST("/transfer", handler::makeTransfer)
                .GET("/summary/{account-number}", handler::getSummary)
        ).build();
    }
}
