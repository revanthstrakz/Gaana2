package com.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.ads.mediation.inmobi.InMobiNetworkKeys;
import com.utilities.Util;
import java.util.HashMap;
import java.util.Map;

public class WebViewsFragment extends BaseGaanaFragment implements a {
    private WebViewContent a;
    private String b = "";
    private WebView c;
    private ProgressBar d;
    private String e = "";
    private TextView f;

    public enum WebViewContent {
        PRIVACY_POLICY,
        TERMS_CONDITIONS,
        OUR_PARTNERS,
        ABOUT_TIL
    }

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.containerView = setContentView(R.layout.web_views, viewGroup);
        this.c = (WebView) this.containerView.findViewById(R.id.webView);
        this.a = WebViewContent.valueOf(getArguments().getString("WebViewContent"));
        this.f = (TextView) this.containerView.findViewById(R.id.tvCurrentViewTag);
        this.d = (ProgressBar) this.containerView.findViewById(R.id.progress_bar);
        switch (this.a) {
            case PRIVACY_POLICY:
                this.b = getString(R.string.privacy_policy);
                this.e = "https://api.gaana.com/index.php?type=privacy_policy&subtype=app ";
                break;
            case TERMS_CONDITIONS:
                this.b = getString(R.string.terms_and_conditions);
                this.e = "https://api.gaana.com/index.php?type=terms_conditions&subtype=app";
                break;
            case OUR_PARTNERS:
                this.b = getString(R.string.our_partners);
                this.e = "https://m.gaana.com/partner.html";
                break;
            case ABOUT_TIL:
                this.b = getString(R.string.about_company);
                this.e = "https://m.gaana.com/TIL.html";
                break;
        }
        this.f.setText(this.b.toLowerCase());
        this.f.setVisibility(8);
        this.c.loadUrl(this.e, a());
        this.d.setVisibility(0);
        this.c.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                WebViewsFragment.this.d.setVisibility(8);
            }
        });
        return this.containerView;
    }

    private Map<String, String> a() {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(Constants.ct)) {
            Constants.ct = "IN";
        }
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("appId", Constants.bt);
        hashMap.put(InMobiNetworkKeys.COUNTRY, Constants.ct);
        hashMap.put("gps_city", Constants.cC);
        hashMap.put("gps_state", Constants.cB);
        hashMap.put("gps_enable", Constants.cD);
        hashMap.put("deviceType", Constants.bU);
        hashMap.put("appVersion", "V7");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PHPSESSID=");
        stringBuilder.append(GaanaApplication.getCurrentSessionId());
        hashMap.put("Cookie", stringBuilder.toString());
        hashMap.put("deviceId", Util.l(GaanaApplication.getContext()));
        stringBuilder = new StringBuilder();
        stringBuilder.append("gaanaAndroid-");
        stringBuilder.append(Constants.cz);
        hashMap.put("gaanaAppVersion", stringBuilder.toString());
        return hashMap;
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).title = this.b.toLowerCase();
        setActionBar(this.containerView, new GenericBackActionBar(this.mContext, this.b.toLowerCase()));
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (!(this.containerView == null || this.containerView.getParent() == null)) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
    }
}
