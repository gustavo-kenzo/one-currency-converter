package br.com.gustavokenzo.currencyConverter.presentation.cli;

import br.com.gustavokenzo.currencyConverter.application.dto.ExchangeRateDTO;
import br.com.gustavokenzo.currencyConverter.application.services.ExchangeRateMapper;
import br.com.gustavokenzo.currencyConverter.domain.entities.ExchangeRate;
import br.com.gustavokenzo.currencyConverter.infra.http.ExchangeRateUrlBuilder;
import br.com.gustavokenzo.currencyConverter.infra.http.HttpClientAdapter;

import java.io.IOException;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClientAdapter client = new HttpClientAdapter();
        String url = ExchangeRateUrlBuilder.buildURL("933f32e3d41a0cd24f3cc88d", "USD");
        ExchangeRateMapper exchangeMapper = new ExchangeRateMapper(client);

        ExchangeRateDTO dto = exchangeMapper.getDTO(client.get(url));
//        System.out.println(dto);
        List<ExchangeRate> entities = exchangeMapper.getExchange(dto);
        System.out.println(entities);
//        ExchangeRate exchangeRate = entities.stream().filter(x -> x.getExchangeCode().equals("EUA")).findFirst().orElse(null);

//        System.out.println(exchangeRate);

    }
}
