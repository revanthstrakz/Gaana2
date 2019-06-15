package com.gaana.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.PersonalizedTags;
import com.gaana.models.PersonalizedTags.PersonalizedTag;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.u;
import com.services.c;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class SmartFeedNxtgenItemView extends BaseItemView {
    private static final String DEFAULT_COLOR_BOTTOM = "#DC4D6F";
    private static final String DEFAULT_COLOR_TOP = "#BB95C5";
    private boolean isFirstCall = true;
    private boolean isToBeRefreshed = false;
    private a mDynamicView;
    private ArrayList<PersonalizedTag> mListOfTags = new ArrayList();
    private View mView = null;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    class ViewPagerAdapter extends PagerAdapter implements OnClickListener {
        private LayoutInflater mLayoutInflater;

        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        public ViewPagerAdapter() {
            this.mLayoutInflater = LayoutInflater.from(SmartFeedNxtgenItemView.this.mContext);
        }

        public int getCount() {
            if (SmartFeedNxtgenItemView.this.mListOfTags.size() > 0) {
                return SmartFeedNxtgenItemView.this.mListOfTags.size();
            }
            return SmartFeedNxtgenItemView.this.mDynamicView.s();
        }

        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            ViewGroup viewGroup2 = viewGroup;
            View inflate = this.mLayoutInflater.inflate(R.layout.item_smart_feed_nxtgen, viewGroup2, false);
            if (SmartFeedNxtgenItemView.this.mListOfTags.size() > 0) {
                final PersonalizedTag personalizedTag = (PersonalizedTag) SmartFeedNxtgenItemView.this.mListOfTags.get(i);
                String colorTop = !TextUtils.isEmpty(personalizedTag.getColorTop()) ? personalizedTag.getColorTop() : SmartFeedNxtgenItemView.DEFAULT_COLOR_TOP;
                String colorBottom = !TextUtils.isEmpty(personalizedTag.getColorBottom()) ? personalizedTag.getColorBottom() : SmartFeedNxtgenItemView.DEFAULT_COLOR_BOTTOM;
                int parseColor = Color.parseColor(colorTop);
                int parseColor2 = Color.parseColor(colorBottom);
                int[] iArr = new int[]{parseColor, parseColor2};
                CrossFadeImageView crossFadeImageView = (CrossFadeImageView) inflate.findViewById(R.id.img_1);
                CrossFadeImageView crossFadeImageView2 = (CrossFadeImageView) inflate.findViewById(R.id.img_2);
                CrossFadeImageView crossFadeImageView3 = (CrossFadeImageView) inflate.findViewById(R.id.img_3);
                CrossFadeImageView crossFadeImageView4 = (CrossFadeImageView) inflate.findViewById(R.id.img_4);
                TextView textView = (TextView) inflate.findViewById(R.id.txt_1);
                TextView textView2 = (TextView) inflate.findViewById(R.id.txt_2);
                TextView textView3 = (TextView) inflate.findViewById(R.id.txt_3);
                TextView textView4 = (TextView) inflate.findViewById(R.id.txt_4);
                crossFadeImageView.setOnClickListener(this);
                crossFadeImageView2.setOnClickListener(this);
                crossFadeImageView3.setOnClickListener(this);
                crossFadeImageView4.setOnClickListener(this);
                textView.setOnClickListener(this);
                textView2.setOnClickListener(this);
                textView3.setOnClickListener(this);
                textView4.setOnClickListener(this);
                inflate.setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, iArr));
                TextView textView5 = (TextView) inflate.findViewById(R.id.txt_header);
                textView5.setTypeface(Typeface.DEFAULT_BOLD);
                TextView textView6 = (TextView) inflate.findViewById(R.id.txt_sub_header);
                textView5.setText(personalizedTag.getTitle());
                textView6.setText(personalizedTag.getSubTitle());
                Button button = (Button) inflate.findViewById(R.id.btn_action);
                button.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (Util.j(SmartFeedNxtgenItemView.this.mContext)) {
                            ((BaseActivity) SmartFeedNxtgenItemView.this.mContext).showProgressDialog();
                            u.a().a("Smartfeed_Tags", "Click Button", ((ViewGroup) view.getParent()).getTag() != null ? ((PersonalizedTag) ((ViewGroup) view.getParent()).getTag()).getEnglishName() : "TAGS");
                            c.a(SmartFeedNxtgenItemView.this.mContext).a(SmartFeedNxtgenItemView.this.mContext, personalizedTag.getButtonUrl(), GaanaApplication.getInstance());
                            return;
                        }
                        aj.a().a(SmartFeedNxtgenItemView.this.mContext, SmartFeedNxtgenItemView.this.mContext.getResources().getString(R.string.error_msg_no_connection));
                    }
                });
                button.setText(personalizedTag.getButtonText());
                button.setTypeface(Typeface.DEFAULT_BOLD);
                textView6 = (TextView) inflate.findViewById(R.id.txt_action_desc);
                textView6.setText(personalizedTag.getButtonDesc());
                ArrayList items = personalizedTag.getItems();
                if (items != null) {
                    switch (items.size()) {
                        case 1:
                            crossFadeImageView.setVisibility(0);
                            crossFadeImageView2.setVisibility(4);
                            crossFadeImageView3.setVisibility(4);
                            crossFadeImageView4.setVisibility(4);
                            button.setVisibility(0);
                            textView.setVisibility(0);
                            textView2.setVisibility(4);
                            textView3.setVisibility(4);
                            textView4.setVisibility(4);
                            textView.setText(((Item) items.get(0)).getName());
                            textView6.setVisibility(0);
                            crossFadeImageView.bindImage(((Item) items.get(0)).getArtwork());
                            crossFadeImageView.setTag(items.get(0));
                            textView.setTag(items.get(0));
                            break;
                        case 2:
                            crossFadeImageView.setVisibility(0);
                            crossFadeImageView2.setVisibility(0);
                            crossFadeImageView3.setVisibility(4);
                            crossFadeImageView4.setVisibility(4);
                            textView.setVisibility(0);
                            textView2.setVisibility(0);
                            textView3.setVisibility(4);
                            textView4.setVisibility(4);
                            textView.setText(((Item) items.get(0)).getName());
                            textView2.setText(((Item) items.get(1)).getName());
                            crossFadeImageView.bindImage(((Item) items.get(0)).getArtwork());
                            crossFadeImageView2.bindImage(((Item) items.get(1)).getArtwork());
                            crossFadeImageView.setTag(items.get(0));
                            crossFadeImageView2.setTag(items.get(1));
                            textView.setTag(items.get(0));
                            textView2.setTag(items.get(1));
                            button.setVisibility(0);
                            textView6.setVisibility(0);
                            break;
                        case 3:
                            crossFadeImageView.setVisibility(0);
                            crossFadeImageView2.setVisibility(0);
                            crossFadeImageView3.setVisibility(0);
                            crossFadeImageView4.setVisibility(4);
                            textView.setVisibility(0);
                            textView2.setVisibility(0);
                            textView3.setVisibility(0);
                            textView4.setVisibility(4);
                            textView.setText(((Item) items.get(0)).getName());
                            textView2.setText(((Item) items.get(1)).getName());
                            textView3.setText(((Item) items.get(2)).getName());
                            crossFadeImageView.bindImage(((Item) items.get(0)).getArtwork());
                            crossFadeImageView2.bindImage(((Item) items.get(1)).getArtwork());
                            crossFadeImageView3.bindImage(((Item) items.get(2)).getArtwork());
                            crossFadeImageView.setTag(items.get(0));
                            crossFadeImageView2.setTag(items.get(1));
                            crossFadeImageView3.setTag(items.get(2));
                            textView.setTag(items.get(0));
                            textView2.setTag(items.get(1));
                            textView3.setTag(items.get(2));
                            button.setVisibility(4);
                            textView6.setVisibility(4);
                            break;
                        case 4:
                            crossFadeImageView.setVisibility(0);
                            crossFadeImageView2.setVisibility(0);
                            crossFadeImageView3.setVisibility(0);
                            crossFadeImageView4.setVisibility(0);
                            textView.setVisibility(0);
                            textView2.setVisibility(0);
                            textView3.setVisibility(0);
                            textView4.setVisibility(0);
                            textView.setText(((Item) items.get(0)).getName());
                            textView2.setText(((Item) items.get(1)).getName());
                            textView3.setText(((Item) items.get(2)).getName());
                            textView4.setText(((Item) items.get(3)).getName());
                            crossFadeImageView.bindImage(((Item) items.get(0)).getArtwork());
                            crossFadeImageView2.bindImage(((Item) items.get(1)).getArtwork());
                            crossFadeImageView3.bindImage(((Item) items.get(2)).getArtwork());
                            crossFadeImageView4.bindImage(((Item) items.get(3)).getArtwork());
                            crossFadeImageView.setTag(items.get(0));
                            crossFadeImageView2.setTag(items.get(1));
                            crossFadeImageView3.setTag(items.get(2));
                            crossFadeImageView4.setTag(items.get(3));
                            textView.setTag(items.get(0));
                            textView2.setTag(items.get(1));
                            textView3.setTag(items.get(2));
                            textView4.setTag(items.get(3));
                            button.setVisibility(4);
                            textView6.setVisibility(4);
                            break;
                        default:
                            inflate.setVisibility(8);
                            break;
                    }
                }
                inflate.setTag(personalizedTag);
            } else {
                inflate.setBackgroundDrawable(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{Color.parseColor(SmartFeedNxtgenItemView.DEFAULT_COLOR_TOP), Color.parseColor(SmartFeedNxtgenItemView.DEFAULT_COLOR_BOTTOM)}));
            }
            viewGroup2.addView(inflate);
            return inflate;
        }

        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }

        public void onClick(View view) {
            if (!Util.j(SmartFeedNxtgenItemView.this.mContext)) {
                aj.a().a(SmartFeedNxtgenItemView.this.mContext, SmartFeedNxtgenItemView.this.mContext.getResources().getString(R.string.error_msg_no_connection));
            } else if (view.getTag() != null) {
                String englishName = ((ViewGroup) view.getParent()).getTag() != null ? ((PersonalizedTag) ((ViewGroup) view.getParent()).getTag()).getEnglishName() : "TAGS";
                Item item = (Item) view.getTag();
                if (item != null) {
                    BaseGaanaFragment o = Util.o(item);
                    if (o != null) {
                        ((GaanaActivity) SmartFeedNxtgenItemView.this.mContext).displayFragment(o);
                    } else {
                        aj.a().a(SmartFeedNxtgenItemView.this.mContext, SmartFeedNxtgenItemView.this.mContext.getString(R.string.error_msg_unexpected_error));
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(englishName);
                    stringBuilder.append("_");
                    stringBuilder.append(item.getEnglishName());
                    u.a().a("Smartfeed_Tags", "Click Playlist", stringBuilder.toString());
                }
            }
        }
    }

    public void setFirstCall(boolean z) {
    }

    public SmartFeedNxtgenItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public SmartFeedNxtgenItemView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
    }

    public SmartFeedNxtgenItemView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
    }

    public void setIsToBeRefreshed(boolean z) {
        this.isToBeRefreshed = z;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        if (this.mDynamicView.s()) {
            return LayoutInflater.from(this.mContext).inflate(R.layout.view_fixed_height_pager, viewGroup, false);
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        return linearLayout;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (this.mViewPagerAdapter == null) {
            this.mViewPagerAdapter = new ViewPagerAdapter();
        }
        final ViewGroup viewGroup2 = (ViewGroup) viewHolder.itemView;
        if (this.mDynamicView.s()) {
            viewGroup2.setVisibility(0);
            ((ViewPager) viewGroup2.findViewById(R.id.view_pager)).setAdapter(this.mViewPagerAdapter);
        }
        if (this.isFirstCall || this.isToBeRefreshed) {
            this.isFirstCall = false;
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.c(Boolean.valueOf(this.isToBeRefreshed));
            uRLManager.a(Integer.valueOf(this.mDynamicView.g()).intValue());
            uRLManager.a(this.mDynamicView.l());
            uRLManager.a(PersonalizedTags.class);
            this.isToBeRefreshed = false;
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    PersonalizedTags personalizedTags = (PersonalizedTags) businessObject;
                    if (personalizedTags == null || personalizedTags.getArrListBusinessObj() == null || personalizedTags.getArrListBusinessObj().size() <= 0) {
                        viewGroup2.removeAllViews();
                        viewGroup2.setVisibility(8);
                        return;
                    }
                    if (SmartFeedNxtgenItemView.this.mDynamicView.s()) {
                        viewGroup2.removeAllViews();
                    } else {
                        View inflate = LayoutInflater.from(SmartFeedNxtgenItemView.this.mContext).inflate(R.layout.view_fixed_height_pager, viewGroup2, false);
                        viewGroup2.removeAllViews();
                        viewGroup2.addView(inflate);
                        viewGroup2.setVisibility(0);
                    }
                    ViewPager viewPager = (ViewPager) viewGroup2.findViewById(R.id.view_pager);
                    viewPager.setVisibility(0);
                    SmartFeedNxtgenItemView.this.mListOfTags.clear();
                    SmartFeedNxtgenItemView.this.mListOfTags.addAll(personalizedTags.getArrListBusinessObj());
                    viewPager.setAdapter(SmartFeedNxtgenItemView.this.mViewPagerAdapter);
                    int dimensionPixelSize = SmartFeedNxtgenItemView.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp5);
                    int dimensionPixelSize2 = SmartFeedNxtgenItemView.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp15);
                    viewPager.setPageMargin(dimensionPixelSize);
                    viewPager.setClipToPadding(false);
                    viewPager.setPadding(dimensionPixelSize2, 0, dimensionPixelSize2, 0);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    viewGroup2.removeAllViews();
                    viewGroup2.setVisibility(8);
                }
            }, uRLManager);
        }
        return viewHolder.itemView;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(getNewView(-1, viewGroup));
    }
}
