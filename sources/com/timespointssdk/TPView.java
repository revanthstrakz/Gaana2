package com.timespointssdk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.util.CBConstant;
import com.timespointssdk.b.a;
import com.timespointssdk.c.b;
import com.timespointssdk.c.c;
import com.timespointssdk.c.d;
import org.json.JSONException;
import org.json.JSONObject;

public class TPView extends RelativeLayout {
    public static String d = "http://52.66.134.173:3000/tp/";
    public static String e = "http://test.ngage.timespoints.com/tpapi/v1/";
    String a = "PTWebView";
    WebView b;
    String c;
    View f;
    private Tracker g;

    public TPView(Context context) {
        super(context);
    }

    public TPView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public TPView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(final Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, d.TPView, 0, 0);
        try {
            this.c = obtainStyledAttributes.getString(d.TPView_viewType);
            this.f = inflate(context, c.tp_view, this);
            View findViewById = this.f.findViewById(b.iv_cross);
            this.b = (WebView) this.f.findViewById(b.webView);
            this.f.setVisibility(8);
            findViewById.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TPView.this.f.setVisibility(8);
                }
            });
            this.b.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    if (str.startsWith("mcs:")) {
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            intent.setData(Uri.parse(str.replaceFirst("mcs:", "http:")));
                            context.startActivity(intent);
                        }
                    } else {
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(context, e.getMessage(), 0).show();
                        }
                    }
                    return true;
                }

                public void onLoadResource(WebView webView, String str) {
                    if (a.a.booleanValue()) {
                        String str2 = TPView.this.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("url---->");
                        stringBuilder.append(str);
                        Log.e(str2, stringBuilder.toString());
                    }
                }
            });
            a(this.c);
            this.g = d.a();
            this.g.set("&uid", g.b("userid"));
            this.g.setScreenName(this.c);
            this.g.setAppName(g.b("pcode"));
            this.g.setClientId(g.b("userid"));
            this.g.setHostname(g.b("userid"));
            this.g.set("AppName1", g.b("pcode"));
            this.g.set("UserId1", g.b("userid"));
            this.g.send(((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().setCustomDimension(1, g.b("pcode"))).setCustomDimension(2, g.b("userid"))).set("AppName2", g.b("pcode"))).set("UserId2", g.b("userid"))).build());
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void a(JSONObject jSONObject) {
        String b = g.b(this.c);
        String str = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("viewType : ");
        stringBuilder.append(this.c);
        Log.e(str, stringBuilder.toString());
        str = this.a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("rawHtml : ");
        stringBuilder.append(b);
        Log.e(str, stringBuilder.toString());
        if (jSONObject.optString("status").equalsIgnoreCase("SUCCESS") && !b.equalsIgnoreCase("")) {
            String optString = jSONObject.optString(CBConstant.RESPONSE);
            a(this.f, b(b, optString));
            try {
                JSONObject jSONObject2 = new JSONObject(optString);
                optString = jSONObject2.getString("actionType");
                if (!optString.equalsIgnoreCase("No Action")) {
                    b = jSONObject2.getString("link");
                    this.b.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == 1) {
                                if (optString.equalsIgnoreCase("Profile")) {
                                    Intent intent = new Intent(TPView.this.getContext(), ProfileViewActivity.class);
                                    if (TPView.this.c.equalsIgnoreCase("nextNgage")) {
                                        String b = g.b("ticketID");
                                        if (!(b == null || b.isEmpty())) {
                                            intent.putExtra("ticketId", b);
                                        }
                                    }
                                    intent.putExtra("profileLink", b);
                                    TPView.this.getContext().startActivity(intent);
                                } else {
                                    TPView.this.b(b);
                                }
                                Tracker a = TPView.this.g;
                                EventBuilder category = new EventBuilder().setCategory("ViewClick");
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(TPView.this.c);
                                stringBuilder.append("~");
                                stringBuilder.append(b);
                                a.send(((EventBuilder) category.setAction(stringBuilder.toString()).set("UID", g.b("userid"))).build());
                            }
                            return false;
                        }
                    });
                }
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    private void a(java.lang.String r4) {
        /*
        r3 = this;
        r0 = "";
        r1 = r4.hashCode();
        r2 = -1216231928; // 0xffffffffb781c608 float:-1.5470214E-5 double:NaN;
        if (r1 == r2) goto L_0x0039;
    L_0x000b:
        r2 = 103149417; // 0x625ef69 float:3.1208942E-35 double:5.09625833E-316;
        if (r1 == r2) goto L_0x002f;
    L_0x0010:
        r2 = 709759981; // 0x2a4e13ed float:1.8303389E-13 double:3.506680234E-315;
        if (r1 == r2) goto L_0x0025;
    L_0x0015:
        r2 = 1201129843; // 0x4797c973 float:77714.9 double:5.934369916E-315;
        if (r1 == r2) goto L_0x001b;
    L_0x001a:
        goto L_0x0043;
    L_0x001b:
        r1 = "nextNgage";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0043;
    L_0x0023:
        r4 = 3;
        goto L_0x0044;
    L_0x0025:
        r1 = "txnPoints";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0043;
    L_0x002d:
        r4 = 1;
        goto L_0x0044;
    L_0x002f:
        r1 = "login";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0043;
    L_0x0037:
        r4 = 0;
        goto L_0x0044;
    L_0x0039:
        r1 = "intervalPoints";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0043;
    L_0x0041:
        r4 = 2;
        goto L_0x0044;
    L_0x0043:
        r4 = -1;
    L_0x0044:
        switch(r4) {
            case 0: goto L_0x0073;
            case 1: goto L_0x00a6;
            case 2: goto L_0x00a6;
            case 3: goto L_0x0048;
            default: goto L_0x0047;
        };
    L_0x0047:
        goto L_0x00a6;
    L_0x0048:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = "https://tpapi.timespoints.com/nextEngagement?uid=";
        r4.append(r0);
        r0 = "userid";
        r0 = com.timespointssdk.g.b(r0);
        r4.append(r0);
        r0 = "&pname=";
        r4.append(r0);
        r0 = "pcode";
        r0 = com.timespointssdk.g.b(r0);
        r4.append(r0);
        r0 = "&pfm=Android";
        r4.append(r0);
        r0 = r4.toString();
        goto L_0x00a6;
    L_0x0073:
        r4 = "userid";
        r4 = com.timespointssdk.g.b(r4);
        r1 = "";
        r4 = r4.equalsIgnoreCase(r1);
        if (r4 == 0) goto L_0x00a6;
    L_0x0081:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = "https://tpapi.timespoints.com/pl/points?deviceId=";
        r4.append(r0);
        r0 = "deviceid";
        r0 = com.timespointssdk.g.b(r0);
        r4.append(r0);
        r0 = "&pcode=";
        r4.append(r0);
        r0 = "pcode";
        r0 = com.timespointssdk.g.b(r0);
        r4.append(r0);
        r0 = r4.toString();
    L_0x00a6:
        r4 = "";
        r4 = r0.equalsIgnoreCase(r4);
        if (r4 != 0) goto L_0x00b3;
    L_0x00ae:
        r4 = "";
        r3.a(r0, r4);
    L_0x00b3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.timespointssdk.TPView.a(java.lang.String):void");
    }

    public void setTransactionID(String str) {
        String str2 = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setTransactionID : ");
        stringBuilder.append(str);
        Log.e(str2, stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://tpapi.timespoints.com/v1/txn/status?uid=");
        stringBuilder2.append(g.b("userid"));
        stringBuilder2.append("&pcode=");
        stringBuilder2.append(g.b("pcode"));
        stringBuilder2.append("&txnId=");
        stringBuilder2.append(str);
        a(stringBuilder2.toString(), "");
    }

    public void setStartAndEndTime(String str, String str2) {
        String str3 = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setStartAndEndTime : ");
        stringBuilder.append(str);
        stringBuilder.append(" : ");
        stringBuilder.append(str2);
        Log.e(str3, stringBuilder.toString());
        if (str.equalsIgnoreCase("")) {
            str = g.k();
        }
        str3 = this.a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("setStartAndEndTime : ");
        stringBuilder.append(str);
        stringBuilder.append(" : ");
        stringBuilder.append(str2);
        Log.e(str3, stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://tpapi.timespoints.com/v1/user/points?uid=");
        stringBuilder2.append(g.b("userid"));
        stringBuilder2.append("&deviceId=");
        stringBuilder2.append(g.b("deviceid"));
        stringBuilder2.append("&pcode=");
        stringBuilder2.append(g.b("pcode"));
        stringBuilder2.append("&startTime=");
        stringBuilder2.append(str);
        stringBuilder2.append("&endTime=");
        stringBuilder2.append(str2);
        a(stringBuilder2.toString(), str2);
    }

    private void a(String str, final String str2) {
        String str3 = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("urlString : ");
        stringBuilder.append(str);
        Log.e(str3, stringBuilder.toString());
        a aVar = new a(new a.a() {
            public void a(String str) {
                if (str != null) {
                    try {
                        if (!str.equals("")) {
                            TPView.this.a(new JSONObject(str));
                            if (!str2.equalsIgnoreCase("")) {
                                g.b("starttimeforintervalapi", str2);
                                return;
                            }
                            return;
                        }
                    } catch (JSONException e) {
                        ThrowableExtension.printStackTrace(e);
                        return;
                    }
                }
                Log.e(TPView.this.a, "Response from Server is NULL");
            }
        });
        if (VERSION.SDK_INT >= 11) {
            if (a.a.booleanValue()) {
                Log.e(this.a, "call to executor");
            }
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
            return;
        }
        aVar.execute(new String[]{str});
    }

    private void b(String str) {
        String str2 = this.a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("openLink : ");
        stringBuilder.append(str);
        Log.e(str2, stringBuilder.toString());
        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0037 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing block: B:8:?, code skipped:
            r3 = r7.replace(r4, "");
     */
    private java.lang.String b(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0041 }
        r0.<init>(r8);	 Catch:{ JSONException -> 0x0041 }
        r8 = "tParams";
        r8 = com.timespointssdk.g.b(r8);	 Catch:{ JSONException -> 0x0041 }
        r1 = ",";
        r8 = r8.split(r1);	 Catch:{ JSONException -> 0x0041 }
        r1 = r8.length;	 Catch:{ JSONException -> 0x0041 }
        r2 = 0;
    L_0x0013:
        if (r2 >= r1) goto L_0x0045;
    L_0x0015:
        r3 = r8[r2];	 Catch:{ JSONException -> 0x0041 }
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0041 }
        r4.<init>();	 Catch:{ JSONException -> 0x0041 }
        r5 = "$";
        r4.append(r5);	 Catch:{ JSONException -> 0x0041 }
        r4.append(r3);	 Catch:{ JSONException -> 0x0041 }
        r5 = "$";
        r4.append(r5);	 Catch:{ JSONException -> 0x0041 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x0041 }
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0037 }
        r3 = r7.replace(r4, r3);	 Catch:{ JSONException -> 0x0037 }
    L_0x0035:
        r7 = r3;
        goto L_0x003e;
    L_0x0037:
        r3 = "";
        r3 = r7.replace(r4, r3);	 Catch:{ JSONException -> 0x0041 }
        goto L_0x0035;
    L_0x003e:
        r2 = r2 + 1;
        goto L_0x0013;
    L_0x0041:
        r8 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r8);
    L_0x0045:
        r8 = r6.a;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Final rawHtmlString ====>";
        r0.append(r1);
        r0.append(r7);
        r0 = r0.toString();
        android.util.Log.e(r8, r0);
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.timespointssdk.TPView.b(java.lang.String, java.lang.String):java.lang.String");
    }

    private void a(View view, String str) {
        this.b.loadDataWithBaseURL("", str, "text/html", "UTF-8", "");
        view.setVisibility(0);
    }
}
