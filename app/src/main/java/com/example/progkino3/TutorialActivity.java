package com.example.progkino3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }
    public void navigatorMainScen(View view){
        Intent intentHome = new Intent(this, HomeActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentHome);
    }
    public void navigatorTren(View view){
        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTren);
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
    public void tutorial_geo1(View view){
        Intent intentGeo1 = new Intent(this, TutorialGeo1Activity.class);
        intentGeo1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentGeo1);
    }

    public void onClickRead4 (View view){

        Toast toast4 = Toast.makeText(getApplicationContext(), "Раздел в разработке!",
                Toast.LENGTH_SHORT);
        toast4.show();
       /* Intent intentTrening2 = new Intent(TrenActivity.this, Trening2Activity.class);
        intentTrening2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening2);*/
    }

}