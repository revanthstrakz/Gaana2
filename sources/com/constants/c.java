package com.constants;

import android.support.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class c {
    public static final Boolean a = Boolean.valueOf(true);
    public static final Boolean b = Boolean.valueOf(true);
    public static String c = "https://api.gaana.com/user.php?type=track_payment_process";
    public static String d = "https://api.gaana.com/";
    public static String e = "https://api.gaana.com/";
    public static String f = "https://api.gaana.com/";
    public static String g = "https://api.gaana.com/";
    public static String h = "https://api.gaana.com/";
    public static String i = "https://api.gaana.com/";
    public static String j = "https://api.gaana.com/";
    public static String k = "https://api.gaana.com/";
    public static String l = "https://api.gaana.com/";
    public static String m = "https://api.gaana.com/";
    public static String n = "https://api.gaana.com/";
    public static String o;
    public static String p;
    public static String q;
    public static String r;
    public static String s;
    public static String t;
    public static String u;
    public static String v;
    public static String w;
    public static String x;
    private static Map<String, String> y = new HashMap();

    public static class a {
        public static String A = "Trial_Sponsored_Ad";
        public static String B = "Nudge_View";
        public static String C = "Radio_Header";
        public static String D = "Videos";
        public static String E = "Last Heard";
        public static String F = "User Activity";
        public static String G = "Today";
        public static String H = "This Week So Far";
        public static String I = "Radio Section";
        public static String J = "Made For You";
        public static String K = "Gaana Originals";
        public static String L = "Playlist for you";
        public static String M = "Popular in Your Language";
        public static String N = "Discover";
        public static String O = "Gaana Exclusives";
        public static String P = "Brand hubs";
        public static String a = "Top Charts";
        public static String b = "Featured Artists";
        public static String c = "New Releases";
        public static String d = "Editor's Pick";
        public static String e = "Radio";
        public static String f = "Radio Mirchi";
        public static String g = "Popular Gaana Radios";
        public static String h = "Artist Radios";
        public static String i = "Mood Radios";
        public static String j = "Latest Music";
        public static String k = "Retro & 90s Music";
        public static String l = "Gaana Specials";
        public static String m = "Gaana Recommends1";
        public static String n = "Gaana Recommends2";
        public static String o = "Gaana Recommends3";
        public static String p = "Gaana Recommends4";
        public static String q = "Gaana Recommends5";
        public static String r = "Gaana Recommends6";
        public static String s = "Referral";
        public static String t = "Songs you like";
        public static String u = "Ad";
        public static String v = "Login_Card";
        public static String w = "Trending Songs";
        public static String x = "Carousel";
        public static String y = "Carousel_2";
        public static String z = "Dummy_View";
    }

    public static class b {
        public static int a = 0;
        public static int b = 1;
        public static int c = 4;
        public static int d = 5;
    }

    public static class c {
        public static String a = "PL";
        public static String b = "AL";
        public static String c = "TR";
        public static String d = "AR";
        public static String e = "DL";
        public static String f = "VD";
        public static String g = "DS";
        public static String h = "AD";
        public static String i = "OC";
        public static String j = "VPL";
        public static String k = "PH";
        public static String l = "RS";
        public static String m = "MD";
        public static String n = "CR";
    }

    public static class d {
        public static String a = "PL";
        public static String b = "AL";
        public static String c = "RM";
        public static String d = "RL";
        public static String e = "HR";
    }

    static {
        y.put("artist_albums", d);
        y.put("album_detail_all", e);
        y.put("radio_gaana_detail", f);
        y.put("playlist_detail_all", g);
        y.put("album_detail", h);
        y.put("album_detail_info", i);
        y.put("playlist_detail_info", j);
        y.put("stream", k);
        y.put("playlist_detail", l);
        y.put("artist_tracks", m);
        y.put("artist_details_info", n);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail_all"));
        stringBuilder.append("album/detail/all?album_id=");
        o = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail_all"));
        stringBuilder.append("playlist/detail/all?playlist_id=");
        p = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_tracks"));
        stringBuilder.append("home/artist/tracks/");
        q = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_albums"));
        stringBuilder.append("home/artist/playlist-album/");
        r = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail"));
        stringBuilder.append("index.php?type=album&subtype=album_detail&album_id=");
        s = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail_info"));
        stringBuilder.append("index.php?type=album&subtype=album_detail_info&album_id=");
        t = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail_info"));
        stringBuilder.append("index.php?type=playlist&subtype=playlist_detail_info&playlist_id=");
        u = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("stream"));
        stringBuilder.append("getURLV1.php?");
        v = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail"));
        stringBuilder.append("index.php?type=playlist&subtype=playlist_detail&");
        w = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_details_info"));
        stringBuilder.append("index.php?type=artist&subtype=artist_details_info&artist_id=");
        x = stringBuilder.toString();
    }

    public static void a(@NonNull Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            if (y.containsKey(entry.getKey())) {
                y.put(entry.getKey(), entry.getValue());
            }
        }
        if (map.size() > 0) {
            a();
        }
    }

    private static void a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail_all"));
        stringBuilder.append("album/detail/all?album_id=");
        o = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail_all"));
        stringBuilder.append("playlist/detail/all?playlist_id=");
        p = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_tracks"));
        stringBuilder.append("home/artist/tracks/");
        q = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_albums"));
        stringBuilder.append("home/artist/playlist-album/");
        r = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail"));
        stringBuilder.append("index.php?type=album&subtype=album_detail&album_id=");
        s = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("album_detail_info"));
        stringBuilder.append("index.php?type=album&subtype=album_detail_info&album_id=");
        t = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail_info"));
        stringBuilder.append("index.php?type=playlist&subtype=playlist_detail_info&playlist_id=");
        u = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("stream"));
        stringBuilder.append("getURLV1.php?");
        v = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("playlist_detail"));
        stringBuilder.append("index.php?type=playlist&subtype=playlist_detail&");
        w = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append((String) y.get("artist_details_info"));
        stringBuilder.append("index.php?type=artist&subtype=artist_details_info&artist_id=");
        x = stringBuilder.toString();
    }
}
