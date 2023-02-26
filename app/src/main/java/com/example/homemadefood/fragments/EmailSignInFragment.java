package com.example.homemadefood.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.ContainerActivity;
import com.example.homemadefood.activities.EntryActivity;
import com.example.homemadefood.activities.MainActivity;
import com.example.homemadefood.databinding.FragmentEmailSignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailSignInFragment extends Fragment {

    FragmentEmailSignInBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressDialog dialog;
    Intent intent;
    String userType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmailSignInBinding.inflate(getLayoutInflater(), container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Singing In");
        dialog.setCancelable(false);

        intent = getActivity().getIntent();
        userType = intent.getStringExtra("userType");

        Log.i("TAG", "Email User Type: "+userType);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null){
            if (userType.equals("Chef")){
                Intent chefIntent = new Intent(getActivity(), ContainerActivity.class);
                chefIntent.putExtra("userType", "Chef");
                chefIntent.putExtra("signIn", "Chef");
                startActivity(chefIntent);
                getActivity().finish();
            }
            if (userType.equals("Consumer")){
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
            if (userType.equals("Delivery")){
                Intent chefIntent = new Intent(getActivity(), ContainerActivity.class);
                chefIntent.putExtra("userType", "Delivery");
                chefIntent.putExtra("signIn", "Delivery");
                startActivity(chefIntent);
                getActivity().finish();
            }
        }


        binding.signInBtn.setOnClickListener(view -> {
            String email = binding.userEmail.getText().toString().trim();
            String password = binding.userPassword.getText().toString().trim();
            if (email.equals("")){
                binding.userEmail.setError("Required");
            }else if (password.equals("")){
                binding.userPassword.setError("Required");
            }else {
                userSignIn(email, password);
            }
        });

        binding.signUpNowBtn.setOnClickListener(view -> {
            Intent signUpNowIntent = new Intent(getActivity(), EntryActivity.class);
            signUpNowIntent.putExtra("authType", "signUp");
            signUpNowIntent.putExtra("userType", userType);
            startActivity(signUpNowIntent);
            getActivity().finish();
        });

        binding.phoneAuthBtn.setOnClickListener(view -> {
            Intent phoneAuthIntent = new Intent(getActivity(), EntryActivity.class);
            phoneAuthIntent.putExtra("authType", "phone");
            phoneAuthIntent.putExtra("userType", userType);
            startActivity(phoneAuthIntent);
            getActivity().finish();
        });

        return binding.getRoot();
    }

    private void userSignIn(String email, String password) {
        dialog.show();

        if (firebaseAuth.getCurrentUser().isEmailVerified()){
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        dialog.dismiss();
                        if (userType.equals("Chef")){
                            Intent chefIntent = new Intent(getActivity(), ContainerActivity.class);
                            chefIntent.putExtra("userType", "Chef");
                            chefIntent.putExtra("signIn", "Chef");
                            startActivity(chefIntent);
                            getActivity().finish();
                        }else if (userType.equals("Consumer")){
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }else if (userType.equals("Delivery")){
                            Intent chefIntent = new Intent(getActivity(), ContainerActivity.class);
                            chefIntent.putExtra("userType", "Delivery");
                            chefIntent.putExtra("signIn", "Delivery");
                            startActivity(chefIntent);
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), "Please Select a User Type First", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    ShowAlert(e.getLocalizedMessage());
                }
            });
        }else {
            Toast.makeText(getActivity(), "Please verify your email first", Toast.LENGTH_SHORT).show();
        }
    }
    private void ShowAlert(String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Caution!");
        builder.setMessage(errorMsg);
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}