package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager.BadTokenException;
import com.google.ads.AdRequest;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzazc {
    private Handler handler;
    private final Context mContext;
    private int state;
    private String zzboa;
    private String zzbuk;
    private final float zzdqe;
    @Nullable
    private String zzebe;
    private String zzelo;
    private float zzelp;
    private float zzelq;
    private float zzelr;
    private int zzels;
    private float zzelt;
    private float zzelu;
    private float zzelv;
    private float zzelw;
    private Runnable zzelx;

    public zzazc(Context context) {
        this.state = 0;
        this.zzelx = new zzazd(this);
        this.mContext = context;
        this.zzdqe = context.getResources().getDisplayMetrics().density;
        this.zzels = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        zzbv.zzlv().zzaak();
        this.handler = zzbv.zzlv().getHandler();
    }

    public zzazc(Context context, String str) {
        this(context);
        this.zzelo = str;
    }

    public final void zze(MotionEvent motionEvent) {
        int actionMasked;
        int historySize;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwd)).booleanValue()) {
            actionMasked = motionEvent.getActionMasked();
            historySize = motionEvent.getHistorySize();
            int pointerCount = motionEvent.getPointerCount();
            if (actionMasked == 0) {
                this.state = 0;
                this.zzelt = motionEvent.getX();
                this.zzelu = motionEvent.getY();
                return;
            }
            if (this.state != -1) {
                int i = 1;
                if (this.state == 0 && actionMasked == 5) {
                    this.state = 5;
                    this.zzelv = motionEvent.getX(1);
                    this.zzelw = motionEvent.getY(1);
                    this.handler.postDelayed(this.zzelx, ((Long) zzwu.zzpz().zzd(zzaan.zzcwe)).longValue());
                    return;
                } else if (this.state == 5) {
                    if (pointerCount == 2) {
                        if (actionMasked == 2) {
                            actionMasked = 0;
                            pointerCount = actionMasked;
                            while (actionMasked < historySize) {
                                if (!zza(motionEvent.getHistoricalX(0, actionMasked), motionEvent.getHistoricalY(0, actionMasked), motionEvent.getHistoricalX(1, actionMasked), motionEvent.getHistoricalY(1, actionMasked))) {
                                    pointerCount = 1;
                                }
                                actionMasked++;
                            }
                            if (zza(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                                i = pointerCount;
                            }
                        } else {
                            i = 0;
                        }
                    }
                    if (i != 0) {
                        this.state = -1;
                        this.handler.removeCallbacks(this.zzelx);
                    }
                }
            }
            return;
        }
        actionMasked = motionEvent.getHistorySize();
        for (historySize = 0; historySize < actionMasked; historySize++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, historySize), motionEvent.getHistoricalY(0, historySize));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    private final boolean zza(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzelt - f) < ((float) this.zzels) && Math.abs(this.zzelu - f2) < ((float) this.zzels) && Math.abs(this.zzelv - f3) < ((float) this.zzels) && Math.abs(this.zzelw - f4) < ((float) this.zzels);
    }

    @VisibleForTesting
    private final void zza(int i, float f, float f2) {
        if (i == 0) {
            this.state = 0;
            this.zzelp = f;
            this.zzelq = f2;
            this.zzelr = f2;
        } else if (this.state != -1) {
            if (i == 2) {
                if (f2 > this.zzelq) {
                    this.zzelq = f2;
                } else if (f2 < this.zzelr) {
                    this.zzelr = f2;
                }
                if (this.zzelq - this.zzelr > 30.0f * this.zzdqe) {
                    this.state = -1;
                    return;
                }
                if (this.state == 0 || this.state == 2) {
                    if (f - this.zzelp >= 50.0f * this.zzdqe) {
                        this.zzelp = f;
                        this.state++;
                    }
                } else if ((this.state == 1 || this.state == 3) && f - this.zzelp <= -50.0f * this.zzdqe) {
                    this.zzelp = f;
                    this.state++;
                }
                if (this.state == 1 || this.state == 3) {
                    if (f > this.zzelp) {
                        this.zzelp = f;
                    }
                } else if (this.state == 2 && f < this.zzelp) {
                    this.zzelp = f;
                }
            } else if (i == 1 && this.state == 4) {
                showDialog();
            }
        }
    }

    public final void showDialog() {
        String str;
        try {
            if (this.mContext instanceof Activity) {
                String str2 = !TextUtils.isEmpty(zzbv.zzlp().zzaag()) ? "Creative Preview (Enabled)" : "Creative Preview";
                str = zzbv.zzlp().zzaah() ? "Troubleshooting (Enabled)" : "Troubleshooting";
                List arrayList = new ArrayList();
                int zza = zza(arrayList, "Ad Information", true);
                int zza2 = zza(arrayList, str2, true);
                int zza3 = zza(arrayList, str, true);
                Builder builder = new Builder(this.mContext, zzbv.zzlh().zzaab());
                builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new zzaze(this, zza, zza2, zza3));
                builder.create().show();
                return;
            }
            zzbbd.zzen("Can not create dialog without Activity Context");
        } catch (BadTokenException e) {
            str = "";
            if (zzaxz.zzza()) {
                Log.v(AdRequest.LOGTAG, str, e);
            }
        }
    }

    public final void setAdUnitId(String str) {
        this.zzboa = str;
    }

    public final void zzee(String str) {
        this.zzbuk = str;
    }

    public final void zzef(String str) {
        this.zzelo = str;
    }

    public final void zzeg(String str) {
        this.zzebe = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        list.add(str);
        return list.size() - 1;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzaad() {
        zzbv.zzlp().zza(this.mContext, this.zzboa, this.zzbuk, this.zzebe);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzaae() {
        zzbv.zzlp().zzf(this.mContext, this.zzboa, this.zzbuk);
    }

    /* Access modifiers changed, original: final|synthetic */
    /* JADX WARNING: Missing block: B:12:0x006e, code skipped:
            if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x0073;
     */
    public final /* synthetic */ void zza(int r1, int r2, int r3, android.content.DialogInterface r4, int r5) {
        /*
        r0 = this;
        if (r5 != r1) goto L_0x009b;
    L_0x0002:
        r1 = r0.mContext;
        r1 = r1 instanceof android.app.Activity;
        if (r1 != 0) goto L_0x000e;
    L_0x0008:
        r1 = "Can not create dialog without Activity Context";
        com.google.android.gms.internal.ads.zzbbd.zzen(r1);
        return;
    L_0x000e:
        r1 = r0.zzelo;
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0071;
    L_0x0016:
        r2 = "\\+";
        r3 = "%20";
        r1 = r1.replaceAll(r2, r3);
        r2 = new android.net.Uri$Builder;
        r2.<init>();
        r1 = r2.encodedQuery(r1);
        r1 = r1.build();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        com.google.android.gms.ads.internal.zzbv.zzlf();
        r1 = com.google.android.gms.internal.ads.zzayh.zzg(r1);
        r3 = r1.keySet();
        r3 = r3.iterator();
    L_0x003f:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0062;
    L_0x0045:
        r4 = r3.next();
        r4 = (java.lang.String) r4;
        r2.append(r4);
        r5 = " = ";
        r2.append(r5);
        r4 = r1.get(r4);
        r4 = (java.lang.String) r4;
        r2.append(r4);
        r4 = "\n\n";
        r2.append(r4);
        goto L_0x003f;
    L_0x0062:
        r1 = r2.toString();
        r1 = r1.trim();
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0071;
    L_0x0070:
        goto L_0x0073;
    L_0x0071:
        r1 = "No debug information";
    L_0x0073:
        r2 = new android.app.AlertDialog$Builder;
        r3 = r0.mContext;
        r2.<init>(r3);
        r2.setMessage(r1);
        r3 = "Ad Information";
        r2.setTitle(r3);
        r3 = "Share";
        r4 = new com.google.android.gms.internal.ads.zzazf;
        r4.<init>(r0, r1);
        r2.setPositiveButton(r3, r4);
        r1 = "Close";
        r3 = com.google.android.gms.internal.ads.zzazg.zzemc;
        r2.setNegativeButton(r1, r3);
        r1 = r2.create();
        r1.show();
        return;
    L_0x009b:
        if (r5 != r2) goto L_0x00ab;
    L_0x009d:
        r1 = "Debug mode [Creative Preview] selected.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);
        r1 = new com.google.android.gms.internal.ads.zzazh;
        r1.<init>(r0);
        com.google.android.gms.internal.ads.zzayf.zzc(r1);
        return;
    L_0x00ab:
        if (r5 != r3) goto L_0x00ba;
    L_0x00ad:
        r1 = "Debug mode [Troubleshooting] selected.";
        com.google.android.gms.internal.ads.zzbbd.zzdn(r1);
        r1 = new com.google.android.gms.internal.ads.zzazi;
        r1.<init>(r0);
        com.google.android.gms.internal.ads.zzayf.zzc(r1);
    L_0x00ba:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzazc.zza(int, int, int, android.content.DialogInterface, int):void");
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzaaf() {
        this.state = 4;
        showDialog();
    }
}
