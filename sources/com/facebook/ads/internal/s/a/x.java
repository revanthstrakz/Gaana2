package com.facebook.ads.internal.s.a;

import android.content.Context;
import android.media.AudioManager;
import java.util.Map;

public class x {
    public static float a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume > 0) {
                return (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
            }
        }
        return 0.0f;
    }

    public static void a(Map<String, String> map, boolean z, boolean z2) {
        map.put("autoplay", z ? "1" : "0");
        map.put("inline", z2 ? "1" : "0");
    }
}
