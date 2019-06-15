package androidx.work.impl;

import android.arch.persistence.a.b;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.utils.e;

@RestrictTo({Scope.LIBRARY_GROUP})
public class g {
    public static android.arch.persistence.room.a.a a = new android.arch.persistence.room.a.a(1, 2) {
        public void a(@NonNull b bVar) {
            bVar.c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            bVar.c("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
            bVar.c("DROP TABLE IF EXISTS alarmInfo");
            bVar.c("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
        }
    };
    public static android.arch.persistence.room.a.a b = new android.arch.persistence.room.a.a(3, 4) {
        public void a(@NonNull b bVar) {
            if (VERSION.SDK_INT >= 23) {
                bVar.c("UPDATE workspec SET schedule_requested_at=0 WHERE state NOT IN (2, 3, 5) AND schedule_requested_at=-1 AND interval_duration<>0");
            }
        }
    };
    public static android.arch.persistence.room.a.a c = new android.arch.persistence.room.a.a(4, 5) {
        public void a(@NonNull b bVar) {
            bVar.c("ALTER TABLE workspec ADD COLUMN `trigger_content_update_delay` INTEGER NOT NULL DEFAULT -1");
            bVar.c("ALTER TABLE workspec ADD COLUMN `trigger_max_content_delay` INTEGER NOT NULL DEFAULT -1");
        }
    };

    public static class a extends android.arch.persistence.room.a.a {
        final Context c;

        public a(@NonNull Context context, int i, int i2) {
            super(i, i2);
            this.c = context;
        }

        public void a(@NonNull b bVar) {
            new e(this.c).a(true);
        }
    }
}
