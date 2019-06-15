package com.managers;

import android.content.Context;
import android.os.Bundle;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.fragments.BaseGaanaFragment;
import com.fragments.ItemListingFragment;
import com.fragments.MyMusicItemFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BasicResponse;
import com.gaana.models.BusinessObject;
import com.gaana.models.Notifications;
import com.gaana.models.Notifications.Notification;
import com.i.i;
import com.managers.URLManager.BusinessObjectType;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.d;
import com.services.l.af;
import com.services.l.s;
import com.services.n;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class aa {
    private static aa a;
    private static GaanaApplication b;
    private d c;
    private boolean d;
    private Notifications e;
    private b f;
    private a g;

    public interface a {
        void a(Notification notification);
    }

    public interface b {
        void a(int i);
    }

    private aa() {
        this.c = null;
        this.d = true;
        this.e = null;
        this.f = null;
        this.g = null;
        this.c = d.a();
        b = GaanaApplication.getInstance();
    }

    public static aa a() {
        if (a == null) {
            a = new aa();
        }
        return a;
    }

    public Notifications b() {
        return this.e;
    }

    public void a(Notifications notifications) {
        this.e = notifications;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    private void f() {
        this.c.a("PREFERENCE_SAVED_GAANA_MOE_PUSH_NOTIFICATIONS", n.a(this.e.getArrListBusinessObj()), false);
    }

    public void a(boolean z) {
        if (this.e == null) {
            this.e = new Notifications();
        }
        ArrayList arrayList = (ArrayList) n.a(this.c.c("PREFERENCE_SAVED_GAANA_MOE_PUSH_NOTIFICATIONS", false));
        if (arrayList != null) {
            if (arrayList.size() >= 100) {
                arrayList.subList(100, arrayList.size()).clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.e.addNotificationToList((Notification) it.next());
            }
            f();
            if (z) {
                h();
            }
        }
    }

    public void a(int i) {
        if (this.e != null) {
            this.e.getArrListBusinessObj().remove(i);
            f();
        }
    }

    public void a(Notification notification, boolean z) {
        if (this.e == null) {
            a(false);
        }
        this.e.addNotificationToList(notification);
        f();
        if (this.g != null) {
            if (z) {
                this.g.a(notification);
            }
            this.e.setLatestNotificationIsViewed();
        }
        h();
    }

    public void a(Context context) {
        b = (GaanaApplication) context.getApplicationContext();
        if (b.isAppInOfflineMode()) {
            ((BaseActivity) context).displayFeatureNotAvailableOfflineDialog("Notification");
        } else if (Util.j(context)) {
            ListingComponents a = Constants.a(true);
            if (this.e != null) {
                ((ListingButton) a.c().get(0)).a(this.e.getArrListBusinessObj());
            }
            b.setListingComponents(a);
            Bundle bundle = new Bundle();
            bundle.putBoolean("notifications", true);
            BaseGaanaFragment itemListingFragment = new ItemListingFragment();
            itemListingFragment.setArguments(bundle);
            if (context instanceof GaanaActivity) {
                ((GaanaActivity) context).displayFragment(itemListingFragment);
            }
        } else {
            ap.a().f(context);
        }
    }

    private void g() {
        if (this.e != null) {
            this.e.setAllNotificationsSeen();
            f();
        }
    }

    public void c() {
        if (this.e != null && this.e.getFreshNotificationsCount() > 0) {
            g();
            if (this.e.getArrListBusinessObj() != null) {
                int size = this.e.getArrListBusinessObj().size();
                if (size > 0) {
                    int i = 0;
                    while (i < size) {
                        if (((Notification) this.e.getArrListBusinessObj().get(i)).getNotificationId() == null || ((Notification) this.e.getArrListBusinessObj().get(i)).getTimeStamp() == null || (((Notification) this.e.getArrListBusinessObj().get(i)).getType().equalsIgnoreCase("FOLLOW_REQUEST") && !((Notification) this.e.getArrListBusinessObj().get(i)).hasSeen())) {
                            i++;
                        } else {
                            String replace = "https://api.gaana.com/user.php?type=update_last_notification_id&last_notification_id=<last_notification_id>".replace("<last_notification_id>", ((Notification) this.e.getArrListBusinessObj().get(i)).getTimeStamp());
                            UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
                            if (!(currentUser == null || !currentUser.getLoginStatus() || replace.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(replace);
                                stringBuilder.append("&token=");
                                stringBuilder.append(currentUser.getAuthToken());
                                replace = stringBuilder.toString();
                            }
                            replace = replace.replace(" ", "%20");
                            URLManager uRLManager = new URLManager();
                            uRLManager.a(replace);
                            uRLManager.a(BasicResponse.class);
                            uRLManager.i(false);
                            i.a().a(new af() {
                                public void onErrorResponse(BusinessObject businessObject) {
                                }

                                public void onRetreivalComplete(Object obj) {
                                    aa.this.b(true);
                                }
                            }, uRLManager);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void b(boolean z) {
        if (this.e == null) {
            a(true);
        }
        URLManager c = ((ListingButton) Constants.a(false).c().get(0)).c();
        c.a(10);
        c.c(Boolean.valueOf(z));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (aa.this.e == null) {
                    aa.this.a(false);
                }
                if (businessObject instanceof Notifications) {
                    Notifications notifications = (Notifications) businessObject;
                    if (!(notifications == null || notifications.getArrListBusinessObj() == null || notifications.getArrListBusinessObj().size() <= 0)) {
                        Iterator it = notifications.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            aa.this.e.addNotificationToList((Notification) it.next());
                        }
                    }
                }
                aa.this.h();
            }
        }, c);
    }

    private void h() {
        if (this.e != null) {
            int freshNotificationsCount = this.e.getFreshNotificationsCount();
            int b = this.c.b("PREFERENCE_PREVIOUS_NOTIFICATION_COUNT", 0, true);
            if (this.e.isLatestNotificationViewed()) {
                if (this.f != null) {
                    this.f.a(0);
                }
            } else if (freshNotificationsCount > 0) {
                if (freshNotificationsCount != b) {
                    this.d = true;
                    this.c.a("PREFERENCE_PREVIOUS_NOTIFICATION_COUNT", freshNotificationsCount, true);
                }
                if (this.d && this.f != null) {
                    this.f.a(freshNotificationsCount);
                }
            }
        }
    }

    public void d() {
        this.e = new Notifications();
        this.c.b("PREFERENCE_SAVED_GAANA_MOE_PUSH_NOTIFICATIONS", false);
    }

    public void e() {
        int i = 0;
        if (this.e == null) {
            a(false);
        }
        if (this.e.getArrListBusinessObj() == null) {
            d();
            return;
        }
        while (i < this.e.getArrListBusinessObj().size()) {
            Notification notification = (Notification) this.e.getArrListBusinessObj().get(i);
            if (notification.getNotificationSrc() == null || !notification.getNotificationSrc().equalsIgnoreCase("moengage") || notification.getNotificationType() != null) {
                a(i);
            }
            i++;
        }
    }

    public void a(long j) {
        if (!(this.e == null || this.e.getArrListBusinessObj() == null || this.e.getArrListBusinessObj().size() <= 0)) {
            int i = 0;
            while (i < this.e.getArrListBusinessObj().size()) {
                if (((Notification) this.e.getArrListBusinessObj().get(i)).getTimeStampInMilliSeconds() != j || ((Notification) this.e.getArrListBusinessObj().get(i)).hasSeen()) {
                    i++;
                } else {
                    this.e.notifSeenAtPosition(i);
                    if (((Notification) this.e.getArrListBusinessObj().get(i)).getNotificationId() != null) {
                        b(i);
                    }
                    f();
                    return;
                }
            }
        }
    }

    private void b(int i) {
        String replace = "https://api.gaana.com/user.php?type=update_last_notification_id&last_notification_id=<last_notification_id>".replace("<last_notification_id>", ((Notification) this.e.getArrListBusinessObj().get(i)).getTimeStamp());
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || replace.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(replace);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            replace = stringBuilder.toString();
        }
        replace = replace.replace(" ", "%20");
        URLManager uRLManager = new URLManager();
        uRLManager.a(replace);
        uRLManager.a(BasicResponse.class);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public void a(Context context, String str) {
        String str2 = "1";
        SortOrder sortOrder = SortOrder.Default;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("notificationTrackData")) {
                u.a().b("Download Notification", "DN_Clicks");
                str = jSONObject.getString("notificationTrackData");
            } else {
                str = null;
            }
            BaseGaanaFragment myMusicItemFragment = new MyMusicItemFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("obj_type", BusinessObjectType.Tracks);
            bundle.putInt("DEEPLINKING_SCREEN", R.id.MyMusicMenuSongs);
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str2);
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM2", str);
            bundle.putString("DEEPLINKING_SCREEN_SORT_ORDER", sortOrder.name());
            myMusicItemFragment.setArguments(bundle);
            ((GaanaActivity) context).displayFragment(myMusicItemFragment);
        } catch (Exception unused) {
        }
    }
}
