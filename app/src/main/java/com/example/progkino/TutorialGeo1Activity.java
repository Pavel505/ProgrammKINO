package com.example.progkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;
import java.util.List;

public class TutorialGeo1Activity extends AppCompatActivity {

    private ListView list_quest_geo;
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    DatabaseReference quest_geo;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_geo1);
        init();
        getDataFromDB();
    }
    private void init(){
        list_quest_geo = findViewById(R.id.list_quest_geo);
        listData = new ArrayList<>();
        adapterAr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        list_quest_geo.setAdapter(adapterAr1);
        db = FirebaseDatabase.getInstance();
        quest_geo = db.getReference("TreningGeo1");
    }
    private void getDataFromDB(){
        ValueEventListener vlistener_geo1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    QuestionTrenTutor questionTrenTutor = ds.getValue(QuestionTrenTutor.class);
                    String txt = questionTrenTutor.getCountry() +":"+ questionTrenTutor.getCapital();
                    listData.add(txt);
                    //listData.add(ds.getValue().toString());
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        quest_geo.addValueEventListener(vlistener_geo1);
    }
}
