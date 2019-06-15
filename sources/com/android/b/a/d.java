package com.android.b.a;

import android.os.Bundle;

public class d {
    private final Bundle a;

    public d(Bundle bundle) {
        this.a = bundle;
    }

    public String a() {
        return this.a.getString("install_referrer");
    }

    public long b() {
        return this.a.getLong("referrer_click_timestamp_seconds");
    }

    public long c() {
        return this.a.getLong("install_begin_timestamp_seconds");
    }
}
