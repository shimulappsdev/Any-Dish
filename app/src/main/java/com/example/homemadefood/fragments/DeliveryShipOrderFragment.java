package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentDeliveryShipOrderBinding;

public class DeliveryShipOrderFragment extends Fragment {

    FragmentDeliveryShipOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryShipOrderBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}