fun main() {
    //testOne()
    testTwo()
}

fun testOne() {
    listOf(1, 2, 3).forEach {
        if (it == 2) return  // ❌ returns from `test()` entirely, not just the lambda!
        println(it)
    }
    println("Done") // will never be printed
}

fun testTwo() {
    listOf(1, 2, 3).forEach {
        if (it == 2) return@forEach  // ✅ returns only from the forEach lambda
        println(it)
    }
    println("Done") // ✅ this line runs now
}

