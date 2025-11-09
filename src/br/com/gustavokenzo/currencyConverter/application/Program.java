package br.com.gustavokenzo.currencyConverter.application;

import br.com.gustavokenzo.currencyConverter.domain.services.ExchangeRateService;
import br.com.gustavokenzo.currencyConverter.presentation.MenuDisplay;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    private static Scanner sc;
    private static ExchangeRateService service;

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);
        sc = new Scanner(System.in);
        int choice = 0;

        MenuDisplay.welcomeMessage();

        String apiKey = getApiKey();
        service = new ExchangeRateService(apiKey);

        while (true) {
            MenuDisplay.options();

            choice = sc.nextInt();
            if (choice == 7) {
                System.out.println("SAINDO...");
                return;
            }
            System.out.println("*********************************************\n");

            try {
                processConversion(choice);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static void processConversion(int choice) {
        String baseCurrency = null;
        String targetCurrency = null;

        switch (choice) {
            case 1 -> {
                baseCurrency = "usd";
                targetCurrency = "ars";
            }
            case 2 -> {
                baseCurrency = "ars";
                targetCurrency = "usd";
            }
            case 3 -> {
                baseCurrency = "usd";
                targetCurrency = "brl";
            }
            case 4 -> {
                baseCurrency = "brl";
                targetCurrency = "usd";
            }
            case 5 -> {
                baseCurrency = "usd";
                targetCurrency = "cop";
            }
            case 6 -> {
                baseCurrency = "cop";
                targetCurrency = "usd";
            }
            default -> {
                throw new RuntimeException(choice + " IS AN INVALID OPTION\n");
            }
        }
        System.out.print("Digite o valor que deseja converter: ");
        double amount = sc.nextDouble();
        double result = service.convert(baseCurrency, targetCurrency, amount);
        System.out.printf("Valor %.2f [%s] corresponde ao valor final de =>>> %.5f [%s]\n\n", amount, baseCurrency.toUpperCase(), result, targetCurrency.toUpperCase());
    }

    private static String getApiKey() {
        String apiKey = System.getenv("EXCHANGE_RATE_API_KEY");
        if (apiKey != null) {
            System.out.println("✓ API Key loaded from environment variable");
            return apiKey.trim();
        } else {
            throw new RuntimeException("✕ API Key not found in the environment variable. Follow the instructions in the README: https://www.google.com/");
        }
    }
}
