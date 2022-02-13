package com.example.onroadfueldemand;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderDetails extends AppCompatActivity {

    EditText OrderDetailsAddress, OrderDetailsBunkName,OrderDetailsBunkContact,OrderDetailsCustomerName,OrderDetailsCustomerContact;
    EditText OrderDetailsFuelPrice,OrderDetailsFuelQuantity,OrderDetailsTotal;
    Button placeOrder;
    AutoCompleteTextView OrderDetailsFuelType;
    TextView OrderDetailsErrorMessage;
    String type;
    ArrayAdapter<String> adapter;
    private static String[] fuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Intent intent = getIntent();
        // mapping of components to the variables
        OrderDetailsAddress = findViewById(R.id.orderDetailsAddress);
        OrderDetailsBunkName = findViewById(R.id.orderDetailsBunkName);
        OrderDetailsBunkContact = findViewById(R.id.orderDetailsBunkContact);
        OrderDetailsCustomerName = findViewById(R.id.orderDetailsCustomerName);
        OrderDetailsCustomerContact = findViewById(R.id.orderDetailsCustomerContact);
        OrderDetailsFuelType = findViewById(R.id.orderDetailsFuelType);
        OrderDetailsFuelPrice = findViewById(R.id.orderDetailsFuelPrice);
        OrderDetailsFuelQuantity = findViewById(R.id.orderDetailsFuelQuantity);
        OrderDetailsTotal = findViewById(R.id.orderDetailsTotal);
        OrderDetailsErrorMessage = findViewById(R.id.OrderDetailsErrorMessage);
        placeOrder = findViewById(R.id.orderDetailsPlaceOrder);

        //for dropdown


        //Intent values from order page
        OrderDetailsBunkName.setText(intent.getStringExtra("bunkName"));
       // OrderDetailsFuelType.setText(intent.getStringExtra("fuelType"));
        //OrderDetailsFuelPrice.setText(intent.getStringExtra("fuelPrice"));
        OrderDetailsBunkContact.setText(intent.getStringExtra("bunkContact"));
        OrderDetailsFuelType.setText(intent.getStringExtra("fuelType"));

        if(OrderDetailsFuelType.getText().toString().equalsIgnoreCase("Petrol & Diesel")){

            Resources res = getResources();
            fuelType = res.getStringArray(R.array.orderDetailsFuelType);
            adapter = new ArrayAdapter<>(this,R.layout.dropdown_item,fuelType);
            OrderDetailsFuelType.setAdapter(adapter);
            OrderDetailsFuelType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    type = parent.getItemAtPosition(position).toString();
                    OrderDetailsFuelType.setText(type);
                    if (OrderDetailsFuelType.getText().toString().equalsIgnoreCase("petrol")) {
                        OrderDetailsFuelPrice.setText(intent.getStringExtra("petrol_price"));
                    }
                    else if(OrderDetailsFuelType.getText().toString().equalsIgnoreCase("diesel"))
                    {
                        OrderDetailsFuelPrice.setText(intent.getStringExtra("diesel_price"));
                    }
                    else
                    {
                        OrderDetailsFuelPrice.setText("0.0");

                    }

                }
            });
        }else
        {
            if (OrderDetailsFuelType.getText().toString().equalsIgnoreCase("petrol")) {
                OrderDetailsFuelPrice.setText(intent.getStringExtra("petrol_price"));
            }
            else if(OrderDetailsFuelType.getText().toString().equalsIgnoreCase("diesel"))
            {
                OrderDetailsFuelPrice.setText(intent.getStringExtra("diesel_price"));
            }
            else
            {
                OrderDetailsFuelPrice.setText("0.0");

            }
        }






        //fetching current user name and contact
        ParseUser user = ParseUser.getCurrentUser();
        OrderDetailsCustomerName.setText(user.getUsername());
        OrderDetailsCustomerContact.setText((CharSequence) user.get("contact"));


        //calculating total amount and set to the amount input text
        OrderDetailsFuelQuantity.addTextChangedListener(new TextWatcher() {
            float result=0;
            int qty = 0;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                OrderDetailsTotal.setText("0.00");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(OrderDetailsFuelQuantity.getText().toString().isEmpty())
                {
                    qty=0;
                }
                else
                {
                    qty = Integer.parseInt(OrderDetailsFuelQuantity.getText().toString());

                }
                float price = Float.parseFloat(OrderDetailsFuelPrice.getText().toString());

                if(qty >= 0 ){
                    result = qty * price;
                }else{
                    qty = 0;
                    OrderDetailsErrorMessage.setText("Enter Valid Value!");
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

                Date cal = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                //dataBse operation
                ParseObject order = new ParseObject("order");
                order.put("bunk_name",OrderDetailsBunkName.getText().toString());
                order.put("bunk_contact",OrderDetailsBunkContact.getText().toString());
                order.put("customer_name", OrderDetailsCustomerName.getText().toString());
                order.put("customer_contact",OrderDetailsCustomerContact.getText().toString());
                order.put("fuel_type",OrderDetailsFuelType.getText().toString());
                order.put("price",Integer.parseInt(OrderDetailsFuelPrice.getText().toString()));
                order.put("quantity",Integer.parseInt(OrderDetailsFuelQuantity.getText().toString()));
                order.put("total_amount", Float.parseFloat(OrderDetailsTotal.getText().toString()));
                order.put("address", OrderDetailsAddress.getText().toString());
                order.put("status", "Pending");
                order.put("date", df.format(cal));
                order.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            AlertDialog.Builder alert = new AlertDialog.Builder(OrderDetails.this);
                            alert.setTitle("Fuel Order");
                            alert.setMessage("Your Order Has Been Placed");
                            alert.setCancelable(false);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(OrderDetails.this, UserMain.class);
                                    startActivity(intent);
                                }
                            });
                            alert.show();
                        }
                    }
                });
            }
        });
    }
}