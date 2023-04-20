import hw2.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTests {


    Calculator calc = new Calculator();

    @CsvSource({"1,2,3", "-4,6,2", "1,2,1"})
    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")

    public void add_addsAAndB_returnsC(int a, int b, int c) {

        int result = calc.add(a, b);
        int expectedResult = c;

        Assertions.assertEquals(expectedResult, result);
    }

}