package com.gaana.view.item;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.CouponApplyModel;
import com.gaana.models.CouponApplyModel.ProductCouponItem;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.BaseItemView;
import com.i.i;
import com.managers.URLManager;
import com.services.l.af;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GaanaPlusApplyCouponView extends BaseItemView {
    private Button apply_button;
    private RelativeLayout apply_coupon_layout;
    private CouponApplyRemovedListener couponApplyRemovedListener;
    private EditText enter_coupon;
    private TextView invalid_coupon_text;
    private int mLayoutResourceId = R.layout.gaana_plus_apply_coupon_view;
    private ProductItem mProduct;
    private TextView message_text;
    private RelativeLayout remove_coupon_layout;
    private TextView remove_coupon_text;
    private boolean showCouponApplyLayout;

    public interface CouponApplyRemovedListener {
        void couponSuccessfullyApplied(ArrayList<ProductCouponItem> arrayList, String str);

        void couponSuccessfullyRemoved();
    }

    public GaanaPlusApplyCouponView(Context context, BaseGaanaFragment baseGaanaFragment, boolean z, CouponApplyRemovedListener couponApplyRemovedListener) {
        super(context, baseGaanaFragment);
        this.showCouponApplyLayout = z;
        this.couponApplyRemovedListener = couponApplyRemovedListener;
    }

    public View getPopulatedView(ViewGroup viewGroup, BusinessObject businessObject) {
        View newView = super.getNewView(this.mLayoutResourceId, viewGroup);
        this.mProduct = (ProductItem) businessObject;
        this.apply_coupon_layout = (RelativeLayout) newView.findViewById(R.id.apply_coupon_layout);
        this.remove_coupon_layout = (RelativeLayout) newView.findViewById(R.id.remove_coupon_layout);
        this.apply_button = (Button) newView.findViewById(R.id.apply_button);
        this.invalid_coupon_text = (TextView) newView.findViewById(R.id.invalid_coupon_text);
        this.message_text = (TextView) newView.findViewById(R.id.message_text);
        this.remove_coupon_text = (TextView) newView.findViewById(R.id.remove_coupon_text);
        if (this.showCouponApplyLayout) {
            this.apply_coupon_layout.setVisibility(0);
            this.remove_coupon_layout.setVisibility(8);
            this.apply_button.setOnClickListener(this);
        } else {
            this.apply_coupon_layout.setVisibility(8);
            this.remove_coupon_layout.setVisibility(0);
            this.remove_coupon_text.setOnClickListener(this);
        }
        this.enter_coupon = (EditText) newView.findViewById(R.id.enter_coupon);
        this.enter_coupon.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                GaanaPlusApplyCouponView.this.invalid_coupon_text.setVisibility(8);
            }
        });
        return newView;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.apply_button) {
            if (id == R.id.remove_coupon_text) {
                ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.coupon_remove));
                removeCoupon();
            }
        } else if (!TextUtils.isEmpty(this.enter_coupon.getText().toString().trim())) {
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.coupon_apply));
            applyCoupon();
        }
    }

    private void applyCoupon() {
        String str = "https://api.gaana.com/gaanaplusservice.php?type=apply_coupon&item_id=<item_id>&coupon_code=<coupon_code>";
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str = stringBuilder.toString();
        }
        str = str.replace("<item_id>", URLEncoder.encode(this.mProduct.getItem_id())).replace("<coupon_code>", URLEncoder.encode(this.enter_coupon.getText().toString()));
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(CouponApplyModel.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                ((BaseActivity) GaanaPlusApplyCouponView.this.mContext).hideProgressDialog();
                CouponApplyModel couponApplyModel = (CouponApplyModel) obj;
                if (couponApplyModel == null || couponApplyModel.getC_success() == null || !couponApplyModel.getC_success().equalsIgnoreCase("1")) {
                    GaanaPlusApplyCouponView.this.invalid_coupon_text.setVisibility(0);
                } else {
                    if (!(GaanaPlusApplyCouponView.this.couponApplyRemovedListener == null || couponApplyModel.getP_list() == null || couponApplyModel.getP_list().size() <= 0)) {
                        GaanaPlusApplyCouponView.this.couponApplyRemovedListener.couponSuccessfullyApplied(couponApplyModel.getP_list(), GaanaPlusApplyCouponView.this.enter_coupon.getText().toString().trim());
                    }
                    GaanaPlusApplyCouponView.this.invalid_coupon_text.setVisibility(8);
                }
                if (couponApplyModel != null && !TextUtils.isEmpty(couponApplyModel.getC_msg())) {
                    GaanaPlusApplyCouponView.this.message_text.setText(couponApplyModel.getC_msg());
                }
            }
        }, uRLManager);
    }

    private void removeCoupon() {
        if (this.couponApplyRemovedListener != null) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            this.couponApplyRemovedListener.couponSuccessfullyRemoved();
        }
    }
}
