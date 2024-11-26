package com.tuaev.astronauts.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "openFeignClient", url = "http://api.open-notify.org/astros.json")
public interface OpenFeignClient{

    @GetMapping()
    String send();
}
