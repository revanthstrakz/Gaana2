package com.youtube;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import java.util.ArrayList;

public class YouTubeVideos extends BusinessObject {
    private static final long serialVersionUID = 1;

    public static class YouTubeVideo extends BusinessObject {
        private static final long serialVersionUID = 1;
        public String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private int f = 0;
        private String g;
        private double h;
        private double i;
        private ArrayList<Artist> j;
        private String k = "";
        private String l = "";
        private long m = 0;

        public String a() {
            return this.d;
        }

        public void a(String str) {
            this.d = str;
        }

        public String b() {
            return Constants.a(this.b, this.e);
        }

        public void b(String str) {
            this.b = str;
        }

        public String c() {
            return this.a;
        }

        public void c(String str) {
            this.a = str;
        }

        public String d() {
            return this.c;
        }

        public void d(String str) {
            this.c = str;
        }

        public void setLanguage(String str) {
            this.e = str;
        }

        public String getLanguage() {
            return this.e;
        }

        public int e() {
            return this.f;
        }

        public void a(int i) {
            this.f = i;
        }

        public void e(String str) {
            this.g = str;
        }

        public long f() {
            return !TextUtils.isEmpty(this.g) ? Long.parseLong(this.g) : -1;
        }

        public void a(double d) {
            this.h = d;
        }

        public void b(double d) {
            this.i = d;
        }

        public String g() {
            String str = "";
            if (this.j == null) {
                return str;
            }
            for (int i = 0; i < this.j.size(); i++) {
                StringBuilder stringBuilder;
                if (i != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(", ");
                    str = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(Constants.a(((Artist) this.j.get(i)).name, this.e));
                str = stringBuilder.toString();
            }
            return str;
        }

        public void a(ArrayList<Artist> arrayList) {
            this.j = arrayList;
        }

        public void f(String str) {
            this.k = str;
        }

        public void g(String str) {
            this.l = str;
        }

        public long h() {
            return this.m;
        }

        public void a(long j) {
            this.m = j;
        }

        public static YouTubeVideo a(Track track) {
            if (track == null) {
                return null;
            }
            YouTubeVideo youTubeVideo = new YouTubeVideo();
            youTubeVideo.b(track.getTrackTitle());
            youTubeVideo.setBusinessObjId(track.getVideoId());
            youTubeVideo.d(track.getAtw());
            youTubeVideo.setLanguage(track.getLanguage());
            youTubeVideo.e(String.valueOf(track.getVideoExpiryTime()));
            if (!TextUtils.isEmpty(track.getVerticalUrl())) {
                youTubeVideo.a(track.getVerticalUrl());
                youTubeVideo.a(1);
            } else if (!TextUtils.isEmpty(track.getHorizontalUrl())) {
                youTubeVideo.a(track.getHorizontalUrl());
                youTubeVideo.a(2);
            } else if (!TextUtils.isEmpty(track.getYoutubeId())) {
                youTubeVideo.a(track.getYoutubeId());
                youTubeVideo.a(0);
            }
            return youTubeVideo;
        }
    }
}
