package com.library.custom_glide;

import android.os.Environment;
import android.support.v4.content.ContextCompat;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.d;
import com.library.util.StorageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GlideFileLoader {
    private static List<String> arrList = new ArrayList();

    public static void add(String str) {
        arrList.add(str);
    }

    public static boolean contains(String str) {
        return arrList.contains(str.replaceAll("/", ""));
    }

    public static void read() {
        try {
            File file = getFile();
            if (file != null) {
                for (File file2 : file.listFiles()) {
                    if (file2.isFile()) {
                        add(file2.getName());
                    }
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static File getFile() {
        File file = new File(getPath());
        file.mkdirs();
        return file;
    }

    public static String getPath() {
        File file = ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0];
        if (file != null && file.isDirectory() && file.canRead()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append("/images");
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder2.append("/images");
        return stringBuilder2.toString();
    }

    public static boolean delete(final String str) {
        d.a(new Runnable() {
            public void run() {
                try {
                    String replaceAll = str.replaceAll("/", "");
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(GlideFileLoader.getPath());
                    stringBuilder.append("/");
                    stringBuilder.append(replaceAll);
                    StorageUtils.delete(new File(stringBuilder.toString()));
                    GlideFileLoader.arrList.remove(replaceAll);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
        return false;
    }

    public static String getFullFileName(String str) {
        for (int i = 0; i < arrList.size(); i++) {
            String str2 = (String) arrList.get(i);
            if (str2.contains(str)) {
                return str2;
            }
        }
        return "";
    }
}
