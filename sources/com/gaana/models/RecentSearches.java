package com.gaana.models;

import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.view.item.SearchItemView.SearchCategory;
import com.managers.URLManager.BusinessObjectType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class RecentSearches implements Serializable {
    private static final long serialVersionUID = 1;
    private ArrayList<AutoComplete> mRecentSearches;

    public ArrayList<AutoComplete> getRecentSearcheItems() {
        return this.mRecentSearches;
    }

    public ArrayList<AutoComplete> getRecentSearchTrackItems() {
        ArrayList arrayList = new ArrayList();
        if (this.mRecentSearches != null) {
            Iterator it = this.mRecentSearches.iterator();
            while (it.hasNext()) {
                AutoComplete autoComplete = (AutoComplete) it.next();
                if (SearchCategory.valueOf(autoComplete.getType()) == SearchCategory.Track) {
                    arrayList.add(autoComplete);
                }
            }
        }
        return arrayList;
    }

    public void add(AutoComplete autoComplete) {
        autoComplete.setRecentSearch(true);
        if (this.mRecentSearches != null) {
            checkAndRemoveExistingEntry(autoComplete);
            this.mRecentSearches.add(0, autoComplete);
            int size = this.mRecentSearches.size();
            if (size > Constants.dh) {
                this.mRecentSearches.remove(size - 1);
                return;
            }
            return;
        }
        this.mRecentSearches = new ArrayList();
        this.mRecentSearches.add(autoComplete);
    }

    private void checkAndRemoveExistingEntry(AutoComplete autoComplete) {
        Iterator it = this.mRecentSearches.iterator();
        while (it.hasNext()) {
            AutoComplete autoComplete2 = (AutoComplete) it.next();
            if (autoComplete2.getType() == null && autoComplete.getType() == null && autoComplete2.getRawTitle().equalsIgnoreCase(autoComplete.getRawTitle())) {
                this.mRecentSearches.remove(autoComplete2);
                return;
            } else if (autoComplete2.getRawTitle().equalsIgnoreCase(autoComplete.getRawTitle()) && autoComplete2.getType() != null && autoComplete2.getType().equalsIgnoreCase(autoComplete.getType())) {
                this.mRecentSearches.remove(autoComplete2);
                return;
            }
        }
    }

    public void checkAndRemoveDeletedLocalEntry() {
        Iterator it = this.mRecentSearches.iterator();
        while (it.hasNext()) {
            AutoComplete autoComplete = (AutoComplete) it.next();
            if (autoComplete.isLocalMedia() && LocalMediaManager.getInstance(GaanaApplication.getContext()).getLocalItemById(getBusinessobjectType(autoComplete.getType()), autoComplete.getBusinessObjectId()) == null) {
                this.mRecentSearches.remove(autoComplete);
                return;
            }
        }
    }

    public BusinessObject deleteFromRecentSearches(String str, BusinessObjectType businessObjectType) {
        Iterator it = this.mRecentSearches.iterator();
        while (it.hasNext()) {
            AutoComplete autoComplete = (AutoComplete) it.next();
            if (autoComplete.getBusinessObjectId() != null && autoComplete.getBusinessObjectId().equalsIgnoreCase(str) && autoComplete.getType() != null && getBusinessobjectType(autoComplete.getType()) == businessObjectType) {
                this.mRecentSearches.remove(autoComplete);
                return autoComplete;
            }
        }
        return null;
    }

    private BusinessObjectType getBusinessobjectType(String str) {
        if (str.equalsIgnoreCase("Track")) {
            return BusinessObjectType.Tracks;
        }
        if (str.equalsIgnoreCase("Album")) {
            return BusinessObjectType.Albums;
        }
        if (str.equalsIgnoreCase("Artist")) {
            return BusinessObjectType.Artists;
        }
        return str.equalsIgnoreCase("Playlist") ? BusinessObjectType.Playlists : null;
    }
}
