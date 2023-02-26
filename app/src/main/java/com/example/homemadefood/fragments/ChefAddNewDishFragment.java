package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentChefAddNewDishBinding;

public class ChefAddNewDishFragment extends Fragment {

    FragmentChefAddNewDishBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefAddNewDishBinding.inflate(getLayoutInflater(), container, false);

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }
}