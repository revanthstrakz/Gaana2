package com.gaana.models;

import com.gaana.models.Tracks.Track;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;

public class SmartDownloadsData extends BusinessObject {
    @SerializedName("S_CTA")
    private String S_CTA;
    @SerializedName("S_Text")
    private String S_Text;
    @SerializedName("Snackbar_CTA")
    private String Snackbar_CTA;
    @SerializedName("Snackbar_text")
    private String Snackbar_text;
    @SerializedName("count")
    private int count;
    @SerializedName("CTA")
    private String cta;
    @SerializedName("entityDescription")
    private String entityDescription;
    @SerializedName("frequency")
    private int frequency;
    @SerializedName("last_download")
    private String last_download;
    @SerializedName("smart_data")
    private HashMap<String, String> sd_keys;
    @SerializedName("Title")
    private String title;
    @SerializedName("tracks")
    private ArrayList<Track> tracks;

    public String getSnackbar_text() {
        return this.Snackbar_text;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSCTAText() {
        return this.S_CTA;
    }

    public String getCTAText() {
        return this.cta;
    }

    public String getSettingsMessage() {
        return this.S_Text;
    }

    public String getSnackbar_CTA() {
        return this.Snackbar_CTA;
    }

    public int getTrackCount() {
        return this.count;
    }

    public String getEntityDescription() {
        return this.entityDescription;
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public HashMap<String, String> getSDKeys() {
        return this.sd_keys;
    }
}
