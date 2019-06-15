package com.moe.pushlibrary.providers;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.moe.pushlibrary.providers.MoEDataContract.BatchDataEntity;
import com.moe.pushlibrary.providers.MoEDataContract.CampaignListEntity;
import com.moe.pushlibrary.providers.MoEDataContract.DatapointEntity;
import com.moe.pushlibrary.providers.MoEDataContract.MessageEntity;
import com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity;
import com.moe.pushlibrary.utils.MoEHelperUtils;
import com.moengage.core.ConfigurationProvider;
import com.moengage.core.Logger;
import com.moengage.core.MoEConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class MoEProvider extends ContentProvider {
    private static final int CAMPAIGN_ID = 12;
    private static final int CAMPAIGN_IDS = 11;
    private static final int DATAPOINTS = 3;
    private static final int DATAPOINT_ID = 4;
    private static final int DATA_BATCH = 14;
    private static final int DATA_BATCHES = 13;
    private static final int INAPPS = 5;
    private static final int INAPP_ID = 6;
    private static final int MESSAGES = 1;
    private static final int MESSAGE_ID = 2;
    private static final int USER_ATTRIBUTES = 9;
    private static final int USER_ATTRIBUTES_ID = 10;
    private static HashMap<String, String> sBatchDataProjectionMap = new HashMap();
    private static HashMap<String, String> sCampaignListProjectionMap = new HashMap();
    private static HashMap<String, String> sEventProjectionMap = new HashMap();
    private static HashMap<String, String> sInAppProjectionMap = new HashMap();
    private static HashMap<String, String> sMessageProjectionMap = new HashMap();
    private static UriMatcher sUriMatcher;
    private static HashMap<String, String> sUserAttributeProjectionMap = new HashMap();
    private DatabaseHelper mOpenHelper = null;

    class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "MOEInteractions";
        static final int DATABASE_VERSION = 12;
        static final String TABLE_NAME_BATCH_DATA = "BATCH_DATA";
        static final String TABLE_NAME_CAMPAIGN_LIST = "CAMPAIGNLIST";
        static final String TABLE_NAME_DATAPOINTS = "DATAPOINTS";
        static final String TABLE_NAME_INAPPS = "INAPPMSG";
        static final String TABLE_NAME_MSGS = "MESSAGES";
        static final String TABLE_NAME_OLD_INAPPS = "INAPPS";
        static final String TABLE_NAME_UINBOX = "UINBOX";
        static final String TABLE_NAME_USER_ATTRIBUTES = "USERATTRIBUTES";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 12);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            createInAppTable(sQLiteDatabase);
            createUserAttributeTable(sQLiteDatabase);
            createCampaignListTable(sQLiteDatabase);
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MESSAGES ( _id INTEGER PRIMARY KEY, msg TEXT, msgclicked INTEGER DEFAULT 0, msgttl INTEGER, gtime INTEGER, msg_tag TEXT); ");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS DATAPOINTS ( _id INTEGER PRIMARY KEY, gtime INTEGER, details TEXT ); ");
            createBatchDataTable(sQLiteDatabase);
            Logger.i("MoEProvider: Database created");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0032  */
        private boolean tableExists(android.database.sqlite.SQLiteDatabase r4, java.lang.String r5) {
            /*
            r3 = this;
            r0 = 0;
            r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002f }
            r1.<init>();	 Catch:{ all -> 0x002f }
            r2 = "select DISTINCT tbl_name from sqlite_master where tbl_name = '";
            r1.append(r2);	 Catch:{ all -> 0x002f }
            r1.append(r5);	 Catch:{ all -> 0x002f }
            r5 = "'";
            r1.append(r5);	 Catch:{ all -> 0x002f }
            r5 = r1.toString();	 Catch:{ all -> 0x002f }
            r4 = r4.rawQuery(r5, r0);	 Catch:{ all -> 0x002f }
            if (r4 == 0) goto L_0x0028;
        L_0x001d:
            r5 = r4.getCount();	 Catch:{ all -> 0x0025 }
            if (r5 <= 0) goto L_0x0028;
        L_0x0023:
            r5 = 1;
            goto L_0x0029;
        L_0x0025:
            r5 = move-exception;
            r0 = r4;
            goto L_0x0030;
        L_0x0028:
            r5 = 0;
        L_0x0029:
            if (r4 == 0) goto L_0x002e;
        L_0x002b:
            r4.close();
        L_0x002e:
            return r5;
        L_0x002f:
            r5 = move-exception;
        L_0x0030:
            if (r0 == 0) goto L_0x0035;
        L_0x0032:
            r0.close();
        L_0x0035:
            throw r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.moe.pushlibrary.providers.MoEProvider$DatabaseHelper.tableExists(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Logger.v("MoEProvider: Provider upgrading DB ");
            for (int i3 = i + 1; i3 <= i2; i3++) {
                switch (i3) {
                    case 3:
                        sQLiteDatabase.beginTransaction();
                        try {
                            createDataPointsTable(sQLiteDatabase);
                            createMessagesTable(sQLiteDatabase);
                            createInAppTable(sQLiteDatabase);
                            MoEProvider.this.portDataFromv2(sQLiteDatabase);
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS moeints");
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS moemsgs");
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS moeinappmsgs");
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Exception e) {
                            Logger.f("MoEProvider: failed to port data. FROM V2.", e);
                        } catch (Throwable th) {
                            sQLiteDatabase.endTransaction();
                        }
                        sQLiteDatabase.endTransaction();
                        break;
                    case 4:
                        break;
                    case 5:
                        sQLiteDatabase.beginTransaction();
                        try {
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS CHATS");
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Exception e2) {
                            Logger.f("MoEProvider: failed to port data.. FOR UBOX", e2);
                        } catch (Throwable th2) {
                            sQLiteDatabase.endTransaction();
                        }
                        sQLiteDatabase.endTransaction();
                        break;
                    case 6:
                        sQLiteDatabase.beginTransaction();
                        try {
                            if (MoEProvider.this.isTableExists(sQLiteDatabase, TABLE_NAME_OLD_INAPPS)) {
                                sQLiteDatabase.execSQL(" ALTER TABLE INAPPS ADD COLUMN TYPE INTEGER");
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Exception e22) {
                            Logger.f("MoEProvider: failed to add column INAPPS", e22);
                        } catch (Throwable th3) {
                            sQLiteDatabase.endTransaction();
                        }
                        sQLiteDatabase.endTransaction();
                        break;
                    case 7:
                        sQLiteDatabase.beginTransaction();
                        try {
                            createDataPointsTable(sQLiteDatabase);
                            MoEProvider.this.populateDatapoints(sQLiteDatabase);
                            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS EVENTS");
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Exception e222) {
                            Logger.f("MoEProvider: failed to populate Datapoints ", e222);
                        } catch (Throwable th4) {
                            sQLiteDatabase.endTransaction();
                        }
                        sQLiteDatabase.endTransaction();
                        break;
                    case 8:
                        sQLiteDatabase.beginTransaction();
                        try {
                            if (!MoEProvider.this.isFieldExist(TABLE_NAME_MSGS, MessageColumns.MSG_TAG, sQLiteDatabase)) {
                                sQLiteDatabase.execSQL(" ALTER TABLE MESSAGES ADD COLUMN msg_tag TEXT");
                            }
                            sQLiteDatabase.setTransactionSuccessful();
                        } catch (Exception e2222) {
                            Logger.f("MoEProvider: failed to add columns to UINBOX / MESSAGES", e2222);
                        } catch (Throwable th5) {
                            sQLiteDatabase.endTransaction();
                        }
                        sQLiteDatabase.endTransaction();
                        break;
                    case 9:
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS INAPPS");
                        createInAppTable(sQLiteDatabase);
                        if (!MoEProvider.this.isFieldExist(TABLE_NAME_MSGS, MessageColumns.MSG_TAG, sQLiteDatabase)) {
                            sQLiteDatabase.execSQL(" ALTER TABLE MESSAGES ADD COLUMN msg_tag TEXT");
                            break;
                        }
                        break;
                    case 10:
                        createUserAttributeTable(sQLiteDatabase);
                        break;
                    case 11:
                        createCampaignListTable(sQLiteDatabase);
                        break;
                    case 12:
                        createBatchDataTable(sQLiteDatabase);
                        break;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to upgrade from DB version");
                        stringBuilder.append(i);
                        stringBuilder.append("to DB version");
                        stringBuilder.append(i2);
                        Logger.v(stringBuilder.toString());
                        break;
                }
            }
            ConfigurationProvider.getInstance(MoEProvider.this.getContext()).setNewDBVersion(12);
            Logger.v("MoEProvider: Database Upgraded");
        }

        private void createDataPointsTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS DATAPOINTS ( _id INTEGER PRIMARY KEY, gtime INTEGER, details TEXT ); ");
        }

        private void createMessagesTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MESSAGES ( _id INTEGER PRIMARY KEY, msg TEXT, msgclicked INTEGER DEFAULT 0, msgttl INTEGER, gtime INTEGER )");
        }

        private void createInAppTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS INAPPMSG ( _id INTEGER PRIMARY KEY, gtime INTEGER, campaign_id TEXT, align_type TEXT, inapp_type TEXT, ttl INTEGER DEFAULT 0, min_delay INTEGER DEFAULT 0, max_times INTEGER DEFAULT 0, shown_count INTEGER DEFAULT 0, persistent INTEGER DEFAULT 0, priority INTEGER DEFAULT 0, context TEXT, last_shown INTEGER DEFAULT 0, is_clicked INTEGER DEFAULT 0, has_errors INTEGER DEFAULT 0, auto_dismiss INTEGER DEFAULT 0, cancelable INTEGER DEFAULT 0, content TEXT, show_only_in TEXT, status TEXT, dim_style TEXT );");
        }

        public void addMSGTagIfRequiredInbox(SQLiteDatabase sQLiteDatabase) {
            if (!MoEProvider.this.isFieldExist(TABLE_NAME_MSGS, MessageColumns.MSG_TAG, sQLiteDatabase)) {
                Logger.v("MoEProvider : addMSGTagIfRequiredInbox : updating inbox table");
                sQLiteDatabase.execSQL(" ALTER TABLE MESSAGES ADD COLUMN msg_tag TEXT");
            }
        }

        public void addUserAttributesTableIfRequired(SQLiteDatabase sQLiteDatabase) {
            if (tableExists(sQLiteDatabase, TABLE_NAME_USER_ATTRIBUTES)) {
                Logger.v("MoEProvider : DatabaseHelper : addUserAttributesTableIfRequired user attribute table already present");
                return;
            }
            Logger.v("MoEProvider : DatabaseHelper : addUserAttributesTableIfRequired creating missing  user attribute table");
            createUserAttributeTable(sQLiteDatabase);
        }

        public void addCampaignListTableIfRequired(SQLiteDatabase sQLiteDatabase) {
            if (tableExists(sQLiteDatabase, TABLE_NAME_CAMPAIGN_LIST)) {
                Logger.v("MoEProvider : DatabaseHelper : addCampaignListTableIfRequired campaign list table already present");
                return;
            }
            Logger.v("MoEProvider : DatabaseHelper : addCampaignListTableIfRequired creating missing  campaign list table");
            createUserAttributeTable(sQLiteDatabase);
        }

        private void createUserAttributeTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS USERATTRIBUTES ( _id INTEGER PRIMARY KEY, attribute_name TEXT, attribute_value TEXT ); ");
        }

        private void createCampaignListTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS CAMPAIGNLIST ( _id INTEGER PRIMARY KEY, campaign_id TEXT, ttl INTEGER );");
        }

        private void createBatchDataTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS BATCH_DATA ( _id INTEGER PRIMARY KEY, batch_data TEXT );");
        }

        /* Access modifiers changed, original: 0000 */
        public void addBatchDataTableIfRequired(SQLiteDatabase sQLiteDatabase) {
            if (tableExists(sQLiteDatabase, TABLE_NAME_BATCH_DATA)) {
                Logger.v("MoEProvider: DatabaseHelper : addBatchDataTableIfRequired batch data table is already present");
                return;
            }
            Logger.v("MoEProvider : DatabaseHelper : addBatchDataTableIfRequired creating missing  campaign list table");
            createBatchDataTable(sQLiteDatabase);
        }
    }

    static {
        sMessageProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sMessageProjectionMap.put(BaseColumns.GTIME, BaseColumns.GTIME);
        sMessageProjectionMap.put("msg", "msg");
        sMessageProjectionMap.put(MessageColumns.MSG_CLICKED, MessageColumns.MSG_CLICKED);
        sMessageProjectionMap.put(MessageColumns.MSG_TTL, MessageColumns.MSG_TTL);
        sMessageProjectionMap.put(MessageColumns.MSG_TAG, MessageColumns.MSG_TAG);
        sEventProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sEventProjectionMap.put(BaseColumns.GTIME, BaseColumns.GTIME);
        sEventProjectionMap.put(DatapointColumns.DETAILS, DatapointColumns.DETAILS);
        sInAppProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sInAppProjectionMap.put(BaseColumns.GTIME, BaseColumns.GTIME);
        sInAppProjectionMap.put("campaign_id", "campaign_id");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_ALIGN_TYPE, InAppMessageColumns.MSG_ALIGN_TYPE);
        sInAppProjectionMap.put(InAppMessageColumns.MSG_INAPP_TYPE, InAppMessageColumns.MSG_INAPP_TYPE);
        sInAppProjectionMap.put("ttl", "ttl");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_MIN_DELAY, InAppMessageColumns.MSG_MIN_DELAY);
        sInAppProjectionMap.put("max_times", "max_times");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_SHOWN_COUNT, InAppMessageColumns.MSG_SHOWN_COUNT);
        sInAppProjectionMap.put("persistent", "persistent");
        sInAppProjectionMap.put("priority", "priority");
        sInAppProjectionMap.put("context", "context");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_LAST_SHOWN, InAppMessageColumns.MSG_LAST_SHOWN);
        sInAppProjectionMap.put(InAppMessageColumns.MSG_IS_CLICKED, InAppMessageColumns.MSG_IS_CLICKED);
        sInAppProjectionMap.put(InAppMessageColumns.MSG_HAS_ERRORS, InAppMessageColumns.MSG_HAS_ERRORS);
        sInAppProjectionMap.put("auto_dismiss", "auto_dismiss");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_CANCELABLE, InAppMessageColumns.MSG_CANCELABLE);
        sInAppProjectionMap.put("content", "content");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_SHOW_ONLY_IN, InAppMessageColumns.MSG_SHOW_ONLY_IN);
        sInAppProjectionMap.put("status", "status");
        sInAppProjectionMap.put(InAppMessageColumns.MSG_CONTAINER_STYLE, InAppMessageColumns.MSG_CONTAINER_STYLE);
        sUserAttributeProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sUserAttributeProjectionMap.put(BaseColumns.GTIME, BaseColumns.GTIME);
        sUserAttributeProjectionMap.put(UserAttributeColumns.ATTRIBUTE_NAME, UserAttributeColumns.ATTRIBUTE_NAME);
        sUserAttributeProjectionMap.put(UserAttributeColumns.ATTRIBUTE_VALUE, UserAttributeColumns.ATTRIBUTE_VALUE);
        sCampaignListProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sCampaignListProjectionMap.put("campaign_id", "campaign_id");
        sCampaignListProjectionMap.put("ttl", "ttl");
        sBatchDataProjectionMap.put(BaseColumns._ID, BaseColumns._ID);
        sBatchDataProjectionMap.put(BatchDataColumns.BATCHED_DATA, BatchDataColumns.BATCHED_DATA);
    }

    private void populateDatapoints(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                Logger.d("Started porting DATA - FOR DATAPOINTS");
                sQLiteDatabase.beginTransaction();
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT _id, action, attrs, gtime, ltime FROM EVENTS", null);
                if (rawQuery != null && rawQuery.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        JSONObject datapointJSON = MoEHelperUtils.getDatapointJSON(rawQuery.getString(1), new JSONObject(rawQuery.getString(2)), Long.toString(rawQuery.getLong(3)), rawQuery.getString(4));
                        if (datapointJSON != null) {
                            contentValues.put(DatapointColumns.DETAILS, datapointJSON.toString());
                            contentValues.put(BaseColumns.GTIME, Long.valueOf(rawQuery.getLong(3)));
                            long insert = sQLiteDatabase.insert("DATAPOINTS", null, contentValues);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("MoEProvider:onUpgrade: Porting event data: ");
                            stringBuilder.append(insert);
                            Logger.v(stringBuilder.toString());
                        }
                    } while (rawQuery.moveToNext());
                    rawQuery.close();
                }
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                Logger.f("MoEProvider: populateDatapoints", e);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
            }
            sQLiteDatabase.endTransaction();
        }
    }

    private void portDataFromv2(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM moemsgs", null);
            if (rawQuery != null && rawQuery.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("msg", rawQuery.getString(1));
                    contentValues.put(MessageColumns.MSG_CLICKED, Integer.valueOf(rawQuery.getInt(2)));
                    contentValues.put(MessageColumns.MSG_TTL, Long.valueOf(Long.parseLong(rawQuery.getString(3))));
                    contentValues.put(BaseColumns.GTIME, Long.valueOf(Long.parseLong(rawQuery.getString(4))));
                    long insert = sQLiteDatabase.insert("MESSAGES", null, contentValues);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("MoEProvider:onUpgrade: Porting message data: ");
                    stringBuilder.append(insert);
                    Logger.v(stringBuilder.toString());
                } while (rawQuery.moveToNext());
                rawQuery.close();
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Exception e) {
            Logger.f("MoEProvider: portDatafromv2", e);
            if (sQLiteDatabase == null) {
                return;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
        sQLiteDatabase.endTransaction();
    }

    public boolean onCreate() {
        this.mOpenHelper = new DatabaseHelper(getContext());
        initializeUriMatcher();
        Logger.d("MoEProvider: Provider created");
        return true;
    }

    private void initializeUriMatcher() {
        sUriMatcher = new UriMatcher(-1);
        Context context = getContext();
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "messages", 1);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "messages/#", 2);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "datapoints", 3);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "datapoints/#", 4);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "inapps", 5);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "inapps/#", 6);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "userattributes/", 9);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "userattributes/#", 10);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "campaignlist/", 11);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "campaignlist/#", 12);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "batchdata/", 13);
        sUriMatcher.addURI(MoEDataContract.getAuthority(context), "batchdata/#", 14);
    }

    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 1:
                return MessageEntity.CONTENT_TYPE;
            case 2:
                return MessageEntity.CONTENT_ITEM_TYPE;
            case 3:
                return DatapointEntity.CONTENT_TYPE;
            case 4:
                return DatapointEntity.CONTENT_ITEM_TYPE;
            case 5:
                return "vnd.android.cursor.dir/vnd.moe.inapps";
            case 6:
                return "vnd.android.cursor.item/vnd.moe.inapp";
            case 9:
                return UserAttributeEntity.CONTENT_TYPE;
            case 10:
                return UserAttributeEntity.CONTENT_ITEM_TYPE;
            case 11:
                return CampaignListEntity.CONTENT_TYPE;
            case 12:
                return CampaignListEntity.CONTENT_ITEM_TYPE;
            case 13:
                return BatchDataEntity.CONTENT_TYPE;
            case 14:
                return BatchDataEntity.CONTENT_ITEM_TYPE;
            default:
                Logger.f("No Matching URI found");
                return null;
        }
    }

    /* JADX WARNING: Missing block: B:10:0x005e, code skipped:
            r1.setProjectionMap(sInAppProjectionMap);
            r1.setTables("INAPPMSG");
     */
    /* JADX WARNING: Missing block: B:11:0x006c, code skipped:
            if (android.text.TextUtils.isEmpty(r15) == false) goto L_0x00ec;
     */
    /* JADX WARNING: Missing block: B:12:0x006e, code skipped:
            r15 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageColumns.DEFAULT_SORT_ORDER;
     */
    /* JADX WARNING: Missing block: B:14:0x0090, code skipped:
            r1.setProjectionMap(sEventProjectionMap);
            r1.setTables("DATAPOINTS");
     */
    /* JADX WARNING: Missing block: B:16:0x00b9, code skipped:
            r1.setProjectionMap(sMessageProjectionMap);
            r1.setTables("MESSAGES");
     */
    /* JADX WARNING: Missing block: B:17:0x00c7, code skipped:
            if (android.text.TextUtils.isEmpty(r15) == false) goto L_0x00ec;
     */
    /* JADX WARNING: Missing block: B:18:0x00c9, code skipped:
            r15 = "gtime DESC";
     */
    public android.database.Cursor query(@android.support.annotation.NonNull android.net.Uri r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15) {
        /*
        r10 = this;
        r0 = r10.mOpenHelper;
        r2 = r0.getReadableDatabase();
        r10.updateIfRequired(r2);
        r1 = new android.database.sqlite.SQLiteQueryBuilder;
        r1.<init>();
        r0 = "LIMIT";
        r9 = r11.getQueryParameter(r0);
        r0 = sUriMatcher;
        r0 = r0.match(r11);
        r3 = 9;
        if (r0 == r3) goto L_0x00e2;
    L_0x001e:
        r3 = 11;
        if (r0 == r3) goto L_0x00d7;
    L_0x0022:
        r3 = 13;
        if (r0 == r3) goto L_0x00cc;
    L_0x0026:
        r3 = 1;
        switch(r0) {
            case 1: goto L_0x00b9;
            case 2: goto L_0x009b;
            case 3: goto L_0x0090;
            case 4: goto L_0x0072;
            case 5: goto L_0x005e;
            case 6: goto L_0x0040;
            default: goto L_0x002a;
        };
    L_0x002a:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "Unknown URI ";
        r0.append(r3);
        r0.append(r11);
        r11 = r0.toString();
        com.moengage.core.Logger.f(r11);
        goto L_0x00ec;
    L_0x0040:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r4 = "_id=";
        r0.append(r4);
        r11 = r11.getPathSegments();
        r11 = r11.get(r3);
        r11 = (java.lang.String) r11;
        r0.append(r11);
        r11 = r0.toString();
        r1.appendWhere(r11);
    L_0x005e:
        r11 = sInAppProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "INAPPMSG";
        r1.setTables(r11);
        r11 = android.text.TextUtils.isEmpty(r15);
        if (r11 == 0) goto L_0x00ec;
    L_0x006e:
        r15 = "priority DESC, gtime DESC";
        goto L_0x00ec;
    L_0x0072:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r4 = "_id=";
        r0.append(r4);
        r11 = r11.getPathSegments();
        r11 = r11.get(r3);
        r11 = (java.lang.String) r11;
        r0.append(r11);
        r11 = r0.toString();
        r1.appendWhere(r11);
    L_0x0090:
        r11 = sEventProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "DATAPOINTS";
        r1.setTables(r11);
        goto L_0x00ec;
    L_0x009b:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r4 = "_id=";
        r0.append(r4);
        r11 = r11.getPathSegments();
        r11 = r11.get(r3);
        r11 = (java.lang.String) r11;
        r0.append(r11);
        r11 = r0.toString();
        r1.appendWhere(r11);
    L_0x00b9:
        r11 = sMessageProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "MESSAGES";
        r1.setTables(r11);
        r11 = android.text.TextUtils.isEmpty(r15);
        if (r11 == 0) goto L_0x00ec;
    L_0x00c9:
        r15 = "gtime DESC";
        goto L_0x00ec;
    L_0x00cc:
        r11 = sBatchDataProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "BATCH_DATA";
        r1.setTables(r11);
        goto L_0x00ec;
    L_0x00d7:
        r11 = sCampaignListProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "CAMPAIGNLIST";
        r1.setTables(r11);
        goto L_0x00ec;
    L_0x00e2:
        r11 = sUserAttributeProjectionMap;
        r1.setProjectionMap(r11);
        r11 = "USERATTRIBUTES";
        r1.setTables(r11);
    L_0x00ec:
        r8 = r15;
        r11 = 0;
        r6 = 0;
        r7 = 0;
        r3 = r12;
        r4 = r13;
        r5 = r14;
        r12 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00f8 }
        r11 = r12;
    L_0x00f8:
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moe.pushlibrary.providers.MoEProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f2  */
    public android.net.Uri insert(android.net.Uri r7, android.content.ContentValues r8) {
        /*
        r6 = this;
        r0 = 0;
        if (r8 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r6.mOpenHelper;
        r1 = r1.getWritableDatabase();
        r6.updateIfRequired(r1);
        r2 = sUriMatcher;
        r2 = r2.match(r7);
        r3 = 1;
        r4 = 0;
        if (r2 == r3) goto L_0x00b4;
    L_0x0018:
        r3 = 3;
        if (r2 == r3) goto L_0x009d;
    L_0x001b:
        r3 = 5;
        if (r2 == r3) goto L_0x0086;
    L_0x001e:
        r3 = 9;
        if (r2 == r3) goto L_0x006f;
    L_0x0022:
        r3 = 11;
        if (r2 == r3) goto L_0x0058;
    L_0x0026:
        r3 = 13;
        if (r2 == r3) goto L_0x0040;
    L_0x002a:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r1 = "Unknown URI ";
        r8.append(r1);
        r8.append(r7);
        r8 = r8.toString();
        com.moengage.core.Logger.f(r8);
        goto L_0x00cb;
    L_0x0040:
        r2 = "BATCH_DATA";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x004a:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.BatchDataEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x0058:
        r2 = "CAMPAIGNLIST";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x0062:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x006f:
        r2 = "USERATTRIBUTES";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x0079:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.UserAttributeEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x0086:
        r2 = "INAPPMSG";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x0090:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.InAppMessageEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x009d:
        r2 = "DATAPOINTS";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x00a7:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.DatapointEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x00b4:
        r2 = "MESSAGES";
        r1 = r1.insert(r2, r0, r8);
        r8 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r8 <= 0) goto L_0x00cb;
    L_0x00be:
        r8 = r6.getContext();
        r8 = com.moe.pushlibrary.providers.MoEDataContract.MessageEntity.getContentUri(r8);
        r8 = android.content.ContentUris.withAppendedId(r8, r1);
        goto L_0x00cc;
    L_0x00cb:
        r8 = r0;
    L_0x00cc:
        if (r8 == 0) goto L_0x00f2;
    L_0x00ce:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "MoEProvider: Added new record : ";
        r1.append(r2);
        r2 = r8.toString();
        r1.append(r2);
        r1 = r1.toString();
        com.moengage.core.Logger.v(r1);
        r1 = r6.getContext();
        r1 = r1.getContentResolver();
        r1.notifyChange(r7, r0);
        goto L_0x00f7;
    L_0x00f2:
        r7 = "MoEProvider: Failed to add new record";
        com.moengage.core.Logger.f(r7);
    L_0x00f7:
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moe.pushlibrary.providers.MoEProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    public void updateIfRequired(SQLiteDatabase sQLiteDatabase) {
        if (MoEConstants.dbUpdateRequired) {
            this.mOpenHelper.addMSGTagIfRequiredInbox(sQLiteDatabase);
            this.mOpenHelper.addUserAttributesTableIfRequired(sQLiteDatabase);
            this.mOpenHelper.addCampaignListTableIfRequired(sQLiteDatabase);
            this.mOpenHelper.addBatchDataTableIfRequired(sQLiteDatabase);
            MoEConstants.dbUpdateRequired = false;
        }
    }

    @NonNull
    public ContentProviderResult[] applyBatch(@NonNull ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[arrayList.size()];
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        updateIfRequired(writableDatabase);
        writableDatabase.beginTransaction();
        try {
            Iterator it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = i + 1;
                contentProviderResultArr[i] = ((ContentProviderOperation) it.next()).apply(this, contentProviderResultArr, i2);
                i = i2;
            }
            writableDatabase.setTransactionSuccessful();
        } catch (OperationApplicationException e) {
            Logger.f("MoEProvider : batch failed: ", e);
        } catch (Exception e2) {
            Logger.f("MoEProvider : batch failed: ", e2);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
        }
        writableDatabase.endTransaction();
        return contentProviderResultArr;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int i = 0;
        if (uri == null) {
            return 0;
        }
        StringBuilder stringBuilder;
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        updateIfRequired(writableDatabase);
        String str2;
        String str3;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3;
        switch (sUriMatcher.match(uri)) {
            case 1:
                i = writableDatabase.delete("MESSAGES", str, strArr);
                break;
            case 2:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "MESSAGES";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id=");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            case 3:
                i = writableDatabase.delete("DATAPOINTS", str, strArr);
                break;
            case 4:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "DATAPOINTS";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id = ");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            case 5:
                i = writableDatabase.delete("INAPPMSG", str, strArr);
                break;
            case 6:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "INAPPMSG";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id=");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            case 9:
                i = writableDatabase.delete("USERATTRIBUTES", str, strArr);
                break;
            case 10:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "USERATTRIBUTES";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id=");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            case 11:
                i = writableDatabase.delete("CAMPAIGNLIST", str, strArr);
                break;
            case 12:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "CAMPAIGNLIST";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id=");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            case 13:
                i = writableDatabase.delete("BATCH_DATA", str, strArr);
                break;
            case 14:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "BATCH_DATA";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("_id=");
                stringBuilder2.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" AND ");
                    stringBuilder3.append(str);
                    str = stringBuilder3.toString();
                }
                stringBuilder2.append(str);
                i = writableDatabase.delete(str3, stringBuilder2.toString(), strArr);
                break;
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown URI ");
                stringBuilder.append(uri);
                Logger.f(stringBuilder.toString());
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        stringBuilder = new StringBuilder();
        stringBuilder.append("MoEProvider: Deleted ");
        stringBuilder.append(i);
        stringBuilder.append(" record(s) for URI: ");
        stringBuilder.append(uri.toString());
        Logger.v(stringBuilder.toString());
        return i;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update;
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        updateIfRequired(writableDatabase);
        String str2;
        String str3;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        switch (sUriMatcher.match(uri)) {
            case 1:
                update = writableDatabase.update("MESSAGES", contentValues, str, strArr);
                break;
            case 2:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "MESSAGES";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            case 3:
                update = writableDatabase.update("DATAPOINTS", contentValues, str, strArr);
                break;
            case 4:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "DATAPOINTS";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            case 5:
                update = writableDatabase.update("INAPPMSG", contentValues, str, strArr);
                break;
            case 6:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "INAPPMSG";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            case 9:
                update = writableDatabase.update("USERATTRIBUTES", contentValues, str, strArr);
                break;
            case 10:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "USERATTRIBUTES";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            case 11:
                update = writableDatabase.update("CAMPAIGNLIST", contentValues, str, strArr);
                break;
            case 12:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "CAMPAIGNLIST";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            case 13:
                update = writableDatabase.update("BATCH_DATA", contentValues, str, strArr);
                break;
            case 14:
                str2 = (String) uri.getPathSegments().get(1);
                str3 = "BATCH_DATA";
                stringBuilder = new StringBuilder();
                stringBuilder.append("_id=");
                stringBuilder.append(str2);
                if (TextUtils.isEmpty(str)) {
                    str = "";
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" AND ");
                    stringBuilder2.append(str);
                    str = stringBuilder2.toString();
                }
                stringBuilder.append(str);
                update = writableDatabase.update(str3, contentValues, stringBuilder.toString(), strArr);
                break;
            default:
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("Unknown URI ");
                stringBuilder3.append(uri);
                Logger.f(stringBuilder3.toString());
                update = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("MoEProvider: Updated ");
        stringBuilder4.append(update);
        stringBuilder4.append(" record(s)");
        Logger.v(stringBuilder4.toString());
        return update;
    }

    public boolean isFieldExist(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA table_info(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
        ArrayList arrayList = new ArrayList();
        if (rawQuery != null) {
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("name")));
                    } while (rawQuery.moveToNext());
                }
            } catch (Throwable th) {
                if (rawQuery != null) {
                    rawQuery.close();
                }
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return arrayList.contains(str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0049  */
    public boolean isTableExists(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 0;
        if (r7 == 0) goto L_0x004b;
    L_0x0003:
        if (r6 == 0) goto L_0x004b;
    L_0x0005:
        r1 = 1;
        r2 = r6.isOpen();	 Catch:{ Exception -> 0x002d }
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        goto L_0x004b;
    L_0x000d:
        r2 = "SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?";
        r3 = 2;
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x002d }
        r4 = "table";
        r3[r0] = r4;	 Catch:{ Exception -> 0x002d }
        r3[r1] = r7;	 Catch:{ Exception -> 0x002d }
        r6 = r6.rawQuery(r2, r3);	 Catch:{ Exception -> 0x002d }
        r7 = r6.moveToFirst();	 Catch:{ Exception -> 0x002d }
        if (r7 != 0) goto L_0x0023;
    L_0x0022:
        return r0;
    L_0x0023:
        r7 = r6.getInt(r0);	 Catch:{ Exception -> 0x002d }
        r6.close();	 Catch:{ Exception -> 0x002b }
        goto L_0x0047;
    L_0x002b:
        r6 = move-exception;
        goto L_0x002f;
    L_0x002d:
        r6 = move-exception;
        r7 = r0;
    L_0x002f:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "MoEProvider: isTableExists Exception ";
        r2.append(r3);
        r6 = r6.toString();
        r2.append(r6);
        r6 = r2.toString();
        com.moengage.core.Logger.f(r6);
    L_0x0047:
        if (r7 <= 0) goto L_0x004a;
    L_0x0049:
        r0 = r1;
    L_0x004a:
        return r0;
    L_0x004b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moe.pushlibrary.providers.MoEProvider.isTableExists(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }
}
