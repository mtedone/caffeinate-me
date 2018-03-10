package caffeinateme;

public class ProductPrice {

    private final String product;
    private final double price;


    public ProductPrice(String product, double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice that = (ProductPrice) o;

        if (Double.compare(that.price, price) != 0) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "product='" + product + '\'' +
                ", price=" + price +
                '}';
    }
}
