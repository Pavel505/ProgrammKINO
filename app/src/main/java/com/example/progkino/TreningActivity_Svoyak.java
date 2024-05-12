package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TreningActivity_Svoyak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_svoyak);
    }



    public void nextQuestion_Svoyak(){

    }

    public void stopActivitySvoyak(View view){
        Intent intentTren_Sv = new Intent(this, TrenActivity.class);
        intentTren_Sv.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren_Sv);
    }

}