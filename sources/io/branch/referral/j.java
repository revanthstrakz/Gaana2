package io.branch.referral;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.applinks.AppLinkData;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class j {

    public interface a {
        void a(String str);
    }

    public static Boolean a(Context context, final a aVar) {
        boolean z = false;
        try {
            Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", new Class[]{Context.class}).invoke(null, new Object[]{context});
            final Class cls = Class.forName("com.facebook.applinks.AppLinkData");
            Method method = cls.getMethod("fetchDeferredAppLinkData", new Class[]{Context.class, String.class, Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler")});
            AnonymousClass1 anonymousClass1 = new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (method.getName().equals("onDeferredAppLinkDataFetched") && objArr[0] != null) {
                        Bundle bundle = (Bundle) Bundle.class.cast(cls.getMethod("getArgumentBundle", new Class[0]).invoke(cls.cast(objArr[0]), new Object[0]));
                        String string = bundle != null ? bundle.getString(AppLinkData.ARGUMENTS_NATIVE_URL) : null;
                        if (aVar != null) {
                            aVar.a(string);
                        }
                    } else if (aVar != null) {
                        aVar.a(null);
                    }
                    return null;
                }
            };
            Object newProxyInstance = Proxy.newProxyInstance(r2.getClassLoader(), new Class[]{r2}, anonymousClass1);
            if (!TextUtils.isEmpty(context.getString(context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName())))) {
                method.invoke(null, new Object[]{context, context.getString(context.getResources().getIdentifier("facebook_app_id", "string", context.getPackageName())), newProxyInstance});
                z = true;
            }
        } catch (Exception unused) {
        }
        return Boolean.valueOf(z);
    }
}
