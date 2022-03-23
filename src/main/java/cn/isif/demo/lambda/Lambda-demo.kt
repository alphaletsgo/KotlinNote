package cn.isif.demo.lambda

/**
 * kotlin lambda学习
 * 需要与函数区分
 */

class Person(val name: String, val age: Int) {
    override fun toString(): String {
        return "[$name,$age]"
    }
}

fun f() {
    println("f")
}
val f1: () -> Unit  = {
    println("000")
}

fun main() {
    //利用lambda寻找集合中最值
    val people = listOf(Person("Dev", 19), Person("Mar", 22), Person("Jac", 17))
    val oldPerson = people.maxOf { it.age }
    println(oldPerson)
    //lambda定义:{ x: Int, y: Int -> x + y }
    val sum = { x: Int, y: Int -> x + y }
    //然后，使用
    println(sum(5, 6))
    "".apply { }
//    val ff = ::f
    val fff = ::f1
    fff.invoke()
    "".apply {

    }

}