package com.moengage.inapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.InAppNetworkCallsTask;
import com.moengage.core.Logger;
import com.moengage.core.MoEConstants;
import com.moengage.core.MoEDispatcher;
import com.moengage.core.MoEUtils;
import com.moengage.inapp.InAppController.NETWORK_CALL_TYPE;
import com.moengage.inapp.InAppMessage.ALIGN_TYPE;
import com.moengage.inapp.InAppMessage.Rules;
import com.moengage.inapp.InAppMessage.TYPE;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class InAppManager {
    private static final String META_DATA_SHOW_INAPP = "showInApp";
    private static InAppManager _INSTANCE;
    private final Object activityLock = new Object();
    private Handler autoDismissHandler;
    private WeakReference<Activity> currentActivity;
    private InAppMessage currentInAppMessage;
    private AtomicBoolean inAppManagerState;
    @Nullable
    private InAppMessageListener inAppMessageListener;
    private InAppRulesCache inAppRulesCache = new InAppRulesCache(this, null);
    private final Object inappFetchLock = new Object();
    private final Object inappQueryLock = new Object();
    private boolean inappsSynced;
    private AtomicBoolean isInAppAllowed;
    private long lastInAppShownAt;
    private long minDelayBetweenInApps;
    private HashSet<String> nonInAppActivityList;
    private AtomicBoolean showingInAppMessage;

    public interface InAppMessageListener {
        @Deprecated
        boolean onInAppClick(@Nullable String str, @Nullable Bundle bundle, @Nullable Uri uri);

        void onInAppClosed(InAppMessage inAppMessage);

        void onInAppShown(InAppMessage inAppMessage);

        boolean showInAppMessage(InAppMessage inAppMessage);
    }

    private class InAppCacheObserver implements Observer {
        private InAppCacheObserver() {
        }

        /* synthetic */ InAppCacheObserver(InAppManager inAppManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void update(Observable observable, Object obj) {
            Logger.v("InAppManager: InAppCacheObserver: updated cache so will try to show inapp");
            if (!InAppManager.this.isShowingInAppMessage()) {
                Activity currentActivity = InAppManager.this.getCurrentActivity();
                if (currentActivity != null) {
                    MoEHelper.getInstance(currentActivity).showInAppIfAny(true);
                }
            }
        }
    }

    private class InAppRulesCache extends Observable {
        private LinkedHashSet<Rules> inappCache;

        private InAppRulesCache() {
        }

        /* synthetic */ InAppRulesCache(InAppManager inAppManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Nullable
        public LinkedHashSet<Rules> getValue() {
            LinkedHashSet linkedHashSet;
            synchronized (InAppManager.this.inappQueryLock) {
                linkedHashSet = this.inappCache;
            }
            return linkedHashSet;
        }

        public void setValue(LinkedHashSet<Rules> linkedHashSet) {
            synchronized (InAppManager.this.inappQueryLock) {
                this.inappCache = linkedHashSet;
            }
            setChanged();
            notifyObservers();
        }
    }

    @Nullable
    @WorkerThread
    public InAppMessage fetchSingleInApp(Context context, String str) {
        return null;
    }

    public static synchronized InAppManager getInstance() {
        InAppManager inAppManager;
        synchronized (InAppManager.class) {
            if (_INSTANCE == null) {
                _INSTANCE = new InAppManager();
            }
            inAppManager = _INSTANCE;
        }
        return inAppManager;
    }

    private InAppManager() {
        this.inAppRulesCache.addObserver(new InAppCacheObserver(this, null));
        this.nonInAppActivityList = new HashSet();
        this.inAppManagerState = new AtomicBoolean(true);
        this.isInAppAllowed = new AtomicBoolean(true);
        this.lastInAppShownAt = -1;
        this.showingInAppMessage = new AtomicBoolean(false);
        this.minDelayBetweenInApps = ConfigurationProvider.getInAppDelayDuration();
        this.inappsSynced = false;
        this.autoDismissHandler = new Handler(Looper.getMainLooper());
    }

    public void changeLocalInAppState(boolean z) {
        this.isInAppAllowed.set(z);
    }

    public void setMinimumInterval(long j) {
        this.minDelayBetweenInApps = j;
    }

    public void setInAppListener(InAppMessageListener inAppMessageListener) {
        this.inAppMessageListener = inAppMessageListener;
    }

    @Nullable
    public InAppMessageListener getInAppMessageListener() {
        return this.inAppMessageListener;
    }

    public void setCacheObserver(Observer observer) {
        this.inAppRulesCache.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        this.inAppRulesCache.deleteObserver(observer);
    }

    @UiThread
    public void registerInAppManager(Activity activity) {
        Logger.v("InAppManager: registerInAppmanager -- ");
        updateCurrentActivityContext(activity);
        getNonInAppActivityList(activity.getApplicationContext());
        changeLocalInAppState(true);
        this.inAppManagerState.set(true);
        if (this.lastInAppShownAt == -1) {
            this.lastInAppShownAt = ConfigurationProvider.getInstance(activity.getApplicationContext()).getLastInAppShownTime();
        }
    }

    @UiThread
    public void unregisterInAppManager(Activity activity) {
        try {
            Logger.v("InAppManager: unregisterInAppManager -- ");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                Logger.v("InAppManager:unregisterInAppManager: current activity instance is null");
            } else if (activity.getClass().getName().equals(currentActivity.getClass().getName())) {
                updateCurrentActivityContext(null);
                this.inAppManagerState.set(false);
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: unregisterInAppManager: ");
            stringBuilder.append(e.getMessage());
            Logger.f(stringBuilder.toString());
            this.inAppManagerState.set(false);
        }
    }

    public void updateCurrentActivityContext(Activity activity) {
        synchronized (this.activityLock) {
            this.currentActivity = new WeakReference(activity);
        }
    }

    @Nullable
    public Activity getCurrentActivity() {
        synchronized (this.activityLock) {
            if (this.currentActivity == null) {
                return null;
            }
            Activity activity = (Activity) this.currentActivity.get();
            if (activity == null) {
                return null;
            } else if (activity.isFinishing()) {
                return null;
            } else {
                return activity;
            }
        }
    }

    public void trackInAppShown(Context context, InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            Logger.v("InAppManager: trackInAppShown");
            InAppTracker.getInstance(context).inAppShown(inAppMessage);
        }
    }

    public void trackInAppWidgetClicked(Context context, InAppMessage inAppMessage, int i) {
        if (inAppMessage != null) {
            Logger.v("InAppManager:trackInAppWidgetClicked");
            InAppTracker.getInstance(context).trackInAppWidgetClicked(inAppMessage, i);
        }
    }

    public void trackInAppPrimaryClick(Context context, InAppMessage inAppMessage) {
        if (inAppMessage != null) {
            Logger.v("InAppManager:trackInAppPrimaryClick");
            InAppTracker.getInstance(context).trackInAppPrimaryClick(inAppMessage);
        }
    }

    public boolean isShowingInAppMessage() {
        return this.showingInAppMessage.get();
    }

    @WorkerThread
    public void updateInAppCache(Context context) {
        Logger.v("InAppManager: updateInAppCache requested");
        this.inAppRulesCache.setValue(InAppsDAO.getInstance(context).getActiveInAppCampaigns());
    }

    public InAppMessage getInAppMessageToShow(ALIGN_TYPE align_type, TYPE type, Context context) {
        synchronized (this.inappQueryLock) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("InAppManager: getInAppMessageToShow for type: ");
                stringBuilder.append(type);
                stringBuilder.append(" ALIGN_TYPE: ");
                stringBuilder.append(align_type);
                Logger.i(stringBuilder.toString());
                if (!this.inAppManagerState.get()) {
                    Logger.i("InAppManager: getInAppMessageToShow - Stop execution for state");
                    return null;
                } else if (this.inAppRulesCache == null) {
                    return null;
                } else if (isInAppAllowedInActivity()) {
                    Activity currentActivity = getCurrentActivity();
                    if (currentActivity == null) {
                        Logger.f("InAppManager: getInAppMessageToShow: current activity instance is null");
                        return null;
                    }
                    String name = currentActivity.getClass().getName();
                    LinkedHashSet value = this.inAppRulesCache.getValue();
                    if (value != null) {
                        Iterator it = value.iterator();
                        while (it.hasNext()) {
                            Rules rules = (Rules) it.next();
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("InAppManager: getInAppMessageToShow: checking campaign with id: ");
                            stringBuilder2.append(rules.campaignId);
                            Logger.i(stringBuilder2.toString());
                            if (align_type == null && rules.alignType == ALIGN_TYPE.EMBED) {
                                Logger.i("InAppManager : getInAppMessageToShow: cannot show nudge as inapp");
                            } else if (align_type != null && align_type != rules.alignType) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("InAppManager: getInAppMessageToShow: ");
                                stringBuilder2.append(rules.campaignId);
                                stringBuilder2.append(" not the intended alignment, looking for ");
                                stringBuilder2.append(align_type);
                                Logger.f(stringBuilder2.toString());
                            } else if (type != null && type != rules.type) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("InAppManager: getInAppMessageToShow: ");
                                stringBuilder2.append(rules.campaignId);
                                stringBuilder2.append(" not the intended type, looking for ");
                                stringBuilder2.append(type);
                                Logger.f(stringBuilder2.toString());
                            } else if (canShowInAppMessage(rules, System.currentTimeMillis() / 1000, name)) {
                                Cursor query = context.getContentResolver().query(InAppMessageEntity.getContentUri(context).buildUpon().appendPath(String.valueOf(rules._id)).build(), InAppMessageEntity.PROJECTION, null, null, null);
                                if (query != null && query.getCount() > 0) {
                                    query.moveToFirst();
                                    InAppMessage marshalInApp = InAppsDAO.getInstance(context).marshalInApp(query, false);
                                    query.close();
                                    return marshalInApp;
                                }
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("InAppManager: getInAppMessageToShow: cannot show inapp ");
                                stringBuilder2.append(rules.campaignId);
                                Logger.f(stringBuilder2.toString());
                            }
                        }
                    }
                } else {
                    Logger.i("InAppManager: getInAppMessageToShow InApp is NOT ALLOWED to be shown here");
                    MoEInAppFailureLogger.getInstance().updateCommonErrorCounter(MoEInAppFailureLogger.FAILURE_REASON_IN_APP_BLOCKED_ON_SCREEN);
                    return null;
                }
            } catch (Exception e) {
                Logger.f("InAppManager: getInAppRowIdToShow", e);
                return null;
            }
        }
    }

    public boolean canShowInAppMessage(Rules rules, long j, String str) {
        StringBuilder stringBuilder;
        if (rules.type == TYPE.SMART || rules.type == TYPE.TEST) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is a smart inapp or test and is active");
            Logger.v(stringBuilder.toString());
            return true;
        } else if (rules.ttl < j || !rules.isActive) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is expired");
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_CAMPAIGN_EXPIRED);
            return false;
        } else if (rules.type == TYPE.LINKED) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is a linked in-app");
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_NOT_EXPECTED_TYPE);
            return false;
        } else if (rules.isClicked && !rules.persistent) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is clicked and not persistent");
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_PERSISTENT);
            return false;
        } else if (rules.isShowing) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is currently showing");
            Logger.f(stringBuilder.toString());
            return false;
        } else if (rules.shownCount >= rules.maxTimes) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" has been shown for the maximum times");
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_SHOW_COUNT);
            return false;
        } else if (!TextUtils.isEmpty(rules.showOnlyIn) && !str.equals(rules.showOnlyIn)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" can only be shown in ");
            stringBuilder.append(rules.showOnlyIn);
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_SHOW_ONLY_IN_SCREEN);
            return false;
        } else if (rules.lastShown + rules.minmumDelay >= j * 1000) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" was showin recently at ");
            stringBuilder.append(rules.lastShown);
            Logger.f(stringBuilder.toString());
            MoEInAppFailureLogger.getInstance().updateCampaignFailureCounter(rules.campaignId, MoEInAppFailureLogger.FAILURE_REASON_MINIMUM_DELAY_BETWEEN_SAME_IN_APP);
            return false;
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: canShowInAppMessage: ");
            stringBuilder.append(rules.campaignId);
            stringBuilder.append(" is FIT TO BE SHOWN");
            Logger.v(stringBuilder.toString());
            return true;
        }
    }

    public boolean isInAppAllowedInActivity() {
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                Logger.v("InAppManager: isInAppAllowedInActivity:Activity context is null cannot show an inapp");
                return false;
            }
            if (this.nonInAppActivityList.contains(currentActivity.getClass().getName())) {
                Logger.v("InAppManager: isInAppAllowedInActivity:inapp is not allowed in this activity");
                return false;
            }
            return true;
        } catch (Exception e) {
            Logger.f("InAppManager: isInAppAllowedInActivity", e);
        }
    }

    public boolean isFetchRequired(Context context) {
        long lastInAppupdate = ConfigurationProvider.getInstance(context).getLastInAppupdate() + 900000;
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("InAppManager: isFetchRequired: Next server sync will happen in ");
        stringBuilder.append((lastInAppupdate - currentTimeMillis) / 1000);
        stringBuilder.append(" seconds. Is synced in this session ? --> ");
        stringBuilder.append(this.inappsSynced);
        Logger.v(stringBuilder.toString());
        return !this.inappsSynced || lastInAppupdate < currentTimeMillis;
    }

    public void syncInApps(Context context) {
        long lastInAppupdate = ConfigurationProvider.getInstance(context).getLastInAppupdate();
        try {
            String str;
            ArrayList inAppCampaignList = InAppsDAO.getInstance(context).getInAppCampaignList();
            if (inAppCampaignList == null || inAppCampaignList.size() <= 0) {
                str = null;
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MoEConstants.REQ_ATTR_CAMPAIGN_LIST, new JSONArray(inAppCampaignList));
                str = jSONObject.toString();
            }
            String str2 = str;
            HashMap hashMap = new HashMap();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MoEUtils.getAPIRoute(context));
            stringBuilder.append(InAppConstants.API_INAPP_V2);
            stringBuilder.append(InAppConstants.API_ENDPOINT_INAPPS);
            String stringBuilder2 = stringBuilder.toString();
            hashMap.put(MoEConstants.PARAM_LAST_UPDATED, Long.toString(lastInAppupdate));
            MoEDispatcher.getInstance(context).addTaskToQueueBeginning(new InAppNetworkCallsTask(context, stringBuilder2, hashMap, str2, NETWORK_CALL_TYPE.SYNC_IN_APPS));
        } catch (Exception e) {
            Logger.f("APIManager: fetchInAppCampaigns", e);
        }
    }

    public void showInAppMessage(View view, InAppMessage inAppMessage, boolean z) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                Logger.f("InAppManager: showInAppMessage: current activity instance is null");
                return;
            }
            if (inAppMessage != null) {
                if (view != null) {
                    this.currentInAppMessage = inAppMessage;
                    if ((z && currentActivity.getClass().getName().equals(InAppController.getInstance().getActivityName())) || (canShowInAppMessage(inAppMessage.rules, System.currentTimeMillis() / 1000, currentActivity.getClass().getName()) && inAppMessage.rules.alignType != ALIGN_TYPE.EMBED)) {
                        addInAppToViewHierarchy(view, inAppMessage, currentActivity);
                        if (this.showingInAppMessage.get() && !z) {
                            trackInAppShown(currentActivity.getApplicationContext(), inAppMessage);
                        }
                    }
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InAppManager: showInAppMessage Campaign Id ");
            stringBuilder.append(inAppMessage.rules.campaignId);
            Logger.f(stringBuilder.toString(), e);
        }
    }

    private void addInAppToViewHierarchy(final View view, final InAppMessage inAppMessage, final Activity activity) {
        if (!ConfigurationProvider.getInstance(activity.getApplicationContext()).hasOptedOutNavBar()) {
            hideStatusBar(activity);
        }
        setInAppShowingState(true);
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView().findViewById(16908290).getRootView();
                frameLayout.addView(view);
                InAppManager.this.lastInAppShownAt = System.currentTimeMillis();
                ConfigurationProvider.getInstance(activity.getApplicationContext()).setLastInAppShownTime(InAppManager.this.lastInAppShownAt);
                inAppMessage.rules.lastShown = InAppManager.this.lastInAppShownAt;
                if (inAppMessage.rules.autoDismiss > 0) {
                    InAppManager.this.autoDismissHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (frameLayout.indexOfChild(view) == -1) {
                                Logger.v("showInAppMessage : in-app was closed before being  autodismissed");
                            } else {
                                InAppTracker.getInstance(activity.getApplicationContext()).trackInAppAutoDismissed(inAppMessage.rules.campaignId);
                                if (inAppMessage.rules.exitAnimation != 0) {
                                    view.setAnimation(AnimationUtils.loadAnimation(activity.getApplicationContext(), inAppMessage.rules.exitAnimation));
                                }
                                frameLayout.removeView(view);
                            }
                            InAppManager.this.handleDismiss();
                        }
                    }, inAppMessage.rules.autoDismiss * 1000);
                }
                if (InAppManager.this.inAppMessageListener != null) {
                    InAppManager.this.inAppMessageListener.onInAppShown(inAppMessage);
                }
            }
        });
    }

    /* Access modifiers changed, original: 0000 */
    public boolean inappTimeCheck() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.lastInAppShownAt + this.minDelayBetweenInApps <= currentTimeMillis) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("InAppManager: inappTimeCheck: an inapp was shown recently at ");
        stringBuilder.append(this.lastInAppShownAt);
        stringBuilder.append("ms cannot show it now. Have to wait for ");
        stringBuilder.append((currentTimeMillis - this.lastInAppShownAt) - this.minDelayBetweenInApps);
        stringBuilder.append("ms");
        Logger.v(stringBuilder.toString());
        MoEInAppFailureLogger.getInstance().updateCommonErrorCounter(MoEInAppFailureLogger.FAILURE_REASON_GLOBAL_DELAY);
        return false;
    }

    public void setInAppShowingState(boolean z) {
        this.showingInAppMessage.set(z);
    }

    public void handleDismiss() {
        setInAppShowingState(false);
        if (this.inAppMessageListener != null) {
            this.inAppMessageListener.onInAppClosed(this.currentInAppMessage);
        }
        this.currentInAppMessage = null;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null && !ConfigurationProvider.getInstance(currentActivity).hasOptedOutNavBar()) {
            showStatusBar(currentActivity);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0070  */
    @android.support.annotation.Nullable
    public com.moengage.inapp.InAppMessage checkAndReturnInApp(android.content.Context r11, java.lang.String r12) {
        /*
        r10 = this;
        r0 = r10.inAppRulesCache;
        r0 = r0.getValue();
        r0 = r0.iterator();
    L_0x000a:
        r1 = r0.hasNext();
        r2 = 0;
        if (r1 == 0) goto L_0x0023;
    L_0x0011:
        r1 = r0.next();
        r1 = (com.moengage.inapp.InAppMessage.Rules) r1;
        r3 = r1.campaignId;
        r3 = r3.equals(r12);
        if (r3 == 0) goto L_0x000a;
    L_0x001f:
        r12 = 1;
        r0 = r1._id;
        goto L_0x0026;
    L_0x0023:
        r0 = 0;
        r12 = r2;
    L_0x0026:
        r3 = 0;
        if (r12 == 0) goto L_0x0074;
    L_0x0029:
        r12 = "InAppManager : checkAndReturnInApp : in-app for given campaign id found";
        com.moengage.core.Logger.v(r12);
        r4 = r11.getContentResolver();	 Catch:{ all -> 0x006d }
        r12 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity.getContentUri(r11);	 Catch:{ all -> 0x006d }
        r12 = r12.buildUpon();	 Catch:{ all -> 0x006d }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x006d }
        r12 = r12.appendPath(r0);	 Catch:{ all -> 0x006d }
        r5 = r12.build();	 Catch:{ all -> 0x006d }
        r6 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity.PROJECTION;	 Catch:{ all -> 0x006d }
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r12 = r4.query(r5, r6, r7, r8, r9);	 Catch:{ all -> 0x006d }
        if (r12 == 0) goto L_0x0067;
    L_0x0051:
        r0 = r12.getCount();	 Catch:{ all -> 0x0064 }
        if (r0 <= 0) goto L_0x0067;
    L_0x0057:
        r12.moveToFirst();	 Catch:{ all -> 0x0064 }
        r11 = com.moengage.inapp.InAppsDAO.getInstance(r11);	 Catch:{ all -> 0x0064 }
        r11 = r11.marshalInApp(r12, r2);	 Catch:{ all -> 0x0064 }
        r3 = r11;
        goto L_0x0067;
    L_0x0064:
        r11 = move-exception;
        r3 = r12;
        goto L_0x006e;
    L_0x0067:
        if (r12 == 0) goto L_0x0074;
    L_0x0069:
        r12.close();
        goto L_0x0074;
    L_0x006d:
        r11 = move-exception;
    L_0x006e:
        if (r3 == 0) goto L_0x0073;
    L_0x0070:
        r3.close();
    L_0x0073:
        throw r11;
    L_0x0074:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.InAppManager.checkAndReturnInApp(android.content.Context, java.lang.String):com.moengage.inapp.InAppMessage");
    }

    public InAppMessage getCurrentInApp() {
        return this.currentInAppMessage;
    }

    public void hideStatusBar(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                if (VERSION.SDK_INT < 16) {
                    activity.getWindow().setFlags(1024, 1024);
                } else {
                    activity.getWindow().getDecorView().setSystemUiVisibility(4);
                }
            }
        });
    }

    public void showStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        if (VERSION.SDK_INT < 16) {
            activity.getWindow().clearFlags(1024);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    private void getNonInAppActivityList(Context context) {
        if (this.nonInAppActivityList.isEmpty() && context != null) {
            try {
                ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), TsExtractor.TS_STREAM_TYPE_AC3).activities;
                if (activityInfoArr != null) {
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        Bundle bundle = activityInfo.metaData;
                        if (!(bundle == null || !bundle.containsKey(META_DATA_SHOW_INAPP) || bundle.getBoolean(META_DATA_SHOW_INAPP))) {
                            this.nonInAppActivityList.add(activityInfo.name);
                        }
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("InAppManager#getNonInAppActivityList ");
                stringBuilder.append(this.nonInAppActivityList.toString());
                Logger.v(stringBuilder.toString());
            } catch (Exception e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("InAppManager#getNonInAppActivityList ");
                stringBuilder2.append(e.getMessage());
                Logger.f(stringBuilder2.toString());
            }
        }
    }

    public void setInappsSynced(boolean z) {
        this.inappsSynced = z;
    }

    public void optOutNavBar(Context context, boolean z) {
        ConfigurationProvider.getInstance(context).setNavBarOptOut(true);
    }
}
