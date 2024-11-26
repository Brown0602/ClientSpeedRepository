package com.tuaev.astronauts.services;

import java.io.IOException;

public interface HttpClientService {

    String sendRequest() throws IOException, InterruptedException;
}
