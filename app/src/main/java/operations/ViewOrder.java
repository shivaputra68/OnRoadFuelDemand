package operations;

public class ViewOrder {

    String bunkName,fuelType,orderID;
    String price;
    String orderStatus;
    String orderDate;

    public ViewOrder(String bunkName, String fuelType, String orderID, String price, String orderStatus, String orderDate) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.orderID = orderID;
        this.price = price;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
}
