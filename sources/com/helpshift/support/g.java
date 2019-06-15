package com.helpshift.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.helpshift.support.h.d;
import com.helpshift.support.model.FaqSearchIndex;
import com.helpshift.support.search.a.b;
import com.helpshift.util.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.JSONArray;
import org.json.JSONException;

public final class g {
    private static FaqSearchIndex a;
    private final String b = "fullIndex.db";
    private Context c;
    private SharedPreferences d;

    public g(Context context) {
        this.c = context;
        this.d = context.getSharedPreferences("HSJsonData", 0);
    }

    private JSONArray j(String str) throws JSONException {
        return new JSONArray(this.d.getString(str, "[]"));
    }

    private String k(String str) {
        return this.d.getString(str, "");
    }

    private Integer l(String str) {
        return a(str, 0);
    }

    private Integer a(String str, int i) {
        return Integer.valueOf(this.d.getInt(str, i));
    }

    public Float a(String str) {
        return Float.valueOf(this.d.getFloat(str, 0.0f));
    }

    public boolean b(String str) {
        return this.d.contains(str);
    }

    public Boolean c(String str) {
        return Boolean.valueOf(this.d.getBoolean(str, false));
    }

    private Long m(String str) {
        return Long.valueOf(this.d.getLong(str, 0));
    }

    private void a(String str, JSONArray jSONArray) {
        Editor edit = this.d.edit();
        edit.putString(str, jSONArray.toString());
        edit.apply();
    }

    private void a(String str, String str2) {
        Editor edit = this.d.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    private void a(String str, Integer num) {
        Editor edit = this.d.edit();
        edit.putInt(str, num.intValue());
        edit.apply();
    }

    private void a(String str, Boolean bool) {
        Editor edit = this.d.edit();
        edit.putBoolean(str, bool.booleanValue());
        edit.apply();
    }

    private void a(String str, Long l) {
        Editor edit = this.d.edit();
        edit.putLong(str, l.longValue());
        edit.apply();
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        new d().b();
        this.c.deleteFile("tfidf.db");
        Editor edit = this.d.edit();
        edit.clear();
        edit.apply();
    }

    /* Access modifiers changed, original: protected */
    public String b() {
        return k("apiKey");
    }

    /* Access modifiers changed, original: protected */
    public void d(String str) {
        a("apiKey", str);
    }

    /* Access modifiers changed, original: protected */
    public String c() {
        return k("domain");
    }

    /* Access modifiers changed, original: protected */
    public void e(String str) {
        a("domain", str);
    }

    /* Access modifiers changed, original: protected */
    public String d() {
        return k("appId");
    }

    /* Access modifiers changed, original: protected */
    public void f(String str) {
        a("appId", str);
    }

    /* Access modifiers changed, original: protected */
    public String e() {
        return k("libraryVersion");
    }

    /* Access modifiers changed, original: protected */
    public void g(String str) {
        a("libraryVersion", str);
    }

    /* Access modifiers changed, original: protected */
    public String f() {
        return k("applicationVersion");
    }

    /* Access modifiers changed, original: protected */
    public void h(String str) {
        a("applicationVersion", str);
    }

    /* Access modifiers changed, original: protected */
    public int g() {
        return l("reviewCounter").intValue();
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        a("reviewCounter", Integer.valueOf(i));
    }

    /* Access modifiers changed, original: protected */
    public int h() {
        return l("launchReviewCounter").intValue();
    }

    /* Access modifiers changed, original: protected */
    public void b(int i) {
        a("launchReviewCounter", Integer.valueOf(i));
    }

    /* Access modifiers changed, original: protected */
    public JSONArray i() throws JSONException {
        return j("cachedImages");
    }

    /* Access modifiers changed, original: protected */
    public void a(JSONArray jSONArray) {
        a("cachedImages", jSONArray);
    }

    /* Access modifiers changed, original: protected */
    public void a(FaqSearchIndex faqSearchIndex) {
        a = faqSearchIndex;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.c.openFileOutput("fullIndex.db", 0));
            objectOutputStream.writeObject(faqSearchIndex);
            objectOutputStream.flush();
            objectOutputStream.close();
            m();
        } catch (Exception e) {
            l.a("HelpShiftDebug", "store index", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void j() throws IOException, ClassCastException, ClassNotFoundException {
        if (a == null) {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.c.openFileInput("fullIndex.db"));
            a = (FaqSearchIndex) objectInputStream.readObject();
            objectInputStream.close();
        }
    }

    /* Access modifiers changed, original: protected */
    public FaqSearchIndex k() {
        return a;
    }

    /* Access modifiers changed, original: protected */
    public Boolean l() {
        return c("dbFlag");
    }

    /* Access modifiers changed, original: protected */
    public void m() {
        a("dbFlag", Boolean.valueOf(true));
    }

    /* Access modifiers changed, original: protected */
    public void n() {
        a("dbFlag", Boolean.valueOf(false));
    }

    /* Access modifiers changed, original: protected */
    public void o() {
        a = null;
        b.b().a();
        this.c.deleteFile("fullIndex.db");
        n();
    }

    /* Access modifiers changed, original: 0000 */
    public long p() {
        return m("lastErrorReportedTime").longValue();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(long j) {
        a("lastErrorReportedTime", Long.valueOf(j));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean q() {
        return a == null;
    }

    public String i(String str) {
        return this.d.getString(str, "");
    }
}
