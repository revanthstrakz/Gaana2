package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbn extends zzba {
    private static final Logger logger = Logger.getLogger(zzbn.class.getName());
    private static final boolean zzfy = zzfd.zzed();
    zzbp zzfz = this;

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String str) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            str = String.valueOf(str);
            super(str.length() != 0 ? valueOf.concat(str) : new String(valueOf));
        }

        zzc(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            str = String.valueOf(str);
            super(str.length() != 0 ? valueOf.concat(str) : new String(valueOf), th);
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    static class zza extends zzbn {
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

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzbb zzbb) throws IOException {
            zzb(i, 2);
            zza(zzbb);
        }

        public final void zza(int i, zzdo zzdo) throws IOException {
            zzb(i, 2);
            zzb(zzdo);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzdo zzdo, zzef zzef) throws IOException {
            zzb(i, 2);
            zzas zzas = (zzas) zzdo;
            int zzs = zzas.zzs();
            if (zzs == -1) {
                zzs = zzef.zzm(zzas);
                zzas.zzf(zzs);
            }
            zzo(zzs);
            zzef.zza(zzdo, this.zzfz);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzg(str);
        }

        public final void zza(zzbb zzbb) throws IOException {
            zzo(zzbb.size());
            zzbb.zza((zzba) this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzdo zzdo, zzef zzef) throws IOException {
            zzas zzas = (zzas) zzdo;
            int zzs = zzas.zzs();
            if (zzs == -1) {
                zzs = zzef.zzm(zzas);
                zzas.zzf(zzs);
            }
            zzo(zzs);
            zzef.zza(zzdo, this.zzfz);
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final int zzag() {
            return this.limit - this.position;
        }

        public final int zzai() {
            return this.position - this.offset;
        }

        public final void zzb(int i, int i2) throws IOException {
            zzo((i << 3) | i2);
        }

        public final void zzb(int i, zzbb zzbb) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbb);
            zzb(1, 4);
        }

        public final void zzb(int i, zzdo zzdo) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdo);
            zzb(1, 4);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zza((byte) z);
        }

        public final void zzb(long j) throws IOException {
            byte[] bArr;
            int i;
            int i2;
            if (!zzbn.zzfy || zzag() < 10) {
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
                zzfd.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzfd.zza(bArr, (long) i2, (byte) ((int) j));
        }

        public final void zzb(zzdo zzdo) throws IOException {
            zzo(zzdo.zzas());
            zzdo.zzb(this);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzn(i2);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzo(i2);
        }

        public final void zzd(long j) throws IOException {
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

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzo(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzq(i2);
        }

        public final void zzg(String str) throws IOException {
            int i = this.position;
            try {
                int zzt = zzbn.zzt(str.length() * 3);
                int zzt2 = zzbn.zzt(str.length());
                if (zzt2 == zzt) {
                    this.position = i + zzt2;
                    zzt = zzff.zza(str, this.buffer, this.position, zzag());
                    this.position = i;
                    zzo((zzt - i) - zzt2);
                    this.position = zzt;
                    return;
                }
                zzo(zzff.zza(str));
                this.position = zzff.zza(str, this.buffer, this.position, zzag());
            } catch (zzfi e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        public final void zzn(int i) throws IOException {
            if (i >= 0) {
                zzo(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzo(int i) throws IOException {
            byte[] bArr;
            int i2;
            if (!zzbn.zzfy || zzag() < 10) {
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
                zzfd.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzfd.zza(bArr, (long) i2, (byte) i);
        }

        public final void zzq(int i) throws IOException {
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
    }

    static final class zzd extends zzbn {
        private final int zzgb;
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzgc = byteBuffer;
            this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzgb = byteBuffer.position();
        }

        private final void zzi(String str) throws IOException {
            try {
                zzff.zza(str, this.zzgd);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            }
        }

        public final void flush() {
            this.zzgc.position(this.zzgd.position());
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzgd.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(e);
            } catch (BufferOverflowException e2) {
                throw new zzc(e2);
            }
        }

        public final void zza(byte b) throws IOException {
            try {
                this.zzgd.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzbb zzbb) throws IOException {
            zzb(i, 2);
            zza(zzbb);
        }

        public final void zza(int i, zzdo zzdo) throws IOException {
            zzb(i, 2);
            zzb(zzdo);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzdo zzdo, zzef zzef) throws IOException {
            zzb(i, 2);
            zza(zzdo, zzef);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzg(str);
        }

        public final void zza(zzbb zzbb) throws IOException {
            zzo(zzbb.size());
            zzbb.zza((zzba) this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzdo zzdo, zzef zzef) throws IOException {
            zzas zzas = (zzas) zzdo;
            int zzs = zzas.zzs();
            if (zzs == -1) {
                zzs = zzef.zzm(zzas);
                zzas.zzf(zzs);
            }
            zzo(zzs);
            zzef.zza(zzdo, this.zzfz);
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final int zzag() {
            return this.zzgd.remaining();
        }

        public final void zzb(int i, int i2) throws IOException {
            zzo((i << 3) | i2);
        }

        public final void zzb(int i, zzbb zzbb) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbb);
            zzb(1, 4);
        }

        public final void zzb(int i, zzdo zzdo) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdo);
            zzb(1, 4);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zza((byte) z);
        }

        public final void zzb(long j) throws IOException {
            while ((j & -128) != 0) {
                this.zzgd.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzgd.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzb(zzdo zzdo) throws IOException {
            zzo(zzdo.zzas());
            zzdo.zzb(this);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzn(i2);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzo(i2);
        }

        public final void zzd(long j) throws IOException {
            try {
                this.zzgd.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzo(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzq(i2);
        }

        public final void zzg(String str) throws IOException {
            int position = this.zzgd.position();
            try {
                int zzt = zzbn.zzt(str.length() * 3);
                int zzt2 = zzbn.zzt(str.length());
                if (zzt2 == zzt) {
                    zzt = this.zzgd.position() + zzt2;
                    this.zzgd.position(zzt);
                    zzi(str);
                    zzt2 = this.zzgd.position();
                    this.zzgd.position(position);
                    zzo(zzt2 - zzt);
                    this.zzgd.position(zzt2);
                    return;
                }
                zzo(zzff.zza(str));
                zzi(str);
            } catch (zzfi e) {
                this.zzgd.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            }
        }

        public final void zzn(int i) throws IOException {
            if (i >= 0) {
                zzo(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzo(int i) throws IOException {
            while ((i & -128) != 0) {
                this.zzgd.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzgd.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }

        public final void zzq(int i) throws IOException {
            try {
                this.zzgd.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc(e);
            }
        }
    }

    static final class zze extends zzbn {
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;
        private final long zzge;
        private final long zzgf;
        private final long zzgg;
        private final long zzgh = (this.zzgg - 10);
        private long zzgi = this.zzgf;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzgc = byteBuffer;
            this.zzgd = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzge = zzfd.zzb(byteBuffer);
            this.zzgf = this.zzge + ((long) byteBuffer.position());
            this.zzgg = this.zzge + ((long) byteBuffer.limit());
        }

        private final void zzk(long j) {
            this.zzgd.position((int) (j - this.zzge));
        }

        public final void flush() {
            this.zzgc.position((int) (this.zzgi - this.zzge));
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                if (this.zzgg - j >= this.zzgi) {
                    zzfd.zza(bArr, (long) i, this.zzgi, j);
                    this.zzgi += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(i2)}));
        }

        public final void zza(byte b) throws IOException {
            if (this.zzgi >= this.zzgg) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
            }
            long j = this.zzgi;
            this.zzgi = j + 1;
            zzfd.zza(j, b);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzbb zzbb) throws IOException {
            zzb(i, 2);
            zza(zzbb);
        }

        public final void zza(int i, zzdo zzdo) throws IOException {
            zzb(i, 2);
            zzb(zzdo);
        }

        /* Access modifiers changed, original: final */
        public final void zza(int i, zzdo zzdo, zzef zzef) throws IOException {
            zzb(i, 2);
            zza(zzdo, zzef);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzg(str);
        }

        public final void zza(zzbb zzbb) throws IOException {
            zzo(zzbb.size());
            zzbb.zza((zzba) this);
        }

        /* Access modifiers changed, original: final */
        public final void zza(zzdo zzdo, zzef zzef) throws IOException {
            zzas zzas = (zzas) zzdo;
            int zzs = zzas.zzs();
            if (zzs == -1) {
                zzs = zzef.zzm(zzas);
                zzas.zzf(zzs);
            }
            zzo(zzs);
            zzef.zza(zzdo, this.zzfz);
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final int zzag() {
            return (int) (this.zzgg - this.zzgi);
        }

        public final void zzb(int i, int i2) throws IOException {
            zzo((i << 3) | i2);
        }

        public final void zzb(int i, zzbb zzbb) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbb);
            zzb(1, 4);
        }

        public final void zzb(int i, zzdo zzdo) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdo);
            zzb(1, 4);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zza((byte) z);
        }

        public final void zzb(long j) throws IOException {
            long j2;
            if (this.zzgi <= this.zzgh) {
                while ((j & -128) != 0) {
                    j2 = this.zzgi;
                    this.zzgi = j2 + 1;
                    zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
            } else {
                while (this.zzgi < this.zzgg) {
                    if ((j & -128) != 0) {
                        j2 = this.zzgi;
                        this.zzgi = j2 + 1;
                        zzfd.zza(j2, (byte) ((((int) j) & 127) | 128));
                        j >>>= 7;
                    }
                }
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
            }
            long j3 = this.zzgi;
            this.zzgi = j3 + 1;
            zzfd.zza(j3, (byte) ((int) j));
        }

        public final void zzb(zzdo zzdo) throws IOException {
            zzo(zzdo.zzas());
            zzdo.zzb(this);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzn(i2);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzo(i2);
        }

        public final void zzd(long j) throws IOException {
            this.zzgd.putLong((int) (this.zzgi - this.zzge), j);
            this.zzgi += 8;
        }

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzo(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzq(i2);
        }

        public final void zzg(String str) throws IOException {
            long j = this.zzgi;
            try {
                int zzt = zzbn.zzt(str.length() * 3);
                int zzt2 = zzbn.zzt(str.length());
                if (zzt2 == zzt) {
                    zzt = ((int) (this.zzgi - this.zzge)) + zzt2;
                    this.zzgd.position(zzt);
                    zzff.zza(str, this.zzgd);
                    zzt2 = this.zzgd.position() - zzt;
                    zzo(zzt2);
                    this.zzgi += (long) zzt2;
                    return;
                }
                zzt = zzff.zza(str);
                zzo(zzt);
                zzk(this.zzgi);
                zzff.zza(str, this.zzgd);
                this.zzgi += (long) zzt;
            } catch (zzfi e) {
                this.zzgi = j;
                zzk(this.zzgi);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc(e2);
            } catch (IndexOutOfBoundsException e22) {
                throw new zzc(e22);
            }
        }

        public final void zzn(int i) throws IOException {
            if (i >= 0) {
                zzo(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzo(int i) throws IOException {
            long j;
            if (this.zzgi <= this.zzgh) {
                while ((i & -128) != 0) {
                    j = this.zzgi;
                    this.zzgi = j + 1;
                    zzfd.zza(j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
            } else {
                while (this.zzgi < this.zzgg) {
                    if ((i & -128) != 0) {
                        j = this.zzgi;
                        this.zzgi = j + 1;
                        zzfd.zza(j, (byte) ((i & 127) | 128));
                        i >>>= 7;
                    }
                }
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(1)}));
            }
            j = this.zzgi;
            this.zzgi = j + 1;
            zzfd.zza(j, (byte) i);
        }

        public final void zzq(int i) throws IOException {
            this.zzgd.putInt((int) (this.zzgi - this.zzge), i);
            this.zzgi += 4;
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzga;
        private int zzgb;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzga = byteBuffer;
            this.zzgb = byteBuffer.position();
        }

        public final void flush() {
            this.zzga.position(this.zzgb + zzai());
        }
    }

    private zzbn() {
    }

    public static int zza(int i, zzcv zzcv) {
        i = zzr(i);
        int zzas = zzcv.zzas();
        return i + (zzt(zzas) + zzas);
    }

    public static int zza(zzcv zzcv) {
        int zzas = zzcv.zzas();
        return zzt(zzas) + zzas;
    }

    public static zzbn zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            return zzfd.zzee() ? new zze(byteBuffer) : new zzd(byteBuffer);
        } else {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzr(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzr(i) + 4;
    }

    public static int zzb(int i, zzcv zzcv) {
        return ((zzr(1) << 1) + zzh(2, i)) + zza(3, zzcv);
    }

    static int zzb(int i, zzdo zzdo, zzef zzef) {
        return zzr(i) + zzb(zzdo, zzef);
    }

    public static int zzb(int i, String str) {
        return zzr(i) + zzh(str);
    }

    public static int zzb(zzbb zzbb) {
        int size = zzbb.size();
        return zzt(size) + size;
    }

    static int zzb(zzdo zzdo, zzef zzef) {
        zzas zzas = (zzas) zzdo;
        int zzs = zzas.zzs();
        if (zzs == -1) {
            zzs = zzef.zzm(zzas);
            zzas.zzf(zzs);
        }
        return zzt(zzs) + zzs;
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(int i, zzbb zzbb) {
        i = zzr(i);
        int size = zzbb.size();
        return i + (zzt(size) + size);
    }

    public static int zzc(int i, zzdo zzdo) {
        return zzr(i) + zzc(zzdo);
    }

    @Deprecated
    static int zzc(int i, zzdo zzdo, zzef zzef) {
        i = zzr(i) << 1;
        zzas zzas = (zzas) zzdo;
        int zzs = zzas.zzs();
        if (zzs == -1) {
            zzs = zzef.zzm(zzas);
            zzas.zzf(zzs);
        }
        return i + zzs;
    }

    public static int zzc(int i, boolean z) {
        return zzr(i) + 1;
    }

    public static int zzc(zzdo zzdo) {
        int zzas = zzdo.zzas();
        return zzt(zzas) + zzas;
    }

    public static zzbn zzc(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzd(int i, long j) {
        return zzr(i) + zzf(j);
    }

    public static int zzd(int i, zzbb zzbb) {
        return ((zzr(1) << 1) + zzh(2, i)) + zzc(3, zzbb);
    }

    public static int zzd(int i, zzdo zzdo) {
        return ((zzr(1) << 1) + zzh(2, i)) + zzc(3, zzdo);
    }

    @Deprecated
    public static int zzd(zzdo zzdo) {
        return zzdo.zzas();
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return zzt(length) + length;
    }

    public static int zze(int i, long j) {
        return zzr(i) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzf(int i, long j) {
        return zzr(i) + zzf(zzj(j));
    }

    public static int zzf(long j) {
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

    public static int zzg(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzg(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, int i2) {
        return zzr(i) + zzt(i2);
    }

    public static int zzh(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzh(String str) {
        int zza;
        try {
            zza = zzff.zza(str);
        } catch (zzfi unused) {
            zza = str.getBytes(zzci.UTF_8).length;
        }
        return zzt(zza) + zza;
    }

    public static int zzi(int i, int i2) {
        return zzr(i) + zzt(zzy(i2));
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzj(int i, int i2) {
        return zzr(i) + 4;
    }

    private static long zzj(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzk(int i, int i2) {
        return zzr(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzr(int i) {
        return zzt(i << 3);
    }

    public static int zzs(int i) {
        return i >= 0 ? zzt(i) : 10;
    }

    public static int zzt(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzu(int i) {
        return zzt(zzy(i));
    }

    public static int zzv(int i) {
        return 4;
    }

    public static int zzw(int i) {
        return 4;
    }

    public static int zzx(int i) {
        return zzs(i);
    }

    private static int zzy(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzz(int i) {
        return zzt(i);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) throws IOException {
        zzq(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbb zzbb) throws IOException;

    public abstract void zza(int i, zzdo zzdo) throws IOException;

    public abstract void zza(int i, zzdo zzdo, zzef zzef) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzbb zzbb) throws IOException;

    public abstract void zza(zzdo zzdo, zzef zzef) throws IOException;

    /* Access modifiers changed, original: final */
    public final void zza(String str, zzfi zzfi) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfi);
        byte[] bytes = str.getBytes(zzci.UTF_8);
        try {
            zzo(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    public final void zza(boolean z) throws IOException {
        zza((byte) z);
    }

    public abstract int zzag();

    public abstract void zzb(int i, int i2) throws IOException;

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzbb zzbb) throws IOException;

    public abstract void zzb(int i, zzdo zzdo) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdo zzdo) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    public abstract void zzd(byte[] bArr, int i, int i2) throws IOException;

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzy(i2));
    }

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(String str) throws IOException;

    public abstract void zzn(int i) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public final void zzp(int i) throws IOException {
        zzo(zzy(i));
    }

    public abstract void zzq(int i) throws IOException;
}
