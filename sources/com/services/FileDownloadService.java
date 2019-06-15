package com.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.constants.Constants;
import com.e.a.e.c;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.google.android.exoplayer2.C;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadHTTPStatus;
import com.managers.SdCardManager;
import com.managers.ap;
import com.managers.j;
import com.managers.k;
import com.managers.l;
import com.managers.o;
import com.managers.u;
import com.payu.custombrowser.util.CBConstant;
import com.services.l.ae;
import com.services.l.bd;
import com.utilities.Util;
import java.util.Calendar;

public class FileDownloadService extends Service {
    public static ae a = null;
    public static bd b = null;
    private static long c = 0;
    private static boolean d = false;
    private String e = "";
    private Handler f;
    private Thread g;
    private boolean h = false;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static boolean a() {
        return d;
    }

    public static void a(boolean z) {
        d = z;
    }

    public static ae b() {
        return a;
    }

    public static void a(ae aeVar) {
        a = aeVar;
    }

    public static void a(bd bdVar) {
        b = bdVar;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (Constants.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onStartCommand ");
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(c);
            Log.d("FileDownloadService", stringBuilder.toString());
        }
        if (this.f == null) {
            this.f = new Handler();
        }
        boolean z = false;
        if (intent != null && intent.hasExtra("IS_FROM_SCHEDULE_AND_OREO")) {
            z = intent.getBooleanExtra("IS_FROM_SCHEDULE_AND_OREO", false);
        }
        if (z) {
            startForeground(1002, j.a(GaanaApplication.getContext()).a());
        }
        c();
        return 1;
    }

    public void c() {
        if (this.g == null || !this.g.isAlive()) {
            this.h = true;
            f();
            return;
        }
        if (Constants.b) {
            Log.d("FileDownloadService", "download thread is already running.. No need to start new thread");
        }
    }

    private void f() {
        if (Constants.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("downloadOnThread ");
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(c);
            Log.d("FileDownloadService", stringBuilder.toString());
        }
        a(true);
        this.g = new Thread(new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                Boolean valueOf = Boolean.valueOf(true);
                FileDownloadService.c = Thread.currentThread().getId();
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                int i = 0;
                int i2 = i;
                do {
                    DownloadManager.c().o();
                    if (Util.j(FileDownloadService.this)) {
                        if (ap.a().f() && DownloadManager.c().K() > Integer.valueOf(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                            Boolean.valueOf(true);
                            FileDownloadService.this.e = "";
                            break;
                        }
                        int l;
                        if (ap.a().o()) {
                            l = DownloadManager.c().l();
                        } else if (ap.a().m()) {
                            l = DownloadManager.c().n();
                        } else {
                            l = DownloadManager.c().l();
                        }
                        final BusinessObject i3 = DownloadManager.c().i(String.valueOf(l));
                        if (!ap.a().j() && i3 != null && !Util.a(i3)) {
                            Boolean.valueOf(true);
                            break;
                        } else if (((GaanaApplication) GaanaApplication.getContext()).isAppInOfflineMode()) {
                            Boolean.valueOf(true);
                            break;
                        } else if (Long.valueOf(Util.q()).compareTo(Long.valueOf((long) Constants.ds)) <= 0) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    FileDownloadService.this.e = Constants.cX;
                                    Toast.makeText(GaanaApplication.getContext(), FileDownloadService.this.e, 0).show();
                                    FileDownloadService.this.e = "";
                                }
                            });
                            break;
                        } else if (Util.k(GaanaApplication.getContext()) != 0 || Util.a(i3) || d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                            if (DownloadManager.c().v()) {
                                if (FileDownloadService.a()) {
                                    if (SdCardManager.a().d().isSuccess().booleanValue()) {
                                        if (!LoginManager.getInstance().hasTokenExpired()) {
                                            if (!k.a().b() || k.a().c(FileDownloadService.this)) {
                                                if (l == -1) {
                                                    Boolean.valueOf(true);
                                                    break;
                                                } else if (!ap.a().m() || DownloadManager.c().j(String.valueOf(l)).booleanValue()) {
                                                    DownloadHTTPStatus downloadHTTPStatus;
                                                    FileDownloadService.a(true);
                                                    if (FileDownloadService.b() != null) {
                                                        FileDownloadService.b().OnNetworkChangeListener(FileDownloadService.d);
                                                    }
                                                    DownloadManager.c().k(l);
                                                    DownloadManager.c().f();
                                                    Intent intent = new Intent("broadcast_intent_download_service");
                                                    intent.putExtra("track_id", l);
                                                    intent.putExtra("DisplayCoachmark", true);
                                                    if (Constants.b) {
                                                        StringBuilder stringBuilder = new StringBuilder();
                                                        stringBuilder.append("Starting download: trackId ");
                                                        stringBuilder.append(l);
                                                        stringBuilder.append(", thread id: ");
                                                        stringBuilder.append(FileDownloadService.c);
                                                        Log.d("FileDownloadService", stringBuilder.toString());
                                                    }
                                                    LocalBroadcastManager.getInstance(FileDownloadService.this).sendBroadcast(intent);
                                                    if (DownloadManager.c().o(l).booleanValue()) {
                                                        downloadHTTPStatus = DownloadHTTPStatus.SUCCESS;
                                                    } else {
                                                        int smartDownload;
                                                        String str;
                                                        DownloadHTTPStatus downloadHTTPStatus2;
                                                        if (i3 != null && i3.getSmartDownload() == 1) {
                                                            u.a().a("Smart Download", "Download", i3.getBusinessObjId());
                                                        } else if (FileDownloadService.b != null && FileDownloadService.this.h) {
                                                            FileDownloadService.b.showAnimationToMyMusic();
                                                            FileDownloadService.this.h = false;
                                                        }
                                                        String str2 = "";
                                                        if (i3 != null) {
                                                            str2 = i3.getName();
                                                            smartDownload = i3.getSmartDownload();
                                                        } else {
                                                            smartDownload = 0;
                                                        }
                                                        j.a(GaanaApplication.getContext()).a(FileDownloadService.this, str2, FileDownloadService.this.getString(R.string.downloading_songs));
                                                        str2 = CBConstant.FAIL;
                                                        String e = DownloadManager.c().e(l, smartDownload);
                                                        if (i == 0) {
                                                            long timeInMillis2 = Calendar.getInstance().getTimeInMillis();
                                                            str = "URL";
                                                            if (timeInMillis != 0) {
                                                                u.a().a("Download", timeInMillis2 - timeInMillis, "Track Download", str);
                                                                i++;
                                                                timeInMillis = Calendar.getInstance().getTimeInMillis();
                                                            }
                                                        }
                                                        DownloadManager.c().a(String.valueOf(l), str2);
                                                        if (TextUtils.isEmpty(e) || e.equalsIgnoreCase(DownloadManager.f) || e.equalsIgnoreCase(DownloadManager.d) || e.equalsIgnoreCase(DownloadManager.e) || e.equalsIgnoreCase(DownloadManager.g)) {
                                                            UninstallIO.sendDownloadFailureEvent("");
                                                            downloadHTTPStatus2 = DownloadHTTPStatus.FAILED;
                                                        } else {
                                                            o a = o.a();
                                                            StringBuilder stringBuilder2 = new StringBuilder();
                                                            stringBuilder2.append(l);
                                                            stringBuilder2.append("");
                                                            downloadHTTPStatus2 = a.a(e, stringBuilder2.toString());
                                                            if (i2 == 0) {
                                                                long timeInMillis3 = Calendar.getInstance().getTimeInMillis();
                                                                stringBuilder2 = new StringBuilder();
                                                                stringBuilder2.append(downloadHTTPStatus2);
                                                                stringBuilder2.append(" Media");
                                                                str = stringBuilder2.toString();
                                                                if (timeInMillis != 0) {
                                                                    u.a().a("Download", timeInMillis3 - timeInMillis, "Track Download", str);
                                                                    i2++;
                                                                }
                                                            }
                                                        }
                                                        if (downloadHTTPStatus2 != DownloadHTTPStatus.SUCCESS) {
                                                            String string = FileDownloadService.this.getResources().getString(R.string.error_download_generic);
                                                            if (!Util.j(FileDownloadService.this)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_no_internet);
                                                            } else if (TextUtils.isEmpty(e)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_generic);
                                                            } else if (e.equalsIgnoreCase(DownloadManager.d)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_content_not_available);
                                                            } else if (e.equalsIgnoreCase(DownloadManager.g)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_content_not_available_for_freedom_user);
                                                                FileDownloadService.this.e = FileDownloadService.this.getResources().getString(R.string.error_download_content_not_available_for_freedom_user);
                                                            } else if (e.equalsIgnoreCase(DownloadManager.e)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_token_expired);
                                                            } else if (e.equalsIgnoreCase(DownloadManager.f)) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_data_not_found);
                                                            } else if (downloadHTTPStatus2 == DownloadHTTPStatus.CONNECTION_RESET || downloadHTTPStatus2 == DownloadHTTPStatus.FAILED) {
                                                                string = FileDownloadService.this.getResources().getString(R.string.error_download_storage_access_problem);
                                                            }
                                                            j.a(GaanaApplication.getContext()).a(0, 0, string);
                                                            UninstallIO.sendDownloadFailureEvent(e);
                                                            MoEngage.getInstance().reportDownloadSuccess(false);
                                                        } else {
                                                            str2 = "success";
                                                            j.a(GaanaApplication.getContext()).a(0, 0, FileDownloadService.this.getString(R.string.download_success_msg));
                                                            MoEngage.getInstance().reportDownloadSuccess(true);
                                                            FileDownloadService.this.f.post(new Runnable() {
                                                                public void run() {
                                                                    if (!GaanaApplication.getInstance().isAppInDataSaveMode() && i3 != null) {
                                                                        i.a().a(i3.getBusinessObjId(), Util.f(GaanaApplication.getContext(), i3.getArtwork()), null);
                                                                    }
                                                                }
                                                            });
                                                            if (i3 != null) {
                                                                AppsFlyer.getInstance().reportDownloadSuccess(i3);
                                                            }
                                                            if (k.a().b() && k.a().c(FileDownloadService.this)) {
                                                                d.a().a("PREFERENCE_KEY_SHOW_SCHEDULE_DOWNLOAD_TOAST", true, true);
                                                                k.a().a(String.valueOf(l));
                                                            }
                                                        }
                                                        DownloadManager.c().a(String.valueOf(l), str2);
                                                        downloadHTTPStatus = downloadHTTPStatus2;
                                                    }
                                                    DownloadManager.c().a(l, downloadHTTPStatus);
                                                    if (Constants.b) {
                                                        StringBuilder stringBuilder3 = new StringBuilder();
                                                        stringBuilder3.append("download try done: trackId ");
                                                        stringBuilder3.append(l);
                                                        stringBuilder3.append(" ");
                                                        stringBuilder3.append(FileDownloadService.c);
                                                        stringBuilder3.append(" ");
                                                        stringBuilder3.append(downloadHTTPStatus);
                                                        Log.d("FileDownloadService", stringBuilder3.toString());
                                                    }
                                                    valueOf = Boolean.valueOf(false);
                                                } else {
                                                    DownloadManager.c().a(l, DownloadHTTPStatus.FAILED);
                                                }
                                            } else {
                                                Boolean.valueOf(true);
                                                break;
                                            }
                                        }
                                        if (Constants.b) {
                                            Log.d("Test", "=> Gaana session expired. stopping download.");
                                        }
                                        Boolean.valueOf(true);
                                    } else {
                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                            public void run() {
                                                FileDownloadService.this.e = FileDownloadService.this.getString(R.string.device_storage_space_low_message);
                                                Toast.makeText(GaanaApplication.getContext(), FileDownloadService.this.e, 0).show();
                                                FileDownloadService.this.e = "";
                                                Intent intent = new Intent();
                                                intent.setAction("broadcast_intent_download_service_freedom_user_info");
                                                intent.putExtra("SNACKBAR_MSG", FileDownloadService.this.e);
                                                LocalBroadcastManager.getInstance(FileDownloadService.this.getApplicationContext()).sendBroadcast(intent);
                                            }
                                        });
                                        break;
                                    }
                                }
                                Boolean.valueOf(true);
                                break;
                            }
                            Boolean.valueOf(true);
                            break;
                        } else {
                            Boolean.valueOf(true);
                            if (FileDownloadService.b() != null) {
                                FileDownloadService.b().OnNetworkChangeListener(FileDownloadService.d);
                            }
                            return;
                        }
                    }
                    Boolean.valueOf(true);
                    break;
                } while (!valueOf.booleanValue());
                FileDownloadService.a(false);
                if (FileDownloadService.b() != null) {
                    FileDownloadService.b().OnNetworkChangeListener(FileDownloadService.d);
                }
                FileDownloadService.c = 0;
                if (!TextUtils.isEmpty(FileDownloadService.this.e) && FileDownloadService.this.e.contains(FileDownloadService.this.getResources().getString(R.string.error_download_content_not_available_for_freedom_user))) {
                    FileDownloadService.this.f.post(new Runnable() {
                        public void run() {
                            Intent intent = new Intent();
                            intent.setAction("broadcast_intent_download_service_freedom_user_info");
                            intent.putExtra("SNACKBAR_MSG", FileDownloadService.this.e);
                            LocalBroadcastManager.getInstance(FileDownloadService.this.getApplicationContext()).sendBroadcast(intent);
                        }
                    });
                }
                DownloadManager.c().a(FileDownloadService.this.e);
                FileDownloadService.this.b(FileDownloadService.this.getApplicationContext());
            }
        });
        this.g.start();
    }

    private void b(Context context) {
        if (DownloadManager.c().M() < 1) {
            d.a().a("PREFERENCE_DOWNLOAD_ALARM_ACTIVE", false, false);
            a(context);
            return;
        }
        int i;
        int i2;
        int i3;
        int i4;
        boolean b = d.a().b("PREFERENCE_DOWNLOAD_ALARM_ACTIVE", false, false);
        int i5 = -1;
        if (TextUtils.isEmpty(Constants.aw) || TextUtils.isEmpty(Constants.ax) || Constants.ay < 1) {
            i = false;
            i2 = -1;
            i3 = i2;
            i4 = i3;
        } else {
            String[] split = Constants.aw.split("-");
            if (split.length > 1) {
                split = split[0].split(":");
                i3 = Integer.parseInt(split[0]);
                i2 = Integer.parseInt(split[1]);
            } else {
                i2 = -1;
                i3 = i2;
            }
            String[] split2 = Constants.ax.split("-");
            if (split2.length > 1) {
                String[] split3 = split2[0].split(":");
                int parseInt = Integer.parseInt(split3[0]);
                i4 = Integer.parseInt(split3[1]);
                i5 = parseInt;
            } else {
                i4 = -1;
            }
            i = (i3 < 0 || i2 < 0 || i5 < 0 || i4 < 0) ? false : 1;
            d.a().a("PREFERENCE_DOWNLOAD_NOTIFICATION_SHOW_WEEKDAY_RANGE", Constants.aw, false);
            d.a().a("PREFERENCE_DOWNLOAD_NOTIFICATION_SHOW_WEEKEND_RANGE", Constants.ax, false);
            d.a().a("PREFERENCE_DOWNLOAD_NOTIFICATION_NOTIFICATION_REPEAT_DAY", Constants.ay, false);
        }
        if (i == 0) {
            d.a().a("PREFERENCE_DOWNLOAD_ALARM_ACTIVE", false, false);
            a(context);
            return;
        }
        if (!(b || i == 0)) {
            a(context);
            Calendar instance = Calendar.getInstance();
            instance.set(13, 0);
            instance.add(5, 1);
            int i6 = instance.get(7);
            if (i6 == 7 || i6 == 1) {
                instance.set(11, i5);
                instance.set(12, i4);
            } else {
                instance.set(11, i3);
                instance.set(12, i2);
            }
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setRepeating(0, instance.getTimeInMillis(), com.comscore.utils.Constants.SESSION_INACTIVE_PERIOD, PendingIntent.getBroadcast(context, 9998, new Intent(context, DownloadNotificationReceiver.class), C.ENCODING_PCM_MU_LAW));
            d.a().a("PREFERENCE_DOWNLOAD_ALARM_ACTIVE", true, false);
        }
    }

    public void a(Context context) {
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 9998, new Intent(context, DownloadScheduleReceiver.class), C.ENCODING_PCM_MU_LAW));
        } catch (Exception unused) {
        }
    }

    private void g() {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                d a = d.a();
                String c = a.c("PREFF_LAST_DOWNLOADE_TRACK_ID", true);
                String c2 = a.c("PREFF_LAST_DOWNLOADE_TRACK_STATUS", true);
                if (!TextUtils.isEmpty(c)) {
                    if (TextUtils.isEmpty(c2)) {
                        c2 = "success";
                    }
                    if (l.a().b(Integer.parseInt(c), c.c, c2)) {
                        a.b("PREFF_LAST_DOWNLOADE_TRACK_ID", true);
                        a.b("PREFF_LAST_DOWNLOADE_TRACK_STATUS", true);
                    }
                }
            }
        }, -1);
    }

    public void onDestroy() {
        if (Constants.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onDestroy ");
            stringBuilder.append(a());
            stringBuilder.append(" ");
            stringBuilder.append(c);
            Log.d("FileDownloadService", stringBuilder.toString());
        }
        a(false);
        c = 0;
        j.a(getApplicationContext()).b();
        g();
        super.onDestroy();
    }

    public void onTaskRemoved(Intent intent) {
        DownloadManager.c().e();
    }
}
