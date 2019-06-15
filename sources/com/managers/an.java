package com.managers;

import android.content.Context;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.models.UserJourney;
import com.gaana.models.UserJourney.Journey;
import com.gaana.models.UserJourney.UserInfo;
import com.google.gson.Gson;
import com.services.d;
import com.utilities.Util;
import java.util.ArrayList;

public class an {
    private static an c = null;
    private static int i = 10;
    public int a = -1;
    public int b = -1;
    private ArrayList<Journey> d;
    private Context e = GaanaApplication.getContext();
    private UserInfo f;
    private String[] g = new String[]{"HOME", "SEARCH", "RADIO", "MY MUSIC"};
    private String[] h = new String[]{"HOME", "SEARCH", "RADIO", "MY MUSIC"};
    private d j = d.a();
    private boolean k = false;
    private ArrayList<Journey> l;
    private boolean m;

    public String a(int i) {
        if (i == -1) {
            return this.g[0];
        }
        return this.g[i];
    }

    public static an a() {
        if (c == null) {
            c = new an();
        }
        return c;
    }

    private an() {
        i = Constants.aS;
        d();
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (Constants.aR != 0 && Constants.aL != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            if (!"".equals(str4) || ai.a() == null) {
                journey.setFN(str4);
            } else {
                journey.setFN(((GaanaApplication) this.e).getPlayoutSectionName());
            }
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setF_idx(str7);
            journey.setT_idx(str8);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public void b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (Constants.aR != 0 && Constants.aQ != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            journey.setFN(str4);
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setF_idx(str7);
            journey.setT_idx(str8);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public void c(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (Constants.aR != 0 && Constants.aM != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            journey.setFN(str4);
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setTN("");
            journey.setF_idx(str7);
            journey.setT_idx(str8);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (Constants.aR != 0 && Constants.aN != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            journey.setFN(str4);
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public void d(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (Constants.aR != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            journey.setFN(str4);
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setF_idx(str7);
            journey.setT_idx(str8);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, long j) {
        Journey journey = new Journey();
        journey.setFId(str2);
        journey.setFN(str3);
        journey.setF_idx(str4);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j);
        stringBuilder.append("");
        journey.setT_idx(stringBuilder.toString());
        journey.setTId(str5);
        journey.setTN(str6);
        journey.setType(str);
        a(journey);
    }

    public void e(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        if (Constants.aR != 0 && Constants.aP != 0) {
            Journey journey = new Journey();
            journey.setFId(str3);
            journey.setFN(str4);
            journey.setTId(str5);
            journey.setTN(str6);
            journey.setF_idx(str7);
            journey.setT_idx(str8);
            journey.setTS(String.valueOf(System.currentTimeMillis()));
            journey.setType(str);
            journey.setSubType(str2);
            a(journey);
        }
    }

    public UserInfo b() {
        if (this.f == null) {
            this.f = new UserInfo();
        }
        if (GaanaApplication.getInstance().getCurrentUser().getUserProfile() != null) {
            this.f.setUId(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getUserId());
            this.f.setNw(Util.p(this.e));
            this.f.setU_dob(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getDob());
            this.f.setU_gender(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getSex());
            this.f.setU_type(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getServerAccountType());
            if (GaanaApplication.getInstance().getCurrentUser().getLoginType() != null) {
                this.f.setU_login_type(GaanaApplication.getInstance().getCurrentUser().getLoginType().name());
            } else {
                this.f.setU_login_type("");
            }
            this.f.setRam(Util.h());
        } else {
            this.f.setNw(Util.p(this.e));
            this.f.setRam(Util.h());
        }
        return this.f;
    }

    private void d() {
        com.i.d.a(new Runnable() {
            public void run() {
                UserJourney userJourney;
                String b = an.this.j.b("PREFF_USER_JOURNEY_EVENTS", null, false);
                if (TextUtils.isEmpty(b)) {
                    userJourney = new UserJourney();
                } else {
                    userJourney = (UserJourney) new Gson().fromJson(b, UserJourney.class);
                }
                if (userJourney == null) {
                    userJourney = new UserJourney();
                }
                if (userJourney.getData() == null) {
                    an.this.d = new ArrayList();
                } else {
                    an.this.d = (ArrayList) userJourney.getData().clone();
                    if (an.this.d == null) {
                        an.this.d = new ArrayList();
                    }
                }
                an.this.k = true;
            }
        });
    }

    @MainThread
    private void a(Journey journey) {
        if (this.k) {
            if (this.d.size() > i && Util.j(this.e)) {
                e();
            } else if (this.d.size() <= i) {
                this.d.add(journey);
                if (this.l != null && this.l.size() > 0) {
                    this.d.addAll(this.l);
                    this.l.clear();
                }
            } else {
                return;
            }
            return;
        }
        if (this.l == null) {
            this.l = new ArrayList();
        }
        this.l.add(journey);
    }

    public void c() {
        if (this.d == null) {
            if (this.l.size() > 0) {
                this.d.addAll(this.l);
                this.l.clear();
            }
            Object userJourney = new UserJourney();
            userJourney.setData(this.d);
            userJourney.setUInfo(this.f);
            this.j.a("PREFF_USER_JOURNEY_EVENTS", new Gson().toJson(userJourney), false);
        }
    }

    private void e() {
        if (!this.m) {
            this.m = true;
            ArrayList arrayList = new ArrayList(this.d.subList(0, i));
            this.d.removeAll(arrayList);
            com.i.d.a(new ao(this, arrayList));
        }
    }
}
