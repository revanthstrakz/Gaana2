package com.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.gaana.BaseLaunchActivity;
import com.gaana.R;
import com.gaana.SaveToGalleryActivity;

public class h {
    private static Dialog a;

    public static boolean a(Activity activity) {
        return a(activity, "android.permission.RECORD_AUDIO", 106);
    }

    public static boolean b(Activity activity) {
        return a(activity, "android.permission.RECORD_AUDIO", 107);
    }

    public static boolean c(Activity activity) {
        return a(activity, "android.permission.GET_ACCOUNTS", 104);
    }

    public static boolean d(Activity activity) {
        return a(activity, "android.permission.CAMERA", 108);
    }

    public static boolean a(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT <= 22) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            z = false;
        }
        return z;
    }

    public static boolean b(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT <= 22) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") != 0) {
            z = false;
        }
        return z;
    }

    public static boolean c(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT <= 22) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            z = false;
        }
        return z;
    }

    public static boolean d(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT <= 22) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") != 0) {
            z = false;
        }
        return z;
    }

    public static boolean e(Activity activity) {
        return a(activity, "android.permission.WRITE_EXTERNAL_STORAGE", 102);
    }

    public static boolean a(Activity activity, String str, int i) {
        if (VERSION.SDK_INT <= 22 || ContextCompat.checkSelfPermission(activity, str) == 0) {
            return true;
        }
        if (a(activity, str)) {
            e(activity, str, i);
        } else {
            d(activity, str, i);
        }
        return false;
    }

    private static void d(Activity activity, String str, int i) {
        ActivityCompat.requestPermissions(activity, new String[]{str}, i);
    }

    public static boolean a(Activity activity, String str) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
    }

    private static void e(android.app.Activity r1, java.lang.String r2, int r3) {
        /*
        r1 = f(r1, r2, r3);
        r3 = r2.hashCode();
        r0 = 0;
        switch(r3) {
            case -1888586689: goto L_0x0035;
            case 463403621: goto L_0x002b;
            case 1271781903: goto L_0x0021;
            case 1365911975: goto L_0x0017;
            case 1831139720: goto L_0x000d;
            default: goto L_0x000c;
        };
    L_0x000c:
        goto L_0x003f;
    L_0x000d:
        r3 = "android.permission.RECORD_AUDIO";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x003f;
    L_0x0015:
        r2 = 3;
        goto L_0x0040;
    L_0x0017:
        r3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x003f;
    L_0x001f:
        r2 = 1;
        goto L_0x0040;
    L_0x0021:
        r3 = "android.permission.GET_ACCOUNTS";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x003f;
    L_0x0029:
        r2 = 2;
        goto L_0x0040;
    L_0x002b:
        r3 = "android.permission.CAMERA";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x003f;
    L_0x0033:
        r2 = 4;
        goto L_0x0040;
    L_0x0035:
        r3 = "android.permission.ACCESS_FINE_LOCATION";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x003f;
    L_0x003d:
        r2 = r0;
        goto L_0x0040;
    L_0x003f:
        r2 = -1;
    L_0x0040:
        switch(r2) {
            case 0: goto L_0x0070;
            case 1: goto L_0x0065;
            case 2: goto L_0x005a;
            case 3: goto L_0x004f;
            case 4: goto L_0x0044;
            default: goto L_0x0043;
        };
    L_0x0043:
        goto L_0x007a;
    L_0x0044:
        r2 = 2131296916; // 0x7f090294 float:1.8211762E38 double:1.0530005873E-314;
        r2 = r1.findViewById(r2);
        r2.setVisibility(r0);
        goto L_0x007a;
    L_0x004f:
        r2 = 2131296915; // 0x7f090293 float:1.821176E38 double:1.053000587E-314;
        r2 = r1.findViewById(r2);
        r2.setVisibility(r0);
        goto L_0x007a;
    L_0x005a:
        r2 = 2131296914; // 0x7f090292 float:1.8211758E38 double:1.0530005863E-314;
        r2 = r1.findViewById(r2);
        r2.setVisibility(r0);
        goto L_0x007a;
    L_0x0065:
        r2 = 2131296912; // 0x7f090290 float:1.8211754E38 double:1.0530005853E-314;
        r2 = r1.findViewById(r2);
        r2.setVisibility(r0);
        goto L_0x007a;
    L_0x0070:
        r2 = 2131296911; // 0x7f09028f float:1.8211752E38 double:1.053000585E-314;
        r2 = r1.findViewById(r2);
        r2.setVisibility(r0);
    L_0x007a:
        r1.show();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.utilities.h.e(android.app.Activity, java.lang.String, int):void");
    }

    private static Dialog f(final Activity activity, final String str, final int i) {
        if (a != null && a.isShowing()) {
            a.dismiss();
        }
        a = new Dialog(activity, 16973834);
        a.setCancelable(false);
        a.setContentView(R.layout.dialog_explanation);
        a.findViewById(R.id.dialog_explanation_ok).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (h.a != null && h.a.isShowing()) {
                    h.a.dismiss();
                }
                h.d(activity, str, i);
            }
        });
        a.findViewById(R.id.dialog_explanation_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (h.a != null && h.a.isShowing()) {
                    h.a.dismiss();
                }
                if (activity instanceof BaseLaunchActivity) {
                    ((BaseLaunchActivity) activity).getUser();
                } else if (activity instanceof SaveToGalleryActivity) {
                    ((SaveToGalleryActivity) activity).onCancelClickedOnRationalDialog();
                }
            }
        });
        return a;
    }

    public static void b(final Activity activity, String str, int i) {
        Snackbar make = Snackbar.make(activity.findViewById(16908290), (CharSequence) str, i);
        make.setAction((int) R.string.settings_text, new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.addFlags(1073741824);
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                activity.startActivityForResult(intent, 501);
            }
        });
        make.getView().findViewById(R.id.snackbar_action).setBackgroundColor(0);
        make.show();
    }
}
