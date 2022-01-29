package operations;

public class BunkOrder {

    String orderId, cusName, cusContact, fuelType, address,status , quantity, amount;

    public BunkOrder(String orderId,String cusName,String cusContact,String fuelType,String address,String status,String quantity,String amount) {
        this.orderId = orderId;
        this.cusName = cusName;
        this.cusContact = cusContact;
        this.fuelType = fuelType;
        this.address = address;
        this.status = status;
        this.quantity = quantity;
        this.amount = amount;
    }

    public void setBunkStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusContact() {
        return cusContact;
    }

    public String getBunkFuelType() {
        return fuelType;
    }

    public String getAddress() {
        return address;
    }

    public String getBunkStatus() {
        return status;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

}
