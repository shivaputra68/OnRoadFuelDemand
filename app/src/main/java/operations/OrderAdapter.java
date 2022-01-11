package operations;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onroadfueldemand.OrderFuel;
import com.example.onroadfueldemand.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    Context context;
    ArrayList<Fuel> FuelDetails;

    public OrderAdapter(Context context, ArrayList<Fuel> fuelDetails) {
        this.context = context;
        FuelDetails = fuelDetails;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.bunk_list, parent, false);
        return new OrderHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderHolder holder, int position) {

        Fuel fuel = FuelDetails.get(position);
        holder.bunkName.setText("Bunk : " + fuel.bunkName);
        holder.fuelType.setText("Fuel Type : " + fuel.fuelType);
        holder.price.setText("Price/Ltr : " + fuel.price + " rs.");
        holder.distance.setText("Distance : " + fuel.distance + " km");
    }

    @Override
    public int getItemCount() {
        return FuelDetails.size();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder {

        TextView bunkName, fuelType, price, distance;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            bunkName = itemView.findViewById(R.id.bunkName);
            fuelType = itemView.findViewById(R.id.fuelType);
            price = itemView.findViewById(R.id.fuelPrice);
            distance = itemView.findViewById(R.id.bunkDistance);
            OrderFuel orderFuel = new OrderFuel();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(itemView.getContext(),OrderDetails.class);
                }
            });

           /* fuelType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(, OrderDetails.class);
                    orderFuel.startActivity(intent);
                }
            });*/
        }
    }

}
