package com.gaana.view.item;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.SmartDownloadsData;
import com.gaana.models.Tracks.Track;
import com.i.i;
import com.library.controls.CircularImageView;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.u;
import com.services.l.af;
import com.views.CustomButtonView;
import com.views.CustomCircularProgressBar;

public class SmartDownloadNotificationView extends BottomSheetDialog {
    private int animationSpeedFactor = 5;
    private BottomSheetDialog bottomSheetDialog;
    private CustomButtonView btn_view;
    CustomCircularProgressBar circularProgressBar = null;
    private Dialog dialogSmartDownload;
    private TextView download_status;
    String downloaded_track_id = null;
    private Context mContext;
    private View mView;
    String notificationType = "";
    int progressCount = 0;
    private TextView progress_percentage;
    private SeekBar seekBar;
    SmartDownloadsData smartDownloadsData;
    private TextView what_is_this;

    public SmartDownloadNotificationView(Context context) {
        super(context);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout.smart_download_notification, null);
        setContentView(this.mView);
    }

    public void show() {
        u.a().a("Smart Download", "Impression", "");
        initUI();
        super.show();
    }

    public void setNotificationType(String str) {
        this.notificationType = str;
    }

    public void setTrackId(String str) {
        this.downloaded_track_id = str;
    }

    private void initUI() {
        this.bottomSheetDialog = this;
        this.seekBar = (SeekBar) this.mView.findViewById(R.id.seekBar);
        this.progress_percentage = (TextView) this.mView.findViewById(R.id.progress_percentage);
        this.download_status = (TextView) this.mView.findViewById(R.id.download_status);
        ((TextView) this.mView.findViewById(R.id.title)).setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        this.btn_view = (CustomButtonView) this.mView.findViewById(R.id.btn_view);
        this.btn_view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SmartDownloadNotificationView.this.bottomSheetDialog != null) {
                    SmartDownloadNotificationView.this.bottomSheetDialog.dismiss();
                }
                u.a().a("Smart Download", "View", "Tracks");
                ((GaanaActivity) SmartDownloadNotificationView.this.mContext).displayDownload(R.id.my_downloads, "0", null, SortOrder.DownloadTime, "smart_download");
            }
        });
        this.what_is_this = (TextView) this.mView.findViewById(R.id.whatsThis);
        this.seekBar.setThumbOffset(10000);
        this.what_is_this.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SmartDownloadNotificationView.this.showWhatisThis_Popup();
            }
        });
        if (this.notificationType.equalsIgnoreCase("repeat")) {
            if (!TextUtils.isEmpty(this.downloaded_track_id)) {
                this.mView.findViewById(R.id.song_download_details).setVisibility(0);
                this.btn_view.setVisibility(8);
                this.mView.findViewById(R.id.download_progress).setVisibility(8);
                setRepeatDownloadsMessages();
                Track track = (Track) DownloadManager.c().a(this.downloaded_track_id, true);
                if (track != null) {
                    setCircularProgress(track);
                }
            }
        } else if (this.notificationType.equalsIgnoreCase("smart_downloads")) {
            this.btn_view.setVisibility(0);
            this.mView.findViewById(R.id.song_download_details).setVisibility(8);
            this.mView.findViewById(R.id.download_progress).setVisibility(0);
            setSmartDownloadSongsMessages();
            disableViewButton();
        }
        showDownloadProgress();
    }

    private void showWhatisThis_Popup() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.smart_download_toast, null);
        TextView textView = (TextView) inflate.findViewById(R.id.action_button);
        if (this.notificationType.equalsIgnoreCase("smart_downloads")) {
            if (!TextUtils.isEmpty(Constants.bK)) {
                textView.setText(Constants.bK.toUpperCase());
            }
            if (!TextUtils.isEmpty(Constants.bL)) {
                ((TextView) inflate.findViewById(R.id.description)).setText(Constants.bL);
            }
        } else if (this.notificationType.equalsIgnoreCase("repeat") && this.smartDownloadsData != null) {
            if (this.smartDownloadsData.getSDKeys().containsKey("Snackbar_CTA")) {
                textView.setText(((String) this.smartDownloadsData.getSDKeys().get("Snackbar_CTA")).toUpperCase());
            }
            if (this.smartDownloadsData.getSDKeys().containsKey("Snackbar_text")) {
                ((TextView) inflate.findViewById(R.id.description)).setText((CharSequence) this.smartDownloadsData.getSDKeys().get("Snackbar_text"));
            }
        }
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SmartDownloadNotificationView.this.dialogSmartDownload.dismiss();
            }
        });
        this.dialogSmartDownload = new Dialog(this.mContext, R.style.DialogCustomTheme);
        this.dialogSmartDownload.setContentView(inflate);
        Window window = this.dialogSmartDownload.getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setGravity(80);
        window.getAttributes().y = 100;
        this.dialogSmartDownload.show();
    }

    private void setCircularProgress(Track track) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.smart_download_song_progress_view, null);
        ((CircularImageView) inflate.findViewById(R.id.song_image)).bindImage(track.getArtwork());
        this.circularProgressBar = (CustomCircularProgressBar) this.mView.findViewById(R.id.circular_download_progress);
        this.circularProgressBar.setVisibility(0);
        inflate.setVisibility(0);
        this.circularProgressBar.setExtraView(inflate);
        this.circularProgressBar.getCircularProgressBar().setStrokeWidth(this.mContext.getResources().getDimension(R.dimen.dp5) * 4.0f);
        TextView textView = (TextView) this.mView.findViewById(R.id.song_title);
        textView.setText(track.getTrackTitle());
        textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        ((TextView) this.mView.findViewById(R.id.song_sub_title)).setText(track.getAlbumTitle());
    }

    private void setRepeatDownloadsMessages() {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://apiv2.gaana.com/smart-download/details?token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        stringBuilder.append("&smart_op=sd_repeat");
        uRLManager.a(stringBuilder.toString());
        uRLManager.a(SmartDownloadsData.class);
        uRLManager.b(Boolean.valueOf(true));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                SmartDownloadNotificationView.this.smartDownloadsData = (SmartDownloadsData) obj;
                SmartDownloadNotificationView.this.setBottomSheetView_Messages();
            }
        }, uRLManager);
    }

    private void setBottomSheetView_Messages() {
        if (this.smartDownloadsData != null) {
            if (!TextUtils.isEmpty(this.smartDownloadsData.getEntityDescription())) {
                ((TextView) this.mView.findViewById(R.id.downloaded_songs_message)).setText(this.smartDownloadsData.getEntityDescription());
            }
            if (this.smartDownloadsData.getSDKeys().containsKey("Title")) {
                ((TextView) this.mView.findViewById(R.id.title)).setText((CharSequence) this.smartDownloadsData.getSDKeys().get("Title"));
            }
            if (this.smartDownloadsData.getSDKeys().containsKey("CTA")) {
                ((CustomButtonView) this.mView.findViewById(R.id.btn_view)).setText((CharSequence) this.smartDownloadsData.getSDKeys().get("CTA"));
            }
        }
    }

    private void setSmartDownloadSongsMessages() {
        if (!TextUtils.isEmpty(Constants.bG)) {
            ((TextView) this.mView.findViewById(R.id.downloaded_songs_message)).setText(Constants.bG);
        }
        if (!TextUtils.isEmpty(Constants.bJ)) {
            ((TextView) this.mView.findViewById(R.id.title)).setText(Constants.bJ);
        }
        if (!TextUtils.isEmpty(Constants.bI)) {
            ((CustomButtonView) this.mView.findViewById(R.id.btn_view)).setText(Constants.bI);
        }
    }

    private void showDownloadProgress() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private long time = 0;

            public void run() {
                if (SmartDownloadNotificationView.this.progressCount < 100) {
                    this.time += 1000;
                    SmartDownloadNotificationView smartDownloadNotificationView = SmartDownloadNotificationView.this;
                    smartDownloadNotificationView.progressCount++;
                    if (SmartDownloadNotificationView.this.notificationType.equalsIgnoreCase("repeat")) {
                        if (SmartDownloadNotificationView.this.circularProgressBar != null) {
                            SmartDownloadNotificationView.this.circularProgressBar.setProgress(SmartDownloadNotificationView.this.progressCount);
                        }
                        if (SmartDownloadNotificationView.this.progressCount == 100 && SmartDownloadNotificationView.this.circularProgressBar != null) {
                            SmartDownloadNotificationView.this.circularProgressBar.getExtraView().findViewById(R.id.smart_downloaded).setVisibility(0);
                        }
                    } else if (SmartDownloadNotificationView.this.notificationType.equalsIgnoreCase("smart_downloads")) {
                        SmartDownloadNotificationView.this.seekBar.setProgress(SmartDownloadNotificationView.this.progressCount);
                        if (SmartDownloadNotificationView.this.progressCount == 100) {
                            SmartDownloadNotificationView.this.download_status.setText(SmartDownloadNotificationView.this.mContext.getResources().getString(R.string.downloaded));
                            SmartDownloadNotificationView.this.enableViewButton();
                        }
                        TextView access$800 = SmartDownloadNotificationView.this.progress_percentage;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(String.valueOf(SmartDownloadNotificationView.this.progressCount));
                        stringBuilder.append("%");
                        access$800.setText(stringBuilder.toString());
                    }
                    handler.postDelayed(this, (long) SmartDownloadNotificationView.this.animationSpeedFactor);
                }
            }
        }, 0);
    }

    private void enableViewButton() {
        this.btn_view.setEnabled(true);
        this.btn_view.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.shape_continue_btn));
    }

    private void disableViewButton() {
        this.btn_view.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.shape_continue_disabled));
    }
}
