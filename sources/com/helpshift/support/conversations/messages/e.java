package com.helpshift.support.conversations.messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.helpshift.e.f;
import com.helpshift.e.h;

public class e {
    private Context a;

    e(Context context) {
        this.a = context;
    }

    public ViewHolder a(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.a).inflate(h.hs__msg_agent_typing, viewGroup, false);
        com.helpshift.support.util.h.a(this.a, inflate.findViewById(f.agent_typing_container).getBackground());
        return new ViewHolder(inflate) {
        };
    }
}
