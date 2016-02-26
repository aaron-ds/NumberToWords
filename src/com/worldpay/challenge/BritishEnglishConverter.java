package com.worldpay.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BritishEnglishConverter implements NumberToWords {

    private static final int ONE_THOUSAND = 1000;
    private static final int MAX_VALUE = 999_999_999;
    private static final int MIN_VALUE = 0;
    private static final String NO_SCALE = "NoScale";
    private static final String DISPLAY_ZERO = "DisplayZero";

    private static final String[] UNITS = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] SCALE = {NO_SCALE, "thousand", "million"};


    public String toWords(int i) {

        if (i < MIN_VALUE || i > MAX_VALUE) {
            throw new IllegalArgumentException("Number out of range - should be between 0 - 999,999,999 inclusive");
        }

        if (i < ONE_THOUSAND) {
            return hundredsFormatter(i);
        }

        return formatByDecomposition(i);
    }

    private String formatByDecomposition(int i) {
        List<String> numberComponents = new ArrayList<>();

        for (int scaleIndex = 0; i != 0; scaleIndex++) {
            int lastThreeDigits = i % ONE_THOUSAND;
            String numberComponent = hundredsFormatter(lastThreeDigits, SCALE[scaleIndex]);
            i /= ONE_THOUSAND;
            //prefix any number ending with a number between 1 and 100 with "and"
            if (scaleIndex == 0 && isBetween1and100(lastThreeDigits)) {
                numberComponent = "and " + numberComponent;
            }
            numberComponents.add(numberComponent);
        }

        return orderResult(numberComponents);
    }

    private boolean isBetween1and100(int i) {
        return i > 0 && i < 100;
    }

    private String hundredsFormatter(int i) {
        return hundredsFormatter(i, DISPLAY_ZERO);
    }

    private String hundredsFormatter(int i, String scale) {
        if (i == 0) {
            return DISPLAY_ZERO.equals(scale) ? UNITS[i] : "";
        }

        String word;
        if (i < 20) {
            word = UNITS[i];
        } else if (i < 100) {
            int firstDigit = i / 10;
            int secondDigit = i % 10;
            word = (secondDigit == 0) ? TENS[firstDigit] : TENS[firstDigit] + " " + UNITS[secondDigit];
        } else {
            int firstDigit = i / 100;
            int tensComponent = i % 100;
            word = (tensComponent == 0) ? UNITS[firstDigit] + " hundred" : UNITS[firstDigit] + " hundred and " + hundredsFormatter(tensComponent);
        }

        return (NO_SCALE.equals(scale) || DISPLAY_ZERO.equals(scale)) ? word : word + " " + scale;
    }

    private String orderResult(List<String> numberComponents) {
        StringBuilder sb = new StringBuilder();
        Collections.reverse(numberComponents);
        for (String word : numberComponents) {
            if (sb.length() != 0 && !word.equals("")) {
                sb.append(" ");
            }
            sb.append(word);
        }

        return sb.toString();
    }

}
