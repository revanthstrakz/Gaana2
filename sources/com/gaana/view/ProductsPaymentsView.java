package com.gaana.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.collapsible_header.f;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.PaymentDetailFragment;
import com.fragments.ReferFriendsFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.ViewPagerAdapter.AddItemListner;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.PaymentProductModel;
import com.gaana.models.PaymentProductModel.LayoutConfig;
import com.gaana.models.PaymentProductModel.PageHeaderConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.item.AppUpdaterView;
import com.gaana.view.transform.ScaleTransformer;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.library.controls.CrossFadeImageView;
import com.managers.PurchaseGoogleManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.PurchaseGoogleManager.c;
import com.managers.PurchaseGoogleManager.d;
import com.managers.URLManager;
import com.managers.ag;
import com.managers.ag.a;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.o;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import com.views.ExpandableHeightListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class ProductsPaymentsView extends BaseItemView {
    AddItemListner addItemListener = new AddItemListner() {
        public Object addItem(ViewGroup viewGroup, ListingButton listingButton) {
            return null;
        }

        public Object addItem(ViewGroup viewGroup, int i) {
            ArrayList access$1100;
            if (i == 0) {
                access$1100 = ProductsPaymentsView.this.bundledProductItems;
            } else {
                access$1100 = ProductsPaymentsView.this.normalProductItems;
            }
            View access$1500;
            ExpandableHeightListView expandableHeightListView;
            if (ProductsPaymentsView.this.lPageHeaderConfig == null || TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) || !ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals("1")) {
                if (ProductsPaymentsView.this.lPageHeaderConfig == null || TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) || !ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                    if (ProductsPaymentsView.this.lPageHeaderConfig != null && !TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) && ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals("4")) {
                        ProductsPaymentsView.this.mIsFocusEnable = ProductsPaymentsView.this.lPageHeaderConfig.isFocusEnable();
                        if (access$1100.size() > 0) {
                            access$1500 = ProductsPaymentsView.this.showCarouselForPlan(access$1100, R.layout.product_carousel_item, true);
                            viewGroup.addView(access$1500);
                            return access$1500;
                        }
                        access$1500 = new View(ProductsPaymentsView.this.mContext);
                        viewGroup.addView(access$1500);
                        return access$1500;
                    } else if (access$1100.size() > 0) {
                        access$1500 = ProductsPaymentsView.this.showRecommendedProductPlan(access$1100, ProductsPaymentsView.this.lPageHeaderConfig, true);
                        viewGroup.addView(access$1500);
                        return access$1500;
                    } else {
                        access$1500 = new View(ProductsPaymentsView.this.mContext);
                        viewGroup.addView(access$1500);
                        return access$1500;
                    }
                } else if (access$1100.size() > 0) {
                    expandableHeightListView = new ExpandableHeightListView(ProductsPaymentsView.this.mContext);
                    expandableHeightListView.setLayoutParams(new LayoutParams(-1, -1));
                    expandableHeightListView.setHasFixedSize(true);
                    expandableHeightListView.setExpanded(true);
                    ProductsPaymentsView.this.setOnScrollListener((ScrollView) ProductsPaymentsView.this.mView.findViewById(R.id.scrollContainer));
                    Resources resources = ProductsPaymentsView.this.mContext.getResources();
                    expandableHeightListView.setPadding((int) resources.getDimension(R.dimen.dp12), (int) resources.getDimension(R.dimen.dp10), (int) resources.getDimension(R.dimen.dp12), (int) resources.getDimension(R.dimen.dp10));
                    expandableHeightListView.setVisibility(0);
                    expandableHeightListView.setLayoutManager(new LinearLayoutManager(ProductsPaymentsView.this.mContext));
                    expandableHeightListView.setAdapter(new ProductListAdapter(access$1100, R.layout.product_list_item_view));
                    viewGroup.addView(expandableHeightListView);
                    return expandableHeightListView;
                } else {
                    access$1500 = new View(ProductsPaymentsView.this.mContext);
                    viewGroup.addView(access$1500);
                    return access$1500;
                }
            } else if (access$1100.size() > 0) {
                expandableHeightListView = new ExpandableHeightListView(ProductsPaymentsView.this.mContext);
                expandableHeightListView.setLayoutParams(new LayoutParams(-1, -1));
                expandableHeightListView.setHasFixedSize(true);
                expandableHeightListView.setExpanded(true);
                ProductsPaymentsView.this.setOnScrollListener((ScrollView) ProductsPaymentsView.this.mView.findViewById(R.id.scrollContainer));
                int dimension = (int) ProductsPaymentsView.this.mContext.getResources().getDimension(R.dimen.dp10);
                expandableHeightListView.setPadding(dimension, dimension, dimension, dimension);
                expandableHeightListView.setVisibility(0);
                expandableHeightListView.setLayoutManager(new GridLayoutManager(ProductsPaymentsView.this.mContext, 2));
                expandableHeightListView.setAdapter(new ProductListAdapter(access$1100, R.layout.gaana_plus_product_item));
                viewGroup.addView(expandableHeightListView);
                return expandableHeightListView;
            } else {
                access$1500 = new View(ProductsPaymentsView.this.mContext);
                viewGroup.addView(access$1500);
                return access$1500;
            }
        }
    };
    private ArrayList<ProductItem> bundledProductItems = new ArrayList();
    private String couponCode;
    private String curr_symbol = "";
    private ImageView[] dots;
    private int dotsCount;
    private TypedValue first_line_color = new TypedValue();
    private long initialTime;
    private String itemId;
    private PageHeaderConfig lPageHeaderConfig;
    private ViewPager mCarouselHeader;
    private int mFinalPosition = -1;
    private GenericBackActionBar mGenericBackActionBar;
    private boolean mIsFocusEnable;
    private ArrayList<String> mItemIdStrings = new ArrayList();
    private int mLayoutResourceId = R.layout.gaana_plus_payment_flow;
    private View mOverlay;
    private ArrayList<String> mProductList = new ArrayList();
    private View mProductLoadProgressBar;
    private View mProductRetryLayout;
    private URLManager mUrlManager;
    private ArrayList<String> mUrls;
    private View mView = null;
    private boolean m_SCROLL_STATE_DRAGGING = false;
    private int nonStudentPackBundle = 2;
    private int nonStudentPackNormal = 0;
    private ArrayList<ProductItem> normalProductItems = new ArrayList();
    private PaymentProductModel paymentProductModel;
    private String productId;
    private boolean scroll_animation = true;
    private int studentPack = 1;

    protected class ProductCarouselAdapter extends Adapter<ViewHolder> {
        private boolean isTrialStatusEnable = false;
        private int mLayoutChildId;
        ArrayList<ProductItem> product_item_list;

        public ProductCarouselAdapter(ArrayList<ProductItem> arrayList, int i, boolean z) {
            this.isTrialStatusEnable = z;
            this.product_item_list = arrayList;
            this.mLayoutChildId = i;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == ProductsPaymentsView.this.nonStudentPackBundle) {
                return new ProductCarouselBundleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_carousel_bundled_item, viewGroup, false));
            }
            return new ProductCarouselHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutChildId, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (this.isTrialStatusEnable) {
                i++;
            }
            if (((ProductItem) this.product_item_list.get(i)).getIsBundlePack()) {
                ProductCarouselBundleHolder productCarouselBundleHolder = (ProductCarouselBundleHolder) viewHolder;
                productCarouselBundleHolder.mTxtProductPackTitle.setTypeface(Typeface.createFromAsset(ProductsPaymentsView.this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
                ProductsPaymentsView.this.bindDataToView((ProductItem) this.product_item_list.get(i), productCarouselBundleHolder, i);
                return;
            }
            ProductCarouselHolder productCarouselHolder = (ProductCarouselHolder) viewHolder;
            productCarouselHolder.mTxtProductCostFinal.setTypeface(Typeface.createFromAsset(ProductsPaymentsView.this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            productCarouselHolder.mTxtProductPopular.setTypeface(Typeface.createFromAsset(ProductsPaymentsView.this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            ProductsPaymentsView.this.bindDataToView((ProductItem) this.product_item_list.get(i), productCarouselHolder, i);
        }

        public int getItemViewType(int i) {
            if (this.isTrialStatusEnable) {
                i++;
            }
            if (((ProductItem) this.product_item_list.get(i)).getIsBundlePack()) {
                return ProductsPaymentsView.this.nonStudentPackBundle;
            }
            return ProductsPaymentsView.this.nonStudentPackNormal;
        }

        public int getItemCount() {
            if (this.isTrialStatusEnable) {
                return this.product_item_list.size() - 1;
            }
            return this.product_item_list.size();
        }
    }

    class ProductCarouselBundleHolder extends ViewHolder {
        private TextView mTxtKnowMore;
        private TextView mTxtProductCostFinal;
        private TextView mTxtProductMsgSub;
        private TextView mTxtProductOrgText;
        private TextView mTxtProductPackDesc;
        private TextView mTxtProductPackTitle;
        private TextView mTxtProductYouSave;
        private View productOverlayView;
        private TextView tncText;

        public ProductCarouselBundleHolder(View view) {
            super(view);
            this.mTxtProductPackTitle = (TextView) view.findViewById(R.id.product_pack_title);
            this.mTxtProductPackDesc = (TextView) view.findViewById(R.id.product_pack_desc);
            this.mTxtKnowMore = (TextView) view.findViewById(R.id.know_more_text);
            this.mTxtProductOrgText = (TextView) view.findViewById(R.id.product_cost_org_text);
            this.mTxtProductCostFinal = (TextView) view.findViewById(R.id.product_cost_final_text);
            this.mTxtProductYouSave = (TextView) view.findViewById(R.id.product_you_save_text);
            this.mTxtProductMsgSub = (TextView) view.findViewById(R.id.tv_product_msg_sub);
            this.productOverlayView = view.findViewById(R.id.product_overlay_view);
            this.tncText = (TextView) view.findViewById(R.id.txt_tnc);
        }
    }

    class ProductCarouselHolder extends ViewHolder {
        private TextView mTxtOffer;
        private TextView mTxtOfferOff;
        private TextView mTxtProductCostFinal;
        private TextView mTxtProductCostMonth;
        private TextView mTxtProductMsgSub;
        private TextView mTxtProductOrgText;
        private TextView mTxtProductPackCostDigit;
        private TextView mTxtProductPopular;
        private TextView mTxtProductYouSave;
        private View productOverlayView;

        public ProductCarouselHolder(View view) {
            super(view);
            this.mTxtProductPopular = (TextView) view.findViewById(R.id.product_pack_popular);
            this.mTxtProductPackCostDigit = (TextView) view.findViewById(R.id.product_pack_cost_digit);
            this.mTxtOffer = (TextView) view.findViewById(R.id.offer_text);
            this.mTxtOfferOff = (TextView) view.findViewById(R.id.offer_text_off);
            this.mTxtProductCostMonth = (TextView) view.findViewById(R.id.product_pack_cost_month);
            this.mTxtProductOrgText = (TextView) view.findViewById(R.id.product_cost_org_text);
            this.mTxtProductCostFinal = (TextView) view.findViewById(R.id.product_cost_final_text);
            this.mTxtProductYouSave = (TextView) view.findViewById(R.id.product_you_save_text);
            this.mTxtProductMsgSub = (TextView) view.findViewById(R.id.tv_product_msg_sub);
            this.productOverlayView = view.findViewById(R.id.product_overlay_view);
        }
    }

    public static class ProductItemViewholder extends ViewHolder {
        TextView offer_text;
        TextView offer_text_off;
        TextView productMsgText;
        ImageView studentPackImage;
        TextView tv_product_cost;
        TextView tv_product_name;
        TextView tv_product_org_cost;
        TextView youSaveText;

        public ProductItemViewholder(View view) {
            super(view);
            this.tv_product_name = (TextView) view.findViewById(R.id.tv_product_name);
            this.tv_product_cost = (TextView) view.findViewById(R.id.tv_product_cost);
            this.tv_product_org_cost = (TextView) view.findViewById(R.id.tv_product_org_cost);
            this.offer_text = (TextView) view.findViewById(R.id.offer_text);
            this.offer_text_off = (TextView) view.findViewById(R.id.offer_text_off);
            this.youSaveText = (TextView) view.findViewById(R.id.tv_product_you_save);
            this.productMsgText = (TextView) view.findViewById(R.id.tv_product_msg_sub);
            this.studentPackImage = (ImageView) view.findViewById(R.id.studentPackImage);
        }
    }

    class ProductListAdapter extends Adapter<ViewHolder> {
        private boolean isTrialStatusEnable = false;
        private int mLayoutChildId;
        private int mPosition = 0;
        ArrayList<ProductItem> product_item_list;

        public ProductListAdapter(ArrayList<ProductItem> arrayList, int i) {
            this.product_item_list = arrayList;
            ProductItem productItem = (ProductItem) arrayList.get(0);
            if (!(productItem == null || TextUtils.isEmpty(productItem.getIs_trial()))) {
                this.isTrialStatusEnable = true;
            }
            this.mLayoutChildId = i;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ProductItemViewholder productItemViewholder = new ProductItemViewholder(ProductsPaymentsView.this.mInflater.inflate(this.mLayoutChildId, viewGroup, false));
            productItemViewholder.tv_product_name.setTypeface(Typeface.createFromAsset(ProductsPaymentsView.this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            productItemViewholder.tv_product_cost.setTypeface(Typeface.createFromAsset(ProductsPaymentsView.this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            return productItemViewholder;
        }

        public int getItemCount() {
            if (this.isTrialStatusEnable) {
                return this.product_item_list.size() - 1;
            }
            return this.product_item_list.size();
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            TextView textView;
            StringBuilder stringBuilder;
            final ProductItemViewholder productItemViewholder = (ProductItemViewholder) viewHolder;
            this.mPosition = i;
            if (this.isTrialStatusEnable) {
                this.mPosition = i + 1;
            }
            final ProductItem productItem = (ProductItem) this.product_item_list.get(this.mPosition);
            View view = viewHolder.itemView;
            ImageView imageView = productItemViewholder.studentPackImage;
            if (!ProductsPaymentsView.this.mItemIdStrings.contains(((ProductItem) this.product_item_list.get(this.mPosition)).getItem_id())) {
                ProductsPaymentsView.this.mItemIdStrings.add(((ProductItem) this.product_item_list.get(this.mPosition)).getItem_id());
                u.a().a(productItem, this.mPosition);
            }
            if (!(productItem == null || TextUtils.isEmpty(productItem.getP_cost_curr()))) {
                ProductsPaymentsView productsPaymentsView = ProductsPaymentsView.this;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(productItem.getP_cost_curr());
                stringBuilder2.append(" ");
                productsPaymentsView.curr_symbol = stringBuilder2.toString();
            }
            if (productItem == null || TextUtils.isEmpty(productItem.getDesc())) {
                productItemViewholder.tv_product_name.setVisibility(8);
            } else {
                productItemViewholder.tv_product_name.setText(productItem.getDesc());
                productItemViewholder.tv_product_name.setVisibility(0);
            }
            if (productItem == null || TextUtils.isEmpty(productItem.getP_orig_cost())) {
                productItemViewholder.tv_product_org_cost.setVisibility(8);
            } else {
                textView = productItemViewholder.tv_product_org_cost;
                stringBuilder = new StringBuilder();
                stringBuilder.append(ProductsPaymentsView.this.curr_symbol);
                stringBuilder.append(productItem.getP_orig_cost());
                textView.setText(stringBuilder.toString());
                productItemViewholder.tv_product_org_cost.setVisibility(0);
                productItemViewholder.tv_product_org_cost.setPaintFlags(productItemViewholder.tv_product_org_cost.getPaintFlags() | 16);
            }
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductsPaymentsView.this.handleProductItemClick(productItem, ProductListAdapter.this.mPosition);
                    u.a().a("Subscription_Payments", ProductsPaymentsView.this.mProductList.toString(), productItem.getDesc());
                }
            });
            if (TextUtils.isEmpty(productItem.getP_orig_cost()) || TextUtils.isEmpty(productItem.getP_cost())) {
                productItemViewholder.youSaveText.setVisibility(8);
                productItemViewholder.offer_text.setVisibility(8);
                productItemViewholder.offer_text_off.setVisibility(8);
            } else {
                float floatValue = Float.valueOf(productItem.getP_orig_cost()).floatValue() - Float.valueOf(productItem.getP_cost()).floatValue();
                if (floatValue > 0.0f) {
                    productItemViewholder.youSaveText.setVisibility(0);
                    textView = productItemViewholder.youSaveText;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(ProductsPaymentsView.this.mContext.getResources().getString(R.string.you_save));
                    stringBuilder.append(" ");
                    stringBuilder.append(ProductsPaymentsView.this.curr_symbol);
                    stringBuilder.append(Math.round(floatValue));
                    textView.setText(stringBuilder.toString());
                    productItemViewholder.offer_text.setVisibility(0);
                    productItemViewholder.offer_text_off.setVisibility(0);
                    int round = Math.round((floatValue / Float.valueOf(productItem.getP_orig_cost()).floatValue()) * 100.0f);
                    textView = productItemViewholder.offer_text;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(round);
                    stringBuilder.append("%");
                    textView.setText(stringBuilder.toString());
                }
            }
            if (!(productItem.getPlanType() == null || !productItem.getPlanType().equalsIgnoreCase("1") || ProductsPaymentsView.this.lPageHeaderConfig == null || ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equalsIgnoreCase("1"))) {
                imageView.setVisibility(0);
                productItemViewholder.youSaveText.setVisibility(0);
                productItemViewholder.youSaveText.setText("NEW");
            }
            if (productItem == null || TextUtils.isEmpty(productItem.getP_cost())) {
                productItemViewholder.tv_product_cost.setVisibility(8);
                return;
            }
            TextView textView2 = productItemViewholder.tv_product_cost;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(ProductsPaymentsView.this.curr_symbol);
            stringBuilder3.append(productItem.getP_cost());
            textView2.setText(stringBuilder3.toString());
            productItemViewholder.tv_product_cost.setVisibility(0);
            String str = "";
            final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(ProductsPaymentsView.this.mContext).e();
            if (productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode()) && productItem.getP_payment_mode().equalsIgnoreCase("android")) {
                str = productItem.getP_id();
            } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || productItem == null || TextUtils.isEmpty(productItem.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !productItem.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                str = e.getIntro_config().getIntro_p_id();
            }
            if (!TextUtils.isEmpty(str)) {
                PurchaseGoogleManager.a(ProductsPaymentsView.this.mContext, null).a(str, new d() {
                    public void onGoolgeProductPriceQueryConpleted(c cVar) {
                        CharSequence charSequence = "";
                        if (cVar != null) {
                            charSequence = cVar.a() ? cVar.c() : cVar.b();
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            TextView textView = productItemViewholder.tv_product_cost;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(ProductsPaymentsView.this.curr_symbol);
                            stringBuilder.append(productItem.getP_cost());
                            textView.setText(stringBuilder.toString());
                            return;
                        }
                        productItemViewholder.tv_product_cost.setText(charSequence);
                        if (cVar.a() && e != null) {
                            productItemViewholder.youSaveText.setVisibility(0);
                            productItemViewholder.productMsgText.setVisibility(0);
                            TextView textView2 = productItemViewholder.youSaveText;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(e.getIntro_config().getIntro_title());
                            stringBuilder2.append(",");
                            textView2.setText(stringBuilder2.toString());
                            productItemViewholder.youSaveText.setTextColor(ProductsPaymentsView.this.mContext.getResources().getColor(R.color.new_gaana_red));
                            charSequence = e.getIntro_config().getIntroductory_offer_expire_msg();
                            if (!TextUtils.isEmpty(charSequence) && charSequence.contains("&&&&")) {
                                charSequence = charSequence.replace("&&&&", cVar.b());
                            }
                            if (!TextUtils.isEmpty(charSequence) && charSequence.contains("####")) {
                                charSequence = charSequence.replace("####", cVar.c());
                            }
                            productItemViewholder.productMsgText.setText(charSequence);
                            if (!(productItem == null || TextUtils.isEmpty(cVar.b()))) {
                                productItemViewholder.tv_product_org_cost.setText(cVar.b());
                                productItemViewholder.tv_product_org_cost.setVisibility(0);
                                productItemViewholder.tv_product_org_cost.setPaintFlags(productItemViewholder.tv_product_org_cost.getPaintFlags() | 16);
                            }
                            long d = cVar.d() - cVar.e();
                            if (d > 0) {
                                productItemViewholder.offer_text.setVisibility(0);
                                productItemViewholder.offer_text_off.setVisibility(0);
                                int round = Math.round((((float) d) / ((float) cVar.d())) * 100.0f);
                                textView2 = productItemViewholder.offer_text;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(round);
                                stringBuilder2.append("%");
                                textView2.setText(stringBuilder2.toString());
                            }
                            String intro_duration = e.getIntro_config().getIntro_duration();
                            if (!TextUtils.isEmpty(intro_duration)) {
                                productItemViewholder.tv_product_name.setText(intro_duration);
                                productItemViewholder.tv_product_name.setVisibility(0);
                            }
                        }
                    }
                });
            }
        }
    }

    class StudentCarouselHolder extends ViewHolder {
        TextView monthText;
        TextView rateText;
        TextView studentPackText;

        public StudentCarouselHolder(View view) {
            super(view);
            this.studentPackText = (TextView) view.findViewById(R.id.studentPackText);
            this.rateText = (TextView) view.findViewById(R.id.rateText);
            this.monthText = (TextView) view.findViewById(R.id.monthText);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private Context mContext;
        private boolean mIsCarousel;
        private ArrayList<ProductItem> paymentProductList;

        public ViewPagerAdapter(Context context, ArrayList<String> arrayList, boolean z, ArrayList<ProductItem> arrayList2) {
            this.mContext = context;
            ProductsPaymentsView.this.mUrls = arrayList;
            this.paymentProductList = arrayList2;
            this.mIsCarousel = z;
        }

        public int getCount() {
            return this.mIsCarousel ? ProductsPaymentsView.this.mUrls.size() : 1;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == ((RelativeLayout) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.product_pager_item, viewGroup, false);
            CrossFadeImageView crossFadeImageView = (CrossFadeImageView) inflate.findViewById(R.id.gaana_header_carousel_image);
            if (ProductsPaymentsView.this.mUrls.size() > 0) {
                crossFadeImageView.bindImage((String) ProductsPaymentsView.this.mUrls.get(i), ScaleType.CENTER_CROP);
                crossFadeImageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (ViewPagerAdapter.this.paymentProductList != null && ViewPagerAdapter.this.paymentProductList.size() > 0) {
                            ProductsPaymentsView.this.handleProductItemClick((ProductItem) ViewPagerAdapter.this.paymentProductList.get(0), i);
                            ag.a(ViewPagerAdapter.this.mContext).a("Subscription Banner", "Gaana Plus");
                        }
                    }
                });
            }
            viewGroup.addView(inflate);
            return inflate;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((RelativeLayout) obj);
        }
    }

    public ProductsPaymentsView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getPopulatedView(ViewGroup viewGroup, BusinessObject businessObject) {
        this.mView = super.getNewView(this.mLayoutResourceId, viewGroup);
        initUI(this.mView);
        return this.mView;
    }

    private void autoScroll(ViewPager viewPager) {
        final int size = this.mUrls.size();
        final ViewPager viewPager2 = viewPager;
        new CountDownTimer(9000, 3000) {
            public void onTick(long j) {
                if (!ProductsPaymentsView.this.m_SCROLL_STATE_DRAGGING) {
                    int currentItem = viewPager2.getCurrentItem();
                    if (currentItem == size - 1) {
                        currentItem = -1;
                    }
                    if (ProductsPaymentsView.this.scroll_animation) {
                        viewPager2.setCurrentItem(currentItem + 1, true);
                        return;
                    }
                    viewPager2.setCurrentItem(currentItem + 1, false);
                    ProductsPaymentsView.this.scroll_animation = true;
                }
            }

            public void onFinish() {
                if (!ProductsPaymentsView.this.m_SCROLL_STATE_DRAGGING) {
                    ProductsPaymentsView.this.scroll_animation = false;
                    start();
                }
            }
        }.start();
    }

    private void initUI(View view) {
        ag.a(this.mContext).a("Subscription Screen", "Gaana Plus");
        this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, this.first_line_color, true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice.php?type=duration_listing");
        stringBuilder.append(ag.a(this.mContext).d());
        String stringBuilder2 = stringBuilder.toString();
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        this.mGenericBackActionBar = new GenericBackActionBar(this.mContext, this.mContext.getResources().getString(R.string.get_gaana_plus));
        this.mGenericBackActionBar.getTitleTextView().setAlpha(0.0f);
        toolbar.removeAllViews();
        this.mGenericBackActionBar.findViewById(R.id.generic_back_actionbar).setBackgroundColor(0);
        ((ImageView) this.mGenericBackActionBar.findViewById(R.id.menu_icon)).setImageResource(R.drawable.actionbar_back);
        toolbar.addView(this.mGenericBackActionBar);
        this.mProductLoadProgressBar = view.findViewById(R.id.product_load_progressbar);
        this.mProductRetryLayout = view.findViewById(R.id.product_retry_container);
        view.findViewById(R.id.product_retry_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Util.j(ProductsPaymentsView.this.mContext)) {
                    ProductsPaymentsView.this.mProductRetryLayout.setVisibility(8);
                    ProductsPaymentsView.this.mProductLoadProgressBar.setVisibility(0);
                    ProductsPaymentsView.this.retriveData();
                    return;
                }
                aj.a().a(ProductsPaymentsView.this.mContext, ProductsPaymentsView.this.getResources().getString(R.string.error_download_no_internet));
            }
        });
        this.mUrlManager = new URLManager();
        this.mUrlManager.a(stringBuilder2);
        this.mUrlManager.a(PaymentProductModel.class);
        this.mUrlManager.b(Boolean.valueOf(false));
        this.mOverlay = view.findViewById(R.id.overlay);
        if (Constants.l) {
            view.findViewById(R.id.product_layout_container).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.payment_grey_bg));
            view.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.payment_grey_bg));
        }
        retriveData();
    }

    private void retriveData() {
        this.initialTime = Calendar.getInstance().getTimeInMillis();
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                ProductsPaymentsView.this.mProductLoadProgressBar.setVisibility(8);
                LayoutConfig layoutConfig = null;
                if (ProductsPaymentsView.this.initialTime != 0) {
                    Constants.a("Load", Calendar.getInstance().getTimeInMillis() - ProductsPaymentsView.this.initialTime, "Products", null);
                }
                ProductsPaymentsView.this.paymentProductModel = (PaymentProductModel) obj;
                if (ProductsPaymentsView.this.paymentProductModel != null) {
                    TextView textView;
                    if (!(ProductsPaymentsView.this.paymentProductModel.getPageHeader() == null || ProductsPaymentsView.this.paymentProductModel.getPageHeader().getPageHeaderConfig() == null)) {
                        ProductsPaymentsView.this.lPageHeaderConfig = ProductsPaymentsView.this.paymentProductModel.getPageHeader().getPageHeaderConfig();
                    }
                    ProductsPaymentsView.this.showAbondonCard();
                    ((RelativeLayout) ProductsPaymentsView.this.mView.findViewById(R.id.scrollPagerView)).setVisibility(0);
                    if (!(ProductsPaymentsView.this.lPageHeaderConfig == null || TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getPageText()))) {
                        textView = (TextView) ProductsPaymentsView.this.mView.findViewById(R.id.gaana_plus_text);
                        textView.setText(ProductsPaymentsView.this.lPageHeaderConfig.getPageText());
                        textView.setVisibility(0);
                    }
                    if (!(ProductsPaymentsView.this.lPageHeaderConfig == null || TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getPageDesc()))) {
                        textView = (TextView) ProductsPaymentsView.this.mView.findViewById(R.id.gaana_plus_text_desc);
                        textView.setText(ProductsPaymentsView.this.lPageHeaderConfig.getPageDesc());
                        textView.setVisibility(0);
                    }
                    ProductsPaymentsView.this.setPagerForHeaderCarousel(ProductsPaymentsView.this.lPageHeaderConfig);
                    if (ProductsPaymentsView.this.lPageHeaderConfig != null) {
                        Util.g(ProductsPaymentsView.this.lPageHeaderConfig.getFootPrintId());
                    }
                    if (ProductsPaymentsView.this.paymentProductModel.getPurchase() != null) {
                        layoutConfig = ProductsPaymentsView.this.paymentProductModel.getPurchase().getPlanLayoutConfig();
                    }
                    if (ProductsPaymentsView.this.paymentProductModel.getPurchase() != null && ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts() != null && ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts().size() > 0) {
                        ExpandableHeightListView expandableHeightListView;
                        if (layoutConfig != null && layoutConfig.isSeperateTabs()) {
                            Iterator it = ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts().iterator();
                            while (it.hasNext()) {
                                ProductItem productItem = (ProductItem) it.next();
                                if (productItem.getIsBundlePack()) {
                                    ProductsPaymentsView.this.bundledProductItems.add(productItem);
                                } else {
                                    ProductsPaymentsView.this.normalProductItems.add(productItem);
                                }
                            }
                            LinearLayout linearLayout = (LinearLayout) ProductsPaymentsView.this.mView.findViewById(R.id.viewpager_container);
                            TabLayout tabLayout = (TabLayout) ProductsPaymentsView.this.mView.findViewById(R.id.sliding_tabs);
                            ScrollableViewPager scrollableViewPager = (ScrollableViewPager) ProductsPaymentsView.this.mView.findViewById(R.id.viewpager);
                            scrollableViewPager.setHorizontalScrollEnabled(false);
                            tabLayout.setTabTextColors(ProductsPaymentsView.this.first_line_color.data, ProductsPaymentsView.this.first_line_color.data);
                            tabLayout.setTabMode(0);
                            tabLayout.setTabGravity(1);
                            com.gaana.adapter.ViewPagerAdapter viewPagerAdapter = new com.gaana.adapter.ViewPagerAdapter();
                            ListingComponents a = Constants.a(layoutConfig);
                            viewPagerAdapter.setAdapterParams(a.c().size(), ProductsPaymentsView.this.addItemListener, a);
                            scrollableViewPager.setAdapter(viewPagerAdapter);
                            scrollableViewPager.setCurrentItem(layoutConfig.getDefaultOption());
                            tabLayout.setupWithViewPager(scrollableViewPager);
                            linearLayout.setVisibility(0);
                        } else if (ProductsPaymentsView.this.lPageHeaderConfig != null && !TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) && ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals("1")) {
                            expandableHeightListView = (ExpandableHeightListView) ProductsPaymentsView.this.mView.findViewById(R.id.products_recycler_view);
                            expandableHeightListView.setHasFixedSize(true);
                            expandableHeightListView.setExpanded(true);
                            ProductsPaymentsView.this.setOnScrollListener((ScrollView) ProductsPaymentsView.this.mView.findViewById(R.id.scrollContainer));
                            int dimension = (int) ProductsPaymentsView.this.mContext.getResources().getDimension(R.dimen.dp10);
                            expandableHeightListView.setPadding(dimension, dimension, dimension, dimension);
                            expandableHeightListView.setVisibility(0);
                            expandableHeightListView.setLayoutManager(new GridLayoutManager(ProductsPaymentsView.this.mContext, 2));
                            expandableHeightListView.setAdapter(new ProductListAdapter(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts(), R.layout.gaana_plus_product_item));
                        } else if (ProductsPaymentsView.this.lPageHeaderConfig != null && !TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) && ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                            expandableHeightListView = (ExpandableHeightListView) ProductsPaymentsView.this.mView.findViewById(R.id.products_recycler_view);
                            expandableHeightListView.setHasFixedSize(true);
                            expandableHeightListView.setExpanded(true);
                            ProductsPaymentsView.this.setOnScrollListener((ScrollView) ProductsPaymentsView.this.mView.findViewById(R.id.scrollContainer));
                            Resources resources = ProductsPaymentsView.this.mContext.getResources();
                            expandableHeightListView.setPadding((int) resources.getDimension(R.dimen.dp12), (int) resources.getDimension(R.dimen.dp10), (int) resources.getDimension(R.dimen.dp12), (int) resources.getDimension(R.dimen.dp10));
                            expandableHeightListView.setVisibility(0);
                            expandableHeightListView.setLayoutManager(new LinearLayoutManager(ProductsPaymentsView.this.mContext));
                            expandableHeightListView.setAdapter(new ProductListAdapter(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts(), R.layout.product_list_item_view));
                        } else if (ProductsPaymentsView.this.lPageHeaderConfig == null || TextUtils.isEmpty(ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign()) || !ProductsPaymentsView.this.lPageHeaderConfig.getScreenDesign().equals("4")) {
                            ProductsPaymentsView.this.showRecommendedProductPlan(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts(), ProductsPaymentsView.this.lPageHeaderConfig, false);
                        } else {
                            ProductsPaymentsView.this.mIsFocusEnable = ProductsPaymentsView.this.lPageHeaderConfig.isFocusEnable();
                            ProductsPaymentsView.this.showCarouselForPlan(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts(), R.layout.product_carousel_item, false);
                        }
                        final ProductItem productItem2 = (ProductItem) ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts().get(0);
                        ProductsPaymentsView.this.getProductlist(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts());
                        if (!(productItem2 == null || TextUtils.isEmpty(productItem2.getIs_trial()) || !Constants.az)) {
                            RelativeLayout relativeLayout = (RelativeLayout) ProductsPaymentsView.this.mView.findViewById(R.id.trial_popup);
                            ((CrossFadeImageView) ProductsPaymentsView.this.mView.findViewById(R.id.trial_gift_image)).bindImage(productItem2.getProductArtwork(), ScaleType.CENTER_CROP);
                            relativeLayout.setVisibility(0);
                            relativeLayout.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    ProductsPaymentsView.this.handleProductItemClick(productItem2, 0);
                                    u.a().a("Subscription_Payments", ProductsPaymentsView.this.mProductList.toString(), productItem2.getDesc());
                                }
                            });
                        }
                        ProductsPaymentsView.this.handleDeepLink(ProductsPaymentsView.this.paymentProductModel.getPurchase().getProducts());
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ProductsPaymentsView.this.mProductLoadProgressBar.setVisibility(8);
                ProductsPaymentsView.this.mProductRetryLayout.setVisibility(0);
            }
        }, this.mUrlManager);
    }

    private void showAbondonCard() {
        new FailedPaymentCardView(this.mContext, this.mFragment).getPopulateView((LinearLayout) this.mView.findViewById(R.id.failedLayout), this.lPageHeaderConfig);
    }

    private void setPagerForHeaderCarousel(PageHeaderConfig pageHeaderConfig) {
        this.mCarouselHeader = (ViewPager) this.mView.findViewById(R.id.pager_introduction);
        LinearLayout linearLayout = (LinearLayout) this.mView.findViewById(R.id.viewPagerCountDots);
        Context context = this.mContext;
        ArrayList arrayList = (pageHeaderConfig == null || pageHeaderConfig.getCarouselImgUrl() == null) ? new ArrayList() : pageHeaderConfig.getCarouselImgUrl();
        ArrayList arrayList2 = arrayList;
        boolean z = pageHeaderConfig != null && pageHeaderConfig.isCarousel();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context, arrayList2, z, pageHeaderConfig.getProductList());
        this.mCarouselHeader.setAdapter(viewPagerAdapter);
        this.mCarouselHeader.setCurrentItem(0);
        ScrollView scrollView = (ScrollView) this.mView.findViewById(R.id.scrollContainer);
        this.mView.findViewById(R.id.fake_layout).setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return ProductsPaymentsView.this.mCarouselHeader.dispatchTouchEvent(motionEvent);
            }
        });
        if (pageHeaderConfig == null || pageHeaderConfig.getCarouselImgUrl() == null || pageHeaderConfig.getCarouselImgUrl().size() <= 0 || !pageHeaderConfig.isCarousel()) {
            linearLayout.setVisibility(8);
            return;
        }
        this.mCarouselHeader.postDelayed(new Runnable() {
            public void run() {
                ProductsPaymentsView.this.autoScroll(ProductsPaymentsView.this.mCarouselHeader);
            }
        }, 4000);
        this.dotsCount = viewPagerAdapter.getCount();
        this.dots = new ImageView[this.dotsCount];
        for (int i = 0; i < this.dotsCount; i++) {
            this.dots[i] = new ImageView(this.mContext);
            this.dots[i].setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Util.b(8), Util.b(8));
            layoutParams.setMargins(Util.b(11), 0, Util.b(11), 0);
            linearLayout.addView(this.dots[i], layoutParams);
        }
        if (this.dots.length > 0 && this.dots[0] != null) {
            this.dots[0].setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.selecteditem_dot));
        }
        this.mCarouselHeader.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                for (int i2 = 0; i2 < ProductsPaymentsView.this.dotsCount; i2++) {
                    ProductsPaymentsView.this.dots[i2].setImageDrawable(ContextCompat.getDrawable(ProductsPaymentsView.this.mContext, R.drawable.nonselecteditem_dot));
                }
                ProductsPaymentsView.this.dots[i].setImageDrawable(ContextCompat.getDrawable(ProductsPaymentsView.this.mContext, R.drawable.selecteditem_dot));
            }

            public void onPageScrollStateChanged(int i) {
                if (i == 1) {
                    ProductsPaymentsView.this.m_SCROLL_STATE_DRAGGING = true;
                }
            }
        });
    }

    private View showRecommendedProductPlan(final ArrayList<ProductItem> arrayList, PageHeaderConfig pageHeaderConfig, boolean z) {
        View inflate;
        ProductItem productItem = (ProductItem) arrayList.get(0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ProductItem productItem2 = (ProductItem) it.next();
            if (productItem2.isRecommended()) {
                productItem = productItem2;
            }
        }
        if (z) {
            inflate = this.mInflater.inflate(R.layout.product_recommend_plan, null);
        } else {
            inflate = this.mView.findViewById(R.id.payment_recommended_product_layout);
        }
        this.mView.findViewById(R.id.gaana_plus_text).setVisibility(4);
        TextView textView = (TextView) inflate.findViewById(R.id.product_recommended_first_text);
        textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        TextView textView2 = (TextView) inflate.findViewById(R.id.product_recommended_second_text);
        textView2.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Regular.ttf"));
        TextView textView3 = (TextView) inflate.findViewById(R.id.product_pack_popular);
        textView3.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        ((TextView) inflate.findViewById(R.id.product_recommended_other_plans)).setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (!TextUtils.isEmpty(productItem.getRecommended_cta())) {
            TextView textView4 = (TextView) inflate.findViewById(R.id.recommendedBuyNowButton);
            textView4.setTypeface(null, 1);
            textView4.setText(productItem.getRecommended_cta());
            textView4.setVisibility(0);
        }
        if (!TextUtils.isEmpty(productItem.getTnc_text())) {
            TextView textView5 = (TextView) inflate.findViewById(R.id.recommendedTnCText);
            textView5.setVisibility(0);
            textView5.setText(productItem.getTnc_text());
            textView5.setPaintFlags(textView5.getPaintFlags() | 8);
            textView5.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(productItem.getTnc_url())) {
                        Intent intent = new Intent(ProductsPaymentsView.this.mContext, WebViewActivity.class);
                        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                        intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                        ProductsPaymentsView.this.mContext.startActivity(intent);
                    }
                }
            });
        }
        if (!TextUtils.isEmpty(productItem.getRecommendedText())) {
            textView2.setText(productItem.getRecommendedText());
        }
        if (!(pageHeaderConfig == null || TextUtils.isEmpty(pageHeaderConfig.getPageText()))) {
            textView.setText(pageHeaderConfig.getPageText());
        }
        if (productItem.isRecommended()) {
            textView3.setText(this.mContext.getResources().getString(R.string.recommended));
        }
        inflate.setVisibility(0);
        bindDataToView(productItem, inflate, -1);
        if (z) {
            final View showCarouselForPlan = showCarouselForPlan(arrayList, R.layout.product_carousel_item, true);
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(showCarouselForPlan, 0);
            frameLayout.addView(inflate, 1);
            showCarouselForPlan.setVisibility(8);
            inflate.findViewById(R.id.product_recommended_other_plans).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductsPaymentsView.this.mView.findViewById(R.id.gaana_plus_text).setVisibility(0);
                    inflate.setVisibility(8);
                    showCarouselForPlan.setVisibility(0);
                }
            });
            return frameLayout;
        }
        inflate.findViewById(R.id.product_recommended_other_plans).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProductsPaymentsView.this.mView.findViewById(R.id.gaana_plus_text).setVisibility(0);
                inflate.setVisibility(8);
                ProductsPaymentsView.this.showCarouselForPlan(arrayList, R.layout.product_carousel_item, false);
            }
        });
        return inflate;
    }

    private void handleDeepLink(ArrayList<ProductItem> arrayList) {
        if (!TextUtils.isEmpty(this.itemId) && TextUtils.isEmpty(this.productId)) {
            int i = -1;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ProductItem productItem = (ProductItem) it.next();
                i++;
                if (productItem.getItem_id().equals(this.itemId)) {
                    handleProductItemClick(productItem, i);
                    break;
                }
            }
        }
        this.itemId = null;
        this.productId = null;
    }

    public void setOnScrollListener(final ScrollView scrollView) {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new OnScrollChangedListener() {
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                float dimension = ProductsPaymentsView.this.getResources().getDimension(R.dimen.purchase_product_itme_image_height_container);
                com.collapsible_header.i.g(ProductsPaymentsView.this.mOverlay, f.a((float) (-scrollY), (float) (ProductsPaymentsView.this.getActionBarSize() - ProductsPaymentsView.this.mOverlay.getHeight()), 0.0f));
                float a = f.a(((float) scrollY) / dimension, 0.0f, 1.0f);
                com.collapsible_header.i.a(ProductsPaymentsView.this.mOverlay, a);
                com.collapsible_header.i.a(ProductsPaymentsView.this.mGenericBackActionBar.getTitleTextView(), a);
            }
        });
    }

    private void getProductlist(ArrayList<ProductItem> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            ProductItem productItem = (ProductItem) arrayList.get(i);
            if (!(productItem == null || productItem.getDesc() == null)) {
                this.mProductList.add(productItem.getDesc());
            }
        }
    }

    public void handleProductItemClick(final ProductItem productItem, int i) {
        if (productItem == null || productItem.getPlanType() == null || !productItem.getPlanType().equalsIgnoreCase("1")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Duration: ");
            stringBuilder.append(productItem.getDuration_days());
            an.a().e("click", "ac", "", "PYMT_PLAN", stringBuilder.toString(), "PRODUCT", "", "");
            if (Constants.aa) {
                u.a().a("Skip Count", "Subscription screen", "Plan Selected");
            }
            if (!(productItem == null || TextUtils.isEmpty(productItem.getAction()))) {
                ag.a(this.mContext).a(this.paymentProductModel);
                if (!(Util.s() == null || TextUtils.isEmpty(productItem.getDesc()))) {
                    u.a().a("Products", productItem.getDesc(), Util.s());
                }
                if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
                    u.a().a(productItem, productItem.getItem_id());
                    u.a().a(productItem, productItem.getDesc(), productItem.getItem_id(), i);
                    ag.a(this.mContext).a(this.mContext, productItem, new a() {
                        public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                            ag.a(ProductsPaymentsView.this.mContext).a("", "", "success");
                            ((BaseActivity) ProductsPaymentsView.this.mContext).updateUserStatus(new au() {
                                public void onUserStatusUpdated() {
                                    ((BaseActivity) ProductsPaymentsView.this.mContext).hideProgressDialog();
                                    ap.a().a(ProductsPaymentsView.this.mContext);
                                    Util.aa();
                                    aj.a().a(ProductsPaymentsView.this.mContext, ProductsPaymentsView.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                                    if (Util.v(ProductsPaymentsView.this.mContext)) {
                                        Intent intent = new Intent(ProductsPaymentsView.this.mContext, GaanaActivity.class);
                                        intent.setFlags(71303168);
                                        ProductsPaymentsView.this.mContext.startActivity(intent);
                                    }
                                }
                            });
                            if (Util.s() != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                                String p_payment_mode = productItem.getP_payment_mode();
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Success; ");
                                stringBuilder.append(Util.s());
                                u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                            }
                        }

                        public void onFailure(String str, String str2) {
                            ag.a(ProductsPaymentsView.this.mContext).a(str, "", str2);
                            if (str == null || !str.equalsIgnoreCase("TRIAL_NOT_APPLICABLE_RELOAD")) {
                                aj.a().a(ProductsPaymentsView.this.mContext, str);
                            } else {
                                o.a().c("https://api.gaana.com/gaanaplusservice.php?type=duration_listing");
                                ProductsPaymentsView.this.initUI(ProductsPaymentsView.this.mView);
                            }
                            if (Util.s() != null && productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
                                String p_payment_mode = productItem.getP_payment_mode();
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Failure; ");
                                stringBuilder.append(Util.s());
                                u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                            }
                        }
                    }, productItem.getItem_id(), productItem.getDesc());
                } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
                    BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
                    paymentDetailFragment.a(productItem);
                    paymentDetailFragment.a(this.couponCode);
                    paymentDetailFragment.a(this.lPageHeaderConfig);
                    ((GaanaActivity) this.mContext).displayFragment(paymentDetailFragment);
                } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(productItem.getAction()) && !TextUtils.isEmpty(productItem.getWeb_url())) {
                    Intent intent = new Intent(this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", productItem.getWeb_url());
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                    this.mContext.startActivity(intent);
                } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(productItem.getAction())) {
                    u.a().a(productItem, productItem.getItem_id());
                    u.a().a(productItem, productItem.getDesc(), productItem.getItem_id(), i);
                    ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
                } else {
                    com.services.c.a(this.mContext).a(this.mContext, productItem.getAction(), GaanaApplication.getInstance());
                }
            }
            return;
        }
        ag a = ag.a(this.mContext);
        a.a(productItem);
        a.a(this.lPageHeaderConfig);
        a.a(i);
        Util.j(this.mContext, productItem.getP_id());
    }

    public void setProductAndItemId(String str, String str2) {
        this.productId = str2;
        this.itemId = str;
    }

    public void setCouponCode(String str) {
        this.couponCode = str;
    }

    private View showCarouselForPlan(ArrayList<ProductItem> arrayList, int i, boolean z) {
        DiscreteScrollView discreteScrollView;
        int i2 = arrayList.size() > 0 && !TextUtils.isEmpty(((ProductItem) arrayList.get(0)).getIs_trial());
        if (z) {
            discreteScrollView = new DiscreteScrollView(this.mContext);
            discreteScrollView.setLayoutParams(new LayoutParams(-1, -2));
        } else {
            discreteScrollView = (DiscreteScrollView) this.mView.findViewById(R.id.product_plan_pager);
        }
        GaanaApplication gaanaApplication = this.mAppState;
        if (!TextUtils.isEmpty(GaanaApplication.getLanguage(this.mContext))) {
            gaanaApplication = this.mAppState;
            if (!GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("english")) {
                discreteScrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (this.mContext.getResources().getDimension(R.dimen.carousel_plan_pager_height) + this.mContext.getResources().getDimension(R.dimen.dp10))));
            }
        }
        discreteScrollView.setAdapter(new ProductCarouselAdapter(arrayList, i, i2));
        discreteScrollView.setItemTransformer(new ScaleTransformer());
        if (this.mIsFocusEnable && arrayList != null) {
            i = i2 != 0 ? arrayList.size() - 1 : arrayList.size();
            while (i2 < arrayList.size()) {
                if (!((ProductItem) arrayList.get(i2)).isFocus()) {
                    i2++;
                } else if (i2 < i) {
                    discreteScrollView.scrollToPosition(i2);
                }
            }
        }
        if (z) {
            return discreteScrollView;
        }
        this.mView.findViewById(R.id.payment_carousel_view).setVisibility(0);
        return discreteScrollView;
    }

    private void bindDataToView(final ProductItem productItem, StudentCarouselHolder studentCarouselHolder, final int i, Object obj) {
        if (productItem != null) {
            TextView textView = studentCarouselHolder.studentPackText;
            TextView textView2 = studentCarouselHolder.rateText;
            TextView textView3 = studentCarouselHolder.monthText;
            int parseInt = Integer.parseInt(productItem.getDuration_days()) / 30;
            String str = "";
            textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            textView2.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            textView3.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
            if (productItem.getP_cost_curr() != null) {
                str = productItem.getP_cost_curr();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("@ ");
            stringBuilder.append(str);
            stringBuilder.append(" ");
            stringBuilder.append(productItem.getP_cost());
            textView2.setText(stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(parseInt);
            stringBuilder2.append(" Month");
            textView3.setText(stringBuilder2.toString());
            if (productItem.getDesc() != null) {
                String[] split = productItem.getDesc().split(" ");
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(split[0]);
                stringBuilder3.append("\n");
                stringBuilder3.append(split[1]);
                textView.setText(stringBuilder3.toString());
            }
            studentCarouselHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ag a = ag.a(ProductsPaymentsView.this.mContext);
                    a.a(productItem);
                    a.a(ProductsPaymentsView.this.lPageHeaderConfig);
                    a.a(i);
                    Util.j(ProductsPaymentsView.this.mContext, productItem.getP_id());
                }
            });
        }
    }

    private void bindDataToView(ProductItem productItem, View view, int i) {
        final ProductItem productItem2 = productItem;
        View view2 = view;
        final int i2 = i;
        if (productItem2 != null) {
            TextView textView;
            if (!this.mItemIdStrings.contains(productItem.getItem_id())) {
                this.mItemIdStrings.add(productItem.getItem_id());
                if (i2 != -1) {
                    u.a().a(productItem2, i2);
                }
            }
            if (!TextUtils.isEmpty(productItem.getP_cost_curr())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(productItem.getP_cost_curr());
                stringBuilder.append(" ");
                this.curr_symbol = stringBuilder.toString();
            }
            final TextView textView2 = (TextView) view2.findViewById(R.id.product_recommended_first_text);
            TextView textView3 = (TextView) view2.findViewById(R.id.product_recommended_second_text);
            final TextView textView4 = (TextView) view2.findViewById(R.id.product_pack_cost_digit);
            final TextView textView5 = (TextView) view2.findViewById(R.id.product_pack_cost_month);
            final TextView textView6 = (TextView) view2.findViewById(R.id.product_cost_final_text);
            final TextView textView7 = (TextView) view2.findViewById(R.id.product_cost_org_text);
            final TextView textView8 = (TextView) view2.findViewById(R.id.offer_text);
            final TextView textView9 = (TextView) view2.findViewById(R.id.offer_text_off);
            final TextView textView10 = (TextView) view2.findViewById(R.id.product_you_save_text);
            final TextView textView11 = (TextView) view2.findViewById(R.id.product_pack_popular);
            if (productItem.getPlanType().equalsIgnoreCase("1")) {
                textView4.setVisibility(4);
                textView5.setText(productItem.getDesc());
                textView5.setVisibility(0);
                textView = textView3;
            } else if (TextUtils.isEmpty(productItem.getDesc())) {
                textView = textView3;
                textView4.setVisibility(8);
            } else {
                textView = textView3;
                textView4.setText(productItem.getDesc().substring(0, 1));
                textView4.setVisibility(0);
                textView5.setText(productItem.getDesc().substring(1).trim());
            }
            if (productItem.isRecommended()) {
                textView11.setText(this.mContext.getResources().getString(R.string.recommended));
            } else if (productItem.isPopular() && !TextUtils.isEmpty(productItem.getPopularText())) {
                textView11.setText(productItem.getPopularText());
            }
            if (TextUtils.isEmpty(productItem.getP_orig_cost())) {
                textView7.setVisibility(8);
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.curr_symbol);
                stringBuilder2.append(productItem.getP_orig_cost());
                textView7.setText(stringBuilder2.toString());
                textView7.setVisibility(0);
                textView7.setPaintFlags(textView7.getPaintFlags() | 16);
            }
            view2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductsPaymentsView.this.handleProductItemClick(productItem2, i2);
                    u.a().a("Subscription_Payments", ProductsPaymentsView.this.mProductList.toString(), productItem2.getDesc());
                }
            });
            if (TextUtils.isEmpty(productItem.getP_orig_cost()) || TextUtils.isEmpty(productItem.getP_cost())) {
                textView10.setVisibility(4);
                textView8.setVisibility(8);
                textView9.setVisibility(8);
            } else {
                float floatValue = Float.valueOf(productItem.getP_orig_cost()).floatValue() - Float.valueOf(productItem.getP_cost()).floatValue();
                if (floatValue > 0.0f) {
                    textView10.setVisibility(0);
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(this.mContext.getResources().getString(R.string.you_save));
                    stringBuilder3.append(" ");
                    stringBuilder3.append(this.curr_symbol);
                    stringBuilder3.append(Math.round(floatValue));
                    textView10.setText(stringBuilder3.toString());
                    textView8.setVisibility(0);
                    textView9.setVisibility(0);
                    int round = Math.round((floatValue / Float.valueOf(productItem.getP_orig_cost()).floatValue()) * 100.0f);
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(round);
                    stringBuilder3.append("%");
                    textView8.setText(stringBuilder3.toString());
                }
            }
            if (TextUtils.isEmpty(productItem.getP_cost())) {
                textView6.setVisibility(8);
            } else {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(this.curr_symbol);
                stringBuilder4.append(productItem.getP_cost());
                textView6.setText(stringBuilder4.toString());
                textView6.setVisibility(0);
                String str = "";
                final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
                if (productItem2 != null && !TextUtils.isEmpty(productItem.getP_payment_mode()) && productItem.getP_payment_mode().equalsIgnoreCase("android")) {
                    str = productItem.getP_id();
                } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || productItem2 == null || TextUtils.isEmpty(productItem.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !productItem.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                    str = e.getIntro_config().getIntro_p_id();
                }
                String str2 = str;
                if (!TextUtils.isEmpty(str2)) {
                    String str3 = str2;
                    d dVar = r0;
                    PurchaseGoogleManager a = PurchaseGoogleManager.a(this.mContext, null);
                    textView3 = textView;
                    AnonymousClass15 anonymousClass15 = new d() {
                        public void onGoolgeProductPriceQueryConpleted(c cVar) {
                            if (cVar != null) {
                                String b = cVar.b();
                                String c = cVar.c();
                                if (cVar.a()) {
                                    if (!TextUtils.isEmpty(c)) {
                                        textView6.setText(c);
                                    }
                                    if (e != null) {
                                        textView2.setText(e.getIntro_config().getIntro_title());
                                        CharSequence intro_eligible_msg = e.getIntro_config().getIntro_eligible_msg();
                                        if (!TextUtils.isEmpty(intro_eligible_msg)) {
                                            if (intro_eligible_msg.contains("&&&&")) {
                                                intro_eligible_msg = intro_eligible_msg.replace("&&&&", cVar.b());
                                            }
                                            if (intro_eligible_msg.contains("####")) {
                                                intro_eligible_msg = intro_eligible_msg.replace("####", cVar.c());
                                            }
                                        }
                                        textView3.setText(intro_eligible_msg);
                                        textView10.setVisibility(0);
                                        textView11.setVisibility(0);
                                        textView11.setText(e.getIntro_config().getIntro_title());
                                        intro_eligible_msg = e.getIntro_config().getIntroductory_offer_expire_msg();
                                        if (!TextUtils.isEmpty(intro_eligible_msg) && intro_eligible_msg.contains("&&&&")) {
                                            intro_eligible_msg = intro_eligible_msg.replace("&&&&", cVar.b());
                                        }
                                        if (!TextUtils.isEmpty(intro_eligible_msg) && intro_eligible_msg.contains("####")) {
                                            intro_eligible_msg = intro_eligible_msg.replace("####", cVar.c());
                                        }
                                        textView10.setText(intro_eligible_msg);
                                        b = e.getIntro_config().getIntro_duration();
                                        if (!TextUtils.isEmpty(b)) {
                                            textView4.setText(b.substring(0, 1));
                                            textView4.setVisibility(0);
                                            textView5.setText(b.substring(1).trim());
                                            textView5.setVisibility(0);
                                        }
                                    }
                                    if (!TextUtils.isEmpty(cVar.b())) {
                                        textView7.setText(cVar.b());
                                        textView7.setVisibility(0);
                                        textView7.setPaintFlags(textView7.getPaintFlags() | 16);
                                    }
                                    try {
                                        long d = cVar.d() - cVar.e();
                                        if (d > 0) {
                                            textView8.setVisibility(0);
                                            textView9.setVisibility(0);
                                            int round = Math.round((((float) d) / ((float) cVar.d())) * 100.0f);
                                            TextView textView = textView8;
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append(round);
                                            stringBuilder.append("%");
                                            textView.setText(stringBuilder.toString());
                                            return;
                                        }
                                        return;
                                    } catch (Exception unused) {
                                        return;
                                    }
                                } else if (!TextUtils.isEmpty(b)) {
                                    textView6.setText(b);
                                    return;
                                } else {
                                    return;
                                }
                            }
                            TextView textView2 = textView6;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(ProductsPaymentsView.this.curr_symbol);
                            stringBuilder2.append(productItem2.getP_cost());
                            textView2.setText(stringBuilder2.toString());
                        }
                    };
                    a.a(str3, dVar);
                }
            }
        }
    }

    private void bindDataToView(final ProductItem productItem, ProductCarouselBundleHolder productCarouselBundleHolder, final int i) {
        if (productItem != null) {
            StringBuilder stringBuilder;
            if (!this.mItemIdStrings.contains(productItem.getItem_id())) {
                this.mItemIdStrings.add(productItem.getItem_id());
                if (i != -1) {
                    u.a().a(productItem, i);
                }
            }
            if (!TextUtils.isEmpty(productItem.getP_cost_curr())) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(productItem.getP_cost_curr());
                stringBuilder.append(" ");
                this.curr_symbol = stringBuilder.toString();
            }
            TextView access$3400 = productCarouselBundleHolder.mTxtProductPackTitle;
            TextView access$4000 = productCarouselBundleHolder.mTxtProductPackDesc;
            TextView access$4100 = productCarouselBundleHolder.mTxtKnowMore;
            final TextView access$4200 = productCarouselBundleHolder.mTxtProductCostFinal;
            final TextView access$4300 = productCarouselBundleHolder.mTxtProductOrgText;
            final TextView access$4400 = productCarouselBundleHolder.mTxtProductYouSave;
            TextView access$4500 = productCarouselBundleHolder.tncText;
            if (TextUtils.isEmpty(productItem.getDesc())) {
                access$3400.setVisibility(8);
            } else {
                access$3400.setText(productItem.getHeaderText());
            }
            if (TextUtils.isEmpty(productItem.getDescText())) {
                access$4000.setVisibility(8);
            } else {
                access$4000.setText(Html.fromHtml(productItem.getDescText()));
            }
            if (TextUtils.isEmpty(productItem.getMoreText())) {
                access$4100.setText(getResources().getString(R.string.know_more));
            } else {
                access$4100.setText(productItem.getMoreText());
                access$4100.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        new AppUpdaterView(ProductsPaymentsView.this.mContext).showDialogForTermsandConditions(productItem.getMoreDesc());
                    }
                });
            }
            if (TextUtils.isEmpty(productItem.getP_orig_cost())) {
                access$4300.setVisibility(8);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.curr_symbol);
                stringBuilder.append(productItem.getP_orig_cost());
                access$4300.setText(stringBuilder.toString());
                access$4300.setVisibility(0);
                access$4300.setPaintFlags(access$4300.getPaintFlags() | 16);
            }
            if (!TextUtils.isEmpty(productItem.getTnc_text())) {
                access$4500.setText(productItem.getTnc_text());
                access$4500.setPaintFlags(access$4500.getPaintFlags() | 8);
                access$4500.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(productItem.getTncHtml())) {
                            new AppUpdaterView(ProductsPaymentsView.this.mContext).showDialogForTermsandConditions(productItem.getTncHtml());
                        } else if (!TextUtils.isEmpty(productItem.getTnc_url())) {
                            Intent intent = new Intent(ProductsPaymentsView.this.mContext, WebViewActivity.class);
                            intent.putExtra("EXTRA_WEBVIEW_URL", productItem.getTnc_url());
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
                            ProductsPaymentsView.this.mContext.startActivity(intent);
                        }
                    }
                });
            }
            productCarouselBundleHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductsPaymentsView.this.handleProductItemClick(productItem, i);
                    u.a().a("Subscription_Payments", ProductsPaymentsView.this.mProductList.toString(), productItem.getDesc());
                }
            });
            if (TextUtils.isEmpty(productItem.getP_orig_cost()) || TextUtils.isEmpty(productItem.getP_cost())) {
                access$4400.setVisibility(4);
            } else {
                float floatValue = Float.valueOf(productItem.getP_orig_cost()).floatValue() - Float.valueOf(productItem.getP_cost()).floatValue();
                if (floatValue > 0.0f) {
                    access$4400.setVisibility(0);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.mContext.getResources().getString(R.string.you_save));
                    stringBuilder2.append(" ");
                    stringBuilder2.append(this.curr_symbol);
                    stringBuilder2.append(Math.round(floatValue));
                    access$4400.setText(stringBuilder2.toString());
                }
            }
            if (TextUtils.isEmpty(productItem.getP_cost())) {
                access$4200.setVisibility(8);
            } else {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(this.curr_symbol);
                stringBuilder3.append(productItem.getP_cost());
                access$4200.setText(stringBuilder3.toString());
                access$4200.setVisibility(0);
                String str = "";
                final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
                if (productItem != null && !TextUtils.isEmpty(productItem.getP_payment_mode()) && productItem.getP_payment_mode().equalsIgnoreCase("android")) {
                    str = productItem.getP_id();
                } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || productItem == null || TextUtils.isEmpty(productItem.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !productItem.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                    str = e.getIntro_config().getIntro_p_id();
                }
                if (!TextUtils.isEmpty(str)) {
                    final ProductItem productItem2 = productItem;
                    PurchaseGoogleManager.a(this.mContext, null).a(str, new d() {
                        public void onGoolgeProductPriceQueryConpleted(c cVar) {
                            CharSequence charSequence = "";
                            if (cVar != null) {
                                charSequence = cVar.a() ? cVar.c() : cVar.b();
                            }
                            if (TextUtils.isEmpty(charSequence)) {
                                TextView textView = access$4200;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(ProductsPaymentsView.this.curr_symbol);
                                stringBuilder.append(productItem2.getP_cost());
                                textView.setText(stringBuilder.toString());
                                return;
                            }
                            access$4200.setText(charSequence);
                            if (cVar.a() && e != null) {
                                access$4400.setVisibility(0);
                                charSequence = e.getIntro_config().getIntroductory_offer_expire_msg();
                                if (!TextUtils.isEmpty(charSequence) && charSequence.contains("&&&&")) {
                                    charSequence = charSequence.replace("&&&&", cVar.b());
                                }
                                if (!TextUtils.isEmpty(charSequence) && charSequence.contains("####")) {
                                    charSequence = charSequence.replace("####", cVar.c());
                                }
                                access$4400.setText(charSequence);
                                if (!TextUtils.isEmpty(cVar.b())) {
                                    access$4300.setText(cVar.b());
                                    access$4300.setVisibility(0);
                                    access$4300.setPaintFlags(access$4300.getPaintFlags() | 16);
                                }
                                long d = cVar.d() - cVar.e();
                                if (d > 0) {
                                    Math.round((((float) d) / ((float) cVar.d())) * 100.0f);
                                }
                                e.getIntro_config().getIntro_duration();
                            }
                        }
                    });
                }
            }
        }
    }

    private void bindDataToView(ProductItem productItem, ProductCarouselHolder productCarouselHolder, int i) {
        final ProductItem productItem2 = productItem;
        final int i2 = i;
        if (productItem2 != null) {
            StringBuilder stringBuilder;
            if (!this.mItemIdStrings.contains(productItem.getItem_id())) {
                this.mItemIdStrings.add(productItem.getItem_id());
                if (i2 != -1) {
                    u.a().a(productItem2, i2);
                }
            }
            if (!TextUtils.isEmpty(productItem.getP_cost_curr())) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(productItem.getP_cost_curr());
                stringBuilder.append(" ");
                this.curr_symbol = stringBuilder.toString();
            }
            final TextView access$4600 = productCarouselHolder.mTxtProductPackCostDigit;
            final TextView access$4700 = productCarouselHolder.mTxtProductCostMonth;
            final TextView access$3600 = productCarouselHolder.mTxtProductCostFinal;
            final TextView access$4800 = productCarouselHolder.mTxtProductOrgText;
            final TextView access$4900 = productCarouselHolder.mTxtOffer;
            final TextView access$5000 = productCarouselHolder.mTxtOfferOff;
            final TextView access$5100 = productCarouselHolder.mTxtProductYouSave;
            final TextView access$3700 = productCarouselHolder.mTxtProductPopular;
            if (TextUtils.isEmpty(productItem.getDesc())) {
                access$4600.setVisibility(8);
            } else {
                access$4600.setText(productItem.getDesc().substring(0, 1));
                access$4600.setVisibility(0);
                access$4700.setText(productItem.getDesc().substring(1).trim());
            }
            if (productItem.isRecommended()) {
                access$3700.setText(this.mContext.getResources().getString(R.string.recommended));
            } else if (productItem.isPopular() && !TextUtils.isEmpty(productItem.getPopularText())) {
                access$3700.setText(productItem.getPopularText());
            }
            if (TextUtils.isEmpty(productItem.getP_orig_cost())) {
                access$4800.setVisibility(8);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.curr_symbol);
                stringBuilder.append(productItem.getP_orig_cost());
                access$4800.setText(stringBuilder.toString());
                access$4800.setVisibility(0);
                access$4800.setPaintFlags(access$4800.getPaintFlags() | 16);
            }
            productCarouselHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProductsPaymentsView.this.handleProductItemClick(productItem2, i2);
                    u.a().a("Subscription_Payments", ProductsPaymentsView.this.mProductList.toString(), productItem2.getDesc());
                }
            });
            if (TextUtils.isEmpty(productItem.getP_orig_cost()) || TextUtils.isEmpty(productItem.getP_cost())) {
                access$5100.setVisibility(4);
                access$4900.setVisibility(8);
                access$5000.setVisibility(8);
            } else {
                float floatValue = Float.valueOf(productItem.getP_orig_cost()).floatValue() - Float.valueOf(productItem.getP_cost()).floatValue();
                if (floatValue > 0.0f) {
                    access$5100.setVisibility(0);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mContext.getResources().getString(R.string.you_save));
                    stringBuilder.append(" ");
                    stringBuilder.append(this.curr_symbol);
                    stringBuilder.append(Math.round(floatValue));
                    access$5100.setText(stringBuilder.toString());
                    access$4900.setVisibility(0);
                    access$5000.setVisibility(0);
                    i2 = Math.round((floatValue / Float.valueOf(productItem.getP_orig_cost()).floatValue()) * 100.0f);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(i2);
                    stringBuilder.append("%");
                    access$4900.setText(stringBuilder.toString());
                }
            }
            if (TextUtils.isEmpty(productItem.getP_cost())) {
                access$3600.setVisibility(8);
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.curr_symbol);
                stringBuilder2.append(productItem.getP_cost());
                access$3600.setText(stringBuilder2.toString());
                access$3600.setVisibility(0);
                String str = "";
                final GoogleIntroductoryPriceConfig e = PurchaseGoogleManager.a(this.mContext).e();
                if (productItem2 != null && !TextUtils.isEmpty(productItem.getP_payment_mode()) && productItem.getP_payment_mode().equalsIgnoreCase("android")) {
                    str = productItem.getP_id();
                } else if (!(e == null || e.getIntro_config() == null || TextUtils.isEmpty(e.getIntro_config().getIntro_p_id()) || productItem2 == null || TextUtils.isEmpty(productItem.getItem_id()) || TextUtils.isEmpty(e.getIntro_config().getIntro_plan_id()) || !productItem.getItem_id().equalsIgnoreCase(e.getIntro_config().getIntro_plan_id()))) {
                    str = e.getIntro_config().getIntro_p_id();
                }
                String str2 = str;
                if (!TextUtils.isEmpty(str2)) {
                    PurchaseGoogleManager.a(this.mContext, null).a(str2, new d() {
                        public void onGoolgeProductPriceQueryConpleted(c cVar) {
                            CharSequence charSequence = "";
                            if (cVar != null) {
                                charSequence = cVar.a() ? cVar.c() : cVar.b();
                            }
                            if (TextUtils.isEmpty(charSequence)) {
                                TextView textView = access$3600;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(ProductsPaymentsView.this.curr_symbol);
                                stringBuilder.append(productItem2.getP_cost());
                                textView.setText(stringBuilder.toString());
                                return;
                            }
                            access$3600.setText(charSequence);
                            if (cVar.a() && e != null) {
                                access$5100.setVisibility(0);
                                access$3700.setVisibility(0);
                                access$3700.setText(e.getIntro_config().getIntro_title());
                                charSequence = e.getIntro_config().getIntroductory_offer_expire_msg();
                                if (!TextUtils.isEmpty(charSequence) && charSequence.contains("&&&&")) {
                                    charSequence = charSequence.replace("&&&&", cVar.b());
                                }
                                if (!TextUtils.isEmpty(charSequence) && charSequence.contains("####")) {
                                    charSequence = charSequence.replace("####", cVar.c());
                                }
                                access$5100.setText(charSequence);
                                if (!TextUtils.isEmpty(cVar.b())) {
                                    access$4800.setText(cVar.b());
                                    access$4800.setVisibility(0);
                                    access$4800.setPaintFlags(access$4800.getPaintFlags() | 16);
                                }
                                long d = cVar.d() - cVar.e();
                                if (d > 0) {
                                    access$4900.setVisibility(0);
                                    access$5000.setVisibility(0);
                                    int round = Math.round((((float) d) / ((float) cVar.d())) * 100.0f);
                                    TextView textView2 = access$4900;
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(round);
                                    stringBuilder2.append("%");
                                    textView2.setText(stringBuilder2.toString());
                                }
                                String intro_duration = e.getIntro_config().getIntro_duration();
                                if (!TextUtils.isEmpty(intro_duration)) {
                                    access$4600.setText(intro_duration.substring(0, 1));
                                    access$4600.setVisibility(0);
                                    access$4700.setText(intro_duration.substring(1).trim());
                                    access$4700.setVisibility(0);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int getActionBarSize() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }
}
