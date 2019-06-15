package com.gaana.models;

import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class PersonalizedTags extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("personalized_tags")
    private ArrayList<PersonalizedTag> personalizedTags = null;

    public static class PersonalizedTag extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("button_desc")
        private String buttonDesc;
        @SerializedName("button_text")
        private String buttonText;
        @SerializedName("button_url")
        private String buttonUrl;
        @SerializedName("color_bottom")
        private String colorBottom;
        @SerializedName("color_top")
        private String colorTop;
        @SerializedName("entities")
        private ArrayList<Item> items = null;
        @SerializedName("sub_title")
        private String subTitle;
        @SerializedName("title")
        private String title;

        public String getTitle() {
            return Constants.b(this.title);
        }

        public String getEnglishName() {
            return Constants.a(this.title);
        }

        public String getName() {
            return Constants.b(this.title);
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getSubTitle() {
            return Constants.b(this.subTitle);
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }

        public String getColorTop() {
            return this.colorTop;
        }

        public void setColorTop(String str) {
            this.colorTop = str;
        }

        public String getColorBottom() {
            return this.colorBottom;
        }

        public void setColorBottom(String str) {
            this.colorBottom = str;
        }

        public ArrayList<Item> getItems() {
            return this.items;
        }

        public ArrayList<?> getArrListBusinessObj() {
            return this.items;
        }

        public void setItems(ArrayList<Item> arrayList) {
            this.items = arrayList;
        }

        public String getButtonUrl() {
            return this.buttonUrl;
        }

        public void setButtonUrl(String str) {
            this.buttonUrl = str;
        }

        public String getButtonText() {
            return this.buttonText;
        }

        public void setButtonText(String str) {
            this.buttonText = str;
        }

        public String getButtonDesc() {
            return this.buttonDesc;
        }

        public void setButtonDesc(String str) {
            this.buttonDesc = str;
        }
    }

    public ArrayList<PersonalizedTag> getPersonalizedTags() {
        return this.personalizedTags;
    }

    public void setPersonalizedTags(ArrayList<PersonalizedTag> arrayList) {
        this.personalizedTags = arrayList;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.personalizedTags;
    }
}
