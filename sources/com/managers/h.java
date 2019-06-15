package com.managers;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.library.controls.CrossFadeImageView;
import com.services.d;
import com.utilities.Util;

public class h {
    private static h a;

    public static h a() {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    a = new h();
                }
            }
        }
        return a;
    }

    private h() {
    }

    public void a(Context context, String str, int i, View view, boolean z, final AdListener adListener) {
        long b = d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
        long b2 = d.a().b(0, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
        long currentTimeMillis = System.currentTimeMillis();
        if (b == 0 || b2 == 0 || currentTimeMillis - b >= b2) {
            final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llNativeAdSlot);
            final int i2 = i;
            final Context context2 = context;
            final boolean z2 = z;
            AdLoader build = new Builder(context, str).withAdListener(new AdListener() {
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    adListener.onAdFailedToLoad(i);
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    adListener.onAdLoaded();
                }
            }).forUnifiedNativeAd(new OnUnifiedNativeAdLoadedListener() {
                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    int dimension = i2 == 0 ? (int) context2.getResources().getDimension(R.dimen.list_padding) : 0;
                    if (linearLayout != null) {
                        linearLayout.removeAllViews();
                        linearLayout.setPadding(dimension, 0, 0, 0);
                        linearLayout.addView(h.this.a(context2, unifiedNativeAd, i2, z2));
                        linearLayout.setVisibility(0);
                    }
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder().setAdChoicesPlacement(1).setImageOrientation(1).build()).build();
            GaanaApplication instance = GaanaApplication.getInstance();
            try {
                PublisherAdRequest.Builder builder = new PublisherAdRequest.Builder();
                if (instance.getNetworkExtrasBundle() != null) {
                    builder.addNetworkExtras(instance.getNetworkExtrasBundle());
                }
                Location location = ((GaanaActivity) context).getLocation();
                if (location != null) {
                    Location location2 = new Location("");
                    location2.setLatitude(location.getLatitude());
                    location2.setLongitude(location.getLongitude());
                    builder.setLocation(location2);
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Util.l(GaanaApplication.getContext()));
                stringBuilder.append("Gaana ");
                builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                build.loadAd(builder.build());
            } catch (Exception unused) {
            }
        }
    }

    public View a(Context context, UnifiedNativeAd unifiedNativeAd, int i, boolean z) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        UnifiedNativeAdView unifiedNativeAdView = null;
        if (i == 0) {
            unifiedNativeAdView = (UnifiedNativeAdView) layoutInflater.inflate(R.layout.dfp_native_home_grid_ad, null);
        } else if (i == 31) {
            unifiedNativeAdView = (UnifiedNativeAdView) layoutInflater.inflate(R.layout.dfp_native_list_item_ad, null);
        }
        if (unifiedNativeAdView != null) {
            TextView textView;
            if (!TextUtils.isEmpty(unifiedNativeAd.getHeadline())) {
                textView = (TextView) unifiedNativeAdView.findViewById(R.id.ad_headline);
                textView.setText(unifiedNativeAd.getHeadline());
                unifiedNativeAdView.setHeadlineView(textView);
            }
            CrossFadeImageView crossFadeImageView = (CrossFadeImageView) unifiedNativeAdView.findViewById(R.id.ad_img);
            if (unifiedNativeAd.getIcon() != null) {
                crossFadeImageView.setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
            } else {
                crossFadeImageView.setVisibility(4);
            }
            unifiedNativeAdView.setImageView(crossFadeImageView);
            if (!TextUtils.isEmpty(unifiedNativeAd.getBody()) && i == 31) {
                textView = (TextView) unifiedNativeAdView.findViewById(R.id.ad_body);
                textView.setText(unifiedNativeAd.getBody());
                unifiedNativeAdView.setBodyView(textView);
            } else if (i == 31) {
                unifiedNativeAdView.findViewById(R.id.ad_body).setVisibility(8);
            }
            if (TextUtils.isEmpty(unifiedNativeAd.getCallToAction())) {
                unifiedNativeAdView.findViewById(R.id.ad_cta).setVisibility(8);
            } else {
                textView = (TextView) unifiedNativeAdView.findViewById(R.id.ad_cta);
                textView.setText(unifiedNativeAd.getCallToAction());
                unifiedNativeAdView.setCallToActionView(textView);
            }
            if (z) {
                s.a().a((ImageView) unifiedNativeAdView.findViewById(R.id.ad_imgLightOverlay), z);
            }
            unifiedNativeAdView.setNativeAd(unifiedNativeAd);
        }
        return unifiedNativeAdView;
    }
}
