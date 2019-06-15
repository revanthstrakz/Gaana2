package com.gaana.revampeddetail.manager;

import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.constants.Constants.REVAMPED_DETAIL_SECTION_TYPE;
import com.gaana.models.Items;
import com.gaana.revampeddetail.model.RevampedDetailObject.RevampedSectionData;
import com.gaana.revampeddetail.model.RevampedEntityFeedData;
import com.gaana.revampeddetail.model.RevampedListAdasCard;
import com.gaana.revampeddetail.model.RevampedSimilarAlbumEntityInfo;
import com.i.i;
import com.managers.URLManager;
import java.util.ArrayList;
import java.util.HashMap;

public class RevampedDetailSectionDataManger {
    HashMap<Integer, SectionResponse> responseHashMap = new HashMap();

    public enum SECTION_RESPONSE_STATE {
        IDLE,
        REQUESTED,
        SUCCESS,
        FAIL,
        DONE
    }

    public interface SectionDataResponseListener {
        void onResponse(SectionResponse sectionResponse);
    }

    public static class SectionResponse {
        int position;
        Object response;
        SECTION_RESPONSE_STATE state;

        public int getPosition() {
            return this.position;
        }

        public void setPosition(int i) {
            this.position = i;
        }

        public SECTION_RESPONSE_STATE getState() {
            return this.state;
        }

        public void setState(SECTION_RESPONSE_STATE section_response_state) {
            this.state = section_response_state;
        }

        public Object getResponse() {
            return this.response;
        }

        public void setResponse(Object obj) {
            this.response = obj;
        }
    }

    public boolean addToDataRequestQueue(RevampedSectionData revampedSectionData, int i, SectionDataResponseListener sectionDataResponseListener, boolean z) {
        if (this.responseHashMap.containsKey(Integer.valueOf(i)) && !z) {
            SectionResponse sectionResponse = (SectionResponse) this.responseHashMap.get(Integer.valueOf(i));
            if (sectionResponse.state != SECTION_RESPONSE_STATE.FAIL) {
                sectionDataResponseListener.onResponse(sectionResponse);
                return true;
            }
        }
        fetchData(revampedSectionData, i, sectionDataResponseListener, z);
        return false;
    }

    public void fetchData(RevampedSectionData revampedSectionData, final int i, final SectionDataResponseListener sectionDataResponseListener, boolean z) {
        int section_type = revampedSectionData.getSection_type();
        String section_data_url = revampedSectionData.getSection_data_url();
        final SectionResponse sectionResponse = new SectionResponse();
        sectionResponse.setPosition(i);
        sectionResponse.setState(SECTION_RESPONSE_STATE.REQUESTED);
        sectionResponse.setResponse(null);
        this.responseHashMap.put(Integer.valueOf(i), sectionResponse);
        URLManager uRLManager = new URLManager();
        uRLManager.a(getClassName(section_type));
        uRLManager.c(Boolean.valueOf(z));
        uRLManager.a(section_data_url);
        i.a().a(uRLManager, toString(), new b<Object>() {
            public void onResponse(Object obj) {
                if (obj != null) {
                    sectionResponse.setResponse(obj);
                    sectionResponse.setState(SECTION_RESPONSE_STATE.SUCCESS);
                    RevampedDetailSectionDataManger.this.responseHashMap.put(Integer.valueOf(i), sectionResponse);
                    sectionDataResponseListener.onResponse(sectionResponse);
                    return;
                }
                sectionResponse.setResponse(null);
                sectionResponse.setState(SECTION_RESPONSE_STATE.FAIL);
                RevampedDetailSectionDataManger.this.responseHashMap.put(Integer.valueOf(i), sectionResponse);
                sectionDataResponseListener.onResponse(sectionResponse);
            }
        }, new a() {
            public void onErrorResponse(VolleyError volleyError) {
                sectionResponse.setResponse(null);
                sectionResponse.setState(SECTION_RESPONSE_STATE.FAIL);
                RevampedDetailSectionDataManger.this.responseHashMap.put(Integer.valueOf(i), sectionResponse);
                sectionDataResponseListener.onResponse(sectionResponse);
            }
        });
    }

    public SectionResponse getSectionResponseForPosition(int i) {
        return this.responseHashMap.containsKey(Integer.valueOf(i)) ? (SectionResponse) this.responseHashMap.get(Integer.valueOf(i)) : null;
    }

    public void setResponseStateDone(int i) {
        if (this.responseHashMap.containsKey(Integer.valueOf(i))) {
            SectionResponse sectionResponse = (SectionResponse) this.responseHashMap.get(Integer.valueOf(i));
            sectionResponse.setState(SECTION_RESPONSE_STATE.DONE);
            this.responseHashMap.put(Integer.valueOf(i), sectionResponse);
        }
    }

    public void requestDataFirst(ArrayList<RevampedSectionData> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                fetchData((RevampedSectionData) arrayList.get(i), i, new SectionDataResponseListener() {
                    public void onResponse(SectionResponse sectionResponse) {
                        RevampedDetailSectionDataManger.this.setResponseStateDone(i);
                    }
                }, false);
            }
        }
    }

    public Class<?> getClassName(int i) {
        if (i == REVAMPED_DETAIL_SECTION_TYPE.PROMOTION.getNumVal() || i == REVAMPED_DETAIL_SECTION_TYPE.TEXT.getNumVal()) {
            return RevampedEntityFeedData.class;
        }
        if (i == REVAMPED_DETAIL_SECTION_TYPE.HOR_SCROLL.getNumVal()) {
            return RevampedSimilarAlbumEntityInfo.class;
        }
        if (i == REVAMPED_DETAIL_SECTION_TYPE.ADS.getNumVal()) {
            return RevampedListAdasCard.class;
        }
        if (i == REVAMPED_DETAIL_SECTION_TYPE.GRID2x2.getNumVal()) {
            return Items.class;
        }
        return RevampedEntityFeedData.class;
    }
}
