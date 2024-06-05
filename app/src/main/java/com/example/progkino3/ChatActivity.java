package com.example.progkino3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino3.Models.Message;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    DatabaseReference eventumes, categories, messages;
    FirebaseDatabase db;
    RecyclerView categoryRecycler,eventumRecycler;
    private FirebaseListAdapter<Message> adapter;
    private Button buttonChatSend;
    private ConstraintLayout activity_chat;
    private ListView list_quest_geo;
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    private List<Message> listTemp;
    DatabaseReference quest_geo;
    TextView mess_user, mess_time, mess_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activity_chat = findViewById(R.id.activity_chat);
        //init();
        init2();
        getDataFromDB();
        //displayAllMessages();
        buttonChatSend = findViewById(R.id.btnChatSend);
        buttonChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField = findViewById(R.id.editMessage);
                if(textField.getText().toString() == "")
                    return;
                db.getReference().child("Message").push().setValue(
                        new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                textField.getText().toString()
                        )
                );
                textField.setText("");
                //init();
                //displayAllMessages();
                init2();
                getDataFromDB();
            }
        });
    }
    private void init(){
        db = FirebaseDatabase.getInstance();
        messages = db.getReference("Message");
        eventumes = db.getReference("Eventum");
    }
    public void displayAllMessages(){
        Query query = FirebaseDatabase.getInstance().getReference().child("Message");

        ListView listOfMessages = findViewById(R.id.listMessage);
        //Query query = eventumes;
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()
                .setQuery(query, Message.class)
                .setLayout(R.layout.list_item_chat)
                .build();
        adapter = new FirebaseListAdapter<Message>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Message model, int position) {
                TextView mess_user, mess_time, mess_text;
                mess_time = v.findViewById(R.id.message_time);
                mess_text = v.findViewById(R.id.message_text);
                mess_user = v.findViewById(R.id.message_user);

                mess_time.setText(model.getmessageTime().toString());// Здесь может быть ошибка
                mess_text.setText(model.gettextMessage());
                mess_user.setText(model.getuserName());

            }
        };

        listOfMessages.setAdapter(adapter);
    }
    private void init2(){
        ListView listOfMessages = findViewById(R.id.listMessage);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listOfMessages.setAdapter(adapterAr1);
        db = FirebaseDatabase.getInstance();
        messages = db.getReference("Message");
        mess_time = findViewById(R.id.message_time);
        mess_text = findViewById(R.id.message_text);
        mess_user = findViewById(R.id.message_user);
    }
    private void getDataFromDB(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ValueEventListener vlistener_geo1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Message message = ds.getValue(Message.class);
                    String strDate = formatter.format(message.getmessageTime());
                    String txt = message.getuserName() +"     "+ strDate  + "\n" + message.gettextMessage();
                    listData.add(txt);
                    //listTemp.add(message);



                   // mess_time.setText("12345");// Здесь может быть ошибка
                   // mess_text.setText(message.gettextMessage().toString());
                    //mess_user.setText(message.getuserName().toString());
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        messages.addValueEventListener(vlistener_geo1);
    }
    public void navigatorMainScen(View view){
        Intent intentHome = new Intent(this, HomeActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentHome);
    }
    public void navigatorTutorial(View view){
        Intent intentTutorial = new Intent(this, TutorialActivity.class);
        intentTutorial.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTutorial);
    }
    public void navigatorTren(View view){
        Intent intentTren = new Intent(this, TrenActivity.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTren);
    }
    public void navigatorContact(View view){
        Intent intentContact = new Intent(this, ContactActivity.class);
        intentContact.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentContact);
    }
    public void navigatorChat(View view){
        Intent intentChat = new Intent(this, ChatActivity.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }


}