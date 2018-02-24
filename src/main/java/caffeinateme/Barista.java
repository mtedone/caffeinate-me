package caffeinateme;

import java.util.ArrayList;
import java.util.List;

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
}
