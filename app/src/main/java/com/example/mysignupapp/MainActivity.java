package com.example.mysignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText edt1, edt2, edt3, edt4, edt5;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById (R.id.btn_register);
        edt1 = findViewById(R.id.fist_name);
        edt2 = findViewById(R.id.second_name);
        edt3 = findViewById(R.id.username);
        edt4 = findViewById(R.id.email);
        edt5 = findViewById(R.id.contacts);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
                startActivity(new Intent(MainActivity.this, UberMap.class));
            }
        });

    }

    private void uploadData() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Clients");

    }



}