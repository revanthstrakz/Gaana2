package com.moengage.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.providers.MoEDataContract.BatchDataEntity;
import com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity;
import com.moe.pushlibrary.providers.MoEDataContract.DatapointEntity;
import com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity;
import com.moe.pushlibrary.providers.MoEDataContract.MessageEntity;
import com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.executor.ITask;
import com.moengage.core.executor.OnTaskCompleteListener;
import com.moengage.core.executor.SDKTask;
import com.moengage.core.executor.TaskProcessor;
import com.moengage.core.executor.TaskResult;
import com.moengage.inapp.InAppController;
import com.moengage.inapp.InAppController.InAppHandler;
import com.moengage.inapp.InAppController.NETWORK_CALL_TYPE;
import com.moengage.location.GeoManager;
import com.moengage.location.GeoManager.LocationHandler;
import com.moengage.push.MoEMessagingManager;
import com.moengage.push.MoEMessagingManager.MessagingHandler;
import com.moengage.push.PushManager;
import com.moengage.push.PushManager.PushHandler;
import com.til.colombia.android.internal.e;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class MoEDispatcher implements OnTaskCompleteListener {
    private static MoEDispatcher _INSTANCE;
    private ConfigurationProvider configProvider;
    private final Object lock = new Object();
    private Context mContext;
    private boolean mNeedToCheckForGAIDChange = true;
    private ScheduledExecutorService mScheduler;
    private TaskProcessor mTaskProcessor;
    private List<String> optedOutActivities;
    private HashMap<String, Boolean> runningTaskList;
    private boolean shouldClearData = false;
    private boolean shouldTrackUniqueId = false;
    private JSONObject uniqueIdAttribute = null;

    private MoEDispatcher(Context context) {
        if (context != null) {
            this.mContext = context;
            this.configProvider = ConfigurationProvider.getInstance(this.mContext);
            this.mTaskProcessor = TaskProcessor.getInstance();
            this.runningTaskList = new HashMap();
            checkAndAddDevice();
            this.mTaskProcessor.setOnTaskCompleteListener(this);
            return;
        }
        Logger.e("MoEDispatcher  : context is null");
    }

    public static MoEDispatcher getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEDispatcher(context);
        }
        return _INSTANCE;
    }

    public void checkForInAppMessages(boolean z) {
        if (this.configProvider.isInAppEnabled() && this.configProvider.isAppEnabled()) {
            Logger.v("MoEDispatcher: showInAppIfPossible: Check in app messages");
            if (z) {
                InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
                if (inAppHandler != null) {
                    inAppHandler.showInAppIfPossible(this.mContext);
                }
            }
        }
    }

    public void onStart(Activity activity, Intent intent) {
        if (!this.configProvider.isAppEnabled()) {
            return;
        }
        if (activity == null) {
            Logger.e("MoEDispatcher:onStart activity instance is null");
            return;
        }
        if (intent == null) {
            intent = activity.getIntent();
        }
        this.mContext = activity.getApplicationContext();
        Logger.v("MoEDispatcher:onStart ----");
        MoEHelperUtils.dumpIntentExtras(intent);
        String name = activity.getClass().getName();
        if (!isActivityOptedOut(activity)) {
            addTaskToQueue(new ActivityStartTask(this.mContext, name, this.mNeedToCheckForGAIDChange));
        }
        Context applicationContext = activity.getApplicationContext();
        int i = applicationContext.getResources().getConfiguration().orientation;
        String activityName = InAppController.getInstance().getActivityName();
        int activityOrientation = InAppController.getInstance().getActivityOrientation();
        if (activityName == null || activityOrientation == -1) {
            saveCurrentActivityDetails(name, i);
            syncInAppsAndGeo();
        } else if (!activityName.equals(name) || activityOrientation == i) {
            saveCurrentActivityDetails(name, i);
            syncInAppsAndGeo();
        } else {
            InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
            if (inAppHandler != null) {
                inAppHandler.showInAppOnConfigurationChange(this.mContext);
            }
        }
        saveCurrentActivityDetails(name, i);
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                extras.remove(MoEHelperConstants.NAVIGATION_PROVIDER_KEY);
                extras.remove(MoEHelperConstants.NAVIGATION_SOURCE_KEY);
                PushHandler pushHandler = PushManager.getInstance().getPushHandler();
                if (pushHandler != null) {
                    pushHandler.logNotificationClicked(applicationContext, intent);
                }
            }
        }
        if (MoEHelper.getActivityCounter() == 1) {
            pushTokenFallBack();
        }
        MoEUtils.updateTestDeviceState(this.mContext);
    }

    private void pushTokenFallBack() {
        PushHandler pushHandler = PushManager.getInstance().getPushHandler();
        if (pushHandler != null) {
            pushHandler.setPushRegistrationFallback(this.mContext);
        }
    }

    private void syncInAppsAndGeo() {
        if (this.configProvider.isAppEnabled()) {
            Logger.v("MoEDispatcher: Fetch or query in app message");
            if (this.configProvider.isInAppEnabled()) {
                InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
                if (inAppHandler != null) {
                    inAppHandler.syncOrShowInApps(this.mContext);
                }
            }
            if (this.configProvider.isGeoEnabled()) {
                LocationHandler handler = GeoManager.getInstance().getHandler(this.mContext);
                if (handler != null) {
                    handler.updateFenceAndLocation(this.mContext);
                }
            }
        }
    }

    public void trackNotificationClicked(long j) {
        if (this.configProvider.isAppEnabled()) {
            addTaskToQueue(new NotificationClickedTask(this.mContext, j));
        }
    }

    public void setUserAttribute(JSONObject jSONObject) {
        addTaskToQueueBeginning(new SetUserAttributeTask(this.mContext, jSONObject, true));
    }

    public void setCustomUserAttribute(JSONObject jSONObject) {
        if (this.configProvider.isAppEnabled()) {
            addTaskToQueueBeginning(new SetUserAttributeTask(this.mContext, jSONObject, false));
        }
    }

    public void setExistingUser(boolean z, Context context) {
        try {
            if (this.configProvider.isAppEnabled()) {
                boolean isInstallRegistered = MoEUtils.isInstallRegistered(context);
                int appVersion = this.configProvider.getAppVersion();
                if (z) {
                    int storedAppVersion = this.configProvider.getStoredAppVersion();
                    if (appVersion != storedAppVersion) {
                        this.configProvider.storeAppVersion(appVersion);
                        MoEEventManager.getInstance(context).trackEvent(MoEHelperConstants.EVENT_APP_UPD, new PayloadBuilder().putAttrInt(MoEHelperConstants.FROM_VERSION, storedAppVersion).putAttrInt(MoEHelperConstants.TO_VERSION, appVersion).putAttrDate(MoEHelperConstants.TIME_OF_UPDATE, new Date()).build());
                        Logger.v("MoEDispatcher:setExistingUser:tracking update");
                    }
                } else if (!isInstallRegistered) {
                    this.configProvider.storeAppVersion(appVersion);
                    PayloadBuilder payloadBuilder = new PayloadBuilder();
                    String installReferrer = MoEHelperUtils.getInstallReferrer(context);
                    if (!TextUtils.isEmpty(installReferrer)) {
                        payloadBuilder.putAttrString(MoEHelperConstants.PREF_KEY_INSTALL_REFERRER, installReferrer);
                    }
                    payloadBuilder.putAttrInt(MoEHelperConstants.VERSION, appVersion).putAttrInt("sdk_ver", MoEHelperConstants.LIB_VERSION).putAttrLong(MoEHelperConstants.TIME_OF_INSTALL, System.currentTimeMillis()).putAttrString(e.C, "ANDROID");
                    MoEEventManager.getInstance(context).trackEvent(MoEHelperConstants.EVENT_APP_INSTALL, payloadBuilder.build());
                    Logger.v("MoEDispatcher:setExistingUser:tracking install");
                }
            }
        } catch (Exception e) {
            Logger.f("MoEDispatcher: setExistingUser: ", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void cancelRegistrationFallback() {
        MoEUtils.setRegistrationScheduled(this.mContext, false);
        MoEUtils.saveCurrentExponentialCounter(this.mContext, 1);
    }

    /* Access modifiers changed, original: 0000 */
    public void writeDataPointToStorage(Event event) {
        if (this.configProvider.isAppEnabled()) {
            addTaskToQueue(new TrackEventTask(this.mContext, event));
        }
    }

    public void onResume(Activity activity, boolean z) {
        if (this.configProvider.isAppEnabled() && !z) {
            showDialogAfterPushClick(activity);
        }
    }

    public void onStop(Activity activity, boolean z) {
        if (this.configProvider.isAppEnabled() && activity != null && !z) {
            addTaskToQueue(new ActivityStopTask(this.mContext, activity.getClass().getName()));
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void handleLogout(boolean z) {
        Logger.i("Started logout process");
        if (this.configProvider.isAppEnabled()) {
            trackLogoutEvent(z);
            sendInteractionData();
            this.shouldClearData = true;
        }
    }

    private void trackLogoutEvent(boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (z) {
                jSONObject.put("type", "forced");
            }
            MoEDAO.getInstance(this.mContext).addEvent(new Event(MoEHelperUtils.getDatapointJSON("MOE_LOGOUT", jSONObject)), this.mContext);
        } catch (Exception e) {
            Logger.f("MoEDispatcher: trackLogoutEvent(): ", e);
        }
    }

    @WorkerThread
    private void clearDataOnLogout() {
        this.mContext.getContentResolver().delete(DatapointEntity.getContentUri(this.mContext), null, null);
        this.mContext.getContentResolver().delete(MessageEntity.getContentUri(this.mContext), null, null);
        this.mContext.getContentResolver().delete(InAppMessageEntity.getContentUri(this.mContext), null, null);
        this.mContext.getContentResolver().delete(UserAttributeEntity.getContentUri(this.mContext), null, null);
        this.mContext.getContentResolver().delete(CampaignListEntity.getContentUri(this.mContext), null, null);
        this.mContext.getContentResolver().delete(BatchDataEntity.getContentUri(this.mContext), null, null);
        ConfigurationProvider.getInstance(this.mContext).removeUserConfigurationOnLogout();
        ConfigurationProvider.getInstance(this.mContext).setDeviceRegistered(false);
        if (ConfigurationProvider.getInstance(this.mContext).isGCMRegistrationEnabled()) {
            PushHandler pushHandler = PushManager.getInstance().getPushHandler();
            if (pushHandler != null) {
                pushHandler.registerForPushToken(this.mContext);
            }
        }
        this.shouldClearData = false;
        Logger.i("Completed logout process");
    }

    public void sendInteractionData() {
        if (this.configProvider.isAppEnabled()) {
            addTaskToQueue(new CreatingDataBatchTask(this.mContext));
        }
    }

    public void onFragmentStart(Activity activity, String str) {
        syncInAppsAndGeo();
    }

    @WorkerThread
    public void setInboxMessageClicked(long j) {
        MoEDAO.getInstance(this.mContext).setMessageClicked(j);
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void handleAppUpdateEvent() {
        try {
            if (this.configProvider.isAppEnabled()) {
                int storedAppVersion = this.configProvider.getStoredAppVersion();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MoEHelperConstants.FROM_VERSION, storedAppVersion);
                jSONObject.put(MoEHelperConstants.TO_VERSION, this.configProvider.getAppVersion());
                MoEDAO.getInstance(this.mContext).addEvent(new Event(MoEHelperUtils.getDatapointJSON(MoEHelperConstants.EVENT_APP_UPD, jSONObject)), this.mContext);
                if (!MoEHelper.isAppInForeground()) {
                    sendInteractionData();
                }
                Logger.i("Adding an update event");
                if (this.configProvider.isGCMRegistrationEnabled()) {
                    dispatchPushTask(PushManager.REQ_REGISTRATION);
                }
            }
        } catch (Exception e) {
            Logger.f("Adding update event", e);
        }
    }

    @WorkerThread
    public Cursor getAllMessages() {
        return MoEDAO.getInstance(this.mContext).getMessages(this.mContext);
    }

    @WorkerThread
    public int getUnreadMessageCount() {
        return MoEDAO.getInstance(this.mContext).getUnreadMessageCount();
    }

    public void initialize(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            Logger.e("MoEDispatcher: initialize : AppId is null");
            return;
        }
        String storedSenderId = ConfigurationProvider.getInstance(this.mContext).getStoredSenderId();
        this.configProvider.saveAppDetails(str, str2);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(storedSenderId) && !storedSenderId.equals(str) && ConfigurationProvider.getInstance(this.mContext).isGCMRegistrationEnabled()) {
            ConfigurationProvider.getInstance(this.mContext).setGCMToken(null);
            dispatchPushTask(PushManager.REQ_REGISTRATION);
        }
    }

    public void checkAndShowLinkedInApp(String str) {
        InAppHandler inAppHandler = InAppController.getInstance().getInAppHandler();
        if (inAppHandler != null) {
            inAppHandler.fetchLinkedInApp(this.mContext, str);
        }
    }

    private void showDialogAfterPushClick(Activity activity) {
        if (activity != null) {
            try {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Bundle extras = intent.getExtras();
                    if (extras != null && extras.containsKey(MoEHelperConstants.GCM_EXTRA_SHOW_DIALOG)) {
                        intent.removeExtra(MoEHelperConstants.GCM_EXTRA_SHOW_DIALOG);
                        if (extras.containsKey(MoEHelperConstants.GCM_EXTRA_COUPON_CODE)) {
                            MoEUtils.showCouponDialog(extras.getString(MoEHelperConstants.GCM_EXTRA_CONTENT), extras.getString(MoEHelperConstants.GCM_EXTRA_COUPON_CODE), activity);
                            intent.removeExtra(MoEHelperConstants.GCM_EXTRA_CONTENT);
                            intent.removeExtra(MoEHelperConstants.GCM_EXTRA_COUPON_CODE);
                        } else {
                            MoEUtils.showNormalDialogWithOk(extras.getString(MoEHelperConstants.GCM_EXTRA_CONTENT), activity);
                            intent.removeExtra(MoEHelperConstants.GCM_EXTRA_CONTENT);
                        }
                    }
                }
            } catch (Exception e) {
                Logger.f("MoEDispatcher: showDialogAfterPushClick : ", e);
            }
        }
    }

    private void saveCurrentActivityDetails(String str, int i) {
        InAppController.getInstance().setActivityName(str);
        InAppController.getInstance().setActivityOrientation(i);
    }

    public void addTaskToQueue(ITask iTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Trying to add ");
        stringBuilder.append(iTask.getTaskTag());
        stringBuilder.append(" to the queue");
        Logger.v(stringBuilder.toString());
        if (!iTask.isSynchronous()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(iTask.getTaskTag());
            stringBuilder.append(" added to queue");
            Logger.v(stringBuilder.toString());
            this.runningTaskList.put(iTask.getTaskTag(), Boolean.valueOf(iTask.isSynchronous()));
            this.mTaskProcessor.addTask(iTask);
        } else if (!this.runningTaskList.containsKey(iTask.getTaskTag())) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(iTask.getTaskTag());
            stringBuilder.append(" added to queue");
            Logger.v(stringBuilder.toString());
            this.runningTaskList.put(iTask.getTaskTag(), Boolean.valueOf(iTask.isSynchronous()));
            this.mTaskProcessor.addTask(iTask);
        }
    }

    public void addTaskToQueueBeginning(ITask iTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Trying to add ");
        stringBuilder.append(iTask.getTaskTag());
        stringBuilder.append(" to the queue");
        Logger.v(stringBuilder.toString());
        if (!iTask.isSynchronous()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(iTask.getTaskTag());
            stringBuilder.append(" added to beginning of queue");
            Logger.v(stringBuilder.toString());
            this.runningTaskList.put(iTask.getTaskTag(), Boolean.valueOf(iTask.isSynchronous()));
            this.mTaskProcessor.addTaskToFront(iTask);
        } else if (!this.runningTaskList.containsKey(iTask.getTaskTag())) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(iTask.getTaskTag());
            stringBuilder.append(" added to beginning of queue");
            Logger.v(stringBuilder.toString());
            this.runningTaskList.put(iTask.getTaskTag(), Boolean.valueOf(iTask.isSynchronous()));
            this.mTaskProcessor.addTaskToFront(iTask);
        }
    }

    private void checkAndAddDevice() {
        synchronized (this.lock) {
            try {
                if (!this.configProvider.isAppEnabled()) {
                    return;
                } else if (this.configProvider.isGCMRegistrationDisabled()) {
                    if (!(TextUtils.isEmpty(this.configProvider.getGCMToken()) || this.configProvider.isDeviceRegistered())) {
                        addTaskToQueue(new DeviceAddTask(this.mContext));
                    }
                } else if (!this.configProvider.isDeviceRegistered()) {
                    MoEUtils.setRegistrationScheduled(this.mContext, true);
                    dispatchPushTask(PushManager.REQ_REGISTRATION);
                }
            } catch (Exception e) {
                Logger.f("MoEDispatcher:checkAndAddDevice", e);
            }
        }
    }

    public void onTaskComplete(String str, TaskResult taskResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Task completed : ");
        stringBuilder.append(str);
        Logger.v(stringBuilder.toString());
        if (this.runningTaskList.containsKey(str)) {
            this.runningTaskList.remove(str);
        }
        boolean z = true;
        int hashCode = str.hashCode();
        if (hashCode != -1633899079) {
            if (hashCode != -993050194) {
                if (hashCode != 481489516) {
                    if (hashCode == 492706894 && str.equals(SDKTask.TAG_SET_USER_ATTRIBUTES)) {
                        z = true;
                    }
                } else if (str.equals(SDKTask.TAG_ACTIVITY_START)) {
                    z = false;
                }
            } else if (str.equals(SDKTask.TAG_SEND_INTERACTION_DATA)) {
                z = true;
            }
        } else if (str.equals(SDKTask.TAG_INAPP_NETWORK_TASK)) {
            z = true;
        }
        switch (z) {
            case false:
                if (taskResult.isSuccess()) {
                    this.mNeedToCheckForGAIDChange = ((Boolean) taskResult.getPayload()).booleanValue();
                    return;
                }
                return;
            case true:
                if (NETWORK_CALL_TYPE.SYNC_IN_APPS.equals(taskResult.getPayload())) {
                    InAppController.getInstance().getInAppHandler().setInappsSynced(taskResult.isSuccess());
                    return;
                }
                return;
            case true:
                if (this.shouldClearData) {
                    clearDataOnLogout();
                    if (this.shouldTrackUniqueId) {
                        trackChangedUniqueId();
                        return;
                    }
                    return;
                }
                return;
            case true:
                if (!taskResult.isSuccess()) {
                    this.shouldTrackUniqueId = true;
                    this.uniqueIdAttribute = (JSONObject) taskResult.getPayload();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void resetStates() {
        this.mNeedToCheckForGAIDChange = true;
    }

    private boolean isActivityOptedOut(Activity activity) {
        try {
            if (this.optedOutActivities == null) {
                this.optedOutActivities = ConfigurationProvider.getInstance(this.mContext).getOptedOutActivities();
            }
            if (this.optedOutActivities != null && this.optedOutActivities.contains(activity.getClass().getName())) {
                return true;
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEDispatcher#isActivityOptedOut Exception Occurred");
            stringBuilder.append(e);
            Logger.f(stringBuilder.toString());
        }
        return false;
    }

    private void dispatchPushTask(String str) {
        PushHandler pushHandler = PushManager.getInstance().getPushHandler();
        if (pushHandler != null) {
            pushHandler.offLoadToWorker(this.mContext, str);
        }
    }

    public void logPushFailureEvent(Context context, String str) {
        int pushRegistrationFailureCount = ConfigurationProvider.getInstance(context).getPushRegistrationFailureCount();
        try {
            if (this.configProvider.isAppEnabled()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("push_fail_count", pushRegistrationFailureCount);
                jSONObject.put("push_fail_reason", str);
                writeDataPointToStorage(new Event(MoEHelperUtils.getDatapointJSON("EVENT_ACTION_DEVICE_ATTRIBUTE", jSONObject)));
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("MoEDispatcher: logPushFailureEvent Exception: +");
            stringBuilder.append(e.toString());
            Logger.f(stringBuilder.toString());
        }
    }

    private void syncConfigIfRequired() {
        if (this.configProvider.getLastConfigSyncTime() + 3600000 < System.currentTimeMillis()) {
            addTaskToQueueBeginning(new SyncConfigAPITask(this.mContext));
        }
    }

    public void trackDeviceLocale() {
        try {
            if (this.configProvider.isAppEnabled()) {
                trackDeviceAndUserAttribute("LOCALE_COUNTRY", Locale.getDefault().getCountry());
                trackDeviceAndUserAttribute("LOCALE_COUNTRY_DISPLAY", Locale.getDefault().getDisplayCountry());
                trackDeviceAndUserAttribute("LOCALE_LANGUAGE", Locale.getDefault().getLanguage());
                trackDeviceAndUserAttribute("LOCALE_LANGUAGE_DISPLAY", Locale.getDefault().getDisplayLanguage());
                trackDeviceAndUserAttribute("LOCALE_DISPLAY", Locale.getDefault().getDisplayName());
                trackDeviceAndUserAttribute("LOCALE_COUNTRY_ ISO3", Locale.getDefault().getISO3Country());
                trackDeviceAndUserAttribute("LOCALE_LANGUAGE_ISO3", Locale.getDefault().getISO3Language());
            }
        } catch (Exception e) {
            Logger.f("MoEDispatcher : trackDeviceLocale", e);
        }
    }

    private void trackDeviceAndUserAttribute(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            setUserAttribute(jSONObject);
        } catch (Exception e) {
            Logger.f("MoEDispatcher: trackDeviceAndUserAttribute() ", e);
        }
    }

    public void logoutUser(boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("IS_FORCE_LOGOUT", z);
            addTaskToQueue(new MoEWorkerTask(this.mContext, "LOGOUT", bundle));
        } catch (Exception e) {
            Logger.f("MoEDispatcher: logoutUser() ", e);
        }
    }

    private void trackChangedUniqueId() {
        if (this.uniqueIdAttribute != null) {
            setUserAttribute(this.uniqueIdAttribute);
            this.uniqueIdAttribute = null;
            this.shouldTrackUniqueId = false;
        }
    }

    public void setAlias(JSONObject jSONObject) {
        if (this.configProvider.isAppEnabled()) {
            addTaskToQueue(new SetAliasTask(this.mContext, jSONObject));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setDeviceAttribute(JSONObject jSONObject) {
        addTaskToQueue(new SetDeviceAttributeTask(this.mContext, jSONObject));
    }

    private void schedulePeriodicFlushIfRequired() {
        try {
            if (this.configProvider.isPeriodicFlushEnabled() && MoEHelper.getInstance(this.mContext).getPeriodicSyncState()) {
                AnonymousClass1 anonymousClass1 = new Runnable() {
                    public void run() {
                        Logger.v("MoEDispatcher: schedulePeriodicFlushIfRequired() inside runnable, will sync now");
                        MoEDispatcher.this.sendInteractionData();
                    }
                };
                long periodicFlushTime = this.configProvider.getPeriodicFlushTime();
                if (MoEHelper.getInstance(this.mContext).getFlushInterval() > periodicFlushTime) {
                    periodicFlushTime = MoEHelper.getInstance(this.mContext).getFlushInterval();
                }
                long j = periodicFlushTime;
                Logger.v("MoEDispatcher: schedulePeriodicFlushIfRequired() scheduling periodic sync");
                this.mScheduler = Executors.newScheduledThreadPool(1);
                this.mScheduler.scheduleWithFixedDelay(anonymousClass1, j, j, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            Logger.e("MoEDispatcher: schedulePeriodicFlushIfRequired() ", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void shutDownPeriodicFlush() {
        try {
            if (this.configProvider.isPeriodicFlushEnabled() && MoEHelper.getInstance(this.mContext).getPeriodicSyncState() && this.mScheduler != null) {
                Logger.v("MoEDispatcher: shutDownPeriodicFlush() shutting down periodic flush");
                this.mScheduler.shutdownNow();
            }
        } catch (Exception e) {
            Logger.f("MoEDispatcher: shutDownPeriodicFlush() ", e);
        }
    }

    public void onAppOpen() {
        try {
            syncConfigIfRequired();
            MessagingHandler messagingHandler = MoEMessagingManager.getInstance().getMessagingHandler(this.mContext);
            if (messagingHandler != null) {
                messagingHandler.forceMessageSync(this.mContext, true);
            }
            PushHandler pushHandler = PushManager.getInstance().getPushHandler();
            if (pushHandler != null) {
                pushHandler.offLoadToWorker(this.mContext, PushManager.REG_ON_APP_OPEN);
            }
            schedulePeriodicFlushIfRequired();
        } catch (Exception e) {
            Logger.f("MoEDispatcher: onAppOpen() ", e);
        }
    }
}
