package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentChefHomeBinding;

public class ChefHomeFragment extends Fragment {

    FragmentChefHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefHomeBinding.inflate(getLayoutInflater(), container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar();


        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}