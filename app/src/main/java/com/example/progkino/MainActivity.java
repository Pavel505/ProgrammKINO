package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Анимация кнопок
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnSignIn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
            }
        });
        // Запускаем авторизацию в БД
        auth = FirebaseAuth.getInstance();
        // Подключение к БД
        db = FirebaseDatabase.getInstance();
        /*
        * android:layout_above="@id/text_bottom"
        * android:id="@id/text_bottom"
        * */
/* <item>
        <share android:share="rectangle">
            <solid android:color="@color/btn_sign_in"></solid>
            <stroke android:color="@color/btn_sign_in_stroke" android:width="2dp"></stroke>
            <corners android:radius="2dp"></corners>
        </share>
    </item>*/
    }
}