package com.actionbar;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.gaana.R;

public class PlayerQueueActionBar extends LinearLayout implements OnClickListener {
    private a a;

    public interface a {
        void onBackClicked();

        void onItemClicked(int i);

        void onMenuClicked(View view);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.menu_add_playlist) {
            if (id != R.id.menu_icon) {
                if (id == R.id.menu_option && this.a != null) {
                    this.a.onMenuClicked(view);
                }
            } else if (this.a != null) {
                this.a.onBackClicked();
            }
        } else if (this.a != null) {
            this.a.onItemClicked(R.id.menu_add_playlist);
        }
    }
}
