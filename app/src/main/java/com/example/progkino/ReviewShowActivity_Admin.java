package com.example.progkino;

import static android.content.ContentValues.TAG;
import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;
import static com.example.progkino.Constant.REVIEW_AUTHOR;
import static com.example.progkino.Constant.REVIEW_CONTENT;
import static com.example.progkino.Constant.REVIEW_TEMA;
import static com.example.progkino.Constant.REVIEW_TIME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progkino.Models.Review;
import com.example.progkino.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ReviewShowActivity_Admin extends AppCompatActivity {
    private TextView text_author, text_time,text_tema,text_review;
    String author,otzv_ves;
    private ArrayAdapter<String> adapterAr3;
    private List<String> listData4,listData432;
    FirebaseAuth auth;
    DatabaseReference reviews;
    FirebaseDatabase db;
    Integer plus_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_show_admin);
        init();
        text_author = findViewById(R.id.textView31_user);

        text_review = findViewById(R.id.textView31_otzv);

        getIntentMain_Review();
        review_reading();
    }

    private void init(){
        listData4 = new ArrayList<>();
        adapterAr3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData4);
        text_time = findViewById(R.id.textView31_time);
        text_tema = findViewById(R.id.textView31_tema);

        db = FirebaseDatabase.getInstance();
        reviews = db.getReference("Review");
    }
    public void review_reading(){
        ValueEventListener vlistener_user1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData4.size() > 0 )listData4.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Review review = ds.getValue(Review.class);
                    DatabaseReference itemRef4 = ds.getRef();
                    Boolean author_b = author.equalsIgnoreCase(review.getAuthorReview());
                    Boolean content_b = otzv_ves.equalsIgnoreCase(review.getReview());
                   /* Log.w(TAG, "точка 4" + author_b );
                    Log.w(TAG, "точка 5" + content_b);
                    Log.w(TAG, "точка 6" + author );
                    Log.w(TAG, "точка 7" + review.getAuthorReview());
                    Log.w(TAG, "точка 8" + otzv_ves );
                    Log.w(TAG, "точка 9" + review.getReview().toString());*/
                    if(author_b && content_b){
                        itemRef4.child("scan").setValue(true);
                        return;
                    };
                }
                adapterAr3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        reviews.addValueEventListener(vlistener_user1);
        //finishActivity(UserActivity_Admin);
    }

    public void getIntentMain_Review(){

        Intent i2 = getIntent();
        if(i2 != null){
            text_author.setText(i2.getStringExtra(REVIEW_AUTHOR));
            text_time.setText(i2.getStringExtra(REVIEW_TIME));
            text_tema.setText(i2.getStringExtra(REVIEW_TEMA));
            text_review.setText(i2.getStringExtra(REVIEW_CONTENT));
        }
        author = text_author.getText().toString();
        otzv_ves = text_review.getText().toString();
    }
    public void stopActivity_Review(View view){
        Intent intentTren = new Intent(this, HomeActivity_Admin.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren);
    }

    public void predActivity_Review(View view){
        AlertDialog.Builder dialog_rev = new AlertDialog.Builder(this);
        dialog_rev.setTitle("Дать предупреждение пользователю" + " ?");
        // Создание объекта для нужного шаблона, помещаев его в View
        LayoutInflater inflater = LayoutInflater.from(this);
        View review_window = inflater.inflate(R.layout.review_window_admin, null);
        dialog_rev.setView(review_window);
        Intent intentTren43 = new Intent(this, HomeActivity_Admin.class);

        dialog_rev.setNegativeButton("Вернуться назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog_rev.setPositiveButton("Дать предупреждение", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                noticePlusUser();

                intentTren43.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentTren43);
            }
        });
        dialog_rev.show();
    }

    public void noticePlusUser(){/*
        ValueEventListener vlistener_user34 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData432.size() > 0 )listData432.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    Review review = ds.getValue(Review.class);
                    DatabaseReference itemRef4 = ds.getRef();
                    Boolean author_b = author.equalsIgnoreCase(user.getEmail());
                    //Boolean content_b = otzv_ves.equalsIgnoreCase(review.getReview());
                   /* Log.w(TAG, "точка 4" + author_b );
                    Log.w(TAG, "точка 5" + content_b);
                    Log.w(TAG, "точка 6" + author );
                    Log.w(TAG, "точка 7" + review.getAuthorReview());
                    Log.w(TAG, "точка 8" + otzv_ves );
                    Log.w(TAG, "точка 9" + review.getReview().toString());*/
                    /*if(author_b){
                        //plus_one = user.get;
                        itemRef4.child("scan").setValue(true);
                        return;
                    };
                }
                adapterAr3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        reviews.addValueEventListener(vlistener_user34);*/
    }

}