package io.branch.referral;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.api.client.http.HttpMethods;
import io.branch.referral.Defines.Jsonkey;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.json.JSONObject;

public class i {
    private static i a;
    private boolean b;
    private boolean c;
    private a d = null;
    private boolean e = false;
    private String f;
    private boolean g;
    private Dialog h;

    public interface b {
        void a(int i, String str, String str2);

        void b(String str, String str2);

        void c(String str, String str2);

        void d(String str, String str2);
    }

    private class a {
        private String b;
        private String c;
        private int d;
        private String e;
        private String f;

        /* synthetic */ a(i iVar, JSONObject jSONObject, String str, AnonymousClass1 anonymousClass1) {
            this(jSONObject, str);
        }

        private a(JSONObject jSONObject, String str) {
            this.b = "";
            this.c = "";
            this.d = 1;
            this.e = "";
            this.f = "";
            try {
                this.c = str;
                if (jSONObject.has(Jsonkey.BranchViewID.getKey())) {
                    this.b = jSONObject.getString(Jsonkey.BranchViewID.getKey());
                }
                if (jSONObject.has(Jsonkey.BranchViewNumOfUse.getKey())) {
                    this.d = jSONObject.getInt(Jsonkey.BranchViewNumOfUse.getKey());
                }
                if (jSONObject.has(Jsonkey.BranchViewUrl.getKey())) {
                    this.e = jSONObject.getString(Jsonkey.BranchViewUrl.getKey());
                }
                if (jSONObject.has(Jsonkey.BranchViewHtml.getKey())) {
                    this.f = jSONObject.getString(Jsonkey.BranchViewHtml.getKey());
                }
            } catch (Exception unused) {
            }
        }

        private boolean a(Context context) {
            return this.d > m.a(context).w(this.b) || this.d == -1;
        }

        public void a(Context context, String str) {
            m.a(context).v(str);
        }
    }

    private class c extends AsyncTask<Void, Void, Boolean> {
        private final a b;
        private final Context c;
        private final b d;

        public c(a aVar, Context context, b bVar) {
            this.b = aVar;
            this.c = context;
            this.d = bVar;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            int responseCode;
            boolean z = false;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b.e).openConnection();
                httpURLConnection.setRequestMethod(HttpMethods.GET);
                httpURLConnection.connect();
                responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        this.b.f = byteArrayOutputStream.toString("UTF-8");
                        byteArrayOutputStream.close();
                        inputStream.close();
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception unused2) {
                responseCode = -1;
            }
            if (responseCode == 200) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                i.this.b(this.b, this.c, this.d);
            } else if (this.d != null) {
                this.d.a(-202, "Unable to create a Branch view due to a temporary network error", this.b.c);
            }
            i.this.e = false;
        }
    }

    private i() {
    }

    public static i a() {
        if (a == null) {
            a = new i();
        }
        return a;
    }

    public boolean a(Context context) {
        boolean a = a(this.d, context, null);
        if (a) {
            this.d = null;
        }
        return a;
    }

    public boolean a(JSONObject jSONObject, String str, Context context, b bVar) {
        return a(new a(this, jSONObject, str, null), context, bVar);
    }

    private boolean a(a aVar, Context context, b bVar) {
        if (this.b || this.e) {
            if (bVar != null) {
                bVar.a(-200, "Unable to create a Branch view. A Branch view is already showing", aVar.c);
            }
            return false;
        }
        this.b = false;
        this.c = false;
        if (!(context == null || aVar == null)) {
            if (aVar.a(context)) {
                if (TextUtils.isEmpty(aVar.f)) {
                    this.e = true;
                    new c(aVar, context, bVar).execute(new Void[0]);
                } else {
                    b(aVar, context, bVar);
                }
                return true;
            } else if (bVar != null) {
                bVar.a(-203, "Unable to create this Branch view. Reached maximum usage limit ", aVar.c);
            }
        }
        return false;
    }

    private void b(final a aVar, Context context, final b bVar) {
        if (!(context == null || aVar == null)) {
            final WebView webView = new WebView(context);
            webView.getSettings().setJavaScriptEnabled(true);
            if (VERSION.SDK_INT >= 19) {
                webView.setLayerType(2, null);
            }
            this.g = false;
            if (!TextUtils.isEmpty(aVar.f)) {
                webView.loadDataWithBaseURL(null, aVar.f, "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        boolean a = i.this.a(str);
                        if (!a) {
                            webView.loadUrl(str);
                        } else if (i.this.h != null) {
                            i.this.h.dismiss();
                        }
                        return a;
                    }

                    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                        super.onPageStarted(webView, str, bitmap);
                    }

                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        super.onReceivedError(webView, i, str, str2);
                        i.this.g = true;
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        i.this.a(aVar, bVar, webView);
                    }
                });
            }
        }
    }

    private void a(final a aVar, final b bVar, WebView webView) {
        if (this.g || Branch.a() == null || Branch.a().b == null) {
            this.b = false;
            if (bVar != null) {
                bVar.a(-202, "Unable to create a Branch view due to a temporary network error", aVar.c);
            }
        } else {
            Activity activity = (Activity) Branch.a().b.get();
            if (activity != null) {
                aVar.a(activity.getApplicationContext(), aVar.b);
                this.f = activity.getClass().getName();
                View relativeLayout = new RelativeLayout(activity);
                relativeLayout.setVisibility(8);
                relativeLayout.addView(webView, new LayoutParams(-1, -1));
                relativeLayout.setBackgroundColor(0);
                if (this.h == null || !this.h.isShowing()) {
                    this.h = new Dialog(activity, 16973834);
                    this.h.setContentView(relativeLayout);
                    relativeLayout.setVisibility(0);
                    webView.setVisibility(0);
                    this.h.show();
                    a(relativeLayout);
                    a((View) webView);
                    this.b = true;
                    if (bVar != null) {
                        bVar.b(aVar.c, aVar.b);
                    }
                    this.h.setOnDismissListener(new OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            i.this.b = false;
                            i.this.h = null;
                            if (bVar == null) {
                                return;
                            }
                            if (i.this.c) {
                                bVar.c(aVar.c, aVar.b);
                            } else {
                                bVar.d(aVar.c, aVar.b);
                            }
                        }
                    });
                } else {
                    if (bVar != null) {
                        bVar.a(-200, "Unable to create a Branch view. A Branch view is already showing", aVar.c);
                    }
                }
            }
        }
    }

    private boolean a(String str) {
        try {
            URI uri = new URI(str);
            if (!uri.getScheme().equalsIgnoreCase("branch-cta")) {
                return false;
            }
            if (uri.getHost().equalsIgnoreCase("accept")) {
                this.c = true;
            } else if (!uri.getHost().equalsIgnoreCase("cancel")) {
                return false;
            } else {
                this.c = false;
            }
            return true;
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    private void a(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(10);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setFillAfter(true);
        view.setVisibility(0);
        view.startAnimation(alphaAnimation);
    }

    public boolean a(JSONObject jSONObject, String str) {
        a aVar = new a(this, jSONObject, str, null);
        if (!(aVar == null || Branch.a().b == null)) {
            Context context = (Activity) Branch.a().b.get();
            if (context != null && aVar.a(context)) {
                this.d = new a(this, jSONObject, str, null);
                return true;
            }
        }
        return false;
    }

    public boolean b(Context context) {
        return this.d != null && this.d.a(context);
    }

    public void a(Activity activity) {
        if (this.f != null && this.f.equalsIgnoreCase(activity.getClass().getName())) {
            this.b = false;
        }
    }
}
