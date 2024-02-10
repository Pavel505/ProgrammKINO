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
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TreningActivity extends AppCompatActivity {
    private ListView listView;
    String answer;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    public int id_q, counter,counter_question, ost_time ;
    TextView text_question_country,text_time;
    DatabaseReference  treningGeo1;
    EditText editTextAnswer;
    FirebaseDatabase db;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening);
        init();
        getDataFromDB();
    }

    private void init(){
        text_time = findViewById(R.id.text_time);
        editTextAnswer = findViewById(R.id.answer_capital);
        listView = findViewById(R.id.listCountry);
        text_question_country = findViewById(R.id.text_question_country);
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningGeo1 = db.getReference("TreningGeo1");
        counter = 0;counter_question = 0;id_q=1;
    }
    private void getDataFromDB(){
        ost_time = 5;
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                answer = "";
                id_q = (int) (Math.random() * (15)) + 1;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    QuestionTrenTutor treningGeo1 = ds.getValue(QuestionTrenTutor.class);
                    if(id_q == treningGeo1.getId()){
                       // text_question_country.setText(txt);
                        String txt = treningGeo1.getCountry().toString();
                        listData.add(txt);
                        answer = treningGeo1.getCapital().toString();
                    }
                }
                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        treningGeo1.addValueEventListener(vlistener);
        //tableTime();
    }

    /*public void tableTime(){

        TimerTask task = new TimerTask() {
            public void run() {
                Log.w(TAG, "Осталось времени: " + ost_time);
                ost_time -= 0.5;
                Log.w(TAG, "Осталось времени: "+ost_time);
                if (ost_time <= 0){
                   // timer.cancel();// &?
                    text_time.setText("Время вышло");
                }
                text_time.setText(ost_time);
            }
        };
        Timer timer = new Timer("Timer");
        long delay = 10000L;
        long period = 1000L;
        timer.scheduleAtFixedRate(task, delay, period);
    }*/
    public void onAnswerOk (View view){
        counter_question  += 1;
        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            counter += 1;
            Log.w(TAG, "Правильный ответ " + counter + "/" + counter_question );
        }
        if (ost_time<0){
            counter -= 0.5;
        }
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);
        getDataFromDB();
        text_question_country.setText(counter + "/" + counter_question);
    }
}