package operations;

public class Fuel {

    String bunkName,fuelType;
    String price;
    String distance,bunkContact;
    boolean fuelAvailability;

    public Fuel(String bunkName, String fuelType, String price, String distance, String bunkContact) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.price = price;
        this.distance = distance;
        this.bunkContact = bunkContact;
        //this.fuelAvailability = fuelAvailability;
    }

    public String getBunkContact() {
        return bunkContact;
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
