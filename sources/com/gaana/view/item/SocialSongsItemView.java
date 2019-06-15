package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.view.item.BaseItemView.SocialHomeGridHolder;
import com.library.controls.CrossFadeImageView;
import java.util.ArrayList;

public class SocialSongsItemView extends DownloadSongsItemView {
    private CrossFadeImageView mCrossFadeImageIcon;
    private LinearLayout profileLikesImage;
    private TextView tvSubtitle;
    private TextView tvTitle;

    public SocialSongsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getGridItemView(ViewHolder viewHolder, BusinessObject businessObject) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        this.mBusinessObject = businessObject;
        SocialHomeGridHolder socialHomeGridHolder = (SocialHomeGridHolder) viewHolder;
        this.tvTitle = socialHomeGridHolder.tvTopHeading;
        this.tvSubtitle = socialHomeGridHolder.tvBottomHeading;
        this.profileLikesImage = socialHomeGridHolder.profileLikesImage;
        int i = 0;
        if (businessObject.getName() != null) {
            this.tvTitle.setVisibility(0);
            this.tvTitle.setText(businessObject.getName());
            this.tvTitle.setTextAppearance(this.mContext, R.style.grid_caption);
        } else {
            this.tvTitle.setVisibility(8);
        }
        socialHomeGridHolder.play_icon.setVisibility(0);
        this.tvSubtitle.setVisibility(8);
        if (businessObject != null && (businessObject instanceof Item)) {
            this.profileLikesImage.setVisibility(0);
            ArrayList arrayList = (ArrayList) ((Item) businessObject).getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("artwork_people")) {
                        ArrayList arrayList2 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("follow_ct")) {
                        ((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue();
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("artwork_people_ids")) {
                        String str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    }
                    i++;
                }
            }
        }
        this.mCrossFadeImageIcon = socialHomeGridHolder.crossFadeImageView;
        bindImage(this.mCrossFadeImageIcon, null, null, businessObject, true, false);
        return this.mView;
    }
}
