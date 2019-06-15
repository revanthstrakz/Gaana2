package com.exoplayer2;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantLock;

public class d implements CookieStore {
    private Map<URI, List<HttpCookie>> a;
    private ReentrantLock b;
    private final boolean c;

    private URI a(URI uri) {
        return uri == null ? null : uri;
    }

    public d() {
        this(25);
    }

    public d(int i) {
        this.a = null;
        this.b = null;
        this.a = new HashMap();
        boolean z = false;
        this.b = new ReentrantLock(false);
        if (i <= 23) {
            z = true;
        }
        this.c = z;
    }

    public void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            throw new NullPointerException("cookie is null");
        }
        this.b.lock();
        try {
            if (httpCookie.getMaxAge() != 0) {
                a(this.a, a(uri), httpCookie);
            }
            this.b.unlock();
        } catch (Throwable th) {
            this.b.unlock();
        }
    }

    public List<HttpCookie> get(URI uri) {
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        List<HttpCookie> arrayList = new ArrayList();
        this.b.lock();
        try {
            a((List) arrayList, this.a, uri.toString());
            a((List) arrayList, this.a, a(uri));
            return arrayList;
        } finally {
            arrayList = this.b;
            arrayList.unlock();
        }
    }

    public List<HttpCookie> getCookies() {
        List<HttpCookie> arrayList = new ArrayList();
        this.b.lock();
        try {
            for (List it : this.a.values()) {
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
            return arrayList;
        } finally {
            Collections.unmodifiableList(arrayList);
            arrayList = this.b;
            arrayList.unlock();
        }
    }

    public List<URI> getURIs() {
        ArrayList arrayList = new ArrayList();
        this.b.lock();
        try {
            ArrayList arrayList2 = new ArrayList(this.a.keySet());
            arrayList2.remove(null);
            List<URI> unmodifiableList = Collections.unmodifiableList(arrayList2);
            return unmodifiableList;
        } finally {
            arrayList.addAll(this.a.keySet());
            this.b.unlock();
        }
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        if (httpCookie == null) {
            throw new NullPointerException("cookie is null");
        }
        this.b.lock();
        try {
            uri = a(uri);
            if (this.a.get(uri) != null) {
                List list = (List) this.a.get(uri);
                if (list != null) {
                    boolean remove = list.remove(httpCookie);
                    this.b.unlock();
                    return remove;
                }
            }
            this.b.unlock();
            return false;
        } catch (Throwable th) {
            this.b.unlock();
        }
    }

    public boolean removeAll() {
        this.b.lock();
        try {
            boolean isEmpty = this.a.isEmpty() ^ 1;
            this.a.clear();
            return isEmpty;
        } finally {
            this.b.unlock();
        }
    }

    private boolean a(String str, String str2) {
        boolean z = false;
        if (str == null || str2 == null) {
            return false;
        }
        boolean equalsIgnoreCase = ".local".equalsIgnoreCase(str);
        int indexOf = str.indexOf(46);
        if (indexOf == 0) {
            indexOf = str.indexOf(46, 1);
        }
        if (!equalsIgnoreCase && (indexOf == -1 || indexOf == str.length() - 1)) {
            return false;
        }
        if (str2.indexOf(46) == -1 && equalsIgnoreCase) {
            return true;
        }
        indexOf = str2.length() - str.length();
        if (indexOf == 0) {
            return str2.equalsIgnoreCase(str);
        }
        if (indexOf > 0) {
            str2 = str2.substring(indexOf);
            if (!this.c || str.startsWith(".")) {
                return str2.equalsIgnoreCase(str);
            }
            return false;
        } else if (indexOf != -1) {
            return false;
        } else {
            if (str.charAt(0) == '.' && str2.equalsIgnoreCase(str.substring(1))) {
                z = true;
            }
            return z;
        }
    }

    private void a(List<HttpCookie> list, Map<URI, List<HttpCookie>> map, String str) {
        ArrayList arrayList = new ArrayList();
        for (Entry value : map.entrySet()) {
            List<HttpCookie> list2 = (List) value.getValue();
            for (HttpCookie httpCookie : list2) {
                String domain = httpCookie.getDomain();
                if ((httpCookie.getVersion() == 0 && a(domain, str)) || (httpCookie.getVersion() == 1 && HttpCookie.domainMatches(domain, str))) {
                    if (httpCookie.hasExpired()) {
                        arrayList.add(httpCookie);
                    } else if (!list.contains(httpCookie)) {
                        list.add(httpCookie);
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                list2.remove((HttpCookie) it.next());
            }
            arrayList.clear();
        }
    }

    private <T extends Comparable<T>> void a(List<HttpCookie> list, Map<T, List<HttpCookie>> map, T t) {
        for (T t2 : map.keySet()) {
            if (t2 == t || (t2 != null && t.compareTo(t2) == 0)) {
                List list2 = (List) map.get(t2);
                if (list2 != null) {
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        HttpCookie httpCookie = (HttpCookie) it.next();
                        if (httpCookie.hasExpired()) {
                            it.remove();
                        } else if (!list.contains(httpCookie)) {
                            list.add(httpCookie);
                        }
                    }
                }
            }
        }
    }

    private <T> void a(Map<T, List<HttpCookie>> map, T t, HttpCookie httpCookie) {
        List list = (List) map.get(t);
        if (list != null) {
            list.remove(httpCookie);
            list.add(httpCookie);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(httpCookie);
        map.put(t, arrayList);
    }
}
