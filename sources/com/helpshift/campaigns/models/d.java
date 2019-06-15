package com.helpshift.campaigns.models;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.helpshift.campaigns.l.i;
import com.helpshift.campaigns.m.a.a.a;
import com.helpshift.k.b;
import com.helpshift.util.a.c;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.s;
import com.til.colombia.android.internal.e;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class d {
    PropertyValue[] a = new PropertyValue[13];
    protected final String[] b;
    String c;
    i d;
    c e;
    c f;

    public d(c cVar, i iVar, c cVar2) {
        r1 = new String[13];
        int i = 0;
        r1[0] = e.C;
        r1[1] = "av";
        r1[2] = "dp";
        r1[3] = "np";
        r1[4] = "dm";
        r1[5] = "cc";
        r1[6] = "ln";
        r1[7] = "ip";
        r1[8] = "tz";
        r1[9] = "ll";
        r1[10] = "ca";
        r1[11] = "pt";
        r1[12] = "sv";
        this.b = r1;
        String c = b.a().b.c();
        if (TextUtils.isEmpty(c)) {
            c = UUID.randomUUID().toString();
            b.a().b.d(c);
        }
        this.c = c;
        this.d = iVar;
        this.d.b(this.c);
        this.f = cVar;
        HashMap d = iVar.d(c);
        if (d != null) {
            while (i < 13) {
                this.a[i] = (PropertyValue) d.get(this.b[i]);
                i++;
            }
        }
        int intValue = a.d.intValue();
        if (this.a[intValue] == null) {
            this.a[intValue] = new PropertyValue("android");
            this.d.b(this.b[intValue], this.a[intValue], c);
        }
        this.e = cVar2;
        b();
    }

    public String a() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public <T> void a(Integer num, T t) {
        if (t != null) {
            PropertyValue propertyValue = this.a[num.intValue()];
            int i = 0;
            if (propertyValue != null && propertyValue.a((Object) t)) {
                i = 1;
            } else if (propertyValue == null) {
                propertyValue = new PropertyValue(t);
                i = propertyValue.b().equals("u") ^ 1;
            }
            if (i != 0) {
                this.a[num.intValue()] = propertyValue;
                this.d.b(this.b[num.intValue()], propertyValue, this.c);
                if (b(num)) {
                    b.a().b.c(Boolean.valueOf(true));
                }
            }
        }
    }

    public Object a(final Integer num) {
        final Object[] objArr = new Object[]{null};
        this.e.a(new Runnable() {
            public void run() {
                PropertyValue b = d.this.d.b(d.this.b[num.intValue()], d.this.c);
                if (b != null) {
                    objArr[0] = b.a();
                }
            }
        });
        return objArr[0];
    }

    public void b() {
        this.e.b(new Runnable() {
            public void run() {
                this.a(a.a, this.f.a());
                this.a(a.e, this.f.b());
                this.a(a.b, this.f.c());
                this.a(a.g, this.f.e());
                this.a(a.k, this.f.f());
                this.a(a.h, this.f.h());
                this.a(a.i, this.f.g());
                this.a(a.m, (Object) "6.4.0");
                Context b = o.b();
                if (com.helpshift.util.b.a(b, "android.permission.ACCESS_COARSE_LOCATION") || com.helpshift.util.b.a(b, "android.permission.ACCESS_FINE_LOCATION")) {
                    PropertyValue propertyValue = d.this.a[a.j.intValue()];
                    Location location = null;
                    if (propertyValue != null) {
                        location = (Location) propertyValue.a();
                    }
                    final Object a = s.a();
                    if (a == null) {
                        this.a(a.f, this.f.d());
                        return;
                    } else if (s.b(a, location)) {
                        this.a(a.f, this.f.d());
                        return;
                    } else {
                        this.a(a.j, a);
                        final Geocoder geocoder = new Geocoder(o.b());
                        d.this.e.b(new Runnable() {
                            public void run() {
                                try {
                                    List fromLocation = geocoder.getFromLocation(a.getLatitude(), a.getLongitude(), 1);
                                    int i = 0;
                                    if (fromLocation != null && fromLocation.size() > 0) {
                                        Object countryCode = ((Address) fromLocation.get(0)).getCountryCode();
                                        if (!TextUtils.isEmpty(countryCode)) {
                                            this.a(a.f, countryCode);
                                            i = 1;
                                        }
                                    }
                                    if (i != 0) {
                                        return;
                                    }
                                } catch (IOException e) {
                                    l.a("Helpshift_DeviceModel", "rescanDevice : ", e);
                                } catch (Throwable th) {
                                    this.a(a.f, this.f.d());
                                }
                                this.a(a.f, this.f.d());
                            }
                        });
                        return;
                    }
                }
                this.a(a.f, this.f.d());
            }
        });
    }

    public HashMap<String, ArrayList> c() {
        final HashMap hashMap = new HashMap();
        this.e.a(new Runnable() {
            public void run() {
                for (int i = 0; i < 13; i++) {
                    PropertyValue propertyValue = this.a[i];
                    if (propertyValue != null && propertyValue.c().equals(com.helpshift.campaigns.m.a.b.a)) {
                        hashMap.put(this.b[i], propertyValue.d());
                    }
                }
            }
        });
        return hashMap;
    }

    @NonNull
    public HashMap<String, ArrayList> d() {
        final HashMap hashMap = new HashMap();
        this.e.a(new Runnable() {
            public void run() {
                for (int i = 0; i < 13; i++) {
                    PropertyValue propertyValue = this.a[i];
                    if (propertyValue != null && (com.helpshift.campaigns.m.a.b.b == propertyValue.c() || com.helpshift.campaigns.m.a.b.a == propertyValue.c())) {
                        hashMap.put(this.b[i], propertyValue.d());
                    }
                }
            }
        });
        return hashMap;
    }

    public HashMap<String, ArrayList> e() {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < 13; i++) {
            PropertyValue propertyValue = this.a[i];
            if (propertyValue != null && propertyValue.c().equals(com.helpshift.campaigns.m.a.b.c)) {
                hashMap.put(this.b[i], propertyValue.d());
            }
        }
        return hashMap;
    }

    public void a(final List<String> list) {
        if (list != null) {
            this.e.b(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    List asList = Arrays.asList(this.b);
                    int size = asList.size();
                    for (String str : list) {
                        int indexOf = asList.indexOf(str);
                        if (indexOf >= 0 && indexOf < size) {
                            PropertyValue propertyValue = this.a[indexOf];
                            if (propertyValue != null) {
                                if (propertyValue.c().equals(com.helpshift.campaigns.m.a.b.c)) {
                                    propertyValue.a(com.helpshift.campaigns.m.a.b.b);
                                    arrayList.add(str);
                                } else if (d.this.b(Integer.valueOf(indexOf))) {
                                    b.a().b.c(Boolean.valueOf(true));
                                }
                            }
                        }
                    }
                    d.this.d.b(com.helpshift.campaigns.m.a.b.b, (String[]) arrayList.toArray(new String[arrayList.size()]), d.this.c);
                }
            });
        }
    }

    public void a(final Integer num, final ArrayList<String> arrayList) {
        if (arrayList != null) {
            this.e.b(new Runnable() {
                public void run() {
                    List asList = Arrays.asList(this.b);
                    int size = asList.size();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        int indexOf = asList.indexOf((String) it.next());
                        if (indexOf >= 0 && indexOf < size) {
                            PropertyValue propertyValue = this.a[indexOf];
                            if (propertyValue != null) {
                                propertyValue.a(num);
                            }
                        }
                    }
                    d.this.d.b(num, (String[]) arrayList.toArray(new String[arrayList.size()]), d.this.c);
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(Integer num) {
        return num.equals(a.b) || num.equals(a.a) || num.equals(a.l);
    }
}
