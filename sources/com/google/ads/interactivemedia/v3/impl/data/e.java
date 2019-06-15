package com.google.ads.interactivemedia.v3.impl.data;

final class e extends m {
    private final int height;
    private final int left;
    private final int top;
    private final int width;

    static final class a extends com.google.ads.interactivemedia.v3.impl.data.m.a {
        private Integer height;
        private Integer left;
        private Integer top;
        private Integer width;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.m.a left(int i) {
            this.left = Integer.valueOf(i);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m.a top(int i) {
            this.top = Integer.valueOf(i);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m.a height(int i) {
            this.height = Integer.valueOf(i);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.m.a width(int i) {
            this.width = Integer.valueOf(i);
            return this;
        }

        public m build() {
            String str = "";
            if (this.left == null) {
                str = String.valueOf(str).concat(" left");
            }
            if (this.top == null) {
                str = String.valueOf(str).concat(" top");
            }
            if (this.height == null) {
                str = String.valueOf(str).concat(" height");
            }
            if (this.width == null) {
                str = String.valueOf(str).concat(" width");
            }
            if (str.isEmpty()) {
                return new e(this.left.intValue(), this.top.intValue(), this.height.intValue(), this.width.intValue());
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private e(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.height = i3;
        this.width = i4;
    }

    public int left() {
        return this.left;
    }

    public int top() {
        return this.top;
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    public String toString() {
        int i = this.left;
        int i2 = this.top;
        int i3 = this.height;
        int i4 = this.width;
        StringBuilder stringBuilder = new StringBuilder(90);
        stringBuilder.append("BoundingRectData{left=");
        stringBuilder.append(i);
        stringBuilder.append(", top=");
        stringBuilder.append(i2);
        stringBuilder.append(", height=");
        stringBuilder.append(i3);
        stringBuilder.append(", width=");
        stringBuilder.append(i4);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (!(this.left == mVar.left() && this.top == mVar.top() && this.height == mVar.height() && this.width == mVar.width())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((this.left ^ 1000003) * 1000003) ^ this.top) * 1000003) ^ this.height) * 1000003) ^ this.width;
    }
}
