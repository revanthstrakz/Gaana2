package com.helpshift.common.a;

import android.support.annotation.NonNull;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class b {
    public static final Integer a = Integer.valueOf(5);
    final String A = "author_name";
    final String B = "type";
    final String C = "meta";
    final String D = "md_state";
    private String E = "CREATE TABLE issues ( _id INTEGER PRIMARY KEY AUTOINCREMENT,server_id TEXT, publish_id TEXT, profile_id TEXT NOT NULL, title TEXT NOT NULL,status INTEGER NOT NULL,show_agent_name INTEGER,message_cursor TEXT,start_new_conversation_action INTEGER,meta TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL );";
    private String F = "CREATE TABLE conversation_inbox ( profile_id TEXT PRIMARY KEY NOT NULL, form_name TEXT,form_email TEXT,description_draft TEXT,description_draft_timestamp TEXT,attachment_draft TEXT,description_type TEXT,archival_text TEXT, reply_text TEXT, persist_message_box INT, since TEXT );";
    private String G = "CREATE TABLE messages ( _id INTEGER PRIMARY KEY AUTOINCREMENT, server_id TEXT, conversation_id TEXT, body TEXT, author_name TEXT, type TEXT, meta TEXT, created_at TEXT, md_state INTEGER  );";
    private final String H = "CREATE INDEX SERVER_IDX ON messages(server_id)";
    final String b = "issues";
    final String c = BaseColumns._ID;
    final String d = "server_id";
    final String e = "publish_id";
    final String f = "profile_id";
    final String g = "title";
    final String h = "created_at";
    final String i = "updated_at";
    final String j = "status";
    final String k = "show_agent_name";
    final String l = "message_cursor";
    final String m = "start_new_conversation_action";
    final String n = "conversation_inbox";
    final String o = "form_name";
    final String p = "form_email";
    final String q = "description_draft";
    final String r = "description_draft_timestamp";
    final String s = "attachment_draft";
    final String t = "description_type";
    final String u = "archival_text";
    final String v = "reply_text";
    final String w = "persist_message_box";
    final String x = "since";
    final String y = "conversation_id";
    final String z = "body";

    @NonNull
    public List<String> a() {
        return new ArrayList(Arrays.asList(new String[]{this.E, this.F, this.G, "CREATE INDEX SERVER_IDX ON messages(server_id)"}));
    }

    @NonNull
    public List<String> b() {
        return new ArrayList(Arrays.asList(new String[]{"DROP TABLE IF EXISTS issues", "DROP TABLE IF EXISTS conversation_inbox", "DROP TABLE IF EXISTS messages"}));
    }
}
