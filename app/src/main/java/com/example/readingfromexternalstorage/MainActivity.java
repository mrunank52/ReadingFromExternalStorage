package com.example.readingfromexternalstorage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv);
        btn = (Button)findViewById(R.id.btn1);

        String FILENAME = "SampleONE.txt";
        String name = "Hello there";


        // Here, thisActivity is the current activity

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }//Oncreate method



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Overided method included in the android library for requesting permission for call access
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnClickEvent(android.view.View v){

            String data="";

            try{
                File textFile = new File( Environment.getExternalStorageDirectory(),"SampleONE.txt");
                FileInputStream fis =  new FileInputStream(textFile);

                if(fis!=null){
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    String line=null;

                    while(  (line= br.readLine()) != null){
                        data = data + line +'\n';
                    }
                    fis.close();
                }
                tv.setText(data);

            }//try
            catch (Exception e){
                Toast.makeText(this,"No such file exists",Toast.LENGTH_SHORT).show();

            }

    }//end of function for btnclickevent



}//End of class
