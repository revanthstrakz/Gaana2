package net.hockeyapp.android.c;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import java.util.ArrayList;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.d.c;
import net.hockeyapp.android.d.i;
import net.hockeyapp.android.e;
import net.hockeyapp.android.f;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;

public class g extends AsyncTask<Void, Void, FeedbackResponse> {
    private Context a;
    private String b;
    private Handler c;
    private String d;
    private String e = null;

    public g(Context context, String str, Handler handler, String str2) {
        this.a = context;
        this.b = str;
        this.c = handler;
        this.d = str2;
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public FeedbackResponse doInBackground(Void... voidArr) {
        if (this.a == null || this.b == null) {
            return null;
        }
        FeedbackResponse a = c.a().a(this.b);
        if (!(a == null || a.b() == null)) {
            ArrayList a2 = a.b().a();
            if (!(a2 == null || a2.isEmpty())) {
                a(a2);
            }
        }
        return a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(FeedbackResponse feedbackResponse) {
        if (feedbackResponse != null && this.c != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("parse_feedback_response", feedbackResponse);
            message.setData(bundle);
            this.c.sendMessage(message);
        }
    }

    private void a(ArrayList<FeedbackMessage> arrayList) {
        FeedbackMessage feedbackMessage = (FeedbackMessage) arrayList.get(arrayList.size() - 1);
        int c = feedbackMessage.c();
        int i = 0;
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("net.hockeyapp.android.feedback", 0);
        if (this.d.equals("send")) {
            sharedPreferences.edit().putInt("idLastMessageSend", c).putInt("idLastMessageProcessed", c).apply();
        } else if (this.d.equals(InAppConstants.API_ENDPOINT_INAPPS)) {
            int i2 = sharedPreferences.getInt("idLastMessageSend", -1);
            int i3 = sharedPreferences.getInt("idLastMessageProcessed", -1);
            if (c != i2 && c != i3) {
                sharedPreferences.edit().putInt("idLastMessageProcessed", c).apply();
                f a = e.a();
                if (a != null) {
                    i = a.a(feedbackMessage);
                }
                if (i == 0) {
                    a(this.a);
                }
            }
        }
    }

    private void a(Context context) {
        if (this.e != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(MoEHelperConstants.NAVIGATION_SOURCE_NOTIFICATION);
            int identifier = context.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
            Class cls = null;
            if (e.a() != null) {
                cls = e.a().a();
            }
            if (cls == null) {
                cls = FeedbackActivity.class;
            }
            Intent intent = new Intent();
            intent.setFlags(805306368);
            intent.setClass(context, cls);
            intent.putExtra("url", this.e);
            Notification a = i.a(context, PendingIntent.getActivity(context, 0, intent, 1073741824), "HockeyApp Feedback", "A new answer to your feedback is available.", identifier);
            if (a != null) {
                notificationManager.notify(2, a);
            }
        }
    }
}
