package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import caffeinateme.steps.UserRegistrationClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class OrderACoffeeStepsDefinition {

    @Steps
    UserRegistrationClient userRegistration;

    @Steps
    Customer cathy;

    @Steps
    Barista barry;

    OrderReceipt orderReceipt;

    @Given("^Cathy has a Caffeinate-Me account$")
    public void cathy_has_a_Caffeinate_Me_account() throws Exception {
        userRegistration.registerUser(cathy);
    }

    @When("^s?he orders a (.*)$")
    public void she_orders_a_large_cappuccino(String order) throws Exception {
        orderReceipt = cathy.placesAnOrderFor(1, order);
    }

    @Then("^Barry should receive the order$")
    public void barry_should_receive_the_order() throws Exception {
        System.out.println(barry.pendingOrders().get(0));
        System.out.println(Order.matchingReceipt(orderReceipt));
        assertThat(barry.pendingOrders()).contains(Order.matchingReceipt(orderReceipt));
    }


}
