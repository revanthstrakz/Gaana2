package com.payu.custombrowser.bean;

import java.util.ArrayList;

public class ReviewOrderBundle {
    ArrayList<ReviewOrderData> a = new ArrayList();

    public ArrayList<ReviewOrderData> getReviewOrderDatas() {
        return this.a;
    }

    public void addOrderDetails(String str, String str2) {
        this.a.add(new ReviewOrderData(str, str2));
    }
}
