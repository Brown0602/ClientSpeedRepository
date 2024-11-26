package com.tuaev.astronauts.clients;

public enum Clients {

    REST_TEMPLATE("RestTemplate"),
    WEB_CLIENT("WebClient"),
    HTTP_CLIENT("HttpClient"),
    REST_CLIENT("RestClient"),
    OPEN_FEIGN("OpenFeign");

    private final String name;

    Clients(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
