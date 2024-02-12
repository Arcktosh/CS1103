import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product product1 = new Product(/* parameters */);
        Product product2 = new Product(/* parameters */);

        // Create customer and add products to cart
        Customer customer = new Customer(/* parameters */);
        customer.addToCart(product1);
        customer.addToCart(product2);

        // Place an order
        Order order = new Order(/* parameters */);

        // Display information
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Order Total: " + order.getOrderTotal());
        // Other relevant information
    }
}
