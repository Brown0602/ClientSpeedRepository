package com.tuaev.astronauts.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class DefaultOpenFeignClientService{

    private final Logger logger = Logger.getLogger(DefaultOpenFeignClientService.class.getName());
    private final OpenFeignClient openFeignClient;

    public String send(){
        long start = System.currentTimeMillis();
        openFeignClient.send();
        long end = System.currentTimeMillis();
        logger.info("OpenFeign закончил работу!");
        return end - start + "ms";
    }
}
