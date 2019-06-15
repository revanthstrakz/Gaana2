package com.helpshift.campaigns.e;

import com.helpshift.a.a.a.b;
import com.helpshift.campaigns.i.c;
import com.helpshift.campaigns.i.f;
import com.helpshift.campaigns.i.g;
import com.helpshift.campaigns.models.CampaignSyncModel;
import com.helpshift.util.o;
import com.helpshift.util.p;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class a implements f, g {
    private static final TimeUnit b = TimeUnit.SECONDS;
    private static final String c;
    c a;
    private final b d;
    private final com.helpshift.a.a.a.a e;
    private final com.helpshift.a.a.a.a f;
    private HashMap<String, Integer> g = ((HashMap) this.h.a("hs__campaigns_icon_image_retry_counts"));
    private com.helpshift.campaigns.l.c h = new com.helpshift.campaigns.l.c(com.helpshift.q.f.a().a);

    public void a(String str) {
    }

    public void b(String str) {
    }

    public void c(String str) {
    }

    public void d(String str) {
    }

    public void e(String str) {
    }

    public void f(String str) {
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(o.b().getPackageName());
        stringBuilder.append("/helpshift/images/");
        c = stringBuilder.toString();
    }

    public a(c cVar) {
        this.a = cVar;
        if (this.g == null) {
            this.g = new HashMap();
        }
        this.d = new b(o.b(), this.h, new ThreadPoolExecutor(5, 5, 1, b, new LinkedBlockingQueue(), new com.helpshift.common.domain.g("cm-dwnld")));
        this.e = new com.helpshift.a.a.a.a.a().a(false).c(false).b(false).a(c).a();
        this.f = new com.helpshift.a.a.a.a.a().a(true).c(true).b(true).a(c).a();
    }

    public void a(final CampaignSyncModel campaignSyncModel) {
        this.d.a(campaignSyncModel.b, this.e, new com.helpshift.a.a.a.a.b() {
            public void a(boolean z, String str, Object obj) {
                if (z) {
                    a.this.a.a(campaignSyncModel, obj.toString());
                } else {
                    a.this.a.b(campaignSyncModel.a);
                }
            }
        }, null);
        this.a.a(campaignSyncModel.a);
    }

    public void a(final String str, final String str2) {
        if (k(str)) {
            AnonymousClass2 anonymousClass2 = new com.helpshift.a.a.a.a.b() {
                public void a(boolean z, String str, Object obj) {
                    if (z) {
                        String obj2 = obj.toString();
                        if (p.a(obj2)) {
                            a.this.a.a(str2, obj2);
                            return;
                        }
                        new File(obj2).delete();
                        a.this.h(str);
                        a.this.a.c(str2);
                        return;
                    }
                    a.this.g(str);
                    a.this.a.c(str2);
                }
            };
            j(str);
            this.d.a(str, this.f, anonymousClass2, null);
        }
    }

    public void b(final String str, final String str2) {
        if (k(str)) {
            AnonymousClass3 anonymousClass3 = new com.helpshift.a.a.a.a.b() {
                public void a(boolean z, String str, Object obj) {
                    if (z) {
                        String obj2 = obj.toString();
                        if (p.a(obj2)) {
                            p.b(obj.toString(), 3);
                            a.this.a.b(str2, obj.toString());
                            return;
                        }
                        new File(obj2).delete();
                        a.this.h(str);
                        a.this.a.d(str2);
                        return;
                    }
                    a.this.g(str);
                    a.this.a.d(str2);
                }
            };
            j(str);
            this.d.a(str, this.f, anonymousClass3, null);
        }
    }

    public void b(CampaignSyncModel campaignSyncModel) {
        a(campaignSyncModel);
    }

    public void a(com.helpshift.campaigns.models.b bVar) {
        Boolean bool = com.helpshift.k.b.a().a.i;
        if (bool == null || !bool.booleanValue()) {
            a(bVar.c, bVar.k());
        }
    }

    private void j(String str) {
        Integer num = (Integer) this.g.get(str);
        if (num == null) {
            this.g.put(str, Integer.valueOf(1));
        } else {
            this.g.put(str, Integer.valueOf(num.intValue() + 1));
        }
        this.h.a("hs__campaigns_icon_image_retry_counts", this.g);
    }

    /* Access modifiers changed, original: 0000 */
    public void g(String str) {
        Integer num = (Integer) this.g.get(str);
        if (num != null && num.intValue() > 0) {
            this.g.put(str, Integer.valueOf(num.intValue() - 1));
            this.h.a("hs__campaigns_icon_image_retry_counts", this.g);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void h(String str) {
        this.g.put(str, Integer.valueOf(5));
        this.h.a("hs__campaigns_icon_image_retry_counts", this.g);
    }

    public void i(String str) {
        this.g.put(str, Integer.valueOf(0));
        this.h.a("hs__campaigns_icon_image_retry_counts", this.g);
    }

    private boolean k(String str) {
        Integer num = (Integer) this.g.get(str);
        if (num == null) {
            this.g.put(str, Integer.valueOf(0));
            this.h.a("hs__campaigns_icon_image_retry_counts", this.g);
        } else if (num.intValue() >= 5) {
            return false;
        }
        return true;
    }
}
