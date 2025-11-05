package com.example.mycalculator;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        MathEval e = new MathEval();
        BigDecimal actual = e.eval("2+2").setScale(2, BigDecimal.ROUND_HALF_UP);
        // comparing Big decimal to Big Decimal value to ensure same precision during comparison of actual and expected values
        assertEquals("Error, Addition should return 4",new BigDecimal("4.00"), actual);
    }
}