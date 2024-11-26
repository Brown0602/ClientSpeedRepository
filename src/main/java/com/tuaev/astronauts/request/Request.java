package com.tuaev.astronauts.request;

import com.tuaev.astronauts.clients.Clients;
import com.tuaev.astronauts.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class Request implements RequestService {

    private final Logger logger = Logger.getLogger(Request.class.getName());
    private final RestTemplateService restTemplateService;
    private final HttpClientService httpClientService;
    private final WebClientService webClientService;
    private final DefaultOpenFeignClientService defaultOpenFeignClientService;
    private final RestClientService restClientService;

    @Override
    public CompletableFuture<Map<String, String>> getClientsPerformance() throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
        CompletableFuture<String> restClient = getRestClient(map);
        Thread.sleep(3000);
        CompletableFuture<String> openFeignClient = getOpenFeignService(map);
        Thread.sleep(3000);
        CompletableFuture<String> webClient = getWebClient(map);
        Thread.sleep(3000);
        CompletableFuture<String> restTemplate = getRestTemplate(map);
        Thread.sleep(3000);
        CompletableFuture<String> httpClient = getHttpClient(map);
        return CompletableFuture.allOf(restClient, openFeignClient, webClient, restTemplate, httpClient).thenApply(v -> map);
    }

    private CompletableFuture<String> getRestClient(Map<String, String> map){
        return CompletableFuture.supplyAsync(restClientService::send).thenApply(result -> map.put(Clients.REST_CLIENT.getName(), result));
    }

    private CompletableFuture<String> getOpenFeignService(Map<String, String> map){
        return CompletableFuture.supplyAsync(defaultOpenFeignClientService::send).thenApply(result -> map.put(Clients.OPEN_FEIGN.getName(), result));
    }

    private CompletableFuture<String> getHttpClient(Map<String, String> map) {
        return CompletableFuture.supplyAsync(()->
        {
            try {
                return httpClientService.sendRequest();
            } catch (IOException | InterruptedException e) {
                logger.log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
            return null;
        }).thenApply(result -> map.put(Clients.HTTP_CLIENT.getName(), result));
    }

    private CompletableFuture<String> getRestTemplate(Map<String, String> map) {
        return CompletableFuture.supplyAsync(()->
        {
            try {
                return restTemplateService.sendRequest();
            } catch (IOException | InterruptedException e) {
                logger.log(Level.WARNING, e.getMessage());
                Thread.currentThread().interrupt();
            }
            return null;
        }).thenApply(result -> map.put(Clients.REST_TEMPLATE.getName(), result));
    }

    private CompletableFuture<String> getWebClient(Map<String, String> map) {
        return CompletableFuture.supplyAsync(webClientService::send).thenApply(result -> map.put(Clients.WEB_CLIENT.getName(), result));
    }
}
