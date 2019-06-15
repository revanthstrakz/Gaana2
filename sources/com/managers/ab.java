package com.managers;

import com.gaana.application.GaanaApplication;
import com.gaana.models.Notifications;
import com.gaana.models.Notifications.Notification;
import com.services.d;
import com.services.n;
import java.util.ArrayList;
import java.util.Iterator;

public class ab {
    private static ab a;
    private static GaanaApplication b;
    private d c;
    private boolean d;
    private Notifications e;
    private c f;
    private b g;
    private a h;

    public interface a {
        void onAllOffersSeen();
    }

    public interface b {
        void b(Notification notification);
    }

    public interface c {
        void a(int i);
    }

    private ab() {
        this.c = null;
        this.d = true;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.c = d.a();
        b = GaanaApplication.getInstance();
    }

    public static ab a() {
        if (a == null) {
            a = new ab();
        }
        return a;
    }

    public Notifications b() {
        return this.e;
    }

    public void a(b bVar) {
        this.g = bVar;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public void a(boolean z) {
        this.d = z;
    }

    private void g() {
        this.c.a("PREFERENCE_SAVED_OFFER_PUSH_NOTIFICATIONS", n.a(this.e.getArrListBusinessObj()), false);
    }

    public void c() {
        if (this.e == null) {
            this.e = new Notifications();
        }
        ArrayList arrayList = (ArrayList) n.a(this.c.c("PREFERENCE_SAVED_OFFER_PUSH_NOTIFICATIONS", false));
        if (arrayList != null) {
            if (arrayList.size() >= 5) {
                arrayList.subList(5, arrayList.size()).clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.e.addNotificationToList((Notification) it.next());
            }
            h();
            g();
        }
    }

    public void a(Notification notification, boolean z) {
        if (this.e == null) {
            c();
        }
        this.e.addNotificationToList(notification);
        h();
        g();
        if (z && this.g != null) {
            this.g.b(notification);
        }
    }

    public int d() {
        return (this.e == null || this.e.getArrListBusinessObj() == null) ? 0 : this.e.getArrListBusinessObj().size();
    }

    public int e() {
        return (this.e == null || this.e.getArrListBusinessObj() == null) ? 0 : this.e.getFreshNotificationsCount();
    }

    private void h() {
        if (this.e != null) {
            int freshNotificationsCount = this.e.getFreshNotificationsCount();
            int b = this.c.b("PREFERENCE_PREVIOUS_OFFER_COUNT", 0, true);
            if (freshNotificationsCount > 0) {
                if (freshNotificationsCount != b) {
                    this.d = true;
                    this.c.a("PREFERENCE_PREVIOUS_OFFER_COUNT", freshNotificationsCount, true);
                }
                if (this.d && this.f != null) {
                    this.f.a(freshNotificationsCount);
                }
            }
        }
    }

    public void f() {
        this.e = new Notifications();
        this.c.b("PREFERENCE_SAVED_OFFER_PUSH_NOTIFICATIONS", false);
    }

    public void a(long j) {
        if (!(this.e == null || this.e.getArrListBusinessObj() == null || this.e.getArrListBusinessObj().size() <= 0)) {
            int i = 0;
            while (i < this.e.getArrListBusinessObj().size()) {
                if (((Notification) this.e.getArrListBusinessObj().get(i)).getTimeStampInMilliSeconds() != j || ((Notification) this.e.getArrListBusinessObj().get(i)).hasSeen()) {
                    i++;
                } else {
                    this.e.notifSeenAtPosition(i);
                    g();
                    if (this.h != null && this.e.getFreshNotificationsCount() == 0) {
                        this.h.onAllOffersSeen();
                    }
                    return;
                }
            }
        }
    }
}
