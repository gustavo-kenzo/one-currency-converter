package br.com.gustavokenzo.currencyConverter.infra.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientAdapter {
    private static final HttpClient client = HttpClient.newHttpClient();

    public String get(String url) throws IOException, InterruptedException {
        if (url == null || url.isBlank())
            throw new RuntimeException("URL can't be empty");

        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200)
            throw new RuntimeException("HTTP " + response.statusCode() + ": " + response.body());

        return response.body();

    }
}
