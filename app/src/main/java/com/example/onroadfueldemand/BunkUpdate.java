package com.example.onroadfueldemand;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BunkUpdate extends AppCompatActivity {


    String[] fuelType, serviceChoice ;
    AutoCompleteTextView fuelTypeView,service;
    ArrayAdapter<String> adapter1,adapter2;
    String selectedFuel, selectedService;
    TextView date;
    EditText petrolPrice,dieselPrice;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_update);

        Intent intent = getIntent();
        Resources res = getResources();
        fuelType = res.getStringArray(R.array.fuelType);
        serviceChoice = res.getStringArray(R.array.fuelAvailable);
        fuelTypeView = findViewById(R.id.fuelTypeView);
        service = findViewById(R.id.fuelAvailableView);
        date = findViewById(R.id.fuelUpdateDate);
        updateButton = findViewById(R.id.fuelUpdateButton);
        petrolPrice = findViewById(R.id.fuelPetrolPrice);
        dieselPrice = findViewById(R.id.fuelDeiselPrice);

        adapter1 = new ArrayAdapter<>(this,R.layout.dropdown_item,fuelType);
        fuelTypeView.setAdapter(adapter1);
        fuelTypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFuel = parent.getItemAtPosition(position).toString();

                if(selectedFuel.equalsIgnoreCase("Diesel")){
                    petrolPrice.setEnabled(false);
                    petrolPrice.setText("0.00");
                    dieselPrice.setEnabled(true);
                }else if(selectedFuel.equalsIgnoreCase("Petrol")){
                    dieselPrice.setEnabled(false);
                    dieselPrice.setText("0.00");
                    petrolPrice.setEnabled(true);
                }else{
                    dieselPrice.setEnabled(true);
                    petrolPrice.setEnabled(true);
                }
            }
        });

        adapter2 = new ArrayAdapter<>(this, R.layout.dropdown_item, serviceChoice);
        service.setAdapter(adapter2);
        service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = parent.getItemAtPosition(position).toString();
            }
        });

        Date date1 =  Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(df.format(date1));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Fuel");
                query.whereEqualTo("owner", ParseUser.getCurrentUser().getUsername());
                query.whereNotEqualTo("date", date.getText().toString());
                query.setLimit(1);
                query.findInBackground(((objects, e) -> {
                    String objectId = "";
                    for(int i=0;i<objects.size();i++){
                        objectId=objects.get(i).getObjectId();
                    }
                    ParseQuery<ParseObject> update_query = ParseQuery.getQuery("Fuel");
                    update_query.getInBackground(objectId, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if(e==null){
                                object.put("petrol_price", Float.parseFloat(petrolPrice.getText().toString()));
                                object.put("diesel_price", Float.parseFloat(dieselPrice.getText().toString()));
                                object.put("fuel_type", selectedFuel);
                                object.put("availability", selectedService);
                                Toast.makeText(BunkUpdate.this, "Data Has Been Updated", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(BunkUpdate.this,"Error while updating", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }));

               /* ParseUser user = ParseUser.getCurrentUser();
                ParseObject object = new ParseObject("Fuel");
                object.put("bunk_name", user.get("name"));
                object.put("owner", user.getUsername());
                object.put("contact", user.get("contact"));
                object.put("fuel_type", selectedFuel);
                object.put("petrol_price", Float.parseFloat(petrolPrice.getText().toString()));
                object.put("diesel_price", Float.parseFloat(dieselPrice.getText().toString()));
                object.put("availability", service.getText().toString());
                object.put("date", date.getText().toString());
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(BunkUpdate.this, "FUEL DETAILES ARE UPDATED !!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(BunkUpdate.this, "Error while Updating", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

            }
        });
    }
}