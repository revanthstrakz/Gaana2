package com.logging;

import android.content.Context;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.CONTENT_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE;
import com.services.AppException;
import com.services.d;
import com.services.h;
import com.services.j;
import com.utilities.Util;
import com.utilities.k;
import org.json.JSONObject;

public class e {
    private static e e;
    public String a = "";
    public String b = "";
    public long c;
    public String d;
    private final int f = 52;

    public long a() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public String b() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public static e c() {
        if (e == null) {
            e = new e();
        }
        return e;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String e() {
        return this.a;
    }

    public long f() {
        return d.a().b(0, "PREFERENCE_KEY_LAST_VIDEO_PLAYED_DURATION", false);
    }

    public void b(long j) {
        d.a().a(j + Util.B(), "PREFERENCE_KEY_LAST_VIDEO_PLAYED_DURATION", false);
    }

    public void a(final VideoTrackLog videoTrackLog, final Context context) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                Util.A();
            }

            public void doBackGroundTask() {
                e.this.b(videoTrackLog, context);
            }
        }, -1);
    }

    private void b(VideoTrackLog videoTrackLog, Context context) {
        String b = d.a().b("PREFERENCE_KEY_LAST_VIDEO_INSERT_ID", null, false);
        GaanaApplication instance = GaanaApplication.getInstance();
        if (videoTrackLog != null) {
            long f = f();
            if (f > videoTrackLog.d()) {
                f = videoTrackLog.d();
            }
            if ("0".equalsIgnoreCase(videoTrackLog.f()) || TextUtils.isEmpty(videoTrackLog.f())) {
                if (f > 0) {
                    d.a().a(f, "PREFERENCE_KEY_LAST_VIDEO_PLAYED_DURATION", false);
                }
                return;
            }
            j jVar = new j();
            String str = "";
            k kVar = new k(Constants.br, Constants.bs);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(videoTrackLog.f());
                stringBuilder.append("_android");
                str = k.a(kVar.b(stringBuilder.toString()));
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://api.gaana.com/vlog.php?last_video_insert_id=");
            stringBuilder2.append(b);
            stringBuilder2.append("&video_id=");
            stringBuilder2.append(videoTrackLog.f());
            stringBuilder2.append("&section_id=");
            stringBuilder2.append(videoTrackLog.a());
            stringBuilder2.append("&start_time=");
            stringBuilder2.append(videoTrackLog.e());
            stringBuilder2.append("&page_id=");
            stringBuilder2.append(videoTrackLog.b());
            stringBuilder2.append("&source=");
            stringBuilder2.append(52);
            stringBuilder2.append("&device_id=");
            stringBuilder2.append(videoTrackLog.c());
            stringBuilder2.append("&last_video_played=");
            stringBuilder2.append(f / 1000);
            stringBuilder2.append("&videotime=");
            stringBuilder2.append(videoTrackLog.d() / 1000);
            stringBuilder2.append("&play_source=");
            stringBuilder2.append(PLAYOUT_SOURCE.NETWORK.ordinal());
            stringBuilder2.append("&content_type=");
            stringBuilder2.append(CONTENT_TYPE.VIDEO_TRACK.ordinal());
            stringBuilder2.append("&platform=android");
            stringBuilder2.append("&data=");
            stringBuilder2.append(str);
            String stringBuilder3 = stringBuilder2.toString();
            if (instance.getCurrentUser().getLoginStatus()) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(stringBuilder3);
                stringBuilder4.append("&token=");
                stringBuilder4.append(instance.getCurrentUser().getAuthToken());
                stringBuilder3 = stringBuilder4.toString();
            }
            try {
                stringBuilder3 = jVar.a(stringBuilder3);
                if (stringBuilder3 != null) {
                    JSONObject jSONObject = new JSONObject(stringBuilder3);
                    if (jSONObject.has("insert_id")) {
                        d.a().a("PREFERENCE_KEY_LAST_VIDEO_INSERT_ID", jSONObject.getString("insert_id"), false);
                        d.a().b("PREFERENCE_KEY_LAST_VIDEO_PLAYED_DURATION", false);
                    }
                }
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            } catch (AppException e3) {
                ThrowableExtension.printStackTrace(e3);
            }
        }
    }
}
