package com.til.colombia.android.service;

import com.til.colombia.android.internal.a.h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

final class cb {
    public static List<String> a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static List<String> a(List<String> list, String str) {
        if (h.a(str)) {
            return list;
        }
        List list2;
        if (list2 == null) {
            list2 = new ArrayList();
        }
        list2.add(str);
        return list2;
    }
}
