package com.example.finalproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityDBA extends AppCompatActivity {
    private EditText editTextPlateNumber;
    private EditText editTextModel;
    private EditText editTextType;
    private EditText editTextOwner;
    private EditText editTextContact;
    private Button buttonSave;
    private Button buttonReturn;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dba);
        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Cars");
        // Connect UI components
        editTextPlateNumber = findViewById(R.id.editTextPlateNumber);
        editTextModel = findViewById(R.id.editTextModel);
        editTextType = findViewById(R.id.editTextType);
        editTextOwner = findViewById(R.id.editTextOwner);
        editTextContact = findViewById(R.id.editTextContact);
        buttonSave = findViewById(R.id.buttonSave);
        buttonReturn = findViewById(R.id.buttonReturn);
        // Set click listener for Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        // Set click listener for Return button
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This closes the current activity and returns to the previous one
            }
        });
    }
    private void saveData() {
        String plateNumber = editTextPlateNumber.getText().toString().trim();
        String model = editTextModel.getText().toString().trim();
        String type = editTextType.getText().toString().trim();
        String owner = editTextOwner.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();
        if (TextUtils.isEmpty(plateNumber) || TextUtils.isEmpty(model) || TextUtils.isEmpty(type) ||
                TextUtils.isEmpty(owner) || TextUtils.isEmpty(contact)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create Car object
        Car car = new Car(model, type, owner, contact);
        // Save the car to the database with plate number as the key
        databaseReference.child("Cars").child(plateNumber).setValue(car)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ActivityDBA.this, "Car information saved successfully", Toast.LENGTH_SHORT).show();
                        // Clear input fields after saving
                        editTextPlateNumber.setText("");
                        editTextModel.setText("");
                        editTextType.setText("");
                        editTextOwner.setText("");
                        editTextContact.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityDBA.this, "Failed to save car information", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
