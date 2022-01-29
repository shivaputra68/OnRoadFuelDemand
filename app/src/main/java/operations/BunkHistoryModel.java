package operations;

public class BunkHistoryModel {

    String orderId, cusName, fuelType, quantity, amount, status, Date;

    public BunkHistoryModel(String orderId, String cusName, String fuelType, String quantity, String amount, String status, String date) {
        this.orderId = orderId;
        this.cusName = cusName;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
        Date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCusName() {
        return cusName;
    }
    public String getFuelType() {
        return fuelType;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getAmount() {
        return amount;
    }
    public String getStatus() {
        return status;
    }
    public String getDate() {
        return Date;
    }
}
