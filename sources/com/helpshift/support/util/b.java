package com.helpshift.support.util;

import com.helpshift.support.l.a;
import java.util.HashMap;
import java.util.Map;

public class b {
    public static final Map<String, Object> a = new HashMap();
    public static final Map<String, Object> b = new HashMap();

    static {
        a.put("enableContactUs", a.a);
        a.put("gotoConversationAfterContactUs", Boolean.valueOf(false));
        a.put("showSearchOnNewConversation", Boolean.valueOf(false));
        a.put("requireEmail", Boolean.valueOf(false));
        a.put("hideNameAndEmail", Boolean.valueOf(false));
        a.put("enableFullPrivacy", Boolean.valueOf(false));
        a.put("showConversationResolutionQuestion", Boolean.valueOf(true));
        a.put("showConversationInfoScreen", Boolean.valueOf(false));
        a.put("enableTypingIndicator", Boolean.valueOf(false));
        b.put("enableLogging", Boolean.valueOf(false));
        b.put("disableHelpshiftBranding", Boolean.valueOf(false));
        b.put("enableInAppNotification", Boolean.valueOf(true));
        b.put("enableDefaultFallbackLanguage", Boolean.valueOf(true));
        b.put("disableAnimations", Boolean.valueOf(false));
        b.put("font", null);
        b.put("supportNotificationChannelId", null);
        b.put("campaignsNotificationChannelId", null);
        b.put("screenOrientation", Integer.valueOf(-1));
    }
}
