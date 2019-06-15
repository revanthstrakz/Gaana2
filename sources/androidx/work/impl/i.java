package androidx.work.impl;

import android.arch.lifecycle.LiveData;
import android.support.annotation.VisibleForTesting;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

class i {
    @VisibleForTesting
    final Set<LiveData> a = Collections.newSetFromMap(new IdentityHashMap());

    i() {
    }
}
