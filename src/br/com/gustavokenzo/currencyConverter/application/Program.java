package br.com.gustavokenzo.currencyConverter.application;

import br.com.gustavokenzo.currencyConverter.domain.services.ExchangeRateService;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExchangeRateService service = new ExchangeRateService("933f32e3d41a0cd24f3cc88d");
        System.out.println(service.buscarTaxa("USD", "brl"));
        System.out.println(service.convert("usd", "brl", 10));

    }
}
