package com.helpshift.common.platform;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import com.helpshift.account.dao.b;
import com.helpshift.account.dao.c;
import com.helpshift.common.domain.f;
import com.helpshift.common.domain.j;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.h;
import com.helpshift.downloader.SupportDownloader;
import com.helpshift.e.g;
import com.helpshift.meta.a.a;
import com.helpshift.notifications.NotificationChannelsManager;
import com.helpshift.notifications.NotificationChannelsManager.NotificationChannelType;
import com.helpshift.support.d;
import com.helpshift.support.util.i;
import com.helpshift.util.l;
import com.helpshift.util.n;
import java.io.IOException;

public class k implements p {
    private final Context a;
    private String b;
    private String c;
    private String d;
    private d e;
    private o f;
    private b g;
    private Device h;
    private com.helpshift.common.platform.network.d i;
    private a j;
    private com.helpshift.conversation.a.a k;
    private com.helpshift.conversation.a.b l;
    private com.helpshift.analytics.a m;
    private com.helpshift.cif.a.a n;
    private com.helpshift.g.a.a o;
    private com.helpshift.g.b.a p;
    private j q;
    private SupportDownloader r;
    private Context s;

    public k(Context context, String str, String str2, String str3) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public Device d() {
        if (this.h == null) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new d(this.a);
                }
            }
        }
        return this.h;
    }

    public com.helpshift.conversation.a.b e() {
        if (this.l == null) {
            synchronized (this) {
                if (this.l == null) {
                    this.l = new b(this.a, m());
                }
            }
        }
        return this.l;
    }

    public com.helpshift.conversation.a.a f() {
        if (this.k == null) {
            synchronized (this) {
                if (this.k == null) {
                    this.k = new a(this.a);
                }
            }
        }
        return this.k;
    }

    public a g() {
        if (this.j == null) {
            synchronized (this) {
                if (this.j == null) {
                    this.j = new i(m());
                }
            }
        }
        return this.j;
    }

    public com.helpshift.analytics.a h() {
        if (this.m == null) {
            synchronized (this) {
                if (this.m == null) {
                    this.m = new com.helpshift.support.h.a(m());
                }
            }
        }
        return this.m;
    }

    public com.helpshift.cif.a.a i() {
        if (this.n == null) {
            synchronized (this) {
                if (this.n == null) {
                    this.n = new c(m());
                }
            }
        }
        return this.n;
    }

    public h j() {
        return new l();
    }

    public com.helpshift.common.platform.network.b k() {
        return new g();
    }

    public com.helpshift.g.b.a l() {
        if (this.p == null) {
            synchronized (this) {
                if (this.p == null) {
                    this.p = new e(y());
                }
            }
        }
        return this.p;
    }

    public o m() {
        if (this.f == null) {
            synchronized (this) {
                if (this.f == null) {
                    this.f = new com.helpshift.support.h.k(this.a);
                }
            }
        }
        return this.f;
    }

    public n n() {
        return new h();
    }

    public com.helpshift.account.dao.a o() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = new b(com.helpshift.support.j.a(), m());
                }
            }
        }
        return this.g;
    }

    public c p() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    this.g = new b(com.helpshift.support.j.a(), m());
                }
            }
        }
        return this.g;
    }

    public com.helpshift.common.platform.network.d q() {
        if (this.i == null) {
            synchronized (this) {
                if (this.i == null) {
                    this.i = new j(m());
                }
            }
        }
        return this.i;
    }

    public com.helpshift.g.a.a r() {
        if (this.o == null) {
            synchronized (this) {
                if (this.o == null) {
                    this.o = new f(m());
                }
            }
        }
        return this.o;
    }

    public boolean s() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public j t() {
        if (this.q == null) {
            synchronized (this) {
                if (this.q == null) {
                    this.q = new j() {
                        public f a(final f fVar) {
                            return new f() {
                                public void a() {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        public void run() {
                                            fVar.a();
                                        }
                                    });
                                }
                            };
                        }
                    };
                }
            }
        }
        return this.q;
    }

    public SupportDownloader u() {
        if (this.r == null) {
            synchronized (this) {
                if (this.r == null) {
                    this.r = new m(this.a, m());
                }
            }
        }
        return this.r;
    }

    public String a(String str, String str2) {
        try {
            str2 = com.helpshift.support.util.a.a(str, str2);
            if (str2 == null) {
                return str;
            }
            return str2;
        } catch (IOException e) {
            l.a("AndroidPlatform", "Saving attachment", e);
            return str;
        }
    }

    public void a(com.helpshift.conversation.dto.c cVar, String str) throws RootAPIException {
        try {
            com.helpshift.support.util.a.a(cVar, str);
        } catch (Exception e) {
            throw RootAPIException.a(e);
        }
    }

    public int v() {
        Context context;
        if (this.s != null) {
            context = this.s;
        } else {
            context = this.a;
        }
        return context.getResources().getInteger(g.hs__issue_description_min_chars);
    }

    public void a(Long l, String str, int i, String str2, String str3) {
        Context context;
        if (this.s != null) {
            context = this.s;
        } else {
            context = com.helpshift.util.b.e(this.a);
        }
        Builder a = i.a(context, l, str, i, str2, str3);
        if (a != null) {
            com.helpshift.util.b.a(this.a, str, new NotificationChannelsManager(this.a).a(a.build(), NotificationChannelType.SUPPORT));
        }
    }

    public void a(String str) {
        com.helpshift.util.b.a(this.a, str, 1);
    }

    public com.helpshift.n.b w() {
        return com.helpshift.n.a.a();
    }

    public boolean x() {
        return n.a(this.a);
    }

    public void a(Object obj) {
        if (obj == null) {
            this.s = null;
        } else if (obj instanceof Context) {
            this.s = (Context) obj;
        }
    }

    private d y() {
        if (this.e == null) {
            synchronized (this) {
                if (this.e == null) {
                    this.e = new d(this.a);
                }
            }
        }
        return this.e;
    }
}
