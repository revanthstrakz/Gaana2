package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.internal.g.e;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaoa;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzawz;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbhc;
import com.google.android.gms.internal.ads.zzbhd;
import com.google.android.gms.internal.ads.zzbhh;
import com.google.android.gms.internal.ads.zzbhk;
import com.google.android.gms.internal.ads.zzbhm;
import com.google.android.gms.internal.ads.zzcu;
import com.google.android.gms.internal.ads.zzcv;
import java.net.URISyntaxException;
import java.util.Map;

@zzark
public final class zzac<T extends zzbhc & zzbhd & zzbhh & zzbhk & zzbhm> implements zzu<T> {
    private final zzw zzdgb;
    private final zzaoa zzdgc;

    public zzac(zzw zzw, zzaoa zzaoa) {
        this.zzdgb = zzw;
        this.zzdgc = zzaoa;
    }

    private static boolean zzg(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzh(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if (TtmlNode.TAG_P.equalsIgnoreCase(str)) {
                return zzbv.zzlh().zzzx();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzbv.zzlh().zzzw();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzbv.zzlh().zzzy();
            }
        }
        return -1;
    }

    private final void zzw(boolean z) {
        if (this.zzdgc != null) {
            this.zzdgc.zzx(z);
        }
    }

    @VisibleForTesting
    static String zza(Context context, zzcu zzcu, String str, View view, @Nullable Activity activity) {
        if (zzcu == null) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (zzcu.zzc(parse)) {
                parse = zzcu.zza(parse, context, view, activity);
            }
            return parse.toString();
        } catch (zzcv unused) {
            return str;
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbhc zzbhc = (zzbhc) obj;
        String zzb = zzawz.zzb((String) map.get("u"), zzbhc.getContext(), true);
        String str = (String) map.get("a");
        if (str == null) {
            zzbbd.zzeo("Action missing from an open GMSG.");
        } else if (this.zzdgb != null && !this.zzdgb.zzju()) {
            this.zzdgb.zzas(zzb);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzbhd) zzbhc).zzadq()) {
                zzbbd.zzeo("Cannot expand WebView that is already expanded.");
                return;
            }
            zzw(false);
            ((zzbhh) zzbhc).zzb(zzg(map), zzh(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzw(false);
            if (zzb != null) {
                ((zzbhh) zzbhc).zza(zzg(map), zzh(map), zzb);
            } else {
                ((zzbhh) zzbhc).zza(zzg(map), zzh(map), (String) map.get("html"), (String) map.get("baseurl"));
            }
        } else if ("app".equalsIgnoreCase(str) && "true".equalsIgnoreCase((String) map.get("system_browser"))) {
            zzw(true);
            zzbhc.getContext();
            if (TextUtils.isEmpty(zzb)) {
                zzbbd.zzeo("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzbhh) zzbhc).zza(new zzc(new zzad(zzbhc.getContext(), ((zzbhk) zzbhc).zzado(), ((zzbhm) zzbhc).getView()).zzi(map)));
            } catch (ActivityNotFoundException e) {
                zzbbd.zzeo(e.getMessage());
            }
        } else {
            zzw(true);
            str = (String) map.get("intent_url");
            Intent intent = null;
            if (!TextUtils.isEmpty(str)) {
                try {
                    intent = Intent.parseUri(str, 0);
                } catch (URISyntaxException e2) {
                    String str2 = "Error parsing the url: ";
                    str = String.valueOf(str);
                    zzbbd.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), e2);
                }
            }
            if (!(intent == null || intent.getData() == null)) {
                Uri data = intent.getData();
                String uri = data.toString();
                if (!TextUtils.isEmpty(uri)) {
                    try {
                        uri = zza(zzbhc.getContext(), ((zzbhk) zzbhc).zzado(), uri, ((zzbhm) zzbhc).getView(), zzbhc.zzabw());
                    } catch (Exception e3) {
                        zzbbd.zzb("Error occurred while adding signals.", e3);
                        zzbv.zzlj().zza(e3, "OpenGmsgHandler.onGmsg");
                    }
                    try {
                        data = Uri.parse(uri);
                    } catch (Exception e32) {
                        String str3 = "Error parsing the uri: ";
                        uri = String.valueOf(uri);
                        zzbbd.zzb(uri.length() != 0 ? str3.concat(uri) : new String(str3), e32);
                        zzbv.zzlj().zza(e32, "OpenGmsgHandler.onGmsg");
                    }
                }
                intent.setData(data);
            }
            if (intent != null) {
                ((zzbhh) zzbhc).zza(new zzc(intent));
                return;
            }
            if (!TextUtils.isEmpty(zzb)) {
                zzb = zza(zzbhc.getContext(), ((zzbhk) zzbhc).zzado(), zzb, ((zzbhm) zzbhc).getView(), zzbhc.zzabw());
            }
            ((zzbhh) zzbhc).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get(TtmlNode.TAG_P), (String) map.get("c"), (String) map.get("f"), (String) map.get(e.a)));
        }
    }
}
