package com.payu.custombrowser;

import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONObject;

class f extends AsyncTask<String, Void, Void> {
    f() {
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public Void doInBackground(String... strArr) {
        String str = strArr[0];
        ClassLoader classLoader = Bank.class.getClassLoader();
        try {
            if (!isCancelled()) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("card_token") && jSONObject.has("merchant_hash")) {
                    Object invoke = classLoader.loadClass("com.payu.india.CallBackHandler.OnetapCallback").getDeclaredMethod("getOneTapCallback", new Class[0]).invoke(null, new Object[0]);
                    invoke.getClass().getDeclaredMethod("saveOneClickHash", new Class[]{String.class, String.class}).invoke(invoke, new Object[]{jSONObject.getString("card_token"), jSONObject.getString("merchant_hash")});
                }
            }
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchMethodException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (Exception e3) {
            ThrowableExtension.printStackTrace(e3);
        }
        return null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
    }
}
