package com.comscore.streaming;

public enum StreamSenseState {
    IDLE("idle", 0, StreamSenseEventType.END),
    PLAYING("playing", 1, StreamSenseEventType.PLAY),
    PAUSED(InAppConstants.INAPP_CAMPAIGN_STATUS_PAUSED, 2, StreamSenseEventType.PAUSE),
    BUFFERING("buffering", 3, StreamSenseEventType.BUFFER);
    
    private String a;
    private int b;
    private StreamSenseEventType c;

    private StreamSenseState(String str, int i, StreamSenseEventType streamSenseEventType) {
        this.a = str;
        this.b = i;
        this.c = streamSenseEventType;
    }

    public int getCode() {
        return this.b;
    }

    public String getName() {
        return this.a;
    }

    public StreamSenseEventType toEventType() {
        return this.c;
    }

    public String toString() {
        return getName();
    }
}
