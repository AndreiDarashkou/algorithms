package com.study;

import com.study.numerical.NumericalAlgorithms;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrei.darashkou on 2/19/2018.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List<Integer> primeFactors = NumericalAlgorithms.findPrimeFactors(11);
        String primeFactorsString = primeFactors.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        System.out.println(primeFactorsString);

        System.out.println(NumericalAlgorithms.isPrimeNumber(121));
    }

}
