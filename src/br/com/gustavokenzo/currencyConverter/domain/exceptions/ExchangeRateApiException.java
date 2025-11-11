package br.com.gustavokenzo.currencyConverter.domain.exceptions;

public class ExchangeRateApiException extends CurrencyConverterException {
    public ExchangeRateApiException(String msg, String suggestion) {
        super(msg, suggestion);
    }
}
