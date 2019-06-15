package com.utilities;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.managers.PlayerManager;
import com.models.PlayerTrack;
import com.services.d;
import com.services.l.az;
import com.services.l.j;
import com.services.l.k;

public class e extends Callback {
    private final j a;
    private Paint b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = false;

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public e(j jVar) {
        this.a = jVar;
        this.b = new Paint();
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public boolean isItemViewSwipeEnabled() {
        return this.c;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (viewHolder instanceof az) {
            return Callback.makeMovementFlags(3, 12);
        }
        return Callback.makeMovementFlags(0, 0);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        if (!this.d && viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        this.a.onItemMove(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof az) {
            this.a.onItemDelete(viewHolder.getAdapterPosition(), i);
        }
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        Canvas canvas2 = canvas;
        ViewHolder viewHolder2 = viewHolder;
        float f3 = f;
        int i2 = i;
        if (i2 != 1) {
            super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        } else if (this.a instanceof k) {
            ((k) this.a).onChildDraw(canvas2, recyclerView, viewHolder2, f3, f2, i2, z);
        } else if (f3 <= 0.0f && viewHolder.getAdapterPosition() != -1 && recyclerView.getChildCount() > 1 && (viewHolder2 instanceof az)) {
            View view = viewHolder2.itemView;
            BusinessObject businessObject = (BusinessObject) viewHolder2.itemView.getTag();
            PlayerTrack i3 = PlayerManager.a(view.getContext()).i();
            if (i3 == null || !businessObject.getBusinessObjId().equals(i3.h())) {
                float bottom = (((float) view.getBottom()) - ((float) view.getTop())) / 3.0f;
                view.setTranslationX(f3);
                if (this.e) {
                    f3 = -((float) (d.a().b() / 3));
                }
                TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
                Drawable drawable;
                if (f3 > 0.0f) {
                    this.b.setColor(view.getResources().getColor(R.color.f17gaana.red));
                    canvas2.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f3, (float) view.getBottom()), this.b);
                    drawable = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(42, -1));
                    drawable.setBounds(new Rect((int) (((float) view.getLeft()) + bottom), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getLeft()) + (2.0f * bottom)), (int) (((float) view.getBottom()) - bottom)));
                    drawable.draw(canvas2);
                } else {
                    this.b.setColor(view.getResources().getColor(R.color.f17gaana.red));
                    canvas2.drawRect(new RectF(((float) view.getRight()) + f3, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom()), this.b);
                    drawable = ContextCompat.getDrawable(view.getContext(), obtainStyledAttributes.getResourceId(42, -1));
                    drawable.setBounds(new Rect((int) (((float) view.getRight()) - (2.0f * bottom)), (int) (((float) view.getTop()) + bottom), (int) (((float) view.getRight()) - bottom), (int) (((float) view.getBottom()) - bottom)));
                    drawable.draw(canvas2);
                }
            }
        }
    }

    public float getSwipeThreshold(ViewHolder viewHolder) {
        if (this.a instanceof k) {
            return ((k) this.a).getSwipeThreshold(viewHolder);
        }
        return super.getSwipeThreshold(viewHolder);
    }

    public float getSwipeVelocityThreshold(float f) {
        if (this.a instanceof k) {
            return ((k) this.a).getSwipeVelocityThreshold(f);
        }
        return super.getSwipeVelocityThreshold(f);
    }

    public void onSelectedChanged(ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof az)) {
            ((az) viewHolder).onItemSelected();
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(1.0f);
        if (viewHolder instanceof az) {
            ((az) viewHolder).onItemClear(viewHolder.getAdapterPosition());
            this.a.onComplete(viewHolder.getAdapterPosition());
        }
    }

    public boolean canDropOver(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        return super.canDropOver(recyclerView, viewHolder, viewHolder2);
    }

    public void c(boolean z) {
        this.e = z;
    }
}
