package com.moengage.core;

import android.text.TextUtils;
import com.comscore.utils.Constants;
import com.moe.pushlibrary.exceptions.APIFailedException;
import com.moengage.core.MoERestClient.API_VERSION;
import org.json.JSONException;
import org.json.JSONObject;

final class MoEParser {
    static boolean isHttpStatusOk(int i) {
        return 200 == i;
    }

    private MoEParser() {
    }

    static boolean isValidAPIResponse(String str, API_VERSION api_version) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (api_version == API_VERSION.V1) {
            if (Constants.RESPONSE_MASK.equals(jSONObject.getString("result"))) {
                return true;
            }
        }
        if ("success".equals(jSONObject.getString("status"))) {
            return true;
        }
        return false;
    }

    static boolean parseReportAddResponse(int i) throws APIFailedException {
        if (isHttpStatusOk(i)) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Getting : ");
        stringBuilder.append(i);
        throw new APIFailedException(stringBuilder.toString());
    }
}
