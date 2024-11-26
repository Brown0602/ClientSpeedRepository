package com.tuaev.astronauts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DefaultWebClientService implements WebClientService {

    private final WebClient webClient;
    private final Logger logger = Logger.getLogger(DefaultWebClientService.class.getName());

    @Override
    public String send() {
        long start = System.currentTimeMillis();
        Mono<String> response = webClient.get()
                .uri("http://api.open-notify.org/astros.json")
                .retrieve()
                .bodyToMono(String.class);
        response.block();
        long end = System.currentTimeMillis();
        logger.info("WebClient закончил работу!");
        return end - start + "ms";
    }
}
