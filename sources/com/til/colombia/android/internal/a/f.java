package com.til.colombia.android.internal.a;

import android.view.View;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.Item;
import java.util.List;

final class f implements d {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void a(List<View> list, List<View> list2) {
        for (View view : list) {
            Item item = (Item) this.a.b.get(view);
            if (item == null) {
                this.a.a(view);
            } else {
                i iVar = (i) this.a.c.get(view);
                if (iVar == null || !item.equals(iVar.a)) {
                    this.a.c.put(view, new i(item));
                }
            }
        }
        for (View view2 : list2) {
            try {
                synchronized (this.a.c) {
                    this.a.c.remove(view2);
                }
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        this.a.b();
    }
}
