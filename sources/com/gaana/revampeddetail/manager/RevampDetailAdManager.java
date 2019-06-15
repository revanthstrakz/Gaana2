package com.gaana.revampeddetail.manager;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.fragments.BaseGaanaFragment;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.managers.ap;
import com.managers.e;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RevampDetailAdManager {
    public static final int REVAMPED_DFP_AD = 1999;
    private int mBaseItemViewType;
    private ColumbiaAdItemview mColumbiaAdItemView = null;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private ArrayList<Integer> mListOfAdPosition = new ArrayList();
    private ArrayList<BusinessObject> mOriginalList = new ArrayList();
    private BusinessObject mParentBusinessObject;
    private int mSizeOfList = 0;

    public void initAds(Context context, BusinessObject businessObject, BaseGaanaFragment baseGaanaFragment, ArrayList<Track> arrayList, int i) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mParentBusinessObject = businessObject;
        this.mOriginalList.clear();
        this.mOriginalList.addAll(arrayList);
        this.mBaseItemViewType = i;
        if (this.mOriginalList != null && this.mOriginalList.size() > 0) {
            this.mSizeOfList = this.mOriginalList.size();
        }
        initAdPositions();
    }

    public void initAdPositions() {
        this.mListOfAdPosition.clear();
        if (ap.a().b(this.mContext) && GaanaApplication.getInstance().getColombiaSdkInit() && TextUtils.isEmpty(getChannelPageAdCode()) && e.ad == 0 && !getSponseredList()) {
            int i = 0;
            int i2 = this.mSizeOfList;
            while (i < i2) {
                if (i == 4) {
                    this.mListOfAdPosition.add(Integer.valueOf(i));
                    i2++;
                } else if (i != 0 && (i - 4) % 7 == 0) {
                    this.mListOfAdPosition.add(Integer.valueOf(i));
                    i2++;
                }
                i++;
            }
            if (this.mListOfAdPosition.isEmpty()) {
                this.mListOfAdPosition.add(Integer.valueOf(this.mSizeOfList));
            }
        }
    }

    public ArrayList<Integer> getListOfAdPosition() {
        return this.mListOfAdPosition;
    }

    public int getAdsListSize() {
        return this.mListOfAdPosition != null ? this.mListOfAdPosition.size() : 0;
    }

    public int getItemViewType(int i) {
        if (isAdonThisPosition(i)) {
            return this.mListOfAdPosition.indexOf(Integer.valueOf(i)) + this.mBaseItemViewType;
        }
        return this.mBaseItemViewType;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (this.mColumbiaAdItemView == null) {
            this.mColumbiaAdItemView = new ColumbiaAdItemview(this.mContext, this.mFragment);
        }
        return new ItemAdViewHolder(this.mColumbiaAdItemView.getNewView(REVAMPED_DFP_AD, viewGroup));
    }

    public View onBindViewHolder(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (viewHolder != null && viewHolder.getItemViewType() >= this.mBaseItemViewType && viewHolder.getItemViewType() < this.mListOfAdPosition.size() + this.mBaseItemViewType) {
            if (this.mColumbiaAdItemView == null) {
                this.mColumbiaAdItemView = new ColumbiaAdItemview(this.mContext, this.mFragment);
            }
            this.mColumbiaAdItemView.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), this.mParentBusinessObject);
        }
        return viewHolder.itemView;
    }

    public boolean isAdonThisPosition(int i) {
        return (this.mListOfAdPosition == null || this.mListOfAdPosition.size() <= 0) ? false : this.mListOfAdPosition.contains(Integer.valueOf(i));
    }

    private int getAdPostion(int i) {
        int i2 = -1;
        if (i == 0) {
            return -1;
        }
        int size = this.mOriginalList.size();
        if (i == 4) {
            if (size > 11) {
                size = i + 4;
            }
            i2 = new Random().nextInt((size - i) + 1) + i;
            this.mListOfAdPosition.add(Integer.valueOf(i2));
        } else if ((i - 4) % 10 == 0) {
            i2 = i + 4;
            if (size <= i2) {
                i2 = size;
            }
            i2 = new Random().nextInt((i2 - i) + 1) + i;
            this.mListOfAdPosition.add(Integer.valueOf(i2));
        }
        return i2;
    }

    public int getPositionwrtAd(int i) {
        if (this.mListOfAdPosition.size() > 0 && i == this.mSizeOfList && i == ((Integer) this.mListOfAdPosition.get(this.mListOfAdPosition.size() - 1)).intValue()) {
            return i - 1;
        }
        Iterator it = this.mListOfAdPosition.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() < i) {
                i2++;
            }
        }
        return i - i2;
    }

    private String getChannelPageAdCode() {
        String str = "";
        if (this.mParentBusinessObject instanceof Album) {
            return ((Album) this.mParentBusinessObject).getChannelPageAdCode();
        }
        return this.mParentBusinessObject instanceof Playlist ? ((Playlist) this.mParentBusinessObject).getChannelPageAdCode() : str;
    }

    private boolean getSponseredList() {
        CharSequence charSequence = "";
        if (this.mParentBusinessObject instanceof Album) {
            charSequence = ((Album) this.mParentBusinessObject).getIsSponsored();
        } else if (this.mParentBusinessObject instanceof Playlist) {
            charSequence = ((Playlist) this.mParentBusinessObject).getIsSponsored();
        }
        return !TextUtils.isEmpty(charSequence) && charSequence.equalsIgnoreCase("1");
    }
}
