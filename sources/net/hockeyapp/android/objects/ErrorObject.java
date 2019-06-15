package net.hockeyapp.android.objects;

import java.io.Serializable;

public class ErrorObject implements Serializable {
    private static final long serialVersionUID = 1508110658372169868L;
    private String a;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }
}
