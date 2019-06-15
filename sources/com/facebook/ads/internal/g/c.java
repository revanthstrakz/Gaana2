package com.facebook.ads.internal.g;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.WorkerThread;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class c extends g {
    public static final b a = new b(0, "event_id", "TEXT PRIMARY KEY");
    public static final b b = new b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    public static final b c = new b(2, "priority", "INTEGER");
    public static final b d = new b(3, "type", "TEXT");
    public static final b e = new b(4, "time", "REAL");
    public static final b f = new b(5, "session_time", "REAL");
    public static final b g = new b(6, "session_id", "TEXT");
    public static final b h = new b(7, "data", "TEXT");
    public static final b i = new b(8, "attempt", "INTEGER");
    public static final b[] j = new b[]{a, b, c, d, e, f, g, h, i};
    private static final String l = g.a("events", j);

    public c(d dVar) {
        super(dVar);
    }

    public String a() {
        return "events";
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public String a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map) {
        String uuid = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(a.b, uuid);
        contentValues.put(b.b, str);
        contentValues.put(c.b, Integer.valueOf(i));
        contentValues.put(d.b, str2);
        contentValues.put(e.b, Double.valueOf(d));
        contentValues.put(f.b, Double.valueOf(d2));
        contentValues.put(g.b, str3);
        contentValues.put(h.b, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(i.b, Integer.valueOf(0));
        f().insertOrThrow("events", null, contentValues);
        return uuid;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.b);
        stringBuilder.append(" = ?");
        return f().delete("events", stringBuilder.toString(), new String[]{str}) > 0;
    }

    public b[] b() {
        return j;
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public Cursor c() {
        return f().rawQuery("SELECT count(*) FROM events", null);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public Cursor d() {
        return f().rawQuery(l, null);
    }
}
