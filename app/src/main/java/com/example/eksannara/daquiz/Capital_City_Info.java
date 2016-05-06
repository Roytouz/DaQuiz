package com.example.eksannara.daquiz;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Capital_City_Info extends AppCompatActivity {
    TextView ddquestion;
    TextView ddpoints;
    static boolean start=true;
    DBAdapterActivity db;
    public static int rowLength=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital__city__info);
        Database();
        rowLength=0;
        if(start==true) {
            db.open();
            Cursor c = db.getAllCapital();
            if (c.moveToFirst()) {
                do {
                    rowLength++;
                } while (c.moveToNext());
            }
            c.close();
        }

        ddpoints=(TextView)findViewById(R.id.cpoint);
        ddquestion=(TextView)findViewById(R.id.cquestion);
        ddquestion.setText(String.valueOf(rowLength));
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(ddpoints.getText().toString()!="0")
            getPoint();
        db.close();
    }
    public void getPoint(){
        try
        {
            FileInputStream fIn =
                    openFileInput("point1.txt");
            InputStreamReader isr = new
                    InputStreamReader(fIn);
            char[] inputBuffer = new char[MainActivity.READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0)
            {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                s += readString;
                inputBuffer = new char[MainActivity.READ_BLOCK_SIZE];
            }
            isr.close();
            String []userdata=s.split(":");
            if(Integer.parseInt(userdata[0])>Integer.parseInt(ddpoints.getText().toString()))
                ddpoints.setText(userdata[0]);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void playclick(View v)
    {
        Intent intent = new Intent(Capital_City_Info.this,Capital_City_Game.class);
        startActivity(intent);
        finish();
    }
    private void Database() {
        String destDir = "/data/data/" + getPackageName() +
                "/databases/";
        String destPath = destDir + "MyDB";
        File f = new File(destPath);
        if (!f.exists()) {
            File directory = new File(destDir);
            directory.mkdirs();
            try {
                CopyDB(getBaseContext().getAssets().open("mydbs"),
                        new FileOutputStream(destPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db = new DBAdapterActivity(this);
    }
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

}
