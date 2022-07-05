package br.com.alura.comex.utils;

import java.math.BigDecimal;

public class GenerateRandomNumber {
    public static BigDecimal generateRandomNumber() {
        return BigDecimal.valueOf(Math.random() * 1000000);
    }
}
