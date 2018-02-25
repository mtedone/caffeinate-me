package caffeinateme;

import net.thucydides.core.annotations.Step;

public class Customer {
    private int distanceFromShop;

    @Step("Notifies caffeinate-me that the customer is {0} meters from the shop.")
    public void setDistanceFromShop(int distanceFromShop) {
        this.distanceFromShop = distanceFromShop;
    }

    @Step
    public void placesOrderFor(String order) {

    }
}
