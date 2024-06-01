package com.example.progkino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserActivity_Admin extends AppCompatActivity {
    RecyclerView eventumRecycler;
    DatabaseReference eventumes,users;
    FirebaseDatabase db;
    private ArrayAdapter<String> adapterAr3;
    private List<String> listData2;
    private List<User> listTemp2;
    private ListView list_users_admin;
    Integer positionEditDel;
    String user_name_pred,user_lastname_pred,user_email_pred,user_login_pred,user_date_pred,
            user_password_pred,user_role_pred,user_city_pred,user_descript_pred;
    TextView user_name_admin,user_lastname_admin,user_email_admin,user_login_admin,user_date_admin,
            user_password_admin,user_role_admin,user_city_admin,user_descript_admin;
    Button btn_edit_user,btn_del_user,btn_add_user;
    EditText editText_Name,editText_Lastname,editText_email,editText_login,editText_password,editText_role,
            editText_city,editText_Date,editText_Descript;
    RelativeLayout root;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_user);
        //root = findViewById(R.id.root_element);
        init();
        getDataFromDB_AdminUser();
        setOnClickItemUser();

        btn_add_user = (Button) findViewById(R.id.btn_add_user);
        btn_add_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                editText_Name = findViewById(R.id.text_name_user_admin);
                String user_name_new =  editText_Name.getText().toString();
                editText_Lastname = findViewById(R.id.text_lastname_user_admin);
                String user_lastname_new = editText_Lastname.getText().toString();
                editText_Date = findViewById(R.id.text_date_user_admin);
                String user_date_new = editText_Date.getText().toString();
                editText_login = findViewById(R.id.text_login_user_admin);
                String user_login_new = editText_login.getText().toString();
                editText_password = findViewById(R.id.text_password_user_admin);
                String user_password_new = editText_password.getText().toString();
                editText_role = findViewById(R.id.text_role_user_admin);
                String user_role_new = editText_role.getText().toString();
                editText_city = findViewById(R.id.text_city_user_admin);
                String user_city_new = editText_city.getText().toString();
                editText_email = findViewById(R.id.text_email_user_admin);
                String user_email_new = editText_email.getText().toString();
                editText_Descript = findViewById(R.id.text_descript_user_admin);
                String user_descript_new = editText_Descript.getText().toString();

                user_add_db(user_name_new, user_lastname_new, user_date_new , user_login_new,user_password_new,user_role_new
                        ,user_city_new,user_email_new,user_descript_new);
            }
        });
        btn_edit_user = (Button) findViewById(R.id.btn_edit_user);
        btn_edit_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                editText_Name = findViewById(R.id.text_name_user_admin);
                String user_name_new =  editText_Name.getText().toString();
                editText_Lastname = findViewById(R.id.text_lastname_user_admin);
                String user_lastname_new = editText_Lastname.getText().toString();
                editText_Date = findViewById(R.id.text_date_user_admin);
                String user_date_new = editText_Date.getText().toString();
                editText_login = findViewById(R.id.text_login_user_admin);
                String user_login_new = editText_login.getText().toString();
                editText_password = findViewById(R.id.text_password_user_admin);
                String user_password_new = editText_password.getText().toString();
                editText_role = findViewById(R.id.text_role_user_admin);
                String user_role_new = editText_role.getText().toString();
                editText_city = findViewById(R.id.text_city_user_admin);
                String user_city_new = editText_city.getText().toString();
                editText_email = findViewById(R.id.text_email_user_admin);
                String user_email_new = editText_email.getText().toString();
                editText_Descript = findViewById(R.id.text_descript_user_admin);
                String user_descript_new = editText_Descript.getText().toString();

                user_edit_DBshka(user_name_new, user_lastname_new, user_date_new , user_login_new,user_password_new,user_role_new
                        ,user_city_new,user_email_new,user_descript_new);
                init();
                getDataFromDB_AdminUser();
            }
        });
        btn_del_user = (Button) findViewById(R.id.btn_del_user);
        btn_del_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                user_delete_DBshka();
            }
        });

    }
    private void init(){
        auth = FirebaseAuth.getInstance();
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        users = db.getReference("User");
        listData2 = new ArrayList<>();
        listTemp2 = new ArrayList<>();
        adapterAr3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData2);
        list_users_admin = findViewById(R.id.list_users);
        list_users_admin.setAdapter(adapterAr3);
        user_name_admin  = findViewById(R.id.text_name_user_admin);
        user_lastname_admin  = findViewById(R.id.text_lastname_user_admin);
        user_date_admin  = findViewById(R.id.text_date_user_admin);
        user_role_admin  = findViewById(R.id.text_role_user_admin);
        user_login_admin  = findViewById(R.id.text_login_user_admin);
        user_password_admin  = findViewById(R.id.text_password_user_admin);
        user_city_admin  = findViewById(R.id.text_city_user_admin);
        user_email_admin  = findViewById(R.id.text_email_user_admin);
        user_descript_admin  = findViewById(R.id.text_descript_user_admin);
        user_name_admin.setText(" ");
        user_lastname_admin.setText(" ");
        user_email_admin.setText(" ");
        user_password_admin.setText(" ");
        user_login_admin.setText(" ");
        user_city_admin.setText(" ");
        user_role_admin.setText(" ");
        user_descript_admin.setText(" ");
        user_date_admin.setText(" ");

    }
    public void navigatorEventum(View view){
        Intent intentChat6 = new Intent(this, EventumActivity_Admin.class);
        intentChat6.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat6);
    }
    public void navigatorMainAdmin(View view){
        Intent intentChat7 = new Intent(this, HomeActivity_Admin.class);
        intentChat7.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat7);
    }

    private void getDataFromDB_AdminUser(){

        ValueEventListener vlistener_user1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData2.size() > 0 )listData2.clear();
                if(listTemp2.size() > 0 )listTemp2.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    String txt = "Имя  : "+ user.getName() + "\n Фамилия : "+ user.getLastName()+ "\n Почта  : "+ user.getEmail() +
                    "\n Логин  : "+ user.getLogin() + "\n Пароль  : "+ user.getPassword() + "\n Роль  : "+ user.getRole() +
                            "\n ДР  : "+ user.getBirthday()  + "\n Город  : "+ user.getCity() + "\n О себе  : "+ user.getUserdescription();
                    listData2.add(txt);
                    listTemp2.add(user);
                }
                adapterAr3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        users.addValueEventListener(vlistener_user1);

    }

    public void setOnClickItemUser(){
        list_users_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User user = listTemp2.get(position);
                positionEditDel = position;

                user_name_pred = user.getName();
                user_lastname_pred = user.getLastName();
                user_city_pred = user.getCity();
                user_date_pred = user.getBirthday();
                user_email_pred = user.getEmail();
                user_password_pred = user.getPassword();
                user_role_pred = user.getRole();
                user_login_pred = user.getLogin();
                user_descript_pred = user.getUserdescription();

                user_name_admin.setText(user_name_pred);
                user_lastname_admin.setText(user_lastname_pred);
                user_city_admin.setText(user_city_pred);
                user_date_admin.setText(user_date_pred);
                user_email_admin.setText(user_email_pred);
                user_password_admin.setText(user_password_pred);
                user_role_admin.setText(user_role_pred);
                user_login_admin.setText(user_login_pred);
                user_descript_admin.setText(user_descript_pred);

            }
        });
    }
    public void user_add_db(String name, String lastname, String date,String login ,
                            String password ,String role,String city,String email, String descript){
        // Регистрация пользователя
        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User() ;
                        //String id = null;
                        user.setName(name);
                        user.setLastName(lastname);
                        user.setLogin(login);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setBirthday(date);
                        user.setCity(city);
                        user.setUserdescription(descript);
                        user.setRole(role);
                        user.setCounter_notice(0);

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user);
                    }
                });
    }
    public void user_delete_DBshka(){
        ValueEventListener vlistener_user1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData2.size() > 0 )listData2.clear();
                if(listTemp2.size() > 0 )listTemp2.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    if(user_email_pred.equalsIgnoreCase(user.getEmail().toString())){
                        DatabaseReference itemRef = ds.getRef();
                        itemRef.removeValue();
                        return;
                    };
                    String txt = "Имя  : "+ user.getName() + "\n Фамилия : "+ user.getName()+ "\n Почта  : "+ user.getEmail() +
                            "\n Логин  : "+ user.getLogin() + "\n Пароль  : "+ user.getPassword() + "\n Роль  : "+ user.getRole() +
                            "\n ДР  : "+ user.getBirthday()  + "\n Город  : "+ user.getCity() + "\n О себе  : "+ user.getUserdescription();
                    listData2.add(txt);
                    listTemp2.add(user);
                }
                adapterAr3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        users.addValueEventListener(vlistener_user1);
    }
    public void user_edit_DBshka(String name, String lastname, String date,String login ,
                                 String password ,String role,String city,String email, String descript){
        ValueEventListener vlistener_user1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData2.size() > 0 )listData2.clear();
                if(listTemp2.size() > 0 )listTemp2.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    DatabaseReference itemRef2 = ds.getRef();
                    if(user_email_pred.equalsIgnoreCase(user.getEmail())){
                        itemRef2.child("name").setValue(name);
                        itemRef2.child("lastName").setValue(lastname);
                        itemRef2.child("email").setValue(email);
                        itemRef2.child("password").setValue(password);
                        itemRef2.child("role").setValue(role);
                        itemRef2.child("birthday").setValue(date);
                        itemRef2.child("login").setValue(login);
                        itemRef2.child("city").setValue(city);
                        itemRef2.child("userdescription").setValue(descript);
                        return;
                    };
                    String txt = "Имя  : "+ name + "\n Фамилия : "+ lastname + "\n Почта  : "+ email +
                            "\n Логин  : "+ login + "\n Пароль  : "+ password + "\n Роль  : "+ role +
                            "\n ДР  : "+ date  + "\n Город  : "+ city + "\n О себе  : "+ descript;
                    listData2.add(txt);
                    listTemp2.add(user);
                }
                adapterAr3.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        users.addValueEventListener(vlistener_user1);
        //finishActivity(UserActivity_Admin);
    }

}