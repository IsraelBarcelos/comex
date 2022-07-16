package br.com.alura.comex.comercial.utils;

import java.util.ArrayList;
import java.util.List;

public class IterableToArrayList<T> {

    public static <T> List<T> execute(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
}
