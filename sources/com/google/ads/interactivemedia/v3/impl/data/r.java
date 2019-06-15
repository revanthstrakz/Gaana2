package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.gp;
import com.google.ads.interactivemedia.v3.internal.hx;
import com.google.ads.interactivemedia.v3.internal.hy;
import com.google.ads.interactivemedia.v3.internal.hz;
import com.google.ads.interactivemedia.v3.internal.ko;
import java.io.IOException;

public class r implements UiElement {
    public static final gp<r> GSON_TYPE_ADAPTER = new gp<r>() {
        public void write(hz hzVar, r rVar) throws IOException {
            if (rVar == null) {
                hzVar.f();
            } else {
                hzVar.b(rVar.getName());
            }
        }

        public r read(hx hxVar) throws IOException {
            if (hxVar.f() != hy.NULL) {
                return new r(hxVar.h());
            }
            hxVar.j();
            return null;
        }
    };
    private final String name;

    public r(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof r)) {
            return false;
        }
        return this.name.equals(((r) obj).name);
    }

    public int hashCode() {
        return ko.a(this.name);
    }

    public String toString() {
        String str = this.name;
        StringBuilder stringBuilder = new StringBuilder(20 + String.valueOf(str).length());
        stringBuilder.append("UiElementImpl[name=");
        stringBuilder.append(str);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
