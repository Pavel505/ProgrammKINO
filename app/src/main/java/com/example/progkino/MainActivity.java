package com.example.progkino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.progkino.Models.Question;
import com.example.progkino.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users,eventumes,questions;

    RelativeLayout root;
    //public EditText name,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Анимация кнопок
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        root = findViewById(R.id.root_element);
        btnSignIn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                showSignInWindow();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        });
    }
    private void init(){
        // Запускаем авторизацию в БД
        auth = FirebaseAuth.getInstance();
        // Подключение к БД
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");
        eventumes = db.getReference("Eventum");
        questions = db.getReference("Question");
    }


    private void showSignInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Войти");
        dialog.setMessage("Введите свои данные");
        // Создание объекта для нужного шаблона, помещаев его в View
        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);

        EditText email = sign_in_window.findViewById(R.id.emailField);
        EditText password = sign_in_window.findViewById(R.id.passField);

        // Возвращает пользователя на начало
        dialog.setNegativeButton("Вернуться назад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        // Обработка данных
        dialog.setPositiveButton("Войти", new DialogInterface.OnClickListener() {
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
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override // При успешной авторизации
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                finish(); // завершает данную сцену + делает переход на новую
                            }
                            // При ошибке
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root, "Ошибка авторизации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        dialog.show();
    }
        // Функция вызова окна регистрации
        private void showRegisterWindow() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Зарегистрироваться");
            dialog.setMessage("Введите все данные для регистрации");
            // Создание объекта для нужного шаблона, помещаев его в View
            LayoutInflater inflater = LayoutInflater.from(this);
            View register_window = inflater.inflate(R.layout.register_window, null);
            dialog.setView(register_window);


            EditText name = register_window.findViewById(R.id.answer_capital);
            EditText lastname = register_window.findViewById(R.id.lastnameField);
            EditText login = register_window.findViewById(R.id.loginField);
            EditText password = register_window.findViewById(R.id.passField);
            EditText email = register_window.findViewById(R.id.emailField);
            EditText birthday = register_window.findViewById(R.id.dataField);
            EditText city = register_window.findViewById(R.id.cityField);
            EditText userdescription = register_window.findViewById(R.id.userdescriptionField);

            // Возвращает пользователя на начало
            dialog.setNegativeButton("Вернуться назад", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            });

            // Обработка данных
            dialog.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    //Проверка на наличие данных ячеек
                    if(TextUtils.isEmpty(name.getText().toString())){
                        Snackbar.make(root, "Введите ваше имя",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(lastname.getText().toString())){
                        Snackbar.make(root, "Введите вашу фамилию",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(login.getText().toString())){
                        Snackbar.make(root, "Введите ваш логин",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(password.getText().toString().length() < 5){
                        Snackbar.make(root, "Пароль должен иметь больше 5 символов!",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(email.getText().toString())){
                        Snackbar.make(root, "Введите вашу почту",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(birthday.getText().toString())){
                        Snackbar.make(root, "Введите вашу дату рождения",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(city.getText().toString())){
                        Snackbar.make(root, "Введите ваш город проживания",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(userdescription.getText().toString())){
                        Snackbar.make(root, "Напишите небольшое описание о себе!",Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    // Регистрация пользователя
                    auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    User user = new User() ;
                                    //String id = null;
                                    user.setName(name.getText().toString());
                                    user.setLastName(lastname.getText().toString());
                                    user.setLogin(login.getText().toString());
                                    user.setPassword(password.getText().toString());
                                    user.setEmail(email.getText().toString());
                                    user.setBirthday(birthday.getText().toString());
                                    user.setCity(city.getText().toString());
                                    user.setUserdescritpion(userdescription.getText().toString());
                                    Question question = new Question();
                                    question.setAnswer("Париж");
                                    question.setAuthor("Волков Александр");
                                    question.setCategory("Тренировка");
                                    question.setComment("-");
                                    question.setContent("Назовите столицу Франции");
                                    question.setSources("-");
                                    question.setUserEmail("1@mail.ru");
                                    questions.push().setValue(question);
/*
                                    Eventum eventum = new Eventum();
                                    eventum.setTitle("Вечер настольных игр");
                                    eventum.setDateEventum("29072024");
                                    eventum.setEventumDescription("Летний вечер настольных игр. 25 штук. 3 часа игры.");
                                    eventum.setType("Настолки");
                                    /*
                                    String id = "1";
                                    String name2 = name.getText().toString();
                                    String email2 = email.getText().toString();
                                    User user = new User(id,name2,null,null,null,email2,null,null,null);*/
                                    //eventumes.push().setValue(eventum);*/
                                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_LONG).show();
                                                }
                                            });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(root, "Ошибка регистрации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                                }
                            });

                }
            });
            dialog.show();
    }
}