package com.moe.pushlibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.moengage.core.Logger;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONObject;

public final class MoEHelperUtils {
    public static final int BASELINE_SCREEN_DPI = 160;
    private static final String EVENT_ACTION = "EVENT_ACTION";
    private static final String EVENT_ATTRS = "EVENT_ATTRS";
    private static final String EVENT_ATTRS_CUST = "EVENT_ATTRS_CUST";
    private static final String EVENT_G_TIME = "EVENT_G_TIME";
    private static final String EVENT_L_TIME = "EVENT_L_TIME";
    private static final String EXTRA_KEY_FROM_INBOX = "from";
    private static final String EXTRA_VALUE_FROM_INBOX = "inbox";
    static final int[] MONTH_NUMBERS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private static final String PREF_NAME = "pref_moe";

    private MoEHelperUtils() {
    }

    public static Bundle convertJSONObjecttoBundle(JSONObject jSONObject) {
        Bundle bundle;
        try {
            Iterator keys = jSONObject.keys();
            bundle = new Bundle();
            while (keys.hasNext()) {
                try {
                    String str = (String) keys.next();
                    String string = jSONObject.getString(str);
                    if (string instanceof Integer) {
                        bundle.putInt(str, ((Integer) string).intValue());
                    } else if (string instanceof Boolean) {
                        bundle.putBoolean(str, ((Boolean) string).booleanValue());
                    } else if (string instanceof String) {
                        bundle.putString(str, string);
                    } else if (string instanceof Byte) {
                        bundle.putByte(str, ((Byte) string).byteValue());
                    } else if (string instanceof Character) {
                        bundle.putChar(str, ((Character) string).charValue());
                    } else if (string instanceof Double) {
                        bundle.putDouble(str, ((Double) string).doubleValue());
                    } else if (string instanceof Float) {
                        bundle.putFloat(str, ((Float) string).floatValue());
                    } else if (string instanceof Long) {
                        bundle.putLong(str, ((Long) string).longValue());
                    } else if (string instanceof Short) {
                        bundle.putShort(str, ((Short) string).shortValue());
                    }
                } catch (Exception unused) {
                    return bundle;
                }
            }
            return bundle;
        } catch (Exception unused2) {
            bundle = null;
            return bundle;
        }
    }

    public static void copyCouponCodeToClipboard(Context context, String str) {
        if (VERSION.SDK_INT < 11) {
            copytoClipboardHoneyLess(context, str);
        } else {
            copytoClipboardHoney(context, str);
        }
        showToast("Coupon code copied to clipboard", context);
    }

    public static void copyCouponCodeToClipboard(Context context, String str, String str2) {
        if (VERSION.SDK_INT < 11) {
            copytoClipboardHoneyLess(context, str);
        } else {
            copytoClipboardHoney(context, str);
        }
        showToast(str2, context);
    }

    public static void showToast(String str, Context context) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(context, str, 0).show();
        }
    }

    @TargetApi(11)
    private static void copytoClipboardHoney(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("coupon code", str));
    }

    private static void copytoClipboardHoneyLess(Context context, String str) {
        ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str);
    }

    public static void dumpIntentExtras(@NonNull Bundle bundle) {
        if (bundle != null) {
            Set<String> keySet = bundle.keySet();
            if (keySet != null && !keySet.isEmpty()) {
                Logger.v("------Start of bundle extras------");
                for (String str : keySet) {
                    Object obj = bundle.get(str);
                    if (obj != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("[ ");
                        stringBuilder.append(str);
                        stringBuilder.append(" = ");
                        stringBuilder.append(obj.toString());
                        stringBuilder.append(" ]");
                        Logger.v(stringBuilder.toString());
                    }
                }
                Logger.v("-------End of bundle extras-------");
            }
        }
    }

    public static void dumpIntentExtras(Intent intent) {
        if (intent != null) {
            dumpIntentExtras(intent.getExtras());
        }
    }

    public static boolean isChangingConfiguration(Activity activity) {
        if (VERSION.SDK_INT <= 11) {
            return false;
        }
        return detectConfigChange(activity);
    }

    @TargetApi(11)
    private static boolean detectConfigChange(Activity activity) {
        return activity == null ? false : activity.isChangingConfigurations();
    }

    public static Intent getLauncherActivityIntent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
    }

    public static void saveInstallReferrer(String str, Context context) {
        if (context != null) {
            getSharedPrefs(context).edit().putString(MoEHelperConstants.PREF_KEY_INSTALL_REFERRER, str).apply();
        }
    }

    public static String getInstallReferrer(Context context) {
        if (context == null) {
            return null;
        }
        return getSharedPrefs(context).getString(MoEHelperConstants.PREF_KEY_INSTALL_REFERRER, null);
    }

    public static void removeInstallReferrer(Context context) {
        if (context != null) {
            getSharedPrefs(context).edit().remove(MoEHelperConstants.PREF_KEY_INSTALL_REFERRER).commit();
        }
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context == null ? null : context.getSharedPreferences(PREF_NAME, 0);
    }

    public static Bitmap downloadImageBitmap(String str) {
        Bitmap decodeStream;
        Throwable e;
        if (str == null || str.length() == 0) {
            Logger.e("Null or empty Url string passed to image bitmap download. Not attempting download.");
            return null;
        }
        try {
            InputStream openStream = new URL(str).openStream();
            decodeStream = BitmapFactory.decodeStream(openStream);
            if (openStream != null) {
                try {
                    openStream.close();
                } catch (OutOfMemoryError e2) {
                    e = e2;
                } catch (UnknownHostException e22) {
                    e = e22;
                    Logger.f(String.format("Unknown Host Exception in image bitmap download for Url: %s. Device may be offline.", new Object[]{str}), e);
                    return decodeStream;
                } catch (MalformedURLException e222) {
                    e = e222;
                    Logger.f(String.format("Malformed URL Exception in image bitmap download for Url: %s. Image Url may be corrupted.", new Object[]{str}), e);
                    return decodeStream;
                } catch (Exception e2222) {
                    e = e2222;
                    Logger.f(String.format("Exception in image bitmap download for Url: %s", new Object[]{str}), e);
                    return decodeStream;
                }
            }
        } catch (OutOfMemoryError e3) {
            e = e3;
            decodeStream = null;
            Logger.f(String.format("Out of Memory Error in image bitmap download for Url: %s.", new Object[]{str}), e);
            return decodeStream;
        } catch (UnknownHostException e4) {
            e = e4;
            decodeStream = null;
            Logger.f(String.format("Unknown Host Exception in image bitmap download for Url: %s. Device may be offline.", new Object[]{str}), e);
            return decodeStream;
        } catch (MalformedURLException e5) {
            e = e5;
            decodeStream = null;
            Logger.f(String.format("Malformed URL Exception in image bitmap download for Url: %s. Image Url may be corrupted.", new Object[]{str}), e);
            return decodeStream;
        } catch (Exception e6) {
            e = e6;
            decodeStream = null;
            Logger.f(String.format("Exception in image bitmap download for Url: %s", new Object[]{str}), e);
            return decodeStream;
        }
        return decodeStream;
    }

    public static String getDate() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        int i3 = instance.get(13);
        int i4 = instance.get(5);
        int i5 = MONTH_NUMBERS[instance.get(2)];
        int i6 = instance.get(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i4);
        stringBuilder.append(":");
        stringBuilder.append(i5);
        stringBuilder.append(":");
        stringBuilder.append(i6);
        stringBuilder.append(":");
        stringBuilder.append(i);
        stringBuilder.append(":");
        stringBuilder.append(i2);
        stringBuilder.append(":");
        stringBuilder.append(i3);
        return stringBuilder.toString();
    }

    public static int getPxFromDp(int i, int i2) {
        return (i * i2) / BASELINE_SCREEN_DPI;
    }

    public static boolean isValidResourceId(Context context, int i) {
        try {
            context.getResources().getResourceName(i);
            return true;
        } catch (NotFoundException | Exception unused) {
            return false;
        }
    }

    public static boolean isFromInbox(Bundle bundle) {
        boolean z = false;
        if (bundle == null) {
            return false;
        }
        if (bundle.containsKey(EXTRA_KEY_FROM_INBOX) && EXTRA_VALUE_FROM_INBOX.equals(bundle.getString(EXTRA_KEY_FROM_INBOX))) {
            z = true;
        }
        return z;
    }

    public static JSONObject getDatapointJSON(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            Object obj = 1;
            if (!(jSONObject == null || jSONObject.length() == 0)) {
                jSONObject3.put(EVENT_ATTRS, jSONObject.toString());
                obj = null;
            }
            if (!(jSONObject2 == null || jSONObject2.length() == 0)) {
                jSONObject3.put(EVENT_ATTRS_CUST, jSONObject2.toString());
                obj = null;
            }
            if (obj != null) {
                jSONObject3.put(EVENT_ATTRS, new JSONObject().toString());
            }
            jSONObject3.put(EVENT_G_TIME, Long.toString(System.currentTimeMillis()));
            jSONObject3.put(EVENT_L_TIME, getDate());
            return jSONObject3;
        } catch (Exception e) {
            Logger.f("MoEHelperUtils:getDatapointJSON ", e);
            return null;
        }
    }

    public static JSONObject getDatapointJSON(String str, JSONObject jSONObject) {
        if (!jSONObject.has(EVENT_G_TIME) || !jSONObject.has(EVENT_L_TIME)) {
            return getDatapointJSON(str, jSONObject, Long.toString(System.currentTimeMillis()), getDate());
        }
        try {
            jSONObject.put(EVENT_ACTION, str);
        } catch (Exception e) {
            Logger.f("MoEHelperUtils: getDatapointJSON ", e);
        }
        return jSONObject;
    }

    public static JSONObject getDatapointJSON(String str, JSONObject jSONObject, String str2, String str3) {
        return getDatapointJSON(str, jSONObject, null, str2, str3);
    }

    public static JSONObject getDatapointJSON(String str, JSONObject jSONObject, JSONObject jSONObject2, String str2, String str3) {
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(EVENT_ACTION, str);
            if (jSONObject != null) {
                jSONObject3.put(EVENT_ATTRS, jSONObject.toString());
            }
            if (!(jSONObject2 == null || jSONObject2.length() == 0)) {
                jSONObject3.put(EVENT_ATTRS_CUST, jSONObject2.toString());
            }
            jSONObject3.put(EVENT_G_TIME, str2);
            jSONObject3.put(EVENT_L_TIME, str3);
            return jSONObject3;
        } catch (Exception e) {
            Logger.f("MoEHelperUtils:getDatapointJSON", e);
            return null;
        }
    }

    public static String getActionFromEvent(String str) {
        try {
            return new JSONObject(str).getString(EVENT_ACTION);
        } catch (Exception e) {
            Logger.f("MoEHelperUtils:getActionFromEvent", e);
            return null;
        }
    }

    public static boolean hasPermission(Context context, String str) {
        boolean z = false;
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                        z = true;
                    }
                    return z;
                }
            } catch (RuntimeException e) {
                Logger.f("MoEHelperUtils: hasPermission ", e);
                return false;
            } catch (Exception e2) {
                Logger.f("MoEHelperUtils: hasPermission ", e2);
                return false;
            }
        }
        return false;
    }
}
