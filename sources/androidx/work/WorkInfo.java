package androidx.work;

import android.support.annotation.NonNull;
import java.util.Set;
import java.util.UUID;

public final class WorkInfo {
    @NonNull
    private UUID a;
    @NonNull
    private State b;
    @NonNull
    private d c;
    @NonNull
    private Set<String> d;

    public enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        public boolean isFinished() {
            return this == SUCCEEDED || this == FAILED || this == CANCELLED;
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) obj;
        if (!this.a == null ? this.a.equals(workInfo.a) : workInfo.a == null) {
            return false;
        }
        if (this.b != workInfo.b) {
            return false;
        }
        if (!this.c == null ? this.c.equals(workInfo.c) : workInfo.c == null) {
            return false;
        }
        if (this.d != null) {
            z = this.d.equals(workInfo.d);
        } else if (workInfo.d != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * (((((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0));
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WorkInfo{mId='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", mState=");
        stringBuilder.append(this.b);
        stringBuilder.append(", mOutputData=");
        stringBuilder.append(this.c);
        stringBuilder.append(", mTags=");
        stringBuilder.append(this.d);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
