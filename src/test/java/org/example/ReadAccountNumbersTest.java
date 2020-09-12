package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ReadAccountNumbersTest {
    @Test
    public void testReadFourAccountNumbers() throws IOException {
        InputStream inStream = this.getClass().getResourceAsStream("/fourAccountNumbers.txt");

        ReadAccountNumbers readAccountNumbers = new ReadAccountNumbers(inStream);

        assertEquals("123456789", readAccountNumbers.next());
        assertEquals("666666666", readAccountNumbers.next());
        assertEquals("333333333", readAccountNumbers.next());
        assertEquals("444444444", readAccountNumbers.next());

        assertFalse(readAccountNumbers.hasNext());

        inStream.close();
    }

}
