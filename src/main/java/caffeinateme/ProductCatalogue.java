package caffeinateme;

import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogue {

    List<ProductPrice> catalogue = new ArrayList<>();
    @Step
    public void addProductsWithPrices(List<ProductPrice> productPrices) {
        catalogue.addAll(productPrices);
    }

    public double priceOf(String productName) {
        return catalogue.stream()
                .filter(product -> product.getProduct().equals(productName))
                .findFirst()
                .orElseThrow(UnknownProductException::new)
                .getPrice();
    }
}
