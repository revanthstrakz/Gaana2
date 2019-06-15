package com.helpshift.websockets;

class j extends n {
    private static final j a = new j();

    private j() {
        super(b());
    }

    private static int[] b() {
        int[] iArr = new int[32];
        for (int i = 0; i < 32; i++) {
            iArr[i] = 5;
        }
        return iArr;
    }

    public static j a() {
        return a;
    }
}
