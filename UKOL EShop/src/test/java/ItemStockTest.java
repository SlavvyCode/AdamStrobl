import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import shop.StandardItem;
import storage.ItemStock;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStockTest {

    @Test
    public void ItemStock_Constructor_Instance() {
        StandardItem item = new StandardItem(1,"testItem", 10,"category",1);
        ItemStock stock = new ItemStock(item);

        assertEquals(item, stock.getItem());
        assertEquals(0, stock.getCount());
    }

    @ParameterizedTest
    @CsvSource({"10, 5, 15", "5, -2, 3", "0, 10, 10"})
    public void IncreaseItemCount_Parameters(int initialCount, int increaseBy, int expectedCount) {
        StandardItem item = new StandardItem(1,"testItem", 10,"category",1);
        ItemStock stock = new ItemStock(item);
        stock.IncreaseItemCount(initialCount);
        stock.IncreaseItemCount(increaseBy);
        assertEquals(expectedCount, stock.getCount());
    }

    @ParameterizedTest
    @CsvSource({"10, 5, 5", "5, -2, 7", "0, 10, -10"})
    public void DecreaseItemCount_Parameters(int initialCount, int decreaseBy, int expectedCount) {
        StandardItem item = new StandardItem(1,"testItem", 10,"category",1);
        ItemStock stock = new ItemStock(item);
        stock.IncreaseItemCount(initialCount);
        stock.decreaseItemCount(decreaseBy);
        assertEquals(expectedCount, stock.getCount());
    }
}
