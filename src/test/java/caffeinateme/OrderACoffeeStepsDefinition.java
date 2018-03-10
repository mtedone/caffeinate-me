package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static caffeinateme.UserRegistrationStepsDefinition.ORDER_RECEIPT;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class OrderACoffeeStepsDefinition {

    @Steps(shared = true)
    Customer customer;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @Steps(shared = true)
    private ProductCatalogue productCatalogue;

    @When("^(?:.*) (?:orders|has ordered) an? (.*)$")
    public void she_orders_a_large_cappuccino(String order) throws Exception {
        orderReceipt = customer.placesAnOrderFor(1, order);

        Serenity.setSessionVariable(ORDER_RECEIPT).to(orderReceipt);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_order() throws Exception {
        System.out.println(barry.pendingOrders().get(0));
        System.out.println(Order.matchingReceipt(orderReceipt));
        assertThat(barry.pendingOrders()).contains(Order.matchingReceipt(orderReceipt));
    }


    @Given("^Sarah has ordered:$")
    public void sarahHasOrdered(List<OrderItem> orders) throws Throwable {
        System.out.println(orders);
        orders.forEach(
                order -> {
                    customer.placesAnOrderFor(order.getQuantity(), order.getProduct());
                }
        );
    }

    Receipt receipt;
    @When("^(?:.*) asks for a receipt$")
    public void sheAsksForAReceipt() throws Throwable {
        receipt = customer.requestAReceipt();
    }

    @Then("^she should receive a receipt totalling:$")
    public void sheShouldReceiveAReceiptTotalling(List<Receipt> receipts) throws Throwable {
        Receipt customerReceipt = receipts.get(0);
        assertThat(customerReceipt).isEqualToIgnoringNullFields(receipt);
    }

    @And("^the following prices:$")
    public void theFollowingPrices(List<ProductPrice> productPrices) throws Throwable {
        productCatalogue.addProductsWithPrices(productPrices);
    }
}
