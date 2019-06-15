package com.facebook.accountkit;

import android.support.annotation.Nullable;
import java.util.Map;

public interface AccountPreferences {

    public interface OnDeletePreferenceListener {
        void onDeletePreference(String str, @Nullable AccountKitError accountKitError);
    }

    public interface OnLoadPreferenceListener {
        void onLoadPreference(String str, @Nullable String str2, @Nullable AccountKitError accountKitError);
    }

    public interface OnLoadPreferencesListener {
        void onLoadPreferences(@Nullable Map<String, String> map, @Nullable AccountKitError accountKitError);
    }

    public interface OnSetPreferenceListener {
        void onSetPreference(String str, String str2, @Nullable AccountKitError accountKitError);
    }

    void deletePreference(String str, OnDeletePreferenceListener onDeletePreferenceListener);

    void loadPreference(String str, OnLoadPreferenceListener onLoadPreferenceListener);

    void loadPreferences(OnLoadPreferencesListener onLoadPreferencesListener);

    void setPreference(String str, String str2, OnSetPreferenceListener onSetPreferenceListener);
}
