package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.af;
import com.managers.ap;
import com.utilities.Util;

public class BaseItemView extends LinearLayout implements OnClickListener {
    protected GaanaApplication mAppState;
    protected Context mContext;
    protected a mDynamicView;
    protected BaseGaanaFragment mFragment;
    protected LayoutInflater mInflater;

    public a getDynamicView() {
        return null;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return null;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public void setFirstCall(boolean z) {
    }

    public void setIsToBeRefreshed(boolean z) {
    }

    public void setPositionToBeRefreshed(int i) {
    }

    public void setUserVisibleHint(boolean z) {
    }

    public BaseItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        this(context, baseGaanaFragment, (AttributeSet) null);
    }

    public BaseItemView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInflater = null;
        this.mContext = null;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = GaanaApplication.getInstance();
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        setLayoutParams(new LayoutParams(-1, -1));
    }

    public BaseItemView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, null);
        this.mInflater = null;
        this.mContext = null;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = GaanaApplication.getInstance();
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        setLayoutParams(new LayoutParams(-1, -1));
        this.mDynamicView = aVar;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        if (viewGroup != null) {
            return this.mInflater.inflate(i, viewGroup, false);
        }
        return this.mInflater.inflate(i, this, false);
    }

    /* Access modifiers changed, original: protected */
    public void populateSpecialGaanaListing(Playlist playlist) {
        af.a(this.mContext, this.mFragment).a((int) R.id.specialGaanaMenu, (BusinessObject) playlist);
    }

    /* Access modifiers changed, original: protected */
    public void populatePlaylistListing(Playlist playlist) {
        af.a(this.mContext, this.mFragment).a((int) R.id.playlistMenu, (BusinessObject) playlist);
    }

    /* Access modifiers changed, original: protected */
    public void populateAlbumListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.albumMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void populateRadioListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.radioMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void populateArtistListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.artistMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void launchYouTubePlayer(String str, String str2, BusinessObject businessObject, int i) {
        Util.a(this.mContext, str, str2, businessObject, i);
    }

    public View getPopulatedView(int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        view.setOnClickListener(this);
        return null;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup, boolean z) {
        return getPopulatedView(i, viewHolder, viewGroup);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.one_touch_radio_header_container) {
            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.ONE_TOUCH.name());
        }
    }

    /* Access modifiers changed, original: protected */
    public LinearLayout getEmptyLayout() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        linearLayout.setGravity(17);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
        return linearLayout;
    }

    public int getItemViewType() {
        if (this.mDynamicView != null && !this.mDynamicView.v()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mDynamicView.m());
            stringBuilder.append(this.mDynamicView.e());
            return stringBuilder.toString().hashCode();
        } else if (this.mDynamicView != null) {
            return "p#a#r#t#yP#l".hashCode();
        } else {
            return hashCode();
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean isBrandView(String str) {
        boolean z = true;
        if (str != null && str.equals("TRENDING_SONG")) {
            if (!(str.equals("TRENDING_SONG") && ap.a().b(this.mContext))) {
                z = false;
            }
            return z;
        } else if (str == null || !str.equals("MADE_FOR_YOU")) {
            return false;
        } else {
            if (!(str.equals("MADE_FOR_YOU") && ap.a().b(this.mContext))) {
                z = false;
            }
            return z;
        }
    }
}
