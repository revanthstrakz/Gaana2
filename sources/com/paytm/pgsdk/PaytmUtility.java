package com.paytm.pgsdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.net.URLEncoder;
import org.json.JSONObject;

public class PaytmUtility {
    private static final String AMPERSAND = "&";
    private static final String EQUAL_TO = "=";
    private static final String TAG = "PGSDK";
    private static final String UTF_8 = "UTF-8";

    protected static synchronized String getStringFromBundle(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                debugLog("Extracting Strings from Bundle...");
                Object obj = 1;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuffer2.append(AMPERSAND);
                    }
                    stringBuffer2.append(str);
                    stringBuffer2.append(EQUAL_TO);
                    stringBuffer2.append(bundle.getString(str));
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Extracted String is ");
                stringBuilder.append(stringBuffer2.toString());
                debugLog(stringBuilder.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized String getURLEncodedStringFromBundle(Bundle bundle) {
        String stringBuffer;
        synchronized (PaytmUtility.class) {
            try {
                debugLog("Extracting Strings from Bundle...");
                Object obj = 1;
                StringBuffer stringBuffer2 = new StringBuffer();
                for (String str : bundle.keySet()) {
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuffer2.append(AMPERSAND);
                    }
                    stringBuffer2.append(URLEncoder.encode(str, "UTF-8"));
                    stringBuffer2.append(EQUAL_TO);
                    stringBuffer2.append(URLEncoder.encode(bundle.getString(str), "UTF-8"));
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("URL encoded String is ");
                stringBuilder.append(stringBuffer2.toString());
                debugLog(stringBuilder.toString());
                stringBuffer = stringBuffer2.toString();
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        return stringBuffer;
    }

    protected static synchronized void debugLog(String str) {
        synchronized (PaytmUtility.class) {
            Log.d(TAG, str);
        }
    }

    protected static synchronized void printStackTrace(Exception exception) {
        synchronized (PaytmUtility.class) {
            ThrowableExtension.printStackTrace(exception);
        }
    }

    protected static synchronized boolean isNetworkAvailable(Context context) {
        synchronized (PaytmUtility.class) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            boolean isConnected = activeNetworkInfo.isConnected();
            return isConnected;
        }
    }

    protected static String getJSONString(Bundle bundle) {
        Object jSONObject;
        StringBuilder stringBuilder;
        if (bundle != null) {
            try {
                if (bundle.size() > 0) {
                    jSONObject = new JSONObject();
                    for (String str : bundle.keySet()) {
                        jSONObject.put(str, bundle.get(str));
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("JSON string is ");
                    stringBuilder.append(jSONObject);
                    debugLog(stringBuilder.toString());
                    return jSONObject.toString();
                }
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        jSONObject = null;
        stringBuilder = new StringBuilder();
        stringBuilder.append("JSON string is ");
        stringBuilder.append(jSONObject);
        debugLog(stringBuilder.toString());
        return jSONObject.toString();
    }

    protected static String getURLEncodedJSONString(Bundle bundle) {
        Object jSONObject;
        StringBuilder stringBuilder;
        if (bundle != null) {
            try {
                if (bundle.size() > 0) {
                    jSONObject = new JSONObject();
                    for (String str : bundle.keySet()) {
                        jSONObject.put(URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(bundle.getString(str), "UTF-8"));
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("URL encoded JSON string is ");
                    stringBuilder.append(jSONObject);
                    debugLog(stringBuilder.toString());
                    return jSONObject.toString();
                }
            } catch (Exception e) {
                printStackTrace(e);
                return null;
            }
        }
        jSONObject = null;
        stringBuilder = new StringBuilder();
        stringBuilder.append("URL encoded JSON string is ");
        stringBuilder.append(jSONObject);
        debugLog(stringBuilder.toString());
        return jSONObject.toString();
    }
}
