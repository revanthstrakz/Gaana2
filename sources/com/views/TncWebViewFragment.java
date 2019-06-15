package com.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.WebViewsFragment.WebViewContent;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.ads.mediation.inmobi.InMobiNetworkKeys;
import com.utilities.Util;
import java.util.HashMap;
import java.util.Map;

public class TncWebViewFragment extends Fragment {
    protected LayoutInflater a;
    private WebViewContent b;
    private String c = "";
    private WebView d;
    private ProgressBar e;
    private String f = "";
    private TextView g;
    private View h;
    private Context i;

    /* Access modifiers changed, original: protected */
    public View a(int i, View view) {
        this.a = LayoutInflater.from(getActivity());
        return this.a.inflate(i, (LinearLayout) view.findViewById(R.id.llParentActivityLayout), false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.a = LayoutInflater.from(getActivity());
        this.i = getActivity();
        this.h = a(R.layout.tnc_web_view, viewGroup);
        this.d = (WebView) this.h.findViewById(R.id.webView);
        this.b = WebViewContent.valueOf(getArguments().getString("WebViewContent"));
        this.g = (TextView) this.h.findViewById(R.id.tvCurrentViewTag);
        this.e = (ProgressBar) this.h.findViewById(R.id.progress_bar);
        switch (this.b) {
            case PRIVACY_POLICY:
                this.c = getString(R.string.privacy_policy);
                this.f = "https://api.gaana.com/index.php?type=privacy_policy&subtype=app ";
                break;
            case TERMS_CONDITIONS:
                this.c = getString(R.string.terms_and_conditions);
                this.f = "https://api.gaana.com/index.php?type=terms_conditions&subtype=app";
                break;
            case OUR_PARTNERS:
                this.c = getString(R.string.our_partners);
                this.f = "https://m.gaana.com/partner.html";
                break;
            case ABOUT_TIL:
                this.c = getString(R.string.about_company);
                this.f = "https://m.gaana.com/TIL.html";
                break;
        }
        this.g.setText(this.c.toLowerCase());
        this.g.setVisibility(8);
        this.d.loadUrl(this.f, a());
        this.e.setVisibility(0);
        this.d.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                TncWebViewFragment.this.e.setVisibility(8);
            }
        });
        return this.h;
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
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (!(this.h == null || this.h.getParent() == null)) {
            ((ViewGroup) this.h.getParent()).removeView(this.h);
        }
        super.onDestroyView();
    }
}
