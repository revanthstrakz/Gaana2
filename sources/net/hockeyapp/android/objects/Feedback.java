package net.hockeyapp.android.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Feedback implements Serializable {
    private static final long serialVersionUID = 2590172806951065320L;
    private String a;
    private String b;
    private int c;
    private String d;
    private ArrayList<FeedbackMessage> e;

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public void c(String str) {
        this.d = str;
    }

    public ArrayList<FeedbackMessage> a() {
        return this.e;
    }

    public void a(ArrayList<FeedbackMessage> arrayList) {
        this.e = arrayList;
    }
}
