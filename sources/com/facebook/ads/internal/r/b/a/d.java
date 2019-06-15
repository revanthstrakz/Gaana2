package com.facebook.ads.internal.r.b.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

class d {

    private static final class a implements Comparator<File> {
        private a() {
        }

        private int a(long j, long j2) {
            return j < j2 ? -1 : j == j2 ? 0 : 1;
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            return a(file.lastModified(), file2.lastModified());
        }
    }

    static void a(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("File ");
                stringBuilder.append(file);
                stringBuilder.append(" is not directory!");
                throw new IOException(stringBuilder.toString());
            }
        } else if (!file.mkdirs()) {
            throw new IOException(String.format(Locale.US, "Directory %s can't be created", new Object[]{file.getAbsolutePath()}));
        }
    }

    static List<File> b(File file) {
        LinkedList linkedList = new LinkedList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return linkedList;
        }
        List asList = Arrays.asList(listFiles);
        Collections.sort(asList, new a());
        return asList;
    }

    static void c(File file) {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!file.setLastModified(currentTimeMillis)) {
                d(file);
                if (file.lastModified() < currentTimeMillis) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Error set last modified date to ");
                    stringBuilder.append(file);
                    throw new IOException(stringBuilder.toString());
                }
            }
        }
    }

    static void d(File file) {
        long length = file.length();
        if (length == 0) {
            e(file);
            return;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
        long j = length - 1;
        randomAccessFile.seek(j);
        byte readByte = randomAccessFile.readByte();
        randomAccessFile.seek(j);
        randomAccessFile.write(readByte);
        randomAccessFile.close();
    }

    private static void e(File file) {
        if (!file.delete() || !file.createNewFile()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error recreate zero-size file ");
            stringBuilder.append(file);
            throw new IOException(stringBuilder.toString());
        }
    }
}
