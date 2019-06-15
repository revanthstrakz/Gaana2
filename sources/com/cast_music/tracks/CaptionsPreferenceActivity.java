package com.cast_music.tracks;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.cast_music.VideoCastManager;
import com.cast_music.b.b;
import com.cast_music.b.d;
import com.gaana.R;

public class CaptionsPreferenceActivity extends PreferenceActivity {
    private static b a;
    private static final String b = b.a(CaptionsPreferenceActivity.class);

    static {
        b bVar = a;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        VideoCastManager y = VideoCastManager.y();
        if (!y.c(16)) {
            b.b(b, "Did you forget to enable FEATURE_CAPTIONS_PREFERENCE when you initialized the VideoCastManage?");
            finish();
        } else if (d.a) {
            startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            finish();
        } else {
            addPreferencesFromResource(R.xml.caption_preference);
            y.T().a(getPreferenceScreen());
        }
    }
}
