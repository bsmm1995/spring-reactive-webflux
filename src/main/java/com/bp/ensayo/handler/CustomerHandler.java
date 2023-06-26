package com.bp.ensayo.handler;

import com.bp.ensayo.service.CustomerService;
import com.bp.ensayo.service.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerService customerService;

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        var mono = customerService.getById(Long.valueOf(serverRequest.pathVariable("id")))
                .switchIfEmpty(Mono.error(() -> new ResponseStatusException(NOT_FOUND)));
        return ServerResponse.ok().body(fromPublisher(mono, CustomerDTO.class));
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        var page = serverRequest.queryParam("page").orElse("0");
        var size = serverRequest.queryParam("size").orElse("5");

        Flux<CustomerDTO> accounts = customerService.getAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accounts, CustomerDTO.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        Mono<CustomerDTO> postDtoMono = serverRequest.bodyToMono(CustomerDTO.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return postDtoMono.flatMap(postDto ->
                        ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(customerService.save(postDto), CustomerDTO.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        Mono<CustomerDTO> postDtoMono = serverRequest.bodyToMono(CustomerDTO.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return postDtoMono.flatMap(postDto ->
                        ServerResponse
                                .status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(customerService.update(id, postDto), CustomerDTO.class))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse
                .status(HttpStatus.NO_CONTENT)
                .build(customerService.delete(id))
                .switchIfEmpty(notFound);
    }
}