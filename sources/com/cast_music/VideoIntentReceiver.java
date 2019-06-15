package com.cast_music;

import android.content.BroadcastReceiver;
import com.cast_music.b.b;

public class VideoIntentReceiver extends BroadcastReceiver {
    private static final String a = b.a(VideoIntentReceiver.class);

    /* JADX WARNING: Missing block: B:23:0x0054, code skipped:
            return;
     */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
        r4 = this;
        r5 = r6.getAction();
        if (r5 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = com.cast_music.VideoCastManager.y();
        r1 = -1;
        r2 = r5.hashCode();
        r3 = 1997055314; // 0x7708a552 float:2.7715057E33 double:9.866764235E-315;
        if (r2 == r3) goto L_0x0016;
    L_0x0015:
        goto L_0x001f;
    L_0x0016:
        r2 = "android.intent.action.MEDIA_BUTTON";
        r5 = r5.equals(r2);
        if (r5 == 0) goto L_0x001f;
    L_0x001e:
        r1 = 0;
    L_0x001f:
        if (r1 == 0) goto L_0x0022;
    L_0x0021:
        goto L_0x0053;
    L_0x0022:
        r5 = "android.intent.extra.KEY_EVENT";
        r5 = r6.hasExtra(r5);
        if (r5 != 0) goto L_0x002b;
    L_0x002a:
        return;
    L_0x002b:
        r5 = r6.getExtras();
        r6 = "android.intent.extra.KEY_EVENT";
        r5 = r5.get(r6);
        r5 = (android.view.KeyEvent) r5;
        if (r5 == 0) goto L_0x0054;
    L_0x0039:
        r6 = r5.getAction();
        if (r6 == 0) goto L_0x0040;
    L_0x003f:
        goto L_0x0054;
    L_0x0040:
        r5 = r5.getKeyCode();
        r6 = 85;
        if (r5 != r6) goto L_0x0053;
    L_0x0048:
        r0.N();	 Catch:{ CastException | NoConnectionException | TransientNetworkDisconnectionException -> 0x004c, CastException | NoConnectionException | TransientNetworkDisconnectionException -> 0x004c, CastException | NoConnectionException | TransientNetworkDisconnectionException -> 0x004c }
        goto L_0x0053;
    L_0x004c:
        r5 = a;
        r6 = "onReceive() Failed to toggle playback ";
        com.cast_music.b.b.b(r5, r6);
    L_0x0053:
        return;
    L_0x0054:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cast_music.VideoIntentReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
