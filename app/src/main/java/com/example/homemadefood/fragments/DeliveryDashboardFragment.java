package com.example.homemadefood.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.adapters.FragmentAdapter;
import com.example.homemadefood.databinding.FragmentDeliveryDashboardBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class DeliveryDashboardFragment extends Fragment {

    FragmentDeliveryDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryDashboardBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}