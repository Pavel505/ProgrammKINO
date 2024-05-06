package com.example.progkino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progkino.Models.Eventum;
import com.example.progkino.Models.User;
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
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    private List<User> listTemp;
    private ListView list_users_admin;
    Integer positionEditDel;
    String user_name_pred,user_lastname_pred,user_email_pred,user_login_pred,user_date_pred,
            user_password_pred,user_role_pred,user_city_pred,user_descript_pred;
    TextView user_name_admin,user_lastname_admin,user_email_admin,user_login_admin,user_date_admin,
            user_password_admin,user_role_admin,user_city_admin,user_descript_admin;
    Button btn_edit_user,btn_del_user,btn_add_user;
    EditText editText_Name,editText_Lastname,editText_email,editText_login,editText_password,editText_role,
            editText_city,editText_Date,editText_Descript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_user);
        init();
        getDataFromDB_AdminUser();
        setOnClickItemUser();

        btn_add_user = (Button) findViewById(R.id.btn_add_user);
        btn_add_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn_edit_user = (Button) findViewById(R.id.btn_edit_user);
        btn_edit_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
        btn_del_user = (Button) findViewById(R.id.btn_del_user);
        btn_del_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void init(){
        eventumRecycler  = findViewById(R.id.eventumRecycler);
        db = FirebaseDatabase.getInstance();
        eventumes = db.getReference("Eventum");
        users = db.getReference("User");
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        list_users_admin = findViewById(R.id.list_users);
        list_users_admin.setAdapter(adapterAr1);
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
    public void navigatorUser(View view){
        Intent intentChat5 = new Intent(this, UserActivity_Admin.class);
        intentChat5.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat5);
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
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    String txt = "Имя  : "+ user.getName() + "\n Фамилия : "+ user.getName()+ "\n Почта  : "+ user.getEmail() +
                    "\n Логин  : "+ user.getLogin() + "\n Пароль  : "+ user.getPassword() + "\n Роль  : "+ user.getRole() +
                            "\n ДР  : "+ user.getBirthday()  + "\n Город  : "+ user.getCity() + "\n О себе  : "+ user.getUserdescritpion();
                    listData.add(txt);
                    listTemp.add(user);
                }
                adapterAr1.notifyDataSetChanged();
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

                User user = listTemp.get(position);
                positionEditDel = position;
                user_name_pred = user.getName().toString();
                user_lastname_pred = user.getLastName().toString();
                user_city_pred = user.getCity().toString();
                user_date_pred = user.getBirthday().toString();
                user_email_pred = user.getEmail().toString();
                user_password_pred = user.getPassword().toString();
                user_role_pred = user.getRole().toString();
                user_login_pred = user.getLogin().toString();
                user_descript_pred = user.getUserdescritpion();

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

}