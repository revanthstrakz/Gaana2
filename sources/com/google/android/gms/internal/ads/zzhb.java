package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

final class zzhb implements zzgi {
    private ByteBuffer zzadm = zzabh;
    private int zzael = -1;
    private int[] zzaem;
    private boolean zzaen;
    private int[] zzaeo;
    private ByteBuffer zzaep = zzabh;
    private boolean zzaeq;
    private int zzzt = -1;

    public final int zzcp() {
        return 2;
    }

    public final void zzb(int[] iArr) {
        this.zzaem = iArr;
    }

    public final boolean zzb(int i, int i2, int i3) throws zzgj {
        int equals = Arrays.equals(this.zzaem, this.zzaeo) ^ 1;
        this.zzaeo = this.zzaem;
        if (this.zzaeo == null) {
            this.zzaen = false;
            return equals;
        } else if (i3 != 2) {
            throw new zzgj(i, i2, i3);
        } else if (equals == 0 && this.zzael == i && this.zzzt == i2) {
            return false;
        } else {
            this.zzael = i;
            this.zzzt = i2;
            this.zzaen = i2 != this.zzaeo.length;
            equals = 0;
            while (equals < this.zzaeo.length) {
                int i4 = this.zzaeo[equals];
                if (i4 >= i2) {
                    throw new zzgj(i, i2, i3);
                }
                this.zzaen = (i4 != equals ? 1 : 0) | this.zzaen;
                equals++;
            }
            return true;
        }
    }

    public final boolean isActive() {
        return this.zzaen;
    }

    public final int zzco() {
        return this.zzaeo == null ? this.zzzt : this.zzaeo.length;
    }

    public final void zzi(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int length = (((limit - position) / (this.zzzt * 2)) * this.zzaeo.length) << 1;
        if (this.zzaep.capacity() < length) {
            this.zzaep = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.zzaep.clear();
        }
        while (position < limit) {
            for (int i : this.zzaeo) {
                this.zzaep.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.zzzt << 1;
        }
        byteBuffer.position(limit);
        this.zzaep.flip();
        this.zzadm = this.zzaep;
    }

    public final void zzcq() {
        this.zzaeq = true;
    }

    public final ByteBuffer zzcr() {
        ByteBuffer byteBuffer = this.zzadm;
        this.zzadm = zzabh;
        return byteBuffer;
    }

    public final boolean zzcj() {
        return this.zzaeq && this.zzadm == zzabh;
    }

    public final void flush() {
        this.zzadm = zzabh;
        this.zzaeq = false;
    }

    public final void reset() {
        flush();
        this.zzaep = zzabh;
        this.zzzt = -1;
        this.zzael = -1;
        this.zzaeo = null;
        this.zzaen = false;
    }
}
