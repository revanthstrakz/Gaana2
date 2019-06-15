package com.comscore.streaming;

import com.comscore.utils.CSLog;
import java.util.HashMap;

class d extends f {
    final /* synthetic */ StreamSenseState a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ StreamSense c;

    d(StreamSense streamSense, StreamSenseState streamSenseState, HashMap hashMap) {
        this.c = streamSense;
        this.a = streamSenseState;
        this.b = hashMap;
        super(streamSense, null);
    }

    public StreamSenseState getNextState() {
        return this.a;
    }

    public void run() {
        CSLog.d(this.c, "Performing delayed transition");
        this.c.a(this.a, this.b);
    }
}
