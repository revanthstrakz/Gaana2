package com.moengage.core;

import android.content.Context;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.inapp.InAppController;
import com.moengage.inapp.InAppController.InAppHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MoEEventManager {
    private static MoEEventManager _INSTANCE;
    private List<String> mBlackListedEvents = null;
    private ConfigurationProvider mConfigProvider = null;
    private Context mContext;
    private int mEventCounter = 0;
    private List<String> mFlushEvents;
    private List<String> mTriggerEvents;

    private MoEEventManager(Context context) {
        this.mContext = context;
        this.mConfigProvider = ConfigurationProvider.getInstance(context);
        getBlackListedEvents();
        getTriggerEvents();
        getFlushEvents();
    }

    public static MoEEventManager getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEEventManager(context);
        }
        return _INSTANCE;
    }

    private boolean isEventBlackListed(String str) {
        return !this.mBlackListedEvents.isEmpty() && this.mBlackListedEvents.contains(str);
    }

    /* Access modifiers changed, original: 0000 */
    public void getBlackListedEvents() {
        try {
            this.mBlackListedEvents = new ArrayList();
            String blackListEvents = this.mConfigProvider.getBlackListEvents();
            if (!TextUtils.isEmpty(blackListEvents)) {
                String[] split = blackListEvents.split(";");
                if (split != null && split.length > 0) {
                    for (Object add : split) {
                        this.mBlackListedEvents.add(add);
                    }
                }
            }
        } catch (Exception e) {
            Logger.e("MoEEventManager: getBlackListedEvents() ", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int getEventCounter() {
        return this.mEventCounter;
    }

    /* Access modifiers changed, original: 0000 */
    public void incrementEventCounter() {
        this.mEventCounter++;
    }

    /* Access modifiers changed, original: 0000 */
    public void setEventCounter(int i) {
        this.mEventCounter = i;
    }

    public void trackEvent(String str, JSONObject jSONObject) {
        try {
            if (!this.mConfigProvider.isAppEnabled()) {
                return;
            }
            if (isEventBlackListed(str)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEEventManager: Event Blacklisted : ");
                stringBuilder.append(str);
                Logger.e(stringBuilder.toString());
                return;
            }
            Event event = new Event(MoEHelperUtils.getDatapointJSON(str.trim(), jSONObject));
            if (str.equals(MoEHelperConstants.EVENT_APP_INSTALL)) {
                MoEUtils.setInstallRegistered(this.mContext);
            }
            if (isSmartTriggerEvent(str)) {
                Logger.v("MoEEventManager:acting on auto trigger");
                InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
                if (inAppHandler != null) {
                    inAppHandler.registerAutoTriggerEvent(this.mContext, event);
                }
            }
            MoEDispatcher.getInstance(this.mContext).writeDataPointToStorage(event);
            if (isFlushEvent(str)) {
                Logger.v("MoEEventManager: trackEvent() flush event, flushing events");
                MoEHelper.getInstance(this.mContext).syncInteractionDataNow();
            }
        } catch (Exception e) {
            Logger.f("MoEEventManager: trackEvent() ", e);
        }
    }

    private boolean isSmartTriggerEvent(String str) {
        return (this.mTriggerEvents != null && this.mTriggerEvents.contains(str)) || MoEHelperConstants.EVENT_APP_INSTALL.equals(str);
    }

    public void getTriggerEvents() {
        try {
            String smartTriggerList = this.mConfigProvider.getSmartTriggerList();
            if (smartTriggerList == null) {
                Logger.v("MoEEventManager:No smart triggers found");
                return;
            }
            String[] split = smartTriggerList.split(";");
            this.mTriggerEvents = new ArrayList(split.length);
            for (Object add : split) {
                this.mTriggerEvents.add(add);
            }
        } catch (Exception e) {
            Logger.e("MoEEventManager: getTriggerEvents()", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void getFlushEvents() {
        try {
            String flushEvents = this.mConfigProvider.getFlushEvents();
            if (TextUtils.isEmpty(flushEvents)) {
                Logger.v("MoEEventManager: getFlushEvents() No flush events");
                return;
            }
            String[] split = flushEvents.split(";");
            this.mFlushEvents = new ArrayList(split.length);
            for (Object add : split) {
                this.mFlushEvents.add(add);
            }
        } catch (Exception unused) {
            Logger.e("MoEEventManager: getFlushEvents()");
        }
    }

    private boolean isFlushEvent(String str) {
        return this.mFlushEvents != null && this.mFlushEvents.contains(str);
    }
}
