package com.k3kc.logcat2graylog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;

import com.k3kc.logcat2graylog.logcat.GraylogReceiver;
import com.k3kc.logcat2graylog.logcat.LogcatData;
import com.k3kc.logcat2graylog.logcat.LogcatWrapper;
import com.k3kc.logcat2graylog.sender.GelfSenderBehavior;
import com.k3kc.logcat2graylog.sender.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button: {

                Log.d("AAA", "btnRunGraylog");

                LogcatData logcatData = new LogcatData(5);
                GraylogReceiver graylogReceiver = new GraylogReceiver(logcatData, new Sender(new GelfSenderBehavior()));

                List<String> sCommand = new ArrayList<String>();
                sCommand.add("logcat");
                sCommand.add("-bmain");
                sCommand.add("-vtime");

                try {
                    LogcatWrapper logcatWrapper = new LogcatWrapper(sCommand, logcatData);
                    Thread thread = new Thread(logcatWrapper);
                    thread.start(); // instead of run

                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            }
            case R.id.button2: {
                Log.d("AAA", "hello");
                break;
            }
        }
    }
}
