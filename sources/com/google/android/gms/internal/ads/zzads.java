package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzads extends NativeAppInstallAd {
    private final VideoController zzcml = new VideoController();
    private final zzadp zzdds;
    private final List<Image> zzddt = new ArrayList();
    private final zzade zzddu;
    private final AdChoicesInfo zzddv;

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0020 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a A:{Catch:{ RemoteException -> 0x0055 }} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007a A:{Catch:{ RemoteException -> 0x0087 }} */
    public zzads(com.google.android.gms.internal.ads.zzadp r5) {
        /*
        r4 = this;
        r4.<init>();
        r0 = new java.util.ArrayList;
        r0.<init>();
        r4.zzddt = r0;
        r0 = new com.google.android.gms.ads.VideoController;
        r0.<init>();
        r4.zzcml = r0;
        r4.zzdds = r5;
        r5 = 0;
        r0 = r4.zzdds;	 Catch:{ RemoteException -> 0x0055 }
        r0 = r0.getImages();	 Catch:{ RemoteException -> 0x0055 }
        if (r0 == 0) goto L_0x005b;
    L_0x001c:
        r0 = r0.iterator();	 Catch:{ RemoteException -> 0x0055 }
    L_0x0020:
        r1 = r0.hasNext();	 Catch:{ RemoteException -> 0x0055 }
        if (r1 == 0) goto L_0x005b;
    L_0x0026:
        r1 = r0.next();	 Catch:{ RemoteException -> 0x0055 }
        r2 = r1 instanceof android.os.IBinder;	 Catch:{ RemoteException -> 0x0055 }
        if (r2 == 0) goto L_0x0047;
    L_0x002e:
        r1 = (android.os.IBinder) r1;	 Catch:{ RemoteException -> 0x0055 }
        if (r1 == 0) goto L_0x0047;
    L_0x0032:
        r2 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage";
        r2 = r1.queryLocalInterface(r2);	 Catch:{ RemoteException -> 0x0055 }
        r3 = r2 instanceof com.google.android.gms.internal.ads.zzadb;	 Catch:{ RemoteException -> 0x0055 }
        if (r3 == 0) goto L_0x0040;
    L_0x003c:
        r1 = r2;
        r1 = (com.google.android.gms.internal.ads.zzadb) r1;	 Catch:{ RemoteException -> 0x0055 }
        goto L_0x0048;
    L_0x0040:
        r2 = new com.google.android.gms.internal.ads.zzadd;	 Catch:{ RemoteException -> 0x0055 }
        r2.<init>(r1);	 Catch:{ RemoteException -> 0x0055 }
        r1 = r2;
        goto L_0x0048;
    L_0x0047:
        r1 = r5;
    L_0x0048:
        if (r1 == 0) goto L_0x0020;
    L_0x004a:
        r2 = r4.zzddt;	 Catch:{ RemoteException -> 0x0055 }
        r3 = new com.google.android.gms.internal.ads.zzade;	 Catch:{ RemoteException -> 0x0055 }
        r3.<init>(r1);	 Catch:{ RemoteException -> 0x0055 }
        r2.add(r3);	 Catch:{ RemoteException -> 0x0055 }
        goto L_0x0020;
    L_0x0055:
        r0 = move-exception;
        r1 = "";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);
    L_0x005b:
        r0 = r4.zzdds;	 Catch:{ RemoteException -> 0x0069 }
        r0 = r0.zzsb();	 Catch:{ RemoteException -> 0x0069 }
        if (r0 == 0) goto L_0x006f;
    L_0x0063:
        r1 = new com.google.android.gms.internal.ads.zzade;	 Catch:{ RemoteException -> 0x0069 }
        r1.<init>(r0);	 Catch:{ RemoteException -> 0x0069 }
        goto L_0x0070;
    L_0x0069:
        r0 = move-exception;
        r1 = "";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);
    L_0x006f:
        r1 = r5;
    L_0x0070:
        r4.zzddu = r1;
        r0 = r4.zzdds;	 Catch:{ RemoteException -> 0x0087 }
        r0 = r0.zzse();	 Catch:{ RemoteException -> 0x0087 }
        if (r0 == 0) goto L_0x008d;
    L_0x007a:
        r0 = new com.google.android.gms.internal.ads.zzada;	 Catch:{ RemoteException -> 0x0087 }
        r1 = r4.zzdds;	 Catch:{ RemoteException -> 0x0087 }
        r1 = r1.zzse();	 Catch:{ RemoteException -> 0x0087 }
        r0.<init>(r1);	 Catch:{ RemoteException -> 0x0087 }
        r5 = r0;
        goto L_0x008d;
    L_0x0087:
        r0 = move-exception;
        r1 = "";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);
    L_0x008d:
        r4.zzddv = r5;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzads.<init>(com.google.android.gms.internal.ads.zzadp):void");
    }

    private final IObjectWrapper zzsc() {
        try {
            return this.zzdds.zzsc();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzdds.performClick(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzdds.recordImpression(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzdds.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzdds.getHeadline();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final List<Image> getImages() {
        return this.zzddt;
    }

    public final CharSequence getBody() {
        try {
            return this.zzdds.getBody();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Image getIcon() {
        return this.zzddu;
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzdds.getCallToAction();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzdds.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final CharSequence getStore() {
        try {
            return this.zzdds.getStore();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final CharSequence getPrice() {
        try {
            return this.zzdds.getPrice();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzdds.getVideoController() != null) {
                this.zzcml.zza(this.zzdds.getVideoController());
            }
        } catch (RemoteException e) {
            zzbbd.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzcml;
    }

    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzddv;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzdds.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzdds.getExtras();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzdds.destroy();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object zzhy() {
        return zzsc();
    }
}
