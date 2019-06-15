package com.helpshift.q;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.util.l;

public class e extends a {
    private final Context b;
    private SQLiteOpenHelper c;

    e(Context context) {
        this.b = context;
        this.c = new c(context);
        this.a = new b(this.c, "__hs__kv_backup");
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
        this.c = new c(this.b);
        this.a = new b(this.c, "__hs__kv_backup");
    }
}
