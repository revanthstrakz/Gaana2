package com.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.services.l.g;

public class FavoriteResultReceiver extends ResultReceiver {
    private g a;

    public FavoriteResultReceiver(Handler handler) {
        super(handler);
    }

    public void a(g gVar) {
        this.a = gVar;
    }

    /* Access modifiers changed, original: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        if (this.a != null) {
            this.a.favouriteSyncCompleted();
        }
    }
}
