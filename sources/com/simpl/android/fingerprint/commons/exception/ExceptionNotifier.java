package com.simpl.android.fingerprint.commons.exception;

import android.content.Context;
import com.simpl.android.fingerprint.commons.models.Attribute;
import com.simpl.android.fingerprint.commons.utils.VersionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionNotifier {
    private static ExceptionNotifier sharedInstance;
    private String identifier;

    private ExceptionNotifier() {
        this.identifier = "";
    }

    private ExceptionNotifier(Context context, String str) {
        this.identifier = str;
        a.a(context, "fb68fde12f8d24307fa351f463d75d12", "production");
        Map hashMap = new HashMap();
        hashMap.put("sdk-version", VersionUtil.getSdkVersion());
        a.a(hashMap);
    }

    public static ExceptionNotifier getSharedInstance() {
        if (sharedInstance != null) {
            return sharedInstance;
        }
        ExceptionNotifier exceptionNotifier = new ExceptionNotifier();
        sharedInstance = exceptionNotifier;
        return exceptionNotifier;
    }

    public static void init(Context context, String str) {
        sharedInstance = new ExceptionNotifier(context, str);
    }

    public static void updateEnvironment(String str) {
        a.a(str);
    }

    public void send(Throwable th) {
        send(th, new ArrayList());
    }

    public void send(Throwable th, Attribute attribute) {
        List arrayList = new ArrayList();
        if (attribute != null) {
            arrayList.add(attribute);
        }
        send(th, arrayList);
    }

    public void send(Throwable th, List<Attribute> list) {
        Map hashMap = new HashMap();
        hashMap.put("User", this.identifier);
        for (Attribute attribute : list) {
            if (attribute.getKey() != null) {
                hashMap.put(attribute.getKey(), attribute.getValue());
            }
        }
        a.a(th, hashMap);
    }
}
