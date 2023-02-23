package com.example.homemadefood.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.EntryActivity;
import com.example.homemadefood.databinding.FragmentPhoneSignInBinding;

public class PhoneSignInFragment extends Fragment {

    FragmentPhoneSignInBinding binding;
    Intent intent;
    String userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneSignInBinding.inflate(getLayoutInflater(), container, false);

        intent = getActivity().getIntent();
        userType = intent.getStringExtra("userType");

        Log.i("TAG", "Phone User Type: "+userType);

        binding.emailSignInBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "email");
            intent.putExtra("userType", userType);
            startActivity(intent);
            getActivity().finish();
        });

        binding.signUpNowBtn.setOnClickListener(view -> {
            Intent signUpNowIntent = new Intent(getActivity(), EntryActivity.class);
            signUpNowIntent.putExtra("authType", "signUp");
            signUpNowIntent.putExtra("userType", userType);
            startActivity(signUpNowIntent);
            getActivity().finish();
        });

        return binding.getRoot();
    }
}