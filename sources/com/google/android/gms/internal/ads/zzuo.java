package com.google.android.gms.internal.ads;

import android.support.v4.view.PointerIconCompat;

public final class zzuo {

    public static final class zza extends zzbrd<zza, zza> implements zzbsn {
        private static final zza zzcar = new zza();
        private static volatile zzbsw<zza> zzcas;

        public static final class zza extends com.google.android.gms.internal.ads.zzbrd.zza<zza, zza> implements zzbsn {
            private zza() {
                super(zza.zzcar);
            }

            /* synthetic */ zza(zzup zzup) {
                this();
            }
        }

        public enum zzb implements zzbrg {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(1003),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(PointerIconCompat.TYPE_WAIT),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(1005),
            REQUEST_FAILED_TO_UPDATE_SIGNALS(PointerIconCompat.TYPE_CELL),
            BANNER_SIZE_INVALID(10000),
            BANNER_SIZE_VALID(10001),
            ANDROID_WEBVIEW_CRASH(10002);
            
            private static final zzbrh<zzb> zzcbx = null;
            private final int value;

            public final int zzom() {
                return this.value;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzcbx = new zzuq();
            }
        }

        private zza() {
        }

        /* Access modifiers changed, original: protected|final */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzup.zzcaq[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zza();
                case 3:
                    return zzbrd.zza((zzbsl) zzcar, "\u0001\u0000", null);
                case 4:
                    return zzcar;
                case 5:
                    Object obj3 = zzcas;
                    if (obj3 == null) {
                        synchronized (zza.class) {
                            obj3 = zzcas;
                            if (obj3 == null) {
                                obj3 = new com.google.android.gms.internal.ads.zzbrd.zzb(zzcar);
                                zzcas = obj3;
                            }
                        }
                    }
                    return obj3;
                case 6:
                    return Byte.valueOf((byte) 1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzbrd.zza(zza.class, zzcar);
        }
    }
}
