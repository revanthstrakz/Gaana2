package com.comscore.streaming;

import com.comscore.applications.ApplicationMeasurement;
import com.comscore.applications.EventType;
import java.util.HashMap;

class e implements Runnable {
    final /* synthetic */ HashMap a;
    final /* synthetic */ String b;
    final /* synthetic */ StreamSense c;

    e(StreamSense streamSense, HashMap hashMap, String str) {
        this.c = streamSense;
        this.a = hashMap;
        this.b = str;
    }

    public void run() {
        this.c.a.getMeasurementDispatcher().sendMeasurmement(ApplicationMeasurement.newApplicationMeasurement(this.c.a, EventType.HIDDEN, this.a, this.b), false);
    }
}
