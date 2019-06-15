package com.helpshift.common.platform;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.helpshift.analytics.b.a;
import com.helpshift.cif.dto.CustomIssueFieldDTO;
import com.helpshift.common.exception.ParseException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.meta.dto.BreadCrumbDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements n {
    public String a(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Exception while calling jsonify on map");
        }
    }

    public Object b(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
            return jSONObject;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Exception while calling jsonify on map");
        }
    }

    public String a(Collection collection) {
        return new JSONArray(collection).toString();
    }

    public Object a(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public String b(List<a> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            for (a a : list) {
                jSONArray.put(a(a));
            }
            return jSONArray.toString();
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Exception while forming analytics string");
        }
    }

    public Object c(List<BreadCrumbDTO> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                for (BreadCrumbDTO a : list) {
                    jSONArray.put(a(a));
                }
            } catch (JSONException e) {
                throw RootAPIException.a(e, ParseException.GENERIC, "Exception while forming breadcrumb string");
            }
        }
        return jSONArray;
    }

    public Object d(List<com.helpshift.meta.dto.a> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            try {
                for (com.helpshift.meta.dto.a a : list) {
                    jSONArray.put(a(a));
                }
            } catch (JSONException e) {
                throw RootAPIException.a(e, ParseException.GENERIC, "Exception while forming debugLog string");
            }
        }
        return jSONArray;
    }

    public String a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.remove(str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return str;
        }
    }

    public Object c(Map<String, Serializable> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                if (obj instanceof String[]) {
                    obj = a(new ArrayList(Arrays.asList((String[]) obj)));
                }
                jSONObject.put(str, obj);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw RootAPIException.a(e, ParseException.GENERIC, "Exception while forming custom meta string");
        }
    }

    public Object e(List<CustomIssueFieldDTO> list) {
        JSONObject jSONObject = new JSONObject();
        for (CustomIssueFieldDTO customIssueFieldDTO : list) {
            try {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(customIssueFieldDTO.b);
                for (Object put : customIssueFieldDTO.c) {
                    jSONArray.put(put);
                }
                jSONObject.put(customIssueFieldDTO.a, jSONArray);
            } catch (JSONException e) {
                throw RootAPIException.a(e, ParseException.GENERIC, "Exception while forming custom issue field string");
            }
        }
        return jSONObject;
    }

    private JSONObject a(com.helpshift.meta.dto.a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (aVar.c != null) {
            jSONObject.put("message", aVar.c);
        }
        jSONObject.put("level", aVar.a);
        jSONObject.put("tag", aVar.b);
        if (!TextUtils.isEmpty(aVar.d)) {
            jSONObject.put("exception", aVar.d);
        }
        return jSONObject;
    }

    private JSONObject a(BreadCrumbDTO breadCrumbDTO) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, breadCrumbDTO.a);
        jSONObject.put("datetime", breadCrumbDTO.b);
        return jSONObject;
    }

    private JSONObject a(a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(HlsSegmentFormat.TS, aVar.d);
        jSONObject.put("t", aVar.b.key);
        if (aVar.c != null) {
            Map hashMap = new HashMap();
            hashMap.putAll(aVar.c);
            jSONObject.put("d", b(hashMap));
        }
        return jSONObject;
    }
}
