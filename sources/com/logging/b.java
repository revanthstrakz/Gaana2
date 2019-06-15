package com.logging;

import android.content.Context;
import com.gaana.BaseActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.util.StorageUtils;
import com.services.d;
import com.services.j;
import com.utilities.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

public class b implements a {
    private static b a;
    private GaanaApplication b;
    private d c = d.a();

    private b() {
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Context context) {
        TrackLog b = GaanaLogger.a().b();
        if (b != null) {
            if (b.j()) {
                LocalMediaManager.getInstance(context).addActivity(b);
                GaanaLogger.a().a(null, context);
                Util.z();
            } else if (Long.parseLong(b.h()) <= 0) {
                GaanaLogger.a().a(null, context);
                Util.z();
            } else {
                String c = c(context);
                this.c.b("PREFERENCE_KEY_OFFLINE_LOG_FILE_NAME", true);
                this.c.a("PREFERENCE_KEY_OFFLINE_LOG_FILE_NAME", c, true);
                File fileInInternalStorage = StorageUtils.getFileInInternalStorage(context, "gaanaCache", c);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileInInternalStorage, true);
                    if (fileInInternalStorage.length() == 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(c);
                        stringBuilder.append("\n");
                        fileOutputStream.write(stringBuilder.toString().getBytes());
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(b.toString());
                    stringBuilder2.append("\n");
                    fileOutputStream.write(stringBuilder2.toString().getBytes());
                    fileOutputStream.close();
                    GaanaLogger.a().a(null, context);
                    Util.z();
                } catch (FileNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                } catch (OutOfMemoryError e3) {
                    ThrowableExtension.printStackTrace(e3);
                    System.gc();
                }
            }
        }
    }

    private String c(Context context) {
        String str;
        StringBuilder stringBuilder;
        if (this.b == null) {
            this.b = GaanaApplication.getInstance();
        }
        if (this.b.getCurrentUser().getUserProfile() == null || this.b.getCurrentUser().getUserProfile().getUserId() == null) {
            str = "0#";
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.b.getCurrentUser().getUserProfile().getUserId());
            stringBuilder.append("#");
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(Util.l(context));
        stringBuilder2.append("#");
        String stringBuilder3 = stringBuilder2.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuilder3);
        stringBuilder.append("5");
        return stringBuilder.toString();
    }

    public void a(BaseActivity baseActivity) {
        String b = this.c.b("PREFERENCE_KEY_OFFLINE_LOG_FILE_NAME", c(baseActivity), true);
        if (b != null) {
            File file = new File(baseActivity.getDir("gaanaCache", 0), b);
            if (file.length() > 0) {
                a(file);
            }
        }
    }

    public void b(Context context) {
        String b = this.c.b("PREFERENCE_KEY_OFFLINE_LOG_FILE_NAME", null, true);
        if (b != null) {
            File file = new File(context.getDir("gaanaCache", 0), b);
            if (!file.delete()) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write("".getBytes());
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                } catch (OutOfMemoryError e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            }
        }
        this.c.b("PREFERENCE_KEY_OFFLINE_LOG_FILE_NAME", true);
    }

    public void a(final File file) {
        com.i.d.a(new Runnable() {
            public void run() {
                String str = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
                try {
                    String a = new j().a("https://api.gaana.com/gaanaplusofflinelog.php", file);
                    if (a != null) {
                        str = new JSONObject(a).getString("status");
                    }
                    if ("true".equalsIgnoreCase(str)) {
                        b.a().b(GaanaApplication.getContext());
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }
}
