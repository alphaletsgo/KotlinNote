package cn.isif.demo.coroutines

import cn.isif.demo.utils.Logger
import kotlinx.coroutines.delay
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

//定义一个协程函数
suspend fun suspendFun1() {
    delay(100)
    Logger.debug("in suspendFun1")
}
//虽然使用suspend关键字，但由于内部不包含耗时操作，不能算一个真正的协程函数
suspend fun suspendFun2() {
    Logger.debug("in suspendFun2")
}
val completion = object : Continuation<Unit> {
    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<Unit>) {
        Logger.debug("resumeWith")
    }

}