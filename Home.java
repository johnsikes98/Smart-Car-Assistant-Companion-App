package com.example.companionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class Home extends Activity {

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        final Context context = this;
        button1 = (Button) findViewById(R.id.button1);
        //button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecentDrive.class);
                startActivity(intent);
            }
        });
/*
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArchiveData.class);
                startActivity(intent);
            }
        });*/

    }
}