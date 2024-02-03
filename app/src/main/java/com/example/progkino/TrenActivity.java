package com.example.progkino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tren);
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
    public void navigatorContact(View view){
        Intent intentContact = new Intent(this, ContactActivity.class);
        intentContact.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentContact);
    }

    public void onClickRead (View view){
        Intent intentTrening = new Intent(TrenActivity.this, TreningActivity.class);
        intentTrening.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening);
    }

    /*
    public void navigatorTrening(View view){

    }*/






/*
    String getStringFromAssetFile(Activity activity)
    {
        AssetManager am = activity.getAssets();
        InputStream is = am.open("1geografy.docx");
        String s = convertStreamToString(is);
        is.close();
        return s;
    }*/
/*
    public String f(){
        String filename = "1geografy.docx";
        String text = filename;
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(text);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str_data = new String(buffer);
        return str_data;
    }*/
/*
    public Bitmap loadBitmapFromAssets(Context context, String filename) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            return BitmapFactory.decodeStream(is,null,opt);
        }
        catch (IOException e) {
            e.printStackTrace();
            return BitmapFactory.decodeStream(null,null,null);
        }
    }*/

}