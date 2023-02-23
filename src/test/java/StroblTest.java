import cz.cvut.fel.ts1.Strobl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StroblTest {

    @Test
    public void factorialTest(){
        Assertions.assertEquals(120, Strobl.factorialRecursive(5));
    }
}
