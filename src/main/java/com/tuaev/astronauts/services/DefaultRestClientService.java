package com.tuaev.astronauts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DefaultRestClientService implements RestClientService{

    private final Logger logger = Logger.getLogger(DefaultRestClientService.class.getName());
    private final RestClient restClient;

    @Override
    public String send() {
        long start = System.currentTimeMillis();
        restClient.get()
                .uri("http://api.open-notify.org/astros.json")
                .retrieve()
                .body(String.class);
        long end = System.currentTimeMillis();
        logger.info("RestClient закончил работу!");
        return end - start + "ms";
    }
}
