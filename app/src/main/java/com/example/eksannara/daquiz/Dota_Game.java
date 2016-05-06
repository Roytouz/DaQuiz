package com.example.eksannara.daquiz;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class Dota_Game extends AppCompatActivity {
    DBAdapterActivity db;
    TextView dquestion;
    ImageView dimg;
    TextView timer;
    CountDownTimer mycd;
    Button dotabutton1;
    Button dotabutton2;
    Button dotabutton3;
    Button dotabutton4;
    TextView dpoints;
    MediaPlayer mp;
    MediaPlayer np;
    Cursor c;
    public static int counter = 1;
    public static int points=0;
    //public static int myint=0;
    //public static ArrayList<Integer> list = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dota__game);
        Database();
        mp = MediaPlayer.create(this, R.raw.correct);
        np = MediaPlayer.create(this,R.raw.wrong);

        counter=1;
        dquestion=(TextView)findViewById(R.id.dtext);
        dotabutton1=(Button)findViewById(R.id.db1);
        dotabutton2=(Button)findViewById(R.id.db2);
        dotabutton3=(Button)findViewById(R.id.db3);
        dotabutton4=(Button)findViewById(R.id.db4);
        dpoints=(TextView)findViewById(R.id.dpoint);
        dimg=(ImageView)findViewById(R.id.dimg);
        timer=(TextView)findViewById(R.id.dtimer);
        timerhandler();
        work(counter);
        points=0;

    }
    //  String Array[]= getResources().getStringArray(R.array.array);
    public void timerhandler()
    {
         mycd=new CountDownTimer(11000,1000)
        {
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }
            public void onFinish() {
                if(counter<=Dota2_Info.rowLength) {
                    timer.setText("0");
                    disablebtn();
                    mycd.cancel();
                    btncolor();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            continuestep();
                            timerhandler();
                            db.close();
                        }
                    }, 1500);
                }
            }
        }.start();
    }
    public void work(int row)
    {

       ArrayList<Integer> dorder = new ArrayList<Integer>();
        for (int i=3; i<=6; i++)
        {
            dorder.add(new Integer(i));
        }
        Collections.shuffle(dorder);

        db.open();
        c = db.getDota(row);
        c.moveToFirst();
        String mDrawableName = c.getString(2).toLowerCase();
        int id = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(id); //HEREERERERE
        dimg.setImageDrawable(drawable);
        dotabutton1.setText(c.getString(dorder.get(3)));
        dotabutton2.setText(c.getString(dorder.get(2)));
        dotabutton3.setText(c.getString(dorder.get(1)));
        dotabutton4.setText(c.getString(dorder.get(0)));
        dquestion.setText(c.getString(1));
        db.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    public void continuestep()
    {
        if (counter == Dota2_Info.rowLength)
        {
            counter++;
            finish();
            Intent intent= new Intent(Dota_Game.this,Dota2_results.class);
            startActivity(intent);

        }
        else
        {
            dotabutton1.setBackgroundResource(android.R.drawable.btn_default);
            dotabutton2.setBackgroundResource(android.R.drawable.btn_default);
            dotabutton3.setBackgroundResource(android.R.drawable.btn_default);
            dotabutton4.setBackgroundResource(android.R.drawable.btn_default);
            counter++;
            dotabutton1.setEnabled(true);
            dotabutton2.setEnabled(true);
            dotabutton3.setEnabled(true);
            dotabutton4.setEnabled(true);
            work(counter);
        }
    }
    public void disablebtn()
    {
        dotabutton1.setEnabled(false);
        dotabutton2.setEnabled(false);
        dotabutton3.setEnabled(false);
        dotabutton4.setEnabled(false);
    }
    public void check(Button btn)
    {
        db.open();
        c = db.getDota(counter);
        c.moveToFirst();
        if (c.getString(3).equals(btn.getText().toString()))
        {
            mp.start();

            disablebtn();
          int time = Integer.parseInt(timer.getText().toString());
            points=points+time;
            dpoints.setText(String.valueOf(points));
            btn.setBackgroundColor(Color.GREEN);
            mycd.cancel();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    continuestep();
                    timerhandler();
                    db.close();
                }
            }, 1500);
        } else
        {
            np.start();
            disablebtn();
            mycd.cancel();
            btn.setBackgroundColor(Color.RED);
            btncolor();
            db.close();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    continuestep();
                    timerhandler();
                }
            }, 1500);

        }
    }
    public void btncolor()
    {
        db.open();
        c = db.getDota(counter);
        c.moveToFirst();
        if(dotabutton1.getText().toString().equals(c.getString(3)))
        {
            dotabutton1.setBackgroundColor(Color.GREEN);
        }
        else if(dotabutton2.getText().toString().equals(c.getString(3)))
        {
            dotabutton2.setBackgroundColor(Color.GREEN);
        }
        else if(dotabutton3.getText().toString().equals(c.getString(3)))
        {
            dotabutton3.setBackgroundColor(Color.GREEN);
        }
        else {
            dotabutton4.setBackgroundColor(Color.GREEN);
        }
        db.close();
    }
    public void dbtn1(View v)
    {
        check(dotabutton1);
    }
    public void dbtn2(View v)
    {
        check(dotabutton2);
    }
    public void dbtn3(View v)
    {
        check(dotabutton3);
    }
    public void dbtn4(View v)
    {
        check(dotabutton4);
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
}


