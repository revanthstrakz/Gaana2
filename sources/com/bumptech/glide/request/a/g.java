package com.bumptech.glide.request.a;

import com.bumptech.glide.f.i;

public abstract class g<Z> extends a<Z> {
    private final int height;
    private final int width;

    public void removeCallback(h hVar) {
    }

    public g() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public g(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public final void getSize(h hVar) {
        if (i.a(this.width, this.height)) {
            hVar.a(this.width, this.height);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: ");
        stringBuilder.append(this.width);
        stringBuilder.append(" and height: ");
        stringBuilder.append(this.height);
        stringBuilder.append(", either provide dimensions in the constructor or call override()");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
