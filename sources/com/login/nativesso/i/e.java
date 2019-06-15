package com.login.nativesso.i;

import android.content.Context;
import com.truecaller.android.sdk.TrueSDK;
import java.util.HashMap;
import java.util.Map;

public class e {
    private static Map<Integer, String> a = new HashMap();

    static {
        a.put(Integer.valueOf(0), "ERROR_TYPE_INTERNAL");
        a.put(Integer.valueOf(1), "ERROR_TYPE_NETWORK");
        a.put(Integer.valueOf(2), "ERROR_TYPE_USER_DENIED");
        a.put(Integer.valueOf(3), "ERROR_TYPE_UNAUTHORIZED_PARTNER");
        a.put(Integer.valueOf(4), "ERROR_TYPE_UNAUTHORIZED_USER");
        a.put(Integer.valueOf(5), "ERROR_TYPE_TRUECALLER_CLOSED_UNEXPECTEDLY");
        a.put(Integer.valueOf(6), "ERROR_TYPE_TRUESDK_TOO_OLD");
        a.put(Integer.valueOf(7), "ERROR_TYPE_POSSIBLE_REQ_CODE_COLLISION");
        a.put(Integer.valueOf(8), "ERROR_TYPE_RESPONSE_SIGNATURE_MISSMATCH");
        a.put(Integer.valueOf(9), "ERROR_TYPE_REQUEST_NONCE_MISSMATCH");
        a.put(Integer.valueOf(10), "ERROR_TYPE_INVALID_ACCOUNT_STATE");
        a.put(Integer.valueOf(11), "ERROR_TYPE_TC_NOT_INSTALLED");
    }

    public static boolean a(Context context) {
        return TrueSDK.getInstance().isUsable();
    }
}
