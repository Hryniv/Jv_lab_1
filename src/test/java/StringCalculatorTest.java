import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    void empty_value_ok() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void one_number_ok() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    void two_numbers_ok() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    void different_count_of_numbers_ok () {
        assertEquals(1, StringCalculator.add("1"));
        assertEquals(7, StringCalculator.add("1,2,3,1"));
        assertEquals(55, StringCalculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    void comma_or_newLine_separators_ok() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
        assertEquals(10, StringCalculator.add("1,5\n4"));
    }

    @Test
    void custom_delimiter_ok() {
        assertEquals(3, StringCalculator.add("//%\n1%2"));
        assertEquals(27, StringCalculator.add("//;\n1;2;4;20"));
        assertEquals(19, StringCalculator.add("//*\n1*2*3*3*10"));
    }

}