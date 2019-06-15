package com.gaana.models;

public class FavoriteItem {
    private String id;
    private boolean isFav;
    private String type;

    public FavoriteItem(String str, String str2, boolean z) {
        this.id = str;
        this.type = str2;
        this.isFav = z;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isFavorite() {
        return this.isFav;
    }
}
