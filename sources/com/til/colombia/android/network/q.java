package com.til.colombia.android.network;

import android.location.Location;
import android.net.Uri;
import android.net.Uri.Builder;
import com.comscore.measurement.MeasurementDispatcher;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.til.colombia.android.internal.a;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import com.til.colombia.android.service.AdRequestResponse;
import com.til.colombia.android.service.AdSize;
import com.til.colombia.android.service.CmFeedRequest;
import com.til.colombia.android.service.ColombiaNativeSponsoredAdView;
import com.til.colombia.android.service.bl;
import com.til.colombia.android.utils.c;
import com.til.colombia.dmp.android.AdvertisingIDUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public final class q {
    private static double a = Math.pow(10.0d, 4.0d);

    public static String a(CmFeedRequest cmFeedRequest) {
        Builder appendQueryParameter = new Builder().encodedPath(h.a).appendEncodedPath(h.b).appendEncodedPath(Long.toString(cmFeedRequest.a.slotId.longValue())).appendEncodedPath(h.c).appendQueryParameter(e.q, Integer.toString(cmFeedRequest.a.pageNo)).appendQueryParameter(e.at, Integer.toString(cmFeedRequest.a.refresh)).appendQueryParameter(e.N, "0");
        if (!com.til.colombia.android.internal.a.h.a(cmFeedRequest.a.cId)) {
            appendQueryParameter.appendQueryParameter(e.r, cmFeedRequest.a.cId);
        }
        if (com.til.colombia.android.internal.a.h.a(h.i().T)) {
            AdvertisingIDUtil.retrieveAndSetAAID(a.a());
        } else {
            appendQueryParameter.appendQueryParameter(e.u, h.i().T);
            appendQueryParameter.appendQueryParameter(e.v, String.valueOf(Integer.valueOf(h.i().U)));
        }
        if (!com.til.colombia.android.internal.a.h.a(cmFeedRequest.a.referer)) {
            appendQueryParameter.appendQueryParameter(e.s, cmFeedRequest.a.referer);
        }
        Builder appendQueryParameter2 = appendQueryParameter.appendQueryParameter(e.O, "aos:4.0.0").appendQueryParameter(e.C, h.i().N).appendQueryParameter(e.A, h.i().R).appendQueryParameter(e.z, h.i().Q).appendQueryParameter(e.w, h.i().P).appendQueryParameter(e.x, h.i().L).appendQueryParameter(e.y, h.i().M).appendQueryParameter(e.D, h.i().c());
        String str = e.J;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        appendQueryParameter2 = appendQueryParameter2.appendQueryParameter(str, stringBuilder.toString()).appendQueryParameter(e.I, h.i().O);
        str = e.E;
        h.i();
        appendQueryParameter2 = appendQueryParameter2.appendQueryParameter(str, h.a());
        str = e.o;
        stringBuilder = new StringBuilder("aos_");
        stringBuilder.append(UUID.randomUUID().toString());
        appendQueryParameter2.appendQueryParameter(str, stringBuilder.toString());
        if (cmFeedRequest.a.appVer != null) {
            appendQueryParameter.appendQueryParameter(e.B, cmFeedRequest.a.appVer);
        }
        if (cmFeedRequest.a.auds != null) {
            appendQueryParameter.appendQueryParameter(e.h, cmFeedRequest.a.auds);
        }
        if (cmFeedRequest.a.lang != null) {
            appendQueryParameter.appendQueryParameter(e.i, cmFeedRequest.a.lang);
        }
        if (cmFeedRequest.a.itemId != null) {
            appendQueryParameter.appendQueryParameter(e.Q, cmFeedRequest.a.itemId);
        }
        return appendQueryParameter.build().toString();
    }

    public static String a(String str) {
        Uri parse = Uri.parse(str);
        Builder builder = new Builder();
        StringBuilder stringBuilder = new StringBuilder(h.a);
        stringBuilder.append(parse.getEncodedPath());
        builder = builder.encodedPath(stringBuilder.toString()).appendQueryParameter(e.q, parse.getQueryParameter(e.q)).appendQueryParameter(e.at, parse.getQueryParameter(e.at));
        if (!com.til.colombia.android.internal.a.h.a(parse.getQueryParameter(e.Q))) {
            builder.appendQueryParameter(e.Q, parse.getQueryParameter(e.Q));
        }
        return builder.build().toString();
    }

    public static String a() {
        return new Builder().encodedPath("https://ade.clmbtech.com").appendEncodedPath(h.j).appendQueryParameter(e.A, h.i().R).appendQueryParameter(e.B, h.i().S).appendQueryParameter(e.O, "aos:4.0.0").build().toString();
    }

    public static String b() {
        return new Builder().encodedPath("https://ade.clmbtech.com").appendEncodedPath("cde/sdk/config/rootConfig.htm").appendQueryParameter(e.A, h.i().R).appendQueryParameter(e.B, h.i().S).appendQueryParameter(e.O, "aos:4.0.0").build().toString();
    }

    public static String a(bl blVar) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        Builder appendEncodedPath = new Builder().encodedPath("https://ade.clmbtech.com").appendEncodedPath(h.f);
        appendEncodedPath.appendQueryParameter(e.f, a(blVar.getAdRequests()));
        if (!com.til.colombia.android.internal.a.h.a(blVar.getPageNo())) {
            appendEncodedPath.appendQueryParameter(e.q, blVar.getPageNo());
        }
        if (!com.til.colombia.android.internal.a.h.a(blVar.getCId())) {
            appendEncodedPath.appendQueryParameter(e.r, blVar.getCId());
        }
        if (com.til.colombia.android.internal.a.h.a(h.i().T)) {
            AdvertisingIDUtil.retrieveAndSetAAID(a.a());
        } else {
            appendEncodedPath.appendQueryParameter(e.u, h.i().T);
            appendEncodedPath.appendQueryParameter(e.v, String.valueOf(Integer.valueOf(h.i().U)));
        }
        if (!com.til.colombia.android.internal.a.h.a(blVar.getReferer())) {
            appendEncodedPath.appendQueryParameter(e.s, blVar.getReferer());
        }
        boolean savers = blVar.getSavers();
        boolean isVideoAutoPlay = blVar.isVideoAutoPlay();
        String str = null;
        String B = savers ? a.B() : null;
        if (!com.til.colombia.android.internal.a.h.a(a.o())) {
            Long valueOf = Long.valueOf(c.e(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsoredTimestamp"));
            if (valueOf != null && System.currentTimeMillis() - valueOf.longValue() > MeasurementDispatcher.MILLIS_PER_DAY) {
                c.a(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED);
            } else if (c.c(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsored")) {
                if (com.til.colombia.android.internal.a.h.a(B)) {
                    B = a.o();
                } else {
                    stringBuilder = new StringBuilder(",");
                    stringBuilder.append(a.o());
                    B = B.concat(stringBuilder.toString());
                }
            }
        }
        if (!isVideoAutoPlay) {
            if (com.til.colombia.android.internal.a.h.a(B)) {
                B = a.C();
            } else {
                stringBuilder2 = new StringBuilder(",");
                stringBuilder2.append(a.C());
                B = B.concat(stringBuilder2.toString());
            }
        }
        if (!com.til.colombia.android.internal.a.h.a(B)) {
            appendEncodedPath.appendQueryParameter(e.h, B);
        }
        Location location = blVar.getLocation();
        if (location != null) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(String.valueOf(((double) Math.round(location.getLatitude() * a)) / a));
            stringBuilder2.append(",");
            stringBuilder2.append(String.valueOf(((double) Math.round(location.getLongitude() * a)) / a));
            str = stringBuilder2.toString();
        }
        if (str != null) {
            appendEncodedPath.appendQueryParameter(e.t, str);
        }
        if (blVar.getAdManager().isFirstRequest()) {
            appendEncodedPath.appendQueryParameter(e.g, "1");
            blVar.getAdManager().setFirstRequest(false);
        }
        if (blVar.getSavers()) {
            appendEncodedPath.appendQueryParameter(e.p, "1");
        }
        if (!com.til.colombia.android.internal.a.h.a(a.l())) {
            appendEncodedPath.appendQueryParameter(e.m, a.l());
        }
        AdSize adSize = blVar.getAdSize();
        if (adSize != null) {
            try {
                appendEncodedPath.appendQueryParameter(e.G, Integer.toString(adSize.getWidth()));
                appendEncodedPath.appendQueryParameter("h", Integer.toString(adSize.getHeight()));
            } catch (Exception unused) {
            }
        }
        Date birthDay = blVar.getBirthDay();
        if (birthDay != null) {
            try {
                appendEncodedPath.appendQueryParameter(e.K, new SimpleDateFormat().format(birthDay));
            } catch (Exception unused2) {
            }
        }
        Builder appendQueryParameter = appendEncodedPath.appendQueryParameter("gender", String.valueOf(blVar.getGender().ordinal())).appendQueryParameter(e.k, InternalAvidAdSessionContext.AVID_API_LEVEL).appendQueryParameter(e.O, "aos:4.0.0").appendQueryParameter(e.C, h.i().N).appendQueryParameter(e.A, h.i().R).appendQueryParameter(e.B, h.i().S).appendQueryParameter(e.z, h.i().Q).appendQueryParameter(e.w, h.i().P).appendQueryParameter(e.x, h.i().L).appendQueryParameter(e.y, h.i().M).appendQueryParameter(e.D, h.i().c());
        str = e.J;
        stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        appendQueryParameter = appendQueryParameter.appendQueryParameter(str, stringBuilder.toString()).appendQueryParameter(e.I, h.i().O);
        str = e.E;
        h.i();
        appendQueryParameter = appendQueryParameter.appendQueryParameter(str, h.a());
        str = e.o;
        stringBuilder = new StringBuilder("aos_");
        stringBuilder.append(UUID.randomUUID().toString());
        appendQueryParameter.appendQueryParameter(str, stringBuilder.toString()).appendQueryParameter(e.N, "0");
        HashMap customAudience = blVar.getCustomAudience();
        if (customAudience != null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            for (Entry entry : customAudience.entrySet()) {
                String str2 = (String) entry.getKey();
                str = (String) entry.getValue();
                if (stringBuilder3.length() != 0) {
                    stringBuilder3.append("$$");
                }
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(str2);
                stringBuilder4.append("~");
                stringBuilder4.append(str);
                stringBuilder3.append(stringBuilder4.toString());
            }
            String stringBuilder5 = stringBuilder3.toString();
            if (stringBuilder5.length() > a.s()) {
                stringBuilder5 = stringBuilder5.substring(0, a.s());
            }
            appendEncodedPath.appendQueryParameter(e.P, stringBuilder5);
        }
        return appendEncodedPath.build().toString();
    }

    private static String a(Set<AdRequestResponse> set) {
        Collection arrayList = new ArrayList();
        for (AdRequestResponse adSlot : set) {
            arrayList.add(adSlot.getAdSlot());
        }
        return com.til.colombia.android.internal.a.h.a(arrayList, ",");
    }

    private static String a(boolean z, boolean z2) {
        String B = z ? a.B() : null;
        if (!com.til.colombia.android.internal.a.h.a(a.o())) {
            Long valueOf = Long.valueOf(c.e(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsoredTimestamp"));
            if (valueOf != null && System.currentTimeMillis() - valueOf.longValue() > MeasurementDispatcher.MILLIS_PER_DAY) {
                c.a(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED);
            } else if (c.c(a.a(), ColombiaNativeSponsoredAdView.PREF_SPONSORED, "sponsored")) {
                if (com.til.colombia.android.internal.a.h.a(B)) {
                    B = a.o();
                } else {
                    StringBuilder stringBuilder = new StringBuilder(",");
                    stringBuilder.append(a.o());
                    B = B.concat(stringBuilder.toString());
                }
            }
        }
        if (z2) {
            return B;
        }
        if (com.til.colombia.android.internal.a.h.a(B)) {
            return a.C();
        }
        StringBuilder stringBuilder2 = new StringBuilder(",");
        stringBuilder2.append(a.C());
        return B.concat(stringBuilder2.toString());
    }

    private static String a(Location location) {
        if (location == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(((double) Math.round(location.getLatitude() * a)) / a));
        stringBuilder.append(",");
        stringBuilder.append(String.valueOf(((double) Math.round(location.getLongitude() * a)) / a));
        return stringBuilder.toString();
    }
}
