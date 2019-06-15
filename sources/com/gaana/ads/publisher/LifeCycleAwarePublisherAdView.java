package com.gaana.ads.publisher;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.m;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class LifeCycleAwarePublisherAdView implements ILifeCycleAwareCustomView {
    private PublisherAdView publisherAdView;

    public void wrap(Object obj) {
        this.publisherAdView = (PublisherAdView) obj;
    }

    @m(a = Event.ON_PAUSE)
    private void onPause() {
        if (this.publisherAdView != null) {
            this.publisherAdView.pause();
        }
    }

    @m(a = Event.ON_RESUME)
    private void onResume() {
        if (this.publisherAdView != null) {
            this.publisherAdView.resume();
        }
    }

    @m(a = Event.ON_DESTROY)
    private void onDestroy() {
        if (this.publisherAdView != null) {
            this.publisherAdView.destroy();
            this.publisherAdView = null;
        }
    }

    public void destroy() {
        onDestroy();
    }
}
