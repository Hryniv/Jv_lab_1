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

}