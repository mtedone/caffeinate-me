package caffeinateme;

public class Order {

    private final long clientId;
    private final int quantity;
    private final String product;
    private OrderPriority priority;

    public Order(long clientId, int quantity, String product) {
        this.clientId = clientId;
        this.quantity = quantity;
        this.product = product;
        this.priority = OrderPriority.Normal;
    }

    public long getClientId() {
        return clientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public OrderReceipt getReceipt() {
        return new OrderReceipt(clientId, quantity, product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (clientId != order.clientId) return false;
        if (quantity != order.quantity) return false;
        if (!product.equals(order.product)) return false;
        return priority == order.priority;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + product.hashCode();
        result = 31 * result + priority.hashCode();
        return result;
    }

    public static Order matchingReceipt(OrderReceipt orderReceipt) {
        return new Order(orderReceipt.getClientId(), orderReceipt.getQuanity(), orderReceipt.getProduct());
    }
}
