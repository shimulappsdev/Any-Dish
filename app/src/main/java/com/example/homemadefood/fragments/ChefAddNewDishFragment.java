package com.example.homemadefood.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homemadefood.R;
import com.example.homemadefood.databinding.FragmentChefAddNewDishBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ChefAddNewDishFragment extends Fragment {

    FragmentChefAddNewDishBinding binding;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference, dateBase;
    StorageReference storage;
    Uri dishImgUri;
    String userType = "Chef", dishImgUrl, currentUser, dishName, dishPrice, dishQuantity, dishDescription, dishId;
    ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChefAddNewDishBinding.inflate(getLayoutInflater(), container, false);

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Uploading Dish");
        dialog.setMessage("Please Wait...!");
        dialog.setCancelable(false);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            currentUser = firebaseUser.getUid();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(userType).child(currentUser);
        storage = FirebaseStorage.getInstance().getReference(userType).child(currentUser);

        binding.addDishBtn.setOnClickListener(view -> {
            dishName = binding.dishNameInput.getText().toString().trim();
            dishPrice = binding.dishPriceInput.getText().toString().trim();
            dishQuantity = binding.dishQuantityInput.getText().toString().trim();
            dishDescription = binding.dishDescriptionInput.getText().toString().trim();

            if (dishName.equals("")){
                binding.dishNameInput.setError("Can't leave empty...!");
            }else if (dishPrice.equals("")){
                binding.dishPriceInput.setError("Can't leave empty...!");
            }else if (dishQuantity.equals("")){
                binding.dishQuantityInput.setError("Can't leave empty...!");
            }else if (dishDescription.equals("")){
                binding.dishDescriptionInput.setError("Can't leave empty...!");
            }else {
                postDish();
            }
        });

        binding.addImgBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,100);
        });

        binding.backBtn.setOnClickListener(view -> {
            getActivity().finish();
        });

        return binding.getRoot();
    }

    private void postDish() {

        if (currentUser != null){
            dialog.show();

            dishId = databaseReference.push().getKey();

            dateBase = databaseReference.child("dishes").child(dishId);

            Map<String, Object> dishMap = new HashMap<>();
            dishMap.put("dish_id", dishId);
            dishMap.put("chef_id", currentUser);
            dishMap.put("dish_name", dishName);
            dishMap.put("dish_price", dishPrice);
            dishMap.put("dish_quantity", dishQuantity);
            dishMap.put("dish_description", dishDescription);
            dishMap.put("dish_image", "");

            dateBase.setValue(dishMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        dialog.dismiss();
                        binding.dishNameInput.setText("");
                        binding.dishPriceInput.setText("");
                        binding.dishQuantityInput.setText("");
                        binding.dishDescriptionInput.setText("");
                        Toast.makeText(getActivity(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Failed to Upload", Toast.LENGTH_SHORT).show();
                }
            });

            if (dishImgUri != null){
                StorageReference storageReference = storage.child("Dish_Image").child("chefDish"+System.currentTimeMillis());
                storageReference.putFile(dishImgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dishImgUrl = String.valueOf(uri);
                                    Map<String, Object> dishMap = new HashMap<>();
                                    dishMap.put("dish_image", dishImgUrl);
                                    dateBase.updateChildren(dishMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                binding.dishImg.setImageResource(R.drawable.imagehint);
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            dishImgUri = data.getData();
            binding.dishImg.setImageURI(dishImgUri);
        }

    }
}