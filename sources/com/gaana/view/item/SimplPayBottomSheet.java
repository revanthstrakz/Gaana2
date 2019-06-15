package com.gaana.view.item;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserSubscriptionData;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.services.l.an;
import com.services.l.au;
import com.simpl.android.zeroClickSdk.Simpl;
import com.simpl.android.zeroClickSdk.SimplPaymentDueListener;
import com.utilities.Util;
import com.views.CustomButtonView;
import java.util.Timer;
import java.util.TimerTask;

public class SimplPayBottomSheet extends BottomSheetDialog {
    BottomSheetDialog bottomSheetDialog = this;
    private CustomButtonView btnPayNow;
    private TextView descriptionText;
    SimplPaymentDialog dialog = null;
    Context mContext;
    ProductItem mProductItem;
    private View mView;
    CheckBox si_renewal;

    public SimplPayBottomSheet(Context context, ProductItem productItem) {
        super(context);
        this.mContext = context;
        this.mProductItem = productItem;
        this.mView = LayoutInflater.from(context).inflate(R.layout.simpl_pay_layout, null);
        this.si_renewal = (CheckBox) this.mView.findViewById(R.id.renew_checkbox);
        this.descriptionText = (TextView) this.mView.findViewById(R.id.description);
        this.btnPayNow = (CustomButtonView) this.mView.findViewById(R.id.btn_PayNow);
        setContentView(this.mView);
        initUI();
    }

    public void showPaymentDialog(Context context, String str, String str2) {
        FragmentTransaction beginTransaction = ((Activity) context).getFragmentManager().beginTransaction();
        this.dialog = new SimplPaymentDialog();
        this.dialog.setViewType(str);
        this.dialog.setPaymentMessage(str2);
        beginTransaction.add(this.dialog, null).commitAllowingStateLoss();
    }

    public void hidePaymentDialog() {
        if (this.dialog != null) {
            this.dialog.hide();
        }
    }

    public void dismissPaymentDialog() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                SimplPayBottomSheet.this.hidePaymentDialog();
                cancel();
                timer.cancel();
            }
        }, 0, 3000);
    }

    public void redirectToHomeScreen() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (Util.v(SimplPayBottomSheet.this.mContext)) {
                    Intent intent = new Intent(SimplPayBottomSheet.this.mContext, GaanaActivity.class);
                    intent.setFlags(71303168);
                    SimplPayBottomSheet.this.mContext.startActivity(intent);
                }
                cancel();
                timer.cancel();
            }
        }, 0, 3000);
    }

    public void redirectToSimplUrl(String str) {
        Simpl.getInstance().openRedirectionURL(this.mContext, str).execute(new SimplPaymentDueListener() {
            public void onSuccess(String str) {
                SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "processing", "");
                SimplPayBottomSheet.this.makeSimplPayment();
            }

            public void onError(Throwable th) {
                Toast.makeText(SimplPayBottomSheet.this.mContext, th.getMessage(), 0).show();
            }
        });
    }

    private void initUI() {
        ((TextView) this.mView.findViewById(R.id.title)).setText(this.mProductItem.getP_pay_desc());
        i.a().a((CrossFadeImageView) this.mView.findViewById(R.id.logo), this.mProductItem.getProductArtwork());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>₹");
        stringBuilder.append(this.mProductItem.getP_cost());
        stringBuilder.append("</b>");
        this.descriptionText.setText(Html.fromHtml(this.mContext.getString(R.string.simpl_message).replace("****", GaanaApplication.getInstance().getCurrentUser().getUserProfile().getPhoneNumber()).replace("***", GaanaApplication.getInstance().getCurrentUser().getUserProfile().getMobileCountryPrefix()).replace("**", stringBuilder.toString())));
        int is_si = this.mProductItem.getIs_si();
        String is_si_msg = this.mProductItem.getIs_si_msg();
        if (is_si == 1) {
            this.si_renewal.setEnabled(false);
            this.si_renewal.setChecked(true);
        }
        this.si_renewal.setText(is_si_msg);
        this.btnPayNow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SimplPayBottomSheet.this.bottomSheetDialog.hide();
                if (!Constants.bN) {
                    return;
                }
                if (Constants.bP == 2 || TextUtils.isEmpty(Constants.bM)) {
                    ag.a(SimplPayBottomSheet.this.mContext).a(SimplPayBottomSheet.this.si_renewal.isChecked(), new an() {
                        public void onSimplPaymentUpdate(UserSubscriptionData userSubscriptionData) {
                        }

                        public void onSimplPaymentUpdate() {
                            SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "processing", "");
                            ag.a(SimplPayBottomSheet.this.mContext).a(SimplPayBottomSheet.this.mProductItem, SimplPayBottomSheet.this.si_renewal.isChecked(), new an() {
                                public void onSimplPaymentUpdate() {
                                }

                                public void onSimplPaymentUpdate(final UserSubscriptionData userSubscriptionData) {
                                    SimplPayBottomSheet.this.hidePaymentDialog();
                                    if (userSubscriptionData.getM_code() == 11008) {
                                        ((BaseActivity) SimplPayBottomSheet.this.mContext).updateUserStatus(new au() {
                                            public void onUserStatusUpdated() {
                                                SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "success", userSubscriptionData.getMessage());
                                                ap.a().a(SimplPayBottomSheet.this.mContext);
                                                Util.aa();
                                                aj.a().a(SimplPayBottomSheet.this.mContext, SimplPayBottomSheet.this.mContext.getString(R.string.enjoy_using_gaana_plus));
                                                SimplPayBottomSheet.this.redirectToHomeScreen();
                                            }
                                        });
                                    } else if (userSubscriptionData.getM_code() == 11006) {
                                        SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                                        SimplPayBottomSheet.this.dismissPaymentDialog();
                                    } else if (userSubscriptionData.getM_code() == 11016) {
                                        SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                                        SimplPayBottomSheet.this.dismissPaymentDialog();
                                    } else if (userSubscriptionData.getM_code() == 11015) {
                                        SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                                        SimplPayBottomSheet.this.dismissPaymentDialog();
                                    } else if (userSubscriptionData.getM_code() == 11007) {
                                        SimplPayBottomSheet.this.hidePaymentDialog();
                                        if (!TextUtils.isEmpty(userSubscriptionData.getRedirectionUrl())) {
                                            SimplPayBottomSheet.this.redirectToSimplUrl(userSubscriptionData.getRedirectionUrl());
                                        }
                                    } else {
                                        SimplPayBottomSheet.this.dismissPaymentDialog();
                                    }
                                }
                            });
                        }
                    });
                } else if (Constants.bP == 0) {
                    if (TextUtils.isEmpty(Constants.bR)) {
                        Toast.makeText(SimplPayBottomSheet.this.mContext, Constants.bS, 1);
                    } else {
                        SimplPayBottomSheet.this.redirectToSimplUrl(Constants.bR);
                    }
                } else if (Constants.bP != 1) {
                } else {
                    if (TextUtils.isEmpty(Constants.bR)) {
                        SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "processing", "");
                        SimplPayBottomSheet.this.makeSimplPayment();
                        return;
                    }
                    SimplPayBottomSheet.this.redirectToSimplUrl(Constants.bR);
                }
            }
        });
    }

    private void makeSimplPayment() {
        ag.a(this.mContext).a(this.si_renewal.isChecked(), new an() {
            public void onSimplPaymentUpdate() {
            }

            public void onSimplPaymentUpdate(UserSubscriptionData userSubscriptionData) {
                SimplPayBottomSheet.this.hidePaymentDialog();
                if (userSubscriptionData.getM_code() == 11008) {
                    SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "success", userSubscriptionData.getMessage());
                    SimplPayBottomSheet.this.redirectToHomeScreen();
                } else if (userSubscriptionData.getM_code() == 11006) {
                    SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                    SimplPayBottomSheet.this.dismissPaymentDialog();
                } else if (userSubscriptionData.getM_code() == 11016) {
                    SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                    SimplPayBottomSheet.this.dismissPaymentDialog();
                } else if (userSubscriptionData.getM_code() == 11015) {
                    SimplPayBottomSheet.this.showPaymentDialog(SimplPayBottomSheet.this.mContext, "failed", userSubscriptionData.getMessage());
                    SimplPayBottomSheet.this.dismissPaymentDialog();
                } else if (userSubscriptionData.getM_code() == 11007) {
                    SimplPayBottomSheet.this.hidePaymentDialog();
                    if (!TextUtils.isEmpty(userSubscriptionData.getRedirectionUrl())) {
                        SimplPayBottomSheet.this.redirectToSimplUrl(userSubscriptionData.getRedirectionUrl());
                    }
                } else {
                    SimplPayBottomSheet.this.dismissPaymentDialog();
                }
            }
        }, this.mProductItem);
    }
}
