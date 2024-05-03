package com.example.progkino;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Category;
import com.example.progkino.Models.Eventum;
import com.example.progkino.adapter.CategoryAdapter;
import com.example.progkino.adapter.EventumAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity_Admin extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    DatabaseReference eventumes, categories;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_home);
        init();

    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        categories = db.getReference("Category");

    }
    public void navigatorUser(View view){
        Intent intentChat3 = new Intent(this, UserActivity_Admin.class);
        intentChat3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat3);
    }
    public void navigatorEventum(View view){
        Intent intentChat4 = new Intent(this, EventumActivity_Admin.class);
        intentChat4.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat4);
    }


}