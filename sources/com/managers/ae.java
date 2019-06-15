package com.managers;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.e.a.c;
import com.e.a.e;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists;
import com.gaana.view.CustomListView.Header;
import com.i.d;
import com.services.l.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ae extends x {
    private ArrayList<BusinessObject> a = new ArrayList();
    private boolean b = false;

    public void a(final URLManager uRLManager, final String str, int i, int i2, String str2, String str3, final s sVar) {
        if (!this.b) {
            d.a(new Runnable() {
                public void run() {
                    if (!ae.this.b) {
                        int i = 1;
                        ae.this.b = true;
                        int i2 = 0;
                        BusinessObject myPlaylist = PlaylistSyncManager.getInstance().getMyPlaylist(false);
                        BusinessObject a = c.a().a(uRLManager.i(), str, "name", "ASC");
                        ArrayList a2 = DownloadManager.c().a("", e.c.b);
                        ae.this.a.clear();
                        if (a2 != null && a2.size() > 0) {
                            ae.this.a.addAll(a2);
                        }
                        if (a.getArrListBusinessObj() != null && a.getArrListBusinessObj().size() > 0) {
                            ae.this.a.addAll(a.getArrListBusinessObj());
                        }
                        if (myPlaylist.getArrListBusinessObj() != null && myPlaylist.getArrListBusinessObj().size() > 0) {
                            ae.this.a.addAll(myPlaylist.getArrListBusinessObj());
                        }
                        Collections.sort(ae.this.a, new Comparator<BusinessObject>() {
                            /* renamed from: a */
                            public int compare(BusinessObject businessObject, BusinessObject businessObject2) {
                                if (!TextUtils.isEmpty(businessObject.getNameAndId()) && !TextUtils.isEmpty(businessObject2.getNameAndId())) {
                                    return businessObject.getNameAndId().compareToIgnoreCase(businessObject2.getNameAndId());
                                }
                                if (TextUtils.isEmpty(businessObject.getNameAndId()) && TextUtils.isEmpty(businessObject2.getNameAndId())) {
                                    return 0;
                                }
                                return TextUtils.isEmpty(businessObject.getNameAndId()) ? 1 : -1;
                            }
                        });
                        while (i < ae.this.a.size()) {
                            if (((BusinessObject) ae.this.a.get(i2)).getBusinessObjId().equals(((BusinessObject) ae.this.a.get(i)).getBusinessObjId())) {
                                ae.this.a.remove(i);
                            } else {
                                i2++;
                                i++;
                            }
                        }
                        final Playlists playlists = new Playlists();
                        playlists.setBusinessObjType(uRLManager.i());
                        playlists.setArrListBusinessObj(ae.this.a);
                        if (sVar != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    ae.this.b = false;
                                    sVar.onRetreivalComplete(playlists);
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    public BusinessObject a(URLManager uRLManager, String str, int i, int i2, String str2, String str3) {
        Playlists playlists = new Playlists();
        ArrayList arrayList = new ArrayList();
        for (i2 = 0; i2 < this.a.size(); i2++) {
            BusinessObject businessObject = (BusinessObject) this.a.get(i2);
            if (!(businessObject instanceof Header) && businessObject.getRawName().toUpperCase().contains(str.toUpperCase())) {
                arrayList.add(businessObject);
            }
        }
        playlists.setArrListBusinessObj(arrayList);
        return playlists;
    }

    public void a() {
        this.b = false;
    }
}
