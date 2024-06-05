/*package com.example.progkino;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.progkino.Models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserActivity_Admin_Upgrate extends AppCompatActivity {
    DatabaseReference users;
    FirebaseDatabase db;
    private ListView list_users_admin;
    private ArrayAdapter<String> adapterAr1;
    private List<String> listData;
    private List<User> listTemp;
    Integer positionEditDel;
    String user_name_pred,user_lastName_pred,user_date_pred,user_descript_pred;
    TextView user_name_admin,user_lastName_admin,user_date_admin,user_descript_admin;
    Button btn_edit_user,btn_del_user,btn_add_user;
    EditText editText_Name,editText_lastName,editText_Date,editText_Descript;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_user);
        init();
        getDataFromDB_AdminUser();
        setOnClickItemUser();
        btn_add_user = findViewById(R.id.btn_add_user);
        btn_add_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                editText_Name = findViewById(R.id.text_name_user_admin);
                String us_name_new = editText_Name.getText().toString();
                editText_lastName = findViewById(R.id.text_lastname_user_admin);
                String user_lastName_new = editText_lastName.getText().toString();
                editText_Date = findViewById(R.id.text_date_user_admin);
                String user_date_new = editText_Date.getText().toString();
                editText_Descript = findViewById(R.id.text_descript_user_admin);
                String user_descript_new = editText_Descript.getText().toString();
                user_add_DB(us_name_new,user_lastName_new,user_date_new, user_descript_new);
            }
        });
        btn_edit_user = findViewById(R.id.btn_edit_user);
        btn_edit_user.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_Name = findViewById(R.id.text_name_user_admin);
                String us_name_new = editText_Name.getText().toString();
                editText_lastName = findViewById(R.id.text_lastname_user_admin);
                String user_lastName_new = editText_lastName.getText().toString();
                editText_Date = findViewById(R.id.text_date_user_admin);
                String user_date_new = editText_Date.getText().toString();
                editText_Descript = findViewById(R.id.text_descript_user_admin);
                String user_descript_new = editText_Descript.getText().toString();
                user_from_DB(us_name_new,user_lastName_new,user_date_new, user_descript_new);
            }
        });

        btn_del_user = findViewById(R.id.btn_del_user);
        btn_del_user.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                setOnClickItemUser();
                user_delete_DB();
            }
        });
    }
    public void navigatorUser(View view){
        Intent intentChat = new Intent(this, UserActivity_Admin_Upgrate.class);
        intentChat.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat);
    }
    public void navigatorMainAdmin(View view){
        Intent intentChat2 = new Intent(this, HomeActivity_Admin.class);
        intentChat2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentChat2);
    }
    private void init(){
        user_name_admin  = findViewById(R.id.text_name_user_admin);
        user_lastName_admin = findViewById(R.id.text_lastname_user_admin);
        user_date_admin = findViewById(R.id.text_date_user_admin);
        user_descript_admin = findViewById(R.id.text_descript_user_admin);

        user_name_admin.setText(" ");
        user_lastName_admin.setText(" ");
        user_date_admin.setText(" ");
        user_descript_admin.setText(" ");

        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");
        list_users_admin = findViewById(R.id.list_users);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapterAr1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        list_users_admin.setAdapter(adapterAr1);
    }


    private void getDataFromDB_AdminUser(){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User userik = ds.getValue(User.class);
                    String txt = "Имя: "+ userik.getName() + "Фамилия: "+ userik.getLastName() + "ДР: "
                            + userik.getBirthday() + "О себе: "+ userik.getUserdescription();
                    listData.add(txt);
                    listTemp.add(userik);
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        users.addValueEventListener(vlistener_eventum1);
    }

    public void setOnClickItemUser(){
        list_users_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User userik = listTemp.get(position);
                positionEditDel = position;

                user_name_pred = userik.getName();
                user_lastName_pred = userik.getLastName();
                user_date_pred = userik.getBirthday();
                user_descript_pred = userik.getUserdescription();

                user_name_admin.setText(user_name_pred);
                user_lastName_admin.setText(user_lastName_pred);
                user_date_admin.setText(user_date_pred);
                user_descript_admin.setText(user_descript_pred);
            }
        });
    }
    public void user_add_DB(String title,String lastName,String date,String descript){
        User userik = new User();
        userik.setName(title);
        userik.setLastName(lastName);
        userik.setBirthday(date);
        userik.setUserdescription(descript);

        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(userik)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Snackbar.make(root, "Пользователь добавлен", Snackbar.LENGTH_LONG).show();
                    }
                });

    }

    private void user_from_DB(String name,String lastName,String date,String descript){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                   User userik = ds.getValue(User.class);

                    if(user_name_pred.equalsIgnoreCase(userik.getName())){
                        // Логика обновления данных
                        DatabaseReference itemRef4 = ds.getRef();
                        //itemRef.removeValue();
                        //ev_admin.child(String.valueOf(positionEditDel)).child("title").setValue("ПОИСК 2025");
                        itemRef4.child("name").setValue(name);
                        itemRef4.child("lastName").setValue(lastName);
                        itemRef4.child("birthday").setValue(date);
                        itemRef4.child("userdescription").setValue(descript);

                        return;
                    }
                    String txt = "Имя: "+ userik.getName() + "Фамилия: "+ userik.getLastName() + "ДР: "
                            + userik.getBirthday() + "О себе: "+ userik.getUserdescription();
                    listData.add(txt);
                    listTemp.add(userik);
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        Log.w(TAG, "точка 3");
        users.addValueEventListener(vlistener_eventum1);
    }
    private void user_delete_DB(){
        ValueEventListener vlistener_eventum1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0 )listData.clear();
                if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User userik = ds.getValue(User.class);
                    if(user_name_pred.equalsIgnoreCase(userik.getName())){
                        // Логика замены данных
                        DatabaseReference itemRef = ds.getRef();
                        itemRef.removeValue();
                        return;
                    }
                    String txt = "Имя: "+ userik.getName() + "Фамилия: "+ userik.getLastName() + "ДР: "
                            + userik.getBirthday() + "О себе: "+ userik.getUserdescription();
                    listData.add(txt);
                    listTemp.add(userik);
                }
                adapterAr1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        users.addValueEventListener(vlistener_eventum1);
    }
}*/