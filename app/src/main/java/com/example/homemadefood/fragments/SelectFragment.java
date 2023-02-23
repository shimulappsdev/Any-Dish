package com.example.homemadefood.fragments;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.EntryActivity;
import com.example.homemadefood.databinding.FragmentSelectBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SelectFragment extends Fragment {

    FragmentSelectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectBinding.inflate(getLayoutInflater(), container, false);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back1),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back5),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back6),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back7),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.back8),3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        binding.selectLayout.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();


        binding.chefLayout.setOnClickListener(view -> {
            Intent chefIntent = new Intent(getActivity(), EntryActivity.class);
            chefIntent.putExtra("userType", "Chef");
            chefIntent.putExtra("authentication", "authentication");
            startActivity(chefIntent);
        });

        binding.consumerLayout.setOnClickListener(view -> {
            Intent consumerIntent = new Intent(getActivity(), EntryActivity.class);
            consumerIntent.putExtra("userType", "Consumer");
            consumerIntent.putExtra("authentication", "authentication");
            startActivity(consumerIntent);
        });

        binding.deliveryLayout.setOnClickListener(view -> {
            Intent deliveryIntent = new Intent(getActivity(), EntryActivity.class);
            deliveryIntent.putExtra("userType", "Delivery");
            deliveryIntent.putExtra("authentication", "authentication");
            startActivity(deliveryIntent);
        });

        return binding.getRoot();
    }
}