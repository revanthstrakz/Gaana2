package com.moengage.core;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.moe.pushlibrary.models.BatchData;
import com.moe.pushlibrary.models.Event;
import com.moe.pushlibrary.models.UserAttribute;
import com.moe.pushlibrary.providers.MoEDataContract;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.moe.pushlibrary.providers.MoEDataContract.BatchDataEntity;
import com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity;
import com.moe.pushlibrary.providers.MoEDataContract.DatapointEntity;
import com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity;
import com.moe.pushlibrary.providers.MoEDataContract.MessageEntity;
import com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity;
import java.util.ArrayList;
import java.util.Iterator;

public final class MoEDAO {
    private static MoEDAO _INSTANCE;
    private String AUTHORITY = null;
    private Uri BATCHED_DATA_URI = null;
    private Uri CAMPAIGN_LIST_URI = null;
    private Uri DATAPOINTS_CONTENT_URI = null;
    private Uri INAPP_CONTENT_URI = null;
    private Uri MESSAGES_CONTENT_URI = null;
    private Uri USER_ATTRIBUTES_URI = null;
    private Context mContext;

    public static MoEDAO getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new MoEDAO(context);
        }
        return _INSTANCE;
    }

    private MoEDAO(Context context) {
        this.MESSAGES_CONTENT_URI = MessageEntity.getContentUri(context);
        this.INAPP_CONTENT_URI = InAppMessageEntity.getContentUri(context);
        this.DATAPOINTS_CONTENT_URI = DatapointEntity.getContentUri(context);
        this.USER_ATTRIBUTES_URI = UserAttributeEntity.getContentUri(context);
        this.CAMPAIGN_LIST_URI = CampaignListEntity.getContentUri(context);
        this.BATCHED_DATA_URI = BatchDataEntity.getContentUri(context);
        this.AUTHORITY = MoEDataContract.getAuthority(context);
        this.mContext = context;
    }

    /* Access modifiers changed, original: 0000 */
    public int getUnreadMessageCount() {
        String[] strArr = new String[1];
        int i = 0;
        strArr[0] = "0";
        Cursor query = this.mContext.getContentResolver().query(this.MESSAGES_CONTENT_URI, MessageEntity.PROJECTION, "msgclicked = ?", strArr, "gtime DESC");
        if (query != null) {
            i = query.getCount();
            closeCursor(query);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Getting Unread PromotionalMessage Count: count=");
        stringBuilder.append(i);
        Logger.v(stringBuilder.toString());
        return i;
    }

    /* Access modifiers changed, original: 0000 */
    public void addEvent(Event event, Context context) {
        if (event == null) {
            Logger.v("Null event passed, skipping it");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event : ");
        stringBuilder.append(event.details);
        Logger.v(stringBuilder.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseColumns.GTIME, Long.valueOf(event.gtime));
        contentValues.put(DatapointColumns.DETAILS, event.details);
        Uri insert = context.getContentResolver().insert(this.DATAPOINTS_CONTENT_URI, contentValues);
        if (insert != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("New Event added with Uri: ");
            stringBuilder2.append(insert.toString());
            Logger.v(stringBuilder2.toString());
        } else {
            Logger.v("Unable to add event");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ArrayList<Event> getInteractionData(int i) {
        Cursor query = this.mContext.getContentResolver().query(this.DATAPOINTS_CONTENT_URI.buildUpon().appendQueryParameter(MoEDataContract.QUERY_PARAMETER_LIMIT, String.valueOf(i)).build(), DatapointEntity.PROJECTION, null, null, "gtime ASC");
        if (query == null || query.getCount() == 0) {
            Logger.v("Empty cursor");
            closeCursor(query);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            arrayList.add(new Event(query.getInt(0), query.getString(2)));
        }
        closeCursor(query);
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    public void deleteInteractionData(ArrayList<Event> arrayList, Context context) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Event event = (Event) it.next();
            arrayList2.add(ContentProviderOperation.newDelete(this.DATAPOINTS_CONTENT_URI).withSelection("_id = ?", new String[]{String.valueOf(event._id)}).build());
        }
        try {
            context.getContentResolver().applyBatch(this.AUTHORITY, arrayList2);
        } catch (RemoteException e) {
            Logger.f("MoEDAO: deleteInteractionData", e);
        } catch (OperationApplicationException e2) {
            Logger.f("MoEDAO: deleteInteractionData", e2);
        } catch (Exception e3) {
            Logger.f("MoEDAO: deleteInteractionData", e3);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Cursor getMessages(Context context) {
        return context.getContentResolver().query(this.MESSAGES_CONTENT_URI, MessageEntity.PROJECTION, null, null, "gtime DESC");
    }

    /* Access modifiers changed, original: 0000 */
    public boolean setMessageClicked(long j) {
        Uri build = this.MESSAGES_CONTENT_URI.buildUpon().appendPath(String.valueOf(j)).build();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageColumns.MSG_CLICKED, Integer.valueOf(1));
        int update = this.mContext.getContentResolver().update(build, contentValues, null, null);
        this.mContext.getContentResolver().notifyChange(build, null);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public void removeExpiredData() {
        String l = Long.toString(System.currentTimeMillis());
        int delete = this.mContext.getContentResolver().delete(this.INAPP_CONTENT_URI, "ttl < ? AND status = ?", new String[]{Long.toString(System.currentTimeMillis() / 1000), InAppConstants.INAPP_CAMPAIGN_STATUS_EXPIRED});
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MoEDAO:removeExpiredData: Number of IN APP records deleted: ");
        stringBuilder.append(delete);
        Logger.v(stringBuilder.toString());
        delete = this.mContext.getContentResolver().delete(this.MESSAGES_CONTENT_URI, "msgttl < ?", new String[]{l});
        stringBuilder = new StringBuilder();
        stringBuilder.append("MoEDAO:removeExpiredData: Number of PromotionalMessage records deleted: ");
        stringBuilder.append(delete);
        Logger.v(stringBuilder.toString());
        int delete2 = this.mContext.getContentResolver().delete(this.CAMPAIGN_LIST_URI, "ttl < ?", new String[]{l});
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("MoEDAO:removeExpiredData: Number of CampaignList records deleted: ");
        stringBuilder2.append(delete2);
        Logger.v(stringBuilder2.toString());
        this.mContext.getContentResolver().notifyChange(this.INAPP_CONTENT_URI, null);
        this.mContext.getContentResolver().notifyChange(this.MESSAGES_CONTENT_URI, null);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean setMessageClickedByTime(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageColumns.MSG_CLICKED, Integer.valueOf(1));
        int update = this.mContext.getContentResolver().update(this.MESSAGES_CONTENT_URI, contentValues, "gtime = ? ", new String[]{String.valueOf(j)});
        this.mContext.getContentResolver().notifyChange(this.MESSAGES_CONTENT_URI, null);
        if (update > 0) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    public void addOrUpdateUserAttribute(@android.support.annotation.NonNull com.moe.pushlibrary.models.UserAttribute r10) {
        /*
        r9 = this;
        if (r10 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "User Attribute -->";
        r0.append(r1);
        r1 = r10.userAttributeName;
        r0.append(r1);
        r1 = ":";
        r0.append(r1);
        r1 = r10.userAttributeValue;
        r0.append(r1);
        r0 = r0.toString();
        com.moengage.core.Logger.v(r0);
        r0 = new android.content.ContentValues;
        r0.<init>();
        r1 = "attribute_name";
        r2 = r10.userAttributeName;
        r0.put(r1, r2);
        r1 = "attribute_value";
        r2 = r10.userAttributeValue;
        r0.put(r1, r2);
        r1 = 0;
        r2 = r9.mContext;	 Catch:{ Exception -> 0x006d }
        r3 = r2.getContentResolver();	 Catch:{ Exception -> 0x006d }
        r4 = r9.USER_ATTRIBUTES_URI;	 Catch:{ Exception -> 0x006d }
        r5 = com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity.PROJECTION;	 Catch:{ Exception -> 0x006d }
        r6 = "attribute_name=?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x006d }
        r2 = 0;
        r8 = r10.userAttributeName;	 Catch:{ Exception -> 0x006d }
        r7[r2] = r8;	 Catch:{ Exception -> 0x006d }
        r8 = 0;
        r2 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x006d }
        if (r2 == 0) goto L_0x0062;
    L_0x0052:
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x005f, all -> 0x005c }
        if (r1 == 0) goto L_0x0062;
    L_0x0058:
        r9.updateUserAttribute(r10, r0);	 Catch:{ Exception -> 0x005f, all -> 0x005c }
        goto L_0x0065;
    L_0x005c:
        r10 = move-exception;
        r1 = r2;
        goto L_0x0079;
    L_0x005f:
        r10 = move-exception;
        r1 = r2;
        goto L_0x006e;
    L_0x0062:
        r9.addUserAttribute(r0);	 Catch:{ Exception -> 0x005f, all -> 0x005c }
    L_0x0065:
        if (r2 == 0) goto L_0x0078;
    L_0x0067:
        r2.close();
        goto L_0x0078;
    L_0x006b:
        r10 = move-exception;
        goto L_0x0079;
    L_0x006d:
        r10 = move-exception;
    L_0x006e:
        r0 = "MoEDAO: addOrUpdateUserAttribute()";
        com.moengage.core.Logger.e(r0, r10);	 Catch:{ all -> 0x006b }
        if (r1 == 0) goto L_0x0078;
    L_0x0075:
        r1.close();
    L_0x0078:
        return;
    L_0x0079:
        if (r1 == 0) goto L_0x007e;
    L_0x007b:
        r1.close();
    L_0x007e:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moengage.core.MoEDAO.addOrUpdateUserAttribute(com.moe.pushlibrary.models.UserAttribute):void");
    }

    private void addUserAttribute(ContentValues contentValues) {
        Uri insert = this.mContext.getContentResolver().insert(this.USER_ATTRIBUTES_URI, contentValues);
        if (insert != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("New user attribute added with Uri: ");
            stringBuilder.append(insert.toString());
            Logger.v(stringBuilder.toString());
            return;
        }
        Logger.v("Unable to user attribute");
    }

    private void updateUserAttribute(@NonNull UserAttribute userAttribute, ContentValues contentValues) {
        int update = this.mContext.getContentResolver().update(this.USER_ATTRIBUTES_URI, contentValues, "attribute_name=?", new String[]{userAttribute.userAttributeName});
        if (update > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("New user attribute updated, count: ");
            stringBuilder.append(update);
            Logger.v(stringBuilder.toString());
            return;
        }
        Logger.v("Unable to user attribute");
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public UserAttribute getUserAttributesForKey(@NonNull String str) {
        Throwable th;
        Cursor cursor = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            UserAttribute userAttribute;
            Cursor query = this.mContext.getContentResolver().query(this.USER_ATTRIBUTES_URI, UserAttributeEntity.PROJECTION, "attribute_name=?", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        userAttribute = new UserAttribute();
                        userAttribute.userAttributeName = query.getString(1);
                        userAttribute.userAttributeValue = query.getString(2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    closeCursor(cursor);
                    throw th;
                }
            }
            closeCursor(query);
            return userAttribute;
        } catch (Throwable th3) {
            th = th3;
            closeCursor(cursor);
            throw th;
        }
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public ArrayList<BatchData> getBatchedData(int i) {
        Cursor query = this.mContext.getContentResolver().query(this.BATCHED_DATA_URI.buildUpon().appendQueryParameter(MoEDataContract.QUERY_PARAMETER_LIMIT, String.valueOf(i)).build(), BatchDataEntity.PROJECTION, null, null, null);
        if (query == null || query.getCount() == 0) {
            Logger.v("Empty cursor");
            closeCursor(query);
            return null;
        }
        ArrayList arrayList = new ArrayList(query.getCount());
        if (query.moveToFirst()) {
            do {
                arrayList.add(new BatchData(query.getLong(query.getColumnIndex(BaseColumns._ID)), query.getString(query.getColumnIndex(BatchDataColumns.BATCHED_DATA))));
            } while (query.moveToNext());
        }
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    public void writeBatch(@NonNull String str) {
        if (str != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(BatchDataColumns.BATCHED_DATA, str);
            Uri insert = this.mContext.getContentResolver().insert(this.BATCHED_DATA_URI, contentValues);
            if (insert != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("MoEDAO: writeBatch() New batch added : uri ");
                stringBuilder.append(insert.toString());
                Logger.v(stringBuilder.toString());
            } else {
                Logger.f("MoEDAO: writeBatch() unable to add batch");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void deleteBatch(BatchData batchData) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newDelete(this.BATCHED_DATA_URI).withSelection("_id = ?", new String[]{String.valueOf(batchData._id)}).build());
        try {
            this.mContext.getContentResolver().applyBatch(this.AUTHORITY, arrayList);
        } catch (RemoteException e) {
            Logger.f("MoEDAO: deleteInteractionData", e);
        } catch (OperationApplicationException e2) {
            Logger.f("MoEDAO: deleteInteractionData", e2);
        } catch (Exception e3) {
            Logger.f("MoEDAO: deleteInteractionData", e3);
        }
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
