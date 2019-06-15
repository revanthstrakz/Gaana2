package com.moengage.locationlibrary;

interface LocationConstants {
    public static final String API_ENDPOINT_GEOFENCEHIT = "/v1/geoFenceHit";
    public static final String API_ENDPOINT_GEOFENCES = "/v1/geoFences";
    public static final String API_GEO_FENCE = "https://apiv2.moengage.com";
    public static final String CURRENT_LATITUDE = "curr_lat";
    public static final String CURRENT_LONGITUDE = "curr_long";
    public static final String EVENT_ATTRIBUTE_CAMPAIGN_ID = "campaign_id";
    public static final String EVENT_ATTRIBUTE_GEO_ID = "geo_id";
    public static final String EVENT_ATTRIBUTE_TRANSITION_TYPE = "transition_type";
    public static final String EVENT_ATTRIBUTE_TRIGGER_LOCATION = "trigger_location";
    public static final String EVENT_GEO_FENCE_HIT = "MOE_GEOFENCE_HIT";
    public static final String GEO_ID_SEPARATOR = ":";
    public static final String PARAM_GEOIDS = "geoIds";
    public static final String PARAM_LAT = "lat";
    public static final String PARAM_LNG = "lng";
    public static final String RESPONSE_ATTR_DISTANCE = "distance";
    public static final String RESPONSE_ATTR_FENCES_INFO = "fencesInfo";
    public static final String RESPONSE_ATTR_FENCE_CAMPAIGN_ID = "cid";
    public static final String RESPONSE_ATTR_FENCE_EXPIRY = "expiry";
    public static final String RESPONSE_ATTR_FENCE_LDELAY = "ldelay";
    public static final String RESPONSE_ATTR_FENCE_RESPONSOVENESS = "responsiveness";
    public static final String RESPONSE_ATTR_GEOID = "geoId";
    public static final String RESPONSE_ATTR_TRANSITION_TYPE = "transitionType";
    public static final String TRANSITION_TYPE_DWELL = "dwell";
    public static final String TRANSITION_TYPE_ENTER = "enter";
    public static final String TRANSITION_TYPE_EXIT = "exit";
}
