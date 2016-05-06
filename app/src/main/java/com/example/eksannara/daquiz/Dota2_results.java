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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Dota2_results extends AppCompatActivity {
    TextView result;
    DBAdapterActivity db;
    TextView name;
    Button reTake;
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dota2_results);
        reTake=(Button)(findViewById(R.id.button3));
        Home=(Button)(findViewById(R.id.button4));
        name=(TextView)findViewById(R.id.playname);
        result=(TextView)findViewById(R.id.dpoint);
        result.setText(String.valueOf(Dota_Game.points));
        name.setText(MainActivity.playername);
        savePoint();
        //db.updateDotaPoint(String.valueOf(Dota_Game.points));
    }
    public void onRetakeClick(View v)
    {
        Intent intent = new Intent(Dota2_results.this, Dota2_Info.class);
        startActivity(intent);
    }
    public void onHomeClick(View v)
    {
        Intent intent =new Intent(Dota2_results.this,MainActivity.class);
        startActivity(intent);
        finish();
        /*
        Intent intent = new Intent(Dota2_results.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent); */
    }

    public void savePoint(){
        try {
            FileOutputStream fOut = openFileOutput("point.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(String.valueOf(Dota_Game.points));;
            osw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
