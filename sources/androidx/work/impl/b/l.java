package androidx.work.impl.b;

import android.arch.persistence.a.e;
import android.arch.persistence.a.f;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.b;
import android.arch.persistence.room.g;
import android.arch.persistence.room.h;
import android.database.Cursor;
import androidx.work.WorkInfo.State;
import androidx.work.d;
import androidx.work.impl.b.j.a;
import java.util.ArrayList;
import java.util.List;

public class l implements k {
    private final RoomDatabase a;
    private final b b;
    private final h c;
    private final h d;
    private final h e;
    private final h f;
    private final h g;
    private final h h;
    private final h i;
    private final h j;

    public l(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new b<j>(roomDatabase) {
            public String a() {
                return "INSERT OR IGNORE INTO `WorkSpec`(`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void a(f fVar, j jVar) {
                if (jVar.a == null) {
                    fVar.a(1);
                } else {
                    fVar.a(1, jVar.a);
                }
                fVar.a(2, (long) p.a(jVar.b));
                if (jVar.c == null) {
                    fVar.a(3);
                } else {
                    fVar.a(3, jVar.c);
                }
                if (jVar.d == null) {
                    fVar.a(4);
                } else {
                    fVar.a(4, jVar.d);
                }
                byte[] a = d.a(jVar.e);
                if (a == null) {
                    fVar.a(5);
                } else {
                    fVar.a(5, a);
                }
                a = d.a(jVar.f);
                if (a == null) {
                    fVar.a(6);
                } else {
                    fVar.a(6, a);
                }
                fVar.a(7, jVar.g);
                fVar.a(8, jVar.h);
                fVar.a(9, jVar.i);
                fVar.a(10, (long) jVar.k);
                fVar.a(11, (long) p.a(jVar.l));
                fVar.a(12, jVar.m);
                fVar.a(13, jVar.n);
                fVar.a(14, jVar.o);
                fVar.a(15, jVar.p);
                androidx.work.b bVar = jVar.j;
                if (bVar != null) {
                    fVar.a(16, (long) p.a(bVar.a()));
                    fVar.a(17, (long) bVar.b());
                    fVar.a(18, (long) bVar.c());
                    fVar.a(19, (long) bVar.d());
                    fVar.a(20, (long) bVar.e());
                    fVar.a(21, bVar.f());
                    fVar.a(22, bVar.g());
                    byte[] a2 = p.a(bVar.h());
                    if (a2 == null) {
                        fVar.a(23);
                        return;
                    } else {
                        fVar.a(23, a2);
                        return;
                    }
                }
                fVar.a(16);
                fVar.a(17);
                fVar.a(18);
                fVar.a(19);
                fVar.a(20);
                fVar.a(21);
                fVar.a(22);
                fVar.a(23);
            }
        };
        this.c = new h(roomDatabase) {
            public String a() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.d = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.e = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.f = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.g = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.h = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.i = new h(roomDatabase) {
            public String a() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.j = new h(roomDatabase) {
            public String a() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    public void a(j jVar) {
        this.a.f();
        try {
            this.b.a(jVar);
            this.a.h();
        } finally {
            this.a.g();
        }
    }

    public void a(String str) {
        f c = this.c.c();
        this.a.f();
        if (str == null) {
            try {
                c.a(1);
            } catch (Throwable th) {
                this.a.g();
                this.c.a(c);
            }
        } else {
            c.a(1, str);
        }
        c.a();
        this.a.h();
        this.a.g();
        this.c.a(c);
    }

    public void a(String str, d dVar) {
        f c = this.d.c();
        this.a.f();
        try {
            byte[] a = d.a(dVar);
            if (a == null) {
                c.a(1);
            } else {
                c.a(1, a);
            }
            if (str == null) {
                c.a(2);
            } else {
                c.a(2, str);
            }
            c.a();
            this.a.h();
        } finally {
            this.a.g();
            this.d.a(c);
        }
    }

    public void a(String str, long j) {
        f c = this.e.c();
        this.a.f();
        try {
            c.a(1, j);
            if (str == null) {
                c.a(2);
            } else {
                c.a(2, str);
            }
            c.a();
            this.a.h();
        } finally {
            this.a.g();
            this.e.a(c);
        }
    }

    public int d(String str) {
        f c = this.f.c();
        this.a.f();
        if (str == null) {
            try {
                c.a(1);
            } catch (Throwable th) {
                this.a.g();
                this.f.a(c);
            }
        } else {
            c.a(1, str);
        }
        int a = c.a();
        this.a.h();
        this.a.g();
        this.f.a(c);
        return a;
    }

    public int e(String str) {
        f c = this.g.c();
        this.a.f();
        if (str == null) {
            try {
                c.a(1);
            } catch (Throwable th) {
                this.a.g();
                this.g.a(c);
            }
        } else {
            c.a(1, str);
        }
        int a = c.a();
        this.a.h();
        this.a.g();
        this.g.a(c);
        return a;
    }

    public int b(String str, long j) {
        f c = this.h.c();
        this.a.f();
        try {
            c.a(1, j);
            if (str == null) {
                c.a(2);
            } else {
                c.a(2, str);
            }
            int a = c.a();
            this.a.h();
            return a;
        } finally {
            this.a.g();
            this.h.a(c);
        }
    }

    public int b() {
        f c = this.i.c();
        this.a.f();
        try {
            int a = c.a();
            this.a.h();
            return a;
        } finally {
            this.a.g();
            this.i.a(c);
        }
    }

    public j b(String str) {
        Throwable th;
        Throwable th2;
        String str2 = str;
        g a = g.a("SELECT * FROM workspec WHERE id=?", 1);
        if (str2 == null) {
            a.a(1);
        } else {
            a.a(1, str2);
        }
        Cursor a2 = this.a.a((e) a);
        try {
            int columnIndexOrThrow = a2.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = a2.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = a2.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = a2.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = a2.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = a2.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = a2.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = a2.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = a2.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = a2.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = a2.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = a2.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = a2.getColumnIndexOrThrow("period_start_time");
            e eVar = a;
            try {
                j jVar;
                int columnIndexOrThrow14 = a2.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = a2.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = a2.getColumnIndexOrThrow("required_network_type");
                int i = columnIndexOrThrow13;
                columnIndexOrThrow13 = a2.getColumnIndexOrThrow("requires_charging");
                int i2 = columnIndexOrThrow12;
                columnIndexOrThrow12 = a2.getColumnIndexOrThrow("requires_device_idle");
                int i3 = columnIndexOrThrow11;
                columnIndexOrThrow11 = a2.getColumnIndexOrThrow("requires_battery_not_low");
                int i4 = columnIndexOrThrow10;
                columnIndexOrThrow10 = a2.getColumnIndexOrThrow("requires_storage_not_low");
                int i5 = columnIndexOrThrow9;
                columnIndexOrThrow9 = a2.getColumnIndexOrThrow("trigger_content_update_delay");
                int i6 = columnIndexOrThrow8;
                columnIndexOrThrow8 = a2.getColumnIndexOrThrow("trigger_max_content_delay");
                int i7 = columnIndexOrThrow7;
                columnIndexOrThrow7 = a2.getColumnIndexOrThrow("content_uri_triggers");
                if (a2.moveToFirst()) {
                    try {
                        String string = a2.getString(columnIndexOrThrow);
                        String string2 = a2.getString(columnIndexOrThrow3);
                        int i8 = columnIndexOrThrow6;
                        androidx.work.b bVar = new androidx.work.b();
                        bVar.a(p.c(a2.getInt(columnIndexOrThrow16)));
                        boolean z = false;
                        bVar.a(a2.getInt(columnIndexOrThrow13) != 0);
                        bVar.b(a2.getInt(columnIndexOrThrow12) != 0);
                        bVar.c(a2.getInt(columnIndexOrThrow11) != 0);
                        if (a2.getInt(columnIndexOrThrow10) != 0) {
                            z = true;
                        }
                        bVar.d(z);
                        bVar.a(a2.getLong(columnIndexOrThrow9));
                        bVar.b(a2.getLong(columnIndexOrThrow8));
                        bVar.a(p.a(a2.getBlob(columnIndexOrThrow7)));
                        jVar = new j(string, string2);
                        jVar.b = p.a(a2.getInt(columnIndexOrThrow2));
                        jVar.d = a2.getString(columnIndexOrThrow4);
                        jVar.e = d.a(a2.getBlob(columnIndexOrThrow5));
                        jVar.f = d.a(a2.getBlob(i8));
                        jVar.g = a2.getLong(i7);
                        jVar.h = a2.getLong(i6);
                        jVar.i = a2.getLong(i5);
                        jVar.k = a2.getInt(i4);
                        jVar.l = p.b(a2.getInt(i3));
                        jVar.m = a2.getLong(i2);
                        jVar.n = a2.getLong(i);
                        jVar.o = a2.getLong(columnIndexOrThrow14);
                        jVar.p = a2.getLong(columnIndexOrThrow15);
                        jVar.j = bVar;
                    } catch (Throwable th22) {
                        th = th22;
                        a = eVar;
                        a2.close();
                        a.b();
                        throw th;
                    }
                }
                jVar = null;
                a2.close();
                eVar.b();
                return jVar;
            } catch (Throwable th3) {
                th22 = th3;
                a = eVar;
                th = th22;
                a2.close();
                a.b();
                throw th;
            }
        } catch (Throwable th4) {
            th22 = th4;
            th = th22;
            a2.close();
            a.b();
            throw th;
        }
    }

    public List<a> c(String str) {
        e a = g.a("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            int columnIndexOrThrow = a2.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = a2.getColumnIndexOrThrow("state");
            List<a> arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                a aVar = new a();
                aVar.a = a2.getString(columnIndexOrThrow);
                aVar.b = p.a(a2.getInt(columnIndexOrThrow2));
                arrayList.add(aVar);
            }
            return arrayList;
        } finally {
            a2.close();
            a.b();
        }
    }

    public State f(String str) {
        e a = g.a("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            State a3 = a2.moveToFirst() ? p.a(a2.getInt(0)) : null;
            a2.close();
            a.b();
            return a3;
        } catch (Throwable th) {
            a2.close();
            a.b();
        }
    }

    public List<d> g(String str) {
        e a = g.a("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            List<d> arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(d.a(a2.getBlob(0)));
            }
            return arrayList;
        } finally {
            a2.close();
            a.b();
        }
    }

    public List<String> h(String str) {
        e a = g.a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            a.a(1);
        } else {
            a.a(1, str);
        }
        Cursor a2 = this.a.a(a);
        try {
            List<String> arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.b();
        }
    }

    public List<String> a() {
        e a = g.a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        Cursor a2 = this.a.a(a);
        try {
            List<String> arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.b();
        }
    }

    public List<j> a(int i) {
        Throwable th;
        Throwable th2;
        g a = g.a("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        a.a(1, (long) i);
        Cursor a2 = this.a.a((e) a);
        try {
            int columnIndexOrThrow = a2.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = a2.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = a2.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = a2.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = a2.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = a2.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = a2.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = a2.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = a2.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = a2.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = a2.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = a2.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = a2.getColumnIndexOrThrow("period_start_time");
            e eVar = a;
            try {
                ArrayList arrayList;
                int columnIndexOrThrow14 = a2.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = a2.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = a2.getColumnIndexOrThrow("required_network_type");
                int i2 = columnIndexOrThrow13;
                columnIndexOrThrow13 = a2.getColumnIndexOrThrow("requires_charging");
                int i3 = columnIndexOrThrow12;
                columnIndexOrThrow12 = a2.getColumnIndexOrThrow("requires_device_idle");
                int i4 = columnIndexOrThrow11;
                columnIndexOrThrow11 = a2.getColumnIndexOrThrow("requires_battery_not_low");
                int i5 = columnIndexOrThrow10;
                columnIndexOrThrow10 = a2.getColumnIndexOrThrow("requires_storage_not_low");
                int i6 = columnIndexOrThrow9;
                columnIndexOrThrow9 = a2.getColumnIndexOrThrow("trigger_content_update_delay");
                int i7 = columnIndexOrThrow8;
                columnIndexOrThrow8 = a2.getColumnIndexOrThrow("trigger_max_content_delay");
                int i8 = columnIndexOrThrow7;
                columnIndexOrThrow7 = a2.getColumnIndexOrThrow("content_uri_triggers");
                int i9 = columnIndexOrThrow6;
                int i10 = columnIndexOrThrow5;
                ArrayList arrayList2 = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    try {
                        String string = a2.getString(columnIndexOrThrow);
                        int i11 = columnIndexOrThrow;
                        String string2 = a2.getString(columnIndexOrThrow3);
                        int i12 = columnIndexOrThrow3;
                        androidx.work.b bVar = new androidx.work.b();
                        ArrayList arrayList3 = arrayList2;
                        bVar.a(p.c(a2.getInt(columnIndexOrThrow16)));
                        bVar.a(a2.getInt(columnIndexOrThrow13) != 0);
                        bVar.b(a2.getInt(columnIndexOrThrow12) != 0);
                        bVar.c(a2.getInt(columnIndexOrThrow11) != 0);
                        bVar.d(a2.getInt(columnIndexOrThrow10) != 0);
                        int i13 = columnIndexOrThrow16;
                        int i14 = columnIndexOrThrow12;
                        bVar.a(a2.getLong(columnIndexOrThrow9));
                        bVar.b(a2.getLong(columnIndexOrThrow8));
                        bVar.a(p.a(a2.getBlob(columnIndexOrThrow7)));
                        j jVar = new j(string, string2);
                        jVar.b = p.a(a2.getInt(columnIndexOrThrow2));
                        jVar.d = a2.getString(columnIndexOrThrow4);
                        columnIndexOrThrow12 = i10;
                        jVar.e = d.a(a2.getBlob(columnIndexOrThrow12));
                        columnIndexOrThrow = i9;
                        jVar.f = d.a(a2.getBlob(columnIndexOrThrow));
                        int i15 = columnIndexOrThrow12;
                        int i16 = columnIndexOrThrow13;
                        columnIndexOrThrow5 = i8;
                        jVar.g = a2.getLong(columnIndexOrThrow5);
                        int i17 = columnIndexOrThrow;
                        int i18 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i7;
                        jVar.h = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = i6;
                        jVar.i = a2.getLong(columnIndexOrThrow13);
                        columnIndexOrThrow = i5;
                        jVar.k = a2.getInt(columnIndexOrThrow);
                        columnIndexOrThrow2 = i4;
                        jVar.l = p.b(a2.getInt(columnIndexOrThrow2));
                        int i19 = columnIndexOrThrow12;
                        int i20 = columnIndexOrThrow13;
                        columnIndexOrThrow6 = i3;
                        jVar.m = a2.getLong(columnIndexOrThrow6);
                        int i21 = columnIndexOrThrow;
                        int i22 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i2;
                        jVar.n = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = columnIndexOrThrow14;
                        jVar.o = a2.getLong(columnIndexOrThrow13);
                        int i23 = columnIndexOrThrow12;
                        int i24 = columnIndexOrThrow13;
                        columnIndexOrThrow = columnIndexOrThrow15;
                        jVar.p = a2.getLong(columnIndexOrThrow);
                        jVar.j = bVar;
                        arrayList = arrayList3;
                        arrayList.add(jVar);
                        columnIndexOrThrow15 = columnIndexOrThrow;
                        i8 = columnIndexOrThrow5;
                        i3 = columnIndexOrThrow6;
                        columnIndexOrThrow = i11;
                        columnIndexOrThrow3 = i12;
                        columnIndexOrThrow16 = i13;
                        columnIndexOrThrow13 = i16;
                        i10 = i15;
                        columnIndexOrThrow2 = i18;
                        i9 = i17;
                        i7 = i19;
                        i6 = i20;
                        i5 = i21;
                        i4 = i22;
                        i2 = i23;
                        columnIndexOrThrow14 = i24;
                        arrayList2 = arrayList;
                        columnIndexOrThrow12 = i14;
                    } catch (Throwable th22) {
                        th = th22;
                        a = eVar;
                        a2.close();
                        a.b();
                        throw th;
                    }
                }
                arrayList = arrayList2;
                a2.close();
                eVar.b();
                return arrayList;
            } catch (Throwable th3) {
                th22 = th3;
                a = eVar;
                th = th22;
                a2.close();
                a.b();
                throw th;
            }
        } catch (Throwable th4) {
            th22 = th4;
            th = th22;
            a2.close();
            a.b();
            throw th;
        }
    }

    public List<j> c() {
        Throwable th;
        Throwable th2;
        g a = g.a("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        Cursor a2 = this.a.a((e) a);
        try {
            int columnIndexOrThrow = a2.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = a2.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = a2.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = a2.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = a2.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = a2.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = a2.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = a2.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = a2.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = a2.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = a2.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = a2.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = a2.getColumnIndexOrThrow("period_start_time");
            e eVar = a;
            try {
                ArrayList arrayList;
                int columnIndexOrThrow14 = a2.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = a2.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = a2.getColumnIndexOrThrow("required_network_type");
                int i = columnIndexOrThrow13;
                columnIndexOrThrow13 = a2.getColumnIndexOrThrow("requires_charging");
                int i2 = columnIndexOrThrow12;
                columnIndexOrThrow12 = a2.getColumnIndexOrThrow("requires_device_idle");
                int i3 = columnIndexOrThrow11;
                columnIndexOrThrow11 = a2.getColumnIndexOrThrow("requires_battery_not_low");
                int i4 = columnIndexOrThrow10;
                columnIndexOrThrow10 = a2.getColumnIndexOrThrow("requires_storage_not_low");
                int i5 = columnIndexOrThrow9;
                columnIndexOrThrow9 = a2.getColumnIndexOrThrow("trigger_content_update_delay");
                int i6 = columnIndexOrThrow8;
                columnIndexOrThrow8 = a2.getColumnIndexOrThrow("trigger_max_content_delay");
                int i7 = columnIndexOrThrow7;
                columnIndexOrThrow7 = a2.getColumnIndexOrThrow("content_uri_triggers");
                int i8 = columnIndexOrThrow6;
                int i9 = columnIndexOrThrow5;
                ArrayList arrayList2 = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    try {
                        String string = a2.getString(columnIndexOrThrow);
                        int i10 = columnIndexOrThrow;
                        String string2 = a2.getString(columnIndexOrThrow3);
                        int i11 = columnIndexOrThrow3;
                        androidx.work.b bVar = new androidx.work.b();
                        ArrayList arrayList3 = arrayList2;
                        bVar.a(p.c(a2.getInt(columnIndexOrThrow16)));
                        bVar.a(a2.getInt(columnIndexOrThrow13) != 0);
                        bVar.b(a2.getInt(columnIndexOrThrow12) != 0);
                        bVar.c(a2.getInt(columnIndexOrThrow11) != 0);
                        bVar.d(a2.getInt(columnIndexOrThrow10) != 0);
                        int i12 = columnIndexOrThrow16;
                        int i13 = columnIndexOrThrow12;
                        bVar.a(a2.getLong(columnIndexOrThrow9));
                        bVar.b(a2.getLong(columnIndexOrThrow8));
                        bVar.a(p.a(a2.getBlob(columnIndexOrThrow7)));
                        j jVar = new j(string, string2);
                        jVar.b = p.a(a2.getInt(columnIndexOrThrow2));
                        jVar.d = a2.getString(columnIndexOrThrow4);
                        columnIndexOrThrow12 = i9;
                        jVar.e = d.a(a2.getBlob(columnIndexOrThrow12));
                        columnIndexOrThrow = i8;
                        jVar.f = d.a(a2.getBlob(columnIndexOrThrow));
                        int i14 = columnIndexOrThrow12;
                        int i15 = columnIndexOrThrow13;
                        columnIndexOrThrow5 = i7;
                        jVar.g = a2.getLong(columnIndexOrThrow5);
                        int i16 = columnIndexOrThrow;
                        int i17 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i6;
                        jVar.h = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = i5;
                        jVar.i = a2.getLong(columnIndexOrThrow13);
                        columnIndexOrThrow = i4;
                        jVar.k = a2.getInt(columnIndexOrThrow);
                        columnIndexOrThrow2 = i3;
                        jVar.l = p.b(a2.getInt(columnIndexOrThrow2));
                        int i18 = columnIndexOrThrow12;
                        int i19 = columnIndexOrThrow13;
                        columnIndexOrThrow6 = i2;
                        jVar.m = a2.getLong(columnIndexOrThrow6);
                        int i20 = columnIndexOrThrow;
                        int i21 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i;
                        jVar.n = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = columnIndexOrThrow14;
                        jVar.o = a2.getLong(columnIndexOrThrow13);
                        int i22 = columnIndexOrThrow12;
                        int i23 = columnIndexOrThrow13;
                        columnIndexOrThrow = columnIndexOrThrow15;
                        jVar.p = a2.getLong(columnIndexOrThrow);
                        jVar.j = bVar;
                        arrayList = arrayList3;
                        arrayList.add(jVar);
                        columnIndexOrThrow15 = columnIndexOrThrow;
                        i7 = columnIndexOrThrow5;
                        i2 = columnIndexOrThrow6;
                        columnIndexOrThrow = i10;
                        columnIndexOrThrow3 = i11;
                        columnIndexOrThrow16 = i12;
                        columnIndexOrThrow13 = i15;
                        i9 = i14;
                        columnIndexOrThrow2 = i17;
                        i8 = i16;
                        i6 = i18;
                        i5 = i19;
                        i4 = i20;
                        i3 = i21;
                        i = i22;
                        columnIndexOrThrow14 = i23;
                        arrayList2 = arrayList;
                        columnIndexOrThrow12 = i13;
                    } catch (Throwable th22) {
                        th = th22;
                        a = eVar;
                        a2.close();
                        a.b();
                        throw th;
                    }
                }
                arrayList = arrayList2;
                a2.close();
                eVar.b();
                return arrayList;
            } catch (Throwable th3) {
                th22 = th3;
                a = eVar;
                th = th22;
                a2.close();
                a.b();
                throw th;
            }
        } catch (Throwable th4) {
            th22 = th4;
            th = th22;
            a2.close();
            a.b();
            throw th;
        }
    }

    public List<j> d() {
        Throwable th;
        Throwable th2;
        g a = g.a("SELECT * FROM workspec WHERE state=0", 0);
        Cursor a2 = this.a.a((e) a);
        try {
            int columnIndexOrThrow = a2.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = a2.getColumnIndexOrThrow("state");
            int columnIndexOrThrow3 = a2.getColumnIndexOrThrow("worker_class_name");
            int columnIndexOrThrow4 = a2.getColumnIndexOrThrow("input_merger_class_name");
            int columnIndexOrThrow5 = a2.getColumnIndexOrThrow("input");
            int columnIndexOrThrow6 = a2.getColumnIndexOrThrow("output");
            int columnIndexOrThrow7 = a2.getColumnIndexOrThrow("initial_delay");
            int columnIndexOrThrow8 = a2.getColumnIndexOrThrow("interval_duration");
            int columnIndexOrThrow9 = a2.getColumnIndexOrThrow("flex_duration");
            int columnIndexOrThrow10 = a2.getColumnIndexOrThrow("run_attempt_count");
            int columnIndexOrThrow11 = a2.getColumnIndexOrThrow("backoff_policy");
            int columnIndexOrThrow12 = a2.getColumnIndexOrThrow("backoff_delay_duration");
            int columnIndexOrThrow13 = a2.getColumnIndexOrThrow("period_start_time");
            e eVar = a;
            try {
                ArrayList arrayList;
                int columnIndexOrThrow14 = a2.getColumnIndexOrThrow("minimum_retention_duration");
                int columnIndexOrThrow15 = a2.getColumnIndexOrThrow("schedule_requested_at");
                int columnIndexOrThrow16 = a2.getColumnIndexOrThrow("required_network_type");
                int i = columnIndexOrThrow13;
                columnIndexOrThrow13 = a2.getColumnIndexOrThrow("requires_charging");
                int i2 = columnIndexOrThrow12;
                columnIndexOrThrow12 = a2.getColumnIndexOrThrow("requires_device_idle");
                int i3 = columnIndexOrThrow11;
                columnIndexOrThrow11 = a2.getColumnIndexOrThrow("requires_battery_not_low");
                int i4 = columnIndexOrThrow10;
                columnIndexOrThrow10 = a2.getColumnIndexOrThrow("requires_storage_not_low");
                int i5 = columnIndexOrThrow9;
                columnIndexOrThrow9 = a2.getColumnIndexOrThrow("trigger_content_update_delay");
                int i6 = columnIndexOrThrow8;
                columnIndexOrThrow8 = a2.getColumnIndexOrThrow("trigger_max_content_delay");
                int i7 = columnIndexOrThrow7;
                columnIndexOrThrow7 = a2.getColumnIndexOrThrow("content_uri_triggers");
                int i8 = columnIndexOrThrow6;
                int i9 = columnIndexOrThrow5;
                ArrayList arrayList2 = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    try {
                        String string = a2.getString(columnIndexOrThrow);
                        int i10 = columnIndexOrThrow;
                        String string2 = a2.getString(columnIndexOrThrow3);
                        int i11 = columnIndexOrThrow3;
                        androidx.work.b bVar = new androidx.work.b();
                        ArrayList arrayList3 = arrayList2;
                        bVar.a(p.c(a2.getInt(columnIndexOrThrow16)));
                        bVar.a(a2.getInt(columnIndexOrThrow13) != 0);
                        bVar.b(a2.getInt(columnIndexOrThrow12) != 0);
                        bVar.c(a2.getInt(columnIndexOrThrow11) != 0);
                        bVar.d(a2.getInt(columnIndexOrThrow10) != 0);
                        int i12 = columnIndexOrThrow16;
                        int i13 = columnIndexOrThrow12;
                        bVar.a(a2.getLong(columnIndexOrThrow9));
                        bVar.b(a2.getLong(columnIndexOrThrow8));
                        bVar.a(p.a(a2.getBlob(columnIndexOrThrow7)));
                        j jVar = new j(string, string2);
                        jVar.b = p.a(a2.getInt(columnIndexOrThrow2));
                        jVar.d = a2.getString(columnIndexOrThrow4);
                        columnIndexOrThrow12 = i9;
                        jVar.e = d.a(a2.getBlob(columnIndexOrThrow12));
                        columnIndexOrThrow = i8;
                        jVar.f = d.a(a2.getBlob(columnIndexOrThrow));
                        int i14 = columnIndexOrThrow12;
                        int i15 = columnIndexOrThrow13;
                        columnIndexOrThrow5 = i7;
                        jVar.g = a2.getLong(columnIndexOrThrow5);
                        int i16 = columnIndexOrThrow;
                        int i17 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i6;
                        jVar.h = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = i5;
                        jVar.i = a2.getLong(columnIndexOrThrow13);
                        columnIndexOrThrow = i4;
                        jVar.k = a2.getInt(columnIndexOrThrow);
                        columnIndexOrThrow2 = i3;
                        jVar.l = p.b(a2.getInt(columnIndexOrThrow2));
                        int i18 = columnIndexOrThrow12;
                        int i19 = columnIndexOrThrow13;
                        columnIndexOrThrow6 = i2;
                        jVar.m = a2.getLong(columnIndexOrThrow6);
                        int i20 = columnIndexOrThrow;
                        int i21 = columnIndexOrThrow2;
                        columnIndexOrThrow12 = i;
                        jVar.n = a2.getLong(columnIndexOrThrow12);
                        columnIndexOrThrow13 = columnIndexOrThrow14;
                        jVar.o = a2.getLong(columnIndexOrThrow13);
                        int i22 = columnIndexOrThrow12;
                        int i23 = columnIndexOrThrow13;
                        columnIndexOrThrow = columnIndexOrThrow15;
                        jVar.p = a2.getLong(columnIndexOrThrow);
                        jVar.j = bVar;
                        arrayList = arrayList3;
                        arrayList.add(jVar);
                        columnIndexOrThrow15 = columnIndexOrThrow;
                        i7 = columnIndexOrThrow5;
                        i2 = columnIndexOrThrow6;
                        columnIndexOrThrow = i10;
                        columnIndexOrThrow3 = i11;
                        columnIndexOrThrow16 = i12;
                        columnIndexOrThrow13 = i15;
                        i9 = i14;
                        columnIndexOrThrow2 = i17;
                        i8 = i16;
                        i6 = i18;
                        i5 = i19;
                        i4 = i20;
                        i3 = i21;
                        i = i22;
                        columnIndexOrThrow14 = i23;
                        arrayList2 = arrayList;
                        columnIndexOrThrow12 = i13;
                    } catch (Throwable th22) {
                        th = th22;
                        a = eVar;
                        a2.close();
                        a.b();
                        throw th;
                    }
                }
                arrayList = arrayList2;
                a2.close();
                eVar.b();
                return arrayList;
            } catch (Throwable th3) {
                th22 = th3;
                a = eVar;
                th = th22;
                a2.close();
                a.b();
                throw th;
            }
        } catch (Throwable th4) {
            th22 = th4;
            th = th22;
            a2.close();
            a.b();
            throw th;
        }
    }

    public int a(State state, String... strArr) {
        int a;
        StringBuilder a2 = android.arch.persistence.room.b.a.a();
        a2.append("UPDATE workspec SET state=");
        a2.append("?");
        a2.append(" WHERE id IN (");
        int i = 2;
        android.arch.persistence.room.b.a.a(a2, strArr.length);
        a2.append(")");
        f a3 = this.a.a(a2.toString());
        a3.a(1, (long) p.a(state));
        for (String str : strArr) {
            if (str == null) {
                a3.a(i);
            } else {
                a3.a(i, str);
            }
            i++;
        }
        this.a.f();
        try {
            a = a3.a();
            this.a.h();
            return a;
        } finally {
            this.a.g();
        }
    }
}
