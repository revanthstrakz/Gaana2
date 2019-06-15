package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 3;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        int i;
        if (constraintWidgetContainer.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            i = constraintWidget.mTop.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i, height);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A:{RETURN} */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r3, int r4) {
        /*
        r0 = r3.mListDimensionBehaviors;
        r0 = r0[r4];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        r2 = 0;
        if (r0 == r1) goto L_0x000a;
    L_0x0009:
        return r2;
    L_0x000a:
        r0 = r3.mDimensionRatio;
        r1 = 0;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        r1 = 1;
        if (r0 == 0) goto L_0x0020;
    L_0x0012:
        r3 = r3.mListDimensionBehaviors;
        if (r4 != 0) goto L_0x0017;
    L_0x0016:
        goto L_0x0018;
    L_0x0017:
        r1 = r2;
    L_0x0018:
        r3 = r3[r1];
        r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r3 != r4) goto L_0x001f;
    L_0x001e:
        return r2;
    L_0x001f:
        return r2;
    L_0x0020:
        if (r4 != 0) goto L_0x0030;
    L_0x0022:
        r4 = r3.mMatchConstraintDefaultWidth;
        if (r4 == 0) goto L_0x0027;
    L_0x0026:
        return r2;
    L_0x0027:
        r4 = r3.mMatchConstraintMinWidth;
        if (r4 != 0) goto L_0x002f;
    L_0x002b:
        r3 = r3.mMatchConstraintMaxWidth;
        if (r3 == 0) goto L_0x003e;
    L_0x002f:
        return r2;
    L_0x0030:
        r4 = r3.mMatchConstraintDefaultHeight;
        if (r4 == 0) goto L_0x0035;
    L_0x0034:
        return r2;
    L_0x0035:
        r4 = r3.mMatchConstraintMinHeight;
        if (r4 != 0) goto L_0x003f;
    L_0x0039:
        r3 = r3.mMatchConstraintMaxHeight;
        if (r3 == 0) goto L_0x003e;
    L_0x003d:
        goto L_0x003f;
    L_0x003e:
        return r1;
    L_0x003f:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int i, ConstraintWidget constraintWidget) {
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget.mBottom.getResolutionNode();
        i = (i & 8) == 8 ? 1 : 0;
        if (!(resolutionNode.type == 4 || resolutionNode3.type == 4)) {
            if (constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) {
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (i != 0) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (i != 0) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget != null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    if (i != 0) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    }
                } else if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    resolutionNode.setType(2);
                    resolutionNode3.setType(2);
                    if (i != 0) {
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                        resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                        resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.setOpposite(resolutionNode3, (float) (-constraintWidget.getWidth()));
                        resolutionNode3.setOpposite(resolutionNode, (float) constraintWidget.getWidth());
                    }
                }
            } else if (constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 0)) {
                int width = constraintWidget.getWidth();
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    if (i != 0) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, width);
                    }
                } else if (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget != null) {
                    if (constraintWidget.mLeft.mTarget != null || constraintWidget.mRight.mTarget == null) {
                        if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                            if (i != 0) {
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                resolutionNode.setType(3);
                                resolutionNode3.setType(3);
                                resolutionNode.setOpposite(resolutionNode3, 0.0f);
                                resolutionNode3.setOpposite(resolutionNode, 0.0f);
                            } else {
                                resolutionNode.setType(2);
                                resolutionNode3.setType(2);
                                resolutionNode.setOpposite(resolutionNode3, (float) (-width));
                                resolutionNode3.setOpposite(resolutionNode, (float) width);
                                constraintWidget.setWidth(width);
                            }
                        }
                    } else if (i != 0) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -width);
                    }
                } else if (i != 0) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, width);
                }
            }
        }
        if (resolutionNode2.type != 4 && resolutionNode4.type != 4) {
            if (constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED) {
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (i != 0) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaseline.mTarget != null) {
                        constraintWidget.mBaseline.getResolutionNode().setType(1);
                        resolutionNode2.dependsOn(1, constraintWidget.mBaseline.getResolutionNode(), -constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (i != 0) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget != null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (i != 0) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                    resolutionNode2.setType(2);
                    resolutionNode4.setType(2);
                    if (i != 0) {
                        resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                        resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                    } else {
                        resolutionNode2.setOpposite(resolutionNode4, (float) (-constraintWidget.getHeight()));
                        resolutionNode4.setOpposite(resolutionNode2, (float) constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                }
            } else if (constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 1)) {
                int height = constraintWidget.getHeight();
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    if (i != 0) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                    }
                } else if (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget != null) {
                    if (constraintWidget.mTop.mTarget != null || constraintWidget.mBottom.mTarget == null) {
                        if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                            if (i != 0) {
                                constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                resolutionNode2.setType(3);
                                resolutionNode4.setType(3);
                                resolutionNode2.setOpposite(resolutionNode4, 0.0f);
                                resolutionNode4.setOpposite(resolutionNode2, 0.0f);
                                return;
                            }
                            resolutionNode2.setType(2);
                            resolutionNode4.setType(2);
                            resolutionNode2.setOpposite(resolutionNode4, (float) (-height));
                            resolutionNode4.setOpposite(resolutionNode2, (float) height);
                            constraintWidget.setHeight(height);
                            if (constraintWidget.mBaselineDistance > 0) {
                                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                            }
                        }
                    } else if (i != 0) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -height);
                    }
                } else if (i != 0) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, height);
                }
            }
        }
    }

    /* JADX WARNING: Missing block: B:11:0x0035, code skipped:
            if (r6.mListAnchors[r24].mTarget.mOwner == r5) goto L_0x0038;
     */
    /* JADX WARNING: Missing block: B:28:0x0053, code skipped:
            if (r5.mHorizontalChainStyle == 2) goto L_0x0055;
     */
    /* JADX WARNING: Missing block: B:30:0x0057, code skipped:
            r1 = null;
     */
    /* JADX WARNING: Missing block: B:40:0x0069, code skipped:
            if (r5.mVerticalChainStyle == 2) goto L_0x0055;
     */
    /* JADX WARNING: Missing block: B:90:0x012d, code skipped:
            if (r3.mListAnchors[r24].mTarget.mOwner == r9) goto L_0x0130;
     */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r21, android.support.constraint.solver.LinearSystem r22, int r23, int r24, android.support.constraint.solver.widgets.ConstraintWidget r25) {
        /*
        r0 = r22;
        r1 = r21;
        r2 = r1.mListDimensionBehaviors;
        r2 = r2[r23];
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        r2 = 0;
        r4 = 1;
        if (r23 != 0) goto L_0x003e;
    L_0x000e:
        r1 = r21.isRtl();
        if (r1 == 0) goto L_0x003e;
    L_0x0014:
        r5 = r25;
        r1 = 0;
    L_0x0017:
        if (r1 != 0) goto L_0x0040;
    L_0x0019:
        r6 = r5.mListAnchors;
        r7 = r24 + 1;
        r6 = r6[r7];
        r6 = r6.mTarget;
        if (r6 == 0) goto L_0x0037;
    L_0x0023:
        r6 = r6.mOwner;
        r7 = r6.mListAnchors;
        r7 = r7[r24];
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x0037;
    L_0x002d:
        r7 = r6.mListAnchors;
        r7 = r7[r24];
        r7 = r7.mTarget;
        r7 = r7.mOwner;
        if (r7 == r5) goto L_0x0038;
    L_0x0037:
        r6 = r2;
    L_0x0038:
        if (r6 == 0) goto L_0x003c;
    L_0x003a:
        r5 = r6;
        goto L_0x0017;
    L_0x003c:
        r1 = r4;
        goto L_0x0017;
    L_0x003e:
        r5 = r25;
    L_0x0040:
        r1 = 2;
        if (r23 != 0) goto L_0x0059;
    L_0x0043:
        r6 = r5.mHorizontalChainStyle;
        if (r6 != 0) goto L_0x0049;
    L_0x0047:
        r6 = r4;
        goto L_0x004a;
    L_0x0049:
        r6 = 0;
    L_0x004a:
        r7 = r5.mHorizontalChainStyle;
        if (r7 != r4) goto L_0x0050;
    L_0x004e:
        r7 = r4;
        goto L_0x0051;
    L_0x0050:
        r7 = 0;
    L_0x0051:
        r5 = r5.mHorizontalChainStyle;
        if (r5 != r1) goto L_0x0057;
    L_0x0055:
        r1 = r4;
        goto L_0x006c;
    L_0x0057:
        r1 = 0;
        goto L_0x006c;
    L_0x0059:
        r6 = r5.mVerticalChainStyle;
        if (r6 != 0) goto L_0x005f;
    L_0x005d:
        r6 = r4;
        goto L_0x0060;
    L_0x005f:
        r6 = 0;
    L_0x0060:
        r7 = r5.mVerticalChainStyle;
        if (r7 != r4) goto L_0x0066;
    L_0x0064:
        r7 = r4;
        goto L_0x0067;
    L_0x0066:
        r7 = 0;
    L_0x0067:
        r5 = r5.mVerticalChainStyle;
        if (r5 != r1) goto L_0x0057;
    L_0x006b:
        goto L_0x0055;
    L_0x006c:
        r9 = r25;
        r5 = r2;
        r10 = r5;
        r11 = r10;
        r17 = r11;
        r8 = 0;
        r12 = 0;
        r13 = 0;
        r14 = 0;
        r15 = 0;
        r16 = 0;
    L_0x007a:
        if (r8 != 0) goto L_0x0138;
    L_0x007c:
        r4 = r9.mListNextVisibleWidget;
        r4[r23] = r2;
        r4 = r9.getVisibility();
        r3 = 8;
        if (r4 == r3) goto L_0x00c5;
    L_0x0088:
        if (r10 == 0) goto L_0x008e;
    L_0x008a:
        r4 = r10.mListNextVisibleWidget;
        r4[r23] = r9;
    L_0x008e:
        if (r11 != 0) goto L_0x0091;
    L_0x0090:
        r11 = r9;
    L_0x0091:
        r12 = r12 + 1;
        if (r23 != 0) goto L_0x009c;
    L_0x0095:
        r4 = r9.getWidth();
        r4 = (float) r4;
        r14 = r14 + r4;
        goto L_0x00a2;
    L_0x009c:
        r4 = r9.getHeight();
        r4 = (float) r4;
        r14 = r14 + r4;
    L_0x00a2:
        if (r9 == r11) goto L_0x00ae;
    L_0x00a4:
        r4 = r9.mListAnchors;
        r4 = r4[r24];
        r4 = r4.getMargin();
        r4 = (float) r4;
        r14 = r14 + r4;
    L_0x00ae:
        r4 = r9.mListAnchors;
        r4 = r4[r24];
        r4 = r4.getMargin();
        r4 = (float) r4;
        r15 = r15 + r4;
        r4 = r9.mListAnchors;
        r10 = r24 + 1;
        r4 = r4[r10];
        r4 = r4.getMargin();
        r4 = (float) r4;
        r15 = r15 + r4;
        r10 = r9;
    L_0x00c5:
        r4 = r9.mListAnchors;
        r4 = r4[r24];
        r4 = r9.mListNextMatchConstraintsWidget;
        r4[r23] = r2;
        r4 = r9.getVisibility();
        if (r4 == r3) goto L_0x0111;
    L_0x00d3:
        r3 = r9.mListDimensionBehaviors;
        r3 = r3[r23];
        r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r3 != r4) goto L_0x0111;
    L_0x00db:
        r13 = r13 + 1;
        if (r23 != 0) goto L_0x00ef;
    L_0x00df:
        r3 = r9.mMatchConstraintDefaultWidth;
        if (r3 == 0) goto L_0x00e5;
    L_0x00e3:
        r3 = 0;
        return r3;
    L_0x00e5:
        r3 = 0;
        r4 = r9.mMatchConstraintMinWidth;
        if (r4 != 0) goto L_0x00ee;
    L_0x00ea:
        r4 = r9.mMatchConstraintMaxWidth;
        if (r4 == 0) goto L_0x00fe;
    L_0x00ee:
        return r3;
    L_0x00ef:
        r3 = 0;
        r4 = r9.mMatchConstraintDefaultHeight;
        if (r4 == 0) goto L_0x00f5;
    L_0x00f4:
        return r3;
    L_0x00f5:
        r3 = r9.mMatchConstraintMinHeight;
        if (r3 != 0) goto L_0x010f;
    L_0x00f9:
        r3 = r9.mMatchConstraintMaxHeight;
        if (r3 == 0) goto L_0x00fe;
    L_0x00fd:
        goto L_0x010f;
    L_0x00fe:
        r3 = r9.mWeight;
        r3 = r3[r23];
        r16 = r16 + r3;
        if (r17 != 0) goto L_0x0109;
    L_0x0106:
        r17 = r9;
        goto L_0x010d;
    L_0x0109:
        r3 = r5.mListNextMatchConstraintsWidget;
        r3[r23] = r9;
    L_0x010d:
        r5 = r9;
        goto L_0x0111;
    L_0x010f:
        r0 = 0;
        return r0;
    L_0x0111:
        r3 = r9.mListAnchors;
        r4 = r24 + 1;
        r3 = r3[r4];
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x012f;
    L_0x011b:
        r3 = r3.mOwner;
        r4 = r3.mListAnchors;
        r4 = r4[r24];
        r4 = r4.mTarget;
        if (r4 == 0) goto L_0x012f;
    L_0x0125:
        r4 = r3.mListAnchors;
        r4 = r4[r24];
        r4 = r4.mTarget;
        r4 = r4.mOwner;
        if (r4 == r9) goto L_0x0130;
    L_0x012f:
        r3 = r2;
    L_0x0130:
        if (r3 == 0) goto L_0x0134;
    L_0x0132:
        r9 = r3;
        goto L_0x0135;
    L_0x0134:
        r8 = 1;
    L_0x0135:
        r4 = 1;
        goto L_0x007a;
    L_0x0138:
        r3 = r25;
        r2 = r3.mListAnchors;
        r2 = r2[r24];
        r2 = r2.getResolutionNode();
        r4 = r9.mListAnchors;
        r5 = r24 + 1;
        r4 = r4[r5];
        r4 = r4.getResolutionNode();
        r8 = r2.target;
        if (r8 == 0) goto L_0x039c;
    L_0x0150:
        r8 = r4.target;
        if (r8 != 0) goto L_0x0156;
    L_0x0154:
        goto L_0x039c;
    L_0x0156:
        r8 = r2.target;
        r8 = r8.state;
        r3 = 1;
        if (r8 == r3) goto L_0x0165;
    L_0x015d:
        r8 = r4.target;
        r8 = r8.state;
        if (r8 == r3) goto L_0x0165;
    L_0x0163:
        r3 = 0;
        return r3;
    L_0x0165:
        r3 = 0;
        if (r13 <= 0) goto L_0x016b;
    L_0x0168:
        if (r13 == r12) goto L_0x016b;
    L_0x016a:
        return r3;
    L_0x016b:
        if (r1 != 0) goto L_0x0174;
    L_0x016d:
        if (r6 != 0) goto L_0x0174;
    L_0x016f:
        if (r7 == 0) goto L_0x0172;
    L_0x0171:
        goto L_0x0174;
    L_0x0172:
        r3 = 0;
        goto L_0x018d;
    L_0x0174:
        if (r11 == 0) goto L_0x0180;
    L_0x0176:
        r3 = r11.mListAnchors;
        r3 = r3[r24];
        r3 = r3.getMargin();
        r3 = (float) r3;
        goto L_0x0181;
    L_0x0180:
        r3 = 0;
    L_0x0181:
        if (r10 == 0) goto L_0x018d;
    L_0x0183:
        r8 = r10.mListAnchors;
        r8 = r8[r5];
        r8 = r8.getMargin();
        r8 = (float) r8;
        r3 = r3 + r8;
    L_0x018d:
        r8 = r2.target;
        r8 = r8.resolvedOffset;
        r4 = r4.target;
        r4 = r4.resolvedOffset;
        r17 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r17 >= 0) goto L_0x019c;
    L_0x0199:
        r4 = r4 - r8;
        r4 = r4 - r14;
        goto L_0x019f;
    L_0x019c:
        r4 = r8 - r4;
        r4 = r4 - r14;
    L_0x019f:
        r18 = 1;
        if (r13 <= 0) goto L_0x0267;
    L_0x01a3:
        if (r13 != r12) goto L_0x0267;
    L_0x01a5:
        r1 = r9.getParent();
        if (r1 == 0) goto L_0x01b9;
    L_0x01ab:
        r1 = r9.getParent();
        r1 = r1.mListDimensionBehaviors;
        r1 = r1[r23];
        r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r1 != r7) goto L_0x01b9;
    L_0x01b7:
        r1 = 0;
        return r1;
    L_0x01b9:
        r4 = r4 + r14;
        r4 = r4 - r15;
        if (r6 == 0) goto L_0x01bf;
    L_0x01bd:
        r15 = r15 - r3;
        r4 = r4 - r15;
    L_0x01bf:
        if (r6 == 0) goto L_0x01db;
    L_0x01c1:
        r1 = r11.mListAnchors;
        r1 = r1[r5];
        r1 = r1.getMargin();
        r1 = (float) r1;
        r8 = r8 + r1;
        r1 = r11.mListNextVisibleWidget;
        r1 = r1[r23];
        if (r1 == 0) goto L_0x01db;
    L_0x01d1:
        r1 = r1.mListAnchors;
        r1 = r1[r24];
        r1 = r1.getMargin();
        r1 = (float) r1;
        r8 = r8 + r1;
    L_0x01db:
        if (r11 == 0) goto L_0x0265;
    L_0x01dd:
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r1 == 0) goto L_0x01fc;
    L_0x01e1:
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r6 = r1.nonresolvedWidgets;
        r20 = r2;
        r2 = r6 - r18;
        r1.nonresolvedWidgets = r2;
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r2 = r1.resolvedWidgets;
        r6 = r2 + r18;
        r1.resolvedWidgets = r6;
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r2 = r1.chainConnectionResolved;
        r6 = r2 + r18;
        r1.chainConnectionResolved = r6;
        goto L_0x01fe;
    L_0x01fc:
        r20 = r2;
    L_0x01fe:
        r1 = r11.mListNextVisibleWidget;
        r1 = r1[r23];
        if (r1 != 0) goto L_0x020b;
    L_0x0204:
        if (r11 != r10) goto L_0x0207;
    L_0x0206:
        goto L_0x020b;
    L_0x0207:
        r9 = r20;
        r3 = 0;
        goto L_0x0261;
    L_0x020b:
        r2 = (float) r13;
        r2 = r4 / r2;
        r3 = 0;
        r6 = (r16 > r3 ? 1 : (r16 == r3 ? 0 : -1));
        if (r6 <= 0) goto L_0x021a;
    L_0x0213:
        r2 = r11.mWeight;
        r2 = r2[r23];
        r2 = r2 * r4;
        r2 = r2 / r16;
    L_0x021a:
        r6 = r11.mListAnchors;
        r6 = r6[r24];
        r6 = r6.getMargin();
        r6 = (float) r6;
        r8 = r8 + r6;
        r6 = r11.mListAnchors;
        r6 = r6[r24];
        r6 = r6.getResolutionNode();
        r9 = r20;
        r7 = r9.resolvedTarget;
        r6.resolve(r7, r8);
        r6 = r11.mListAnchors;
        r6 = r6[r5];
        r6 = r6.getResolutionNode();
        r7 = r9.resolvedTarget;
        r8 = r8 + r2;
        r6.resolve(r7, r8);
        r2 = r11.mListAnchors;
        r2 = r2[r24];
        r2 = r2.getResolutionNode();
        r2.addResolvedValue(r0);
        r2 = r11.mListAnchors;
        r2 = r2[r5];
        r2 = r2.getResolutionNode();
        r2.addResolvedValue(r0);
        r2 = r11.mListAnchors;
        r2 = r2[r5];
        r2 = r2.getMargin();
        r2 = (float) r2;
        r8 = r8 + r2;
    L_0x0261:
        r11 = r1;
        r2 = r9;
        goto L_0x01db;
    L_0x0265:
        r1 = 1;
        return r1;
    L_0x0267:
        r9 = r2;
        r2 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
        if (r2 >= 0) goto L_0x026e;
    L_0x026c:
        r2 = 0;
        return r2;
    L_0x026e:
        if (r1 == 0) goto L_0x02f4;
    L_0x0270:
        r4 = r4 - r3;
        r1 = r25.getHorizontalBiasPercent();
        r4 = r4 * r1;
        r8 = r8 + r4;
    L_0x0277:
        if (r11 == 0) goto L_0x02f1;
    L_0x0279:
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r1 == 0) goto L_0x0295;
    L_0x027d:
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r2 = r1.nonresolvedWidgets;
        r6 = r2 - r18;
        r1.nonresolvedWidgets = r6;
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r2 = r1.resolvedWidgets;
        r6 = r2 + r18;
        r1.resolvedWidgets = r6;
        r1 = android.support.constraint.solver.LinearSystem.sMetrics;
        r2 = r1.chainConnectionResolved;
        r6 = r2 + r18;
        r1.chainConnectionResolved = r6;
    L_0x0295:
        r1 = r11.mListNextVisibleWidget;
        r1 = r1[r23];
        if (r1 != 0) goto L_0x029d;
    L_0x029b:
        if (r11 != r10) goto L_0x02ef;
    L_0x029d:
        if (r23 != 0) goto L_0x02a5;
    L_0x029f:
        r2 = r11.getWidth();
        r2 = (float) r2;
        goto L_0x02aa;
    L_0x02a5:
        r2 = r11.getHeight();
        r2 = (float) r2;
    L_0x02aa:
        r3 = r11.mListAnchors;
        r3 = r3[r24];
        r3 = r3.getMargin();
        r3 = (float) r3;
        r8 = r8 + r3;
        r3 = r11.mListAnchors;
        r3 = r3[r24];
        r3 = r3.getResolutionNode();
        r4 = r9.resolvedTarget;
        r3.resolve(r4, r8);
        r3 = r11.mListAnchors;
        r3 = r3[r5];
        r3 = r3.getResolutionNode();
        r4 = r9.resolvedTarget;
        r8 = r8 + r2;
        r3.resolve(r4, r8);
        r2 = r11.mListAnchors;
        r2 = r2[r24];
        r2 = r2.getResolutionNode();
        r2.addResolvedValue(r0);
        r2 = r11.mListAnchors;
        r2 = r2[r5];
        r2 = r2.getResolutionNode();
        r2.addResolvedValue(r0);
        r2 = r11.mListAnchors;
        r2 = r2[r5];
        r2 = r2.getMargin();
        r2 = (float) r2;
        r8 = r8 + r2;
    L_0x02ef:
        r11 = r1;
        goto L_0x0277;
    L_0x02f1:
        r0 = 1;
        goto L_0x039b;
    L_0x02f4:
        if (r6 != 0) goto L_0x02f8;
    L_0x02f6:
        if (r7 == 0) goto L_0x02f1;
    L_0x02f8:
        if (r6 == 0) goto L_0x02fc;
    L_0x02fa:
        r4 = r4 - r3;
        goto L_0x02ff;
    L_0x02fc:
        if (r7 == 0) goto L_0x02ff;
    L_0x02fe:
        r4 = r4 - r3;
    L_0x02ff:
        r1 = r12 + 1;
        r1 = (float) r1;
        r1 = r4 / r1;
        if (r7 == 0) goto L_0x0313;
    L_0x0306:
        r2 = 1;
        if (r12 <= r2) goto L_0x030f;
    L_0x0309:
        r1 = r12 + -1;
        r1 = (float) r1;
        r1 = r4 / r1;
        goto L_0x0313;
    L_0x030f:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r4 / r1;
    L_0x0313:
        r2 = r8 + r1;
        if (r7 == 0) goto L_0x0324;
    L_0x0317:
        r3 = 1;
        if (r12 <= r3) goto L_0x0324;
    L_0x031a:
        r2 = r11.mListAnchors;
        r2 = r2[r24];
        r2 = r2.getMargin();
        r2 = (float) r2;
        r2 = r2 + r8;
    L_0x0324:
        if (r6 == 0) goto L_0x0332;
    L_0x0326:
        if (r11 == 0) goto L_0x0332;
    L_0x0328:
        r3 = r11.mListAnchors;
        r3 = r3[r24];
        r3 = r3.getMargin();
        r3 = (float) r3;
        r2 = r2 + r3;
    L_0x0332:
        if (r11 == 0) goto L_0x02f1;
    L_0x0334:
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        if (r3 == 0) goto L_0x0350;
    L_0x0338:
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r6 = r3.nonresolvedWidgets;
        r12 = r6 - r18;
        r3.nonresolvedWidgets = r12;
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r6 = r3.resolvedWidgets;
        r12 = r6 + r18;
        r3.resolvedWidgets = r12;
        r3 = android.support.constraint.solver.LinearSystem.sMetrics;
        r6 = r3.chainConnectionResolved;
        r12 = r6 + r18;
        r3.chainConnectionResolved = r12;
    L_0x0350:
        r3 = r11.mListNextVisibleWidget;
        r3 = r3[r23];
        if (r3 != 0) goto L_0x0358;
    L_0x0356:
        if (r11 != r10) goto L_0x0399;
    L_0x0358:
        if (r23 != 0) goto L_0x0360;
    L_0x035a:
        r4 = r11.getWidth();
        r4 = (float) r4;
        goto L_0x0365;
    L_0x0360:
        r4 = r11.getHeight();
        r4 = (float) r4;
    L_0x0365:
        r6 = r11.mListAnchors;
        r6 = r6[r24];
        r6 = r6.getResolutionNode();
        r7 = r9.resolvedTarget;
        r6.resolve(r7, r2);
        r6 = r11.mListAnchors;
        r6 = r6[r5];
        r6 = r6.getResolutionNode();
        r7 = r9.resolvedTarget;
        r8 = r2 + r4;
        r6.resolve(r7, r8);
        r6 = r11.mListAnchors;
        r6 = r6[r24];
        r6 = r6.getResolutionNode();
        r6.addResolvedValue(r0);
        r6 = r11.mListAnchors;
        r6 = r6[r5];
        r6 = r6.getResolutionNode();
        r6.addResolvedValue(r0);
        r4 = r4 + r1;
        r2 = r2 + r4;
    L_0x0399:
        r11 = r3;
        goto L_0x0332;
    L_0x039b:
        return r0;
    L_0x039c:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):boolean");
    }
}
