package com.google.android.gms.internal.measurement;

public abstract class zzau extends zzat {
    private boolean zzvz;

    protected zzau(zzaw zzaw) {
        super(zzaw);
    }

    public abstract void zzag();

    public final boolean isInitialized() {
        return this.zzvz;
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzcl() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzq() {
        zzag();
        this.zzvz = true;
    }
}
