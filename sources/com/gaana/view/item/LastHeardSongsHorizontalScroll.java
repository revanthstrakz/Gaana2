package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.Tracks.Track;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.i.d;
import com.i.i;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.services.l.ad;
import com.services.l.s;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.c;
import java.util.ArrayList;
import java.util.Iterator;

public class LastHeardSongsHorizontalScroll extends BaseItemView {
    private BaseGaanaFragment baseGaana = null;
    public boolean isCrossed = false;
    public boolean isVisible = false;
    private BaseItemView mBaseItemView = null;
    private a mDynamicView;
    private int mLayoutId = R.layout.view_featured_album_item;
    private View mView = null;

    public LastHeardSongsHorizontalScroll(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.baseGaana = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(this.mInflater.inflate(R.layout.last_heard_songs, viewGroup, false));
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return populateLastHeardSongs(i, viewHolder.itemView, new BusinessObject(), "", "");
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View populateLastHeardSongs(int i, final View view, BusinessObject businessObject, String str, String str2) {
        this.mBaseItemView = new PlaylistItemView(getContext(), this.baseGaana);
        ((PlaylistItemView) this.mBaseItemView).setSourceName(this.mDynamicView.p());
        this.mView = view;
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.mDynamicView.l());
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.a(Integer.valueOf(this.mDynamicView.g()).intValue());
        uRLManager.a(BusinessObjectType.GenericItems);
        if (!this.isCrossed && Constants.aE) {
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        view.setVisibility(8);
                        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                        layoutParams.width = 0;
                        layoutParams.height = 0;
                        view.setLayoutParams(layoutParams);
                        return;
                    }
                    final ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                    Items items = (Items) businessObject;
                    ((ImageView) view.findViewById(R.id.cross)).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            LastHeardSongsHorizontalScroll.this.isCrossed = true;
                            ((BaseActivity) LastHeardSongsHorizontalScroll.this.mContext).sendGAEvent(((BaseActivity) LastHeardSongsHorizontalScroll.this.mContext).currentScreen, "Last Heard Playlist Suggestion", "Cancel - Click");
                            LastHeardSongsHorizontalScroll.collapse(view);
                        }
                    });
                    if (!TextUtils.isEmpty(items.getTagDescription())) {
                        ((TextView) view.findViewById(R.id.playlist_text)).setText(items.getTagDescription());
                    }
                    if (!TextUtils.isEmpty(items.getPageTitle())) {
                        ((TextView) view.findViewById(R.id.sample_text)).setText(items.getPageTitle());
                    }
                    ((TextView) view.findViewById(R.id.save_playlist)).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            ((BaseActivity) LastHeardSongsHorizontalScroll.this.mContext).checkSetLoginStatus(new ad() {
                                public void onLoginSuccess() {
                                    d.a(new Runnable() {
                                        public void run() {
                                            if (LastHeardSongsHorizontalScroll.this.mFragment.isAdded()) {
                                                ArrayList arrayList = new ArrayList();
                                                Iterator it = arrListBusinessObj.iterator();
                                                while (it.hasNext()) {
                                                    arrayList.add((Track) Util.g((Item) it.next()));
                                                }
                                                ((BaseActivity) LastHeardSongsHorizontalScroll.this.mContext).sendGAEvent(((BaseActivity) LastHeardSongsHorizontalScroll.this.mContext).currentScreen, "Last Heard Playlist Suggestion", "Save Playlist - Click");
                                                Constants.aE = false;
                                                ap.a().a(LastHeardSongsHorizontalScroll.this.mContext, arrayList);
                                            }
                                        }
                                    });
                                }
                            }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
                        }
                    });
                    HorizontalRecyclerView horizontalRecyclerView = (HorizontalRecyclerView) view.findViewById(R.id.horizontal_list_view);
                    horizontalRecyclerView.setAdapter(horizontalRecyclerView.a(LastHeardSongsHorizontalScroll.this.mContext, 0));
                    view.setVisibility(0);
                    LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                    layoutParams2.width = -1;
                    layoutParams2.height = -2;
                    view.setLayoutParams(layoutParams2);
                    horizontalRecyclerView.setViewRecycleListner(1, arrListBusinessObj.size(), false, new c() {
                        public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                            return viewHolder;
                        }

                        public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                            return ((PlaylistItemView) LastHeardSongsHorizontalScroll.this.mBaseItemView).getPopulatedViewForLastHeard(viewHolder, arrListBusinessObj, i3);
                        }
                    });
                }
            }, uRLManager);
        }
        return view;
    }

    public static void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        AnonymousClass2 anonymousClass2 = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            /* Access modifiers changed, original: protected */
            public void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    view.setVisibility(8);
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.height = 0;
                    view.setLayoutParams(layoutParams);
                    return;
                }
                view.getLayoutParams().height = measuredHeight - ((int) (((float) measuredHeight) * f));
                view.requestLayout();
            }
        };
        anonymousClass2.setDuration((long) ((int) (((float) measuredHeight) / view.getContext().getResources().getDisplayMetrics().density)));
        view.startAnimation(anonymousClass2);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!z && this.mView != null) {
            this.mView.setVisibility(8);
            LayoutParams layoutParams = (LayoutParams) this.mView.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.height = 0;
            this.mView.setLayoutParams(layoutParams);
            Constants.aE = false;
        }
    }
}
