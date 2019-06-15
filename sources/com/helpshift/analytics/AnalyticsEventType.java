package com.helpshift.analytics;

import com.helpshift.support.webkit.b;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;

public enum AnalyticsEventType {
    APP_START("a"),
    LIBRARY_OPENED("o"),
    LIBRARY_OPENED_DECOMP("d"),
    SUPPORT_LAUNCH("l"),
    PERFORMED_SEARCH("s"),
    BROWSED_FAQ_LIST(b.a),
    READ_FAQ("f"),
    MARKED_HELPFUL("h"),
    MARKED_UNHELPFUL("u"),
    REPORTED_ISSUE("i"),
    CONVERSATION_POSTED(TtmlNode.TAG_P),
    REVIEWED_APP(e.o),
    OPEN_ISSUE("c"),
    OPEN_INBOX(AvidJSONUtil.KEY_X),
    LIBRARY_QUIT("q"),
    MESSAGE_ADDED("m"),
    RESOLUTION_ACCEPTED(AvidJSONUtil.KEY_Y),
    RESOLUTION_REJECTED("n"),
    START_CSAT_RATING("sr"),
    CANCEL_CSAT_RATING("cr"),
    LINK_VIA_FAQ("fl"),
    TICKET_AVOIDED("ta"),
    TICKET_AVOIDANCE_FAILED("taf"),
    DYNAMIC_FORM_OPEN("dfo"),
    ADMIN_MESSAGE_DEEPLINK_CLICKED("ml"),
    DYNAMIC_FORM_CLOSE("dfc"),
    CONVERSATION_INFORMATION_OPENED("ii");
    
    public final String key;

    private AnalyticsEventType(String str) {
        this.key = str;
    }
}
