package com.dynamicview;

import com.dynamicview.f.a;
import com.gaana.models.BusinessObject;
import com.managers.ColombiaManager.ADSTATUS;
import com.services.l.s;
import com.services.l.w;
import com.til.colombia.android.service.Item;

public class b implements s {
    private a a;
    private int b;
    private w c;
    private boolean d;
    private BusinessObject e;
    private boolean f = true;
    private long g = 0;
    private boolean h = false;
    private ADSTATUS i;
    private Item j;

    b() {
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean a() {
        return this.f;
    }

    public void a(long j) {
        this.g = j;
    }

    public long b() {
        return this.g;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public a c() {
        return this.a;
    }

    public void a(w wVar) {
        this.c = wVar;
    }

    public boolean d() {
        return this.h;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public ADSTATUS e() {
        return this.i;
    }

    public void a(ADSTATUS adstatus) {
        this.i = adstatus;
    }

    public Item f() {
        return this.j;
    }

    public void a(Item item) {
        this.j = item;
    }

    public void c(boolean z) {
        this.d = z;
    }

    public boolean g() {
        return this.d;
    }

    public BusinessObject h() {
        return this.e;
    }

    public void i() {
        if (this.c != null) {
            this.c.a(this.e, this.a, this.b);
        }
    }

    public void onRetreivalComplete(BusinessObject businessObject) {
        this.e = businessObject;
        if (this.c != null) {
            this.c.a(businessObject, this.a, this.b);
        }
    }

    public void onErrorResponse(BusinessObject businessObject) {
        if (this.c != null) {
            this.c.b(businessObject, this.a, this.b);
        }
    }
}
