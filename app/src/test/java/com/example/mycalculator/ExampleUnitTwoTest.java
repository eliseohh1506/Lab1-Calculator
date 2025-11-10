package com.example.mycalculator;
import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Unit test 2 for verifying multiplication and division operations in the MathEval calculator
 * Execute on the development machine to ensure that the calculator handles the order of the
 * operation correctly (multiplication, division, addition, subtraction)
 *
 * @see <a href="http://d.android.com/tools/testing">Android Testing Documentation</a>
 */

    public class ExampleUnitTwoTest {
        @Test

        public void operation_order_isCorrect() throws Exception{

            BigDecimal actual = MathEval.eval("10/2*3-4+6").setScale(2, RoundingMode.HALF_UP);

            BigDecimal expected = new BigDecimal("17.00");

            assertEquals("Error, expression 10/2*3-4+6 should return 17.00", expected, actual);
        }
    }