package com.example.progkino;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    Integer positionEditDel;
    String eventum_title_pred,eventum_type_pred,eventum_date_pred,eventum_descript_pred;
    TextView ev_name_admin,ev_type_admin,ev_date_admin,ev_descript_admin;
    Button btn_edit_eventum,btn_delete_eventum,btn_search_eventum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_eventum);
        init();
        getDataFromDB_AdminEventum();
        setOnClickItemEventum();
        Button btn_edit_eventum = (Button) findViewById(R.id.btnChatSend);
        btn_edit_eventum.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventum_from_DB();
            }
        });
        Button btn_del_eventum = (Button) findViewById(R.id.btnSignIn2);
        btn_del_eventum.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                setOnClickItemEventum();
                eventum_delete_DB();
            }
        });
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
        ev_name_admin  = findViewById(R.id.text_title_eventum_admin);
        ev_date_admin  = findViewById(R.id.text_date_eventum_admin);
        ev_type_admin  = findViewById(R.id.text_type_eventum_admin);
        ev_descript_admin  = findViewById(R.id.text_descript_eventum_admin);
        ev_name_admin.setText(" ");
        ev_date_admin.setText(" ");
        ev_type_admin.setText(" ");
        ev_descript_admin.setText(" ");
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

    public void setOnClickItemEventum(){
        list_eventumes_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Eventum eventum = listTemp.get(position);
                positionEditDel = position;
                eventum_title_pred = eventum.getTitle().toString();
                eventum_type_pred = eventum.getType().toString();
                eventum_date_pred = eventum.getDateEventum().toString();
                eventum_descript_pred = eventum.getEventumDescription().toString();
                ev_name_admin.setText(eventum_title_pred);
                ev_type_admin.setText(eventum_type_pred);
                ev_date_admin.setText(eventum_date_pred);
                ev_descript_admin.setText(eventum_descript_pred);

                /*Intent i = new Intent(TutorialGeo1Activity.this, TutorialShowActivity.class);
                i.putExtra(GEO_CAPITAL, questionTrenTutor.getCapital());
                i.putExtra(GEO_COUNTRY, questionTrenTutor.getCountry());
                i.putExtra(GEO_IMAGE, questionTrenTutor.getImgUrl() );
                startActivity(i);*/
            }
        });
    }
    private void eventum_from_DB(){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Eventum eventum = ds.getValue(Eventum.class);
                    if(eventum_title_pred.equalsIgnoreCase(eventum.getTitle().toString())){
                        // Логика удаления данных
                        //DatabaseReference itemRef = ds.getRef();
                        //itemRef.removeValue();
                        return;
                    };
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
    private void eventum_delete_DB(){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Eventum eventum = ds.getValue(Eventum.class);
                    if(eventum_title_pred.equalsIgnoreCase(eventum.getTitle().toString())){
                        // Логика замены данных
                        DatabaseReference itemRef = ds.getRef();
                        itemRef.removeValue();
                        return;
                    };
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