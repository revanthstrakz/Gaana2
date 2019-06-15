package com.views;

import com.gaana.R;

public class a {
    public static final a a = new a(3000, R.color.confirm);
    public static final a b = new a(3000, R.color.info);
    public static final a c = new a(5000, R.color.alert);

    public static class a {
        private final int a;
        private final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (aVar.a == this.a && aVar.b == this.b) {
                z = true;
            }
            return z;
        }
    }
}
