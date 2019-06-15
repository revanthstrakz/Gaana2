package com.til.colombia.android.service;

import android.util.Log;
import com.til.colombia.android.internal.a.g.a;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.ErrorCode;

final class l implements a {
    final /* synthetic */ AdRequestResponse a;

    l(AdRequestResponse adRequestResponse) {
        this.a = adRequestResponse;
    }

    public final void a(boolean z) {
        String str = i.f;
        StringBuilder stringBuilder = new StringBuilder("Media files download: ");
        stringBuilder.append(z);
        Log.i(str, stringBuilder.toString());
        if (z) {
            b.a(this.a.colombiaAdRequest, this.a.adListener, this.a.response);
        } else {
            this.a.onItemRequestFailedOnMainThread(new Exception(ErrorCode.MEDIA_DOWNLOAD_ERROR.toString()));
        }
    }
}
