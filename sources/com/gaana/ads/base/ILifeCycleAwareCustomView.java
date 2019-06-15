package com.gaana.ads.base;

import android.arch.lifecycle.d;

public interface ILifeCycleAwareCustomView extends d {
    void destroy();

    void wrap(Object obj);
}
