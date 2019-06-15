package androidx.work.impl;

import android.arch.persistence.a.c;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.a;
import android.arch.persistence.room.b.b.d;
import android.arch.persistence.room.f;
import androidx.work.impl.b.b;
import androidx.work.impl.b.e;
import androidx.work.impl.b.h;
import androidx.work.impl.b.i;
import androidx.work.impl.b.k;
import androidx.work.impl.b.l;
import androidx.work.impl.b.n;
import androidx.work.impl.b.o;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class WorkDatabase_Impl extends WorkDatabase {
    private volatile k d;
    private volatile b e;
    private volatile n f;
    private volatile e g;
    private volatile h h;

    /* Access modifiers changed, original: protected */
    public c b(a aVar) {
        return aVar.a.a(c.b.a(aVar.b).a(aVar.c).a(new f(aVar, new f.a(5) {
            public void b(android.arch.persistence.a.b bVar) {
                bVar.c("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.c("CREATE  INDEX `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                bVar.c("CREATE  INDEX `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                bVar.c("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
                bVar.c("CREATE  INDEX `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                bVar.c("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.c("CREATE  INDEX `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                bVar.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.c("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                bVar.c("CREATE  INDEX `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                bVar.c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                bVar.c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c84d23ade98552f1cec71088c1f0794c\")");
            }

            public void a(android.arch.persistence.a.b bVar) {
                bVar.c("DROP TABLE IF EXISTS `Dependency`");
                bVar.c("DROP TABLE IF EXISTS `WorkSpec`");
                bVar.c("DROP TABLE IF EXISTS `WorkTag`");
                bVar.c("DROP TABLE IF EXISTS `SystemIdInfo`");
                bVar.c("DROP TABLE IF EXISTS `WorkName`");
            }

            /* Access modifiers changed, original: protected */
            public void d(android.arch.persistence.a.b bVar) {
                if (WorkDatabase_Impl.this.c != null) {
                    int size = WorkDatabase_Impl.this.c.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) WorkDatabase_Impl.this.c.get(i)).a(bVar);
                    }
                }
            }

            public void c(android.arch.persistence.a.b bVar) {
                WorkDatabase_Impl.this.a = bVar;
                bVar.c("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.this.a(bVar);
                if (WorkDatabase_Impl.this.c != null) {
                    int size = WorkDatabase_Impl.this.c.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.b) WorkDatabase_Impl.this.c.get(i)).b(bVar);
                    }
                }
            }

            /* Access modifiers changed, original: protected */
            public void e(android.arch.persistence.a.b bVar) {
                android.arch.persistence.a.b bVar2 = bVar;
                HashMap hashMap = new HashMap(2);
                hashMap.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 1));
                hashMap.put("prerequisite_id", new android.arch.persistence.room.b.b.a("prerequisite_id", "TEXT", true, 2));
                HashSet hashSet = new HashSet(2);
                hashSet.add(new android.arch.persistence.room.b.b.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                hashSet.add(new android.arch.persistence.room.b.b.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"})));
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new d("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                hashSet2.add(new d("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"})));
                android.arch.persistence.room.b.b bVar3 = new android.arch.persistence.room.b.b("Dependency", hashMap, hashSet, hashSet2);
                android.arch.persistence.room.b.b a = android.arch.persistence.room.b.b.a(bVar2, "Dependency");
                StringBuilder stringBuilder;
                if (bVar3.equals(a)) {
                    hashMap = new HashMap(23);
                    hashMap.put("id", new android.arch.persistence.room.b.b.a("id", "TEXT", true, 1));
                    hashMap.put("state", new android.arch.persistence.room.b.b.a("state", "INTEGER", true, 0));
                    hashMap.put("worker_class_name", new android.arch.persistence.room.b.b.a("worker_class_name", "TEXT", true, 0));
                    hashMap.put("input_merger_class_name", new android.arch.persistence.room.b.b.a("input_merger_class_name", "TEXT", false, 0));
                    hashMap.put("input", new android.arch.persistence.room.b.b.a("input", "BLOB", true, 0));
                    hashMap.put("output", new android.arch.persistence.room.b.b.a("output", "BLOB", true, 0));
                    hashMap.put("initial_delay", new android.arch.persistence.room.b.b.a("initial_delay", "INTEGER", true, 0));
                    hashMap.put("interval_duration", new android.arch.persistence.room.b.b.a("interval_duration", "INTEGER", true, 0));
                    hashMap.put("flex_duration", new android.arch.persistence.room.b.b.a("flex_duration", "INTEGER", true, 0));
                    hashMap.put("run_attempt_count", new android.arch.persistence.room.b.b.a("run_attempt_count", "INTEGER", true, 0));
                    hashMap.put("backoff_policy", new android.arch.persistence.room.b.b.a("backoff_policy", "INTEGER", true, 0));
                    hashMap.put("backoff_delay_duration", new android.arch.persistence.room.b.b.a("backoff_delay_duration", "INTEGER", true, 0));
                    hashMap.put("period_start_time", new android.arch.persistence.room.b.b.a("period_start_time", "INTEGER", true, 0));
                    hashMap.put("minimum_retention_duration", new android.arch.persistence.room.b.b.a("minimum_retention_duration", "INTEGER", true, 0));
                    hashMap.put("schedule_requested_at", new android.arch.persistence.room.b.b.a("schedule_requested_at", "INTEGER", true, 0));
                    hashMap.put("required_network_type", new android.arch.persistence.room.b.b.a("required_network_type", "INTEGER", false, 0));
                    hashMap.put("requires_charging", new android.arch.persistence.room.b.b.a("requires_charging", "INTEGER", true, 0));
                    hashMap.put("requires_device_idle", new android.arch.persistence.room.b.b.a("requires_device_idle", "INTEGER", true, 0));
                    hashMap.put("requires_battery_not_low", new android.arch.persistence.room.b.b.a("requires_battery_not_low", "INTEGER", true, 0));
                    hashMap.put("requires_storage_not_low", new android.arch.persistence.room.b.b.a("requires_storage_not_low", "INTEGER", true, 0));
                    hashMap.put("trigger_content_update_delay", new android.arch.persistence.room.b.b.a("trigger_content_update_delay", "INTEGER", true, 0));
                    hashMap.put("trigger_max_content_delay", new android.arch.persistence.room.b.b.a("trigger_max_content_delay", "INTEGER", true, 0));
                    hashMap.put("content_uri_triggers", new android.arch.persistence.room.b.b.a("content_uri_triggers", "BLOB", false, 0));
                    hashSet = new HashSet(0);
                    hashSet2 = new HashSet(1);
                    hashSet2.add(new d("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"})));
                    bVar3 = new android.arch.persistence.room.b.b("WorkSpec", hashMap, hashSet, hashSet2);
                    a = android.arch.persistence.room.b.b.a(bVar2, "WorkSpec");
                    if (bVar3.equals(a)) {
                        hashMap = new HashMap(2);
                        hashMap.put("tag", new android.arch.persistence.room.b.b.a("tag", "TEXT", true, 1));
                        hashMap.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 2));
                        hashSet = new HashSet(1);
                        hashSet.add(new android.arch.persistence.room.b.b.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                        hashSet2 = new HashSet(1);
                        hashSet2.add(new d("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                        bVar3 = new android.arch.persistence.room.b.b("WorkTag", hashMap, hashSet, hashSet2);
                        a = android.arch.persistence.room.b.b.a(bVar2, "WorkTag");
                        if (bVar3.equals(a)) {
                            hashMap = new HashMap(2);
                            hashMap.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 1));
                            hashMap.put("system_id", new android.arch.persistence.room.b.b.a("system_id", "INTEGER", true, 0));
                            hashSet = new HashSet(1);
                            hashSet.add(new android.arch.persistence.room.b.b.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                            bVar3 = new android.arch.persistence.room.b.b("SystemIdInfo", hashMap, hashSet, new HashSet(0));
                            a = android.arch.persistence.room.b.b.a(bVar2, "SystemIdInfo");
                            if (bVar3.equals(a)) {
                                hashMap = new HashMap(2);
                                hashMap.put("name", new android.arch.persistence.room.b.b.a("name", "TEXT", true, 1));
                                hashMap.put("work_spec_id", new android.arch.persistence.room.b.b.a("work_spec_id", "TEXT", true, 2));
                                HashSet hashSet3 = new HashSet(1);
                                hashSet3.add(new android.arch.persistence.room.b.b.b("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                                hashSet = new HashSet(1);
                                hashSet.add(new d("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
                                android.arch.persistence.room.b.b bVar4 = new android.arch.persistence.room.b.b("WorkName", hashMap, hashSet3, hashSet);
                                android.arch.persistence.room.b.b a2 = android.arch.persistence.room.b.b.a(bVar2, "WorkName");
                                if (!bVar4.equals(a2)) {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("Migration didn't properly handle WorkName(androidx.work.impl.model.WorkName).\n Expected:\n");
                                    stringBuilder.append(bVar4);
                                    stringBuilder.append("\n Found:\n");
                                    stringBuilder.append(a2);
                                    throw new IllegalStateException(stringBuilder.toString());
                                }
                                return;
                            }
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Migration didn't properly handle SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n");
                            stringBuilder.append(bVar3);
                            stringBuilder.append("\n Found:\n");
                            stringBuilder.append(a);
                            throw new IllegalStateException(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Migration didn't properly handle WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n");
                        stringBuilder.append(bVar3);
                        stringBuilder.append("\n Found:\n");
                        stringBuilder.append(a);
                        throw new IllegalStateException(stringBuilder.toString());
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Migration didn't properly handle WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n");
                    stringBuilder.append(bVar3);
                    stringBuilder.append("\n Found:\n");
                    stringBuilder.append(a);
                    throw new IllegalStateException(stringBuilder.toString());
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("Migration didn't properly handle Dependency(androidx.work.impl.model.Dependency).\n Expected:\n");
                stringBuilder.append(bVar3);
                stringBuilder.append("\n Found:\n");
                stringBuilder.append(a);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }, "c84d23ade98552f1cec71088c1f0794c", "1db8206f0da6aa81bbdd2d99a82d9e14")).a());
    }

    /* Access modifiers changed, original: protected */
    public android.arch.persistence.room.c c() {
        return new android.arch.persistence.room.c(this, "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName");
    }

    public k m() {
        if (this.d != null) {
            return this.d;
        }
        k kVar;
        synchronized (this) {
            if (this.d == null) {
                this.d = new l(this);
            }
            kVar = this.d;
        }
        return kVar;
    }

    public b n() {
        if (this.e != null) {
            return this.e;
        }
        b bVar;
        synchronized (this) {
            if (this.e == null) {
                this.e = new androidx.work.impl.b.c(this);
            }
            bVar = this.e;
        }
        return bVar;
    }

    public n o() {
        if (this.f != null) {
            return this.f;
        }
        n nVar;
        synchronized (this) {
            if (this.f == null) {
                this.f = new o(this);
            }
            nVar = this.f;
        }
        return nVar;
    }

    public e p() {
        if (this.g != null) {
            return this.g;
        }
        e eVar;
        synchronized (this) {
            if (this.g == null) {
                this.g = new androidx.work.impl.b.f(this);
            }
            eVar = this.g;
        }
        return eVar;
    }

    public h q() {
        if (this.h != null) {
            return this.h;
        }
        h hVar;
        synchronized (this) {
            if (this.h == null) {
                this.h = new i(this);
            }
            hVar = this.h;
        }
        return hVar;
    }
}
