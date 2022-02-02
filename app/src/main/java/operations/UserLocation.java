package operations;

public class UserLocation {

    Double latitude,longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public UserLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
