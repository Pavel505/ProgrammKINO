package com.example.progkino3;

import static com.example.progkino3.Constant.REVIEW_AUTHOR;
import static com.example.progkino3.Constant.REVIEW_CONTENT;
import static com.example.progkino3.Constant.REVIEW_TEMA;
import static com.example.progkino3.Constant.REVIEW_TIME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progkino3.Models.Rating;
import com.example.progkino3.Models.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity_Admin extends AppCompatActivity {
    DatabaseReference eventumes,ratings;
    FirebaseDatabase db;
    private ListView list_user_otzv,list_user_tren;
    private ArrayAdapter<String> adapterAr43, adapterAr4123;
    private List<String> listData,listData12;
    private List<Review> listTemp;
    private List<Rating> listTempRa;
    DatabaseReference quest_geo,reviews;
    String dop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_home);
        init();
        getDataFromDB();
        setOnClickItem();
        getDataFromDB_trenUsers();

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
    private void init(){
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        list_user_otzv = findViewById(R.id.list_user_otzv);
        list_user_tren = findViewById(R.id.list_user_tren);
        listData = new ArrayList<>();
        listData12 = new ArrayList<>();
        listTempRa = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr43 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        adapterAr4123 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData12);
        list_user_otzv.setAdapter(adapterAr43);
        list_user_tren.setAdapter(adapterAr4123);


        quest_geo = db.getReference("TreningGeo1");
        reviews = db.getReference("Review");
        ratings = db.getReference("Rating");
    }
    private void getDataFromDB(){
        ValueEventListener vlistener_geo1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Review review = ds.getValue(Review.class);
                    if (review.getScan()) {
                        dop = "Прочитано";
                    } else {
                        dop = "НЕ ПРОЧИТАНО";
                    }
                    String txt = "Время: "+ review.getTimeReview() + " Пишет о: "+ review.getTemaReview()+ "\n" + dop;
                    listData.add(txt);
                    listTemp.add(review);
                }
                adapterAr43.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        reviews.addValueEventListener(vlistener_geo1);
    }
    private void getDataFromDB_trenUsers(){
        ValueEventListener vlistener_geo_trenUser1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData12.size() > 0 )listData12.clear();
                if(listTempRa.size() > 0 )listTempRa.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Rating rating = ds.getValue(Rating.class);
                    String txt = "Игрок: "+ rating.getUserName() + "\n" + " Ответы: + "+ rating.getAnswerYes() + " | - " + rating.getAnswerNo() +
                            "\n" + "Тема:" + rating.getAnswerTema_test();
                    listData12.add(txt);
                    listTempRa.add(rating);
                }
                adapterAr4123.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        ratings.addValueEventListener(vlistener_geo_trenUser1);
    }

    private void setOnClickItem(){
        list_user_otzv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Review review = listTemp.get(position);
               // Intent i = new Intent(TutorialGeo1Activity.this, TutorialShowActivity.class);
                Intent i = new Intent(HomeActivity_Admin.this, ReviewShowActivity_Admin.class);
                i.putExtra(REVIEW_AUTHOR, review.getAuthorReview());
                i.putExtra(REVIEW_TEMA, review.getTemaReview());
                i.putExtra(REVIEW_TIME, review.getTimeReview());
                i.putExtra(REVIEW_CONTENT, review.getReview());
                startActivity(i);
            }
        });
    }
    public void inUserStr(View view){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
        Toast toast5 = Toast.makeText(getApplicationContext(), "Пользовательский режим",
                Toast.LENGTH_SHORT);
        toast5.show();
    }

}