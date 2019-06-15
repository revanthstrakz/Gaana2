package com.moengage.inapp;

interface InAppConstants {
    public static final String API_ENDPOINT_FETCH_SINGLE_INAPP = "/campaigns/inappcampaign/";
    public static final String API_ENDPOINT_INAPPS = "fetch";
    public static final String API_INAPP_V2 = "/campaigns/inappcampaigns/";
    public static final String API_SMART_TRIGGER = "/v2/autotriggerinapps";
    public static final String APP_RATED_EVENT = "MOE_APP_RATED";
    public static final String APP_RATING_ATTRIBUTE = "rating";
    public static final String EVENT_IN_APP_AUTO_DISMISS = "IN_APP_AUTO_DISMISS";
    public static final String EVENT_IN_APP_CLICKED = "IN_APP_CLICKED";
    public static final String EVENT_IN_APP_CLOSE_CLICKED = "IN_APP_CLOSE_CLICKED";
    public static final String EVENT_IN_APP_SHOWN = "IN_APP_SHOWN";
    public static final String INAPP_BACKGROUND_PROPERTIES = "properties";
    public static final String INAPP_CAMPAIGN_ALIGN = "align";
    public static final String INAPP_CAMPAIGN_AUTODISMISS = "auto_dismiss";
    public static final String INAPP_CAMPAIGN_CANCELABLE = "cancellable";
    public static final String INAPP_CAMPAIGN_CID = "cid";
    public static final String INAPP_CAMPAIGN_CONTENT = "content";
    public static final String INAPP_CAMPAIGN_CONTEXT = "context";
    public static final String INAPP_CAMPAIGN_INTERVAL = "interval";
    public static final String INAPP_CAMPAIGN_MAX_TIMES = "max_times";
    public static final String INAPP_CAMPAIGN_PERSISTENT = "persistent";
    public static final String INAPP_CAMPAIGN_PRIORITY = "priority";
    public static final String INAPP_CAMPAIGN_SHOWONLYIN = "show_only_screen";
    public static final String INAPP_CAMPAIGN_STATUS = "status";
    public static final String INAPP_CAMPAIGN_STATUS_ACTIVE = "active";
    public static final String INAPP_CAMPAIGN_STATUS_EXPIRED = "expired";
    public static final String INAPP_CAMPAIGN_STATUS_OOS = "out_of_segment";
    public static final String INAPP_CAMPAIGN_STATUS_PAUSED = "paused";
    public static final String INAPP_CAMPAIGN_TTL = "ttl";
    public static final String INAPP_CAMPAIGN_TYPE = "type";
    public static final String INAPP_CAMPAIGN_WIDGET_DATA = "widgets";
    public static final String LINKED_IN_APP = "moe_inapp_cid";
    public static final String PARAM_EVENT = "event";
    public static final String RESP_ATTR_CAMPAIGN_INFO = "campaign_info";
    public static final String RESP_ATTR_DEBUG_ENABLED = "debug";
    public static final String RESP_ATTR_DELAY = "minimum_delay_bw_inapps";
}
