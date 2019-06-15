package com.helpshift.websockets;

class k extends n {
    private static final k a = new k();

    private k() {
        super(b());
    }

    private static int[] b() {
        int[] iArr = new int[288];
        int i = 0;
        while (i < 144) {
            iArr[i] = 8;
            i++;
        }
        while (i < 256) {
            iArr[i] = 9;
            i++;
        }
        while (i < 280) {
            iArr[i] = 7;
            i++;
        }
        while (i < 288) {
            iArr[i] = 8;
            i++;
        }
        return iArr;
    }

    public static k a() {
        return a;
    }
}
