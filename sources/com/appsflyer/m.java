package com.appsflyer;

final class m {
    private e a;
    private String b;
    private boolean c;

    enum e {
        GOOGLE(0),
        AMAZON(1);
        
        /* renamed from: ˏ */
        private int f5;

        private e(int i) {
            this.f5 = i;
        }

        public final String toString() {
            return String.valueOf(this.f5);
        }
    }

    m(e eVar, String str, boolean z) {
        this.a = eVar;
        this.b = str;
        this.c = z;
    }

    /* Access modifiers changed, original: final */
    public final String a() {
        return this.b;
    }

    /* Access modifiers changed, original: final */
    public final boolean b() {
        return this.c;
    }

    public final String toString() {
        return String.format("%s,%s", new Object[]{this.b, Boolean.valueOf(this.c)});
    }
}
