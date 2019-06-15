package com.managers;

import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.PreferedArtists;
import com.gaana.models.PreferedArtists.PreferedArtist;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.services.d;
import com.services.l.af;
import com.services.n;
import com.utilities.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class b {
    private static b a;
    private GaanaApplication b;
    private d c = null;

    public interface a {
        void onArtistsSavedOnServer(String str, boolean z);
    }

    public static b a(GaanaApplication gaanaApplication) {
        if (a == null) {
            a = new b();
        }
        a.b = gaanaApplication;
        a.c = d.a();
        return a;
    }

    private String a(ArrayList<PreferedArtist> arrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PreferedArtist preferedArtist = (PreferedArtist) it.next();
            if (preferedArtist.isPrefered()) {
                stringBuilder.append(preferedArtist.getArtistId());
                stringBuilder.append(",");
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        return (stringBuilder2 == null || stringBuilder2.length() <= 2) ? stringBuilder2 : stringBuilder2.substring(0, stringBuilder2.length() - 1);
    }

    private void b(ArrayList<PreferedArtist> arrayList) {
        PreferedArtists preferedArtists = (PreferedArtists) n.a(this.c.c("PREFERENCE_ARTIST_SETTINGS", false));
        if (preferedArtists instanceof PreferedArtists) {
            Serializable serializable = preferedArtists;
            if (serializable != null) {
                serializable.setArrList(arrayList);
                this.c.a("PREFERENCE_ARTIST_SETTINGS", n.a(serializable), false);
            }
        }
    }

    public void a(final Context context, final ArrayList<PreferedArtist> arrayList, final a aVar) {
        if (!Util.j(context)) {
            ap.a().f(context);
        } else if (this.b.isAppInOfflineMode()) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).displayFeatureNotAvailableOfflineDialog(context.getResources().getString(R.string.artist));
            }
        } else {
            String replace = "https://api.gaana.com/splash/update/device-artist?artists=<artists>".replace("<artists>", a((ArrayList) arrayList));
            UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
            if (currentUser != null && currentUser.getLoginStatus()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(replace);
                stringBuilder.append("&token=");
                stringBuilder.append(currentUser.getAuthToken());
                replace = stringBuilder.toString();
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a(String.class);
            uRLManager.a(replace);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    try {
                        JSONObject jSONObject = new JSONObject((String) obj);
                        String str = "";
                        boolean z = true;
                        if (jSONObject.getInt("status") == 1) {
                            b.this.b(arrayList);
                            q.a().f();
                            if (arrayList != null && arrayList.size() > 0) {
                                StringBuilder stringBuilder = new StringBuilder();
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    PreferedArtist preferedArtist = (PreferedArtist) it.next();
                                    if (preferedArtist.isPrefered()) {
                                        stringBuilder.append(preferedArtist.getArtistId());
                                        stringBuilder.append(",");
                                    }
                                }
                                String stringBuilder2 = stringBuilder.toString();
                                if (!TextUtils.isEmpty(stringBuilder2)) {
                                    u.a().a("Settings", "Artist Selected", stringBuilder2.substring(0, stringBuilder2.length() - 1));
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (aVar != null) {
                            aVar.onArtistsSavedOnServer(str, z);
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        if (aVar != null) {
                            aVar.onArtistsSavedOnServer(context.getResources().getString(R.string.error_updating_artists), false);
                        }
                    }
                }
            }, uRLManager);
        }
    }
}
