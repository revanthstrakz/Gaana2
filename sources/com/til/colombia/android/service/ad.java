package com.til.colombia.android.service;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.android.volley.i.b;
import com.til.colombia.android.commons.CmError;
import com.til.colombia.android.service.listener.a;

final class ad implements b<byte[]> {
    final /* synthetic */ a a;
    final /* synthetic */ CmManager b;

    ad(CmManager cmManager, a aVar) {
        this.b = cmManager;
        this.a = aVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        byte[] bArr = (byte[]) obj;
        if (this.a != null) {
            if (bArr == null) {
                this.a.onFeedFailed(CmError.WRONG_FORMAT_ERROR);
            } else if (VERSION.SDK_INT >= 11) {
                new a(this.a, bArr).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                new a(this.a, bArr).execute(new Void[0]);
            }
        }
    }

    private void a(byte[] bArr) {
        if (this.a != null) {
            if (bArr == null) {
                this.a.onFeedFailed(CmError.WRONG_FORMAT_ERROR);
            } else if (VERSION.SDK_INT >= 11) {
                new a(this.a, bArr).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                new a(this.a, bArr).execute(new Void[0]);
            }
        }
    }
}
