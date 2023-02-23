package com.example.homemadefood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}