package org.example;

import java.util.HashMap;
import java.util.Map;

public class OcrDigit {
    private char[][] chars = new char[3][3];
    private char digit = '\0';

    public void setRow(int rowNumber, String row) throws OcrException {
        if (rowNumber < 0) {
            throw new OcrException("row number must be greater than zero");
        }
        if (rowNumber > 2) {
            throw new OcrException("row number must be less than three");
        }
        if (row.length() != 3) {
            throw new OcrException("rows must have exactly three characters");
        }

        for (int j=0; j<3; ++j) {
            chars[rowNumber][j] = row.charAt(j);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<3; ++i) {
            builder.append('\n');
            for (int j=0; j<3; ++j) {
                builder.append(chars[i][j]);
            }
        }
        return builder.toString();
    }

    private char parse() throws OcrException {
        templateLoop: for (Map.Entry<char[][], Character> entry : templates.entrySet()) {
            final char[][] key = entry.getKey();
            for (int i=0; i<3; ++i) {
                for (int j=0; j<3; ++j) {
                    if (key[i][j] != chars[i][j]) {
                        continue templateLoop;
                    }
                }
            }
            return entry.getValue();
        }
        throw new OcrException("bad digit: " + this.toString());
    }

    public char getDigit() throws OcrException {
        if (digit == '\0') {
            digit = parse();
        }
        return digit;
    }


    static private Map<char[][], Character> templates = new HashMap<>();
    static {
        final char[][] zero = new char[][]{{' ', '_', ' '}, {'|', ' ', '|'}, {'|', '_', '|'}};
        templates.put(zero, '0');

        final char[][] one = new char[][]{{' ', ' ', ' '}, {' ', ' ', '|'}, {' ', ' ', '|'}};
        templates.put(one, '1');

        final char[][] two = new char[][]{{' ', '_', ' '}, {' ', '_', '|'}, {'|', '_', ' '}};
        templates.put(two, '2');

        final char[][] three = new char[][]{{' ', '_', ' '}, {' ', '_', '|'}, {' ', '_', '|'}};
        templates.put(three, '3');

        final char[][] four = new char[][]{{' ', ' ', ' '}, {'|', '_', '|'}, {' ', ' ', '|'}};
        templates.put(four, '4');

        final char[][] five = new char[][]{{' ', '_', ' '}, {'|', '_', ' '}, {' ', '_', '|'}};
        templates.put(five, '5');

        final char[][] six = new char[][]{{' ', '_', ' '}, {'|', '_', ' '}, {'|', '_', '|'}};
        templates.put(six, '6');

        final char[][] seven = new char[][]{{' ', '_', ' '}, {' ', ' ', '|'}, {' ', ' ', '|'}};
        templates.put(seven, '7');

        final char[][] eight = new char[][]{{' ', '_', ' '}, {'|', '_', '|'}, {'|', '_', '|'}};
        templates.put(eight, '8');

        final char[][] nine = new char[][]{{' ', '_', ' '}, {'|', '_', '|'}, {' ', '_', '|'}};
        templates.put(nine, '9');
    }
}
