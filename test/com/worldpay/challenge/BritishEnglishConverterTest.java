package com.worldpay.challenge;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BritishEnglishConverterTest {

    private NumberToWords n2w = new BritishEnglishConverter();

    @Test
    public void testNumbersUpToNineteen() {
        Map<Integer, String> ints = new HashMap<>();
        ints.put(0, "zero");
        ints.put(1, "one");
        ints.put(2, "two");
        ints.put(3, "three");
        ints.put(4, "four");
        ints.put(5, "five");
        ints.put(6, "six");
        ints.put(7, "seven");
        ints.put(8, "eight");
        ints.put(9, "nine");
        ints.put(10, "ten");
        ints.put(11, "eleven");
        ints.put(12, "twelve");
        ints.put(13, "thirteen");
        ints.put(14, "fourteen");
        ints.put(15, "fifteen");
        ints.put(16, "sixteen");
        ints.put(17, "seventeen");
        ints.put(18, "eighteen");
        ints.put(19, "nineteen");

        bulkAssert(ints);
    }

    @Test
    public void testTens() {
        Map<Integer, String> ints = new HashMap<>();
        ints.put(20, "twenty");
        ints.put(30, "thirty");
        ints.put(40, "fourty");
        ints.put(50, "fifty");
        ints.put(60, "sixty");
        ints.put(70, "seventy");
        ints.put(80, "eighty");
        ints.put(90, "ninety");

        bulkAssert(ints);
    }

    @Test
    public void testTwoDigits() {
        Map<Integer, String> ints = new HashMap<>();
        ints.put(21, "twenty one");
        ints.put(35, "thirty five");
        ints.put(42, "fourty two");
        ints.put(57, "fifty seven");
        ints.put(64, "sixty four");
        ints.put(79, "seventy nine");
        ints.put(88, "eighty eight");
        ints.put(93, "ninety three");

        bulkAssert(ints);
    }

    @Test
    public void testHundreds() {
        Map<Integer, String> ints = new HashMap<>();
        ints.put(100, "one hundred");
        ints.put(205, "two hundred and five");
        ints.put(313, "three hundred and thirteen");
        ints.put(450, "four hundred and fifty");
        ints.put(583, "five hundred and eighty three");

        bulkAssert(ints);
    }

    @Test
    public void testThousands() {
        String word = n2w.toWords(1000);

        assertEquals("one thousand", word);
    }

    @Test
    public void testThousandsWhereHundredsComponentIsZero() {
        String word = n2w.toWords(318_000);

        assertEquals("three hundred and eighteen thousand", word);
    }

    @Test
    public void testMillions() {
        String word = n2w.toWords(758_700_005);

        assertEquals("seven hundred and fifty eight million seven hundred thousand and five", word);
    }

    @Test
    public void testLargeNumberWhereThousandsComponentIsZero() {
        String word = n2w.toWords(32_000_964);

        assertEquals("thirty two million nine hundred and sixty four", word);
    }

    @Test
    public void testLargeNumberWhereHundredsComponentIsZero() {
        String word = n2w.toWords(192_274_000);

        assertEquals("one hundred and ninety two million two hundred and seventy four thousand", word);
    }

    @Test
    public void testLargeNumberWhereThousandsAndHundredsComponentIsZero() {
        String word = n2w.toWords(63_000_000);

        assertEquals("sixty three million", word);
    }

    @Test
    public void testLargestNumber() {
        String word = n2w.toWords(999999999);

        assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", word);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownIfNumberTooLarge() {
        n2w.toWords(1_000_000_000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionThrownIfNegativeNumber() {
        n2w.toWords(-1);
    }

    private void bulkAssert(Map<Integer, String> intsToExpected) {
        for (Map.Entry<Integer, String> entry : intsToExpected.entrySet()) {
            assertEquals(entry.getValue(), n2w.toWords(entry.getKey()));
        }
    }
}
