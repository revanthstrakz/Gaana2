package com.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.b.i;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.FontResponseData;
import com.services.l.aa;
import com.services.l.s;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class p {
    public static p a;
    private Handler b = null;
    private Context c = GaanaApplication.getContext();
    private HashMap<String, Typeface> d;
    private aa e = null;

    class a extends AsyncTask<String, String, String> {
        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: b */
        public void onProgressUpdate(String... strArr) {
        }

        a() {
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public String doInBackground(String... strArr) {
            File file = ContextCompat.getExternalFilesDirs(p.this.c, null)[0];
            try {
                URL url = new URL(strArr[0]);
                String str = strArr[1];
                URLConnection openConnection = url.openConnection();
                openConnection.connect();
                int contentLength = openConnection.getContentLength();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 8192);
                File file2 = new File(file.getAbsolutePath(), "fonts");
                file2.mkdirs();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(file2);
                stringBuilder.append(File.separator);
                stringBuilder.append(str);
                str = stringBuilder.toString();
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    long j2 = j + ((long) read);
                    String[] strArr2 = new String[1];
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("");
                    stringBuilder2.append((int) ((100 * j2) / ((long) contentLength)));
                    strArr2[0] = stringBuilder2.toString();
                    publishProgress(strArr2);
                    fileOutputStream.write(bArr, 0, read);
                    j = j2;
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                Typeface a = p.this.a(str, p.this.c);
                if (!(a == null || p.this.e == null)) {
                    p.this.e.onFontRetrieved(a);
                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }
    }

    public static p a() {
        if (a == null) {
            a = new p();
        }
        return a;
    }

    public void a(String str, aa aaVar, String... strArr) {
        b(str, aaVar);
    }

    private void b(String str, aa aaVar) {
        a(str, aaVar);
    }

    public void a(final String str, final aa aaVar) {
        this.e = aaVar;
        Typeface a = a(str);
        if (a == null || aaVar == null) {
            a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject instanceof FontResponseData) {
                        String fontLink = ((FontResponseData) businessObject).getFontLink();
                        p.this.e = aaVar;
                        new a().execute(new String[]{fontLink, str});
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    i.a(p.this.c.getAssets(), "fonts/Roboto-Regular.ttf");
                    if (p.this.e != null) {
                        p.this.e.onError("");
                    }
                }
            }, str);
        } else {
            aaVar.onFontRetrieved(a);
        }
    }

    private void a(s sVar, String str) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/font/detail?display_language=");
        stringBuilder.append(str);
        uRLManager.a(stringBuilder.toString());
        uRLManager.i(true);
        uRLManager.c(0);
        uRLManager.a(new HashMap());
        uRLManager.a(FontResponseData.class);
        com.i.i.a().a(sVar, uRLManager, Boolean.valueOf(true));
    }

    public Typeface a(String str) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        if (this.d.containsKey(str)) {
            return (Typeface) this.d.get(str);
        }
        try {
            Object a;
            File file = new File(ContextCompat.getExternalFilesDirs(this.c, null)[0].getAbsolutePath(), "fonts");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file);
            stringBuilder.append(File.separator);
            stringBuilder.append(str);
            String stringBuilder2 = stringBuilder.toString();
            if (!(str.toLowerCase().equals("hindi") || str.toLowerCase().equals("bhojpuri") || str.toLowerCase().equals("bengali"))) {
                if (!str.toLowerCase().equals("marathi")) {
                    a = a(stringBuilder2, this.c);
                    this.d.put("familyName", a);
                    if (a != null || this.e == null) {
                        return null;
                    }
                    this.e.onFontRetrieved(a);
                    return a;
                }
            }
            a = a("Mangal", this.c);
            this.d.put("familyName", a);
            if (a != null) {
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public Typeface a(String str, Context context) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                try {
                    return Typeface.createFromFile(file);
                } catch (Exception unused) {
                    return null;
                }
            }
        }
        return null;
    }
}
