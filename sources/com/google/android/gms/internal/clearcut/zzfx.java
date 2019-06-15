package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzfx implements Cloneable {
    private Object value;
    private zzfv<?, ?> zzrp;
    private List<Object> zzrq = new ArrayList();

    zzfx() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzen()];
        zza(zzfs.zzg(bArr));
        return bArr;
    }

    private final zzfx zzeq() {
        zzfx zzfx = new zzfx();
        try {
            zzfx.zzrp = this.zzrp;
            if (this.zzrq == null) {
                zzfx.zzrq = null;
            } else {
                zzfx.zzrq.addAll(this.zzrq);
            }
            if (this.value != null) {
                Object obj;
                if (this.value instanceof zzfz) {
                    obj = (zzfz) ((zzfz) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    obj = ((byte[]) this.value).clone();
                } else {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length][];
                        zzfx.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        obj = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        obj = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        obj = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        obj = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        obj = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzfz[]) {
                        zzfz[] zzfzArr = (zzfz[]) this.value;
                        zzfz[] zzfzArr2 = new zzfz[zzfzArr.length];
                        zzfx.value = zzfzArr2;
                        while (i < zzfzArr.length) {
                            zzfzArr2[i] = (zzfz) zzfzArr[i].clone();
                            i++;
                        }
                    }
                }
                zzfx.value = obj;
                return zzfx;
            }
            return zzfx;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzeq();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfx)) {
            return false;
        }
        zzfx zzfx = (zzfx) obj;
        if (this.value != null && zzfx.value != null) {
            return this.zzrp != zzfx.zzrp ? false : !this.zzrp.zzrk.isArray() ? this.value.equals(zzfx.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzfx.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzfx.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzfx.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzfx.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzfx.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzfx.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzfx.value);
        } else {
            if (this.zzrq != null && zzfx.zzrq != null) {
                return this.zzrq.equals(zzfx.zzrq);
            }
            try {
                return Arrays.equals(toByteArray(), zzfx.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public final int hashCode() {
        try {
            return 527 + Arrays.hashCode(toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzfs zzfs) throws IOException {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator it = this.zzrq.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    /* Access modifiers changed, original: final */
    public final int zzen() {
        if (this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator it = this.zzrq.iterator();
        if (!it.hasNext()) {
            return 0;
        }
        it.next();
        throw new NoSuchMethodError();
    }
}
