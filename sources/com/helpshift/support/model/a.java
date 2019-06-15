package com.helpshift.support.model;

import java.util.Locale;

public final class a {
    private double a;
    private String b;

    public a(double d) {
        if (d < 1024.0d) {
            this.a = d;
            this.b = " B";
        } else if (d < 1048576.0d) {
            this.a = d / 1024.0d;
            this.b = " KB";
        } else {
            this.a = d / 1048576.0d;
            this.b = " MB";
        }
    }

    public String a() {
        StringBuilder stringBuilder;
        if (this.b.equals(" MB")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.a)}));
            stringBuilder.append(this.b);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.US, "%.0f", new Object[]{Double.valueOf(this.a)}));
        stringBuilder.append(this.b);
        return stringBuilder.toString();
    }
}
