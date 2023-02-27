package com.example.homemadefood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.homemadefood.R;
import com.example.homemadefood.adapters.ActivityFragmentAdapter;
import com.example.homemadefood.adapters.FragmentAdapter;
import com.example.homemadefood.databinding.ActivityMainBinding;
import com.example.homemadefood.fragments.ChefHomeFragment;
import com.example.homemadefood.fragments.ChefOrderFragment;
import com.example.homemadefood.fragments.ChefPendingOrderFragment;
import com.example.homemadefood.fragments.ChefPostDishFragment;
import com.example.homemadefood.fragments.CustomerCartFragment;
import com.example.homemadefood.fragments.CustomerHomeFragment;
import com.example.homemadefood.fragments.CustomerOrderFragment;
import com.example.homemadefood.fragments.CustomerProfileFragment;
import com.example.homemadefood.fragments.CustomerTrackFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentArrayList.add(new CustomerHomeFragment());
        fragmentArrayList.add(new CustomerCartFragment());
        fragmentArrayList.add(new CustomerOrderFragment());
        fragmentArrayList.add(new CustomerTrackFragment());
        fragmentArrayList.add(new CustomerProfileFragment());

        ActivityFragmentAdapter fragmentAdapter = new ActivityFragmentAdapter(this, fragmentArrayList);
        binding.viewPager.setAdapter(fragmentAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomMenu.setSelectedItemId(R.id.customer_home_menu);
                        break;
                    case 1:
                        binding.bottomMenu.setSelectedItemId(R.id.customer_cart_menu);
                        break;
                    case 2:
                        binding.bottomMenu.setSelectedItemId(R.id.customer_order_menu);
                        break;
                    case 3:
                        binding.bottomMenu.setSelectedItemId(R.id.customer_track_menu);
                        break;
                    case 4:
                        binding.bottomMenu.setSelectedItemId(R.id.customer_profile_menu);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        binding.bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.customer_home_menu:
                        binding.viewPager.setCurrentItem(0);
                        break;

                    case R.id.customer_cart_menu:
                        binding.viewPager.setCurrentItem(1);
                        break;

                    case R.id.customer_order_menu:
                        binding.viewPager.setCurrentItem(2);
                        break;

                    case R.id.customer_track_menu:
                        binding.viewPager.setCurrentItem(3);
                        break;

                    case R.id.customer_profile_menu:
                        binding.viewPager.setCurrentItem(4);
                        break;
                }

                return true;
            }
        });

    }
}