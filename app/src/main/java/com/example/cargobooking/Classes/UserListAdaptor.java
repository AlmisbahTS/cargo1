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

public class UserListAdaptor extends RecyclerView.Adapter<UserListAdaptor.UserVH> {

    Context context;
    ArrayList<UserModel> list = new ArrayList<>();

    public UserListAdaptor(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        holder.user_password.setText(list.get(position).getPassword());
        holder.user_email.setText(list.get(position).getEmail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserVH extends RecyclerView.ViewHolder {
        TextView user_email, user_password;
        MaterialCardView cardView;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            user_email = itemView.findViewById(R.id.user_email);
            user_password = itemView.findViewById(R.id.user_password);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
