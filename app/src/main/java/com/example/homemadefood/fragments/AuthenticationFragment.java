package com.example.homemadefood.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.ContainerActivity;
import com.example.homemadefood.activities.EntryActivity;
import com.example.homemadefood.activities.MainActivity;
import com.example.homemadefood.databinding.FragmentAuthenticationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationFragment extends Fragment {

    FragmentAuthenticationBinding binding;
    Intent intent;
    String userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthenticationBinding.inflate(getLayoutInflater(), container, false);

        intent = getActivity().getIntent();
        userType = intent.getStringExtra("userType");


        binding.emailAuthBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "email");
            intent.putExtra("userType", userType);
            startActivity(intent);
        });

        binding.phoneAuthBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "phone");
            intent.putExtra("userType", userType);
            startActivity(intent);
        });

        binding.signUpBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "signUp");
            intent.putExtra("userType", userType);
            startActivity(intent);
        });

        return binding.getRoot();
    }
}