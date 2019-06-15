package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.PlayerMaterialActionBar;
import com.actionbar.PlayerMaterialActionBar.PlayerVersion;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.VideoCardPagerAdapter;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.VideoFeed;
import com.gaana.models.VideoFeedItemData;
import com.gaana.models.VideoFeedMetaData;
import com.gaana.view.CustomTextView;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.GaanaMiniListView.GaanaMiniParentViewHolder;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.library.controls.RoundedCornerImageView;
import com.managers.URLManager;
import com.managers.ap;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.services.l.af;
import com.services.l.al;
import com.services.l.bb;
import com.utilities.Util;
import com.views.VideoSlidingUpPanelLayout;
import com.views.VideoSlidingUpPanelLayout.PanelState;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.TypeCastException;

public class GaanaVideoPlayerFragment extends BaseGaanaFragment implements OnClickListener, a {
    private static String U = "BUNDLE_YOUTUBE_VIDEO";
    private static String V = "BUNDLE_YOUTUBE_SECTION";
    private static String W = "BUNDLE_YOUTUBE_SEEK_POSITION";
    public static final a a = new a();
    private int A = -1;
    private boolean B;
    private boolean C;
    private Integer D = Integer.valueOf(0);
    private BusinessObject E;
    private VideoCardPagerAdapter F;
    private YouTubeVideo G;
    private String H = "";
    private String I = GAANA_ENTRY_PAGE.VIDEO_FEED.name();
    private final al J = new i(this);
    private TimerTask K;
    private Timer L = new Timer();
    private boolean M;
    private final OnTouchListener N = new g(this);
    private GestureDetectorCompat O;
    private final int P = 120;
    private final int Q = 80;
    private final j R = new j(this);
    private final bb S = m.a;
    private Boolean T = Boolean.valueOf(false);
    private HashMap X;
    private RecyclerView b;
    private ViewGroup c;
    private ViewGroup d;
    private b e;
    private HorizontalScrollView f;
    private LinearLayout g;
    private BaseGaanaFragment h;
    private PlayerMaterialActionBar i;
    private TextView j;
    private TextView k;
    private ImageView l;
    private VideoSlidingUpPanelLayout m;
    private Toolbar n;
    private View o;
    private TextView p;
    private ViewPager q;
    private ImageView r;
    private ArrayList<Item> s;
    private ArrayList<Item> t;
    private String u = "";
    private String v = "";
    private String w;
    private String x;
    private ProgressBar y;
    private FrameLayout z;

    public enum Direction {
        up,
        down,
        left,
        right;
        
        public static final a Companion = null;

        public static final class a {
            private final boolean a(double d, float f, float f2) {
                return d >= ((double) f) && d < ((double) f2);
            }

            private a() {
            }

            public /* synthetic */ a(a aVar) {
                this();
            }

            public final Direction a(double d) {
                a aVar = this;
                if (aVar.a(d, 45.0f, 135.0f)) {
                    return Direction.up;
                }
                if (aVar.a(d, 0.0f, 45.0f) || aVar.a(d, 315.0f, 360.0f)) {
                    return Direction.right;
                }
                if (aVar.a(d, 225.0f, 315.0f)) {
                    return Direction.down;
                }
                return Direction.left;
            }
        }

        static {
            Companion = new a();
        }
    }

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(a aVar) {
            this();
        }

        public final String a() {
            return GaanaVideoPlayerFragment.U;
        }

        public final String b() {
            return GaanaVideoPlayerFragment.V;
        }

        public final String c() {
            return GaanaVideoPlayerFragment.W;
        }

        public final String d() {
            return a();
        }

        public final String e() {
            return b();
        }

        public final String f() {
            return c();
        }
    }

    public static final class c {
        private BusinessObject a;
        private int b;
        private String c;

        public c(BusinessObject businessObject, int i, String str) {
            kotlin.jvm.internal.c.b(businessObject, "businessObject");
            kotlin.jvm.internal.c.b(str, "header");
            this.a = businessObject;
            this.b = i;
            this.c = str;
        }

        public final BusinessObject a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final String c() {
            return this.c;
        }
    }

    public static final class f implements OnGestureListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public boolean onDown(MotionEvent motionEvent) {
            kotlin.jvm.internal.c.b(motionEvent, com.facebook.ads.internal.g.e.a);
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            kotlin.jvm.internal.c.b(motionEvent, com.facebook.ads.internal.g.e.a);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            kotlin.jvm.internal.c.b(motionEvent, "e1");
            kotlin.jvm.internal.c.b(motionEvent2, "e2");
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            kotlin.jvm.internal.c.b(motionEvent, com.facebook.ads.internal.g.e.a);
        }

        f(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            kotlin.jvm.internal.c.b(motionEvent, com.facebook.ads.internal.g.e.a);
            this.a.t();
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            VideoSlidingUpPanelLayout a;
            kotlin.jvm.internal.c.b(motionEvent, "e1");
            kotlin.jvm.internal.c.b(motionEvent2, "e2");
            Direction a2 = this.a.a(motionEvent.getX(), motionEvent.getY(), motionEvent2.getX(), motionEvent2.getY());
            if (!(a2 != Direction.down || this.a.B || this.a.a() == null)) {
                VideoSlidingUpPanelLayout a3 = this.a.a();
                if (a3 == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (a3.e()) {
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.g();
                    return true;
                }
            }
            if (a2 == Direction.up && !this.a.B) {
                a = this.a.a();
                if (this.a.C) {
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.h();
                    this.a.t();
                    return true;
                } else if (a != null) {
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (a.f()) {
                        a = this.a.a();
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.h();
                    }
                }
            }
            return false;
        }
    }

    static final class g implements OnTouchListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        g(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            GestureDetectorCompat A = this.a.O;
            if (A == null) {
                kotlin.jvm.internal.c.a();
            }
            return A.onTouchEvent(motionEvent);
        }
    }

    public static final class k implements AnimatorListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public void onAnimationCancel(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
        }

        public void onAnimationRepeat(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
        }

        k(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void onAnimationStart(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
            ViewGroup h = this.a.c;
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            h.setVisibility(0);
            VideoSlidingUpPanelLayout a = this.a.a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            if (a.e()) {
                a = this.a.a();
                if (a == null) {
                    kotlin.jvm.internal.c.a();
                }
                a.g();
            }
            TextView i = this.a.j;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setVisibility(0);
            i = this.a.k;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setVisibility(0);
            LinearLayout k = this.a.g;
            if (k == null) {
                kotlin.jvm.internal.c.a();
            }
            k.setVisibility(0);
            RecyclerView c = this.a.b;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            c.setVisibility(0);
            ImageView l = this.a.r;
            if (l == null) {
                kotlin.jvm.internal.c.a();
            }
            l.setVisibility(0);
            a = this.a.a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setSlidingEnabled(true);
            a = this.a.a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setmSlideState(PanelState.COLLAPSED);
        }

        public void onAnimationEnd(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
            ViewGroup h = this.a.c;
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            h.setClickable(true);
            TextView i = this.a.j;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setClickable(true);
            i = this.a.k;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setClickable(true);
            LinearLayout k = this.a.g;
            if (k == null) {
                kotlin.jvm.internal.c.a();
            }
            k.setClickable(true);
            RecyclerView c = this.a.b;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            c.setClickable(true);
            ImageView l = this.a.r;
            if (l == null) {
                kotlin.jvm.internal.c.a();
            }
            l.setClickable(true);
            this.a.M = false;
            this.a.c();
        }
    }

    public static final class l implements AnimatorListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public void onAnimationCancel(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
        }

        public void onAnimationRepeat(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
        }

        l(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void onAnimationStart(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
            ViewGroup h = this.a.c;
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            h.setClickable(false);
            TextView i = this.a.j;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setClickable(false);
            i = this.a.k;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setClickable(false);
            LinearLayout k = this.a.g;
            if (k == null) {
                kotlin.jvm.internal.c.a();
            }
            k.setClickable(false);
            RecyclerView c = this.a.b;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            c.setClickable(false);
            ImageView l = this.a.r;
            if (l == null) {
                kotlin.jvm.internal.c.a();
            }
            l.setClickable(false);
            VideoSlidingUpPanelLayout a = this.a.a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setTouchEnabled(false);
        }

        public void onAnimationEnd(Animator animator) {
            kotlin.jvm.internal.c.b(animator, "animation");
            ViewGroup h = this.a.c;
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            h.setVisibility(4);
            TextView i = this.a.j;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setVisibility(4);
            i = this.a.k;
            if (i == null) {
                kotlin.jvm.internal.c.a();
            }
            i.setVisibility(4);
            LinearLayout k = this.a.g;
            if (k == null) {
                kotlin.jvm.internal.c.a();
            }
            k.setVisibility(4);
            RecyclerView c = this.a.b;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            c.setVisibility(4);
            ImageView l = this.a.r;
            if (l == null) {
                kotlin.jvm.internal.c.a();
            }
            l.setVisibility(4);
        }
    }

    static final class o implements OnClickListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;
        final /* synthetic */ TextView b;
        final /* synthetic */ VideoFeed c;

        o(GaanaVideoPlayerFragment gaanaVideoPlayerFragment, TextView textView, VideoFeed videoFeed) {
            this.a = gaanaVideoPlayerFragment;
            this.b = textView;
            this.c = videoFeed;
        }

        public final void onClick(View view) {
            if ((kotlin.jvm.internal.c.a(this.a.p, this.b) ^ 1) != 0) {
                String url = this.c.getUrl();
                String section_name = this.c.getSection_name();
                if (!TextUtils.isEmpty(this.a.H) && Integer.valueOf(this.c.getSection_type()).equals(Integer.valueOf(1))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(url);
                    stringBuilder.append("&video_id=");
                    url = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(url);
                    stringBuilder.append(this.a.H);
                    url = stringBuilder.toString();
                }
                this.a.a(this.b);
                TextView e = this.a.p;
                if (e == null) {
                    kotlin.jvm.internal.c.a();
                }
                e.setTypeface(null);
                this.a.p = this.b;
                this.b.setTypeface(Util.i(this.a.mContext));
                com.logging.e.c().a = section_name;
                GaanaVideoPlayerFragment gaanaVideoPlayerFragment = this.a;
                kotlin.jvm.internal.c.a((Object) section_name, "sectionName");
                gaanaVideoPlayerFragment.u = section_name;
                gaanaVideoPlayerFragment = this.a;
                kotlin.jvm.internal.c.a((Object) url, "url");
                gaanaVideoPlayerFragment.a(url, section_name);
                u.a().a("VideoFeed", "TapOnTag", section_name);
            }
        }
    }

    public static final class p extends TimerTask {
        final /* synthetic */ GaanaVideoPlayerFragment a;
        final /* synthetic */ Handler b;

        static final class a implements Runnable {
            final /* synthetic */ p a;

            a(p pVar) {
                this.a = pVar;
            }

            public final void run() {
                if (!this.a.a.M) {
                    Context context = this.a.a.mContext;
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
                    } else if (((GaanaActivity) context).hasWindowFocus()) {
                        this.a.a.M = true;
                        this.a.a.d();
                        this.a.a.b(0);
                        return;
                    }
                }
                this.a.a.M = false;
            }
        }

        p(GaanaVideoPlayerFragment gaanaVideoPlayerFragment, Handler handler) {
            this.a = gaanaVideoPlayerFragment;
            this.b = handler;
        }

        public void run() {
            this.b.post(new a(this));
        }
    }

    private final class b extends Adapter<ViewHolder> {
        final /* synthetic */ GaanaVideoPlayerFragment a;
        private int b;
        private boolean c;
        private ArrayList<Item> d;

        public b(GaanaVideoPlayerFragment gaanaVideoPlayerFragment, ArrayList<Item> arrayList) {
            kotlin.jvm.internal.c.b(arrayList, "items");
            this.a = gaanaVideoPlayerFragment;
            this.d = arrayList;
        }

        public final void a(int i, boolean z, boolean z2) {
            this.c = z;
            int i2 = !z2 ? this.b : -1;
            this.b = i;
            if (!this.c) {
                if (!z2) {
                    notifyItemChanged(i2);
                }
                notifyItemChanged(this.b);
            }
        }

        public final void a(ArrayList<Item> arrayList, boolean z) {
            kotlin.jvm.internal.c.b(arrayList, "itemsList");
            this.d = arrayList;
            this.c = z;
            notifyDataSetChanged();
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            kotlin.jvm.internal.c.b(viewGroup, "parent");
            i = (int) (((float) (Util.U() - (4 * Util.b(8)))) * 0.5625f);
            Object from = LayoutInflater.from(viewGroup.getContext());
            kotlin.jvm.internal.c.a(from, "LayoutInflater.from(parent.context)");
            Object inflate = from.inflate(R.layout.item_playlist_grid_110x150, viewGroup, false);
            kotlin.jvm.internal.c.a(inflate, "inflater.inflate(R.layou…d_110x150, parent, false)");
            from = inflate.findViewById(R.id.imgProductIcon);
            kotlin.jvm.internal.c.a(from, "convertView.findViewById(R.id.imgProductIcon)");
            RoundedCornerImageView roundedCornerImageView = (RoundedCornerImageView) from;
            LayoutParams layoutParams = roundedCornerImageView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.height = i;
            roundedCornerImageView.setLayoutParams(layoutParams2);
            from = inflate.findViewById(R.id.selectedBg);
            kotlin.jvm.internal.c.a(from, "convertView.findViewById<View>(R.id.selectedBg)");
            layoutParams = from.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
            layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.height = i;
            from.setLayoutParams(layoutParams2);
            from = inflate.findViewById(R.id.shadow_layer);
            kotlin.jvm.internal.c.a(from, "convertView.findViewById(R.id.shadow_layer)");
            layoutParams = from.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
            layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.height = i;
            from.setLayoutParams(layoutParams2);
            from = inflate.findViewById(R.id.tvTopHeadingMix);
            kotlin.jvm.internal.c.a(from, "convertView.findViewById(R.id.tvTopHeadingMix)");
            CustomTextView customTextView = (CustomTextView) from;
            layoutParams = customTextView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
            layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin = i;
            customTextView.setLayoutParams(layoutParams2);
            return new PlaylistGridHolder(inflate);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            kotlin.jvm.internal.c.b(viewHolder, "holder");
            boolean z = viewHolder instanceof GaanaMiniParentViewHolder;
            GaanaVideoPlayerFragment gaanaVideoPlayerFragment = this.a;
            Object obj = this.d.get(i);
            kotlin.jvm.internal.c.a(obj, "items.get(position)");
            gaanaVideoPlayerFragment.a(i, viewHolder, (BusinessObject) obj, null, "hello", null);
            Object findViewById;
            if (this.c) {
                findViewById = viewHolder.itemView.findViewById(R.id.selectedBg);
                kotlin.jvm.internal.c.a(findViewById, "holder.itemView.findView…Id<View>(R.id.selectedBg)");
                findViewById.setVisibility(4);
            } else if (i == this.b) {
                findViewById = viewHolder.itemView.findViewById(R.id.selectedBg);
                kotlin.jvm.internal.c.a(findViewById, "holder.itemView.findView…Id<View>(R.id.selectedBg)");
                findViewById.setVisibility(0);
            } else {
                findViewById = viewHolder.itemView.findViewById(R.id.selectedBg);
                kotlin.jvm.internal.c.a(findViewById, "holder.itemView.findView…Id<View>(R.id.selectedBg)");
                findViewById.setVisibility(4);
            }
        }

        public int getItemCount() {
            return this.d.size();
        }
    }

    public static final class d implements af {
        final /* synthetic */ GaanaVideoPlayerFragment a;
        final /* synthetic */ String b;

        public void onErrorResponse(BusinessObject businessObject) {
            kotlin.jvm.internal.c.b(businessObject, "businessObject");
        }

        d(GaanaVideoPlayerFragment gaanaVideoPlayerFragment, String str) {
            this.a = gaanaVideoPlayerFragment;
            this.b = str;
        }

        public void onRetreivalComplete(Object obj) {
            kotlin.jvm.internal.c.b(obj, "businessObj");
            ProgressBar m = this.a.y;
            if (m == null) {
                kotlin.jvm.internal.c.a();
            }
            m.setVisibility(8);
            ArrayList arrayList = (ArrayList) null;
            VideoFeedItemData videoFeedItemData = (VideoFeedItemData) obj;
            ArrayList entities = videoFeedItemData.getEntities();
            if (entities != null) {
                ViewPager r;
                RecyclerView c;
                if (this.a.e == null) {
                    this.a.s = entities;
                    GaanaVideoPlayerFragment gaanaVideoPlayerFragment = this.a;
                    GaanaVideoPlayerFragment gaanaVideoPlayerFragment2 = this.a;
                    obj = videoFeedItemData.getEntities();
                    kotlin.jvm.internal.c.a(obj, "(businessObj).entities");
                    gaanaVideoPlayerFragment.e = new b(gaanaVideoPlayerFragment2, obj);
                    c = this.a.b;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    c.setLayoutManager(new GridLayoutManager(this.a.mContext, 3));
                    c = this.a.b;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    c.setAdapter(this.a.e);
                } else {
                    b n = this.a.e;
                    if (n == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    obj = videoFeedItemData.getEntities();
                    kotlin.jvm.internal.c.a(obj, "(businessObj).entities");
                    n.a(obj, this.a.v.equals(this.a.u) ^ 1);
                    c = this.a.b;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    c.scrollToPosition(0);
                }
                if (this.a.F == null) {
                    GaanaVideoPlayerFragment gaanaVideoPlayerFragment3 = this.a;
                    Context context = this.a.mContext;
                    BaseGaanaFragment q = this.a.h;
                    if (q == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.fragments.GaanaVideoPlayerFragment");
                    }
                    gaanaVideoPlayerFragment3.F = new VideoCardPagerAdapter(context, (GaanaVideoPlayerFragment) q, this.a.q, entities, this.a.b());
                }
                if (this.a.t == null) {
                    this.a.w = this.b;
                    r = this.a.q;
                    if (r == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    r.setAdapter(this.a.F);
                    r = this.a.q;
                    if (r == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    r.setOffscreenPageLimit(2);
                    r = this.a.q;
                    if (r == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    r.addOnPageChangeListener(this.a.R);
                    com.logging.e.c().b = this.a.I;
                    VideoCardPagerAdapter p = this.a.F;
                    if (p == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    p.playVideoOnItemClick(0, this.a.A);
                    this.a.A = -1;
                    this.a.a(0);
                }
                this.a.x = this.b;
                this.a.t = entities;
                this.a.d();
                this.a.M = false;
                this.a.c();
                r = this.a.q;
                if (r == null) {
                    kotlin.jvm.internal.c.a();
                }
                r.setOnTouchListener(this.a.N);
                this.a.s();
            }
        }
    }

    public static final class e implements af {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public void onErrorResponse(BusinessObject businessObject) {
            kotlin.jvm.internal.c.b(businessObject, "businessObject");
        }

        e(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void onRetreivalComplete(Object obj) {
            kotlin.jvm.internal.c.b(obj, "businessObj");
            Context context = this.a.mContext;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.BaseActivity");
            }
            ((BaseActivity) context).hideProgressDialog();
            GaanaVideoPlayerFragment gaanaVideoPlayerFragment = this.a;
            ArrayList video_feed = ((VideoFeedMetaData) obj).getVideo_feed();
            kotlin.jvm.internal.c.a((Object) video_feed, "(businessObj as VideoFeedMetaData).video_feed");
            gaanaVideoPlayerFragment.a(video_feed);
        }
    }

    public static final class h implements com.views.VideoSlidingUpPanelLayout.b {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public void a(View view, float f) {
            kotlin.jvm.internal.c.b(view, "panel");
        }

        h(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void a(View view, PanelState panelState, PanelState panelState2) {
            kotlin.jvm.internal.c.b(view, "panel");
            kotlin.jvm.internal.c.b(panelState, "previousState");
            kotlin.jvm.internal.c.b(panelState2, "newState");
            ImageView a;
            if (panelState2 == PanelState.EXPANDED) {
                a = this.a.l;
                if (a == null) {
                    kotlin.jvm.internal.c.a();
                }
                a.setRotation(180.0f);
            } else if (panelState2 == PanelState.COLLAPSED) {
                a = this.a.l;
                if (a == null) {
                    kotlin.jvm.internal.c.a();
                }
                a.setRotation(360.0f);
                this.a.M = false;
                this.a.c();
            }
        }
    }

    public static final class i implements al {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        public void a(boolean z) {
        }

        i(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void a(ViewHolder viewHolder, int i) {
            this.a.e();
            this.a.M = true;
        }
    }

    public static final class j implements OnPageChangeListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;
        private float b;
        private Boolean c = Boolean.valueOf(false);

        j(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                VideoCardPagerAdapter p = this.a.F;
                if (p == null) {
                    kotlin.jvm.internal.c.a();
                }
                p.updatePlayer(0, 0);
            }
            this.a.D = Integer.valueOf(i);
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (this.a.a() != null) {
                VideoSlidingUpPanelLayout a = this.a.a();
                if (a == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (a.e()) {
                    VideoSlidingUpPanelLayout a2 = this.a.a();
                    if (a2 == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a2.g();
                    return;
                }
            }
            float f2 = ((float) i) + f;
            this.c = Boolean.valueOf(f2 > this.b);
            Boolean bool = this.c;
            if (bool == null) {
                kotlin.jvm.internal.c.a();
            }
            VideoCardPagerAdapter p;
            Integer B;
            if (bool.booleanValue()) {
                p = this.a.F;
                if (p == null) {
                    kotlin.jvm.internal.c.a();
                }
                B = this.a.D;
                if (B == null) {
                    kotlin.jvm.internal.c.a();
                }
                p.updatePlayer(B.intValue(), 1);
            } else if (this.a.t != null) {
                ArrayList s = this.a.t;
                if (s == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (i < s.size() - 1) {
                    p = this.a.F;
                    if (p == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    B = this.a.D;
                    if (B == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    p.updatePlayer(B.intValue(), -1);
                }
            }
            this.b = f2;
            this.a.M = false;
        }

        public void onPageSelected(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" onPageSelected, position ");
            stringBuilder.append(i);
            Log.v("gb103", stringBuilder.toString());
            Boolean h = this.a.h();
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            if (h.booleanValue()) {
                this.a.a(Boolean.valueOf(false));
                return;
            }
            h = this.c;
            if (h == null) {
                kotlin.jvm.internal.c.a();
            }
            if (h.booleanValue()) {
                u.a().a("VideoFeed", "Swipe", "Left");
            } else {
                u.a().a("VideoFeed", "Swipe", "Right");
            }
            this.a.a(i);
            if (this.a.e != null) {
                b n = this.a.e;
                if (n == null) {
                    kotlin.jvm.internal.c.a();
                }
                n.a(i, this.a.v.equals(this.a.u) ^ 1, false);
            }
            this.a.M = false;
            this.a.d();
            this.a.c();
        }
    }

    static final class m implements bb {
        public static final m a = new m();

        m() {
        }
    }

    public static final class n extends OnScrollListener {
        final /* synthetic */ GaanaVideoPlayerFragment a;

        n(GaanaVideoPlayerFragment gaanaVideoPlayerFragment) {
            this.a = gaanaVideoPlayerFragment;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0) {
                if (recyclerView == null) {
                    kotlin.jvm.internal.c.a();
                }
                LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.GridLayoutManager");
                }
                VideoSlidingUpPanelLayout a;
                if (((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.setScrollingView(null);
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.setScrollableView(null);
                } else {
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.setScrollingView(this.a.b);
                    a = this.a.a();
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.setScrollableView(this.a.b);
                }
                this.a.B = false;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (i != 0) {
                this.a.B = true;
            }
        }
    }

    public void l() {
        if (this.X != null) {
            this.X.clear();
        }
    }

    public VideoSlidingUpPanelLayout a() {
        return this.m;
    }

    public void a(VideoSlidingUpPanelLayout videoSlidingUpPanelLayout) {
        this.m = videoSlidingUpPanelLayout;
    }

    public void onSaveInstanceState(Bundle bundle) {
        kotlin.jvm.internal.c.b(bundle, "outState");
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        kotlin.jvm.internal.c.b(layoutInflater, "inflater");
        if (this.d == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            View contentView = setContentView(R.layout.fragment_video_player, viewGroup);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            this.d = (ViewGroup) contentView;
            ViewGroup viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.b = (RecyclerView) viewGroup2.findViewById(R.id.recycler_view);
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.c = (ViewGroup) viewGroup2.findViewById(R.id.ll_queue_container);
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.f = (HorizontalScrollView) viewGroup2.findViewById(R.id.horiz_scroll_view);
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.g = (LinearLayout) viewGroup2.findViewById(R.id.horiz_scroll_item_view);
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.q = (ViewPager) viewGroup2.findViewById(R.id.viewPager);
            this.i = new PlayerMaterialActionBar(getContext(), PlayerVersion.PlayerVideo);
            PlayerMaterialActionBar playerMaterialActionBar = this.i;
            if (playerMaterialActionBar == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = playerMaterialActionBar.findViewById(R.id.trackText);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.j = (TextView) contentView;
            playerMaterialActionBar = this.i;
            if (playerMaterialActionBar == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = playerMaterialActionBar.findViewById(R.id.albumText);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.k = (TextView) contentView;
            playerMaterialActionBar = this.i;
            if (playerMaterialActionBar == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = playerMaterialActionBar.findViewById(R.id.menu_icon);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            this.r = (ImageView) contentView;
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.l = (ImageView) viewGroup2.findViewById(R.id.chevron_up);
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            a((VideoSlidingUpPanelLayout) viewGroup2.findViewById(R.id.sliding_layout_video));
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = viewGroup2.findViewById(R.id.progressbar);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
            }
            this.y = (ProgressBar) contentView;
            viewGroup2 = this.d;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.z = (FrameLayout) viewGroup2.findViewById(R.id.playerTopLayout);
            VideoSlidingUpPanelLayout a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setOverlayed(true);
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setBackgroundResource(R.drawable.shape_bg_transparant);
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setShadowHeight(0);
            RecyclerView recyclerView = this.b;
            if (recyclerView == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = recyclerView;
            Object k = Util.k();
            kotlin.jvm.internal.c.a(k, "Util.getScreenResolution()");
            a(contentView, -1, Integer.parseInt((String) n.b((CharSequence) k, new String[]{AvidJSONUtil.KEY_X}, false, 0, 6, null).get(0)) / 2);
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            FrameLayout frameLayout = (FrameLayout) a.findViewById(R.id.drag_view);
            o();
            p();
            this.h = this;
            n();
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setScrollingView(this.b);
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setScrollableView(this.b);
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            a.setPanelSlideListener(new h(this));
            recyclerView = this.b;
            if (recyclerView == null) {
                kotlin.jvm.internal.c.a();
            }
            recyclerView.addItemDecoration(new com.views.d(3, Util.b(8), true));
            recyclerView = this.b;
            if (recyclerView == null) {
                kotlin.jvm.internal.c.a();
            }
            recyclerView.setItemAnimator((ItemAnimator) null);
        }
        m();
        updateView();
        setGAScreenName("Gaana Videos Feed Screen", "Gaana Videos Feed Screen");
        return this.d;
    }

    private final void m() {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            kotlin.jvm.internal.c.a();
        }
        recyclerView.setOnScrollListener(new n(this));
    }

    private final void a(View view, int i, int i2) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        view.setLayoutParams(layoutParams);
    }

    private final void a(int i) {
        Item item = (Item) null;
        ArrayList arrayList = this.s;
        if (arrayList == null) {
            kotlin.jvm.internal.c.a();
        }
        Item item2 = (Item) arrayList.get(i);
        BusinessObject f = Util.f(item2);
        if (f == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.youtube.YouTubeVideos.YouTubeVideo");
        }
        YouTubeVideo youTubeVideo = (YouTubeVideo) f;
        if (item2 != null) {
            TextView textView = this.j;
            if (textView == null) {
                kotlin.jvm.internal.c.a();
            }
            textView.setText(item2.getName());
            TextView textView2 = this.k;
            if (textView2 == null) {
                kotlin.jvm.internal.c.a();
            }
            textView2.setText(youTubeVideo.g());
            textView2 = this.j;
            if (textView2 == null) {
                kotlin.jvm.internal.c.a();
            }
            textView2.setVisibility(0);
            textView2 = this.k;
            if (textView2 == null) {
                kotlin.jvm.internal.c.a();
            }
            textView2.setVisibility(0);
        }
    }

    private final void n() {
        ViewGroup viewGroup = this.d;
        if (viewGroup == null) {
            kotlin.jvm.internal.c.a();
        }
        View findViewById = viewGroup.findViewById(R.id.toolbar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.Toolbar");
        }
        this.n = (Toolbar) findViewById;
        Toolbar toolbar = this.n;
        if (toolbar == null) {
            kotlin.jvm.internal.c.a();
        }
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar = this.n;
        if (toolbar == null) {
            kotlin.jvm.internal.c.a();
        }
        toolbar.getMenu().clear();
        toolbar = this.n;
        if (toolbar == null) {
            kotlin.jvm.internal.c.a();
        }
        toolbar.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.transparent_color));
        toolbar = this.n;
        if (toolbar == null) {
            kotlin.jvm.internal.c.a();
        }
        toolbar.addView(this.i);
        PlayerMaterialActionBar playerMaterialActionBar = this.i;
        if (playerMaterialActionBar == null) {
            kotlin.jvm.internal.c.a();
        }
        playerMaterialActionBar.setToolbar(this.n);
        playerMaterialActionBar = this.i;
        if (playerMaterialActionBar == null) {
            kotlin.jvm.internal.c.a();
        }
        playerMaterialActionBar.setVisibility(0);
        toolbar = this.n;
        if (toolbar == null) {
            kotlin.jvm.internal.c.a();
        }
        toolbar.bringToFront();
    }

    private final void o() {
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            if (arguments == null) {
                kotlin.jvm.internal.c.a();
            }
            if (arguments.getSerializable(U) != null) {
                arguments = getArguments();
                if (arguments == null) {
                    kotlin.jvm.internal.c.a();
                }
                Serializable serializable = arguments.getSerializable(U);
                if (serializable == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.youtube.YouTubeVideos.YouTubeVideo");
                }
                this.G = (YouTubeVideo) serializable;
                arguments = getArguments();
                if (arguments == null) {
                    kotlin.jvm.internal.c.a();
                }
                Object string = arguments.getString(V);
                Bundle arguments2 = getArguments();
                if (arguments2 == null) {
                    kotlin.jvm.internal.c.a();
                }
                this.A = arguments2.getInt(W);
                YouTubeVideo youTubeVideo = this.G;
                if (youTubeVideo == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (!TextUtils.isEmpty(youTubeVideo.getBusinessObjId())) {
                    youTubeVideo = this.G;
                    if (youTubeVideo == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    Object businessObjId = youTubeVideo.getBusinessObjId();
                    kotlin.jvm.internal.c.a(businessObjId, "youTubeVideoFromBundle!!.businessObjId");
                    this.H = businessObjId;
                }
                if (!TextUtils.isEmpty((CharSequence) string)) {
                    kotlin.jvm.internal.c.a(string, "launchedFrom");
                    this.I = string;
                }
            }
        }
    }

    private final void p() {
        URLManager uRLManager = new URLManager();
        uRLManager.c(0);
        uRLManager.a(VideoFeedMetaData.class);
        uRLManager.a("https://apiv2.gaana.com/video/metadata");
        com.i.i.a().a((af) new e(this), uRLManager);
    }

    private final void a(ArrayList<VideoFeed> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            VideoFeed videoFeed = (VideoFeed) it.next();
            TextView textView = new TextView(this.mContext);
            textView.setText(videoFeed.getSection_name());
            textView.setBackgroundResource(R.drawable.video_feed_category_border);
            textView.setPadding(Util.b(14), Util.b(7), Util.b(14), Util.b(7));
            textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(Util.b(3), Util.b(10), Util.b(2), Util.b(10));
            textView.setLayoutParams(layoutParams);
            textView.setTag(videoFeed);
            textView.setOnClickListener(new o(this, textView, videoFeed));
            LinearLayout linearLayout = this.g;
            if (linearLayout == null) {
                kotlin.jvm.internal.c.a();
            }
            linearLayout.addView(textView);
            if (Integer.valueOf(videoFeed.getSection_type()).equals(Integer.valueOf(1))) {
                com.logging.e.c().a = videoFeed.getSection_name();
                a(textView);
                this.p = textView;
                Object section_name = videoFeed.getSection_name();
                kotlin.jvm.internal.c.a(section_name, "(item).section_name");
                this.u = section_name;
                section_name = videoFeed.getSection_name();
                kotlin.jvm.internal.c.a(section_name, "(item).section_name");
                this.v = section_name;
                String url = videoFeed.getUrl();
                kotlin.jvm.internal.c.a((Object) url, "item.url");
                if (!TextUtils.isEmpty(this.H)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(url);
                    stringBuilder.append("&video_id=");
                    url = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(url);
                    stringBuilder.append(this.H);
                    url = stringBuilder.toString();
                }
                textView.setTypeface(Util.i(this.mContext));
                String section_name2 = videoFeed.getSection_name();
                kotlin.jvm.internal.c.a((Object) section_name2, "item.section_name");
                a(url, section_name2);
            }
        }
    }

    private final void a(TextView textView) {
        if (textView == null) {
            kotlin.jvm.internal.c.a();
        }
        textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.black));
        textView.setBackgroundResource(R.drawable.video_feed_category_border_selected);
        if (this.p != null) {
            textView = this.p;
            if (textView == null) {
                kotlin.jvm.internal.c.a();
            }
            textView.setBackgroundResource(R.drawable.video_feed_category_border);
            textView = this.p;
            if (textView == null) {
                kotlin.jvm.internal.c.a();
            }
            textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
        }
    }

    public final al b() {
        return this.J;
    }

    public final void c() {
        if (!this.M) {
            Context context = this.mContext;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
            } else if (((GaanaActivity) context).hasWindowFocus()) {
                Handler handler = new Handler(Looper.getMainLooper());
                if (this.K != null) {
                    TimerTask timerTask = this.K;
                    if (timerTask == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    timerTask.cancel();
                }
                Timer timer = this.L;
                if (timer == null) {
                    kotlin.jvm.internal.c.a();
                }
                timer.cancel();
                this.L = new Timer();
                this.K = new p(this, handler);
                try {
                    this.L.schedule(this.K, 7000);
                } catch (Exception unused) {
                }
            }
        }
    }

    private final void b(int i) {
        VideoSlidingUpPanelLayout a;
        if (a() != null) {
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            if (a.e()) {
                d();
                return;
            }
        }
        if (a() != null) {
            a = a();
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            if (a.f()) {
                q();
            }
        }
    }

    public final void d() {
        if (this.K != null) {
            TimerTask timerTask = this.K;
            if (timerTask == null) {
                kotlin.jvm.internal.c.a();
            }
            timerTask.cancel();
        }
        if (this.L != null) {
            Timer timer = this.L;
            if (timer == null) {
                kotlin.jvm.internal.c.a();
            }
            timer.cancel();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    public final void e() {
        /*
        r1 = this;
        r0 = r1.a();
        if (r0 == 0) goto L_0x0017;
    L_0x0006:
        r0 = r1.a();
        if (r0 != 0) goto L_0x000f;
    L_0x000c:
        kotlin.jvm.internal.c.a();
    L_0x000f:
        r0 = r0.e();
        if (r0 == 0) goto L_0x0017;
    L_0x0015:
        r0 = 1;
        goto L_0x0018;
    L_0x0017:
        r0 = 0;
    L_0x0018:
        if (r0 == 0) goto L_0x0027;
    L_0x001a:
        r0 = r1.a();
        if (r0 != 0) goto L_0x0023;
    L_0x0020:
        kotlin.jvm.internal.c.a();
    L_0x0023:
        r0.g();
        return;
    L_0x0027:
        r0 = r1.f();
        if (r0 == 0) goto L_0x0031;
    L_0x002d:
        r1.r();
        goto L_0x0034;
    L_0x0031:
        r1.q();
    L_0x0034:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.GaanaVideoPlayerFragment.e():void");
    }

    private final void q() {
        if (this.F != null) {
            VideoCardPagerAdapter videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter != null) {
                videoCardPagerAdapter.setPlayerFadeout(true);
            }
        }
        this.C = true;
        TextView textView = this.j;
        if (textView == null) {
            kotlin.jvm.internal.c.a();
        }
        textView.animate().alpha(0.0f).setDuration(500).start();
        textView = this.k;
        if (textView == null) {
            kotlin.jvm.internal.c.a();
        }
        textView.animate().alpha(0.0f).setDuration(500).start();
        LinearLayout linearLayout = this.g;
        if (linearLayout == null) {
            kotlin.jvm.internal.c.a();
        }
        linearLayout.animate().alpha(0.0f).setDuration(500).start();
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            kotlin.jvm.internal.c.a();
        }
        recyclerView.animate().alpha(0.0f).setDuration(500).start();
        ImageView imageView = this.r;
        if (imageView == null) {
            kotlin.jvm.internal.c.a();
        }
        imageView.animate().alpha(0.0f).setDuration(500).start();
        ViewGroup viewGroup = this.c;
        if (viewGroup == null) {
            kotlin.jvm.internal.c.a();
        }
        viewGroup.animate().alpha(0.0f).setListener(new l(this)).setDuration(500).start();
        this.C = true;
    }

    private final void r() {
        if (this.F != null) {
            VideoCardPagerAdapter videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter != null) {
                videoCardPagerAdapter.setPlayerFadeout(false);
            }
        }
        this.C = false;
        TextView textView = this.j;
        if (textView == null) {
            kotlin.jvm.internal.c.a();
        }
        textView.animate().alpha(1.0f).setDuration(500).start();
        textView = this.k;
        if (textView == null) {
            kotlin.jvm.internal.c.a();
        }
        textView.animate().alpha(1.0f).setDuration(500).start();
        LinearLayout linearLayout = this.g;
        if (linearLayout == null) {
            kotlin.jvm.internal.c.a();
        }
        linearLayout.animate().alpha(1.0f).setDuration(500).start();
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            kotlin.jvm.internal.c.a();
        }
        recyclerView.animate().alpha(1.0f).setDuration(500).start();
        ImageView imageView = this.r;
        if (imageView == null) {
            kotlin.jvm.internal.c.a();
        }
        imageView.animate().alpha(1.0f).setDuration(500).start();
        ViewGroup viewGroup = this.c;
        if (viewGroup == null) {
            kotlin.jvm.internal.c.a();
        }
        viewGroup.animate().alpha(1.0f).setListener(new k(this)).setDuration(500).start();
    }

    public final boolean f() {
        return this.C;
    }

    private final void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            ProgressBar progressBar = this.y;
            if (progressBar == null) {
                kotlin.jvm.internal.c.a();
            }
            progressBar.setVisibility(0);
            URLManager uRLManager = new URLManager();
            uRLManager.c(0);
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.a(VideoFeedItemData.class);
            uRLManager.a(str);
            com.i.i.a().a((af) new d(this, str2), uRLManager);
        }
    }

    public final boolean g() {
        if (this.t == null) {
            return true;
        }
        VideoCardPagerAdapter videoCardPagerAdapter;
        if (this.F == null) {
            Context context = this.mContext;
            BaseGaanaFragment baseGaanaFragment = this.h;
            if (baseGaanaFragment == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.fragments.GaanaVideoPlayerFragment");
            }
            this.F = new VideoCardPagerAdapter(context, (GaanaVideoPlayerFragment) baseGaanaFragment, this.q, this.t, this.J);
            videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            videoCardPagerAdapter.setUpdateLastSongPlayedDuration();
            ViewPager viewPager = this.q;
            if (viewPager == null) {
                kotlin.jvm.internal.c.a();
            }
            viewPager.setAdapter(this.F);
            viewPager = this.q;
            if (viewPager == null) {
                kotlin.jvm.internal.c.a();
            }
            viewPager.setOffscreenPageLimit(1);
            viewPager = this.q;
            if (viewPager == null) {
                kotlin.jvm.internal.c.a();
            }
            viewPager.addOnPageChangeListener(this.R);
        }
        if (this.F == null || (!TextUtils.isEmpty(this.w) && m.a(this.w, this.x, true))) {
            return false;
        }
        videoCardPagerAdapter = this.F;
        if (videoCardPagerAdapter == null) {
            kotlin.jvm.internal.c.a();
        }
        videoCardPagerAdapter.setUpdateLastSongPlayedDuration();
        this.w = this.x;
        videoCardPagerAdapter = this.F;
        if (videoCardPagerAdapter == null) {
            kotlin.jvm.internal.c.a();
        }
        videoCardPagerAdapter.updateList(this.t);
        videoCardPagerAdapter = this.F;
        if (videoCardPagerAdapter == null) {
            kotlin.jvm.internal.c.a();
        }
        videoCardPagerAdapter.notifyDataSetChanged();
        return true;
    }

    private final void s() {
        if (this.O == null) {
            this.O = new GestureDetectorCompat(this.mContext, new f(this));
        }
    }

    public final Direction a(float f, float f2, float f3, float f4) {
        return Direction.Companion.a(b(f, f2, f3, f4));
    }

    public final double b(float f, float f2, float f3, float f4) {
        double d = (double) 180;
        return ((((Math.atan2((double) (f2 - f4), (double) (f3 - f)) + 3.141592653589793d) * d) / 3.141592653589793d) + d) % ((double) 360);
    }

    private final void t() {
        VideoCardPagerAdapter videoCardPagerAdapter;
        if (this.J != null) {
            this.J.a(null, -1);
        }
        if (this.C) {
            videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            if (videoCardPagerAdapter.getVideoController() != null) {
                videoCardPagerAdapter = this.F;
                if (videoCardPagerAdapter == null) {
                    kotlin.jvm.internal.c.a();
                }
                videoCardPagerAdapter.getVideoController().setVisibility(0);
                return;
            }
        }
        videoCardPagerAdapter = this.F;
        if (videoCardPagerAdapter == null) {
            kotlin.jvm.internal.c.a();
        }
        if (videoCardPagerAdapter.getVideoController() != null) {
            videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            videoCardPagerAdapter.getVideoController().setVisibility(8);
        }
    }

    public final View a(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str, com.dynamicview.f.a aVar) {
        Object obj;
        ViewHolder viewHolder2 = viewHolder;
        BusinessObject businessObject2 = businessObject;
        String str2 = str;
        kotlin.jvm.internal.c.b(viewHolder2, "viewHolder");
        kotlin.jvm.internal.c.b(businessObject2, "businessObj");
        kotlin.jvm.internal.c.b(str2, "headerName");
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder2;
        this.o = playlistGridHolder.itemView;
        c cVar = new c(businessObject2, i, str2);
        View view = this.o;
        if (view == null) {
            kotlin.jvm.internal.c.a();
        }
        view.setTag(cVar);
        view = this.o;
        if (view == null) {
            kotlin.jvm.internal.c.a();
        }
        view.setOnClickListener(this);
        Item item = (Item) businessObject2;
        String artwork = item.getArtwork();
        if (!(Constants.cN || artwork == null)) {
            artwork = m.a(artwork, "80x80", "175x175", false, 4, null);
        }
        String g = Util.g(this.mContext, artwork);
        if ((kotlin.jvm.internal.c.a(item.getEntityType(), com.constants.c.c.g) || kotlin.jvm.internal.c.a(item.getEntityType(), com.constants.c.c.i)) && playlistGridHolder.tvSectionTitle != null) {
            obj = playlistGridHolder.tvSectionTitle;
            kotlin.jvm.internal.c.a(obj, "holder.tvSectionTitle");
            obj.setVisibility(0);
            obj = playlistGridHolder.tvSectionTitle;
            kotlin.jvm.internal.c.a(obj, "holder.tvSectionTitle");
            obj.setText(item.getName());
            playlistGridHolder.tvSectionTitle.setTypeface(null, 1);
        } else if (playlistGridHolder.tvSectionTitle != null) {
            obj = playlistGridHolder.tvSectionTitle;
            kotlin.jvm.internal.c.a(obj, "holder.tvSectionTitle");
            obj.setVisibility(8);
        }
        List entityInfo = item.getEntityInfo();
        int i2 = (kotlin.jvm.internal.c.a(item.getEntityType(), com.constants.c.c.g) || kotlin.jvm.internal.c.a(item.getEntityType(), com.constants.c.c.i)) ? 1 : 0;
        if (!(playlistGridHolder.tvSectionTitle == null || i2 == 0)) {
            Object obj2 = playlistGridHolder.tvSectionTitle;
            kotlin.jvm.internal.c.a(obj2, "holder.tvSectionTitle");
            obj2.setTypeface(Util.h(this.mContext));
            playlistGridHolder.tvSectionTitle.setAllCaps(true);
            playlistGridHolder.tvSectionTitle.setTextSize(2, 14.0f);
        }
        if (entityInfo != null) {
            int size = entityInfo.size();
            for (int i3 = 0; i3 < size; i3++) {
                Object obj3 = entityInfo.get(i3);
                kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                if (kotlin.jvm.internal.c.a(((EntityInfo) obj3).getKey(), (Object) "parental_warning")) {
                    obj3 = entityInfo.get(i3);
                    kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                    if (((EntityInfo) obj3).getValue() instanceof Double) {
                        obj3 = entityInfo.get(i3);
                        kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                        obj3 = ((EntityInfo) obj3).getValue();
                        if (obj3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Double");
                        }
                        int compare = Double.compare(((Double) obj3).doubleValue(), 1.0d);
                    } else {
                        obj3 = entityInfo.get(i3);
                        kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                        kotlin.jvm.internal.c.a(((EntityInfo) obj3).getValue(), (Object) "1");
                    }
                } else if (i2 != 0) {
                    obj3 = entityInfo.get(i3);
                    kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                    if (kotlin.jvm.internal.c.a(((EntityInfo) obj3).getKey(), (Object) "bg_colour")) {
                        obj3 = entityInfo.get(i3);
                        kotlin.jvm.internal.c.a(obj3, "entityInfos[i]");
                        obj3 = ((EntityInfo) obj3).getValue();
                        if (obj3 != null) {
                            String obj4 = obj3.toString();
                            if (!TextUtils.isEmpty(obj4)) {
                                GradientDrawable gradientDrawable = new GradientDrawable();
                                gradientDrawable.setCornerRadius((float) Util.b(4));
                                gradientDrawable.setColor(Color.parseColor(obj4));
                                playlistGridHolder.crossFadeImageView.setImageDrawable(gradientDrawable);
                            }
                        }
                    }
                }
            }
        }
        if (playlistGridHolder.tvTopHeadingMix != null) {
            obj = playlistGridHolder.tvTopHeadingMix;
            kotlin.jvm.internal.c.a(obj, "holder.tvTopHeadingMix");
            obj.setVisibility(0);
            playlistGridHolder.tvTopHeadingMix.setText(item.getName());
        }
        if (Constants.cN) {
            if (g == null) {
                kotlin.jvm.internal.c.a();
            }
            if (n.a((CharSequence) g, (CharSequence) "175x175", false, 2, null)) {
                g = m.a(g, "175x175", "80x80", false, 4, null);
            }
        }
        if (i2 == 0) {
            if (aVar != null && aVar.e() == VIEW_SIZE.GRID_LARGE.getNumVal()) {
                g = item.getArtworkSpecific();
            }
            CrossFadeImageView crossFadeImageView = playlistGridHolder.crossFadeImageView;
            obj = this.mAppState;
            kotlin.jvm.internal.c.a(obj, "mAppState");
            crossFadeImageView.bindImage(businessObject2, g, obj.isAppInOfflineMode());
            Object obj5 = playlistGridHolder.crossFadeImageView;
            kotlin.jvm.internal.c.a(obj5, "holder.crossFadeImageView");
            obj5.setScaleType(ScaleType.FIT_XY);
        }
        Object obj6 = playlistGridHolder.itemView;
        kotlin.jvm.internal.c.a(obj6, "holder.itemView");
        obj6.setVisibility(0);
        View view2 = this.o;
        if (view2 == null) {
            kotlin.jvm.internal.c.a();
        }
        return view2;
    }

    public final void a(Boolean bool) {
        this.T = bool;
    }

    public final Boolean h() {
        return this.T;
    }

    public void onClick(View view) {
        if (view == null) {
            kotlin.jvm.internal.c.a();
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.fragments.GaanaVideoPlayerFragment.TagObject");
        }
        c cVar = (c) tag;
        BusinessObject a = cVar.a();
        if (a == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.BusinessObject");
        }
        this.E = a;
        cVar.b();
        cVar.c();
        this.s = this.t;
        this.v = this.u;
        this.T = Boolean.valueOf(true);
        if (this.E instanceof Item) {
            BusinessObject businessObject = this.E;
            if (businessObject == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Item");
            }
            Item item = (Item) businessObject;
            BusinessObject businessObject2 = this.E;
            if (businessObject2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Item");
            }
            Object entityType = ((Item) businessObject2).getEntityType();
            if (kotlin.jvm.internal.c.a(entityType, com.constants.c.c.f)) {
                com.logging.e.c().b = GAANA_ENTRY_PAGE.VIDEO_FEED.name();
                a = Util.f(item);
                if (a == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.youtube.YouTubeVideos.YouTubeVideo");
                }
                YouTubeVideo youTubeVideo = (YouTubeVideo) a;
                a((BusinessObject) youTubeVideo, cVar.b());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Position ");
                stringBuilder.append(cVar.b());
                stringBuilder.append(" - Video - ");
                stringBuilder.append(youTubeVideo.getBusinessObjId());
                u.a().a("VideoFeed", "TapOnVideo", stringBuilder.toString());
            } else if (kotlin.jvm.internal.c.a(entityType, com.constants.c.c.h)) {
                if (Util.j((Context) getActivity())) {
                    tag = this.mAppState;
                    kotlin.jvm.internal.c.a(tag, "mAppState");
                    if (!tag.isAppInOfflineMode()) {
                        String str = "";
                        String str2 = "";
                        String str3 = "";
                        for (Object obj : item.getEntityInfo()) {
                            kotlin.jvm.internal.c.a(obj, "entityInfo");
                            if (m.a(obj.getKey(), "ad_url", true)) {
                                str = obj.getValue().toString();
                            } else if (m.a(obj.getKey(), "dl_url", true)) {
                                str2 = obj.getValue().toString();
                            } else if (m.a(obj.getKey(), "web_dl_url", true)) {
                                str3 = obj.getValue().toString();
                            }
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            com.services.c.a(this.mContext).a(this.mContext, str2, this.mAppState);
                        } else if (!TextUtils.isEmpty(str)) {
                            Intent intent = new Intent(this.mContext, WebViewActivity.class);
                            intent.putExtra("EXTRA_WEBVIEW_URL", str);
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                            intent.putExtra("title", item.getName());
                            this.mContext.startActivity(intent);
                        } else if (!TextUtils.isEmpty(str3)) {
                            if (!(m.a(str3, "http://", false, 2, null) || m.a(str3, "https://", false, 2, null))) {
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("http://");
                                stringBuilder2.append(str3);
                                str3 = stringBuilder2.toString();
                            }
                            this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str3)));
                        }
                    }
                }
                ap.a().f(this.mContext);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(BusinessObject businessObject, int i) {
        kotlin.jvm.internal.c.b(businessObject, "businessObject");
        boolean z = this.w == null || !m.a(this.w, this.x, false);
        g();
        a(i);
        if (this.e != null) {
            b bVar;
            if (z) {
                bVar = this.e;
                if (bVar == null) {
                    kotlin.jvm.internal.c.a();
                }
                bVar.a(i, false, true);
            } else {
                bVar = this.e;
                if (bVar == null) {
                    kotlin.jvm.internal.c.a();
                }
                bVar.a(i, 1 ^ this.v.equals(this.u), false);
            }
        }
        VideoCardPagerAdapter videoCardPagerAdapter = this.F;
        if (videoCardPagerAdapter == null) {
            kotlin.jvm.internal.c.a();
        }
        videoCardPagerAdapter.playVideoOnItemClick(i, -1, z);
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.t = (ArrayList) null;
        this.x = (String) null;
        if (this.F != null) {
            VideoCardPagerAdapter videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            videoCardPagerAdapter.releasePlayers();
        }
        if (Constants.dc) {
            Object instance = GaanaApplication.getInstance();
            kotlin.jvm.internal.c.a(instance, "GaanaApplication.getInstance()");
            if (instance.isAppInForeground()) {
                com.player_framework.o.c(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                Constants.dc = false;
            }
        }
        l();
    }

    public void onResume() {
        super.onResume();
        Context context;
        if (GaanaMusicService.t()) {
            context = this.mContext;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
            }
            ((GaanaActivity) context).popBackStackImmediate();
        } else if (this.F != null) {
            VideoCardPagerAdapter videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            if (videoCardPagerAdapter.isPlaying()) {
                context = this.mContext;
                if (context == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
                }
                ((GaanaActivity) context).getWindow().addFlags(128);
            }
        }
    }

    public void onPause() {
        super.onPause();
        Context context = this.mContext;
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
        }
        ((GaanaActivity) context).getWindow().clearFlags(128);
    }

    public void onStop() {
        super.onStop();
        Context context = this.mContext;
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
        }
        ((GaanaActivity) context).getWindow().clearFlags(128);
        if (this.F != null) {
            VideoCardPagerAdapter videoCardPagerAdapter = this.F;
            if (videoCardPagerAdapter == null) {
                kotlin.jvm.internal.c.a();
            }
            videoCardPagerAdapter.pausePlayer();
        }
    }
}
