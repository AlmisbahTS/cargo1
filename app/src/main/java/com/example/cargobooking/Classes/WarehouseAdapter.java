package com.example.cargobooking.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargobooking.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.WarehouseVH> {
    Context context;
    ArrayList<WarehousesDataModel> list = new ArrayList<>();

    public WarehouseAdapter(Context context, ArrayList<WarehousesDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WarehouseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wh_list_item, parent, false);
        return new WarehouseVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseVH holder, int position) {

        holder.tv_whs_name.setText(list.get(position).getName());
        holder.tv_supervisor.setText(list.get(position).getSupervisor());
        holder.tv_location.setText(list.get(position).getLocation());
        holder.tv_capacity.setText(list.get(position).getCapacity());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WarehouseVH extends RecyclerView.ViewHolder {
        TextView tv_location, tv_capacity, tv_supervisor, tv_whs_name;
        MaterialCardView cardView;

        public WarehouseVH(@NonNull View itemView) {
            super(itemView);
            tv_location = itemView.findViewById(R.id.tv_wh_location);
            tv_capacity = itemView.findViewById(R.id.tv_capacity);
            tv_supervisor = itemView.findViewById(R.id.tv_supervisor_name);
            tv_whs_name = itemView.findViewById(R.id.tv_wh_name);
            cardView = itemView.findViewById(R.id.card);
        }
    }

}
