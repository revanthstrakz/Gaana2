package com.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.RecycledViewPool;
import android.support.v7.widget.RecyclerView.RecyclerListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants.VIEW_SIZE;
import com.constants.Constants.VIEW_SUBTYPE;
import com.gaana.R;
import com.gaana.view.item.BaseItemView.CuratedDownloadSongSelectionHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.BaseItemView.SocialHomeGridHolder;
import com.managers.ap;
import com.managers.g;
import com.managers.u;
import java.util.Random;

public class HorizontalRecyclerView extends RecyclerView {
    boolean a = false;
    private b b;
    private int c;
    private boolean d = false;
    private int e;
    private boolean f;
    private LayoutManager g;
    private Context h;
    private int i;
    private RecyclerListener j = new RecyclerListener() {
        public void onViewRecycled(ViewHolder viewHolder) {
            if (viewHolder instanceof PlaylistGridHolder) {
                ((PlaylistGridHolder) viewHolder).crossFadeImageView.onViewRecycled();
            }
        }
    };

    public interface c {
        ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2);

        View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder);
    }

    public interface a extends c {
        int getItemViewType(int i);
    }

    public class b extends Adapter<ViewHolder> {
        private c b;
        private int c = 0;
        private int d = -1;
        private int e = -1;
        private final int f = 1;
        private final int g = 0;

        public b(Context context, int i) {
            this.c = i;
            this.d = new Random().nextInt(5) + 1;
        }

        public b(Context context, int i, int i2) {
            this.c = i;
            this.e = i2;
            this.d = new Random().nextInt(5) + 1;
        }

        public void a(c cVar) {
            this.b = cVar;
        }

        public void a(int i) {
            this.e = i;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            int a = HorizontalRecyclerView.this.c;
            ViewHolder viewHolder = null;
            if (a != 6) {
                switch (a) {
                    case 0:
                        if (i != R.layout.item_playlist_grid_ad_140x140 || this.b == null) {
                            if (i != 911) {
                                if (i != 912) {
                                    View inflate;
                                    if (i != 909) {
                                        if (i != 908) {
                                            if (i != 1 || !HorizontalRecyclerView.this.d || !ap.a().b(HorizontalRecyclerView.this.getContext())) {
                                                if (i != 913) {
                                                    if (i != 914) {
                                                        if (this.b != null) {
                                                            viewHolder = this.b.createViewHolder(null, viewGroup, HorizontalRecyclerView.this.e != 915 ? 910 : i, i);
                                                        }
                                                        if (viewHolder == null) {
                                                            if (this.e == VIEW_SIZE.RECENTLY_PLAYED_SMALL.getNumVal()) {
                                                                inflate = from.inflate(R.layout.item_playlist_grid_65x65, viewGroup, false);
                                                            } else {
                                                                inflate = from.inflate(R.layout.item_playlist_grid_140x140, viewGroup, false);
                                                            }
                                                            viewHolder = new PlaylistGridHolder(inflate);
                                                            break;
                                                        }
                                                    }
                                                    viewHolder = new PlaylistGridHolder(from.inflate(R.layout.item_playlist_grid_70x70, viewGroup, false));
                                                    break;
                                                }
                                                viewHolder = new PlaylistGridHolder(from.inflate(R.layout.view_item_similar_item, viewGroup, false));
                                                break;
                                            }
                                            viewHolder = new ItemAdViewHolder(from.inflate(R.layout.view_native_ad, viewGroup, false));
                                            break;
                                        }
                                        inflate = from.inflate(R.layout.download_disabled_gridview, viewGroup, false);
                                        viewHolder = new ItemAdViewHolder(inflate);
                                        HorizontalRecyclerView.this.setOnClickOnDownloadView(inflate);
                                        HorizontalRecyclerView.this.setTextViewBold((TextView) inflate.findViewById(R.id.txt_download));
                                        break;
                                    }
                                    inflate = from.inflate(R.layout.download_disabled_view, viewGroup, false);
                                    viewHolder = new ItemAdViewHolder(inflate);
                                    HorizontalRecyclerView.this.setOnClickOnDownloadView(inflate);
                                    HorizontalRecyclerView.this.setOnClickOnDownloadView(inflate.findViewById(R.id.cta_download));
                                    HorizontalRecyclerView.this.setTextViewBold((TextView) inflate.findViewById(R.id.txt_download));
                                    HorizontalRecyclerView.this.setTextViewBold((TextView) inflate.findViewById(R.id.cta_download));
                                    break;
                                } else if (this.b != null) {
                                    viewHolder = this.b.createViewHolder(null, viewGroup, 912, i);
                                    break;
                                }
                            }
                            viewHolder = new SocialHomeGridHolder(from.inflate(R.layout.home_social_feed, viewGroup, false));
                            break;
                        }
                        return this.b.createViewHolder(null, viewGroup, i, i);
                        break;
                    case 1:
                        viewHolder = new ItemAdViewHolder(from.inflate(R.layout.view_last_songs_playlist, viewGroup, false));
                        break;
                }
            }
            viewHolder = new CuratedDownloadSongSelectionHolder(from.inflate(R.layout.view_curated_download_scroll_item, viewGroup, false));
            if (this.b != null) {
                viewHolder = this.b.createViewHolder(viewHolder, viewGroup, i, i);
            }
            return viewHolder;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (this.b != null) {
                this.b.getCompatibleView(viewHolder.getItemViewType(), this.d, i, viewHolder);
            }
        }

        public int getItemCount() {
            return this.c;
        }

        public int getItemViewType(int i) {
            if (HorizontalRecyclerView.this.e == VIEW_SUBTYPE.SOCIAL.getNumVal() && getItemCount() > 1) {
                return 911;
            }
            if (HorizontalRecyclerView.this.e == VIEW_SUBTYPE.CHAMELEON.getNumVal() && HorizontalRecyclerView.this.a) {
                return 912;
            }
            if (HorizontalRecyclerView.this.e == VIEW_SUBTYPE.SEARCH_TRENDING.getNumVal()) {
                return 914;
            }
            if (HorizontalRecyclerView.this.e == 913) {
                return 913;
            }
            int i2 = 909;
            if (this.b instanceof a) {
                if (HorizontalRecyclerView.this.f && i == 0) {
                    if (getItemCount() > 1) {
                        i2 = 908;
                    }
                    return i2;
                } else if (i == this.d && HorizontalRecyclerView.this.d) {
                    return 1;
                } else {
                    return ((a) this.b).getItemViewType(i);
                }
            } else if (HorizontalRecyclerView.this.f && i == 0) {
                if (getItemCount() > 1) {
                    i2 = 908;
                }
                return i2;
            } else if (ap.a().b(HorizontalRecyclerView.this.getContext()) && i == this.d && HorizontalRecyclerView.this.d) {
                return 1;
            } else {
                return 0;
            }
        }

        public void b(int i) {
            this.c = i;
            if (HorizontalRecyclerView.this.d) {
                this.d = this.d >= i ? i - 1 : this.d;
            }
        }
    }

    public void a(int i) {
        if (this.b != null) {
            this.b.notifyItemChanged(i);
        }
    }

    public int getCurrentScrollX() {
        return this.i;
    }

    public void setCurrentScrollX(int i) {
        this.i = i;
    }

    public b a(Context context, int i) {
        if (this.b == null) {
            this.b = new b(context, i);
        }
        return this.b;
    }

    public b a(Context context, int i, int i2) {
        if (this.b == null) {
            this.b = new b(context, i, i2);
        } else {
            this.b.a(i2);
        }
        return this.b;
    }

    public void setViewSubType(int i) {
        this.e = i;
    }

    public void setViewRecycleListner(int i, int i2, boolean z, boolean z2, c cVar) {
        this.c = i;
        this.d = z;
        this.a = z2;
        if (this.b != null) {
            this.b.a(cVar);
        }
        setCount(i2);
    }

    public void setViewRecycleListner(int i, int i2, boolean z, c cVar) {
        setViewRecycleListner(i, i2, z, false, cVar);
    }

    public HorizontalRecyclerView(Context context) {
        super(context);
        a(context);
    }

    public HorizontalRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HorizontalRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setRecyclerViewPool(@NonNull RecycledViewPool recycledViewPool) {
        setRecycledViewPool(recycledViewPool);
    }

    private void a(Context context) {
        this.g = new LinearLayoutManager(context, 0, false);
        this.g.setItemPrefetchEnabled(true);
        this.h = context;
        setLayoutManager(this.g);
        setRecyclerListener(this.j);
    }

    public void setCount(int i) {
        if (this.b != null) {
            this.b.b(i);
            this.b.notifyDataSetChanged();
        }
    }

    public void a() {
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }

    private void setOnClickOnDownloadView(View view) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("My Downloads", "Add Downloads", "CuratedDownloadsPersonalized");
                    g.a(HorizontalRecyclerView.this.h, null, null);
                    u.a().b("CuratedDownloadsPersonalized", "PopUpView");
                }
            });
        }
    }

    public void setTextViewBold(TextView textView) {
        textView.setTypeface(textView.getTypeface(), 1);
    }

    public void a(boolean z) {
        this.f = z;
    }
}
