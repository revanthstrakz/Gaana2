package com.gaana.localmedia;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public class LocalMediaContentObserver extends ContentObserver {
    private OnContentChanged onContentChanged = null;

    public interface OnContentChanged {
        void contentChanged();
    }

    public LocalMediaContentObserver(Handler handler) {
        super(handler);
    }

    public void onChange(boolean z) {
        onChange(z, null);
    }

    public void onChange(boolean z, Uri uri) {
        if (!z && uri != null && this.onContentChanged != null) {
            this.onContentChanged.contentChanged();
        }
    }

    public void setOnSearchCompleted(OnContentChanged onContentChanged) {
        this.onContentChanged = onContentChanged;
    }
}
