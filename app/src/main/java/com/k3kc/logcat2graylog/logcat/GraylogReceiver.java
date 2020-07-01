package com.k3kc.logcat2graylog.logcat;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GraylogReceiver implements Observer {


    public void GraylogReceive(ArrayList<String> collectedData) {
        Log.d("AAA", "Collected data ready for graylog");
    }

    @Override
    public void update(Observable observable, Object o) {
        if(o instanceof LogcatData) {
            LogcatData  logcatData = (LogcatData)o;
            GraylogReceive(logcatData.collectedData);
        }
    }
}
