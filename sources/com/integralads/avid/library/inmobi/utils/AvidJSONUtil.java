package com.integralads.avid.library.inmobi.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AvidJSONUtil {
    private static String[] KEYS = new String[]{KEY_X, KEY_Y, "width", "height"};
    public static final String KEY_CHILD_VIEWS = "childViews";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_FRIENDLY_OBSTRUCTION_FOR = "isFriendlyObstructionFor";
    public static final String KEY_ROOT_VIEW = "rootView";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";
    static float density = Resources.getSystem().getDisplayMetrics().density;

    public static void init(Context context) {
        if (context != null) {
            density = context.getResources().getDisplayMetrics().density;
        }
    }

    public static JSONObject getEmptyTreeJSONObject() {
        return getTreeJSONObject(getViewState(0, 0, 0, 0), AvidTimestamp.getCurrentTime());
    }

    public static JSONObject getTreeJSONObject(JSONObject jSONObject, double d) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(KEY_TIMESTAMP, d);
            jSONObject2.put(KEY_ROOT_VIEW, jSONObject);
        } catch (JSONException e) {
            AvidLogs.e("Error with creating treeJSONObject", e);
        }
        return jSONObject2;
    }

    public static JSONObject getViewState(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_X, (double) pxToDp(i));
            jSONObject.put(KEY_Y, (double) pxToDp(i2));
            jSONObject.put("width", (double) pxToDp(i3));
            jSONObject.put("height", (double) pxToDp(i4));
        } catch (JSONException e) {
            AvidLogs.e("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    static float pxToDp(int i) {
        return ((float) i) / density;
    }

    public static void addAvidId(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("id", str);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting avid id", e);
        }
    }

    public static void addFriendlyObstruction(JSONObject jSONObject, List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        try {
            jSONObject.put(KEY_IS_FRIENDLY_OBSTRUCTION_FOR, jSONArray);
        } catch (JSONException e) {
            AvidLogs.e("Error with setting friendly obstruction", e);
        }
    }

    public static void addChildState(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(KEY_CHILD_VIEWS);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put(KEY_CHILD_VIEWS, optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void fixStateFrame(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(KEY_CHILD_VIEWS);
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            int i = 0;
            int i2 = 0;
            int i3 = i2;
            while (i < length) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt(KEY_X);
                    int optInt2 = optJSONObject.optInt(KEY_Y);
                    int optInt3 = optJSONObject.optInt("width");
                    int optInt4 = optJSONObject.optInt("height");
                    i2 = Math.max(i2, optInt + optInt3);
                    i3 = Math.max(i3, optInt2 + optInt4);
                }
                i++;
            }
            try {
                jSONObject.put("width", i2);
                jSONObject.put("height", i3);
            } catch (JSONException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public static boolean equalStates(@NonNull JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        if (jSONObject2 != null && compareRequiredValues(jSONObject, jSONObject2) && compareSessionId(jSONObject, jSONObject2) && compareFriendlySessionIds(jSONObject, jSONObject2) && compareChildren(jSONObject, jSONObject2)) {
            return true;
        }
        return false;
    }

    private static boolean compareRequiredValues(JSONObject jSONObject, JSONObject jSONObject2) {
        for (String str : KEYS) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareSessionId(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject.optString("id", "").equals(jSONObject2.optString("id", ""));
    }

    private static boolean compareFriendlySessionIds(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        if (!compareJSONArrays(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!optJSONArray.optString(i, "").equals(optJSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareChildren(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(KEY_CHILD_VIEWS);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(KEY_CHILD_VIEWS);
        if (!compareJSONArrays(optJSONArray, optJSONArray2)) {
            return false;
        }
        if (optJSONArray == null) {
            return true;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!equalStates(optJSONArray.optJSONObject(i), optJSONArray2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:9:0x000f, code skipped:
            return false;
     */
    private static boolean compareJSONArrays(org.json.JSONArray r2, org.json.JSONArray r3) {
        /*
        r0 = 1;
        if (r2 != 0) goto L_0x0006;
    L_0x0003:
        if (r3 != 0) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = 0;
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        if (r3 != 0) goto L_0x000f;
    L_0x000b:
        if (r2 == 0) goto L_0x0010;
    L_0x000d:
        if (r3 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r2 = r2.length();
        r3 = r3.length();
        if (r2 != r3) goto L_0x001b;
    L_0x001a:
        return r0;
    L_0x001b:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.integralads.avid.library.inmobi.utils.AvidJSONUtil.compareJSONArrays(org.json.JSONArray, org.json.JSONArray):boolean");
    }
}
