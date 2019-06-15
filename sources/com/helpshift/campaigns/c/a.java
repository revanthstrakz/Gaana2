package com.helpshift.campaigns.c;

import android.text.TextUtils;
import com.helpshift.campaigns.models.AnalyticsEvent;
import com.helpshift.d.e;
import com.helpshift.network.b.e.b;
import com.helpshift.network.errors.NetworkError;
import com.helpshift.network.i;
import com.helpshift.q.d;
import com.helpshift.util.a.c;
import com.helpshift.util.j;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class a implements com.helpshift.b.a, i {
    d a;
    c b;
    public final e c;

    public void a() {
    }

    public void a(Integer num) {
    }

    public com.helpshift.network.a.a e() {
        return null;
    }

    protected a(d dVar, c cVar, e eVar) {
        this.a = dVar;
        this.b = cVar;
        this.c = eVar;
        o.a().a((com.helpshift.b.a) this);
    }

    public void a(Integer num, String str, Boolean bool) {
        if (TextUtils.isEmpty(str)) {
            l.a("Helpshift_AnalyticsCnt", "Encountered empty campaign id for analytics record");
            return;
        }
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(num, str, bool);
        if (!b(num, str)) {
            a(analyticsEvent);
        }
    }

    private void a(final AnalyticsEvent analyticsEvent) {
        this.b.b(new Runnable() {
            public void run() {
                a.this.a(analyticsEvent.e, analyticsEvent.b);
                ArrayList arrayList = (ArrayList) a.this.a.a("kAnalyticsEvents");
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(analyticsEvent);
                a.this.a.a("kAnalyticsEvents", arrayList);
                a.this.c.a("data_type_analytics_event", 1);
            }
        });
    }

    private boolean b(Integer num, String str) {
        HashMap f = f();
        return f != null && f.containsKey(str) && ((List) f.get(str)).contains(num);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Integer num, String str) {
        HashMap f = f();
        if (f == null) {
            f = new HashMap();
        }
        List list = (List) f.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(num);
        f.put(str, list);
        this.a.a("kRecordedEventsMap", f);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(final String[] strArr) {
        this.b.b(new Runnable() {
            public void run() {
                ArrayList arrayList = (ArrayList) a.this.a.a("kAnalyticsEvents");
                a.this.a.b("kAnalyticsEvents");
                ArrayList arrayList2 = new ArrayList(Arrays.asList(strArr));
                ArrayList arrayList3 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AnalyticsEvent analyticsEvent = (AnalyticsEvent) it.next();
                    if (!arrayList2.contains(analyticsEvent.a)) {
                        arrayList3.add(analyticsEvent);
                    }
                }
                if (arrayList3.size() > 0) {
                    a.this.a.a("kAnalyticsEvents", arrayList3);
                }
            }
        });
    }

    private HashMap<String, List<Integer>> f() {
        return (HashMap) this.a.a("kRecordedEventsMap");
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<AnalyticsEvent> c() {
        return (ArrayList) this.a.a("kAnalyticsEvents");
    }

    public com.helpshift.network.a.a d() {
        ArrayList c = c();
        if (c == null || c.size() <= 0) {
            return null;
        }
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            AnalyticsEvent analyticsEvent = (AnalyticsEvent) it.next();
            arrayList.add(analyticsEvent.a());
            arrayList2.add(analyticsEvent.a);
        }
        JSONArray a = j.a(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put(com.facebook.ads.internal.g.e.a, a.toString());
        hashMap.put("sv", "6.4.0");
        hashMap.put("v", "1.1.0");
        HashMap c2 = b.a().a.c();
        for (String str : c2.keySet()) {
            hashMap.put(str, c2.get(str).toString());
        }
        final String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
        return new com.helpshift.network.a.a(1, "/ma/ae/", hashMap, new b<JSONArray>() {
            public void a(JSONArray jSONArray, Integer num) {
                this.b.b(new Runnable() {
                    public void run() {
                        this.a(strArr);
                        this.c.a("data_type_analytics_event", false);
                    }
                });
            }
        }, new com.helpshift.network.b.e.a() {
            public void a(NetworkError networkError, Integer num) {
                this.c.a("data_type_analytics_event", networkError);
            }
        }, new com.helpshift.network.b.b());
    }

    public void b() {
        ArrayList c = c();
        if (c != null && c.size() > 0) {
            this.c.b("data_type_analytics_event", c.size());
        }
    }
}
