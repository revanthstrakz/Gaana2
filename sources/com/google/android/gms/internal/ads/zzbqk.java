package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbqk extends zzbpt {
    private static final Logger logger = Logger.getLogger(zzbqk.class.getName());
    private static final boolean zzfme = zzbua.zzapc();
    zzbqm zzfmf = this;

    static class zza extends zzbqk {
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

        public final void zzu(int i, int i2) throws IOException {
            zzfa((i << 3) | i2);
        }

        public final void zzv(int i, int i2) throws IOException {
            zzu(i, 0);
            zzez(i2);
        }

        public final void zzw(int i, int i2) throws IOException {
            zzu(i, 0);
            zzfa(i2);
        }

        public final void zzy(int i, int i2) throws IOException {
            zzu(i, 5);
            zzfc(i2);
        }

        public final void zzj(int i, long j) throws IOException {
            zzu(i, 0);
            zzay(j);
        }

        public final void zzl(int i, long j) throws IOException {
            zzu(i, 1);
            zzba(j);
        }

        public final void zzj(int i, boolean z) throws IOException {
            zzu(i, 0);
            zzd((byte) z);
        }

        public final void zzf(int i, String str) throws IOException {
            zzu(i, 2);
            zzfx(str);
        }

        public final void zza(int i, zzbpu zzbpu) throws IOException {
            zzu(i, 2);
            zzan(zzbpu);
        }

        public final void zzan(zzbpu zzbpu) throws IOException {
            zzfa(zzbpu.size());
            zzbpu.zza((zzbpt) this);
        }

        public final void zzl(byte[] bArr, int i, int i2) throws IOException {
            zzfa(i2);
            write(bArr, 0, i2);
        }

        public final void zza(int i, zzbsl zzbsl) throws IOException {
            zzu(i, 2);
            zze(zzbsl);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzu(i, 2);
            zzbpl zzbpl = (zzbpl) zzbsl;
            int zzakg = zzbpl.zzakg();
            if (zzakg == -1) {
                zzakg = zzbtc.zzac(zzbpl);
                zzbpl.zzei(zzakg);
            }
            zzfa(zzakg);
            zzbtc.zza(zzbsl, this.zzfmf);
        }

        public final void zzb(int i, zzbsl zzbsl) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbsl);
            zzu(1, 4);
        }

        public final void zzb(int i, zzbpu zzbpu) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbpu);
            zzu(1, 4);
        }

        public final void zze(zzbsl zzbsl) throws IOException {
            zzfa(zzbsl.zzamj());
            zzbsl.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzbpl zzbpl = (zzbpl) zzbsl;
            int zzakg = zzbpl.zzakg();
            if (zzakg == -1) {
                zzakg = zzbtc.zzac(zzbpl);
                zzbpl.zzei(zzakg);
            }
            zzfa(zzakg);
            zzbtc.zza(zzbsl, this.zzfmf);
        }

        public final void zzd(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzez(int i) throws IOException {
            if (i >= 0) {
                zzfa(i);
            } else {
                zzay((long) i);
            }
        }

        public final void zzfa(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!zzbqk.zzfme || zzalu() < 10) {
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
                zzbua.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzbua.zza(bArr, (long) i2, (byte) i);
        }

        public final void zzfc(int i) throws IOException {
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
                bArr[i2] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzay(long j) throws IOException {
            byte[] bArr;
            int i;
            int i2;
            if (!zzbqk.zzfme || zzalu() < 10) {
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
                zzbua.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzbua.zza(bArr, (long) i2, (byte) ((int) j));
        }

        public final void zzba(long j) throws IOException {
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

        public final void zzh(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzfx(String str) throws IOException {
            int i = this.position;
            try {
                int zzff = zzbqk.zzff(str.length() * 3);
                int zzff2 = zzbqk.zzff(str.length());
                if (zzff2 == zzff) {
                    this.position = i + zzff2;
                    zzff = zzbuc.zza(str, this.buffer, this.position, zzalu());
                    this.position = i;
                    zzfa((zzff - i) - zzff2);
                    this.position = zzff;
                    return;
                }
                zzfa(zzbuc.zza(str));
                this.position = zzbuc.zza(str, this.buffer, this.position, zzalu());
            } catch (zzbug e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        public final int zzalu() {
            return this.limit - this.position;
        }

        public final int zzalx() {
            return this.position - this.offset;
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzfmg;
        private int zzfmh;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzfmg = byteBuffer;
            this.zzfmh = byteBuffer.position();
        }

        public final void flush() {
            this.zzfmg.position(this.zzfmh + zzalx());
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

    static final class zzd extends zzbqk {
        private final ByteBuffer zzaep;
        private final int zzfmh;
        private final ByteBuffer zzfmi;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzfmi = byteBuffer;
            this.zzaep = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzfmh = byteBuffer.position();
        }

        public final void zzu(int i, int i2) throws IOException {
            zzfa((i << 3) | i2);
        }

        public final void zzv(int i, int i2) throws IOException {
            zzu(i, 0);
            zzez(i2);
        }

        public final void zzw(int i, int i2) throws IOException {
            zzu(i, 0);
            zzfa(i2);
        }

        public final void zzy(int i, int i2) throws IOException {
            zzu(i, 5);
            zzfc(i2);
        }

        public final void zzj(int i, long j) throws IOException {
            zzu(i, 0);
            zzay(j);
        }

        public final void zzl(int i, long j) throws IOException {
            zzu(i, 1);
            zzba(j);
        }

        public final void zzj(int i, boolean z) throws IOException {
            zzu(i, 0);
            zzd((byte) z);
        }

        public final void zzf(int i, String str) throws IOException {
            zzu(i, 2);
            zzfx(str);
        }

        public final void zza(int i, zzbpu zzbpu) throws IOException {
            zzu(i, 2);
            zzan(zzbpu);
        }

        public final void zza(int i, zzbsl zzbsl) throws IOException {
            zzu(i, 2);
            zze(zzbsl);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzu(i, 2);
            zza(zzbsl, zzbtc);
        }

        public final void zzb(int i, zzbsl zzbsl) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbsl);
            zzu(1, 4);
        }

        public final void zzb(int i, zzbpu zzbpu) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbpu);
            zzu(1, 4);
        }

        public final void zze(zzbsl zzbsl) throws IOException {
            zzfa(zzbsl.zzamj());
            zzbsl.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzbpl zzbpl = (zzbpl) zzbsl;
            int zzakg = zzbpl.zzakg();
            if (zzakg == -1) {
                zzakg = zzbtc.zzac(zzbpl);
                zzbpl.zzei(zzakg);
            }
            zzfa(zzakg);
            zzbtc.zza(zzbsl, this.zzfmf);
        }

        public final void zzd(byte b) throws IOException {
            try {
                this.zzaep.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzan(zzbpu zzbpu) throws IOException {
            zzfa(zzbpu.size());
            zzbpu.zza((zzbpt) this);
        }

        public final void zzl(byte[] bArr, int i, int i2) throws IOException {
            zzfa(i2);
            write(bArr, 0, i2);
        }

        public final void zzez(int i) throws IOException {
            if (i >= 0) {
                zzfa(i);
            } else {
                zzay((long) i);
            }
        }

        public final void zzfa(int i) throws IOException {
            while ((i & -128) != 0) {
                this.zzaep.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzaep.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzfc(int i) throws IOException {
            try {
                this.zzaep.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzay(long j) throws IOException {
            while ((j & -128) != 0) {
                this.zzaep.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzaep.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzba(long j) throws IOException {
            try {
                this.zzaep.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzaep.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            } catch (BufferOverflowException e2) {
                throw new zzc(e2);
            }
        }

        public final void zzh(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzfx(String str) throws IOException {
            int position = this.zzaep.position();
            try {
                int zzff = zzbqk.zzff(str.length() * 3);
                int zzff2 = zzbqk.zzff(str.length());
                if (zzff2 == zzff) {
                    zzff = this.zzaep.position() + zzff2;
                    this.zzaep.position(zzff);
                    zzfz(str);
                    zzff2 = this.zzaep.position();
                    this.zzaep.position(position);
                    zzfa(zzff2 - zzff);
                    this.zzaep.position(zzff2);
                    return;
                }
                zzfa(zzbuc.zza(str));
                zzfz(str);
            } catch (zzbug e) {
                this.zzaep.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            }
        }

        public final void flush() {
            this.zzfmi.position(this.zzaep.position());
        }

        public final int zzalu() {
            return this.zzaep.remaining();
        }

        private final void zzfz(String str) throws IOException {
            try {
                zzbuc.zza(str, this.zzaep);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            }
        }
    }

    static final class zze extends zzbqk {
        private final ByteBuffer zzaep;
        private long zzaha = this.zzfmk;
        private final ByteBuffer zzfmi;
        private final long zzfmj;
        private final long zzfmk;
        private final long zzfml;
        private final long zzfmm = (this.zzfml - 10);

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzfmi = byteBuffer;
            this.zzaep = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzfmj = zzbua.zzo(byteBuffer);
            this.zzfmk = this.zzfmj + ((long) byteBuffer.position());
            this.zzfml = this.zzfmj + ((long) byteBuffer.limit());
        }

        public final void zzu(int i, int i2) throws IOException {
            zzfa((i << 3) | i2);
        }

        public final void zzv(int i, int i2) throws IOException {
            zzu(i, 0);
            zzez(i2);
        }

        public final void zzw(int i, int i2) throws IOException {
            zzu(i, 0);
            zzfa(i2);
        }

        public final void zzy(int i, int i2) throws IOException {
            zzu(i, 5);
            zzfc(i2);
        }

        public final void zzj(int i, long j) throws IOException {
            zzu(i, 0);
            zzay(j);
        }

        public final void zzl(int i, long j) throws IOException {
            zzu(i, 1);
            zzba(j);
        }

        public final void zzj(int i, boolean z) throws IOException {
            zzu(i, 0);
            zzd((byte) z);
        }

        public final void zzf(int i, String str) throws IOException {
            zzu(i, 2);
            zzfx(str);
        }

        public final void zza(int i, zzbpu zzbpu) throws IOException {
            zzu(i, 2);
            zzan(zzbpu);
        }

        public final void zza(int i, zzbsl zzbsl) throws IOException {
            zzu(i, 2);
            zze(zzbsl);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzu(i, 2);
            zza(zzbsl, zzbtc);
        }

        public final void zzb(int i, zzbsl zzbsl) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbsl);
            zzu(1, 4);
        }

        public final void zzb(int i, zzbpu zzbpu) throws IOException {
            zzu(1, 3);
            zzw(2, i);
            zza(3, zzbpu);
            zzu(1, 4);
        }

        public final void zze(zzbsl zzbsl) throws IOException {
            zzfa(zzbsl.zzamj());
            zzbsl.zzb(this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzbsl zzbsl, zzbtc zzbtc) throws IOException {
            zzbpl zzbpl = (zzbpl) zzbsl;
            int zzakg = zzbpl.zzakg();
            if (zzakg == -1) {
                zzakg = zzbtc.zzac(zzbpl);
                zzbpl.zzei(zzakg);
            }
            zzfa(zzakg);
            zzbtc.zza(zzbsl, this.zzfmf);
        }

        public final void zzd(byte b) throws IOException {
            if (this.zzaha >= this.zzfml) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzaha), Long.valueOf(this.zzfml), Integer.valueOf(1)}));
            }
            long j = this.zzaha;
            this.zzaha = j + 1;
            zzbua.zza(j, b);
        }

        public final void zzan(zzbpu zzbpu) throws IOException {
            zzfa(zzbpu.size());
            zzbpu.zza((zzbpt) this);
        }

        public final void zzl(byte[] bArr, int i, int i2) throws IOException {
            zzfa(i2);
            write(bArr, 0, i2);
        }

        public final void zzez(int i) throws IOException {
            if (i >= 0) {
                zzfa(i);
            } else {
                zzay((long) i);
            }
        }

        public final void zzfa(int i) throws IOException {
            long j;
            if (this.zzaha <= this.zzfmm) {
                while ((i & -128) != 0) {
                    j = this.zzaha;
                    this.zzaha = j + 1;
                    zzbua.zza(j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                j = this.zzaha;
                this.zzaha = j + 1;
                zzbua.zza(j, (byte) i);
                return;
            }
            while (this.zzaha < this.zzfml) {
                if ((i & -128) == 0) {
                    j = this.zzaha;
                    this.zzaha = j + 1;
                    zzbua.zza(j, (byte) i);
                    return;
                }
                j = this.zzaha;
                this.zzaha = j + 1;
                zzbua.zza(j, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzaha), Long.valueOf(this.zzfml), Integer.valueOf(1)}));
        }

        public final void zzfc(int i) throws IOException {
            this.zzaep.putInt((int) (this.zzaha - this.zzfmj), i);
            this.zzaha += 4;
        }

        public final void zzay(long j) throws IOException {
            long j2;
            long j3;
            if (this.zzaha <= this.zzfmm) {
                while ((j & -128) != 0) {
                    j2 = this.zzaha;
                    this.zzaha = j2 + 1;
                    zzbua.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                j3 = this.zzaha;
                this.zzaha = j3 + 1;
                zzbua.zza(j3, (byte) ((int) j));
                return;
            }
            while (this.zzaha < this.zzfml) {
                if ((j & -128) == 0) {
                    j3 = this.zzaha;
                    this.zzaha = j3 + 1;
                    zzbua.zza(j3, (byte) ((int) j));
                    return;
                }
                j2 = this.zzaha;
                this.zzaha = j2 + 1;
                zzbua.zza(j2, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzaha), Long.valueOf(this.zzfml), Integer.valueOf(1)}));
        }

        public final void zzba(long j) throws IOException {
            this.zzaep.putLong((int) (this.zzaha - this.zzfmj), j);
            this.zzaha += 8;
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                if (this.zzfml - j >= this.zzaha) {
                    zzbua.zza(bArr, (long) i, this.zzaha, j);
                    this.zzaha += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzaha), Long.valueOf(this.zzfml), Integer.valueOf(i2)}));
        }

        public final void zzh(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzfx(String str) throws IOException {
            long j = this.zzaha;
            try {
                int zzff = zzbqk.zzff(str.length() * 3);
                int zzff2 = zzbqk.zzff(str.length());
                if (zzff2 == zzff) {
                    zzff = ((int) (this.zzaha - this.zzfmj)) + zzff2;
                    this.zzaep.position(zzff);
                    zzbuc.zza(str, this.zzaep);
                    zzff2 = this.zzaep.position() - zzff;
                    zzfa(zzff2);
                    this.zzaha += (long) zzff2;
                    return;
                }
                zzff = zzbuc.zza(str);
                zzfa(zzff);
                zzbh(this.zzaha);
                zzbuc.zza(str, this.zzaep);
                this.zzaha += (long) zzff;
            } catch (zzbug e) {
                this.zzaha = j;
                zzbh(this.zzaha);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            } catch (IndexOutOfBoundsException e22) {
                throw new zzc(e22);
            }
        }

        public final void flush() {
            this.zzfmi.position((int) (this.zzaha - this.zzfmj));
        }

        public final int zzalu() {
            return (int) (this.zzfml - this.zzaha);
        }

        private final void zzbh(long j) {
            this.zzaep.position((int) (j - this.zzfmj));
        }
    }

    public static int zzbc(long j) {
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

    public static int zzbe(long j) {
        return 8;
    }

    public static int zzbe(boolean z) {
        return 1;
    }

    public static int zzbf(long j) {
        return 8;
    }

    private static long zzbg(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzff(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzfh(int i) {
        return 4;
    }

    public static int zzfi(int i) {
        return 4;
    }

    private static int zzfk(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzg(float f) {
        return 4;
    }

    public static zzbqk zzt(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, zzbpu zzbpu) throws IOException;

    public abstract void zza(int i, zzbsl zzbsl) throws IOException;

    public abstract void zza(int i, zzbsl zzbsl, zzbtc zzbtc) throws IOException;

    public abstract void zza(zzbsl zzbsl, zzbtc zzbtc) throws IOException;

    public abstract int zzalu();

    public abstract void zzan(zzbpu zzbpu) throws IOException;

    public abstract void zzay(long j) throws IOException;

    public abstract void zzb(int i, zzbpu zzbpu) throws IOException;

    public abstract void zzb(int i, zzbsl zzbsl) throws IOException;

    public abstract void zzba(long j) throws IOException;

    public abstract void zzd(byte b) throws IOException;

    public abstract void zze(zzbsl zzbsl) throws IOException;

    public abstract void zzez(int i) throws IOException;

    public abstract void zzf(int i, String str) throws IOException;

    public abstract void zzfa(int i) throws IOException;

    public abstract void zzfc(int i) throws IOException;

    public abstract void zzfx(String str) throws IOException;

    public abstract void zzj(int i, long j) throws IOException;

    public abstract void zzj(int i, boolean z) throws IOException;

    public abstract void zzl(int i, long j) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzu(int i, int i2) throws IOException;

    public abstract void zzv(int i, int i2) throws IOException;

    public abstract void zzw(int i, int i2) throws IOException;

    public abstract void zzy(int i, int i2) throws IOException;

    public static zzbqk zzn(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        } else if (zzbua.zzapd()) {
            return new zze(byteBuffer);
        } else {
            return new zzd(byteBuffer);
        }
    }

    private zzbqk() {
    }

    public final void zzx(int i, int i2) throws IOException {
        zzw(i, zzfk(i2));
    }

    public final void zzk(int i, long j) throws IOException {
        zzj(i, zzbg(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzy(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(int i, double d) throws IOException {
        zzl(i, Double.doubleToRawLongBits(d));
    }

    public final void zzfb(int i) throws IOException {
        zzfa(zzfk(i));
    }

    public final void zzaz(long j) throws IOException {
        zzay(zzbg(j));
    }

    public final void zzf(float f) throws IOException {
        zzfc(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zzba(Double.doubleToRawLongBits(d));
    }

    public final void zzbd(boolean z) throws IOException {
        zzd((byte) z);
    }

    public static int zzz(int i, int i2) {
        return zzfd(i) + zzfe(i2);
    }

    public static int zzaa(int i, int i2) {
        return zzfd(i) + zzff(i2);
    }

    public static int zzab(int i, int i2) {
        return zzfd(i) + zzff(zzfk(i2));
    }

    public static int zzac(int i, int i2) {
        return zzfd(i) + 4;
    }

    public static int zzad(int i, int i2) {
        return zzfd(i) + 4;
    }

    public static int zzm(int i, long j) {
        return zzfd(i) + zzbc(j);
    }

    public static int zzn(int i, long j) {
        return zzfd(i) + zzbc(j);
    }

    public static int zzo(int i, long j) {
        return zzfd(i) + zzbc(zzbg(j));
    }

    public static int zzp(int i, long j) {
        return zzfd(i) + 8;
    }

    public static int zzq(int i, long j) {
        return zzfd(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzfd(i) + 4;
    }

    public static int zzc(int i, double d) {
        return zzfd(i) + 8;
    }

    public static int zzk(int i, boolean z) {
        return zzfd(i) + 1;
    }

    public static int zzae(int i, int i2) {
        return zzfd(i) + zzfe(i2);
    }

    public static int zzg(int i, String str) {
        return zzfd(i) + zzfy(str);
    }

    public static int zzc(int i, zzbpu zzbpu) {
        i = zzfd(i);
        int size = zzbpu.size();
        return i + (zzff(size) + size);
    }

    public static int zza(int i, zzbrs zzbrs) {
        i = zzfd(i);
        int zzamj = zzbrs.zzamj();
        return i + (zzff(zzamj) + zzamj);
    }

    public static int zzc(int i, zzbsl zzbsl) {
        return zzfd(i) + zzf(zzbsl);
    }

    static int zzb(int i, zzbsl zzbsl, zzbtc zzbtc) {
        return zzfd(i) + zzb(zzbsl, zzbtc);
    }

    public static int zzd(int i, zzbsl zzbsl) {
        return ((zzfd(1) << 1) + zzaa(2, i)) + zzc(3, zzbsl);
    }

    public static int zzd(int i, zzbpu zzbpu) {
        return ((zzfd(1) << 1) + zzaa(2, i)) + zzc(3, zzbpu);
    }

    public static int zzb(int i, zzbrs zzbrs) {
        return ((zzfd(1) << 1) + zzaa(2, i)) + zza(3, zzbrs);
    }

    public static int zzfd(int i) {
        return zzff(i << 3);
    }

    public static int zzfe(int i) {
        return i >= 0 ? zzff(i) : 10;
    }

    public static int zzfg(int i) {
        return zzff(zzfk(i));
    }

    public static int zzbb(long j) {
        return zzbc(j);
    }

    public static int zzbd(long j) {
        return zzbc(zzbg(j));
    }

    public static int zzfj(int i) {
        return zzfe(i);
    }

    public static int zzfy(String str) {
        int zza;
        try {
            zza = zzbuc.zza(str);
        } catch (zzbug unused) {
            zza = str.getBytes(zzbrf.UTF_8).length;
        }
        return zzff(zza) + zza;
    }

    public static int zza(zzbrs zzbrs) {
        int zzamj = zzbrs.zzamj();
        return zzff(zzamj) + zzamj;
    }

    public static int zzao(zzbpu zzbpu) {
        int size = zzbpu.size();
        return zzff(size) + size;
    }

    public static int zzu(byte[] bArr) {
        int length = bArr.length;
        return zzff(length) + length;
    }

    public static int zzf(zzbsl zzbsl) {
        int zzamj = zzbsl.zzamj();
        return zzff(zzamj) + zzamj;
    }

    static int zzb(zzbsl zzbsl, zzbtc zzbtc) {
        zzbpl zzbpl = (zzbpl) zzbsl;
        int zzakg = zzbpl.zzakg();
        if (zzakg == -1) {
            zzakg = zzbtc.zzac(zzbpl);
            zzbpl.zzei(zzakg);
        }
        return zzff(zzakg) + zzakg;
    }

    public final void zzalv() {
        if (zzalu() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(String str, zzbug zzbug) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzbug);
        byte[] bytes = str.getBytes(zzbrf.UTF_8);
        try {
            zzfa(bytes.length);
            zzh(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzbsl zzbsl, zzbtc zzbtc) {
        i = zzfd(i) << 1;
        zzbpl zzbpl = (zzbpl) zzbsl;
        int zzakg = zzbpl.zzakg();
        if (zzakg == -1) {
            zzakg = zzbtc.zzac(zzbpl);
            zzbpl.zzei(zzakg);
        }
        return i + zzakg;
    }

    @Deprecated
    public static int zzg(zzbsl zzbsl) {
        return zzbsl.zzamj();
    }

    @Deprecated
    public static int zzfl(int i) {
        return zzff(i);
    }

    /* synthetic */ zzbqk(zzbql zzbql) {
        this();
    }
}
