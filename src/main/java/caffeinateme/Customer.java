package caffeinateme;

import net.thucydides.core.annotations.Step;

public class Customer {
    private int distanceFromShop;

    String actor;

    @Step("Notifies caffeinate-me that #actor is {0} meters from the shop.")
    public void setDistanceFromShop(int distanceFromShop) {
        this.distanceFromShop = distanceFromShop;
    }

    @Step("#actor places an order for {0}")
    public void placesOrderFor(String order) {

    }
}
