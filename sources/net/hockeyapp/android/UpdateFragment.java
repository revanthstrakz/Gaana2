package net.hockeyapp.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import java.util.Locale;
import net.hockeyapp.android.c.d;
import net.hockeyapp.android.c.e;
import net.hockeyapp.android.d.a;
import net.hockeyapp.android.d.k;
import net.hockeyapp.android.i.b;
import net.hockeyapp.android.i.c;
import org.json.JSONArray;
import org.json.JSONException;

@TargetApi(11)
public class UpdateFragment extends DialogFragment implements OnClickListener, j {
    public static final String FRAGMENT_URL = "url";
    public static final String FRAGMENT_VERSION_INFO = "versionInfo";
    private d mDownloadTask;
    private String mUrlString;
    private k mVersionHelper;
    private JSONArray mVersionInfo;

    public static UpdateFragment newInstance(JSONArray jSONArray, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString(FRAGMENT_VERSION_INFO, jSONArray.toString());
        UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.setArguments(bundle);
        return updateFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.mUrlString = getArguments().getString("url");
            this.mVersionInfo = new JSONArray(getArguments().getString(FRAGMENT_VERSION_INFO));
            setStyle(1, 16973939);
        } catch (JSONException unused) {
            dismiss();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View layoutView = getLayoutView();
        this.mVersionHelper = new k(getActivity(), this.mVersionInfo.toString(), this);
        ((TextView) layoutView.findViewById(b.label_title)).setText(getAppName());
        final TextView textView = (TextView) layoutView.findViewById(b.label_version);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Version ");
        stringBuilder.append(this.mVersionHelper.a());
        final String stringBuilder2 = stringBuilder.toString();
        final String b = this.mVersionHelper.b();
        String str = "Unknown size";
        if (this.mVersionHelper.c() >= 0) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) r2) / 1048576.0f)}));
            stringBuilder3.append(" MB");
            str = stringBuilder3.toString();
        } else {
            a.a(new e(getActivity(), this.mUrlString, new net.hockeyapp.android.b.a() {
                public void a(d dVar) {
                    if (dVar instanceof e) {
                        long c = ((e) dVar).c();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(((float) c) / 1048576.0f)}));
                        stringBuilder.append(" MB");
                        String stringBuilder2 = stringBuilder.toString();
                        textView.setText(UpdateFragment.this.getString(i.d.hockeyapp_update_version_details_label, new Object[]{stringBuilder2, b, stringBuilder2}));
                    }
                }
            }));
        }
        textView.setText(getString(i.d.hockeyapp_update_version_details_label, new Object[]{stringBuilder2, b, str}));
        ((Button) layoutView.findViewById(b.button_update)).setOnClickListener(this);
        WebView webView = (WebView) layoutView.findViewById(b.web_update_details);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL("https://sdk.hockeyapp.net/", this.mVersionHelper.a(false), "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING, null);
        return layoutView;
    }

    public void onClick(View view) {
        prepareDownload();
    }

    /* JADX WARNING: Missing block: B:14:0x0065, code skipped:
            return;
     */
    public void onRequestPermissionsResult(int r1, java.lang.String[] r2, int[] r3) {
        /*
        r0 = this;
        r2 = r2.length;
        if (r2 == 0) goto L_0x0065;
    L_0x0003:
        r2 = r3.length;
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        goto L_0x0065;
    L_0x0007:
        r2 = 1;
        if (r1 != r2) goto L_0x0064;
    L_0x000a:
        r1 = 0;
        r1 = r3[r1];
        if (r1 != 0) goto L_0x0017;
    L_0x000f:
        r1 = r0.getActivity();
        r0.startDownloadTask(r1);
        goto L_0x0064;
    L_0x0017:
        r1 = "User denied write permission, can't continue with updater task.";
        net.hockeyapp.android.d.d.b(r1);
        r1 = net.hockeyapp.android.k.a();
        if (r1 == 0) goto L_0x0026;
    L_0x0022:
        r1.c();
        goto L_0x0064;
    L_0x0026:
        r1 = new android.app.AlertDialog$Builder;
        r2 = r0.getActivity();
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
        r3 = new net.hockeyapp.android.UpdateFragment$2;
        r3.<init>(r0);
        r1 = r1.setPositiveButton(r2, r3);
        r1 = r1.create();
        r1.show();
    L_0x0064:
        return;
    L_0x0065:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.UpdateFragment.onRequestPermissionsResult(int, java.lang.String[], int[]):void");
    }

    public int getCurrentVersionCode() {
        try {
            return getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 128).versionCode;
        } catch (NameNotFoundException | NullPointerException unused) {
            return -1;
        }
    }

    public void prepareDownload() {
        if (VERSION.SDK_INT < 23 || getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            startDownloadTask(getActivity());
            dismiss();
            return;
        }
        requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
    }

    private void startDownloadTask(final Activity activity) {
        this.mDownloadTask = new d(activity, this.mUrlString, new net.hockeyapp.android.b.a() {
            public void a(d dVar) {
            }

            public void a(d dVar, Boolean bool) {
                if (bool.booleanValue()) {
                    UpdateFragment.this.startDownloadTask(activity);
                }
            }
        });
        a.a(this.mDownloadTask);
    }

    public String getAppName() {
        Activity activity = getActivity();
        try {
            PackageManager packageManager = activity.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(activity.getPackageName(), 0)).toString();
        } catch (NameNotFoundException unused) {
            return "";
        }
    }

    public View getLayoutView() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LayoutInflater.from(getActivity()).inflate(c.hockeyapp_fragment_update, linearLayout);
        return linearLayout;
    }
}
