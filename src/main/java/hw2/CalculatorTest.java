package hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();


//   NázevMetody_TestovanýStav_OčekávanýVýstup

    @Test
    public void add_FivePlusThree_8() {
        Assertions.assertEquals(8,calculator.add(5,3));
    }
    @Test
    public void subtract_FiveMinusEight_MinusThree() {
        Assertions.assertEquals(-3,calculator.subtract(5,8));
    }
    @Test
    public void multiply_FiveTimesEight_Forty() {
        Assertions.assertEquals(40,calculator.multiply(5,8));

    }

    @Test
    public void divide_FortyDividedByFive_Eight() {

        Assertions.assertEquals(8,calculator.divide(40,5));
    }
    @Test
    public void divide_DivisionByZero_ThrowsException() {


        Assertions.assertThrows(ArithmeticException.class,()->calculator.divide(3,0));
    }

}
