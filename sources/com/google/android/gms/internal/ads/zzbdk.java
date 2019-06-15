package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@zzark
public final class zzbdk extends FrameLayout implements zzbdh {
    private final zzbdz zzerc;
    private final FrameLayout zzerd;
    private final zzaba zzere;
    private final zzbeb zzerf;
    private final long zzerg;
    @Nullable
    private zzbdi zzerh;
    private boolean zzeri;
    private boolean zzerj;
    private boolean zzerk;
    private boolean zzerl;
    private long zzerm;
    private long zzern;
    private String zzero;
    private Bitmap zzerp;
    private ImageView zzerq;
    private boolean zzerr;

    public static void zzb(zzbdz zzbdz) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzbdz.zza("onVideoEvent", (Map) hashMap);
    }

    public static void zza(zzbdz zzbdz, Map<String, List<Map<String, Object>>> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("mimeTypes", map);
        zzbdz.zza("onVideoEvent", (Map) hashMap);
    }

    public static void zza(zzbdz zzbdz, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("error", str);
        zzbdz.zza("onVideoEvent", (Map) hashMap);
    }

    public zzbdk(Context context, zzbdz zzbdz, int i, boolean z, zzaba zzaba, zzbdy zzbdy) {
        Context context2 = context;
        super(context2);
        zzbdz zzbdz2 = zzbdz;
        this.zzerc = zzbdz2;
        zzaba zzaba2 = zzaba;
        this.zzere = zzaba2;
        this.zzerd = new FrameLayout(context2);
        addView(this.zzerd, new LayoutParams(-1, -1));
        Preconditions.checkNotNull(zzbdz2.zzid());
        this.zzerh = zzbdz2.zzid().zzbmt.zza(context2, zzbdz2, i, z, zzaba2, zzbdy);
        if (this.zzerh != null) {
            this.zzerd.addView(this.zzerh, new LayoutParams(-1, -1, 17));
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcpc)).booleanValue()) {
                zzabl();
            }
        }
        this.zzerq = new ImageView(context2);
        this.zzerg = ((Long) zzwu.zzpz().zzd(zzaan.zzcpg)).longValue();
        this.zzerl = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcpe)).booleanValue();
        if (this.zzere != null) {
            this.zzere.zzg("spinner_used", this.zzerl ? "1" : "0");
        }
        this.zzerf = new zzbeb(this);
        if (this.zzerh != null) {
            this.zzerh.zza(this);
        }
        if (this.zzerh == null) {
            zzi("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            LayoutParams layoutParams = new LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzerd.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzer(String str) {
        this.zzero = str;
    }

    public final void zza(float f, float f2) {
        if (this.zzerh != null) {
            this.zzerh.zza(f, f2);
        }
    }

    public final void zzff() {
        if (this.zzerh != null) {
            if (TextUtils.isEmpty(this.zzero)) {
                zzb("no_src", new String[0]);
            } else {
                this.zzerh.setVideoPath(this.zzero);
            }
        }
    }

    public final void pause() {
        if (this.zzerh != null) {
            this.zzerh.pause();
        }
    }

    public final void play() {
        if (this.zzerh != null) {
            this.zzerh.play();
        }
    }

    public final void seekTo(int i) {
        if (this.zzerh != null) {
            this.zzerh.seekTo(i);
        }
    }

    public final void zzabj() {
        if (this.zzerh != null) {
            zzbdi zzbdi = this.zzerh;
            zzbdi.zzerb.setMuted(true);
            zzbdi.zzabd();
        }
    }

    public final void zzabk() {
        if (this.zzerh != null) {
            zzbdi zzbdi = this.zzerh;
            zzbdi.zzerb.setMuted(false);
            zzbdi.zzabd();
        }
    }

    public final void setVolume(float f) {
        if (this.zzerh != null) {
            zzbdi zzbdi = this.zzerh;
            zzbdi.zzerb.setVolume(f);
            zzbdi.zzabd();
        }
    }

    public final void zzcz(int i) {
        this.zzerh.zzcz(i);
    }

    public final void zzda(int i) {
        this.zzerh.zzda(i);
    }

    public final void zzdb(int i) {
        this.zzerh.zzdb(i);
    }

    public final void zzdc(int i) {
        this.zzerh.zzdc(i);
    }

    @TargetApi(14)
    public final void zzf(MotionEvent motionEvent) {
        if (this.zzerh != null) {
            this.zzerh.dispatchTouchEvent(motionEvent);
        }
    }

    @TargetApi(14)
    public final void zzabl() {
        if (this.zzerh != null) {
            TextView textView = new TextView(this.zzerh.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzerh.zzaaz());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzerd.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzerd.bringChildToFront(textView);
        }
    }

    public final void zzabe() {
        this.zzerf.resume();
        zzayh.zzelc.post(new zzbdn(this));
    }

    public final void zzcg() {
        if (this.zzerh != null && this.zzern == 0) {
            float duration = ((float) this.zzerh.getDuration()) / 1000.0f;
            int videoWidth = this.zzerh.getVideoWidth();
            int videoHeight = this.zzerh.getVideoHeight();
            zzb("canplaythrough", "duration", String.valueOf(duration), "videoWidth", String.valueOf(videoWidth), "videoHeight", String.valueOf(videoHeight));
        }
    }

    public final void zzabf() {
        if (!(this.zzerc.zzabw() == null || this.zzerj)) {
            this.zzerk = (this.zzerc.zzabw().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzerk) {
                this.zzerc.zzabw().getWindow().addFlags(128);
                this.zzerj = true;
            }
        }
        this.zzeri = true;
    }

    public final void onPaused() {
        zzb("pause", new String[0]);
        zzabo();
        this.zzeri = false;
    }

    public final void zzabg() {
        zzb("ended", new String[0]);
        zzabo();
    }

    public final void zzi(String str, @Nullable String str2) {
        zzb("error", "what", str, "extra", str2);
    }

    public final void zzabh() {
        if (!(!this.zzerr || this.zzerp == null || zzabn())) {
            this.zzerq.setImageBitmap(this.zzerp);
            this.zzerq.invalidate();
            this.zzerd.addView(this.zzerq, new LayoutParams(-1, -1));
            this.zzerd.bringChildToFront(this.zzerq);
        }
        this.zzerf.pause();
        this.zzern = this.zzerm;
        zzayh.zzelc.post(new zzbdo(this));
    }

    public final void destroy() {
        this.zzerf.pause();
        if (this.zzerh != null) {
            this.zzerh.stop();
        }
        zzabo();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzerf.pause();
            if (this.zzerh != null) {
                zzbdi zzbdi = this.zzerh;
                Executor executor = zzbcg.zzepo;
                zzbdi.getClass();
                executor.execute(zzbdl.zza(zzbdi));
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    /* Access modifiers changed, original: final */
    public final void zzabm() {
        if (this.zzerh != null) {
            long currentPosition = (long) this.zzerh.getCurrentPosition();
            if (this.zzerm != currentPosition && currentPosition > 0) {
                float f = ((float) currentPosition) / 1000.0f;
                zzb("timeupdate", "time", String.valueOf(f));
                this.zzerm = currentPosition;
            }
        }
    }

    public final void zzabi() {
        if (this.zzeri && zzabn()) {
            this.zzerd.removeView(this.zzerq);
        }
        if (this.zzerp != null) {
            long elapsedRealtime = zzbv.zzlm().elapsedRealtime();
            if (this.zzerh.getBitmap(this.zzerp) != null) {
                this.zzerr = true;
            }
            long elapsedRealtime2 = zzbv.zzlm().elapsedRealtime() - elapsedRealtime;
            if (zzaxz.zzza()) {
                StringBuilder stringBuilder = new StringBuilder(46);
                stringBuilder.append("Spinner frame grab took ");
                stringBuilder.append(elapsedRealtime2);
                stringBuilder.append("ms");
                zzaxz.v(stringBuilder.toString());
            }
            if (elapsedRealtime2 > this.zzerg) {
                zzbbd.zzeo("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzerl = false;
                this.zzerp = null;
                if (this.zzere != null) {
                    this.zzere.zzg("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final void zzm(int i, int i2) {
        if (this.zzerl) {
            i = Math.max(i / ((Integer) zzwu.zzpz().zzd(zzaan.zzcpf)).intValue(), 1);
            i2 = Math.max(i2 / ((Integer) zzwu.zzpz().zzd(zzaan.zzcpf)).intValue(), 1);
            if (this.zzerp == null || this.zzerp.getWidth() != i || this.zzerp.getHeight() != i2) {
                this.zzerp = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                this.zzerr = false;
            }
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.zzerf.resume();
        } else {
            this.zzerf.pause();
            this.zzern = this.zzerm;
        }
        zzayh.zzelc.post(new zzbdm(this, z));
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.zzerf.resume();
            z = true;
        } else {
            this.zzerf.pause();
            this.zzern = this.zzerm;
            z = false;
        }
        zzayh.zzelc.post(new zzbdp(this, z));
    }

    private final boolean zzabn() {
        return this.zzerq.getParent() != null;
    }

    private final void zzb(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        Object obj = null;
        for (Object obj2 : strArr) {
            if (obj == null) {
                obj = obj2;
            } else {
                hashMap.put(obj, obj2);
                obj = null;
            }
        }
        this.zzerc.zza("onVideoEvent", (Map) hashMap);
    }

    private final void zzabo() {
        if (!(this.zzerc.zzabw() == null || !this.zzerj || this.zzerk)) {
            this.zzerc.zzabw().getWindow().clearFlags(128);
            this.zzerj = false;
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzas(boolean z) {
        zzb("windowFocusChanged", "hasWindowFocus", String.valueOf(z));
    }
}
