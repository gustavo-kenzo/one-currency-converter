package br.com.gustavokenzo.currencyConverter.domain.exceptions;

public class CurrencyConverterException extends RuntimeException {
    private final String suggestion;

    public CurrencyConverterException(String msg, String suggestion) {
        super(msg);
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
