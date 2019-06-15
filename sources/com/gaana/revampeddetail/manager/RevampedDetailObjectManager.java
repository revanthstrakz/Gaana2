package com.gaana.revampeddetail.manager;

import com.constants.Constants.REVAMPED_DETAIL_CAROUSAL_CARD_TYPE;
import com.constants.Constants.REVAMPED_DETAIL_SECTION_TYPE;
import com.constants.Constants.REVAMPED_DETAIL_VIEW_TYPE;
import com.gaana.revampeddetail.model.RevampedCarouselData;
import com.gaana.revampeddetail.model.RevampedCarouselData.CarouselCardData;
import com.gaana.revampeddetail.model.RevampedDetailObject;
import com.gaana.revampeddetail.model.RevampedDetailObject.RevampedSectionData;
import java.util.ArrayList;
import java.util.Iterator;

public class RevampedDetailObjectManager {
    RevampedSectionData carousalSection;
    RevampedDetailObject detailObjectModel;
    int detailType;
    private boolean isRefreshing = false;
    ArrayList<RevampedSectionData> otherSections;

    public RevampedDetailObjectManager(int i) {
        this.detailType = i;
    }

    public void setData(RevampedDetailObject revampedDetailObject, boolean z) {
        this.detailObjectModel = revampedDetailObject;
        this.isRefreshing = z;
        if (revampedDetailObject != null && revampedDetailObject.getSection_data() != null && revampedDetailObject.getSection_data().size() != 0) {
            this.otherSections = new ArrayList();
            Iterator it = revampedDetailObject.getSection_data().iterator();
            while (it.hasNext()) {
                RevampedSectionData revampedSectionData = (RevampedSectionData) it.next();
                if (revampedSectionData != null) {
                    if (revampedSectionData.getSection_type() == REVAMPED_DETAIL_SECTION_TYPE.CAROUSAL.getNumVal()) {
                        this.carousalSection = revampedSectionData;
                    } else {
                        this.otherSections.add(revampedSectionData);
                    }
                }
            }
        }
    }

    public boolean isRefreshing() {
        return this.isRefreshing;
    }

    public void resetData() {
        this.detailObjectModel = null;
        this.carousalSection = null;
        this.otherSections = null;
    }

    public int getDetailType() {
        if (this.detailObjectModel != null) {
            return this.detailObjectModel.getDetailType();
        }
        return this.detailType;
    }

    public RevampedSectionData getCarousalSection() {
        return this.carousalSection;
    }

    public ArrayList<RevampedSectionData> getOtherSections() {
        return this.otherSections;
    }

    public RevampedSectionData getArtistSectionData() {
        if (this.otherSections != null && this.otherSections.size() > 0) {
            Iterator it = this.otherSections.iterator();
            while (it.hasNext()) {
                RevampedSectionData revampedSectionData = (RevampedSectionData) it.next();
                if (revampedSectionData.getSection_type() == REVAMPED_DETAIL_SECTION_TYPE.LIST.getNumVal() && revampedSectionData.getView_type() == REVAMPED_DETAIL_VIEW_TYPE.ARTIST_PAGE_LIST.getNumVal()) {
                    return revampedSectionData;
                }
            }
        }
        return null;
    }

    public String getArtworkUrl() {
        return this.detailObjectModel.getAtw();
    }

    public RevampedDetailObject getDetailObjectModel() {
        return this.detailObjectModel;
    }

    public RevampedCarouselData getDummyMetaCarouselData() {
        RevampedCarouselData revampedCarouselData = new RevampedCarouselData();
        CarouselCardData carouselCardData = new CarouselCardData();
        carouselCardData.setCard_type(REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.META.getNumVal());
        ArrayList arrayList = new ArrayList();
        arrayList.add(carouselCardData);
        revampedCarouselData.setCarousel_data(arrayList);
        return revampedCarouselData;
    }
}
