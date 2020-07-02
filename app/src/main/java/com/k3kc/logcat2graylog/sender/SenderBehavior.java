package com.k3kc.logcat2graylog.sender;

import java.util.ArrayList;

public interface SenderBehavior {
    void send(ArrayList<String> data);
}
