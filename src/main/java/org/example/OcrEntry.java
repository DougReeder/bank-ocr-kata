package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Three lines of text representing a string of OCR digits
 */
public class OcrEntry {
    private String[] lines = new String[3];
    private List<OcrDigit> ocrDigits = new ArrayList<OcrDigit>();
    private String digits = null;

    public OcrEntry(String[] lines) throws OcrException {
        if (lines.length < 3) {
            throw new OcrException("Must pass at least three lines");
        }

        System.arraycopy(lines, 0, this.lines, 0, 3);
    }

    public String getDigits() throws OcrException {
        if (digits == null) {
            digits = parse();
        }
        return digits;
    }

    private String parse() throws OcrException {
        for (int rowNum=0; rowNum<3; ++rowNum) {
            String row = lines[rowNum];
            int numDigits = row.length() / 3;

            for (int d=ocrDigits.size(); d<numDigits; ++d) {
                ocrDigits.add(new OcrDigit());
            }

            for (int d=0; d<numDigits; ++d) {
                OcrDigit ocrDigit = ocrDigits.get(d);
                ocrDigit.setRow(rowNum,row.substring(d*3, (d+1)*3));
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Iterator<OcrDigit> i = ocrDigits.iterator(); i.hasNext();) {
            char digit;
            try {
                digit = i.next().getDigit();
            } catch (OcrException ex) {
                digit = '?';
            }
            builder.append(digit);
        }
        return builder.toString();
    }
}
