package com.managers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.bumptech.glide.e;
import com.bumptech.glide.request.a.g;
import com.bumptech.glide.request.b.d;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.ads.publisher.LifeCycleAwarePublisherAdView;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.library.controls.CrossFadeImageView;
import com.til.colombia.android.service.Item;
import com.utilities.Util;

public class ColombiaAdViewManager {
    static boolean a;
    private static ColombiaAdViewManager b;
    private String c = null;
    private String d = null;
    private String e = null;
    private c f;

    public interface b {
        void a(ADSTATUS adstatus);

        void b(ADSTATUS adstatus);

        void c(ADSTATUS adstatus);

        void d(ADSTATUS adstatus);
    }

    public enum ADSTATUS {
        LOADING,
        CLOSED,
        FAILED,
        LOADED
    }

    public interface a {
        void DFPAdFailed();

        void DFPAdLoaded(UnifiedNativeAd unifiedNativeAd);
    }

    public interface c {
        void l();
    }

    public static ColombiaAdViewManager a() {
        if (b == null) {
            b = new ColombiaAdViewManager();
        }
        return b;
    }

    public void a(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.c = str;
    }

    public String b() {
        return this.c;
    }

    public void a(Context context, View view, int i, String str, PublisherAdView publisherAdView, b bVar, String str2, AdsUJData... adsUJDataArr) {
        final View view2 = view;
        AdsUJData[] adsUJDataArr2 = adsUJDataArr;
        Context context2 = context;
        if (ap.a().b(context2)) {
            AdsUJData adsUJData = (adsUJDataArr2 == null || adsUJDataArr2.length <= 0) ? null : adsUJDataArr2[0];
            final AdsUJData adsUJData2 = adsUJData;
            if (view2 != null) {
                View view3 = (LinearLayout) view2.findViewById(R.id.llNativeAdSlot);
                if (adsUJData2 != null) {
                    an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", "start", adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
                }
                final LinearLayout linearLayout = view3;
                final int i2 = i;
                final Context context3 = context2;
                final PublisherAdView publisherAdView2 = publisherAdView;
                final b bVar2 = bVar;
                final String str3 = str2;
                ColombiaManager.b().a(1, context2, 27, -1, view3, str, new com.managers.ColombiaManager.a() {
                    public void onItemLoaded(Item item) {
                        if (linearLayout != null) {
                            linearLayout.setVisibility(0);
                            if (adsUJData2 != null) {
                                an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
                            }
                        }
                    }

                    public void onItemRequestFailed(Exception exception) {
                        if (e.Z == 0) {
                            if (i2 == 27) {
                                ColombiaAdViewManager.this.b(context3, view2, e.z, publisherAdView2, bVar2, str3);
                            } else {
                                ColombiaAdViewManager.this.b(context3, view2, e.A, publisherAdView2, bVar2, str3);
                            }
                        } else if (linearLayout != null) {
                            linearLayout.setVisibility(8);
                        }
                    }
                }, str2);
            }
        }
    }

    public void a(Context context, View view, String str, PublisherAdView publisherAdView, final b bVar, String str2) {
        if (ap.a().p() && !str.equalsIgnoreCase("0")) {
            publisherAdView.setAdUnitId(str);
            publisherAdView.setAdSizes(new AdSize(1, 1));
            publisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                    bVar.a(ADSTATUS.CLOSED);
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    bVar.c(ADSTATUS.FAILED);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        if (bVar != null) {
                            bVar.b(ADSTATUS.LOADED);
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            a(context, publisherAdView, str2);
        }
    }

    public void a(Context context, View view, String str, PublisherAdView publisherAdView, b bVar, int i, String str2, AdsUJData... adsUJDataArr) {
        AdsUJData adsUJData;
        final Context context2 = context;
        View view2 = view;
        String str3 = str;
        PublisherAdView publisherAdView2 = publisherAdView;
        String str4 = str2;
        AdsUJData[] adsUJDataArr2 = adsUJDataArr;
        if (adsUJDataArr2 == null || adsUJDataArr2.length <= 0) {
            adsUJData = new AdsUJData();
            adsUJData.setSectionName(str4);
            adsUJData.setAdUnitCode(str3);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
        } else {
            adsUJData = adsUJDataArr2[0];
        }
        AdsUJData adsUJData2 = adsUJData;
        if (ap.a().b(context2)) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(R.id.llNativeAdSlot);
            if (e.S == 50 || e.S == 100) {
                final CrossFadeImageView crossFadeImageView = new CrossFadeImageView(context2);
                crossFadeImageView.getLayoutParams().height = Util.b(i);
                linearLayout.removeAllViews();
                linearLayout.addView(crossFadeImageView);
                if (e.S == 100) {
                    crossFadeImageView.setBitmapToImageView((int) R.drawable.masthead_320x100);
                } else {
                    crossFadeImageView.setBitmapToImageView((int) R.drawable.masthead_320x50);
                }
                crossFadeImageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        com.services.c.a(context2).a(context2, "http://gaana.com/view/purchase", GaanaApplication.getInstance());
                    }
                });
                if (!TextUtils.isEmpty(e.T)) {
                    e.c(context).asBitmap().load(e.T).into(new g() {
                        public void onResourceReady(Object obj, d dVar) {
                            crossFadeImageView.setBitmapToImageView((Bitmap) obj);
                            crossFadeImageView.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    com.services.c.a(context2).a(context2, e.U, GaanaApplication.getInstance());
                                }
                            });
                        }

                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                        }
                    });
                }
            }
            long b = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
            long b2 = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
            long currentTimeMillis = System.currentTimeMillis();
            if ((b == 0 || b2 == 0 || currentTimeMillis - b >= b2) && publisherAdView2 != null && !TextUtils.isEmpty(str) && !str3.equals("0")) {
                b bVar2 = bVar;
                bVar2.d(ADSTATUS.LOADING);
                if (!(view2 == null || !ap.a().p() || str3.equalsIgnoreCase("0"))) {
                    if (publisherAdView.getAdUnitId() == null) {
                        publisherAdView2.setAdUnitId(str3);
                    }
                    publisherAdView2.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, i));
                    final b bVar3 = bVar2;
                    final LinearLayout linearLayout2 = linearLayout;
                    final PublisherAdView publisherAdView3 = publisherAdView2;
                    final AdsUJData adsUJData3 = adsUJData2;
                    publisherAdView2.setAdListener(new AdListener() {
                        public void onAdClosed() {
                            super.onAdClosed();
                            bVar3.a(ADSTATUS.CLOSED);
                        }

                        public void onAdFailedToLoad(int i) {
                            super.onAdFailedToLoad(i);
                            bVar3.c(ADSTATUS.FAILED);
                        }

                        public void onAdLeftApplication() {
                            super.onAdLeftApplication();
                        }

                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        public void onAdLoaded() {
                            super.onAdLoaded();
                            try {
                                linearLayout2.removeAllViews();
                                linearLayout2.addView(publisherAdView3);
                                linearLayout2.setVisibility(0);
                                bVar3.b(ADSTATUS.LOADED);
                                if (adsUJData3 != null) {
                                    an.a().e("ad", "", adsUJData3.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData3.getSectionIndex(), adsUJData3.getAdUnitCode());
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    if (adsUJData2 != null) {
                        an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", "start", adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
                    }
                    a(context2, publisherAdView2, str4);
                }
            }
        }
    }

    public void b(Context context, View view, String str, PublisherAdView publisherAdView, b bVar, String str2) {
        Context context2 = context;
        View view2 = view;
        String str3 = str;
        PublisherAdView publisherAdView2 = publisherAdView;
        String str4 = str2;
        long b = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        if (b != 0 && b2 != 0 && currentTimeMillis - b < b2) {
            return;
        }
        if (publisherAdView2 == null || TextUtils.isEmpty(str) || str3.equals("0")) {
            return;
        }
        b bVar2 = bVar;
        bVar2.d(ADSTATUS.LOADING);
        AdsUJData adsUJData = new AdsUJData();
        adsUJData.setSectionName(str4);
        adsUJData.setAdUnitCode(str3);
        adsUJData.setSectionId("");
        adsUJData.setAdType("dfp");
        if (ap.a().b(context2) && view2 != null) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(R.id.llNativeAdSlot);
            if (ap.a().p() && !str3.equalsIgnoreCase("0")) {
                if (publisherAdView.getAdUnitId() == null) {
                    publisherAdView2.setAdUnitId(str3);
                }
                publisherAdView2.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, 50));
                final b bVar3 = bVar2;
                final LinearLayout linearLayout2 = linearLayout;
                final PublisherAdView publisherAdView3 = publisherAdView2;
                final AdsUJData adsUJData2 = adsUJData;
                publisherAdView2.setAdListener(new AdListener() {
                    public void onAdClosed() {
                        super.onAdClosed();
                        bVar3.a(ADSTATUS.CLOSED);
                    }

                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        bVar3.c(ADSTATUS.FAILED);
                    }

                    public void onAdLeftApplication() {
                        super.onAdLeftApplication();
                    }

                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    public void onAdLoaded() {
                        super.onAdLoaded();
                        try {
                            bVar3.b(ADSTATUS.LOADED);
                            linearLayout2.removeAllViews();
                            linearLayout2.addView(publisherAdView3);
                            linearLayout2.setVisibility(0);
                            if (adsUJData2 != null) {
                                an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
                            }
                        } catch (Exception unused) {
                        }
                    }
                });
                if (adsUJData != null) {
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                }
                a(context2, publisherAdView2, str4);
            }
        }
    }

    private void a(Context context, AdLoader adLoader, String str) {
        try {
            Location location = ((GaanaActivity) context).getLocation();
            StringBuilder stringBuilder;
            if (location != null) {
                Builder builder = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str);
                    builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                }
                builder.addCustomTargeting("GUL", GaanaApplication.getInstance().getSongLanguagesString());
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                adLoader.loadAd(builder.setLocation(location2).build());
                return;
            }
            Builder builder2 = new Builder();
            if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str);
                builder2.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
            }
            builder2.addCustomTargeting("GUL", GaanaApplication.getInstance().getSongLanguagesString());
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            stringBuilder.append("Gaana ");
            builder2.setPublisherProvidedId(Util.c(stringBuilder.toString()));
            adLoader.loadAd(builder2.build());
        } catch (Exception unused) {
        }
    }

    private void a(Context context, PublisherAdView publisherAdView, String str) {
        try {
            Location location = ((GaanaActivity) context).getLocation();
            StringBuilder stringBuilder;
            if (location != null) {
                Builder builder = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str);
                    builder.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                publisherAdView.loadAd(builder.setLocation(location2).build());
                return;
            }
            Builder builder2 = new Builder();
            if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str);
                builder2.addNetworkExtras(GaanaApplication.getInstance().getNetworkExtrasBundle());
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.l(GaanaApplication.getContext()));
            stringBuilder.append("Gaana ");
            builder2.setPublisherProvidedId(Util.c(stringBuilder.toString()));
            publisherAdView.loadAd(builder2.build());
        } catch (Exception unused) {
        }
    }

    public void a(Context context, LinearLayout linearLayout, String str) {
        a(context, linearLayout, str, "", new AdsUJData[0]);
    }

    public void a(Context context, LinearLayout linearLayout, String str, String str2, AdsUJData... adsUJDataArr) {
        a(context, linearLayout, str, str2, null, adsUJDataArr);
    }

    public ILifeCycleAwareCustomView a(Context context, LinearLayout linearLayout, String str, String str2, com.services.l.a aVar, AdsUJData... adsUJDataArr) {
        AdsUJData adsUJData;
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        AdsUJData[] adsUJDataArr2 = adsUJDataArr;
        if (adsUJDataArr2 == null || adsUJDataArr2.length <= 0) {
            adsUJData = new AdsUJData();
            adsUJData.setSectionName(str4);
            adsUJData.setAdUnitCode(str3);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
        } else {
            adsUJData = adsUJDataArr2[0];
        }
        AdsUJData adsUJData2 = adsUJData;
        d();
        e();
        long b = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        if ((b == 0 || b2 == 0 || currentTimeMillis - b >= b2) && ap.a().p() && !str3.equalsIgnoreCase("0")) {
            PublisherAdView publisherAdView = new PublisherAdView(context2);
            publisherAdView.setAdUnitId(str3);
            publisherAdView.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, 50));
            final com.services.l.a aVar2 = aVar;
            final LinearLayout linearLayout2 = linearLayout;
            final PublisherAdView publisherAdView2 = publisherAdView;
            final AdsUJData adsUJData3 = adsUJData2;
            publisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (aVar2 != null) {
                        aVar2.onAdBottomBannerGone();
                    }
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        linearLayout2.removeAllViews();
                        linearLayout2.addView(publisherAdView2);
                        linearLayout2.setVisibility(0);
                        if (adsUJData3 != null) {
                            an.a().e("ad", "", adsUJData3.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData3.getSectionIndex(), adsUJData3.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                    if (aVar2 != null) {
                        aVar2.onAdBottomBannerLoaded();
                    }
                }
            });
            if (adsUJData2 != null) {
                try {
                    an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", "start", adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
                } catch (Exception unused) {
                }
            }
            Location location = ((GaanaActivity) context2).getLocation();
            if (location != null) {
                Builder builder = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str4);
                    AdMobExtras networkExtrasBundle = GaanaApplication.getInstance().getNetworkExtrasBundle();
                    if ("PARTYDETAILS_BOTTOM_BANNER".equalsIgnoreCase(str4)) {
                        networkExtrasBundle.getExtras().putString("party_source", this.d);
                        networkExtrasBundle.getExtras().putString("source_playlist_id", this.e);
                    }
                    builder.addNetworkExtras(networkExtrasBundle);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                Location location2 = new Location("");
                location2.setLatitude(location.getLatitude());
                location2.setLongitude(location.getLongitude());
                publisherAdView.loadAd(builder.setLocation(location2).build());
            } else {
                Builder builder2 = new Builder();
                if (GaanaApplication.getInstance().getNetworkExtrasBundle() != null) {
                    GaanaApplication.getInstance().getNetworkExtrasBundle().getExtras().putString("section_name", str4);
                    AdMobExtras networkExtrasBundle2 = GaanaApplication.getInstance().getNetworkExtrasBundle();
                    if ("PARTYDETAILS_BOTTOM_BANNER".equalsIgnoreCase(str4)) {
                        networkExtrasBundle2.getExtras().putString("party_source", this.d);
                        networkExtrasBundle2.getExtras().putString("source_playlist_id", this.e);
                    }
                    builder2.addNetworkExtras(networkExtrasBundle2);
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                stringBuilder2.append("Gaana ");
                builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                linearLayout.setVisibility(8);
                publisherAdView.loadAd(builder2.build());
            }
            LifeCycleAwarePublisherAdView lifeCycleAwarePublisherAdView = new LifeCycleAwarePublisherAdView();
            lifeCycleAwarePublisherAdView.wrap(publisherAdView);
            return lifeCycleAwarePublisherAdView;
        }
        return null;
    }

    public void a(PublisherAdView publisherAdView) {
        if (publisherAdView != null) {
            publisherAdView.destroy();
        }
    }

    public void a(Context context, View view) {
        if (!ap.a().b(context) && view != null) {
            ((LinearLayout) view.findViewById(R.id.llNativeAdSlot)).setVisibility(8);
        }
    }

    public void a(View view) {
        if (view != null) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llNativeAdSlot);
            linearLayout.removeAllViews();
            linearLayout.setVisibility(8);
        }
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public void c() {
        if (this.f != null) {
            this.f.l();
        }
    }

    public void a(View view, Context context, String str) {
        Context context2 = context;
        View view2 = view;
        String str2 = str;
        a().a(context2, view2, str2, new PublisherAdView(context), new b() {
            public void a(ADSTATUS adstatus) {
            }

            public void b(ADSTATUS adstatus) {
            }

            public void c(ADSTATUS adstatus) {
            }

            public void d(ADSTATUS adstatus) {
            }
        }, "");
    }

    public void a(Context context, ViewGroup viewGroup, PublisherAdView publisherAdView, String str, com.managers.ColombiaManager.a aVar, int i, String str2, AdsUJData... adsUJDataArr) {
        AdsUJData adsUJData;
        Context context2 = context;
        PublisherAdView publisherAdView2 = publisherAdView;
        String str3 = str;
        String str4 = str2;
        AdsUJData[] adsUJDataArr2 = adsUJDataArr;
        if (adsUJDataArr2 == null || adsUJDataArr2.length <= 0) {
            adsUJData = new AdsUJData();
            adsUJData.setSectionName(str4);
            adsUJData.setAdUnitCode(str3);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
        } else {
            adsUJData = adsUJDataArr2[0];
        }
        AdsUJData adsUJData2 = adsUJData;
        long b = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        if (b != 0 && b2 != 0 && currentTimeMillis - b < b2) {
            return;
        }
        if (publisherAdView2 == null || TextUtils.isEmpty(str) || str3.equals("0")) {
            return;
        }
        e();
        if (!ap.a().b(context2) || viewGroup == null || !ap.a().p() || str3.equalsIgnoreCase("0")) {
        } else {
            if (publisherAdView.getAdUnitId() == null) {
                publisherAdView.setAdUnitId(str);
            }
            publisherAdView2.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, i));
            final com.managers.ColombiaManager.a aVar2 = aVar;
            final Context context3 = context2;
            final PublisherAdView publisherAdView3 = publisherAdView2;
            final ViewGroup viewGroup2 = viewGroup;
            final AdsUJData adsUJData3 = adsUJData2;
            publisherAdView2.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (aVar2 != null) {
                        aVar2.onItemRequestFailed(null);
                    }
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        com.services.d.a().a("MASTHEAD_DISPLAY_COUNT", com.services.d.a().b("MASTHEAD_DISPLAY_COUNT", 0, false) + 1, false);
                        FrameLayout frameLayout = new FrameLayout(context3);
                        frameLayout.setLayoutParams(new LayoutParams(Util.a(context3, (int) ModuleDescriptor.MODULE_VERSION), Util.a(context3, 100)));
                        frameLayout.addView(publisherAdView3);
                        viewGroup2.removeAllViews();
                        viewGroup2.addView(frameLayout);
                        viewGroup2.setPadding(0, context3.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half), 0, context3.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half));
                        viewGroup2.setVisibility(0);
                        if (aVar2 != null) {
                            aVar2.onItemLoaded(null);
                        }
                        if (adsUJData3 != null) {
                            an.a().e("ad", "", adsUJData3.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData3.getSectionIndex(), adsUJData3.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData2 != null) {
                an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", "start", adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
            }
            a(context2, publisherAdView2, str4);
        }
    }

    public void a(Context context, String str, final a aVar) {
        AdLoader.Builder builder = new AdLoader.Builder(context, str);
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (aVar != null) {
                    aVar.DFPAdLoaded(unifiedNativeAd);
                }
            }
        });
        a(context, builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                if (aVar != null) {
                    aVar.DFPAdFailed();
                }
            }

            public void onAdLoaded() {
                super.onAdLoaded();
            }
        }).build(), "");
    }

    public void a(Context context, ViewGroup viewGroup, PublisherAdView publisherAdView, String str, com.managers.ColombiaManager.a aVar, String str2, AdsUJData... adsUJDataArr) {
        AdsUJData adsUJData;
        Context context2 = context;
        PublisherAdView publisherAdView2 = publisherAdView;
        String str3 = str;
        String str4 = str2;
        AdsUJData[] adsUJDataArr2 = adsUJDataArr;
        if (adsUJDataArr2 == null || adsUJDataArr2.length <= 0) {
            adsUJData = new AdsUJData();
            adsUJData.setSectionName(str4);
            adsUJData.setAdUnitCode(str3);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
        } else {
            adsUJData = adsUJDataArr2[0];
        }
        AdsUJData adsUJData2 = adsUJData;
        long b = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = com.services.d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        if (b != 0 && b2 != 0 && currentTimeMillis - b < b2) {
            return;
        }
        if (publisherAdView2 == null || TextUtils.isEmpty(str) || str3.equals("0")) {
            return;
        }
        if (!ap.a().b(context2) || viewGroup == null || !ap.a().p() || str3.equalsIgnoreCase("0")) {
        } else {
            if (publisherAdView.getAdUnitId() == null) {
                publisherAdView.setAdUnitId(str);
            }
            publisherAdView2.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, 100));
            final com.managers.ColombiaManager.a aVar2 = aVar;
            final Context context3 = context2;
            final PublisherAdView publisherAdView3 = publisherAdView2;
            final ViewGroup viewGroup2 = viewGroup;
            final AdsUJData adsUJData3 = adsUJData2;
            publisherAdView2.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    aVar2.onItemRequestFailed(null);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        FrameLayout frameLayout = new FrameLayout(context3);
                        frameLayout.setLayoutParams(new LayoutParams(Util.a(context3, (int) ModuleDescriptor.MODULE_VERSION), Util.a(context3, 100)));
                        frameLayout.addView(publisherAdView3);
                        viewGroup2.removeAllViews();
                        viewGroup2.addView(frameLayout);
                        viewGroup2.setVisibility(0);
                        aVar2.onItemLoaded(null);
                        if (adsUJData3 != null) {
                            an.a().e("ad", "", adsUJData3.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData3.getSectionIndex(), adsUJData3.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData2 != null) {
                an.a().e("ad", "", adsUJData2.getSectionId(), "ad_load", "", "start", adsUJData2.getSectionIndex(), adsUJData2.getAdUnitCode());
            }
            a(context2, publisherAdView2, str4);
        }
    }

    public static void a(ADSTATUS adstatus) {
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");
            stringBuilder.append(adstatus);
            Log.e("adstatus", stringBuilder.toString());
        }
    }

    public void d() {
        if (Util.c()) {
            GaanaApplication.getInstance().setNetworkExtrasBundle("GC", "FOREGROUND");
        } else {
            GaanaApplication.getInstance().setNetworkExtrasBundle("GC", "BACKGROUND");
        }
    }

    public void e() {
        f.v();
        if (f()) {
            GaanaApplication.getInstance().setNetworkExtrasBundle("campaign", f.v().e());
        }
    }

    public boolean f() {
        f v = f.v();
        return !TextUtils.isEmpty(v.e()) && ((long) v.d()) + v.l() >= System.currentTimeMillis() / 1000;
    }
}
