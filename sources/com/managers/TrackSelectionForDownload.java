package com.managers;

import com.gaana.models.BusinessObject;
import com.managers.DownloadManager.DownloadStatus;
import com.services.l.ar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class TrackSelectionForDownload {
    public static boolean a = false;
    private static TrackSelectionForDownload b;
    private HashMap<DownloadSelectionType, ArrayList<BusinessObject>> c;
    private HashMap<DownloadSelectionType, ArrayList<BusinessObject>> d;
    private HashMap<DownloadSelectionType, Boolean> e;
    private List<BusinessObject> f;
    private ar g;

    public enum DownloadSelectionType {
        RECENTLY_PLAYED,
        LISTEN_AGAIN,
        DAILY_MIX,
        WEEKLY_MIX,
        FAVORITE_SONGS,
        TRENDING_SONGS,
        GAANA_MEMORIES,
        OTHERS
    }

    private TrackSelectionForDownload() {
        c();
    }

    public static TrackSelectionForDownload a() {
        if (b == null) {
            b = new TrackSelectionForDownload();
        }
        return b;
    }

    public static void b() {
        if (b != null) {
            b = null;
        }
    }

    public void c() {
        this.c = new HashMap();
        this.e = new HashMap();
        this.d = new HashMap();
        this.f = new ArrayList();
    }

    public void a(DownloadSelectionType downloadSelectionType, ArrayList<BusinessObject> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED) {
                arrayList2.add(businessObject);
            }
        }
        this.d.put(downloadSelectionType, arrayList2);
    }

    public void a(ar arVar) {
        this.g = arVar;
    }

    public int a(DownloadSelectionType downloadSelectionType) {
        return (this.c == null || this.c.get(downloadSelectionType) == null) ? 0 : ((ArrayList) this.c.get(downloadSelectionType)).size();
    }

    public boolean b(DownloadSelectionType downloadSelectionType) {
        ArrayList arrayList = (ArrayList) this.d.get(downloadSelectionType);
        return arrayList != null && a(downloadSelectionType) == arrayList.size();
    }

    public void a(BusinessObject businessObject, DownloadSelectionType downloadSelectionType) {
        if (a(businessObject.getBusinessObjId()) < 0) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED) {
                this.f.add(businessObject);
                b(businessObject, downloadSelectionType);
            }
        }
    }

    public void b(BusinessObject businessObject, DownloadSelectionType downloadSelectionType) {
        if (!a(businessObject.getBusinessObjId(), downloadSelectionType)) {
            Object obj = (ArrayList) this.c.get(downloadSelectionType);
            if (obj != null) {
                obj.add(businessObject);
            } else {
                obj = new ArrayList();
                obj.add(businessObject);
            }
            this.c.remove(downloadSelectionType);
            this.c.put(downloadSelectionType, obj);
            if (this.g != null) {
                this.g.onTrackSelectionChanged(d());
            }
        }
    }

    public void c(BusinessObject businessObject, DownloadSelectionType downloadSelectionType) {
        int a = a(businessObject.getBusinessObjId());
        if (a >= 0) {
            this.f.remove(a);
            d(businessObject, downloadSelectionType);
        }
    }

    public void d(BusinessObject businessObject, DownloadSelectionType downloadSelectionType) {
        this.e.put(downloadSelectionType, Boolean.valueOf(false));
        if (a(businessObject.getBusinessObjId(), downloadSelectionType)) {
            int i = -1;
            ArrayList arrayList = (ArrayList) this.c.get(downloadSelectionType);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BusinessObject businessObject2 = (BusinessObject) it.next();
                if (businessObject2.getBusinessObjId().equals(businessObject.getBusinessObjId())) {
                    i = arrayList.indexOf(businessObject2);
                    break;
                }
            }
            arrayList.remove(i);
            this.c.remove(downloadSelectionType);
            this.c.put(downloadSelectionType, arrayList);
            if (this.g != null) {
                this.g.onTrackSelectionChanged(d());
            }
        }
    }

    public void a(DownloadSelectionType downloadSelectionType, boolean z) {
        this.e.put(downloadSelectionType, Boolean.valueOf(z));
    }

    public boolean a(ArrayList<BusinessObject> arrayList, DownloadSelectionType downloadSelectionType) {
        if (arrayList == null || arrayList.size() <= 0) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (a(businessObject.getBusinessObjId()) < 0) {
                DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                if (e == null || e == DownloadStatus.TRIED_BUT_FAILED || e == DownloadStatus.PAUSED) {
                    this.f.add(businessObject);
                }
            }
        }
        b((ArrayList) arrayList, downloadSelectionType);
        return true;
    }

    public boolean b(ArrayList<BusinessObject> arrayList, DownloadSelectionType downloadSelectionType) {
        if (arrayList == null || arrayList.size() <= 0) {
            return false;
        }
        this.c.remove(downloadSelectionType);
        ArrayList arrayList2 = (ArrayList) this.d.get(downloadSelectionType);
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return false;
        }
        this.c.put(downloadSelectionType, (ArrayList) arrayList2.clone());
        this.e.put(downloadSelectionType, Boolean.valueOf(true));
        if (this.g != null) {
            this.g.onTrackSelectionChanged(d());
        }
        return true;
    }

    public void c(DownloadSelectionType downloadSelectionType) {
        List<BusinessObject> list = (List) this.c.get(downloadSelectionType);
        if (list != null) {
            for (BusinessObject businessObjId : list) {
                int a = a(businessObjId.getBusinessObjId());
                if (a >= 0) {
                    this.f.remove(a);
                }
            }
            d(downloadSelectionType);
        }
    }

    public void d(DownloadSelectionType downloadSelectionType) {
        this.c.remove(downloadSelectionType);
        this.e.put(downloadSelectionType, Boolean.valueOf(false));
        if (this.g != null) {
            this.g.onTrackSelectionChanged(d());
        }
    }

    public boolean e(DownloadSelectionType downloadSelectionType) {
        return this.e.get(downloadSelectionType) != null ? ((Boolean) this.e.get(downloadSelectionType)).booleanValue() : false;
    }

    public boolean a(String str, DownloadSelectionType downloadSelectionType) {
        if (this.c.get(downloadSelectionType) != null) {
            Iterator it = ((ArrayList) this.c.get(downloadSelectionType)).iterator();
            while (it.hasNext()) {
                if (((BusinessObject) it.next()).getBusinessObjId().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int a(String str) {
        if (this.f != null && this.f.size() > 0) {
            for (int i = 0; i < this.f.size(); i++) {
                if (((BusinessObject) this.f.get(i)).getBusinessObjId().equalsIgnoreCase(str)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int d() {
        return this.f.size();
    }

    public List<BusinessObject> e() {
        return this.f;
    }
}
