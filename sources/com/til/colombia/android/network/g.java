package com.til.colombia.android.network;

import com.android.volley.toolbox.f;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class g extends f {
    /* Access modifiers changed, original: protected|final */
    public final HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection createConnection = super.createConnection(url);
        String str = e.c;
        h.i();
        createConnection.setRequestProperty(str, h.j());
        createConnection.setInstanceFollowRedirects(false);
        return createConnection;
    }
}
