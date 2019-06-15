package com.til.colombia.android.utils;

import com.til.colombia.android.service.ItemResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdUtil {
    private Map<String, ItemResponse> adMap = new HashMap();
    private Set<String> reqCodes = new HashSet();

    public ItemResponse getAd(String str) {
        return (ItemResponse) this.adMap.get(str);
    }

    public void putAd(String str, ItemResponse itemResponse) {
        this.adMap.put(str, itemResponse);
    }

    public void putReqCode(String str) {
        this.reqCodes.add(str);
    }

    public boolean isExist(String str) {
        return this.reqCodes.contains(str);
    }
}
