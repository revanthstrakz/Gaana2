package com.managers;

import android.content.Context;
import android.text.TextUtils;
import com.constants.Constants;
import com.constants.Constants.QUEUE_ACTION;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSessionManager;
import com.gaana.lrc.LrcRow;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.o;
import com.services.d;
import com.services.l.aj;
import com.services.l.ak;
import com.services.l.ax;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PlayerManager {
    private static PlayerTrack F;
    public static boolean a;
    public static boolean b;
    private static PlayerManager d;
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private ArrayList<Track> D;
    private BusinessObject E;
    private ArrayList<PlayerTrack> G = null;
    private ArrayList<PlayerTrack> H = null;
    public ax c;
    private a e;
    private final Object f = new Object();
    private Track g = null;
    private int h = 0;
    private ArrayList<PlayerTrack> i;
    private ArrayList<String> j;
    private ArrayList<PlayerTrack> k;
    private PlayerTrack l;
    private int m = -1;
    private List<LrcRow> n;
    private int o;
    private GaanaApplication p;
    private Context q;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private aj w;
    private PlayerType x = PlayerType.GAANA;
    private boolean y = false;
    private ak z;

    public enum PlayerType {
        GAANA,
        GAANA_RADIO,
        OFFLINE
    }

    public enum PlaySequenceType {
        CURRENT,
        NEXT,
        PREV
    }

    public interface a {
        void a(boolean z);

        void i();

        void j();

        void k();
    }

    private PlayerManager(Context context) {
        this.q = context.getApplicationContext();
        this.p = (GaanaApplication) context.getApplicationContext();
    }

    public static PlayerManager a(Context context) {
        if (d == null) {
            d = new PlayerManager(context);
        }
        return d;
    }

    public PlayerTrack a() {
        return F;
    }

    public void a(PlayerTrack playerTrack) {
        F = playerTrack;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a(aj ajVar) {
        this.w = ajVar;
    }

    public void a(ak akVar) {
        this.z = akVar;
    }

    public boolean b() {
        return this.t;
    }

    public void c() {
        d.a().a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
        this.t = false;
        this.j = null;
        o.a().a(new ArrayList());
    }

    public void a(boolean z) {
        this.C = z;
    }

    public void a(boolean z, ArrayList<PlayerTrack> arrayList) {
        d.a().a("PREFERENCE_KEY_SHUFFLE_STATUS", z, true);
        int i = 0;
        ArrayList g;
        int i2;
        if (z) {
            if (!this.C || arrayList == null) {
                this.j = r();
            } else {
                this.j = c((ArrayList) arrayList);
            }
            o.a().a(this.j);
            int d = d(this.l);
            if (this.C && arrayList != null) {
                this.C = false;
                Collections.shuffle(arrayList);
                l(false);
                if (this.i == null) {
                    this.i = new ArrayList();
                }
                g = g((ArrayList) arrayList);
                this.i.addAll(g);
                b((PlayerTrack) g.get(0));
                d(this.i, this.l, d(this.l));
            } else if (d < this.i.size() - 1) {
                i2 = d + 1;
                g = new ArrayList(this.i.subList(i2, this.i.size()));
                Collections.shuffle(g);
                while (i < g.size()) {
                    this.i.set(i2 + i, g.get(i));
                    i++;
                }
                d(this.i, this.l, d);
            }
        } else if (this.t) {
            g = new ArrayList();
            for (i2 = 0; i2 <= this.m; i2++) {
                g.add(i2, this.i.get(i2));
            }
            for (i2 = this.m + 1; i2 < this.i.size(); i2++) {
                g.add(this.l);
            }
            i2 = this.m + 1;
            while (i < this.j.size() && i2 <= this.i.size() - 1) {
                for (int i3 = this.m + 1; i3 < this.i.size(); i3++) {
                    if (((String) this.j.get(i)).equalsIgnoreCase(((PlayerTrack) this.i.get(i3)).h())) {
                        g.set(i2, this.i.get(i3));
                        i2++;
                        break;
                    }
                }
                i++;
            }
            i = c(g, this.l);
            if (this.m < g.size() && i < g.size()) {
                Collections.swap(g, this.m, i);
            }
            d(g, this.l, this.m);
            this.j = null;
            o.a().a(new ArrayList());
        } else {
            this.j = null;
            o.a().a(new ArrayList());
        }
        this.t = z;
    }

    public boolean d() {
        return this.r;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public boolean e() {
        return this.s;
    }

    public void c(boolean z) {
        this.s = z;
    }

    public void d(boolean z) {
        this.y = z;
    }

    public boolean f() {
        return this.y;
    }

    public boolean g() {
        return this.A;
    }

    public void e(boolean z) {
        this.A = z;
    }

    public boolean h() {
        return this.B;
    }

    public void f(boolean z) {
        this.B = z;
    }

    public void b(PlayerTrack playerTrack) {
        this.l = playerTrack;
    }

    public PlayerTrack i() {
        return this.l;
    }

    public void a(PlayerType playerType, Context context) {
        boolean z = this.x != playerType;
        this.x = playerType;
        if (playerType == PlayerType.GAANA) {
            ad.a(context).a(Boolean.valueOf(false));
            ad.a(context).d("");
            ad.a(context).b(Boolean.valueOf(false));
            ad.a(context).e("");
            j();
        }
        if (z && this.p.getPlayerStatus() && this.w != null) {
            this.w.a(this.x);
        }
    }

    public void a(ArrayList<PlayerTrack> arrayList, boolean z, boolean z2) {
        if (this.G != null) {
            j(true);
        }
        d(true);
        this.k = arrayList;
        if (z2 && this.z != null) {
            this.z.a(Boolean.valueOf(z));
        }
    }

    private ArrayList<PlayerTrack> a(String str, int i, ArrayList<Track> arrayList, String str2, BusinessObject businessObject) {
        if (arrayList == null) {
            return null;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PlayerTrack playerTrack = new PlayerTrack((Track) it.next(), str, i, str2);
            playerTrack.f(GaanaApplication.getInstance().getPageName());
            arrayList2.add(playerTrack);
        }
        return arrayList2;
    }

    private PlayerTrack a(String str, int i, Track track, String str2, BusinessObject businessObject) {
        if (track == null) {
            return null;
        }
        PlayerTrack playerTrack = new PlayerTrack(track, str, i, str2);
        playerTrack.f(GaanaApplication.getInstance().getPageName());
        return playerTrack;
    }

    private PlayerTrack a(String str, int i, Track track, String str2, BusinessObject businessObject, boolean z) {
        if (track == null) {
            return null;
        }
        PlayerTrack playerTrack = new PlayerTrack(track, str, i, str2);
        playerTrack.f(GaanaApplication.getInstance().getPageName());
        if (!z) {
            return playerTrack;
        }
        playerTrack.d(true);
        return playerTrack;
    }

    public void j() {
        if (this.x == PlayerType.GAANA) {
            a = true;
        }
        if (this.i != null) {
            this.o = this.i.size();
        } else {
            this.o = 0;
        }
        if (this.l == null || this.l.a(true) == null) {
            b((PlayerTrack) this.i.get(0));
        }
        this.m = d(this.l);
    }

    public void k() {
        if (this.k != null && this.k.size() != 0) {
            this.i = this.k;
            this.o = this.i.size();
            b((PlayerTrack) this.i.get(0));
            this.m = d(this.l);
        }
    }

    public int l() {
        return this.i != null ? this.i.size() : 0;
    }

    public PlayerType m() {
        return this.x;
    }

    public void a(PlayerType playerType) {
        if (!(this.x == playerType || playerType != PlayerType.GAANA_RADIO || this.i == null)) {
            o.a().a(this.i, s(), null);
        }
        this.x = playerType;
        if (playerType == PlayerType.GAANA) {
            ad.a(this.q).a(Boolean.valueOf(false));
            ad.a(this.q).d("");
            ad.a(this.q).b(Boolean.valueOf(false));
            ad.a(this.q).e("");
        }
    }

    public ArrayList<PlayerTrack> n() {
        return this.i;
    }

    public void a(ArrayList<String> arrayList) {
        this.j = (ArrayList) arrayList.clone();
        this.t = true;
    }

    public void c(PlayerTrack playerTrack) {
        this.m = d(playerTrack);
    }

    public void a(List<LrcRow> list) {
        this.n = list;
    }

    public List<LrcRow> o() {
        return this.n;
    }

    public void a(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack) {
        a((ArrayList) arrayList, playerTrack, 0, false, false);
    }

    public void a(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i, boolean z) {
        a((ArrayList) arrayList, playerTrack, i, false, z);
    }

    public void a(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i) {
        a((ArrayList) arrayList, playerTrack, i, false, false);
    }

    public void b(ArrayList<PlayerTrack> arrayList) {
        this.G = new ArrayList();
        this.G.addAll(arrayList);
    }

    public void b(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i) {
        synchronized (this.f) {
            if (this.G != null) {
                j(true);
            }
            if (arrayList != null) {
                p();
                this.H = null;
                if (this.i == null) {
                    this.i = new ArrayList();
                }
                int d = d(playerTrack);
                if (d >= 0) {
                    this.i.remove(d);
                }
                b(playerTrack);
                b((ArrayList) arrayList, playerTrack);
            }
            e(this.i, playerTrack, i);
            J();
        }
    }

    public void p() {
        if (this.x == PlayerType.GAANA_RADIO) {
            this.i = o.a().e();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0015 A:{Catch:{ all -> 0x0068 }} */
    public void b(java.util.ArrayList<com.models.PlayerTrack> r6, com.models.PlayerTrack r7) {
        /*
        r5 = this;
        r0 = r5.f;
        monitor-enter(r0);
        if (r6 == 0) goto L_0x006a;
    L_0x0005:
        r1 = r6.size();	 Catch:{ all -> 0x0068 }
        if (r1 <= 0) goto L_0x006a;
    L_0x000b:
        r1 = r6.iterator();	 Catch:{ all -> 0x0068 }
    L_0x000f:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0068 }
        if (r2 == 0) goto L_0x0033;
    L_0x0015:
        r2 = r1.next();	 Catch:{ all -> 0x0068 }
        r2 = (com.models.PlayerTrack) r2;	 Catch:{ all -> 0x0068 }
        r2 = r5.d(r2);	 Catch:{ all -> 0x0068 }
        if (r2 < 0) goto L_0x000f;
    L_0x0021:
        r3 = r5.i;	 Catch:{ all -> 0x0068 }
        if (r3 == 0) goto L_0x000f;
    L_0x0025:
        r3 = r5.i;	 Catch:{ all -> 0x0068 }
        r3 = r3.size();	 Catch:{ all -> 0x0068 }
        if (r3 <= 0) goto L_0x000f;
    L_0x002d:
        r3 = r5.i;	 Catch:{ all -> 0x0068 }
        r3.remove(r2);	 Catch:{ all -> 0x0068 }
        goto L_0x000f;
    L_0x0033:
        r7 = r5.c(r6, r7);	 Catch:{ all -> 0x0068 }
        if (r7 < 0) goto L_0x006a;
    L_0x0039:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0068 }
        r2 = 0;
        r3 = r6.subList(r2, r7);	 Catch:{ all -> 0x0068 }
        r1.<init>(r3);	 Catch:{ all -> 0x0068 }
        r3 = new java.util.ArrayList;	 Catch:{ all -> 0x0068 }
        r4 = r6.size();	 Catch:{ all -> 0x0068 }
        r6 = r6.subList(r7, r4);	 Catch:{ all -> 0x0068 }
        r3.<init>(r6);	 Catch:{ all -> 0x0068 }
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0068 }
        r6.<init>();	 Catch:{ all -> 0x0068 }
        r6.addAll(r3);	 Catch:{ all -> 0x0068 }
        r6.addAll(r1);	 Catch:{ all -> 0x0068 }
        r6 = r5.e(r6);	 Catch:{ all -> 0x0068 }
        r7 = r5.i;	 Catch:{ all -> 0x0068 }
        r7.addAll(r6);	 Catch:{ all -> 0x0068 }
        r5.l(r2);	 Catch:{ all -> 0x0068 }
        goto L_0x006a;
    L_0x0068:
        r6 = move-exception;
        goto L_0x0079;
    L_0x006a:
        r5.K();	 Catch:{ all -> 0x0068 }
        r6 = r5.e;	 Catch:{ all -> 0x0068 }
        if (r6 == 0) goto L_0x0077;
    L_0x0071:
        r6 = r5.e;	 Catch:{ all -> 0x0068 }
        r7 = 1;
        r6.a(r7);	 Catch:{ all -> 0x0068 }
    L_0x0077:
        monitor-exit(r0);	 Catch:{ all -> 0x0068 }
        return;
    L_0x0079:
        monitor-exit(r0);	 Catch:{ all -> 0x0068 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.PlayerManager.b(java.util.ArrayList, com.models.PlayerTrack):void");
    }

    private void J() {
        b(false);
        c(false);
    }

    private void l(boolean z) {
        int i = 0;
        int i2;
        PlayerTrack playerTrack;
        if (Constants.cY) {
            if (this.i != null && z) {
                for (i2 = 0; i2 < this.i.size(); i2++) {
                    playerTrack = (PlayerTrack) this.i.get(i2);
                    if (!playerTrack.h().equals(this.l.h())) {
                        JukeSessionManager.getInstance().addPlayNext(JukeSessionManager.getInstance().getJukeSessionPlaylist(), playerTrack.h());
                    }
                }
            }
            if (z) {
                aj.a().a(this.q, this.q.getResources().getString(R.string.playing_all_songs_party));
            } else if (!this.l.j().equals("PARTY")) {
                aj.a().a(this.q, this.q.getResources().getString(R.string.playing_song_party));
            }
            this.i = new ArrayList();
            this.i.add(this.l);
            this.m = 0;
        } else if (this.i != null && this.i.size() > 100) {
            int d = d(this.l);
            i2 = d - 100;
            if (d != -1 && i2 > 0) {
                ArrayList arrayList = new ArrayList();
                while (i < i2) {
                    playerTrack = (PlayerTrack) this.i.get(i);
                    if (!playerTrack.m()) {
                        arrayList.add(playerTrack);
                    }
                    i++;
                }
                if (arrayList.size() > 0) {
                    this.i.removeAll(arrayList);
                }
            }
        }
    }

    private void K() {
        synchronized (this.f) {
            if (this.i != null) {
                if (this.i.size() > Constants.dg) {
                    int size = this.i.size() - Constants.dg;
                    int i = 0;
                    Iterator it = this.i.iterator();
                    while (it.hasNext()) {
                        it.next();
                        if (i >= size) {
                            break;
                        }
                        it.remove();
                        i++;
                    }
                }
                this.m = d(this.l);
                this.o = this.i.size();
            }
        }
    }

    private ArrayList<?> e(ArrayList<?> arrayList) {
        synchronized (this.f) {
            if (arrayList != null) {
                try {
                    if (arrayList.size() > Constants.dg) {
                        int i = 0;
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            it.next();
                            if (i >= Constants.dg) {
                                it.remove();
                            }
                            i++;
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        return arrayList;
    }

    private ArrayList<Track> f(ArrayList<Track> arrayList) {
        synchronized (this.f) {
            if (arrayList != null) {
                try {
                    int i = 0;
                    int size = this.i != null ? this.i.size() : 0;
                    if (arrayList.size() + size > Constants.dg) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            it.next();
                            if (size + i >= Constants.dg) {
                                it.remove();
                            }
                            i++;
                        }
                    }
                } finally {
                }
            }
        }
        return arrayList;
    }

    public void a(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i, boolean z, boolean z2) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null) {
            p();
            if (this.i != null) {
                g((ArrayList) arrayList);
                if (z) {
                    this.i.addAll(this.i.isEmpty() ? 0 : 1 + d(playerTrack), arrayList);
                } else {
                    this.i.addAll(arrayList);
                }
            } else {
                this.i = arrayList;
            }
            K();
        }
        if (!(d(playerTrack) != -1 || this.i == null || this.i.isEmpty())) {
            b(null);
            i = 0;
        }
        e(this.i, playerTrack, i);
        if (z2) {
            J();
        }
    }

    public void a(ax axVar) {
        this.c = axVar;
    }

    public void c(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i) {
        if (this.G != null) {
            j(true);
        }
        this.i = arrayList;
        e(this.i, playerTrack, i);
    }

    public void d(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null) {
            this.i = arrayList;
            K();
        }
        e(this.i, playerTrack, i);
        J();
    }

    public void g(boolean z) {
        if (this.e != null) {
            this.e.a(z);
        }
    }

    private void e(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack, int i) {
        if (arrayList != null && playerTrack != null) {
            b(playerTrack);
            this.m = d(playerTrack);
            this.o = arrayList.size();
        } else if (arrayList != null && !arrayList.isEmpty() && playerTrack == null) {
            b((PlayerTrack) arrayList.get(i));
            this.m = i;
            this.o = arrayList.size();
        } else if (arrayList == null && playerTrack != null) {
            b(playerTrack);
            this.m = d(playerTrack);
        } else if (arrayList == null && playerTrack == null) {
            b((PlayerTrack) arrayList.get(i));
            this.m = i;
        }
    }

    public void q() {
        Iterator it = a(this.q).n().iterator();
        int i = 0;
        while (it.hasNext()) {
            PlayerTrack playerTrack = (PlayerTrack) it.next();
            if (playerTrack.e() == SOURCE_TYPE.CF_TRACK.ordinal() && !playerTrack.m()) {
                it.remove();
                i++;
            }
        }
        if (i > 0 && this.e != null) {
            this.e.k();
        }
    }

    public ArrayList<String> r() {
        ArrayList arrayList = new ArrayList();
        if (this.i != null) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack = (PlayerTrack) it.next();
                if (!(playerTrack == null || playerTrack.h() == null)) {
                    arrayList.add(playerTrack.h());
                }
            }
        }
        return arrayList;
    }

    public ArrayList<String> c(ArrayList<PlayerTrack> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack = (PlayerTrack) it.next();
                if (!(playerTrack == null || playerTrack.h() == null)) {
                    arrayList2.add(playerTrack.h());
                }
            }
        }
        return arrayList2;
    }

    public int s() {
        return this.m;
    }

    public boolean t() {
        return this.m >= this.o - 1;
    }

    public boolean u() {
        return this.m > 0;
    }

    public boolean v() {
        return (this.x == PlayerType.GAANA && (this.r || this.s)) ? false : true;
    }

    public PlayerTrack a(PlaySequenceType playSequenceType) {
        switch (playSequenceType) {
            case NEXT:
                switch (this.x) {
                    case GAANA:
                        if (!this.s) {
                            if (!t()) {
                                this.m++;
                                b((PlayerTrack) this.i.get(this.m));
                                break;
                            }
                            b(null);
                            break;
                        }
                        if (t()) {
                            this.m = 0;
                        } else {
                            this.m++;
                        }
                        b((PlayerTrack) this.i.get(this.m));
                        this.l.a(true);
                        break;
                    case GAANA_RADIO:
                        if (!(this.i == null || this.i.size() == 0 || t())) {
                            this.m++;
                            b((PlayerTrack) this.i.get(this.m));
                            break;
                        }
                }
                break;
            case PREV:
                if (!this.s) {
                    if (u()) {
                        this.m--;
                        b((PlayerTrack) this.i.get(this.m));
                        break;
                    }
                }
                if (this.m < 0) {
                    this.m = 0;
                } else if (this.m == 0) {
                    this.m = this.o - 1;
                } else {
                    this.m--;
                }
                b((PlayerTrack) this.i.get(this.m));
                break;
                break;
        }
        if (!(this.l != null || this.i == null || this.i.size() == 0)) {
            b((PlayerTrack) this.i.get(this.m));
        }
        return this.l;
    }

    public PlayerTrack b(PlaySequenceType playSequenceType) {
        int i = this.m;
        switch (playSequenceType) {
            case CURRENT:
                return this.l;
            case NEXT:
                switch (this.x) {
                    case GAANA:
                        if (this.s) {
                            return (PlayerTrack) this.i.get(t() ? 0 : i + 1);
                        } else if (t()) {
                            return null;
                        } else {
                            return (PlayerTrack) this.i.get(i + 1);
                        }
                    case GAANA_RADIO:
                        if (this.i == null || this.i.size() == 0 || t()) {
                            return null;
                        }
                        return (PlayerTrack) this.i.get(i + 1);
                    default:
                        return null;
                }
            case PREV:
                if (this.s) {
                    return (PlayerTrack) this.i.get(i == 0 ? this.o - 1 : i - 1);
                } else if (!u()) {
                    return null;
                } else {
                    return (PlayerTrack) this.i.get(i - 1);
                }
            default:
                return null;
        }
    }

    public PlayerTrack w() {
        int i = this.m;
        if (this.s) {
            return (PlayerTrack) this.i.get(i == 0 ? this.o - 1 : i - 1);
        } else if (!u()) {
            return null;
        } else {
            return (PlayerTrack) this.i.get(i - 1);
        }
    }

    public PlayerTrack x() {
        int i = this.m;
        if (this.s) {
            return (PlayerTrack) this.i.get(i == this.o + -1 ? 0 : i + 1);
        } else if (i >= this.o - 1) {
            return null;
        } else {
            return (PlayerTrack) this.i.get(i + 1);
        }
    }

    public boolean y() {
        return this.u;
    }

    public void h(boolean z) {
        this.u = z;
    }

    public boolean z() {
        return this.v;
    }

    public void i(boolean z) {
        this.v = z;
        this.r = z;
    }

    public int a(PlayerTrack playerTrack, Context context, boolean z) {
        if (this.G != null) {
            j(true);
        }
        if (this.x == PlayerType.GAANA_RADIO) {
            aj.a().a(context, context.getString(R.string.feature_not_availble));
            return -1;
        } else if (this.i == null || this.i.size() < Constants.dg) {
            int i;
            if (this.i == null || this.i.size() == 0) {
                this.i = new ArrayList();
                b(playerTrack);
            }
            int i2 = 0;
            while (i2 < this.i.size()) {
                if (playerTrack.h().equals(((PlayerTrack) this.i.get(i2)).a(true).getBusinessObjId())) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 != -1 && playerTrack.h().equals(this.l.h())) {
                aj.a().a(context, context.getString(R.string.already_playing));
                i = 0;
            } else if (this.i.isEmpty() || i2 != this.i.size() - 1 || z) {
                this.H = null;
                aj.a().a(context, context.getString(R.string.already_added_queue));
                if (i2 != -1) {
                    this.i.remove(i2);
                }
                playerTrack.d(this.p.getPlayoutSectionName());
                if (z) {
                    ArrayList arrayList = this.i;
                    i2 = (i2 == -1 || i2 >= this.m) ? this.m + 1 : this.m;
                    arrayList.add(i2, playerTrack);
                } else {
                    this.i.add(this.i.size(), playerTrack);
                }
                if (b()) {
                    this.j.add(this.j.size(), playerTrack.h());
                    o.a().a(this.j);
                }
                this.o = this.i.size();
                if (this.o == 1) {
                    this.m = 0;
                    i = 1;
                } else {
                    this.m = d(this.l);
                    i = 0;
                }
                a(PlayerType.GAANA);
            } else {
                aj.a().a(context, context.getString(R.string.already_added_queue));
                return 0;
            }
            if (this.e != null) {
                this.e.i();
            } else if (z && a(GaanaApplication.getContext()).d()) {
                a(GaanaApplication.getContext()).b(false);
                d.a().a("PREFERENCE_KEY_REPEAT_STATUS", 0, true);
            }
            return i;
        } else {
            aj a = aj.a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getString(R.string.exceeded_queue));
            stringBuilder.append(Constants.dg);
            stringBuilder.append(context.getString(R.string.remove_songs));
            a.a(context, stringBuilder.toString());
            return -1;
        }
    }

    public boolean a(PlayerTrack playerTrack, Context context) {
        boolean z = false;
        if (this.x == PlayerType.GAANA_RADIO) {
            aj.a().a(context, context.getString(R.string.feature_not_availble));
            return false;
        }
        if (this.i == null || !this.p.getPlayerStatus()) {
            this.i = new ArrayList();
            this.m = -1;
        }
        int i = 0;
        while (i < this.i.size()) {
            if (playerTrack.h().equals(((PlayerTrack) this.i.get(i)).a(true).getBusinessObjId())) {
                break;
            }
            i++;
        }
        i = -1;
        if (this.m == -1 || i != this.m) {
            if (i != -1) {
                this.i.remove(i);
            }
            this.m = d(this.l);
            this.i.add(this.m + 1, playerTrack);
        }
        this.o = this.i.size();
        if (this.o == 1) {
            this.m = 0;
            z = true;
        }
        a(PlayerType.GAANA);
        if (this.m != -1) {
            aj.a().a(context, context.getString(R.string.song_played_next));
        }
        if (this.e != null) {
            this.e.i();
        }
        return z;
    }

    public void a(String str, int i, String str2, BusinessObject businessObject, ArrayList<Track> arrayList, Context context) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null && arrayList.size() != 0) {
            this.H = null;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            a(context, a(str, i, e(arrayList2), str2, businessObject));
        }
    }

    public void a(ArrayList<PlayerTrack> arrayList, Context context) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null && arrayList.size() != 0) {
            this.H = null;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            a(context, e(arrayList2));
        }
    }

    public void a(Context context, ArrayList<PlayerTrack> arrayList) {
        b((PlayerTrack) arrayList.get(0));
        this.j = c((ArrayList) arrayList);
        a(true);
        a(true, (ArrayList) arrayList);
        a(PlayerType.GAANA, this.q);
        b(context);
    }

    public void a(String str, int i, String str2, BusinessObject businessObject, ArrayList<Track> arrayList, Context context, boolean z) {
        ArrayList<Track> arrayList2 = arrayList;
        if (this.G != null) {
            j(true);
        }
        if (arrayList2 != null && arrayList.size() != 0) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(arrayList2);
            c();
            this.H = null;
            String str3 = str;
            int i2 = i;
            String str4 = str2;
            BusinessObject businessObject2 = businessObject;
            b(a(str3, i2, (Track) arrayList3.get(0), str4, businessObject2, z));
            a(a(str3, i2, e(arrayList3), str4, businessObject2), this.l, 0);
            l(true);
            a(PlayerType.GAANA, this.q);
            b(context);
            J();
        }
    }

    public void b(String str, int i, String str2, BusinessObject businessObject, ArrayList<Track> arrayList, Context context) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null && arrayList.size() != 0) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            c();
            this.H = null;
            String str3 = str;
            int i2 = i;
            String str4 = str2;
            BusinessObject businessObject2 = businessObject;
            b(a(str3, i2, (Track) arrayList2.get(0), str4, businessObject2));
            a(a(str3, i2, e(arrayList2), str4, businessObject2), this.l, 0);
            l(true);
            a(PlayerType.GAANA, this.q);
            b(context);
            J();
        }
    }

    public void b(ArrayList<PlayerTrack> arrayList, Context context) {
        if (this.G != null) {
            j(true);
        }
        if (arrayList != null && arrayList.size() != 0) {
            c();
            this.H = null;
            b((PlayerTrack) arrayList.get(0));
            a(e((ArrayList) arrayList), this.l, 0);
            l(true);
            a(PlayerType.GAANA, this.q);
            b(context);
            J();
        }
    }

    public void a(Context context, ArrayList<Track> arrayList, String str, String str2, String str3) {
        ArrayList arrayList2;
        if (arrayList != null) {
            arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack = new PlayerTrack((Track) it.next(), str, SOURCE_TYPE.TRACK.ordinal(), str3);
                playerTrack.f(str2);
                playerTrack.d(str3);
                arrayList2.add(playerTrack);
            }
        } else {
            arrayList2 = null;
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            b(arrayList2, context);
        }
    }

    private ArrayList<PlayerTrack> g(ArrayList<PlayerTrack> arrayList) {
        synchronized (this.f) {
            ArrayList r = r();
            for (int i = 0; i < arrayList.size(); i++) {
                String h = ((PlayerTrack) arrayList.get(i)).h();
                if (r.contains(h)) {
                    int indexOf = r.indexOf(h);
                    if (this.i.size() > indexOf) {
                        this.i.remove(indexOf);
                        r.remove(indexOf);
                    }
                }
            }
        }
        return arrayList;
    }

    public int d(PlayerTrack playerTrack) {
        int i = 0;
        if (playerTrack == null) {
            return 0;
        }
        if (this.i != null) {
            while (i < this.i.size()) {
                if (playerTrack.h().equals(((PlayerTrack) this.i.get(i)).h())) {
                    break;
                }
                i++;
            }
        }
        i = -1;
        return i;
    }

    public int c(ArrayList<PlayerTrack> arrayList, PlayerTrack playerTrack) {
        int i = 0;
        if (playerTrack == null || TextUtils.isEmpty(playerTrack.h())) {
            return 0;
        }
        if (arrayList != null) {
            while (i < arrayList.size()) {
                if (playerTrack.h().equals(((PlayerTrack) arrayList.get(i)).h())) {
                    break;
                }
                i++;
            }
        }
        i = -1;
        return i;
    }

    public void A() {
        this.i = null;
        this.l = null;
        this.m = -1;
        if (this.e != null) {
            this.e.k();
        }
    }

    public PlayerTrack a(Track track) {
        for (int i = 0; i < this.i.size(); i++) {
            if (track.getBusinessObjId().equals(((PlayerTrack) this.i.get(i)).h())) {
                return (PlayerTrack) this.i.get(i);
            }
        }
        return null;
    }

    public PlayerTrack a(int i) {
        return (i < 0 || this.i == null || i >= this.i.size()) ? null : (PlayerTrack) this.i.get(i);
    }

    public boolean a(String str) {
        if (this.i != null) {
            for (int i = 0; i < this.i.size(); i++) {
                if (((PlayerTrack) this.i.get(i)).h().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void b(Context context) {
        if (context instanceof GaanaActivity) {
            ((GaanaActivity) context).setUpdatePlayerFragment();
        }
    }

    public void a(ArrayList<?> arrayList, BusinessObject businessObject, Context context, boolean z) {
        a((ArrayList) arrayList, businessObject, context, true, z);
    }

    public void a(ArrayList<?> arrayList, BusinessObject businessObject, Context context, boolean z, boolean z2) {
        if (this.G != null) {
            j(true);
        }
        if (this.x == PlayerType.GAANA_RADIO) {
            if (z) {
                aj.a().a(context, context.getString(R.string.feature_not_availble));
            }
        } else if (this.i == null || this.i.size() < Constants.dg) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            ArrayList f = f(arrayList2);
            a(PlayerType.GAANA);
            if (z) {
                f = b(f, businessObject);
            } else {
                f = a(f, businessObject);
            }
            if (f == null || f.size() <= 0) {
                if (z) {
                    aj.a().a(context, context.getString(R.string.already_in_queue));
                }
                return;
            }
            int i = (this.i == null || this.i.size() == 0) ? true : 0;
            if (this.m == -1) {
                this.m = 0;
            }
            a(this.q).a(f, this.l, this.m, z2, false);
            int size = f.size();
            if (b()) {
                ArrayList arrayList3 = new ArrayList();
                if (f.size() > 0) {
                    Iterator it = f.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(((PlayerTrack) it.next()).h());
                    }
                }
                this.j.addAll(arrayList3);
                o.a().a(this.j);
            }
            a(PlayerType.GAANA);
            if (size > 0) {
                this.H = null;
                if (z) {
                    aj.a().a(context, context.getString(R.string.already_added_queue));
                }
            } else if (size == 0 && z) {
                aj.a().a(context, context.getString(R.string.already_in_queue));
            }
            this.o = this.i.size();
            if (i != 0) {
                this.m = 0;
                b((PlayerTrack) f.get(0));
                e(true);
                j();
            }
            if (this.e != null) {
                this.e.i();
            }
        } else {
            if (z) {
                aj a = aj.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.exceeded_queue));
                stringBuilder.append(Constants.dg);
                stringBuilder.append(context.getString(R.string.remove_songs));
                a.a(context, stringBuilder.toString());
            }
        }
    }

    public ArrayList<PlayerTrack> a(ArrayList<Track> arrayList, BusinessObject businessObject) {
        int ordinal = SOURCE_TYPE.CF_TRACK.ordinal();
        if (arrayList == null) {
            return null;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject2 = (BusinessObject) it.next();
            Track track = (Track) businessObject2;
            if (!(businessObject2 == null || TextUtils.isEmpty(businessObject2.getBusinessObjId()))) {
                PlayerTrack playerTrack = new PlayerTrack();
                playerTrack.f(GaanaApplication.getInstance().getPageName());
                playerTrack.a(track);
                playerTrack.b(track.getSeedTrackId());
                playerTrack.e(businessObject2.getBusinessObjId());
                playerTrack.c(track.getName());
                playerTrack.a(track.getBusinessObjId());
                playerTrack.b(ordinal);
                playerTrack.d(PLAYOUT_SECTION_TYPE.CF_TRACK.name());
                if (this.l == null || !this.l.h().equals(businessObject2.getBusinessObjId())) {
                    arrayList2.add(playerTrack);
                }
            }
        }
        return arrayList2;
    }

    public ArrayList<PlayerTrack> b(ArrayList<Track> arrayList, BusinessObject businessObject) {
        String str = "";
        String str2 = "";
        int ordinal = SOURCE_TYPE.OTHER.ordinal();
        if (businessObject != null) {
            str = businessObject.getName();
            str2 = businessObject.getBusinessObjId();
            if (businessObject instanceof Album) {
                ordinal = SOURCE_TYPE.ALBUM.ordinal();
            } else if (businessObject instanceof Playlist) {
                ordinal = SOURCE_TYPE.PLAYLIST.ordinal();
            }
        }
        if (arrayList == null) {
            return null;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject2 = (BusinessObject) it.next();
            if (!(businessObject2 == null || TextUtils.isEmpty(businessObject2.getBusinessObjId()))) {
                PlayerTrack playerTrack = new PlayerTrack();
                playerTrack.f(GaanaApplication.getInstance().getPageName());
                if (businessObject2 instanceof Track) {
                    Track track = (Track) businessObject2;
                    playerTrack.a(track);
                    playerTrack.b(track.getSeedTrackId());
                }
                playerTrack.e(businessObject2.getBusinessObjId());
                playerTrack.c(str);
                playerTrack.a(str2);
                playerTrack.b(ordinal);
                playerTrack.d(this.p.getPlayoutSectionName());
                if (this.l == null || !this.l.h().equals(businessObject2.getBusinessObjId())) {
                    arrayList2.add(playerTrack);
                }
            }
        }
        return arrayList2;
    }

    public void a(BusinessObject businessObject, boolean z, Context context) {
        if (this.G != null) {
            j(false);
        }
        if (this.i != null) {
            PlayerTrack a = a((Track) businessObject);
            if (a != null) {
                int d = d(a);
                if (d >= 0) {
                    this.i.remove(d);
                    a(this.q).a(a.h(), false);
                    a(this.q).a(QUEUE_ACTION.SWIPE, d, -1);
                    if (!z) {
                        aj.a().a(context, context.getString(R.string.track_removed));
                    }
                }
            }
            if (this.i.size() == 0) {
                o.d(GaanaApplication.getContext());
            }
        }
    }

    public void a(String str, boolean z) {
        if (!this.t) {
            return;
        }
        if (z) {
            this.j.add(str);
        } else {
            this.j.remove(str);
        }
    }

    private void b(int i) {
        int i2 = 0;
        if (this.G != null) {
            j(false);
        }
        if (this.m == i) {
            int size = this.i.size();
            if (size <= i) {
                if (size < 0) {
                    i2 = -1;
                }
                this.m = i2;
            }
            b(a(this.m));
            if (this.l != null && GaanaMusicService.t()) {
                o.a(this.q);
            }
        }
        if (this.m > i) {
            this.m--;
        }
        this.o = this.i.size();
        if (this.e != null) {
            this.e.k();
        }
    }

    public void a(QUEUE_ACTION queue_action, int i, int i2) {
        if (queue_action == QUEUE_ACTION.MOVE) {
            if (this.m == i) {
                this.m = i2;
            } else if (i < this.m && i2 >= this.m) {
                this.m--;
            } else if (i > this.m && i2 <= this.m) {
                this.m++;
            }
        } else if (queue_action == QUEUE_ACTION.UNDO) {
            if (this.m >= i) {
                this.m++;
            }
            this.o++;
            if (this.e != null) {
                this.e.i();
            }
        } else {
            b(i);
        }
    }

    public void B() {
        if (this.i != null && this.i.size() != 0) {
            int s = s();
            PlayerTrack playerTrack = (PlayerTrack) this.i.get(s);
            this.i.clear();
            this.i.add(playerTrack);
            j(false);
            if (b() && this.j != null) {
                for (int i = 0; i < this.j.size(); i++) {
                    if (this.l.h().equals(this.j.get(i))) {
                        String str = (String) this.j.get(i);
                        this.j = new ArrayList();
                        this.j.add(str);
                        o.a().a(this.j);
                        break;
                    }
                }
            }
            if (!(this.i == null || s == -1)) {
                this.o = this.i.size();
                if (this.o == 1) {
                    this.m = 0;
                }
                this.m = d(this.l);
                if (this.e != null) {
                    this.e.k();
                }
            }
        }
    }

    public void C() {
        if (this.i != null && this.i.size() != 0) {
            this.i.clear();
            if (this.j != null) {
                this.j.clear();
            }
            b(null);
            this.m = -1;
            if (this.e != null) {
                this.e.k();
            }
        }
    }

    public void j(boolean z) {
        if (this.i != null && this.i.size() != 0 && this.G != null && this.G.size() != 0) {
            if (z) {
                this.i.clear();
                b(null);
                this.m = -1;
                if (this.e != null) {
                    this.e.a(true);
                }
            }
            this.G.clear();
            this.G = null;
        }
    }

    public void D() {
        if (this.e != null) {
            this.e.k();
        }
    }

    public Object E() {
        return this.G;
    }

    public Track F() {
        return this.g;
    }

    public void b(Track track) {
        this.g = track;
    }

    public void k(boolean z) {
        if (this.m != -1 && this.i != null && this.i.size() > this.m && this.i.get(this.m) != null) {
            ((PlayerTrack) this.i.get(this.m)).e(z);
        }
    }

    public ArrayList<Track> G() {
        return this.D;
    }

    public void d(ArrayList<Track> arrayList) {
        this.D = arrayList;
    }

    public BusinessObject H() {
        return this.E;
    }

    public void a(BusinessObject businessObject) {
        this.E = businessObject;
    }

    public a I() {
        return this.e;
    }

    public boolean c(Track track) {
        return track != null && GaanaApplication.getInstance().isAppInForeground() && Constants.cH && !Constants.cN && com.utilities.d.g() && GaanaApplication.getInstance().isVideoAutoplay() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue() && !track.isLocalMedia() && ((TextUtils.isEmpty(track.getBusinessObjId()) || !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) && !TextUtils.isEmpty(track.getVerticalUrl()));
    }

    public boolean d(Track track) {
        return track != null && GaanaApplication.getInstance().isAppInForeground() && GaanaApplication.getInstance().isVideoAutoplay() && !Constants.cN && com.utilities.d.g() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue() && !track.isLocalMedia() && ((TextUtils.isEmpty(track.getBusinessObjId()) || !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) && !TextUtils.isEmpty(track.getClipVideoUrl()));
    }
}
