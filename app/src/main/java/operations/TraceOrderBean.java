package operations;

public class TraceOrderBean {

    String bunkName,fuelType,status;

    public TraceOrderBean(String bunkName, String fuelType, String status) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.status = status;
    }

    public String getBunkName() {
        return bunkName;
    }

    public void setBunkName(String bunkName) {
        this.bunkName = bunkName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
