package com.payu.india.e;

import android.os.AsyncTask;
import com.google.api.client.http.HttpMethods;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.india.Model.CardInformation;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PayuResponse;
import com.payu.india.Model.PostData;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends AsyncTask<PayuConfig, String, PayuResponse> {
    com.payu.india.b.b a;

    public b(com.payu.india.b.b bVar) {
        this.a = bVar;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public PayuResponse doInBackground(PayuConfig... payuConfigArr) {
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
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        postData.a(5017);
                        postData.a("ERROR");
                    } else {
                        CardInformation cardInformation = new CardInformation();
                        cardInformation.a(Boolean.valueOf(jSONObject.getString("isDomestic").contentEquals("Y")));
                        cardInformation.a(jSONObject.getString("issuingBank"));
                        cardInformation.c(jSONObject.getString("cardType"));
                        cardInformation.b(jSONObject.getString("cardCategory"));
                        payuResponse.a(cardInformation);
                    }
                    payuResponse.a(postData);
                    return payuResponse;
                }
            }
        } catch (MalformedURLException | ProtocolException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException | JSONException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(PayuResponse payuResponse) {
        super.onPostExecute(payuResponse);
        this.a.c(payuResponse);
    }
}
