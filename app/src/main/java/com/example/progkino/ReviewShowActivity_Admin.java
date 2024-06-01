package com.example.progkino;

import static com.example.progkino.Constant.GEO_CAPITAL;
import static com.example.progkino.Constant.GEO_COUNTRY;
import static com.example.progkino.Constant.GEO_IMAGE;
import static com.example.progkino.Constant.REVIEW_AUTHOR;
import static com.example.progkino.Constant.REVIEW_CONTENT;
import static com.example.progkino.Constant.REVIEW_TEMA;
import static com.example.progkino.Constant.REVIEW_TIME;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class ReviewShowActivity_Admin extends AppCompatActivity {
    private TextView text_author, text_time,text_tema,text_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_show_admin);
        init();
        getIntentMain_Review();

    }

    private void init(){
        text_author = findViewById(R.id.textView31_user);
        text_time = findViewById(R.id.textView31_time);
        text_tema = findViewById(R.id.textView31_tema);
        text_review = findViewById(R.id.textView31_otzv);
    }

    public void getIntentMain_Review(){
        Intent i2 = getIntent();
        if(i2 != null){

            text_author.setText(i2.getStringExtra(REVIEW_AUTHOR));
            text_time.setText(i2.getStringExtra(REVIEW_TIME));
            text_tema.setText(i2.getStringExtra(REVIEW_TEMA));
            text_review.setText(i2.getStringExtra(REVIEW_CONTENT));
        }
    }
    public void stopActivity_Review(View view){
        Intent intentTren = new Intent(this, HomeActivity_Admin.class);
        intentTren.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentTren);
    }

    public void predActivity_Review(View view){
        AlertDialog.Builder dialog_rev = new AlertDialog.Builder(this);
        dialog_rev.setTitle("Дать предупреждение пользователю" + " ?");
        // Создание объекта для нужного шаблона, помещаев его в View
        LayoutInflater inflater = LayoutInflater.from(this);
        View review_window = inflater.inflate(R.layout.review_window_admin, null);
        dialog_rev.setView(review_window);
        Intent intentTren43 = new Intent(this, HomeActivity_Admin.class);

        dialog_rev.setNegativeButton("Вернуться назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog_rev.setPositiveButton("Дать предупреждение", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {


                intentTren43.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentTren43);
            }
        });
        dialog_rev.show();
    }
}