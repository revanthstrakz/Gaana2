package com.inmobi.rendering;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.api.client.http.HttpMethods;
import com.inmobi.a.n;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.ah;
import com.inmobi.ads.ak;
import com.inmobi.ads.br;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.mraid.MediaRenderView;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.MraidMediaProcessor.HeadphonesPluggedChangeReceiver;
import com.inmobi.rendering.mraid.MraidMediaProcessor.RingerModeChangeReceiver;
import com.inmobi.rendering.mraid.b;
import com.inmobi.rendering.mraid.g;
import com.inmobi.rendering.mraid.h;
import com.payu.custombrowser.util.CBConstant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    static final String[] a = new String[]{"tel", "sms", "calendar", "inlineVideo"};
    private static final String b = "c";
    private RenderView c;
    private RenderingProperties d;
    private g e;

    @TargetApi(16)
    private static class a implements OnGlobalLayoutListener {
        private int a;
        private int b;
        private View c;
        private final Boolean d = Boolean.valueOf(false);

        a(View view) {
            this.c = view;
        }

        public final void onGlobalLayout() {
            try {
                this.a = com.inmobi.commons.core.utilities.b.c.b(this.c.getWidth());
                this.b = com.inmobi.commons.core.utilities.b.c.b(this.c.getHeight());
                if (VERSION.SDK_INT >= 16) {
                    this.c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    this.c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                synchronized (this.d) {
                    this.d.notify();
                }
            } catch (Exception e) {
                c.b;
                new StringBuilder("SDK encountered unexpected error in JavaScriptBridge$1.onGlobalLayout(); ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getPlatform(String str) {
        return "android";
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        return "7.2.1";
    }

    @JavascriptInterface
    public String getVersion(String str) {
        return "2.0";
    }

    @JavascriptInterface
    public void log(String str, String str2) {
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
    }

    @JavascriptInterface
    public void storePicture(String str, String str2) {
    }

    c(RenderView renderView, RenderingProperties renderingProperties) {
        this.c = renderView;
        this.d = renderingProperties;
    }

    @JavascriptInterface
    public void open(final String str, final String str2) {
        if (this.c != null) {
            if (this.c.e()) {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            c.this.c.c("open", str, str2);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "open");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered unexpected error in handling open() request from creative; ").append(e.getMessage());
                        }
                    }
                });
            } else {
                this.c.c("open");
            }
        }
    }

    @JavascriptInterface
    public void openEmbedded(final String str, final String str2) {
        if (this.c != null) {
            if (this.c.e()) {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            c.this.c.c("openEmbedded", str, str2);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "openEmbedded");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered unexpected error in handling openEmbedded() request from creative; ").append(e.getMessage());
                        }
                    }
                });
            } else {
                this.c.c("openEmbedded");
            }
        }
    }

    @JavascriptInterface
    public void ping(String str, String str2, boolean z) {
        if (this.c != null) {
            StringBuilder stringBuilder;
            if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
                RenderView renderView = this.c;
                stringBuilder = new StringBuilder("Invalid URL:");
                stringBuilder.append(str2);
                renderView.b(str, stringBuilder.toString(), "ping");
                return;
            }
            stringBuilder = new StringBuilder("JavaScript called ping() URL: >>> ");
            stringBuilder.append(str2);
            stringBuilder.append(" <<<");
            try {
                new com.inmobi.rendering.a.c.AnonymousClass1(str2, z).start();
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "ping");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling ping() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void pingInWebView(String str, String str2, boolean z) {
        if (this.c != null) {
            StringBuilder stringBuilder;
            if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
                RenderView renderView = this.c;
                stringBuilder = new StringBuilder("Invalid URL:");
                stringBuilder.append(str2);
                renderView.b(str, stringBuilder.toString(), "pingInWebView");
                return;
            }
            stringBuilder = new StringBuilder("JavaScript called pingInWebView() URL: >>> ");
            stringBuilder.append(str2);
            stringBuilder.append(" <<<");
            try {
                new com.inmobi.rendering.a.c.AnonymousClass3(str2, z).start();
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "pingInWebView");
                Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling pingInWebView() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        return Integer.toString(VERSION.SDK_INT);
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        try {
            this.c.getListener().w();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "fireAdReady");
            new StringBuilder("SDK encountered unexpected error in handling fireAdReady() signal from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        try {
            this.c.getListener().y();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "fireAdFailed");
            new StringBuilder("SDK encountered unexpected error in handling fireAdFailed() signal from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String getDefaultPosition(String str) {
        if (this.c == null) {
            return new JSONObject().toString();
        }
        synchronized (this.c.getDefaultPositionMonitor()) {
            this.c.setDefaultPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.setDefaultPosition();
                    } catch (Exception e) {
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting default position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.k) {
                try {
                    this.c.getDefaultPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getDefaultPosition();
    }

    @JavascriptInterface
    public String getCurrentPosition(String str) {
        if (this.c == null) {
            return "";
        }
        synchronized (this.c.getCurrentPositionMonitor()) {
            this.c.setCurrentPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.setCurrentPosition();
                    } catch (Exception e) {
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting current position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.l) {
                try {
                    this.c.getCurrentPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getCurrentPosition();
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        if (this.c != null && !"Expanded".equals(this.c.getState())) {
            try {
                this.c.setExpandProperties(b.a(str2));
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "setExpandProperties");
                new StringBuilder("SDK encountered unexpected error in setExpandProperties(); ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        if (this.c == null) {
            return "";
        }
        return this.c.getExpandProperties().c;
    }

    @JavascriptInterface
    public void expand(final String str, final String str2) {
        if (this.d.a != PlacementType.PLACEMENT_TYPE_FULLSCREEN && this.c != null) {
            if (!this.c.e()) {
                this.c.c("expand");
            } else if (!this.c.o) {
                this.c.b(str, "Creative is not visible. Ignoring request.", "expand");
            } else if (str2 == null || str2.length() == 0 || str2.startsWith("http")) {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            RenderView a = c.this.c;
                            String str = str;
                            String str2 = str2;
                            if ("Default".equals(a.d) || "Resized".equals(a.d)) {
                                a.t = true;
                                com.inmobi.rendering.mraid.c cVar = a.f;
                                if (cVar.c == null) {
                                    cVar.c = (ViewGroup) cVar.a.getParent();
                                    cVar.d = cVar.c.indexOfChild(cVar.a);
                                }
                                if (cVar.a != null) {
                                    int a2;
                                    b expandProperties = cVar.a.getExpandProperties();
                                    cVar.b = URLUtil.isValidUrl(str2);
                                    if (cVar.b) {
                                        AdContainer renderView = new RenderView(cVar.a.getContainerContext(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), null, cVar.a.getImpressionId());
                                        renderView.a(cVar.a.getListener(), cVar.a.getAdConfig());
                                        renderView.setOriginalRenderView(cVar.a);
                                        renderView.loadUrl(str2);
                                        renderView.setPlacementId(cVar.a.getPlacementId());
                                        renderView.setAllowAutoRedirection(cVar.a.getAllowAutoRedirection());
                                        renderView.setCreativeId(cVar.a.getCreativeId());
                                        a2 = InMobiAdActivity.a(renderView);
                                        if (expandProperties != null) {
                                            renderView.setUseCustomClose(cVar.a.m);
                                        }
                                    } else {
                                        FrameLayout frameLayout = new FrameLayout(cVar.a.getContainerContext());
                                        LayoutParams layoutParams = new LayoutParams(cVar.a.getWidth(), cVar.a.getHeight());
                                        frameLayout.setId(SupportMenu.USER_MASK);
                                        cVar.c.addView(frameLayout, cVar.d, layoutParams);
                                        cVar.c.removeView(cVar.a);
                                        a2 = InMobiAdActivity.a(cVar.a);
                                    }
                                    cVar.a.getListener().G();
                                    Intent intent = new Intent(cVar.a.getContainerContext(), InMobiAdActivity.class);
                                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a2);
                                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 200);
                                    com.inmobi.commons.a.a.a(cVar.a.getContainerContext(), intent);
                                }
                                a.requestLayout();
                                a.invalidate();
                                a.n = true;
                                a.setFocusable(true);
                                a.setFocusableInTouchMode(true);
                                a.requestFocus();
                                HashMap hashMap = new HashMap();
                                hashMap.put(CBConstant.COMMAND, "expand");
                                hashMap.put("scheme", br.a(str));
                                a.c.b("CreativeInvokedAction", hashMap);
                                return;
                            }
                            new StringBuilder("Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:").append(a.d);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "expand");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to expand ad; SDK encountered an unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered unexpected error in handling expand() request; ").append(e.getMessage());
                        }
                    }
                });
            } else {
                this.c.b(str, "Invalid URL", "expand");
            }
        }
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        if (this.c != null) {
            h a = h.a(str2, this.c.getResizeProperties());
            if (a == null) {
                this.c.b(str, "setResizeProperties", "All mandatory fields are not present");
            }
            this.c.setResizeProperties(a);
        }
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        if (this.c == null) {
            return "";
        }
        h resizeProperties = this.c.getResizeProperties();
        if (resizeProperties == null) {
            return "";
        }
        return resizeProperties.a();
    }

    @JavascriptInterface
    public void resize(final String str) {
        if (this.d.a != PlacementType.PLACEMENT_TYPE_FULLSCREEN && this.c != null) {
            if (this.c.o) {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                    public final void run() {
                        try {
                            RenderView a = c.this.c;
                            String str = str;
                            if (("Default".equals(a.d) || "Resized".equals(a.d)) && a.getResizeProperties() != null) {
                                a.t = true;
                                a.g.a();
                                a.requestLayout();
                                a.invalidate();
                                a.n = true;
                                a.setFocusable(true);
                                a.setFocusableInTouchMode(true);
                                a.requestFocus();
                                a.setAndUpdateViewState("Resized");
                                a.c.c(a);
                                a.t = false;
                                HashMap hashMap = new HashMap();
                                hashMap.put(CBConstant.COMMAND, "resize");
                                hashMap.put("scheme", br.a(str));
                                a.c.b("CreativeInvokedAction", hashMap);
                            }
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "resize");
                            Logger.a(InternalLogLevel.ERROR, c.b, "Could not resize ad; SDK encountered an unexpected error");
                            c.b;
                            new StringBuilder("SDK encountered an unexpected error in handling resize() request; ").append(e.getMessage());
                        }
                    }
                });
            } else {
                this.c.b(str, "Creative is not visible. Ignoring request.", "resize");
            }
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        this.e = g.a(str2, this.c.getOrientationProperties());
        this.c.setOrientationProperties(this.e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        return this.e.d;
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        if (this.c == null) {
            return false;
        }
        return this.c.o;
    }

    @JavascriptInterface
    public void useCustomClose(final String str, final boolean z) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
            public final void run() {
                try {
                    c.this.c.b(z);
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "useCustomClose");
                    c.b;
                    new StringBuilder("SDK encountered internal error in handling useCustomClose() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void playVideo(final String str, final String str2) {
        if (this.c != null) {
            if (str2 == null || str2.trim().length() == 0 || !str2.startsWith("http") || !(str2.endsWith("mp4") || str2.endsWith("avi") || str2.endsWith("m4v"))) {
                this.c.b(str, "Null or empty or invalid media playback URL supplied", "playVideo");
                return;
            }
            StringBuilder stringBuilder = new StringBuilder("JavaScript called: playVideo (");
            stringBuilder.append(str2);
            stringBuilder.append(")");
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        RenderView a = c.this.c;
                        String str = str;
                        String trim = str2.trim();
                        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == a.e.a || "Expanded".equals(a.getViewState())) {
                            if (a.b != null) {
                                if (a.b.get() != null) {
                                    a.setAdActiveFlag(true);
                                    MraidMediaProcessor mraidMediaProcessor = a.h;
                                    Activity activity = (Activity) a.b.get();
                                    mraidMediaProcessor.b = new MediaRenderView(activity);
                                    MediaRenderView mediaRenderView = mraidMediaProcessor.b;
                                    mediaRenderView.h = MediaRenderView.a(trim);
                                    mediaRenderView.g = "anonymous";
                                    if (mediaRenderView.b == null) {
                                        mediaRenderView.b = Bitmap.createBitmap(24, 24, Config.ARGB_8888);
                                        mediaRenderView.b = MediaRenderView.b(mediaRenderView.h);
                                    }
                                    ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
                                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                                    layoutParams.addRule(13);
                                    mraidMediaProcessor.b.setLayoutParams(layoutParams);
                                    RelativeLayout relativeLayout = new RelativeLayout(activity);
                                    relativeLayout.setOnTouchListener(new OnTouchListener() {
                                        public final boolean onTouch(View view, MotionEvent motionEvent) {
                                            return true;
                                        }
                                    });
                                    relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                                    relativeLayout.addView(mraidMediaProcessor.b);
                                    viewGroup.addView(relativeLayout, new LayoutParams(-1, -1));
                                    mraidMediaProcessor.b.c = relativeLayout;
                                    mraidMediaProcessor.b.requestFocus();
                                    mraidMediaProcessor.b.setOnKeyListener(new OnKeyListener() {
                                        public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                                            if (4 != i || keyEvent.getAction() != 0) {
                                                return false;
                                            }
                                            MraidMediaProcessor.this.b.a();
                                            return true;
                                        }
                                    });
                                    mraidMediaProcessor.b.d = new a() {
                                        public final void a(MediaRenderView mediaRenderView) {
                                            MraidMediaProcessor.f;
                                            MraidMediaProcessor.this.a.setAdActiveFlag(false);
                                            ViewGroup viewGroup = mediaRenderView.c;
                                            if (viewGroup != null) {
                                                ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
                                            }
                                            mediaRenderView.c = null;
                                        }

                                        public final void a() {
                                            MraidMediaProcessor.f;
                                        }
                                    };
                                    MediaRenderView mediaRenderView2 = mraidMediaProcessor.b;
                                    mediaRenderView2.setVideoPath(mediaRenderView2.h);
                                    mediaRenderView2.setOnCompletionListener(mediaRenderView2);
                                    mediaRenderView2.setOnPreparedListener(mediaRenderView2);
                                    mediaRenderView2.setOnErrorListener(mediaRenderView2);
                                    if (mediaRenderView2.a == null && VERSION.SDK_INT >= 19) {
                                        mediaRenderView2.a = new CustomMediaController(mediaRenderView2.getContext());
                                        mediaRenderView2.a.setAnchorView(mediaRenderView2);
                                        mediaRenderView2.setMediaController(mediaRenderView2.a);
                                    }
                                    HashMap hashMap = new HashMap();
                                    hashMap.put(CBConstant.COMMAND, "playVideo");
                                    hashMap.put("scheme", br.a(str));
                                    a.c.b("CreativeInvokedAction", hashMap);
                                }
                            }
                            a.b(str, "Media playback is  not allowed before it is visible! Ignoring request ...", "playVideo");
                        }
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", "playVideo");
                        Logger.a(InternalLogLevel.ERROR, "InMobi", "Error playing video; SDK encountered an unexpected error");
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in handling playVideo() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public String getState(String str) {
        return this.c.getState().toLowerCase(Locale.ENGLISH);
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", com.inmobi.commons.core.utilities.b.c.a().a);
            jSONObject.put("height", com.inmobi.commons.core.utilities.b.c.a().b);
        } catch (JSONException unused) {
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getScreenSize");
            new StringBuilder("SDK encountered unexpected error while getting screen dimensions; ").append(e.getMessage());
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0064 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:1|2|(2:4|(2:6|7)(1:8))|9|(3:13|59|21)|25|26|27|28) */
    @android.webkit.JavascriptInterface
    public java.lang.String getMaxSize(java.lang.String r7) {
        /*
        r6 = this;
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = r6.c;	 Catch:{ Exception -> 0x008c }
        r1 = r1.getFullScreenActivity();	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x0024;
    L_0x000d:
        r1 = r6.c;	 Catch:{ Exception -> 0x008c }
        r1 = r1.getContainerContext();	 Catch:{ Exception -> 0x008c }
        r1 = r1 instanceof android.app.Activity;	 Catch:{ Exception -> 0x008c }
        if (r1 != 0) goto L_0x001c;
    L_0x0017:
        r1 = r6.getScreenSize(r7);	 Catch:{ Exception -> 0x008c }
        return r1;
    L_0x001c:
        r1 = r6.c;	 Catch:{ Exception -> 0x008c }
        r1 = r1.getContainerContext();	 Catch:{ Exception -> 0x008c }
        r1 = (android.app.Activity) r1;	 Catch:{ Exception -> 0x008c }
    L_0x0024:
        r2 = 16908290; // 0x1020002 float:2.3877235E-38 double:8.353805E-317;
        r1 = r1.findViewById(r2);	 Catch:{ Exception -> 0x008c }
        r1 = (android.widget.FrameLayout) r1;	 Catch:{ Exception -> 0x008c }
        r2 = r1.getWidth();	 Catch:{ Exception -> 0x008c }
        r2 = com.inmobi.commons.core.utilities.b.c.b(r2);	 Catch:{ Exception -> 0x008c }
        r3 = r1.getHeight();	 Catch:{ Exception -> 0x008c }
        r3 = com.inmobi.commons.core.utilities.b.c.b(r3);	 Catch:{ Exception -> 0x008c }
        r4 = r6.c;	 Catch:{ Exception -> 0x008c }
        r4 = r4.getFullScreenActivity();	 Catch:{ Exception -> 0x008c }
        if (r4 == 0) goto L_0x0073;
    L_0x0045:
        if (r2 == 0) goto L_0x0049;
    L_0x0047:
        if (r3 != 0) goto L_0x0073;
    L_0x0049:
        r2 = new com.inmobi.rendering.c$a;	 Catch:{ Exception -> 0x008c }
        r2.<init>(r1);	 Catch:{ Exception -> 0x008c }
        r1 = r1.getViewTreeObserver();	 Catch:{ Exception -> 0x008c }
        r1.addOnGlobalLayoutListener(r2);	 Catch:{ Exception -> 0x008c }
        r1 = r2.d;	 Catch:{ Exception -> 0x008c }
        monitor-enter(r1);	 Catch:{ Exception -> 0x008c }
        r3 = r2.d;	 Catch:{ InterruptedException -> 0x0064 }
        r3.wait();	 Catch:{ InterruptedException -> 0x0064 }
        goto L_0x0064;
    L_0x0062:
        r2 = move-exception;
        goto L_0x0071;
    L_0x0064:
        r3 = r2.a;	 Catch:{ all -> 0x0062 }
        r2 = r2.b;	 Catch:{ all -> 0x0062 }
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        r5 = r3;
        r3 = r2;
        r2 = r5;
        goto L_0x0073;
    L_0x0071:
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        throw r2;	 Catch:{ Exception -> 0x008c }
    L_0x0073:
        r1 = "width";
        r0.put(r1, r2);	 Catch:{ JSONException -> 0x007d }
        r1 = "height";
        r0.put(r1, r3);	 Catch:{ JSONException -> 0x007d }
    L_0x007d:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008c }
        r2 = "getMaxSize called:";
        r1.<init>(r2);	 Catch:{ Exception -> 0x008c }
        r2 = r0.toString();	 Catch:{ Exception -> 0x008c }
        r1.append(r2);	 Catch:{ Exception -> 0x008c }
        goto L_0x00a4;
    L_0x008c:
        r1 = move-exception;
        r2 = r6.c;
        r3 = "Unexpected error";
        r4 = "getMaxSize";
        r2.b(r7, r3, r4);
        r7 = new java.lang.StringBuilder;
        r2 = "SDK encountered unexpected error in handling getMaxSize() request from creative; ";
        r7.<init>(r2);
        r1 = r1.getMessage();
        r7.append(r1);
    L_0x00a4:
        r7 = r0.toString();
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.getMaxSize(java.lang.String):java.lang.String");
    }

    @JavascriptInterface
    public void close(final String str) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
            public final void run() {
                try {
                    c.this.c.getReferenceContainer().b();
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "close");
                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Failed to close ad; SDK encountered an unexpected error");
                    c.b;
                    new StringBuilder("SDK encountered an expected error in handling the close() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        return PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.d.a ? "interstitial" : "inline";
    }

    @JavascriptInterface
    @TargetApi(23)
    public void createCalendarEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        String str12 = str;
        final String str13 = str3;
        final String str14 = str4;
        if (this.c != null) {
            if (!this.c.e()) {
                this.c.c("createCalendarEvent");
            } else if (!this.c.e("calendar")) {
            } else {
                if (str13 == null || str3.trim().length() == 0 || str14 == null || str4.trim().length() == 0) {
                    this.c.b(str12, "Mandatory parameter(s) start and/or end date not supplied", "createCalendarEvent");
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder("createCalendarEvent called with parameters: \nevent ID: ");
                final String str15 = str2;
                stringBuilder.append(str15);
                stringBuilder.append("; startDate: ");
                stringBuilder.append(str13);
                stringBuilder.append("; endDate: ");
                stringBuilder.append(str14);
                stringBuilder.append("; location: ");
                final String str16 = str5;
                stringBuilder.append(str16);
                stringBuilder.append("; description: ");
                final String str17 = str6;
                stringBuilder.append(str17);
                stringBuilder.append("; summary: ");
                final String str18 = str7;
                stringBuilder.append(str18);
                stringBuilder.append("; status: ");
                final String str19 = str8;
                stringBuilder.append(str19);
                stringBuilder.append("; transparency: ");
                final String str20 = str9;
                stringBuilder.append(str20);
                stringBuilder.append("; recurrence: ");
                final String str21 = str10;
                stringBuilder.append(str21);
                stringBuilder.append("; reminder: ");
                String str22 = str11;
                stringBuilder.append(str22);
                Context b = com.inmobi.commons.a.a.b();
                if (b != null) {
                    if (VERSION.SDK_INT < 23 || (b.checkSelfPermission("android.permission.WRITE_CALENDAR") == 0 && b.checkSelfPermission("android.permission.READ_CALENDAR") == 0)) {
                        try {
                            this.c.a(str12, str15, str13, str14, str16, str17, str18, str19, str20, str21, str22);
                            return;
                        } catch (Exception e) {
                            Exception exception = e;
                            this.c.b(str12, "Unexpected error", "createCalendarEvent");
                            Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                            new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(exception.getMessage());
                            return;
                        }
                    }
                    AnonymousClass2 anonymousClass2 = r1;
                    String[] strArr = new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"};
                    final String str23 = str12;
                    str12 = str22;
                    AnonymousClass2 anonymousClass22 = new InMobiAdActivity.b() {
                        public final void a(int[] iArr) {
                            if (iArr.length == 2 && iArr[0] == 0 && iArr[1] == 0) {
                                try {
                                    c.this.c.a(str23, str15, str13, str14, str16, str17, str18, str19, str20, str21, str12);
                                    return;
                                } catch (Exception e) {
                                    c.this.c.b(str23, "Unexpected error", "createCalendarEvent");
                                    Logger.a(InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                                    c.b;
                                    new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(e.getMessage());
                                    return;
                                }
                            }
                            c.this.c.b(str23, "Permission denied by user.", "createCalendarEvent");
                        }
                    };
                    InMobiAdActivity.a(strArr, (InMobiAdActivity.b) anonymousClass2);
                }
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00fe */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:28|29|(3:38|39|40)|41|42) */
    @android.webkit.JavascriptInterface
    public void postToSocial(java.lang.String r6, int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
        r5 = this;
        r0 = r5.c;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r5.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0015;
    L_0x000d:
        r6 = r5.c;
        r7 = "postToSocial";
        r6.c(r7);
        return;
    L_0x0015:
        r0 = new java.lang.StringBuilder;
        r1 = "postToSocial called with parameters: socialType: ";
        r0.<init>(r1);
        r0.append(r7);
        r1 = "; text: ";
        r0.append(r1);
        r0.append(r8);
        r1 = "; link: ";
        r0.append(r1);
        r0.append(r9);
        r1 = "; image URL: ";
        r0.append(r1);
        r0.append(r10);
        r0 = r5.c;	 Catch:{ Exception -> 0x0115 }
        r1 = "postToSocial";
        r1 = r0.e(r1);	 Catch:{ Exception -> 0x0115 }
        if (r1 == 0) goto L_0x0114;
    L_0x0041:
        r1 = r0.i;	 Catch:{ Exception -> 0x0115 }
        r0 = r0.getContainerContext();	 Catch:{ Exception -> 0x0115 }
        if (r8 == 0) goto L_0x010a;
    L_0x0049:
        r2 = r8.length();	 Catch:{ Exception -> 0x0115 }
        if (r2 == 0) goto L_0x010a;
    L_0x004f:
        if (r9 == 0) goto L_0x010a;
    L_0x0051:
        r2 = r9.length();	 Catch:{ Exception -> 0x0115 }
        if (r2 == 0) goto L_0x010a;
    L_0x0057:
        r2 = "http";
        r2 = r9.startsWith(r2);	 Catch:{ Exception -> 0x0115 }
        if (r2 == 0) goto L_0x010a;
    L_0x005f:
        if (r10 == 0) goto L_0x010a;
    L_0x0061:
        r2 = r10.length();	 Catch:{ Exception -> 0x0115 }
        if (r2 == 0) goto L_0x010a;
    L_0x0067:
        r2 = "http";
        r2 = r10.startsWith(r2);	 Catch:{ Exception -> 0x0115 }
        if (r2 == 0) goto L_0x010a;
    L_0x006f:
        r2 = ".jpg";
        r2 = r10.endsWith(r2);	 Catch:{ Exception -> 0x0115 }
        if (r2 != 0) goto L_0x0079;
    L_0x0077:
        goto L_0x010a;
    L_0x0079:
        r2 = 0;
        switch(r7) {
            case 1: goto L_0x00f8;
            case 2: goto L_0x00b3;
            case 3: goto L_0x0081;
            default: goto L_0x007d;
        };	 Catch:{ Exception -> 0x0115 }
    L_0x007d:
        r7 = r1.a;	 Catch:{ Exception -> 0x0115 }
        goto L_0x0102;
    L_0x0081:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0115 }
        r1.<init>();	 Catch:{ Exception -> 0x0115 }
        r1.append(r8);	 Catch:{ Exception -> 0x0115 }
        r2 = " ";
        r1.append(r2);	 Catch:{ Exception -> 0x0115 }
        r1.append(r9);	 Catch:{ Exception -> 0x0115 }
        r2 = " ";
        r1.append(r2);	 Catch:{ Exception -> 0x0115 }
        r1.append(r10);	 Catch:{ Exception -> 0x0115 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0115 }
        r2 = "com.twitter.android";
        r3 = new android.content.Intent;	 Catch:{ Exception -> 0x0115 }
        r3.<init>();	 Catch:{ Exception -> 0x0115 }
        r4 = "text/plain";
        r3.setType(r4);	 Catch:{ Exception -> 0x0115 }
        r3.setPackage(r2);	 Catch:{ Exception -> 0x0115 }
        r2 = "android.intent.extra.TEXT";
        r3.putExtra(r2, r1);	 Catch:{ Exception -> 0x0115 }
        r2 = r3;
        goto L_0x00f8;
    L_0x00b3:
        r1 = "ads";
        r1 = com.inmobi.commons.core.utilities.f.a(r1);	 Catch:{ Exception -> 0x0115 }
        if (r1 == 0) goto L_0x00f8;
    L_0x00bb:
        r1 = com.inmobi.rendering.mraid.i.a();	 Catch:{ Exception -> 0x0115 }
        if (r1 == 0) goto L_0x00f8;
    L_0x00c1:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0115 }
        r1.<init>();	 Catch:{ Exception -> 0x0115 }
        r1.append(r8);	 Catch:{ Exception -> 0x0115 }
        r2 = " ";
        r1.append(r2);	 Catch:{ Exception -> 0x0115 }
        r1.append(r9);	 Catch:{ Exception -> 0x0115 }
        r2 = " ";
        r1.append(r2);	 Catch:{ Exception -> 0x0115 }
        r1.append(r10);	 Catch:{ Exception -> 0x0115 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0115 }
        r2 = new com.google.android.gms.plus.PlusShare$Builder;	 Catch:{ Exception -> 0x0115 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0115 }
        r3 = "text/plain";
        r2 = r2.setType(r3);	 Catch:{ Exception -> 0x0115 }
        r1 = r2.setText(r1);	 Catch:{ Exception -> 0x0115 }
        r2 = android.net.Uri.parse(r10);	 Catch:{ Exception -> 0x0115 }
        r1 = r1.setContentUrl(r2);	 Catch:{ Exception -> 0x0115 }
        r2 = r1.getIntent();	 Catch:{ Exception -> 0x0115 }
    L_0x00f8:
        if (r2 == 0) goto L_0x00fe;
    L_0x00fa:
        com.inmobi.commons.a.a.a(r0, r2);	 Catch:{ ActivityNotFoundException -> 0x00fe }
        return;
    L_0x00fe:
        com.inmobi.rendering.mraid.i.a(r0, r7, r8, r9, r10);	 Catch:{ Exception -> 0x0115 }
        goto L_0x0114;
    L_0x0102:
        r8 = "Unsupported type of social network";
        r9 = "postToSocial";
        r7.b(r6, r8, r9);	 Catch:{ Exception -> 0x0115 }
        return;
    L_0x010a:
        r7 = r1.a;	 Catch:{ Exception -> 0x0115 }
        r8 = "Attempting to share with null/empty/invalid parameters";
        r9 = "postToSocial";
        r7.b(r6, r8, r9);	 Catch:{ Exception -> 0x0115 }
        return;
    L_0x0114:
        return;
    L_0x0115:
        r7 = move-exception;
        r8 = r5.c;
        r9 = "Unexpected error";
        r10 = "postToSocial";
        r8.b(r6, r9, r10);
        r6 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.ERROR;
        r8 = "InMobi";
        r9 = "Could not post to social network; SDK encountered an unexpected error";
        com.inmobi.commons.core.utilities.Logger.a(r6, r8, r9);
        r6 = new java.lang.StringBuilder;
        r8 = "SDK encountered an unexpected error in handling the postToSocial() request from creative; ";
        r6.<init>(r8);
        r7 = r7.getMessage();
        r6.append(r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.postToSocial(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        if (Arrays.asList(a).contains(str2) || this.c.e(str2)) {
            return String.valueOf(this.c.e(str2));
        }
        return InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
    }

    @JavascriptInterface
    public void openExternal(String str, String str2, @Nullable String str3) {
        if (this.c != null) {
            if (this.c.e()) {
                this.c.a("openExternal", str, str2, str3);
            } else {
                this.c.c("openExternal");
            }
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        if (URLUtil.isValidUrl(str2)) {
            try {
                Map hashMap = new HashMap();
                hashMap.put(CBConstant.COMMAND, "ping");
                hashMap.put("scheme", br.a(str));
                this.c.a("CreativeInvokedAction", hashMap);
                final com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpMethods.GET, str2);
                cVar.u = false;
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                new com.inmobi.commons.core.network.a(cVar, new com.inmobi.commons.core.network.a.a() {
                    public final void a(d dVar) {
                        c.b;
                        try {
                            n.a().a(cVar.g());
                            n.a().b(dVar.c());
                            n.a().c(SystemClock.elapsedRealtime() - elapsedRealtime);
                        } catch (Exception e) {
                            c.b;
                            new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                        }
                    }

                    public final void b(d dVar) {
                        c.b;
                    }
                }).a();
                return;
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "asyncPing");
                new StringBuilder("SDK encountered internal error in handling asyncPing() request from creative; ").append(e.getMessage());
                return;
            }
        }
        this.c.b(str, "Invalid url", "asyncPing");
    }

    @JavascriptInterface
    public void disableCloseRegion(final String str, final boolean z) {
        if (this.c != null) {
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        c.this.c.a(z);
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", "disableCloseRegion");
                        c.b;
                        new StringBuilder("SDK encountered unexpected error in handling disableCloseRegion() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0083 */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:27:?, code skipped:
            r4.c.getListener().b(new java.util.HashMap());
     */
    /* JADX WARNING: Missing block: B:28:0x0091, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:29:0x0092, code skipped:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:30:0x0093, code skipped:
            r4.c.b(r5, "Unexpected error", "onUserInteraction");
            new java.lang.StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(r6.getMessage());
     */
    /* JADX WARNING: Missing block: B:31:0x00aa, code skipped:
            return;
     */
    @android.webkit.JavascriptInterface
    public void onUserInteraction(java.lang.String r5, java.lang.String r6) {
        /*
        r4 = this;
        r0 = r4.c;
        if (r0 == 0) goto L_0x0014;
    L_0x0004:
        r0 = r4.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0014;
    L_0x000c:
        r5 = r4.c;
        r6 = "onUserInteraction";
        r5.c(r6);
        return;
    L_0x0014:
        if (r6 != 0) goto L_0x003e;
    L_0x0016:
        r6 = r4.c;	 Catch:{ Exception -> 0x0025 }
        r6 = r6.getListener();	 Catch:{ Exception -> 0x0025 }
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x0025 }
        r0.<init>();	 Catch:{ Exception -> 0x0025 }
        r6.b(r0);	 Catch:{ Exception -> 0x0025 }
        return;
    L_0x0025:
        r6 = move-exception;
        r0 = r4.c;
        r1 = "Unexpected error";
        r2 = "onUserInteraction";
        r0.b(r5, r1, r2);
        r5 = new java.lang.StringBuilder;
        r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r5.<init>(r0);
        r6 = r6.getMessage();
        r5.append(r6);
        return;
    L_0x003e:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0083 }
        r0.<init>(r6);	 Catch:{ JSONException -> 0x0083 }
        r6 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0083 }
        r6.<init>();	 Catch:{ JSONException -> 0x0083 }
        r1 = r0.keys();	 Catch:{ JSONException -> 0x0083 }
    L_0x004c:
        r2 = r1.hasNext();	 Catch:{ JSONException -> 0x0083 }
        if (r2 == 0) goto L_0x0060;
    L_0x0052:
        r2 = r1.next();	 Catch:{ JSONException -> 0x0083 }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x0083 }
        r3 = r0.get(r2);	 Catch:{ JSONException -> 0x0083 }
        r6.put(r2, r3);	 Catch:{ JSONException -> 0x0083 }
        goto L_0x004c;
    L_0x0060:
        r0 = r4.c;	 Catch:{ Exception -> 0x006a }
        r0 = r0.getListener();	 Catch:{ Exception -> 0x006a }
        r0.b(r6);	 Catch:{ Exception -> 0x006a }
        return;
    L_0x006a:
        r6 = move-exception;
        r0 = r4.c;	 Catch:{ JSONException -> 0x0083 }
        r1 = "Unexpected error";
        r2 = "onUserInteraction";
        r0.b(r5, r1, r2);	 Catch:{ JSONException -> 0x0083 }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0083 }
        r1 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0083 }
        r6 = r6.getMessage();	 Catch:{ JSONException -> 0x0083 }
        r0.append(r6);	 Catch:{ JSONException -> 0x0083 }
        return;
    L_0x0083:
        r6 = r4.c;	 Catch:{ Exception -> 0x0092 }
        r6 = r6.getListener();	 Catch:{ Exception -> 0x0092 }
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x0092 }
        r0.<init>();	 Catch:{ Exception -> 0x0092 }
        r6.b(r0);	 Catch:{ Exception -> 0x0092 }
        return;
    L_0x0092:
        r6 = move-exception;
        r0 = r4.c;
        r1 = "Unexpected error";
        r2 = "onUserInteraction";
        r0.b(r5, r1, r2);
        r5 = new java.lang.StringBuilder;
        r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r5.<init>(r0);
        r6 = r6.getMessage();
        r5.append(r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.onUserInteraction(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x006f */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:20|21|22) */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r4.c.getListener().a(new java.util.HashMap());
     */
    /* JADX WARNING: Missing block: B:22:0x007d, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:23:0x007e, code skipped:
            r6 = move-exception;
     */
    /* JADX WARNING: Missing block: B:24:0x007f, code skipped:
            r4.c.b(r5, "Unexpected error", "incentCompleted");
            new java.lang.StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(r6.getMessage());
     */
    /* JADX WARNING: Missing block: B:25:0x0096, code skipped:
            return;
     */
    @android.webkit.JavascriptInterface
    public void incentCompleted(java.lang.String r5, java.lang.String r6) {
        /*
        r4 = this;
        if (r6 != 0) goto L_0x002a;
    L_0x0002:
        r6 = r4.c;	 Catch:{ Exception -> 0x0011 }
        r6 = r6.getListener();	 Catch:{ Exception -> 0x0011 }
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x0011 }
        r0.<init>();	 Catch:{ Exception -> 0x0011 }
        r6.a(r0);	 Catch:{ Exception -> 0x0011 }
        return;
    L_0x0011:
        r6 = move-exception;
        r0 = r4.c;
        r1 = "Unexpected error";
        r2 = "incentCompleted";
        r0.b(r5, r1, r2);
        r5 = new java.lang.StringBuilder;
        r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r5.<init>(r0);
        r6 = r6.getMessage();
        r5.append(r6);
        return;
    L_0x002a:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x006f }
        r0.<init>(r6);	 Catch:{ JSONException -> 0x006f }
        r6 = new java.util.HashMap;	 Catch:{ JSONException -> 0x006f }
        r6.<init>();	 Catch:{ JSONException -> 0x006f }
        r1 = r0.keys();	 Catch:{ JSONException -> 0x006f }
    L_0x0038:
        r2 = r1.hasNext();	 Catch:{ JSONException -> 0x006f }
        if (r2 == 0) goto L_0x004c;
    L_0x003e:
        r2 = r1.next();	 Catch:{ JSONException -> 0x006f }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x006f }
        r3 = r0.get(r2);	 Catch:{ JSONException -> 0x006f }
        r6.put(r2, r3);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0038;
    L_0x004c:
        r0 = r4.c;	 Catch:{ Exception -> 0x0056 }
        r0 = r0.getListener();	 Catch:{ Exception -> 0x0056 }
        r0.a(r6);	 Catch:{ Exception -> 0x0056 }
        return;
    L_0x0056:
        r6 = move-exception;
        r0 = r4.c;	 Catch:{ JSONException -> 0x006f }
        r1 = "Unexpected error";
        r2 = "incentCompleted";
        r0.b(r5, r1, r2);	 Catch:{ JSONException -> 0x006f }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x006f }
        r1 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r0.<init>(r1);	 Catch:{ JSONException -> 0x006f }
        r6 = r6.getMessage();	 Catch:{ JSONException -> 0x006f }
        r0.append(r6);	 Catch:{ JSONException -> 0x006f }
        return;
    L_0x006f:
        r6 = r4.c;	 Catch:{ Exception -> 0x007e }
        r6 = r6.getListener();	 Catch:{ Exception -> 0x007e }
        r0 = new java.util.HashMap;	 Catch:{ Exception -> 0x007e }
        r0.<init>();	 Catch:{ Exception -> 0x007e }
        r6.a(r0);	 Catch:{ Exception -> 0x007e }
        return;
    L_0x007e:
        r6 = move-exception;
        r0 = r4.c;
        r1 = "Unexpected error";
        r2 = "incentCompleted";
        r0.b(r5, r1, r2);
        r5 = new java.lang.StringBuilder;
        r0 = "SDK encountered unexpected error in handling onUserInteraction() signal from creative; ";
        r5.<init>(r0);
        r6 = r6.getMessage();
        r5.append(r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.incentCompleted(java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        int b = com.inmobi.commons.core.utilities.b.c.b();
        if (b == 1) {
            return "0";
        }
        if (b == 3) {
            return "90";
        }
        if (b == 2) {
            return "180";
        }
        return b == 4 ? "270" : "-1";
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x003e */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:9|10|11|12|13|14) */
    @android.webkit.JavascriptInterface
    public void saveContent(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
        r4 = this;
        if (r6 == 0) goto L_0x00b0;
    L_0x0002:
        r0 = r6.length();
        if (r0 == 0) goto L_0x00b0;
    L_0x0008:
        if (r7 == 0) goto L_0x00b0;
    L_0x000a:
        r0 = r7.length();
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        goto L_0x00b0;
    L_0x0012:
        r0 = r4.c;	 Catch:{ Exception -> 0x0097 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0097 }
        r2 = "saveContent called: content ID: ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0097 }
        r1.append(r6);	 Catch:{ Exception -> 0x0097 }
        r2 = "; URL: ";
        r1.append(r2);	 Catch:{ Exception -> 0x0097 }
        r1.append(r7);	 Catch:{ Exception -> 0x0097 }
        r1 = "saveContent";
        r1 = r0.e(r1);	 Catch:{ Exception -> 0x0097 }
        if (r1 != 0) goto L_0x0069;
    L_0x002e:
        r1 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0097 }
        r1.<init>();	 Catch:{ Exception -> 0x0097 }
        r2 = "url";
        r1.put(r2, r7);	 Catch:{ JSONException -> 0x003e }
        r7 = "reason";
        r2 = 5;
        r1.put(r7, r2);	 Catch:{ JSONException -> 0x003e }
    L_0x003e:
        r7 = r1.toString();	 Catch:{ Exception -> 0x0097 }
        r1 = "\"";
        r2 = "\\\"";
        r7 = r7.replace(r1, r2);	 Catch:{ Exception -> 0x0097 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0097 }
        r2 = "sendSaveContentResult(\"saveContent_";
        r1.<init>(r2);	 Catch:{ Exception -> 0x0097 }
        r1.append(r6);	 Catch:{ Exception -> 0x0097 }
        r6 = "\", 'failed', \"";
        r1.append(r6);	 Catch:{ Exception -> 0x0097 }
        r1.append(r7);	 Catch:{ Exception -> 0x0097 }
        r6 = "\");";
        r1.append(r6);	 Catch:{ Exception -> 0x0097 }
        r6 = r1.toString();	 Catch:{ Exception -> 0x0097 }
        r0.a(r5, r6);	 Catch:{ Exception -> 0x0097 }
        return;
    L_0x0069:
        r1 = new java.util.HashSet;	 Catch:{ Exception -> 0x0097 }
        r1.<init>();	 Catch:{ Exception -> 0x0097 }
        r2 = new com.inmobi.ads.bm;	 Catch:{ Exception -> 0x0097 }
        r3 = -1;
        r2.<init>(r3, r7);	 Catch:{ Exception -> 0x0097 }
        r1.add(r2);	 Catch:{ Exception -> 0x0097 }
        r7 = new com.inmobi.ads.cache.b;	 Catch:{ Exception -> 0x0097 }
        r2 = java.util.UUID.randomUUID();	 Catch:{ Exception -> 0x0097 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0097 }
        r0 = r0.w;	 Catch:{ Exception -> 0x0097 }
        r7.<init>(r2, r1, r0, r6);	 Catch:{ Exception -> 0x0097 }
        r7.g = r5;	 Catch:{ Exception -> 0x0097 }
        r6 = com.inmobi.ads.cache.AssetStore.a();	 Catch:{ Exception -> 0x0097 }
        r0 = r6.c;	 Catch:{ Exception -> 0x0097 }
        r1 = new com.inmobi.ads.cache.AssetStore$4;	 Catch:{ Exception -> 0x0097 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x0097 }
        r0.execute(r1);	 Catch:{ Exception -> 0x0097 }
        return;
    L_0x0097:
        r6 = move-exception;
        r7 = r4.c;
        r0 = "Unexpected error";
        r1 = "saveContent";
        r7.b(r5, r0, r1);
        r5 = new java.lang.StringBuilder;
        r7 = "SDK encountered unexpected error in handling saveContent() request from creative; ";
        r5.<init>(r7);
        r6 = r6.getMessage();
        r5.append(r6);
        return;
    L_0x00b0:
        r0 = new org.json.JSONObject;
        r0.<init>();
        r1 = "url";
        if (r7 != 0) goto L_0x00bb;
    L_0x00b9:
        r7 = "";
    L_0x00bb:
        r0.put(r1, r7);	 Catch:{ JSONException -> 0x00c4 }
        r7 = "reason";
        r1 = 1;
        r0.put(r7, r1);	 Catch:{ JSONException -> 0x00c4 }
    L_0x00c4:
        r7 = r0.toString();
        r0 = "\"";
        r1 = "\\\"";
        r7 = r7.replace(r0, r1);
        r0 = new java.lang.StringBuilder;
        r1 = "sendSaveContentResult(\"saveContent_";
        r0.<init>(r1);
        if (r6 != 0) goto L_0x00db;
    L_0x00d9:
        r6 = "";
    L_0x00db:
        r0.append(r6);
        r6 = "\", 'failed', \"";
        r0.append(r6);
        r0.append(r7);
        r6 = "\");";
        r0.append(r6);
        r6 = r0.toString();
        r7 = r4.c;
        r7.a(r5, r6);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.c.saveContent(java.lang.String, java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        try {
            RenderView.d();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "cancelSaveContent");
            new StringBuilder("SDK encountered unexpected error in handling cancelSaveContent() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        if (this.c == null) {
            return InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        }
        boolean z = false;
        try {
            this.c.getMediaProcessor();
            z = MraidMediaProcessor.a();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking if device is muted; ").append(e.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        if (this.c == null) {
            return InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        }
        boolean z = false;
        try {
            this.c.getMediaProcessor();
            z = MraidMediaProcessor.d();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking if headphones are plugged-in; ").append(e.getMessage());
        }
        return String.valueOf(z);
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        if (this.c != null) {
            try {
                MraidMediaProcessor mediaProcessor = this.c.getMediaProcessor();
                Context b = com.inmobi.commons.a.a.b();
                if (b != null && mediaProcessor.c == null) {
                    mediaProcessor.c = new RingerModeChangeReceiver(str);
                    b.registerReceiver(mediaProcessor.c, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
                }
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "registerDeviceMuteEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerDeviceMuteEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        if (this.c != null) {
            try {
                this.c.getMediaProcessor().b();
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "unRegisterDeviceMuteEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceMuteEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        if (this.c != null) {
            try {
                MraidMediaProcessor mediaProcessor = this.c.getMediaProcessor();
                Context b = com.inmobi.commons.a.a.b();
                if (b != null && mediaProcessor.d == null) {
                    mediaProcessor.d = new com.inmobi.rendering.mraid.MraidMediaProcessor.a(str, b, new Handler());
                    b.getContentResolver().registerContentObserver(System.CONTENT_URI, true, mediaProcessor.d);
                }
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "registerDeviceVolumeChangeEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerDeviceVolumeChangeEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        if (this.c != null) {
            try {
                this.c.getMediaProcessor().c();
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "unregisterDeviceVolumeChangeEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceVolumeChangeEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public int getDeviceVolume(String str) {
        if (this.c == null) {
            return -1;
        }
        try {
            MraidMediaProcessor mediaProcessor = this.c.getMediaProcessor();
            Context b = com.inmobi.commons.a.a.b();
            if (b == null) {
                return -1;
            }
            if (mediaProcessor.a.getRenderingConfig().m && com.inmobi.commons.a.a.d()) {
                return 0;
            }
            return ((AudioManager) b.getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDeviceVolume");
            new StringBuilder("SDK encountered unexpected error in handling getDeviceVolume() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        if (this.c != null) {
            try {
                MraidMediaProcessor mediaProcessor = this.c.getMediaProcessor();
                Context b = com.inmobi.commons.a.a.b();
                if (b != null && mediaProcessor.e == null) {
                    mediaProcessor.e = new HeadphonesPluggedChangeReceiver(str);
                    b.registerReceiver(mediaProcessor.e, new IntentFilter("android.intent.action.HEADSET_PLUG"));
                }
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "registerHeadphonePluggedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerHeadphonePluggedEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        if (this.c != null) {
            try {
                this.c.getMediaProcessor().e();
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "unregisterHeadphonePluggedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterHeadphonePluggedEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        if (this.c != null) {
            this.c.setDisableBackButton(z);
        }
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        if (this.c == null) {
            return false;
        }
        return this.c.q;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        if (this.c != null) {
            try {
                this.c.r = str;
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "registerBackButtonPressedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling registerBackButtonPressedEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        if (this.c != null) {
            try {
                this.c.r = null;
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "unregisterBackButtonPressedEventListener");
                new StringBuilder("SDK encountered unexpected error in handling unregisterBackButtonPressedEventListener() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void startDownloader(String str, String str2, String str3, String str4) {
        if (this.c != null) {
            if (this.c.e()) {
                try {
                    if (TextUtils.isEmpty(str2)) {
                        this.c.b(str, "Invalid URL", "startDownloader");
                        return;
                    }
                    RenderView renderView = this.c;
                    AdContainer referenceContainer = renderView.getReferenceContainer();
                    if (referenceContainer instanceof ah) {
                        ah ahVar = (ah) referenceContainer;
                        ak.a(str2, str3, str4);
                        ahVar.a(renderView);
                        return;
                    }
                    if (referenceContainer instanceof RenderView) {
                        ak.a(str2, str3, str4);
                    }
                    return;
                } catch (Exception e) {
                    this.c.b(str, "Unexpected error", "startDownloader");
                    new StringBuilder("SDK encountered unexpected error in handling startDownloader() request from creative; ").append(e.getMessage());
                    return;
                }
            }
            this.c.c("startDownloader");
        }
    }

    @JavascriptInterface
    public void registerDownloaderCallbacks(String str) {
        if (this.c != null) {
            try {
                RenderView renderView = this.c;
                AdContainer referenceContainer = renderView.getReferenceContainer();
                if (referenceContainer instanceof ah) {
                    ((ah) referenceContainer).a(renderView);
                }
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "registerDownloaderCallbacks");
                new StringBuilder("SDK encountered unexpected error in handling registerDownloaderCallbacks() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void unregisterDownloaderCallbacks(String str) {
        if (this.c != null) {
            try {
                RenderView renderView = this.c;
                AdContainer referenceContainer = renderView.getReferenceContainer();
                if (referenceContainer instanceof ah) {
                    ah ahVar = (ah) referenceContainer;
                    if (ahVar.z != null) {
                        ahVar.z.remove(renderView);
                    }
                }
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "unregisterDownloaderCallbacks");
                new StringBuilder("SDK encountered unexpected error in handling unregisterDownloaderCallbacks() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public int getDownloadProgress(String str) {
        if (this.c == null) {
            return -1;
        }
        try {
            return this.c.getDownloadProgress();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDownloadProgress");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadProgress() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public int getDownloadStatus(String str) {
        if (this.c == null) {
            return -1;
        }
        try {
            return this.c.getDownloadStatus();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDownloadStatus");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void setCloseEndCardTracker(String str, String str2) {
        if (this.c != null) {
            try {
                this.c.setCloseEndCardTracker(str2);
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "getDownloadStatus");
                new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void fireSkip(String str) {
        RenderView.f();
    }

    @JavascriptInterface
    public void fireComplete(String str) {
        if (this.c != null) {
            RenderView.g();
        }
    }

    @JavascriptInterface
    public void showEndCard(String str) {
        if (this.c != null) {
            AdContainer referenceContainer = this.c.getReferenceContainer();
            if (referenceContainer instanceof ah) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        ah.this.r = true;
                        ah.this.c(null);
                    }
                });
            }
        }
    }

    @JavascriptInterface
    public void saveBlob(String str, String str2) {
        if (this.c != null) {
            RenderView renderView = this.c;
            if (renderView.u != null) {
                renderView.u.e(str2);
            }
        }
    }

    @JavascriptInterface
    public void getBlob(String str, String str2) {
        if (this.c != null) {
            RenderView renderView = this.c;
            if (renderView.u != null) {
                renderView.u.a(str, str2, renderView);
            }
        }
    }
}
