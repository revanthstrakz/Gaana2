package com.library.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.cache.CacheResult;
import com.library.managers.cache.FileProperites;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

public class StorageUtils {
    private static final long LOW_STORAGE_THRESHOLD = 10485760;
    private static final String TAG = "StorageUtils";
    private static Boolean hasAppDirSet = Boolean.valueOf(false);
    private static String mAppStorageDirectory = Environment.getExternalStorageDirectory().toString();

    public static boolean isSdCardWrittenable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void setAppStorageDirectory(File file) {
        if (!hasAppDirSet.booleanValue() && file != null) {
            mAppStorageDirectory = file.toString();
            hasAppDirSet = Boolean.valueOf(true);
        }
    }

    public static long getAvailableStorage() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (RuntimeException unused) {
            return 0;
        }
    }

    public static int getAvailableStorageMB() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            return (int) ((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
        } catch (RuntimeException unused) {
            return 0;
        }
    }

    public static boolean checkAvailableStorage() {
        return getAvailableStorage() >= LOW_STORAGE_THRESHOLD;
    }

    public static boolean isSDCardPresent() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static Bitmap getLoacalBitmap(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String size(long j) {
        StringBuilder stringBuilder;
        if (j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED > 0) {
            float f = ((float) j) / 1048576.0f;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(decimalFormat.format((double) f));
            stringBuilder.append("MB");
            return stringBuilder.toString();
        }
        long j2 = j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (j2 > 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("");
            stringBuilder2.append(j2);
            stringBuilder2.append("KB");
            return stringBuilder2.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(j);
        stringBuilder.append("B");
        return stringBuilder.toString();
    }

    public static Boolean putBitmapInDisk(Bitmap bitmap, String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mAppStorageDirectory);
            stringBuilder.append(str2);
            File file = new File(stringBuilder.toString());
            if (!(file.exists() && file.isDirectory())) {
                file.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, URLEncoder.encode(str, "UTF-8")).getAbsolutePath());
            if (bitmap.hasAlpha()) {
                bitmap.compress(CompressFormat.PNG, 0, fileOutputStream);
            } else {
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }

    public static Boolean putBitmapInDisk(Bitmap bitmap, String str, String str2, FileProperites fileProperites) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mAppStorageDirectory);
            stringBuilder.append(str2);
            File file = new File(stringBuilder.toString());
            if (!(file.exists() && file.isDirectory())) {
                file.mkdir();
            }
            File file2 = new File(file, URLEncoder.encode(str, "UTF-8"));
            FileOutputStream fileOutputStream = new FileOutputStream(file2.getAbsolutePath());
            if (bitmap.hasAlpha()) {
                bitmap.compress(CompressFormat.PNG, 0, fileOutputStream);
            } else {
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            fileProperites.setSize(file2.length());
            return Boolean.valueOf(true);
        } catch (Exception unused) {
            return Boolean.valueOf(false);
        }
    }

    public static Bitmap getBitmapFromDisk(String str, String str2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mAppStorageDirectory);
            stringBuilder.append(str2);
            stringBuilder.append("/");
            stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
            str = stringBuilder.toString();
            if (!new File(str).exists()) {
                return null;
            }
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static Bitmap getBitmapFromDisk(String str, String str2, int i, int i2) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(mAppStorageDirectory);
            stringBuilder.append(str2);
            stringBuilder.append("/");
            stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
            str = stringBuilder.toString();
            if (!new File(str).exists()) {
                return null;
            }
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (OutOfMemoryError unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:17:0x003b, code skipped:
            return r4;
     */
    public static synchronized boolean delete(java.io.File r7) {
        /*
        r0 = com.library.util.StorageUtils.class;
        monitor-enter(r0);
        r1 = r7.exists();	 Catch:{ all -> 0x0043 }
        r2 = 0;
        r3 = 0;
        if (r1 == 0) goto L_0x003c;
    L_0x000b:
        r1 = r7.isDirectory();	 Catch:{ all -> 0x0043 }
        r4 = 1;
        if (r1 == 0) goto L_0x0028;
    L_0x0012:
        r1 = r7.listFiles();	 Catch:{ all -> 0x0043 }
        r5 = r1.length;	 Catch:{ all -> 0x0043 }
    L_0x0017:
        if (r3 >= r5) goto L_0x0023;
    L_0x0019:
        r6 = r1[r3];	 Catch:{ all -> 0x0043 }
        r6 = delete(r6);	 Catch:{ all -> 0x0043 }
        r4 = r4 & r6;
        r3 = r3 + 1;
        goto L_0x0017;
    L_0x0023:
        r1 = r7.delete();	 Catch:{ all -> 0x0043 }
        r4 = r4 & r1;
    L_0x0028:
        r1 = r7.isFile();	 Catch:{ all -> 0x0043 }
        if (r1 == 0) goto L_0x0033;
    L_0x002e:
        r7 = r7.delete();	 Catch:{ all -> 0x0043 }
        r4 = r4 & r7;
    L_0x0033:
        if (r4 != 0) goto L_0x003a;
    L_0x0035:
        r7 = "Delete failed;";
        android.util.Log.e(r2, r7);	 Catch:{ all -> 0x0043 }
    L_0x003a:
        monitor-exit(r0);
        return r4;
    L_0x003c:
        r7 = "File does not exist.";
        android.util.Log.e(r2, r7);	 Catch:{ all -> 0x0043 }
        monitor-exit(r0);
        return r3;
    L_0x0043:
        r7 = move-exception;
        monitor-exit(r0);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.library.util.StorageUtils.delete(java.io.File):boolean");
    }

    public static Boolean deletePercentageContent(File file, int i) {
        if (file.isDirectory()) {
            long fileSize = (getFileSize(file) * ((long) i)) / 100;
            long j = 0;
            File[] listFiles = file.listFiles();
            i = listFiles.length;
            int i2 = 0;
            while (i2 < i) {
                File file2 = listFiles[i2];
                if (j >= fileSize) {
                    break;
                }
                long length = j + file2.length();
                file2.delete();
                i2++;
                j = length;
            }
        }
        return Boolean.valueOf(true);
    }

    public static Boolean checkDeletePercentageContent(String str, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mAppStorageDirectory);
        stringBuilder.append(str);
        File file = new File(stringBuilder.toString());
        if (file.exists() && file.isDirectory()) {
            return deletePercentageContent(file, i);
        }
        return Boolean.valueOf(false);
    }

    public static long getFileSize(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] listFiles = file.listFiles();
        int i = 0;
        while (i < listFiles.length) {
            i++;
            j += listFiles[i].length();
        }
        return j;
    }

    public static long getFileSize(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mAppStorageDirectory);
        stringBuilder.append(str);
        return getFileSize(new File(stringBuilder.toString()));
    }

    public static CacheResult getStorageStatus() {
        if (!isSdCardWrittenable()) {
            return new CacheResult(2);
        }
        if (((long) getAvailableStorageMB()) < LOW_STORAGE_THRESHOLD) {
            return new CacheResult(1);
        }
        return new CacheResult(0);
    }

    public static CacheResult getStorageStatus(int i) {
        if (!isSdCardWrittenable()) {
            return new CacheResult(2);
        }
        if (getAvailableStorageMB() < i) {
            return new CacheResult(1);
        }
        return new CacheResult(0);
    }

    public static File getFileInInternalStorage(Context context, String str, String str2) {
        File file = new File(context.getDir(str, 0), str2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return file;
    }
}
