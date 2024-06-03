package com.example.progkino;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progkino.Models.Eventum;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventumPage extends AppCompatActivity {
    Boolean relike = true;
    Boolean nado = true;
    Boolean srabatavanie = true;
    DatabaseReference eventumes;
    private List<String> listData54;
    private ArrayAdapter<String> adapterAr435;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventum_page);

        ConstraintLayout eventumBg = findViewById(R.id.eventumPageBack);
        ImageView eventumImage = findViewById(R.id.eventumPageImage);
        TextView eventumTitle = findViewById(R.id.eventumPageTitle);
        TextView eventumDate = findViewById(R.id.eventumPageDate);
        TextView eventumType = findViewById(R.id.eventumPageType);
        TextView eventumDescription = findViewById(R.id.eventumPageText);

        eventumBg.setBackgroundColor(getIntent().getIntExtra("eventumBg",0));
        eventumImage.setImageResource(getIntent().getIntExtra("eventumImage",0));
        eventumTitle.setText(getIntent().getStringExtra("eventumTitle"));
        eventumDate.setText(getIntent().getStringExtra("eventumDate"));
        eventumType.setText(getIntent().getStringExtra("eventumType"));
        eventumDescription.setText(getIntent().getStringExtra("eventumDescription"));
        Button btnAddEvent = (Button) findViewById(R.id.btn_add_event);
        db = FirebaseDatabase.getInstance();
        listData54 = new ArrayList<>();
        adapterAr435 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData54);
        eventumes = db.getReference("Eventum");
        String titleEvent = eventumTitle.getText().toString();
        btnAddEvent.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeOneEvent(titleEvent);
            }
        });

    }

    public void likeOneEvent(String title){
        ValueEventListener vlistener_event_Like1 = new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                srabatavanie = false;
                if(listData54.size() > 0 )listData54.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    DatabaseReference itemRef3 = ds.getRef();
                    Eventum eventum = ds.getValue(Eventum.class);
                    Boolean uslovie1 = title.equalsIgnoreCase(eventum.getTitle());
                    Boolean uslovie2 = uslovie1 && relike;
                    //Boolean uslovie3 = true;
                    Log.w(TAG, "точка 1" + title);
                    Log.w(TAG, "точка 2" + eventum.getTitle());
                    Log.w(TAG, "точка 3" + relike);
                    Log.w(TAG, "точка 4" + nado);
                    if(!relike){
                        if(nado){
                            if(!srabatavanie) {
                                Log.w(TAG, "точка нажали");
                                /*Toast toast77 = Toast.makeText(getApplicationContext(), "Вы уже нажали!!",
                                        Toast.LENGTH_SHORT);
                                toast77.show();*/
                                srabatavanie = true;
                                return;
                            }
                        } else{
                            Log.w(TAG, "точка надо");
                            nado = true;
                            return;
                        }
                    }
                    //Boolean uslovie3 = title.equalsIgnoreCase(eventum.getTitle());
                    if (uslovie2) {
                        nado = false;
                        Log.w(TAG, "точка база");
                        itemRef3.child("like").setValue(eventum.getLike() + 1);
                        relike = false;
                        Toast toast78 = Toast.makeText(getApplicationContext(), "Лайк учтён!!",
                                Toast.LENGTH_SHORT);
                        toast78.show();

                        break;

                    }
                    Log.w(TAG, "точка К");
                    String txt = title;
                    listData54.add(txt);
                }
                adapterAr435.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        eventumes.addValueEventListener(vlistener_event_Like1);
    }

}