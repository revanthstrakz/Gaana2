package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private final int channelCount;
    private final short[] downSampleBuffer = new short[this.maxRequiredFrameCount];
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount = (2 * this.maxPeriod);
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int i, int i2, float f, float f2, int i3) {
        this.inputSampleRateHz = i;
        this.channelCount = i2;
        this.speed = f;
        this.pitch = f2;
        this.rate = ((float) i) / ((float) i3);
        this.minPeriod = i / 400;
        this.maxPeriod = i / 65;
        this.inputBuffer = new short[(this.maxRequiredFrameCount * i2)];
        this.outputBuffer = new short[(this.maxRequiredFrameCount * i2)];
        this.pitchBuffer = new short[(this.maxRequiredFrameCount * i2)];
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining() / this.channelCount;
        int i = (this.channelCount * remaining) * 2;
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, remaining);
        shortBuffer.get(this.inputBuffer, this.inputFrameCount * this.channelCount, i / 2);
        this.inputFrameCount += remaining;
        processStreamInput();
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.channelCount, this.outputFrameCount);
        shortBuffer.put(this.outputBuffer, 0, this.channelCount * min);
        this.outputFrameCount -= min;
        System.arraycopy(this.outputBuffer, min * this.channelCount, this.outputBuffer, 0, this.outputFrameCount * this.channelCount);
    }

    public void queueEndOfStream() {
        int i = this.inputFrameCount;
        float f = this.rate * this.pitch;
        int i2 = this.outputFrameCount + ((int) ((((((float) i) / (this.speed / this.pitch)) + ((float) this.pitchFrameCount)) / f) + 0.5f));
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, (this.maxRequiredFrameCount * 2) + i);
        for (int i3 = 0; i3 < (this.maxRequiredFrameCount * 2) * this.channelCount; i3++) {
            this.inputBuffer[(this.channelCount * i) + i3] = (short) 0;
        }
        this.inputFrameCount += 2 * this.maxRequiredFrameCount;
        processStreamInput();
        if (this.outputFrameCount > i2) {
            this.outputFrameCount = i2;
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
    }

    public int getFramesAvailable() {
        return this.outputFrameCount;
    }

    private short[] ensureSpaceForAdditionalFrames(short[] sArr, int i, int i2) {
        int length = sArr.length / this.channelCount;
        if (i + i2 <= length) {
            return sArr;
        }
        return Arrays.copyOf(sArr, (((3 * length) / 2) + i2) * this.channelCount);
    }

    private void removeProcessedInputFrames(int i) {
        int i2 = this.inputFrameCount - i;
        System.arraycopy(this.inputBuffer, i * this.channelCount, this.inputBuffer, 0, this.channelCount * i2);
        this.inputFrameCount = i2;
    }

    private void copyToOutput(short[] sArr, int i, int i2) {
        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i2);
        System.arraycopy(sArr, i * this.channelCount, this.outputBuffer, this.outputFrameCount * this.channelCount, this.channelCount * i2);
        this.outputFrameCount += i2;
    }

    private int copyInputToOutput(int i) {
        int min = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, i, min);
        this.remainingInputToCopyFrameCount -= min;
        return min;
    }

    private void downSampleInput(short[] sArr, int i, int i2) {
        int i3 = this.maxRequiredFrameCount / i2;
        int i4 = this.channelCount * i2;
        i *= this.channelCount;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = 0;
            int i7 = i6;
            while (i6 < i4) {
                i7 += sArr[((i5 * i4) + i) + i6];
                i6++;
            }
            this.downSampleBuffer[i5] = (short) (i7 / i4);
        }
    }

    private int findPitchPeriodInRange(short[] sArr, int i, int i2, int i3) {
        i *= this.channelCount;
        int i4 = 1;
        int i5 = 0;
        int i6 = 255;
        int i7 = i5;
        while (i2 <= i3) {
            int i8 = 0;
            int i9 = i8;
            while (i8 < i2) {
                i9 += Math.abs(sArr[i + i8] - sArr[(i + i2) + i8]);
                i8++;
            }
            if (i9 * i7 < i4 * i2) {
                i7 = i2;
                i4 = i9;
            }
            if (i9 * i6 > i5 * i2) {
                i6 = i2;
                i5 = i9;
            }
            i2++;
        }
        this.minDiff = i4 / i7;
        this.maxDiff = i5 / i6;
        return i7;
    }

    /* JADX WARNING: Missing block: B:12:0x0018, code skipped:
            return false;
     */
    private boolean previousPeriodBetter(int r3, int r4) {
        /*
        r2 = this;
        r0 = 0;
        if (r3 == 0) goto L_0x0018;
    L_0x0003:
        r1 = r2.prevPeriod;
        if (r1 != 0) goto L_0x0008;
    L_0x0007:
        goto L_0x0018;
    L_0x0008:
        r1 = r3 * 3;
        if (r4 <= r1) goto L_0x000d;
    L_0x000c:
        return r0;
    L_0x000d:
        r3 = r3 * 2;
        r4 = r2.prevMinDiff;
        r4 = r4 * 3;
        if (r3 > r4) goto L_0x0016;
    L_0x0015:
        return r0;
    L_0x0016:
        r3 = 1;
        return r3;
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.Sonic.previousPeriodBetter(int, int):boolean");
    }

    private int findPitchPeriod(short[] sArr, int i) {
        int findPitchPeriodInRange;
        int i2 = this.inputSampleRateHz > 4000 ? this.inputSampleRateHz / 4000 : 1;
        if (this.channelCount == 1 && i2 == 1) {
            findPitchPeriodInRange = findPitchPeriodInRange(sArr, i, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i, i2);
            int findPitchPeriodInRange2 = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i2, this.maxPeriod / i2);
            if (i2 != 1) {
                findPitchPeriodInRange2 *= i2;
                i2 *= 4;
                int i3 = findPitchPeriodInRange2 - i2;
                findPitchPeriodInRange2 += i2;
                if (i3 < this.minPeriod) {
                    i3 = this.minPeriod;
                }
                if (findPitchPeriodInRange2 > this.maxPeriod) {
                    findPitchPeriodInRange2 = this.maxPeriod;
                }
                if (this.channelCount == 1) {
                    findPitchPeriodInRange = findPitchPeriodInRange(sArr, i, i3, findPitchPeriodInRange2);
                } else {
                    downSampleInput(sArr, i, 1);
                    findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, i3, findPitchPeriodInRange2);
                }
            } else {
                findPitchPeriodInRange = findPitchPeriodInRange2;
            }
        }
        i = previousPeriodBetter(this.minDiff, this.maxDiff) ? this.prevPeriod : findPitchPeriodInRange;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = findPitchPeriodInRange;
        return i;
    }

    private void moveNewSamplesToPitchBuffer(int i) {
        int i2 = this.outputFrameCount - i;
        this.pitchBuffer = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, i2);
        System.arraycopy(this.outputBuffer, this.channelCount * i, this.pitchBuffer, this.pitchFrameCount * this.channelCount, this.channelCount * i2);
        this.outputFrameCount = i;
        this.pitchFrameCount += i2;
    }

    private void removePitchFrames(int i) {
        if (i != 0) {
            System.arraycopy(this.pitchBuffer, this.channelCount * i, this.pitchBuffer, 0, (this.pitchFrameCount - i) * this.channelCount);
            this.pitchFrameCount -= i;
        }
    }

    private short interpolate(short[] sArr, int i, int i2, int i3) {
        short s = sArr[i];
        short s2 = sArr[i + this.channelCount];
        i = this.newRatePosition * i2;
        int i4 = (this.oldRatePosition + 1) * i3;
        i = i4 - i;
        i4 -= this.oldRatePosition * i3;
        return (short) (((s * i) + ((i4 - i) * s2)) / i4);
    }

    private void adjustRate(float f, int i) {
        if (this.outputFrameCount != i) {
            int i2 = (int) (((float) this.inputSampleRateHz) / f);
            int i3 = this.inputSampleRateHz;
            while (true) {
                if (i2 <= 16384 && i3 <= 16384) {
                    break;
                }
                i2 /= 2;
                i3 /= 2;
            }
            moveNewSamplesToPitchBuffer(i);
            int i4 = 0;
            while (true) {
                boolean z = true;
                if (i4 < this.pitchFrameCount - 1) {
                    while ((this.oldRatePosition + 1) * i2 > this.newRatePosition * i3) {
                        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                        for (int i5 = 0; i5 < this.channelCount; i5++) {
                            this.outputBuffer[(this.outputFrameCount * this.channelCount) + i5] = interpolate(this.pitchBuffer, (this.channelCount * i4) + i5, i3, i2);
                        }
                        this.newRatePosition++;
                        this.outputFrameCount++;
                    }
                    this.oldRatePosition++;
                    if (this.oldRatePosition == i3) {
                        this.oldRatePosition = 0;
                        if (this.newRatePosition != i2) {
                            z = false;
                        }
                        Assertions.checkState(z);
                        this.newRatePosition = 0;
                    }
                    i4++;
                } else {
                    removePitchFrames(this.pitchFrameCount - 1);
                    return;
                }
            }
        }
    }

    private int skipPitchPeriod(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f >= 2.0f) {
            i3 = (int) (((float) i2) / (f - 1.0f));
        } else {
            this.remainingInputToCopyFrameCount = (int) ((((float) i2) * (2.0f - f)) / (f - 1.0f));
            i3 = i2;
        }
        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i3);
        overlapAdd(i3, this.channelCount, this.outputBuffer, this.outputFrameCount, sArr, i, sArr, i + i2);
        this.outputFrameCount += i3;
        return i3;
    }

    private int insertPitchPeriod(short[] sArr, int i, float f, int i2) {
        int i3;
        if (f < 0.5f) {
            i3 = (int) ((((float) i2) * f) / (1.0f - f));
        } else {
            this.remainingInputToCopyFrameCount = (int) ((((float) i2) * ((2.0f * f) - 1.0f)) / (1.0f - f));
            i3 = i2;
        }
        int i4 = i2 + i3;
        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i4);
        System.arraycopy(sArr, this.channelCount * i, this.outputBuffer, this.outputFrameCount * this.channelCount, this.channelCount * i2);
        overlapAdd(i3, this.channelCount, this.outputBuffer, this.outputFrameCount + i2, sArr, i + i2, sArr, i);
        this.outputFrameCount += i4;
        return i3;
    }

    private void changeSpeed(float f) {
        if (this.inputFrameCount >= this.maxRequiredFrameCount) {
            int i = this.inputFrameCount;
            int i2 = 0;
            do {
                if (this.remainingInputToCopyFrameCount > 0) {
                    i2 += copyInputToOutput(i2);
                } else {
                    int findPitchPeriod = findPitchPeriod(this.inputBuffer, i2);
                    if (((double) f) > 1.0d) {
                        i2 += findPitchPeriod + skipPitchPeriod(this.inputBuffer, i2, f, findPitchPeriod);
                    } else {
                        i2 += insertPitchPeriod(this.inputBuffer, i2, f, findPitchPeriod);
                    }
                }
            } while (this.maxRequiredFrameCount + i2 <= i);
            removeProcessedInputFrames(i2);
        }
    }

    private void processStreamInput() {
        int i = this.outputFrameCount;
        float f = this.speed / this.pitch;
        float f2 = this.rate * this.pitch;
        double d = (double) f;
        if (d > 1.00001d || d < 0.99999d) {
            changeSpeed(f);
        } else {
            copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f2 != 1.0f) {
            adjustRate(f2, i);
        }
    }

    private static void overlapAdd(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i4 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i3 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i9] = (short) (((sArr2[i7] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i9 += i2;
                i7 += i2;
                i8 += i2;
            }
        }
    }
}
