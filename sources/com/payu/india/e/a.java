package com.payu.india.e;

import android.os.AsyncTask;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PayuResponse;
import com.payu.india.Model.PostData;
import com.payu.india.b.c;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends AsyncTask<PayuConfig, String, PayuResponse> {
    com.payu.india.b.a a;
    PayuConfig b;
    PayuResponse c;

    class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            String str = "";
            Matcher matcher = Pattern.compile("var2=([^&]*)").matcher(a.this.b.a());
            while (matcher.find()) {
                str = matcher.group(1);
            }
            String str2 = "";
            Matcher matcher2 = Pattern.compile("var1=([^&]*)").matcher(a.this.b.a());
            while (matcher2.find()) {
                str2 = matcher2.group(1);
            }
            if (!(str.equals("") || str2.equals(""))) {
                c a = com.payu.india.a.a.a();
                if (a != null) {
                    a.a(str, str2);
                }
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            a.this.a.b(a.this.c);
        }
    }

    public a(com.payu.india.b.a aVar) {
        this.a = aVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public PayuResponse doInBackground(PayuConfig... payuConfigArr) {
        this.b = payuConfigArr[0];
        PayuResponse payuResponse = new PayuResponse();
        PostData postData = new PostData();
        try {
            URL url;
            PayuConfig payuConfig = payuConfigArr[0];
            switch (payuConfig.b()) {
                case 0:
                    url = new URL("https://info.payu.in/merchant/postservice.php?form=2");
                    break;
                case 1:
                    url = new URL("https://mobiletest.payu.in/merchant/postservice?form=2");
                    break;
                case 2:
                    url = new URL("https://test.payu.in/merchant/postservice?form=2");
                    break;
                case 3:
                    url = new URL("https://mobiledev.payu.in/merchant/postservice?form=2");
                    break;
                default:
                    url = new URL("https://info.payu.in/merchant/postservice.php?form=2");
                    break;
            }
            byte[] bytes = payuConfig.a().getBytes("UTF-8");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(HttpMethods.POST);
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
            httpURLConnection.setDoOutput(true);
            httpURLConnection.getOutputStream().write(bytes);
            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    stringBuffer.append(new String(bArr, 0, read));
                } else {
                    JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                    if (jSONObject.has("msg")) {
                        postData.b(jSONObject.getString("msg"));
                    }
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 1) {
                        postData.a(0);
                        postData.a("SUCCESS");
                    } else {
                        postData.a(5017);
                        postData.a("ERROR");
                    }
                    payuResponse.a(postData);
                    return payuResponse;
                }
            }
        } catch (MalformedURLException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (ProtocolException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IOException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (JSONException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(PayuResponse payuResponse) {
        super.onPostExecute(payuResponse);
        this.c = payuResponse;
        new a().execute(new Void[0]);
    }
}
