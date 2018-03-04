package caffeinateme;

public class Order {

    private final long clientId;
    private final int quantity;
    private final String product;
    private Urgency urgency;
    private int etaInMinutes;

    public Order(long clientId, int quantity, String product) {
        this.clientId = clientId;
        this.quantity = quantity;
        this.product = product;
        this.urgency = Urgency.Normal;
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

    public Urgency getUrgency() {
        if (etaInMinutes < 5) return Urgency.Urgent;
        if (etaInMinutes <= 10) return Urgency.High;
        return Urgency.Normal;
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
                ", urgency=" + urgency +
                ", etaInMinutes=" + etaInMinutes +
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
        return urgency == order.urgency;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + product.hashCode();
        result = 31 * result + urgency.hashCode();
        return result;
    }

    public static Order matchingReceipt(OrderReceipt orderReceipt) {
        return new Order(orderReceipt.getClientId(), orderReceipt.getQuantity(), orderReceipt.getProduct());
    }

    public void updateEtaTo(int etaInMinutes) {
        this.etaInMinutes = etaInMinutes;
    }
}
