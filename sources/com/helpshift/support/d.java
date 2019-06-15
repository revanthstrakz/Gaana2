package com.helpshift.support;

import android.content.Context;
import android.database.SQLException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.comscore.measurement.MeasurementDispatcher;
import com.helpshift.support.HSSearch.HS_SEARCH_OPTIONS;
import com.helpshift.support.b.a;
import com.helpshift.support.h.b;
import com.helpshift.support.h.h;
import com.helpshift.support.h.i;
import com.helpshift.support.model.FaqSearchIndex;
import com.helpshift.util.j;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.x;
import com.payu.custombrowser.util.CBConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class d {
    public static ArrayList<e> a;
    private static final Object h = new Object();
    public g b;
    public c c;
    h d;
    b e;
    private Iterator f = null;
    private ArrayList<Faq> g = null;

    public d(Context context) {
        this.b = new g(context);
        this.c = new c(this.b.c(), this.b.d(), this.b.b(), this.b);
        this.d = new i();
        this.e = new com.helpshift.support.h.d();
    }

    protected static void a() {
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                e eVar = (e) a.get(i);
                if (eVar != null) {
                    eVar.a();
                }
            }
        }
    }

    protected static void b() {
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                e eVar = (e) a.get(i);
                if (eVar != null) {
                    eVar.b();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, String str2, String str3) {
        this.b.d(str);
        this.b.e(str2);
        this.b.f(str3);
        this.c = new c(str2, str3, str, this.b);
    }

    private void l() {
        ArrayList d = d();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d.size(); i++) {
            arrayList.addAll(a(((Section) d.get(i)).a()));
        }
        synchronized (h) {
            this.g = new ArrayList(arrayList);
        }
    }

    private void b(final Handler handler, final Handler handler2, final FaqTagFilter faqTagFilter) throws SQLException {
        this.c.a(new Handler() {
            public void handleMessage(Message message) {
                HashMap hashMap = (HashMap) message.obj;
                Message obtainMessage = handler.obtainMessage();
                if (hashMap != null) {
                    l.a("Helpshift_ApiData", "FAQ fetch success, updating new data.");
                    d.this.a((JSONArray) hashMap.get(CBConstant.RESPONSE));
                    obtainMessage.obj = d.this.d.a(faqTagFilter);
                    obtainMessage.what = a.d;
                    handler.sendMessage(obtainMessage);
                    d.this.c();
                } else {
                    l.a("Helpshift_ApiData", "FAQ fetch success, no new data.");
                    obtainMessage.what = a.c;
                    handler.sendMessage(obtainMessage);
                }
                d.a();
            }
        }, new Handler() {
            public void handleMessage(Message message) {
                l.a("Helpshift_ApiData", "FAQ fetch failed.");
                HashMap hashMap = (HashMap) message.obj;
                Message obtainMessage = handler2.obtainMessage();
                obtainMessage.obj = hashMap;
                obtainMessage.what = a.e;
                handler2.sendMessage(obtainMessage);
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public void a(JSONArray jSONArray) {
        String str = "Helpshift_ApiData";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Updating ");
        stringBuilder.append(jSONArray == null ? 0 : jSONArray.length());
        stringBuilder.append(" FAQ sections in DB");
        l.a(str, stringBuilder.toString());
        this.d.b();
        this.d.a(jSONArray);
    }

    /* Access modifiers changed, original: 0000 */
    public void c() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                d.this.h();
            }
        }, "HS-search-index");
        thread.setDaemon(true);
        thread.start();
    }

    public void a(Handler handler, Handler handler2, FaqTagFilter faqTagFilter) {
        Object obj;
        try {
            obj = (ArrayList) this.d.a(faqTagFilter);
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting sections data ", e);
            obj = null;
        }
        if (obj != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = a.a;
            obtainMessage.obj = obj;
            handler.sendMessage(obtainMessage);
        } else {
            Message obtainMessage2 = handler2.obtainMessage();
            obtainMessage2.what = a.b;
            handler2.sendMessage(obtainMessage2);
        }
        b(handler, handler2, faqTagFilter);
    }

    /* Access modifiers changed, original: protected */
    public ArrayList<Section> d() {
        try {
            return (ArrayList) this.d.a();
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting sections data ", e);
            return null;
        }
    }

    public ArrayList<Section> a(ArrayList<Section> arrayList, FaqTagFilter faqTagFilter) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!a((Section) arrayList.get(i), faqTagFilter)) {
                arrayList2.add(arrayList.get(i));
            }
        }
        return arrayList2;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(Section section, FaqTagFilter faqTagFilter) {
        return a(section.a(), faqTagFilter).isEmpty();
    }

    public ArrayList<Faq> a(String str, FaqTagFilter faqTagFilter) {
        ArrayList<Faq> arrayList = new ArrayList();
        try {
            return (ArrayList) this.e.a(str, faqTagFilter);
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting faqs for section", e);
            return arrayList;
        }
    }

    /* Access modifiers changed, original: protected */
    public ArrayList<Faq> a(String str) {
        ArrayList<Faq> arrayList = new ArrayList();
        try {
            return (ArrayList) this.e.c(str);
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting faqs for section", e);
            return arrayList;
        }
    }

    public void a(String str, boolean z) {
        this.e.a(str, Boolean.valueOf(z));
    }

    public ArrayList<Faq> a(String str, HS_SEARCH_OPTIONS hs_search_options) {
        return a(str, hs_search_options, null);
    }

    public ArrayList<Faq> a(String str, HS_SEARCH_OPTIONS hs_search_options, FaqTagFilter faqTagFilter) {
        if (this.g == null) {
            l();
        } else {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ((Faq) it.next()).d();
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String toLowerCase = str.toLowerCase();
        if (this.b.q() || !this.b.l().booleanValue()) {
            for (int i = 0; i < this.g.size(); i++) {
                Faq faq = (Faq) this.g.get(i);
                if (!faq.b.toLowerCase().contains(toLowerCase)) {
                    linkedHashSet.add(faq);
                }
            }
        } else {
            FaqSearchIndex k = this.b.k();
            Map map = null;
            if (k != null) {
                map = k.a;
            }
            ArrayList a = HSSearch.a(str, hs_search_options);
            ArrayList a2 = HSSearch.a(str, map);
            Iterator it2 = a.iterator();
            while (it2.hasNext()) {
                HashMap hashMap = (HashMap) it2.next();
                int intValue = Integer.decode((String) hashMap.get("f")).intValue();
                if (intValue < this.g.size()) {
                    Faq faq2 = (Faq) this.g.get(intValue);
                    faq2.a((ArrayList) hashMap.get("t"));
                    linkedHashSet.add(faq2);
                }
            }
            Iterator it3 = a2.iterator();
            while (it3.hasNext()) {
                HashMap hashMap2 = (HashMap) it3.next();
                int intValue2 = Integer.decode((String) hashMap2.get("f")).intValue();
                if (intValue2 < this.g.size()) {
                    Faq faq3 = (Faq) this.g.get(intValue2);
                    faq3.a((ArrayList) hashMap2.get("t"));
                    linkedHashSet.add(faq3);
                }
            }
        }
        if (faqTagFilter != null) {
            return new ArrayList(this.e.a(new ArrayList(linkedHashSet), faqTagFilter));
        }
        return new ArrayList(linkedHashSet);
    }

    public List<Faq> a(FaqTagFilter faqTagFilter) {
        if (this.g == null) {
            l();
        } else {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                ((Faq) it.next()).d();
            }
        }
        if (faqTagFilter != null) {
            return new ArrayList(this.e.a(new ArrayList(this.g), faqTagFilter));
        }
        return this.g;
    }

    /* Access modifiers changed, original: protected */
    public Boolean e() {
        com.helpshift.configuration.a.a m = o.d().m();
        if (!m.a("app_reviewed")) {
            com.helpshift.configuration.b.a b = m.b();
            String c = m.c("reviewUrl");
            if (b.a && !TextUtils.isEmpty(c)) {
                int g = this.b.g();
                String str = b.c;
                int i = b.b;
                if (i > 0) {
                    if ("l".equals(str) && g >= i) {
                        return Boolean.valueOf(true);
                    }
                    if ("s".equals(str) && g != 0 && (new Date().getTime() / 1000) - ((long) g) >= ((long) i)) {
                        return Boolean.valueOf(true);
                    }
                }
            }
        }
        return Boolean.valueOf(false);
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        int g = this.b.g();
        int h = this.b.h();
        if (g == 0) {
            h = (int) (new Date().getTime() / 1000);
        } else {
            int i = h;
            h = g;
            g = i;
        }
        this.b.b(g + 1);
        if ("l".equals(o.d().m().b().c)) {
            h = this.b.h();
        }
        this.b.a(h);
    }

    /* Access modifiers changed, original: protected */
    public void g() {
        int g = this.b.g();
        String str = o.d().m().b().c;
        if (str.equals("s")) {
            g = (int) (new Date().getTime() / 1000);
        } else if (str.equals("l")) {
            g = 0;
        }
        this.b.a(g);
        this.b.b(0);
    }

    public void b(String str) {
        try {
            JSONArray i = this.b.i();
            i.put(str);
            this.b.a(i);
        } catch (JSONException e) {
            l.a("Helpshift_ApiData", "storeFile", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        l.a("Helpshift_ApiData", "Updating search indexes.");
        this.b.o();
        l();
        FaqSearchIndex a = HSSearch.a(new ArrayList(this.g));
        if (a != null) {
            this.b.a(a);
        }
        b();
        l.a("Helpshift_ApiData", "Search index update finished.");
    }

    public void i() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    d.this.b.j();
                } catch (IOException | ClassCastException | ClassNotFoundException e) {
                    l.c("Helpshift_ApiData", "Exception while loading index: trying to re-create the index", e);
                    d.this.h();
                    try {
                        d.this.b.j();
                    } catch (Exception e2) {
                        l.c("Helpshift_ApiData", "Exception caught again, while loading index: ", e2);
                    }
                }
            }
        }, "HS-load-index");
        thread.setDaemon(true);
        thread.start();
    }

    public void a(final String str, final Handler handler, Handler handler2, FaqTagFilter faqTagFilter) {
        try {
            if (TextUtils.isEmpty(str)) {
                handler2.sendMessage(handler2.obtainMessage());
                return;
            }
            Section a = this.d.a(str);
            if (a != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.obj = a;
                handler.sendMessage(obtainMessage);
            }
            b(new Handler() {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    Section a = d.this.d.a(str);
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.obj = a;
                    handler.sendMessage(obtainMessage);
                }
            }, handler2, faqTagFilter);
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting section data ", e);
        }
    }

    public Section c(String str) {
        return this.d.a(str);
    }

    public void a(String str, Handler handler, Handler handler2) {
        if (TextUtils.isEmpty(str)) {
            handler2.sendMessage(handler2.obtainMessage());
            return;
        }
        try {
            Section a = this.d.a(str);
            if (a != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.obj = a;
                handler.sendMessage(obtainMessage);
            } else {
                handler2.sendMessage(handler2.obtainMessage());
            }
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting section data ", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String d(String str) {
        ArrayList d = d();
        String str2 = "";
        for (int i = 0; i < d.size(); i++) {
            Section section = (Section) d.get(i);
            if (section.c().equals(str)) {
                str2 = section.a();
            }
        }
        return str2;
    }

    private void b(final String str, final Handler handler, final Handler handler2) {
        this.c.a(str, new Handler() {
            public void handleMessage(Message message) {
                Message obtainMessage = handler.obtainMessage();
                HashMap hashMap = (HashMap) message.obj;
                if (hashMap != null) {
                    try {
                        JSONObject jSONObject = (JSONObject) hashMap.get(CBConstant.RESPONSE);
                        Faq faq = new Faq(0, jSONObject.getString("id"), jSONObject.getString("publish_id"), d.this.d(jSONObject.getString("section_id")), jSONObject.getString("title"), jSONObject.getString("body"), 0, Boolean.valueOf(jSONObject.getString("is_rtl") == "true"), jSONObject.has("stags") ? j.a(jSONObject.getString("stags")) : new ArrayList(), jSONObject.has("issue_tags") ? j.a(jSONObject.getString("issue_tags")) : new ArrayList());
                        obtainMessage.obj = faq;
                        handler.sendMessage(obtainMessage);
                        d.this.e.a(faq);
                    } catch (JSONException e) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Exception in getting question ");
                        stringBuilder.append(e);
                        l.a("Helpshift_ApiData", stringBuilder.toString());
                    }
                }
            }
        }, new Handler() {
            public void handleMessage(Message message) {
                Message obtainMessage = handler2.obtainMessage();
                HashMap hashMap = (HashMap) message.obj;
                if (hashMap != null) {
                    int intValue = ((Integer) hashMap.get("status")).intValue();
                    if (intValue == com.helpshift.common.domain.network.j.l.intValue() || intValue == com.helpshift.common.domain.network.j.m.intValue()) {
                        d.this.e.a(str);
                        String a = d.this.c.a(str);
                        o.c().q().a(a, "");
                        com.helpshift.k.b.a().b.b(a);
                    }
                }
                handler2.sendMessage(obtainMessage);
            }
        });
    }

    public void a(String str, Handler handler, Handler handler2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            handler2.sendMessage(handler2.obtainMessage());
            return;
        }
        Object obj = null;
        try {
            obj = this.e.b(str);
        } catch (SQLException e) {
            l.c("Helpshift_ApiData", "Database exception in getting faq ", e);
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.obj = obj;
        handler.sendMessage(obtainMessage);
        if (obj == null || z) {
            b(str, handler, handler2);
        }
    }

    public String j() {
        return o.d().j().a().b;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(List<com.helpshift.j.d.a> list) {
        if (list != null && !list.isEmpty()) {
            AnonymousClass8 anonymousClass8 = new Callback() {
                public boolean handleMessage(Message message) {
                    l.b();
                    return true;
                }
            };
            AnonymousClass9 anonymousClass9 = new Callback() {
                public boolean handleMessage(Message message) {
                    d.this.b.a((x.b(Float.valueOf(o.c().q().a())) - MeasurementDispatcher.MILLIS_PER_DAY) - 1);
                    return true;
                }
            };
            this.c.a(new Handler(anonymousClass8), new Handler(anonymousClass9), (List) list, j(), this.b.c());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void k() {
        for (String a : this.e.a()) {
            String a2 = this.c.a(a2);
            o.c().q().a(a2, "");
            com.helpshift.k.b.a().b.b(a2);
        }
        o.c().q().a("/faqs/", null);
    }
}
