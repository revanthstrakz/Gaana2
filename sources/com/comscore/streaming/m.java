package com.comscore.streaming;

import com.comscore.utils.CSLog;
import java.util.HashMap;
import java.util.TimerTask;

class m extends TimerTask {
    final /* synthetic */ long a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ StreamSenseVideoView c;

    m(StreamSenseVideoView streamSenseVideoView, long j, HashMap hashMap) {
        this.c = streamSenseVideoView;
        this.a = j;
        this.b = hashMap;
    }

    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startEventTimer -> lastPosition:");
        stringBuilder.append(this.a);
        stringBuilder.append(" currentPosition:");
        stringBuilder.append(this.c.getCurrentPlayerSafePosition());
        CSLog.d((Object) "StreamSenseVideoView", stringBuilder.toString());
        long parseLong = Long.parseLong((String) this.b.get("ns_ts"));
        long a = this.c.getCurrentPlayerSafePosition() - this.a;
        long currentTimeMillis = System.currentTimeMillis() - parseLong;
        if (a >= 500) {
            long j = currentTimeMillis - a;
            if (j > 0) {
                StreamSenseVideoView.a(this.c, j);
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("addToBufferingTotal=");
                stringBuilder2.append(j);
                CSLog.d((Object) "StreamSenseVideoView", stringBuilder2.toString());
                this.b.put("ns_ts", String.valueOf(parseLong + j));
            }
            this.b.put("ns_st_bt", String.valueOf(this.c.h));
            this.c.d();
            this.c.b(this.b, this.c.getCurrentPlayerSafePosition());
            this.c.g();
            return;
        }
        this.c.d();
        this.c.a(this.b);
    }
}
