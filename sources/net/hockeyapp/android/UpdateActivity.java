package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.comscore.utils.Constants;
import com.facebook.ads.AudienceNetworkActivity;
import java.util.Locale;
import net.hockeyapp.android.c.d;
import net.hockeyapp.android.c.e;
import net.hockeyapp.android.d.a;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.d.k;
import net.hockeyapp.android.i.b;
import net.hockeyapp.android.i.c;
import net.hockeyapp.android.objects.ErrorObject;

public class UpdateActivity extends Activity implements OnClickListener, j {
    protected d a;
    protected k b;
    private ErrorObject c;
    private Context d;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("App Update");
        setContentView(a());
        this.d = this;
        this.b = new k(this, getIntent().getStringExtra("json"), this);
        b();
        this.a = (d) getLastNonConfigurationInstance();
        if (this.a != null) {
            this.a.a((Context) this);
        }
    }

    public Object onRetainNonConfigurationInstance() {
        if (this.a != null) {
            this.a.a();
        }
        return this.a;
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i) {
        return onCreateDialog(i, null);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != 0) {
            return null;
        }
        return new Builder(this).setMessage("An error has occured").setCancelable(false).setTitle("Error").setIcon(17301543).setPositiveButton(Constants.RESPONSE_MASK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UpdateActivity.this.c = null;
                dialogInterface.cancel();
            }
        }).create();
    }

    /* Access modifiers changed, original: protected */
    public void onPrepareDialog(int i, Dialog dialog) {
        if (i == 0) {
            AlertDialog alertDialog = (AlertDialog) dialog;
            if (this.c != null) {
                alertDialog.setMessage(this.c.a());
            } else {
                alertDialog.setMessage("An unknown error has occured.");
            }
        }
    }

    /* JADX WARNING: Missing block: B:14:0x0062, code skipped:
            return;
     */
    public void onRequestPermissionsResult(int r1, java.lang.String[] r2, int[] r3) {
        /*
        r0 = this;
        r0.e();
        r2 = r2.length;
        if (r2 == 0) goto L_0x0062;
    L_0x0006:
        r2 = r3.length;
        if (r2 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0062;
    L_0x000a:
        r2 = 1;
        if (r1 != r2) goto L_0x0061;
    L_0x000d:
        r1 = 0;
        r1 = r3[r1];
        if (r1 != 0) goto L_0x0016;
    L_0x0012:
        r0.g();
        goto L_0x0061;
    L_0x0016:
        r1 = "User denied write permission, can't continue with updater task.";
        net.hockeyapp.android.d.d.b(r1);
        r1 = net.hockeyapp.android.k.a();
        if (r1 == 0) goto L_0x0025;
    L_0x0021:
        r1.c();
        goto L_0x0061;
    L_0x0025:
        r1 = new android.app.AlertDialog$Builder;
        r2 = r0.d;
        r1.<init>(r2);
        r2 = net.hockeyapp.android.i.d.hockeyapp_permission_update_title;
        r2 = r0.getString(r2);
        r1 = r1.setTitle(r2);
        r2 = net.hockeyapp.android.i.d.hockeyapp_permission_update_message;
        r2 = r0.getString(r2);
        r1 = r1.setMessage(r2);
        r2 = net.hockeyapp.android.i.d.hockeyapp_permission_dialog_negative_button;
        r2 = r0.getString(r2);
        r3 = 0;
        r1 = r1.setNegativeButton(r2, r3);
        r2 = net.hockeyapp.android.i.d.hockeyapp_permission_dialog_positive_button;
        r2 = r0.getString(r2);
        r3 = new net.hockeyapp.android.UpdateActivity$2;
        r3.<init>(r0);
        r1 = r1.setPositiveButton(r2, r3);
        r1 = r1.create();
        r1.show();
    L_0x0061:
        return;
    L_0x0062:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.UpdateActivity.onRequestPermissionsResult(int, java.lang.String[], int[]):void");
    }

    public int getCurrentVersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 128).versionCode;
        } catch (NameNotFoundException unused) {
            return -1;
        }
    }

    @SuppressLint({"InflateParams"})
    public View a() {
        return getLayoutInflater().inflate(c.hockeyapp_activity_update, null);
    }

    public void onClick(View view) {
        g();
        view.setEnabled(false);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        ((TextView) findViewById(b.label_title)).setText(f());
        final TextView textView = (TextView) findViewById(b.label_version);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Version ");
        stringBuilder.append(this.b.a());
        final String stringBuilder2 = stringBuilder.toString();
        final String b = this.b.b();
        String str = "Unknown size";
        if (this.b.c() >= 0) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) r4) / 1048576.0f)}));
            stringBuilder3.append(" MB");
            str = stringBuilder3.toString();
        } else {
            a.a(new e(this, getIntent().getStringExtra("url"), new net.hockeyapp.android.b.a() {
                public void a(d dVar) {
                    if (dVar instanceof e) {
                        long c = ((e) dVar).c();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) c) / 1048576.0f)}));
                        stringBuilder.append(" MB");
                        String stringBuilder2 = stringBuilder.toString();
                        textView.setText(UpdateActivity.this.getString(i.d.hockeyapp_update_version_details_label, new Object[]{stringBuilder2, b, stringBuilder2}));
                    }
                }
            }));
        }
        textView.setText(getString(i.d.hockeyapp_update_version_details_label, new Object[]{stringBuilder2, b, str}));
        ((Button) findViewById(b.button_update)).setOnClickListener(this);
        WebView webView = (WebView) findViewById(b.web_update_details);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL("https://sdk.hockeyapp.net/", c(), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
    }

    /* Access modifiers changed, original: protected */
    public String c() {
        return this.b.a(false);
    }

    /* Access modifiers changed, original: protected */
    public void d() {
        a(getIntent().getStringExtra("url"));
    }

    /* Access modifiers changed, original: protected */
    public void a(String str) {
        a(str, new net.hockeyapp.android.b.a() {
            public void a(d dVar, Boolean bool) {
                if (bool.booleanValue()) {
                    UpdateActivity.this.d();
                } else {
                    UpdateActivity.this.e();
                }
            }

            public void a(d dVar) {
                UpdateActivity.this.e();
            }
        });
        a.a(this.a);
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, net.hockeyapp.android.b.a aVar) {
        this.a = new d(this, str, aVar);
    }

    public void e() {
        findViewById(b.button_update).setEnabled(true);
    }

    public String f() {
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 0)).toString();
        } catch (NameNotFoundException unused) {
            return "";
        }
    }

    private boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    @SuppressLint({"InlinedApi"})
    private boolean h() {
        boolean z = true;
        try {
            if (VERSION.SDK_INT < 17 || VERSION.SDK_INT >= 21) {
                if (Secure.getInt(getContentResolver(), "install_non_market_apps") != 1) {
                    z = false;
                }
                return z;
            }
            if (Global.getInt(getContentResolver(), "install_non_market_apps") != 1) {
                z = false;
            }
            return z;
        } catch (SettingNotFoundException unused) {
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public void g() {
        if (!i.a(this.d)) {
            this.c = new ErrorObject();
            this.c.a(getString(i.d.hockeyapp_error_no_network_message));
            runOnUiThread(new Runnable() {
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        } else if (a(this.d)) {
            if (h()) {
                d();
                return;
            }
            this.c = new ErrorObject();
            this.c.a("The installation from unknown sources is not enabled. Please check the device settings.");
            runOnUiThread(new Runnable() {
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        } else if (VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            this.c = new ErrorObject();
            this.c.a("The permission to access the external storage permission is not set. Please contact the developer.");
            runOnUiThread(new Runnable() {
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
        }
    }
}
