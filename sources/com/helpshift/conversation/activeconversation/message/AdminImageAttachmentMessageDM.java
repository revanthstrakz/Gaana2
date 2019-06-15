package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.platform.p;
import com.helpshift.conversation.c.c;
import com.helpshift.downloader.SupportDownloader.StorageDirType;
import com.helpshift.downloader.a;

public class AdminImageAttachmentMessageDM extends i {
    public AdminImageAttachmentState a;

    public enum AdminImageAttachmentState {
        DOWNLOAD_NOT_STARTED,
        THUMBNAIL_DOWNLOADING,
        THUMBNAIL_DOWNLOADED,
        IMAGE_DOWNLOADING,
        IMAGE_DOWNLOADED
    }

    public boolean a() {
        return true;
    }

    public AdminImageAttachmentMessageDM(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        super(str2, str3, str4, str5, str6, str7, str8, i, true, MessageType.ADMIN_IMAGE_ATTACHMENT);
        this.i = str;
        b();
    }

    public void b() {
        if (e() != null) {
            this.a = AdminImageAttachmentState.IMAGE_DOWNLOADED;
        } else if (d() != null) {
            this.a = AdminImageAttachmentState.THUMBNAIL_DOWNLOADED;
        } else {
            this.a = AdminImageAttachmentState.DOWNLOAD_NOT_STARTED;
        }
    }

    public String d() {
        if (!a(this.h)) {
            this.a = AdminImageAttachmentState.DOWNLOAD_NOT_STARTED;
            this.h = null;
        }
        return this.h;
    }

    public String e() {
        if (!a(this.g)) {
            if (d() != null) {
                this.a = AdminImageAttachmentState.THUMBNAIL_DOWNLOADED;
            }
            this.g = null;
        }
        return this.g;
    }

    public void a(AdminImageAttachmentState adminImageAttachmentState) {
        this.a = adminImageAttachmentState;
        g();
    }

    public void a(final p pVar) {
        if (this.a == AdminImageAttachmentState.DOWNLOAD_NOT_STARTED) {
            a(AdminImageAttachmentState.THUMBNAIL_DOWNLOADING);
            pVar.u().a(this.b, StorageDirType.EXTERNAL_OR_INTERNAL, new a() {
                public void a(String str, int i) {
                }

                public void a(String str) {
                    AdminImageAttachmentMessageDM.this.a(AdminImageAttachmentState.DOWNLOAD_NOT_STARTED);
                }

                public void a(String str, String str2) {
                    AdminImageAttachmentMessageDM.this.h = str2;
                    pVar.f().a(AdminImageAttachmentMessageDM.this);
                    AdminImageAttachmentMessageDM.this.a(AdminImageAttachmentState.THUMBNAIL_DOWNLOADED);
                }
            });
        }
    }

    public void a(final p pVar, final c cVar) {
        if (this.a == AdminImageAttachmentState.IMAGE_DOWNLOADED) {
            if (cVar != null) {
                cVar.a(e(), this.c);
            }
        } else if (this.a == AdminImageAttachmentState.THUMBNAIL_DOWNLOADED) {
            a(AdminImageAttachmentState.IMAGE_DOWNLOADING);
            pVar.u().a(this.e, StorageDirType.EXTERNAL_ONLY, new a() {
                public void a(String str, int i) {
                }

                public void a(String str) {
                    AdminImageAttachmentMessageDM.this.a(AdminImageAttachmentState.THUMBNAIL_DOWNLOADED);
                }

                public void a(String str, String str2) {
                    AdminImageAttachmentMessageDM.this.g = str2;
                    pVar.f().a(AdminImageAttachmentMessageDM.this);
                    AdminImageAttachmentMessageDM.this.a(AdminImageAttachmentState.IMAGE_DOWNLOADED);
                    if (cVar != null) {
                        cVar.a(str2, AdminImageAttachmentMessageDM.this.c);
                    }
                }
            });
        }
    }
}
