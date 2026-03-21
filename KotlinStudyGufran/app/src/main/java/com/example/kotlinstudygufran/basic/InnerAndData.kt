package com.example.kotlinstudygufran.basic

class Aeroplane {
    private var table: Map<String, Int>? = null

    val power = 10;

    inner class Engine {
        fun run() {
            println("Engine Power - " + power)
        }
    }
}

data class Mobile(val model: String, var price: Int)

enum class Direction(val degree: Int? = 0) {
    NORTH(90), SOUTH, EAST(180), WEST;

    fun getRotationValue(): Int? {
        return degree;
    }
}

fun main() {
    val aeroplane = Aeroplane();
    aeroplane.Engine().run();

    var name: String? = "Hello Apple ";

    lateinit var heavyVariable: String;
    ///println("heavyVariable" + heavyVariable)  // throw UninitializedPropertyAccessException
    heavyVariable = "Heavy Variable "
    println("heavyVariable" + heavyVariable)

    val someVariable: String by lazy {
        println("in lazy block ");
        "John"
    };

    println("someVariable - " + someVariable)

    val iPhone16 = Mobile("iPhone16", 1129)
    // iPhone16.model="Apple" // not allowed
    iPhone16.price = 1000;

    val (model, price) = iPhone16;

    val tomAndJerry = Pair<String, String>("Tom", "Jerry");
    println("tomAndJerry first - " + tomAndJerry.first)
    println("tomAndJerry second - " + tomAndJerry.second)


    println("Direction North - " + Direction.NORTH)
    println("Direction North - getRotationValue " + Direction.NORTH.getRotationValue())

    //scope function

    val nothing = Mobile("Nothing 4A", 220).let {
        return@let "This is ${it.model} at exclusive price of ${it.price}"
    }
    println("Info - " + nothing)

    val redMi = Mobile("Redmi Note10 Pro", 200);
    println("Info Redmi - " + redMi.price)

    redMi.run {
        this.price = 199
    }
    println("Info Redmi after change - " + redMi.price)


}