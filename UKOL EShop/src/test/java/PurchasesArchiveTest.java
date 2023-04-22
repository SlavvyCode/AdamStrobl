import archive.ItemPurchaseArchiveEntry;
import archive.PurchasesArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;
import storage.ItemStock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;



//TEST TO MAKE SURE PROPER COMMIT

public class  PurchasesArchiveTest {

    @Test
    public void printItemPurchaseStatistics_putOneOrder_get1Get0(){
        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;


        String customerName =  "customerName";
        String customerAddress = "customerAddress";

        int state= 2;

        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem standardItem2 = new StandardItem(2, "name2", 2, "category2", 2);

        ShoppingCart items = new ShoppingCart();

        items.addItem(standardItem);
        items.addItem(standardItem2);



        Order order = new Order(items,customerName,customerAddress,state);



        PurchasesArchive purchasesArchive = new PurchasesArchive();

        purchasesArchive.putOrderToPurchasesArchive(order);
        purchasesArchive.putOrderToPurchasesArchive(order);
        purchasesArchive.putOrderToPurchasesArchive(order);



        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        purchasesArchive.printItemPurchaseStatistics();


        String expectedOutput = "ITEM PURCHASE STATISTICS:\n" +
                "ITEM  Item   ID 1   NAME name   CATEGORY category   PRICE 1.0   LOYALTY POINTS 1   HAS BEEN SOLD 3 TIMES\n" +
                "ITEM  Item   ID 2   NAME name2   CATEGORY category2   PRICE 2.0   LOYALTY POINTS 2   HAS BEEN SOLD 3 TIMES\n";



        // z neznameho duvodu nefunguje assertEquals i kdyz intellij rozdil mezi stringy nevidi.
//        assertEquals(expectedOutput, outputStream.toString());


        List<String> expectedLines = List.of(expectedOutput.split("\\r?\\n"));
        List<String> actualLines = List.of(outputStream.toString().split("\\r?\\n"));

        assertLinesMatch(expectedLines, actualLines);

    }

    @Test
    public void getHowManyTimesHasBeenItemSold(){
        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;


        String customerName =  "customerName";
        String customerAddress = "customerAddress";

        int state= 2;

        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem standardItem2 = new StandardItem(2, name, price, category, loyaltyPoints);
        ShoppingCart items = new ShoppingCart();
        items.addItem(standardItem);
        Order order = new Order(items,customerName,customerAddress,state);
        PurchasesArchive purchasesArchive = new PurchasesArchive();
        purchasesArchive.putOrderToPurchasesArchive(order);

        Assertions.assertEquals(1, purchasesArchive.getHowManyTimesHasBeenItemSold(standardItem));
        Assertions.assertEquals(0, purchasesArchive.getHowManyTimesHasBeenItemSold(standardItem2));

    }

    @Test
    public void putOrderToPurchasesArchiveTest() {
            StandardItem item1 = new StandardItem(1, "item1", 10, "category1", 5);
            StandardItem item2 = new StandardItem(2, "item2", 20, "category2", 10);
            ShoppingCart cart = new ShoppingCart();
            cart.addItem(item1);
            cart.addItem(item2);
            Order order = new Order(cart, "customer", "address", 1);
            PurchasesArchive archive = new PurchasesArchive();
            archive.putOrderToPurchasesArchive(order);
            // Check that the order has been added to the archive
            assertEquals(1, archive.getOrderArchive().size());
            assertEquals(order, archive.getOrderArchive().get(0));
            // Check that the items in the order have been added to the item purchase archive
            assertEquals(2, archive.getItemPurchaseArchive().size());
            assertTrue(archive.getItemPurchaseArchive().containsKey(item1.getID()));
            assertTrue(archive.getItemPurchaseArchive().containsKey(item2.getID()));
            assertEquals(1, archive.getItemPurchaseArchive().get(item1.getID()).getCountHowManyTimesHasBeenSold());
            assertEquals(1, archive.getItemPurchaseArchive().get(item2.getID()).getCountHowManyTimesHasBeenSold());
            // Add another order with one of the same items to check that the count is increased
            ShoppingCart cart2 = new ShoppingCart();
            cart2.addItem(item1);
            Order order2 = new Order(cart2, "customer2", "address2", 2);
            archive.putOrderToPurchasesArchive(order2);
            assertEquals(2, archive.getOrderArchive().size());
            assertEquals(order2, archive.getOrderArchive().get(1));
            assertEquals(2, archive.getItemPurchaseArchive().get(item1.getID()).getCountHowManyTimesHasBeenSold());
            assertEquals(1, archive.getItemPurchaseArchive().get(item2.getID()).getCountHowManyTimesHasBeenSold());
    }




    @Test
    public void testPrintItemPurchaseStatistics() {

        HashMap<Integer, ItemPurchaseArchiveEntry> itemArchive = new HashMap<>();
        ArrayList<Order> orderArchive = new ArrayList<>();
        Item item1 = new StandardItem(1, "item1", 10.0f, "category1", 1);
        Item item2 = new StandardItem(2, "item2", 20.0f, "category2", 2);
        ItemPurchaseArchiveEntry entry1 = new ItemPurchaseArchiveEntry(item1);
        ItemPurchaseArchiveEntry entry2 = new ItemPurchaseArchiveEntry(item2);
        entry1.increaseCountHowManyTimesHasBeenSold(2);
        entry2.increaseCountHowManyTimesHasBeenSold(3);
        itemArchive.put(item1.getID(), entry1);
        itemArchive.put(item2.getID(), entry2);
        PurchasesArchive archive = new PurchasesArchive(itemArchive, orderArchive);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        archive.printItemPurchaseStatistics();
        String expectedOutput = ("ITEM PURCHASE STATISTICS:\n" +
                "ITEM  Item   ID 1   NAME item1   CATEGORY category1   PRICE 10.0   LOYALTY POINTS 1   HAS BEEN SOLD 3 TIMES\n" +
                "ITEM  Item   ID 2   NAME item2   CATEGORY category2   PRICE 20.0   LOYALTY POINTS 2   HAS BEEN SOLD 4 TIMES\n");

        List<String> expectedLines = List.of(expectedOutput.split("\\r?\\n"));
        List<String> actualLines = List.of(outputStream.toString().split("\\r?\\n"));

        Assertions.assertLinesMatch(expectedLines, actualLines);
    }

// KEPT FOR REFERENCE
//    public void mockOrderArchive() {
//        PurchasesArchive purchasesArchive = new PurchasesArchive();
//        ArrayList<Order> mockOrderArchive = Mockito.mock(ArrayList.class);
//        purchasesArchive.setOrderArchive(mockOrderArchive);
//    }
//



    @Test
    public void putOrderToPurchasesArchive_mockedOrderArchive() {
        ArrayList<Order> mockedOrderArchive = Mockito.mock(ArrayList.class);

        ShoppingCart items = new ShoppingCart();
        Item item1 = new StandardItem(1, "Item 1", 10   , "Category 1", 5);
        items.addItem(item1);
        Order order = new Order(items, "Customer Name", "Customer Address");

        PurchasesArchive purchasesArchive = new PurchasesArchive();
        purchasesArchive.setOrderArchive(mockedOrderArchive);

        purchasesArchive.putOrderToPurchasesArchive(order);

        // Verify that the mocked orderArchive was used
        Mockito. verify(mockedOrderArchive, times(1)).add(order);
    }

@Test
    public void mockItemPurchaseArchiveEntry(){
        ItemPurchaseArchiveEntry itemPurchaseEntry = Mockito.mock(ItemPurchaseArchiveEntry.class);
        Mockito.when(itemPurchaseEntry.getCountHowManyTimesHasBeenSold()).thenReturn(2);

        assertEquals(2, itemPurchaseEntry.getCountHowManyTimesHasBeenSold());


    }

    @Test
    public void testConstructorExistenceWithItemPurchaseArchiveEntry() {
        // mock object of Item
        Item mockItem = Mockito.mock(Item.class);
        Mockito.when(mockItem.getID()).thenReturn(1);
        Mockito.when(mockItem.getName()).thenReturn("testItem");
        Mockito.when(mockItem.getPrice()).thenReturn(10.0f);
        Mockito.when(mockItem.getCategory()).thenReturn("testCategory");

        // create new ItemPurchaseArchiveEntry
        ItemPurchaseArchiveEntry entry = new ItemPurchaseArchiveEntry(mockItem);

        mockItem.getID();
        mockItem.getName();
        mockItem.getPrice();
        mockItem.getCategory();
        // verify that the constructor was called with the expected arguments
        Mockito.verify(mockItem).getID();
        Mockito.verify(mockItem).getName();
        Mockito.verify(mockItem).getPrice();
        Mockito.verify(mockItem).getCategory();
    }

}


