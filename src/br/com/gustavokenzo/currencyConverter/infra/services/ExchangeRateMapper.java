package br.com.gustavokenzo.currencyConverter.infra.services;

import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.dto.ExchangeRateDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class ExchangeRateMapper {
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    //Mapping the request to the DTO ExchangeRateDTO.
    private ExchangeRateDTO getDTO(String json) {
        ExchangeRateDTO exchangeDTO = gson.fromJson(json, ExchangeRateDTO.class);
        return exchangeDTO;
    }

    //Mapping the DTO to entity ExchangeRate
    public ExchangeRate getExchange(String json) throws IOException, InterruptedException {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON não pode ser vazio");
        }
        ExchangeRateDTO dto = getDTO(json);
        if (dto.conversionRate() == null || dto.conversionRate() <= 0) {
            throw new RuntimeException("Taxa de conversão inválida na resposta");
        }
        ExchangeRate exchangeRate = new ExchangeRate(dto.baseCode(), dto.targetCode(), dto.conversionRate());
        return exchangeRate;
    }

}
