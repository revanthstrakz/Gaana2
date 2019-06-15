package com.helpshift.campaigns.models;

import android.support.annotation.NonNull;
import com.helpshift.campaigns.l.i;
import com.helpshift.campaigns.m.a.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class h {
    public final String a;
    public String b;
    public String c;
    i d;
    HashMap<String, PropertyValue> e;

    public h(String str, i iVar) {
        this.a = str;
        this.e = iVar.c(str);
        this.d = iVar;
        PropertyValue a = iVar.a("name", str);
        if (a != null) {
            this.b = a.toString();
        }
        PropertyValue a2 = iVar.a("email", str);
        if (a2 != null) {
            this.c = a2.toString();
        }
    }

    public HashMap<String, PropertyValue> a() {
        HashMap hashMap = new HashMap();
        if (this.e != null) {
            for (Entry entry : this.e.entrySet()) {
                String str = (String) entry.getKey();
                PropertyValue propertyValue = (PropertyValue) entry.getValue();
                if (propertyValue.c().equals(b.a)) {
                    hashMap.put(str, propertyValue);
                }
            }
        }
        return hashMap;
    }

    @NonNull
    public HashMap<String, PropertyValue> b() {
        HashMap hashMap = new HashMap();
        if (this.e != null) {
            for (Entry entry : this.e.entrySet()) {
                String str = (String) entry.getKey();
                PropertyValue propertyValue = (PropertyValue) entry.getValue();
                if (propertyValue != null && (b.a == propertyValue.c() || b.b == propertyValue.c())) {
                    hashMap.put(str, propertyValue);
                }
            }
        }
        return hashMap;
    }

    public HashMap<String, PropertyValue> c() {
        HashMap hashMap = new HashMap();
        if (this.e != null) {
            for (Entry entry : this.e.entrySet()) {
                String str = (String) entry.getKey();
                PropertyValue propertyValue = (PropertyValue) entry.getValue();
                if (propertyValue.c().equals(b.c)) {
                    hashMap.put(str, propertyValue);
                }
            }
        }
        return hashMap;
    }

    public void a(List<String> list) {
        if (this.e != null && list != null) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                PropertyValue propertyValue = (PropertyValue) this.e.get(str);
                if (propertyValue != null && propertyValue.c().equals(b.c)) {
                    propertyValue.a(b.b);
                    arrayList.add(str);
                }
            }
            this.d.a(b.b, (String[]) arrayList.toArray(new String[arrayList.size()]), this.a);
        }
    }

    public void a(Integer num, ArrayList<String> arrayList) {
        if (this.e != null && arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PropertyValue propertyValue = (PropertyValue) this.e.get((String) it.next());
                if (propertyValue != null) {
                    propertyValue.a(num);
                }
            }
            this.d.a(num, (String[]) arrayList.toArray(new String[arrayList.size()]), this.a);
        }
    }
}
