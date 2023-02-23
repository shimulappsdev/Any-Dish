package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentDeliveryPostDishBinding;

public class DeliveryPostDishFragment extends Fragment {

    FragmentDeliveryPostDishBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryPostDishBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}