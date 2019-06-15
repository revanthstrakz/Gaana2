package com.helpshift.websockets;

import java.util.Map.Entry;

class t extends s {
    private static final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) -1, (byte) -1};
    private boolean b;
    private boolean c;
    private int d = 32768;
    private int e = 32768;
    private int f;
    private c g;

    public t() {
        super("permessage-deflate");
    }

    public t(String str) {
        super(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void a() throws WebSocketException {
        for (Entry entry : c().entrySet()) {
            b((String) entry.getKey(), (String) entry.getValue());
        }
        this.f = this.d + 1024;
    }

    private void b(String str, String str2) throws WebSocketException {
        if ("server_no_context_takeover".equals(str)) {
            this.b = true;
        } else if ("client_no_context_takeover".equals(str)) {
            this.c = true;
        } else if ("server_max_window_bits".equals(str)) {
            this.d = c(str, str2);
        } else if ("client_max_window_bits".equals(str)) {
            this.e = c(str, str2);
        } else {
            WebSocketError webSocketError = WebSocketError.PERMESSAGE_DEFLATE_UNSUPPORTED_PARAMETER;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("permessage-deflate extension contains an unsupported parameter: ");
            stringBuilder.append(str);
            throw new WebSocketException(webSocketError, stringBuilder.toString());
        }
    }

    private int c(String str, String str2) throws WebSocketException {
        int d = d(str, str2);
        int i = 256;
        for (int i2 = 8; i2 < d; i2++) {
            i *= 2;
        }
        return i;
    }

    private int d(String str, String str2) throws WebSocketException {
        int b = b(str2);
        if (b >= 0) {
            return b;
        }
        throw new WebSocketException(WebSocketError.PERMESSAGE_DEFLATE_INVALID_MAX_WINDOW_BITS, String.format("The value of %s parameter of permessage-deflate extension is invalid: %s", new Object[]{str, str2}));
    }

    private int b(String str) {
        if (str == null) {
            return -1;
        }
        try {
            int parseInt = Integer.parseInt(str);
            return (parseInt < 8 || 15 < parseInt) ? -1 : parseInt;
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    /* Access modifiers changed, original: protected */
    public byte[] a(byte[] bArr) throws WebSocketException {
        c cVar = new c(bArr.length + a.length);
        cVar.a(bArr);
        cVar.a(a);
        if (this.g == null) {
            this.g = new c(this.f);
        }
        int a = this.g.a();
        try {
            f.a(cVar, this.g);
            bArr = this.g.c(a);
            this.g.d(this.f);
            if (this.b) {
                this.g.b();
            }
            return bArr;
        } catch (Exception e) {
            throw new WebSocketException(WebSocketError.DECOMPRESSION_ERROR, String.format("Failed to decompress the message: %s", new Object[]{e.getMessage()}), e);
        }
    }

    /* Access modifiers changed, original: protected */
    public byte[] b(byte[] bArr) throws WebSocketException {
        if (!c(bArr)) {
            return bArr;
        }
        try {
            return d(e.a(bArr));
        } catch (Exception e) {
            throw new WebSocketException(WebSocketError.COMPRESSION_ERROR, String.format("Failed to compress the message: %s", new Object[]{e.getMessage()}), e);
        }
    }

    private boolean c(byte[] bArr) {
        return this.e == 32768 || bArr.length < this.e;
    }

    private static byte[] d(byte[] bArr) throws FormatException {
        c cVar = new c(bArr.length + 1);
        cVar.a(bArr);
        int[] iArr = new int[1];
        boolean[] zArr = new boolean[1];
        while (a(cVar, iArr, zArr)) {
        }
        if (zArr[0]) {
            return cVar.a(0, (((iArr[0] - 1) / 8) + 1) - 4);
        }
        a(cVar, iArr);
        return cVar.a(0, ((iArr[0] - 1) / 8) + 1);
    }

    private static void a(c cVar, int[] iArr) {
        int i = iArr[0] % 8;
        if (i != 0) {
            switch (i) {
                case 6:
                case 7:
                    break;
            }
        }
        cVar.b(0);
        iArr[0] = iArr[0] + 3;
    }

    private static boolean a(com.helpshift.websockets.c r5, int[] r6, boolean[] r7) throws com.helpshift.websockets.FormatException {
        /*
        r0 = r5.a(r6);
        r1 = 1;
        r2 = 0;
        if (r0 == 0) goto L_0x000e;
    L_0x0008:
        r3 = r6[r2];
        r3 = r3 - r1;
        r5.f(r3);
    L_0x000e:
        r3 = 2;
        r4 = r5.a(r6, r3);
        switch(r4) {
            case 0: goto L_0x003d;
            case 1: goto L_0x0038;
            case 2: goto L_0x0034;
            default: goto L_0x0016;
        };
    L_0x0016:
        r5 = "[%s] Bad compression type '11' at the bit index '%d'.";
        r7 = new java.lang.Object[r3];
        r0 = com.helpshift.websockets.t.class;
        r0 = r0.getSimpleName();
        r7[r2] = r0;
        r6 = r6[r2];
        r6 = java.lang.Integer.valueOf(r6);
        r7[r1] = r6;
        r5 = java.lang.String.format(r5, r7);
        r6 = new com.helpshift.websockets.FormatException;
        r6.<init>(r5);
        throw r6;
    L_0x0034:
        d(r5, r6);
        goto L_0x003b;
    L_0x0038:
        c(r5, r6);
    L_0x003b:
        r3 = r2;
        goto L_0x0044;
    L_0x003d:
        r3 = b(r5, r6);
        if (r3 != 0) goto L_0x003b;
    L_0x0043:
        r3 = r1;
    L_0x0044:
        r5 = r5.a();
        r6 = r6[r2];
        r6 = r6 / 8;
        if (r5 > r6) goto L_0x004f;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        if (r0 == 0) goto L_0x0055;
    L_0x0051:
        if (r3 == 0) goto L_0x0055;
    L_0x0053:
        r7[r2] = r1;
    L_0x0055:
        if (r0 != 0) goto L_0x0058;
    L_0x0057:
        goto L_0x0059;
    L_0x0058:
        r1 = r2;
    L_0x0059:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.websockets.t.a(com.helpshift.websockets.c, int[], boolean[]):boolean");
    }

    private static int b(c cVar, int[] iArr) {
        int i = ((iArr[0] + 7) & -8) / 8;
        int a = (cVar.a(i) & 255) + ((cVar.a(i + 1) & 255) * 256);
        iArr[0] = ((i + 4) + a) * 8;
        return a;
    }

    private static void c(c cVar, int[] iArr) throws FormatException {
        a(cVar, iArr, k.a(), j.a());
    }

    private static void d(c cVar, int[] iArr) throws FormatException {
        n[] nVarArr = new n[2];
        g.a(cVar, iArr, nVarArr);
        a(cVar, iArr, nVarArr[0], nVarArr[1]);
    }

    private static void a(c cVar, int[] iArr, n nVar, n nVar2) throws FormatException {
        while (true) {
            int a = nVar.a(cVar, iArr);
            if (a != 256) {
                if (a < 0 || a > 255) {
                    g.a(cVar, iArr, a);
                    g.a(cVar, iArr, nVar2);
                }
            } else {
                return;
            }
        }
    }
}
