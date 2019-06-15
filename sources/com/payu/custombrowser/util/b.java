package com.payu.custombrowser.util;

import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.payu.custombrowser.bean.a;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.entity.mime.MIME;

public class b extends AsyncTask<a, String, String> {
    private com.payu.custombrowser.b.a a;

    private b() {
    }

    public b(com.payu.custombrowser.b.a aVar) {
        this.a = aVar;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public String doInBackground(a... aVarArr) {
        a aVar = aVarArr[0];
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(aVar.b()).openConnection();
            httpsURLConnection.setRequestMethod(aVar.a());
            httpsURLConnection.setSSLSocketFactory(new h());
            httpsURLConnection.setRequestProperty(MIME.CONTENT_TYPE, aVar.d());
            String str = "Content-Length";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(aVar.c() != null ? aVar.c().length() : 0);
            httpsURLConnection.setRequestProperty(str, stringBuilder.toString());
            httpsURLConnection.getOutputStream().write(aVar.c().getBytes());
            InputStream inputStream = httpsURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return stringBuffer.toString();
                }
                stringBuffer.append(new String(bArr, 0, read));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return e.getMessage().toString();
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        this.a.a(str);
    }
}
