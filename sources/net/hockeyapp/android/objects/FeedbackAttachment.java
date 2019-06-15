package net.hockeyapp.android.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import net.hockeyapp.android.a;

public class FeedbackAttachment implements Serializable {
    private static final long serialVersionUID = 5059651319640956830L;
    private int a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String b() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.b);
        stringBuilder.append(this.a);
        return stringBuilder.toString();
    }

    public boolean d() {
        File a = a.a();
        if (!a.exists() || !a.isDirectory()) {
            return false;
        }
        File[] listFiles = a.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.equals(FeedbackAttachment.this.c());
            }
        });
        boolean z = true;
        if (listFiles == null || listFiles.length != 1) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(FeedbackAttachment.class.getSimpleName());
        stringBuilder.append("\n");
        stringBuilder.append("id         ");
        stringBuilder.append(this.a);
        stringBuilder.append("\n");
        stringBuilder.append("message id ");
        stringBuilder.append(this.b);
        stringBuilder.append("\n");
        stringBuilder.append("filename   ");
        stringBuilder.append(this.c);
        stringBuilder.append("\n");
        stringBuilder.append("url        ");
        stringBuilder.append(this.d);
        stringBuilder.append("\n");
        stringBuilder.append("createdAt  ");
        stringBuilder.append(this.e);
        stringBuilder.append("\n");
        stringBuilder.append("updatedAt  ");
        stringBuilder.append(this.f);
        return stringBuilder.toString();
    }
}
