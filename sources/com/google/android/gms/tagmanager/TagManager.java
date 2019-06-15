package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@VisibleForTesting
public class TagManager {
    private static TagManager zzbgq;
    private final DataLayer zzazp;
    private final zzal zzber;
    private final zza zzbgn;
    private final zzfm zzbgo;
    private final ConcurrentMap<String, zzv> zzbgp;
    private final Context zzri;

    @VisibleForTesting
    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal);
    }

    @VisibleForTesting
    private TagManager(Context context, zza zza, DataLayer dataLayer, zzfm zzfm) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.zzri = context.getApplicationContext();
        this.zzbgo = zzfm;
        this.zzbgn = zza;
        this.zzbgp = new ConcurrentHashMap();
        this.zzazp = dataLayer;
        this.zzazp.zza(new zzga(this));
        this.zzazp.zza(new zzg(this.zzri));
        this.zzber = new zzal();
        this.zzri.registerComponentCallbacks(new zzgc(this));
        zza.zzo(this.zzri);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzbgq == null) {
                if (context == null) {
                    zzdi.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzbgq = new TagManager(context, new zzgb(), new DataLayer(new zzat(context)), zzfn.zzqe());
            }
            tagManager = zzbgq;
        }
        return tagManager;
    }

    public DataLayer getDataLayer() {
        return this.zzazp;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        zzy zza = this.zzbgn.zza(this.zzri, this, null, str, i, this.zzber);
        zza.zznt();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        zzy zza = this.zzbgn.zza(this.zzri, this, handler.getLooper(), str, i, this.zzber);
        zza.zznt();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        zzy zza = this.zzbgn.zza(this.zzri, this, null, str, i, this.zzber);
        zza.zznu();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        zzy zza = this.zzbgn.zza(this.zzri, this, handler.getLooper(), str, i, this.zzber);
        zza.zznu();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        zzy zza = this.zzbgn.zza(this.zzri, this, null, str, i, this.zzber);
        zza.zznv();
        return zza;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        zzy zza = this.zzbgn.zza(this.zzri, this, handler.getLooper(), str, i, this.zzber);
        zza.zznv();
        return zza;
    }

    public void dispatch() {
        this.zzbgo.dispatch();
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdi.setLogLevel(z ? 2 : 5);
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized boolean zzb(Uri uri) {
        zzeh zzpm = zzeh.zzpm();
        if (!zzpm.zzb(uri)) {
            return false;
        }
        String containerId = zzpm.getContainerId();
        switch (zzgd.zzbgs[zzpm.zzpn().ordinal()]) {
            case 1:
                zzv zzv = (zzv) this.zzbgp.get(containerId);
                if (zzv != null) {
                    zzv.zzdf(null);
                    zzv.refresh();
                    break;
                }
                break;
            case 2:
            case 3:
                for (String str : this.zzbgp.keySet()) {
                    zzv zzv2 = (zzv) this.zzbgp.get(str);
                    if (str.equals(containerId)) {
                        zzv2.zzdf(zzpm.zzpo());
                        zzv2.refresh();
                    } else if (zzv2.zznq() != null) {
                        zzv2.zzdf(null);
                        zzv2.refresh();
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    @VisibleForTesting
    public final int zza(zzv zzv) {
        this.zzbgp.put(zzv.getContainerId(), zzv);
        return this.zzbgp.size();
    }

    @VisibleForTesting
    public final boolean zzb(zzv zzv) {
        return this.zzbgp.remove(zzv.getContainerId()) != null;
    }

    private final void zzeb(String str) {
        for (zzv zzde : this.zzbgp.values()) {
            zzde.zzde(str);
        }
    }
}
