package com.example.progkino;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class TutorialShowActivity extends AppCompatActivity {
    private TextView tvCapital, tvCountry;
    private ImageView imageViewGeo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_show);
        init();
        getIntentMain();
    }
     private void init(){
        imageViewGeo = findViewById(R.id.imageViewGeo1);
        tvCapital = findViewById(R.id.tvCapital);
        tvCountry= findViewById(R.id.tvCountry);
     }

     private void getIntentMain(){
        Intent i2 = getIntent();
        if(i2 != null){
            Picasso.get().load(i2.getStringExtra(GEO_IMAGE)).into(imageViewGeo);
            tvCapital.setText(i2.getStringExtra(GEO_CAPITAL));
            tvCountry.setText(i2.getStringExtra(GEO_COUNTRY));
        }
     }

}
