package com.example.cargobooking.Admin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cargobooking.Admin.OrderActivity;
import com.example.cargobooking.Admin.ReportActivity;
import com.example.cargobooking.Admin.UsersActivity;
import com.example.cargobooking.Admin.WarehouseActivity;
import com.example.cargobooking.R;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {


    private MaterialCardView btn_wh, btn_orders, btn_reports, btn_users;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btn_orders = root.findViewById(R.id.btn_orders);
        btn_reports = root.findViewById(R.id.btn_reports);
        btn_users = root.findViewById(R.id.btn_users);
        btn_wh = root.findViewById(R.id.btn_whs);

        actions();
        return root;
    }

    private void actions() {
        btn_wh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WarehouseActivity.class);
                startActivity(intent);
            }
        });
        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });
        btn_reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                startActivity(intent);
            }
        });
        btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UsersActivity.class);
                startActivity(intent);
            }
        });
    }
}