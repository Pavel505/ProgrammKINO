package com.example.progkino;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.widget.ListView;
import com.google.firebase.database.DatabaseReference;


import java.util.List;

public class TutorialGeo1Activity extends AppCompatActivity {

    private ListView list_quest_geo;
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    private List<QuestionTrenTutor> listTemp;
    DatabaseReference quest_geo;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_geo1);
        init();
        getDataFromDB();
        setOnClickItem();
    }
    private void init(){
        list_quest_geo = findViewById(R.id.list_quest_geo);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
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
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    QuestionTrenTutor questionTrenTutor = ds.getValue(QuestionTrenTutor.class);
                    String txt = questionTrenTutor.getCountry() +":"+ questionTrenTutor.getCapital();
                    listData.add(txt);
                    listTemp.add(questionTrenTutor);
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        quest_geo.addValueEventListener(vlistener_geo1);
    }

    private void setOnClickItem(){
        list_quest_geo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionTrenTutor questionTrenTutor = listTemp.get(position);
                Intent i = new Intent(TutorialGeo1Activity.this, TutorialShowActivity.class);
                i.putExtra(GEO_CAPITAL, questionTrenTutor.getCapital());
                i.putExtra(GEO_COUNTRY, questionTrenTutor.getCountry());
                i.putExtra(GEO_IMAGE, questionTrenTutor.getImgUrl() );
                startActivity(i);
            }
        });
    }

}
