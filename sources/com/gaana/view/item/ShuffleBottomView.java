package com.gaana.view.item;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.TrialProductFeature;
import com.models.ListingButton;
import com.services.d;
import com.services.l.as;
import com.utilities.Util.BLOCK_ACTION;

public class ShuffleBottomView extends BottomSheetDialog implements OnClickListener {
    private TextView additionalText;
    private BLOCK_ACTION mBlockAction;
    private TrialProductFeature mBusinessObj;
    private Context mContext;
    private final ListingButton mListingButton;
    private ProductItem mProduct;
    private View mView;
    private as onTrialSuccess;
    private TextView topHeaderTitle;

    public ShuffleBottomView(Context context, ListingButton listingButton) {
        super(context);
        this.mContext = context;
        this.mListingButton = listingButton;
        init(context);
    }

    private void init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.popup_shuffle_view_layout, null);
        Button button = (Button) this.mView.findViewById(R.id.payNowButton);
        CheckBox checkBox = (CheckBox) this.mView.findViewById(R.id.checkbox);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.mView.findViewById(R.id.coordinator_layout);
        setContentView(this.mView);
        BottomSheetBehavior.from((RelativeLayout) this.mView.findViewById(R.id.layout)).setState(3);
        this.topHeaderTitle = (TextView) this.mView.findViewById(R.id.topHeaderTitle);
        this.topHeaderTitle.setText("Shuffle Play");
        ((TextView) this.mView.findViewById(R.id.subTitleText)).setText("Playing the top 200 songs for you now");
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    d.a().a("PREFERENCE_SHUFFLE_DIALOG", false, false);
                }
            }
        });
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.payNowButton) {
            dismiss();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
