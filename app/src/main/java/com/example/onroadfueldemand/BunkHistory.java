package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import operations.BunkHistoryAdapter;
import operations.BunkHistoryModel;

public class BunkHistory extends AppCompatActivity {

    RecyclerView recyclerViewBunkHistory;
    ArrayList<BunkHistoryModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_history);

        recyclerViewBunkHistory = findViewById(R.id.recyclerView_bunkHistory);
        Intent i = getIntent();
        setBunkHistory();
        BunkHistoryAdapter adapter = new BunkHistoryAdapter(this, list);
        recyclerViewBunkHistory.setAdapter(adapter);
        recyclerViewBunkHistory.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setBunkHistory() {
        list.add(new BunkHistoryModel("123","shiva","petrol","2","100","delivered","18/10/1000"));
        list.add(new BunkHistoryModel("123","shiva","petrol","2","100","delivered","18/10/1000"));
        list.add(new BunkHistoryModel("123","shiva","petrol","2","100","delivered","18/10/1000"));

    }
}