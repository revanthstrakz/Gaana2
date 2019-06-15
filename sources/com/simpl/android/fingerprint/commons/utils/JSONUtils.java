package com.simpl.android.fingerprint.commons.utils;

import com.simpl.android.fingerprint.a.g;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

public class JSONUtils {
    public static JSONArray getJSONArray(ArrayList<String> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put((String) it.next());
            }
        }
        return jSONArray;
    }

    public static <T extends g> JSONArray getJSONArrayFromJsonable(ArrayList<T> arrayList) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            jSONArray.put(((g) it.next()).a());
        }
        return jSONArray;
    }
}
