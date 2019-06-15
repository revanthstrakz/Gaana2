package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.commons.USER_ACTION;

final class ar implements OnClickListener {
    final /* synthetic */ ColombiaNativeAudioAdView a;

    ar(ColombiaNativeAudioAdView colombiaNativeAudioAdView) {
        this.a = colombiaNativeAudioAdView;
    }

    public final void onClick(View view) {
        this.a.destroy();
        this.a.finish(USER_ACTION.SKIPPED);
    }
}
