package com.helpshift.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    public static JSONObject a(Map<String, ArrayList> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), new JSONArray((Collection) entry.getValue()));
            }
        } catch (JSONException e) {
            l.a("HelpshiftDebug", "JSON Exception in parsing complex object", e);
        }
        return jSONObject;
    }

    public static JSONArray a(List<HashMap> list) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (HashMap hashMap : list) {
                JSONObject jSONObject = new JSONObject();
                for (Object next : hashMap.keySet()) {
                    if (next instanceof String) {
                        jSONObject.put((String) next, hashMap.get(next));
                    }
                }
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e) {
            l.a("HelpshiftDebug", "JSON Exception in parsing complex list", e);
        }
        return jSONArray;
    }

    public static ArrayList<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
            l.a("HelpshiftDebug", "jsonToStringArrayList", e);
        }
        return arrayList;
    }
}
