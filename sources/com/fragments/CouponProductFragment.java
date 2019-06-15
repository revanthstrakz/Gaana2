package com.fragments;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.actionbar.GenericBackActionBar;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.DiscreteScrollView;
import com.gaana.view.DiscreteScrollView.OnItemChangedListener;
import com.gaana.view.transform.ScaleTransformer;
import com.i.i;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.o;
import com.managers.u;
import com.models.CouponProducts;
import com.models.CouponProducts.PaymentGateway;
import com.models.CouponProducts.ProductGateway;
import com.services.l.af;
import com.services.l.au;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class CouponProductFragment extends BaseGaanaFragment implements a {
    protected LayoutInflater a = null;
    PaymentGateway b;
    private String c = "Choose Payment Method";
    private DiscreteScrollView d;
    private a e;
    private Spinner f;
    private View g;
    private TextView h;
    private int i = 0;
    private String j;
    private boolean k = false;

    protected class a extends Adapter<ViewHolder> {
        CouponProducts a;
        private int c;

        public int getItemViewType(int i) {
            return 0;
        }

        public a(CouponProducts couponProducts, int i) {
            this.a = couponProducts;
            this.c = i;
        }

        public CouponProducts a() {
            return this.a;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(LayoutInflater.from(viewGroup.getContext()).inflate(this.c, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            final ProductGateway productGateway = (ProductGateway) this.a.a().get(i);
            final b bVar = (b) viewHolder;
            bVar.d.setText(((PaymentGateway) productGateway.a().get(0)).c());
            bVar.c.setText(productGateway.d());
            CharSequence charSequence = productGateway.b().split(" ")[0];
            CharSequence charSequence2 = productGateway.b().split(" ")[1];
            bVar.a.setText(charSequence);
            bVar.b.setText(charSequence2);
            if (productGateway.g()) {
                CouponProductFragment.this.i = i;
                bVar.e.setVisibility(0);
                CouponProductFragment.this.g = bVar.e;
                CouponProductFragment.this.h = bVar.d;
                CouponProductFragment.this.b(this.a);
                CouponProductFragment.this.k = true;
                ((TextView) CouponProductFragment.this.containerView.findViewById(R.id.choose_plan_message)).setText(productGateway.f());
            }
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CouponProductFragment.this.i = i;
                    CouponProductFragment.this.h = bVar.d;
                    bVar.e.setVisibility(0);
                    if (!(CouponProductFragment.this.g == null || CouponProductFragment.this.g == bVar.e)) {
                        CouponProductFragment.this.g.setVisibility(8);
                    }
                    CouponProductFragment.this.g = bVar.e;
                    CouponProductFragment.this.a(productGateway.a());
                }
            });
        }

        public int getItemCount() {
            return this.a.a().size();
        }
    }

    class b extends ViewHolder {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        CheckBox e;

        public b(View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.product_pack_cost_digit);
            this.b = (TextView) view.findViewById(R.id.product_pack_cost_month);
            this.c = (TextView) view.findViewById(R.id.product_you_save_text);
            this.d = (TextView) view.findViewById(R.id.product_cost_final_text);
            this.e = (CheckBox) view.findViewById(R.id.selected_product_checkbox);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.containerView == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.containerView = setContentView(R.layout.coupon_products_layout, viewGroup);
            a();
            setActionBar(this.containerView, new GenericBackActionBar(this.mContext, this.c), false);
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        this.j = getArguments().getString("COUPONCODE");
        this.a = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        setGAScreenName("Payment Detail", "PaymentDetailScreen");
        MoEngage.getInstance().reportSectionViewedEvent("Payment");
        ((GaanaActivity) this.mContext).title = this.c;
        b();
        return this.containerView;
    }

    private void a() {
        this.f = (Spinner) this.containerView.findViewById(R.id.payment_methods);
        this.containerView.findViewById(R.id.proceed_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CouponProductFragment.this.e != null) {
                    CouponProducts a = CouponProductFragment.this.e.a();
                    ProductGateway productGateway = (ProductGateway) a.a().get(CouponProductFragment.this.i);
                    if (a != null) {
                        ProductItem productItem = new ProductItem();
                        productItem.setCouponCode(CouponProductFragment.this.j);
                        productItem.setAction(productGateway.c());
                        productItem.setDescription(productGateway.b());
                        productItem.setItemId(productGateway.e());
                        if (CouponProductFragment.this.b != null) {
                            productItem.setProductId(CouponProductFragment.this.b.e());
                            productItem.setPaymentMode(CouponProductFragment.this.b.b());
                            productItem.setP_Cost(CouponProductFragment.this.b.c());
                            productItem.setP_cost_curr(CouponProductFragment.this.b.g());
                            productItem.setSaved_card_msg(CouponProductFragment.this.b.h());
                            productItem.setIs_si_msg(CouponProductFragment.this.b.f());
                        }
                        CouponProductFragment.this.a(productItem, 0);
                    }
                }
            }
        });
    }

    private void b() {
        String str = "https://api.gaana.com/app_pmt_config.php?type=get_cpmnt_list";
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || str.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&p_code=");
            stringBuilder.append(this.j);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str = stringBuilder.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(str);
        uRLManager.a(BusinessObjectType.CouponProducts);
        if (Util.j(this.mContext)) {
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    CouponProducts couponProducts = (CouponProducts) obj;
                    CouponProductFragment.this.c(couponProducts);
                    CouponProductFragment.this.d.getLayoutManager().scrollToPosition(CouponProductFragment.this.a(couponProducts));
                    ((BaseActivity) CouponProductFragment.this.mContext).hideProgressDialog();
                }
            }, uRLManager);
        }
    }

    private int a(CouponProducts couponProducts) {
        Iterator it = couponProducts.a().iterator();
        int i = -1;
        while (it.hasNext()) {
            i++;
            if (((ProductGateway) it.next()).g()) {
                return i;
            }
        }
        return 0;
    }

    private void b(CouponProducts couponProducts) {
        ProductGateway productGateway = (ProductGateway) couponProducts.a().get(this.i);
        a(productGateway.a());
        this.b = (PaymentGateway) productGateway.a().get(0);
        ((TextView) this.containerView.findViewById(R.id.proceed_message)).setText(this.b.a());
    }

    private void c(CouponProducts couponProducts) {
        this.d = (DiscreteScrollView) this.containerView.findViewById(R.id.product_plan_pager);
        this.e = new a(couponProducts, R.layout.coupon_product_item);
        this.d.setAdapter(this.e);
        this.d.setItemTransformer(new ScaleTransformer());
        this.containerView.findViewById(R.id.payment_carousel_view).setVisibility(0);
        this.d.addOnItemChangedListener(new OnItemChangedListener<ViewHolder>() {
            public void onCurrentItemChanged(@Nullable ViewHolder viewHolder, int i) {
            }
        });
    }

    public void a(final ProductItem productItem, int i) {
        if (Constants.aa) {
            u.a().a("Skip Count", "Subscription screen", "Plan Selected");
        }
        if (productItem != null && !TextUtils.isEmpty(productItem.getAction())) {
            if (!(Util.s() == null || TextUtils.isEmpty(productItem.getDesc()))) {
                u.a().a("Products", productItem.getDesc(), Util.s());
            }
            ag.a(this.mContext).a(this.mContext, productItem, new com.managers.ag.a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    ag.a(CouponProductFragment.this.mContext).a("", "", "success");
                    ((BaseActivity) CouponProductFragment.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) CouponProductFragment.this.mContext).hideProgressDialog();
                            ap.a().a(CouponProductFragment.this.mContext);
                            Util.aa();
                            aj.a().a(CouponProductFragment.this.mContext, CouponProductFragment.this.getContext().getString(R.string.enjoy_using_gaana_plus));
                            if (Util.v(CouponProductFragment.this.mContext)) {
                                Intent intent = new Intent(CouponProductFragment.this.mContext, GaanaActivity.class);
                                intent.setFlags(71303168);
                                CouponProductFragment.this.mContext.startActivity(intent);
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
                    ag.a(CouponProductFragment.this.mContext).a(str, "", str2);
                    if (str == null || !str.equalsIgnoreCase("TRIAL_NOT_APPLICABLE_RELOAD")) {
                        aj.a().a(CouponProductFragment.this.mContext, str);
                    } else {
                        o.a().c("https://api.gaana.com/gaanaplusservice.php?type=duration_listing");
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
        }
    }

    private void a(final ArrayList<PaymentGateway> arrayList) {
        this.f.setAdapter(new SpinnerAdapter() {
            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return (long) i;
            }

            public int getItemViewType(int i) {
                return 0;
            }

            public int getViewTypeCount() {
                return 1;
            }

            public boolean hasStableIds() {
                return false;
            }

            public boolean isEmpty() {
                return false;
            }

            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                return CouponProductFragment.this.a(((PaymentGateway) arrayList.get(i)).d());
            }

            public int getCount() {
                return arrayList.size();
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                return CouponProductFragment.this.a(((PaymentGateway) arrayList.get(i)).d());
            }
        });
        this.f.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                CouponProductFragment.this.b = (PaymentGateway) arrayList.get(i);
                ((TextView) CouponProductFragment.this.containerView.findViewById(R.id.proceed_message)).setText(CouponProductFragment.this.b.a());
                if (CouponProductFragment.this.h != null) {
                    CouponProductFragment.this.h.setText(CouponProductFragment.this.b.c());
                }
            }
        });
    }

    private ViewGroup a(String str) {
        RelativeLayout relativeLayout = (RelativeLayout) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.bank_code_item, null);
        ((TextView) relativeLayout.findViewById(R.id.bank_code_item)).setText(str);
        return relativeLayout;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }
}
