package com.comscore.applications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import com.comscore.analytics.ApplicationState;
import com.comscore.analytics.Core;
import com.comscore.measurement.Label;
import com.comscore.measurement.Measurement;
import com.comscore.metrics.EventType;
import com.comscore.streaming.Constants;
import com.comscore.utils.API13;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.HashMap;
import java.util.Locale;

public class ApplicationMeasurement extends Measurement {
    protected ApplicationMeasurement(Core core, EventType eventType, String str) {
        this(core, eventType, str, false, false, true);
    }

    protected ApplicationMeasurement(Core core, EventType eventType, String str, boolean z, boolean z2, boolean z3) {
        String str2;
        Core core2 = core;
        String str3 = str;
        boolean z4 = z;
        boolean z5 = z2;
        boolean z6 = z3;
        super(core);
        core2.update(z6);
        if (z6) {
            int foregroundTransitionsCountDelta = core2.getForegroundTransitionsCountDelta(z5);
            long foregroundTotalTime = core2.getForegroundTotalTime(z4);
            long foregroundTimeDelta = core2.getForegroundTimeDelta(z5);
            long backgroundTotalTime = core2.getBackgroundTotalTime(z4);
            long backgroundTimeDelta = core2.getBackgroundTimeDelta(z5);
            long j = foregroundTotalTime;
            long inactiveTotalTime = core2.getInactiveTotalTime(z4);
            long inactiveTimeDelta = core2.getInactiveTimeDelta(z5);
            long applicationSessionTimeDelta = core2.getApplicationSessionTimeDelta(z5);
            long activeUserSessionTimeDelta = core2.getActiveUserSessionTimeDelta(z5);
            long userSessionTimeDelta = core2.getUserSessionTimeDelta(z5);
            long autoUpdateInterval = core.getAutoUpdateInterval();
            int applicationSessionCountDelta = core2.getApplicationSessionCountDelta(z5);
            int activeUserSessionCountDelta = core2.getActiveUserSessionCountDelta(z5);
            int userSessionCountDelta = core2.getUserSessionCountDelta(z5);
            int i = userSessionCountDelta;
            int userInteractionCount = core2.getUserInteractionCount(z5);
            int i2 = applicationSessionCountDelta;
            setLabel(new Label("ns_ap_fg", String.valueOf(foregroundTransitionsCountDelta), Boolean.valueOf(false)));
            long j2 = autoUpdateInterval;
            setLabel(new Label("ns_ap_ft", String.valueOf(j), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_dft", String.valueOf(foregroundTimeDelta), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_bt", String.valueOf(backgroundTotalTime), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_dbt", String.valueOf(backgroundTimeDelta), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_it", String.valueOf(inactiveTotalTime), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_dit", String.valueOf(inactiveTimeDelta), Boolean.valueOf(false)));
            if (j2 >= 60000) {
                setLabel(new Label("ns_ap_ut", String.valueOf(j2), Boolean.valueOf(false)));
            }
            setLabel(new Label("ns_ap_as", String.valueOf(i2), Boolean.valueOf(false)));
            setLabel(new Label("ns_ap_das", String.valueOf(applicationSessionTimeDelta), Boolean.valueOf(false)));
            if (activeUserSessionCountDelta >= 0) {
                setLabel(new Label("ns_ap_aus", String.valueOf(activeUserSessionCountDelta), Boolean.valueOf(false)));
                setLabel(new Label("ns_ap_daus", String.valueOf(activeUserSessionTimeDelta), Boolean.valueOf(false)));
                setLabel(new Label("ns_ap_uc", String.valueOf(userInteractionCount), Boolean.valueOf(false)));
            }
            if (i >= 0) {
                setLabel(new Label("ns_ap_us", String.valueOf(i), Boolean.valueOf(false)));
                setLabel(new Label("ns_ap_dus", String.valueOf(userSessionTimeDelta), Boolean.valueOf(false)));
            }
            setLabel(new Label("ns_ap_usage", Long.toString(this.c - core.getGenesis()), Boolean.valueOf(false)));
            str2 = str;
        } else {
            str2 = str3;
        }
        if (str2 != null) {
            setPixelURL(str2);
        }
        setLabel(new Label("c1", Constants.C1_VALUE, Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_an", core.getAppName(), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_pn", "android", Boolean.valueOf(false)));
        setLabel(new Label("c12", core.getVisitorId(), Boolean.valueOf(false)));
        if (core.getCrossPublisherId() != null) {
            setLabel(new Label("ns_ak", core.getCrossPublisherId(), Boolean.valueOf(false)));
            if (core.getIdHelper().isIdChanged()) {
                setLabel(new Label("ns_ap_ni", "1", Boolean.valueOf(false)));
            }
        }
        if (core.getIdHelper().getMD5AdvertisingId() != null) {
            setLabel("ns_ap_i3", core.getIdHelper().getMD5AdvertisingId());
        }
        setLabel(new Label("ns_ap_device", Build.DEVICE, Boolean.valueOf(false)));
        setLabel(new Label("ns_type", a(eventType).toString(), Boolean.valueOf(false)));
        setLabel(new Label("ns_ts", Long.toString(this.c), Boolean.valueOf(false)));
        setLabel(new Label("ns_nc", "1", Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_pfv", VERSION.RELEASE, Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_pv", VERSION.RELEASE, Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_pfm", "android", Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_ar", System.getProperty("os.arch"), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_ev", eventType.toString(), Boolean.valueOf(false)));
        Context appContext = core.getAppContext();
        setLabel(new Label("ns_ap_ver", core.getCurrentVersion(), Boolean.valueOf(false)));
        Point a = a(appContext);
        int i3 = a.x;
        int i4 = a.y;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(i3));
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(Integer.toString(i4));
        setLabel(new Label("ns_ap_res", stringBuilder.toString(), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_lang", Locale.getDefault().getLanguage(), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_sv", core.getVersion(), Boolean.valueOf(false)));
        if (eventType.equals(EventType.KEEPALIVE)) {
            setLabel("ns_ap_oc", String.valueOf(core.getOfflineCache().getEventCount()));
        }
        long coldStartId = core.getColdStartId();
        int coldStartCount = core.getColdStartCount();
        setLabel(new Label("ns_ap_id", String.valueOf(coldStartId), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_cs", String.valueOf(coldStartCount), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_bi", core.getAppContext().getPackageName(), Boolean.valueOf(false)));
    }

    @SuppressLint({"NewApi"})
    private Point a(Context context) {
        Point point = new Point();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 13) {
            return API13.getDisplaySize(defaultDisplay);
        }
        point.x = defaultDisplay.getWidth();
        point.y = defaultDisplay.getHeight();
        return point;
    }

    private static EventType a(EventType eventType) {
        return (eventType == EventType.START || eventType == EventType.CLOSE || eventType == EventType.VIEW) ? EventType.VIEW : EventType.HIDDEN;
    }

    public static ApplicationMeasurement newApplicationMeasurement(Core core, EventType eventType, HashMap<String, String> hashMap, String str) {
        ApplicationMeasurement appStartMeasurement;
        boolean z = true;
        if (eventType == EventType.START) {
            core.incrementRunsCount();
            appStartMeasurement = new AppStartMeasurement(core, eventType, str, core.handleColdStart());
        } else if (eventType == EventType.AGGREGATE) {
            appStartMeasurement = new AggregateMeasurement(core, eventType, str);
        } else if (eventType != EventType.CLOSE) {
            boolean z2 = hashMap == null || hashMap.get("ns_st_ev") != "hb";
            boolean z3 = hashMap == null || !hashMap.containsKey("ns_st_ev");
            ApplicationMeasurement applicationMeasurement = new ApplicationMeasurement(core, eventType, str, false, z2, z3);
        } else {
            appStartMeasurement = null;
        }
        if (eventType != EventType.AGGREGATE) {
            appStartMeasurement.a(core.getLabels());
        }
        if (eventType != EventType.AGGREGATE) {
            z = false;
        }
        appStartMeasurement.a((HashMap) hashMap, z);
        if (appStartMeasurement.hasLabel("name").booleanValue()) {
            return appStartMeasurement;
        }
        if (core.getCurrentActivityName() != null) {
            appStartMeasurement.setLabel("name", core.getCurrentActivityName());
            return appStartMeasurement;
        }
        String str2;
        String str3;
        if (eventType == EventType.START) {
            str2 = "name";
            str3 = "start";
        } else if (core.getApplicationState() == ApplicationState.FOREGROUND) {
            str2 = "name";
            str3 = "foreground";
        } else {
            str2 = "name";
            str3 = com.comscore.utils.Constants.DEFAULT_BACKGROUND_PAGE_NAME;
        }
        appStartMeasurement.setLabel(str2, str3);
        return appStartMeasurement;
    }
}
