package caffeinateme.steps;

import caffeinateme.Order;
import caffeinateme.OrderReceipt;
import caffeinateme.ProductCatalogue;
import caffeinateme.Receipt;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersClient {

    List<Order> orders = new ArrayList<>();

    @Steps(shared = true)
    ProductCatalogue productCatalogue;

    @Step("Place order for customer {0} for {1} x {2}")
    public OrderReceipt placeOrder(long customerId, int quantity, String product) {
        Order order = new Order(customerId, quantity, product);
        orders.add(order);
        return order.getReceipt();
    }

    @Step
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    @Step("Notifies updated ETA for customer {0} who is {1} minutes away from the shop")
    public void updateCustomerEta(long customerId, int minutesAway) {
        orders.stream().filter(order -> order.getClientId() == customerId)
                .forEach(order -> order.updateEtaTo(minutesAway));
    }

    public Receipt getReceiptFor(long customerId) {
        double subtotal = orders.stream()
                .filter(order -> order.getClientId() == customerId)
                .mapToDouble(this::subtotalFor)
                .sum();

        double serviceFee = subtotal * 0.05; //5%
        double total = subtotal + serviceFee;
        return new Receipt(roundedToTwoDecimalPlaces(subtotal),
                roundedToTwoDecimalPlaces(serviceFee),
                roundedToTwoDecimalPlaces(total));
    }

    private double roundedToTwoDecimalPlaces(double value) {
        return new BigDecimal(Double.toString(value))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    private double subtotalFor(Order order) {
        return productCatalogue.priceOf(order.getProduct()) * order.getQuantity();
    }
}
