package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzbrd<MessageType extends zzbrd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbpl<MessageType, BuilderType> {
    private static Map<Object, zzbrd<?, ?>> zzfpw = new ConcurrentHashMap();
    protected zzbtv zzfpu = zzbtv.zzaoz();
    private int zzfpv = -1;

    public enum zze {
        public static final int zzfqb = 1;
        public static final int zzfqc = 2;
        public static final int zzfqd = 3;
        public static final int zzfqe = 4;
        public static final int zzfqf = 5;
        public static final int zzfqg = 6;
        public static final int zzfqh = 7;
        private static final /* synthetic */ int[] zzfqi = new int[]{zzfqb, zzfqc, zzfqd, zzfqe, zzfqf, zzfqg, zzfqh};
        public static final int zzfqj = 1;
        public static final int zzfqk = 2;
        private static final /* synthetic */ int[] zzfql = new int[]{zzfqj, zzfqk};
        public static final int zzfqm = 1;
        public static final int zzfqn = 2;
        private static final /* synthetic */ int[] zzfqo = new int[]{zzfqm, zzfqn};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzfqi.clone();
        }
    }

    public static class zzd<ContainingType extends zzbsl, Type> extends zzbqo<ContainingType, Type> {
    }

    public static class zzb<T extends zzbrd<T, ?>> extends zzbpn<T> {
        private final T zzfpx;

        public zzb(T t) {
            this.zzfpx = t;
        }

        public final /* synthetic */ Object zza(zzbqf zzbqf, zzbqq zzbqq) throws zzbrl {
            return zzbrd.zza(this.zzfpx, zzbqf, zzbqq);
        }
    }

    public static abstract class zza<MessageType extends zzbrd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbpm<MessageType, BuilderType> {
        private final MessageType zzfpx;
        protected MessageType zzfpy;
        private boolean zzfpz = false;

        protected zza(MessageType messageType) {
            this.zzfpx = messageType;
            this.zzfpy = (zzbrd) messageType.zza(zze.zzfqe, null, null);
        }

        /* Access modifiers changed, original: protected|final */
        public final void zzamw() {
            if (this.zzfpz) {
                zzbrd zzbrd = (zzbrd) this.zzfpy.zza(zze.zzfqe, null, null);
                zza(zzbrd, this.zzfpy);
                this.zzfpy = zzbrd;
                this.zzfpz = false;
            }
        }

        public final boolean isInitialized() {
            return zzbrd.zza(this.zzfpy, false);
        }

        /* renamed from: zzamx */
        public MessageType zzamz() {
            if (this.zzfpz) {
                return this.zzfpy;
            }
            zzbrd zzbrd = this.zzfpy;
            zzbsy.zzaog().zzaf(zzbrd).zzs(zzbrd);
            this.zzfpz = true;
            return this.zzfpy;
        }

        /* renamed from: zzamy */
        public final MessageType zzana() {
            zzbrd zzbrd = (zzbrd) zzamz();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzbrd.zza(zze.zzfqb, null, null)).byteValue();
            boolean z = true;
            if (byteValue != (byte) 1) {
                if (byteValue == (byte) 0) {
                    z = false;
                } else {
                    z = zzbsy.zzaog().zzaf(zzbrd).zzae(zzbrd);
                    if (booleanValue) {
                        zzbrd.zza(zze.zzfqc, z ? zzbrd : null, null);
                    }
                }
            }
            if (z) {
                return zzbrd;
            }
            throw new zzbtt(zzbrd);
        }

        public final BuilderType zza(MessageType messageType) {
            zzamw();
            zza(this.zzfpy, messageType);
            return this;
        }

        private static void zza(MessageType messageType, MessageType messageType2) {
            zzbsy.zzaog().zzaf(messageType).zzd(messageType, messageType2);
        }

        public final /* synthetic */ zzbpm zzakh() {
            return (zza) clone();
        }

        public final /* synthetic */ zzbsl zzamv() {
            return this.zzfpx;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zza = (zza) this.zzfpx.zza(zze.zzfqf, null, null);
            zza.zza((zzbrd) zzamz());
            return zza;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbrd<MessageType, BuilderType> implements zzbsn {
        protected zzbqu<Object> zzfqa = zzbqu.zzami();
    }

    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzbso.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzfkx != 0) {
            return this.zzfkx;
        }
        this.zzfkx = zzbsy.zzaog().zzaf(this).hashCode(this);
        return this.zzfkx;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzbrd) zza(zze.zzfqg, null, null)).getClass().isInstance(obj)) {
            return zzbsy.zzaog().zzaf(this).equals(this, (zzbrd) obj);
        }
        return false;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzfqb, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean zzae = zzbsy.zzaog().zzaf(this).zzae(this);
        if (booleanValue) {
            zza(zze.zzfqc, zzae ? this : null, null);
        }
        return zzae;
    }

    /* Access modifiers changed, original: final */
    public final int zzakg() {
        return this.zzfpv;
    }

    /* Access modifiers changed, original: final */
    public final void zzei(int i) {
        this.zzfpv = i;
    }

    public final void zzb(zzbqk zzbqk) throws IOException {
        zzbsy.zzaog().zzf(getClass()).zza(this, zzbqm.zza(zzbqk));
    }

    public final int zzamj() {
        if (this.zzfpv == -1) {
            this.zzfpv = zzbsy.zzaog().zzaf(this).zzac(this);
        }
        return this.zzfpv;
    }

    static <T extends zzbrd<?, ?>> T zzd(Class<T> cls) {
        T t = (zzbrd) zzfpw.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzbrd) zzfpw.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected static <T extends zzbrd<?, ?>> void zza(Class<T> cls, T t) {
        zzfpw.put(cls, t);
    }

    protected static Object zza(zzbsl zzbsl, String str, Object[] objArr) {
        return new zzbta(zzbsl, str, objArr);
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

    protected static final <T extends zzbrd<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzfqb, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        return zzbsy.zzaog().zzaf(t).zzae(t);
    }

    protected static zzbrj zzamr() {
        return zzbre.zzanb();
    }

    protected static <E> zzbrk<E> zzams() {
        return zzbsz.zzaoh();
    }

    static <T extends zzbrd<T, ?>> T zza(T t, zzbqf zzbqf, zzbqq zzbqq) throws zzbrl {
        zzbrd zzbrd = (zzbrd) t.zza(zze.zzfqe, null, null);
        try {
            zzbsy.zzaog().zzaf(zzbrd).zza(zzbrd, zzbqi.zza(zzbqf), zzbqq);
            zzbsy.zzaog().zzaf(zzbrd).zzs(zzbrd);
            return zzbrd;
        } catch (IOException e) {
            if (e.getCause() instanceof zzbrl) {
                throw ((zzbrl) e.getCause());
            }
            throw new zzbrl(e.getMessage()).zzj(zzbrd);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzbrl) {
                throw ((zzbrl) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzbrd<T, ?>> T zza(T t, byte[] bArr) throws zzbrl {
        zzbrd zzbrd = (zzbrd) t.zza(zze.zzfqe, null, null);
        try {
            zzbsy.zzaog().zzaf(zzbrd).zza(zzbrd, bArr, 0, bArr.length, new zzbpr());
            zzbsy.zzaog().zzaf(zzbrd).zzs(zzbrd);
            if (zzbrd.zzfkx == 0) {
                return zzbrd;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbrl) {
                throw ((zzbrl) e.getCause());
            }
            throw new zzbrl(e.getMessage()).zzj(zzbrd);
        } catch (IndexOutOfBoundsException unused) {
            throw zzbrl.zzanc().zzj(zzbrd);
        }
    }

    protected static <T extends zzbrd<T, ?>> T zza(T t, zzbpu zzbpu) throws zzbrl {
        boolean booleanValue;
        byte byteValue;
        zzbrd zza = zza((zzbrd) t, zzbpu, zzbqq.zzamd());
        boolean z = false;
        if (zza != null) {
            boolean z2;
            booleanValue = Boolean.TRUE.booleanValue();
            byteValue = ((Byte) zza.zza(zze.zzfqb, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z2 = true;
            } else if (byteValue == (byte) 0) {
                z2 = false;
            } else {
                z2 = zzbsy.zzaog().zzaf(zza).zzae(zza);
                if (booleanValue) {
                    zza.zza(zze.zzfqc, z2 ? zza : null, null);
                }
            }
            if (!z2) {
                throw new zzbtt(zza).zzaox().zzj(zza);
            }
        }
        if (zza != null) {
            booleanValue = Boolean.TRUE.booleanValue();
            byteValue = ((Byte) zza.zza(zze.zzfqb, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue != (byte) 0) {
                z = zzbsy.zzaog().zzaf(zza).zzae(zza);
                if (booleanValue) {
                    zza.zza(zze.zzfqc, z ? zza : null, null);
                }
            }
            if (!z) {
                throw new zzbtt(zza).zzaox().zzj(zza);
            }
        }
        return zza;
    }

    private static <T extends zzbrd<T, ?>> T zza(T t, zzbpu zzbpu, zzbqq zzbqq) throws zzbrl {
        zzbrd zza;
        try {
            zzbqf zzakp = zzbpu.zzakp();
            zza = zza((zzbrd) t, zzakp, zzbqq);
            zzakp.zzeo(0);
            return zza;
        } catch (zzbrl e) {
            throw e.zzj(zza);
        } catch (zzbrl e2) {
            throw e2;
        }
    }

    protected static <T extends zzbrd<T, ?>> T zzb(T t, byte[] bArr) throws zzbrl {
        zzbrd zza = zza((zzbrd) t, bArr);
        if (zza != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza.zza(zze.zzfqb, null, null)).byteValue();
            boolean z = true;
            if (byteValue != (byte) 1) {
                if (byteValue == (byte) 0) {
                    z = false;
                } else {
                    z = zzbsy.zzaog().zzaf(zza).zzae(zza);
                    if (booleanValue) {
                        zza.zza(zze.zzfqc, z ? zza : null, null);
                    }
                }
            }
            if (!z) {
                throw new zzbtt(zza).zzaox().zzj(zza);
            }
        }
        return zza;
    }

    public final /* synthetic */ zzbsm zzamt() {
        zza zza = (zza) zza(zze.zzfqf, null, null);
        zza.zza(this);
        return zza;
    }

    public final /* synthetic */ zzbsm zzamu() {
        return (zza) zza(zze.zzfqf, null, null);
    }

    public final /* synthetic */ zzbsl zzamv() {
        return (zzbrd) zza(zze.zzfqg, null, null);
    }
}
