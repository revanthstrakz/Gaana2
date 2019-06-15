package com.gaana.view.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.juke.JukeTrack;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.library.controls.CrossFadeImageView;
import com.logging.d;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.u;
import com.models.PlayerTrack;
import com.services.l.ao;
import com.services.l.az;
import com.services.l.s;
import com.utilities.Util;

public class VotingSongsItemView extends SongsItemView {
    private ao mDragListener;
    private BusinessObject mParentBusinessObject;
    private int mSessionType;

    public static class VotingSongsItemViewHolder extends ViewHolder implements az {
        public ImageView mImgHolder;
        public ImageView mImgMoreOption;
        public CrossFadeImageView mImgProductIcon;
        public ImageView mImgVote;
        public final TextView mNowPlaying;
        public ImageView mPlayerIcon;
        public TextView mSubtitleText;
        public TextView mTitleText;
        public TextView mTxtVoteCount;
        public final TextView mUpNext;

        public void onItemClear(int i) {
        }

        public void onItemSelected() {
        }

        public VotingSongsItemViewHolder(View view) {
            super(view);
            this.mTitleText = (TextView) view.findViewById(R.id.tvSongName);
            this.mSubtitleText = (TextView) view.findViewById(R.id.tvAlbumName);
            this.mImgProductIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.mImgHolder = (ImageView) view.findViewById(R.id.img_edit_holder);
            this.mImgVote = (ImageView) view.findViewById(R.id.img_vote);
            this.mImgMoreOption = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.mTxtVoteCount = (TextView) view.findViewById(R.id.txt_vote_count);
            this.mPlayerIcon = (ImageView) view.findViewById(R.id.img_player);
            this.mNowPlaying = (TextView) view.findViewById(R.id.view_vote_now_playing);
            this.mUpNext = (TextView) view.findViewById(R.id.view_vote_now_next);
        }
    }

    public void setSessionType(int i) {
        this.mSessionType = i;
    }

    public void setParentBusinessObject(BusinessObject businessObject) {
        this.mParentBusinessObject = businessObject;
    }

    public void setDragListener(ao aoVar) {
        this.mDragListener = aoVar;
    }

    public VotingSongsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mSessionType = 2;
        this.mLayoutId = R.layout.view_voting_songs_item;
        ((BaseActivity) this.mContext).currentItem = "Song";
        setWillNotDraw(false);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        super.getPoplatedView(viewHolder.itemView, businessObject);
        final VotingSongsItemViewHolder votingSongsItemViewHolder = (VotingSongsItemViewHolder) viewHolder;
        int i = 0;
        if (Constants.cY && votingSongsItemViewHolder.getAdapterPosition() == 1) {
            votingSongsItemViewHolder.itemView.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half), 0, 0);
        } else {
            votingSongsItemViewHolder.itemView.setPadding(0, 0, 0, 0);
        }
        if (businessObject instanceof JukeTrack) {
            JukeTrack jukeTrack = (JukeTrack) businessObject;
            votingSongsItemViewHolder.mTitleText.setText(jukeTrack.getName());
            votingSongsItemViewHolder.mSubtitleText.setText(getSubtitleText(jukeTrack));
            votingSongsItemViewHolder.mPlayerIcon.setTag(jukeTrack);
            votingSongsItemViewHolder.mPlayerIcon.setOnClickListener(this);
            votingSongsItemViewHolder.mImgHolder.setVisibility(isReOrderingAllowed() ? 0 : 8);
            if (this.mSessionType != 0) {
                votingSongsItemViewHolder.mImgVote.setOnClickListener(this);
                votingSongsItemViewHolder.mImgVote.setTag(jukeTrack);
                votingSongsItemViewHolder.mImgVote.setTag(R.id.tg_view, votingSongsItemViewHolder.mTxtVoteCount);
                votingSongsItemViewHolder.mImgVote.setTag(R.id.tg_view_player, votingSongsItemViewHolder.mPlayerIcon);
                votingSongsItemViewHolder.mTxtVoteCount.setOnClickListener(this);
                votingSongsItemViewHolder.mTxtVoteCount.setTag(jukeTrack);
                votingSongsItemViewHolder.mTxtVoteCount.setTag(R.id.tg_view, votingSongsItemViewHolder.mImgVote);
                votingSongsItemViewHolder.mTxtVoteCount.setTag(R.id.tg_view_player, votingSongsItemViewHolder.mPlayerIcon);
                votingSongsItemViewHolder.mImgVote.setVisibility(0);
                votingSongsItemViewHolder.mTxtVoteCount.setVisibility(0);
                toggleIcon(votingSongsItemViewHolder.mTxtVoteCount, votingSongsItemViewHolder.mImgVote, votingSongsItemViewHolder.mPlayerIcon, jukeTrack, false);
            } else {
                votingSongsItemViewHolder.mTxtVoteCount.setVisibility(8);
                votingSongsItemViewHolder.mImgVote.setVisibility(8);
                votingSongsItemViewHolder.mPlayerIcon.setVisibility(8);
            }
        } else if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            votingSongsItemViewHolder.mTitleText.setText(businessObject.getName());
            votingSongsItemViewHolder.mSubtitleText.setText(getSubtitleText(track));
            ImageView imageView = votingSongsItemViewHolder.mImgHolder;
            if (!isReOrderingAllowed()) {
                i = 8;
            }
            imageView.setVisibility(i);
            votingSongsItemViewHolder.mImgVote.setVisibility(8);
        }
        votingSongsItemViewHolder.mImgMoreOption.setOnClickListener(this);
        votingSongsItemViewHolder.mImgMoreOption.setTag(businessObject);
        votingSongsItemViewHolder.mImgHolder.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() == 0 && VotingSongsItemView.this.mDragListener != null) {
                    VotingSongsItemView.this.mDragListener.onStartDrag(votingSongsItemViewHolder);
                }
                return false;
            }
        });
        votingSongsItemViewHolder.mImgProductIcon.bindImage(businessObject.getAtw());
        return viewHolder.itemView;
    }

    @NonNull
    private String getSubtitleText(JukeTrack jukeTrack) {
        if (TextUtils.isEmpty(jukeTrack.getAddedBy())) {
            return !TextUtils.isEmpty(jukeTrack.getSubtitleText()) ? jukeTrack.getSubtitleText() : "";
        } else {
            return String.format(this.mContext.getResources().getString(R.string.added_by), new Object[]{jukeTrack.getAddedBy()});
        }
    }

    @NonNull
    private String getSubtitleText(Track track) {
        String albumTitle = track.getAlbumTitle();
        String artistNames = track.getArtistNames();
        if (!TextUtils.isEmpty(albumTitle) && !TextUtils.isEmpty(artistNames)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(albumTitle);
            stringBuilder.append(" - ");
            stringBuilder.append(artistNames);
            return stringBuilder.toString();
        } else if (TextUtils.isEmpty(albumTitle) && TextUtils.isEmpty(artistNames)) {
            return "";
        } else {
            return TextUtils.isEmpty(albumTitle) ? artistNames : albumTitle;
        }
    }

    public void onClick(View view) {
        ImageView imageView;
        JukeTrack jukeTrack;
        TextView textView;
        ImageView imageView2;
        if (view.getId() == R.id.clickoptionImage) {
            u.a().b("PartyHub", "Song_3Dot");
            showOptionMenu(view);
        } else if (view.getId() == R.id.img_vote) {
            imageView = (ImageView) view;
            jukeTrack = (JukeTrack) view.getTag();
            textView = (TextView) view.getTag(R.id.tg_view);
            imageView2 = (ImageView) view.getTag(R.id.tg_view_player);
            jukeTrack.setVotedBy(jukeTrack.getVotedBy() ^ 1);
            toggleIcon(textView, imageView, imageView2, jukeTrack, true);
        } else if (view.getId() == R.id.txt_vote_count) {
            textView = (TextView) view;
            jukeTrack = (JukeTrack) view.getTag();
            imageView = (ImageView) view.getTag(R.id.tg_view);
            imageView2 = (ImageView) view.getTag(R.id.tg_view_player);
            jukeTrack.setVotedBy(jukeTrack.getVotedBy() ^ 1);
            toggleIcon(textView, imageView, imageView2, jukeTrack, true);
        } else if (view.getId() == R.id.img_player) {
            JukeTrack jukeTrack2 = (JukeTrack) view.getTag();
            if (jukeTrack2 != null && jukeTrack2.getPlayStatus() == 2) {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.song_added_to_queue));
                JukeSessionManager.getInstance().addDeleteTracks((JukePlaylist) this.mParentBusinessObject, jukeTrack2.getBusinessObjId(), true);
                if (this.mFragment instanceof JukePartyFragment) {
                    ((JukePartyFragment) this.mFragment).onItemSetToLast(jukeTrack2);
                }
            }
        } else {
            BusinessObject d = Util.d((BusinessObject) view.getTag());
            if (!d.isLocalMedia() && Util.c(d)) {
                d = DownloadManager.c().a(d.getBusinessObjType(), d.getBusinessObjId());
                if (JukeSessionManager.getInstance().isCurrentJukeSession(this.mParentBusinessObject)) {
                    u.a().a("PartyHub", "Song_Play", ((JukePlaylist) this.mParentBusinessObject).getAdmin() ? "Admin" : "Joinee");
                    PlayerTrack a = d.a().a(null, d, false);
                    if (this.mParentBusinessObject instanceof JukePlaylist) {
                        JukePlaylist jukePlaylist = (JukePlaylist) this.mParentBusinessObject;
                        a.f("PARTY");
                        a.d(jukePlaylist.getPartySource());
                        a.c(jukePlaylist.getSourcePlayListId());
                    } else {
                        a.f("PARTY");
                        a.d("PARTY");
                        a.c("PARTY");
                    }
                    JukeSessionManager.getInstance().getJukeQueueManager().setupPlayer(a);
                } else if (d != null) {
                    View view2 = new View(this.mContext);
                    view2.setTag(d);
                    super.onClick(view2);
                }
            } else if (!d.isLocalMedia()) {
                u.a().a("PartyHub", "Song_Play", ((JukePlaylist) this.mParentBusinessObject).getAdmin() ? "Admin" : "Joinee");
                Util.a(this.mContext, this.mFragment, d, false, new s() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(BusinessObject businessObject) {
                        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                            if (JukeSessionManager.getInstance().isCurrentJukeSession(VotingSongsItemView.this.mParentBusinessObject)) {
                                PlayerTrack a = d.a().a(null, businessObject.getArrListBusinessObj() != null ? (Track) businessObject.getArrListBusinessObj().get(0) : null, false);
                                if (VotingSongsItemView.this.mParentBusinessObject instanceof JukePlaylist) {
                                    JukePlaylist jukePlaylist = (JukePlaylist) VotingSongsItemView.this.mParentBusinessObject;
                                    a.f("PARTY");
                                    a.d(jukePlaylist.getPartySource());
                                    a.c(jukePlaylist.getSourcePlayListId());
                                } else {
                                    a.f("PARTY");
                                    a.d("PARTY");
                                    a.c("PARTY");
                                }
                                JukeSessionManager.getInstance().getJukeQueueManager().setupPlayer(a);
                            } else if (businessObject.getArrListBusinessObj().get(0) != null) {
                                View view = new View(VotingSongsItemView.this.mContext);
                                view.setTag(businessObject.getArrListBusinessObj().get(0));
                                super.onClick(view);
                            }
                        }
                    }
                });
            }
        }
    }

    private boolean isCurrentJukeSession() {
        return Constants.cY && (JukeSessionManager.getInstance().getJukeSessionPlaylist() != null ? JukeSessionManager.getInstance().getJukeSessionPlaylist().getBusinessObjId() : "").equals(this.mParentBusinessObject.getBusinessObjId());
    }

    private void toggleIcon(TextView textView, ImageView imageView, ImageView imageView2, JukeTrack jukeTrack, boolean z) {
        long voteCount = z ? jukeTrack.getVotedBy() ? jukeTrack.getVoteCount() + 1 : jukeTrack.getVoteCount() - 1 : jukeTrack.getVoteCount();
        jukeTrack.setVoteCount(voteCount);
        int i = ((this.mParentBusinessObject instanceof JukePlaylist) && ((JukePlaylist) this.mParentBusinessObject).getPlStatus() == 2 && jukeTrack.getPlayStatus() == 1) ? 1 : 0;
        if (i == 0) {
            if (jukeTrack.getVotedBy()) {
                imageView.setImageResource(R.drawable.vector_ic_up_vote_selected);
            } else if (Constants.l) {
                imageView.setImageResource(R.drawable.vector_ic_up_vote_white);
            } else {
                imageView.setImageResource(R.drawable.vector_ic_up_vote);
            }
            textView.setText(String.valueOf(voteCount));
            imageView2.setVisibility(4);
        } else {
            textView.setVisibility(4);
            imageView.setVisibility(4);
            imageView2.setVisibility(0);
        }
        if (z) {
            u.a().a("PartyHub", jukeTrack.getVotedBy() ? "Upvote" : "Upvote_Remove", ((JukePlaylist) this.mParentBusinessObject).getAdmin() ? "Admin" : "Joinee");
            JukeSessionManager.getInstance().addVote((JukePlaylist) this.mParentBusinessObject, jukeTrack.getBusinessObjId(), jukeTrack.getVotedBy());
        }
    }

    /* Access modifiers changed, original: protected */
    public void showOptionMenu(View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if (businessObject instanceof Track) {
            PopupWindowView.getInstance(this.mContext, this.mFragment).contextPopupWindow(businessObject, false, false);
            return;
        }
        businessObject = Util.d(businessObject);
        if (!businessObject.isLocalMedia() && Util.c(businessObject)) {
            businessObject = DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId());
            PopupWindowView instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
            instance.setDownloadPopupListener(new DownloadPopupListener() {
                public void onPopupClicked(String str, BusinessObject businessObject) {
                    if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
                        VotingSongsItemView.this.deleteDownload(businessObject);
                    } else {
                        VotingSongsItemView.this.startDownload(businessObject);
                    }
                }
            });
            instance.contextPopupWindow(businessObject, false, false);
        } else if (!businessObject.isLocalMedia()) {
            Util.a(this.mContext, this.mFragment, businessObject, false, new DownloadPopupListener() {
                public void onPopupClicked(String str, BusinessObject businessObject) {
                    if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
                        VotingSongsItemView.this.deleteDownload(businessObject);
                    } else {
                        VotingSongsItemView.this.startDownload(businessObject);
                    }
                }
            });
        }
    }

    private boolean isReOrderingAllowed() {
        return this.mSessionType == 0 || ((this.mParentBusinessObject instanceof JukePlaylist) && !((JukePlaylist) this.mParentBusinessObject).getVoteEnable() && ((JukePlaylist) this.mParentBusinessObject).getAdmin());
    }

    private BusinessObject convertToBusinessObject(BusinessObject businessObject) {
        Track track = new Track();
        track.setBusinessObjId(businessObject.getBusinessObjId());
        track.setName(businessObject.getRawName());
        track.setBusinessObjType(BusinessObjectType.Tracks);
        return track;
    }
}
