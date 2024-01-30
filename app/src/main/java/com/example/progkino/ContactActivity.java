package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void navigatorMainScen(View view){
        Intent intentHome = new Intent(this, HomeActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentHome);
    }
    public void navigatorTutorial(View view){
        Intent intentTutorial = new Intent(this, TutorialActivity.class);
        intentTutorial.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTutorial);
    }
    public void navigatorTren(View view){
        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTren);
    }

}