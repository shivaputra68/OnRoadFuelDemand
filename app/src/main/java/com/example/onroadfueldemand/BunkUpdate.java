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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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
                    dieselPrice.setEnabled(true);
                }else if(selectedFuel.equalsIgnoreCase("Petrol")){
                    dieselPrice.setEnabled(false);
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

        String date1 =  Calendar.getInstance().getTime().toString();
        date.setText(date1);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}