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

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewAdapter.OrderViewHolder>{

    Context context;
    ArrayList<ViewOrder> ordersHistory;

    public OrderViewAdapter(Context context, ArrayList<ViewOrder> ordersHistory) {
        this.context = context;
        this.ordersHistory = ordersHistory;
    }

    @NonNull
    @Override
    public OrderViewAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_orders, parent, false);
        return new OrderViewAdapter.OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ViewOrder viewOrder = ordersHistory.get(position);
        holder.orderID.setText("ID : "+viewOrder.getOrderID());
        holder.bunkName.setText("Bunk : "+viewOrder.getBunkName());
        holder.fuelType.setText("Fuel : "+viewOrder.getFuelType());
        holder.quantity.setText("Quantity : "+viewOrder.getQuantity());
        holder.amount.setText("Amount : "+viewOrder.getAmount());
        holder.date.setText("Date : "+viewOrder.getOrderDate());
        holder.status.setText("Status : "+viewOrder.getOrderStatus());
    }


    @Override
    public int getItemCount() {
        return ordersHistory.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderID, bunkName, fuelType, quantity, amount, date, status;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderID = itemView.findViewById(R.id.viewOrderOrderId);
            bunkName = itemView.findViewById(R.id.viewOrderBunkName);
            fuelType = itemView.findViewById(R.id.viewOrderFuelType);
            quantity = itemView.findViewById(R.id.viewOrderFuelQuantity);
            amount = itemView.findViewById(R.id.viewOrderAmount);
            date = itemView.findViewById(R.id.viewOrderDate);
            status = itemView.findViewById(R.id.viewOrderStatus);

        }
    }
}
