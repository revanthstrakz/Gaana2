package net.hockeyapp.android.objects;

import java.io.Serializable;

public class FeedbackResponse implements Serializable {
    private static final long serialVersionUID = -1093570359639034766L;
    private String a;
    private Feedback b;
    private String c;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public Feedback b() {
        return this.b;
    }

    public void a(Feedback feedback) {
        this.b = feedback;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }
}
