package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
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

    }

    private void setBunkHistory() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("bunk_name", ParseUser.getCurrentUser().get("name"));
        query.findInBackground(((objects, e) -> {
            for(int i=0;i<objects.size();i++){
                list.add(new BunkHistoryModel(objects.get(i).getObjectId(), objects.get(i).get("customer_name").toString(),
                        objects.get(i).get("fuel_type").toString(), objects.get(i).get("quantity").toString(), objects.get(i).get("total_amount").toString(),
                        objects.get(i).get("status").toString(), objects.get(i).get("date").toString()));
            }
            BunkHistoryAdapter adapter = new BunkHistoryAdapter(this, list);
            recyclerViewBunkHistory.setAdapter(adapter);
            recyclerViewBunkHistory.setLayoutManager(new LinearLayoutManager(this));
        }));
    }
}