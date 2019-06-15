package com.f;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.AudioEffect.Descriptor;
import android.media.audiofx.Equalizer;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.gaana.R;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.player_framework.GaanaMusicService;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.services.d;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class c implements a, m {
    private static c a;
    private static final UUID b;
    private Equalizer c;
    private short d;

    public void onAdEventUpdate(f fVar, AdEvent adEvent) {
    }

    public void onBufferingUpdate(f fVar, int i) {
    }

    public void onCompletion(f fVar) {
    }

    public void onError(f fVar, int i, int i2) {
    }

    public void onInfo(f fVar, int i, int i2) {
    }

    static {
        if (VERSION.SDK_INT >= 18) {
            b = AudioEffect.EFFECT_TYPE_EQUALIZER;
        } else {
            b = UUID.fromString("0bed4300-ddd6-11db-8f34-0002a5d5c51b");
        }
    }

    public static c a() {
        if (a == null) {
            a = new c();
            a.d = a.b(c());
        }
        return a;
    }

    private c() {
        o.a("LISTENER_KEY_EQUALIZER", (m) this);
        o.a("LISTENER_KEY_EQUALIZER", (a) this);
    }

    private Equalizer e() {
        if (this.c == null) {
            this.c = new Equalizer(0, GaanaMusicService.s().getAudioSessionId());
            this.c.setEnabled(true);
        }
        return this.c;
    }

    private void f() {
        if (this.c != null) {
            this.c.setEnabled(false);
            this.c.release();
            this.c = null;
        }
    }

    public void a(@Nullable String str) {
        short b = b(str);
        if (b != this.d) {
            a(b, "setEqualizerPresetIfNotAlreadySet()");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(short s, String str) {
        if (s >= (short) 0) {
            try {
                f();
                Equalizer e = e();
                this.d = s;
                e.usePreset(s);
                short numberOfBands = e.getNumberOfBands();
                short s2 = e.getBandLevelRange()[0];
                for (short s3 = (short) 0; s3 < numberOfBands; s3 = (short) (s3 + 1)) {
                    e.setBandLevel(s3, (short) (e.getBandLevel(s3) - s2));
                }
                d.a().a("PREFERENCE_EQUALIZER_SELECTED_TYPE", e().getPresetName(s), false);
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        f();
        this.d = (short) -1;
        d.a().a("PREFERENCE_EQUALIZER_SELECTED_TYPE", "", false);
    }

    private short b(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            Equalizer e = e();
            short numberOfPresets = e.getNumberOfPresets();
            for (short s = (short) 0; s < numberOfPresets; s++) {
                short s2 = (short) s;
                if (str.equalsIgnoreCase(e.getPresetName(s2))) {
                    return s2;
                }
            }
        }
        return (short) -1;
    }

    private HashMap<String, Integer> g() {
        HashMap hashMap = new HashMap();
        hashMap.put("Rock", Integer.valueOf(0));
        hashMap.put("Dance", Integer.valueOf(1));
        hashMap.put("Heavy Metal", Integer.valueOf(2));
        hashMap.put("Folk", Integer.valueOf(3));
        hashMap.put("Jazz", Integer.valueOf(4));
        hashMap.put("Pop", Integer.valueOf(5));
        hashMap.put("Classical", Integer.valueOf(6));
        hashMap.put("Hip Hop", Integer.valueOf(7));
        hashMap.put("Flat", Integer.valueOf(8));
        return hashMap;
    }

    public int a(List<d> list, @Nullable String str) {
        HashMap g = g();
        Equalizer e = e();
        short numberOfPresets = e.getNumberOfPresets();
        int i = -1;
        for (short s = (short) 0; s < numberOfPresets; s++) {
            String presetName = e.getPresetName((short) s);
            if (g.containsKey(presetName)) {
                boolean equalsIgnoreCase = presetName.equalsIgnoreCase(str);
                list.add(new d(s, presetName, equalsIgnoreCase, ((Integer) g.get(presetName)).intValue()));
                if (equalsIgnoreCase) {
                    i = list.size() - 1;
                }
            }
        }
        return i;
    }

    public static int a(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_bkg_unselected});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int b(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_bkg_selected});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int c(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_name_unselected});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int d(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_name_selected});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int[] e(Context context) {
        int[] iArr = new int[]{r4.getResourceId(0, 0), r4.getResourceId(1, 0), r4.getResourceId(2, 0), r4.getResourceId(3, 0), r4.getResourceId(4, 0), r4.getResourceId(5, 0), r4.getResourceId(6, 0), r4.getResourceId(7, 0), context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_rock_unselected, R.attr.equalizer_list_item_dance_unselected, R.attr.equalizer_list_item_heavy_metal_unselected, R.attr.equalizer_list_item_folk_unselected, R.attr.equalizer_list_item_jazz_unselected, R.attr.equalizer_list_item_pop_unselected, R.attr.equalizer_list_item_classical_unselected, R.attr.equalizer_list_item_hip_hop_unselected, R.attr.equalizer_list_item_flat_unselected}).getResourceId(8, 0)};
        context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_rock_unselected, R.attr.equalizer_list_item_dance_unselected, R.attr.equalizer_list_item_heavy_metal_unselected, R.attr.equalizer_list_item_folk_unselected, R.attr.equalizer_list_item_jazz_unselected, R.attr.equalizer_list_item_pop_unselected, R.attr.equalizer_list_item_classical_unselected, R.attr.equalizer_list_item_hip_hop_unselected, R.attr.equalizer_list_item_flat_unselected}).recycle();
        return iArr;
    }

    public static int[] f(Context context) {
        int[] iArr = new int[]{r4.getResourceId(0, 0), r4.getResourceId(1, 0), r4.getResourceId(2, 0), r4.getResourceId(3, 0), r4.getResourceId(4, 0), r4.getResourceId(5, 0), r4.getResourceId(6, 0), r4.getResourceId(7, 0), context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_rock_selected, R.attr.equalizer_list_item_dance_selected, R.attr.equalizer_list_item_heavy_metal_selected, R.attr.equalizer_list_item_folk_selected, R.attr.equalizer_list_item_jazz_selected, R.attr.equalizer_list_item_pop_selected, R.attr.equalizer_list_item_classical_selected, R.attr.equalizer_list_item_hip_hop_selected, R.attr.equalizer_list_item_flat_selected}).getResourceId(8, 0)};
        context.obtainStyledAttributes(new int[]{R.attr.equalizer_list_item_rock_selected, R.attr.equalizer_list_item_dance_selected, R.attr.equalizer_list_item_heavy_metal_selected, R.attr.equalizer_list_item_folk_selected, R.attr.equalizer_list_item_jazz_selected, R.attr.equalizer_list_item_pop_selected, R.attr.equalizer_list_item_classical_selected, R.attr.equalizer_list_item_hip_hop_selected, R.attr.equalizer_list_item_flat_selected}).recycle();
        return iArr;
    }

    public static String c() {
        return d.a().c("PREFERENCE_EQUALIZER_SELECTED_TYPE", false);
    }

    public static boolean d() {
        return h() && i();
    }

    private static boolean h() {
        for (Descriptor descriptor : AudioEffect.queryEffects()) {
            if (b != null && b.equals(descriptor.type)) {
                return true;
            }
        }
        return false;
    }

    private static boolean i() {
        try {
            new Equalizer(0, GaanaMusicService.s().getAudioSessionId()).setEnabled(true);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public void a(boolean z) {
        if (z && this.d != (short) -1) {
            a(this.d, "onAudioSessionIDCreated()");
        }
    }

    public void onPrepared(f fVar) {
        if (this.d != (short) -1) {
            a(this.d, "onPrepared()");
        }
    }
}
