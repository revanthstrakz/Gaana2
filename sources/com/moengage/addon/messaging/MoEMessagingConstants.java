package com.moengage.addon.messaging;

interface MoEMessagingConstants {
    public static final String ACTION_SYNC_MESSAGES = "ACTION_SYNC_MESSAGES";
    public static final String ATTR_RESPONSE_DATA = "data";
    public static final String ATTR_RESPONSE_MESSAGE = "messagesInfo";
    public static final long DEFAULT_CAMPAIGN_TTL = 2419200000L;
    public static final String MESSAGE_FETCH_API_EU = "http://apiv2eu.moengage.com/v1/getAndroidInboxMessages";
    public static final String MESSAGE_FETCH_API_GENERAL = "http://api.moengage.com/v1/getAndroidInboxMessages";
    public static final String MESSAGE_FETCH_API_INDIA = "http://apiv2mumbai.moengage.com/v1/getAndroidInboxMessages";
    public static final int MESSAGE_SYNC_JOB_ID = 99911;
    public static final String PARAM_IS_APP_OPEN = "on_app_open";
    public static final int REQ_CODE_FETCH_MESSAGES = 10001;
}
