package com.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import com.gaana.R;
import java.util.ArrayList;

public class VoiceRecognition implements RecognitionListener {
    private final Context a;
    private SpeechRecognizer b;
    private Intent c;
    private boolean d;
    private a e;

    public interface a {
        void a(VoiceCommand voiceCommand, String str);

        void a(ArrayList<String> arrayList);
    }

    public enum VoiceCommand {
        ERROR,
        LISTENING_STARTED,
        SPEECH_STARTED,
        SPEECH_ENDED,
        RESULT
    }

    public void onBufferReceived(byte[] bArr) {
    }

    public void onEvent(int i, Bundle bundle) {
    }

    public void onPartialResults(Bundle bundle) {
    }

    public void onRmsChanged(float f) {
    }

    public VoiceRecognition(Context context) {
        this.a = context;
        e();
        f();
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    private void e() {
        this.c = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.c.putExtra("android.speech.extra.LANGUAGE_PREFERENCE", "en");
        this.c.putExtra("calling_package", this.a.getPackageName());
        this.c.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        this.c.putExtra("android.speech.extra.LANGUAGE", "en-US");
        this.c.putExtra("android.speech.extra.MAX_RESULTS", 3);
    }

    private void f() {
        this.b = SpeechRecognizer.createSpeechRecognizer(this.a, ComponentName.unflattenFromString("com.google.android.googlequicksearchbox/com.google.android.voicesearch.serviceapi.GoogleRecognitionService"));
        this.b.setRecognitionListener(this);
    }

    public void a() {
        if (this.b != null) {
            this.b.startListening(this.c);
            this.d = true;
        }
        u.a().a("VoiceInteraction", "Listening", "Start");
    }

    public void b() {
        if (this.b != null) {
            this.b.stopListening();
            this.d = false;
        }
    }

    public boolean c() {
        return this.d;
    }

    public void d() {
        if (this.b != null) {
            this.b.destroy();
        }
    }

    public void onBeginningOfSpeech() {
        a(VoiceCommand.SPEECH_STARTED, null);
    }

    public void onEndOfSpeech() {
        this.d = false;
        a(VoiceCommand.SPEECH_ENDED, null);
    }

    public void onError(int i) {
        this.d = false;
        a(VoiceCommand.ERROR, a(i));
    }

    private void a(VoiceCommand voiceCommand, String str) {
        if (this.e != null) {
            this.e.a(voiceCommand, str);
        }
    }

    public void onReadyForSpeech(Bundle bundle) {
        a(VoiceCommand.SPEECH_STARTED, null);
    }

    public void onResults(Bundle bundle) {
        ArrayList stringArrayList = bundle.getStringArrayList("results_recognition");
        if (this.e != null) {
            this.e.a(stringArrayList);
        }
    }

    public String a(int i) {
        String string = this.a.getResources().getString(R.string.voice_result_error_msg);
        String string2 = this.a.getResources().getString(R.string.voice_network_error);
        switch (i) {
            case 1:
                return string2;
            case 2:
                return string2;
            case 3:
                return string;
            case 4:
                return string;
            case 5:
                return string;
            case 6:
                return string;
            case 7:
                return string;
            case 8:
                return string;
            case 9:
                return string;
            default:
                return string;
        }
    }
}
