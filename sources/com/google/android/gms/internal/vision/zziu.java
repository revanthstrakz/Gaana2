package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zziu {
    private static final Logger logger = Logger.getLogger(zziu.class.getName());
    private static final boolean zzaaz = zzk(Long.TYPE);
    private static final boolean zzaba = zzk(Integer.TYPE);
    private static final zzd zzabb;
    private static final boolean zzabc = zzhl();
    private static final long zzabd = ((long) zzi(byte[].class));
    private static final long zzabe = ((long) zzi(boolean[].class));
    private static final long zzabf = ((long) zzj(boolean[].class));
    private static final long zzabg = ((long) zzi(int[].class));
    private static final long zzabh = ((long) zzj(int[].class));
    private static final long zzabi = ((long) zzi(long[].class));
    private static final long zzabj = ((long) zzj(long[].class));
    private static final long zzabk = ((long) zzi(float[].class));
    private static final long zzabl = ((long) zzj(float[].class));
    private static final long zzabm = ((long) zzi(double[].class));
    private static final long zzabn = ((long) zzj(double[].class));
    private static final long zzabo = ((long) zzi(Object[].class));
    private static final long zzabp = ((long) zzj(Object[].class));
    private static final long zzabq;
    private static final boolean zzabr = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Class<?> zzrm = zzeg.zzcl();
    private static final boolean zzsr = zzhk();
    private static final Unsafe zzzc = zzhj();

    static abstract class zzd {
        Unsafe zzabs;

        zzd(Unsafe unsafe) {
            this.zzabs = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzabs.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzabs.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzabs.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzabs.putLong(obj, j, j2);
        }
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (-1 & j), b);
        }

        public final byte zzy(Object obj, long j) {
            if (zziu.zzabr) {
                return zziu.zzq(obj, j);
            }
            return zziu.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zziu.zzabr) {
                zziu.zza(obj, j, b);
            } else {
                zziu.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zziu.zzabr) {
                return zziu.zzs(obj, j);
            }
            return zziu.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zziu.zzabr) {
                zziu.zzb(obj, j, z);
            } else {
                zziu.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (-1 & j2), bArr, (int) j, (int) j3);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            if (zziu.zzabr) {
                return zziu.zzq(obj, j);
            }
            return zziu.zzr(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zziu.zzabr) {
                zziu.zza(obj, j, b);
            } else {
                zziu.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            if (zziu.zzabr) {
                return zziu.zzs(obj, j);
            }
            return zziu.zzt(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zziu.zzabr) {
                zziu.zzb(obj, j, z);
            } else {
                zziu.zzc(obj, j, z);
            }
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(long j, byte b) {
            this.zzabs.putByte(j, b);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzabs.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzabs.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzabs.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzabs.putBoolean(obj, j, z);
        }

        public final float zzn(Object obj, long j) {
            return this.zzabs.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzabs.putFloat(obj, j, f);
        }

        public final double zzo(Object obj, long j) {
            return this.zzabs.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzabs.putDouble(obj, j, d);
        }

        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzabs.copyMemory(bArr, zziu.zzabd + j, null, j2, j3);
        }
    }

    private zziu() {
    }

    static boolean zzhh() {
        return zzsr;
    }

    static boolean zzhi() {
        return zzabc;
    }

    static <T> T zzh(Class<T> cls) {
        try {
            return zzzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        return zzsr ? zzabb.zzabs.arrayBaseOffset(cls) : -1;
    }

    private static int zzj(Class<?> cls) {
        return zzsr ? zzabb.zzabs.arrayIndexScale(cls) : -1;
    }

    static int zzk(Object obj, long j) {
        return zzabb.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzabb.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzabb.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzabb.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzabb.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzabb.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzabb.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzabb.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzabb.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzabb.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzabb.zzabs.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzabb.zzabs.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzabb.zzy(bArr, zzabd + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzabb.zze(bArr, zzabd + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzabb.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzabb.zza(j, b);
    }

    static long zzb(ByteBuffer byteBuffer) {
        return zzabb.zzl(byteBuffer, zzabq);
    }

    static Unsafe zzhj() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zziv());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzhk() {
        if (zzzc == null) {
            return false;
        }
        try {
            Class cls = zzzc.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzeg.zzck()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder stringBuilder = new StringBuilder(71 + String.valueOf(valueOf).length());
            stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
            stringBuilder.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", stringBuilder.toString());
            return false;
        }
    }

    private static boolean zzhl() {
        if (zzzc == null) {
            return false;
        }
        try {
            Class cls = zzzc.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzhm() == null) {
                return false;
            }
            if (zzeg.zzck()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            StringBuilder stringBuilder = new StringBuilder(71 + String.valueOf(valueOf).length());
            stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
            stringBuilder.append(valueOf);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", stringBuilder.toString());
            return false;
        }
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzeg.zzck()) {
            return false;
        }
        try {
            Class cls2 = zzrm;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzhm() {
        Field zzb;
        if (zzeg.zzck()) {
            zzb = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb != null) {
                return zzb;
            }
        }
        zzb = zzb(Buffer.class, AccountKitGraphConstants.EMAIL_ADDRESS_KEY);
        return (zzb == null || zzb.getType() != Long.TYPE) ? null : zzb;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, j & -4) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    private static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, j & -4) >>> ((int) ((j & 3) << 3)));
    }

    private static void zza(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = ((((int) j) ^ -1) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ -1)));
    }

    private static void zzb(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ -1)));
    }

    private static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != (byte) 0;
    }

    private static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != (byte) 0;
    }

    private static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, (byte) z);
    }

    private static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, (byte) z);
    }

    static {
        zzd zzd = null;
        if (zzzc != null) {
            if (!zzeg.zzck()) {
                zzd = new zzc(zzzc);
            } else if (zzaaz) {
                zzd = new zzb(zzzc);
            } else if (zzaba) {
                zzd = new zza(zzzc);
            }
        }
        zzabb = zzd;
        Field zzhm = zzhm();
        long objectFieldOffset = (zzhm == null || zzabb == null) ? -1 : zzabb.zzabs.objectFieldOffset(zzhm);
        zzabq = objectFieldOffset;
    }
}
