package com.k3kc.logcat2graylog.logcat;

import java.util.ArrayList;
import java.util.Observable;

public class LogcatData extends Observable {

    private final int capacity;
    ArrayList<String> collectedData;

    public LogcatData(int capacity) {
        super();
        this.capacity = capacity;
        collectedData = new ArrayList<String>(capacity);
    }

    public void logcatDataChanged() {
        setChanged();
        notifyObservers();
    }

    public void setDataFromLogcat(String data) {
        collectedData.add(data);
        if(collectedData.size() >= capacity) {
            logcatDataChanged();
            collectedData.clear();
        }
    }

    public ArrayList<String> getData() {
        return collectedData;
    }

}
