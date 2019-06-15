package com.google.android.gms.internal.icing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzgd {
    private static final Logger logger = Logger.getLogger(zzgd.class.getName());
    private static final Class<?> zzft = zzcb.zzam();
    private static final boolean zzgn = zzdu();
    private static final Unsafe zzme = zzdt();
    private static final boolean zzob = zzj(Long.TYPE);
    private static final boolean zzoc = zzj(Integer.TYPE);
    private static final zzd zzod;
    private static final boolean zzoe = zzdv();
    private static final long zzof = ((long) zzh(byte[].class));
    private static final long zzog = ((long) zzh(boolean[].class));
    private static final long zzoh = ((long) zzi(boolean[].class));
    private static final long zzoi = ((long) zzh(int[].class));
    private static final long zzoj = ((long) zzi(int[].class));
    private static final long zzok = ((long) zzh(long[].class));
    private static final long zzol = ((long) zzi(long[].class));
    private static final long zzom = ((long) zzh(float[].class));
    private static final long zzon = ((long) zzi(float[].class));
    private static final long zzoo = ((long) zzh(double[].class));
    private static final long zzop = ((long) zzi(double[].class));
    private static final long zzoq = ((long) zzh(Object[].class));
    private static final long zzor = ((long) zzi(Object[].class));
    private static final long zzos;
    private static final boolean zzot = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static abstract class zzd {
        Unsafe zzou;

        zzd(Unsafe unsafe) {
            this.zzou = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);

        public final int zzj(Object obj, long j) {
            return this.zzou.getInt(obj, j);
        }

        public final void zza(Object obj, long j, int i) {
            this.zzou.putInt(obj, j, i);
        }

        public final long zzk(Object obj, long j) {
            return this.zzou.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzou.putLong(obj, j, j2);
        }
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzp(obj, j);
            }
            return zzgd.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgd.zzot) {
                zzgd.zza(obj, j, b);
            } else {
                zzgd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzr(obj, j);
            }
            return zzgd.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgd.zzot) {
                zzgd.zzb(obj, j, z);
            } else {
                zzgd.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzp(obj, j);
            }
            return zzgd.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzgd.zzot) {
                zzgd.zza(obj, j, b);
            } else {
                zzgd.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzgd.zzot) {
                return zzgd.zzr(obj, j);
            }
            return zzgd.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzgd.zzot) {
                zzgd.zzb(obj, j, z);
            } else {
                zzgd.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzou.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzou.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzou.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzou.putBoolean(obj, j, z);
        }

        public final float zzm(Object obj, long j) {
            return this.zzou.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzou.putFloat(obj, j, f);
        }

        public final double zzn(Object obj, long j) {
            return this.zzou.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzou.putDouble(obj, j, d);
        }
    }

    private zzgd() {
    }

    static boolean zzdr() {
        return zzgn;
    }

    static boolean zzds() {
        return zzoe;
    }

    static <T> T zzg(Class<T> cls) {
        try {
            return zzme.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzh(Class<?> cls) {
        return zzgn ? zzod.zzou.arrayBaseOffset(cls) : -1;
    }

    private static int zzi(Class<?> cls) {
        return zzgn ? zzod.zzou.arrayIndexScale(cls) : -1;
    }

    static int zzj(Object obj, long j) {
        return zzod.zzj(obj, j);
    }

    static void zza(Object obj, long j, int i) {
        zzod.zza(obj, j, i);
    }

    static long zzk(Object obj, long j) {
        return zzod.zzk(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzod.zza(obj, j, j2);
    }

    static boolean zzl(Object obj, long j) {
        return zzod.zzl(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzod.zza(obj, j, z);
    }

    static float zzm(Object obj, long j) {
        return zzod.zzm(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzod.zza(obj, j, f);
    }

    static double zzn(Object obj, long j) {
        return zzod.zzn(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzod.zza(obj, j, d);
    }

    static Object zzo(Object obj, long j) {
        return zzod.zzou.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzod.zzou.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzod.zzx(bArr, zzof + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzod.zze(bArr, zzof + j, b);
    }

    static Unsafe zzdt() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzge());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdu() {
        if (zzme == null) {
            return false;
        }
        try {
            Class cls = zzme.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzcb.zzal()) {
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

    private static boolean zzdv() {
        if (zzme == null) {
            return false;
        }
        try {
            Class cls = zzme.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzdw() == null) {
                return false;
            }
            if (zzcb.zzal()) {
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

    private static boolean zzj(Class<?> cls) {
        if (!zzcb.zzal()) {
            return false;
        }
        try {
            Class cls2 = zzft;
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

    private static Field zzdw() {
        Field zzb;
        if (zzcb.zzal()) {
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

    private static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, j & -4) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    private static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, j & -4) >>> ((int) ((j & 3) << 3)));
    }

    private static void zza(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    private static void zzb(Object obj, long j, byte b) {
        long j2 = j & -4;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    private static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != (byte) 0;
    }

    private static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != (byte) 0;
    }

    private static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, (byte) z);
    }

    private static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, (byte) z);
    }

    static {
        zzd zzd = null;
        if (zzme != null) {
            if (!zzcb.zzal()) {
                zzd = new zzc(zzme);
            } else if (zzob) {
                zzd = new zzb(zzme);
            } else if (zzoc) {
                zzd = new zza(zzme);
            }
        }
        zzod = zzd;
        Field zzdw = zzdw();
        long objectFieldOffset = (zzdw == null || zzod == null) ? -1 : zzod.zzou.objectFieldOffset(zzdw);
        zzos = objectFieldOffset;
    }
}
