package com.moengage.pushbase.push;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class MoEPushCallBacks {
    private static MoEPushCallBacks _INSTANCE;
    private OnMoEPushClearedListener onMoEPushClearedListener;
    private OnMoEPushClickListener onMoEPushClickListener;
    private OnMoEPushNavigationAction onMoEPushNavigationAction;
    private OnMoEPushReceiveListener onMoEPushReceiveListener;

    public interface OnMoEPushClearedListener {
        void onPushCleared(Bundle bundle);
    }

    public interface OnMoEPushClickListener {
        void onPushClick(Bundle bundle);
    }

    public interface OnMoEPushNavigationAction {
        boolean onClick(@Nullable String str, @Nullable Bundle bundle, @Nullable Uri uri);
    }

    public interface OnMoEPushReceiveListener {
        void onPushReceived(Bundle bundle);
    }

    private MoEPushCallBacks() {
    }

    public static MoEPushCallBacks getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEPushCallBacks();
        }
        return _INSTANCE;
    }

    public void setOnMoEPushClickListener(OnMoEPushClickListener onMoEPushClickListener) {
        this.onMoEPushClickListener = onMoEPushClickListener;
    }

    public void setOnMoEPushReceiveListener(OnMoEPushReceiveListener onMoEPushReceiveListener) {
        this.onMoEPushReceiveListener = onMoEPushReceiveListener;
    }

    public void setOnMoEPushNavigationAction(OnMoEPushNavigationAction onMoEPushNavigationAction) {
        this.onMoEPushNavigationAction = onMoEPushNavigationAction;
    }

    public void setOnMoEPushClearedListener(OnMoEPushClearedListener onMoEPushClearedListener) {
        this.onMoEPushClearedListener = onMoEPushClearedListener;
    }

    public void onPushReceived(Bundle bundle) {
        if (this.onMoEPushReceiveListener != null) {
            this.onMoEPushReceiveListener.onPushReceived(bundle);
        }
    }

    public void onPushClicked(Bundle bundle) {
        if (this.onMoEPushClickListener != null) {
            this.onMoEPushClickListener.onPushClick(bundle);
        }
    }

    public boolean onPushNavigationAction(String str, Bundle bundle, Uri uri) {
        return this.onMoEPushNavigationAction != null ? this.onMoEPushNavigationAction.onClick(str, bundle, uri) : false;
    }

    public void onPushCleared(Bundle bundle) {
        if (this.onMoEPushClearedListener != null) {
            this.onMoEPushClearedListener.onPushCleared(bundle);
        }
    }
}
