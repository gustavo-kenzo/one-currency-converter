package br.com.gustavokenzo.currencyConverter.application;

import br.com.gustavokenzo.currencyConverter.domain.exceptions.ApiKeyNotFoundException;
import br.com.gustavokenzo.currencyConverter.domain.exceptions.CurrencyConverterException;
import br.com.gustavokenzo.currencyConverter.domain.services.ExchangeRateService;
import br.com.gustavokenzo.currencyConverter.presentation.MenuDisplay;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    private static ExchangeRateService service;

    public static void main(String[] args) throws IOException, InterruptedException {
        Locale.setDefault(Locale.US);

        try (Scanner sc = new Scanner(System.in)) {
            int choice = 0;

            MenuDisplay.welcomeMessage();

            String apiKey = getApiKey();
            service = new ExchangeRateService(apiKey);


            while (true) {
                MenuDisplay.options();
                try {
                    choice = sc.nextInt();
                    if (choice == 7) {
                        System.out.println("SAINDO...");
                        return;
                    }
                    System.out.println("*********************************************\n");

                    processConversion(choice, sc);
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ ERROR: " + e.getMessage());
                    System.out.println("\uD83D\uDCA1 SUGESTION: Check the entered values and try again" + "\n");
                } catch (CurrencyConverterException e) {
                    System.out.println("❌ ERROR: " + e.getMessage());
                    System.out.println("\uD83D\uDCA1 SUGESTION: " + e.getSuggestion() + "\n");
                }catch (InputMismatchException e){
                    sc.nextLine();
                    System.out.println("❌ INPUT ERROR: Invalid input. Please enter a number\n");
                }
                catch (RuntimeException e) {
                    System.out.print("❌ UNEXPECTED ERROR: " + e.getMessage());
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("❌ ERROR: " + e.getMessage());
        } catch (ApiKeyNotFoundException e) {
            System.out.print("❌ ERROR: " + e.getMessage());
            System.out.println("\uD83D\uDCA1 SUGESTION: " + e.getSuggestion() + "\n");
        }
    }

    private static void processConversion(int choice, Scanner sc) {
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
                throw new IllegalArgumentException(choice + " IS AN INVALID OPTION");
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
            throw new ApiKeyNotFoundException();
        }
    }
}
