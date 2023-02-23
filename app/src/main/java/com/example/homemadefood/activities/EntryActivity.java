package com.example.homemadefood.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.ActivityEntryBinding;
import com.example.homemadefood.fragments.AuthenticationFragment;
import com.example.homemadefood.fragments.EmailSignInFragment;
import com.example.homemadefood.fragments.PhoneSignInFragment;
import com.example.homemadefood.fragments.SelectFragment;
import com.example.homemadefood.fragments.SignUpFragment;
import com.example.homemadefood.fragments.SplashFragment;

public class EntryActivity extends AppCompatActivity {

    ActivityEntryBinding binding;
    SplashFragment splashFragment = new SplashFragment();
    SignUpFragment signUpFragment = new SignUpFragment();
    SelectFragment selectFragment = new SelectFragment();
    EmailSignInFragment emailSignInFragment = new EmailSignInFragment();
    PhoneSignInFragment phoneSignInFragment = new PhoneSignInFragment();
    AuthenticationFragment authenticationFragment = new AuthenticationFragment();

    Intent intent;
    String select,authType, authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        select = intent.getStringExtra("select");
        authType = intent.getStringExtra("authType");
        authentication = intent.getStringExtra("authentication");

        if (select == null && authType == null && authentication == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, splashFragment).commit();
        }

        if (select != null && select.equals("select")){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, selectFragment).commit();
        }

        if (authType != null){
            if (authType.equals("email")){
                getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, emailSignInFragment).commit();
            }
            if (authType.equals("phone")){
                getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, phoneSignInFragment).commit();
            }
            if (authType.equals("signUp")){
                getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, signUpFragment).commit();
            }
        }

        if (authentication != null && authentication.equals("authentication")){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, authenticationFragment).commit();
        }

    }
}