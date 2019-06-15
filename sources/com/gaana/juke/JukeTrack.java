package com.gaana.juke;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;

public class JukeTrack extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("curr_play_idx")
    private int curPlayingIndex;
    @SerializedName("added_by")
    private String mAddedBy;
    @SerializedName("play_status")
    private long mPlayStatus = 0;
    @SerializedName("track_id")
    private String mTrackId;
    @SerializedName("track_title")
    private String mTrackTitle;
    @SerializedName("vote_count")
    private long mVoteCount;
    @SerializedName("voted_by")
    private boolean mVotedBy;
    @SerializedName("qr_url")
    private String qrUrl;
    private String subtitleText;

    public String getAddedBy() {
        return this.mAddedBy;
    }

    public void setAddedBy(String str) {
        this.mAddedBy = str;
    }

    public long getPlayStatus() {
        return this.mPlayStatus;
    }

    public void setPlayStatus(long j) {
        this.mPlayStatus = j;
    }

    public void setBusinessObjId(String str) {
        this.mTrackId = str;
    }

    public String getBusinessObjId() {
        if (TextUtils.isEmpty(this.mTrackId)) {
            return super.getBusinessObjId();
        }
        return this.mTrackId;
    }

    public void setName(String str) {
        this.mTrackTitle = str;
    }

    public String getEnglishName() {
        return Constants.a(this.mTrackTitle);
    }

    public String getName() {
        return Constants.a(this.mTrackTitle);
    }

    public String getRawName() {
        return this.mTrackTitle;
    }

    public long getVoteCount() {
        return this.mVoteCount;
    }

    public void setVoteCount(long j) {
        this.mVoteCount = j;
    }

    public boolean getVotedBy() {
        return this.mVotedBy;
    }

    public void setVotedBy(boolean z) {
        this.mVotedBy = z;
    }

    public String getQrUrl() {
        return this.qrUrl;
    }

    public void setSubtitleText(String str) {
        this.subtitleText = str;
    }

    public String getSubtitleText() {
        return this.subtitleText;
    }

    public int getCurrentPlayingIndex() {
        return this.curPlayingIndex;
    }
}
