package com.example.eksannara.daquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Politic_Result extends AppCompatActivity {
    TextView result;
    TextView name;
    Button reTake;
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politic__result);
        reTake=(Button)(findViewById(R.id.button3));
        Home=(Button)(findViewById(R.id.button4));
        name=(TextView)findViewById(R.id.playname);
        result=(TextView)findViewById(R.id.dpoint);
        result.setText(String.valueOf(politic_game.points));
        name.setText(MainActivity.playername);
        savePoint();

    }
    public void onRetakeClick(View v)
    {
        Intent intent = new Intent(Politic_Result.this, Politics.class);
        startActivity(intent);
        finish();
    }
    public void onHomeClick(View v)
    {
        Intent intent =new Intent(Politic_Result.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void savePoint(){
        try {
            FileOutputStream fOut = openFileOutput("point3.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(String.valueOf(politic_game.points));;
            osw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }





}
