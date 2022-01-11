package operations;

public class OrderDetails {

    int orderno = 12345;
    String fuelType,bunkName,bunkContact,customerName,customerContact;
    int quantity,price,total;


    public void calPrice(){

        total = price*quantity;
    }
}
