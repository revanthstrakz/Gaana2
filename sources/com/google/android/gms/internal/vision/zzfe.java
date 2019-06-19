package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzfe extends zzen {
    private static final Logger logger = Logger.getLogger(zzfe.class.getName());
    private static final boolean zzsr = zziu.zzhh();
    zzfg zzss = this;

    static class zza extends zzfe {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i + i2;
            if (((i | i2) | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i3;
        }

        public void flush() {
        }

        public final void zzd(int i, int i2) throws IOException {
            zzas((i << 3) | i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzd(i, 0);
            zze(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzd(i, 0);
            zzc((byte) z);
        }

        public final void zza(int i, String str) throws IOException {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzeo zzeo) throws IOException {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(zzeo zzeo) throws IOException {
            zzas(zzeo.size());
            zzeo.zza((zzen) this);
        }

        public final void zzf(byte[] bArr, int i, int i2) throws IOException {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zza(int i, zzhf zzhf) throws IOException {
            zzd(i, 2);
            zzb(zzhf);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzhf zzhf, zzhw zzhw) throws IOException {
            zzd(i, 2);
            zzec zzec = (zzec) zzhf;
            int zzcf = zzec.zzcf();
            if (zzcf == -1) {
                zzcf = zzhw.zzp(zzec);
                zzec.zzy(zzcf);
            }
            zzas(zzcf);
            zzhw.zza(zzhf, this.zzss);
        }

        public final void zzb(int i, zzhf zzhf) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, zzeo zzeo) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(zzhf zzhf) throws IOException {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzhf zzhf, zzhw zzhw) throws IOException {
            zzec zzec = (zzec) zzhf;
            int zzcf = zzec.zzcf();
            if (zzcf == -1) {
                zzcf = zzhw.zzp(zzec);
                zzec.zzy(zzcf);
            }
            zzas(zzcf);
            zzhw.zza(zzhf, this.zzss);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzar(int i) throws IOException {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!zzfe.zzsr || zzdz() < 10) {
                while ((i & -128) != 0) {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) i;
                    return;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            while ((i & -128) != 0) {
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                zziu.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zziu.zza(bArr, (long) i2, (byte) i);
        }

        public final void zzau(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 8);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) (i >> 16);
                bArr = this.buffer;
                i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = i >> 24;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zze(long j) throws IOException {
            byte[] bArr;
            int i;
            int i2;
            if (!zzfe.zzsr || zzdz() < 10) {
                while ((j & -128) != 0) {
                    bArr = this.buffer;
                    i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((int) j);
                    return;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            while ((j & -128) != 0) {
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                zziu.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zziu.zza(bArr, (long) i2, (byte) ((int) j));
        }

        public final void zzg(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 8));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 16));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 24));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 32));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 40));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 48));
                bArr = this.buffer;
                i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            int i = this.position;
            try {
                int zzax = zzfe.zzax(str.length() * 3);
                int zzax2 = zzfe.zzax(str.length());
                if (zzax2 == zzax) {
                    this.position = i + zzax2;
                    zzax = zziw.zza(str, this.buffer, this.position, zzdz());
                    this.position = i;
                    zzas((zzax - i) - zzax2);
                    this.position = zzax;
                    return;
                }
                zzas(zziw.zza(str));
                this.position = zziw.zza(str, this.buffer, this.position, zzdz());
            } catch (zzja e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        public final int zzdz() {
            return this.limit - this.position;
        }

        public final int zzec() {
            return this.position - this.offset;
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzst;
        private int zzsu;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzst = byteBuffer;
            this.zzsu = byteBuffer.position();
        }

        public final void flush() {
            this.zzst.position(this.zzsu + zzec());
        }
    }

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String str) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            str = String.valueOf(str);
            super(str.length() != 0 ? valueOf.concat(str) : new String(valueOf));
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzc(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            str = String.valueOf(str);
            super(str.length() != 0 ? valueOf.concat(str) : new String(valueOf), th);
        }
    }

    static final class zzd extends zzfe {
        private final int zzsu;
        private final ByteBuffer zzsv;
        private final ByteBuffer zzsw;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzsv = byteBuffer;
            this.zzsw = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzsu = byteBuffer.position();
        }

        public final void zzd(int i, int i2) throws IOException {
            zzas((i << 3) | i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzd(i, 0);
            zze(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzd(i, 0);
            zzc((byte) z);
        }

        public final void zza(int i, String str) throws IOException {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzeo zzeo) throws IOException {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(int i, zzhf zzhf) throws IOException {
            zzd(i, 2);
            zzb(zzhf);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzhf zzhf, zzhw zzhw) throws IOException {
            zzd(i, 2);
            zza(zzhf, zzhw);
        }

        public final void zzb(int i, zzhf zzhf) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, zzeo zzeo) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(zzhf zzhf) throws IOException {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzhf zzhf, zzhw zzhw) throws IOException {
            zzec zzec = (zzec) zzhf;
            int zzcf = zzec.zzcf();
            if (zzcf == -1) {
                zzcf = zzhw.zzp(zzec);
                zzec.zzy(zzcf);
            }
            zzas(zzcf);
            zzhw.zza(zzhf, this.zzss);
        }

        public final void zzc(byte b) throws IOException {
            try {
                this.zzsw.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zza(zzeo zzeo) throws IOException {
            zzas(zzeo.size());
            zzeo.zza((zzen) this);
        }

        public final void zzf(byte[] bArr, int i, int i2) throws IOException {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zzar(int i) throws IOException {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) throws IOException {
            while ((i & -128) != 0) {
                this.zzsw.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzsw.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzau(int i) throws IOException {
            try {
                this.zzsw.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zze(long j) throws IOException {
            while ((j & -128) != 0) {
                this.zzsw.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzsw.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzg(long j) throws IOException {
            try {
                this.zzsw.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzsw.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            } catch (BufferOverflowException e2) {
                throw new zzc(e2);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            int position = this.zzsw.position();
            try {
                int zzax = zzfe.zzax(str.length() * 3);
                int zzax2 = zzfe.zzax(str.length());
                if (zzax2 == zzax) {
                    zzax = this.zzsw.position() + zzax2;
                    this.zzsw.position(zzax);
                    zzo(str);
                    zzax2 = this.zzsw.position();
                    this.zzsw.position(position);
                    zzas(zzax2 - zzax);
                    this.zzsw.position(zzax2);
                    return;
                }
                zzas(zziw.zza(str));
                zzo(str);
            } catch (zzja e) {
                this.zzsw.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            }
        }

        public final void flush() {
            this.zzsv.position(this.zzsw.position());
        }

        public final int zzdz() {
            return this.zzsw.remaining();
        }

        private final void zzo(String str) throws IOException {
            try {
                zziw.zza(str, this.zzsw);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            }
        }
    }

    static final class zze extends zzfe {
        private final ByteBuffer zzsv;
        private final ByteBuffer zzsw;
        private final long zzsx;
        private final long zzsy;
        private final long zzsz;
        private final long zzta = (this.zzsz - 10);
        private long zztb = this.zzsy;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzsv = byteBuffer;
            this.zzsw = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzsx = zziu.zzb(byteBuffer);
            this.zzsy = this.zzsx + ((long) byteBuffer.position());
            this.zzsz = this.zzsx + ((long) byteBuffer.limit());
        }

        public final void zzd(int i, int i2) throws IOException {
            zzas((i << 3) | i2);
        }

        public final void zze(int i, int i2) throws IOException {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzd(i, 0);
            zze(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzd(i, 0);
            zzc((byte) z);
        }

        public final void zza(int i, String str) throws IOException {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzeo zzeo) throws IOException {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(int i, zzhf zzhf) throws IOException {
            zzd(i, 2);
            zzb(zzhf);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzhf zzhf, zzhw zzhw) throws IOException {
            zzd(i, 2);
            zza(zzhf, zzhw);
        }

        public final void zzb(int i, zzhf zzhf) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, zzeo zzeo) throws IOException {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(zzhf zzhf) throws IOException {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzhf zzhf, zzhw zzhw) throws IOException {
            zzec zzec = (zzec) zzhf;
            int zzcf = zzec.zzcf();
            if (zzcf == -1) {
                zzcf = zzhw.zzp(zzec);
                zzec.zzy(zzcf);
            }
            zzas(zzcf);
            zzhw.zza(zzhf, this.zzss);
        }

        public final void zzc(byte b) throws IOException {
            if (this.zztb >= this.zzsz) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zztb), Long.valueOf(this.zzsz), Integer.valueOf(1)}));
            }
            long j = this.zztb;
            this.zztb = j + 1;
            zziu.zza(j, b);
        }

        public final void zza(zzeo zzeo) throws IOException {
            zzas(zzeo.size());
            zzeo.zza((zzen) this);
        }

        public final void zzf(byte[] bArr, int i, int i2) throws IOException {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zzar(int i) throws IOException {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) throws IOException {
            long j;
            if (this.zztb <= this.zzta) {
                while ((i & -128) != 0) {
                    j = this.zztb;
                    this.zztb = j + 1;
                    zziu.zza(j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                j = this.zztb;
                this.zztb = j + 1;
                zziu.zza(j, (byte) i);
                return;
            }
            while (this.zztb < this.zzsz) {
                if ((i & -128) == 0) {
                    j = this.zztb;
                    this.zztb = j + 1;
                    zziu.zza(j, (byte) i);
                    return;
                }
                j = this.zztb;
                this.zztb = j + 1;
                zziu.zza(j, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zztb), Long.valueOf(this.zzsz), Integer.valueOf(1)}));
        }

        public final void zzau(int i) throws IOException {
            this.zzsw.putInt((int) (this.zztb - this.zzsx), i);
            this.zztb += 4;
        }

        public final void zze(long j) throws IOException {
            long j2;
            long j3;
            if (this.zztb <= this.zzta) {
                while ((j & -128) != 0) {
                    j2 = this.zztb;
                    this.zztb = j2 + 1;
                    zziu.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                j3 = this.zztb;
                this.zztb = j3 + 1;
                zziu.zza(j3, (byte) ((int) j));
                return;
            }
            while (this.zztb < this.zzsz) {
                if ((j & -128) == 0) {
                    j3 = this.zztb;
                    this.zztb = j3 + 1;
                    zziu.zza(j3, (byte) ((int) j));
                    return;
                }
                j2 = this.zztb;
                this.zztb = j2 + 1;
                zziu.zza(j2, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zztb), Long.valueOf(this.zzsz), Integer.valueOf(1)}));
        }

        public final void zzg(long j) throws IOException {
            this.zzsw.putLong((int) (this.zztb - this.zzsx), j);
            this.zztb += 8;
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                if (this.zzsz - j >= this.zztb) {
                    zziu.zza(bArr, (long) i, this.zztb, j);
                    this.zztb += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zztb), Long.valueOf(this.zzsz), Integer.valueOf(i2)}));
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            long j = this.zztb;
            try {
                int zzax = zzfe.zzax(str.length() * 3);
                int zzax2 = zzfe.zzax(str.length());
                if (zzax2 == zzax) {
                    zzax = ((int) (this.zztb - this.zzsx)) + zzax2;
                    this.zzsw.position(zzax);
                    zziw.zza(str, this.zzsw);
                    zzax2 = this.zzsw.position() - zzax;
                    zzas(zzax2);
                    this.zztb += (long) zzax2;
                    return;
                }
                zzax = zziw.zza(str);
                zzas(zzax);
                zzn(this.zztb);
                zziw.zza(str, this.zzsw);
                this.zztb += (long) zzax;
            } catch (zzja e) {
                this.zztb = j;
                zzn(this.zztb);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            } catch (IndexOutOfBoundsException e22) {
                throw new zzc(e22);
            }
        }

        public final void flush() {
            this.zzsv.position((int) (this.zztb - this.zzsx));
        }

        public final int zzdz() {
            return (int) (this.zzsz - this.zztb);
        }

        private final void zzn(long j) {
            this.zzsw.position((int) (j - this.zzsx));
        }
    }

    public static int zzax(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzaz(int i) {
        return 4;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzba(int i) {
        return 4;
    }

    private static int zzbc(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzg(float f) {
        return 4;
    }

    public static zzfe zzg(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzi(long j) {
        if ((j & -128) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        int i;
        if ((j & -34359738368L) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((j & -2097152) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & -16384) != 0) {
            i++;
        }
        return i;
    }

    public static int zzi(boolean z) {
        return 1;
    }

    public static int zzk(long j) {
        return 8;
    }

    public static int zzl(long j) {
        return 8;
    }

    private static long zzm(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzeo zzeo) throws IOException;

    public abstract void zza(int i, zzhf zzhf) throws IOException;

    public abstract void zza(int i, zzhf zzhf, zzhw zzhw) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzeo zzeo) throws IOException;

    public abstract void zza(zzhf zzhf, zzhw zzhw) throws IOException;

    public abstract void zzar(int i) throws IOException;

    public abstract void zzas(int i) throws IOException;

    public abstract void zzau(int i) throws IOException;

    public abstract void zzb(int i, zzeo zzeo) throws IOException;

    public abstract void zzb(int i, zzhf zzhf) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzhf zzhf) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract int zzdz();

    public abstract void zze(int i, int i2) throws IOException;

    public abstract void zze(long j) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzf(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzg(long j) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzm(String str) throws IOException;

    public static zzfe zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        } else if (zziu.zzhi()) {
            return new zze(byteBuffer);
        } else {
            return new zzd(byteBuffer);
        }
    }

    private zzfe() {
    }

    public final void zzg(int i, int i2) throws IOException {
        zzf(i, zzbc(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzm(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzat(int i) throws IOException {
        zzas(zzbc(i));
    }

    public final void zzf(long j) throws IOException {
        zze(zzm(j));
    }

    public final void zzf(float f) throws IOException {
        zzau(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzg(Double.doubleToRawLongBits(d));
    }

    public final void zzh(boolean z) throws IOException {
        zzc((byte) z);
    }

    public static int zzi(int i, int i2) {
        return zzav(i) + zzaw(i2);
    }

    public static int zzj(int i, int i2) {
        return zzav(i) + zzax(i2);
    }

    public static int zzk(int i, int i2) {
        return zzav(i) + zzax(zzbc(i2));
    }

    public static int zzl(int i, int i2) {
        return zzav(i) + 4;
    }

    public static int zzm(int i, int i2) {
        return zzav(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzav(i) + zzi(j);
    }

    public static int zze(int i, long j) {
        return zzav(i) + zzi(j);
    }

    public static int zzf(int i, long j) {
        return zzav(i) + zzi(zzm(j));
    }

    public static int zzg(int i, long j) {
        return zzav(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzav(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzav(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzav(i) + 8;
    }

    public static int zzc(int i, boolean z) {
        return zzav(i) + 1;
    }

    public static int zzn(int i, int i2) {
        return zzav(i) + zzaw(i2);
    }

    public static int zzb(int i, String str) {
        return zzav(i) + zzn(str);
    }

    public static int zzc(int i, zzeo zzeo) {
        i = zzav(i);
        int size = zzeo.size();
        return i + (zzax(size) + size);
    }

    public static int zza(int i, zzgm zzgm) {
        i = zzav(i);
        int zzeq = zzgm.zzeq();
        return i + (zzax(zzeq) + zzeq);
    }

    public static int zzc(int i, zzhf zzhf) {
        return zzav(i) + zzc(zzhf);
    }

    static int zzb(int i, zzhf zzhf, zzhw zzhw) {
        return zzav(i) + zzb(zzhf, zzhw);
    }

    public static int zzd(int i, zzhf zzhf) {
        return ((zzav(1) << 1) + zzj(2, i)) + zzc(3, zzhf);
    }

    public static int zzd(int i, zzeo zzeo) {
        return ((zzav(1) << 1) + zzj(2, i)) + zzc(3, zzeo);
    }

    public static int zzb(int i, zzgm zzgm) {
        return ((zzav(1) << 1) + zzj(2, i)) + zza(3, zzgm);
    }

    public static int zzav(int i) {
        return zzax(i << 3);
    }

    public static int zzaw(int i) {
        return i >= 0 ? zzax(i) : 10;
    }

    public static int zzay(int i) {
        return zzax(zzbc(i));
    }

    public static int zzh(long j) {
        return zzi(j);
    }

    public static int zzj(long j) {
        return zzi(zzm(j));
    }

    public static int zzbb(int i) {
        return zzaw(i);
    }

    public static int zzn(String str) {
        int zza;
        try {
            zza = zziw.zza(str);
        } catch (zzja unused) {
            zza = str.getBytes(zzga.UTF_8).length;
        }
        return zzax(zza) + zza;
    }

    public static int zza(zzgm zzgm) {
        int zzeq = zzgm.zzeq();
        return zzax(zzeq) + zzeq;
    }

    public static int zzb(zzeo zzeo) {
        int size = zzeo.size();
        return zzax(size) + size;
    }

    public static int zzh(byte[] bArr) {
        int length = bArr.length;
        return zzax(length) + length;
    }

    public static int zzc(zzhf zzhf) {
        int zzeq = zzhf.zzeq();
        return zzax(zzeq) + zzeq;
    }

    static int zzb(zzhf zzhf, zzhw zzhw) {
        zzec zzec = (zzec) zzhf;
        int zzcf = zzec.zzcf();
        if (zzcf == -1) {
            zzcf = zzhw.zzp(zzec);
            zzec.zzy(zzcf);
        }
        return zzax(zzcf) + zzcf;
    }

    public final void zzea() {
        if (zzdz() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(String str, zzja zzja) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzja);
        byte[] bytes = str.getBytes(zzga.UTF_8);
        try {
            zzas(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzhf zzhf, zzhw zzhw) {
        i = zzav(i) << 1;
        zzec zzec = (zzec) zzhf;
        int zzcf = zzec.zzcf();
        if (zzcf == -1) {
            zzcf = zzhw.zzp(zzec);
            zzec.zzy(zzcf);
        }
        return i + zzcf;
    }

    @Deprecated
    public static int zzd(zzhf zzhf) {
        return zzhf.zzeq();
    }

    @Deprecated
    public static int zzbd(int i) {
        return zzax(i);
    }

    /* synthetic */ zzfe(zzff zzff) {
        this();
    }
}
