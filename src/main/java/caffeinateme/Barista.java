package caffeinateme;

import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class Barista {
    public List<String> getPendingOrders() {
        List<String> pendingOrders = new ArrayList();
        pendingOrders.add("large cappuccino");
        return pendingOrders;
    }

    public List<String> getUrgentOrders() {
        List<String> urgentOrders = new ArrayList<>();
        urgentOrders.add("large cappuccino");
        return urgentOrders;
    }

    @Step
    public void shouldHaveAPendingOrderFor(String someOrder) {
        assertThat(getPendingOrders(), hasItem(someOrder));
    }
}
