package com.google.ads.mediation.inmobi;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.inmobi.ads.InMobiNative;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

class InMobiAppInstallNativeAdMapper extends NativeAppInstallAdMapper {
    private String[] mImpressionTrackers;
    private final InMobiAdapter mInMobiAdapter;
    private final InMobiNative mInMobiNative;
    private final Boolean mIsOnlyURL;
    private final HashMap<String, String> mLandingUrlMap = new HashMap();
    private final MediationNativeListener mMediationNativeListener;

    public void recordImpression() {
    }

    public void trackView(View view) {
    }

    InMobiAppInstallNativeAdMapper(InMobiAdapter inMobiAdapter, InMobiNative inMobiNative, Boolean bool, MediationNativeListener mediationNativeListener) {
        this.mInMobiAdapter = inMobiAdapter;
        this.mInMobiNative = inMobiNative;
        this.mIsOnlyURL = bool;
        this.mMediationNativeListener = mediationNativeListener;
    }

    /* Access modifiers changed, original: 0000 */
    public void mapAppInstallAd(Context context) {
        try {
            if (this.mInMobiNative.getCustomAdContent() != null) {
                JSONObject customAdContent = this.mInMobiNative.getCustomAdContent();
                setHeadline((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdTitle(), "title"));
                setBody((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdDescription(), "description"));
                setCallToAction((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdCtaText(), InMobiNetworkValues.CTA));
                String str = (String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdLandingPageUrl(), InMobiNetworkValues.LANDING_URL);
                Bundle bundle = new Bundle();
                bundle.putString(InMobiNetworkValues.LANDING_URL, str);
                setExtras(bundle);
                this.mLandingUrlMap.put(InMobiNetworkValues.LANDING_URL, str);
                HashMap hashMap = new HashMap();
                URL url = new URL(this.mInMobiNative.getAdIconUrl());
                final Uri parse = Uri.parse(url.toURI().toString());
                final Double valueOf = Double.valueOf(1.0d);
                if (this.mIsOnlyURL.booleanValue()) {
                    setIcon(new InMobiNativeMappedImage(null, parse, valueOf.doubleValue()));
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new InMobiNativeMappedImage(new ColorDrawable(0), null, 1.0d));
                    setImages(arrayList);
                } else {
                    hashMap.put("icon_key", url);
                }
                try {
                    if (customAdContent.has("rating")) {
                        setStarRating(Double.parseDouble(customAdContent.getString("rating")));
                    }
                    if (customAdContent.has(InMobiNetworkValues.PACKAGE_NAME)) {
                        setStore("Google Play");
                    } else {
                        setStore("Others");
                    }
                    if (customAdContent.has(InMobiNetworkValues.PRICE)) {
                        setPrice(customAdContent.getString(InMobiNetworkValues.PRICE));
                    }
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                final RelativeLayout relativeLayout = new RelativeLayout(context);
                relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
                relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        relativeLayout.addView(InMobiAppInstallNativeAdMapper.this.mInMobiNative.getPrimaryViewOfWidth(null, relativeLayout, relativeLayout.getWidth()));
                        if (VERSION.SDK_INT >= 16) {
                            relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                    }
                });
                setMediaView(relativeLayout);
                setHasVideoContent(true);
                setOverrideClickHandling(false);
                if (this.mIsOnlyURL.booleanValue()) {
                    this.mMediationNativeListener.onAdLoaded(this.mInMobiAdapter, (NativeAdMapper) this);
                } else {
                    new ImageDownloaderAsyncTask(new DrawableDownloadListener() {
                        public void onDownloadSuccess(HashMap<String, Drawable> hashMap) {
                            Drawable drawable = (Drawable) hashMap.get("icon_key");
                            InMobiAppInstallNativeAdMapper.this.setIcon(new InMobiNativeMappedImage(drawable, parse, valueOf.doubleValue()));
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(new InMobiNativeMappedImage(new ColorDrawable(0), null, 1.0d));
                            InMobiAppInstallNativeAdMapper.this.setImages(arrayList);
                            if (drawable != null) {
                                InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdLoaded(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, InMobiAppInstallNativeAdMapper.this);
                            } else {
                                InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, 2);
                            }
                        }

                        public void onDownloadFailure() {
                            InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, 3);
                        }
                    }).execute(new Object[]{hashMap});
                }
                return;
            }
            this.mMediationNativeListener.onAdFailedToLoad(this.mInMobiAdapter, 3);
        } catch (MandatoryParamException | MalformedURLException | URISyntaxException e2) {
            ThrowableExtension.printStackTrace(e2);
            this.mMediationNativeListener.onAdFailedToLoad(this.mInMobiAdapter, 3);
        }
    }

    public void handleClick(View view) {
        this.mInMobiNative.reportAdClickAndOpenLandingPage();
    }

    public void untrackView(View view) {
        this.mInMobiNative.destroy();
    }
}
