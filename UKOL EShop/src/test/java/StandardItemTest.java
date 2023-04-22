import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import shop.StandardItem;

import java.util.ArrayList;

public class StandardItemTest {

    //    public StandardItem(int id, String name, float price, String category, int loyaltyPoints) {
//        super(id, name, price, category);
//        this.loyaltyPoints = loyaltyPoints;
    @Test
    public void StandardItem_Constructor_Instance() {

        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;

        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);
        Assertions.assertEquals(id, standardItem.getID());
        Assertions.assertEquals(name, standardItem.getName());
        Assertions.assertEquals(category, standardItem.getCategory());
        Assertions.assertEquals(loyaltyPoints, standardItem.getLoyaltyPoints());
        Assertions.assertEquals(price, standardItem.getPrice());

    }

    @Test
    public void Copy_Thing_CopyOfThing(){

        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;


        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);

        StandardItem copy =  standardItem.copy();



        Assertions.assertNotSame(standardItem,copy);
        Assertions.assertEquals(standardItem.getID(), copy.getID());
        Assertions.assertEquals(standardItem.getName(), copy.getName());
        Assertions.assertEquals(standardItem.getCategory(), copy.getCategory());
        Assertions.assertEquals(standardItem.getLoyaltyPoints(), copy.getLoyaltyPoints());
        Assertions.assertEquals(standardItem.getPrice(), copy.getPrice());


    }

    @ParameterizedTest(name = "{0} equals {1} should be {2}")
    @CsvSource({
            "1, 1, true",
            "1, 2, false",
            "2, 2, true",
            "2, 3, false"
    })
    public void testEquals(int loyaltyPoints1, int loyaltyPoints2, boolean expectedResult) {

        StandardItem item1 = new StandardItem(1, "Item 1", 10.0f, "Category 1", loyaltyPoints1);
        StandardItem item2 = new StandardItem(2, "Item 2", 20.0f, "Category 2", loyaltyPoints2);

        boolean result = item1.equals(item2);

        Assertions.assertEquals(expectedResult, result);
    }

//   NázevMetody_TestovanýStav_OčekávanýVýstup
}


