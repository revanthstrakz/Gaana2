package com.managers;

import android.content.Intent;
import android.support.v4.util.ArraySet;
import com.e.a.c;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.URLManager.BusinessObjectType;
import com.services.d;
import com.services.l.g;
import com.utilities.Util;
import com.widget.GaanaWidgetProvider;
import java.util.Set;

public class n {
    private static GaanaApplication a;
    private static final Set<String> b = new ArraySet();
    private static final Set<String> c = new ArraySet();
    private static final Set<String> d = new ArraySet();
    private static final Set<String> e = new ArraySet();
    private static final Set<String> f = new ArraySet();
    private static final Set<String> g = new ArraySet();
    private static final Set<String> h = new ArraySet();
    private static final Object i = new Object();
    private boolean j;

    private static class a {
        private static final n a = new n();
    }

    /* synthetic */ n(AnonymousClass1 anonymousClass1) {
        this();
    }

    private n() {
        this.j = false;
        a = GaanaApplication.getInstance();
    }

    public static n a() {
        return a.a;
    }

    public void b() {
        this.j = false;
        b.clear();
        c.clear();
        h.clear();
        d.clear();
        e.clear();
        f.clear();
        g.clear();
        b.clear();
    }

    public boolean a(BusinessObject businessObject) {
        if (a == null) {
            return f(businessObject);
        }
        if (a.getCurrentUser() == null || !a.getCurrentUser().getLoginStatus()) {
            return false;
        }
        Set g = g(businessObject);
        boolean z;
        if (a.a == null || g == null || g.size() == 0) {
            int a = c.a().a(businessObject);
            if (!this.j && a == -1) {
                return f(businessObject);
            }
            z = true;
            if (a != 1) {
                z = false;
            }
            return z;
        }
        try {
            z = g.contains(businessObject.getBusinessObjId());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            z = false;
        }
        return z;
    }

    private boolean f(BusinessObject businessObject) {
        String favoriteStatus = businessObject.getFavoriteStatus();
        return favoriteStatus != null && favoriteStatus.compareTo("1") == 0;
    }

    public void b(final BusinessObject businessObject) {
        if (a.a != null) {
            MoEngage.getInstance().reportAddToFavorites(businessObject);
            AppsFlyer.getInstance().reportFavorite(businessObject);
            String businessObjId = businessObject.getBusinessObjId();
            Set g = g(businessObject);
            if (g != null) {
                g.add(businessObject.getBusinessObjId());
                c.a().a(businessObject, businessObject.isUserFavorited(), true);
                a(businessObjId);
            }
            if (!a.isAppInOfflineMode() && Util.j(GaanaApplication.getContext())) {
                FavouriteSyncManager.getInstance().performSync(new g() {
                    public void favouriteSyncCompleted() {
                        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks && GaanaWidgetProvider.c != null && GaanaWidgetProvider.c.getBusinessObjId().equalsIgnoreCase(businessObject.getBusinessObjId())) {
                            n.this.b(businessObject.getBusinessObjId());
                        }
                    }
                });
            }
        }
    }

    private void b(String str) {
        if (d.a().b("PREFERENCE_DOES_WIDGET_EXIST", true, false)) {
            Intent intent = new Intent(a, GaanaWidgetProvider.class);
            intent.setAction("APP_WIDGET_FAV_STATE_CHANGE");
            intent.putExtra("trackID", str);
            a.sendBroadcast(intent);
        }
    }

    public void c(final BusinessObject businessObject) {
        if (a.a != null) {
            String businessObjId = businessObject.getBusinessObjId();
            Set g = g(businessObject);
            if (g != null && g.contains(businessObject.getBusinessObjId())) {
                g.remove(businessObject.getBusinessObjId());
            }
            c.a().a(businessObject, businessObject.isUserFavorited(), true);
            a(businessObjId);
            if (!a.isAppInOfflineMode() && Util.j(GaanaApplication.getContext())) {
                FavouriteSyncManager.getInstance().performSync(new g() {
                    public void favouriteSyncCompleted() {
                        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks && GaanaWidgetProvider.c != null && GaanaWidgetProvider.c.getBusinessObjId().equalsIgnoreCase(businessObject.getBusinessObjId())) {
                            n.this.b(businessObject.getBusinessObjId());
                        }
                    }
                });
            }
        }
    }

    public void d(BusinessObject businessObject) {
        synchronized (i) {
            if (!b.contains(businessObject.getBusinessObjId())) {
                b.add(businessObject.getBusinessObjId());
            }
        }
    }

    public static void a(String str) {
        synchronized (i) {
            b.remove(str);
        }
    }

    public static boolean e(BusinessObject businessObject) {
        synchronized (i) {
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            if (b.contains(entityId)) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Missing block: B:33:0x0087, code skipped:
            if (r1.getEntityType().equals(com.constants.c.d.c) == false) goto L_0x008a;
     */
    private static java.util.Set<java.lang.String> g(com.gaana.models.BusinessObject r4) {
        /*
        r0 = r4 instanceof com.gaana.models.FavoriteOccasions.FavoriteOccasion;
        if (r0 == 0) goto L_0x0007;
    L_0x0004:
        r4 = h;
        return r4;
    L_0x0007:
        r0 = r4 instanceof com.gaana.models.Tracks.Track;
        if (r0 != 0) goto L_0x00d5;
    L_0x000b:
        r0 = r4 instanceof com.gaana.models.OfflineTrack;
        if (r0 != 0) goto L_0x00d5;
    L_0x000f:
        r0 = r4 instanceof com.gaana.models.Item;
        if (r0 == 0) goto L_0x0024;
    L_0x0013:
        r1 = r4;
        r1 = (com.gaana.models.Item) r1;
        r1 = r1.getEntityType();
        r2 = com.constants.c.c.c;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0024;
    L_0x0022:
        goto L_0x00d5;
    L_0x0024:
        r1 = r4 instanceof com.gaana.models.Playlists.Playlist;
        if (r1 != 0) goto L_0x00d2;
    L_0x0028:
        if (r0 == 0) goto L_0x003b;
    L_0x002a:
        r1 = r4;
        r1 = (com.gaana.models.Item) r1;
        r1 = r1.getEntityType();
        r2 = com.constants.c.c.a;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x003b;
    L_0x0039:
        goto L_0x00d2;
    L_0x003b:
        r1 = r4 instanceof com.gaana.models.Albums.Album;
        if (r1 != 0) goto L_0x00cf;
    L_0x003f:
        if (r0 == 0) goto L_0x0052;
    L_0x0041:
        r1 = r4;
        r1 = (com.gaana.models.Item) r1;
        r1 = r1.getEntityType();
        r2 = com.constants.c.c.b;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0052;
    L_0x0050:
        goto L_0x00cf;
    L_0x0052:
        r1 = r4 instanceof com.gaana.models.Artists.Artist;
        if (r1 != 0) goto L_0x00cc;
    L_0x0056:
        if (r0 == 0) goto L_0x0068;
    L_0x0058:
        r1 = r4;
        r1 = (com.gaana.models.Item) r1;
        r1 = r1.getEntityType();
        r2 = com.constants.c.c.d;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x0068;
    L_0x0067:
        goto L_0x00cc;
    L_0x0068:
        r1 = r4 instanceof com.gaana.models.Radios.Radio;
        if (r1 != 0) goto L_0x00c9;
    L_0x006c:
        if (r0 == 0) goto L_0x008a;
    L_0x006e:
        r1 = r4;
        r1 = (com.gaana.models.Item) r1;
        r2 = r1.getEntityType();
        r3 = com.constants.c.d.d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x00c9;
    L_0x007d:
        r1 = r1.getEntityType();
        r2 = com.constants.c.d.c;
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x008a;
    L_0x0089:
        goto L_0x00c9;
    L_0x008a:
        r1 = r4 instanceof com.gaana.models.DiscoverTags.DiscoverTag;
        if (r1 != 0) goto L_0x0099;
    L_0x008e:
        if (r0 == 0) goto L_0x00c4;
    L_0x0090:
        r0 = r4;
        r0 = (com.gaana.models.Item) r0;
        r0 = r0.getEntityType();
        if (r0 != 0) goto L_0x00c4;
    L_0x0099:
        if (r1 == 0) goto L_0x00a2;
    L_0x009b:
        r4 = (com.gaana.models.DiscoverTags.DiscoverTag) r4;
        r4 = r4.getTagEntityType();
        goto L_0x00a8;
    L_0x00a2:
        r4 = (com.gaana.models.Item) r4;
        r4 = r4.getEntityType();
    L_0x00a8:
        r0 = com.constants.c.d.a;
        r0 = r0.equals(r4);
        if (r0 == 0) goto L_0x00b3;
    L_0x00b0:
        r4 = e;
        return r4;
    L_0x00b3:
        r0 = com.constants.c.d.c;
        r0 = r0.equals(r4);
        if (r0 != 0) goto L_0x00c6;
    L_0x00bb:
        r0 = com.constants.c.d.d;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x00c4;
    L_0x00c3:
        goto L_0x00c6;
    L_0x00c4:
        r4 = 0;
        return r4;
    L_0x00c6:
        r4 = d;
        return r4;
    L_0x00c9:
        r4 = d;
        return r4;
    L_0x00cc:
        r4 = g;
        return r4;
    L_0x00cf:
        r4 = f;
        return r4;
    L_0x00d2:
        r4 = e;
        return r4;
    L_0x00d5:
        r4 = c;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.n.g(com.gaana.models.BusinessObject):java.util.Set");
    }

    public void c() {
        com.i.d.a(new Runnable() {
            public void run() {
                synchronized (n.i) {
                    n.this.b();
                    n.c.addAll(c.a().d());
                    n.f.addAll(c.a().f());
                    n.g.addAll(c.a().g());
                    n.e.addAll(c.a().e());
                    n.d.addAll(c.a().j());
                    n.d.addAll(c.a().h());
                    n.h.addAll(c.a().i());
                    n.this.j = true;
                }
            }
        });
    }

    public void a(String str, String str2) {
        u.a().a("Favorite", str, str2);
    }

    public void b(String str, String str2) {
        u.a().a("UnFavorite", str, str2);
    }
}
