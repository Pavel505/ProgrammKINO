package com.example.progkino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EventumActivity_Admin extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    DatabaseReference eventumes, categories;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_eventum);
        init();

    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        categories = db.getReference("Category");

    }
    public void navigatorUser(View view){
        Intent intentChat = new Intent(this, UserActivity_Admin.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }
    public void navigatorMainAdmin(View view){
        Intent intentChat2 = new Intent(this, HomeActivity_Admin.class);
        intentChat2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat2);
    }


}