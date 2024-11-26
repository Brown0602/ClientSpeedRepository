package com.tuaev.astronauts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DefaultRestTemplateService implements RestTemplateService{

    private final RestTemplate restTemplate;
    private final Logger logger = Logger.getLogger(DefaultRestTemplateService.class.getName());

    @Override
    public String sendRequest() {
        long start = System.currentTimeMillis();
        restTemplate.getForEntity("http://api.open-notify.org/astros.json", String.class);
        long end = System.currentTimeMillis();
        long requestResultRestTemplate = end - start;
        logger.info("RestTemplate закончил работу!");
        return requestResultRestTemplate + "ms";
    }
}
