package com.comscore.applications;

import com.comscore.analytics.Core;
import com.comscore.measurement.Label;
import com.comscore.utils.Constants;
import com.comscore.utils.InstallReferrerReceiver;
import com.comscore.utils.RootDetector;
import java.util.HashMap;

public class AppStartMeasurement extends ApplicationMeasurement {
    protected AppStartMeasurement(Core core, EventType eventType, String str, boolean z) {
        super(core, eventType, str, z, true, true);
        setLabel(new Label("ns_ap_gs", String.valueOf(core.getFirstInstallId()), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_install", String.valueOf(core.getInstallId()), Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_runs", String.valueOf(core.getRunsCount()), Boolean.valueOf(false)));
        if (z) {
            setLabel(new Label("ns_ap_csf", "1", Boolean.valueOf(false)));
        }
        String str2 = "0";
        if (RootDetector.isDeviceRooted()) {
            str2 = "1";
        }
        setLabel(new Label("ns_ap_jb", str2, Boolean.valueOf(false)));
        setLabel(new Label("ns_ap_lastrun", String.valueOf(core.getPreviousGenesis()), Boolean.valueOf(false)));
        str2 = core.getPreviousVersion();
        if (str2 != null && str2.length() > 0) {
            setLabel(new Label("ns_ap_updated", str2, Boolean.valueOf(false)));
        }
        str2 = core.getStorage().get(Constants.EXCEPTION_OCURRENCES_KEY);
        if (!(str2 == null || str2.length() <= 0 || str2.equals("0"))) {
            setLabel(new Label("ns_ap_er", str2, Boolean.valueOf(false)));
            core.getStorage().remove(Constants.EXCEPTION_OCURRENCES_KEY);
        }
        if (z) {
            HashMap retrieveReferrerLabels = InstallReferrerReceiver.retrieveReferrerLabels(core.getAppContext());
            if (retrieveReferrerLabels != null) {
                for (String str3 : retrieveReferrerLabels.keySet()) {
                    setLabel(str3, (String) retrieveReferrerLabels.get(str3));
                }
            }
        }
    }
}
