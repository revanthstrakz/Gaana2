package com.gaana.juke;

import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class JukePlaylist extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("dl_url")
    private String dlUrl;
    @SerializedName("admin")
    private boolean mAdmin;
    @SerializedName("curr_play_idx")
    private int mCurrentPlayingIndex;
    @SerializedName("error")
    private String mError;
    @SerializedName("party_tracks")
    private ArrayList<JukeTrack> mJukeTracks;
    @SerializedName("nick_name")
    private String mNickName;
    @SerializedName("pid")
    private String mPid;
    @SerializedName("pl_status")
    private long mPlStatus = -1;
    @SerializedName("status")
    private long mStatus;
    @SerializedName("sync_interval")
    private long mSyncInterval = 0;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("vote_enable")
    private boolean mVoteEnable;
    @SerializedName("playlist_owner")
    private String ownerName;
    @SerializedName("party_source")
    private String partySource;
    @SerializedName("ph_atw")
    private String phAtw;
    @SerializedName("qr_url")
    private String qrUrl;
    @SerializedName("source_playlist_id")
    private String sourcePlayListId;

    public String getName() {
        if (TextUtils.isEmpty(this.mTitle)) {
            return super.getName();
        }
        return Constants.b(this.mTitle);
    }

    public void setName(String str) {
        this.mTitle = str;
    }

    public String getRawName() {
        return this.mTitle;
    }

    public void setBusinessObjId(String str) {
        this.mPid = str;
    }

    public String getBusinessObjId() {
        return this.mPid;
    }

    public String getEnglishName() {
        return Constants.a(this.mTitle);
    }

    public boolean getAdmin() {
        return this.mAdmin;
    }

    public void setAdmin(boolean z) {
        this.mAdmin = z;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.mJukeTracks;
    }

    public void setPartyTracks(ArrayList<JukeTrack> arrayList) {
        this.mJukeTracks = arrayList;
    }

    public long getPlStatus() {
        return this.mPlStatus;
    }

    public void setPlStatus(long j) {
        this.mPlStatus = j;
    }

    public long getStatus() {
        return this.mStatus;
    }

    public void setStatus(long j) {
        this.mStatus = j;
    }

    public boolean getVoteEnable() {
        return this.mVoteEnable;
    }

    public void setVoteEnable(boolean z) {
        this.mVoteEnable = z;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public String getQrUrl() {
        return this.qrUrl;
    }

    public void setArrList(ArrayList<?> arrayList) {
        this.mJukeTracks = arrayList;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public String getError() {
        return this.mError;
    }

    public String getmTitle() {
        return this.mTitle;
    }

    public String getDlUrl() {
        return this.dlUrl;
    }

    public int getCurrentPlayingIndex() {
        return this.mCurrentPlayingIndex;
    }

    public void setmCurrentPlayingIndex(int i) {
        this.mCurrentPlayingIndex = i;
    }

    public void setOwnerName(String str) {
        this.ownerName = str;
    }

    public long getSyncIntervalInMillis() {
        if (this.mSyncInterval <= 0) {
            return 30000;
        }
        return this.mSyncInterval * 1000;
    }

    public String getPartySource() {
        return this.partySource;
    }

    public void setPartySource(String str) {
        this.partySource = str;
    }

    public String getSourcePlayListId() {
        return this.sourcePlayListId;
    }

    public void setSourcePlayListId(String str) {
        this.sourcePlayListId = str;
    }

    public String getAtw() {
        return this.atw;
    }

    public String getPhAtw() {
        return this.phAtw;
    }
}
