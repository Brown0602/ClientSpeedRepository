package com.tuaev.astronauts.controllers;

import com.tuaev.astronauts.request.Request;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class AstronautsController {

    private final Request requests;

    @GetMapping("clients")
    public ResponseEntity<Map<String, String>> getRequestTime() throws ExecutionException, InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(requests.getClientsPerformance().get());
    }
}
