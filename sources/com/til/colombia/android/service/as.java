package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;

final class as implements OnClickListener {
    final /* synthetic */ ColombiaNativeAudioAdView a;

    as(ColombiaNativeAudioAdView colombiaNativeAudioAdView) {
        this.a = colombiaNativeAudioAdView;
    }

    public final void onClick(View view) {
        this.a.click();
    }
}
