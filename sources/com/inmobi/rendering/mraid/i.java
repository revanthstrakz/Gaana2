package com.inmobi.rendering.mraid;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.rendering.RenderView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

public class i {
    private static final String b = "i";
    public RenderView a;
    private a c;

    /* renamed from: com.inmobi.rendering.mraid.i$1 */
    class AnonymousClass1 implements com.inmobi.rendering.InMobiAdActivity.a {
        final /* synthetic */ Context a;
        final /* synthetic */ int b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;

        public AnonymousClass1(Context context, int i, String str, String str2, String str3, String str4) {
            this.a = context;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
        }

        public final void a() {
            i.b;
            if (this.b == a.a(this.a)) {
                i.b;
                return;
            }
            ContentValues contentValues = new ContentValues();
            String str = this.c;
            int i = -1;
            int hashCode = str.hashCode();
            if (hashCode != -1320822226) {
                if (hashCode != -804109473) {
                    if (hashCode == 476588369 && str.equals(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED)) {
                        i = 2;
                    }
                } else if (str.equals("confirmed")) {
                    i = 1;
                }
            } else if (str.equals("tentative")) {
                i = 0;
            }
            switch (i) {
                case 0:
                    contentValues.put("eventStatus", Integer.valueOf(0));
                    break;
                case 1:
                    contentValues.put("eventStatus", Integer.valueOf(1));
                    break;
                case 2:
                    contentValues.put("eventStatus", Integer.valueOf(2));
                    break;
            }
            ContentResolver contentResolver = this.a.getContentResolver();
            contentResolver.update(ContentUris.withAppendedId(Events.CONTENT_URI, (long) a.a(this.a)), contentValues, null, null);
            if (!(this.d == null || "".equals(this.d))) {
                try {
                    if (this.d.startsWith("+")) {
                        i = Integer.parseInt(this.d.substring(1)) / 60000;
                    } else {
                        i = Integer.parseInt(this.d) / 60000;
                    }
                } catch (NumberFormatException unused) {
                    GregorianCalendar b = a.b(this.d);
                    if (b == null) {
                        i.b;
                        new StringBuilder("Invalid reminder date provided. date string: ").append(this.d);
                        return;
                    }
                    i = ((int) (b.getTimeInMillis() - a.b(this.e).getTimeInMillis())) / 60000;
                    if (i > 0) {
                        i.this.a.b(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                        return;
                    }
                }
                int i2 = -i;
                String[] strArr = new String[1];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a.a(this.a));
                strArr[0] = stringBuilder.toString();
                contentResolver.delete(Reminders.CONTENT_URI, "event_id=?", strArr);
                if (i2 < 0) {
                    i.this.a.b(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                    return;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Integer.valueOf(a.a(this.a)));
                contentValues2.put("method", Integer.valueOf(1));
                contentValues2.put("minutes", Integer.valueOf(i2));
                contentResolver.insert(Reminders.CONTENT_URI, contentValues2);
            }
        }
    }

    static final class a extends Handler {
        private static final String a = "i$a";
        private WeakReference<RenderView> b;

        public a(Looper looper, RenderView renderView) {
            super(looper);
            this.b = new WeakReference(renderView);
        }

        public final void handleMessage(Message message) {
            if (message.what == 1) {
                String str = (String) message.obj;
                RenderView renderView = (RenderView) this.b.get();
                if (renderView != null) {
                    renderView.a(str, "broadcastEvent('vibrateComplete');");
                }
            }
        }
    }

    public i(RenderView renderView) {
        this.a = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.c = new a(handlerThread.getLooper(), renderView);
    }

    public static boolean a() {
        try {
            PlusShare.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static void a(Context context, int i, String str, String str2, String str3) {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(" ");
        stringBuilder2.append(str2);
        stringBuilder2.append(" ");
        stringBuilder2.append(str3);
        String stringBuilder3 = stringBuilder2.toString();
        StringBuilder stringBuilder4;
        switch (i) {
            case 1:
                stringBuilder4 = new StringBuilder("https://www.facebook.com/dialog/feed?app_id=181821551957328&link=");
                stringBuilder4.append(URLEncoder.encode(str2, "UTF-8"));
                stringBuilder4.append("&picture=");
                stringBuilder4.append(URLEncoder.encode(str3, "UTF-8"));
                stringBuilder4.append("&name=&description=");
                stringBuilder4.append(URLEncoder.encode(str, "UTF-8"));
                stringBuilder4.append("&redirect_uri=");
                stringBuilder4.append(URLEncoder.encode(str2, "UTF-8"));
                stringBuilder = stringBuilder4.toString();
                break;
            case 2:
                stringBuilder4 = new StringBuilder("https://m.google.com/app/plus/x/?v=compose&content=");
                stringBuilder4.append(URLEncoder.encode(stringBuilder3, "UTF-8"));
                stringBuilder = stringBuilder4.toString();
                break;
            case 3:
                try {
                    stringBuilder4 = new StringBuilder("http://twitter.com/home?status=");
                    stringBuilder4.append(URLEncoder.encode(stringBuilder3, "UTF-8"));
                    stringBuilder = stringBuilder4.toString();
                    break;
                } catch (UnsupportedEncodingException unused) {
                    return;
                }
            default:
                stringBuilder = null;
                break;
        }
        if (stringBuilder != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(stringBuilder));
            try {
                com.inmobi.commons.a.a.a(context, intent);
                return;
            } catch (ActivityNotFoundException e) {
                e.getMessage();
                return;
            }
        }
        Intent intent2 = new Intent();
        intent2.setType("text/plain");
        intent2.putExtra("android.intent.extra.TEXT", stringBuilder3);
        try {
            com.inmobi.commons.a.a.a(context, intent2);
        } catch (ActivityNotFoundException e2) {
            e2.getMessage();
        }
    }

    public final void a(Context context) {
        if (this.c != null && this.c.hasMessages(1)) {
            this.c.removeMessages(1);
            ((Vibrator) context.getSystemService("vibrator")).cancel();
        }
    }

    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str.length() == 0) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.optString("frequency");
            if (str == null || "".equals(str)) {
                return "";
            }
            if (!("daily".equals(str) || "weekly".equals(str) || "monthly".equals(str))) {
                if (!"yearly".equals(str)) {
                    return "";
                }
            }
            stringBuilder.append("freq=");
            stringBuilder.append(str);
            stringBuilder.append(";");
            String optString = jSONObject.optString("interval");
            if (!(optString == null || "".equals(optString))) {
                stringBuilder.append("interval=");
                stringBuilder.append(Integer.parseInt(optString));
                stringBuilder.append(";");
            }
            optString = a.a(jSONObject.optString("expires"));
            if (optString != null) {
                stringBuilder.append("until=");
                stringBuilder.append(optString.replace("+", "Z+").replace("-", "Z-"));
                stringBuilder.append(";");
            }
            if (str.equals("weekly")) {
                optString = a.a(jSONObject.optJSONArray("daysInWeek"));
                if (optString != null) {
                    stringBuilder.append("byday=");
                    stringBuilder.append(optString);
                    stringBuilder.append(";");
                }
            }
            if (str.equals("monthly")) {
                optString = a.a(jSONObject.optJSONArray("daysInMonth"), -31, 31);
                if (optString != null) {
                    stringBuilder.append("bymonthday=");
                    stringBuilder.append(optString);
                    stringBuilder.append(";");
                }
            }
            if (str.equals("yearly")) {
                optString = a.a(jSONObject.optJSONArray("daysInYear"), -366, 366);
                if (optString != null) {
                    stringBuilder.append("byyearday=");
                    stringBuilder.append(optString);
                    stringBuilder.append(";");
                }
            }
            if (str.equals("monthly")) {
                optString = a.a(jSONObject.optJSONArray("weeksInMonth"), -4, 4);
                if (optString != null) {
                    stringBuilder.append("byweekno=");
                    stringBuilder.append(optString);
                    stringBuilder.append(";");
                }
            }
            if (str.equals("yearly")) {
                str = a.a(jSONObject.optJSONArray("monthsInYear"), 1, 12);
                if (str != null) {
                    stringBuilder.append("bymonth=");
                    stringBuilder.append(str);
                    stringBuilder.append(";");
                }
            }
            return stringBuilder.toString();
        } catch (JSONException e) {
            new StringBuilder("Error Parsing recurrence string").append(e.toString());
            return "";
        }
    }
}
