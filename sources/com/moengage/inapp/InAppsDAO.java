package com.moengage.inapp;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moe.pushlibrary.providers.MoEDataContract;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity;
import com.moengage.core.Logger;
import com.moengage.inapp.InAppMessage.Rules;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

final class InAppsDAO {
    private static InAppsDAO _INSTANCE;
    private String AUTHORITY = null;
    private Uri INAPP_CONTENT_URI = null;
    private Context mContext;

    private InAppsDAO(Context context) {
        this.mContext = context;
        this.INAPP_CONTENT_URI = InAppMessageEntity.getContentUri(context);
        this.AUTHORITY = MoEDataContract.getAuthority(context);
    }

    public static InAppsDAO getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new InAppsDAO(context);
        }
        return _INSTANCE;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean updateInApp(InAppMessage inAppMessage) {
        Uri build = this.INAPP_CONTENT_URI.buildUpon().appendPath(String.valueOf(inAppMessage.rules._id)).build();
        int update = this.mContext.getContentResolver().update(build, getContentValues(inAppMessage), null, null);
        this.mContext.getContentResolver().notifyChange(build, null);
        return update > 0;
    }

    static ContentValues getContentValues(InAppMessage inAppMessage) {
        if (inAppMessage == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        if (inAppMessage.rules._id != 0) {
            contentValues.put(BaseColumns._ID, Long.valueOf(inAppMessage.rules._id));
        }
        contentValues.put("campaign_id", inAppMessage.rules.campaignId);
        contentValues.put("auto_dismiss", Long.valueOf(inAppMessage.rules.autoDismiss));
        contentValues.put(InAppMessageColumns.MSG_CANCELABLE, Integer.valueOf(inAppMessage.rules.cancelable));
        if (inAppMessage.rules.context != null) {
            contentValues.put("context", inAppMessage.rules.context);
        }
        contentValues.put("max_times", Integer.valueOf(inAppMessage.rules.maxTimes));
        contentValues.put(InAppMessageColumns.MSG_MIN_DELAY, Long.valueOf(inAppMessage.rules.minmumDelay));
        contentValues.put("persistent", Integer.valueOf(inAppMessage.rules.persistent));
        contentValues.put("priority", Integer.valueOf(inAppMessage.rules.priority));
        contentValues.put(InAppMessageColumns.MSG_SHOW_ONLY_IN, inAppMessage.rules.showOnlyIn);
        contentValues.put("ttl", Long.valueOf(inAppMessage.rules.ttl));
        contentValues.put(InAppMessageColumns.MSG_INAPP_TYPE, inAppMessage.getInAppType());
        contentValues.put(InAppMessageColumns.MSG_ALIGN_TYPE, inAppMessage.getAlignType());
        contentValues.put("content", inAppMessage.content);
        contentValues.put("status", inAppMessage.status);
        contentValues.put(InAppMessageColumns.MSG_CONTAINER_STYLE, inAppMessage.dimStyle);
        contentValues.put(BaseColumns.GTIME, Long.valueOf(inAppMessage.rules.lastUpdatedTime));
        return contentValues;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean addInAppMsg(InAppMessage inAppMessage) {
        if (inAppMessage == null) {
            Logger.f("InAppMessage is null or param is null cannot add it");
            return false;
        }
        Uri insert = this.mContext.getContentResolver().insert(this.INAPP_CONTENT_URI, getContentValues(inAppMessage));
        if (insert == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("New InApp PromotionalMessage added: ");
        stringBuilder.append(insert.toString());
        Logger.v(stringBuilder.toString());
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public ArrayList<InAppMessage> getAllStoredInApps() {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = this.mContext.getContentResolver().query(this.INAPP_CONTENT_URI, InAppMessageEntity.PROJECTION, null, null, InAppMessageColumns.DEFAULT_SORT_ORDER);
            if (query != null) {
                if (query.getCount() != 0) {
                    while (query.moveToNext()) {
                        InAppMessage marshalInApp = marshalInApp(query, false);
                        if (marshalInApp != null) {
                            arrayList.add(marshalInApp);
                        }
                    }
                    query.close();
                    return arrayList;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e) {
            Logger.f("getAllLocalInApps", e);
        }
    }

    public LinkedHashSet<Rules> getActiveInAppCampaigns() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            Cursor query = this.mContext.getContentResolver().query(this.INAPP_CONTENT_URI, InAppMessageEntity.PROJECTION, "status = ? AND ttl > ? AND has_errors != 1", new String[]{"active", String.valueOf(System.currentTimeMillis() / 1000)}, null);
            if (query != null) {
                if (query.getCount() != 0) {
                    while (query.moveToNext()) {
                        InAppMessage marshalInApp = marshalInApp(query, true);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("InAppDAO: getActiveInAppCampaigns: found active campaign: ");
                        stringBuilder.append(marshalInApp.rules.campaignId);
                        Logger.v(stringBuilder.toString());
                        linkedHashSet.add(marshalInApp.rules);
                    }
                    query.close();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("MoEDAO: Found ");
                    stringBuilder2.append(linkedHashSet.size());
                    stringBuilder2.append(" active campaigns.");
                    Logger.v(stringBuilder2.toString());
                    return linkedHashSet;
                }
            }
            if (query != null) {
                query.close();
            }
            Logger.v("MoEDAO: getActiveInAppCampaigns: nothing found");
            return null;
        } catch (Exception e) {
            Logger.f("getAllLocalInApps", e);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public InAppMessage marshalInApp(Cursor cursor, boolean z) {
        InAppMessage inAppMessage = new InAppMessage();
        boolean z2 = false;
        inAppMessage.rules._id = (long) cursor.getInt(0);
        inAppMessage.rules.campaignId = cursor.getString(2);
        inAppMessage.rules.shownCount = cursor.getInt(8);
        inAppMessage.rules.autoDismiss = (long) cursor.getInt(15);
        inAppMessage.rules.cancelable = cursor.getInt(16) == 1;
        inAppMessage.rules.context = cursor.getString(11);
        inAppMessage.rules.isClicked = cursor.getInt(13) == 1;
        inAppMessage.rules.lastShown = cursor.getLong(12);
        inAppMessage.rules.maxTimes = cursor.getInt(7);
        inAppMessage.rules.minmumDelay = (long) cursor.getInt(6);
        Rules rules = inAppMessage.rules;
        if (cursor.getInt(9) == 1) {
            z2 = true;
        }
        rules.persistent = z2;
        inAppMessage.rules.priority = cursor.getInt(10);
        inAppMessage.rules.showOnlyIn = cursor.getString(18);
        inAppMessage.rules.ttl = (long) cursor.getInt(5);
        inAppMessage.setInAppType(cursor.getString(4));
        inAppMessage.setAlignType(cursor.getString(3));
        inAppMessage.dimStyle = cursor.getString(20);
        if (!z) {
            inAppMessage.content = cursor.getString(17);
        }
        inAppMessage.status = cursor.getString(19);
        inAppMessage.rules.isActive = true;
        return inAppMessage;
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<String> getInAppCampaignList() {
        try {
            Cursor query = this.mContext.getContentResolver().query(this.INAPP_CONTENT_URI, new String[]{"status", "campaign_id"}, null, null, null);
            if (query != null) {
                if (query.getCount() != 0) {
                    ArrayList arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        String string = query.getString(0);
                        if ("active".equals(string) || InAppConstants.INAPP_CAMPAIGN_STATUS_PAUSED.equals(string)) {
                            string = query.getString(1);
                            if (!TextUtils.isEmpty(string)) {
                                arrayList.add(string);
                            }
                        }
                    }
                    query.close();
                    return arrayList;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e) {
            Logger.f("MoEDAO: getInAppCampaignList", e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateInAppClicked(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InAppMessageColumns.MSG_IS_CLICKED, Integer.valueOf(1));
        if (this.mContext.getContentResolver().update(InAppMessageEntity.getContentUri(this.mContext), contentValues, "campaign_id = ?", new String[]{str}) <= 0) {
            Logger.f("InAppsDAO#updateInAppClicked :DB update failed");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    public void updateInAppShownCount(java.lang.String r10) {
        /*
        r9 = this;
        r0 = 0;
        r1 = r9.mContext;	 Catch:{ all -> 0x0069 }
        r2 = r1.getContentResolver();	 Catch:{ all -> 0x0069 }
        r1 = r9.mContext;	 Catch:{ all -> 0x0069 }
        r3 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity.getContentUri(r1);	 Catch:{ all -> 0x0069 }
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ all -> 0x0069 }
        r5 = "shown_count";
        r8 = 0;
        r4[r8] = r5;	 Catch:{ all -> 0x0069 }
        r5 = "campaign_id = ?";
        r6 = new java.lang.String[r1];	 Catch:{ all -> 0x0069 }
        r6[r8] = r10;	 Catch:{ all -> 0x0069 }
        r7 = 0;
        r2 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0069 }
        if (r2 == 0) goto L_0x0036;
    L_0x0022:
        r0 = r2.moveToFirst();	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0036;
    L_0x0028:
        r0 = "shown_count";
        r0 = r2.getColumnIndex(r0);	 Catch:{ all -> 0x0033 }
        r0 = r2.getInt(r0);	 Catch:{ all -> 0x0033 }
        goto L_0x0037;
    L_0x0033:
        r10 = move-exception;
        r0 = r2;
        goto L_0x006a;
    L_0x0036:
        r0 = r8;
    L_0x0037:
        if (r2 == 0) goto L_0x003c;
    L_0x0039:
        r2.close();
    L_0x003c:
        r0 = r0 + r1;
        r2 = new android.content.ContentValues;
        r2.<init>();
        r3 = "shown_count";
        r0 = java.lang.Integer.valueOf(r0);
        r2.put(r3, r0);
        r0 = r9.mContext;
        r0 = r0.getContentResolver();
        r3 = r9.mContext;
        r3 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity.getContentUri(r3);
        r4 = "campaign_id = ?";
        r1 = new java.lang.String[r1];
        r1[r8] = r10;
        r10 = r0.update(r3, r2, r4, r1);
        if (r10 > 0) goto L_0x0068;
    L_0x0063:
        r10 = "InApps#updateInAppShownCount : DAODB update failed";
        com.moengage.core.Logger.f(r10);
    L_0x0068:
        return;
    L_0x0069:
        r10 = move-exception;
    L_0x006a:
        if (r0 == 0) goto L_0x006f;
    L_0x006c:
        r0.close();
    L_0x006f:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.inapp.InAppsDAO.updateInAppShownCount(java.lang.String):void");
    }

    /* Access modifiers changed, original: 0000 */
    public void updateInAppShown(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InAppMessageColumns.MSG_LAST_SHOWN, Long.valueOf(j));
        if (this.mContext.getContentResolver().update(InAppMessageEntity.getContentUri(this.mContext), contentValues, "campaign_id = ?", new String[]{str}) <= 0) {
            Logger.f("InAppsDAO#updateInAppShown DB update failed");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void addOrUpdateInApps(ArrayList<InAppMessage> arrayList) {
        Logger.v("MoEDAO: addOrUpdateInApps: add or update inapp data");
        try {
            ArrayList allStoredInApps = getAllStoredInApps();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator it;
            if (allStoredInApps == null) {
                it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(ContentProviderOperation.newInsert(this.INAPP_CONTENT_URI).withValues(getContentValues((InAppMessage) it.next())).build());
                }
            } else {
                it = arrayList.iterator();
                while (it.hasNext()) {
                    InAppMessage inAppMessage = (InAppMessage) it.next();
                    Object obj = null;
                    Iterator it2 = allStoredInApps.iterator();
                    while (it2.hasNext()) {
                        InAppMessage inAppMessage2 = (InAppMessage) it2.next();
                        if (inAppMessage2.rules.campaignId.equals(inAppMessage.rules.campaignId)) {
                            inAppMessage.rules._id = inAppMessage2.rules._id;
                            inAppMessage.rules.isClicked = inAppMessage2.rules.isClicked;
                            inAppMessage.rules.shownCount = inAppMessage2.rules.shownCount;
                            inAppMessage.rules.lastShown = inAppMessage2.rules.lastShown;
                            obj = 1;
                            break;
                        }
                    }
                    StringBuilder stringBuilder;
                    if (obj != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Will update campaign: ");
                        stringBuilder.append(inAppMessage.rules.campaignId);
                        stringBuilder.append(" dump: ");
                        Logger.v(stringBuilder.toString());
                        inAppMessage.dump();
                        arrayList3.add(ContentProviderOperation.newUpdate(this.INAPP_CONTENT_URI.buildUpon().appendPath(String.valueOf(inAppMessage.rules._id)).build()).withValues(getContentValues(inAppMessage)).build());
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Will add campaign: ");
                        stringBuilder.append(inAppMessage.rules.campaignId);
                        stringBuilder.append(" dump: ");
                        Logger.v(stringBuilder.toString());
                        inAppMessage.dump();
                        arrayList2.add(ContentProviderOperation.newInsert(this.INAPP_CONTENT_URI).withValues(getContentValues(inAppMessage)).build());
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                this.mContext.getContentResolver().applyBatch(this.AUTHORITY, arrayList3);
            }
            if (!arrayList2.isEmpty()) {
                this.mContext.getContentResolver().applyBatch(this.AUTHORITY, arrayList2);
            }
        } catch (RemoteException e) {
            Logger.f("MoEDAO: addInAppMsgs: ApplyBatch", e);
        } catch (OperationApplicationException e2) {
            Logger.f("MoEDAO: addInAppMsgs: ApplyBatch", e2);
        } catch (Exception e3) {
            Logger.f("MoEDAO: addInAppMsgs: ApplyBatch", e3);
        }
    }
}
