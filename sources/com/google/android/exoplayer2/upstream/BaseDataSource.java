package com.google.android.exoplayer2.upstream;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Map;

public abstract class BaseDataSource implements DataSource {
    @Nullable
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners = new ArrayList(1);

    public Map getResponseHeaders() {
        return DataSource$$CC.getResponseHeaders(this);
    }

    protected BaseDataSource(boolean z) {
        this.isNetwork = z;
    }

    public final void addTransferListener(TransferListener transferListener) {
        if (!this.listeners.contains(transferListener)) {
            this.listeners.add(transferListener);
            this.listenerCount++;
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void transferInitializing(DataSpec dataSpec) {
        for (int i = 0; i < this.listenerCount; i++) {
            ((TransferListener) this.listeners.get(i)).onTransferInitializing(this, dataSpec, this.isNetwork);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void transferStarted(DataSpec dataSpec) {
        this.dataSpec = dataSpec;
        for (int i = 0; i < this.listenerCount; i++) {
            ((TransferListener) this.listeners.get(i)).onTransferStart(this, dataSpec, this.isNetwork);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void bytesTransferred(int i) {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i2 = 0; i2 < this.listenerCount; i2++) {
            ((TransferListener) this.listeners.get(i2)).onBytesTransferred(this, dataSpec, this.isNetwork, i);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void transferEnded() {
        DataSpec dataSpec = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i = 0; i < this.listenerCount; i++) {
            ((TransferListener) this.listeners.get(i)).onTransferEnd(this, dataSpec, this.isNetwork);
        }
        this.dataSpec = null;
    }
}
