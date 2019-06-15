package com.gaana.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import com.constants.Constants.QUEUE_ACTION;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.DownloadSongsItemView;
import com.managers.ColombiaAdViewManager;
import com.managers.PlayerManager;
import com.managers.aj;
import com.managers.aj.b;
import com.managers.an;
import com.managers.e;
import com.managers.u;
import com.models.PlayerTrack;
import com.services.d;
import com.services.l.ao;
import com.services.l.az;
import com.services.l.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MusicQueueAdapterV2 extends Adapter<ViewHolder> implements OnMenuItemClickListener, j {
    private static int ITEM_TYPE_CURRENT = 103;
    private static final int LEFT = 4;
    public static final int LIST = 1;
    private boolean isSwipeAnimated = false;
    private ArrayList<Object> mArrrListItems;
    private Context mContext;
    private int mDeletedPosition;
    private Set<Object> mDeletedSet;
    private PlayerTrack mDeletedTrack;
    private ao mDragStartListener;
    private final PlayerManager mPlayerManager;
    private boolean mShouldUpdateList = false;
    final IUpdatePlayer mUpdateListener;
    private int startPosition = 0;

    public interface IUpdatePlayer {
        void onListUpdated();
    }

    public static class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public static class ListViewHolder extends ViewHolder implements az {
        private ImageView mDragIcon;
        private View mView;

        public void onItemClear(int i) {
        }

        public void onItemSelected() {
        }

        public ListViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mDragIcon = (ImageView) view.findViewById(R.id.f33download.img.holder);
        }

        public void setVisibility(boolean z) {
            LayoutParams layoutParams = (LayoutParams) this.itemView.getLayoutParams();
            this.itemView.getContext();
            if (z) {
                layoutParams.height = -2;
                layoutParams.width = -1;
                this.itemView.setVisibility(0);
            } else {
                this.itemView.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    public void updateArrayList(ArrayList<?> arrayList) {
        this.mArrrListItems = arrayList;
    }

    public MusicQueueAdapterV2(Context context, ArrayList<?> arrayList, ao aoVar, IUpdatePlayer iUpdatePlayer) {
        this.mArrrListItems = arrayList;
        this.mDragStartListener = aoVar;
        this.mContext = context;
        this.mUpdateListener = iUpdatePlayer;
        this.mPlayerManager = PlayerManager.a(this.mContext);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == ITEM_TYPE_CURRENT) {
            return new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_player_queue_v2_current, viewGroup, false));
        }
        return new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_player_queue_v2, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
        Track a = ((PlayerTrack) this.mArrrListItems.get(i)).a(true);
        if (a == null) {
            if (this.mDeletedSet == null) {
                this.mDeletedSet = new HashSet();
            }
            if (!this.mDeletedSet.contains(this.mArrrListItems.get(i))) {
                this.mDeletedSet.add(this.mArrrListItems.get(i));
            }
            listViewHolder.setVisibility(false);
            this.mShouldUpdateList = true;
            return;
        }
        listViewHolder.setVisibility(true);
        new DownloadSongsItemView(this.mContext, null, true).getPoplatedView(listViewHolder.mView, ((PlayerTrack) this.mArrrListItems.get(i)).a(true), null, true);
        ImageView imageView = (ImageView) listViewHolder.itemView.findViewById(R.id.player_queue_fav);
        if (imageView != null) {
            if (a.isFavorite().booleanValue()) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        if (viewHolder.getItemViewType() == ITEM_TYPE_CURRENT) {
            TextView textView = (TextView) listViewHolder.itemView.findViewById(R.id.section_item_queue_next);
            TextView textView2 = (TextView) listViewHolder.itemView.findViewById(R.id.section_item_queue_nowplaying);
            if (i == getItemCount() - 1) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            if (i == 0) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
            }
            if (textView.getVisibility() == 0) {
                String string = this.mContext.getString(R.string.queue_up_next);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" (");
                stringBuilder.append((this.mArrrListItems.size() - 1) - this.mPlayerManager.s());
                stringBuilder.append(")");
                textView.setText(string.concat(stringBuilder.toString()));
                LinearLayout linearLayout = (LinearLayout) listViewHolder.itemView.findViewById(R.id.adSlot);
                GaanaApplication.getInstance().setGADParameter("Player Queue");
                ColombiaAdViewManager.a().a(this.mContext, linearLayout, e.A, "Please Queue Up Next", new AdsUJData[0]);
            } else {
                listViewHolder.itemView.findViewById(R.id.adSlot).setVisibility(8);
            }
        } else {
            listViewHolder.mDragIcon.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) == 0 && MusicQueueAdapterV2.this.mArrrListItems != null && MusicQueueAdapterV2.this.mArrrListItems.size() > 0) {
                        MusicQueueAdapterV2.this.mDragStartListener.onStartDrag(viewHolder);
                        MusicQueueAdapterV2.this.startPosition = viewHolder.getAdapterPosition();
                    }
                    return false;
                }
            });
        }
    }

    public int getItemCount() {
        return this.mArrrListItems.size();
    }

    public int getItemViewType(int i) {
        return (i >= this.mArrrListItems.size() || i != this.mPlayerManager.s()) ? 1 : ITEM_TYPE_CURRENT;
    }

    public ArrayList<?> getAdapterArrayList() {
        return this.mArrrListItems;
    }

    public boolean onItemMove(int i, int i2) {
        Collections.swap(this.mArrrListItems, i, i2);
        boolean z = this.mPlayerManager.s() == i || this.mPlayerManager.s() == i2;
        PlayerManager.a(this.mContext).a(QUEUE_ACTION.MOVE, i, i2);
        notifyItemMoved(i, i2);
        d.a().a("PREFERENCE_KEY_HOLD_DRAG_INITIATED", true, false);
        this.mUpdateListener.onListUpdated();
        if (z) {
            notifyItemChanged(this.mPlayerManager.s());
        }
        return true;
    }

    public void onItemDelete(int i, int i2) {
        if (!(i2 == 4 ? "Left" : "Right").equals("Right") && this.mArrrListItems.size() > 0 && this.mPlayerManager.s() != i) {
            this.mDeletedPosition = i;
            this.mDeletedTrack = (PlayerTrack) this.mArrrListItems.get(this.mDeletedPosition);
            this.mArrrListItems.remove(i);
            PlayerManager.a(this.mContext).a(this.mDeletedTrack.h(), false);
            notifyItemRemoved(i);
            PlayerManager.a(this.mContext).a(QUEUE_ACTION.SWIPE, i, 0);
            String str = i2 == 4 ? "Left" : "Right";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" Swipe Track Removed");
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("PlayerQueue - ");
            stringBuilder3.append(str);
            stringBuilder3.append(" Swipe Track Removed");
            u.a().a("PlayerQueue", stringBuilder2, stringBuilder3.toString());
            an.a().a("swipe", "delete", "", "queue", "", "", "", String.valueOf(i));
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.single_song_delete_message), new b() {
                public void undoSnackBar() {
                    if (MusicQueueAdapterV2.this.mDeletedPosition > -1 && MusicQueueAdapterV2.this.mDeletedTrack != null) {
                        MusicQueueAdapterV2.this.mArrrListItems.add(MusicQueueAdapterV2.this.mDeletedPosition, MusicQueueAdapterV2.this.mDeletedTrack);
                        PlayerManager.a(MusicQueueAdapterV2.this.mContext).a(MusicQueueAdapterV2.this.mDeletedTrack.h(), true);
                        MusicQueueAdapterV2.this.notifyItemInserted(MusicQueueAdapterV2.this.mDeletedPosition);
                        PlayerManager.a(MusicQueueAdapterV2.this.mContext).a(QUEUE_ACTION.UNDO, MusicQueueAdapterV2.this.mDeletedPosition, 0);
                        MusicQueueAdapterV2.this.mUpdateListener.onListUpdated();
                        MusicQueueAdapterV2.this.mDeletedPosition = -1;
                        MusicQueueAdapterV2.this.mDeletedTrack = null;
                    }
                }
            });
            d.a().a("PREFERENCE_KEY_SLIDE_LEFT_INITIATED", true, false);
        }
    }

    public void onComplete(int i) {
        if (i > 0) {
            String str = i - this.startPosition > 0 ? "down" : "up";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Track moved ");
            stringBuilder.append(str);
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("PlayerQueue - Track moved ");
            stringBuilder3.append(str);
            u.a().a("PlayerQueue", stringBuilder2, stringBuilder3.toString());
            an.a().a("swipe", "move", "", "queue", "", "", String.valueOf(this.startPosition), String.valueOf(i));
        }
        this.mUpdateListener.onListUpdated();
    }

    public void updateListIfNeeded() {
        if (this.mShouldUpdateList && this.mDeletedSet != null) {
            for (Object remove : this.mDeletedSet) {
                this.mArrrListItems.remove(remove);
            }
            this.mDeletedSet.clear();
            this.mDeletedSet = null;
            this.mShouldUpdateList = false;
            notifyDataSetChanged();
            if (this.mUpdateListener != null) {
                this.mUpdateListener.onListUpdated();
            }
            PlayerManager.a(this.mContext).D();
        }
    }

    public void setIsSwipeAnimated(boolean z) {
        this.isSwipeAnimated = z;
    }
}
