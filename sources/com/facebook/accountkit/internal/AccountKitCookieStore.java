package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class AccountKitCookieStore implements CookieStore {
    private static final List<String> ALLOW_PERSIST_COOKIE_NAMES = new ArrayList();
    private static final List<String> ALLOW_PERSIST_DOMAINS = new ArrayList();
    private static final String SP_COOKIE_STORE = "cookieStore";
    private static final String SP_KEY_DELIMITER = "|";
    private static final String SP_KEY_DELIMITER_REGEX = "\\|";
    private final Map<URI, List<HttpCookie>> map = new HashMap();
    private final SharedPreferences sharedPreferences;

    static {
        ALLOW_PERSIST_DOMAINS.add(".accountkit.com");
        ALLOW_PERSIST_COOKIE_NAMES.add("aksb");
    }

    public AccountKitCookieStore(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SP_COOKIE_STORE, 0);
        loadFromSharedPreferences();
    }

    private void loadFromSharedPreferences() {
        for (Entry entry : this.sharedPreferences.getAll().entrySet()) {
            try {
                URI uri = new URI(((String) entry.getKey()).split(SP_KEY_DELIMITER_REGEX, 2)[0]);
                HttpCookie decode = new SerializableHttpCookie().decode((String) entry.getValue());
                if (decode != null) {
                    List list = (List) this.map.get(uri);
                    if (list == null) {
                        list = new ArrayList();
                        this.map.put(uri, list);
                    }
                    list.add(decode);
                }
            } catch (URISyntaxException unused) {
            }
        }
    }

    private void saveToSharedPreferences(URI uri, HttpCookie httpCookie) {
        if (ALLOW_PERSIST_DOMAINS.contains(httpCookie.getDomain()) && ALLOW_PERSIST_COOKIE_NAMES.contains(httpCookie.getName())) {
            Editor edit = this.sharedPreferences.edit();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(uri.toString());
            stringBuilder.append(SP_KEY_DELIMITER);
            stringBuilder.append(httpCookie.getName());
            edit.putString(stringBuilder.toString(), new SerializableHttpCookie().encode(httpCookie));
            edit.apply();
        }
    }

    public synchronized void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            try {
                throw new NullPointerException("cookie == null");
            } catch (Throwable th) {
            }
        } else {
            uri = cookiesUri(uri);
            List list = (List) this.map.get(uri);
            if (list == null) {
                list = new ArrayList();
                this.map.put(uri, list);
            } else {
                list.remove(httpCookie);
            }
            list.add(httpCookie);
            saveToSharedPreferences(uri, httpCookie);
        }
    }

    private URI cookiesUri(URI uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URI("http", uri.getHost(), null, null);
        } catch (URISyntaxException unused) {
            return uri;
        }
    }

    public synchronized List<HttpCookie> get(URI uri) {
        ArrayList arrayList;
        if (uri == null) {
            try {
                throw new NullPointerException("uri == null");
            } catch (Throwable th) {
            }
        } else {
            Iterator it;
            arrayList = new ArrayList();
            List list = (List) this.map.get(uri);
            if (list != null) {
                it = list.iterator();
                while (it.hasNext()) {
                    HttpCookie httpCookie = (HttpCookie) it.next();
                    if (httpCookie.hasExpired()) {
                        it.remove();
                    } else {
                        arrayList.add(httpCookie);
                    }
                }
            }
            for (Entry entry : this.map.entrySet()) {
                if (!uri.equals(entry.getKey())) {
                    Iterator it2 = ((List) entry.getValue()).iterator();
                    while (it2.hasNext()) {
                        HttpCookie httpCookie2 = (HttpCookie) it2.next();
                        if (HttpCookie.domainMatches(httpCookie2.getDomain(), uri.getHost())) {
                            if (httpCookie2.hasExpired()) {
                                it2.remove();
                            } else if (!arrayList.contains(httpCookie2)) {
                                arrayList.add(httpCookie2);
                            }
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<HttpCookie> getCookies() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (List it : this.map.values()) {
            Iterator it2 = it.iterator();
            while (it2.hasNext()) {
                HttpCookie httpCookie = (HttpCookie) it2.next();
                if (httpCookie.hasExpired()) {
                    it2.remove();
                } else if (!arrayList.contains(httpCookie)) {
                    arrayList.add(httpCookie);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<URI> getURIs() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.map.keySet());
        arrayList.remove(null);
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized boolean remove(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            try {
                throw new NullPointerException("cookie == null");
            } catch (Throwable th) {
            }
        } else {
            List list = (List) this.map.get(cookiesUri(uri));
            if (list == null) {
                return false;
            }
            return list.remove(httpCookie);
        }
    }

    public synchronized boolean removeAll() {
        int isEmpty;
        isEmpty = this.map.isEmpty() ^ 1;
        this.map.clear();
        return isEmpty;
    }
}
