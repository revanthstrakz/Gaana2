package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

final class zzay extends ThreadLocal<ByteBuffer> {
    zzay(zzax zzax) {
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ Object initialValue() {
        return ByteBuffer.allocate(32);
    }
}
