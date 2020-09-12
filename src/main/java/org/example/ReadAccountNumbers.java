package org.example;

import java.io.*;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadAccountNumbers implements Iterator<String> {
    private BufferedReader bufReader;
    private String nextAccountNumber = null;
    private static Pattern ocrLine = Pattern.compile("^[|_ ]{3}");

    public ReadAccountNumbers(InputStream inStream) throws IOException {
        bufReader = new BufferedReader(new InputStreamReader(inStream));

        attemptNext();
    }

    private void attemptNext() throws IOException {
        String lines[] = new String[3];
        int numRows = 0;
        String strLine;
        while (numRows < 3 && (strLine = bufReader.readLine()) != null)   {
            Matcher matcher = ocrLine.matcher(strLine);
            if (matcher.find()) {
                lines[numRows] = strLine;
                ++numRows;
            }
        }

        if (numRows == 3) {
            try {
                OcrEntry entry = new OcrEntry(lines);
                nextAccountNumber = entry.getDigits();
            } catch (OcrException ex) {
                nextAccountNumber = null;
                System.err.println(ex);
            }
        } else {
            nextAccountNumber = null;
        }
    }

    @Override
    public boolean hasNext() {
        return nextAccountNumber != null;
    }

    @Override
    public String next() {
        String accountNumber = nextAccountNumber;
        try {
            attemptNext();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return accountNumber;
    }
}
