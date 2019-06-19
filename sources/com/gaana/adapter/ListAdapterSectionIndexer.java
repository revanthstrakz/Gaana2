package com.gaana.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import com.fragments.BaseGaanaFragment;
import com.gaana.FastScrollRecyclerView.FastScrollRecyclerView.SectionedAdapter;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.localmedia.LocalMediaFilter.GenericFilter;
import com.gaana.models.BusinessObject;
import com.managers.x;
import com.services.l.j;
import java.util.ArrayList;
import java.util.Collections;

public class ListAdapterSectionIndexer extends ListAdapter implements Filterable, SectionedAdapter, j {
    public static final int ITEM_TYPE_EDIT_PLAYLIST_SELECT_SONG = 4;
    public static final int ITEM_TYPE_NOTIFICATION = 2;
    private ArrayList<BusinessObject> arrayListItem = null;
    private boolean hasLoadingFeature = true;
    private int mItemTouchHelperType = -1;
    private OnNotificationsCleared mOnNotificationsCleared = null;
    private SearchFilter mSearchFilter = null;

    public interface OnSearchCompleted {
        void onSearch(ArrayList<BusinessObject> arrayList);
    }

    public interface OnFilterStarted {
        ArrayList<BusinessObject> onFilter(ArrayList<Object> arrayList, String str, boolean z, String str2, String str3, x xVar);
    }

    public interface OnNotificationsCleared {
        void notificationsCleared();
    }

    public class SearchFilter extends Filter {
        private boolean fetchFromDb;
        private x loadStrategy;
        private OnFilterStarted onFilterStarted;
        private OnSearchCompleted onSearchCompleted;
        private String orderType;
        private String sortColumn;
        private ArrayList<Object> sourceArrList = new ArrayList();

        public SearchFilter() {
            this.sourceArrList.addAll(ListAdapterSectionIndexer.this.mArrrListItems);
        }

        public void setOnSearchCompleted(OnSearchCompleted onSearchCompleted) {
            this.onSearchCompleted = onSearchCompleted;
        }

        public void setOnFilterStarted(OnFilterStarted onFilterStarted) {
            this.onFilterStarted = onFilterStarted;
        }

        public void shouldFetchFromDb(boolean z) {
            this.fetchFromDb = z;
        }

        public void setLoadStrategy(x xVar) {
            this.loadStrategy = xVar;
        }

        /* Access modifiers changed, original: protected */
        public FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = this.sourceArrList.clone();
                filterResults.count = this.sourceArrList.size();
                ListAdapterSectionIndexer.this.hasLoadingFeature = true;
            } else {
                if (this.onFilterStarted == null) {
                    this.onFilterStarted = new GenericFilter();
                }
                ArrayList onFilter = this.onFilterStarted.onFilter(this.sourceArrList, charSequence.toString(), this.fetchFromDb, "name", "DESC", this.loadStrategy);
                filterResults.values = onFilter;
                filterResults.count = onFilter.size();
            }
            return filterResults;
        }

        /* Access modifiers changed, original: protected */
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList arrayList = (ArrayList) filterResults.values;
            if (filterResults.count == 0) {
                ListAdapterSectionIndexer.this.iAddListItemView.showHideEmtpyView(true);
            } else {
                ListAdapterSectionIndexer.this.iAddListItemView.showHideEmtpyView(false);
            }
            ListAdapterSectionIndexer.this.setAdapterArrayList(arrayList);
            if (this.onSearchCompleted != null) {
                this.onSearchCompleted.onSearch(arrayList);
            }
        }
    }

    public void onComplete(int i) {
    }

    public void onItemDelete(int i, int i2) {
    }

    @NonNull
    public String getSectionName(int i) {
        if (this.arrayListItem == null || i > this.arrayListItem.size() - 1) {
            return "";
        }
        String name = ((BusinessObject) this.arrayListItem.get(i)).getName();
        return TextUtils.isEmpty(name) ? "" : String.valueOf(name.charAt(0));
    }

    public boolean hasLoadingFeature() {
        return this.hasLoadingFeature;
    }

    public boolean onItemMove(int i, int i2) {
        if (this.mItemTouchHelperType != 4) {
            return false;
        }
        Collections.swap(this.arrayListItem, i, i2);
        return super.onItemMove(i, i2);
    }

    public void setOnNotificationsCleared(OnNotificationsCleared onNotificationsCleared) {
        this.mOnNotificationsCleared = onNotificationsCleared;
    }

    public ListAdapterSectionIndexer(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public void setParamaters(ArrayList<?> arrayList, IAddListItemView iAddListItemView) {
        super.setParamaters(arrayList, iAddListItemView);
        this.arrayListItem = arrayList;
    }

    public void updateAdapterArrayList(ArrayList<?> arrayList) {
        super.updateAdapterArrayList(arrayList);
    }

    public void setAdapterArrayList(ArrayList<?> arrayList) {
        super.setAdapterArrayList(arrayList);
    }

    public void removeItem(Object obj) {
        super.removeItem(obj);
    }

    public void setItemType(int i) {
        this.mItemTouchHelperType = i;
    }

    public int getItemType() {
        return this.mItemTouchHelperType;
    }

    public Filter getFilter() {
        if (this.mSearchFilter == null) {
            this.mSearchFilter = new SearchFilter();
        }
        return this.mSearchFilter;
    }
}
