package com.gaana.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.Constants.QUEUE_ACTION;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.view.item.DownloadSongsItemView;
import com.managers.PlayerManager;
import com.managers.aj;
import com.managers.aj.b;
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

public class MusicQueueAdapter extends Adapter<ViewHolder> implements j {
    private static int ITEM_TYPE_HISTORY = 101;
    private static int ITEM_TYPE_UPNEXT = 102;
    private static final int LEFT = 4;
    public static final int LIST = 1;
    private boolean isSwipeAnimated = false;
    private ArrayList<Object> mArrrListItems;
    private Context mContext;
    private int mDeletedPosition;
    private Set<Object> mDeletedSet;
    private PlayerTrack mDeletedTrack;
    private ao mDragStartListener;
    private Paint mPaint;
    private final PlayerManager mPlayerManager;
    private boolean mShouldUpdateList = false;
    private final TypedArray mTypedArray;
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

        public void onItemSelected() {
        }

        public ListViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mDragIcon = (ImageView) view.findViewById(R.id.f33download.img.holder);
        }

        public void onItemClear(int i) {
            View findViewById = this.itemView.findViewById(R.id.item_player_overlay);
            if (i <= PlayerManager.a(GaanaApplication.getContext()).s() || Constants.ab) {
                if (Constants.ab) {
                    findViewById.setClickable(true);
                } else {
                    findViewById.setClickable(false);
                }
                findViewById.setVisibility(0);
                return;
            }
            findViewById.setClickable(false);
            findViewById.setVisibility(8);
        }

        public void setVisibility(boolean z) {
            LayoutParams layoutParams = (LayoutParams) this.itemView.getLayoutParams();
            Context context = this.itemView.getContext();
            if (z) {
                layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.item_two_line_bar_height);
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

    public static class SectionItemHolder extends ViewHolder {
        private TextView mHeaderText;

        public SectionItemHolder(View view) {
            super(view);
            this.mHeaderText = (TextView) view.findViewById(R.id.section_item_queue_headerText);
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

    public void updateArrayList(ArrayList<?> arrayList) {
        this.mArrrListItems = arrayList;
    }

    public MusicQueueAdapter(Context context, ArrayList<?> arrayList, ao aoVar, IUpdatePlayer iUpdatePlayer) {
        this.mArrrListItems = arrayList;
        this.mDragStartListener = aoVar;
        this.mContext = context;
        this.mUpdateListener = iUpdatePlayer;
        this.mPlayerManager = PlayerManager.a(this.mContext);
        this.mTypedArray = this.mContext.obtainStyledAttributes(new int[]{R.attr.tab_layout_background_attr});
        this.mPaint = new Paint();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == ITEM_TYPE_UPNEXT) {
            return new SectionItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_item_queue_header, viewGroup, false));
        }
        return new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_item_player_queue, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == 1) {
            ListViewHolder listViewHolder = (ListViewHolder) viewHolder;
            if (((PlayerTrack) this.mArrrListItems.get(i)).a(true) == null) {
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
            listViewHolder.mView.setBackgroundColor(this.mTypedArray.getColor(0, 0));
            View findViewById = listViewHolder.mView.findViewById(R.id.item_player_overlay);
            if (i <= this.mPlayerManager.s() || Constants.ab) {
                if (Constants.ab) {
                    findViewById.setClickable(true);
                } else {
                    findViewById.setClickable(false);
                }
                findViewById.setVisibility(0);
            } else {
                findViewById.setClickable(false);
                findViewById.setVisibility(8);
            }
            listViewHolder.mDragIcon.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) == 0 && MusicQueueAdapter.this.mArrrListItems != null && MusicQueueAdapter.this.mArrrListItems.size() > 0) {
                        MusicQueueAdapter.this.mDragStartListener.onStartDrag(viewHolder);
                        MusicQueueAdapter.this.startPosition = viewHolder.getAdapterPosition();
                    }
                    return false;
                }
            });
        } else if (viewHolder.getItemViewType() == ITEM_TYPE_UPNEXT) {
            if (this.mPlayerManager.n().size() < 1 || this.mPlayerManager.t() || this.mPlayerManager.s() == 0) {
                ((SectionItemHolder) viewHolder).setVisibility(false);
            } else {
                SectionItemHolder sectionItemHolder = (SectionItemHolder) viewHolder;
                sectionItemHolder.setVisibility(true);
                TextView access$500 = sectionItemHolder.mHeaderText;
                String string = this.mContext.getString(R.string.queue_up_next);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(" (");
                stringBuilder.append((this.mArrrListItems.size() - 1) - this.mPlayerManager.s());
                stringBuilder.append(")");
                access$500.setText(string.concat(stringBuilder.toString()));
            }
        }
    }

    public int getItemCount() {
        return this.mArrrListItems.size();
    }

    public int getItemViewType(int i) {
        return i == this.mPlayerManager.s() ? ITEM_TYPE_UPNEXT : 1;
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
        if (!(i2 == 4 ? "Left" : "Right").equals("Right") && this.mArrrListItems.size() > 0) {
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
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.single_song_delete_message), new b() {
                public void undoSnackBar() {
                    if (MusicQueueAdapter.this.mDeletedPosition > -1 && MusicQueueAdapter.this.mDeletedTrack != null) {
                        MusicQueueAdapter.this.mArrrListItems.add(MusicQueueAdapter.this.mDeletedPosition, MusicQueueAdapter.this.mDeletedTrack);
                        PlayerManager.a(MusicQueueAdapter.this.mContext).a(MusicQueueAdapter.this.mDeletedTrack.h(), true);
                        MusicQueueAdapter.this.notifyItemInserted(MusicQueueAdapter.this.mDeletedPosition);
                        PlayerManager.a(MusicQueueAdapter.this.mContext).a(QUEUE_ACTION.UNDO, MusicQueueAdapter.this.mDeletedPosition, 0);
                        MusicQueueAdapter.this.mUpdateListener.onListUpdated();
                        MusicQueueAdapter.this.mDeletedPosition = -1;
                        MusicQueueAdapter.this.mDeletedTrack = null;
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
