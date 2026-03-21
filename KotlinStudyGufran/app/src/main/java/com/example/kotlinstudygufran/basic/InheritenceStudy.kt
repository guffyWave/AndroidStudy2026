package com.example.kotlinstudygufran.basic

open class Car {
    var userAge: Int = 0
        get() = field;
        set(value) {
            if(value>=0){
                field=value;
            }else{
                println("Age can't be negative");
            }
        }

    open fun myFunction() {
        println("Called myMunction from Car")
    }
}

class Toyota : Car() {
    override fun myFunction() {
        println("Called myMunction from Toyota")
    }
}

interface  Foo{
    var count:Int; // property initialisation not allowed
    fun someFun()
}

class Bar:Foo{
    override var count: Int=10
//        get() = 10
//        set(value) {
//            field=value
//        }

    override fun someFun() {
        TODO("Not yet implemented")
    }

}

fun main() {
    val car=Car();
    println("Car user age - "+car.userAge)
    car.userAge=10
    println("Car user age - "+car.userAge)
    car.userAge=-2
    println("Car user age - "+car.userAge)

    val toyota=Toyota();
    toyota.userAge=45;
    println("Toyota userAge - "+toyota.userAge)

}