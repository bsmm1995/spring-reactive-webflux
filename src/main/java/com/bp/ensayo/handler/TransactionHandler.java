package com.bp.ensayo.handler;

import com.bp.ensayo.service.TransactionService;
import com.bp.ensayo.service.dto.Transaction;
import com.bp.ensayo.service.dto.TransactionDTO;
import com.bp.ensayo.service.dto.TransferDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionHandler {
    private final TransactionService transactionService;

    public Mono<ServerResponse> makeDeposit(ServerRequest serverRequest) {
        final Mono<Transaction> mono = serverRequest.bodyToMono(Transaction.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono.flatMap(transactionService::makeDeposit), Transaction.class);
    }

    public Mono<ServerResponse> makeWithdrawal(ServerRequest serverRequest) {
        final Mono<Transaction> mono = serverRequest.bodyToMono(Transaction.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono.flatMap(transactionService::makeWithdrawal), Transaction.class);
    }

    public Mono<ServerResponse> makeTransfer(ServerRequest serverRequest) {
        final Mono<TransferDTO> mono = serverRequest.bodyToMono(TransferDTO.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono.flatMap(transactionService::makeTransfer), TransferDTO.class);
    }

    public Mono<ServerResponse> getSummary(ServerRequest serverRequest) {
        var accountNumber = serverRequest.pathVariable("account-number");
        return ServerResponse.ok()
                .body(fromPublisher(transactionService.getSummary(accountNumber), TransactionDTO.class));
    }
}