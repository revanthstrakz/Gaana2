package com.simpl.android.zeroClickSdk.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class a extends ContextWrapper {
    public static final String a = "a";

    public a(Context context) {
        super(context);
    }

    @SuppressLint({"NewApi"})
    private static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" ");
        stringBuilder.append(str2);
        str2 = stringBuilder.toString();
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str2.getBytes(StandardCharsets.UTF_8));
            String.format("pkg: %s -- hash: %s", new Object[]{str, Base64.encodeToString(Arrays.copyOfRange(instance.digest(), 0, 9), 3).substring(0, 11)});
            return Base64.encodeToString(Arrays.copyOfRange(instance.digest(), 0, 9), 3).substring(0, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.e(a, "hash:NoSuchAlgorithm", e);
            return null;
        }
    }

    public final ArrayList<String> a() {
        ArrayList arrayList = new ArrayList();
        try {
            String packageName = getPackageName();
            for (Signature toCharsString : getPackageManager().getPackageInfo(packageName, 64).signatures) {
                if (a(packageName, toCharsString.toCharsString()) != null) {
                    arrayList.add(String.format("%s", new Object[]{a(packageName, toCharsString.toCharsString())}));
                }
            }
        } catch (NameNotFoundException e) {
            Log.e(a, "Unable to find package to obtain hash.", e);
        }
        return arrayList;
    }
}
