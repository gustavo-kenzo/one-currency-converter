package br.com.gustavokenzo.currencyConverter.domain.exceptions;

public class ApiKeyNotFoundException extends CurrencyConverterException {

    public ApiKeyNotFoundException() {
        super("API key not found in environment variables", "Configure the API key in your operating system's environment variables. README: xxxxxx");
    }
}
