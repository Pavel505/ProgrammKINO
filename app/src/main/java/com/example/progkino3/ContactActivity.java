package com.example.progkino3;

import static android.content.ContentValues.TAG;
import static com.example.progkino3.InformationUser.no_const_role;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.progkino3.Models.Review;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactActivity extends AppCompatActivity {
   // ConstraintLayout root;
    RelativeLayout root2;
    DatabaseReference reviews;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(no_const_role.equalsIgnoreCase("admin")){
            setContentView(R.layout.activity_contact_admin);
        }else {
            setContentView(R.layout.activity_contact);
            Button btnCreateReview = (Button) findViewById(R.id.otzv);
            btnCreateReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createReview();
                }
            });
        }
        db = FirebaseDatabase.getInstance();
        reviews = db.getReference("Review");
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
    public void navigatorChat(View view){
        Intent intentChat = new Intent(this, ChatActivity.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }
    public void createReview() {
        AlertDialog.Builder dialog_rev = new AlertDialog.Builder(this);
        dialog_rev.setTitle("Что вы хотели написать?");
        dialog_rev.setMessage("Это могут быть замечания по интерфейсу, работоспособности приложения и т.п. Обращаем внимание, что отзыв должен быть не менее 5 симоволов");
        // Создание объекта для нужного шаблона, помещаев его в View
        LayoutInflater inflater = LayoutInflater.from(this);
        View review_window = inflater.inflate(R.layout.review_window, null);
        dialog_rev.setView(review_window);

        EditText temaReview = review_window.findViewById(R.id.tema_reviewField);
        EditText contextReview = review_window.findViewById(R.id.reviewField);

        dialog_rev.setNegativeButton("Вернуться назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog_rev.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Проверка на наличие данных ячеек

                if(TextUtils.isEmpty(temaReview.getText().toString())){
                    Toast toast_answer2 = Toast.makeText(getApplicationContext(), "Заполните поле темы сообщения",
                            Toast.LENGTH_LONG);
                    toast_answer2.show();
                    return;
                }
                if(contextReview.getText().toString().length() < 5){
                    Toast toast_answer = Toast.makeText(getApplicationContext(), "Отзыв должен иметь меньше 5 символов!",
                            Toast.LENGTH_LONG);
                    toast_answer.show();
                    return;
                }
                String temRev = temaReview.getText().toString();
                String rev = contextReview.getText().toString();
                String author = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                newReview(temRev, rev,author);

            }
        });

        dialog_rev.show();
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void newReview(String temaReview, String reviewtxt, String email){
        Review review = new Review();
        review.setTemaReview(temaReview);
        review.setAuthorReview(email);
        review.setReview(reviewtxt);
        Date data_now = new Date();
        review.setScan(false);
        String strDate = formatter.format(data_now);
        review.setTimeReview(strDate);

        db.getReference().child("Review").push().setValue(
                new Review(reviewtxt,temaReview,email,strDate,false
                )
        );
        Toast toast_answer3 = Toast.makeText(getApplicationContext(), "Отзыв отправлен",
                Toast.LENGTH_LONG);
        toast_answer3.show();
    }

    public void inAdminStr (View view){
        startActivity(new Intent(this, UserActivity_Admin.class));
        finish();
    }
}