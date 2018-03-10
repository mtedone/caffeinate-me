package caffeinateme;

public class Receipt {

    private final double subtotal;
    private final double serviceFee;
    private final double total;

    public Receipt(double subtotal, double serviceFee, double total) {
        this.subtotal = subtotal;
        this.serviceFee = serviceFee;
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (Double.compare(receipt.subtotal, subtotal) != 0) return false;
        if (Double.compare(receipt.serviceFee, serviceFee) != 0) return false;
        return Double.compare(receipt.total, total) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(subtotal);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(serviceFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "subtotal=" + subtotal +
                ", serviceFee=" + serviceFee +
                ", total=" + total +
                '}';
    }
}
