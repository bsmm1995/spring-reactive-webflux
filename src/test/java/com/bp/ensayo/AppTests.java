package com.bp.ensayo;

import com.bp.ensayo.service.dto.AccountDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@AutoConfigureWebTestClient //1.Simulado, sin levantar el servidor Nety
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)  //@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AppTests {
    @Autowired
    private WebTestClient client;
    private final String URL = "http://localhost:8080/accounts";

    @Test
    void listarCuentas() {
        client.get()
                .uri(URL)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(AccountDTO.class)
                .consumeWith(response -> {
                    List<AccountDTO> accounts = response.getResponseBody();
                    assert accounts != null;
                    accounts.forEach(p -> {
                        System.out.println("Cuenta: " + p.getAccountNumber());
                    });
                    Assertions.assertThat(accounts.size() == 1).isEqualTo(Boolean.TRUE);
                });
    }

    @Test
    void obtenerCuenta() {
        client.get()
                .uri(URL.concat("/1"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(AccountDTO.class)
                .consumeWith(response -> {
                    AccountDTO account = response.getResponseBody();
                    assert account != null;
                    Assertions.assertThat(account.getId() == 1).isEqualTo(Boolean.TRUE);
                });
    }

}
