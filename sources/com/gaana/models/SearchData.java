package com.gaana.models;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchData implements Serializable {
    private static final long serialVersionUID = 1;
    private ArrayList<SearchEvents> mSearchEvents;

    public static class SearchEvents implements Serializable {
        private static final long serialVersionUID = 1;
        private int actionDetailId;
        private int actionTypeId;
        private String attemptId = "";
        private String comments;
        private String itemID;
        private int itemType;
        private String keyword;
        private int position;
        private String searchReqId;
        private String sessionId = "";
        private long timestamp;

        public SearchEvents(String str, String str2, String str3, int i, int i2, int i3, String str4, int i4, String str5, String str6, long j) {
            this.searchReqId = str;
            this.sessionId = str2;
            this.attemptId = str3;
            this.actionTypeId = i;
            this.actionDetailId = i2;
            this.itemType = i3;
            this.itemID = str4;
            this.position = i4;
            this.keyword = str5;
            this.comments = str6;
            this.timestamp = j;
        }

        public String getSearchReqId() {
            return this.searchReqId;
        }

        public int getActionTypeId() {
            return this.actionTypeId;
        }

        public int getActionDetailId() {
            return this.actionDetailId;
        }

        public int getItemType() {
            return this.itemType;
        }

        public String getItemID() {
            return this.itemID;
        }

        public int getPosition() {
            return this.position;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public String getComments() {
            return this.comments;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public String getAttemptId() {
            return this.attemptId;
        }

        public String getSessionId() {
            return this.sessionId;
        }
    }

    public ArrayList<SearchEvents> getSearchEvents() {
        return this.mSearchEvents;
    }

    public void add(SearchEvents searchEvents) {
        if (this.mSearchEvents == null) {
            this.mSearchEvents = new ArrayList();
        }
        this.mSearchEvents.add(searchEvents);
    }
}
