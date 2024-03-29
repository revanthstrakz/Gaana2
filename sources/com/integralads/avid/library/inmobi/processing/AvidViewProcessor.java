package com.integralads.avid.library.inmobi.processing;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class AvidViewProcessor implements IAvidNodeProcessor {
    private final int[] xyAxisCoordinates = new int[2];

    public JSONObject getState(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.xyAxisCoordinates);
        return AvidJSONUtil.getViewState(this.xyAxisCoordinates[0], this.xyAxisCoordinates[1], width, height);
    }

    public void iterateChildren(View view, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || VERSION.SDK_INT < 21) {
                iterateChilren(viewGroup, jSONObject, iAvidViewWalker);
            } else {
                sortAndIterateChilren(viewGroup, jSONObject, iAvidViewWalker);
            }
        }
    }

    private void iterateChilren(ViewGroup viewGroup, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            iAvidViewWalker.walkView(viewGroup.getChildAt(i), this, jSONObject);
        }
    }

    @TargetApi(21)
    private void sortAndIterateChilren(ViewGroup viewGroup, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        ArrayList arrayList2 = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                iAvidViewWalker.walkView((View) it2.next(), this, jSONObject);
            }
        }
    }
}
