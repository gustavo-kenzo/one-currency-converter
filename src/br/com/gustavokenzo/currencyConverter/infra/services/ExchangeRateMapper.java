package br.com.gustavokenzo.currencyConverter.infra.services;

import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.dto.ExchangeRateDTO;
import br.com.gustavokenzo.currencyConverter.infra.http.ExchangeRateUrlBuilder;
import br.com.gustavokenzo.currencyConverter.infra.http.HttpClientAdapter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class ExchangeRateMapper {
    private final HttpClientAdapter client;
    private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();


    public ExchangeRateMapper(HttpClientAdapter client) {
        this.client = client;
    }

    //Mapping the request to the DTO ExchangeRateDTO.
    private ExchangeRateDTO getDTO(String json) throws IOException, InterruptedException {
        ExchangeRateDTO exchangeDTO = gson.fromJson(json, ExchangeRateDTO.class);
        return exchangeDTO;
    }

    //Mapping the DTO to entity ExchangeRate
    public ExchangeRate getExchange(String json) throws IOException, InterruptedException {
        ExchangeRateDTO dto = getDTO(json);
        ExchangeRate exchangeRate = new ExchangeRate(dto.targetCode(), dto.conversionRate());
        return exchangeRate;
    }

}
