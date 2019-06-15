package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class VideoFeedMetaData extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("count")
    private String count;
    @SerializedName("status")
    private int status;
    @SerializedName("video_feed")
    private ArrayList<VideoFeed> video_feed;

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public ArrayList<VideoFeed> getVideo_feed() {
        return this.video_feed;
    }

    public void setVideo_feed(ArrayList<VideoFeed> arrayList) {
        this.video_feed = arrayList;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
