package com.gaana.revampeddetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.revampeddetail.model.RevampedSimilarAlbumEntityInfo.GenericEntity;
import com.library.controls.CrossFadeImageView;
import com.managers.af;
import com.managers.an;
import com.utilities.Util;
import java.util.ArrayList;

public class RevampedSimilarAlbumEntityAdapter extends Adapter<ViewHolder> {
    ArrayList<GenericEntity> entityArrayList;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private LayoutInflater mInflator;

    public static class RevampedSimilarAlbumHolder extends ViewHolder {
        CrossFadeImageView imageView;
        TextView title;

        public RevampedSimilarAlbumHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.item_title);
            this.imageView = (CrossFadeImageView) view.findViewById(R.id.item_image);
        }
    }

    private RevampedSimilarAlbumEntityAdapter() {
    }

    public RevampedSimilarAlbumEntityAdapter(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mInflator = LayoutInflater.from(context);
        this.entityArrayList = new ArrayList();
    }

    public void setData(ArrayList<GenericEntity> arrayList) {
        if (this.entityArrayList == null) {
            this.entityArrayList = new ArrayList();
        } else {
            this.entityArrayList.clear();
        }
        this.entityArrayList.addAll(arrayList);
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RevampedSimilarAlbumHolder(this.mInflator.inflate(R.layout.revamped_detail_list_item_horscroll_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final GenericEntity genericEntity = (GenericEntity) this.entityArrayList.get(i);
        RevampedSimilarAlbumHolder revampedSimilarAlbumHolder = (RevampedSimilarAlbumHolder) viewHolder;
        revampedSimilarAlbumHolder.imageView.bindImage(genericEntity.getArtwork());
        revampedSimilarAlbumHolder.title.setText(genericEntity.getEnglishName());
        revampedSimilarAlbumHolder.itemView.setTag(Integer.valueOf(i));
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (RevampedSimilarAlbumEntityAdapter.this.mFragment != null && (RevampedSimilarAlbumEntityAdapter.this.mFragment instanceof RevampedDetailListing)) {
                    ((RevampedDetailListing) RevampedSimilarAlbumEntityAdapter.this.mFragment).a("Similar Album", false);
                }
                int intValue = ((Integer) view.getTag()).intValue();
                BusinessObject a = Util.a(genericEntity);
                af.a(RevampedSimilarAlbumEntityAdapter.this.mContext, RevampedSimilarAlbumEntityAdapter.this.mFragment).a((int) R.id.albumMenu, a);
                an.a().a("click", "ac", a.getBusinessObjId(), "", "", "similaralbum", String.valueOf(intValue), "");
            }
        });
    }

    public int getItemCount() {
        return (this.entityArrayList == null || this.entityArrayList.size() <= 0) ? 0 : this.entityArrayList.size();
    }
}
