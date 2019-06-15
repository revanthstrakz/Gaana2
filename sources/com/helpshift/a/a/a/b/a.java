package com.helpshift.a.a.a.b;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.helpshift.util.l;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class a {
    private static final String a = "a";
    private Context b;
    private com.helpshift.a.a.a.a.a c;
    private String d;
    private boolean e;

    public a(Context context, com.helpshift.a.a.a.a.a aVar, String str, boolean z) {
        this.b = context;
        this.c = aVar;
        this.d = str;
        this.e = z;
    }

    public File a(String str) throws IOException {
        HashMap hashMap = (HashMap) this.c.a("kDownloadManagerCachedFiles");
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        String str2 = (String) hashMap.get(str);
        if (!TextUtils.isEmpty(str2)) {
            File file = new File(str2);
            if (file.exists()) {
                return file;
            }
        }
        File c = c(str);
        if (c == null) {
            throw new IOException("Error creating cache file");
        }
        hashMap.put(str, c.getAbsolutePath());
        this.c.a("kDownloadManagerCachedFiles", hashMap);
        return c;
    }

    public void b(String str) {
        HashMap hashMap = (HashMap) this.c.a("kDownloadManagerCachedFiles");
        hashMap.remove(str);
        this.c.a("kDownloadManagerCachedFiles", hashMap);
    }

    private File c(String str) {
        File filesDir;
        if (TextUtils.isEmpty(this.d) || !d("android.permission.WRITE_EXTERNAL_STORAGE")) {
            filesDir = this.b.getFilesDir();
        } else {
            filesDir = Environment.getExternalStoragePublicDirectory(this.d);
        }
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        if (this.e) {
            try {
                File file = new File(filesDir, ".nomedia");
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                Log.d(a, "Exception while creating no media file", e);
            }
        }
        if (!filesDir.canWrite()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Support_");
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append(str.substring(str.lastIndexOf("/") + 1));
        return new File(filesDir, stringBuilder.toString());
    }

    private boolean d(String str) {
        boolean z = false;
        try {
            if (this.b.getPackageManager().checkPermission(str, this.b.getPackageName()) == 0) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error checking for permission : ");
            stringBuilder.append(str);
            l.a(str2, stringBuilder.toString(), e);
            return false;
        }
    }
}
