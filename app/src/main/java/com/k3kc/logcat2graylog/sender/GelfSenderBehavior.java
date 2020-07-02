package com.k3kc.logcat2graylog.sender;

import org.graylog2.gelfclient.GelfConfiguration;
import org.graylog2.gelfclient.GelfMessage;
import org.graylog2.gelfclient.GelfMessageBuilder;
import org.graylog2.gelfclient.GelfMessageLevel;
import org.graylog2.gelfclient.GelfTransports;
import org.graylog2.gelfclient.transport.GelfTransport;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class GelfSenderBehavior implements SenderBehavior {

    private GelfTransport transport;
    private GelfMessageBuilder builder;

    public GelfSenderBehavior() {
        GelfConfiguration config = new GelfConfiguration(new InetSocketAddress("syslog.kamensktel.ru", 12201))
                .transport(GelfTransports.UDP)
                .queueSize(512)
                .connectTimeout(5000)
                .reconnectDelay(1000)
                .tcpNoDelay(true)
                .sendBufferSize(32768);

        transport = GelfTransports.create(config);
        builder = new GelfMessageBuilder("").level(GelfMessageLevel.INFO);
    }

    public void send(ArrayList<String> data) {
        for(String line: data) {
            GelfMessage message = builder.message(line).build();
            transport.trySend(message);
        }
    }
}

