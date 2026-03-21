package com.example.gufranstudyphaseone.basics

//fun main() {
//    println("Bismillah Hirrahma Nirrahim")
//    getApple()
//    print("Apple is red ")
//    println("Apple")
//    println("")
//    println("Added - " + addTwoNumbers(2, 3))
//
//    var name: String = "Gufran"
//
//    val greet = { name: String -> "Hello $name" }
//    println(greet("Salman"))
//
//    val human = Human("Gufran", 25);
//    println("Name with salutation - " + human.nameWithSalutation);
//
//
//}

fun String.isPalindrom(): Boolean {
    return this.reversed() == this
}


fun addTwoNumbers(a: Int, b: Int): Int {
    return a + b;
}

fun studyKotlinLiveTemplates(): Unit {
//    var abc = object : Car() {
//        override fun myFunction() {
//            super.myFunction();
//            println("Called myMunction from Car")
//        }
//    }

    //  abc.myFunction();


    for (s in arrayOf("Apple", "Ball", "Cat")) {
        println(s);
    }


    arrayOf(1, 2, 3).forEach { item -> println(item) }


    val fruitList = listOf("Apple", "Ball", "Cat")

    for (fruit in fruitList) {
        println(fruit)
    }


}

class Human(val name: String, val age: Int)

val Human.nameWithSalutation: String
    get() {
        return "Hello $name"
    }

fun getApple(): String {
    println("Apple is red")
    return "Apple"
}


interface Flyable {
    fun fly()
}


object Logger {
    fun log(message: String) {
        println(message)
    }
}

object AppSettings {
    var darkModeEnabled: Boolean = false;

    fun toggleDarkMode(): Unit {
        darkModeEnabled = !darkModeEnabled;
    }
}

fun doWork() {
    Logger.log("Doing work")
    println("")
}

fun greet(name: String, age: Int) {
    println("name = [${name}], age = [${age}]")
}


fun process() {
    var count = 0
    var status = "READY";

    println("count = ${count}")
    println("status = ${status}")

}

fun main() {
    println("Main")
    println("Current Setting App Setting - isDark  " + AppSettings.darkModeEnabled)
    AppSettings.toggleDarkMode()
    println("Current Setting App Setting - isDark  " + AppSettings.darkModeEnabled)
}
