package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.MainActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityDBS extends AppCompatActivity {
    private EditText inputKey;
    private Button buttonFetch;
    private TextView textResult;
    private Button buttonReturn;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbs);

        inputKey = findViewById(R.id.input_key);
        buttonFetch = findViewById(R.id.button_fetch);
        textResult = findViewById(R.id.text_result);
        buttonReturn = findViewById(R.id.button);

        FirebaseApp.initializeApp(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cars/Cars");

        buttonFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDBS.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void fetchData() {
        String key = inputKey.getText().toString().trim();

        if (TextUtils.isEmpty(key)) {
            Toast.makeText(this, "Please enter a key", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fetch data from Firebase
        databaseReference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get Car object and use the values to update the UI
                    Car car = dataSnapshot.getValue(Car.class);

                    if (car != null) {
                        String result = "Model: " + car.getCarModel() + "\n" +
                                "Type: " + car.getCarType() + "\n" +
                                "Owner: " + car.getOwnerName() + "\n" +
                                "Contact: " + car.getOwnerContact();
                        textResult.setText(result);
                    }
                } else {
                    textResult.setText("No data found for the key: " + key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Toast.makeText(ActivityDBS.this, "Error fetching data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}