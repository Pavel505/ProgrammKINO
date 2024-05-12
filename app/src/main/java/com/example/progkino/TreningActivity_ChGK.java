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
import com.example.progkino.Models.QuestionTrenTutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TreningActivity_ChGK extends AppCompatActivity {
    Boolean refren_action_send_answer;
    TextView text_counter_vern_answers,text_counter_nevern_answers,text_time_chgk,text_question_chgk,
            text_comment_chgk,text_sources_chgk,text_author_chgk;
    EditText editTextAnswer;
    FirebaseDatabase db;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    DatabaseReference treningChGK;
    public int id_q, counter_question, ost_time,no_answer ;
    public float counter;
    String answer,author_chgk,sources_chgk,comment_chgk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_chgk);
        init();
        getDataFromDB_ChGK();
    }



    private void init(){
        text_time_chgk = findViewById(R.id.text_time_ost);
        editTextAnswer = findViewById(R.id.answer_chgk);
        // Попробовать сделать без костыля с TextView
        //listView = findViewById(R.id.listCountry);
        text_question_chgk = findViewById(R.id.text_question_chgk);
        text_comment_chgk = findViewById(R.id.textView_comment);
        text_sources_chgk = findViewById(R.id.textView_ist);
        text_author_chgk = findViewById(R.id.textView_author);
        text_counter_vern_answers = findViewById(R.id.text_answers_vern);
        text_counter_nevern_answers = findViewById(R.id.text_answers_nevern);
        text_counter_vern_answers.setText("0");
        text_counter_nevern_answers.setText("0");
        text_question_chgk.setText(" ");
        text_author_chgk.setText(" ");
        text_sources_chgk.setText(" ");
        text_comment_chgk.setText(" ");
        text_time_chgk.setText("70");
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        // listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningChGK = db.getReference("TreningChGK");
        counter = 0;counter_question = 0;id_q=1;no_answer=0;
    }
    private void getDataFromDB_ChGK(){
        refren_action_send_answer = false;
        text_author_chgk.setText(" ");
        text_sources_chgk.setText(" ");
        text_comment_chgk.setText(" ");
        ost_time = 70;
        ValueEventListener vlistener = new ValueEventListener() {
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
                        text_question_chgk.setText(txt);
                        answer = question_chgk.getAnswer().toString();
                    }
                }
                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        Log.w(TAG, "точка 8");
        treningChGK.addValueEventListener(vlistener);

        tableTime(true);

    }

    Timer timer_chgk = new Timer();
    private void tableTime(Boolean vkl_time) {
        if(!vkl_time)
        {
            timer_chgk.cancel();
            return;
        }else{
            timer_chgk = new Timer();
        }
        timer_chgk.scheduleAtFixedRate(new TimerTask() {

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
                            timer_chgk.cancel();
                            text_time_chgk.setText("Время вышло");
                        }
                        else {
                            text_time_chgk.setText(String.valueOf(ost_time));
                        };
                    }
                });
            }
        }, 0, 1000);
    }



    public void onAnswerOk_ChGK (View view){
        if (refren_action_send_answer){
            Toast toast_answer = Toast.makeText(getApplicationContext(), "Вы уже ответили на этот вопрос!",
                    Toast.LENGTH_SHORT);
            toast_answer.show();
            return;
        };
        tableTime(false);
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
        text_counter_vern_answers.setText(counterS);
        text_counter_nevern_answers.setText(no_answerS);

        text_comment_chgk.setText(comment_chgk);
        text_sources_chgk.setText(sources_chgk);
        text_author_chgk.setText(author_chgk);

        refren_action_send_answer = true;
    }
    public void nextQuestion(View view){
        getDataFromDB_ChGK();
    }
    public void navigatorTren(View view){
        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTren);
    }

    public void stopActivityChGK(View view){
        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren);
    }


}