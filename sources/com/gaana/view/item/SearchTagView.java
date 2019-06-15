package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.e;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.SearchTags.Tag;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.u;
import com.services.c;

public class SearchTagView extends FrameLayout {
    private final Context mContext;

    public class SearchTagHolder extends ViewHolder {
        public ImageView mImageArtWork;
        public TextView mText;

        public SearchTagHolder(View view) {
            super(view);
            this.mImageArtWork = (ImageView) view.findViewById(R.id.img_artwork);
            this.mImageArtWork.setPadding(0, 0, 0, 0);
            this.mText = (TextView) view.findViewById(R.id.txt_name);
        }
    }

    public SearchTagView(Context context) {
        super(context);
        this.mContext = context;
    }

    public void bindView(SearchTagHolder searchTagHolder, final Tag tag) {
        e.c(this.mContext).load(tag.getAtw()).into(searchTagHolder.mImageArtWork);
        searchTagHolder.mText.setText(tag.getTagName());
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().a("Online-SearchScreen", "QuickSearch", tag.getTagName());
                c.a(SearchTagView.this.mContext).a(SearchTagView.this.mContext, tag.getDeeplink(), GaanaApplication.getInstance());
                GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.QUICK_SEARCH.name());
                GaanaSearchManager.a().b(true);
            }
        });
    }

    public SearchTagHolder createViewHolder() {
        LayoutInflater.from(this.mContext).inflate(R.layout.item_explore, this, true);
        return new SearchTagHolder(this);
    }
}
