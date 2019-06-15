package com.google.android.gms.tagmanager;

import android.net.Uri;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@ShowFirstParty
@VisibleForTesting
class zzeh {
    private static zzeh zzbea;
    private volatile String zzazo = null;
    private volatile zza zzbeb = zza.NONE;
    private volatile String zzbec = null;
    private volatile String zzbed = null;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzeh() {
    }

    @ShowFirstParty
    static zzeh zzpm() {
        zzeh zzeh;
        synchronized (zzeh.class) {
            if (zzbea == null) {
                zzbea = new zzeh();
            }
            zzeh = zzbea;
        }
        return zzeh;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized boolean zzb(Uri uri) {
        try {
            String decode = URLDecoder.decode(uri.toString(), "UTF-8");
            String str;
            String valueOf;
            if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                str = "Container preview url: ";
                String valueOf2 = String.valueOf(decode);
                zzdi.v(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
                if (decode.matches(".*?&gtm_debug=x$")) {
                    this.zzbeb = zza.CONTAINER_DEBUG;
                } else {
                    this.zzbeb = zza.CONTAINER;
                }
                this.zzbed = uri.getQuery().replace("&gtm_debug=x", "");
                if (this.zzbeb == zza.CONTAINER || this.zzbeb == zza.CONTAINER_DEBUG) {
                    valueOf = String.valueOf("/r?");
                    str = String.valueOf(this.zzbed);
                    this.zzbec = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
                }
                this.zzazo = zzdx(this.zzbed);
                return true;
            } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                valueOf = "Invalid preview uri: ";
                decode = String.valueOf(decode);
                zzdi.zzab(decode.length() != 0 ? valueOf.concat(decode) : new String(valueOf));
                return false;
            } else if (!zzdx(uri.getQuery()).equals(this.zzazo)) {
                return false;
            } else {
                valueOf = "Exit preview mode for container: ";
                str = String.valueOf(this.zzazo);
                zzdi.v(str.length() != 0 ? valueOf.concat(str) : new String(valueOf));
                this.zzbeb = zza.NONE;
                this.zzbec = null;
                return true;
            }
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    /* Access modifiers changed, original: final */
    public final zza zzpn() {
        return this.zzbeb;
    }

    /* Access modifiers changed, original: final */
    public final String zzpo() {
        return this.zzbec;
    }

    /* Access modifiers changed, original: final */
    public final String getContainerId() {
        return this.zzazo;
    }

    private static String zzdx(String str) {
        return str.split("&")[0].split("=")[1];
    }
}
