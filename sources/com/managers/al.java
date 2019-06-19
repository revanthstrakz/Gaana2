package com.managers;

import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import java.util.ArrayList;
import java.util.Iterator;

public class al {
    public static boolean a;
    private static al b;
    private ArrayList<BusinessObject> c;
    private boolean d;
    private boolean e;

    private al() {
        b();
    }

    public static al a() {
        if (b == null) {
            b = new al();
        }
        return b;
    }

    public void b() {
        this.c = new ArrayList();
    }

    public void a(BusinessObject businessObject, boolean z) {
        if (!c(businessObject, z)) {
            this.c.add(businessObject);
        }
    }

    public void b(BusinessObject businessObject, boolean z) {
        this.d = false;
        if (c(businessObject, z)) {
            int i = -1;
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                BusinessObject businessObject2 = (BusinessObject) it.next();
                if (businessObject2.getBusinessObjId().equals(businessObject.getBusinessObjId())) {
                    i = this.c.indexOf(businessObject2);
                }
            }
            this.c.remove(i);
        }
    }

    public void a(ArrayList<BusinessObject> arrayList) {
        this.c.clear();
        this.c.addAll(arrayList);
        this.d = true;
    }

    public void b(ArrayList<Track> arrayList) {
        this.c.clear();
        this.c.addAll(arrayList);
        this.d = true;
    }

    public void c() {
        this.c.clear();
        this.d = false;
    }

    public boolean d() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.d;
    }

    public boolean c(BusinessObject businessObject, boolean z) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((BusinessObject) it.next()).getBusinessObjId().equals(businessObject.getBusinessObjId())) {
                return true;
            }
        }
        return false;
    }

    public int f() {
        return this.c.size();
    }

    public BusinessObject g() {
        BusinessObject businessObject = new BusinessObject();
        businessObject.setArrListBusinessObj(this.c);
        return businessObject;
    }
}
