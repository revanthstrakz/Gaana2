package com.gaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gaana.R;
import com.gaana.view.item.BaseItemView.EmptyMessageHolder;
import com.gaana.view.item.BaseItemView.FriendsActivityListHolder;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.services.l.l;
import java.util.ArrayList;

public class RecyclerListAdapter extends Adapter<ViewHolder> {
    private final int empty_friends_activity = 1;
    private final int friends_activity = 0;
    private int holderType = -1;
    IAddListItemView iAddListItemView;
    ArrayList<Object> mArrrListItems;
    private Context mContext;
    private l mLoadMoreListner;
    private final int recently_played_view = 2;
    private final int trending_song_view = 5;

    public interface IAddListItemView {
        View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup);

        int getItemViewType(int i);
    }

    public RecyclerListAdapter(Context context, ArrayList<Object> arrayList) {
        this.mContext = context;
        this.mArrrListItems = arrayList;
    }

    public void setLoadMoreListner(l lVar) {
        this.mLoadMoreListner = lVar;
    }

    public void setParamaters(int i, ArrayList<?> arrayList, IAddListItemView iAddListItemView) {
        this.holderType = i;
        this.mArrrListItems = new ArrayList();
        this.mArrrListItems.addAll(arrayList);
        this.iAddListItemView = iAddListItemView;
    }

    public ArrayList<Object> getAdapterArrayList() {
        return this.mArrrListItems;
    }

    public void setAdapterArrayList(ArrayList<?> arrayList) {
        if (arrayList != null) {
            this.mArrrListItems = arrayList;
        }
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        this.mArrrListItems.remove(obj);
        notifyDataSetChanged();
    }

    public void clearAdapter() {
        this.mArrrListItems.clear();
        notifyDataSetChanged();
    }

    public void updateAdapterArrayList(ArrayList<?> arrayList) {
        if (!(arrayList == null || arrayList.size() == 0)) {
            this.mArrrListItems.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public Object getItem(int i) {
        return this.mArrrListItems.get(i);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        int i2 = this.holderType;
        if (i2 != 5) {
            switch (i2) {
                case 0:
                    return new FriendsActivityListHolder(from.inflate(R.layout.view_item_activity_small, viewGroup, false));
                case 1:
                    return new EmptyMessageHolder(from.inflate(R.layout.view_user_msg, viewGroup, false));
                case 2:
                    break;
                default:
                    return null;
            }
        }
        return new AlbumDetailItemHolder(from.inflate(R.layout.view_item_download, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.mLoadMoreListner != null) {
            int i2 = 1;
            if (i != getItemCount() - 1) {
                i2 = 0;
            }
            if (i2 != 0) {
                this.mLoadMoreListner.loadMoreData(getItemCount(), this.mArrrListItems.get(i));
            }
        }
        int i3 = this.holderType;
        if (i3 != 5) {
            switch (i3) {
                case 0:
                case 1:
                case 2:
                    break;
                default:
                    return;
            }
        }
        this.iAddListItemView.addListItemView(getItem(i), viewHolder, null);
    }

    public int getItemCount() {
        if ((this.holderType == 2 || this.holderType == 5) && this.mArrrListItems.size() > 5) {
            return 5;
        }
        return this.mArrrListItems.size();
    }

    public int getItemViewType(int i) {
        return this.iAddListItemView.getItemViewType(i);
    }
}
