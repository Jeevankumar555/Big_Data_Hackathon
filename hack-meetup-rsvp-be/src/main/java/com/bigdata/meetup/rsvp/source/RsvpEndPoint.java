package com.bigdata.meetup.rsvp.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import javax.websocket.*;
import java.net.URI;


@ClientEndpoint
public class RsvpEndPoint {
    Session userSession = null;
    private MessageHandler messageHandler;
    private SourceFunction.SourceContext context;

    public RsvpEndPoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null)
            this.messageHandler.handleMessage(message);
    }


    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }


    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}