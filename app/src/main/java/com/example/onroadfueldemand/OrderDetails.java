package com.example.onroadfueldemand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetails extends AppCompatActivity {

    EditText OrderDetailsID, OrderDetailsBunkName,OrderDetailsBunkContact,OrderDetailsCustomerName,OrderDetailsCustomerContact;
    EditText OrderDetailsFuelType,OrderDetailsFuelPrice,OrderDetailsFuelQuantity,OrderDetailsTotal;
    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();

        OrderDetailsID = findViewById(R.id.orderDetailsID);
        OrderDetailsBunkName = findViewById(R.id.orderDetailsBunkName);
        OrderDetailsBunkContact = findViewById(R.id.orderDetailsBunkContact);
        OrderDetailsCustomerName = findViewById(R.id.orderDetailsCustomerName);
        OrderDetailsCustomerContact = findViewById(R.id.orderDetailsCustomerContact);
        OrderDetailsFuelType = findViewById(R.id.orderDetailsFuelType);
        OrderDetailsFuelPrice = findViewById(R.id.orderDetailsFuelPrice);
        OrderDetailsFuelQuantity = findViewById(R.id.orderDetailsFuelQuantity);
        OrderDetailsTotal = findViewById(R.id.orderDetailsTotal);
        placeOrder = findViewById(R.id.orderDeatilsPlaceOrder);

        //Intent values from order page
        OrderDetailsBunkName.setText(intent.getStringExtra("bunkName"));

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(OrderDetails.this);
                alert.setTitle("Fuel Order");
                alert.setMessage("Your Order Has Been Placed");
                alert.setCancelable(false);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Orders();
                        Intent intent = new Intent(OrderDetails.this, UserMain.class);
                        startActivity(intent);
                    }
                });
            }
        });


    }

    private void Orders() {

        String OrderID = OrderDetailsID.getText().toString();
        String OrderBunkName = OrderDetailsBunkName.getText().toString();
        String OrderBunkContact = OrderDetailsBunkContact.getText().toString();
        String OrderCustomerName = OrderDetailsCustomerName.getText().toString();
        String OrderCustomerContact = OrderDetailsCustomerContact.getText().toString();
        String OrderFuelType = OrderDetailsFuelType.getText().toString();
        int OrderFuelPrice = Integer.parseInt(OrderDetailsFuelPrice.getText().toString());
        int OrderFuelQuantity = Integer.parseInt(OrderDetailsFuelQuantity.getText().toString());
        int OrderTotal = OrderFuelPrice * OrderFuelQuantity;

        //databse operation


    }
}