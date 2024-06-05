package com.example.progkino3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class TrenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren);
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
    public void navigatorContact(View view){
        Intent intentContact = new Intent(this, ContactActivity.class);
        intentContact.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentContact);
    }
    public void navigatorChat(View view){
        Intent intentChat = new Intent(this, ChatActivity.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }

    public void onClickRead (View view){
        Intent intentTrening = new Intent(TrenActivity.this, TreningActivity.class);
        intentTrening.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening);

    }

    public void onClickRead2 (View view){
        Intent intentTrening2 = new Intent(TrenActivity.this, Trening2Activity.class);
        intentTrening2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening2);
    }

    public void onClickReadChGK (View view) throws IOException {
        Intent intentTreningChGK = new Intent(this, TreningActivity_ChGK.class);
        intentTreningChGK.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTreningChGK);
    }
    public void onClickReadSvoyak (View view) throws IOException {
        Intent intentTreningSvoyak = new Intent(this, TreningActivity_Svoyak.class);
        intentTreningSvoyak.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTreningSvoyak);
    }

}
