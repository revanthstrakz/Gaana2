package com.inmobi.ads;

import android.support.annotation.Nullable;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.utilities.d;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class NativeTracker {
    private static final String e = "NativeTracker";
    String a;
    TrackerEventType b;
    Map<String, String> c;
    Map<String, Object> d;
    private String f;
    private int g;

    enum TrackerEventType {
        TRACKER_EVENT_TYPE_UNKNOWN,
        TRACKER_EVENT_TYPE_LOAD,
        TRACKER_EVENT_TYPE_CLIENT_FILL,
        TRACKER_EVENT_TYPE_RENDER,
        TRACKER_EVENT_TYPE_PAGE_VIEW,
        TRACKER_EVENT_TYPE_CLICK,
        TRACKER_EVENT_TYPE_VIDEO_RENDER,
        TRACKER_EVENT_TYPE_FALLBACK_URL,
        TRACKER_EVENT_TYPE_PLAY,
        TRACKER_EVENT_TYPE_Q1,
        TRACKER_EVENT_TYPE_Q2,
        TRACKER_EVENT_TYPE_Q3,
        TRACKER_EVENT_TYPE_Q4,
        TRACKER_EVENT_TYPE_CREATIVE_VIEW,
        TRACKER_EVENT_TYPE_FULLSCREEN,
        TRACKER_EVENT_TYPE_EXIT_FULLSCREEN,
        TRACKER_EVENT_TYPE_MUTE,
        TRACKER_EVENT_TYPE_UNMUTE,
        TRACKER_EVENT_TYPE_PAUSE,
        TRACKER_EVENT_TYPE_RESUME,
        TRACKER_EVENT_TYPE_ERROR,
        TRACKER_EVENT_TYPE_END_CARD_CLOSE,
        TRACKER_EVENT_TYPE_CUSTOM_EVENT_VIDEO,
        TRACKER_EVENT_TYPE_IAS,
        TRACKER_EVENT_TYPE_MOAT,
        TRACKER_EVENT_TYPE_DOWNLOADER_INIT,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED,
        TRACKER_EVENT_TYPE_DOWNLOADER_ERROR
    }

    public NativeTracker(String str, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this("url_ping", str, i, trackerEventType, map);
    }

    private NativeTracker(String str, String str2, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this.f = str;
        this.a = str2.trim();
        this.g = i;
        this.b = trackerEventType;
        this.c = map;
    }

    @Nullable
    static NativeTracker a(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("type");
            if (string != null) {
                if (string.length() != 0) {
                    Object obj;
                    string = string.toLowerCase(Locale.US);
                    int hashCode = string.hashCode();
                    if (hashCode == -1918378017) {
                        obj = "html_script";
                    } else if (hashCode == -970292670) {
                        obj = "url_ping";
                    } else if (hashCode == -284840886) {
                        obj = "unknown";
                    } else if (hashCode == 2015859192) {
                        string.equals("webview_ping");
                    }
                    boolean equals = string.equals(obj);
                }
            }
            return new NativeTracker(jSONObject.getString("url"), jSONObject.optInt("eventId", -1), a(jSONObject.getString("eventType")), new HashMap());
        } catch (JSONException e) {
            new StringBuilder("Error building tracker from JSONObject; ").append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    static TrackerEventType a(String str) {
        if (str == null || str.length() == 0) {
            return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1638835128:
                if (str.equals("midpoint")) {
                    obj = 10;
                    break;
                }
                break;
            case -1337830390:
                if (str.equals("thirdQuartile")) {
                    obj = 11;
                    break;
                }
                break;
            case -934426579:
                if (str.equals("resume")) {
                    obj = 19;
                    break;
                }
                break;
            case -840405966:
                if (str.equals("unmute")) {
                    obj = 17;
                    break;
                }
                break;
            case -667101923:
                if (str.equals("zMoatVASTIDs")) {
                    obj = 22;
                    break;
                }
                break;
            case -599445191:
                if (str.equals("complete")) {
                    obj = 12;
                    break;
                }
                break;
            case -284840886:
                if (str.equals("unknown")) {
                    obj = 1;
                    break;
                }
                break;
            case -174104201:
                if (str.equals("client_fill")) {
                    obj = 3;
                    break;
                }
                break;
            case -45894975:
                if (str.equals("IAS_VIEWABILITY")) {
                    obj = 21;
                    break;
                }
                break;
            case 3327206:
                if (str.equals("load")) {
                    obj = 2;
                    break;
                }
                break;
            case 3363353:
                if (str.equals("mute")) {
                    obj = 16;
                    break;
                }
                break;
            case 94750088:
                if (str.equals("click")) {
                    obj = 7;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    obj = 20;
                    break;
                }
                break;
            case 106440182:
                if (str.equals("pause")) {
                    obj = 18;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    obj = 8;
                    break;
                }
                break;
            case 110066619:
                if (str.equals("fullscreen")) {
                    obj = 14;
                    break;
                }
                break;
            case 113951609:
                if (str.equals("exitFullscreen")) {
                    obj = 15;
                    break;
                }
                break;
            case 354294980:
                if (str.equals("VideoImpression")) {
                    obj = 5;
                    break;
                }
                break;
            case 560220243:
                if (str.equals("firstQuartile")) {
                    obj = 9;
                    break;
                }
                break;
            case 883937877:
                if (str.equals("page_view")) {
                    obj = 6;
                    break;
                }
                break;
            case 1342121331:
                if (str.equals("closeEndCard")) {
                    obj = 23;
                    break;
                }
                break;
            case 1778167540:
                if (str.equals("creativeView")) {
                    obj = 13;
                    break;
                }
                break;
            case 2114088489:
                if (str.equals("Impression")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return TrackerEventType.TRACKER_EVENT_TYPE_LOAD;
            case 3:
                return TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL;
            case 4:
                return TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
            case 5:
                return TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER;
            case 6:
                return TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW;
            case 7:
                return TrackerEventType.TRACKER_EVENT_TYPE_CLICK;
            case 8:
                return TrackerEventType.TRACKER_EVENT_TYPE_PLAY;
            case 9:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q1;
            case 10:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q2;
            case 11:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q3;
            case 12:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q4;
            case 13:
                return TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            case 14:
                return TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN;
            case 15:
                return TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN;
            case 16:
                return TrackerEventType.TRACKER_EVENT_TYPE_MUTE;
            case 17:
                return TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE;
            case 18:
                return TrackerEventType.TRACKER_EVENT_TYPE_PAUSE;
            case 19:
                return TrackerEventType.TRACKER_EVENT_TYPE_RESUME;
            case 20:
                return TrackerEventType.TRACKER_EVENT_TYPE_ERROR;
            case 21:
                return TrackerEventType.TRACKER_EVENT_TYPE_IAS;
            case 22:
                return TrackerEventType.TRACKER_EVENT_TYPE_MOAT;
            case 23:
                return TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE;
            default:
                return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
    }

    public final String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            Object obj;
            jSONObject.put("type", this.f);
            jSONObject.put("url", this.a);
            String str = "eventType";
            switch (this.b) {
                case TRACKER_EVENT_TYPE_LOAD:
                    obj = "load";
                    break;
                case TRACKER_EVENT_TYPE_CLIENT_FILL:
                    obj = "client_fill";
                    break;
                case TRACKER_EVENT_TYPE_RENDER:
                    obj = "Impression";
                    break;
                case TRACKER_EVENT_TYPE_VIDEO_RENDER:
                    obj = "VideoImpression";
                    break;
                case TRACKER_EVENT_TYPE_PAGE_VIEW:
                    obj = "page_view";
                    break;
                case TRACKER_EVENT_TYPE_CLICK:
                    obj = "click";
                    break;
                case TRACKER_EVENT_TYPE_PLAY:
                    obj = "start";
                    break;
                case TRACKER_EVENT_TYPE_Q1:
                    obj = "firstQuartile";
                    break;
                case TRACKER_EVENT_TYPE_Q2:
                    obj = "midpoint";
                    break;
                case TRACKER_EVENT_TYPE_Q3:
                    obj = "thirdQuartile";
                    break;
                case TRACKER_EVENT_TYPE_Q4:
                    obj = "complete";
                    break;
                case TRACKER_EVENT_TYPE_CREATIVE_VIEW:
                    obj = "creativeView";
                    break;
                case TRACKER_EVENT_TYPE_FULLSCREEN:
                    obj = "fullscreen";
                    break;
                case TRACKER_EVENT_TYPE_EXIT_FULLSCREEN:
                    obj = "exitFullscreen";
                    break;
                case TRACKER_EVENT_TYPE_MUTE:
                    obj = "mute";
                    break;
                case TRACKER_EVENT_TYPE_UNMUTE:
                    obj = "unmute";
                    break;
                case TRACKER_EVENT_TYPE_PAUSE:
                    obj = "pause";
                    break;
                case TRACKER_EVENT_TYPE_RESUME:
                    obj = "resume";
                    break;
                case TRACKER_EVENT_TYPE_ERROR:
                    obj = "error";
                    break;
                case TRACKER_EVENT_TYPE_IAS:
                    obj = "IAS_VIEWABILITY";
                    break;
                case TRACKER_EVENT_TYPE_MOAT:
                    obj = "zMoatVASTIDs";
                    break;
                case TRACKER_EVENT_TYPE_END_CARD_CLOSE:
                    obj = "closeEndCard";
                    break;
                default:
                    obj = "unknown";
                    break;
            }
            jSONObject.put(str, obj);
            jSONObject.put("eventId", this.g);
            jSONObject.put("extras", d.a(this.c == null ? new HashMap() : this.c, ","));
            return jSONObject.toString();
        } catch (JSONException e) {
            StringBuilder stringBuilder = new StringBuilder("Error serializing an ");
            stringBuilder.append(e);
            stringBuilder.append(" instance (");
            stringBuilder.append(e.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }
}
