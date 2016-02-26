package com.worldpay.challenge;

/**
 * An interface used to convert an int into a text representation. Implementations may be in any language
 * and may impose limits on the range of ints that can be converted.
 */
public interface NumberToWords {

    /**
     * Converts an int to a word representation
     * @param i number to convert
     * @return String representing the number in words
     */
    String toWords(int i);
}
