package com.utilities;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import com.player_framework.MediaButtonIntentReceiver;

public class d {
    public static final boolean a() {
        return VERSION.SDK_INT >= 11;
    }

    public static final boolean b() {
        return VERSION.SDK_INT >= 19;
    }

    public static final boolean c() {
        return VERSION.SDK_INT >= 13;
    }

    public static final boolean d() {
        return VERSION.SDK_INT >= 26;
    }

    public static final boolean e() {
        return VERSION.SDK_INT <= 27;
    }

    public static final boolean f() {
        return VERSION.SDK_INT >= 24;
    }

    public static final boolean g() {
        return VERSION.SDK_INT >= 16;
    }

    public static final boolean h() {
        return VERSION.SDK_INT >= 18;
    }

    public static final boolean i() {
        return VERSION.SDK_INT >= 23;
    }

    public static void a(Context context) {
        ((AudioManager) context.getSystemService("audio")).registerMediaButtonEventReceiver(new ComponentName(context, MediaButtonIntentReceiver.class));
    }

    public static boolean j() {
        return VERSION.SDK_INT >= 21;
    }

    public static String a(int i) {
        if (i >= 0 && i <= 10) {
            return "0-10 seconds of the video";
        }
        if (i >= 11 && i <= 20) {
            return "11-20 seconds of the video";
        }
        if (i >= 21 && i <= 30) {
            return "21-30 seconds of the vide0";
        }
        if (i >= 31) {
            return "30 seconds of video";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" seconds of video");
        return stringBuilder.toString();
    }
}
