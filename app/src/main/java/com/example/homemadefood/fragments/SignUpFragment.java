package com.example.homemadefood.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homemadefood.R;
import com.example.homemadefood.activities.ContainerActivity;
import com.example.homemadefood.activities.EntryActivity;
import com.example.homemadefood.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Intent intent;
    String userType, phoneNumber;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater(), container, false);

        intent = getActivity().getIntent();
        userType = intent.getStringExtra("userType");
        binding.userType.setText(userType);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Singing Up as a "+userType);
        dialog.setCancelable(false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        binding.signUpBtn.setOnClickListener(view -> {

            String firstName = binding.userFirstName.getText().toString().trim();
            String lastName = binding.userLastName.getText().toString().trim();
            String email = binding.userEmail.getText().toString().trim();
            String password = binding.userPassword.getText().toString().trim();
            String confirmPassword = binding.userRePassword.getText().toString().trim();
            String houseAddress = binding.houseAddress.getText().toString().trim();
            String areaAddress = binding.areaAddress.getText().toString().trim();
            String postalCode = binding.postalCodeAddress.getText().toString().trim();
            String phone = binding.phoneNumberInput.getText().toString().trim();

            if (firstName.equals("")){
                binding.userFirstName.setError("Required");
            }else if (lastName.equals("")){
                binding.userLastName.setError("Required");
            }else if (email.equals("")){
                binding.userEmail.setError("Required");
            }else if (password.equals("")){
                binding.userPassword.setError("Required");
            }else if (confirmPassword.equals("") || !confirmPassword.equals(password)){
                binding.userRePassword.setError("Not Match");
            }else if (houseAddress.equals("")){
                binding.houseAddress.setError("Required");
            }else if (areaAddress.equals("")){
                binding.areaAddress.setError("Required");
            }else if (phone.equals("")){
                binding.phoneNumberInput.setError("Required");
            }else {
                userSignUp(firstName, lastName, email, password, confirmPassword, houseAddress, areaAddress, postalCode, phone, userType);
            }

        });

        binding.loginNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "email");
            intent.putExtra("userType", userType);
            startActivity(intent);
            getActivity().finish();
        });

        binding.emailAuthBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "email");
            intent.putExtra("userType", userType);
            startActivity(intent);
            getActivity().finish();
        });

        binding.phoneAuthBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("authType", "phone");
            intent.putExtra("userType", userType);
            startActivity(intent);
            getActivity().finish();
        });



        return binding.getRoot();
    }
    private void userSignUp(String firstName, String lastName, String email, String password, String confirmPassword, String houseAddress, String areaAddress, String postalCode, String phone, String userType) {

        dialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String userId = firebaseUser.getUid();
                    phoneNumber = binding.ccpPhone.selectedCountryCode()+phone;

                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("user_id", userId);
                    userMap.put("first_name", firstName);
                    userMap.put("last_name", lastName);
                    userMap.put("profile_image", "");
                    userMap.put("email", email);
                    userMap.put("password", password);
                    userMap.put("confirm_password", confirmPassword);
                    userMap.put("houseAddress", houseAddress);
                    userMap.put("area_address", areaAddress);
                    userMap.put("postal_code", postalCode);
                    userMap.put("phone", phoneNumber);
                    userMap.put("user_type", userType);

                    databaseReference.child(userType).child(userId).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setMessage("You have Successfully Signed Up ! \nPlease make sure verify your phone number and email");
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialog.dismiss();
                                                Intent intent = new Intent(getActivity(), EntryActivity.class);
                                                intent.putExtra("select", "select");
                                                startActivity(intent);
                                                getActivity().finish();
                                            }
                                        });

                                        AlertDialog alertDialog = builder.create();
                                        alertDialog.show();

                                    }else {
                                        ShowAlert(task.getException().getLocalizedMessage());
                                    }
                                }
                            });

                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                ShowAlert(e.getLocalizedMessage());
            }
        });

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