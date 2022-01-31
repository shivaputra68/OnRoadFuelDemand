package operations;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadfueldemand.R;

import java.util.ArrayList;

import Interfaces.OrderFuelRecyclerClickListner;

public class AdminBunkAdapter extends RecyclerView.Adapter<AdminBunkAdapter.AdminBunkHolder> {

    ArrayList<AdminBunkVerify> adminBunkVerifies ;
    private final OrderFuelRecyclerClickListner recyclerClickListner;
    private static String[] status;
    Context context;
    ArrayAdapter<String> adapter;

    public AdminBunkAdapter(Context context, ArrayList<AdminBunkVerify> adminBunkVerifies, OrderFuelRecyclerClickListner recyclerClickListner) {
        this.adminBunkVerifies = adminBunkVerifies;
        this.recyclerClickListner = recyclerClickListner;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminBunkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_bunk_request,parent,false);

        return new AdminBunkAdapter.AdminBunkHolder(view, recyclerClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBunkAdapter.AdminBunkHolder holder, int position) {
        adapter = new ArrayAdapter<>(context,R.layout.dropdown_item,status);
        int pos = position;
        holder.bunkName.setText("Bunk : "+adminBunkVerifies.get(position).getBunkName());
        holder.bunkContact.setText("Contact : "+adminBunkVerifies.get(position).getBunkContact());
        holder.ownerName.setText("Owner : "+adminBunkVerifies.get(position).getOwnerName());
        holder.bunkAddress.setText("Address : "+adminBunkVerifies.get(position).getBunkAddress());
        holder.status.setAdapter(adapter);
        holder.status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                String value = parent.getItemAtPosition(position1).toString();
                adminBunkVerifies.get(pos).setStatus(value);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminBunkVerifies.size();
    }

    public static class AdminBunkHolder extends RecyclerView.ViewHolder {

        TextView bunkName, ownerName, bunkContact, bunkAddress,requestID;
        AutoCompleteTextView status;
        Button adminUpdate;

        public AdminBunkHolder(@NonNull View itemView, OrderFuelRecyclerClickListner recyclerClickListner) {
            super(itemView);

            requestID = itemView.findViewById(R.id.bunkRequestOrderId);
            bunkName = itemView.findViewById(R.id.adminBunkName);
            ownerName = itemView.findViewById(R.id.adminOwnerName);
            bunkContact = itemView.findViewById(R.id.adminBunkContact);
            bunkAddress = itemView.findViewById(R.id.adminBunkAddress);
            status = itemView.findViewById(R.id.adminRequestStatus);
            adminUpdate = itemView.findViewById(R.id.adminRequestUpdate);
            Resources res = itemView.getResources();
            AdminBunkAdapter.status = res.getStringArray(R.array.adminRequestUpdate);

            adminUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerClickListner != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerClickListner.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
