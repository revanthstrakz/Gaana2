package io.branch.referral;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import io.branch.referral.Branch.i;

class ShareLinkManager {
    private static int b = 100;
    a a;
    private i c;

    private class CopyLinkItem extends ResolveInfo {
        final /* synthetic */ ShareLinkManager a;

        public CharSequence loadLabel(PackageManager packageManager) {
            return this.a.c.d();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return this.a.c.c();
        }
    }

    private class MoreShareItem extends ResolveInfo {
        final /* synthetic */ ShareLinkManager a;

        public CharSequence loadLabel(PackageManager packageManager) {
            return this.a.c.b();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return this.a.c.a();
        }
    }

    public void a(boolean z) {
        if (this.a != null && this.a.isShowing()) {
            if (z) {
                this.a.cancel();
            } else {
                this.a.dismiss();
            }
        }
    }
}
