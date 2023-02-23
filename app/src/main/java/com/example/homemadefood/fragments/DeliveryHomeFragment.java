package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentDeliveryHomeBinding;

public class DeliveryHomeFragment extends Fragment {

    FragmentDeliveryHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryHomeBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}