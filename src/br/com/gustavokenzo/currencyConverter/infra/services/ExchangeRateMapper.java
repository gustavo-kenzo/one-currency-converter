package br.com.gustavokenzo.currencyConverter.infra.services;

import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.domain.exceptions.ExchangeRateApiException;
import br.com.gustavokenzo.currencyConverter.infra.dto.ExchangeRateDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class ExchangeRateMapper {
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    //Mapping the request to the DTO ExchangeRateDTO.
    private ExchangeRateDTO getDTO(String json) {
        try {
            ExchangeRateDTO exchangeDTO = gson.fromJson(json, ExchangeRateDTO.class);
            return exchangeDTO;
        } catch (JsonSyntaxException e) {
            throw new ExchangeRateApiException("Invalid JSON received from the API" + e.getMessage(), "The API response format may have changed");
        }
    }

    //Mapping the DTO to entity ExchangeRate
    public ExchangeRate getExchange(String json) {
        if (json == null || json.isBlank())
            throw new IllegalArgumentException("JSON can't be empty");

        ExchangeRateDTO dto = getDTO(json);

        if (dto.conversionRate() == null || dto.conversionRate() <= 0)
            throw new ExchangeRateApiException("Invalid conversion rate in the response","The API may have returned invalid data.");

        ExchangeRate exchangeRate = new ExchangeRate(dto.baseCode(), dto.targetCode(), dto.conversionRate());
        return exchangeRate;
    }

}
