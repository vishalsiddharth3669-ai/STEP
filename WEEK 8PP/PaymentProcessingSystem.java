import java.util.ArrayList;
import java.util.List;

public class PaymentProcessingSystem {

    public static void main(String[] args) {

        Customer customerX = new Customer("Customer X");

        Order orderX = new Order("Order X", customerX);

        orderX.addProduct(
                new Product("Product A", 50),
                2);

        orderX.addProduct(
                new Product("Product B", 30),
                1);

        orderX.pay(
                new CreditCardPayment());

        Customer customerY = new Customer("Customer Y");

        Order orderY = new Order("Order Y", customerY);

        orderY.pay(
                new CreditCardPayment());

        Customer customerZ = new Customer("Customer Z");

        Order orderZ = new Order("Order Z", customerZ);

        orderZ.addProduct(
                new Product("Product C", 100),
                1);

        orderZ.pay(
                new PayPalPayment(false));
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Product {

    private String name;
    private double price;

    public Product(
            String name,
            double price) {

        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {

    private Product product;
    private int quantity;

    public OrderItem(
            Product product,
            int quantity) {

        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCardPayment
        implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        return true;
    }
}

class PayPalPayment
        implements PaymentMethod {

    private boolean successful;

    public PayPalPayment(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean processPayment(double amount) {

        return successful;
    }
}

class BankTransferPayment
        implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        return true;
    }
}

class Order {

    private String orderId;
    private Customer customer;

    private List<OrderItem> items = new ArrayList<>();

    private String status = "Pending";

    public Order(
            String orderId,
            Customer customer) {

        this.orderId = orderId;
        this.customer = customer;

        System.out.println(
                "Order created for "
                        + customer.getName());
    }

    public void addProduct(
            Product product,
            int quantity) {

        if (quantity > 0) {

            items.add(
                    new OrderItem(
                            product,
                            quantity));
        }
    }

    public double getTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void pay(
            PaymentMethod paymentMethod) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order.");

            return;
        }

        System.out.println(
                "Payment initiated for "
                        + orderId);

        boolean success = paymentMethod.processPayment(
                getTotal());

        if (success) {

            status = "Paid";

            System.out.println(
                    "Payment for "
                            + orderId
                            + " successful.");

        } else {

            System.out.println(
                    "Payment for "
                            + orderId
                            + " failed.");
        }

        System.out.println(
                "Order status: "
                        + status);
    }
}