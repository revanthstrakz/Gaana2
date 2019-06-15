package com.gaana.models;

import com.gaana.models.SearchAutoSuggests.AutoComplete;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TrendingSearches extends BusinessObject {
    @SerializedName("topTrending")
    private ArrayList<AutoComplete> mArrSearchTrending;

    public ArrayList<AutoComplete> getTrendingSearchItems() {
        return this.mArrSearchTrending;
    }

    public void setTrendingSearchItems(ArrayList<AutoComplete> arrayList) {
        this.mArrSearchTrending = arrayList;
    }
}
