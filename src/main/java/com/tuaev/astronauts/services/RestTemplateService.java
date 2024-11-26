package com.tuaev.astronauts.services;

import java.io.IOException;

public interface RestTemplateService {

    String sendRequest() throws IOException, InterruptedException;
}
