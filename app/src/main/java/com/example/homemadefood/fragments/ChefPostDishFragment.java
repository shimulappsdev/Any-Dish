package com.example.homemadefood.fragments;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.ContainerActivity;
import com.example.homemadefood.databinding.FragmentChefPostDishBinding;

public class ChefPostDishFragment extends Fragment {

    FragmentChefPostDishBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefPostDishBinding.inflate(getLayoutInflater(), container, false);

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

        binding.chefPostDishLayout.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        binding.postDishBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ContainerActivity.class);
            intent.putExtra("addDish","addDish");
            startActivity(intent);
        });

        return binding.getRoot();
    }
}