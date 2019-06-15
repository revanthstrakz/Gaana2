package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.impl.data.q.b;
import com.google.ads.interactivemedia.v3.internal.la;
import java.util.Collection;
import java.util.List;

final class i extends q {
    private final la<b> obstructions;

    static final class a extends com.google.ads.interactivemedia.v3.impl.data.q.a {
        private la<b> obstructions;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.q.a obstructions(List<b> list) {
            this.obstructions = la.a((Collection) list);
            return this;
        }

        public q build() {
            String str = "";
            if (this.obstructions == null) {
                str = String.valueOf(str).concat(" obstructions");
            }
            if (str.isEmpty()) {
                return new i(this.obstructions);
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private i(la<b> laVar) {
        this.obstructions = laVar;
    }

    /* Access modifiers changed, original: 0000 */
    public la<b> obstructions() {
        return this.obstructions;
    }

    public String toString() {
        String valueOf = String.valueOf(this.obstructions);
        StringBuilder stringBuilder = new StringBuilder(34 + String.valueOf(valueOf).length());
        stringBuilder.append("ObstructionListData{obstructions=");
        stringBuilder.append(valueOf);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        return this.obstructions.equals(((q) obj).obstructions());
    }

    public int hashCode() {
        return this.obstructions.hashCode() ^ 1000003;
    }
}
