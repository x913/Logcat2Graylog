package com.k3kc.logcat2graylog.logcat;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LogcatWrapper implements Runnable {

    LogcatData logcatData;
    Process logcatProcess;
    BufferedReader bufferedReader;

    public LogcatWrapper(List<String> command, LogcatData logcatData) throws IOException {
        super();
        this.logcatData = logcatData;
        logcatProcess = new ProcessBuilder().command(command).start();

    }


    @Override
    public void run() {

        Log.d("AAA", "Logcat wrapper is running");

        String line = "";
        bufferedReader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null)
                    break;

                logcatData.setDataFromLogcat(line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d("AAA", "Logcat wrapper completed");
    }

}
