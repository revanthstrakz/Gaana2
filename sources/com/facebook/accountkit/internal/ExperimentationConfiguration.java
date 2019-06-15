package com.facebook.accountkit.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ExperimentationConfiguration {
    private static final String AK_PREFERENCES;
    private static final long DEFAULT_TTL = TimeUnit.DAYS.toMillis(3);
    private static final String PREFERENCE_PREFIX = TAG;
    private static final String PREF_CREATE_TIME;
    private static final String PREF_TTL;
    private static final String PREF_UNIT_ID;
    private static final String TAG = "ExperimentationConfiguration";
    private final SharedPreferences mSharedPrefs;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREFERENCE_PREFIX);
        stringBuilder.append(".AK_PREFERENCES");
        AK_PREFERENCES = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(PREFERENCE_PREFIX);
        stringBuilder.append(".PREF_CREATE_TIME");
        PREF_CREATE_TIME = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(PREFERENCE_PREFIX);
        stringBuilder.append(".PREF_TTL");
        PREF_TTL = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(PREFERENCE_PREFIX);
        stringBuilder.append(".PREF_UNIT_ID");
        PREF_UNIT_ID = stringBuilder.toString();
    }

    static void load(Context context, String str, Long l, @Nullable Long l2, Map<Integer, Integer> map) {
        if (str != null && l != null) {
            saveConfiguration(context, str, l.longValue(), l2, map);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private static void saveConfiguration(Context context, String str, long j, @Nullable Long l, Map<Integer, Integer> map) {
        Editor edit = getSharedPreferences(context).edit();
        edit.clear();
        edit.putLong(PREF_CREATE_TIME, j);
        if (l != null) {
            edit.putLong(PREF_TTL, l.longValue());
        }
        edit.putString(PREF_UNIT_ID, str);
        for (Integer num : map.keySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(PREFERENCE_PREFIX);
            stringBuilder.append(num);
            edit.putInt(stringBuilder.toString(), ((Integer) map.get(num)).intValue());
        }
        edit.commit();
    }

    ExperimentationConfiguration(Context context) {
        this.mSharedPrefs = getSharedPreferences(context);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(AK_PREFERENCES, 0);
    }

    public boolean exists() {
        return this.mSharedPrefs.getLong(PREF_CREATE_TIME, -1) > 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean isStale() {
        long time = Calendar.getInstance().getTime().getTime();
        return Math.abs(time - this.mSharedPrefs.getLong(PREF_CREATE_TIME, time)) > this.mSharedPrefs.getLong(PREF_TTL, DEFAULT_TTL);
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String getUnitID() {
        return this.mSharedPrefs.getString(PREF_UNIT_ID, null);
    }

    public int getIntValue(Feature feature) {
        SharedPreferences sharedPreferences = this.mSharedPrefs;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREFERENCE_PREFIX);
        stringBuilder.append(feature.getPrefKey());
        return sharedPreferences.getInt(stringBuilder.toString(), feature.getDefaultValue());
    }

    public boolean getBooleanValue(Feature feature) {
        return getIntValue(feature) > 0;
    }
}
