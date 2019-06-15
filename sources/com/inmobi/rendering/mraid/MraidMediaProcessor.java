package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import com.inmobi.rendering.RenderView;

@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    private static final String f = "MraidMediaProcessor";
    public RenderView a;
    public MediaRenderView b;
    public RingerModeChangeReceiver c;
    public a d;
    public HeadphonesPluggedChangeReceiver e;

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        private String b;

        public HeadphonesPluggedChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                boolean z = false;
                int intExtra = intent.getIntExtra("state", 0);
                MraidMediaProcessor.f;
                MraidMediaProcessor mraidMediaProcessor = MraidMediaProcessor.this;
                String str = this.b;
                if (1 == intExtra) {
                    z = true;
                }
                MraidMediaProcessor.b(mraidMediaProcessor, str, z);
            }
        }
    }

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        private String b;

        public RingerModeChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
                MraidMediaProcessor.f;
                MraidMediaProcessor.a(MraidMediaProcessor.this, this.b, 2 != intExtra);
            }
        }
    }

    public final class a extends ContentObserver {
        private Context b;
        private int c = -1;
        private String d;

        public a(String str, Context context, Handler handler) {
            super(handler);
            this.d = str;
            this.b = context;
        }

        public final void onChange(boolean z) {
            super.onChange(z);
            if (this.b != null) {
                int streamVolume = ((AudioManager) this.b.getSystemService("audio")).getStreamVolume(3);
                if (streamVolume != this.c) {
                    this.c = streamVolume;
                    MraidMediaProcessor.a(MraidMediaProcessor.this, this.d, streamVolume);
                }
            }
        }
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.a = renderView;
    }

    public static boolean a() {
        Context b = com.inmobi.commons.a.a.b();
        if (b == null || 2 == ((AudioManager) b.getSystemService("audio")).getRingerMode()) {
            return false;
        }
        return true;
    }

    public final void b() {
        Context b = com.inmobi.commons.a.a.b();
        if (!(b == null || this.c == null)) {
            b.unregisterReceiver(this.c);
            this.c = null;
        }
    }

    public final void c() {
        Context b = com.inmobi.commons.a.a.b();
        if (!(b == null || this.d == null)) {
            b.getContentResolver().unregisterContentObserver(this.d);
            this.d = null;
        }
    }

    public static boolean d() {
        Context b = com.inmobi.commons.a.a.b();
        if (b == null) {
            return false;
        }
        return ((AudioManager) b.getSystemService("audio")).isWiredHeadsetOn();
    }

    public final void e() {
        Context b = com.inmobi.commons.a.a.b();
        if (!(b == null || this.e == null)) {
            b.unregisterReceiver(this.e);
            this.e = null;
        }
    }

    static /* synthetic */ void b(MraidMediaProcessor mraidMediaProcessor, String str, boolean z) {
        if (mraidMediaProcessor.a != null) {
            RenderView renderView = mraidMediaProcessor.a;
            StringBuilder stringBuilder = new StringBuilder("fireHeadphonePluggedEvent(");
            stringBuilder.append(z);
            stringBuilder.append(");");
            renderView.a(str, stringBuilder.toString());
        }
    }
}
