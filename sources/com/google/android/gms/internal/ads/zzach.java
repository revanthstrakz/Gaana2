package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public class zzach implements zzacd {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private final zzbbi zzbob;
    @Nullable
    private zzawv zzbor;
    @Nullable
    private final zzaqp zzbqa;
    @Nullable
    private String zzbqb;
    private final zzace zzdby;
    private boolean zzdcb = false;
    private final zzabn zzdcc;
    @Nullable
    private final JSONObject zzdcd;
    @Nullable
    private final zzacf zzdce;
    private final zzcu zzdcf;
    @VisibleForTesting
    boolean zzdcg;
    @VisibleForTesting
    boolean zzdch;
    @Nullable
    private zzacw zzdci;
    private long zzdcj = 0;
    private long zzdck = 0;
    private WeakReference<View> zzdcl = null;

    public zzach(Context context, zzace zzace, @Nullable zzaqp zzaqp, zzcu zzcu, @Nullable JSONObject jSONObject, @Nullable zzacf zzacf, @Nullable zzbbi zzbbi, @Nullable String str) {
        this.mContext = context;
        this.zzdby = zzace;
        this.zzbqa = zzaqp;
        this.zzdcf = zzcu;
        this.zzdcd = jSONObject;
        this.zzdce = zzacf;
        this.zzbob = zzbbi;
        this.zzbqb = str;
        this.zzdcc = new zzabn(this.zzbqa);
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
    }

    @Nullable
    public View zza(OnClickListener onClickListener, boolean z) {
        zzabm zzrw = this.zzdce.zzrw();
        if (zzrw == null) {
            return null;
        }
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (!z) {
            int zzrr = zzrw.zzrr();
            if (zzrr != 0) {
                switch (zzrr) {
                    case 2:
                        layoutParams.addRule(12);
                        layoutParams.addRule(11);
                        break;
                    case 3:
                        layoutParams.addRule(12);
                        layoutParams.addRule(9);
                        break;
                    default:
                        layoutParams.addRule(10);
                        layoutParams.addRule(11);
                        break;
                }
            }
            layoutParams.addRule(10);
            layoutParams.addRule(9);
        }
        zzabp zzabp = new zzabp(this.mContext, zzrw, layoutParams);
        zzabp.setOnClickListener(onClickListener);
        zzabp.setContentDescription((CharSequence) zzwu.zzpz().zzd(zzaan.zzcuj));
        return zzabp;
    }

    public boolean zzsj() {
        zzabm zzrw = this.zzdce.zzrw();
        return zzrw != null && zzrw.zzrs();
    }

    public boolean zzsl() {
        return this.zzdcd != null && this.zzdcd.optBoolean("allow_pub_owned_ad_view", false);
    }

    public boolean zzsk() {
        return this.zzdcd != null && this.zzdcd.optBoolean("allow_custom_click_gesture", false);
    }

    private final boolean zzbs(String str) {
        JSONObject optJSONObject = this.zzdcd == null ? null : this.zzdcd.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null) {
            return false;
        }
        return optJSONObject.optBoolean(str, false);
    }

    public final void zza(View view, String str, @Nullable Bundle bundle, Map<String, WeakReference<View>> map, View view2, boolean z) {
        JSONObject jSONObject;
        String str2;
        Throwable e;
        View view3 = view2;
        JSONObject zza = zza((Map) map, view3);
        JSONObject zzp = zzp(view3);
        JSONObject zzq = zzq(view2);
        JSONObject zzr = zzr(view3);
        try {
            JSONObject zza2 = zzbv.zzlf().zza(bundle, null);
            jSONObject = new JSONObject();
            try {
                jSONObject.put("click_point", zza2);
                str2 = str;
                try {
                    jSONObject.put("asset_id", str2);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                str2 = str;
                zzbbd.zzb("Error occurred while grabbing click signals.", e);
                zza(view, zzp, zza, zzq, zzr, str2, jSONObject, null, z);
            }
        } catch (Exception e4) {
            e = e4;
            str2 = str;
            jSONObject = null;
            zzbbd.zzb("Error occurred while grabbing click signals.", e);
            zza(view, zzp, zza, zzq, zzr, str2, jSONObject, null, z);
        }
        zza(view, zzp, zza, zzq, zzr, str2, jSONObject, null, z);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Entry entry : map.entrySet()) {
                    if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                        zza(view, (String) entry.getKey(), bundle, map, view2, false);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzdce.zzrv())) {
            zza(view, "3099", bundle, map, view2, false);
        } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzdce.zzrv())) {
            zza(view, "2099", bundle, map, view2, false);
        } else {
            if ("1".equals(this.zzdce.zzrv())) {
                zza(view, "1099", bundle, map, view2, false);
            }
        }
    }

    public final void performClick(Bundle bundle) {
        if (bundle == null) {
            zzbbd.zzdn("Click data is null. No click is reported.");
        } else if (zzbs("click_reporting")) {
            zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbv.zzlf().zza(bundle, null), false);
        } else {
            zzbbd.e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        }
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6, boolean z) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzdcd);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            jSONObject = new JSONObject();
            long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
            jSONObject.put("time_from_last_touch_down", currentTimeMillis - this.zzdck);
            jSONObject.put("time_from_last_touch", currentTimeMillis - this.zzdcj);
            jSONObject7.put("touch_signal", jSONObject);
            jSONObject = new JSONObject();
            jSONObject.put("asset_id", str);
            jSONObject.put("template", this.zzdce.zzrv());
            zzbv.zzlh();
            jSONObject.put("is_privileged_process", zzayp.zzaaa());
            boolean z2 = false;
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue() && this.zzdcc.zzrt() != null && this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject.put(AvidJSONUtil.KEY_TIMESTAMP, currentTimeMillis);
            jSONObject.put("has_custom_click_handler", this.zzdby.zzar(this.zzdce.getCustomTemplateId()) != null);
            if (this.zzdcb && zzsk()) {
                jSONObject.put("custom_click_gesture_eligible", true);
            }
            if (z) {
                jSONObject.put("is_custom_click_gesture", true);
            }
            String str2 = "has_custom_click_handler";
            if (this.zzdby.zzar(this.zzdce.getCustomTemplateId()) != null) {
                z2 = true;
            }
            jSONObject7.put(str2, z2);
            try {
                jSONObject2 = this.zzdcd.optJSONObject("tracking_urls_and_actions");
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                jSONObject.put("click_signals", this.zzdcf.zzab().zza(this.mContext, jSONObject2.optString("click_string"), view));
            } catch (Exception e) {
                zzbbd.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put("click", jSONObject);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzbqb);
            zzbbo.zza(this.zzbqa.zzi(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e2) {
            zzbbd.zzb("Unable to create click JSON.", e2);
        }
    }

    public void zzd(MotionEvent motionEvent) {
        this.zzdcj = zzbv.zzlm().currentTimeMillis();
        if (motionEvent.getAction() == 0) {
            this.zzdck = zzbv.zzlm().currentTimeMillis();
        }
        this.zzdcf.zza(motionEvent);
    }

    public final void reportTouchEvent(Bundle bundle) {
        if (bundle == null) {
            zzbbd.zzdn("Touch event data is null. No touch event is reported.");
        } else if (zzbs("touch_reporting")) {
            this.zzdcf.zzab().zza((int) bundle.getFloat(AvidJSONUtil.KEY_X), (int) bundle.getFloat(AvidJSONUtil.KEY_Y), bundle.getInt("duration_ms"));
        } else {
            zzbbd.e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        }
    }

    /* JADX WARNING: Missing block: B:37:0x005d, code skipped:
            return;
     */
    public final void zzc(android.view.View r4, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5) {
        /*
        r3 = this;
        r0 = r3.mLock;
        monitor-enter(r0);
        r1 = r3.zzdcg;	 Catch:{ all -> 0x005e }
        if (r1 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r0);	 Catch:{ all -> 0x005e }
        return;
    L_0x0009:
        r1 = zzn(r4);	 Catch:{ all -> 0x005e }
        if (r1 == 0) goto L_0x0014;
    L_0x000f:
        r3.zza(r4, r5);	 Catch:{ all -> 0x005e }
        monitor-exit(r0);	 Catch:{ all -> 0x005e }
        return;
    L_0x0014:
        r1 = com.google.android.gms.internal.ads.zzaan.zzcun;	 Catch:{ all -> 0x005e }
        r2 = com.google.android.gms.internal.ads.zzwu.zzpz();	 Catch:{ all -> 0x005e }
        r1 = r2.zzd(r1);	 Catch:{ all -> 0x005e }
        r1 = (java.lang.Boolean) r1;	 Catch:{ all -> 0x005e }
        r1 = r1.booleanValue();	 Catch:{ all -> 0x005e }
        if (r1 == 0) goto L_0x005c;
    L_0x0026:
        if (r5 == 0) goto L_0x005c;
    L_0x0028:
        monitor-enter(r5);	 Catch:{ all -> 0x005e }
        r1 = r5.entrySet();	 Catch:{ all -> 0x0059 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0059 }
    L_0x0031:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0059 }
        if (r2 == 0) goto L_0x0057;
    L_0x0037:
        r2 = r1.next();	 Catch:{ all -> 0x0059 }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ all -> 0x0059 }
        r2 = r2.getValue();	 Catch:{ all -> 0x0059 }
        r2 = (java.lang.ref.WeakReference) r2;	 Catch:{ all -> 0x0059 }
        r2 = r2.get();	 Catch:{ all -> 0x0059 }
        r2 = (android.view.View) r2;	 Catch:{ all -> 0x0059 }
        if (r2 == 0) goto L_0x0031;
    L_0x004b:
        r2 = zzn(r2);	 Catch:{ all -> 0x0059 }
        if (r2 == 0) goto L_0x0031;
    L_0x0051:
        r3.zza(r4, r5);	 Catch:{ all -> 0x0059 }
        monitor-exit(r5);	 Catch:{ all -> 0x0059 }
        monitor-exit(r0);	 Catch:{ all -> 0x005e }
        return;
    L_0x0057:
        monitor-exit(r5);	 Catch:{ all -> 0x0059 }
        goto L_0x005c;
    L_0x0059:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0059 }
        throw r4;	 Catch:{ all -> 0x005e }
    L_0x005c:
        monitor-exit(r0);	 Catch:{ all -> 0x005e }
        return;
    L_0x005e:
        r4 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x005e }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzach.zzc(android.view.View, java.util.Map):void");
    }

    private static boolean zzn(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    public void zzjl() {
        this.zzdby.zzjl();
    }

    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zzp(view), zza((Map) map, view), zzq(view), zzr(view), null);
    }

    public final boolean recordImpression(Bundle bundle) {
        if (zzbs("impression_reporting")) {
            return zza(null, null, null, null, zzbv.zzlf().zza(bundle, null));
        }
        zzbbd.e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
        return false;
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzdcg) {
            return true;
        }
        this.zzdcg = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzdcd);
            jSONObject6.put("ads_id", this.zzbqb);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzbbo.zza(this.zzbqa.zzj(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzdby.zza((zzacd) this);
            this.zzdby.zziq();
            zzjl();
            return true;
        } catch (JSONException e) {
            zzbbd.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    public void zzsm() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (!this.zzdch) {
            this.zzdch = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ad", this.zzdcd);
                jSONObject.put("ads_id", this.zzbqb);
                zzbbo.zza(this.zzbqa.zzk(jSONObject), "NativeAdEngineImpl.recordDownloadedImpression");
            } catch (JSONException e) {
                zzbbd.zzb("", e);
            }
        }
    }

    public final View zzss() {
        return this.zzdcl != null ? (View) this.zzdcl.get() : null;
    }

    public final void zzm(View view) {
        this.zzdcl = new WeakReference(view);
    }

    public final void zzj(View view) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctk)).booleanValue() && this.zzdcf != null) {
            zzcq zzab = this.zzdcf.zzab();
            if (zzab != null) {
                zzab.zzb(view);
            }
        }
    }

    public void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        view.setOnTouchListener(onTouchListener);
        view.setClickable(true);
        view.setOnClickListener(onClickListener);
        if (map != null) {
            synchronized (map) {
                for (Entry value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener(onTouchListener);
                        view2.setClickable(true);
                        view2.setOnClickListener(onClickListener);
                    }
                }
            }
        }
        if (map2 != null) {
            synchronized (map2) {
                for (Entry value2 : map2.entrySet()) {
                    View view3 = (View) ((WeakReference) value2.getValue()).get();
                    if (view3 != null) {
                        view3.setOnTouchListener(onTouchListener);
                    }
                }
            }
        }
    }

    public zzbgg zzsp() throws zzbgq {
        if (this.zzdcd == null || this.zzdcd.optJSONObject("overlay") == null) {
            return null;
        }
        zzbv.zzlg();
        Context context = this.mContext;
        zzwf zzg = zzwf.zzg(this.mContext);
        zzbgg zza = zzbgm.zza(context, zzbht.zzb(zzg), zzg.zzckk, false, false, this.zzdcf, this.zzbob, null, null, null, zzum.zzoi());
        if (zza != null) {
            zza.getView().setVisibility(8);
            new zzacj(zza).zza(this.zzbqa);
        }
        return zza;
    }

    public final void zzf(Map<String, WeakReference<View>> map) {
        if (this.zzdce.zzrx() != null) {
            boolean z = false;
            zzayb zzyq;
            String adUnitId;
            String zzrv;
            if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzdce.zzrv())) {
                zzyq = zzbv.zzlj().zzyq();
                adUnitId = this.zzdby.getAdUnitId();
                zzrv = this.zzdce.zzrv();
                if (map != null && (map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO) || map.containsKey(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO))) {
                    z = true;
                }
                zzyq.zzb(adUnitId, zzrv, z);
            } else if ("1".equals(this.zzdce.zzrv())) {
                zzyq = zzbv.zzlj().zzyq();
                adUnitId = this.zzdby.getAdUnitId();
                zzrv = this.zzdce.zzrv();
                if (map != null && (map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO) || map.containsKey(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO))) {
                    z = true;
                }
                zzyq.zzb(adUnitId, zzrv, z);
            }
        }
    }

    public final boolean zzb(View view, zzacb zzacb) {
        ViewGroup.LayoutParams layoutParams;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcum)).booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        } else {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        View zzrx = this.zzdce.zzrx();
        if (zzrx == null) {
            return false;
        }
        ViewParent parent = zzrx.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzrx);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(zzrx, layoutParams);
        this.zzdby.zza(zzacb);
        return true;
    }

    public final void zzi(View view) {
        this.zzdby.zzi(view);
    }

    public final void zza(View view, zzacb zzacb) {
        if (!zzb(view, zzacb)) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            if (this.zzdce instanceof zzacg) {
                zzacg zzacg = (zzacg) this.zzdce;
                if (zzacg.getImages() != null && zzacg.getImages().size() > 0) {
                    Object obj = zzacg.getImages().get(0);
                    zzadb zzj = obj instanceof IBinder ? zzadc.zzj((IBinder) obj) : null;
                    if (zzj != null) {
                        try {
                            IObjectWrapper zzsa = zzj.zzsa();
                            if (zzsa != null) {
                                Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzsa);
                                ImageView imageView = new ImageView(this.mContext);
                                imageView.setImageDrawable(drawable);
                                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException unused) {
                            zzbbd.zzeo("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    public void zza(zzaet zzaet) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue()) {
            return;
        }
        if (this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzdcc.zza(zzaet);
        } else {
            zzbbd.zzeo("setUnconfirmedClickListener: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
        }
    }

    public void cancelUnconfirmedClick() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue() && this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzdcc.cancelUnconfirmedClick();
        }
    }

    public void setClickConfirmingView(View view) {
        if (!((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue()) {
            return;
        }
        if (this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzabn zzabn = this.zzdcc;
            if (view != null) {
                view.setOnClickListener(zzabn);
                view.setClickable(true);
                zzabn.zzdam = new WeakReference(view);
            }
            return;
        }
        zzbbd.zzeo("setClickConfirmingView: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
    }

    public void zzsi() {
        this.zzdcb = true;
    }

    public void recordCustomClickGesture() {
        if (this.zzdci == null) {
            zzbbd.zzdn("Ad should be associated with an ad view before calling recordCustomClickGesture()");
        } else if (!this.zzdcb) {
            zzbbd.zzdn("Custom click reporting failed. enableCustomClickGesture is not set.");
        } else if (zzsk()) {
            zza(this.zzdci.zzsw(), "3099", null, this.zzdci.zzsv(), this.zzdci.zzsw(), true);
        } else {
            zzbbd.zzdn("Custom click reporting failed. Ad unit id not whitelisted.");
        }
    }

    public final void zza(zzacw zzacw) {
        this.zzdci = zzacw;
    }

    @Nullable
    public final zzacw zzst() {
        return this.zzdci;
    }

    public void zzsr() {
        this.zzdby.zzjn();
    }

    public void zzjm() {
        this.zzdby.zzjm();
    }

    public void zzsq() {
        this.zzbqa.zzug();
    }

    public final Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    private static int[] zzo(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    @VisibleForTesting
    private final int zzck(int i) {
        zzwu.zzpv();
        return zzbat.zzb(this.mContext, i);
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzck(rect.right - rect.left));
        jSONObject.put("height", zzck(rect.bottom - rect.top));
        jSONObject.put(AvidJSONUtil.KEY_X, zzck(rect.left));
        jSONObject.put(AvidJSONUtil.KEY_Y, zzck(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private final JSONObject zzp(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            Object zzb;
            int[] zzo = zzo(view);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzck(view.getMeasuredWidth()));
            jSONObject2.put("height", zzck(view.getMeasuredHeight()));
            jSONObject2.put(AvidJSONUtil.KEY_X, zzck(zzo[0]));
            jSONObject2.put(AvidJSONUtil.KEY_Y, zzck(zzo[1]));
            jSONObject2.put("relative_to", "window");
            jSONObject.put("frame", jSONObject2);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                zzb = zzb(rect);
            } else {
                zzb = new JSONObject();
                zzb.put("width", 0);
                zzb.put("height", 0);
                zzb.put(AvidJSONUtil.KEY_X, zzck(zzo[0]));
                zzb.put(AvidJSONUtil.KEY_Y, zzck(zzo[1]));
                zzb.put("relative_to", "window");
            }
            jSONObject.put("visible_bounds", zzb);
        } catch (Exception unused) {
            zzbbd.zzeo("Unable to get native ad view bounding box");
        }
        return jSONObject;
    }

    private static JSONObject zzq(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbv.zzlf();
            jSONObject.put("contained_in_scroll_view", zzayh.zzy(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzr(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbv.zzlf();
            jSONObject.put("can_show_on_lock_screen", zzayh.zzx(view));
            zzbv.zzlf();
            jSONObject.put("is_keyguard_locked", zzayh.zzau(this.mContext));
        } catch (JSONException unused) {
            zzbbd.zzeo("Unable to get lock screen information");
        }
        return jSONObject;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x00f9 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:22|23|34) */
    /* JADX WARNING: Missing block: B:23:?, code skipped:
            com.google.android.gms.internal.ads.zzbbd.zzeo("Unable to get asset views information");
     */
    private final org.json.JSONObject zza(java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r13, android.view.View r14) {
        /*
        r12 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        if (r13 == 0) goto L_0x0105;
    L_0x0007:
        if (r14 != 0) goto L_0x000b;
    L_0x0009:
        goto L_0x0105;
    L_0x000b:
        r14 = zzo(r14);
        monitor-enter(r13);
        r1 = r13.entrySet();	 Catch:{ all -> 0x0102 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0102 }
    L_0x0018:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0102 }
        if (r2 == 0) goto L_0x0100;
    L_0x001e:
        r2 = r1.next();	 Catch:{ all -> 0x0102 }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ all -> 0x0102 }
        r3 = r2.getValue();	 Catch:{ all -> 0x0102 }
        r3 = (java.lang.ref.WeakReference) r3;	 Catch:{ all -> 0x0102 }
        r3 = r3.get();	 Catch:{ all -> 0x0102 }
        r3 = (android.view.View) r3;	 Catch:{ all -> 0x0102 }
        if (r3 == 0) goto L_0x0018;
    L_0x0032:
        r4 = zzo(r3);	 Catch:{ all -> 0x0102 }
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0102 }
        r5.<init>();	 Catch:{ all -> 0x0102 }
        r6 = new org.json.JSONObject;	 Catch:{ all -> 0x0102 }
        r6.<init>();	 Catch:{ all -> 0x0102 }
        r7 = "width";
        r8 = r3.getMeasuredWidth();	 Catch:{ JSONException -> 0x00f9 }
        r8 = r12.zzck(r8);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "height";
        r8 = r3.getMeasuredHeight();	 Catch:{ JSONException -> 0x00f9 }
        r8 = r12.zzck(r8);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "x";
        r8 = 0;
        r9 = r4[r8];	 Catch:{ JSONException -> 0x00f9 }
        r10 = r14[r8];	 Catch:{ JSONException -> 0x00f9 }
        r9 = r9 - r10;
        r9 = r12.zzck(r9);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r9);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "y";
        r9 = 1;
        r10 = r4[r9];	 Catch:{ JSONException -> 0x00f9 }
        r11 = r14[r9];	 Catch:{ JSONException -> 0x00f9 }
        r10 = r10 - r11;
        r10 = r12.zzck(r10);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r10);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "relative_to";
        r10 = "ad_view";
        r6.put(r7, r10);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "frame";
        r5.put(r7, r6);	 Catch:{ JSONException -> 0x00f9 }
        r6 = new android.graphics.Rect;	 Catch:{ JSONException -> 0x00f9 }
        r6.<init>();	 Catch:{ JSONException -> 0x00f9 }
        r7 = r3.getLocalVisibleRect(r6);	 Catch:{ JSONException -> 0x00f9 }
        if (r7 == 0) goto L_0x0094;
    L_0x008f:
        r4 = r12.zzb(r6);	 Catch:{ JSONException -> 0x00f9 }
        goto L_0x00c7;
    L_0x0094:
        r6 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00f9 }
        r6.<init>();	 Catch:{ JSONException -> 0x00f9 }
        r7 = "width";
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "height";
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "x";
        r10 = r4[r8];	 Catch:{ JSONException -> 0x00f9 }
        r8 = r14[r8];	 Catch:{ JSONException -> 0x00f9 }
        r10 = r10 - r8;
        r8 = r12.zzck(r10);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x00f9 }
        r7 = "y";
        r4 = r4[r9];	 Catch:{ JSONException -> 0x00f9 }
        r8 = r14[r9];	 Catch:{ JSONException -> 0x00f9 }
        r4 = r4 - r8;
        r4 = r12.zzck(r4);	 Catch:{ JSONException -> 0x00f9 }
        r6.put(r7, r4);	 Catch:{ JSONException -> 0x00f9 }
        r4 = "relative_to";
        r7 = "ad_view";
        r6.put(r4, r7);	 Catch:{ JSONException -> 0x00f9 }
        r4 = r6;
    L_0x00c7:
        r6 = "visible_bounds";
        r5.put(r6, r4);	 Catch:{ JSONException -> 0x00f9 }
        r4 = r3 instanceof android.widget.TextView;	 Catch:{ JSONException -> 0x00f9 }
        if (r4 == 0) goto L_0x00ee;
    L_0x00d0:
        r3 = (android.widget.TextView) r3;	 Catch:{ JSONException -> 0x00f9 }
        r4 = "text_color";
        r6 = r3.getCurrentTextColor();	 Catch:{ JSONException -> 0x00f9 }
        r5.put(r4, r6);	 Catch:{ JSONException -> 0x00f9 }
        r4 = "font_size";
        r6 = r3.getTextSize();	 Catch:{ JSONException -> 0x00f9 }
        r6 = (double) r6;	 Catch:{ JSONException -> 0x00f9 }
        r5.put(r4, r6);	 Catch:{ JSONException -> 0x00f9 }
        r4 = "text";
        r3 = r3.getText();	 Catch:{ JSONException -> 0x00f9 }
        r5.put(r4, r3);	 Catch:{ JSONException -> 0x00f9 }
    L_0x00ee:
        r2 = r2.getKey();	 Catch:{ JSONException -> 0x00f9 }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x00f9 }
        r0.put(r2, r5);	 Catch:{ JSONException -> 0x00f9 }
        goto L_0x0018;
    L_0x00f9:
        r2 = "Unable to get asset views information";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r2);	 Catch:{ all -> 0x0102 }
        goto L_0x0018;
    L_0x0100:
        monitor-exit(r13);	 Catch:{ all -> 0x0102 }
        return r0;
    L_0x0102:
        r14 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x0102 }
        throw r14;
    L_0x0105:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzach.zza(java.util.Map, android.view.View):org.json.JSONObject");
    }

    @Nullable
    public final zzawv zzsu() {
        if (!zzbv.zzmf().zzv(this.mContext)) {
            return null;
        }
        if (this.zzbor == null) {
            this.zzbor = new zzawv(this.mContext, this.zzdby.getAdUnitId());
        }
        return this.zzbor;
    }
}
