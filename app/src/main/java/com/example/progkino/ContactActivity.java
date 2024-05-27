package com.example.progkino;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.progkino.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btnCreateReview = (Button) findViewById(R.id.otzv);
        btnCreateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createReview();
            }
        });

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
        dialog_rev.setMessage("Это могут быть замечания по интерфейсу, работоспособности приложения и т.п.");
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
       /* dialog_rev.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Проверка на наличие данных ячеек

                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите ваш логин",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().length() < 5){
                    Snackbar.make(root, "Пароль должен иметь больше 5 символов!",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                //Log.w(TAG, "Почта здесь юзера1" + email.getText().toString());
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override // При успешной авторизации
                            public void onSuccess(AuthResult authResult) {
                                // ДЛЯ ОПРЕДЕЛНИЕ РОЛЕЙ
                                // Связывание с БД, поиск нужного человека и сравнение роли
                                ValueEventListener vlistener_userRole = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(!proverka) {return;}else{proverka = false;};
                                        if(listData.size() > 0 )listData.clear();
                                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                                            User user = ds.getValue(User.class);
                                            if(email.getText().toString().equalsIgnoreCase(user.getEmail().toString())){
                                                role_user = user.getRole().toString();

                                                listData.add(role_user);
                                                if(role_user.equalsIgnoreCase("admin")){
                                                    startActivity(new Intent(MainActivity.this, UserActivity_Admin.class));
                                                    finish();
                                                }else {
                                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                    finish();
                                                }
                                                return;
                                            }
                                        }
                                        adapterAr1.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {}
                                };
                                users.addValueEventListener(vlistener_userRole);
                            }
                            // При ошибке
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });*/

        dialog_rev.show();
    }

}