package net.hockeyapp.android.objects;

public class b {
    private String a;
    private String b;
    private String c;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(b.class.getSimpleName());
        stringBuilder.append("\n");
        stringBuilder.append("userDescription ");
        stringBuilder.append(this.a);
        stringBuilder.append("\n");
        stringBuilder.append("userEmail       ");
        stringBuilder.append(this.b);
        stringBuilder.append("\n");
        stringBuilder.append("userID          ");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }
}
