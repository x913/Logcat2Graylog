package com.k3kc.logcat2graylog.logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LogcatWrapper extends Thread {

    Process logcatProcess;
    BufferedReader bufferedReader;

    public LogcatWrapper(List<String> command) throws IOException {
        super();
        logcatProcess = new ProcessBuilder().command(command).start();
        bufferedReader = new BufferedReader(new InputStreamReader(logcatProcess.getInputStream()));
    }


    @Override
    public void run() {
        super.run();
        String line = "";
        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null))
                    break;



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
