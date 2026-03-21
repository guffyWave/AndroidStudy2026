package com.example.kotlinstudygufran.basic

class Worker {
    fun doWork() {
        println("Doing work");
    }
}

fun Worker.migrateToFirstWorld() {
    println("Migrate to first world")
}

fun String.addExclamation(): String {
    return this + "!";
}

//Singelton object - thread safe , lazy initisalisation, global access
object AppLogger {
    private var logCount: Int = 0

    init {
        //Applogger init - executed only once
        println("Applogger init - executed only once")
    }

    fun log(message: String) {
        logCount++
        println(" Logging message - " + message + logCount)
    }
}

class FancyList<T> {
    var primaryItem: T? = null;
    var itemList = mutableListOf<T>();

    fun getFancyPrimaryItem(): T? {
        return primaryItem;
    }

    fun getFancyList(): List<T> {
        return itemList;
    }

}


// In Kotlin, you simply declare the sealed class
// and its direct subclasses within the same file/compilation unit.
// The 'permits' keyword is NOT used.
public sealed class Bird {
}

class Sparrow : Bird() {}
class Eagle : Bird() {}


class MyClass {
    companion object {
        val greeting = "Hello";
        fun sayHello(name: String) {
            println("Said hello to ${name}")
        }
    }
}


fun main() {
    val worker = Worker();
    worker.doWork();
    worker.migrateToFirstWorld();

    val greet = "Hello";
    println(greet.addExclamation());

    AppLogger.log("This some thing ")

    AppLogger.log("Aple is red  ")
    AppLogger.log("Sky is blue ")

    MyClass.sayHello("Salman");

    val greetings: (String) -> String = { name -> "Hello ${name}" }
}