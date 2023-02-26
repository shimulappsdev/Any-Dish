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
import com.example.homemadefood.databinding.FragmentChefDashboardBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class ChefDashboardFragment extends Fragment {

    FragmentChefDashboardBinding binding;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefDashboardBinding.inflate(getLayoutInflater(), container, false);

        fragmentArrayList.add(new ChefHomeFragment());
        fragmentArrayList.add(new ChefPendingOrderFragment());
        fragmentArrayList.add(new ChefOrderFragment());
        fragmentArrayList.add(new ChefPostDishFragment());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, fragmentArrayList);
        binding.viewPager.setAdapter(fragmentAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_home_menu);
                        break;
                    case 1:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_home_pendingOrder_menu);
                        break;
                    case 2:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_home_order_menu);
                        break;
                    case 3:
                        binding.bottomMenu.setSelectedItemId(R.id.delivery_home_postDish_menu);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        binding.bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.delivery_home_menu:
                        binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.delivery_home_pendingOrder_menu:
                        binding.viewPager.setCurrentItem(1);
                        break;

                    case R.id.delivery_home_order_menu:
                        binding.viewPager.setCurrentItem(2);
                        break;

                    case R.id.delivery_home_postDish_menu:
                        binding.viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });

        return binding.getRoot();
    }
}