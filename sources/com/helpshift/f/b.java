package com.helpshift.f;

import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import java.io.File;

public class b implements a {
    a a;
    private e b;

    public b(e eVar, a aVar) {
        this.a = aVar;
        this.b = eVar;
    }

    public void a() {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.a();
                }
            });
        }
    }

    public void b() {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.b();
                }
            });
        }
    }

    public void a(final String str) {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.a(str);
                }
            });
        }
    }

    public void c() {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.c();
                }
            });
        }
    }

    public void b(final String str) {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.b(str);
                }
            });
        }
    }

    public void a(final int i, final String str) {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.a(i, str);
                }
            });
        }
    }

    public void a(final File file) {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.a(file);
                }
            });
        }
    }

    public void a(final int i) {
        if (this.a != null) {
            this.b.c(new f() {
                public void a() {
                    b.this.a.a(i);
                }
            });
        }
    }

    public boolean d() {
        return this.a != null;
    }
}
