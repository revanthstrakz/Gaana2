package com.appsflyer;

final class p implements a {
    private a a = this;

    interface a {
        Class<?> b(String str) throws ClassNotFoundException;
    }

    enum d {
        UNITY("android_unity", "com.unity3d.player.UnityPlayer"),
        REACT_NATIVE("android_reactNative", "com.facebook.react.ReactApplication"),
        CORDOVA("android_cordova", "org.apache.cordova.CordovaActivity"),
        SEGMENT("android_segment", "com.segment.analytics.integrations.Integration"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        DEFAULT("android_native", "android_native");
        
        /* renamed from: ʻ */
        private String f13;
        /* renamed from: ॱॱ */
        private String f14;

        private d(String str, String str2) {
            this.f14 = str;
            this.f13 = str2;
        }
    }

    /* Access modifiers changed, original: final */
    public final String a() {
        for (d dVar : d.values()) {
            if (a(dVar.f13)) {
                return dVar.f14;
            }
        }
        return d.DEFAULT.f14;
    }

    p() {
    }

    /* Access modifiers changed, original: final */
    public final boolean a(String str) {
        try {
            this.a.b(str);
            StringBuilder stringBuilder = new StringBuilder("Class: ");
            stringBuilder.append(str);
            stringBuilder.append(" is found.");
            AFLogger.a(stringBuilder.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            AFLogger.a(th.getMessage(), th);
            return false;
        }
    }

    public final Class<?> b(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
