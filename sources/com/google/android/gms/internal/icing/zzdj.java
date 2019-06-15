package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzdj<MessageType extends zzdj<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbx<MessageType, BuilderType> {
    private static Map<Object, zzdj<?, ?>> zzjv = new ConcurrentHashMap();
    protected zzfy zzjt = zzfy.zzdp();
    private int zzju = -1;

    public enum zzd {
        public static final int zzka = 1;
        public static final int zzkb = 2;
        public static final int zzkc = 3;
        public static final int zzkd = 4;
        public static final int zzke = 5;
        public static final int zzkf = 6;
        public static final int zzkg = 7;
        private static final /* synthetic */ int[] zzkh = new int[]{zzka, zzkb, zzkc, zzkd, zzke, zzkf, zzkg};
        public static final int zzki = 1;
        public static final int zzkj = 2;
        private static final /* synthetic */ int[] zzkk = new int[]{zzki, zzkj};
        public static final int zzkl = 1;
        public static final int zzkm = 2;
        private static final /* synthetic */ int[] zzkn = new int[]{zzkl, zzkm};

        public static int[] zzcb() {
            return (int[]) zzkh.clone();
        }
    }

    public static class zzb<T extends zzdj<T, ?>> extends zzbz<T> {
        private final T zzjw;

        public zzb(T t) {
            this.zzjw = t;
        }
    }

    public static abstract class zza<MessageType extends zzdj<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzby<MessageType, BuilderType> {
        private final MessageType zzjw;
        protected MessageType zzjx;
        private boolean zzjy = false;

        protected zza(MessageType messageType) {
            this.zzjw = messageType;
            this.zzjx = (zzdj) messageType.zza(zzd.zzkd, null, null);
        }

        /* Access modifiers changed, original: protected|final */
        public final void zzbw() {
            if (this.zzjy) {
                zzdj zzdj = (zzdj) this.zzjx.zza(zzd.zzkd, null, null);
                zza(zzdj, this.zzjx);
                this.zzjx = zzdj;
                this.zzjy = false;
            }
        }

        public final boolean isInitialized() {
            return zzdj.zza(this.zzjx, false);
        }

        /* renamed from: zzbx */
        public MessageType zzbz() {
            if (this.zzjy) {
                return this.zzjx;
            }
            zzdj zzdj = this.zzjx;
            zzfc.zzcy().zzn(zzdj).zze(zzdj);
            this.zzjy = true;
            return this.zzjx;
        }

        /* renamed from: zzby */
        public final MessageType zzca() {
            zzdj zzdj = (zzdj) zzbz();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzdj.zza(zzd.zzka, null, null)).byteValue();
            boolean z = true;
            if (byteValue != (byte) 1) {
                if (byteValue == (byte) 0) {
                    z = false;
                } else {
                    z = zzfc.zzcy().zzn(zzdj).zzm(zzdj);
                    if (booleanValue) {
                        zzdj.zza(zzd.zzkb, z ? zzdj : null, null);
                    }
                }
            }
            if (z) {
                return zzdj;
            }
            throw new zzfw(zzdj);
        }

        public final BuilderType zza(MessageType messageType) {
            zzbw();
            zza(this.zzjx, messageType);
            return this;
        }

        private static void zza(MessageType messageType, MessageType messageType2) {
            zzfc.zzcy().zzn(messageType).zzc(messageType, messageType2);
        }

        public final /* synthetic */ zzby zzah() {
            return (zza) clone();
        }

        public final /* synthetic */ zzeq zzbv() {
            return this.zzjw;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) this.zzjw.zza(zzd.zzke, null, null);
            zza.zza((zzdj) zzbz());
            return zza;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzdj<MessageType, BuilderType> implements zzes {
        protected zzdc<Object> zzjz = zzdc.zzbh();
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzet.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzfp != 0) {
            return this.zzfp;
        }
        this.zzfp = zzfc.zzcy().zzn(this).hashCode(this);
        return this.zzfp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzdj) zza(zzd.zzkf, null, null)).getClass().isInstance(obj)) {
            return zzfc.zzcy().zzn(this).equals(this, (zzdj) obj);
        }
        return false;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzd.zzka, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean zzm = zzfc.zzcy().zzn(this).zzm(this);
        if (booleanValue) {
            zza(zzd.zzkb, zzm ? this : null, null);
        }
        return zzm;
    }

    /* Access modifiers changed, original: final */
    public final int zzag() {
        return this.zzju;
    }

    /* Access modifiers changed, original: final */
    public final void zzg(int i) {
        this.zzju = i;
    }

    public final void zzb(zzct zzct) throws IOException {
        zzfc.zzcy().zze(getClass()).zza(this, zzcv.zza(zzct));
    }

    public final int zzbi() {
        if (this.zzju == -1) {
            this.zzju = zzfc.zzcy().zzn(this).zzl(this);
        }
        return this.zzju;
    }

    static <T extends zzdj<?, ?>> T zzc(Class<T> cls) {
        T t = (zzdj) zzjv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzdj) zzjv.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzdj) ((zzdj) zzgd.zzg(cls)).zza(zzd.zzkf, null, null);
            if (t == null) {
                throw new IllegalStateException();
            }
            zzjv.put(cls, t);
        }
        return t;
    }

    protected static <T extends zzdj<?, ?>> void zza(Class<T> cls, T t) {
        zzjv.put(cls, t);
    }

    protected static Object zza(zzeq zzeq, String str, Object[] objArr) {
        return new zzfe(zzeq, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzdj<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zzka, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        return zzfc.zzcy().zzn(t).zzm(t);
    }

    protected static zzdp zzbq() {
        return zzef.zzck();
    }

    protected static zzdn zzbr() {
        return zzcw.zzaz();
    }

    protected static zzdm zzbs() {
        return zzcc.zzan();
    }

    protected static <E> zzdq<E> zzbt() {
        return zzfd.zzcz();
    }

    public final /* synthetic */ zzer zzbu() {
        zza zza = (zza) zza(zzd.zzke, null, null);
        zza.zza(this);
        return zza;
    }

    public final /* synthetic */ zzeq zzbv() {
        return (zzdj) zza(zzd.zzkf, null, null);
    }
}
