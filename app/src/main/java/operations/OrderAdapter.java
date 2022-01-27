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

import Interfaces.OrderFuelRecyclerClickListner;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    Context context;
    ArrayList<Fuel> fuel;
    private final OrderFuelRecyclerClickListner recyclerClickListner;

    public OrderAdapter(Context context, ArrayList<Fuel> fuel, OrderFuelRecyclerClickListner recyclerClickListner){
        this.context = context;
        this.fuel = fuel;
        this.recyclerClickListner = recyclerClickListner;

    }

    @NonNull
    @Override
    public OrderAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bunk_list,parent,false);

        return new OrderAdapter.OrderHolder(view, recyclerClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderHolder holder, int position) {
        holder.bunkName.setText(fuel.get(position).getBunkName());
        holder.fuelType.setText("Type : "+fuel.get(position).getFuelType());
        holder.fuelPrice.setText("Price : "+fuel.get(position).getPrice()+" rs.");
        holder.bunkDistance.setText("Distance : "+fuel.get(position).getDistance()+" KM");
    }

    @Override
    public int getItemCount() {
        return fuel.size();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder {

        TextView bunkName, fuelType,fuelPrice,bunkDistance;

        public OrderHolder(@NonNull View itemView, OrderFuelRecyclerClickListner recyclerClickListner) {
            super(itemView);

            bunkName = itemView.findViewById(R.id.fuelBunkName);
            fuelType = itemView.findViewById(R.id.fuelType);
            fuelPrice = itemView.findViewById(R.id.fuelPrice);
            bunkDistance = itemView.findViewById(R.id.fuelBunkDistance);

            itemView.setOnClickListener(new View.OnClickListener() {
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
