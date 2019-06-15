package com.gaana.revampeddetail.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RevampedCarouselData {
    @SerializedName("carousel_data")
    private ArrayList<CarouselCardData> carousel_data;

    public static class CarouselCardData {
        @SerializedName("card_type")
        private int card_type;
        @SerializedName("detail_entities")
        private ArrayList<CarouselDetailEntity> detail_entities;

        public int getCard_type() {
            return this.card_type;
        }

        public void setCard_type(int i) {
            this.card_type = i;
        }

        public ArrayList<CarouselDetailEntity> getDetail_entities() {
            return this.detail_entities;
        }

        public void setDetail_entities(ArrayList<CarouselDetailEntity> arrayList) {
            this.detail_entities = arrayList;
        }
    }

    public static class CarouselDetailEntity {
        @SerializedName("detail_entity_info")
        private DetailEntityInfo detail_entity_info;
        @SerializedName("weightage")
        private int weightage;

        public int getWeightage() {
            return this.weightage;
        }

        public void setWeightage(int i) {
            this.weightage = i;
        }

        public DetailEntityInfo getDetail_entity_info() {
            return this.detail_entity_info;
        }

        public void setDetail_entity_info(DetailEntityInfo detailEntityInfo) {
            this.detail_entity_info = detailEntityInfo;
        }
    }

    public static class DetailEntityInfo {
        @SerializedName("detail_entity_atw")
        private String detail_entity_atw;
        @SerializedName("detail_entity_desc")
        private String detail_entity_desc;
        @SerializedName("detail_entity_id")
        private String detail_entity_id;
        @SerializedName("detail_entity_title")
        private String detail_entity_title;
        @SerializedName("detail_entity_type")
        private String detail_entity_type;
        @SerializedName("detail_entity_url")
        private String detail_entity_url;

        public String getDetail_entity_desc() {
            return this.detail_entity_desc;
        }

        public void setDetail_entity_desc(String str) {
            this.detail_entity_desc = str;
        }

        public String getDetail_entity_title() {
            return this.detail_entity_title;
        }

        public void setDetail_entity_title(String str) {
            this.detail_entity_title = str;
        }

        public String getDetail_entity_atw() {
            return this.detail_entity_atw;
        }

        public void setDetail_entity_atw(String str) {
            this.detail_entity_atw = str;
        }

        public String getDetail_entity_url() {
            return this.detail_entity_url;
        }

        public void setDetail_entity_url(String str) {
            this.detail_entity_url = str;
        }

        public String getDetail_entity_id() {
            return this.detail_entity_id;
        }

        public void setDetail_entity_id(String str) {
            this.detail_entity_id = str;
        }

        public String getDetail_entity_type() {
            return this.detail_entity_type;
        }

        public void setDetail_entity_type(String str) {
            this.detail_entity_type = str;
        }
    }

    public ArrayList<CarouselCardData> getCarousel_data() {
        return this.carousel_data;
    }

    public void setCarousel_data(ArrayList<CarouselCardData> arrayList) {
        this.carousel_data = arrayList;
    }
}
