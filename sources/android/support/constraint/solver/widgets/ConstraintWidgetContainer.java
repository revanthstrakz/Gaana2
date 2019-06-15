package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    private static final boolean USE_SNAPSHOT = true;
    int mDebugSolverPassCount;
    private boolean mHeightMeasuredTooSmall;
    ConstraintWidget[] mHorizontalChainsArray;
    int mHorizontalChainsSize;
    private boolean mIsRtl;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    ConstraintWidget[] mVerticalChainsArray;
    int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;

    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void fillMetrics(Metrics metrics) {
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 3;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 3;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
        this.mIsRtl = false;
        this.mSystem = new LinearSystem();
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 3;
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        this.mDebugSolverPassCount = 0;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.mListDimensionBehaviors[0];
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.mListDimensionBehaviors[1];
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem);
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                constraintWidget.addToSolver(linearSystem);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, 1);
        }
        return true;
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem);
            if (constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public void analyze(int i) {
        super.analyze(i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) this.mChildren.get(i2)).analyze(i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01c5  */
    public void layout() {
        /*
        r18 = this;
        r1 = r18;
        r2 = r1.mX;
        r3 = r1.mY;
        r4 = r18.getWidth();
        r5 = 0;
        r4 = java.lang.Math.max(r5, r4);
        r6 = r18.getHeight();
        r6 = java.lang.Math.max(r5, r6);
        r1.mWidthMeasuredTooSmall = r5;
        r1.mHeightMeasuredTooSmall = r5;
        r7 = r1.mParent;
        if (r7 == 0) goto L_0x0046;
    L_0x001f:
        r7 = r1.mSnapshot;
        if (r7 != 0) goto L_0x002a;
    L_0x0023:
        r7 = new android.support.constraint.solver.widgets.Snapshot;
        r7.<init>(r1);
        r1.mSnapshot = r7;
    L_0x002a:
        r7 = r1.mSnapshot;
        r7.updateFrom(r1);
        r7 = r1.mPaddingLeft;
        r1.setX(r7);
        r7 = r1.mPaddingTop;
        r1.setY(r7);
        r18.resetAnchors();
        r7 = r1.mSystem;
        r7 = r7.getCache();
        r1.resetSolverVariables(r7);
        goto L_0x004a;
    L_0x0046:
        r1.mX = r5;
        r1.mY = r5;
    L_0x004a:
        r7 = r1.mOptimizationLevel;
        r8 = 8;
        r9 = 1;
        if (r7 == 0) goto L_0x0062;
    L_0x0051:
        r7 = r1.optimizeFor(r8);
        if (r7 != 0) goto L_0x005a;
    L_0x0057:
        r18.optimizeReset();
    L_0x005a:
        r18.optimize();
        r7 = r1.mSystem;
        r7.graphOptimizer = r9;
        goto L_0x0066;
    L_0x0062:
        r7 = r1.mSystem;
        r7.graphOptimizer = r5;
    L_0x0066:
        r7 = r1.mListDimensionBehaviors;
        r7 = r7[r9];
        r10 = r1.mListDimensionBehaviors;
        r10 = r10[r5];
        r18.resetChains();
        r11 = r1.mChildren;
        r11 = r11.size();
        r12 = r5;
    L_0x0078:
        if (r12 >= r11) goto L_0x008e;
    L_0x007a:
        r13 = r1.mChildren;
        r13 = r13.get(r12);
        r13 = (android.support.constraint.solver.widgets.ConstraintWidget) r13;
        r14 = r13 instanceof android.support.constraint.solver.widgets.WidgetContainer;
        if (r14 == 0) goto L_0x008b;
    L_0x0086:
        r13 = (android.support.constraint.solver.widgets.WidgetContainer) r13;
        r13.layout();
    L_0x008b:
        r12 = r12 + 1;
        goto L_0x0078;
    L_0x008e:
        r13 = r5;
        r14 = r13;
        r12 = r9;
    L_0x0091:
        if (r12 == 0) goto L_0x0209;
    L_0x0093:
        r13 = r13 + r9;
        r15 = r1.mSystem;	 Catch:{ Exception -> 0x00a9 }
        r15.reset();	 Catch:{ Exception -> 0x00a9 }
        r15 = r1.mSystem;	 Catch:{ Exception -> 0x00a9 }
        r15 = r1.addChildrenToSolver(r15);	 Catch:{ Exception -> 0x00a9 }
        if (r15 == 0) goto L_0x00c5;
    L_0x00a1:
        r12 = r1.mSystem;	 Catch:{ Exception -> 0x00a7 }
        r12.minimize();	 Catch:{ Exception -> 0x00a7 }
        goto L_0x00c5;
    L_0x00a7:
        r0 = move-exception;
        goto L_0x00ab;
    L_0x00a9:
        r0 = move-exception;
        r15 = r12;
    L_0x00ab:
        r12 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r12);
        r8 = java.lang.System.out;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r5 = "EXCEPTION : ";
        r9.append(r5);
        r9.append(r12);
        r5 = r9.toString();
        r8.println(r5);
    L_0x00c5:
        r5 = 2;
        if (r15 == 0) goto L_0x00d3;
    L_0x00c8:
        r8 = r1.mSystem;
        r9 = android.support.constraint.solver.widgets.Optimizer.flags;
        r1.updateChildrenFromSolver(r8, r9);
    L_0x00cf:
        r9 = r5;
    L_0x00d0:
        r5 = 8;
        goto L_0x011a;
    L_0x00d3:
        r8 = r1.mSystem;
        r1.updateFromSolver(r8);
        r8 = 0;
    L_0x00d9:
        if (r8 >= r11) goto L_0x00cf;
    L_0x00db:
        r9 = r1.mChildren;
        r9 = r9.get(r8);
        r9 = (android.support.constraint.solver.widgets.ConstraintWidget) r9;
        r12 = r9.mListDimensionBehaviors;
        r15 = 0;
        r12 = r12[r15];
        r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r12 != r15) goto L_0x00fc;
    L_0x00ec:
        r12 = r9.getWidth();
        r15 = r9.getWrapWidth();
        if (r12 >= r15) goto L_0x00fc;
    L_0x00f6:
        r8 = android.support.constraint.solver.widgets.Optimizer.flags;
        r12 = 1;
        r8[r5] = r12;
        goto L_0x00cf;
    L_0x00fc:
        r12 = 1;
        r15 = r9.mListDimensionBehaviors;
        r15 = r15[r12];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r15 != r5) goto L_0x0115;
    L_0x0105:
        r5 = r9.getHeight();
        r9 = r9.getWrapHeight();
        if (r5 >= r9) goto L_0x0115;
    L_0x010f:
        r5 = android.support.constraint.solver.widgets.Optimizer.flags;
        r9 = 2;
        r5[r9] = r12;
        goto L_0x00d0;
    L_0x0115:
        r9 = 2;
        r8 = r8 + 1;
        r5 = r9;
        goto L_0x00d9;
    L_0x011a:
        if (r13 >= r5) goto L_0x0187;
    L_0x011c:
        r8 = android.support.constraint.solver.widgets.Optimizer.flags;
        r8 = r8[r9];
        if (r8 == 0) goto L_0x0187;
    L_0x0122:
        r8 = 0;
        r9 = 0;
        r12 = 0;
    L_0x0125:
        if (r8 >= r11) goto L_0x014b;
    L_0x0127:
        r15 = r1.mChildren;
        r15 = r15.get(r8);
        r15 = (android.support.constraint.solver.widgets.ConstraintWidget) r15;
        r5 = r15.mX;
        r16 = r15.getWidth();
        r5 = r5 + r16;
        r9 = java.lang.Math.max(r9, r5);
        r5 = r15.mY;
        r15 = r15.getHeight();
        r5 = r5 + r15;
        r12 = java.lang.Math.max(r12, r5);
        r8 = r8 + 1;
        r5 = 8;
        goto L_0x0125;
    L_0x014b:
        r5 = r1.mMinWidth;
        r5 = java.lang.Math.max(r5, r9);
        r8 = r1.mMinHeight;
        r8 = java.lang.Math.max(r8, r12);
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r10 != r9) goto L_0x016e;
    L_0x015b:
        r9 = r18.getWidth();
        if (r9 >= r5) goto L_0x016e;
    L_0x0161:
        r1.setWidth(r5);
        r5 = r1.mListDimensionBehaviors;
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r12 = 0;
        r5[r12] = r9;
        r5 = 1;
        r9 = 1;
        goto L_0x0170;
    L_0x016e:
        r9 = r14;
        r5 = 0;
    L_0x0170:
        r12 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r7 != r12) goto L_0x0189;
    L_0x0174:
        r12 = r18.getHeight();
        if (r12 >= r8) goto L_0x0189;
    L_0x017a:
        r1.setHeight(r8);
        r5 = r1.mListDimensionBehaviors;
        r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r9 = 1;
        r5[r9] = r8;
        r5 = 1;
        r9 = 1;
        goto L_0x0189;
    L_0x0187:
        r9 = r14;
        r5 = 0;
    L_0x0189:
        r8 = r1.mMinWidth;
        r12 = r18.getWidth();
        r8 = java.lang.Math.max(r8, r12);
        r12 = r18.getWidth();
        if (r8 <= r12) goto L_0x01a5;
    L_0x0199:
        r1.setWidth(r8);
        r5 = r1.mListDimensionBehaviors;
        r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r9 = 0;
        r5[r9] = r8;
        r5 = 1;
        r9 = 1;
    L_0x01a5:
        r8 = r1.mMinHeight;
        r12 = r18.getHeight();
        r8 = java.lang.Math.max(r8, r12);
        r12 = r18.getHeight();
        if (r8 <= r12) goto L_0x01c2;
    L_0x01b5:
        r1.setHeight(r8);
        r5 = r1.mListDimensionBehaviors;
        r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r12 = 1;
        r5[r12] = r8;
        r5 = r12;
        r9 = r5;
        goto L_0x01c3;
    L_0x01c2:
        r12 = 1;
    L_0x01c3:
        if (r9 != 0) goto L_0x0201;
    L_0x01c5:
        r8 = r1.mListDimensionBehaviors;
        r14 = 0;
        r8 = r8[r14];
        r15 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r8 != r15) goto L_0x01e3;
    L_0x01ce:
        if (r4 <= 0) goto L_0x01e3;
    L_0x01d0:
        r8 = r18.getWidth();
        if (r8 <= r4) goto L_0x01e3;
    L_0x01d6:
        r1.mWidthMeasuredTooSmall = r12;
        r5 = r1.mListDimensionBehaviors;
        r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r5[r14] = r8;
        r1.setWidth(r4);
        r5 = r12;
        r9 = r5;
    L_0x01e3:
        r8 = r1.mListDimensionBehaviors;
        r8 = r8[r12];
        r14 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r8 != r14) goto L_0x0201;
    L_0x01eb:
        if (r6 <= 0) goto L_0x0201;
    L_0x01ed:
        r8 = r18.getHeight();
        if (r8 <= r6) goto L_0x0201;
    L_0x01f3:
        r1.mHeightMeasuredTooSmall = r12;
        r5 = r1.mListDimensionBehaviors;
        r8 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED;
        r5[r12] = r8;
        r1.setHeight(r6);
        r12 = 1;
        r14 = 1;
        goto L_0x0203;
    L_0x0201:
        r12 = r5;
        r14 = r9;
    L_0x0203:
        r5 = 0;
        r8 = 8;
        r9 = 1;
        goto L_0x0091;
    L_0x0209:
        r4 = r1.mParent;
        if (r4 == 0) goto L_0x0239;
    L_0x020d:
        r2 = r1.mMinWidth;
        r3 = r18.getWidth();
        r2 = java.lang.Math.max(r2, r3);
        r3 = r1.mMinHeight;
        r4 = r18.getHeight();
        r3 = java.lang.Math.max(r3, r4);
        r4 = r1.mSnapshot;
        r4.applyTo(r1);
        r4 = r1.mPaddingLeft;
        r2 = r2 + r4;
        r4 = r1.mPaddingRight;
        r2 = r2 + r4;
        r1.setWidth(r2);
        r2 = r1.mPaddingTop;
        r3 = r3 + r2;
        r2 = r1.mPaddingBottom;
        r3 = r3 + r2;
        r1.setHeight(r3);
        goto L_0x023d;
    L_0x0239:
        r1.mX = r2;
        r1.mY = r3;
    L_0x023d:
        if (r14 == 0) goto L_0x0249;
    L_0x023f:
        r2 = r1.mListDimensionBehaviors;
        r3 = 0;
        r2[r3] = r10;
        r2 = r1.mListDimensionBehaviors;
        r3 = 1;
        r2[r3] = r7;
    L_0x0249:
        r2 = r1.mSystem;
        r2 = r2.getCache();
        r1.resetSolverVariables(r2);
        r2 = r18.getRootConstraintContainer();
        if (r1 != r2) goto L_0x025b;
    L_0x0258:
        r18.updateDrawPosition();
    L_0x025b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    public void preOptimize() {
        optimizeReset();
        analyze(this.mOptimizationLevel);
    }

    public void solveGraph() {
        ResolutionAnchor resolutionNode = getAnchor(Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(Type.TOP).getResolutionNode();
        resolutionNode.resolve(null, 0.0f);
        resolutionNode2.resolve(null, 0.0f);
    }

    public void resetGraph() {
        ResolutionAnchor resolutionNode = getAnchor(Type.LEFT).getResolutionNode();
        ResolutionAnchor resolutionNode2 = getAnchor(Type.TOP).getResolutionNode();
        resolutionNode.invalidateAnchors();
        resolutionNode2.invalidateAnchors();
        resolutionNode.resolve(null, 0.0f);
        resolutionNode2.resolve(null, 0.0f);
    }

    public void optimizeForDimensions(int i, int i2) {
        if (!(this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT || this.mResolutionWidth == null)) {
            this.mResolutionWidth.resolve(i);
        }
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && this.mResolutionHeight != null) {
            this.mResolutionHeight.resolve(i2);
        }
    }

    public void optimizeReset() {
        int size = this.mChildren.size();
        resetResolutionNodes();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetResolutionNodes();
        }
    }

    public void optimize() {
        if (!optimizeFor(8)) {
            analyze(this.mOptimizationLevel);
        }
        solveGraph();
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* Access modifiers changed, original: 0000 */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mHorizontalChainsSize) {
            if (this.mHorizontalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mVerticalChainsSize) {
            if (this.mVerticalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }
}
