package com.example.cargobooking.Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargobooking.Admin.EditOrderActivity;
import com.example.cargobooking.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderVH> {

    Context context;
    ArrayList<OrderModel> list = new ArrayList<>();

    public OrderListAdapter(Context context, ArrayList<OrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item, parent, false);
        return new OrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderVH holder, int position) {
        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_from.setText(list.get(position).getForm());
        holder.tv_to.setText(list.get(position).getTo());
        holder.tv_quantity.setText(list.get(position).getQuantity());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, list.get(position).getId());
            }
        });
    }

    private void showMenu(View v, int id) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.inflate(R.menu.order_menu);
        DatabaseHelper db = new DatabaseHelper(context);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.cancel) {
                    db.cancelOrder(id);
                } else if (item.getItemId() == R.id.edit) {
                    Intent i = new Intent(context, EditOrderActivity.class);
                    i.putExtra("id", id);
                    context.startActivity(i);
                }
                return true;
            }
        });
        popupMenu.show();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class OrderVH extends RecyclerView.ViewHolder {
        TextView tv_title, tv_quantity, tv_date, tv_from, tv_to;
        MaterialCardView cardView;

        public OrderVH(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_order_title);
            tv_quantity = itemView.findViewById(R.id.tv_order_quantity);
            tv_from = itemView.findViewById(R.id.tv_from);
            tv_to = itemView.findViewById(R.id.tv_to);
            tv_date = itemView.findViewById(R.id.tv_order_date);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
