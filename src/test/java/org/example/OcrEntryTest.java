package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OcrEntryTest {
    @Test
    void shouldRejectTooFewLines() {
        String lines[] = new String[]{" _ ", "| |"};

        Exception ex = assertThrows(Exception.class, () -> {new OcrEntry(lines);});

        assertTrue(ex.getMessage().contains("three lines"));
    }

    @Test
    void shouldAllowExtraLines() throws OcrException {
        String lines[] = new String[]{" _ ", "| |", "|_|", "   "};

        OcrEntry entry = new OcrEntry(lines);
    }

    @Test
    void shouldMarkAsIllegibleIfLineTooShort() throws OcrException {
        String lines[] = new String[]{" _", "| |", "|_||_|"};

        OcrEntry entry = new OcrEntry(lines);

        assertEquals("??", entry.getDigits());
    }

    @Test
    void shouldParseOneDigit() throws OcrException {
        String lines[] = new String[]{" _ ", "| |", "|_|"};

        OcrEntry entry = new OcrEntry(lines);

        assertEquals("0", entry.getDigits());
    }

    @Test
    void shouldParseNineDigits() throws OcrException {
        String lines[] = new String[]{"    _  _     _  _  _  _  _ ", "  | _| _||_||_ |_   ||_||_|", "  ||_  _|  | _||_|  ||_| _|"};

        OcrEntry entry = new OcrEntry(lines);

        assertEquals("123456789", entry.getDigits());
    }
}
