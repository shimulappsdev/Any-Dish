package com.example.homemadefood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.ActivityContainerBinding;
import com.example.homemadefood.fragments.ChefAddNewDishFragment;
import com.example.homemadefood.fragments.ChefDashboardFragment;
import com.example.homemadefood.fragments.DeliveryDashboardFragment;

public class ContainerActivity extends AppCompatActivity {

    ActivityContainerBinding binding;
    ChefDashboardFragment chefDashboardFragment = new ChefDashboardFragment();
    DeliveryDashboardFragment deliveryDashboardFragment = new DeliveryDashboardFragment();
    ChefAddNewDishFragment chefAddNewDishFragment = new ChefAddNewDishFragment();
    Intent intent;
    String signIn, addDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        signIn = intent.getStringExtra("signIn");
        addDish = intent.getStringExtra("addDish");

        if (signIn != null){
            if (signIn.equals("Chef")){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, chefDashboardFragment).commit();
            }
            if (signIn.equals("Delivery")){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, deliveryDashboardFragment).commit();
            }
        }

        if (addDish != null && addDish.equals("addDish")){
            getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, chefAddNewDishFragment).commit();
        }
    }
}