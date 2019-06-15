package com.managers;

import com.e.a.c;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.services.h;
import com.services.l.s;

public class m extends x {
    public void a() {
    }

    public void a(URLManager uRLManager, String str, int i, int i2, String str2, String str3, s sVar) {
        final s sVar2 = sVar;
        final URLManager uRLManager2 = uRLManager;
        final String str4 = str;
        final int i3 = i;
        final int i4 = i2;
        final String str5 = str2;
        final String str6 = str3;
        h.a().a(new TaskListner() {
            s a = sVar2;
            BusinessObject b = null;
            boolean c = false;

            public void onBackGroundTaskCompleted() {
                try {
                    if (this.a != null && !this.c) {
                        this.a.onRetreivalComplete(this.b);
                    }
                } catch (Exception unused) {
                }
            }

            public void doBackGroundTask() {
                try {
                    this.b = c.a().a(uRLManager2.i(), str4, i3, i4, str5, str6);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }, -1);
    }

    public BusinessObject a(URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        return c.a().a(uRLManager.i(), str, i, i2, str2, str3);
    }
}
