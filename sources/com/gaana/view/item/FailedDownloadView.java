package com.gaana.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.view.BaseItemView;

public class FailedDownloadView extends BaseItemView implements OnClickListener {
    public FailedDownloadView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.failed_download_view, this, true);
        return this;
    }
}
