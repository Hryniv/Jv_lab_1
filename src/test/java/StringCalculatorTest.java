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

}