package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.platform.p;
import com.helpshift.conversation.c.c;
import com.helpshift.downloader.SupportDownloader.StorageDirType;
import com.helpshift.downloader.a;

public class AdminAttachmentMessageDM extends c {
    int a = 0;
    public AdminGenericAttachmentState b;

    public enum AdminGenericAttachmentState {
        DOWNLOAD_NOT_STARTED,
        DOWNLOADING,
        DOWNLOADED
    }

    public boolean a() {
        return true;
    }

    public AdminAttachmentMessageDM(String str, String str2, String str3, String str4, int i, String str5, String str6, String str7) {
        super(str2, str3, str4, i, str5, str6, str7, true, MessageType.ADMIN_ATTACHMENT);
        this.i = str;
        b();
    }

    public void b() {
        if (e() != null) {
            this.b = AdminGenericAttachmentState.DOWNLOADED;
        } else {
            this.b = AdminGenericAttachmentState.DOWNLOAD_NOT_STARTED;
        }
    }

    public String c() {
        if (this.b == AdminGenericAttachmentState.DOWNLOADING && this.a > 0) {
            double d = ((double) (this.f * this.a)) / 100.0d;
            if (d < ((double) this.f)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(a(d));
                stringBuilder.append("/");
                stringBuilder.append(super.c());
                return stringBuilder.toString();
            }
        }
        return super.c();
    }

    public boolean d() {
        return this.b == AdminGenericAttachmentState.DOWNLOAD_NOT_STARTED;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(AdminGenericAttachmentState adminGenericAttachmentState) {
        this.b = adminGenericAttachmentState;
        g();
    }

    public void a(final p pVar, final c cVar) {
        if (this.b == AdminGenericAttachmentState.DOWNLOADED) {
            if (cVar != null) {
                cVar.a(e(), this.c);
            }
        } else if (this.b == AdminGenericAttachmentState.DOWNLOAD_NOT_STARTED) {
            a(AdminGenericAttachmentState.DOWNLOADING);
            pVar.u().a(this.e, StorageDirType.EXTERNAL_ONLY, new a() {
                public void a(String str) {
                    AdminAttachmentMessageDM.this.a(AdminGenericAttachmentState.DOWNLOAD_NOT_STARTED);
                }

                public void a(String str, String str2) {
                    AdminAttachmentMessageDM.this.g = str2;
                    pVar.f().a(AdminAttachmentMessageDM.this);
                    AdminAttachmentMessageDM.this.a(AdminGenericAttachmentState.DOWNLOADED);
                    if (cVar != null) {
                        cVar.a(str2, AdminAttachmentMessageDM.this.c);
                    }
                }

                public void a(String str, int i) {
                    AdminAttachmentMessageDM.this.a = i;
                    AdminAttachmentMessageDM.this.g();
                }
            });
        }
    }

    public String e() {
        if (!a(this.g)) {
            this.g = null;
            this.b = AdminGenericAttachmentState.DOWNLOAD_NOT_STARTED;
        }
        return this.g;
    }
}
