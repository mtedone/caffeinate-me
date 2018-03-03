package caffeinateme;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class OrderCoffeeSteps {

    @Steps
    Customer cathy;

    @Steps
    Barista barry;

    String cathyOrder;

    @Given("^Cathy is (\\d+) meters from the coffee shop$")
    public void cathy_is_n_meters_from_the_coffee_shop(int distanceInMeters) throws Exception {
        cathy.setDistanceFromShop(distanceInMeters);
    }


    @When("^Cathy (?:orders|has ordered) a (.*)$")
    public void cathy_orders_a(String order) throws Exception {
        this.cathyOrder = order;
        cathy.placesOrderFor(cathyOrder);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_order() throws Exception {
        barry.shouldHaveAPendingOrderFor(cathyOrder);
    }

    @Then("^Barry should know that the order is (.*)$")
    public void barry_should_know_that_the_order_is_Urgent(String priority) throws Exception {
        assertThat(barry.getUrgentOrders(), hasItem(cathyOrder));
    }

}
