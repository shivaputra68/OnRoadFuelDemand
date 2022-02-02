package operations;

public class Fuel {

    String bunkName,fuelType;
    String petrol_price,diesel_price;
    String distance,bunkContact;

    public Fuel(String bunkName, String fuelType, String petrol_price, String diesel_price, String distance, String bunkContact) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.petrol_price = petrol_price;
        this.diesel_price = diesel_price;
        this.distance = distance;
        this.bunkContact = bunkContact;
    }

    public String getPetrol_price() {
        return petrol_price;
    }

    public String getDiesel_price() {
        return diesel_price;
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



    public String getDistance() {
        return distance;
    }


    }

