package com.example.kotlinstudygufran.basic

//Format Cmd+Option+L
class Invoice {
    fun getID(): String {
        return "12ANSB"
    }
}

//here name is val by default
//Kotlin generates getter for name , getter & setter for departmebt
class Employee constructor(name: String, var department: String){
    fun doWork(){
        println("Doing work");

        println("Apple is red")
    }
}

fun main() {
    println("hello main ")
    println("Bismillah hirrahma Nirahim")

    val invoice = Invoice();
    println("Invoice Id - " + invoice.getID())

    val gufran = Employee("Gufran", "Mobile App Development");
    println("Department - " + gufran.department)
    gufran.department = "IT Manager"
    println("Department - " + gufran.department)

    //gufran.name="Mohit " can't change

    gufran.doWork();






}
