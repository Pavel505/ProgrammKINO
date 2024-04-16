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
    public static List<Eventum> fullEventumList = new ArrayList<>();
    public static List<Category> categoryList = new ArrayList<>();
    public static String color1 = "#E91E1E";
    public static String color2 = "#4CAF50";
    public static String color3 = "#0B24AF";
    public static String color4 = "#9C27B0";
    DatabaseReference eventumes, categories, messages;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        eventumList.add(new Eventum(5, "#350307","2024-02-01" ,"Турнир по игре ЧГК","chgk3", "Городская студ лига", "Интеллект"));
        eventumList.add(new Eventum(6,"#D300A5CD","Вечер настолок", "2024-03-02","nastolki", "Настолки","Настолки"));
        fullEventumList.addAll(eventumList);

        categoryList.add(new Category(1,"Интеллект"));
        categoryList.add(new Category(2,"Настолки"));
        categoryList.add(new Category(3,"Сюжетки"));
        categoryList.add(new Category(4,"Турниры"));
        categoryList.add(new Category(5,"Обновить"));

        setCategoryRecycler(categoryList);
        setEventumRecycler(eventumList);

        for(Eventum ev : eventumList){
            switch (ev.getType()) {
                case  ("Интеллект"):
                    ev.setImg("chgk1");
                    ev.setColor(color1);
                    break;
                case ("Настолки"):
                    ev.setImg("nastolki");
                    ev.setColor(color2);
                    break;
                case ("Сюжетки"):
                    ev.setImg("siget");
                    ev.setColor(color3);
                    break;
                default:
                    ev.setImg("turnir1");
                    ev.setColor(color4);
                    break;
            }
        }

    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        categories = db.getReference("Category");
        messages = db.getReference("Message");
        messages.setValue("Like12");
    }

    /*public void filterAll(){
        eventumList.clear();
        eventumList.addAll(fullEventumList);
    }*/
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
    public void navigatorChat(View view){
        Intent intentChat = new Intent(this, ChatActivity.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }
    private void setEventumRecycler(List<Eventum> eventumList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        eventumRecycler.hasFixedSize();
        eventumRecycler.setLayoutManager(layoutManager);

        eventumAdapter = new EventumAdapter(this, eventumList);
        eventumRecycler.setAdapter(eventumAdapter);
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(eventumList.size() > 0 )eventumList.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Eventum eventum = ds.getValue(Eventum.class);
                    /*eventumList.add(new Eventum(eventum.getId(),"#350307",eventum.getDateEventum().toString(),eventum.getEventumDescription().toString(),
                            "chgk", eventum.getTitle().toString(), eventum.getType().toString()));*/
                    eventumList.add(eventum);
                    fullEventumList.add(eventum);
                }
                eventumAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                DatabaseError databaseError = null;
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        eventumes.addValueEventListener(vlistener);
        Log.w(TAG, "Размер списка мероприятий: " + eventumList.size() + fullEventumList.size());

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
        Log.w(TAG, "Размер списка мероприятий2: " + eventumList.size() + fullEventumList.size());
        eventumList.clear();
        eventumList.addAll(fullEventumList);
        List<Eventum> filterEventumes = new ArrayList<>();
       // String secondElement = String.valueOf(categoryList.get(category));
        if (category != "Обновить") {
            for (Eventum ev : eventumList) {
                if (ev.getType().equalsIgnoreCase(category)) {
                    filterEventumes.add(ev);
                    Log.w(TAG, "loadPost:onCancelled" + ev.getType() + category);
                }
            }
            eventumList.clear();
            eventumList.addAll(filterEventumes);
        }

        eventumAdapter.notifyDataSetChanged();// Берет новые значения и обновляет

    }
}