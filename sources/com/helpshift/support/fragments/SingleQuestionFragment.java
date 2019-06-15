package com.helpshift.support.fragments;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.ContactUsFilter;
import com.helpshift.support.ContactUsFilter.LOCATION;
import com.helpshift.support.Faq;
import com.helpshift.support.d;
import com.helpshift.support.d.c;
import com.helpshift.support.util.g;
import com.helpshift.support.webkit.CustomWebView;
import com.helpshift.util.l;
import com.helpshift.util.n;
import com.helpshift.util.o;
import com.helpshift.util.v;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class SingleQuestionFragment extends MainFragment implements OnClickListener, com.helpshift.support.webkit.b.a {
    boolean a;
    private int b = 1;
    private d d;
    private CustomWebView e;
    private View f;
    private TextView g;
    private Button h;
    private Button i;
    private Button j;
    private Faq k;
    private String l;
    private String m;
    private boolean n;
    private View o;
    private c p;
    private boolean q;
    private int r = 0;
    private boolean s = false;

    private static class a extends Handler {
        private WeakReference<SingleQuestionFragment> a;

        public a(SingleQuestionFragment singleQuestionFragment) {
            this.a = new WeakReference(singleQuestionFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            SingleQuestionFragment singleQuestionFragment = (SingleQuestionFragment) this.a.get();
            if (singleQuestionFragment != null && !singleQuestionFragment.isDetached()) {
                g.a(102, singleQuestionFragment.getView());
            }
        }
    }

    private static class b extends Handler {
        private WeakReference<SingleQuestionFragment> a;

        public b(SingleQuestionFragment singleQuestionFragment) {
            this.a = new WeakReference(singleQuestionFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            SingleQuestionFragment singleQuestionFragment = (SingleQuestionFragment) this.a.get();
            if (singleQuestionFragment != null) {
                Faq faq = (Faq) message.obj;
                if (faq != null) {
                    singleQuestionFragment.a(faq);
                    String a = faq.a();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("FAQ question loaded : ");
                    stringBuilder.append(faq.b);
                    l.a("Helpshift_SingleQstn", stringBuilder.toString());
                    if (!(singleQuestionFragment.a || TextUtils.isEmpty(a))) {
                        singleQuestionFragment.f();
                    }
                }
            }
        }
    }

    public boolean b() {
        return true;
    }

    public static SingleQuestionFragment a(Bundle bundle, int i, boolean z) {
        SingleQuestionFragment singleQuestionFragment = new SingleQuestionFragment();
        singleQuestionFragment.setArguments(bundle);
        singleQuestionFragment.b = i;
        singleQuestionFragment.s = z;
        return singleQuestionFragment;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.d = new d(context);
        a(context);
        SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
        if (a != null) {
            this.p = a.c();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName());
        stringBuilder.append(this.b);
        this.c = stringBuilder.toString();
    }

    private void a(Context context) {
        int i = VERSION.SDK_INT >= 21 ? 16843829 : 16842907;
        this.l = v.b(context, 16842806);
        this.m = v.b(context, i);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = h.hs__single_question_fragment;
        if (this.s) {
            i = h.hs__single_question_layout_with_cardview;
        }
        return layoutInflater.inflate(i, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.e = (CustomWebView) view.findViewById(f.web_view);
        this.e.setWebViewClient(new com.helpshift.support.webkit.b(getContext(), this));
        this.e.setWebChromeClient(new com.helpshift.support.webkit.a(getActivity().getWindow().getDecorView(), view.findViewById(f.faq_content_view)));
        this.h = (Button) view.findViewById(f.helpful_button);
        this.h.setOnClickListener(this);
        this.i = (Button) view.findViewById(f.unhelpful_button);
        this.i.setOnClickListener(this);
        this.f = view.findViewById(f.question_footer);
        this.g = (TextView) view.findViewById(f.question_footer_message);
        this.j = (Button) view.findViewById(f.contact_us_button);
        this.j.setOnClickListener(this);
        if (VERSION.SDK_INT >= 24) {
            this.h.setText(k.hs__mark_yes);
            this.i.setText(k.hs__mark_no);
            this.j.setText(k.hs__contact_us_btn);
        }
        if (this.b == 2) {
            this.j.setText(getResources().getString(k.hs__send_anyway));
        }
        this.d.a(getArguments().getString("questionPublishId"), new b(this), new a(this), getArguments().getInt("support_mode") == 3);
        this.o = view.findViewById(f.progress_bar);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.q = bundle.getBoolean("decomp", false);
        }
    }

    public void onPause() {
        super.onPause();
        this.e.onPause();
    }

    public void onResume() {
        super.onResume();
        if (m()) {
            Fragment parentFragment = getParentFragment();
            if (parentFragment instanceof FaqFlowFragment) {
                ((FaqFlowFragment) parentFragment).a(false);
            }
        }
        this.e.onResume();
        if (this.q || !m()) {
            b(getString(k.hs__question_header));
        }
        if (this.k != null && !TextUtils.isEmpty(this.k.a()) && !this.a) {
            f();
        }
    }

    public void onStart() {
        super.onStart();
        if (!l()) {
            this.a = false;
        }
    }

    public void onDestroyView() {
        g.a(getView());
        super.onDestroyView();
    }

    public void onStop() {
        super.onStop();
        if (this.q || !m()) {
            b(getString(k.hs__help_header));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Faq faq) {
        this.k = faq;
        this.e.loadDataWithBaseURL(null, b(faq), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
    }

    private String b(Faq faq) {
        StringBuilder stringBuilder;
        String str = "24px";
        String str2 = "32px";
        String str3 = "16px";
        String str4 = "1.5";
        String b = com.helpshift.views.a.b();
        String str5 = "";
        String str6 = "";
        if (!TextUtils.isEmpty(b)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("file:///android_asset/");
            stringBuilder2.append(b);
            b = stringBuilder2.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("@font-face {    font-family: custom;    src: url('");
            stringBuilder2.append(b);
            stringBuilder2.append("');");
            stringBuilder2.append("}");
            str5 = stringBuilder2.toString();
            str6 = "font-family: custom, sans-serif;";
        }
        b = faq.e;
        String str7 = faq.b;
        if (b.contains("<iframe")) {
            try {
                b = b.replace("https", "http");
            } catch (NullPointerException e) {
                l.c("Helpshift_SingleQstn", "Error replacing https in bodyText", e);
            }
        }
        if (faq.g.booleanValue()) {
            stringBuilder = new StringBuilder("<html dir=\"rtl\">");
        } else {
            stringBuilder = new StringBuilder("<html>");
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(16);
        stringBuilder3.append("px ");
        stringBuilder3.append(16);
        stringBuilder3.append("px ");
        stringBuilder3.append(96);
        stringBuilder3.append("px ");
        stringBuilder3.append(16);
        stringBuilder3.append("px;");
        String stringBuilder4 = stringBuilder3.toString();
        stringBuilder.append("<head>");
        stringBuilder.append("    <style type='text/css'>");
        stringBuilder.append(str5);
        stringBuilder.append("        img,");
        stringBuilder.append("        object,");
        stringBuilder.append("        embed {");
        stringBuilder.append("            max-width: 100%;");
        stringBuilder.append("        }");
        stringBuilder.append("        a,");
        stringBuilder.append("        a:visited,");
        stringBuilder.append("        a:active,");
        stringBuilder.append("        a:hover {");
        stringBuilder.append("            color: ");
        stringBuilder.append(this.m);
        stringBuilder.append(";");
        stringBuilder.append("        }");
        stringBuilder.append("        body {");
        stringBuilder.append("            background-color: transparent;");
        stringBuilder.append("            margin: 0;");
        stringBuilder.append("            padding: ");
        stringBuilder.append(stringBuilder4);
        stringBuilder.append("            font-size: ");
        stringBuilder.append(str3);
        stringBuilder.append(";");
        stringBuilder.append(str6);
        stringBuilder.append("            line-height: ");
        stringBuilder.append(str4);
        stringBuilder.append(";");
        stringBuilder.append("            white-space: normal;");
        stringBuilder.append("            word-wrap: break-word;");
        stringBuilder.append("            color: ");
        stringBuilder.append(this.l);
        stringBuilder.append(";");
        stringBuilder.append("        }");
        stringBuilder.append("        .title {");
        stringBuilder.append("            display: block;");
        stringBuilder.append("            margin: 0;");
        stringBuilder.append("            padding: 0 0 ");
        stringBuilder.append(16);
        stringBuilder.append(" 0;");
        stringBuilder.append("            font-size: ");
        stringBuilder.append(str);
        stringBuilder.append(";");
        stringBuilder.append(str6);
        stringBuilder.append("            line-height: ");
        stringBuilder.append(str2);
        stringBuilder.append(";");
        stringBuilder.append("        }");
        stringBuilder.append("        h1, h2, h3 { ");
        stringBuilder.append("            line-height: 1.4; ");
        stringBuilder.append("        }");
        stringBuilder.append("    </style>");
        stringBuilder.append("    <script language='javascript'>");
        stringBuilder.append("     window.onload = function () {");
        stringBuilder.append("        var w = window,");
        stringBuilder.append("            d = document,");
        stringBuilder.append("            e = d.documentElement,");
        stringBuilder.append("            g = d.getElementsByTagName('body')[0],");
        stringBuilder.append("            sWidth = Math.min (w.innerWidth || Infinity, e.clientWidth || Infinity, g.clientWidth || Infinity),");
        stringBuilder.append("            sHeight = Math.min (w.innerHeight || Infinity, e.clientHeight || Infinity, g.clientHeight || Infinity);");
        stringBuilder.append("        var frame, fw, fh;");
        stringBuilder.append("        var iframes = document.getElementsByTagName('iframe');");
        stringBuilder.append("        var padding = ");
        stringBuilder.append(32);
        stringBuilder.append(";");
        stringBuilder.append("        for (var i=0; i < iframes.length; i++) {");
        stringBuilder.append("            frame = iframes[i];");
        stringBuilder.append("            fw = frame.offsetWidth;");
        stringBuilder.append("            fh = frame.offsetHeight;");
        stringBuilder.append("            if (fw >= fh && fw > (sWidth - padding)) {");
        stringBuilder.append("                frame.style.width = sWidth - padding;");
        stringBuilder.append("                frame.style.height = ((sWidth - padding) * fh/fw).toString();");
        stringBuilder.append("            }");
        stringBuilder.append("        }");
        stringBuilder.append("        document.addEventListener('click', function (event) {");
        stringBuilder.append("            if (event.target instanceof HTMLImageElement) {");
        stringBuilder.append("                event.preventDefault();");
        stringBuilder.append("                event.stopPropagation();");
        stringBuilder.append("            }");
        stringBuilder.append("        }, false);");
        stringBuilder.append("    };");
        stringBuilder.append("    </script>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("    <strong class='title'> ");
        stringBuilder.append(str7);
        stringBuilder.append(" </strong> ");
        stringBuilder.append(b);
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    private void a(boolean z) {
        if (this.k != null) {
            String a = this.k.a();
            this.d.a(a, z);
            o.d().r().a(a, z);
        }
    }

    public void onClick(View view) {
        SupportFragment a;
        if (view.getId() == f.helpful_button) {
            a(true);
            a(1);
            if (this.b == 2) {
                a = com.helpshift.support.util.c.a((Fragment) this);
                if (a != null) {
                    a.c().h();
                }
            }
        } else if (view.getId() == f.unhelpful_button) {
            a(false);
            a(-1);
        } else if (view.getId() == f.contact_us_button && this.p != null) {
            if (this.b == 1) {
                com.helpshift.support.c.c a2 = a();
                if (a2 != null) {
                    a2.a(null);
                    return;
                }
                return;
            }
            a = com.helpshift.support.util.c.a((Fragment) this);
            if (a != null) {
                a.c().c();
            }
        }
    }

    public com.helpshift.support.c.c a() {
        com.helpshift.support.c.b bVar = (com.helpshift.support.c.b) getParentFragment();
        return bVar != null ? bVar.a() : null;
    }

    private void a(int i) {
        if (i != 0) {
            this.r = i;
        }
        h();
    }

    public void c() {
        b(true);
        this.e.setBackgroundColor(0);
    }

    public void d() {
        if (isVisible()) {
            b(false);
            a(this.k.f);
            if (this.n) {
                this.n = false;
            } else {
                g();
            }
            this.e.setBackgroundColor(0);
        }
    }

    private void g() {
        this.n = true;
        Faq a = com.helpshift.support.util.h.a(getContext(), this.k, getArguments().getStringArrayList("searchTerms"));
        if (a != null) {
            a(a);
        }
    }

    private void b(boolean z) {
        if (this.o == null) {
            return;
        }
        if (z) {
            this.o.setVisibility(0);
        } else {
            this.o.setVisibility(8);
        }
    }

    private void h() {
        switch (this.r) {
            case -1:
                n();
                return;
            case 0:
                i();
                return;
            case 1:
                j();
                return;
            default:
                return;
        }
    }

    private void i() {
        this.f.setVisibility(0);
        this.g.setText(getResources().getString(k.hs__mark_yes_no_question));
        this.j.setVisibility(8);
        this.h.setVisibility(0);
        this.i.setVisibility(0);
    }

    private void j() {
        this.f.setVisibility(0);
        this.g.setText(getResources().getString(k.hs__question_helpful_message));
        this.g.setGravity(17);
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.i.setVisibility(8);
    }

    private void n() {
        this.f.setVisibility(0);
        this.g.setText(getResources().getString(k.hs__question_unhelpful_message));
        o();
        this.h.setVisibility(8);
        this.i.setVisibility(8);
    }

    private void o() {
        if (ContactUsFilter.a(LOCATION.QUESTION_FOOTER)) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(8);
        }
    }

    public String e() {
        return this.k != null ? this.k.a() : "";
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        Map hashMap = new HashMap();
        hashMap.put("id", this.k.a());
        hashMap.put("nt", Boolean.valueOf(n.a(getContext())));
        o.d().f().a(AnalyticsEventType.READ_FAQ, hashMap);
        this.a = true;
    }
}
