package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class OcrDigitTest {
    @Test
    void shouldInitiallyBeEmpty() {
        OcrDigit digit = new OcrDigit();

        assertEquals("\n\0\0\0\n\0\0\0\n\0\0\0", digit.toString());
    }

    @Test
    void shouldRejectHighRowNumber() {
        OcrDigit digit = new OcrDigit();

        Exception ex = assertThrows(Exception.class, () -> {digit.setRow(3, "|||");});

        assertTrue(ex.getMessage().contains("row number"));
    }

    @Test
    void shouldRejectLowRowNumber() {
        OcrDigit digit = new OcrDigit();

        Exception ex = assertThrows(Exception.class, () -> {digit.setRow(-1, "|||");});

        assertTrue(ex.getMessage().contains("row number"));
    }

    @Test
    void shouldRejectLongRows() {
        OcrDigit digit = new OcrDigit();

        Exception ex = assertThrows(Exception.class, () -> {digit.setRow(0, "||||");});

        assertTrue(ex.getMessage().contains("three"));
    }

    @Test
    void shouldRepresentZero() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "| |");
        digit.setRow(2, "|_|");

        assertEquals("\n _ \n| |\n|_|", digit.toString());
    }

    @Test
    void shouldParseZero() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "| |");
        digit.setRow(2, "|_|");

        assertEquals('0', digit.getDigit());
    }

    @Test
    void shouldParseOne() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, "   ");
        digit.setRow(1, "  |");
        digit.setRow(2, "  |");

        assertEquals('1', digit.getDigit());
    }

    @Test
    void shouldParseTwo() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, " _|");
        digit.setRow(2, "|_ ");

        assertEquals('2', digit.getDigit());
    }

    @Test
    void shouldParseThree() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, " _|");
        digit.setRow(2, " _|");

        assertEquals('3', digit.getDigit());
    }

    @Test
    void shouldParseFour() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, "   ");
        digit.setRow(1, "|_|");
        digit.setRow(2, "  |");

        assertEquals('4', digit.getDigit());
    }

    @Test
    void shouldParseFive() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "|_ ");
        digit.setRow(2, " _|");

        assertEquals('5', digit.getDigit());
    }

    @Test
    void shouldParseSix() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "|_ ");
        digit.setRow(2, "|_|");

        assertEquals('6', digit.getDigit());
    }

    @Test
    void shouldParseSixAlternate() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, "   ");
        digit.setRow(1, "|_ ");
        digit.setRow(2, "|_|");

        assertEquals('6', digit.getDigit());
    }

    @Test
    void shouldParseSeven() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "  |");
        digit.setRow(2, "  |");

        assertEquals('7', digit.getDigit());
    }

    @Test
    void shouldParseEight() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "|_|");
        digit.setRow(2, "|_|");

        assertEquals('8', digit.getDigit());
    }

    @Test
    void shouldParseNine() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "|_|");
        digit.setRow(2, " _|");

        assertEquals('9', digit.getDigit());
    }

    @Test
    void shouldParseNineAlternate() throws OcrException {
        OcrDigit digit = new OcrDigit();

        digit.setRow(0, " _ ");
        digit.setRow(1, "|_|");
        digit.setRow(2, "  |");

        assertEquals('9', digit.getDigit());
    }
}
