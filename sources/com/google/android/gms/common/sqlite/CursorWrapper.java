package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzez;

    @KeepForSdk
    public CursorWrapper(Cursor cursor) {
        Object cursor2;
        super(cursor2);
        for (int i = 0; i < 10 && (cursor2 instanceof android.database.CursorWrapper); i++) {
            cursor2 = ((android.database.CursorWrapper) cursor2).getWrappedCursor();
        }
        if (cursor2 instanceof AbstractWindowedCursor) {
            this.zzez = (AbstractWindowedCursor) cursor2;
            return;
        }
        String str = "Unknown type: ";
        String valueOf = String.valueOf(cursor2.getClass().getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zzez.setWindow(cursorWindow);
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzez.fillWindow(i, cursorWindow);
    }

    public boolean onMove(int i, int i2) {
        return this.zzez.onMove(i, i2);
    }

    public /* synthetic */ Cursor getWrappedCursor() {
        return this.zzez;
    }
}
