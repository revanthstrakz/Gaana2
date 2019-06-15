package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.an;
import com.services.l.ai;
import com.utilities.Util;
import com.views.HorizontalRecyclerView;
import com.views.HorizontalRecyclerView.b;
import com.views.HorizontalRecyclerView.c;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class SimilarItemHorizontalScroll extends BaseItemView {
    b horizontalRecyclarAdapter;
    private BaseItemView mBaseItemView = null;
    private int mCurrentindex = -1;
    private int mLayoutId = R.layout.view_featured_album_item;
    View mSimilarView;
    private ai onRecyclerItemClicked;

    public SimilarItemHorizontalScroll(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public SimilarItemHorizontalScroll(Context context, BaseGaanaFragment baseGaanaFragment, ai aiVar) {
        super(context, baseGaanaFragment);
        this.onRecyclerItemClicked = aiVar;
    }

    public void init(String str) {
        try {
            this.mBaseItemView = (BaseItemView) Class.forName(str).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this.mFragment});
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalArgumentException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (InstantiationException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (IllegalAccessException e4) {
            ThrowableExtension.printStackTrace(e4);
        } catch (InvocationTargetException e5) {
            ThrowableExtension.printStackTrace(e5);
        } catch (NoSuchMethodException e6) {
            ThrowableExtension.printStackTrace(e6);
        }
    }

    public b getAdapter() {
        return this.horizontalRecyclarAdapter;
    }

    public void openCloseView() {
        if (this.mSimilarView != null) {
            ImageView imageView = (ImageView) this.mSimilarView.findViewById(R.id.chevronBtn);
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (this.mSimilarView.getTag() == null) {
                this.mSimilarView.animate().translationY((float) Util.b(170)).setDuration(500).start();
                this.mSimilarView.setTag("Down");
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_up});
                drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else if (this.mSimilarView.getTag().equals("Down")) {
                this.mSimilarView.animate().translationY(0.0f).setDuration(500).start();
                this.mSimilarView.setTag("Up");
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_down});
                drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else if (this.mSimilarView.getTag().equals("Up")) {
                this.mSimilarView.animate().translationY((float) Util.b(170)).setDuration(500).start();
                this.mSimilarView.setTag("Down");
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_up});
                drawable = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            }
        }
    }

    private void setInfoTextBG(TextView textView, boolean z) {
        if (z) {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.similar_info_bg_red));
            textView.setTextColor(getResources().getColor(R.color.vector_white_color));
        } else if (Constants.l) {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.similar_info_bg));
            textView.setTextColor(getResources().getColor(R.color.vector_white_color));
        } else {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.similar_info_bg_white));
            textView.setTextColor(getResources().getColor(R.color.vector_black_color));
        }
    }

    public View populateSimilar(BusinessObject businessObject, String str, String str2) {
        View inflate;
        init(str);
        ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.horizontal_list_view, null);
        if (this.mBaseItemView instanceof TrackItemView) {
            inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.horizontal_list_view_similar_items, null);
            this.mSimilarView = inflate;
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                }
            });
            final TextView textView = (TextView) this.mSimilarView.findViewById(R.id.infoText);
            final ImageView imageView = (ImageView) this.mSimilarView.findViewById(R.id.search_chevron);
            final LinearLayout linearLayout = (LinearLayout) this.mSimilarView.findViewById(R.id.txtContainer);
            imageView.setVisibility(8);
            linearLayout.setVisibility(8);
            setInfoTextBG(textView, false);
            textView.setTypeface(Util.i(this.mContext));
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (linearLayout.getVisibility() == 0) {
                        linearLayout.setVisibility(8);
                        imageView.setVisibility(8);
                        SimilarItemHorizontalScroll.this.setInfoTextBG(textView, false);
                        return;
                    }
                    linearLayout.setVisibility(0);
                    imageView.setVisibility(0);
                    SimilarItemHorizontalScroll.this.setInfoTextBG(textView, true);
                }
            });
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.horizontal_list_view);
            if (recyclerView != null) {
                recyclerView.setOnScrollListener(new OnScrollListener() {
                    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                        super.onScrollStateChanged(recyclerView, i);
                        if (i == 0) {
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                            an.a().c("scroll", AvidJSONUtil.KEY_X, "", "queue", "", "", String.valueOf(SimilarItemHorizontalScroll.this.mCurrentindex), String.valueOf(linearLayoutManager.findLastCompletelyVisibleItemPosition()));
                            SimilarItemHorizontalScroll.this.mCurrentindex = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        }
                    }

                    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                    }
                });
            }
            ((ImageView) inflate.findViewById(R.id.chevronBtn)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    BaseFragment baseFragment = ((GaanaActivity) SimilarItemHorizontalScroll.this.mContext).getmCurrentPlayerFragment();
                    if (baseFragment != null && (baseFragment instanceof PlayerFragmentV2)) {
                        ((PlayerFragmentV2) baseFragment).m();
                    } else if (baseFragment != null && (baseFragment instanceof PlayerFragmentV4)) {
                        ((PlayerFragmentV4) baseFragment).s();
                    }
                }
            });
        } else {
            inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.horizontal_list_view, null);
        }
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.view_background});
        inflate.setBackgroundColor(obtainStyledAttributes.getColor(0, 0));
        obtainStyledAttributes.recycle();
        HorizontalRecyclerView horizontalRecyclerView = (HorizontalRecyclerView) inflate.findViewById(R.id.horizontal_list_view);
        TextView textView2 = (TextView) inflate.findViewById(R.id.f62similar.header.text);
        textView2.setText(str2);
        textView2.setIncludeFontPadding(false);
        this.horizontalRecyclarAdapter = null;
        if (businessObject == null) {
            return null;
        }
        if (this.mBaseItemView instanceof TrackItemView) {
            horizontalRecyclerView.setViewSubType(913);
            this.horizontalRecyclarAdapter = horizontalRecyclerView.a(this.mBaseItemView.getContext(), 0, VIEW_SIZE.RECENTLY_PLAYED_SMALL.getNumVal());
        } else {
            this.horizontalRecyclarAdapter = horizontalRecyclerView.a(this.mBaseItemView.getContext(), 0);
        }
        horizontalRecyclerView.setAdapter(this.horizontalRecyclarAdapter);
        final ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        if (arrListBusinessObj.size() > 0) {
            horizontalRecyclerView.setViewRecycleListner(0, arrListBusinessObj.size(), false, new c() {
                public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                    return viewHolder;
                }

                public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                    View view = null;
                    if (SimilarItemHorizontalScroll.this.mBaseItemView instanceof AlbumItemView) {
                        view = ((AlbumItemView) SimilarItemHorizontalScroll.this.mBaseItemView).getPoplatedViewForGrid(viewHolder, (BusinessObject) arrListBusinessObj.get(i3), null);
                    } else if ((SimilarItemHorizontalScroll.this.mBaseItemView instanceof GenericItemView) && (SimilarItemHorizontalScroll.this.mFragment instanceof GaanaSpecialDetailsMaterialListing)) {
                        ((GenericItemView) SimilarItemHorizontalScroll.this.mBaseItemView).setSourceName(PLAYOUT_SECTION_TYPE.SIMILAR_ALBUM.name());
                        view = ((GenericItemView) SimilarItemHorizontalScroll.this.mBaseItemView).getPoplatedGenericView(i3, viewHolder, (BusinessObject) arrListBusinessObj.get(i3), null, "");
                    } else if (SimilarItemHorizontalScroll.this.mBaseItemView instanceof RadioItemView) {
                        view = ((RadioItemView) SimilarItemHorizontalScroll.this.mBaseItemView).getPoplatedViewForGrid(viewHolder, (BusinessObject) arrListBusinessObj.get(i3), null);
                    } else if (SimilarItemHorizontalScroll.this.mBaseItemView instanceof ArtistItemView) {
                        view = ((ArtistItemView) SimilarItemHorizontalScroll.this.mBaseItemView).getPoplatedViewForGrid(viewHolder.itemView, (BusinessObject) arrListBusinessObj.get(i3), null);
                    } else if (SimilarItemHorizontalScroll.this.mBaseItemView instanceof TrackItemView) {
                        view = ((TrackItemView) SimilarItemHorizontalScroll.this.mBaseItemView).getPoplatedViewForGrid(viewHolder, (BusinessObject) arrListBusinessObj.get(i3), null, SimilarItemHorizontalScroll.this.onRecyclerItemClicked, i3);
                    }
                    if (i3 == 0) {
                        view.setPadding((int) SimilarItemHorizontalScroll.this.getResources().getDimension(R.dimen.activity_horizontal_margin), 0, (int) SimilarItemHorizontalScroll.this.getResources().getDimension(R.dimen.activity_horizontal_margin_half), 0);
                    } else {
                        view.setPadding((int) SimilarItemHorizontalScroll.this.getResources().getDimension(R.dimen.activity_horizontal_margin_half), 0, (int) SimilarItemHorizontalScroll.this.getResources().getDimension(R.dimen.activity_horizontal_margin_half), 0);
                    }
                    return view;
                }
            });
        }
        return inflate;
    }
}
