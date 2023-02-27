package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentCustomerCartBinding;

public class CustomerCartFragment extends Fragment {

    FragmentCustomerCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerCartBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}