package com.example.eksannara.daquiz;

import com.example.eksannara.daquiz.InputNameDialogFragment.InputNameDialogListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements InputNameDialogListener {
    public static String playername="Guest";
    public static final int READ_BLOCK_SIZE = 100;
    TextView names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names=(TextView)findViewById(R.id.name);
        String[] topics = {"Dota2","Capital Cities","Politics"};
        final int[] imgs={R.drawable.dota,R.drawable.capital,R.drawable.politic};
        ListAdapter narasAdapter = new CustomAdapter(this,topics,imgs);
        ListView narasListView = (ListView) findViewById(R.id.narasListView);
        try{
            FileInputStream fIn = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];

            String check = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0)
            {
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                check += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            isr.close();

            if (check.equals(""))
            {
            }
            else names.setText(check);




            /*edcontent.setText(s);
            String []userdata=s.split(":");
            Toast.makeText(getBaseContext(),
                    "File loaded successfully! Username:" + userdata[0],
                    Toast.LENGTH_SHORT).show();*/
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        narasListView.setAdapter(narasAdapter);
        narasListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this, Dota2_Info.class);
                            startActivity(intent);
                        }
                        if (position == 1) {
                            Intent intent = new Intent(MainActivity.this, Capital_City_Info.class);
                            startActivity(intent);
                        }
                        if (position == 2) {
                            Intent intent = new Intent(MainActivity.this, Politics.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }



    public void showInputNameDialog(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        InputNameDialogFragment inputNameDialog = new InputNameDialogFragment();
        inputNameDialog.setCancelable(false);
        inputNameDialog.setDialogTitle("Enter Name");
        inputNameDialog.show(fragmentManager, "input dialog");
    }

    @Override
    public void onFinishInputDialog(String inputText) {
        names.setText(inputText);
        playername=inputText;
        /*Toast.makeText(this, "Returned from dialog: " + inputText,
                Toast.LENGTH_SHORT).show();*/
        try{
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(inputText);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
