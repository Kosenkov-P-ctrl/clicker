package com.kosenkovps.clicker.service;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    private static final String ABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] AbcToChar = ABC.toCharArray();
    private final int base = AbcToChar.length;

    public String encode(long input){
        StringBuilder encoded = new StringBuilder();
        if(input == 0) {
            return String.valueOf(AbcToChar[0]);
        }
        while (input > 0) {
            encoded.append(AbcToChar[(int) (input % base)]);
            input = input / base;
        }
        return encoded.reverse().toString();
    }

    public long decode(String input) {
        char[] inputChar = input.toCharArray();
        int length = inputChar.length;
        int decoded = 0;
        int counter = 1;
        for (char c : inputChar) {
            decoded += ABC.indexOf(c) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
