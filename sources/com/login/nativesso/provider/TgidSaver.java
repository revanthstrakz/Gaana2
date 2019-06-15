package com.login.nativesso.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.login.nativesso.exception.ServerException;
import com.login.nativesso.i.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class TgidSaver extends ContentProvider {
    private static final HashMap<String, String> a = new HashMap();
    private Context b;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    static {
        a.put(".txt", "text/plain");
    }

    public String getType(Uri uri) {
        String uri2 = uri.toString();
        for (String str : a.keySet()) {
            if (uri2.endsWith(str)) {
                return (String) a.get(str);
            }
        }
        return null;
    }

    public boolean onCreate() {
        this.b = getContext();
        File file = new File(getContext().getFilesDir(), "tgid_file.txt");
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        int callingUid = Binder.getCallingUid();
        this.b = getContext();
        str = this.b.getPackageManager().getNameForUid(callingUid);
        File file = new File(getContext().getFilesDir(), uri.getPath());
        if (!file.exists()) {
            throw new FileNotFoundException(uri.getPath());
        } else if (c.b(this.b, str)) {
            return ParcelFileDescriptor.open(file, 805306368);
        } else {
            throw new ServerException("SERVER_ERROR");
        }
    }
}
