package com.example.companionapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.view.Gravity;
import android.os.AsyncTask;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.List;

public class RecentDrive extends Activity {

    private TableLayout rdTable;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_drive);

        rdTable = (TableLayout) findViewById(R.id.RecentDrive_table);
        rdTable.setStretchAllColumns(true);

        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadData() throws IOException {

        InputStream is = getResources().openRawResource(R.raw.exampledataset);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";



        String myDirectory = "DriveData";
        String dataFile = "data.csv";

        String extStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();

        File outDirectory = new File(extStorage + File.separator + myDirectory);
        if(!outDirectory.exists()) {
            outDirectory.mkdir();
        }

        File file = new File(outDirectory + File.separator + dataFile);
        if(!file.exists()) {
            file.createNewFile();
        }

        FileInputStream fis = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        String line = "";


        final TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv.setGravity(Gravity.LEFT);
        tv.setPadding(5, 15, 0, 15);
        tv.setText("Sign Type:");

        final TextView tv2 = new TextView(this);
        tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv2.setGravity(Gravity.LEFT);
        tv2.setPadding(5, 15, 0, 15);
        tv2.setText("Time Seen:");

        final TextView tv3 = new TextView(this);
        tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tv3.setGravity(Gravity.LEFT);
        tv3.setPadding(5, 15, 0, 15);
        tv3.setText("Total Signs:");

        final TableRow tr = new TableRow(this);
        TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        trParams.setMargins(0,0,0,0);
        tr.setPadding(0,0,0,0);
        tr.setLayoutParams(trParams);
        tr.addView(tv);
        tr.addView(tv2);
        tr.addView(tv3);

        rdTable.addView(tr, trParams);

        while((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String sign = tokens[0];
            //int time = Integer.parseInt(tokens[1]);
            String time = tokens[1];
            int totalSigns = Integer.parseInt(tokens[2]);

            final ImageView signs = new ImageView(this);
            signs.setLayoutParams(new TableRow.LayoutParams(100,140));
            setImage(signs, sign);
            signs.setPadding(5, 15, 0, 15);

            final TextView times = new TextView(this);
            times.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            times.setGravity(Gravity.LEFT);
            times.setPadding(5, 15, 0, 15);
            times.setText(time);

            final TextView totals = new TextView(this);
            totals.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            totals.setGravity(Gravity.LEFT);
            totals.setPadding(5, 15, 0, 15);
            totals.setText(String.valueOf(totalSigns));

            final TableRow newRow = new TableRow(this);
            TableLayout.LayoutParams newRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(0,0,0,0);
            newRow.setPadding(0,0,0,0);
            newRow.setLayoutParams(newRowParams);
            newRow.addView(signs);
            newRow.addView(times);
            newRow.addView(totals);

            rdTable.addView(newRow, newRowParams);

            final TableRow separator = new TableRow(this);
            TableLayout.LayoutParams sepParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            sepParams.setMargins(0,0,0,0);
            separator.setLayoutParams(sepParams);

            TextView sepText = new TextView(this);
            TableRow.LayoutParams sepLay = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            sepLay.span = 3;
            sepText.setLayoutParams(sepLay);
            sepText.setHeight(1);

            separator.addView(sepText);
            rdTable.addView(separator, sepParams);
        }
    }

    public void setImage(ImageView image, String sign) {

        switch (sign) {
            case "stop":
                image.setImageResource(R.drawable.stop);
                break;
            case "keep right":
                image.setImageResource(R.drawable.keepright);
                break;
            case "added lane":
                image.setImageResource(R.drawable.addedlane);
                break;
            case "merge":
                image.setImageResource(R.drawable.merge);
                break;
            case "pedestrian":
                image.setImageResource(R.drawable.pedestrian);
                break;
            case "speed limit":
                image.setImageResource(R.drawable.speedlimit);
                break;
            case "traffic signal":
            case "traffic light":
                image.setImageResource(R.drawable.trafficlight);
                break;
            case "turn":
                image.setImageResource(R.drawable.turn);
                break;
            case "yield":
                image.setImageResource(R.drawable.yield);
                break;
        }
    }
}