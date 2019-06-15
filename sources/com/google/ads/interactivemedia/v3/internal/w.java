package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import com.google.ads.interactivemedia.v3.internal.u.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class w implements u {
    private final u a;

    public w(u uVar) {
        this.a = uVar;
    }

    public JSONObject a(View view) {
        return ac.a(0, 0, 0, 0);
    }

    public void a(View view, JSONObject jSONObject, a aVar, boolean z) {
        Iterator it = a().iterator();
        while (it.hasNext()) {
            aVar.a((View) it.next(), this.a, jSONObject);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<View> a() {
        ArrayList arrayList = new ArrayList();
        p a = p.a();
        if (a != null) {
            Collection<g> c = a.c();
            IdentityHashMap identityHashMap = new IdentityHashMap((c.size() * 2) + 3);
            for (g g : c) {
                View g2 = g.g();
                if (g2 != null && ag.c(g2)) {
                    g2 = g2.getRootView();
                    if (!(g2 == null || identityHashMap.containsKey(g2))) {
                        identityHashMap.put(g2, g2);
                        float a2 = ag.a(g2);
                        int size = arrayList.size();
                        while (size > 0 && ag.a((View) arrayList.get(size - 1)) > a2) {
                            size--;
                        }
                        arrayList.add(size, g2);
                    }
                }
            }
        }
        return arrayList;
    }
}
