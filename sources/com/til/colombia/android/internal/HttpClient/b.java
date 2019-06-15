package com.til.colombia.android.internal.HttpClient;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.h;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public final class b implements CookieStore {
    private static final String a = "ColombiaCookieStore";
    private static final String b = "names";
    private static final String c = "cookie_";
    private static final String f = "ColombiaCookiePrefsFile3";
    private final SharedPreferences d;
    private final ConcurrentHashMap<String, HttpCookie> e = new ConcurrentHashMap();

    public final List<URI> getURIs() {
        return null;
    }

    public b(Context context) {
        int i = 0;
        this.d = context.getSharedPreferences(f, 0);
        String string = this.d.getString(b, null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                SharedPreferences sharedPreferences = this.d;
                StringBuilder stringBuilder = new StringBuilder(c);
                stringBuilder.append(str);
                String string2 = sharedPreferences.getString(stringBuilder.toString(), null);
                if (string2 != null) {
                    HttpCookie a = a(string2);
                    if (a != null) {
                        this.e.put(str, a);
                    }
                }
                i++;
            }
            Date date = new Date();
            a();
        }
    }

    public final void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie.getDomain().equalsIgnoreCase(h.h)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(httpCookie.getName());
            stringBuilder.append(httpCookie.getDomain());
            String stringBuilder2 = stringBuilder.toString();
            if (httpCookie.hasExpired()) {
                this.e.remove(stringBuilder2);
            } else {
                this.e.put(stringBuilder2, httpCookie);
            }
            Editor edit = this.d.edit();
            edit.putString(b, TextUtils.join(",", this.e.keySet()));
            StringBuilder stringBuilder3 = new StringBuilder(c);
            stringBuilder3.append(stringBuilder2);
            edit.putString(stringBuilder3.toString(), a(new SerializableCookie(httpCookie)));
            edit.apply();
        }
    }

    public final List<HttpCookie> get(URI uri) {
        return new ArrayList(this.e.values());
    }

    public final List<HttpCookie> getCookies() {
        return new ArrayList(this.e.values());
    }

    public final boolean remove(URI uri, HttpCookie httpCookie) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpCookie.getName());
        stringBuilder.append(httpCookie.getDomain());
        String stringBuilder2 = stringBuilder.toString();
        this.e.remove(stringBuilder2);
        Editor edit = this.d.edit();
        StringBuilder stringBuilder3 = new StringBuilder(c);
        stringBuilder3.append(stringBuilder2);
        edit.remove(stringBuilder3.toString());
        edit.commit();
        return false;
    }

    public final boolean removeAll() {
        Editor edit = this.d.edit();
        for (String str : this.e.keySet()) {
            StringBuilder stringBuilder = new StringBuilder(c);
            stringBuilder.append(str);
            edit.remove(stringBuilder.toString());
        }
        edit.remove(b);
        edit.apply();
        this.e.clear();
        return false;
    }

    private boolean a() {
        Editor edit = this.d.edit();
        boolean z = false;
        for (Entry entry : this.e.entrySet()) {
            String str = (String) entry.getKey();
            HttpCookie httpCookie = (HttpCookie) entry.getValue();
            if (httpCookie.hasExpired() || httpCookie.getMaxAge() == 0) {
                this.e.remove(str);
                StringBuilder stringBuilder = new StringBuilder(c);
                stringBuilder.append(str);
                edit.remove(stringBuilder.toString());
                z = true;
            }
        }
        if (z) {
            edit.putString(b, TextUtils.join(",", this.e.keySet()));
        }
        edit.apply();
        return z;
    }

    private String a(SerializableCookie serializableCookie) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableCookie);
            return a(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            Log.a(a, "IOException in encodeCookie", e);
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuilder.append('0');
            }
            stringBuilder.append(Integer.toHexString(i));
        }
        return stringBuilder.toString().toUpperCase(Locale.US);
    }

    private static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private HttpCookie a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject()).getCookie();
        } catch (IOException e) {
            Log.a(a, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e2) {
            Log.a(a, "ClassNotFoundException in decodeCookie", e2);
        }
        return null;
    }
}
