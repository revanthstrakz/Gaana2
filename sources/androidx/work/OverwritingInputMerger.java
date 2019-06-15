package androidx.work;

import android.support.annotation.NonNull;
import androidx.work.d.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OverwritingInputMerger extends e {
    @NonNull
    public d a(@NonNull List<d> list) {
        a aVar = new a();
        Map hashMap = new HashMap();
        for (d a : list) {
            hashMap.putAll(a.a());
        }
        aVar.a(hashMap);
        return aVar.a();
    }
}
