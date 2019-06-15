package com.utilities;

public class l {
    public long a;
    public long b;
    public int c;

    public void a(long j, double d, double d2) {
        double d3 = (double) (((float) (j - 946728000000L)) / 8.64E7f);
        double d4 = 6.240059852600098d + (0.017201969400048256d * d3);
        double sin = (((((0.03341960161924362d * Math.sin(d4)) + d4) + (3.4906598739326E-4d * Math.sin(2.0d * d4))) + (5.236000106378924E-6d * Math.sin(3.0d * d4))) + 1.7965930700302124d) + 3.1415927410125732d;
        double d5 = (-d2) / 360.0d;
        d3 = ((((double) (((float) Math.round((d3 - 8.999999845400453E-4d) - d5)) + 9.0E-4f)) + d5) + (0.0052999998442828655d * Math.sin(d4))) + (-0.006899999920278788d * Math.sin(2.0d * sin));
        double asin = Math.asin(Math.sin(sin) * Math.sin(0.4092797040939331d));
        d4 = 0.01745329238474369d * d;
        sin = (Math.sin(-0.10471975803375244d) - (Math.sin(d4) * Math.sin(asin))) / (Math.cos(d4) * Math.cos(asin));
        if (sin >= 1.0d) {
            this.c = 1;
            this.a = -1;
            this.b = -1;
        } else if (sin <= -1.0d) {
            this.c = 0;
            this.a = -1;
            this.b = -1;
        } else {
            d4 = (double) ((float) (Math.acos(sin) / 6.283185307179586d));
            this.a = Math.round((d3 + d4) * 8.64E7d) + 946728000000L;
            this.b = Math.round((d3 - d4) * 8.64E7d) + 946728000000L;
            if (this.b >= j || this.a <= j) {
                this.c = 1;
            } else {
                this.c = 0;
            }
        }
    }
}
