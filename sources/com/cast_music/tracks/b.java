package com.cast_music.tracks;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import com.cast_music.VideoCastManager;
import com.cast_music.b.c;
import com.cast_music.b.d;
import com.gaana.R;
import com.google.android.gms.cast.TextTrackStyle;
import java.util.HashMap;
import java.util.Map;

public class b implements OnSharedPreferenceChangeListener {
    private static final String a = com.cast_music.b.b.a(b.class);
    private static final Map<String, String> e = new HashMap();
    private static final Map<String, Integer> f = new HashMap();
    private static final Map<String, Integer> g = new HashMap();
    private final Context b;
    private final SharedPreferences c;
    private final c d;
    private ListPreference h;
    private ListPreference i;
    private ListPreference j;
    private ListPreference k;
    private ListPreference l;
    private ListPreference m;
    private ListPreference n;
    private CheckBoxPreference o;
    private boolean p = false;

    static {
        e.put("FF", "100");
        e.put("BF", "75");
        e.put("80", "50");
        e.put("3F", "25");
        f.put("FONT_FAMILY_SANS_SERIF", Integer.valueOf(0));
        f.put("FONT_FAMILY_SERIF", Integer.valueOf(2));
        f.put("FONT_FAMILY_MONOSPACED_SANS_SERIF", Integer.valueOf(1));
        g.put("EDGE_TYPE_NONE", Integer.valueOf(0));
        g.put("EDGE_TYPE_OUTLINE", Integer.valueOf(1));
        g.put("EDGE_TYPE_DROP_SHADOW", Integer.valueOf(2));
    }

    public b(Context context) {
        this.b = context;
        this.c = PreferenceManager.getDefaultSharedPreferences(this.b);
        this.c.registerOnSharedPreferenceChangeListener(this);
        this.d = VideoCastManager.y().u();
    }

    public TextTrackStyle a() {
        TextTrackStyle fromSystemSettings = TextTrackStyle.fromSystemSettings(this.b);
        if (d.a) {
            return fromSystemSettings;
        }
        fromSystemSettings.setFontGenericFamily(((Integer) f.get(b())).intValue());
        fromSystemSettings.setBackgroundColor(Color.parseColor(g()));
        fromSystemSettings.setEdgeType(((Integer) g.get(f())).intValue());
        fromSystemSettings.setFontScale(c());
        boolean isBold = Typeface.DEFAULT.isBold();
        boolean isItalic = Typeface.DEFAULT.isItalic();
        int i = 0;
        if (isBold && isItalic) {
            i = 3;
        } else if ((isBold || isItalic) && isBold) {
            i = 1;
        }
        fromSystemSettings.setFontStyle(i);
        fromSystemSettings.setForegroundColor(a(d(), e()));
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Edge is: ");
        stringBuilder.append(f());
        com.cast_music.b.b.a(str, stringBuilder.toString());
        fromSystemSettings.setBackgroundColor(a(g(), h()));
        return fromSystemSettings;
    }

    public String b() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_font_family), "FONT_FAMILY_SANS_SERIF");
    }

    public float c() {
        return Float.parseFloat(this.d.b(this.b.getString(R.string.ccl_key_caption_font_scale), String.valueOf(1.0f)));
    }

    public String d() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_text_color), this.b.getString(R.string.ccl_prefs_caption_text_color_value_default));
    }

    public String e() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_text_opacity), this.b.getString(R.string.ccl_prefs_caption_text_opacity_value_default));
    }

    public String f() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_edge_type), "EDGE_TYPE_NONE");
    }

    public String g() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_background_color), this.b.getString(R.string.ccl_prefs_caption_background_color_value_default));
    }

    public String h() {
        return this.d.b(this.b.getString(R.string.ccl_key_caption_background_opacity), this.b.getString(R.string.ccl_prefs_caption_background_opacity_value_default));
    }

    public void a(PreferenceScreen preferenceScreen) {
        this.o = (CheckBoxPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_enabled));
        this.h = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_font_scale));
        this.i = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_font_family));
        this.j = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_text_color));
        this.k = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_text_opacity));
        this.l = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_edge_type));
        this.m = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_background_color));
        this.n = (ListPreference) preferenceScreen.findPreference(this.b.getString(R.string.ccl_key_caption_background_opacity));
        this.p = true;
        a(this.c, this.b.getString(R.string.ccl_key_caption_enabled), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_font_family), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_font_scale), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_text_color), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_text_opacity), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_edge_type), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_background_color), false);
        a(this.c, this.b.getString(R.string.ccl_key_caption_background_opacity), false);
    }

    private void a(boolean z) {
        this.h.setEnabled(z);
        this.i.setEnabled(z);
        this.j.setEnabled(z);
        this.k.setEnabled(z);
        this.l.setEnabled(z);
        this.m.setEnabled(z);
        this.n.setEnabled(z);
    }

    private String a(SharedPreferences sharedPreferences, int i, int i2, int i3, int i4) {
        Resources resources = this.b.getResources();
        String string = sharedPreferences.getString(resources.getString(i), resources.getString(i2));
        String[] stringArray = resources.getStringArray(i3);
        String[] stringArray2 = resources.getStringArray(i4);
        for (i3 = 0; i3 < stringArray2.length; i3++) {
            if (stringArray2[i3].equals(string)) {
                return stringArray[i3];
            }
        }
        return "";
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        a(sharedPreferences, str, true);
    }

    public void a(SharedPreferences sharedPreferences, String str, boolean z) {
        if (!this.p) {
            return;
        }
        if (this.b.getString(R.string.ccl_key_caption_enabled).equals(str)) {
            this.o.setSummary(this.o.isChecked() ? R.string.ccl_prefs_caption_enabled : R.string.ccl_prefs_caption_disabled);
            a(this.o.isChecked());
            if (z) {
                VideoCastManager.y().c(this.o.isChecked());
            }
            return;
        }
        String b;
        ListPreference listPreference;
        StringBuilder stringBuilder;
        if (this.b.getString(R.string.ccl_key_caption_font_scale).equals(str)) {
            this.h.setSummary(a(sharedPreferences, R.string.ccl_key_caption_font_scale, R.string.ccl_prefs_caption_font_scale_value_default, R.array.ccl_prefs_caption_font_scale_names, R.array.ccl_prefs_caption_font_scale_values));
        } else if (this.b.getString(R.string.ccl_key_caption_font_family).equals(str)) {
            this.i.setSummary(a(sharedPreferences, R.string.ccl_key_caption_font_family, R.string.ccl_prefs_caption_font_family_value_default, R.array.ccl_prefs_caption_font_family_names, R.array.ccl_prefs_caption_font_family_values));
        } else if (this.b.getString(R.string.ccl_key_caption_text_color).equals(str)) {
            this.j.setSummary(a(sharedPreferences, R.string.ccl_key_caption_text_color, R.string.ccl_prefs_caption_text_color_value_default, R.array.ccl_prefs_caption_color_names, R.array.ccl_prefs_caption_color_values));
        } else if (this.b.getString(R.string.ccl_key_caption_text_opacity).equals(str)) {
            b = this.d.b(this.b.getString(R.string.ccl_key_caption_text_opacity), this.b.getString(R.string.ccl_prefs_caption_text_opacity_value_default));
            listPreference = this.k;
            stringBuilder = new StringBuilder();
            stringBuilder.append((String) e.get(b));
            stringBuilder.append("%%");
            listPreference.setSummary(stringBuilder.toString());
        } else if (this.b.getString(R.string.ccl_key_caption_edge_type).equals(str)) {
            this.l.setSummary(a(sharedPreferences, R.string.ccl_key_caption_edge_type, R.string.ccl_prefs_caption_edge_type_value_default, R.array.ccl_prefs_caption_edge_type_names, R.array.ccl_prefs_caption_edge_type_values));
        } else if (this.b.getString(R.string.ccl_key_caption_background_color).equals(str)) {
            this.m.setSummary(a(sharedPreferences, R.string.ccl_key_caption_background_color, R.string.ccl_prefs_caption_background_color_value_default, R.array.ccl_prefs_caption_color_names, R.array.ccl_prefs_caption_color_values));
        } else if (this.b.getString(R.string.ccl_key_caption_background_opacity).equals(str)) {
            b = this.d.b(this.b.getString(R.string.ccl_key_caption_background_opacity), this.b.getString(R.string.ccl_prefs_caption_background_opacity_value_default));
            listPreference = this.n;
            stringBuilder = new StringBuilder();
            stringBuilder.append((String) e.get(b));
            stringBuilder.append("%%");
            listPreference.setSummary(stringBuilder.toString());
        }
        if (z) {
            VideoCastManager.y().b(a());
        }
    }

    private static int a(String str, String str2) {
        str = str.replace("#", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return Color.parseColor(stringBuilder.toString());
    }
}
