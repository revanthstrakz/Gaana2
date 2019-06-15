package com.til.colombia.android.adapters;

import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.g;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.bl;
import java.lang.reflect.InvocationTargetException;

public abstract class a {
    public abstract void createCache();

    public abstract void requestAd(bl blVar, String str, String str2, AdListener adListener);

    public abstract boolean returnCacheAd(bl blVar, String str, String str2, AdListener adListener);

    private static final a getAdapter(String str) {
        Object obj = -1;
        try {
            int hashCode = str.hashCode();
            if (hashCode != -771886824) {
                if (hashCode == 1617929173) {
                    if (str.equals(g.c)) {
                        obj = null;
                    }
                }
            } else if (str.equals(g.d)) {
                obj = 1;
            }
            switch (obj) {
                case null:
                    if (Class.forName(g.a) != null) {
                        return getNetworkAdapter(g.c);
                    }
                    return unknownAdNetwork(str);
                case 1:
                    if (Class.forName(g.b) != null) {
                        return getNetworkAdapter(g.d);
                    }
                    return unknownAdNetwork(str);
                default:
                    return unknownAdNetwork(str);
            }
        } catch (ClassNotFoundException unused) {
            return unknownAdNetwork(str);
        } catch (VerifyError e) {
            Log.a(i.f, "Caught VerifyError", e);
            return unknownAdNetwork(str);
        }
    }

    private static final a unknownAdNetwork(String str) {
        String str2 = i.f;
        StringBuilder stringBuilder = new StringBuilder("Unsupported networkAdapter type: ");
        stringBuilder.append(str);
        Log.a(str2, stringBuilder.toString());
        return null;
    }

    private static final a getNetworkAdapter(String str) {
        try {
            return (a) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            Log.a(i.f, "", e);
        } catch (SecurityException e2) {
            Log.a(i.f, "", e2);
        } catch (NoSuchMethodException e3) {
            Log.a(i.f, "", e3);
        } catch (InvocationTargetException e4) {
            Log.a(i.f, "", e4);
        } catch (IllegalAccessException e5) {
            Log.a(i.f, "", e5);
        } catch (InstantiationException e6) {
            Log.a(i.f, "", e6);
        }
        return null;
    }

    public static final a getInstance(String str) throws Throwable {
        if (com.til.colombia.android.internal.a.g().containsKey(str) && com.til.colombia.android.internal.a.g().get(str) != null) {
            return (a) com.til.colombia.android.internal.a.g().get(str);
        }
        a adapter = getAdapter(str);
        if (adapter != null) {
            com.til.colombia.android.internal.a.g().put(str, adapter);
            Log.a(i.f, "Valid adapter, calling requestAd()");
            return adapter;
        }
        throw new Exception("Invalid adapter");
    }
}
