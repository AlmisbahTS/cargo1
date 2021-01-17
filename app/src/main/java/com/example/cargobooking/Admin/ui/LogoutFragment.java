package com.example.cargobooking.Admin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cargobooking.LoginActivity;
import com.example.cargobooking.R;

public class LogoutFragment extends Fragment {


    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
        Intent out = new Intent(getActivity(), LoginActivity.class);
        out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(out);
        requireActivity().finish();
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}