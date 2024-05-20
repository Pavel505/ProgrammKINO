package com.example.progkino;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progkino.Models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TreningActivity_Svoyak extends AppCompatActivity {
    Boolean refren_action_send_answer;
    TextView text_counter_svoyak,text_counter_nevern_answers,text_time_svoyak,text_question_svoyak,
            text_comment_svoyak, text_author_svoyak;
    EditText editTextAnswer;
    FirebaseDatabase db;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    DatabaseReference treningSvoyak;
    public int id_q, counter_question, ost_time,no_answer ;
    public float counter;
    String answer,author_chgk,sources_chgk,comment_chgk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_svoyak);
        init();
        getDataFromDB_Svoyak();
    }
    public void init(){
        text_time_svoyak = findViewById(R.id.texttime_ost);
        editTextAnswer = findViewById(R.id.answer_chgk2);
        text_question_svoyak = findViewById(R.id.textView_question_svoyak);
        text_comment_svoyak = findViewById(R.id.textView_comment_svoyak);
        text_author_svoyak = findViewById(R.id.textView_author2);
        text_counter_svoyak = findViewById(R.id.textView_counter);
        text_question_svoyak.setText(" ");
        text_author_svoyak.setText(" ");
        text_comment_svoyak.setText(" ");
        text_counter_svoyak.setText("0");
        text_time_svoyak.setText("15");
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        // listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningSvoyak = db.getReference("TreningChGK");
        counter = 0;counter_question = 0;id_q=1;no_answer=0; ost_time=30;



    }

    public void getDataFromDB_Svoyak(){
        refren_action_send_answer = false;
        text_author_svoyak.setText(" ");
        text_comment_svoyak.setText(" ");
        ost_time = 20;
        ValueEventListener vlistener_svoyak = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                answer = "";
                id_q = (int) (Math.random() * (5)) + 1;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Question question_chgk = ds.getValue(Question.class);
                    // QuestionTrenTutor treningGeo1 = ds.getValue(QuestionTrenTutor.class);
                    if(id_q == question_chgk.getId()){
                        // text_question_country.setText(txt);
                        String txt = question_chgk.getContent();
                        author_chgk = question_chgk.getAuthor();
                        comment_chgk = "Ответ: "+ question_chgk.getAnswer() + "\n " + question_chgk.getComment();
                        sources_chgk = question_chgk.getSources();
                        listData.add(txt);
                        text_question_svoyak.setText(txt);
                        answer = question_chgk.getAnswer().toString();
                    }
                }
                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        Log.w(TAG, "точка 8");
        treningSvoyak.addValueEventListener(vlistener_svoyak);

        tableTime_Svoyak(true);
    }
    Timer timer_svoyak = new Timer();
    private void tableTime_Svoyak(Boolean vkl_time) {
        if(!vkl_time)
        {
            timer_svoyak.cancel();
            return;
        }else{
            timer_svoyak = new Timer();
        }
        timer_svoyak.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Info", "Value: " + ost_time);
                        ost_time-=1;
                        Log.i("Info", "Value2: " + ost_time);
                        if(ost_time < 0)
                        {
                            timer_svoyak.cancel();
                            text_time_svoyak.setText("Время вышло");
                        }
                        else {
                            // Почему-то возникает ошибка, перевпроверить!
                            // + переход на след.стр.
                            text_time_svoyak.setText(String.valueOf(ost_time));
                        };
                    }
                });
            }
        }, 0, 1000);
    }

    public void onAnswerOk_Svoyak (View view){
        if (refren_action_send_answer){
            Toast toast_answer = Toast.makeText(getApplicationContext(), "Вы уже ответили на этот вопрос!",
                    Toast.LENGTH_SHORT);
            toast_answer.show();
            return;
        };
        tableTime_Svoyak(false);
        counter_question  += 1;
        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            if (ost_time>0) {
                counter += 1;
                Toast toast3 = Toast.makeText(getApplicationContext(), "Верно!",
                        Toast.LENGTH_SHORT);
                toast3.show();
                Log.w(TAG, "Правильный ответ " + counter + "/" + counter_question);
            }else{
                Toast toast4 = Toast.makeText(getApplicationContext(), "Верно, но время вышло!",
                        Toast.LENGTH_SHORT);
                toast4.show();
            }
        } else {
            no_answer+=1;
        }
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);

        String counterS = Float.toString(counter);
        String no_answerS = Integer.toString(no_answer);

        text_counter_svoyak.setText(counterS);
        //text_counter_nevern_answers.setText(no_answerS);

        text_comment_svoyak.setText(comment_chgk);
        text_author_svoyak.setText(author_chgk);

        refren_action_send_answer = true;
    }
    public void nextQuestion_Svoyak(View view){
        getDataFromDB_Svoyak();
    }

    public void stopActivitySvoyak(View view){
        Intent intentTren_Sv = new Intent(this, TrenActivity.class);
        intentTren_Sv.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren_Sv);
    }

}