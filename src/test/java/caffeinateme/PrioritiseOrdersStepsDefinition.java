package caffeinateme;

import caffeinateme.steps.Barista;
import caffeinateme.steps.Customer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Optional;

import static caffeinateme.UserRegistrationStepsDefinition.ORDER_RECEIPT;
import static org.assertj.core.api.Assertions.assertThat;

public class PrioritiseOrdersStepsDefinition {

    @Steps(shared = true)
    Customer sarah;

    @Steps
    Barista barry;

    @Given("^Sarah is (\\d+) minutes away from the shop$")
    public void sarah_is_minutes_away_from_the_shop(int minutesAway) throws Exception {
        sarah.updatesEtaTo(minutesAway);
    }

    List<Order> pendingOrders;
    @When("^Barry reviews the pending orders$")
    public void barry_reviews_the_pending_orders() throws Exception {
        pendingOrders = barry.pendingOrders();
    }

    @Then("^Sarah's order should have an urgency of (.*)$")
    public void sarahsOrderShouldHaveAnUrgencyOf(Urgency urgency) throws Exception {
        Optional<Order> sarahsOrder = sarahsOrderIn(pendingOrders);
        assertThat(sarahsOrder).isPresent();
        assertThat(sarahsOrder.get().getUrgency()).isEqualTo(urgency);
    }

    private Optional<Order> sarahsOrderIn(List<Order> pendingOrders) {
        OrderReceipt orderReceipt = Serenity.sessionVariableCalled(ORDER_RECEIPT);
        return pendingOrders.stream().filter(order -> order.equals(Order.matchingReceipt(orderReceipt)))
                .findFirst();
    }
}
