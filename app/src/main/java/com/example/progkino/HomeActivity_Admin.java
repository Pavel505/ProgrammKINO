package com.example.progkino;

import static android.content.ContentValues.TAG;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Category;
import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.QuestionTrenTutor;
import com.example.progkino.Models.Review;
import com.example.progkino.adapter.CategoryAdapter;
import com.example.progkino.adapter.EventumAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity_Admin extends AppCompatActivity {
    DatabaseReference eventumes;
    FirebaseDatabase db;
    private ListView list_user_otzv;
    private ArrayAdapter<String> adapterAr43;
    private List<String> listData;
    private List<Review> listTemp;
    DatabaseReference quest_geo,reviews;
    String dop ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_home);
        init();
        getDataFromDB();
        setOnClickItem();

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
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr43 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        list_user_otzv.setAdapter(adapterAr43);

        quest_geo = db.getReference("TreningGeo1");
        reviews = db.getReference("Review");
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

    private void setOnClickItem(){
        list_user_otzv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Review review = listTemp.get(position);
                /*Intent i = new Intent(TutorialGeo1Activity.this, TutorialShowActivity.class);
                i.putExtra(GEO_CAPITAL, questionTrenTutor.getCapital());
                i.putExtra(GEO_COUNTRY, questionTrenTutor.getCountry());
                i.putExtra(GEO_IMAGE, questionTrenTutor.getImgUrl() );
                startActivity(i);*/
            }
        });
    }

}