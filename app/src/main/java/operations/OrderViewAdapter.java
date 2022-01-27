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
        View v = LayoutInflater.from(context).inflate(R.layout.bunk_list, parent, false);
        return new OrderViewAdapter.OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ViewOrder viewOrder = ordersHistory.get(position);
        holder.bunkName.setText(viewOrder.bunkName);
        holder.fuelType.setText(viewOrder.fuelType);
        holder.price.setText(viewOrder.price);
       // holder.distance.setText(viewOrder.bunkName);
       // holder.bunkName.setText(viewOrder.bunkName);

    }


    @Override
    public int getItemCount() {
        return ordersHistory.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView bunkName, fuelType, price, distance;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

           /* bunkName = itemView.findViewById(R.id.bunkName);
            fuelType = itemView.findViewById(R.id.fuelType);
            price = itemView.findViewById(R.id.fuelPrice);
*/
        }
    }
}
