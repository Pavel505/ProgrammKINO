package com.example.progkino;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class TreningActivity extends AppCompatActivity {
    private ListView listView;
    String answer;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    public int id_q, counter,counter_question, ost_time,no_answer ;
    TextView text_answers,text_answers_all,text_time;
    DatabaseReference  treningGeo1;
    EditText editTextAnswer;
    FirebaseDatabase db;
    Timer timer2;
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
        text_answers = findViewById(R.id.text_answers);
        text_answers_all = findViewById(R.id.text_answers_all);
        text_answers.setText("0");
        text_answers_all.setText("0");
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningGeo1 = db.getReference("TreningGeo1");
        counter = 0;counter_question = 0;id_q=1;
    }
    private void getDataFromDB(){
        ost_time = 7;
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
        //tableTimer();

    }
    /*private void timerTick() {
        this.runOnUiThread(doTask);
    }

    private Runnable doTask = new Runnable() {
        public void run() {

            ost_time -= 1;
            Log.w(TAG, "Осталось времени: "+ ost_time);
            if (ost_time <= 0){
                text_time.setText("Время вышло");
            }
            text_time.setText(ost_time);
        }
    };*/
   /* public void tableTime(){

        TimerTask task = new TimerTask() {
            public void run() {
                Log.w(TAG, "Осталось времени: " + ost_time);
                ost_time -= 0.5;
                Log.w(TAG, "Осталось времени: "+ost_time);
                if (ost_time <= 0){
                    text_time.setText("Время вышло");
                }
                text_time.setText(ost_time);
            }
        };
        Timer timer2 = new Timer("Timer");
        long delay = 10000L;
        long period = 1000L;
        timer2.scheduleAtFixedRate(task, delay, period);
    }
    public  void  tableTimer(){
        Timer myTimer;
        myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                timerTick();
            }
        }, 0, 7000);
        myTimer.cancel();
    }*/

    public void onAnswerOk (View view){
        counter_question  += 1;
        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            counter += 1;
            Toast toast = Toast.makeText(getApplicationContext(), "Верно!",
                    Toast.LENGTH_SHORT);
            toast.show();
            Log.w(TAG, "Правильный ответ " + counter + "/" + counter_question );
        }
        /*if (ost_time<0){
            counter -= 0.5;
            Toast toast2 = Toast.makeText(getApplicationContext(), "Верно, но время вышло!",
                    Toast.LENGTH_SHORT);
            toast2.show();
        }*/
        no_answer = counter_question - counter;
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);
        getDataFromDB();
        String counterS = Integer.toString(counter);
        String no_answerS = Integer.toString(no_answer);
        text_answers.setText(counterS);
        text_answers_all.setText(no_answerS);
    }
}