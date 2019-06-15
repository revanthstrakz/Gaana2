package com.gaana.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil.Callback;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.LocalTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.revampeddetail.manager.RevampDetailAdManager;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.managers.ColombiaAdViewManager;
import com.managers.af;
import com.managers.ap.a;
import com.services.d;
import com.services.l.az;
import com.services.l.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomListAdapter extends Adapter<android.support.v7.widget.RecyclerView.ViewHolder> implements k {
    private static final int VIEW_TYPE_BANNER_AD = 1008;
    private static final int VIEW_TYPE_HEADER = -1;
    public int REPETATIVE_AD_INTERVAL = 4;
    public int REPETATIVE_AD_INTERVAL_REVAMPED = 3;
    private String adSectionName;
    private String dfpBannerAdCode;
    android.support.v7.widget.RecyclerView.ViewHolder holder;
    private IAddListItemView iAddListItemView;
    boolean isDistanceCleared = false;
    private boolean isFavorating = false;
    private boolean isSwipeAnimated = false;
    private int itemDropDelta = -1000;
    private int itemPlayNextDelta = -1000;
    private ArrayList<Integer> listofBannerAdPositions = new ArrayList();
    private Context mContext;
    private int mCount;
    private BaseGaanaFragment mFragment;
    private View mHeaderView;
    private Paint mPaint;
    private int mTranslateDelta = 0;
    private boolean showRepetativeBannerAd = false;

    public interface IAddListItemView {
        View addListItemView(int i, android.support.v7.widget.RecyclerView.ViewHolder viewHolder, ViewGroup viewGroup);

        android.support.v7.widget.RecyclerView.ViewHolder createViewHolder(ViewGroup viewGroup, int i);

        int getItemViewType(int i);

        void showHideEmtpyView(boolean z);
    }

    public static class CustomListDiffUtil extends Callback {
        private RevampDetailAdManager adManager;
        private List<Track> tracksListNew;
        private List<Track> tracksListOld;

        public boolean areContentsTheSame(int i, int i2) {
            return false;
        }

        public CustomListDiffUtil(List<Track> list, List<Track> list2, RevampDetailAdManager revampDetailAdManager) {
            this.tracksListOld = list;
            this.tracksListNew = list2;
            this.adManager = revampDetailAdManager;
        }

        public int getOldListSize() {
            return this.tracksListOld != null ? this.tracksListOld.size() : 0;
        }

        public int getNewListSize() {
            return this.tracksListNew != null ? this.tracksListNew.size() : 0;
        }

        public boolean areItemsTheSame(int i, int i2) {
            return ((Track) this.tracksListOld.get(i)).getBusinessObjId() == ((Track) this.tracksListNew.get(i2)).getBusinessObjId();
        }

        @Nullable
        public Object getChangePayload(int i, int i2) {
            return this.tracksListOld.get(this.adManager.getPositionwrtAd(i));
        }
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private View mView;

        public ViewHolder(View view) {
            super(view);
            this.mView = view;
        }
    }

    public float getSwipeThreshold(android.support.v7.widget.RecyclerView.ViewHolder viewHolder) {
        return 1.0f;
    }

    public float getSwipeVelocityThreshold(float f) {
        return 1.0f;
    }

    public void onItemDelete(int i, int i2) {
    }

    public boolean onItemMove(int i, int i2) {
        return false;
    }

    public CustomListAdapter(Context context, View view) {
        this.mContext = context;
        this.mHeaderView = view;
        this.mPaint = new Paint();
        this.mTranslateDelta = d.a().b() / 4;
    }

    public CustomListAdapter(Context context, View view, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mHeaderView = view;
        this.mPaint = new Paint();
        this.mFragment = baseGaanaFragment;
        this.mTranslateDelta = d.a().b() / 4;
    }

    public int getActualItemIndex(int i) {
        Iterator it = this.listofBannerAdPositions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() < i) {
                i2++;
            }
        }
        return i - i2;
    }

    public void setShowRepetativeBannerAd(boolean z) {
        this.showRepetativeBannerAd = z;
    }

    public void setDFPBannerAdCode(String str) {
        this.dfpBannerAdCode = str;
    }

    public void setAdSectionName(String str) {
        this.adSectionName = str;
    }

    public void insertAdSpots() {
        if (this.showRepetativeBannerAd) {
            int i;
            if (this.mFragment instanceof RevampedDetailListing) {
                i = this.mCount;
                int i2 = this.REPETATIVE_AD_INTERVAL_REVAMPED;
                while (i2 < i) {
                    if (!this.listofBannerAdPositions.contains(Integer.valueOf(i2))) {
                        this.listofBannerAdPositions.add(Integer.valueOf(i2));
                        i++;
                    }
                    i2 += this.REPETATIVE_AD_INTERVAL_REVAMPED;
                }
            } else if (this.listofBannerAdPositions.size() == 0 && this.mCount / 2 > 0) {
                if (!this.listofBannerAdPositions.contains(Integer.valueOf(this.REPETATIVE_AD_INTERVAL + 1))) {
                    this.listofBannerAdPositions.add(Integer.valueOf(this.REPETATIVE_AD_INTERVAL + 1));
                }
                insertAdSpots();
            } else if (((Integer) this.listofBannerAdPositions.get(this.listofBannerAdPositions.size() - 1)).intValue() < ((this.mCount / 2) + this.listofBannerAdPositions.size()) + 2) {
                i = ((Integer) this.listofBannerAdPositions.get(this.listofBannerAdPositions.size() - 1)).intValue() + (this.REPETATIVE_AD_INTERVAL - 1);
                if (!this.listofBannerAdPositions.contains(Integer.valueOf(i))) {
                    this.listofBannerAdPositions.add(Integer.valueOf(i));
                }
                insertAdSpots();
            }
        }
    }

    public void setParamaters(int i, IAddListItemView iAddListItemView) {
        this.mCount = i;
        this.iAddListItemView = iAddListItemView;
        if (this.mFragment instanceof RevampedDetailListing) {
            insertAdSpots();
        }
    }

    public void updateAdapterCount(int i) {
        this.mCount = i;
        insertAdSpots();
        notifyDataSetChanged();
    }

    public void removeItem(int i) {
        this.mCount--;
        notifyItemRemoved(i);
    }

    public void addItem(int i) {
        this.mCount++;
        notifyItemInserted(i);
    }

    public void updateAdapterRange(int i, int i2) {
        if (this.mHeaderView != null) {
            this.mCount += i2;
        } else {
            this.mCount += i2 + 1;
        }
        notifyItemRangeInserted(i, i2);
    }

    public void clearAdapter() {
        notifyDataSetChanged();
    }

    public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1008) {
            return new ItemAdViewHolder(getADView(viewGroup));
        }
        if (i != -1 || this.mHeaderView == null) {
            return this.iAddListItemView.createViewHolder(viewGroup, i);
        }
        return new DetailListingItemHolder(this.mHeaderView);
    }

    public View getADView(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
    }

    public void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        if (this.listofBannerAdPositions.contains(Integer.valueOf(i))) {
            ColombiaAdViewManager.a().a(viewHolder.itemView.getContext(), (LinearLayout) viewHolder.itemView, new PublisherAdView(viewHolder.itemView.getContext()), this.dfpBannerAdCode, null, 50, this.adSectionName, new AdsUJData[0]);
        } else if (this.mHeaderView == null) {
            this.iAddListItemView.addListItemView(getActualItemIndex(i), viewHolder, null);
        } else if (getActualItemIndex(i) != 0) {
            this.iAddListItemView.addListItemView(getActualItemIndex(i) - 1, viewHolder, null);
        }
    }

    public int getItemCount() {
        if (this.mHeaderView == null) {
            return this.mCount + this.listofBannerAdPositions.size();
        }
        return (this.mCount + 1) + this.listofBannerAdPositions.size();
    }

    public int getItemViewType(int i) {
        if (this.listofBannerAdPositions.contains(Integer.valueOf(i))) {
            return 1008;
        }
        if (this.mHeaderView == null) {
            return this.iAddListItemView.getItemViewType(i);
        }
        if (i == 0) {
            i = -1;
        } else {
            i = this.iAddListItemView.getItemViewType(i - 1);
        }
        return i;
    }

    public void onComplete(int i) {
        if (this.isDistanceCleared && this.holder != null) {
            this.holder.getAdapterPosition();
            BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
            if (currentFragment != null && (currentFragment instanceof RevampedDetailListing)) {
                ((RevampedDetailListing) currentFragment).a(true);
            }
            BusinessObject businessObject = (BusinessObject) this.holder.itemView.getTag();
            String str = "Swipe Favorite Track";
            if (businessObject.isFavorite().booleanValue()) {
                str = "Swipe UnFavorite Track ";
            }
            if (!this.isFavorating && this.itemDropDelta >= this.mTranslateDelta) {
                setFavorited(this.holder, businessObject);
                d.a().a("PREFERENCE_KEY_SWIPE_TO_FAVORITE_INITIATED", true, false);
            } else if (this.itemPlayNextDelta >= this.mTranslateDelta) {
                af.a(this.mContext, currentFragment).a((int) R.id.enqueueNextMenu, businessObject);
                this.isDistanceCleared = false;
                str = "Swipe Play Next Track";
            }
            if (currentFragment != null && (currentFragment instanceof RevampedDetailListing)) {
                ((RevampedDetailListing) currentFragment).a(str, false);
            }
        }
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, android.support.v7.widget.RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        Canvas canvas2 = canvas;
        android.support.v7.widget.RecyclerView.ViewHolder viewHolder2 = viewHolder;
        float f3 = f;
        if (i == 1 && viewHolder.getAdapterPosition() != -1 && recyclerView.getChildCount() > 1 && (viewHolder2 instanceof az)) {
            if (((double) f3) != 0.0d) {
                BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (currentFragment != null && (currentFragment instanceof RevampedDetailListing)) {
                    ((RevampedDetailListing) currentFragment).a(false);
                }
            }
            View view = viewHolder2.itemView;
            float bottom = (((float) view.getBottom()) - ((float) view.getTop())) / 3.0f;
            TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
            BusinessObject businessObject = (BusinessObject) viewHolder2.itemView.getTag();
            if (!(businessObject instanceof LocalTrack)) {
                view.setTranslationX(f3);
                Drawable drawable;
                float f4;
                LinearLayout linearLayout;
                TextView textView;
                if (this.isSwipeAnimated) {
                    Drawable drawable2;
                    float f5 = (float) (this.mTranslateDelta - 1);
                    this.mPaint.setColor(view.getResources().getColor(R.color.f17gaana.red));
                    if (businessObject.isFavorite().booleanValue()) {
                        drawable2 = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(52, -1));
                    } else {
                        drawable2 = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(50, -1));
                    }
                    canvas2.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f5, (float) view.getBottom()), this.mPaint);
                    drawable2.setBounds(new Rect((int) (((float) view.getLeft()) + bottom), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getLeft()) + (2.0f * bottom)), (int) (((float) view.getBottom()) - bottom)));
                    drawable2.draw(canvas2);
                } else if (f3 > 0.0f) {
                    this.itemPlayNextDelta = -1000;
                    CharSequence charSequence;
                    if (f3 < ((float) this.mTranslateDelta)) {
                        if (z) {
                            this.itemDropDelta = (int) f3;
                        }
                        if (Constants.l) {
                            this.mPaint.setColor(view.getResources().getColor(R.color.swipe_white_bg_color));
                        } else {
                            this.mPaint.setColor(view.getResources().getColor(R.color.black));
                        }
                        String str = "ADD TO \nFAVORITES";
                        if (businessObject.isFavorite().booleanValue()) {
                            drawable = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(51, -1));
                            charSequence = "REMOVE FROM \nFAVORITES";
                        } else {
                            charSequence = str;
                            drawable = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(48, -1));
                        }
                        canvas2.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f3, (float) view.getBottom()), this.mPaint);
                        f4 = 2.0f * bottom;
                        drawable.setBounds(new Rect((int) (((float) view.getLeft()) + bottom), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getLeft()) + f4), (int) (((float) view.getBottom()) - bottom)));
                        drawable.draw(canvas2);
                        linearLayout = new LinearLayout(view.getContext());
                        textView = new TextView(view.getContext());
                        textView.setVisibility(0);
                        textView.setTextSize(2, 10.0f);
                        textView.setTypeface(null, 1);
                        textView.setText(charSequence);
                        textView.setTextColor(this.mContext.getResources().getColor(R.color.white));
                        linearLayout.addView(textView);
                        linearLayout.measure(canvas.getWidth(), canvas.getHeight());
                        linearLayout.layout(0, 0, canvas.getWidth(), canvas.getHeight());
                        canvas2.translate((float) ((int) ((((float) view.getLeft()) + f4) + ((float) drawable.getMinimumWidth()))), (float) ((int) (((float) view.getTop()) + bottom)));
                        linearLayout.draw(canvas2);
                    } else if (f3 >= ((float) this.mTranslateDelta) && businessObject != null) {
                        Drawable drawable3;
                        if (z) {
                            this.itemDropDelta = (int) f3;
                        }
                        this.isDistanceCleared = true;
                        charSequence = "ADD TO \nFAVORITES";
                        if (businessObject.isFavorite().booleanValue()) {
                            drawable3 = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(50, -1));
                            charSequence = "REMOVE FROM \nFAVORITES";
                        } else {
                            drawable3 = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(52, -1));
                        }
                        this.holder = viewHolder2;
                        this.mPaint.setColor(view.getResources().getColor(R.color.f17gaana.red));
                        canvas2.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f3, (float) view.getBottom()), this.mPaint);
                        f4 = 2.0f * bottom;
                        drawable3.setBounds(new Rect((int) (((float) view.getLeft()) + bottom), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getLeft()) + f4), (int) (((float) view.getBottom()) - bottom)));
                        drawable3.draw(canvas2);
                        LinearLayout linearLayout2 = new LinearLayout(view.getContext());
                        TextView textView2 = new TextView(view.getContext());
                        textView2.setVisibility(0);
                        textView2.setTextSize(2, 10.0f);
                        textView2.setTypeface(null, 1);
                        textView2.setTextColor(this.mContext.getResources().getColor(R.color.white));
                        textView2.setText(charSequence);
                        linearLayout2.addView(textView2);
                        linearLayout2.measure(canvas.getWidth(), canvas.getHeight());
                        linearLayout2.layout(0, 0, canvas.getWidth(), canvas.getHeight());
                        canvas2.translate((float) ((int) ((((float) view.getLeft()) + f4) + ((float) drawable3.getMinimumWidth()))), (float) ((int) (((float) view.getTop()) + bottom)));
                        linearLayout2.draw(canvas2);
                    }
                } else if (f3 < 0.0f) {
                    this.itemDropDelta = -1000;
                    float abs = Math.abs(f);
                    if (abs < ((float) this.mTranslateDelta)) {
                        if (z) {
                            this.itemPlayNextDelta = (int) abs;
                        }
                        if (Constants.l) {
                            this.mPaint.setColor(view.getResources().getColor(R.color.swipe_white_bg_color));
                        } else {
                            this.mPaint.setColor(view.getResources().getColor(R.color.black));
                        }
                        Drawable drawable4 = this.mContext.getResources().getDrawable(R.drawable.play_next_start);
                        canvas2.drawRect(new RectF(((float) view.getRight()) + f3, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom()), this.mPaint);
                        f4 = 2.0f * bottom;
                        drawable4.setBounds(new Rect((int) (((float) view.getRight()) - f4), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getRight()) - bottom), (int) (((float) view.getBottom()) - bottom)));
                        drawable4.draw(canvas2);
                        linearLayout = new LinearLayout(view.getContext());
                        textView = new TextView(view.getContext());
                        textView.setVisibility(0);
                        textView.setTextSize(2, 10.0f);
                        textView.setTypeface(null, 1);
                        textView.setText("PLAY \nNEXT");
                        linearLayout.addView(textView);
                        linearLayout.measure(canvas.getWidth(), canvas.getHeight());
                        linearLayout.layout(canvas.getWidth(), 0, 0, canvas.getHeight());
                        canvas2.translate((float) ((int) (((((float) view.getRight()) - f4) - ((float) drawable4.getMinimumWidth())) - ((float) textView.getWidth()))), (float) ((int) (((float) view.getTop()) + bottom)));
                        linearLayout.draw(canvas2);
                    } else if (abs >= ((float) this.mTranslateDelta) && businessObject != null) {
                        if (z) {
                            this.itemPlayNextDelta = (int) abs;
                        }
                        this.isDistanceCleared = true;
                        this.holder = viewHolder2;
                        this.mPaint.setColor(view.getResources().getColor(R.color.f17gaana.red));
                        drawable = this.mContext.getResources().getDrawable(R.drawable.play_next_active);
                        canvas2.drawRect(new RectF(((float) view.getRight()) + f3, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom()), this.mPaint);
                        f4 = 2.0f * bottom;
                        drawable.setBounds(new Rect((int) (((float) view.getRight()) - f4), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getRight()) - bottom), (int) (((float) view.getBottom()) - bottom)));
                        drawable.draw(canvas2);
                        linearLayout = new LinearLayout(view.getContext());
                        textView = new TextView(view.getContext());
                        textView.setVisibility(0);
                        textView.setTextSize(2, 10.0f);
                        textView.setTextColor(this.mContext.getResources().getColor(R.color.white));
                        textView.setTypeface(null, 1);
                        textView.setText("PLAY \nNEXT");
                        linearLayout.addView(textView);
                        linearLayout.measure(canvas.getWidth(), canvas.getHeight());
                        linearLayout.layout(canvas.getWidth(), 0, 0, canvas.getHeight());
                        canvas2.translate((float) ((int) (((((float) view.getRight()) - f4) - ((float) drawable.getMinimumWidth())) - ((float) textView.getWidth()))), (float) ((int) (((float) view.getTop()) + bottom)));
                        linearLayout.draw(canvas2);
                    }
                }
            }
        }
    }

    public void setIsSwipeAnimated(boolean z) {
        this.isSwipeAnimated = z;
    }

    private void setFavorited(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, BusinessObject businessObject) {
        this.isFavorating = true;
        if (GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null) {
            this.isFavorating = false;
        }
        af.a(viewHolder.itemView.getContext(), ((GaanaActivity) viewHolder.itemView.getContext()).getCurrentFragment()).a((int) R.id.favoriteMenu, businessObject, new a() {
            public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                CustomListAdapter.this.isFavorating = false;
                CustomListAdapter.this.isDistanceCleared = false;
            }
        });
    }
}
