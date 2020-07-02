package com.k3kc.logcat2graylog.logcat;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;

public class LogcatData extends Observable {

    private final int capacity;
    ArrayList<String> collectedData;

    public LogcatData(int capacity) {
        super();
        this.capacity = capacity;
        collectedData = new ArrayList<>(capacity);
    }

    public void logcatDataChanged() {
        Log.d("AAA", "logcatDataChanged");
        setChanged();
        notifyObservers();
        collectedData.clear();
    }

    public void setDataFromLogcat(String data) {
        collectedData.add(data);
        if(collectedData.size() >= capacity) {
            logcatDataChanged();
        }
    }

    public ArrayList<String> getData() {
        return collectedData;
    }

}
