package com.tuaev.astronauts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DefaultHttpClientService implements HttpClientService{

    private final Logger logger = Logger.getLogger(DefaultHttpClientService.class.getName());

    @Override
    public String sendRequest() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.open-notify.org/astros.json"))
                .build();
        long start = System.currentTimeMillis();
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        long end = System.currentTimeMillis();
        long requestResultHttpClient = end - start;
        logger.info("HttpClient закончил работу!");
        return requestResultHttpClient + "ms";
    }
}
