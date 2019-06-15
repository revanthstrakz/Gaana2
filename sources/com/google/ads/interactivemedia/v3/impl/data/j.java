package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.impl.data.q.b;

final class j extends b {
    private final boolean attached;
    private final m bounds;
    private final boolean hidden;
    private final String type;

    static final class a extends com.google.ads.interactivemedia.v3.impl.data.q.b.a {
        private Boolean attached;
        private m bounds;
        private Boolean hidden;
        private String type;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.q.b.a attached(boolean z) {
            this.attached = Boolean.valueOf(z);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.q.b.a bounds(m mVar) {
            if (mVar == null) {
                throw new NullPointerException("Null bounds");
            }
            this.bounds = mVar;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.q.b.a hidden(boolean z) {
            this.hidden = Boolean.valueOf(z);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.q.b.a type(String str) {
            if (str == null) {
                throw new NullPointerException("Null type");
            }
            this.type = str;
            return this;
        }

        public b build() {
            String str = "";
            if (this.attached == null) {
                str = String.valueOf(str).concat(" attached");
            }
            if (this.bounds == null) {
                str = String.valueOf(str).concat(" bounds");
            }
            if (this.hidden == null) {
                str = String.valueOf(str).concat(" hidden");
            }
            if (this.type == null) {
                str = String.valueOf(str).concat(" type");
            }
            if (str.isEmpty()) {
                return new j(this.attached.booleanValue(), this.bounds, this.hidden.booleanValue(), this.type);
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private j(boolean z, m mVar, boolean z2, String str) {
        this.attached = z;
        this.bounds = mVar;
        this.hidden = z2;
        this.type = str;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean attached() {
        return this.attached;
    }

    /* Access modifiers changed, original: 0000 */
    public m bounds() {
        return this.bounds;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean hidden() {
        return this.hidden;
    }

    /* Access modifiers changed, original: 0000 */
    public String type() {
        return this.type;
    }

    public String toString() {
        boolean z = this.attached;
        String valueOf = String.valueOf(this.bounds);
        boolean z2 = this.hidden;
        String str = this.type;
        StringBuilder stringBuilder = new StringBuilder((61 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
        stringBuilder.append("ObstructionData{attached=");
        stringBuilder.append(z);
        stringBuilder.append(", bounds=");
        stringBuilder.append(valueOf);
        stringBuilder.append(", hidden=");
        stringBuilder.append(z2);
        stringBuilder.append(", type=");
        stringBuilder.append(str);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!(this.attached == bVar.attached() && this.bounds.equals(bVar.bounds()) && this.hidden == bVar.hidden() && this.type.equals(bVar.type()))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 1237;
        int hashCode = ((((this.attached ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.bounds.hashCode()) * 1000003;
        if (this.hidden) {
            i = 1231;
        }
        return ((hashCode ^ i) * 1000003) ^ this.type.hashCode();
    }
}
