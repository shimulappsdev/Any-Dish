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
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeliveryDashboardBinding.inflate(getLayoutInflater(), container, false);

        fragmentArrayList.add(new DeliveryPendingOrderFragment());
        fragmentArrayList.add(new DeliveryShipOrderFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, fragmentArrayList);
        binding.viewPager.setAdapter(fragmentAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_pendingOrder_menu);
                        break;
                    case 1:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_shipOrder_menu);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        binding.bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.delivery_pendingOrder_menu:
                        binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.delivery_shipOrder_menu:
                        binding.viewPager.setCurrentItem(1);
                        break;
                }

                return true;
            }
        });

        return binding.getRoot();
    }
}