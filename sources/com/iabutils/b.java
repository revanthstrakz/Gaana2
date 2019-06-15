package com.iabutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class b {
    Map<String, e> a = new HashMap();
    Map<String, c> b = new HashMap();

    b() {
    }

    public e a(String str) {
        return (e) this.a.get(str);
    }

    public c b(String str) {
        return (c) this.b.get(str);
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    /* Access modifiers changed, original: 0000 */
    public List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        for (c cVar : this.b.values()) {
            if (cVar.a().equals(str)) {
                arrayList.add(cVar.c());
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(e eVar) {
        this.a.put(eVar.a(), eVar);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar) {
        this.b.put(cVar.c(), cVar);
    }
}
