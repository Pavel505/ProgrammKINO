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
    Boolean refren_action_send_answer, ignor_answer, uslovie,replay_tema,question_one;
    TextView text_counter_svoyak,text_counter_nevern_answers,text_time_svoyak,text_question_svoyak,
            text_comment_svoyak, text_author_svoyak,textView_nominal,textView_tema;
    EditText editTextAnswer;
    FirebaseDatabase db;
    int nominal_question, real_nominal_question;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData, list_tems;
    DatabaseReference treningSvoyak;
    public int id_q, counter_question, ost_time,no_answer ;
    public float counter;
    String answer,author_chgk,sources_chgk,comment_chgk, tema_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_svoyak);
        init();
        getDataFromDB_Svoyak();
    }
    public void init(){
        replay_tema = false;tema_question = " ";question_one = true;
        ignor_answer = true;real_nominal_question=100;
        textView_tema = findViewById(R.id.textView_tema);
        textView_nominal = findViewById(R.id.textView_nominal);
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
        list_tems = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        // listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningSvoyak = db.getReference("TreningSvoyak");
        counter = 0;counter_question = 0;id_q=1;no_answer=0; ost_time=30;



    }

    public void getDataFromDB_Svoyak(){
        refren_action_send_answer = false;

        text_question_svoyak.setText(" ");
        text_author_svoyak.setText(" ");
        text_comment_svoyak.setText(" ");

        text_author_svoyak.setText(" ");
        text_comment_svoyak.setText(" ");
        ost_time = 20;
        ValueEventListener vlistener_svoyak = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                answer = "";
                id_q = (int) (Math.random() * (20)) % 5 + 1;
               // Boolean kon = !replay_tema && real_nominal_question==100;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Question question_chgk = ds.getValue(Question.class);
                    replay_tema = false;
                    if(real_nominal_question == 100){
                        // Доделать выход при отыгрывании всех тем

                        Log.w(TAG, "точка 12" + replay_tema);
                        for(int i = 0; i < list_tems.size(); i++) {
                            if(list_tems.get(i).equalsIgnoreCase(question_chgk.getTema())){
                                replay_tema = true;
                                Log.w(TAG, "точка 14" + list_tems.get(i)+ " " + list_tems.size());
                               // return;
                            }
                        }
                        Log.w(TAG, "точка 16" + replay_tema + real_nominal_question + tema_question + list_tems.size());
                        boolean df = tema_question.equalsIgnoreCase(question_chgk.getTema());
                        uslovie = question_chgk.getNominal() == 100 && !replay_tema;

                    }else{
                         uslovie = tema_question.equalsIgnoreCase(question_chgk.getTema()) && real_nominal_question == question_chgk.getNominal();
                    }

                    Log.w(TAG, "точка 18" + uslovie + replay_tema + real_nominal_question + tema_question + list_tems.size());
                    if(uslovie){
                        String txt = question_chgk.getContent();
                        tema_question = question_chgk.getTema();
                        nominal_question = question_chgk.getNominal();
                        author_chgk = question_chgk.getAuthor();
                        comment_chgk = "Ответ: "+ question_chgk.getAnswer() + "\n " + question_chgk.getComment();
                        sources_chgk = question_chgk.getSources();
                        listData.add(txt);
                        text_question_svoyak.setText(txt);
                        textView_nominal.setText(Integer.toString(nominal_question));
                        textView_tema.setText(tema_question);
                        answer = question_chgk.getAnswer().toString();
                        return;
                    }

                }
                if (text_question_svoyak.getText().equals(" ")){
                    Toast toast_answer = Toast.makeText(getApplicationContext(), "Темы закончились!Рекомендуем нажать *Завершить*",
                            Toast.LENGTH_LONG);
                    toast_answer.show();
                    return;
                }


                adapterAr.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
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
                        ost_time-=1;
                        if(ost_time < 1)
                        {
                            timer_svoyak.cancel();
                            text_time_svoyak.setText("Время вышло");
                        }
                        else {

                            text_time_svoyak.setText(String.valueOf(ost_time));
                        };
                    }
                });
            }
        }, 0, 1000);
    }

    public void onAnswerOk_Svoyak (View view){
        counter_question  += 1;
        if (refren_action_send_answer){
            Toast toast_answer = Toast.makeText(getApplicationContext(), "Вы уже ответили на этот вопрос!",
                    Toast.LENGTH_SHORT);
            toast_answer.show();
            return;
        };
        if(real_nominal_question == 500){
            list_tems.add(tema_question);
            real_nominal_question = 100;
            Toast toast3 = Toast.makeText(getApplicationContext(), "Отыграно тем:" + counter_question/5 + " !",
                    Toast.LENGTH_SHORT);
            toast3.show();
        }else{
            real_nominal_question+=100;
        }
        Log.w(TAG, "точка 28" + real_nominal_question);
        tableTime_Svoyak(false);

        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            if (ost_time>0) {
                counter += nominal_question;
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
            counter -= nominal_question;
        }
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);

        String counterS = Float.toString(counter);
        String no_answerS = Integer.toString(no_answer);

        text_counter_svoyak.setText(counterS);
        //text_counter_nevern_answers.setText(no_answerS);

        text_comment_svoyak.setText(comment_chgk);
        text_author_svoyak.setText(author_chgk);

        refren_action_send_answer = true;
        ignor_answer = false;
    }
    public void nextQuestion_Svoyak(View view){
        if(ignor_answer){
            Toast toast37 = Toast.makeText(getApplicationContext(), "Вы не ввели свой и не узнали правильный ответ!",
                    Toast.LENGTH_SHORT);
            toast37.show();
        }else {
            getDataFromDB_Svoyak();
            ignor_answer = true;
        }
    }

    public void stopActivitySvoyak(View view){
        Intent intentTren_Sv = new Intent(this, TrenActivity.class);
        intentTren_Sv.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren_Sv);
    }

}