package com.tuaev.astronauts.request;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface RequestService {

    CompletableFuture<Map<String, String>> getClientsPerformance() throws ExecutionException, InterruptedException;
}
