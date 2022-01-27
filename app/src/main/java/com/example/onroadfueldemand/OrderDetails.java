package com.example.onroadfueldemand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class OrderDetails extends AppCompatActivity {

    EditText OrderDetailsID, OrderDetailsBunkName,OrderDetailsBunkContact,OrderDetailsCustomerName,OrderDetailsCustomerContact;
    EditText OrderDetailsFuelType,OrderDetailsFuelPrice,OrderDetailsFuelQuantity,OrderDetailsTotal;
    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        // mapping of components to the variables
        OrderDetailsID = findViewById(R.id.orderDetailsID);
        OrderDetailsBunkName = findViewById(R.id.orderDetailsBunkName);
        OrderDetailsBunkContact = findViewById(R.id.orderDetailsBunkContact);
        OrderDetailsCustomerName = findViewById(R.id.orderDetailsCustomerName);
        OrderDetailsCustomerContact = findViewById(R.id.orderDetailsCustomerContact);
        OrderDetailsFuelType = findViewById(R.id.orderDetailsFuelType);
        OrderDetailsFuelPrice = findViewById(R.id.orderDetailsFuelPrice);
        OrderDetailsFuelQuantity = findViewById(R.id.orderDetailsFuelQuantity);
        OrderDetailsTotal = findViewById(R.id.orderDetailsTotal);
        placeOrder = findViewById(R.id.orderDetailsPlaceOrder);

        //Intent values from order page
        OrderDetailsBunkName.setText(intent.getStringExtra("bunkName"));
        OrderDetailsFuelType.setText(intent.getStringExtra("fuelType"));
        OrderDetailsFuelPrice.setText(intent.getStringExtra("fuelPrice"));

        //ffetching current user name and contact
        ParseUser user = ParseUser.getCurrentUser();
        OrderDetailsCustomerName.setText(user.getUsername());
        OrderDetailsCustomerContact.setText((CharSequence) user.get("contact"));


        //calculating total amount and set to the amount input text
        OrderDetailsFuelQuantity.addTextChangedListener(new TextWatcher() {
            float result=0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                OrderDetailsTotal.setText("0.00");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int qty = Integer.parseInt(OrderDetailsFuelQuantity.getText().toString());
                float price = Float.parseFloat(OrderDetailsFuelPrice.getText().toString());

                if(qty > 0){
                    result = qty * price;

                }else{

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                OrderDetailsTotal.setText(String.valueOf(result));
            }
        });

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
        Float OrderFuelPrice = Float.parseFloat(OrderDetailsFuelPrice.getText().toString());
        Float OrderFuelQuantity = Float.parseFloat(OrderDetailsFuelQuantity.getText().toString());
        Float OrderTotal = OrderFuelPrice * OrderFuelQuantity;

        //databse operation
    }
}