package com.managers;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.e.a.e.c;
import com.gaana.models.BusinessObject;
import com.i.d;
import com.managers.URLManager.BusinessObjectType;
import com.services.l.s;
import java.util.ArrayList;

public class z extends x {
    private ArrayList<BusinessObject> a = new ArrayList();
    private ArrayList<BusinessObject> b = new ArrayList();
    private BusinessObject c = new BusinessObject();
    private int d = 0;
    private int e = 0;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private int i;
    private int j;
    private String k;

    public void a(URLManager uRLManager, String str, int i, int i2, String str2, String str3, s sVar) {
        if (!this.h) {
            final String str4 = str;
            final int i3 = i;
            final int i4 = i2;
            final URLManager uRLManager2 = uRLManager;
            final String str5 = str2;
            final String str6 = str3;
            final s sVar2 = sVar;
            d.a(new Runnable() {
                public void run() {
                    if (!z.this.h) {
                        z.this.h = true;
                        z.this.k = str4;
                        z.this.i = i3;
                        z.this.j = i4;
                        if (!TextUtils.isEmpty(str4) && i3 == 0) {
                            z.this.a();
                        }
                        switch (AnonymousClass2.a[uRLManager2.i().ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                                z.this.c = z.this.b(uRLManager2, str4, i3, i4, str5, str6);
                                break;
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                z.this.h = false;
                                if (z.this.c != null && sVar2 != null) {
                                    sVar2.onRetreivalComplete(z.this.c);
                                } else if (sVar2 != null) {
                                    sVar2.onErrorResponse(null);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public BusinessObject a(URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        this.k = str;
        this.i = i;
        this.j = i2;
        if (!TextUtils.isEmpty(str) && i == 0) {
            a();
        }
        switch (uRLManager.i()) {
            case Tracks:
            case Playlists:
            case Albums:
                this.c = b(uRLManager, str, i, i2, str2, str3);
                break;
            case Artists:
                this.c = c(uRLManager, str, i, i2, str2, str3);
                break;
        }
        return this.c;
    }

    public BusinessObject b(URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        if (i2 == -1) {
            i2 = 20;
        }
        if (!this.f && a(this.b, this.d, i2)) {
            i = c.c;
            if (uRLManager.i() == BusinessObjectType.Albums) {
                i = c.a;
            } else if (uRLManager.i() == BusinessObjectType.Playlists) {
                i = c.b;
            }
            BusinessObject a = DownloadManager.c().a(str, i, false, true, -1, this.b.size(), i2);
            if (!(a == null || a.getArrListBusinessObj() == null)) {
                if (a.getArrListBusinessObj().size() < i2) {
                    this.f = true;
                } else {
                    this.f = false;
                }
                this.b.addAll(a.getArrListBusinessObj());
            }
        }
        if (!this.g && a(this.a, this.e, i2)) {
            BusinessObject a2 = com.e.a.c.a().a(uRLManager.i(), str, this.a.size(), i2, "name", "ASC");
            if (!(a2 == null || a2.getArrListBusinessObj() == null)) {
                if (a2.getArrListBusinessObj().size() < i2) {
                    this.g = true;
                } else {
                    this.g = false;
                }
                this.a.addAll(a2.getArrListBusinessObj());
            }
        }
        return a(uRLManager.i(), i2);
    }

    public BusinessObject c(URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        if (i2 == -1) {
            i2 = 20;
        }
        if (!this.f && a(this.b, this.d, i2)) {
            BusinessObject a = DownloadManager.c().a(uRLManager.q(), i, i2);
            if (!(a == null || a.getArrListBusinessObj() == null)) {
                if (a.getArrListBusinessObj().size() < i2) {
                    this.f = true;
                } else {
                    this.f = false;
                }
                this.b.addAll(a.getArrListBusinessObj());
            }
        }
        if (!this.g && a(this.a, this.e, i2)) {
            if (this.a.size() > 0) {
                i = this.a.size() + i2;
            }
            BusinessObject a2 = com.e.a.c.a().a(uRLManager.q(), i, i2);
            if (!(a2 == null || a2.getArrListBusinessObj() == null)) {
                if (a2.getArrListBusinessObj().size() < i2) {
                    this.g = true;
                } else {
                    this.g = false;
                }
                this.a.addAll(a2.getArrListBusinessObj());
            }
        }
        return a(BusinessObjectType.Artists, i2);
    }

    @NonNull
    private BusinessObject a(BusinessObjectType businessObjectType, int i) {
        ArrayList arrayList = new ArrayList();
        while (this.d < this.b.size() && this.e < this.a.size() && arrayList.size() < i) {
            BusinessObject businessObject = (BusinessObject) this.b.get(this.d);
            BusinessObject businessObject2 = (BusinessObject) this.a.get(this.e);
            int compareTo = businessObject.getNameAndId().compareTo(businessObject2.getNameAndId());
            if (compareTo == 0) {
                arrayList.add(businessObject2);
                this.d++;
                this.e++;
            } else if (compareTo > 0) {
                this.e++;
                arrayList.add(businessObject2);
            } else {
                this.d++;
                arrayList.add(businessObject);
            }
        }
        while (arrayList.size() < i && this.e < this.a.size()) {
            arrayList.add(this.a.get(this.e));
            this.e++;
        }
        while (arrayList.size() < i && this.d < this.b.size()) {
            arrayList.add(this.b.get(this.d));
            this.d++;
        }
        BusinessObject businessObject3 = new BusinessObject();
        businessObject3.setBusinessObjType(businessObjectType);
        if (arrayList.size() > 0) {
            businessObject3.setCount(String.valueOf(arrayList.size()));
            businessObject3.setArrListBusinessObj(arrayList);
        } else {
            businessObject3.setVolleyError(new VolleyError("No matching data found in db. Probably reached endLimit of data"));
        }
        return businessObject3;
    }

    private boolean a(ArrayList<BusinessObject> arrayList, int i, int i2) {
        return arrayList.size() - i < i2;
    }

    public void a() {
        this.d = 0;
        this.e = 0;
        this.a.clear();
        this.b.clear();
        this.g = false;
        this.f = false;
        this.h = false;
    }
}
