package com.example.progkino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EventumActivity_Admin extends AppCompatActivity {
    RecyclerView eventumRecycler;
    DatabaseReference eventumes;
    FirebaseDatabase db;
    private ListView list_eventumes_admin;
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    private List<Eventum> listTemp;
    DatabaseReference ev_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_eventum);
        init();
        getDataFromDB_AdminEventum();

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
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        list_eventumes_admin = findViewById(R.id.list_quest_geo);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        list_eventumes_admin.setAdapter(adapterAr1);
        db = FirebaseDatabase.getInstance();
        ev_admin = db.getReference("Eventum");
    }

    private void getDataFromDB_AdminEventum(){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Eventum eventum = ds.getValue(Eventum.class);
                    String txt = "Название: "+ eventum.getTitle() +"\n Тип: "+ eventum.getType() + "\n Дата: " + eventum
                            .getDateEventum() + "\n Описание: "+ eventum.getEventumDescription();
                    listData.add(txt);
                    listTemp.add(eventum);
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        ev_admin.addValueEventListener(vlistener_eventum1);
    }

}