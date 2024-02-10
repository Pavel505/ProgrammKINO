package com.example.progkino;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreningActivity extends AppCompatActivity {
    private ListView listView;
    String answer;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    public int id_q, counter,counter_question ;
    TextView text_question_country;
    DatabaseReference  treningGeo1;
    EditText editTextAnswer;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening);
        init();
        getDataFromDB();
    }

    private void init(){
        editTextAnswer = findViewById(R.id.answer_capital);
        listView = findViewById(R.id.listCountry);
        text_question_country = findViewById(R.id.text_question_country);
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningGeo1 = db.getReference("TreningGeo1");
        counter = 0;counter_question = 0;
    }
    private void getDataFromDB(){
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                Random r = new Random();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    id_q = r.nextInt(4) + 1;


                    QuestionTrenTutor treningGeo1 = ds.getValue(QuestionTrenTutor.class);
                    String txt = treningGeo1.getCountry().toString();
                    if(id_q == treningGeo1.getId()){
                       // text_question_country.setText(txt);
                        listData.add(txt);
                        answer = treningGeo1.getCapital().toString();
                    }
                   // text_question_country = txt;
                    //listData.add(ds.getValue().toString());
                }
                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        treningGeo1.addValueEventListener(vlistener);
    }

    public void onAnswerOk (View view){
        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            counter += 1;
            Log.w(TAG, "Правильный ответ " + counter + "/" + counter_question );
        }
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);
        getDataFromDB();
        counter_question  += 1;
        text_question_country.setText(counter + "/" + counter_question);
    }
}