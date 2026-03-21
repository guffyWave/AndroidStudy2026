package com.example.gufranstudyphaseone.basics

data class DeviceConfig(var language: String, var theme: String, var darkMode: Boolean)

data class User(val name: String, val age: Int)

fun getDeviceConfig(): DeviceConfig {
    return DeviceConfig("en", "light", true);
}


fun main() {
    println("Bismillah Hirrahma Nirrahim");
    testSomething()
}

fun testSomething(): Unit {
    var userList: MutableList<String> = mutableListOf("Apple", "Ball", "Cat");
    var fruitsList: List<String> = listOf("Mango", "Ball", "Cat");
    fruitsList.forEach { fruit -> println(fruit) }

    var gufran = User("Gufran", 25);
    gufran.let { user -> println(user.name) }


}
