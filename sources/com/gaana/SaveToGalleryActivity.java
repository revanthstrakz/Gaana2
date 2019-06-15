package com.gaana;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.utilities.h;

public class SaveToGalleryActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_CONTENT = "EXTRA_KEY_CONTENT";
    public String uri = "";

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.empty_layout);
        this.uri = getIntent().getExtras().getString(EXTRA_KEY_CONTENT);
        if (h.e(this)) {
            addImageToGallery(this.uri, this);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length <= 0 || iArr[0] != 0) {
            showErrorToastAndFinish();
        } else if (i == 102) {
            addImageToGallery(this.uri, this);
        }
    }

    public void onCancelClickedOnRationalDialog() {
        showErrorToastAndFinish();
    }

    public void showErrorToastAndFinish() {
        Toast.makeText(this, getString(R.string.enable_storage_permission), 0).show();
        finish();
    }

    public void addImageToGallery(String str, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("_data", str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Gaana Poster ");
        stringBuilder.append(System.currentTimeMillis());
        contentValues.put("_display_name", stringBuilder.toString());
        context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
        Toast.makeText(this, R.string.save_gallery_success, 0).show();
        ((AppCompatActivity) context).finish();
    }
}
