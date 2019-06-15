package com.dynamicview;

import android.content.res.Resources;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.facebook.share.internal.ShareConstants;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.TrackSelectionForDownload.DownloadSelectionType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    private static ArrayList<String> a = new ArrayList();
    private static ArrayList<String> b = new ArrayList();

    static {
        a.add(com.constants.c.a.G);
        a.add(com.constants.c.a.z);
        a.add(com.constants.c.a.P);
        a.add(com.constants.c.a.B);
        a.add(com.constants.c.a.x);
        a.add(com.constants.c.a.F);
        a.add(com.constants.c.a.w);
        a.add(com.constants.c.a.a);
        a.add(com.constants.c.a.J);
        a.add(com.constants.c.a.c);
        a.add(com.constants.c.a.K);
        a.add(com.constants.c.a.L);
        a.add(com.constants.c.a.d);
        a.add(com.constants.c.a.H);
        a.add(com.constants.c.a.y);
        a.add(com.constants.c.a.M);
        a.add(com.constants.c.a.b);
        a.add(com.constants.c.a.N);
        a.add(com.constants.c.a.l);
        a.add(com.constants.c.a.O);
        a.add(com.constants.c.a.u);
        a.add(com.constants.c.a.e);
        a.add(com.constants.c.a.D);
        b.add(com.constants.c.a.I);
        b.add(com.constants.c.a.C);
        b.add(com.constants.c.a.f);
        b.add(com.constants.c.a.j);
        b.add(com.constants.c.a.i);
        b.add(com.constants.c.a.h);
        b.add(com.constants.c.a.g);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x018a  */
    public static com.dynamicview.f.a a(java.lang.String r10) {
        /*
        r0 = com.constants.c.a.I;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0032;
    L_0x0008:
        r10 = new com.dynamicview.f$a;
        r0 = com.gaana.application.GaanaApplication.getContext();
        r0 = r0.getResources();
        r1 = 2131822353; // 0x7f110711 float:1.9277475E38 double:1.0532601877E-314;
        r2 = r0.getString(r1);
        r3 = "url";
        r0 = com.dynamicview.DynamicViewManager.DynamicViewType.section_heading;
        r4 = r0.name();
        r5 = "url_seeall";
        r6 = "source_name";
        r7 = "ad_code";
        r8 = "0";
        r9 = "140";
        r1 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
    L_0x002f:
        r0 = r10;
        goto L_0x0188;
    L_0x0032:
        r0 = com.constants.c.a.C;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0062;
    L_0x003a:
        r0 = new com.dynamicview.f$a;
        r3 = 0;
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.card;
        r4 = r1.name();
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = com.constants.Constants.VIEW_SIZE.CARD_SMALL;
        r10 = r10.getNumVal();
        r0.a(r10);
        r10 = com.constants.Constants.ACTION_TYPE.ONE_TOUCH_DIALOG;
        r10 = r10.getNumVal();
        r0.b(r10);
        goto L_0x0188;
    L_0x0062:
        r0 = com.constants.c.a.f;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0093;
    L_0x006a:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/radio/mirchi";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/radio/mirchi?limit=0,20";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.RADIO_MIRCHI;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/radiomirchi/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.f;
        r0.h(r10);
        goto L_0x0188;
    L_0x0093:
        r0 = com.constants.c.a.g;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x00c4;
    L_0x009b:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/gaana-radio/popular";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/gaana-radio/popular";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.POPULAR_RADIO;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/popularradio/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.g;
        r0.h(r10);
        goto L_0x0188;
    L_0x00c4:
        r0 = com.constants.c.a.h;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x00f5;
    L_0x00cc:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/gaana-radio/artist";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/gaana-radio/artist";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.ARTIST_RADIO;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/artistradio/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.h;
        r0.h(r10);
        goto L_0x0188;
    L_0x00f5:
        r0 = com.constants.c.a.i;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0125;
    L_0x00fd:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/gaana-radio/mood";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/gaana-radio/mood";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MOODS_RADIO;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/moodsradio/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.i;
        r0.h(r10);
        goto L_0x0188;
    L_0x0125:
        r0 = com.constants.c.a.j;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0155;
    L_0x012d:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/gaana-radio/latest";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/gaana-radio/latest";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.LATEST_RADIO;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/latestradio/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.h;
        r0.h(r10);
        goto L_0x0188;
    L_0x0155:
        r0 = com.constants.c.a.k;
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0185;
    L_0x015d:
        r0 = new com.dynamicview.f$a;
        r3 = "https://api.gaana.com/home/gaana-radio/retro";
        r1 = com.dynamicview.DynamicViewManager.DynamicViewType.hor_scroll;
        r4 = r1.name();
        r5 = "https://api.gaana.com/home/gaana-radio/retro";
        r1 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.FLASHBACK_RADIO;
        r6 = r1.name();
        r7 = "";
        r8 = "";
        r9 = "240";
        r1 = r0;
        r2 = r10;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9);
        r10 = "gaana://view/flashbackradio/seeall";
        r0.g(r10);
        r10 = com.constants.c.a.k;
        r0.h(r10);
        goto L_0x0188;
    L_0x0185:
        r10 = 0;
        goto L_0x002f;
    L_0x0188:
        if (r0 == 0) goto L_0x0197;
    L_0x018a:
        r10 = 1;
        r0.d(r10);
        r10 = com.dynamicview.DynamicViewManager.DynamicViewType.grid;
        r10 = r10.name();
        r0.f(r10);
    L_0x0197:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dynamicview.a.a(java.lang.String):com.dynamicview.f$a");
    }

    public static com.dynamicview.f.a b(String str) {
        com.dynamicview.f.a aVar;
        com.dynamicview.f.a aVar2;
        if (str.equals(com.constants.c.a.x)) {
            aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/showcase", DynamicViewType.carousel.name(), null, PLAYOUT_SECTION_TYPE.HOME_CAROUSEL_VIEW.name(), null, null, "240");
            aVar2.a(VIEW_SIZE.CAROUSEL_VIEW.getNumVal());
        } else if (str.equals(com.constants.c.a.P)) {
            aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/sponsored/occasion?v=1", DynamicViewType.spon_oc.name(), null, PLAYOUT_SECTION_TYPE.SPONSORED_OCCASION.name(), null, null, "240");
            aVar2.a(VIEW_SIZE.SPONSORED_OC_SIZE.getNumVal());
        } else {
            com.dynamicview.f.a aVar3;
            if (str.equals(com.constants.c.a.G)) {
                aVar2 = new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.section_heading_today), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140");
            } else if (str.equals(com.constants.c.a.H)) {
                aVar2 = new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.section_heading_week), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140");
            } else if (str.equals(com.constants.c.a.I)) {
                aVar2 = new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.section_heading_radio), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140");
            } else if (str.equals(com.constants.c.a.F)) {
                aVar2 = new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.recently_played), "https://apiv2.gaana.com/user/entity/activity", DynamicViewType.user_activity.name(), "https://apiv2.gaana.com/user/entity/activity", null, null, null, "30");
                aVar2.a(VIEW_SIZE.RECENTLY_PLAYED_SMALL.getNumVal());
            } else if (str.equals(com.constants.c.a.w)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/trending/songs", DynamicViewType.double_scroll.name(), "https://apiv2.gaana.com/home/trending/songs", PLAYOUT_SECTION_TYPE.TRENDING_SONG.name(), "", "", "240");
                aVar3.f(DynamicViewType.list.name());
                aVar3.a(VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal());
                aVar3.b(false);
                aVar3.g("gaana://view/trendingsong/seeall");
                aVar3.j("X5X");
            } else if (str.equals(com.constants.c.a.a)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/playlist/top-charts", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/playlist/top-charts?limit=0,20", PLAYOUT_SECTION_TYPE.TOP_CHARTS.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.g("gaana://view/topcharts/seeall");
                aVar3.h(com.constants.c.a.a);
            } else if (str.equals(com.constants.c.a.J)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/made-for-you/mixes", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/made-for-you/mixes", PLAYOUT_SECTION_TYPE.MADE_FOR_YOU.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.a(VIEW_SIZE.GRID_LARGE_MIX.getNumVal());
            } else if (str.equals(com.constants.c.a.K)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/smartfeed/vertical/2", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/smartfeed/vertical/2", PLAYOUT_SECTION_TYPE.GAANA_ORIGINALS.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.a(VIEW_SIZE.GRID_LARGE.getNumVal());
            } else if (str.equals(com.constants.c.a.L)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/device/playlist", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/device/playlist", PLAYOUT_SECTION_TYPE.PLAYLIST_FOR_YOU.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
            } else if (str.equals(com.constants.c.a.v)) {
                aVar3 = new com.dynamicview.f.a(str, null, DynamicViewType.login_card.name(), null, null, null, null, "240");
            } else if (str.equals(com.constants.c.a.c)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/album/featured", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/album/featured/more?limit=0,20", PLAYOUT_SECTION_TYPE.NEW_RELEASES.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.g("gaana://view/newrelease/seeall");
                aVar3.h(com.constants.c.a.c);
            } else if (str.equals(com.constants.c.a.u)) {
                aVar3 = new com.dynamicview.f.a(str, null, DynamicViewType.ad.name(), null, null, "195946", "0", "240");
            } else if (str.equals(com.constants.c.a.t)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/user/track-history", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/user/track-history?show_all=1", PLAYOUT_SECTION_TYPE.HEAR_IT_ALL.name(), "", "", "240");
                aVar3.f(DynamicViewType.list.name());
                aVar3.b(false);
                aVar3.g("gaana://view/hearitagain/seeall");
                aVar3.h(com.constants.c.a.t);
            } else if (str.equals(com.constants.c.a.s)) {
                aVar3 = new com.dynamicview.f.a(str, null, DynamicViewType.img_card.name(), null, null, null, null, "240");
            } else if (str.equals(com.constants.c.a.l)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/gaana-special", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/gaana-special?limit=0,20", PLAYOUT_SECTION_TYPE.GAANA_SPECIALS.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.a(VIEW_SIZE.GRID_LARGE.getNumVal());
                aVar3.g("gaana://view/gaanaspecials/seeall");
                aVar3.h(com.constants.c.a.l);
            } else if (str.equals(com.constants.c.a.d)) {
                aVar3 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/playlist/hour", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/playlist/hour?limit=0,20", PLAYOUT_SECTION_TYPE.PoTH.name(), "", "", "240");
                aVar3.f(DynamicViewType.grid.name());
                aVar3.a(false);
                aVar3.g("gaana://view/editorspick/seeall");
                aVar3.h(com.constants.c.a.d);
            } else if (str.equals(com.constants.c.a.e)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/radio/gaana-mirchi", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/radio/gaana-mirchi", PLAYOUT_SECTION_TYPE.RADIO.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid.name());
                aVar2.g("gaana://view/homeradio/seeall");
                aVar2.h(com.constants.c.a.e);
            } else if (str.equals(com.constants.c.a.b)) {
                aVar2 = new com.dynamicview.f.a(str, "https://api.gaana.com/home/featured/artists", DynamicViewType.cir_hor_scroll.name(), "https://api.gaana.com/home/featured/artists?limit=0,20", PLAYOUT_SECTION_TYPE.FEATURED_ARTISTS.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid.name());
                aVar2.g("gaana://view/featuredartist/seeall");
            } else if (str.equals(com.constants.c.a.B)) {
                aVar2 = new com.dynamicview.f.a(str, null, DynamicViewType.nudge.name(), null, null, null, null, "240");
            } else if (str.equals(com.constants.c.a.z)) {
                aVar2 = new com.dynamicview.f.a(str, null, DynamicViewType.dummy_view.name(), null, null, null, null, "240");
            } else if (str.equals(com.constants.c.a.A)) {
                aVar2 = new com.dynamicview.f.a(str, null, DynamicViewType.trial_sponsor_ad.name(), null, null, null, null, "240");
            } else if (str.equals(com.constants.c.a.D)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/horizontal/videos", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/horizontal/videos", PLAYOUT_SECTION_TYPE.HORIZONTAL_VIDEOS.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid.name());
                aVar2.a(VIEW_SIZE.GRID_RECTANGLE_255x142.getNumVal());
                aVar2.h(com.constants.c.a.D);
            } else if (str.equals(com.constants.c.a.y)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/showcase/week-so-far", DynamicViewType.carousel.name(), null, PLAYOUT_SECTION_TYPE.HOME_CAROUSEL_VIEW.name(), null, null, "240");
                aVar2.a(VIEW_SIZE.CAROUSEL_VIEW.getNumVal());
            } else if (str.equals(com.constants.c.a.M)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/popular/songs/lang1", DynamicViewType.double_scroll.name(), "https://apiv2.gaana.com/home/popular/songs/lang1", PLAYOUT_SECTION_TYPE.POPULAR_SONGS_1.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid.name());
            } else if (str.equals(com.constants.c.a.N)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/discover/category-listing/178?new_artwork=1&v=1", DynamicViewType.double_scroll.name(), "https://apiv2.gaana.com/home/discover/category-listing/178?new_artwork=1&v=1", PLAYOUT_SECTION_TYPE.DISCOVER_CATEGORY_OCCASION.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid_rect.name());
                aVar2.a(VIEW_SIZE.SCROLL_RECTANGLE_DISCOVER.getNumVal());
            } else if (str.equals(com.constants.c.a.O)) {
                aVar2 = new com.dynamicview.f.a(str, "https://apiv2.gaana.com/home/smartfeed/116", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/smartfeed/116", PLAYOUT_SECTION_TYPE.GAANA_EXCLUSIVES.name(), "", "", "240");
                aVar2.f(DynamicViewType.grid.name());
            } else {
                aVar = null;
            }
            aVar = r11;
        }
        if (aVar != null) {
            aVar.d(true);
        }
        return aVar;
    }

    public static e a() {
        f fVar = new f();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(b((String) it.next()));
        }
        fVar.a(arrayList);
        it = b.iterator();
        while (it.hasNext()) {
            arrayList2.add(a((String) it.next()));
        }
        fVar.b(arrayList2);
        e eVar = new e();
        arrayList2 = new ArrayList();
        com.dynamicview.e.a aVar = new com.dynamicview.e.a();
        aVar.a(arrayList);
        arrayList2.add(aVar);
        eVar.a(arrayList2);
        return eVar;
    }

    public static List<com.dynamicview.f.a> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.radio), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
        Iterator it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(a((String) it.next()));
        }
        return arrayList;
    }

    public static List<com.dynamicview.f.a> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.dynamicview.f.a(GaanaApplication.getContext().getResources().getString(R.string.radio), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
        Iterator it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(a((String) it.next()));
        }
        return arrayList;
    }

    public static f d() {
        f fVar = new f();
        ArrayList arrayList = new ArrayList();
        Resources resources = GaanaApplication.getContext().getResources();
        com.dynamicview.f.a aVar = new com.dynamicview.f.a("header", "http://a10.gaanacdn.com/images/showcase/Not_So_Bollywood__1484504730.jpg", DynamicViewType.header.name(), null, "Curated Header", null, null, "240");
        aVar.e(ShareConstants.TITLE);
        com.dynamicview.f.a aVar2 = new com.dynamicview.f.a(resources.getString(R.string.recently_played), "https://apiv2.gaana.com/user/entity/activity", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/user/entity/activity", DownloadSelectionType.RECENTLY_PLAYED.name(), null, null, "15");
        aVar2.b("Curated Recently Played");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.listen_again), "https://apiv2.gaana.com/home/user/track-history", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/user/track-history?show_all=1", DownloadSelectionType.LISTEN_AGAIN.name(), "208090", "2-5", "240");
        aVar2.b("Curated Listen Again");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.daily_mix), "https://apiv2.gaana.com/home/daily-mix", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/daily-mix", DownloadSelectionType.DAILY_MIX.name(), null, null, "15");
        aVar2.b("Curated Daily Mix");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.weekly_mix), "https://apiv2.gaana.com/home/weekly-mix", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/home/weekly-mix", DownloadSelectionType.WEEKLY_MIX.name(), null, null, "15");
        aVar2.b("Curated Weekly Mix");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.favorite_songs), "https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,40", DynamicViewType.hor_scroll.name(), null, DownloadSelectionType.FAVORITE_SONGS.name(), null, null, "240");
        aVar2.b("Curated Favorite Songs");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.trending_songs), "https://apiv2.gaana.com/home/trending/songs", DynamicViewType.hor_scroll.name(), null, DownloadSelectionType.TRENDING_SONGS.name(), null, null, "240");
        aVar2.b("Curated Treanding Songs");
        aVar2 = new com.dynamicview.f.a(resources.getString(R.string.gaana_memories), "https://api.gaana.com/home/your-gaana-memories", DynamicViewType.hor_scroll.name(), null, DownloadSelectionType.GAANA_MEMORIES.name(), null, null, "240");
        aVar2.b("Curated Gaana Memories");
        arrayList.add(aVar);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        arrayList.add(aVar2);
        fVar.c(arrayList);
        fVar.a("http://a10.gaanacdn.com/images/showcase/Not_So_Bollywood__1484504730.jpg");
        return fVar;
    }
}
