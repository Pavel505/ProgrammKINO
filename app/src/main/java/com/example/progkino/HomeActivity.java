package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.progkino.Models.Category;
import com.example.progkino.Models.Eventum;
import com.example.progkino.adapter.CategoryAdapter;
import com.example.progkino.adapter.EventumAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    CategoryAdapter categoryAdapter;
    static EventumAdapter eventumAdapter;
    static List<Eventum> eventumList = new ArrayList<>();
    static List<Eventum> fullEventumList = new ArrayList<>();
    static List<Category> categoryList = new ArrayList<>();
    String color1 = "#E91E1E";
    String color2 = "#4CAF50";
    String color3 = "#0B24AF";
    String color4 = "#9C27B0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        categoryList.add(new Category(1,"Интеллект"));
        categoryList.add(new Category(2,"Настолки"));
        categoryList.add(new Category(3,"Сюжетки"));
        categoryList.add(new Category(4,"Турниры"));

        setCategoryRecycler(categoryList);

        eventumList.add(new Eventum(1,"chgk","#350307","Лига вузов европы","2024-02-01" , "Интеллект","Турнир по игре ЧГК"));
        eventumList.add(new Eventum(2,"vorosh","#350307","Ворошиловский стрелок\n 5 этап", "2024-02-02", "Интеллект","орошиловский стрелок: 5 этап. Интрига..."));
        eventumList.add(new Eventum(3,"nastolki","#D300A5CD","Вечер настолок", "2024-03-02", "Настолки","Вечер настолок"));
        eventumList.add(new Eventum(4,"voroshchr","#D33D00CD","Чемпионат России", "2024-03-02", "Турниры","Соберет много команд: и Оголодавших ...."));

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