package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.internal.NativeProtocol;
import com.google.api.client.http.HttpStatusCodes;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.NativeVideoView;
import com.inmobi.ads.NativeVideoWrapper;
import com.inmobi.ads.ah;
import com.inmobi.ads.ao;
import com.inmobi.ads.bd;
import com.inmobi.ads.be;
import com.inmobi.ads.c;
import com.inmobi.ads.c.k;
import com.inmobi.ads.ca;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(15)
public class InMobiAdActivity extends Activity {
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, a> b = new HashMap();
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> c = new HashMap();
    public static Integer d = Integer.valueOf(0);
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, b> e = new HashMap();
    public static Integer f = Integer.valueOf(0);
    private static final String g = "InMobiAdActivity";
    private static SparseArray<AdContainer> h = new SparseArray();
    private static RenderView i;
    private static com.inmobi.rendering.RenderView.a j;
    public boolean a = false;
    private AdContainer k;
    private RenderView l;
    private CustomView m;
    private CustomView n;
    private NativeVideoView o;
    private int p;
    private int q;
    private boolean r = false;
    private boolean s = false;

    public interface a {
        void a();
    }

    public interface b {
        void a(int[] iArr);
    }

    public static int a(AdContainer adContainer) {
        int hashCode = adContainer.hashCode();
        h.put(hashCode, adContainer);
        return hashCode;
    }

    public static void a(@NonNull Object obj) {
        h.remove(obj.hashCode());
    }

    public static void a(RenderView renderView) {
        i = renderView;
    }

    public static void a(com.inmobi.rendering.RenderView.a aVar) {
        j = aVar;
    }

    public static int a(Intent intent, a aVar) {
        d = Integer.valueOf(d.intValue() + 1);
        b.put(d, aVar);
        c.put(d, intent);
        return d.intValue();
    }

    public static void a(String[] strArr, b bVar) {
        try {
            if (com.inmobi.commons.a.a.a()) {
                f = Integer.valueOf(f.intValue() + 1);
                e.put(f, bVar);
                int intValue = f.intValue();
                Intent intent = new Intent(com.inmobi.commons.a.a.b(), InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 104);
                intent.putExtra("id", intValue);
                intent.putExtra(NativeProtocol.RESULT_ARGS_PERMISSIONS, strArr);
                com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.b(), intent);
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error while requesting permissions; ").append(e.getMessage());
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (!this.a) {
            if (100 == this.p) {
                if (!(this.l == null || this.l.getFullScreenEventsListener() == null)) {
                    try {
                        if (!this.r) {
                            this.r = true;
                            this.l.getFullScreenEventsListener().a(this.l);
                        }
                    } catch (Exception unused) {
                    }
                }
            } else if (this.q == 200 && 102 == this.p) {
                if (!(this.k == null || this.k.getFullScreenEventsListener() == null)) {
                    if (!this.r) {
                        this.r = true;
                        this.k.getFullScreenEventsListener().a(null);
                    }
                }
            } else if (HttpStatusCodes.STATUS_CODE_CREATED == this.q) {
                if ((this.k instanceof bd) && this.o != null) {
                    final be beVar = (be) this.o.getTag();
                    if (beVar != null && this.s) {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public final void run() {
                                if (InMobiAdActivity.this.k == null) {
                                    return;
                                }
                                if (InMobiAdActivity.this.k.getRenderingProperties().a != PlacementType.PLACEMENT_TYPE_FULLSCREEN || !((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                                    InMobiAdActivity.this.o.start();
                                }
                            }
                        }, 50);
                    }
                    if (this.k.getFullScreenEventsListener() != null) {
                        try {
                            if (!this.r) {
                                this.r = true;
                                this.k.getFullScreenEventsListener().a(beVar);
                            }
                        } catch (Exception e) {
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        }
                    }
                } else if (this.k instanceof ah) {
                    try {
                        if (!this.r) {
                            this.r = true;
                            this.k.getFullScreenEventsListener().a(null);
                        }
                    } catch (Exception e2) {
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                    }
                }
            }
            this.s = false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        if (!(this.a || 102 != this.p || this.k == null)) {
            ca viewableAd = this.k.getViewableAd();
            if (200 == this.q) {
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.k.getRenderingProperties().a) {
                    try {
                        viewableAd.a(this.m, this.n);
                    } catch (Exception e) {
                        new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e.getMessage());
                        if (this.k.getFullScreenEventsListener() != null) {
                            this.k.getFullScreenEventsListener().a();
                        }
                    }
                }
            } else if (HttpStatusCodes.STATUS_CODE_CREATED == this.q) {
                try {
                    com.inmobi.commons.core.configs.a cVar = new c();
                    com.inmobi.commons.core.configs.b.a().a(cVar, null);
                    if (viewableAd.b() != null) {
                        if (this.k instanceof bd) {
                            be beVar = (be) this.o.getTag();
                            if (beVar != null) {
                                k kVar = cVar.k;
                                int i = kVar.g;
                                if (beVar.G.containsKey("time")) {
                                    i = ((Integer) beVar.G.get("time")).intValue();
                                }
                                kVar.g = i;
                                viewableAd.a(new View[0]);
                            }
                        } else if (this.k instanceof ah) {
                            try {
                                viewableAd.a(new View[0]);
                            } catch (Exception e2) {
                                new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e2.getMessage());
                                if (this.k.getFullScreenEventsListener() != null) {
                                    this.k.getFullScreenEventsListener().a();
                                }
                            }
                        }
                    }
                } catch (Exception e22) {
                    new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e22.getMessage());
                    if (this.k.getFullScreenEventsListener() != null) {
                        this.k.getFullScreenEventsListener().a();
                    }
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22));
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(23)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.inmobi.commons.a.a.a()) {
            this.r = false;
            this.p = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
            RelativeLayout relativeLayout;
            float f;
            LayoutParams layoutParams;
            if (this.p == 100) {
                c adConfig;
                String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
                long longExtra = getIntent().getLongExtra(AudienceNetworkActivity.PLACEMENT_ID, Long.MIN_VALUE);
                boolean booleanExtra = getIntent().getBooleanExtra("allowAutoRedirection", false);
                String stringExtra2 = getIntent().getStringExtra("impressionId");
                String stringExtra3 = getIntent().getStringExtra("creativeId");
                this.l = new RenderView(this, new RenderingProperties(PlacementType.PLACEMENT_TYPE_FULLSCREEN), null, stringExtra2);
                this.l.setPlacementId(longExtra);
                this.l.setCreativeId(stringExtra3);
                this.l.setAllowAutoRedirection(booleanExtra);
                com.inmobi.rendering.RenderView.a aVar = RenderView.a;
                if (i != null) {
                    aVar = i.getListener();
                    adConfig = i.getAdConfig();
                } else {
                    adConfig = new c();
                    if (j != null) {
                        aVar = j;
                    }
                }
                this.l.setIsInAppBrowser(true);
                this.l.a(aVar, adConfig);
                relativeLayout = new RelativeLayout(this);
                LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                layoutParams2.addRule(10);
                layoutParams2.addRule(2, 65533);
                relativeLayout.setBackgroundColor(-1);
                relativeLayout.addView(this.l, layoutParams2);
                f = com.inmobi.commons.core.utilities.b.c.a().c;
                LinearLayout linearLayout = new LinearLayout(this);
                layoutParams = new LayoutParams(-1, (int) (48.0f * f));
                linearLayout.setOrientation(0);
                linearLayout.setId(65533);
                linearLayout.setWeightSum(100.0f);
                linearLayout.setBackgroundResource(17301658);
                linearLayout.setBackgroundColor(-7829368);
                layoutParams.addRule(12);
                relativeLayout.addView(linearLayout, layoutParams);
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
                layoutParams3.weight = 25.0f;
                CustomView customView = new CustomView(this, f, 2);
                customView.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            view.setBackgroundColor(-7829368);
                            InMobiAdActivity.this.a = true;
                            InMobiAdActivity.this.finish();
                            return true;
                        } else if (motionEvent.getAction() != 0) {
                            return true;
                        } else {
                            view.setBackgroundColor(-16711681);
                            return true;
                        }
                    }
                });
                linearLayout.addView(customView, layoutParams3);
                customView = new CustomView(this, f, 3);
                customView.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            view.setBackgroundColor(-7829368);
                            InMobiAdActivity.this.l.reload();
                            return true;
                        } else if (motionEvent.getAction() != 0) {
                            return true;
                        } else {
                            view.setBackgroundColor(-16711681);
                            return true;
                        }
                    }
                });
                linearLayout.addView(customView, layoutParams3);
                customView = new CustomView(this, f, 4);
                customView.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            view.setBackgroundColor(-7829368);
                            if (InMobiAdActivity.this.l.canGoBack()) {
                                InMobiAdActivity.this.l.goBack();
                            } else {
                                InMobiAdActivity.this.a = true;
                                InMobiAdActivity.this.finish();
                            }
                            return true;
                        } else if (motionEvent.getAction() != 0) {
                            return true;
                        } else {
                            view.setBackgroundColor(-16711681);
                            return true;
                        }
                    }
                });
                linearLayout.addView(customView, layoutParams3);
                customView = new CustomView(this, f, 6);
                customView.setOnTouchListener(new OnTouchListener() {
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1) {
                            view.setBackgroundColor(-7829368);
                            if (InMobiAdActivity.this.l.canGoForward()) {
                                InMobiAdActivity.this.l.goForward();
                            }
                            return true;
                        } else if (motionEvent.getAction() != 0) {
                            return true;
                        } else {
                            view.setBackgroundColor(-16711681);
                            return true;
                        }
                    }
                });
                linearLayout.addView(customView, layoutParams3);
                setContentView(relativeLayout);
                this.l.loadUrl(stringExtra);
                this.l.setFullScreenActivityContext(this);
                return;
            }
            int intExtra;
            if (this.p == 102) {
                if (getIntent().hasExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX")) {
                    this.k = (AdContainer) h.get(getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", -1));
                    if (this.k == null) {
                        finish();
                        return;
                    }
                    this.q = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 0);
                    if (this.q == 0) {
                        if (this.k.getFullScreenEventsListener() != null) {
                            this.k.getFullScreenEventsListener().a();
                        }
                        finish();
                        return;
                    }
                    if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                        requestWindowFeature(1);
                        getWindow().setFlags(1024, 1024);
                    }
                    if ((200 != this.q || "html".equals(this.k.getMarkupType())) && (HttpStatusCodes.STATUS_CODE_CREATED != this.q || "inmobiJson".equals(this.k.getMarkupType()))) {
                        try {
                            this.k.setFullScreenActivityContext(this);
                            FrameLayout frameLayout = (FrameLayout) findViewById(16908290);
                            relativeLayout = new RelativeLayout(getApplicationContext());
                            relativeLayout.setId(65534);
                            f = com.inmobi.commons.core.utilities.b.c.a().c;
                            if ("html".equals(this.k.getMarkupType())) {
                                relativeLayout.setBackgroundColor(0);
                                layoutParams = new LayoutParams(-1, -1);
                                layoutParams.addRule(10);
                                int i = (int) (50.0f * f);
                                LayoutParams layoutParams4 = new LayoutParams(i, i);
                                layoutParams4.addRule(11);
                                this.m = new CustomView(this, f, 0);
                                this.m.setId(65532);
                                this.m.setOnClickListener(new OnClickListener() {
                                    public final void onClick(View view) {
                                        InMobiAdActivity.this.a = true;
                                        try {
                                            InMobiAdActivity.this.k.b();
                                        } catch (Exception e) {
                                            InMobiAdActivity.g;
                                            new StringBuilder("Encountered unexpected error in processing close request: ").append(e.getMessage());
                                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                                        }
                                    }
                                });
                                this.n = new CustomView(this, f, 1);
                                this.n.setId(65531);
                                this.n.setOnClickListener(new OnClickListener() {
                                    public final void onClick(View view) {
                                        InMobiAdActivity.this.a = true;
                                        try {
                                            InMobiAdActivity.this.k.b();
                                        } catch (Exception e) {
                                            InMobiAdActivity.g;
                                            new StringBuilder("Encountered unexpected error in processing close request: ").append(e.getMessage());
                                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                                        }
                                    }
                                });
                                View a = this.k.getViewableAd().a();
                                if (a != null) {
                                    ViewGroup viewGroup = (ViewGroup) a.getParent();
                                    if (viewGroup != null) {
                                        viewGroup.removeView(a);
                                    }
                                    relativeLayout.addView(a, layoutParams);
                                    relativeLayout.addView(this.m, layoutParams4);
                                    relativeLayout.addView(this.n, layoutParams4);
                                    ((RenderView) this.k).a(((RenderView) this.k).p);
                                    ((RenderView) this.k).b(((RenderView) this.k).m);
                                }
                            } else if ("inmobiJson".equals(this.k.getMarkupType())) {
                                PlacementType placementType = this.k.getRenderingProperties().a;
                                relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                                ao aoVar = (ao) this.k.getDataModel();
                                Point point = aoVar.d.c.a;
                                com.inmobi.commons.core.configs.b.a().a(new c(), null);
                                ca viewableAd = this.k.getViewableAd();
                                View b = aoVar.c ? viewableAd.b() : null;
                                if (b == null) {
                                    b = viewableAd.a(null, relativeLayout, false);
                                }
                                if (this.k instanceof bd) {
                                    NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.k.getVideoContainerView();
                                    if (nativeVideoWrapper != null) {
                                        this.o = nativeVideoWrapper.getVideoView();
                                        this.o.requestFocus();
                                        be beVar = (be) this.o.getTag();
                                        if (beVar.y != null) {
                                            beVar.a((be) beVar.y);
                                        }
                                        if (PlacementType.PLACEMENT_TYPE_INLINE == placementType) {
                                            beVar.v.put("placementType", PlacementType.PLACEMENT_TYPE_INLINE);
                                        } else {
                                            beVar.v.put("placementType", PlacementType.PLACEMENT_TYPE_FULLSCREEN);
                                        }
                                    }
                                }
                                if (b != null) {
                                    relativeLayout.addView(b, new LayoutParams(point.x, point.y));
                                }
                                this.k.setRequestedScreenOrientation();
                            } else {
                                if (this.k.getFullScreenEventsListener() != null) {
                                    this.k.getFullScreenEventsListener().a();
                                }
                                finish();
                                return;
                            }
                            frameLayout.addView(relativeLayout, new LayoutParams(-1, -1));
                            return;
                        } catch (Exception e) {
                            this.k.setFullScreenActivityContext(null);
                            if (this.k.getFullScreenEventsListener() != null) {
                                this.k.getFullScreenEventsListener().a();
                            }
                            finish();
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            return;
                        }
                    }
                    if (this.k.getFullScreenEventsListener() != null) {
                        this.k.getFullScreenEventsListener().a();
                    }
                    finish();
                    return;
                }
            } else if (this.p == 103) {
                intExtra = getIntent().getIntExtra("id", -1);
                if (intExtra != -1) {
                    startActivityForResult((Intent) c.get(Integer.valueOf(intExtra)), intExtra);
                }
                return;
            } else if (this.p == 104) {
                intExtra = getIntent().getIntExtra("id", -1);
                if (intExtra != -1) {
                    String[] stringArrayExtra = getIntent().getStringArrayExtra(NativeProtocol.RESULT_ARGS_PERMISSIONS);
                    if (stringArrayExtra != null && stringArrayExtra.length > 0 && VERSION.SDK_INT >= 23) {
                        com.inmobi.commons.core.utilities.a.b();
                        requestPermissions(stringArrayExtra, intExtra);
                    }
                }
            }
            return;
        }
        finish();
        Logger.a(InternalLogLevel.DEBUG, "InMobi", "Session not found, AdActivity will be closed");
    }

    public void onStop() {
        super.onStop();
        if (!this.a) {
            this.s = true;
            if (this.o != null) {
                this.o.pause();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        if (this.a) {
            if (100 == this.p) {
                if (!(this.l == null || this.l.getFullScreenEventsListener() == null)) {
                    try {
                        this.l.getFullScreenEventsListener().b(this.l);
                        this.l.destroy();
                        this.l = null;
                    } catch (Exception unused) {
                    }
                }
            } else if (!(102 != this.p || this.k == null || this.k.getFullScreenEventsListener() == null)) {
                if (200 == this.q) {
                    try {
                        this.k.getFullScreenEventsListener().b(null);
                    } catch (Exception e) {
                        new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e.getMessage());
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                    }
                } else if (HttpStatusCodes.STATUS_CODE_CREATED == this.q && VERSION.SDK_INT >= 15) {
                    if (this.k instanceof bd) {
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ((bd) this.k).getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            try {
                                this.k.getFullScreenEventsListener().b((be) nativeVideoWrapper.getVideoView().getTag());
                            } catch (Exception e2) {
                                new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e2.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                            }
                        }
                    } else if (this.k instanceof ah) {
                        try {
                            this.k.getFullScreenEventsListener().b(null);
                        } catch (Exception e22) {
                            new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e22.getMessage());
                            Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22));
                        }
                    }
                }
            }
            if (this.k != null) {
                this.k.destroy();
                this.k = null;
            }
        } else if (!(100 == this.p || 102 != this.p || this.k == null)) {
            if (200 == this.q) {
                RenderView renderView = (RenderView) this.k;
                renderView.setFullScreenActivityContext(null);
                try {
                    renderView.b();
                } catch (Exception e222) {
                    new StringBuilder("Encountered unexpected error in processing close request: ").append(e222.getMessage());
                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                }
            } else if (HttpStatusCodes.STATUS_CODE_CREATED == this.q && VERSION.SDK_INT >= 15) {
                if (this.k instanceof bd) {
                    bd bdVar = (bd) this.k;
                    if (this.o != null) {
                        be beVar = (be) this.o.getTag();
                        if (beVar != null) {
                            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                                this.o.a();
                            }
                            if (this.k.getFullScreenEventsListener() != null) {
                                try {
                                    this.k.getFullScreenEventsListener().b(beVar);
                                } catch (Exception e2222) {
                                    new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e2222.getMessage());
                                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2222));
                                }
                            }
                        }
                    }
                } else if ((this.k instanceof ah) && this.k.getFullScreenEventsListener() != null) {
                    try {
                        this.k.getFullScreenEventsListener().b(null);
                    } catch (Exception e22222) {
                        new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e22222.getMessage());
                        Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e22222));
                    }
                }
            }
            a(this.k);
            this.k.destroy();
            this.k = null;
        }
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.l != null) {
            RenderView renderView = this.l;
            if ("Resized".equals(renderView.d) && renderView.getResizeProperties() != null) {
                renderView.g.a();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a aVar = (a) b.remove(Integer.valueOf(i));
        if (aVar != null) {
            c.remove(Integer.valueOf(i));
            aVar.a();
            this.a = true;
            finish();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (!z) {
            if (this.l != null) {
                this.l.setOrientationProperties(this.l.getOrientationProperties());
            }
            if (this.k != null) {
                this.k.setRequestedScreenOrientation();
            }
        }
    }

    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        onMultiWindowModeChanged(z);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        com.inmobi.commons.core.utilities.a.c();
        b bVar = (b) e.remove(Integer.valueOf(i));
        if (bVar != null) {
            bVar.a(iArr);
        }
        finish();
    }

    public void onBackPressed() {
        if (this.p == 102) {
            if (this.k != null && !this.k.c()) {
                boolean z = false;
                if (200 == this.q) {
                    RenderView renderView = (RenderView) this.k;
                    if (renderView != null) {
                        if (renderView.r != null) {
                            z = true;
                        }
                        if (z) {
                            renderView.a(renderView.r, "broadcastEvent('backButtonPressed')");
                        }
                        if (!renderView.q) {
                            this.a = true;
                            try {
                                renderView.b();
                            } catch (Exception e) {
                                new StringBuilder("Encountered unexpected error in processing close request: ").append(e.getMessage());
                                Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                            }
                        }
                    }
                } else if (this.k instanceof bd) {
                    bd bdVar = (bd) this.k;
                    if (bdVar != null && !bdVar.h().b) {
                        this.a = true;
                        if (this.o != null) {
                            be beVar = (be) this.o.getTag();
                            if (beVar != null) {
                                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                                    this.o.a();
                                }
                                try {
                                    if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                                        beVar.v.put("seekPosition", Integer.valueOf(this.o.getCurrentPosition()));
                                        if (!bdVar.l && ((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                            beVar.v.put("didRequestFullScreen", Boolean.valueOf(false));
                                            if (beVar.y != null) {
                                                beVar.y.v.put("didRequestFullScreen", Boolean.valueOf(false));
                                            }
                                            bdVar.b();
                                            beVar.v.put("isFullScreen", Boolean.valueOf(false));
                                        }
                                    }
                                    return;
                                } catch (Exception e2) {
                                    new StringBuilder("Encountered unexpected error in onVideoClosed handler: ").append(e2.getMessage());
                                    Logger.a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in closing video");
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                                }
                            }
                            return;
                        }
                        finish();
                    }
                } else if (this.k instanceof ah) {
                    ah ahVar = (ah) this.k;
                    if (ahVar == null) {
                        finish();
                    } else if (!ahVar.h().b) {
                        ahVar.b();
                    }
                }
            }
        } else if (this.p == 100) {
            this.a = true;
            finish();
        }
    }
}
