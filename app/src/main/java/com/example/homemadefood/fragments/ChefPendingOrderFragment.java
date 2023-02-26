package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentChefPendingOrderBinding;

public class ChefPendingOrderFragment extends Fragment {

    FragmentChefPendingOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefPendingOrderBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}