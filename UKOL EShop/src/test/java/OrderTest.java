import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;

import java.util.ArrayList;

public class OrderTest {


    @Test
    public void Order_ConstructorIncludingState_Instance(){

        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;


        String customerName =  "customerName";
        String customerAddress = "customerAddress";

        int state= 2;

        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);

        ShoppingCart items = new ShoppingCart();

        items.addItem(standardItem);


        Order order = new Order(items,customerName,customerAddress,state);

        Assertions.assertNotNull(order, "Order instance should not be null");
        Assertions.assertNotNull(order.getItems(), "Order items should not be null");
        Assertions.assertNotNull(order.getCustomerName(), "Customer order name should not be null");
        Assertions.assertNotNull(order.getCustomerAddress(), "Customer order address should not be null");
        Assertions.assertNotNull(order.getState(), "Customer order state should not be null");


        Assertions.assertEquals(1, order.getItems().size(), "Order should contain one item");
        Assertions.assertEquals(standardItem, order.getItems().get(0), "Order should contain the added item");
        Assertions.assertEquals(customerName, order.getCustomerName(), "Order should have the correct customer name");
        Assertions.assertEquals(customerAddress, order.getCustomerAddress(), "Order should have the correct customer address");
        Assertions.assertEquals(customerAddress, order.getState(), "Order should have the correct state");


        ///test for multiple items
        StandardItem item1 = new StandardItem(1, "Item 1", 10.0f, "Category 1", 1);
        StandardItem item2 = new StandardItem(2, "Item 2", 20.0f, "Category 2", 2);
        StandardItem item3 = new StandardItem(3, "Item 3", 30.0f, "Category 3", 3);

        ShoppingCart itemsMultiple = new ShoppingCart();
        items.addItem(item1);
        items.addItem(item2);
        items.addItem(item3);


        Order order2 = new Order(items, customerName, customerAddress);

        Assertions.assertNotNull(order2);
        Assertions.assertNotNull(order2.getItems());
        Assertions.assertEquals(3, order2.getItems().size());
        Assertions.assertEquals(item1, order2.getItems().get(0));
        Assertions.assertEquals(item2, order2.getItems().get(1));
        Assertions.assertEquals(item3, order2.getItems().get(2));
        Assertions.assertEquals(customerName, order2.getCustomerName());
        Assertions.assertEquals(customerAddress, order2.getCustomerAddress());
    }
    @Test
    public void Order_Constructor_Instance(){
        int id = 1;
        String name = "name";
        String category = "category";
        int loyaltyPoints = 1;
        int price = 1;


        String customerName =  "customerName";
        String customerAddress = "customerAddress";

        StandardItem standardItem = new StandardItem(id, name, price, category, loyaltyPoints);

        ShoppingCart items = new ShoppingCart();

        items.addItem(standardItem);


        Order order = new Order(items,customerName,customerAddress);

        Assertions.assertNotNull(order, "Order instance should not be null");
        Assertions.assertNotNull(order.getItems(), "Order items should not be null");
        Assertions.assertNotNull(order.getCustomerName(), "Customer order name should not be null");
        Assertions.assertNotNull(order.getCustomerAddress(), "Customer order address should not be null");
        Assertions.assertEquals(1, order.getItems().size(), "Order should contain one item");
        Assertions.assertEquals(standardItem, order.getItems().get(0), "Order should contain the added item");
        Assertions.assertEquals(customerName, order.getCustomerName(), "Order should have the correct customer name");
        Assertions.assertEquals(customerAddress, order.getCustomerAddress(), "Order should have the correct customer address");












        ///test for multiple items
        StandardItem item1 = new StandardItem(1, "Item 1", 10.0f, "Category 1", 1);
        StandardItem item2 = new StandardItem(2, "Item 2", 20.0f, "Category 2", 2);
        StandardItem item3 = new StandardItem(3, "Item 3", 30.0f, "Category 3", 3);

        ShoppingCart itemsMultiple = new ShoppingCart();
        items.addItem(item1);
        items.addItem(item2);
        items.addItem(item3);


        Order order2 = new Order(items, customerName, customerAddress);

         Assertions.assertNotNull(order2);
         Assertions.assertNotNull(order2.getItems());
         Assertions.assertEquals(3, order2.getItems().size());
         Assertions.assertEquals(item1, order2.getItems().get(0));
         Assertions.assertEquals(item2, order2.getItems().get(1));
         Assertions.assertEquals(item3, order2.getItems().get(2));
         Assertions.assertEquals(customerName, order2.getCustomerName());
         Assertions.assertEquals(customerAddress, order2.getCustomerAddress());
    }


}
