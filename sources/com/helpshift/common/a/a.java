package com.helpshift.common.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.helpshift.conversation.activeconversation.message.AdminAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.AdminImageAttachmentMessageDM;
import com.helpshift.conversation.activeconversation.message.MessageType;
import com.helpshift.conversation.activeconversation.message.e;
import com.helpshift.conversation.activeconversation.message.f;
import com.helpshift.conversation.activeconversation.message.g;
import com.helpshift.conversation.activeconversation.message.h;
import com.helpshift.conversation.activeconversation.message.i;
import com.helpshift.conversation.activeconversation.message.j;
import com.helpshift.conversation.activeconversation.message.k;
import com.helpshift.conversation.activeconversation.message.m;
import com.helpshift.conversation.activeconversation.message.n;
import com.helpshift.conversation.activeconversation.message.o;
import com.helpshift.conversation.dto.ConversationStatus;
import com.helpshift.conversation.dto.c;
import com.helpshift.conversation.states.ConversationCSATState;
import com.helpshift.util.l;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private static a A;
    private final String a = "csat_rating";
    private final String b = "csat_state";
    private final String c = "csat_feedback";
    private final String d = "increment_message_count";
    private final String e = "ended_delegate_sent";
    private final String f = "image_draft_orig_name";
    private final String g = "image_draft_orig_size";
    private final String h = "image_draft_file_path";
    private final String i = "image_copy_done";
    private final String j = "referredMessageId";
    private final String k = "rejected_reason";
    private final String l = "rejected_conv_id";
    private final String m = "is_answered";
    private final String n = "content_type";
    private final String o = "file_name";
    private final String p = "url";
    private final String q = "size";
    private final String r = "thumbnail_url";
    private final String s = "thumbnailFilePath";
    private final String t = "filePath";
    private final String u = "chat_launch_src";
    private final String v = "seen_cursor";
    private final String w = "seen_sync_status";
    private final String x = "read_at";
    private final b y = new b();
    private final com.helpshift.l.a.a z;

    private class a {
        final String a;
        final String b;
        final String c;
        final String d;
        final int e;

        a(JSONObject jSONObject) {
            this.b = jSONObject.optString("file_name", null);
            this.a = jSONObject.optString("content_type", null);
            this.c = jSONObject.optString("url", null);
            this.e = jSONObject.optInt("size", 0);
            this.d = jSONObject.optString("filePath", null);
        }
    }

    private class b extends a {
        final String g;
        final String h;

        b(JSONObject jSONObject) {
            super(jSONObject);
            this.g = jSONObject.optString("thumbnail_url", null);
            this.h = jSONObject.optString("thumbnailFilePath", null);
        }
    }

    private a(Context context) {
        this.z = new com.helpshift.l.a.a(context, this.y);
    }

    public static a a(Context context) {
        if (A == null) {
            synchronized (a.class) {
                if (A == null) {
                    A = new a(context);
                }
            }
        }
        return A;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069 A:{SYNTHETIC, Splitter:B:22:0x0069} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0070 A:{SYNTHETIC, Splitter:B:27:0x0070} */
    public synchronized java.util.List<com.helpshift.conversation.activeconversation.a> a(long r13) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0074 }
        r0.<init>();	 Catch:{ all -> 0x0074 }
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0074 }
        r2.<init>();	 Catch:{ all -> 0x0074 }
        r3 = r12.y;	 Catch:{ all -> 0x0074 }
        r3.getClass();	 Catch:{ all -> 0x0074 }
        r3 = "profile_id";
        r2.append(r3);	 Catch:{ all -> 0x0074 }
        r3 = " = ?";
        r2.append(r3);	 Catch:{ all -> 0x0074 }
        r7 = r2.toString();	 Catch:{ all -> 0x0074 }
        r2 = 1;
        r8 = new java.lang.String[r2];	 Catch:{ all -> 0x0074 }
        r2 = 0;
        r13 = java.lang.String.valueOf(r13);	 Catch:{ all -> 0x0074 }
        r8[r2] = r13;	 Catch:{ all -> 0x0074 }
        r13 = r12.z;	 Catch:{ Exception -> 0x005f }
        r4 = r13.getReadableDatabase();	 Catch:{ Exception -> 0x005f }
        r13 = r12.y;	 Catch:{ Exception -> 0x005f }
        r13.getClass();	 Catch:{ Exception -> 0x005f }
        r5 = "issues";
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r13 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x005f }
        r14 = r13.moveToFirst();	 Catch:{ Exception -> 0x005a, all -> 0x0057 }
        if (r14 == 0) goto L_0x0051;
    L_0x0044:
        r14 = r12.b(r13);	 Catch:{ Exception -> 0x005a, all -> 0x0057 }
        r0.add(r14);	 Catch:{ Exception -> 0x005a, all -> 0x0057 }
        r14 = r13.moveToNext();	 Catch:{ Exception -> 0x005a, all -> 0x0057 }
        if (r14 != 0) goto L_0x0044;
    L_0x0051:
        if (r13 == 0) goto L_0x006c;
    L_0x0053:
        r13.close();	 Catch:{ all -> 0x0074 }
        goto L_0x006c;
    L_0x0057:
        r14 = move-exception;
        r1 = r13;
        goto L_0x006e;
    L_0x005a:
        r14 = move-exception;
        r1 = r13;
        goto L_0x0060;
    L_0x005d:
        r14 = move-exception;
        goto L_0x006e;
    L_0x005f:
        r14 = move-exception;
    L_0x0060:
        r13 = "Helpshift_ConverDB";
        r2 = "Error in read conversations with profileId";
        com.helpshift.util.l.c(r13, r2, r14);	 Catch:{ all -> 0x005d }
        if (r1 == 0) goto L_0x006c;
    L_0x0069:
        r1.close();	 Catch:{ all -> 0x0074 }
    L_0x006c:
        monitor-exit(r12);
        return r0;
    L_0x006e:
        if (r1 == 0) goto L_0x0073;
    L_0x0070:
        r1.close();	 Catch:{ all -> 0x0074 }
    L_0x0073:
        throw r14;	 Catch:{ all -> 0x0074 }
    L_0x0074:
        r13 = move-exception;
        monitor-exit(r12);
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.a(long):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A:{SYNTHETIC, Splitter:B:26:0x0062} */
    /* JADX WARNING: Missing block: B:11:0x0044, code skipped:
            if (r0 != null) goto L_0x0046;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0057, code skipped:
            if (r0 != null) goto L_0x0046;
     */
    public synchronized com.helpshift.conversation.activeconversation.a a(java.lang.Long r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0066 }
        r0.<init>();	 Catch:{ all -> 0x0066 }
        r1 = r11.y;	 Catch:{ all -> 0x0066 }
        r1.getClass();	 Catch:{ all -> 0x0066 }
        r1 = "_id";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r1 = " = ?";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r5 = r0.toString();	 Catch:{ all -> 0x0066 }
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0066 }
        r0 = 0;
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0066 }
        r6[r0] = r12;	 Catch:{ all -> 0x0066 }
        r12 = 0;
        r0 = r11.z;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r2 = r0.getReadableDatabase();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r0 = r11.y;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r0.getClass();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r3 = "issues";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r0 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x004a }
        if (r1 == 0) goto L_0x0044;
    L_0x003f:
        r1 = r11.b(r0);	 Catch:{ Exception -> 0x004a }
        r12 = r1;
    L_0x0044:
        if (r0 == 0) goto L_0x005a;
    L_0x0046:
        r0.close();	 Catch:{ all -> 0x0066 }
        goto L_0x005a;
    L_0x004a:
        r1 = move-exception;
        goto L_0x0050;
    L_0x004c:
        r0 = move-exception;
        goto L_0x0060;
    L_0x004e:
        r1 = move-exception;
        r0 = r12;
    L_0x0050:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in read conversations with localId";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x005a;
    L_0x0059:
        goto L_0x0046;
    L_0x005a:
        monitor-exit(r11);
        return r12;
    L_0x005c:
        r12 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
    L_0x0060:
        if (r12 == 0) goto L_0x0065;
    L_0x0062:
        r12.close();	 Catch:{ all -> 0x0066 }
    L_0x0065:
        throw r0;	 Catch:{ all -> 0x0066 }
    L_0x0066:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.a(java.lang.Long):com.helpshift.conversation.activeconversation.a");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A:{SYNTHETIC, Splitter:B:26:0x0062} */
    /* JADX WARNING: Missing block: B:11:0x0044, code skipped:
            if (r0 != null) goto L_0x0046;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0057, code skipped:
            if (r0 != null) goto L_0x0046;
     */
    public synchronized com.helpshift.conversation.activeconversation.a a(java.lang.String r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0066 }
        r0.<init>();	 Catch:{ all -> 0x0066 }
        r1 = r11.y;	 Catch:{ all -> 0x0066 }
        r1.getClass();	 Catch:{ all -> 0x0066 }
        r1 = "server_id";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r1 = " = ?";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r5 = r0.toString();	 Catch:{ all -> 0x0066 }
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0066 }
        r0 = 0;
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0066 }
        r6[r0] = r12;	 Catch:{ all -> 0x0066 }
        r12 = 0;
        r0 = r11.z;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r2 = r0.getReadableDatabase();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r0 = r11.y;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r0.getClass();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r3 = "issues";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r0 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x004a }
        if (r1 == 0) goto L_0x0044;
    L_0x003f:
        r1 = r11.b(r0);	 Catch:{ Exception -> 0x004a }
        r12 = r1;
    L_0x0044:
        if (r0 == 0) goto L_0x005a;
    L_0x0046:
        r0.close();	 Catch:{ all -> 0x0066 }
        goto L_0x005a;
    L_0x004a:
        r1 = move-exception;
        goto L_0x0050;
    L_0x004c:
        r0 = move-exception;
        goto L_0x0060;
    L_0x004e:
        r1 = move-exception;
        r0 = r12;
    L_0x0050:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in read conversations with serverId";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x005a;
    L_0x0059:
        goto L_0x0046;
    L_0x005a:
        monitor-exit(r11);
        return r12;
    L_0x005c:
        r12 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
    L_0x0060:
        if (r12 == 0) goto L_0x0065;
    L_0x0062:
        r12.close();	 Catch:{ all -> 0x0066 }
    L_0x0065:
        throw r0;	 Catch:{ all -> 0x0066 }
    L_0x0066:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.a(java.lang.String):com.helpshift.conversation.activeconversation.a");
    }

    public synchronized long a(com.helpshift.conversation.activeconversation.a aVar) {
        long j;
        ContentValues c = c(aVar);
        j = -1;
        try {
            SQLiteDatabase writableDatabase = this.z.getWritableDatabase();
            this.y.getClass();
            j = writableDatabase.insert("issues", null, c);
        } catch (Exception e) {
            l.c("Helpshift_ConverDB", "Error in insert conversation", e);
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x007c A:{SYNTHETIC, Splitter:B:37:0x007c} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008a A:{SYNTHETIC, Splitter:B:45:0x008a} */
    public synchronized java.util.List<java.lang.Long> a(java.util.List<com.helpshift.conversation.activeconversation.a> r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r6.size();	 Catch:{ all -> 0x0097 }
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r5);
        return r1;
    L_0x000a:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0097 }
        r0.<init>();	 Catch:{ all -> 0x0097 }
        r6 = r6.iterator();	 Catch:{ all -> 0x0097 }
    L_0x0013:
        r2 = r6.hasNext();	 Catch:{ all -> 0x0097 }
        if (r2 == 0) goto L_0x0027;
    L_0x0019:
        r2 = r6.next();	 Catch:{ all -> 0x0097 }
        r2 = (com.helpshift.conversation.activeconversation.a) r2;	 Catch:{ all -> 0x0097 }
        r2 = r5.c(r2);	 Catch:{ all -> 0x0097 }
        r0.add(r2);	 Catch:{ all -> 0x0097 }
        goto L_0x0013;
    L_0x0027:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0097 }
        r6.<init>();	 Catch:{ all -> 0x0097 }
        r2 = r5.z;	 Catch:{ Exception -> 0x0072 }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x0072 }
        r2.beginTransaction();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
    L_0x0039:
        r3 = r0.hasNext();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        if (r3 == 0) goto L_0x0058;
    L_0x003f:
        r3 = r0.next();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r3 = (android.content.ContentValues) r3;	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r4 = r5.y;	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r4.getClass();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r4 = "issues";
        r3 = r2.insert(r4, r1, r3);	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        r6.add(r3);	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        goto L_0x0039;
    L_0x0058:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x006c, all -> 0x006a }
        if (r2 == 0) goto L_0x0086;
    L_0x005d:
        r2.endTransaction();	 Catch:{ Exception -> 0x0061 }
        goto L_0x0086;
    L_0x0061:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert conversations inside finally block";
    L_0x0066:
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x0097 }
        goto L_0x0086;
    L_0x006a:
        r6 = move-exception;
        goto L_0x0088;
    L_0x006c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0073;
    L_0x006f:
        r6 = move-exception;
        r2 = r1;
        goto L_0x0088;
    L_0x0072:
        r0 = move-exception;
    L_0x0073:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in insert conversations";
        com.helpshift.util.l.c(r2, r3, r0);	 Catch:{ all -> 0x006f }
        if (r1 == 0) goto L_0x0086;
    L_0x007c:
        r1.endTransaction();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0086;
    L_0x0080:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert conversations inside finally block";
        goto L_0x0066;
    L_0x0086:
        monitor-exit(r5);
        return r6;
    L_0x0088:
        if (r2 == 0) goto L_0x0096;
    L_0x008a:
        r2.endTransaction();	 Catch:{ Exception -> 0x008e }
        goto L_0x0096;
    L_0x008e:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert conversations inside finally block";
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x0097 }
    L_0x0096:
        throw r6;	 Catch:{ all -> 0x0097 }
    L_0x0097:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.a(java.util.List):java.util.List");
    }

    public synchronized void b(com.helpshift.conversation.activeconversation.a aVar) {
        ContentValues c = c(aVar);
        StringBuilder stringBuilder = new StringBuilder();
        this.y.getClass();
        stringBuilder.append(BaseColumns._ID);
        stringBuilder.append(" = ?");
        String stringBuilder2 = stringBuilder.toString();
        String[] strArr = new String[]{String.valueOf(aVar.a)};
        try {
            SQLiteDatabase writableDatabase = this.z.getWritableDatabase();
            this.y.getClass();
            writableDatabase.update("issues", c, stringBuilder2, strArr);
        } catch (Exception e) {
            l.c("Helpshift_ConverDB", "Error in update conversation", e);
        }
        return;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A:{SYNTHETIC, Splitter:B:44:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f A:{SYNTHETIC, Splitter:B:36:0x009f} */
    public synchronized void b(java.util.List<com.helpshift.conversation.activeconversation.a> r9) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r9.size();	 Catch:{ all -> 0x00ba }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r8);
        return;
    L_0x0009:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00ba }
        r0.<init>();	 Catch:{ all -> 0x00ba }
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00ba }
        r1.<init>();	 Catch:{ all -> 0x00ba }
        r2 = r9.iterator();	 Catch:{ all -> 0x00ba }
    L_0x0017:
        r3 = r2.hasNext();	 Catch:{ all -> 0x00ba }
        r4 = 0;
        if (r3 == 0) goto L_0x003a;
    L_0x001e:
        r3 = r2.next();	 Catch:{ all -> 0x00ba }
        r3 = (com.helpshift.conversation.activeconversation.a) r3;	 Catch:{ all -> 0x00ba }
        r5 = r8.c(r3);	 Catch:{ all -> 0x00ba }
        r0.add(r5);	 Catch:{ all -> 0x00ba }
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ all -> 0x00ba }
        r3 = r3.a;	 Catch:{ all -> 0x00ba }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00ba }
        r5[r4] = r3;	 Catch:{ all -> 0x00ba }
        r1.add(r5);	 Catch:{ all -> 0x00ba }
        goto L_0x0017;
    L_0x003a:
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ba }
        r3.<init>();	 Catch:{ all -> 0x00ba }
        r5 = r8.y;	 Catch:{ all -> 0x00ba }
        r5.getClass();	 Catch:{ all -> 0x00ba }
        r5 = "_id";
        r3.append(r5);	 Catch:{ all -> 0x00ba }
        r5 = " = ?";
        r3.append(r5);	 Catch:{ all -> 0x00ba }
        r3 = r3.toString();	 Catch:{ all -> 0x00ba }
        r5 = r8.z;	 Catch:{ Exception -> 0x0095 }
        r5 = r5.getWritableDatabase();	 Catch:{ Exception -> 0x0095 }
        r5.beginTransaction();	 Catch:{ Exception -> 0x008f, all -> 0x008d }
    L_0x005c:
        r2 = r9.size();	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        if (r4 >= r2) goto L_0x007b;
    L_0x0062:
        r2 = r8.y;	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r2.getClass();	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r2 = "issues";
        r6 = r0.get(r4);	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r6 = (android.content.ContentValues) r6;	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r7 = r1.get(r4);	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r7 = (java.lang.String[]) r7;	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r5.update(r2, r6, r3, r7);	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        r4 = r4 + 1;
        goto L_0x005c;
    L_0x007b:
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x008f, all -> 0x008d }
        if (r5 == 0) goto L_0x00a9;
    L_0x0080:
        r5.endTransaction();	 Catch:{ Exception -> 0x0084 }
        goto L_0x00a9;
    L_0x0084:
        r9 = move-exception;
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update conversations inside finally block";
    L_0x0089:
        com.helpshift.util.l.c(r0, r1, r9);	 Catch:{ all -> 0x00ba }
        goto L_0x00a9;
    L_0x008d:
        r9 = move-exception;
        goto L_0x00ab;
    L_0x008f:
        r9 = move-exception;
        r2 = r5;
        goto L_0x0096;
    L_0x0092:
        r9 = move-exception;
        r5 = r2;
        goto L_0x00ab;
    L_0x0095:
        r9 = move-exception;
    L_0x0096:
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update conversations";
        com.helpshift.util.l.c(r0, r1, r9);	 Catch:{ all -> 0x0092 }
        if (r2 == 0) goto L_0x00a9;
    L_0x009f:
        r2.endTransaction();	 Catch:{ Exception -> 0x00a3 }
        goto L_0x00a9;
    L_0x00a3:
        r9 = move-exception;
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update conversations inside finally block";
        goto L_0x0089;
    L_0x00a9:
        monitor-exit(r8);
        return;
    L_0x00ab:
        if (r5 == 0) goto L_0x00b9;
    L_0x00ad:
        r5.endTransaction();	 Catch:{ Exception -> 0x00b1 }
        goto L_0x00b9;
    L_0x00b1:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in update conversations inside finally block";
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x00ba }
    L_0x00b9:
        throw r9;	 Catch:{ all -> 0x00ba }
    L_0x00ba:
        r9 = move-exception;
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.b(java.util.List):void");
    }

    public synchronized com.helpshift.conversation.dto.a.a a(com.helpshift.conversation.dto.a.a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        this.y.getClass();
        stringBuilder.append("profile_id");
        stringBuilder.append(" = ?");
        String stringBuilder2 = stringBuilder.toString();
        String[] strArr = new String[]{String.valueOf(aVar.a)};
        ContentValues b = b(aVar);
        try {
            SQLiteDatabase writableDatabase = this.z.getWritableDatabase();
            this.y.getClass();
            if (a(writableDatabase, "conversation_inbox", stringBuilder2, strArr)) {
                this.y.getClass();
                writableDatabase.update("conversation_inbox", b, stringBuilder2, strArr);
            } else {
                this.y.getClass();
                writableDatabase.insert("conversation_inbox", null, b);
            }
        } catch (Exception e) {
            l.c("Helpshift_ConverDB", "Error in store conversation inbox record", e);
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A:{SYNTHETIC, Splitter:B:26:0x0062} */
    /* JADX WARNING: Missing block: B:11:0x0044, code skipped:
            if (r13 != null) goto L_0x0046;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0057, code skipped:
            if (r13 != null) goto L_0x0046;
     */
    public synchronized com.helpshift.conversation.dto.a.a b(long r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0066 }
        r0.<init>();	 Catch:{ all -> 0x0066 }
        r1 = r11.y;	 Catch:{ all -> 0x0066 }
        r1.getClass();	 Catch:{ all -> 0x0066 }
        r1 = "profile_id";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r1 = " = ?";
        r0.append(r1);	 Catch:{ all -> 0x0066 }
        r5 = r0.toString();	 Catch:{ all -> 0x0066 }
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0066 }
        r0 = 0;
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0066 }
        r6[r0] = r12;	 Catch:{ all -> 0x0066 }
        r12 = 0;
        r13 = r11.z;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r2 = r13.getReadableDatabase();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r13 = r11.y;	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r13.getClass();	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r3 = "conversation_inbox";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r13 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004e, all -> 0x004c }
        r0 = r13.moveToFirst();	 Catch:{ Exception -> 0x004a }
        if (r0 == 0) goto L_0x0044;
    L_0x003f:
        r0 = r11.a(r13);	 Catch:{ Exception -> 0x004a }
        r12 = r0;
    L_0x0044:
        if (r13 == 0) goto L_0x005a;
    L_0x0046:
        r13.close();	 Catch:{ all -> 0x0066 }
        goto L_0x005a;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0050;
    L_0x004c:
        r13 = move-exception;
        goto L_0x0060;
    L_0x004e:
        r0 = move-exception;
        r13 = r12;
    L_0x0050:
        r1 = "Helpshift_ConverDB";
        r2 = "Error in read conversation inbox record";
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x005c }
        if (r13 == 0) goto L_0x005a;
    L_0x0059:
        goto L_0x0046;
    L_0x005a:
        monitor-exit(r11);
        return r12;
    L_0x005c:
        r12 = move-exception;
        r10 = r13;
        r13 = r12;
        r12 = r10;
    L_0x0060:
        if (r12 == 0) goto L_0x0065;
    L_0x0062:
        r12.close();	 Catch:{ all -> 0x0066 }
    L_0x0065:
        throw r13;	 Catch:{ all -> 0x0066 }
    L_0x0066:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.b(long):com.helpshift.conversation.dto.a.a");
    }

    public synchronized long a(j jVar) {
        long j;
        ContentValues c = c(jVar);
        j = -1;
        try {
            SQLiteDatabase writableDatabase = this.z.getWritableDatabase();
            b bVar = this.y;
            j = writableDatabase.insert("messages", null, c);
        } catch (Exception e) {
            l.c("Helpshift_ConverDB", "Error in insert message", e);
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0079 A:{SYNTHETIC, Splitter:B:37:0x0079} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0087 A:{SYNTHETIC, Splitter:B:45:0x0087} */
    public synchronized java.util.List<java.lang.Long> c(java.util.List<com.helpshift.conversation.activeconversation.message.j> r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r6.size();	 Catch:{ all -> 0x0094 }
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r5);
        return r1;
    L_0x000a:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0094 }
        r0.<init>();	 Catch:{ all -> 0x0094 }
        r6 = r6.iterator();	 Catch:{ all -> 0x0094 }
    L_0x0013:
        r2 = r6.hasNext();	 Catch:{ all -> 0x0094 }
        if (r2 == 0) goto L_0x0027;
    L_0x0019:
        r2 = r6.next();	 Catch:{ all -> 0x0094 }
        r2 = (com.helpshift.conversation.activeconversation.message.j) r2;	 Catch:{ all -> 0x0094 }
        r2 = r5.c(r2);	 Catch:{ all -> 0x0094 }
        r0.add(r2);	 Catch:{ all -> 0x0094 }
        goto L_0x0013;
    L_0x0027:
        r6 = new java.util.ArrayList;	 Catch:{ all -> 0x0094 }
        r6.<init>();	 Catch:{ all -> 0x0094 }
        r2 = r5.z;	 Catch:{ Exception -> 0x006f }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x006f }
        r2.beginTransaction();	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
    L_0x0039:
        r3 = r0.hasNext();	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        if (r3 == 0) goto L_0x0055;
    L_0x003f:
        r3 = r0.next();	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r3 = (android.content.ContentValues) r3;	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r4 = r5.y;	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r4 = "messages";
        r3 = r2.insert(r4, r1, r3);	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r3 = java.lang.Long.valueOf(r3);	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        r6.add(r3);	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        goto L_0x0039;
    L_0x0055:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x0069, all -> 0x0067 }
        if (r2 == 0) goto L_0x0083;
    L_0x005a:
        r2.endTransaction();	 Catch:{ Exception -> 0x005e }
        goto L_0x0083;
    L_0x005e:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert messages inside finally block";
    L_0x0063:
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x0094 }
        goto L_0x0083;
    L_0x0067:
        r6 = move-exception;
        goto L_0x0085;
    L_0x0069:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0070;
    L_0x006c:
        r6 = move-exception;
        r2 = r1;
        goto L_0x0085;
    L_0x006f:
        r0 = move-exception;
    L_0x0070:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in insert messages";
        com.helpshift.util.l.c(r2, r3, r0);	 Catch:{ all -> 0x006c }
        if (r1 == 0) goto L_0x0083;
    L_0x0079:
        r1.endTransaction();	 Catch:{ Exception -> 0x007d }
        goto L_0x0083;
    L_0x007d:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert messages inside finally block";
        goto L_0x0063;
    L_0x0083:
        monitor-exit(r5);
        return r6;
    L_0x0085:
        if (r2 == 0) goto L_0x0093;
    L_0x0087:
        r2.endTransaction();	 Catch:{ Exception -> 0x008b }
        goto L_0x0093;
    L_0x008b:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in insert messages inside finally block";
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x0094 }
    L_0x0093:
        throw r6;	 Catch:{ all -> 0x0094 }
    L_0x0094:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.c(java.util.List):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0066 A:{SYNTHETIC, Splitter:B:22:0x0066} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006d A:{SYNTHETIC, Splitter:B:27:0x006d} */
    public synchronized java.util.List<com.helpshift.conversation.activeconversation.message.j> c(long r13) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r2.<init>();	 Catch:{ all -> 0x0071 }
        r3 = r12.y;	 Catch:{ all -> 0x0071 }
        r3.getClass();	 Catch:{ all -> 0x0071 }
        r3 = "conversation_id";
        r2.append(r3);	 Catch:{ all -> 0x0071 }
        r3 = " = ?";
        r2.append(r3);	 Catch:{ all -> 0x0071 }
        r7 = r2.toString();	 Catch:{ all -> 0x0071 }
        r2 = 1;
        r8 = new java.lang.String[r2];	 Catch:{ all -> 0x0071 }
        r2 = 0;
        r13 = java.lang.String.valueOf(r13);	 Catch:{ all -> 0x0071 }
        r8[r2] = r13;	 Catch:{ all -> 0x0071 }
        r13 = r12.z;	 Catch:{ Exception -> 0x005c }
        r4 = r13.getReadableDatabase();	 Catch:{ Exception -> 0x005c }
        r13 = r12.y;	 Catch:{ Exception -> 0x005c }
        r5 = "messages";
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r13 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x005c }
        r14 = r13.moveToFirst();	 Catch:{ Exception -> 0x0057, all -> 0x0054 }
        if (r14 == 0) goto L_0x004e;
    L_0x0041:
        r14 = r12.c(r13);	 Catch:{ Exception -> 0x0057, all -> 0x0054 }
        r0.add(r14);	 Catch:{ Exception -> 0x0057, all -> 0x0054 }
        r14 = r13.moveToNext();	 Catch:{ Exception -> 0x0057, all -> 0x0054 }
        if (r14 != 0) goto L_0x0041;
    L_0x004e:
        if (r13 == 0) goto L_0x0069;
    L_0x0050:
        r13.close();	 Catch:{ all -> 0x0071 }
        goto L_0x0069;
    L_0x0054:
        r14 = move-exception;
        r1 = r13;
        goto L_0x006b;
    L_0x0057:
        r14 = move-exception;
        r1 = r13;
        goto L_0x005d;
    L_0x005a:
        r14 = move-exception;
        goto L_0x006b;
    L_0x005c:
        r14 = move-exception;
    L_0x005d:
        r13 = "Helpshift_ConverDB";
        r2 = "Error in read messages";
        com.helpshift.util.l.c(r13, r2, r14);	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0069;
    L_0x0066:
        r1.close();	 Catch:{ all -> 0x0071 }
    L_0x0069:
        monitor-exit(r12);
        return r0;
    L_0x006b:
        if (r1 == 0) goto L_0x0070;
    L_0x006d:
        r1.close();	 Catch:{ all -> 0x0071 }
    L_0x0070:
        throw r14;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r13 = move-exception;
        monitor-exit(r12);
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.c(long):java.util.List");
    }

    public synchronized void b(j jVar) {
        ContentValues c = c(jVar);
        StringBuilder stringBuilder = new StringBuilder();
        this.y.getClass();
        stringBuilder.append(BaseColumns._ID);
        stringBuilder.append(" = ?");
        String stringBuilder2 = stringBuilder.toString();
        String[] strArr = new String[]{String.valueOf(jVar.n)};
        try {
            SQLiteDatabase writableDatabase = this.z.getWritableDatabase();
            b bVar = this.y;
            writableDatabase.update("messages", c, stringBuilder2, strArr);
        } catch (Exception e) {
            l.c("Helpshift_ConverDB", "Error in update message", e);
        }
        return;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x009c A:{SYNTHETIC, Splitter:B:36:0x009c} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00aa A:{SYNTHETIC, Splitter:B:44:0x00aa} */
    public synchronized void d(java.util.List<com.helpshift.conversation.activeconversation.message.j> r9) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r9.size();	 Catch:{ all -> 0x00b7 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r8);
        return;
    L_0x0009:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00b7 }
        r0.<init>();	 Catch:{ all -> 0x00b7 }
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x00b7 }
        r1.<init>();	 Catch:{ all -> 0x00b7 }
        r2 = r9.iterator();	 Catch:{ all -> 0x00b7 }
    L_0x0017:
        r3 = r2.hasNext();	 Catch:{ all -> 0x00b7 }
        r4 = 0;
        if (r3 == 0) goto L_0x003a;
    L_0x001e:
        r3 = r2.next();	 Catch:{ all -> 0x00b7 }
        r3 = (com.helpshift.conversation.activeconversation.message.j) r3;	 Catch:{ all -> 0x00b7 }
        r5 = r8.c(r3);	 Catch:{ all -> 0x00b7 }
        r0.add(r5);	 Catch:{ all -> 0x00b7 }
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ all -> 0x00b7 }
        r3 = r3.n;	 Catch:{ all -> 0x00b7 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x00b7 }
        r5[r4] = r3;	 Catch:{ all -> 0x00b7 }
        r1.add(r5);	 Catch:{ all -> 0x00b7 }
        goto L_0x0017;
    L_0x003a:
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b7 }
        r3.<init>();	 Catch:{ all -> 0x00b7 }
        r5 = r8.y;	 Catch:{ all -> 0x00b7 }
        r5.getClass();	 Catch:{ all -> 0x00b7 }
        r5 = "_id";
        r3.append(r5);	 Catch:{ all -> 0x00b7 }
        r5 = " = ?";
        r3.append(r5);	 Catch:{ all -> 0x00b7 }
        r3 = r3.toString();	 Catch:{ all -> 0x00b7 }
        r5 = r8.z;	 Catch:{ Exception -> 0x0092 }
        r5 = r5.getWritableDatabase();	 Catch:{ Exception -> 0x0092 }
        r5.beginTransaction();	 Catch:{ Exception -> 0x008c, all -> 0x008a }
    L_0x005c:
        r2 = r9.size();	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        if (r4 >= r2) goto L_0x0078;
    L_0x0062:
        r2 = r8.y;	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r2 = "messages";
        r6 = r0.get(r4);	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r6 = (android.content.ContentValues) r6;	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r7 = r1.get(r4);	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r7 = (java.lang.String[]) r7;	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r5.update(r2, r6, r3, r7);	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        r4 = r4 + 1;
        goto L_0x005c;
    L_0x0078:
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x008c, all -> 0x008a }
        if (r5 == 0) goto L_0x00a6;
    L_0x007d:
        r5.endTransaction();	 Catch:{ Exception -> 0x0081 }
        goto L_0x00a6;
    L_0x0081:
        r9 = move-exception;
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update messages";
    L_0x0086:
        com.helpshift.util.l.c(r0, r1, r9);	 Catch:{ all -> 0x00b7 }
        goto L_0x00a6;
    L_0x008a:
        r9 = move-exception;
        goto L_0x00a8;
    L_0x008c:
        r9 = move-exception;
        r2 = r5;
        goto L_0x0093;
    L_0x008f:
        r9 = move-exception;
        r5 = r2;
        goto L_0x00a8;
    L_0x0092:
        r9 = move-exception;
    L_0x0093:
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update messages";
        com.helpshift.util.l.c(r0, r1, r9);	 Catch:{ all -> 0x008f }
        if (r2 == 0) goto L_0x00a6;
    L_0x009c:
        r2.endTransaction();	 Catch:{ Exception -> 0x00a0 }
        goto L_0x00a6;
    L_0x00a0:
        r9 = move-exception;
        r0 = "Helpshift_ConverDB";
        r1 = "Error in update messages";
        goto L_0x0086;
    L_0x00a6:
        monitor-exit(r8);
        return;
    L_0x00a8:
        if (r5 == 0) goto L_0x00b6;
    L_0x00aa:
        r5.endTransaction();	 Catch:{ Exception -> 0x00ae }
        goto L_0x00b6;
    L_0x00ae:
        r0 = move-exception;
        r1 = "Helpshift_ConverDB";
        r2 = "Error in update messages";
        com.helpshift.util.l.c(r1, r2, r0);	 Catch:{ all -> 0x00b7 }
    L_0x00b6:
        throw r9;	 Catch:{ all -> 0x00b7 }
    L_0x00b7:
        r9 = move-exception;
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.d(java.util.List):void");
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT COUNT(*) FROM ");
        stringBuilder.append(str);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(str2);
        stringBuilder.append(" LIMIT 1");
        return DatabaseUtils.longForQuery(sQLiteDatabase, stringBuilder.toString(), strArr) > 0;
    }

    private com.helpshift.conversation.dto.a.a a(Cursor cursor) {
        Cursor cursor2 = cursor;
        this.y.getClass();
        long j = cursor2.getLong(cursor2.getColumnIndex("profile_id"));
        this.y.getClass();
        String string = cursor2.getString(cursor2.getColumnIndex("form_name"));
        this.y.getClass();
        String string2 = cursor2.getString(cursor2.getColumnIndex("form_email"));
        this.y.getClass();
        String string3 = cursor2.getString(cursor2.getColumnIndex("description_draft"));
        this.y.getClass();
        long j2 = cursor2.getLong(cursor2.getColumnIndex("description_draft_timestamp"));
        this.y.getClass();
        c c = c(cursor2.getString(cursor2.getColumnIndex("attachment_draft")));
        this.y.getClass();
        int i = cursor2.getInt(cursor2.getColumnIndex("description_type"));
        this.y.getClass();
        String string4 = cursor2.getString(cursor2.getColumnIndex("archival_text"));
        this.y.getClass();
        String string5 = cursor2.getString(cursor2.getColumnIndex("reply_text"));
        this.y.getClass();
        boolean z = cursor2.getInt(cursor2.getColumnIndex("persist_message_box")) == 1;
        this.y.getClass();
        return new com.helpshift.conversation.dto.a.a(j, string, string2, string3, j2, c, i, string4, string5, z, cursor2.getString(cursor2.getColumnIndex("since")));
    }

    private ContentValues b(com.helpshift.conversation.dto.a.a aVar) {
        ContentValues contentValues = new ContentValues();
        this.y.getClass();
        contentValues.put("profile_id", Long.valueOf(aVar.a));
        this.y.getClass();
        contentValues.put("form_name", aVar.b);
        this.y.getClass();
        contentValues.put("form_email", aVar.c);
        this.y.getClass();
        contentValues.put("description_draft", aVar.d);
        this.y.getClass();
        contentValues.put("description_draft_timestamp", Long.valueOf(aVar.e));
        this.y.getClass();
        contentValues.put("description_type", Integer.valueOf(aVar.g));
        this.y.getClass();
        contentValues.put("archival_text", aVar.h);
        this.y.getClass();
        contentValues.put("reply_text", aVar.i);
        this.y.getClass();
        contentValues.put("persist_message_box", Integer.valueOf(aVar.j));
        this.y.getClass();
        contentValues.put("since", aVar.k);
        try {
            String a = a(aVar.f);
            this.y.getClass();
            contentValues.put("attachment_draft", a);
        } catch (JSONException e) {
            l.c("Helpshift_ConverDB", "Error in generating meta string for image attachment", e);
        }
        return contentValues;
    }

    private ContentValues c(com.helpshift.conversation.activeconversation.a aVar) {
        ContentValues contentValues = new ContentValues();
        this.y.getClass();
        contentValues.put("profile_id", Long.valueOf(aVar.r));
        this.y.getClass();
        contentValues.put("server_id", aVar.b);
        this.y.getClass();
        contentValues.put("publish_id", aVar.h);
        this.y.getClass();
        contentValues.put("title", aVar.c);
        this.y.getClass();
        contentValues.put("status", Integer.valueOf(aVar.d.getValue()));
        this.y.getClass();
        contentValues.put("message_cursor", aVar.k);
        this.y.getClass();
        contentValues.put("show_agent_name", Integer.valueOf(aVar.i));
        this.y.getClass();
        contentValues.put("start_new_conversation_action", Integer.valueOf(aVar.q));
        this.y.getClass();
        contentValues.put("created_at", aVar.e);
        this.y.getClass();
        contentValues.put("updated_at", aVar.f);
        try {
            String d = d(aVar);
            b bVar = this.y;
            contentValues.put("meta", d);
        } catch (JSONException e) {
            l.c("Helpshift_ConverDB", "Error in generating meta string for conversation", e);
        }
        return contentValues;
    }

    private String a(c cVar) throws JSONException {
        if (cVar == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("image_draft_orig_name", cVar.c);
        jSONObject.put("image_draft_orig_size", cVar.d);
        jSONObject.put("image_draft_file_path", cVar.b);
        jSONObject.put("image_copy_done", cVar.e);
        return jSONObject.toString();
    }

    private String d(com.helpshift.conversation.activeconversation.a aVar) throws JSONException {
        ConversationCSATState conversationCSATState = aVar.n;
        JSONObject jSONObject = new JSONObject();
        String str = aVar.p;
        int i = aVar.o;
        jSONObject.put("csat_feedback", str);
        jSONObject.put("csat_rating", i);
        jSONObject.put("csat_state", conversationCSATState.getValue());
        jSONObject.put("increment_message_count", aVar.l);
        jSONObject.put("ended_delegate_sent", aVar.m);
        return jSONObject.toString();
    }

    private com.helpshift.conversation.activeconversation.a b(Cursor cursor) {
        Cursor cursor2 = cursor;
        this.y.getClass();
        Long valueOf = Long.valueOf(cursor2.getLong(cursor2.getColumnIndex(BaseColumns._ID)));
        this.y.getClass();
        long j = cursor2.getLong(cursor2.getColumnIndex("profile_id"));
        this.y.getClass();
        String string = cursor2.getString(cursor2.getColumnIndex("server_id"));
        this.y.getClass();
        String string2 = cursor2.getString(cursor2.getColumnIndex("publish_id"));
        this.y.getClass();
        String string3 = cursor2.getString(cursor2.getColumnIndex("title"));
        this.y.getClass();
        int i = cursor2.getInt(cursor2.getColumnIndex("status"));
        this.y.getClass();
        boolean z = cursor2.getInt(cursor2.getColumnIndex("show_agent_name")) == 1;
        this.y.getClass();
        String string4 = cursor2.getString(cursor2.getColumnIndex("message_cursor"));
        this.y.getClass();
        boolean z2 = cursor2.getInt(cursor2.getColumnIndex("start_new_conversation_action")) == 1;
        b bVar = this.y;
        String string5 = cursor2.getString(cursor2.getColumnIndex("meta"));
        this.y.getClass();
        String string6 = cursor2.getString(cursor2.getColumnIndex("created_at"));
        this.y.getClass();
        String string7 = cursor2.getString(cursor2.getColumnIndex("updated_at"));
        com.helpshift.conversation.activeconversation.a aVar = r6;
        ConversationStatus fromInt = ConversationStatus.fromInt(i);
        String str = string5;
        boolean z3 = z2;
        com.helpshift.conversation.activeconversation.a aVar2 = new com.helpshift.conversation.activeconversation.a(string, string3, fromInt, string6, string7, string2, string4, z);
        aVar.a(valueOf.longValue());
        aVar.r = j;
        aVar.a(z3, false);
        a(aVar, str);
        return aVar;
    }

    private ContentValues c(j jVar) {
        ContentValues contentValues = new ContentValues();
        this.y.getClass();
        contentValues.put("server_id", jVar.i);
        this.y.getClass();
        contentValues.put("conversation_id", jVar.m);
        this.y.getClass();
        contentValues.put("body", jVar.j);
        this.y.getClass();
        contentValues.put("author_name", jVar.l);
        this.y.getClass();
        contentValues.put("created_at", jVar.k);
        this.y.getClass();
        contentValues.put("type", jVar.x.getValue());
        this.y.getClass();
        contentValues.put("md_state", Integer.valueOf(jVar.r));
        try {
            this.y.getClass();
            contentValues.put("meta", d(jVar));
        } catch (JSONException e) {
            l.c("Helpshift_ConverDB", "Error in generating meta string for message", e);
        }
        return contentValues;
    }

    private void a(com.helpshift.conversation.activeconversation.a aVar, String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("csat_rating", 0);
                int optInt2 = jSONObject.optInt("csat_state", ConversationCSATState.NONE.getValue());
                aVar.a(optInt, ConversationCSATState.fromInt(optInt2), jSONObject.optString("csat_feedback", null));
                aVar.b(jSONObject.optBoolean("increment_message_count", false), false);
                aVar.m = jSONObject.optBoolean("ended_delegate_sent", false);
            } catch (JSONException e) {
                l.c("Helpshift_ConverDB", "Error in parseAndSetMetaData", e);
            }
        }
    }

    private c c(String str) {
        Throwable e;
        if (str == null) {
            return null;
        }
        c cVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.optString("image_draft_orig_name", null);
            Long valueOf = Long.valueOf(jSONObject.optLong("image_draft_orig_size", -1));
            String optString = jSONObject.optString("image_draft_file_path", null);
            boolean optBoolean = jSONObject.optBoolean("image_copy_done", false);
            if (valueOf.longValue() == -1) {
                valueOf = null;
            }
            cVar = new c(optString, str, valueOf);
            try {
                cVar.e = optBoolean;
            } catch (JSONException e2) {
                e = e2;
            }
        } catch (JSONException e3) {
            e = e3;
            cVar = null;
            l.c("Helpshift_ConverDB", "Error in parseAndGetImageAttachmentDraft", e);
            return cVar;
        }
        return cVar;
    }

    private j c(Cursor cursor) {
        int i;
        long j;
        long j2;
        j oVar;
        long j3;
        Cursor cursor2 = cursor;
        this.y.getClass();
        long j4 = cursor2.getLong(cursor2.getColumnIndex(BaseColumns._ID));
        this.y.getClass();
        long j5 = cursor2.getLong(cursor2.getColumnIndex("conversation_id"));
        this.y.getClass();
        String string = cursor2.getString(cursor2.getColumnIndex("server_id"));
        this.y.getClass();
        String string2 = cursor2.getString(cursor2.getColumnIndex("body"));
        this.y.getClass();
        String string3 = cursor2.getString(cursor2.getColumnIndex("author_name"));
        this.y.getClass();
        String string4 = cursor2.getString(cursor2.getColumnIndex("meta"));
        this.y.getClass();
        String string5 = cursor2.getString(cursor2.getColumnIndex("type"));
        this.y.getClass();
        String string6 = cursor2.getString(cursor2.getColumnIndex("created_at"));
        this.y.getClass();
        int i2 = cursor2.getInt(cursor2.getColumnIndex("md_state"));
        MessageType fromValue = MessageType.fromValue(string5);
        JSONObject d = d(string4);
        switch (fromValue) {
            case USER_TEXT:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new o(string2, string6, string3);
                oVar.i = string;
                break;
            case ADMIN_TEXT:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new com.helpshift.conversation.activeconversation.message.b(string, string2, string6, string3);
                break;
            case ACCEPTED_APP_REVIEW:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new com.helpshift.conversation.activeconversation.message.a(string2, string6, string3, a(d));
                oVar.i = string;
                break;
            case REQUESTED_APP_REVIEW:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new k(string, string2, string6, string3, b(d));
                break;
            case FOLLOWUP_ACCEPTED:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new g(string2, string6, string3, a(d));
                oVar.i = string;
                break;
            case FOLLOWUP_REJECTED:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new h(string2, string6, string3, a(d));
                oVar.i = string;
                a((h) oVar, d);
                break;
            case CONFIRMATION_ACCEPTED:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new e(string2, string6, string3);
                oVar.i = string;
                break;
            case CONFIRMATION_REJECTED:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new f(string2, string6, string3);
                oVar.i = string;
                break;
            case SCREENSHOT:
                i = i2;
                j = j4;
                j2 = j5;
                b d2 = d(d);
                j nVar = new n(string2, string6, string3, d2.a, d2.g, d2.b, d2.c, d2.e);
                nVar.g = d2.d;
                nVar.i = string;
                nVar.b(a(d));
                oVar = nVar;
                break;
            case REQUESTED_SCREENSHOT:
                i = i2;
                j = j4;
                j2 = j5;
                oVar = new m(string, string2, string6, string3, b(d));
                break;
            case ADMIN_ATTACHMENT:
                i = i2;
                j = j4;
                j2 = j5;
                a c = c(d);
                oVar = new AdminAttachmentMessageDM(string, string2, string6, string3, c.e, c.a, c.c, c.b);
                oVar.g = c.d;
                oVar.b();
                break;
            case ADMIN_IMAGE_ATTACHMENT:
                b d3 = d(d);
                String str = d3.c;
                String str2 = d3.b;
                string5 = d3.g;
                i = i2;
                j = j4;
                String str3 = str2;
                j2 = j5;
                j jVar = oVar;
                String str4 = string5;
                b bVar = d3;
                oVar = new AdminImageAttachmentMessageDM(string, string2, string6, string3, str, str3, str4, d3.a, d3.e);
                jVar.g = bVar.d;
                jVar.h = bVar.h;
                jVar.b();
                oVar = jVar;
                break;
            case REQUEST_FOR_REOPEN:
                oVar = new com.helpshift.conversation.activeconversation.message.l(string, string2, string6, string3);
                oVar.a = b(d);
                i = i2;
                j = j4;
                j3 = j5;
                break;
            default:
                throw new UnsupportedOperationException("Message type not supported");
        }
        j3 = j2;
        oVar.m = Long.valueOf(j3);
        oVar.n = Long.valueOf(j);
        oVar.r = i;
        a(oVar, d);
        return oVar;
    }

    private void a(j jVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("read_at", "");
            String optString2 = jSONObject.optString("seen_cursor", null);
            String optString3 = jSONObject.optString("chat_launch_src", null);
            boolean optBoolean = jSONObject.optBoolean("seen_sync_status", false);
            jVar.q = optString2;
            jVar.p = optString3;
            jVar.s = optBoolean;
            jVar.o = optString;
        }
    }

    private JSONObject d(String str) {
        JSONObject jSONObject = null;
        if (str == null) {
            return null;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    private String a(JSONObject jSONObject) {
        return jSONObject == null ? null : jSONObject.optString("referredMessageId", null);
    }

    private boolean b(JSONObject jSONObject) {
        return jSONObject == null ? false : jSONObject.optBoolean("is_answered", false);
    }

    private a c(JSONObject jSONObject) {
        return jSONObject == null ? null : new a(jSONObject);
    }

    private b d(JSONObject jSONObject) {
        return jSONObject == null ? null : new b(jSONObject);
    }

    private void a(h hVar, JSONObject jSONObject) {
        if (jSONObject != null) {
            int optInt = jSONObject.optInt("rejected_reason");
            String optString = jSONObject.optString("rejected_conv_id", null);
            hVar.b = optInt;
            hVar.c = optString;
        }
    }

    private String d(j jVar) throws JSONException {
        JSONObject jSONObject;
        switch (jVar.x) {
            case ADMIN_TEXT:
                jSONObject = new JSONObject();
                a(jSONObject, jVar);
                break;
            case ACCEPTED_APP_REVIEW:
                jSONObject = new JSONObject();
                a(jSONObject, ((com.helpshift.conversation.activeconversation.message.a) jVar).a);
                break;
            case REQUESTED_APP_REVIEW:
                jSONObject = new JSONObject();
                a(jSONObject, ((k) jVar).a);
                a(jSONObject, jVar);
                break;
            case FOLLOWUP_ACCEPTED:
                jSONObject = new JSONObject();
                a(jSONObject, ((g) jVar).a);
                break;
            case FOLLOWUP_REJECTED:
                jSONObject = new JSONObject();
                a(jSONObject, (h) jVar);
                break;
            case SCREENSHOT:
                jSONObject = new JSONObject();
                a(jSONObject, (n) jVar);
                break;
            case REQUESTED_SCREENSHOT:
                jSONObject = new JSONObject();
                a(jSONObject, ((m) jVar).a);
                a(jSONObject, jVar);
                break;
            case ADMIN_ATTACHMENT:
                jSONObject = new JSONObject();
                a(jSONObject, (com.helpshift.conversation.activeconversation.message.c) jVar);
                a(jSONObject, jVar);
                break;
            case ADMIN_IMAGE_ATTACHMENT:
                jSONObject = new JSONObject();
                a(jSONObject, (i) jVar);
                a(jSONObject, jVar);
                break;
            case REQUEST_FOR_REOPEN:
                jSONObject = new JSONObject();
                a(jSONObject, ((com.helpshift.conversation.activeconversation.message.l) jVar).a);
                a(jSONObject, jVar);
                break;
            default:
                jSONObject = null;
                break;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    private void a(JSONObject jSONObject, j jVar) throws JSONException {
        jSONObject.put("seen_cursor", jVar.q);
        jSONObject.put("chat_launch_src", jVar.p);
        jSONObject.put("seen_sync_status", jVar.s);
        jSONObject.put("read_at", jVar.o);
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        jSONObject.put("referredMessageId", str);
    }

    private void a(JSONObject jSONObject, h hVar) throws JSONException {
        jSONObject.put("referredMessageId", hVar.a);
        jSONObject.put("rejected_reason", hVar.b);
        jSONObject.put("rejected_conv_id", hVar.c);
    }

    private void a(JSONObject jSONObject, boolean z) throws JSONException {
        jSONObject.put("is_answered", z);
    }

    private void a(JSONObject jSONObject, com.helpshift.conversation.activeconversation.message.c cVar) throws JSONException {
        jSONObject.put("content_type", cVar.c);
        jSONObject.put("file_name", cVar.d);
        jSONObject.put("filePath", cVar.g);
        jSONObject.put("url", cVar.e);
        jSONObject.put("size", cVar.f);
    }

    private void a(JSONObject jSONObject, n nVar) throws JSONException {
        a(jSONObject, (com.helpshift.conversation.activeconversation.message.c) nVar);
        jSONObject.put("thumbnail_url", nVar.b);
        jSONObject.put("referredMessageId", nVar.a);
    }

    private void a(JSONObject jSONObject, i iVar) throws JSONException {
        a(jSONObject, (com.helpshift.conversation.activeconversation.message.c) iVar);
        jSONObject.put("thumbnail_url", iVar.b);
        jSONObject.put("thumbnailFilePath", iVar.h);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f A:{SYNTHETIC, Splitter:B:26:0x005f} */
    /* JADX WARNING: Missing block: B:11:0x0041, code skipped:
            if (r0 != null) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0057, code skipped:
            if (r0 != null) goto L_0x0043;
     */
    public synchronized com.helpshift.conversation.activeconversation.message.j b(java.lang.String r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r0.<init>();	 Catch:{ all -> 0x0063 }
        r1 = r11.y;	 Catch:{ all -> 0x0063 }
        r1.getClass();	 Catch:{ all -> 0x0063 }
        r1 = "server_id";
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r1 = " = ?";
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r5 = r0.toString();	 Catch:{ all -> 0x0063 }
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0063 }
        r0 = 0;
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0063 }
        r6[r0] = r12;	 Catch:{ all -> 0x0063 }
        r12 = 0;
        r0 = r11.z;	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r2 = r0.getReadableDatabase();	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r0 = r11.y;	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r3 = "messages";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r0 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0047 }
        if (r1 == 0) goto L_0x0041;
    L_0x003c:
        r1 = r11.c(r0);	 Catch:{ Exception -> 0x0047 }
        r12 = r1;
    L_0x0041:
        if (r0 == 0) goto L_0x005a;
    L_0x0043:
        r0.close();	 Catch:{ all -> 0x0063 }
        goto L_0x005a;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0050;
    L_0x0049:
        r0 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
        goto L_0x005d;
    L_0x004e:
        r1 = move-exception;
        r0 = r12;
    L_0x0050:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in read message with serverId";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x005a;
    L_0x0059:
        goto L_0x0043;
    L_0x005a:
        monitor-exit(r11);
        return r12;
    L_0x005c:
        r12 = move-exception;
    L_0x005d:
        if (r0 == 0) goto L_0x0062;
    L_0x005f:
        r0.close();	 Catch:{ all -> 0x0063 }
    L_0x0062:
        throw r12;	 Catch:{ all -> 0x0063 }
    L_0x0063:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.b(java.lang.String):com.helpshift.conversation.activeconversation.message.j");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x005f A:{SYNTHETIC, Splitter:B:26:0x005f} */
    /* JADX WARNING: Missing block: B:11:0x0041, code skipped:
            if (r0 != null) goto L_0x0043;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0057, code skipped:
            if (r0 != null) goto L_0x0043;
     */
    public synchronized com.helpshift.conversation.activeconversation.message.j b(java.lang.Long r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r0.<init>();	 Catch:{ all -> 0x0063 }
        r1 = r11.y;	 Catch:{ all -> 0x0063 }
        r1.getClass();	 Catch:{ all -> 0x0063 }
        r1 = "_id";
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r1 = " = ?";
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r5 = r0.toString();	 Catch:{ all -> 0x0063 }
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0063 }
        r0 = 0;
        r12 = java.lang.String.valueOf(r12);	 Catch:{ all -> 0x0063 }
        r6[r0] = r12;	 Catch:{ all -> 0x0063 }
        r12 = 0;
        r0 = r11.z;	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r2 = r0.getReadableDatabase();	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r0 = r11.y;	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r3 = "messages";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r0 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004e, all -> 0x0049 }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0047 }
        if (r1 == 0) goto L_0x0041;
    L_0x003c:
        r1 = r11.c(r0);	 Catch:{ Exception -> 0x0047 }
        r12 = r1;
    L_0x0041:
        if (r0 == 0) goto L_0x005a;
    L_0x0043:
        r0.close();	 Catch:{ all -> 0x0063 }
        goto L_0x005a;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0050;
    L_0x0049:
        r0 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
        goto L_0x005d;
    L_0x004e:
        r1 = move-exception;
        r0 = r12;
    L_0x0050:
        r2 = "Helpshift_ConverDB";
        r3 = "Error in read message with localId";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x005a;
    L_0x0059:
        goto L_0x0043;
    L_0x005a:
        monitor-exit(r11);
        return r12;
    L_0x005c:
        r12 = move-exception;
    L_0x005d:
        if (r0 == 0) goto L_0x0062;
    L_0x005f:
        r0.close();	 Catch:{ all -> 0x0063 }
    L_0x0062:
        throw r12;	 Catch:{ all -> 0x0063 }
    L_0x0063:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.common.a.a.b(java.lang.Long):com.helpshift.conversation.activeconversation.message.j");
    }

    public synchronized void a() {
        this.z.a(this.z.getWritableDatabase());
    }
}
