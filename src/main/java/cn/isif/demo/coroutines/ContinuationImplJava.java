package cn.isif.demo.coroutines;

import cn.isif.demo.utils.Logger;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;


public class ContinuationImplJava implements Continuation<Object> {
    private int label;
    private final Continuation<Unit> completion;

    public ContinuationImplJava(Continuation<Unit> completion) {
        this.completion = completion;
    }

    @NotNull
    @Override
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override
    public void resumeWith(@NotNull Object o) {
        try {
            Object result = o;
            switch (label) {
                case 0:
                    Logger.debug("case 0");
                    result = FunctionProviderKt.suspendFun1(this);
                    label++;
                    if (isSuspend(result)) return;
                case 1:
                    Logger.debug("case 1");
                    result = DelayKt.delay(1_000L,this);
                    label++;
                    if (isSuspend(result)) return;
                case 2:
                    Logger.debug("case 2");
                    result = FunctionProviderKt.suspendFun2(this);
                    label++;
                    if (isSuspend(result)) return;
                case 3:
                    Logger.debug("case 3");

            }
            completion.resumeWith(Unit.INSTANCE);
        } catch (Exception e) {
            completion.resumeWith(e);
        }
    }

    private boolean isSuspend(Object o) {
        return o == IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
