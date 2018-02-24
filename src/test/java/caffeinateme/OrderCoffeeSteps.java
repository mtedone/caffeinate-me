package caffeinateme;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderCoffeeSteps {

    Customer cathy = new Customer();

    @Given("^Cathy is (\\d+) meters from the coffee shop$")
    public void cathy_is_n_meters_from_the_coffee_shop(int distanceInMeters) throws Exception {
        cathy.setDistanceFromShop(distanceInMeters);
    }


    @When("^Cathy orders a (.*)$")
    public void cathy_orders_a(String order) throws Exception {
        cathy.placesOrderFor(order);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_order() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Barry should know that the order is Urgent$")
    public void barry_should_know_that_the_order_is_Urgent() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
