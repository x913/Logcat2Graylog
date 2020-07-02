package com.k3kc.logcat2graylog.logcat;

import android.util.Log;


import com.k3kc.logcat2graylog.sender.GelfSenderBehavior;
import com.k3kc.logcat2graylog.sender.Sender;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GraylogReceiver implements Observer {

    Observable observable;
    Sender sender;

    public GraylogReceiver(Observable observable, Sender sender) {
        this.sender = sender;
        this.observable = observable;
        observable.addObserver(this);
        this.sender = new Sender(new GelfSenderBehavior());
    }

    public void OnGrayLogDataReady(ArrayList<String> collectedData) {
        Log.d("AAA", "Collected data ready for graylog! " + collectedData.size());
        sender.send(collectedData);
    }

    @Override
    public void update(Observable o, Object args) {
        if(o instanceof LogcatData) {
            LogcatData  logcatData = (LogcatData)o;
            OnGrayLogDataReady(logcatData.collectedData);
        }
    }
}
