package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TreningActivity_ChGK extends AppCompatActivity {

    TextView text_counter_vern_answers,text_counter_nevern_answers,text_time;
    EditText editTextAnswer;
    FirebaseDatabase db;
    private ArrayAdapter<String> adapterAr;
    private List<String> listData;
    DatabaseReference treningChGK;
    public int id_q, counter_question, ost_time,no_answer ;
    public float counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trening_chgk);
        init();
        getDataFromDB_ChGK();
    }



    private void init(){
        text_time = findViewById(R.id.text_time_ost);
        editTextAnswer = findViewById(R.id.answer_chgk);
        // Попробовать сделать без костыля с TextView
        //listView = findViewById(R.id.listCountry);
        text_counter_vern_answers = findViewById(R.id.text_answers_vern);
        text_counter_nevern_answers = findViewById(R.id.text_answers_nevern);
        text_counter_vern_answers.setText("0");
        text_counter_nevern_answers.setText("0");
        text_time.setText("70");
        listData = new ArrayList<>();
        adapterAr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        // listView.setAdapter(adapterAr);
        db = FirebaseDatabase.getInstance();
        treningChGK = db.getReference("TreningChGK");
        counter = 0;counter_question = 0;id_q=1;no_answer=0;
    }
    private void getDataFromDB_ChGK(){

    }
    private void stopActivityChGK(){
        //Проверить работоспособность
        super.finish();
    }
}