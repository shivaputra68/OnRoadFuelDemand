package operations;

public class Fuel {

    String bunkName,fuelType;
    String price;
    String distance;
    boolean fuelAvailability;

    public Fuel(String bunkName, String fuelType, String price, String distance) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.price = price;
        this.distance = distance;
        //this.fuelAvailability = fuelAvailability;
    }

    public String getBunkName() {
        return bunkName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getPrice() {
        return price;
    }

    public String getDistance() {
        return distance;
    }

    public boolean isFuelAvailability() {
        return fuelAvailability;
    }
}
