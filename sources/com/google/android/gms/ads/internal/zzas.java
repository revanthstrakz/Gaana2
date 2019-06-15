package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzadb;
import com.google.android.gms.internal.ads.zzadc;
import com.google.android.gms.internal.ads.zzakx;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzbbd;
import com.google.android.gms.internal.ads.zzbgg;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzas {
    @Nullable
    public static View zze(@Nullable zzaxf zzaxf) {
        if (zzaxf == null) {
            zzbbd.e("AdState is null");
            return null;
        } else if (zzf(zzaxf) && zzaxf.zzdrv != null) {
            return zzaxf.zzdrv.getView();
        } else {
            try {
                IObjectWrapper zzut = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzut() : null;
                if (zzut != null) {
                    return (View) ObjectWrapper.unwrap(zzut);
                }
                zzbbd.zzeo("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzbbd.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zza(zzbgg zzbgg, zzakx zzakx, CountDownLatch countDownLatch) {
        zzbgg zzbgg2 = zzbgg;
        zzakx zzakx2 = zzakx;
        CountDownLatch countDownLatch2 = countDownLatch;
        boolean z = false;
        try {
            View view = zzbgg.getView();
            if (view == null) {
                zzbbd.zzeo("AdWebView is null");
            } else {
                view.setVisibility(4);
                List list = zzakx2.zzdnb.zzdll;
                if (list != null) {
                    if (!list.isEmpty()) {
                        zzbgg2.zza("/nativeExpressAssetsLoaded", new zzav(countDownLatch2));
                        zzbgg2.zza("/nativeExpressAssetsLoadingFailed", new zzaw(countDownLatch2));
                        zzals zzuu = zzakx2.zzdnc.zzuu();
                        zzalv zzuv = zzakx2.zzdnc.zzuv();
                        View view2 = null;
                        String headline;
                        List images;
                        String body;
                        zzadb zzsb;
                        String callToAction;
                        if (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) && zzuu != null) {
                            headline = zzuu.getHeadline();
                            images = zzuu.getImages();
                            body = zzuu.getBody();
                            zzsb = zzuu.zzsb();
                            callToAction = zzuu.getCallToAction();
                            double starRating = zzuu.getStarRating();
                            String store = zzuu.getStore();
                            String price = zzuu.getPrice();
                            Bundle extras = zzuu.getExtras();
                            if (zzuu.zzvc() != null) {
                                view2 = (View) ObjectWrapper.unwrap(zzuu.zzvc());
                            }
                            zzbgg.zzadl().zza(new zzat(new zzabs(headline, images, body, zzsb, callToAction, starRating, store, price, null, extras, null, view2, zzuu.zzsd(), null), zzakx2.zzdnb.zzdlk, zzbgg2));
                        } else if (!list.contains("1") || zzuv == null) {
                            zzbbd.zzeo("No matching template id and mapper");
                        } else {
                            headline = zzuv.getHeadline();
                            images = zzuv.getImages();
                            body = zzuv.getBody();
                            zzsb = zzuv.zzsf();
                            callToAction = zzuv.getCallToAction();
                            String advertiser = zzuv.getAdvertiser();
                            Bundle extras2 = zzuv.getExtras();
                            if (zzuv.zzvc() != null) {
                                view2 = (View) ObjectWrapper.unwrap(zzuv.zzvc());
                            }
                            zzbgg.zzadl().zza(new zzau(new zzabu(headline, images, body, zzsb, callToAction, advertiser, null, extras2, null, view2, zzuv.zzsd(), null), zzakx2.zzdnb.zzdlk, zzbgg2));
                        }
                        String str = zzakx2.zzdnb.zzdli;
                        String str2 = zzakx2.zzdnb.zzdlj;
                        if (str2 != null) {
                            zzbgg2.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
                        } else {
                            zzbgg2.loadData(str, "text/html", "UTF-8");
                        }
                        z = true;
                    }
                }
                zzbbd.zzeo("No template ids present in mediation response");
            }
        } catch (RemoteException e) {
            zzbbd.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            RuntimeException runtimeException = e2;
            countDownLatch.countDown();
            throw runtimeException;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    public static boolean zzf(@Nullable zzaxf zzaxf) {
        return (zzaxf == null || !zzaxf.zzdyd || zzaxf.zzdnb == null || zzaxf.zzdnb.zzdli == null) ? false : true;
    }

    @VisibleForTesting
    static zzu<zzbgg> zza(@Nullable zzals zzals, @Nullable zzalv zzalv, zzab zzab) {
        return new zzax(zzals, zzab, zzalv);
    }

    private static void zzd(zzbgg zzbgg) {
        OnClickListener onClickListener = zzbgg.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzbgg.getView());
        }
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if (TtmlNode.TAG_IMAGE.equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzbbd.zzeo("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzbbd.zzeo("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    @VisibleForTesting
    private static String zza(@Nullable zzadb zzadb) {
        if (zzadb == null) {
            zzbbd.zzeo("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzadb.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException unused) {
            zzbbd.zzeo("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzadb);
    }

    private static String zzb(zzadb zzadb) {
        try {
            IObjectWrapper zzsa = zzadb.zzsa();
            if (zzsa == null) {
                zzbbd.zzeo("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzsa);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzbbd.zzeo("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException unused) {
            zzbbd.zzeo("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static String zza(@Nullable Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzbbd.zzeo("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    @Nullable
    private static zzadb zzg(Object obj) {
        return obj instanceof IBinder ? zzadc.zzj((IBinder) obj) : null;
    }
}
