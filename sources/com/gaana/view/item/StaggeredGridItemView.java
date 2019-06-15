package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.c.c;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.view.item.GenericItemView.TagObject;
import com.library.controls.CrossFadeImageView;
import com.utilities.Util;
import java.util.List;

public class StaggeredGridItemView extends BaseItemView {

    public static class StaggeredGridViewHolder extends ViewHolder {
        public CrossFadeImageView mImgView1;
        public CrossFadeImageView mImgView2;
        public CrossFadeImageView mImgView3;
        public CrossFadeImageView mImgView4;
        public CrossFadeImageView mImgView5;
        public CrossFadeImageView mImgView6;
        public TextView mTxtHeader;
        public TextView mTxtSubHeader;

        public StaggeredGridViewHolder(View view) {
            super(view);
            this.mImgView1 = (CrossFadeImageView) view.findViewById(R.id.img_0);
            this.mImgView2 = (CrossFadeImageView) view.findViewById(R.id.img_1);
            this.mImgView3 = (CrossFadeImageView) view.findViewById(R.id.img_2);
            this.mImgView4 = (CrossFadeImageView) view.findViewById(R.id.img_3);
            this.mImgView5 = (CrossFadeImageView) view.findViewById(R.id.img_4);
            this.mImgView6 = (CrossFadeImageView) view.findViewById(R.id.img_5);
            this.mTxtHeader = (TextView) view.findViewById(R.id.txt_header);
            this.mTxtSubHeader = (TextView) view.findViewById(R.id.txt_sub_header);
        }
    }

    public StaggeredGridItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.item_made_for_you;
    }

    public StaggeredGridItemView(Context context, BaseFragment baseFragment, int i) {
        super(context, baseFragment, i);
        this.mLayoutId = R.layout.item_made_for_you;
    }

    public View getPoplatedGenericView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        this.mView = super.getPoplatedView(viewHolder, businessObject);
        Item item = (Item) businessObject;
        StaggeredGridViewHolder staggeredGridViewHolder = (StaggeredGridViewHolder) viewHolder;
        staggeredGridViewHolder.mImgView1.bindImage(item.getArtwork());
        this.mView.setTag(new TagObject(businessObject, i, str));
        this.mView.setOnClickListener(this);
        if (item.getArtworks() != null) {
            staggeredGridViewHolder.mImgView2.bindImage((String) item.getArtworks().get(1));
            staggeredGridViewHolder.mImgView3.bindImage((String) item.getArtworks().get(2));
            staggeredGridViewHolder.mImgView4.bindImage((String) item.getArtworks().get(3));
            staggeredGridViewHolder.mImgView5.bindImage((String) item.getArtworks().get(4));
            staggeredGridViewHolder.mImgView6.bindImage((String) item.getArtworks().get(0));
        }
        staggeredGridViewHolder.mTxtHeader.setText(businessObject.getName());
        if (item.getEntityType().equals(c.j)) {
            int i2;
            List entityInfo = item.getEntityInfo();
            if (entityInfo != null) {
                int size = entityInfo.size();
                i2 = -1;
                for (int i3 = 0; i3 < size; i3++) {
                    if (((EntityInfo) entityInfo.get(i3)).getKey().equals("vpl_count")) {
                        try {
                            i2 = Integer.parseInt(((EntityInfo) entityInfo.get(i3)).getValue().toString());
                        } catch (Exception unused) {
                        }
                    }
                }
            } else {
                i2 = -1;
            }
            if (i2 == -1 || i2 <= 0) {
                staggeredGridViewHolder.mTxtSubHeader.setVisibility(4);
            } else {
                TextView textView = staggeredGridViewHolder.mTxtSubHeader;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i2);
                stringBuilder.append("+ ");
                stringBuilder.append(this.mContext.getResources().getString(R.string.songs));
                textView.setText(stringBuilder.toString());
                staggeredGridViewHolder.mTxtSubHeader.setVisibility(0);
            }
        }
        return this.mView;
    }

    public void onClick(View view) {
        super.onClick(view);
        TagObject tagObject = (TagObject) view.getTag();
        this.mBusinessObject = tagObject.getBusinessObject();
        int position = tagObject.getPosition() + 1;
        String header = tagObject.getHeader();
        BaseGaanaFragment o = Util.o((Item) this.mBusinessObject);
        if (o != null) {
            ((GaanaActivity) this.mContext).displayFragment(o);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(header);
        stringBuilder.append(" VPL ");
        stringBuilder.append(" click ");
        header = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("Position ");
        stringBuilder.append(position);
        stringBuilder.append(" - Album - ");
        stringBuilder.append(this.mBusinessObject.getBusinessObjId());
        ((BaseActivity) this.mContext).sendGAEvent("Browse", header, stringBuilder.toString());
    }
}
