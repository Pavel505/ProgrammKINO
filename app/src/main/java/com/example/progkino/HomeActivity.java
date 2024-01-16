package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.progkino.Models.Category;
import com.example.progkino.Models.Eventum;
import com.example.progkino.adapter.CategoryAdapter;
import com.example.progkino.adapter.EventumAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    RecyclerView categoryRecycler,eventumRecycler;
    CategoryAdapter categoryAdapter;
    EventumAdapter eventumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Интеллект"));
        categoryList.add(new Category(2,"Настолки"));
        categoryList.add(new Category(3,"Сюжетки"));
        categoryList.add(new Category(4,"Турниры"));

        setCategoryRecycler(categoryList);


        List<Eventum> eventumList = new ArrayList<>();

        eventumList.add(new Eventum(1,"chgk","#610C13","Лига вузов европы","2024-02-01" , "Интеллект","Турнир по игре ЧГК"));
        eventumList.add(new Eventum(2,"vorosh","#D3176DC8","Ворошиловский стрелок\n 5 этап", "2024-02-02", "Интеллект","Вечер настолок"));


        setEventumRecycler(eventumList);
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
}