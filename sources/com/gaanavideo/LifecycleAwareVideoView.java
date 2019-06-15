package com.gaanavideo;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.m;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.gaana.ads.base.ILifeCycleAwareCustomView;

public class LifecycleAwareVideoView implements ILifeCycleAwareCustomView {
    private VideoPlayerAutoPlayView a;

    public void wrap(Object obj) {
        this.a = (VideoPlayerAutoPlayView) obj;
    }

    @m(a = Event.ON_PAUSE)
    private void onPause() {
        if (this.a != null) {
            this.a.e();
        }
    }

    @m(a = Event.ON_STOP)
    private void onStop() {
        if (this.a != null) {
            this.a.i();
        }
    }

    @m(a = Event.ON_DESTROY)
    private void onDestroy() {
        if (this.a != null) {
            this.a.i();
            this.a = null;
        }
    }

    public void destroy() {
        onDestroy();
    }
}
