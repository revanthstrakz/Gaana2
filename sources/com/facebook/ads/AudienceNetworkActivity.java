package com.facebook.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.j;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.d.a.f;
import com.facebook.ads.internal.view.e;
import com.facebook.ads.internal.view.g;
import com.facebook.ads.internal.view.g.b.z;
import com.facebook.ads.internal.view.h;
import com.facebook.ads.internal.view.n;
import com.facebook.ads.internal.view.o;
import com.facebook.ads.internal.view.u;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
    @Deprecated
    public static final String AD_ICON_URL = "adIconUrl";
    @Deprecated
    public static final String AD_SUBTITLE = "adSubtitle";
    @Deprecated
    public static final String AD_TITLE = "adTitle";
    public static final String AUDIENCE_NETWORK_UNIQUE_ID_EXTRA = "uniqueId";
    public static final String AUTOPLAY = "autoplay";
    public static final String BROWSER_URL = "browserURL";
    public static final String CLIENT_TOKEN = "clientToken";
    @Deprecated
    public static final String CONTEXT_SWITCH_BEHAVIOR = "contextSwitchBehavior";
    @Deprecated
    public static final String END_CARD_ACTIVATION_COMMAND = "facebookRewardedVideoEndCardActivationCommand";
    @Deprecated
    public static final String END_CARD_MARKUP = "facebookRewardedVideoEndCardMarkup";
    public static final String HANDLER_TIME = "handlerTime";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String REQUEST_TIME = "requestTime";
    public static final String REWARD_SERVER_URL = "rewardServerURL";
    public static final String SKIP_DELAY_SECONDS_KEY = "skipAfterSeconds";
    public static final String USE_CACHE = "useCache";
    public static final String VIDEO_LOGGER = "videoLogger";
    public static final String VIDEO_MPD = "videoMPD";
    @Deprecated
    public static final String VIDEO_REPORT_URL = "videoReportURL";
    public static final String VIDEO_SEEK_TIME = "videoSeekTime";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    @Deprecated
    public static final String WEBVIEW_ENCODING = "utf-8";
    @Deprecated
    public static final String WEBVIEW_MIME_TYPE = "text/html";
    private final List<BackButtonInterceptor> a = new ArrayList();
    private RelativeLayout b;
    private int c = -1;
    private String d;
    private com.facebook.ads.internal.settings.a.a e;
    private long f;
    private long g;
    private int h;
    private com.facebook.ads.internal.view.a i;
    private com.facebook.ads.internal.view.a.b j;
    private com.facebook.ads.internal.view.c.c k;

    public interface BackButtonInterceptor {
        boolean interceptBackButton();
    }

    public enum Type {
        INTERSTITIAL_WEB_VIEW(com.facebook.ads.internal.settings.a.a.INTERSTITIAL_WEB_VIEW),
        INTERSTITIAL_OLD_NATIVE_VIDEO(com.facebook.ads.internal.settings.a.a.INTERSTITIAL_OLD_NATIVE_VIDEO),
        INTERSTITIAL_NATIVE_VIDEO(com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_VIDEO),
        INTERSTITIAL_NATIVE_IMAGE(com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_IMAGE),
        INTERSTITIAL_NATIVE_CAROUSEL(com.facebook.ads.internal.settings.a.a.INTERSTITIAL_NATIVE_CAROUSEL),
        FULL_SCREEN_VIDEO(com.facebook.ads.internal.settings.a.a.FULL_SCREEN_VIDEO),
        REWARDED_VIDEO(com.facebook.ads.internal.settings.a.a.REWARDED_VIDEO),
        BROWSER(com.facebook.ads.internal.settings.a.a.BROWSER);
        
        com.facebook.ads.internal.settings.a.a a;

        private Type(com.facebook.ads.internal.settings.a.a aVar) {
            this.a = aVar;
        }
    }

    private static class b {
        private final AudienceNetworkActivity a;
        private final Intent b;
        private final com.facebook.ads.internal.o.c c;

        private b(AudienceNetworkActivity audienceNetworkActivity, Intent intent, com.facebook.ads.internal.o.c cVar) {
            this.a = audienceNetworkActivity;
            this.b = intent;
            this.c = cVar;
        }

        private com.facebook.ads.internal.view.a a() {
            return new h(this.a, this.c, i(), h() ? new com.facebook.ads.internal.f.b(this.a) : null, new a());
        }

        private com.facebook.ads.internal.view.a a(RelativeLayout relativeLayout) {
            u uVar = new u(this.a, this.c, new a());
            uVar.a((View) relativeLayout);
            uVar.a(this.b.getIntExtra("video_time_polling_interval", 200));
            return uVar;
        }

        private boolean a(k kVar) {
            j j = kVar.e().j();
            return (j == null || j.g()) ? false : true;
        }

        @Nullable
        private com.facebook.ads.internal.view.a b() {
            com.facebook.ads.internal.view.a a = com.facebook.ads.internal.adapters.h.a(this.b.getStringExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA));
            if (a == null) {
                return null;
            }
            a.setListener(new a());
            return a;
        }

        private com.facebook.ads.internal.view.a c() {
            return new com.facebook.ads.internal.view.b(this.a, this.c, new a());
        }

        private com.facebook.ads.internal.view.a d() {
            n nVar;
            k kVar = (k) this.b.getSerializableExtra("rewardedVideoAdDataBundle");
            if (a(kVar)) {
                nVar = new n(this.a, this.c, new d(), kVar);
            } else {
                RelativeLayout oVar = new o(this.a, this.c, new com.facebook.ads.internal.view.g.a(this.a), new d(), kVar);
            }
            return nVar;
        }

        private com.facebook.ads.internal.view.a e() {
            return new e(this.a, this.c, new a());
        }

        private com.facebook.ads.internal.view.a f() {
            return new f(this.a, this.c, h() ? new com.facebook.ads.internal.f.b(this.a) : null, new a());
        }

        private com.facebook.ads.internal.view.a g() {
            return new g(this.a, i(), this.c, new a());
        }

        private boolean h() {
            return this.b.getBooleanExtra(AudienceNetworkActivity.USE_CACHE, false);
        }

        private com.facebook.ads.internal.adapters.a.g i() {
            return (com.facebook.ads.internal.adapters.a.g) this.b.getSerializableExtra("ad_data_bundle");
        }
    }

    private class c implements OnLongClickListener {
        private c() {
        }

        public boolean onLongClick(View view) {
            if (!(AudienceNetworkActivity.this.k == null || AudienceNetworkActivity.this.b == null)) {
                AudienceNetworkActivity.this.k.setBounds(0, 0, AudienceNetworkActivity.this.b.getWidth(), AudienceNetworkActivity.this.b.getHeight());
                AudienceNetworkActivity.this.k.a(AudienceNetworkActivity.this.k.a() ^ 1);
            }
            return true;
        }
    }

    private static class a implements com.facebook.ads.internal.view.a.a {
        final WeakReference<AudienceNetworkActivity> a;

        private a(AudienceNetworkActivity audienceNetworkActivity) {
            this.a = new WeakReference(audienceNetworkActivity);
        }

        public void a(View view) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view);
            }
        }

        public void a(View view, int i) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b.addView(view, i);
            }
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b(str);
            }
        }

        public void a(String str, com.facebook.ads.internal.l.d dVar) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str, dVar);
            }
        }

        public void b(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).a(str);
            }
        }
    }

    private static class d extends a {
        private d(AudienceNetworkActivity audienceNetworkActivity) {
            super();
        }

        public void a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).b(str);
                String a = z.REWARDED_VIDEO_END_ACTIVITY.a();
                String a2 = z.REWARDED_VIDEO_ERROR.a();
                if (str.equals(a) || str.equals(a2)) {
                    ((AudienceNetworkActivity) this.a.get()).finish();
                }
            }
        }
    }

    @Nullable
    private com.facebook.ads.internal.view.a a() {
        b bVar = new b(getIntent(), com.facebook.ads.internal.o.d.a((Context) this));
        if (this.e == null) {
            return null;
        }
        switch (this.e) {
            case FULL_SCREEN_VIDEO:
                return bVar.a(this.b);
            case REWARDED_VIDEO:
                return bVar.d();
            case INTERSTITIAL_WEB_VIEW:
                return bVar.e();
            case BROWSER:
                return bVar.c();
            case INTERSTITIAL_OLD_NATIVE_VIDEO:
                return bVar.b();
            case INTERSTITIAL_NATIVE_VIDEO:
                return bVar.a();
            case INTERSTITIAL_NATIVE_IMAGE:
                return bVar.g();
            case INTERSTITIAL_NATIVE_CAROUSEL:
                return bVar.f();
            default:
                return null;
        }
    }

    private void a(Intent intent, Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getInt(PREDEFINED_ORIENTATION_KEY, -1);
            this.d = bundle.getString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
            this.e = (com.facebook.ads.internal.settings.a.a) bundle.getSerializable(VIEW_TYPE);
            return;
        }
        this.c = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
        this.d = intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
        this.e = (com.facebook.ads.internal.settings.a.a) intent.getSerializableExtra(VIEW_TYPE);
        this.h = intent.getIntExtra(SKIP_DELAY_SECONDS_KEY, 0) * 1000;
    }

    private void a(Intent intent, boolean z) {
        if (com.facebook.ads.internal.n.a.b(this) && this.e != com.facebook.ads.internal.settings.a.a.BROWSER) {
            this.k = new com.facebook.ads.internal.view.c.c();
            this.k.a(intent.getStringExtra(PLACEMENT_ID));
            this.k.b(getPackageName());
            long longExtra = intent.getLongExtra(REQUEST_TIME, 0);
            if (longExtra != 0) {
                this.k.a(longExtra);
            }
            View textView = new TextView(this);
            textView.setText("Debug");
            textView.setTextColor(-1);
            y.a(textView, Color.argb(MoEHelperUtils.BASELINE_SCREEN_DPI, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams(layoutParams);
            c cVar = new c();
            textView.setOnLongClickListener(cVar);
            if (z) {
                this.b.addView(textView);
            } else {
                this.b.setOnLongClickListener(cVar);
            }
            this.b.getOverlay().add(this.k);
        }
    }

    private void a(String str) {
        if (this.j == null) {
            this.j = new com.facebook.ads.internal.view.a.b(getApplicationContext(), com.facebook.ads.internal.o.d.a((Context) this), this.i, new a(), str);
            this.j.setLayoutParams(new LayoutParams(-1, -1));
        }
        y.a(this.b);
        this.b.addView(this.j);
        this.j.a();
    }

    private void a(String str, com.facebook.ads.internal.l.d dVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(this.d);
        Intent intent = new Intent(stringBuilder.toString());
        intent.putExtra("event", dVar);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void b(String str) {
        if ("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW".equals(str)) {
            finish();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(this.d);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(stringBuilder.toString()));
    }

    public void addBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.add(backButtonInterceptor);
    }

    public void finish() {
        if (!isFinishing()) {
            b(this.e == com.facebook.ads.internal.settings.a.a.REWARDED_VIDEO ? z.REWARDED_VIDEO_CLOSED.a() : "com.facebook.ads.interstitial.dismissed");
            super.finish();
        }
    }

    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        this.g += currentTimeMillis - this.f;
        this.f = currentTimeMillis;
        if (this.g > ((long) this.h)) {
            Object obj = null;
            for (BackButtonInterceptor interceptBackButton : this.a) {
                if (interceptBackButton.interceptBackButton()) {
                    obj = 1;
                }
            }
            if (obj == null) {
                super.onBackPressed();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.i instanceof i) {
            ((i) this.i).a(configuration);
        } else if (this.i instanceof o) {
            ((o) this.i).onConfigurationChanged(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            com.facebook.ads.internal.s.a.d.a();
            boolean z = true;
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            this.b = new RelativeLayout(this);
            y.a(this.b, (int) ViewCompat.MEASURED_STATE_MASK);
            setContentView(this.b, new LayoutParams(-1, -1));
            Intent intent = getIntent();
            a(intent, bundle);
            this.i = a();
            if (this.i == null) {
                com.facebook.ads.internal.l.b.a(com.facebook.ads.internal.l.a.a(null, "Unable to infer viewType from intent or savedInstanceState"));
                b("com.facebook.ads.interstitial.error");
                finish();
                return;
            }
            this.i.a(intent, bundle, this);
            b("com.facebook.ads.interstitial.displayed");
            this.f = System.currentTimeMillis();
            if (this.e != com.facebook.ads.internal.settings.a.a.INTERSTITIAL_WEB_VIEW) {
                z = false;
            }
            a(intent, z);
        } catch (Exception e) {
            com.facebook.ads.internal.s.d.a.a((Context) this, "an_activity", com.facebook.ads.internal.s.d.b.L, e);
            finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        try {
            b(this.e == com.facebook.ads.internal.settings.a.a.REWARDED_VIDEO ? z.REWARDED_VIDEO_ACTIVITY_DESTROYED.a() : "com.facebook.ads.interstitial.activity_destroyed");
            if (this.b != null) {
                this.b.removeAllViews();
            }
            if (this.i != null) {
                com.facebook.ads.internal.adapters.h.a(this.i);
                this.i.onDestroy();
                this.i = null;
            }
            if (this.k != null && com.facebook.ads.internal.n.a.b(this)) {
                this.k.b();
            }
            if (this.j != null) {
                this.j.b();
            }
        } catch (Exception e) {
            com.facebook.ads.internal.s.d.a.a((Context) this, "an_activity", com.facebook.ads.internal.s.d.b.M, e);
        }
        super.onDestroy();
    }

    public void onPause() {
        this.g += System.currentTimeMillis() - this.f;
        if (this.i != null) {
            this.i.a(false);
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f = System.currentTimeMillis();
        if (this.i != null) {
            this.i.b(false);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.i != null) {
            this.i.a(bundle);
        }
        bundle.putInt(PREDEFINED_ORIENTATION_KEY, this.c);
        bundle.putString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.d);
        bundle.putSerializable(VIEW_TYPE, this.e);
    }

    public void onStart() {
        super.onStart();
        if (this.c != -1) {
            try {
                setRequestedOrientation(this.c);
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void removeBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.a.remove(backButtonInterceptor);
    }
}
