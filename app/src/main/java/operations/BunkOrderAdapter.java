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

public class BunkOrderAdapter extends RecyclerView.Adapter<BunkOrderAdapter.BunkOrderHolder> {

    private static String[] status;
    Context context;
    ArrayList<BunkOrder> bunkOrder;
    private final OrderFuelRecyclerClickListner recyclerClickListner;
    ArrayAdapter<String> adapter1;

    public BunkOrderAdapter(Context context, ArrayList<BunkOrder> bunkOrder, OrderFuelRecyclerClickListner recyclerClickListner) {
        this.context = context;
        this.bunkOrder = bunkOrder;
        this.recyclerClickListner = recyclerClickListner;
    }

    @NonNull
    @Override
    public BunkOrderAdapter.BunkOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bunk_order,parent,false);

        return new BunkOrderAdapter.BunkOrderHolder(view, recyclerClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull BunkOrderAdapter.BunkOrderHolder holder, int position) {
        adapter1 = new ArrayAdapter<>(context,R.layout.dropdown_item,status);
        int pos = position;
        holder.orderId.setText("Order ID : " +bunkOrder.get(position).getOrderId());
        holder.cusName.setText("Customer : "+bunkOrder.get(position).getCusName());
        holder.cusContact.setText("Contact : "+bunkOrder.get(position).getCusContact());
        holder.fuelType.setText("Fuel : "+bunkOrder.get(position).getBunkFuelType());
        holder.quantity.setText("Quantity : "+bunkOrder.get(position).getQuantity());
        holder.amount.setText("Amount : "+bunkOrder.get(position).getAmount());
        holder.location.setText("Location : "+bunkOrder.get(position).getAddress());
        //setting value for autoCompleteTextView from strings and assigning value to the bunkOrder attribute(status)
        holder.autoCompleteTextView.setAdapter(adapter1);
        holder.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                String value = parent.getItemAtPosition(position1).toString();
                bunkOrder.get(pos).setBunkStatus(value);
                System.out.println(bunkOrder.get(pos).getBunkStatus());
            }
        });

    }

    @Override
    public int getItemCount() {
        return bunkOrder.size();
    }


    public static class BunkOrderHolder extends RecyclerView.ViewHolder {

        TextView orderId,cusName,cusContact, fuelType, quantity, amount, location;
        Button accept;
        AutoCompleteTextView autoCompleteTextView;

        public BunkOrderHolder(@NonNull View itemView, OrderFuelRecyclerClickListner recyclerClickListner) {
            super(itemView);

            orderId = itemView.findViewById(R.id.bunkRequestOrderId);
            cusName = itemView.findViewById(R.id.bunkRequestCusName);
            cusContact = itemView.findViewById(R.id.bunkRequestCusContact);
            fuelType = itemView.findViewById(R.id.bunkRequestfuelType);
            quantity = itemView.findViewById(R.id.bunkRequestfuelQuantity);
            amount = itemView.findViewById(R.id.bunkRequestAmount);
            autoCompleteTextView = itemView.findViewById(R.id.bunkRequestStatus);
            location = itemView.findViewById(R.id.bunkRequestLocation);
            accept = itemView.findViewById(R.id.bunkRequestAccept);
            Resources res = itemView.getResources();
            BunkOrderAdapter.status = res.getStringArray(R.array.bunkRequestUpdate);

            accept.setOnClickListener(new View.OnClickListener() {
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
