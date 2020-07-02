package com.k3kc.logcat2graylog.sender;

import java.util.ArrayList;

public class Sender {
    SenderBehavior senderBehavior;

    public Sender(SenderBehavior senderBehavior) {
        this.senderBehavior = senderBehavior;
    }

    public void send(ArrayList<String> data) {
        senderBehavior.send(data);
    };

}
