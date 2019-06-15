package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class zzhg implements zzgi {
    private int zzach = 0;
    private ByteBuffer zzadm = zzabh;
    private int zzael = -1;
    private ByteBuffer zzaep = zzabh;
    private boolean zzaeq;
    private int zzzt = -1;

    public final int zzcp() {
        return 2;
    }

    public final boolean zzb(int i, int i2, int i3) throws zzgj {
        if (i3 != 3 && i3 != 2 && i3 != Integer.MIN_VALUE && i3 != 1073741824) {
            throw new zzgj(i, i2, i3);
        } else if (this.zzael == i && this.zzzt == i2 && this.zzach == i3) {
            return false;
        } else {
            this.zzael = i;
            this.zzzt = i2;
            this.zzach = i3;
            if (i3 == 2) {
                this.zzaep = zzabh;
            }
            return true;
        }
    }

    public final boolean isActive() {
        return (this.zzach == 0 || this.zzach == 2) ? false : true;
    }

    public final int zzco() {
        return this.zzzt;
    }

    public final void zzi(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        int i2 = this.zzach;
        if (i2 == Integer.MIN_VALUE) {
            i = (i / 3) << 1;
        } else if (i2 == 3) {
            i <<= 1;
        } else if (i2 != 1073741824) {
            throw new IllegalStateException();
        } else {
            i /= 2;
        }
        if (this.zzaep.capacity() < i) {
            this.zzaep = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.zzaep.clear();
        }
        i = this.zzach;
        if (i == Integer.MIN_VALUE) {
            while (position < limit) {
                this.zzaep.put(byteBuffer.get(position + 1));
                this.zzaep.put(byteBuffer.get(position + 2));
                position += 3;
            }
        } else if (i == 3) {
            while (position < limit) {
                this.zzaep.put((byte) 0);
                this.zzaep.put((byte) ((byteBuffer.get(position) & 255) - 128));
                position++;
            }
        } else if (i != 1073741824) {
            throw new IllegalStateException();
        } else {
            while (position < limit) {
                this.zzaep.put(byteBuffer.get(position + 2));
                this.zzaep.put(byteBuffer.get(position + 3));
                position += 4;
            }
        }
        byteBuffer.position(byteBuffer.limit());
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
        this.zzael = -1;
        this.zzzt = -1;
        this.zzach = 0;
    }
}
