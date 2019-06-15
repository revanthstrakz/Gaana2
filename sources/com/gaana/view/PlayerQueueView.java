package com.gaana.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.RecyclerListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.adapter.MusicQueueAdapter;
import com.gaana.adapter.MusicQueueAdapter.IUpdatePlayer;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.managers.PlayerManager;
import com.services.d;
import com.services.l.ao;
import com.utilities.e;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerQueueView extends Dialog implements IUpdatePlayer, ao {
    Callback callback;
    public Handler handler;
    public boolean isAnimationRunning;
    LinearLayoutManager layoutManager;
    private MusicQueueAdapter listAdapter = null;
    private BusinessObject mBusinessObject;
    private ItemTouchHelper mItemTouchHelper;
    private RecyclerView mListView = null;
    private PlayerManager mPlayerManager;
    private RecyclerListener mRecyclerListener = new RecyclerListener() {
        public void onViewRecycled(ViewHolder viewHolder) {
            if (viewHolder instanceof AlbumDetailItemHolder) {
                ((AlbumDetailItemHolder) viewHolder).mCrossFadeImageIcon.onViewRecycled();
            }
        }
    };
    private OnScrollListener mScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 0 && PlayerQueueView.this.listAdapter != null) {
                PlayerQueueView.this.listAdapter.updateListIfNeeded();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            PlayerQueueView.this.setPanelText(recyclerView.getContext());
        }
    };
    private TextView mTextPanel;
    private View mView;
    boolean stopAnimation = false;

    public PlayerQueueView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, 16973836);
        requestWindowFeature(1);
        this.mPlayerManager = PlayerManager.a(context);
    }

    public PlayerQueueView(Context context, BusinessObject businessObject, BaseGaanaFragment baseGaanaFragment) {
        super(context);
        this.mBusinessObject = businessObject;
        requestWindowFeature(1);
        this.mPlayerManager = PlayerManager.a(context);
    }

    public View getView(Context context, ArrayList<?> arrayList, IUpdatePlayer iUpdatePlayer) {
        if (this.mView == null && this.mBusinessObject == null) {
            populateView(context, arrayList, iUpdatePlayer);
        }
        return this.mView;
    }

    public View getView(Context context, ArrayList<?> arrayList, IUpdatePlayer iUpdatePlayer, ViewGroup viewGroup) {
        populateView(context, arrayList, iUpdatePlayer, viewGroup);
        return this.mView;
    }

    public void refreshListView() {
        if (this.mListView != null && this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    public MusicQueueAdapter getListAdapter() {
        return this.listAdapter;
    }

    private void populateView(Context context, ArrayList<?> arrayList, IUpdatePlayer iUpdatePlayer) {
        this.mView = LayoutInflater.from(getContext()).inflate(R.layout.player_queue_view, null);
        setContentView(this.mView);
        this.mListView = (RecyclerView) this.mView.findViewById(R.id.scroll);
        if (this.layoutManager == null) {
            this.layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        }
        this.mListView.setLayoutManager(this.layoutManager);
        this.mListView.setItemAnimator(new DefaultItemAnimator());
        ((Toolbar) this.mView.findViewById(R.id.toolbar)).setContentInsetsAbsolute(0, 0);
        this.mListView.setRecyclerListener(this.mRecyclerListener);
        this.listAdapter = new MusicQueueAdapter(context, arrayList, this, iUpdatePlayer);
        this.mListView.setAdapter(this.listAdapter);
        this.callback = new e(this.listAdapter);
        this.mItemTouchHelper = new ItemTouchHelper(this.callback);
        this.mItemTouchHelper.attachToRecyclerView(this.mListView);
        this.mListView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((e) PlayerQueueView.this.callback).c(false);
                return false;
            }
        });
        this.mListView.addOnScrollListener(this.mScrollListener);
    }

    public void dragQueueItem(RecyclerView recyclerView, final float f, float f2) {
        if (recyclerView.getChildCount() > 1) {
            View view;
            ((e) this.callback).c(true);
            int findFirstCompletelyVisibleItemPosition = this.layoutManager.findFirstCompletelyVisibleItemPosition();
            if (recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition) == null || !(recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition).itemView.getTag() instanceof BusinessObject)) {
                findFirstCompletelyVisibleItemPosition++;
                if (recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition) != null && (recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition).itemView.getTag() instanceof BusinessObject)) {
                    view = recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition).itemView;
                } else {
                    return;
                }
            }
            view = recyclerView.findViewHolderForAdapterPosition(findFirstCompletelyVisibleItemPosition).itemView;
            this.isAnimationRunning = true;
            view.animate().translationX(f2).setDuration(1000).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    view.animate().translationX(f).setDuration(1000).setListener(new AnimatorListener() {
                                        public void onAnimationCancel(Animator animator) {
                                        }

                                        public void onAnimationRepeat(Animator animator) {
                                        }

                                        public void onAnimationStart(Animator animator) {
                                        }

                                        public void onAnimationEnd(Animator animator) {
                                            PlayerQueueView.this.isAnimationRunning = false;
                                            ((e) PlayerQueueView.this.callback).c(false);
                                            ((e) PlayerQueueView.this.callback).b(true);
                                        }
                                    });
                                }
                            });
                        }
                    }, 1000);
                }
            });
            this.listAdapter.setIsSwipeAnimated(true);
            this.mItemTouchHelper.startSwipe(recyclerView.getChildViewHolder(view));
        }
    }

    private void populateView(Context context, ArrayList<?> arrayList, IUpdatePlayer iUpdatePlayer, ViewGroup viewGroup) {
        this.mView = LayoutInflater.from(getContext()).inflate(R.layout.player_queue_view, viewGroup, true);
        this.mListView = (RecyclerView) this.mView.findViewById(R.id.scroll);
        this.mTextPanel = (TextView) this.mView.findViewById(R.id.player_queue_panel_text);
        TextView textView;
        String string;
        StringBuilder stringBuilder;
        if (this.mPlayerManager.s() > 0) {
            textView = this.mTextPanel;
            string = context.getResources().getString(R.string.queue_previous);
            stringBuilder = new StringBuilder();
            stringBuilder.append(" (");
            stringBuilder.append(this.mPlayerManager.s());
            stringBuilder.append(")");
            textView.setText(string.concat(stringBuilder.toString()));
        } else if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 1) {
            this.mTextPanel.setVisibility(8);
        } else {
            textView = this.mTextPanel;
            string = context.getResources().getString(R.string.queue_up_next);
            stringBuilder = new StringBuilder();
            stringBuilder.append(" (");
            stringBuilder.append(this.mPlayerManager.n().size() - this.mPlayerManager.s());
            stringBuilder.append(")");
            textView.setText(string.concat(stringBuilder.toString()));
        }
        this.mListView.setRecyclerListener(this.mRecyclerListener);
        if (this.layoutManager == null) {
            this.layoutManager = new LinearLayoutManager(getContext());
        }
        this.mListView.setLayoutManager(this.layoutManager);
        this.mListView.setHasFixedSize(false);
        this.mListView.addOnScrollListener(this.mScrollListener);
        if (arrayList != null) {
            this.listAdapter = new MusicQueueAdapter(context, arrayList, this, iUpdatePlayer);
            this.mListView.setAdapter(this.listAdapter);
            this.callback = new e(this.listAdapter);
            this.mItemTouchHelper = new ItemTouchHelper(this.callback);
            this.mItemTouchHelper.attachToRecyclerView(this.mListView);
        }
        this.mListView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((e) PlayerQueueView.this.callback).c(false);
                return false;
            }
        });
    }

    public void stopQueueAnimation() {
        if (this.mListView != null) {
            View childAt = this.mListView.getChildAt(0);
            if (childAt != null) {
                childAt.clearAnimation();
                this.stopAnimation = true;
                this.isAnimationRunning = false;
            }
        }
    }

    public void setSwipeCoachmarkAnimation() {
        if (!this.isAnimationRunning) {
            this.stopAnimation = false;
            if (!this.stopAnimation) {
                animate();
            }
        }
    }

    private void removeSongCoachmarkAction(int i) {
        i++;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (!PlayerQueueView.this.stopAnimation) {
                    PlayerQueueView.this.dragQueueItem(PlayerQueueView.this.mListView, 0.0f, -((float) (d.a().b() / 3)));
                    d.a().a("SWIPE_TO_DELETE_ANIMATION", i + 1, false);
                    d.a().a("SESSION_OCCURENCE_SWIPE_TO_DELETE_ANIMATION", GaanaApplication.sessionHistoryCount, false);
                }
            }
        });
    }

    private void animate() {
        if (!d.a().b("PREFERENCE_KEY_SLIDE_LEFT_INITIATED", false, false)) {
            int b = d.a().b("SWIPE_TO_DELETE_ANIMATION", 0, false);
            if (b < 3) {
                int b2 = d.a().b("SESSION_OCCURENCE_SWIPE_TO_DELETE_ANIMATION", 0, false);
                int i = b2 + 5;
                if (b2 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        removeSongCoachmarkAction(b);
                    }
                } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 6) {
                    removeSongCoachmarkAction(b);
                }
            }
        }
    }

    public ArrayList<?> getAdapterArrayList() {
        return this.listAdapter != null ? this.listAdapter.getAdapterArrayList() : null;
    }

    public void notifyDataSetChanged() {
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
            updateQueueStatus();
        }
    }

    public void notifyItemRangeInserted(int i, int i2) {
        if (this.listAdapter != null) {
            this.listAdapter.notifyItemRangeInserted(i, i2);
        }
    }

    public void notifyItemChanged(int i) {
        if (this.listAdapter != null) {
            this.listAdapter.notifyItemRangeChanged(i, 3);
        }
    }

    public void updateArrayList(ArrayList<?> arrayList) {
        if (this.listAdapter != null) {
            this.listAdapter.updateArrayList(arrayList);
            this.listAdapter.notifyDataSetChanged();
        }
    }

    public void onStartDrag(ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
    }

    public View getPlayerQueueView() {
        return this.mView;
    }

    public RecyclerView getRecyclerView() {
        return this.mListView;
    }

    /* Access modifiers changed, original: protected */
    public int getActionBarSize() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public void updateQueueStatus() {
        if (this.listAdapter != null && this.listAdapter.getAdapterArrayList() != null && this.listAdapter.getAdapterArrayList().size() > 0) {
            setPanelText(this.mListView.getContext());
        }
    }

    public void onListUpdated() {
        updateQueueStatus();
    }

    private void setPanelText(Context context) {
        TextView textView;
        String string;
        StringBuilder stringBuilder;
        if (((LinearLayoutManager) this.mListView.getLayoutManager()).findFirstVisibleItemPosition() > this.mPlayerManager.s() && this.mPlayerManager.n().size() > 1) {
            textView = this.mTextPanel;
            string = context.getResources().getString(R.string.queue_up_next);
            stringBuilder = new StringBuilder();
            stringBuilder.append(" (");
            stringBuilder.append((this.mPlayerManager.n().size() - 1) - this.mPlayerManager.s());
            stringBuilder.append(")");
            textView.setText(string.concat(stringBuilder.toString()));
            this.mTextPanel.setVisibility(0);
        } else if (this.mPlayerManager.s() > 0) {
            textView = this.mTextPanel;
            string = context.getResources().getString(R.string.queue_previous);
            stringBuilder = new StringBuilder();
            stringBuilder.append(" (");
            stringBuilder.append(this.mPlayerManager.s());
            stringBuilder.append(")");
            textView.setText(string.concat(stringBuilder.toString()));
            this.mTextPanel.setVisibility(0);
        } else if (this.mPlayerManager.n().size() <= 1) {
            this.mTextPanel.setVisibility(8);
        } else if (this.mPlayerManager.s() == 0 && this.mPlayerManager.n().size() > 1) {
            textView = this.mTextPanel;
            string = context.getResources().getString(R.string.queue_up_next);
            stringBuilder = new StringBuilder();
            stringBuilder.append(" (");
            stringBuilder.append((this.mPlayerManager.n().size() - 1) - this.mPlayerManager.s());
            stringBuilder.append(")");
            textView.setText(string.concat(stringBuilder.toString()));
            this.mTextPanel.setVisibility(0);
        }
    }
}
