package com.comscore.android.id;

public class DeviceId {
    private String a;
    private String b;
    private int c;
    private int d;

    public DeviceId(String str) {
        this.b = str;
        this.c = 0;
        this.d = 0;
    }

    public DeviceId(String str, int i, int i2) {
        this.b = str;
        this.c = i;
        this.d = i2;
    }

    public DeviceId(String str, String str2, int i, int i2) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
    }

    public int getCommonness() {
        return this.c;
    }

    public String getId() {
        return this.b;
    }

    public String getName() {
        return this.a;
    }

    public int getPersistency() {
        return this.d;
    }

    public String getSuffix() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getCommonness());
        stringBuilder.append("");
        stringBuilder.append(getPersistency());
        return stringBuilder.toString();
    }
}
