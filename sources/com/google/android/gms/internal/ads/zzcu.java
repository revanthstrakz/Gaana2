package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzcu {
    private static final String[] zzry = new String[]{"/aclk", "/pcs/click"};
    private String zzru = "googleads.g.doubleclick.net";
    private String zzrv = "/pagead/ads";
    private String zzrw = "ad.doubleclick.net";
    private String[] zzrx = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzcq zzrz;

    public zzcu(zzcq zzcq) {
        this.zzrz = zzcq;
    }

    private final boolean zza(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzrw);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final boolean zzb(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String endsWith : this.zzrx) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final zzcq zzab() {
        return this.zzrz;
    }

    public final Uri zza(Uri uri, Context context) throws zzcv {
        return zza(uri, context, null, false, null, null);
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzrz.zza(motionEvent);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcv {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcv("Provided Uri is not in a valid state");
        }
    }

    public final boolean zzc(Uri uri) {
        if (zzb(uri)) {
            for (String endsWith : zzry) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcv {
        try {
            String zza;
            boolean zza2 = zza(uri);
            if (zza2) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcv("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcv("Query parameter already exists: ms");
            }
            if (z) {
                zza = this.zzrz.zza(context, str, view, activity);
            } else {
                zza = this.zzrz.zza(context);
            }
            String str2;
            String uri2;
            int indexOf;
            StringBuilder stringBuilder;
            if (zza2) {
                str2 = "dc_ms";
                uri2 = uri.toString();
                indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    indexOf++;
                    stringBuilder = new StringBuilder(uri2.substring(0, indexOf));
                    stringBuilder.append(str2);
                    stringBuilder.append("=");
                    stringBuilder.append(zza);
                    stringBuilder.append(";");
                    stringBuilder.append(uri2.substring(indexOf));
                    return Uri.parse(stringBuilder.toString());
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                StringBuilder stringBuilder2 = new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2));
                stringBuilder2.append(";");
                stringBuilder2.append(str2);
                stringBuilder2.append("=");
                stringBuilder2.append(zza);
                stringBuilder2.append(";");
                stringBuilder2.append(uri2.substring(indexOf2 + encodedPath.length()));
                return Uri.parse(stringBuilder2.toString());
            }
            str2 = "ms";
            uri2 = uri.toString();
            indexOf = uri2.indexOf("&adurl");
            if (indexOf == -1) {
                indexOf = uri2.indexOf("?adurl");
            }
            if (indexOf == -1) {
                return uri.buildUpon().appendQueryParameter(str2, zza).build();
            }
            indexOf++;
            stringBuilder = new StringBuilder(uri2.substring(0, indexOf));
            stringBuilder.append(str2);
            stringBuilder.append("=");
            stringBuilder.append(zza);
            stringBuilder.append("&");
            stringBuilder.append(uri2.substring(indexOf));
            return Uri.parse(stringBuilder.toString());
        } catch (UnsupportedOperationException unused) {
            throw new zzcv("Provided Uri is not in a valid state");
        }
    }
}
