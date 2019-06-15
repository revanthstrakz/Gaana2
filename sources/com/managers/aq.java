package com.managers;

import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.c.c;
import com.gaana.GaanaActivity;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks;
import com.gaana.models.UserRecentActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager.BusinessObjectType;
import com.services.d;
import com.services.h;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class aq implements com.android.volley.i.a, b<Object> {
    private static aq a;
    private d b = d.a();
    private UserRecentActivity c;
    private UserRecentActivity d;
    private a e;
    private s f;
    private Gson g = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();

    public interface a {
        void OnUserRecentActivityErrorResponse(VolleyError volleyError);

        void OnUserRecentActivityFetched(UserRecentActivity userRecentActivity);
    }

    private aq() {
        String c = d.a().c("pref_user_act", false);
        if (!TextUtils.isEmpty(c)) {
            this.c = (UserRecentActivity) this.g.fromJson(c, UserRecentActivity.class);
        }
        boolean b = d.a().b("PREFERENCE_RECENT_UPGRADED", false, false);
        if (!(this.c == null || b)) {
            ArrayList entities = this.c.getEntities();
            if (!(entities == null || entities.isEmpty())) {
                Iterator it = entities.iterator();
                while (it.hasNext()) {
                    if (!((Item) it.next()).getEntityType().equals(c.c)) {
                        it.remove();
                    }
                }
            }
            this.b.a("pref_user_act", this.g.toJson(this.c), false);
            d.a().a("PREFERENCE_RECENT_UPGRADED", true, false);
        }
        c = d.a().c("pref_radio_act", false);
        if (!TextUtils.isEmpty(c)) {
            this.d = (UserRecentActivity) this.g.fromJson(c, UserRecentActivity.class);
        }
    }

    public static aq a() {
        if (a == null) {
            a = new aq();
        }
        return a;
    }

    public void a(final UserRecentActivity userRecentActivity, final boolean z) {
        h.a().a(new TaskListner() {
            private String d = "";
            private String e = "";

            public void doBackGroundTask() {
                if (!z && aq.this.c != null) {
                    this.e = aq.this.g.toJson(userRecentActivity);
                } else if (aq.this.d != null) {
                    this.d = aq.this.g.toJson(userRecentActivity);
                }
            }

            public void onBackGroundTaskCompleted() {
                if (z) {
                    aq.this.b.a("pref_radio_act", this.d, false);
                } else {
                    aq.this.b.a("pref_user_act", this.e, false);
                }
            }
        }, -1);
    }

    public void a(Item item) {
        if (this.c == null) {
            this.c = new UserRecentActivity();
            this.c.setTimeStamp(Long.toString(new Date().getTime()));
        }
        if (item.getEntityType().equals(c.c)) {
            this.c.addEntity(item);
            a(this.c, false);
        } else if (item.getEntityType().equals(com.constants.c.d.d) || item.getEntityType().equals(com.constants.c.d.c)) {
            if (this.d == null) {
                this.d = new UserRecentActivity();
                this.d.setTimeStamp(Long.toString(new Date().getTime()));
            }
            this.d.addEntity(item);
            a(this.d, true);
        }
    }

    public void a(URLManager uRLManager, a aVar) {
        this.e = aVar;
        if (aVar == null || this.c == null || this.c.getArrListBusinessObj() == null || this.c.getArrListBusinessObj().size() <= 0) {
            i.a().a(uRLManager, toString(), (b) this, (com.android.volley.i.a) this);
        } else {
            aVar.OnUserRecentActivityFetched(this.c);
        }
    }

    public void a(URLManager uRLManager, s sVar) {
        this.f = sVar;
        if (this.f == null || this.c == null || this.c.getArrListBusinessObj() == null || this.c.getArrListBusinessObj().size() <= 0) {
            i.a().a(uRLManager, toString(), (b) this, (com.android.volley.i.a) this);
        } else {
            this.f.onRetreivalComplete(a(this.c));
        }
    }

    public void b(URLManager uRLManager, final a aVar) {
        if (aVar == null || this.d == null) {
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject instanceof UserRecentActivity) {
                        aq.this.d = (UserRecentActivity) businessObject;
                        if (aq.this.d.getEntities() != null && aq.this.d.getEntities().size() > 0) {
                            aq.this.d.setTimeStamp(Long.toString(new Date().getTime()));
                            aq.this.b.a("pref_radio_act", aq.this.g.toJson(aq.this.d), false);
                        }
                        if (aVar != null) {
                            aVar.OnUserRecentActivityFetched(aq.this.d);
                        }
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (aq.this.d != null && aVar != null) {
                        aVar.OnUserRecentActivityFetched(aq.this.d);
                    } else if (aVar != null) {
                        aVar.OnUserRecentActivityErrorResponse(businessObject.getVolleyError());
                    }
                }
            }, uRLManager);
        } else {
            aVar.OnUserRecentActivityFetched(this.d);
        }
    }

    public ArrayList<Item> b() {
        return this.c != null ? this.c.getTracksInUserRecentActivity() : new ArrayList();
    }

    public ArrayList<Item> a(int i) {
        return this.c != null ? this.c.getTracksInUserRecentActivity(i) : new ArrayList();
    }

    public void onResponse(Object obj) {
        if (obj instanceof UserRecentActivity) {
            this.c = (UserRecentActivity) obj;
            if (!(this.c == null || this.c.getEntities() == null || this.c.getEntities().size() <= 0)) {
                this.c.setTimeStamp(Long.toString(new Date().getTime()));
                this.b.a("pref_user_act", this.g.toJson(this.c), false);
            }
            if (this.e != null) {
                this.e.OnUserRecentActivityFetched(this.c);
            }
            if (this.f != null) {
                this.f.onRetreivalComplete(a(this.c));
            }
        }
        c();
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (this.c != null && this.e != null) {
            this.e.OnUserRecentActivityFetched(this.c);
        } else if (this.e != null) {
            this.e.OnUserRecentActivityErrorResponse(volleyError);
        }
        c();
    }

    public void c() {
        this.e = null;
    }

    public BusinessObject a(BusinessObject businessObject) {
        BusinessObject tracks;
        if (businessObject != null && (businessObject instanceof BusinessObject) && businessObject.getVolleyError() == null) {
            tracks = new Tracks();
            tracks.setBusinessObjType(BusinessObjectType.Tracks);
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            if (arrListBusinessObj != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = arrListBusinessObj.iterator();
                while (it.hasNext()) {
                    arrayList.add(Util.g((Item) it.next()));
                }
                tracks.setArrListBusinessObj(arrayList);
            }
        } else {
            tracks = null;
        }
        return tracks != null ? tracks : businessObject;
    }

    public void a(String str) {
        if (this.c != null) {
            BusinessObject deleteFromRecentlyPlayed = this.c.deleteFromRecentlyPlayed(str);
            this.b.a("pref_user_act", this.g.toJson(this.c), false);
            if (deleteFromRecentlyPlayed != null && ai.a() != null && (((GaanaActivity) ai.a()).getCurrentFragment() instanceof SongParallexListingFragment)) {
                ((GaanaActivity) ai.a()).refreshListView(deleteFromRecentlyPlayed, true);
                Util.t(str);
            }
        }
    }
}
