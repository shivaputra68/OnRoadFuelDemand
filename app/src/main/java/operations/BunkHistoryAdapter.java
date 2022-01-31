package operations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadfueldemand.R;

import java.util.ArrayList;

public class BunkHistoryAdapter extends RecyclerView.Adapter<BunkHistoryAdapter.BunkHistoryHolder> {

    Context context;
    ArrayList<BunkHistoryModel> list;

    public BunkHistoryAdapter(Context context, ArrayList<BunkHistoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BunkHistoryAdapter.BunkHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.bunk_history, parent, false);
        return new BunkHistoryAdapter.BunkHistoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BunkHistoryAdapter.BunkHistoryHolder holder, int position) {
        BunkHistoryModel model = list.get(position);
        holder.orderID.setText("ID : "+model.getOrderId());
        holder.cusName.setText("Customer : "+model.getCusName());
        holder.fuelType.setText("Fuel : "+model.getFuelType());
        holder.quantity.setText("Quantity : "+model.getQuantity());
        holder.amount.setText("Amount : "+model.getAmount());
        holder.date.setText("Date : "+model.getDate());
        holder.status.setText("Status : "+model.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BunkHistoryHolder extends RecyclerView.ViewHolder {

        TextView orderID, cusName, fuelType, quantity, amount, date, status;

        public BunkHistoryHolder(@NonNull View itemView) {
            super(itemView);

            orderID = itemView.findViewById(R.id.bunkHistoryOrderId);
            cusName = itemView.findViewById(R.id.bunkHistoryCustomerName);
            fuelType = itemView.findViewById(R.id.bunkHistoryrFuelType);
            quantity = itemView.findViewById(R.id.bunkHistoryFuelQuantity);
            amount = itemView.findViewById(R.id.bunkHistoryAmount);
            date = itemView.findViewById(R.id.bunkHistoryDate);
            status = itemView.findViewById(R.id.bunkHistoryStatus);
        }
    }
}
