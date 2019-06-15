package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.constants.c.c;
import com.google.gson.annotations.SerializedName;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class UserRecentActivity extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("entities")
    private ArrayList<Item> entities;
    @SerializedName("entityDescription")
    private String entityDescription;
    @SerializedName("status")
    private int status;
    private String timeStamp;

    public ArrayList<Item> getEntities() {
        return this.entities;
    }

    public ArrayList<Item> getArrListBusinessObj() {
        return this.entities;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String str) {
        this.timeStamp = str;
    }

    public void addEntity(Item item) {
        if (!TextUtils.isEmpty(item.getEntityId())) {
            if (this.entities != null) {
                checkAndRemoveExistingEntry(item);
                this.entities.add(0, item);
                int size = this.entities.size();
                if (size > Constants.f15do) {
                    this.entities.remove(size - 1);
                }
            } else {
                this.entities = new ArrayList();
                this.entities.add(item);
            }
        }
    }

    private void checkAndRemoveExistingEntry(Item item) {
        if (this.entities != null && this.entities.size() > 0) {
            Iterator it = this.entities.iterator();
            while (it.hasNext()) {
                Item item2 = (Item) it.next();
                if (item2.getEntityId().equalsIgnoreCase(item.getEntityId()) && item2.getEntityType().equalsIgnoreCase(item.getEntityType())) {
                    this.entities.remove(item2);
                    return;
                }
            }
        }
    }

    public ArrayList<Item> getTracksInUserRecentActivity() {
        ArrayList arrayList = new ArrayList();
        if (this.entities != null && this.entities.size() > 0) {
            Iterator it = this.entities.iterator();
            while (it.hasNext()) {
                Item item = (Item) it.next();
                if (item.getEntityType().equalsIgnoreCase(c.c)) {
                    arrayList.add(item);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Item> getTracksInUserRecentActivity(int i) {
        ArrayList arrayList = new ArrayList();
        if (this.entities != null && this.entities.size() > 0) {
            Iterator it = this.entities.iterator();
            while (it.hasNext()) {
                Item item = (Item) it.next();
                if (arrayList.size() == i) {
                    break;
                } else if (item.getEntityType().equalsIgnoreCase(c.c)) {
                    arrayList.add(item);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Item> getAlbumsInUserRecentActivity() {
        ArrayList arrayList = new ArrayList();
        if (this.entities != null && this.entities.size() > 0) {
            Iterator it = this.entities.iterator();
            while (it.hasNext()) {
                Item item = (Item) it.next();
                if (item.getEntityType().equalsIgnoreCase(c.b)) {
                    arrayList.add(item);
                }
            }
        }
        return arrayList;
    }

    public BusinessObject deleteFromRecentlyPlayed(String str) {
        if (this.entities != null && this.entities.size() > 0) {
            Iterator it = this.entities.iterator();
            while (it.hasNext()) {
                Item item = (Item) it.next();
                if (item.getEntityId().equalsIgnoreCase(str)) {
                    this.entities.remove(item);
                    return Util.g(item);
                }
            }
        }
        return null;
    }
}
