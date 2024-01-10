package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Анимация кнопок
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Button btnAlpha = (Button)findViewById(R.id.alpha);
        btnAlpha.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
            }
        });

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