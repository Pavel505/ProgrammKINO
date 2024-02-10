package com.example.progkino;

import static android.content.ContentValues.TAG;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;
import static com.example.progkino.HomeActivity.eventumList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public void onClickRead2 (View view){
        Intent intentTrening2 = new Intent(TrenActivity.this, Trening2Activity.class);
        intentTrening2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening2);
    }

    public void onClickRead3 (View view) throws IOException {
        /*FileInputStream fin = null;
       // TextView textView = findViewById(R.id.text);
        try {
            fin = openFileInput("skazki.md");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            //textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }*/
        /*try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(("skazki.md"))));
            String str = "";
            // читаем содержимое
            while ((str = br.readLine()) != null) {


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*Intent intentTrening3 = new Intent(TrenActivity.this, Trening2Activity.class);
        intentTrening3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentTrening3);*/
        /*File file = new File("C:\\Users\\root\\Desktop\\ProgKino\\app\\src\\main\\assets\\skazki.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int data = fileInputStream.read();
        while (data != -1) {
// Обработка данных из файла
            data = fileInputStream.read();
        }
        fileInputStream.close();*/
    }

    /*
    public void navigatorTrening(View view){
public void openText(View view){

        FileInputStream fin = null;
        TextView textView = findViewById(R.id.text);
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            textView.setText(text);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
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
    public String f(View view){
        String filename = "skazki.txt";

    }*/

/*
    public Bitmap loadBitmapFromAssets(Context context) {
        String filename = "skazki.txt";
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
