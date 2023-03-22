import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    void empty_value_Ok() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void one_number_Ok() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    void two_numbers_Ok() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    void different_count_of_numbers_Ok () {
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(7, StringCalculator.add("1,2,3,1"));
        assertEquals(55, StringCalculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    void comma_and_newLineSeparator_Ok() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
        assertEquals(10, StringCalculator.add("1,5\n4"));
    }

    @Test
    void newLineDelimiter_Ok() {
        assertEquals(9, StringCalculator.add("1\n6\n2"));
    }

    @Test
    void invalidInputDelimiters_NotOk() {
        try {
            StringCalculator.add("1,\n5");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ignored) {
        }
    }

    @Test
    void numbersStartWithInvalidInputDelimiter_NotOk () {
        try {
            StringCalculator.add(",1,5");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ex) {
            String message = ex.getMessage();
            if(!message.equals("Input start with delimiter: ,")) {
                fail("\nExpected: " + '"' + "Input start with delimiter: ," + '"'
                        + "\nActual: " + '"' + message + '"');
            }
        }

    }

    @Test
    void numbersEndWithInvalidInputDelimiter_NotOk () {
        try {
            StringCalculator.add("1,5\n");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ex) {
            String message = ex.getMessage();
            if(!message.equals("Input end with delimiter: \n")) {
                fail("\nExpected: " + '"' + "Input end with delimiter: ," + '"'
                        + "\nActual: " + '"' + message + '"');
            }
        }
    }

    @Test
    void numbersStartWithUnknownDelimiter_NotOk () {
        try {
            StringCalculator.add("*1,5");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ex) {
            String message = ex.getMessage();
            if(!message.equals("Input start with unknown delimiter: *")) {
                fail("\nExpected: " + '"' + "Input start with unknown delimiter: *" + '"'
                        + "\nActual: " + '"' + message + '"');
            }
        }

    }

    @Test
    void numbersEndWithUnknownDelimiter_NotOk () {
        try {
            StringCalculator.add("1,5**");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ex) {
            String message = ex.getMessage();
            if(!message.equals("Input end with unknown delimiter: **")) {
                fail("\nExpected: " + '"' + "Input end with unknown delimiter: **" + '"'
                        + "\nActual: " + '"' + message + '"');
            }
        }
    }

    @Test
    void negativeNumberWithInvalidInputDelimiter_NotOk() {
        try {
            StringCalculator.add("1,5,\n-7");
            fail("Expected InvalidDelimiterInputException");
        } catch (InvalidDelimitersInputException ex) {
            String message = ex.getMessage();
            if(!message.equals("Input contain unknown delimiter (start at index = 3 end at index = 4): ,\n")) {
                fail("\nExpected: " + '"' + "Input contain unknown delimiter (start at index = 3 end at index = 4): ,\n" + '"'
                        + "\nActual: " + '"' + message + '"');
            }
        }
    }

    @Test
    void custom_delimiter_Ok() {
        assertEquals(3, StringCalculator.add("//%\n1%2"));
        assertEquals(27, StringCalculator.add("//;\n1;2;4;20"));
        assertEquals(19, StringCalculator.add("//*\n1*2*3*3*10"));
    }

    @Test
    void negativeNumber_NotOk() {
        try {
            StringCalculator.add("1,2,4\n5,-1");
            fail("Expected NegativesNotAllowedException");
        } catch (NegativesNotAllowedException ignored) {
        }
    }

    @Test
    void negativeNumberExceptionMessage_Ok() {
        try {
            StringCalculator.add("-1,5,-4,5,-7");
        } catch (RuntimeException ex) {
            String message = ex.getMessage();
            if (!message.equals("Not allowed to add negative numbers: -1, -4, -7")) {
                fail("\nExpected: " + '"' + "Not allowed to add negative numbers: -1, -4, -7" + '"'
                + "\nActual: " + '"' + message + '"');
            }
        }
    }

    @Test
    void numbersBiggerThen1000_Ok () {
        assertEquals(1007, StringCalculator.add("1000,7,10001"));
        assertEquals(1999, StringCalculator.add("1000,999,1001"));
    }

}