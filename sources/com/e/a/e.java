package com.e.a;

import com.playercache.TrackCacheQueueManager.CACHING_BEHAVIOUR;

public class e {

    public static class a {
        public static String a = "downloadsync_details";
    }

    public static class b {
        public static String a = "feed_table";
    }

    public static class c {
        public static int a = 0;
        public static int b = 1;
        public static int c = 2;
    }

    public static class d {
        public static String a = "last_heard_songs";
    }

    public static class e {
        public static String a = "local_media_activity";
    }

    public static class f {
        public static String a = "local_playlist_metadata";
    }

    public static class g {
        public static String a = "local_playlist_track_mapping";
    }

    public static class h {
        public static String a = "playlist_details";
    }

    public static class i {
        public static String a = "song_identify_hostory";
    }

    public static class j {
        public static String a = "track_cache_queue_table";
    }

    public static class k {
        public static String a = "track_details";
    }

    public static class l {
        public static String a = "table_track_metadata";
    }

    public static class m {
        public static String a = "track_playlist_relation";
    }

    public static class n {
        public static String a = "local_playlist_favourite";
    }

    public static String a(String str) {
        StringBuilder stringBuilder;
        if (str.equals(l.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(l.a);
            stringBuilder.append(" (");
            stringBuilder.append("track_id");
            stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY,");
            stringBuilder.append("track_metadata");
            stringBuilder.append(" TEXT NOT NULL, ");
            stringBuilder.append("track_name");
            stringBuilder.append(" TEXT NOT NULL, ");
            stringBuilder.append("track_language");
            stringBuilder.append(" TEXT NOT NULL, ");
            stringBuilder.append("artist_name");
            stringBuilder.append(" TEXT NOT NULL,");
            stringBuilder.append("video_link");
            stringBuilder.append(" TEXT, ");
            stringBuilder.append("download_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            stringBuilder.append("offline_play_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            stringBuilder.append("offline_play_count");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT (0),");
            stringBuilder.append("parental_warn");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT (0),");
            stringBuilder.append("has_downloaded");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0),");
            stringBuilder.append("smart_download");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT (0),");
            stringBuilder.append("free_download");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT (0),");
            stringBuilder.append("album_name");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("track_artwork");
            stringBuilder.append(" TEXT)");
            return stringBuilder.toString();
        } else if (str.equals(k.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(k.a);
            stringBuilder.append(" (");
            stringBuilder.append("playlist_id");
            stringBuilder.append(" INTEGER NOT NULL ,");
            stringBuilder.append("track_id");
            stringBuilder.append(" INTEGER NOT NULL DEFAULT (-1) ,");
            stringBuilder.append("track_position_in_playlist");
            stringBuilder.append(" INTEGER NOT NULL,");
            stringBuilder.append("has_downloaded");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0),");
            stringBuilder.append(" PRIMARY KEY('playlist_id','track_id'))");
            return stringBuilder.toString();
        } else if (str.equals(h.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(h.a);
            stringBuilder.append(" (");
            stringBuilder.append("playlist_id");
            stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY,");
            stringBuilder.append("playlist_content");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("playlist_name");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("artist_name");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("playlist_type");
            stringBuilder.append(" INTEGER,");
            stringBuilder.append("download_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            stringBuilder.append("download_status");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT 1)");
            return stringBuilder.toString();
        } else if (str.equals(a.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(a.a);
            stringBuilder.append(" (");
            stringBuilder.append("business_id");
            stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY,");
            stringBuilder.append("entity_type");
            stringBuilder.append(" INTEGER,");
            stringBuilder.append("sync_status");
            stringBuilder.append(" INTEGER NOT NULL  DEFAULT 0,");
            stringBuilder.append("sync_type");
            stringBuilder.append(" INTEGER)");
            return stringBuilder.toString();
        } else if (str.equals(f.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(f.a);
            stringBuilder.append(" (");
            stringBuilder.append("id");
            stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
            stringBuilder.append("playlist_id");
            stringBuilder.append(" INTEGER UNIQUE,");
            stringBuilder.append("local_playlist_id");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("name");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("language");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("has_synced");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0),");
            stringBuilder.append("last_sync_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            stringBuilder.append("time_stamp");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,");
            stringBuilder.append("playlist_metadata");
            stringBuilder.append(" TEXT)");
            return stringBuilder.toString();
        } else if (str.equals(g.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(g.a);
            stringBuilder.append(" (");
            stringBuilder.append("id");
            stringBuilder.append(" INTEGER NOT NULL ,");
            stringBuilder.append("track_id");
            stringBuilder.append(" INTEGER ,");
            stringBuilder.append("track_position_in_playlist");
            stringBuilder.append(" INTEGER,");
            stringBuilder.append("is_local");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0),");
            stringBuilder.append("added_on");
            stringBuilder.append(" TIMESTAMP,");
            stringBuilder.append("track_metadata");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("has_synced");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0),");
            stringBuilder.append(" PRIMARY KEY('id','track_id') ON CONFLICT REPLACE)");
            return stringBuilder.toString();
        } else if (str.equals(n.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(n.a);
            stringBuilder.append(" (");
            stringBuilder.append("id");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("business_object");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("favourite_status");
            stringBuilder.append(" INTEGER,");
            stringBuilder.append("has_synced");
            stringBuilder.append(" BOOL NOT NULL  DEFAULT (0))");
            return stringBuilder.toString();
        } else if (str.equals(e.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(e.a);
            stringBuilder.append(" (");
            stringBuilder.append("item_id");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("item_name");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("item_parent_id");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("item_parent_name");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("item_parent_type");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("item_artwork");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("item_activity_type");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("item_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            return stringBuilder.toString();
        } else if (str.equals(d.a)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
            stringBuilder.append(d.a);
            stringBuilder.append(" (");
            stringBuilder.append("duration_time");
            stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,");
            stringBuilder.append("play_count");
            stringBuilder.append(" INTEGER ,");
            stringBuilder.append("track_artwork");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("track_id");
            stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY ,");
            stringBuilder.append("track_name");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("track_language");
            stringBuilder.append(" TEXT,");
            stringBuilder.append("artist_name");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("time_stamp");
            stringBuilder.append(" LONG ,");
            stringBuilder.append("track_source");
            stringBuilder.append(" TEXT ,");
            stringBuilder.append("track_metadata");
            stringBuilder.append(" TEXT)");
            return stringBuilder.toString();
        } else if (str.equals("local_favorite_table")) {
            return "CREATE TABLE IF NOT EXISTS local_favorite_table (id TEXT ,business_object TEXT ,name TEXT ,album_id TEXT ,album_name TEXT ,artist_names TEXT ,artwork TEXT ,popularity INTEGER ,type TEXT ,favourite_status INTEGER,added_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,has_synced BOOL NOT NULL  DEFAULT (0),PRIMARY KEY('id','type') ON CONFLICT REPLACE)";
        } else {
            if (str.equals("social_feed_table")) {
                return "CREATE TABLE IF NOT EXISTS social_feed_table (feed_data TEXT ,feed_type TEXT ,gaana_feed_id TEXT ,position INTEGER)";
            }
            if (str.equals(i.a)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
                stringBuilder.append(i.a);
                stringBuilder.append(" (");
                stringBuilder.append("duration_time");
                stringBuilder.append(" TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,");
                stringBuilder.append("track_id");
                stringBuilder.append(" INTEGER NOT NULL PRIMARY KEY ,");
                stringBuilder.append("track_name");
                stringBuilder.append(" TEXT,");
                stringBuilder.append("track_language");
                stringBuilder.append(" TEXT,");
                stringBuilder.append("time_stamp");
                stringBuilder.append(" LONG ,");
                stringBuilder.append("track_metadata");
                stringBuilder.append(" TEXT)");
                return stringBuilder.toString();
            } else if (!str.equals(j.a)) {
                return null;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
                stringBuilder.append(j.a);
                stringBuilder.append(" (");
                stringBuilder.append("track_id");
                stringBuilder.append(" TEXT ,");
                stringBuilder.append("track_cache_behaviour");
                stringBuilder.append(" INTEGER DEFAULT (");
                stringBuilder.append(CACHING_BEHAVIOUR.PARTIAL_CACHE.ordinal());
                stringBuilder.append("),");
                stringBuilder.append("track_cache_status");
                stringBuilder.append(" INTEGER,");
                stringBuilder.append("track_metadata");
                stringBuilder.append(" TEXT,");
                stringBuilder.append("PRIMARY KEY('track_id') ON CONFLICT REPLACE)");
                return stringBuilder.toString();
            }
        }
    }
}
