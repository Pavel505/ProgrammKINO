package com.example.progkino;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progkino.Models.Eventum;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TreningActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    DatabaseReference users,eventumes;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening);
        init();
        getDataFromDB();
    }
    private void init(){
        listView = findViewById(R.id.listView1);
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");
        eventumes = db.getReference("Eventum");
    }
    private void getDataFromDB(){
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Eventum eventum = ds.getValue(Eventum.class);
                    String txt = eventum.getTitle() +":"+ eventum.getType();
                    listData.add(txt);
                    //listData.add(ds.getValue().toString());
                }
                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        eventumes.addValueEventListener(vlistener);
    }

}