package cn.isif.demo.coroutines

import cn.isif.demo.utils.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.startCoroutine

/**
 * 协程的作用：协程主要是让原来要使用“异步+回调方式”写出来的复杂代码, 简化成可以用看似同步的方式写 出来(对线程的操作进一步抽象)
 *
 * 关于协程的一点个人理解
 * Kotlin协程可以说是一个线程框架，但不仅仅局限于线程框架，线程切换只是它核心功能之一
 * 协程的挂起针对的是协程函数，所谓的挂起从实现上来讲，只是对真正的协程函数切了线程
 */
fun main() {
//    halo()
//    haloRunBlocking()
//    blocking()
    baseSuspend()
//    testContinuation()
}

//一个简单的协程使用
fun halo() {
    val name = Thread.currentThread().name
    println("in $name")
    //GlobalScope 全局作用域，生命周期等同于应用的生命周期
    GlobalScope.launch {
//        delay(1000L)//延时1秒执行协程作用域后面的代码
//        Thread.sleep(1_000)
        println("run in ${Thread.currentThread().name}")
    }
    println("end $name")
    Thread.sleep(2000L)
}

//runBlocking
fun haloRunBlocking() {
    val name = Thread.currentThread().name
    println("in $name")
    GlobalScope.launch {
        delay(1000L)
        println("run in ${Thread.currentThread().name}")
    }
    println("end $name")
    //执行到runBlocking函数，需要等runBlocking函数中的所有协程都执行完毕之后才会继续执行
    runBlocking {
        delay(2000L)
    }
}
//runBlocking 另外一种用法
fun blocking() = runBlocking {
    val name = Thread.currentThread().name
    println("in $name")
    val job = GlobalScope.launch {
        delay(1000L)
        println("run in ${Thread.currentThread().name}")
    }
    println("end $name")
//    delay(2000L) //延时2秒，等待上面的任务执行完毕
    job.join() //join会等待job执行完毕
}

//使用非框架实现
fun baseSuspend() {
    suspend {
        Logger.debug("base in")
        //当执行协程函数的时候会使用线程池执行协程函数
//        suspendFun1()
        delay(3_000L)
        Logger.debug("base end")
    }.startCoroutine(completion)
    Thread.sleep(4_000)
}

fun testContinuation() {
    ContinuationImplJava(completion).resumeWith(Unit)
}