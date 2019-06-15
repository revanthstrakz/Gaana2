package com.helpshift.common.domain.network;

import com.helpshift.common.domain.e;
import com.helpshift.common.exception.NetworkException;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.network.Method;
import com.helpshift.common.platform.network.c;
import com.helpshift.common.platform.network.f;
import com.helpshift.common.platform.network.g;
import com.helpshift.common.platform.network.i;
import com.helpshift.common.platform.p;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.mime.MIME;

public class m extends a {
    public /* bridge */ /* synthetic */ g c(Map map) {
        return super.c(map);
    }

    public m(String str, e eVar, p pVar) {
        super(str, eVar, pVar);
    }

    /* Access modifiers changed, original: 0000 */
    public f a(Map<String, String> map) {
        String a = a(new File((String) map.get("screenshot")).getPath());
        if (b(a)) {
            return new i(Method.POST, a(), a(Method.POST, b(map)), a, c(), 30000);
        }
        throw RootAPIException.a(null, NetworkException.UNSUPPORTED_MIME_TYPE);
    }

    private String a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            String guessContentTypeFromStream = URLConnection.guessContentTypeFromStream(fileInputStream);
            if (guessContentTypeFromStream == null) {
                guessContentTypeFromStream = URLConnection.guessContentTypeFromName(str);
            }
            fileInputStream.close();
            return guessContentTypeFromStream;
        } catch (IOException unused) {
            return null;
        }
    }

    private boolean b(String str) {
        return new HashSet(Arrays.asList(new String[]{"image/jpeg", "image/png", "image/gif", "image/x-png", "image/x-citrix-pjpeg", "image/x-citrix-gif", "image/pjpeg"})).contains(str);
    }

    private List<c> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c("Connection", "Keep-Alive"));
        arrayList.add(new c(MIME.CONTENT_TYPE, "multipart/form-data;boundary=*****"));
        return arrayList;
    }
}
