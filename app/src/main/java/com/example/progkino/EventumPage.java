package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EventumPage extends AppCompatActivity {

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
    }
}