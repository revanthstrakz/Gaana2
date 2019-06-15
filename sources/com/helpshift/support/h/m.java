package com.helpshift.support.h;

import android.content.Context;
import com.helpshift.q.a;
import com.helpshift.q.b;
import com.helpshift.util.l;

class m extends a {
    private final Context b;
    private l c;

    m(Context context) {
        this.b = context;
        this.c = new l(context);
        this.a = new b(this.c, null);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (Exception e) {
            l.c("Helpshift_RetryKeyValue", "Error in closing DB", e);
        }
        this.c = new l(this.b);
        this.a = new b(this.c, null);
    }
}
