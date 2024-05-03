package com.example.progkino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserActivity_Admin extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    DatabaseReference eventumes, categories;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_user);
        init();

    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        categories = db.getReference("Category");

    }
    public void navigatorUser(View view){
        Intent intentChat5 = new Intent(this, UserActivity_Admin.class);
        intentChat5.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat5);
    }
    public void navigatorEventum(View view){
        Intent intentChat6 = new Intent(this, EventumActivity_Admin.class);
        intentChat6.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat6);
    }
    public void navigatorMainAdmin(View view){
        Intent intentChat7 = new Intent(this, HomeActivity_Admin.class);
        intentChat7.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat7);
    }

}