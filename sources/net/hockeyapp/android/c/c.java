package net.hockeyapp.android.c;

import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public abstract class c<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    protected static String a(HttpURLConnection httpURLConnection) throws IOException {
        InputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        String a = a(bufferedInputStream);
        bufferedInputStream.close();
        return a;
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(readLine);
                    stringBuilder2.append("\n");
                    stringBuilder.append(stringBuilder2.toString());
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            } catch (IOException e2) {
                ThrowableExtension.printStackTrace(e2);
                inputStream.close();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
                throw th;
            }
        }
        inputStream.close();
        return stringBuilder.toString();
    }
}
