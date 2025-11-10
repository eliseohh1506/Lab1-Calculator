package com.example.mycalculator;
import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;


    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */
    public class ExampleUnitOneTest {
        @Test
        public void addition_isCorrect() throws Exception {
                assertEquals(4, 2 + 2);

                BigDecimal actual = MathEval.eval("2+2").setScale(2, RoundingMode.HALF_UP);

                // comparing Big decimal to Big Decimal value to ensure same precision during comparison of actual and expected values
                assertEquals("Error, Addition should return 4",new BigDecimal("4.00"), actual);
        }
    }
