package operations;

public class ViewOrder {

    String bunkName,fuelType,orderID, amount, orderStatus, orderDate, quantity;

    public String getBunkName() {
        return bunkName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getAmount() {
        return amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public ViewOrder(String bunkName, String fuelType, String orderID, String amount, String orderStatus, String orderDate, String quantity) {
        this.bunkName = bunkName;
        this.fuelType = fuelType;
        this.orderID = orderID;
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }
}
