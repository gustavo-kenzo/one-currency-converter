package br.com.gustavokenzo.currencyConverter.presentation;

public class MenuDisplay {
    public static void welcomeMessage() {
        System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]\n");
    }

    public static void options() {
        System.out.println("1) Dólar =>> Peso Argentino");
        System.out.println("2) Peso Argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real Brasileiro");
        System.out.println("4) Real Brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Peso Colombiano");
        System.out.println("6) Peso Colombiano =>> Dólar");
        System.out.println("7) Sair");

        System.out.print("Escolha um opção válida: ");
    }
}
