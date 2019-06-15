package com.payu.custombrowser;

import android.app.Activity;
import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.CustomBrowserResultData;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class e {
    InvocationHandler a = new InvocationHandler() {
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("method.getName() ");
            stringBuilder.append(method.getName());
            c.b("testsamsung", stringBuilder.toString());
            for (Object append : objArr) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("args[0] ");
                stringBuilder2.append(append);
                c.b("testsamsung", stringBuilder2.toString());
            }
            PayUCustomBrowserCallback a;
            if (method.getName().equalsIgnoreCase(CBConstant.SAMSUNGPAY_SUCCESS)) {
                a = e.this.b;
                stringBuilder = new StringBuilder();
                stringBuilder.append(objArr[0]);
                stringBuilder.append("");
                a.onPaymentSuccess(stringBuilder.toString(), null);
            } else if (method.getName().equalsIgnoreCase(CBConstant.SAMSUNGPAY_FAILURE)) {
                a = e.this.b;
                stringBuilder = new StringBuilder();
                stringBuilder.append(objArr[0]);
                stringBuilder.append("");
                a.onPaymentFailure(stringBuilder.toString(), null);
            } else if (method.getName().equalsIgnoreCase(CBConstant.SAMSUNGPAY_INIT_SUCCESS)) {
                CustomBrowserResultData customBrowserResultData = new CustomBrowserResultData();
                customBrowserResultData.setSamsungPayVpa((String) objArr[0]);
                e.this.b.isPaymentOptionAvailable(customBrowserResultData);
            } else if (method.getName().equalsIgnoreCase(CBConstant.SAMSUNGPAY_INIT_FAILURE)) {
                a = e.this.b;
                stringBuilder = new StringBuilder();
                stringBuilder.append(objArr[0]);
                stringBuilder.append("");
                int parseInt = Integer.parseInt(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                stringBuilder.append(objArr[1]);
                stringBuilder.append("");
                a.onCBErrorReceived(parseInt, stringBuilder.toString());
            }
            return null;
        }
    };
    private PayUCustomBrowserCallback b;
    private ClassLoader c;
    private Constructor d;
    private Class e;
    private Object f;

    /* Access modifiers changed, original: 0000 */
    public void a(Activity activity, CustomBrowserConfig customBrowserConfig) {
        try {
            if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
                this.b = b.SINGLETON.getPayuCustomBrowserCallback();
            }
            this.f.getClass().getMethod("makePayment", new Class[]{Activity.class, String.class}).invoke(this.f, new Object[]{activity, customBrowserConfig.getPayuPostData()});
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Context context, String str, String str2, String str3) {
        if (!(b.SINGLETON == null || b.SINGLETON.getPayuCustomBrowserCallback() == null)) {
            this.b = b.SINGLETON.getPayuCustomBrowserCallback();
        }
        try {
            this.c = e.class.getClassLoader();
            this.e = this.c.loadClass("com.payu.samsungpay.SamsungWrapper");
            this.d = this.e.getDeclaredConstructor(new Class[]{InvocationHandler.class});
            this.d.setAccessible(true);
            this.f = this.d.newInstance(new Object[]{this.a});
            this.f.getClass().getMethod("checkSamsungPayAvailability", new Class[]{String.class, String.class, String.class, Context.class}).invoke(this.f, new Object[]{str, str2, str3, context});
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}
