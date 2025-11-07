package br.com.gustavokenzo.currencyConverter.application.services;

import br.com.gustavokenzo.currencyConverter.application.dto.ExchangeRateDTO;
import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.http.HttpClientAdapter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

public class ExchangeRateMapper {
    private final HttpClientAdapter client;
    private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public ExchangeRateMapper(HttpClientAdapter client) {
        this.client = client;
    }

    //Mapping the request to the DTO ExchangeRateDTO.
    public ExchangeRateDTO getDTO(String json) throws IOException, InterruptedException {
        ExchangeRateDTO exchangeDTO = gson.fromJson(json, ExchangeRateDTO.class);
        return exchangeDTO;
    }

    //Mapping the DTO to entity ExchangeRate
    public List<ExchangeRate> getExchange(ExchangeRateDTO dto) throws IOException, InterruptedException {
        List<ExchangeRate> exchangeRates = dto.conversionRates()
                .entrySet()
                .parallelStream()
                .map(e -> new ExchangeRate(e.getKey(), e.getValue()))
                .toList();

        return exchangeRates;
    }

}
