package com.example.progkino;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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


public class HomeActivity extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    CategoryAdapter categoryAdapter;
    static EventumAdapter eventumAdapter;
    public static List<Eventum> eventumList = new ArrayList<>();
    static List<Eventum> fullEventumList = new ArrayList<>();
    static List<Category> categoryList = new ArrayList<>();
    String color1 = "#E91E1E";
    String color2 = "#4CAF50";
    String color3 = "#0B24AF";
    String color4 = "#9C27B0";
    private ListView listView;
    private RecyclerView listView2;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    DatabaseReference users,eventumes;
    FirebaseDatabase db;
    //public long id ;
    //String dat, color ,img ,descr,type ,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.w(TAG, "loadPost:onCancelled1");
        init();
        getDataFromDB();
        Log.w(TAG, "loadPost:onCancelled3");

        eventumList.add(new Eventum(4, "#350307","2024-02-01" ,"Турнир по игре ЧГК","chgk", "Городская студ лига", "Интеллект"));
       /* eventumList.add(new Eventum(2,"vorosh","#350307","Ворошиловский стрелок\n 5 этап", "2024-02-02", "Интеллект","орошиловский стрелок: 5 этап. Интрига..."));
        eventumList.add(new Eventum(3,"nastolki","#D300A5CD","Вечер настолок", "2024-03-02", "Настолки","Вечер настолок"));
        eventumList.add(new Eventum(4,"voroshchr","#D33D00CD","Чемпионат России", "2024-03-02", "Турниры","Соберет много команд: и Оголодавших ...."));
*/
        Log.w(TAG, "l6" + eventumList.get(0).getTitle());
        eventumList.add(new Eventum(7, "#370307","2025-02-01" ,
                "Турнир по игре ЧГП","chgk", "Лига irjkjn77777777", "Интеллект"));
        categoryList.add(new Category(1,"Интеллект"));
        categoryList.add(new Category(2,"Настолки"));
        categoryList.add(new Category(3,"Сюжетки"));
        categoryList.add(new Category(4,"Турниры"));

        setCategoryRecycler(categoryList);

        for(Eventum ev : eventumList){
            switch (ev.getType()) {
                case  ("Интеллект"):
                    ev.setImg("chgk");
                    ev.setColor(color1);
                    break;
                case ("Настолки"):
                    ev.setImg("nastolki");
                    ev.setColor(color2);
                    break;
                case ("Сюжетки"):
                    ev.setImg("nastolki");
                    ev.setColor(color3);
                    break;
                default:
                    ev.setColor(color4);
                    break;
            }
        }

        fullEventumList.addAll(eventumList);
        setEventumRecycler(eventumList);
    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler); // Ошибка может?
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        //eventumRecycler.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
    }
    public void getDataFromDB(){
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadPost:onCancelled2");
                if(eventumList.size() > 0 )eventumList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Log.w(TAG, "loadPost:onCancelled4");
                    Eventum eventum = ds.getValue(Eventum.class);
                    long id = eventum.getId();String title = eventum.getTitle();
                    String dat = eventum.getDateEventum();String color = eventum.getColor();
                    String img = eventum.getImg();String descr = eventum.getEventumDescription();
                    String type = eventum.getType();
                    eventumList.add(new Eventum(id,color,dat,descr, img, title, type));
                    eventumList.add(new Eventum(eventum.getId(),"null",eventum.getDateEventum().toString(),eventum.getEventumDescription().toString(),
                            "null", eventum.getTitle().toString(), eventum.getType().toString()));
                    eventumList.add(new Eventum(1, "#370307","2025-02-01" ,
                            "Турнир по игре ЧГП","chgk", "Лига irjkjn", "Интеллект"));

                    eventumList.add(eventum);
                    Log.w(TAG, "loadPost:onCancelled5" + eventum.getTitle());

                }
                eventumList.add(new Eventum(7, "#370307","2025-02-01" ,
                        "Турнир по игре ЧГП","chgk", "Лига irjkjn77777777", "Интеллект"));
                adapterAr.notifyDataSetChanged();
                Log.w(TAG, "l5" + eventumList.get(0).getTitle());
                Log.w(TAG, "loadPost:onCancelled5" + eventumList.get(1).getTitle());
                Log.w(TAG, "loadPost:onCancelled5" + eventumList.get(2).getTitle());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                DatabaseError databaseError = null;
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        eventumes.addValueEventListener(vlistener);
    }
    private void getDataFromDB2(){/*
        eventumes.child("Eventum").child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });*/

    }
    /*
    View.OnClickListener(new View.OnClickListener){

    }
    TextView.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {

        }

    });
    tv.setMovementMethod(LinkMovementMethod.getInstance());

    Button btnSignIn2 = (Button) findViewById(R.id.button);
    clickTutorial.setOnClickListener{
        navigatorTutorial();
    }*/
    //TextView clickTutorial = (TextView) findViewById(R.id.tutorial);

    public void navigatorMainScen(View view){
        Intent intentHome = new Intent(HomeActivity.this, HomeActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentHome);
    }
    public void navigatorTutorial(View view){
        Intent intentTutorial = new Intent(HomeActivity.this, TutorialActivity.class);
        intentTutorial.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTutorial);
    }
    public void navigatorTren(View view){
        Intent intentTren = new Intent(HomeActivity.this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTren);
    }
    public void navigatorContact(View view){
        Intent intentContact = new Intent(HomeActivity.this, ContactActivity.class);
        intentContact.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentContact);
    }
    private void setEventumRecycler(List<Eventum> eventumList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        eventumRecycler = findViewById(R.id.eventumRecycler);
        eventumRecycler.setLayoutManager(layoutManager);

        eventumAdapter = new EventumAdapter(this, eventumList);
        eventumRecycler.setAdapter(eventumAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        // Эдементы идут горизонтально
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showEventumesByCategory(String category){

        eventumList.clear();
        eventumList.addAll(fullEventumList);
        List<Eventum> filterEventumes = new ArrayList<>();
       // String secondElement = String.valueOf(categoryList.get(category));

        for(Eventum ev : eventumList){
            if(ev.getType() == category) {
                filterEventumes.add(ev);
            }
        }

        eventumList.clear();
        eventumList.addAll(filterEventumes);

        eventumAdapter.notifyDataSetChanged();// Берет новые значения и обновляет

    }
}