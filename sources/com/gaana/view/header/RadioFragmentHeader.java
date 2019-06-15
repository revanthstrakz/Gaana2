package com.gaana.view.header;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.RadioMoods;
import com.gaana.view.BaseItemView;
import com.gaana.view.item.PopupWindowView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.services.l.s;
import com.utilities.Util;

public class RadioFragmentHeader extends BaseItemView implements OnClickListener {
    private boolean isFirstCall = true;
    private boolean isViewDestroyed = false;
    private Dialog mDialog = null;
    private a mDynamicView;
    private int mLayoutResourceId = R.layout.view_header_radio_home;
    private String mUniqueId = "";

    public static class RadioHeaderViewHolder extends ViewHolder {
        public RadioHeaderViewHolder(View view) {
            super(view);
        }
    }

    public RadioFragmentHeader(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
    }

    public RadioFragmentHeader(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
        this.mUniqueId = this.mDynamicView.y();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RadioHeaderViewHolder(getNewView(this.mLayoutResourceId, viewGroup));
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        return super.getNewView(this.mLayoutResourceId, viewGroup);
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return getPopulatedView(i, viewHolder.itemView, viewGroup, null);
    }

    public View getPopulatedView(int i, View view, ViewGroup viewGroup, BusinessObject businessObject) {
        if (view == null) {
            view = super.getNewView(this.mLayoutResourceId, viewGroup);
        }
        this.isViewDestroyed = false;
        String string = this.mContext.getString(R.string.msg_onetouch_radio_1);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.mContext, R.style.header_first_line_bold), 0, string.length(), 17);
        spannableStringBuilder.append("\n");
        spannableStringBuilder.append("\n");
        spannableStringBuilder.append(this.mContext.getString(R.string.msg_onetouch_radio_3));
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.mContext, R.style.subheader_second_line), string.length(), spannableStringBuilder.length(), 17);
        ((TextView) view.findViewById(R.id.one_touch_radio_header_container)).setText(spannableStringBuilder, BufferType.SPANNABLE);
        view.findViewById(R.id.one_touch_radio_header_container).setOnClickListener(this);
        return view;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.one_touch_radio_header_container) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
            } else if (Util.j(this.mContext)) {
                ((BaseActivity) this.mContext).sendGAEvent("RadioScreen", "One Touch Radio", "RadioScreen - One Touch Radio");
                an.a().a("click", "en", this.mUniqueId, an.a().a(an.a().a), "", BusinessObjectType.RadioMoods.toString(), "", "");
                this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.ONE_TOUCH.name());
                URLManager uRLManager = new URLManager();
                uRLManager.a("https://api.gaana.com/home/one-touch-moods");
                uRLManager.b(Boolean.valueOf(false));
                uRLManager.a(BusinessObjectType.RadioMoods);
                if (this.isFirstCall) {
                    this.isFirstCall = false;
                    createLoadingView(getContext().getString(R.string.starting_one_touch_radio), this.mContext);
                    i.a().a(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            RadioFragmentHeader.this.dismissDialog();
                            if (!RadioFragmentHeader.this.isViewDestroyed && businessObject != null) {
                                RadioFragmentHeader.this.isFirstCall = true;
                                RadioMoods radioMoods = (RadioMoods) businessObject;
                                if (radioMoods.getArrListItem() != null && radioMoods.getArrListItem().size() > 0) {
                                    PopupWindowView.getInstance(RadioFragmentHeader.this.mContext, RadioFragmentHeader.this.mFragment).contextOneTouchPopup(radioMoods);
                                }
                            }
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            RadioFragmentHeader.this.dismissDialog();
                            RadioFragmentHeader.this.isFirstCall = true;
                            if (!RadioFragmentHeader.this.isViewDestroyed) {
                                aj.a().a(RadioFragmentHeader.this.mContext, RadioFragmentHeader.this.mContext.getResources().getString(R.string.err_retry));
                            }
                        }
                    }, uRLManager);
                }
            } else {
                ap.a().f(this.mContext);
            }
        }
    }

    private void dismissDialog() {
        try {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
        } catch (Exception unused) {
        }
    }

    public void setFirstCall(boolean z) {
        this.isViewDestroyed = z;
        this.isFirstCall = z;
    }

    private void createLoadingView(String str, Context context) {
        if (context != null) {
            try {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setLayoutParams(new LayoutParams(-2, -2));
                LayoutInflater.from(context).inflate(R.layout.view_loading_radio, linearLayout, true);
                if (str != null) {
                    ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(str);
                } else {
                    ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(this.mContext.getResources().getString(R.string.loading));
                }
                if (this.mDialog != null && this.mDialog.isShowing()) {
                    this.mDialog.dismiss();
                    this.mDialog = null;
                }
                this.mDialog = new Dialog(context, R.style.dialog_transparent_bg);
                this.mDialog.setContentView(linearLayout);
                this.mDialog.setCancelable(true);
                this.mDialog.show();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }
}
