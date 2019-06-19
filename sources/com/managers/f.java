package com.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.comscore.utils.Constants;
import com.facebook.share.internal.ShareConstants;
import com.gaana.AudioAdActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSessionManager;
import com.gaana.login.UserInfo;
import com.gaana.models.SDKConfig.ColombiaAdCode.AdConfig;
import com.gaana.models.SDKConfig.DfpAdCode.DfpMediaConfig;
import com.gaana.models.Tracks.Track;
import com.google.android.exoplayer2.C;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.helpshift.common.platform.network.c;
import com.i.i;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.PlayerManager.PlaySequenceType;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.services.d;
import com.services.n;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.ColombiaException;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.Colombia;
import com.til.colombia.android.service.ColombiaAdManager.GENDER;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ColombiaAdRequest.Builder;
import com.til.colombia.android.service.ColombiaNativeSponsoredAdView;
import com.til.colombia.android.service.ColombiaNativeVideoAdView;
import com.til.colombia.android.service.ColombiaVideoView;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;
import com.til.colombia.android.service.PublisherAdRequest;
import com.utilities.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class f {
    public static boolean a;
    public static String b;
    public static long c;
    public static boolean d;
    private static f i;
    private static com.managers.ColombiaManager.b m;
    private static long t;
    private static long u;
    private static long v;
    private int A = 1;
    private int B = 0;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private long F = -1;
    private int G = 0;
    private int H = 0;
    private int I = 0;
    private long J = -1;
    private int K = -1;
    private int L = -1;
    private int M = 0;
    private int N = 0;
    private int O = 0;
    private int P = -1;
    private int Q = -1;
    private int R = 0;
    private int S = 0;
    private Item T;
    private Item U;
    private boolean V = false;
    private boolean W = false;
    private boolean X = false;
    private boolean Y = false;
    private int Z = 0;
    private boolean aA = false;
    private String aa = "";
    private String ab = "";
    private long ac = System.currentTimeMillis();
    private long ad = 0;
    private int ae = 0;
    private String af;
    private String ag;
    private final int ah = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
    private final int ai = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private int aj = HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES;
    private int ak = Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private String al = "";
    private long am;
    private int an;
    private c ao = null;
    private HashMap<String, String> ap;
    private boolean aq = false;
    private boolean ar = false;
    private boolean as = false;
    private com.models.a at = null;
    private boolean au = false;
    private boolean av = false;
    private boolean aw;
    private b ax;
    private boolean ay = false;
    private boolean az = false;
    AdListener e = new AdListener() {
        private long b = 0;

        public boolean onItemClick(Item item) {
            return false;
        }

        public void onMediaItemDisplayed(Item item) {
            if (item != null) {
                f.this.aw = item.isSOVItem();
                f.this.am = System.currentTimeMillis() / 1000;
                f.this.B = f.this.B + 1;
                if (item.getItemType() == ITEM_TYPE.AUDIO) {
                    f.c = System.currentTimeMillis();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(item.getDuration());
                    stringBuilder.append("");
                    f.b = stringBuilder.toString();
                    if (f.m != null) {
                        f.m.e();
                        f.m.a(true);
                    }
                }
            }
        }

        public void onMediaItemClicked(Item item) {
            if (f.this.U != null && f.this.U.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
                if (!(f.this.o == null || f.this.p == null)) {
                    f.this.B = f.this.B + 1;
                    f.this.C = true;
                    if (VERSION.SDK_INT < 19) {
                        f.this.o.removeView(f.this.p);
                    } else if (f.this.p.isAttachedToWindow()) {
                        f.this.o.removeView(f.this.p);
                    }
                    f.this.p = null;
                    f.this.o = null;
                }
                f.this.D = false;
            }
            f.a = false;
        }

        public void onMediaItemClosed(Item item, USER_ACTION user_action) {
            Intent intent;
            if (f.this.U == null || f.this.U.getItemType() != ITEM_TYPE.VIDEO_INCENTIVE) {
                f.this.j = false;
            } else {
                f.this.j = true;
                if (f.this.C) {
                    Util.a(true);
                    f.this.C = false;
                }
                if (!(f.this.o == null || f.this.p == null)) {
                    if (VERSION.SDK_INT < 19) {
                        f.this.o.removeView(f.this.p);
                    } else if (f.this.p.isAttachedToWindow()) {
                        f.this.o.removeView(f.this.p);
                    }
                    f.this.p = null;
                    f.this.o = null;
                }
                f.this.D = false;
            }
            f.a = false;
            if (f.this.U != null && f.this.U.getItemType() == ITEM_TYPE.AUDIO) {
                if (f.this.ax != null) {
                    f.this.ax.updatePlayerOnAdStop();
                } else if (f.this.w != null && Util.c()) {
                    intent = new Intent(f.this.w, GaanaActivity.class);
                    intent.setFlags(872415232);
                    f.this.w.startActivity(intent);
                }
                f.this.W = false;
                AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
            }
            if (f.this.U != null) {
                f.this.U.destroy();
                f.this.U = null;
            }
            if (f.m != null) {
                f.m.c();
                f.m.b();
            }
            f.this.A();
            if (this.b > 0 && f.this.w != null) {
                this.b = 0;
                intent = new Intent(f.this.w, GaanaActivity.class);
                intent.setFlags(339738624);
                f.this.w.startActivity(intent);
            }
            f.this.k(false);
        }

        public void onMediaItemCompleted(Item item, int i) {
            if (f.this.U != null) {
                if (item == null || item.getItemType() != ITEM_TYPE.VIDEO_INCENTIVE) {
                    f.this.j = false;
                } else {
                    f.this.j = true;
                    f.this.l = true;
                    f.this.C = false;
                    f.this.aA = true;
                    this.b = (long) i;
                    d.a().a(System.currentTimeMillis(), "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
                    d.a().a(this.b, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
                }
            }
            f.a = false;
            f.this.h(true);
        }

        public void onMediaItemError(Item item, Exception exception) {
            if (f.this.U != null) {
                f.this.U.destroy();
                f.this.U = null;
            }
            if (f.this.W) {
                f.this.W = false;
            }
            if (!(GaanaMusicService.s().isPlaying() || f.m == null)) {
                f.m.b();
                f.m.c();
            }
            f.a = false;
            f.this.D = false;
            f.this.k(false);
        }

        public void onMediaItemSkipEnabled(Item item) {
            f.a = true;
            if (f.this.U != null && f.this.U.getItemType() == ITEM_TYPE.AUDIO) {
                if (Util.c()) {
                    try {
                        if (f.this.ax != null) {
                            f.this.ax.updatePlayerEnableSkip();
                        }
                    } catch (Exception unused) {
                    }
                }
                if (f.m != null) {
                    f.m.e();
                }
            }
        }

        public void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
            Item item = (itemResponse.getPaidItems() == null || itemResponse.getPaidItems().size() <= 0) ? (itemResponse.getOrganicItems() == null || itemResponse.getOrganicItems().size() <= 0) ? null : (Item) itemResponse.getOrganicItems().get(0) : (Item) itemResponse.getPaidItems().get(0);
            f.this.y = false;
            f.this.U = item;
            d.a().a(System.currentTimeMillis(), "pref_key_back_loaded_time", false);
            f.this.an = item.getLineItemId().intValue();
            f.this.I = f.this.H;
        }

        public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
            f.a = false;
            f.this.y = true;
        }
    };
    AdListener f = new AdListener() {
        private long b = 0;

        public boolean onItemClick(Item item) {
            return false;
        }

        public void onMediaItemDisplayed(Item item) {
            if (item != null) {
                f.this.B = f.this.B + 1;
                f.this.aw = item.isSOVItem();
                f.this.am = System.currentTimeMillis() / 1000;
                if (item.getItemType() == ITEM_TYPE.AUDIO) {
                    f.c = System.currentTimeMillis();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(item.getDuration());
                    stringBuilder.append("");
                    f.b = stringBuilder.toString();
                    if (f.m != null) {
                        f.m.e();
                        f.m.a(true);
                    }
                }
            }
        }

        public void onMediaItemClicked(Item item) {
            if (f.this.T != null && f.this.T.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
                if (!(f.this.o == null || f.this.p == null)) {
                    f.this.B = f.this.B + 1;
                    f.this.C = true;
                    if (VERSION.SDK_INT < 19) {
                        f.this.o.removeView(f.this.p);
                    } else if (f.this.p.isAttachedToWindow()) {
                        f.this.o.removeView(f.this.p);
                    }
                    f.this.p = null;
                    f.this.o = null;
                }
                f.this.D = false;
            }
            f.a = false;
        }

        public void onMediaItemClosed(Item item, USER_ACTION user_action) {
            Intent intent;
            if (f.this.T == null || f.this.T.getItemType() != ITEM_TYPE.VIDEO_INCENTIVE) {
                f.this.j = false;
            } else {
                f.this.j = true;
                if (f.this.C) {
                    Util.a(true);
                    f.this.C = false;
                }
                if (!(f.this.o == null || f.this.p == null)) {
                    if (VERSION.SDK_INT < 19) {
                        f.this.o.removeView(f.this.p);
                    } else if (f.this.p.isAttachedToWindow()) {
                        f.this.o.removeView(f.this.p);
                    }
                    f.this.p = null;
                    f.this.o = null;
                }
                f.this.D = false;
            }
            f.a = false;
            if (f.this.T != null && f.this.T.getItemType() == ITEM_TYPE.AUDIO) {
                if (f.this.ax != null) {
                    f.this.ax.updatePlayerOnAdStop();
                } else if (f.this.w != null && Util.c()) {
                    intent = new Intent(f.this.w, GaanaActivity.class);
                    intent.setFlags(872415232);
                    f.this.w.startActivity(intent);
                }
                f.this.V = false;
                AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
            }
            if (f.this.T != null) {
                f.this.T.destroy();
                f.this.T = null;
            }
            if (f.m != null) {
                f.m.c();
                f.m.b();
            }
            f.this.A();
            if (this.b <= 0 || f.this.w == null) {
                f.this.k(true);
                return;
            }
            this.b = 0;
            intent = new Intent(f.this.w, GaanaActivity.class);
            intent.setFlags(339738624);
            f.this.w.startActivity(intent);
        }

        public void onMediaItemCompleted(Item item, int i) {
            if (item == null || item.getItemType() != ITEM_TYPE.VIDEO_INCENTIVE) {
                f.this.j = false;
            } else {
                f.this.j = true;
                f.this.l = true;
                f.this.az = false;
                f.this.aA = true;
                f.this.C = false;
                this.b = (long) i;
                d.a().a(System.currentTimeMillis(), "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
                d.a().a(this.b, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
            }
            f.a = false;
            f.this.h(true);
        }

        public void onMediaItemError(Item item, Exception exception) {
            if (f.this.T != null) {
                f.this.T.destroy();
                f.this.T = null;
            }
            if (f.this.V) {
                f.this.V = false;
            }
            if (!(GaanaMusicService.s().isPlaying() || f.m == null)) {
                f.m.b();
                f.m.c();
            }
            f.a = false;
            f.this.D = false;
            f.this.k(true);
        }

        public void onMediaItemSkipEnabled(Item item) {
            f.a = true;
            if (f.this.T != null && f.this.T.getItemType() == ITEM_TYPE.AUDIO) {
                if (Util.c()) {
                    try {
                        if (f.this.ax != null) {
                            f.this.ax.updatePlayerEnableSkip();
                        }
                    } catch (Exception unused) {
                    }
                }
                if (f.m != null) {
                    f.m.e();
                }
            }
        }

        public void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
            Item item = (itemResponse.getPaidItems() == null || itemResponse.getPaidItems().size() <= 0) ? (itemResponse.getOrganicItems() == null || itemResponse.getOrganicItems().size() <= 0) ? null : (Item) itemResponse.getOrganicItems().get(0) : (Item) itemResponse.getPaidItems().get(0);
            f.this.z = false;
            f.this.T = item;
            if (f.this.az && f.this.T.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
                f.this.ay = true;
            }
            d.a().a(System.currentTimeMillis(), "pref_key_fore_loaded_time", false);
            f.this.an = item.getLineItemId().intValue();
            f.this.I = f.this.G;
        }

        public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
            f.a = false;
            f.this.z = true;
            if (f.this.az) {
                f.this.az = false;
                f.this.ay = false;
            }
        }
    };
    ColombiaNativeVideoAdView g;
    a h;
    private boolean j = true;
    private boolean k = false;
    private boolean l = true;
    private ColombiaNativeSponsoredAdView n;
    private WindowManager o = null;
    private FrameLayout p = null;
    private Bitmap q = null;
    private Bitmap r = null;
    private String s = null;
    private Context w = null;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public interface a {
        void adPopulated(ColombiaNativeVideoAdView colombiaNativeVideoAdView);
    }

    public interface b {
        void updatePlayerEnableSkip();

        void updatePlayerOnAdStop();
    }

    public com.models.a a() {
        return this.at;
    }

    public void a(com.models.a aVar) {
        this.at = aVar;
    }

    public boolean b() {
        return this.au;
    }

    public void a(boolean z) {
        this.au = z;
    }

    public boolean c() {
        return this.as;
    }

    public void b(boolean z) {
        this.as = z;
    }

    public void c(boolean z) {
        this.av = z;
    }

    public void a(int i) {
        this.Z = i;
    }

    public void a(String str) {
        this.aa = str;
    }

    public void a(long j) {
        this.am = j;
    }

    public int d() {
        return this.Z;
    }

    public String e() {
        return this.aa;
    }

    public String f() {
        return this.ab;
    }

    public void b(String str) {
        this.ab = str;
    }

    public long g() {
        return this.ac;
    }

    public void b(long j) {
        this.ac = j;
    }

    public int h() {
        return this.ae;
    }

    public void b(int i) {
        this.ae = i;
    }

    public long i() {
        return this.ad;
    }

    public void c(long j) {
        this.ad = j;
    }

    public int j() {
        return this.aj;
    }

    public int k() {
        return this.ak;
    }

    public long l() {
        return this.am;
    }

    public void a(DfpMediaConfig dfpMediaConfig, int i) {
        if (dfpMediaConfig != null) {
            switch (i) {
                case 0:
                    this.af = dfpMediaConfig.getAdCode();
                    b(dfpMediaConfig, i);
                    if (dfpMediaConfig.getAWC_width() != 0 && dfpMediaConfig.getAWC_width() != 0) {
                        this.aj = dfpMediaConfig.getAWC_width();
                        this.ak = dfpMediaConfig.getAWC_height();
                        return;
                    }
                    return;
                case 1:
                    this.ag = dfpMediaConfig.getAdCode();
                    b(dfpMediaConfig, i);
                    return;
                default:
                    return;
            }
        }
    }

    public void a(AdConfig adConfig) {
        if (adConfig != null && c(adConfig.getAd_code())) {
            this.F = Long.parseLong(adConfig.getAd_code());
            this.G = Integer.parseInt(adConfig.getTi());
            c(adConfig);
        }
    }

    public void m() {
        this.J = -1;
        this.F = -1;
    }

    public void b(AdConfig adConfig) {
        if (adConfig != null && c(adConfig.getAd_code())) {
            this.J = Long.parseLong(adConfig.getAd_code());
            this.H = Integer.parseInt(adConfig.getTi());
            d(adConfig);
            this.al = adConfig.getFollow_up();
        }
    }

    public String n() {
        return this.al;
    }

    private boolean c(String str) {
        return !TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str);
    }

    private void b(DfpMediaConfig dfpMediaConfig, int i) {
        switch (i) {
            case 0:
                this.M = dfpMediaConfig.getSt() * 1000;
                this.L = dfpMediaConfig.getFrequency();
                return;
            case 1:
                this.R = dfpMediaConfig.getSt() * 1000;
                this.Q = dfpMediaConfig.getFrequency();
                return;
            default:
                return;
        }
    }

    private void c(AdConfig adConfig) {
        if (c(adConfig.getFtac())) {
            this.K = Integer.parseInt(adConfig.getFtac());
        }
        if (c(adConfig.getSac())) {
            this.L = Integer.parseInt(adConfig.getSac());
        }
        if (c(adConfig.getSt())) {
            this.M = Integer.parseInt(adConfig.getSt()) * 1000;
        }
        if (c(adConfig.getTi())) {
            this.N = Integer.parseInt(adConfig.getTi()) * 1000;
        }
        if (c(adConfig.getRetry())) {
            this.O = Integer.parseInt(adConfig.getRetry());
        }
    }

    private void d(AdConfig adConfig) {
        if (c(adConfig.getFtac())) {
            this.P = Integer.parseInt(adConfig.getFtac());
        }
        if (c(adConfig.getSac())) {
            this.Q = Integer.parseInt(adConfig.getSac());
        }
        if (c(adConfig.getSt())) {
            this.R = Integer.parseInt(adConfig.getSt()) * 1000;
        }
        if (c(adConfig.getTi())) {
            this.S = Integer.parseInt(adConfig.getTi()) * 1000;
        }
        if (c(adConfig.getRetry())) {
            this.O = Integer.parseInt(adConfig.getRetry());
        }
    }

    public int o() {
        if (Util.c()) {
            return this.L;
        }
        return this.Q;
    }

    public String d(boolean z) {
        if (z) {
            return this.af;
        }
        return this.ag;
    }

    private long K() {
        if (Util.c()) {
            return this.F;
        }
        return this.J;
    }

    private int L() {
        if (Util.c()) {
            return this.M;
        }
        return this.R;
    }

    public Bitmap p() {
        return this.q;
    }

    public Bitmap q() {
        return this.r;
    }

    public String r() {
        return this.s;
    }

    public void e(boolean z) {
        this.X = z;
        if (!this.X && this.ax != null) {
            this.ax.updatePlayerOnAdStop();
        }
    }

    public void f(boolean z) {
        this.ar = z;
    }

    public boolean s() {
        return this.ar;
    }

    public boolean t() {
        return this.X;
    }

    public void g(boolean z) {
        this.Y = z;
    }

    public boolean u() {
        return this.Y;
    }

    public static f v() {
        if (i == null) {
            synchronized (f.class) {
                if (i == null) {
                    i = new f();
                }
            }
        }
        return i;
    }

    private f() {
    }

    public void a(b bVar) {
        this.ax = bVar;
    }

    public void a(com.managers.ColombiaManager.b bVar) {
        m = bVar;
    }

    private boolean a(Track track, Context context) {
        PlayerTrack i = PlayerManager.a(context).i();
        if (i != null) {
            Track b = i.b();
            if (b != null && b.getBusinessObjId().equalsIgnoreCase(track.getBusinessObjId())) {
                return true;
            }
        }
        return false;
    }

    public boolean w() {
        return this.W || this.V;
    }

    public boolean x() {
        if (!d) {
            return true;
        }
        d = false;
        return false;
    }

    public void h(boolean z) {
        d = z;
    }

    private boolean M() {
        return this.l;
    }

    public void i(boolean z) {
        this.j = z;
    }

    public void y() {
        if (!this.k) {
            this.k = true;
            v = System.currentTimeMillis();
        }
    }

    public void j(boolean z) {
        this.aq = z;
    }

    @SuppressLint({"LongLogTag"})
    public boolean a(boolean z, boolean z2, int i) {
        if (this.av) {
            this.av = false;
            return false;
        } else if (!GaanaMusicService.c(z2) || (ColombiaAdViewManager.a().f() && !this.aq)) {
            return false;
        } else {
            if (i > 0 || GaanaMusicService.h() >= o() || (this.j && System.currentTimeMillis() - v > ((long) L()))) {
                return t() ^ 1;
            }
            return false;
        }
    }

    @SuppressLint({"LongLogTag"})
    public boolean a(boolean z, int i) {
        if (ColombiaAdViewManager.a().f()) {
            return true;
        }
        boolean z2 = false;
        if (i <= 0 && GaanaMusicService.h() < o() && (!this.j || t - u <= ((long) L()))) {
            return false;
        }
        if (!(l(z) || w() || ((!this.j || u == 0) && u == 0))) {
            z2 = true;
        }
        return z2;
    }

    private boolean l(boolean z) {
        Item item = z ? this.T : this.U;
        String str = z ? "pref_key_fore_loaded_time" : "pref_key_back_loaded_time";
        if (item == null) {
            return false;
        }
        if (System.currentTimeMillis() - d.a().b(0, str, false) <= Constants.SESSION_INACTIVE_PERIOD) {
            return false;
        }
        item.destroy();
        k(z);
        return true;
    }

    public void a(Builder builder, PlayerTrack playerTrack, Context context) {
        this.w = context.getApplicationContext();
        if (m != null) {
            if (builder == null) {
                m.b();
                return;
            }
            if (a(playerTrack.a(true), context)) {
                try {
                    if (this.aA && com.constants.Constants.aJ && !TextUtils.isEmpty(com.constants.Constants.aI)) {
                        Toast.makeText(context, com.constants.Constants.aI, 1).show();
                        this.aA = false;
                    }
                    if (!Util.c() || !this.ay || this.T == null || l(true)) {
                        t = System.currentTimeMillis();
                        if (this.x) {
                            this.x = false;
                            u = t;
                        }
                        if (M() && K() > 0) {
                            u = t;
                            m.b();
                            a(v().a(builder), K());
                        } else if ((Util.c() && this.z && this.T == null) || (!Util.c() && this.y && this.U == null)) {
                            m.b();
                            k(Util.c());
                        } else if (a(Util.c(), playerTrack.b().getAvAd()) && K() > 0) {
                            a(context, Util.c());
                        } else if (w()) {
                            E();
                            h(false);
                            A();
                            C();
                        } else {
                            m.b();
                        }
                    } else {
                        m.a();
                        m.a(true);
                        m.d();
                        a(this.T, context);
                    }
                } catch (Exception unused) {
                    m.b();
                    C();
                }
            }
        }
    }

    public void z() {
        this.x = true;
    }

    public void A() {
        u = System.currentTimeMillis();
    }

    public void a(Builder builder, long j) {
        GaanaApplication.getInstance().updateMetadata();
        if (j == this.F) {
            this.E = true;
        } else {
            this.E = false;
        }
        Long valueOf;
        int i;
        if (this.l) {
            if (this.az) {
                valueOf = Long.valueOf(this.J);
                i = this.A;
                this.A = i + 1;
                builder.addRequest(valueOf, i, "section3", this.e).addGender(GENDER.MALE).enableRecordManualImpression(true).addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).downloadIconBitmap(true).downloadImageBitmap(true);
                this.az = false;
            } else {
                valueOf = Long.valueOf(this.F);
                i = this.A;
                this.A = i + 1;
                Builder addRequest = builder.addRequest(valueOf, i, "section3", this.f);
                Long valueOf2 = Long.valueOf(this.J);
                int i2 = this.A;
                this.A = i2 + 1;
                addRequest.addRequest(valueOf2, i2, "section3", this.e).addGender(GENDER.MALE).enableRecordManualImpression(true).addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).downloadIconBitmap(true).downloadImageBitmap(true);
            }
            this.l = false;
        } else if (this.E) {
            valueOf = Long.valueOf(this.F);
            i = this.A;
            this.A = i + 1;
            builder.addRequest(valueOf, i, "section3", this.f).addGender(GENDER.MALE).enableRecordManualImpression(true).addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).downloadIconBitmap(true).downloadImageBitmap(true);
        } else {
            valueOf = Long.valueOf(this.J);
            i = this.A;
            this.A = i + 1;
            builder.addRequest(valueOf, i, "section3", this.e).addGender(GENDER.MALE).enableRecordManualImpression(true).addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).downloadIconBitmap(true).downloadImageBitmap(true);
        }
        builder.addCustomAudience("GUL", GaanaApplication.getInstance().getSongLanguagesString());
        try {
            Colombia.getNativeAds(builder.build());
        } catch (ColombiaException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void B() {
        Item item = this.V ? this.T : this.U;
        if (w()) {
            E();
        } else if (item != null) {
            item.destroy();
            if (this.V) {
                this.z = true;
                this.V = false;
            } else {
                this.y = true;
                this.W = false;
            }
        }
        a = false;
        AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
        this.E = false;
    }

    private void a(Context context, boolean z) {
        if (this.U == null && this.T == null) {
            m.b();
        } else if (z && this.T != null && this.T.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
            this.j = false;
            d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", false, false);
            m.a();
            m.a(true);
            m.d();
            a(this.T, context);
        } else if (z && this.T != null && this.T.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
            this.j = false;
            d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", false, false);
            m.a();
            m.a(true);
            m.d();
            this.T.show();
        } else if (!z && this.U != null && this.U.getItemType() == ITEM_TYPE.AUDIO) {
            this.j = false;
            h(false);
            m.a();
            a(this.U, context, z);
        } else if (z && this.T != null && this.T.getItemType() == ITEM_TYPE.AUDIO) {
            this.j = false;
            h(false);
            d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", false, false);
            m.a();
            a(this.T, context, z);
        } else if (z && this.T != null && this.T.getItemType() == ITEM_TYPE.AUDIO_BANNER) {
            this.j = false;
            d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", false, false);
            m.a();
            m.a(true);
            m.d();
            this.T.show();
        } else if (z) {
            if (this.T != null) {
                this.T.destroy();
                this.T = null;
                k(true);
            }
            m.b();
        } else if (z) {
            if (z) {
                if (this.T != null) {
                    this.T.destroy();
                    this.T = null;
                    k(true);
                }
            } else if (this.U != null) {
                this.U.destroy();
                this.U = null;
                k(false);
            }
            m.b();
        } else {
            if (this.U != null) {
                this.U.destroy();
                this.U = null;
                k(false);
            }
            m.b();
        }
    }

    private void a(Item item, Context context) {
        N();
        this.n = (ColombiaNativeSponsoredAdView) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.colombia_init_sponsored_ad_banner, null);
        this.o = (WindowManager) context.getSystemService("window");
        this.n.setCallToActionView(this.n.findViewById(R.id.sponsored_call_to_action));
        this.n.setDismissView(this.n.findViewById(R.id.sponsored_dismiss));
        this.n.setImageView(this.n.findViewById(R.id.bgSponsoredImage));
        this.ay = false;
        this.az = false;
        ImageView imageView = (ImageView) this.n.getImageView();
        if (item.getImage() != null) {
            imageView.setImageBitmap(item.getImage());
        } else if (item.getImageUrl() != null) {
            imageView.setImageBitmap(BitmapFactory.decodeFile(item.getImageUrl()));
        }
        Button button = (Button) this.n.getCallToActionView();
        String ctaText = item.getCtaText();
        if (!(ctaText == null || ctaText.isEmpty())) {
            button.setText(ctaText);
        }
        ((TextView) this.n.getDismissView()).setText("Dismiss");
        ((TextView) this.n.getDismissView()).setGravity(1);
        this.n.setPlayAudio(true);
        this.n.setItem(item);
        this.n.commit();
        this.n.getDismissView().setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f.this.j = true;
                if (!(f.this.o == null || f.this.p == null)) {
                    f.this.n.destroy();
                    if (VERSION.SDK_INT < 19) {
                        f.this.o.removeView(f.this.p);
                    } else if (f.this.p.isAttachedToWindow()) {
                        f.this.o.removeView(f.this.p);
                    }
                    f.this.p = null;
                    f.this.o = null;
                }
                f.this.D = false;
                if (f.m != null) {
                    f.m.b();
                }
                f.this.A();
                if (f.this.T != null) {
                    f.this.T.destroy();
                    f.this.T = null;
                    f.this.k(true);
                }
            }
        });
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 48;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.height = -1;
        layoutParams.width = -1;
        layoutParams.type = CastStatusCodes.APPLICATION_NOT_RUNNING;
        layoutParams.flags = 1410;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        this.p = new FrameLayout(context);
        this.p.addView(this.n);
        this.o.addView(this.p, layoutParams);
        this.D = true;
    }

    private void N() {
        if (!(this.o == null || this.p == null)) {
            if (VERSION.SDK_INT < 19) {
                this.o.removeView(this.p);
            } else if (this.p.isAttachedToWindow()) {
                this.o.removeView(this.p);
            }
            this.p = null;
            this.o = null;
        }
        if (this.n != null) {
            this.n.destroy();
        }
        this.D = false;
    }

    public void C() {
        Builder f = ColombiaManager.b().f();
        Builder a = a(f);
        if (f != null && K() > 0) {
            a(a, K());
        }
    }

    public void k(boolean z) {
        Builder a = a(ColombiaManager.b().f());
        long j = z ? this.F : this.J;
        if (z) {
            this.z = false;
        } else {
            this.y = false;
        }
        if (a != null && j > 0) {
            a(a, j);
        }
    }

    private Builder a(Builder builder) {
        PlayerTrack a = PlayerManager.a(this.w).a(PlaySequenceType.CURRENT);
        String currentSponsoredOccassion = GaanaApplication.getInstance().getCurrentSponsoredOccassion();
        if (currentSponsoredOccassion != null) {
            builder.addCustomAudience("OC", currentSponsoredOccassion);
        } else {
            if (a.e() == SOURCE_TYPE.PLAYLIST.ordinal() && GaanaApplication.getInstance().isCurrentALPLSponsored()) {
                builder.addCustomAudience("PL", a.c());
            } else if (a.e() == SOURCE_TYPE.ALBUM.ordinal() && GaanaApplication.getInstance().isCurrentALPLSponsored()) {
                builder.addCustomAudience("AL", a.c());
            } else if (a.e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
                builder.addCustomAudience("RM", a.c());
            } else if (a.e() == SOURCE_TYPE.GAANA_RADIO.ordinal()) {
                builder.addCustomAudience("RL", a.c());
            } else if (a.e() == SOURCE_TYPE.ARTIST.ordinal()) {
                builder.addCustomAudience("AR", a.c());
            }
            if (com.constants.Constants.cY && JukeSessionManager.getInstance().getJukeSessionPlaylist() != null) {
                builder.addCustomAudience(ShareConstants.FEED_SOURCE_PARAM, "party");
                builder.addCustomAudience("cpl", JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
            }
        }
        return builder;
    }

    public String a(PlayerTrack playerTrack) {
        if (playerTrack == null) {
            return null;
        }
        String currentSponsoredOccassion = GaanaApplication.getInstance().getCurrentSponsoredOccassion();
        String str = "";
        StringBuilder stringBuilder;
        if (currentSponsoredOccassion == null) {
            String stringBuilder2;
            StringBuilder stringBuilder3;
            if (playerTrack.e() == SOURCE_TYPE.PLAYLIST.ordinal() && GaanaApplication.getInstance().isCurrentALPLSponsored()) {
                if (com.constants.Constants.cj.get("PL") == null || !((Boolean) com.constants.Constants.cj.get("PL")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&PL=");
                    stringBuilder3.append(playerTrack.c());
                    stringBuilder2 = stringBuilder3.toString();
                }
                str = "&source=party";
                stringBuilder = new StringBuilder();
                stringBuilder.append("&cpl");
                stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
                str = stringBuilder.toString();
            } else if (playerTrack.e() == SOURCE_TYPE.ALBUM.ordinal() && GaanaApplication.getInstance().isCurrentALPLSponsored()) {
                if (com.constants.Constants.cj.get("AL") == null || !((Boolean) com.constants.Constants.cj.get("AL")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&AL=");
                    stringBuilder3.append(playerTrack.c());
                    stringBuilder2 = stringBuilder3.toString();
                }
                str = "&source=party";
                stringBuilder = new StringBuilder();
                stringBuilder.append("&cpl");
                stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
                str = stringBuilder.toString();
            } else if (playerTrack.e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
                if (com.constants.Constants.cj.get("RM") == null || !((Boolean) com.constants.Constants.cj.get("RM")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&RM=");
                    stringBuilder3.append(playerTrack.c());
                    stringBuilder2 = stringBuilder3.toString();
                }
                if (com.constants.Constants.cY && JukeSessionManager.getInstance().getJukeSessionPlaylist() != null) {
                    if (com.constants.Constants.cj.get("party") == null || !((Boolean) com.constants.Constants.cj.get("party")).booleanValue()) {
                        str = "&source=party";
                    }
                    if (com.constants.Constants.cj.get("cpl") == null || !((Boolean) com.constants.Constants.cj.get("cpl")).booleanValue()) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("&cpl");
                        stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
                        str = stringBuilder.toString();
                    }
                }
            } else if (playerTrack.e() == SOURCE_TYPE.GAANA_RADIO.ordinal()) {
                if (com.constants.Constants.cj.get("RL") == null || !((Boolean) com.constants.Constants.cj.get("RL")).booleanValue()) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&RL=");
                    stringBuilder3.append(playerTrack.c());
                    stringBuilder2 = stringBuilder3.toString();
                }
                str = "&source=party";
                stringBuilder = new StringBuilder();
                stringBuilder.append("&cpl");
                stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
                str = stringBuilder.toString();
            } else {
                if (playerTrack.e() == SOURCE_TYPE.ARTIST.ordinal() && (com.constants.Constants.cj.get("AR") == null || !((Boolean) com.constants.Constants.cj.get("AR")).booleanValue())) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("&AR=");
                    stringBuilder3.append(playerTrack.c());
                    stringBuilder2 = stringBuilder3.toString();
                }
                str = "&source=party";
                stringBuilder = new StringBuilder();
                stringBuilder.append("&cpl");
                stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
                str = stringBuilder.toString();
            }
            str = stringBuilder2;
            str = "&source=party";
            stringBuilder = new StringBuilder();
            stringBuilder.append("&cpl");
            stringBuilder.append(JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId());
            str = stringBuilder.toString();
        } else if (com.constants.Constants.cj.get("OC") == null || !((Boolean) com.constants.Constants.cj.get("OC")).booleanValue()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("&OC=");
            stringBuilder.append(currentSponsoredOccassion);
            str = stringBuilder.toString();
        }
        return str;
    }

    private void a(Item item, Context context, boolean z) {
        try {
            if (item.getImage() != null) {
                this.r = item.getImage();
                this.q = this.r;
            }
            if (item.getBrandText() != null) {
                this.s = item.getBrandText();
            } else {
                this.s = "Sponsored Ad";
            }
        } catch (Exception unused) {
            this.q = null;
            this.r = null;
            this.s = "Sponsored Ad";
        }
        if (!z && this.U != null) {
            this.W = true;
            this.U.show();
            if (!(item.getImage() == null || item.getImageUrl() == null)) {
                i.a().a(this.U.getImageUrl(), null);
                String a = n.a((Serializable) item);
                if (a != null) {
                    d.a().a("PREFERENCE_KEY_AUDIO_AD_SERIALIZED_DATA", a, false);
                }
                d.a().a("PREFERENCE_KEY_AUDIO_AD_CALLED_STATUS", true, false);
            }
        } else if (z && this.T != null) {
            this.T.show();
            this.V = true;
        }
        if (a(context) && Util.c()) {
            Intent intent = new Intent(GaanaApplication.getContext(), AudioAdActivity.class);
            intent.putExtra("APP_OPEN", true);
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    private boolean a(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (VERSION.SDK_INT >= 20) {
            return powerManager.isInteractive();
        }
        return powerManager.isScreenOn();
    }

    public boolean D() {
        a = false;
        AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
        boolean z = true;
        if (this.W && this.U != null) {
            this.U.destroy();
            this.U = null;
            this.W = false;
            this.y = true;
            z = false;
        } else if (this.V && this.T != null) {
            this.T.destroy();
            this.T = null;
            this.V = false;
            this.z = true;
        }
        if (GaanaMusicService.s() instanceof com.player_framework.c) {
            ((com.player_framework.c) GaanaMusicService.s()).B();
        }
        return z;
    }

    public void E() {
        if (!this.W && !this.V) {
            return;
        }
        if (this.ax != null) {
            this.ax.updatePlayerOnAdStop();
            D();
            return;
        }
        D();
        d = true;
        if (this.w != null && Util.c()) {
            Intent intent = new Intent(this.w, GaanaActivity.class);
            intent.setFlags(872415232);
            this.w.startActivity(intent);
        }
    }

    public void F() {
        if (this.W) {
            if (this.ax != null) {
                this.ax.updatePlayerOnAdStop();
            }
            a = false;
            AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
            this.W = false;
            if (this.U != null) {
                this.U.destroy();
                this.U = null;
                k(false);
            }
        } else if (this.V) {
            if (this.ax != null) {
                this.ax.updatePlayerOnAdStop();
            }
            a = false;
            AudioAdActivity.SHOW_FAKE_CHOTA_PLAYER = false;
            this.V = false;
            if (this.T != null) {
                this.T.destroy();
                this.T = null;
                k(true);
            }
        }
        A();
    }

    public void G() {
        if (!this.az) {
            Builder f = ColombiaManager.b().f();
            if (f != null && this.F > 0) {
                f.addRequest(Long.valueOf(this.F), this.A, "section3", this.f).addGender(GENDER.MALE).addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).enableRecordManualImpression(true).downloadIconBitmap(true).downloadImageBitmap(true);
                this.A++;
                f.addCustomAudience("GUL", GaanaApplication.getInstance().getSongLanguagesString());
                try {
                    Colombia.getNativeAds(f.build());
                    this.az = true;
                } catch (ColombiaException e) {
                    this.az = false;
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    private void a(Item item, View view, Context context) {
        if (this.g != null) {
            this.g.clear();
            this.g.removeAllViews();
        }
        this.g = new ColombiaNativeVideoAdView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        ColombiaVideoView colombiaVideoView = new ColombiaVideoView(context);
        colombiaVideoView.setLayoutParams(layoutParams);
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            linearLayout.removeAllViews();
            linearLayout.addView(this.g);
            linearLayout.setVisibility(0);
        }
        this.g.addView(colombiaVideoView);
        this.g.setVideoView(colombiaVideoView);
        this.g.setItem(item);
        this.g.commit();
        if (this.h != null) {
            this.h.adPopulated(this.g);
        }
    }

    public void a(long j, final View view, final Context context) {
        Builder f = ColombiaManager.b().f();
        f.addCustomAudience("GUL", GaanaApplication.getInstance().getSongLanguagesString());
        try {
            Colombia.getNativeAds(f.addRequest(new PublisherAdRequest.Builder(Long.valueOf(j), 1, "section1", new AdListener() {
                public void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
                    Item item = (itemResponse.getPaidItems() == null || itemResponse.getPaidItems().size() <= 0) ? (itemResponse.getOrganicItems() == null || itemResponse.getOrganicItems().size() <= 0) ? null : (Item) itemResponse.getOrganicItems().get(0) : (Item) itemResponse.getPaidItems().get(0);
                    f.this.a(item, view, context);
                }
            }).build()).addVideoAutoPlay(GaanaApplication.getInstance().isVideoAutoplay()).build());
        } catch (ColombiaException e) {
            Log.e("NATIVE EXAMPLE", "", e);
        }
    }

    public void H() {
        this.h = null;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public HashMap<String, String> I() {
        return this.ap;
    }

    public void a(UserInfo userInfo) {
        try {
            this.ap = new HashMap();
            this.ap.put("lan", GaanaApplication.getInstance().getSongLanguagesString());
            if (!(userInfo == null || !userInfo.getLoginStatus() || userInfo.getUserProfile() == null)) {
                if (!com.constants.Constants.el) {
                    CharSequence dob = userInfo.getUserProfile().getDob();
                    if (dob.contains("-")) {
                        dob = dob.replaceAll("-", "/");
                    }
                    if (dob != null) {
                        if (dob.contains("0000")) {
                            dob = "00/00/0000";
                        }
                        if (TextUtils.isEmpty(dob)) {
                            this.ap.put(e.K, "undefined");
                        } else {
                            Object obj;
                            int a = com.utilities.a.a(new SimpleDateFormat("dd/MM/yyyy").parse(dob)).a();
                            if (a >= 0) {
                                if (a <= 90) {
                                    if (a < 0 || a > 18) {
                                        obj = a <= 25 ? "19to25" : a <= 35 ? "26to35" : a <= 45 ? "36to45" : a <= 60 ? "46to60" : a <= 90 ? "60plus" : "undefined";
                                        this.ap.put(e.K, obj);
                                    } else {
                                        obj = "0to18";
                                        this.ap.put(e.K, obj);
                                    }
                                }
                            }
                            obj = "undefined";
                            this.ap.put(e.K, obj);
                        }
                    } else {
                        this.ap.put(e.K, "undefined");
                    }
                    String sex = userInfo.getUserProfile().getSex();
                    if (TextUtils.isEmpty(sex)) {
                        this.ap.put("gender", "U");
                    } else {
                        this.ap.put("gender", sex.substring(0, 1).toUpperCase());
                    }
                    return;
                }
            }
            this.ap.put(e.K, "undefined");
            this.ap.put("gender", "U");
        } catch (Exception e) {
            this.ap.put(e.K, "undefined");
            ThrowableExtension.printStackTrace(e);
        }
    }
}
