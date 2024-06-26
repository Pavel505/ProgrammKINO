package com.example.progkino3;

import static android.content.ContentValues.TAG;

import static com.example.progkino3.Constant.Color_BASA_Intel;
import static com.example.progkino3.Constant.GEO_CAPITAL;
import static com.example.progkino3.Constant.IMG_BASA;
import static com.example.progkino3.InformationUser.counter_minus;
import static com.example.progkino3.InformationUser.counter_plus;
import static com.example.progkino3.InformationUser.namerealuser;

import android.content.Intent;
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

import com.example.progkino3.Models.Eventum;
import com.example.progkino3.Models.Message;
import com.example.progkino3.Models.QuestionTrenTutor;
import com.example.progkino3.Models.Rating;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TreningActivity extends AppCompatActivity {
    private ListView listView;
    String answer;
    private ArrayAdapter<String> adapterAr_capital;
    private List<String> listData_capital;
    public int id_q, counter_question, ost_time,no_answer ;
    public float counter;
    TextView text_answers,text_answers_all,text_time,text_zagolovok ;
    DatabaseReference  treningGeo1,  ratings;
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
        text_zagolovok = findViewById(R.id.text_question_zagolovok1);
        text_time = findViewById(R.id.text_time);
        editTextAnswer = findViewById(R.id.answer_capital);
        listView = findViewById(R.id.listCountry);
        text_answers = findViewById(R.id.text_answers);
        text_answers_all = findViewById(R.id.text_answers_all);
        text_zagolovok.setText("Готовы проверить себя в географии? Назовите столицу страны:");
        text_answers.setText("0");
        text_answers_all.setText("0");
        text_time.setText("10");
        listData_capital = new ArrayList<>();
        adapterAr_capital = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData_capital);
        listView.setAdapter(adapterAr_capital);
        db = FirebaseDatabase.getInstance();
        treningGeo1 = db.getReference("TreningGeo1");
        ratings = db.getReference("Rating");
        counter = 0;counter_question = 0;id_q=1;no_answer=0;
    }
    private void getDataFromDB(){
        
        ost_time = 15;
        ValueEventListener vlistener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData_capital.size() > 0 )listData_capital.clear();
                answer = "";
                id_q = (int) (Math.random() * (23)) + 1;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    QuestionTrenTutor treningGeo1 = ds.getValue(QuestionTrenTutor.class);
                    if(id_q == treningGeo1.getId()){
                       // text_question_country.setText(txt);
                        String txt = treningGeo1.getCountry().toString();
                        listData_capital.add(txt);
                        answer = treningGeo1.getCapital().toString();
                    }
                }
                adapterAr_capital.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        Log.w(TAG, "точка 8");
        treningGeo1.addValueEventListener(vlistener);

        tableTime(true);
        //Log.i("Info", "Value_3: " + ost_time);

    }
    Timer timer4 = new Timer();
    private void tableTime(Boolean vkl_time) {
        if(!vkl_time)
        {
            timer4.cancel();
            return;
        }else{
            timer4 = new Timer();
        }
        timer4.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                //final float value = Utils.randInt(-10, 35);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Info", "Value: " + ost_time);
                        ost_time-=1;
                        Log.i("Info", "Value2: " + ost_time);
                        if(ost_time < 0)
                        {
                            timer4.cancel();
                            text_time.setText("Время вышло");
                        }
                        else {
                            text_time.setText(String.valueOf(ost_time));
                        };
                    }
                });
            }
        }, 0, 1000);
    }



    public void onAnswerOk (View view){
        tableTime(false);
        counter_question  += 1;
        String answer_znatok = editTextAnswer.getText().toString();
        if (answer.equalsIgnoreCase(answer_znatok)){
            if (ost_time>0) {
                counter += 1;
                Toast toast = Toast.makeText(getApplicationContext(), "Верно!",
                        Toast.LENGTH_SHORT);
                toast.show();
                Log.w(TAG, "Правильный ответ " + counter + "/" + counter_question);
            }else{
                counter += 0.5;
                Toast toast2 = Toast.makeText(getApplicationContext(), "Верно, но время вышло!",
                        Toast.LENGTH_SHORT);
                toast2.show();
            }
        } else {
            no_answer+=1;
            Toast toastProverka = Toast.makeText(getApplicationContext(), " Правильный ответ: " + answer,
                    Toast.LENGTH_SHORT);
            toastProverka.show();
        }
        editTextAnswer.setText("");
        Log.w(TAG, "Другой ответ " + id_q + " " + answer + " " + answer_znatok);

        String counterS = Float.toString(counter);
        String no_answerS = Integer.toString(no_answer);
        text_answers.setText(counterS);
        text_answers_all.setText(no_answerS);

        getDataFromDB();
    }

    public void stopActivity_TrenGeo(View view){

        counter_plus = text_answers.getText().toString();
        counter_minus = text_answers_all.getText().toString();
        String tema_testi = "География: столицы";

        db.getReference().child("Rating").push().setValue(
                new Rating(/*FirebaseAuth.getInstance().getCurrentUser().getEmail(),*/
                        counter_plus, counter_minus, tema_testi, FirebaseAuth.getInstance().getCurrentUser().getEmail().toString()
                )
        );
        Toast toast244 = Toast.makeText(getApplicationContext(), "Данные о тренировке сохранены!",
                Toast.LENGTH_SHORT);
        toast244.show();

        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren);
    }

}