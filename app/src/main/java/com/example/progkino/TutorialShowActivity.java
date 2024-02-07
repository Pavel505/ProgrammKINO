package com.example.progkino;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TutorialShowActivity extends AppCompatActivity {
    private TextView tvCapital, tvCountry;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_show);
        init();
        getIntentMain();
    }
     private void init(){
        tvCapital = findViewById(R.id.tvCapital);
        tvCountry= findViewById(R.id.tvCountry);
     }

     private void getIntentMain(){
        Intent i2 = getIntent();
        if(i2 != null){
            tvCapital.setText(i2.getStringExtra(GEO_CAPITAL));
            tvCountry.setText(i2.getStringExtra(GEO_COUNTRY));
        }
     }

}
