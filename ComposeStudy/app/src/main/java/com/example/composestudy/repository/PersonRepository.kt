package com.example.composestudy.repository

import com.example.composestudy.model.Person

class PersonRepository {
    fun getAllData(): List<Person> {
        val personList = listOf<Person>(
            Person(id = 1, firstName = "Alice", lastName = "Smith", age = 30),
            Person(id = 2, firstName = "Bob", lastName = "Johnson", age = 25),
            Person(id = 3, firstName = "Charlie", lastName = "Williams", age = 35),
            Person(id = 4, firstName = "David", lastName = "Brown", age = 40),
            Person(id = 5, firstName = "Eve", lastName = "Jones", age = 28),
            Person(id = 6, firstName = "Frank", lastName = "Davis", age = 45),
            Person(id = 7, firstName = "Grace", lastName = "Miller", age = 22),
            Person(id = 8, firstName = "Henry", lastName = "Wilson", age = 50),
            Person(id = 9, firstName = "Ivy", lastName = "Moore", age = 33),
            Person(id = 10, firstName = "Jack", lastName = "Taylor", age = 27),
            Person(id = 11, firstName = "Katie", lastName = "Anderson", age = 38),
            Person(id = 12, firstName = "Leo", lastName = "Thomas", age = 29),
            Person(id = 13, firstName = "Mia", lastName = "Jackson", age = 41),
            Person(id = 14, firstName = "Noah", lastName = "White", age = 26),
            Person(id = 15, firstName = "Olivia", lastName = "Harris", age = 32),
            Person(id = 16, firstName = "Peter", lastName = "Martin", age = 47),
            Person(id = 17, firstName = "Quinn", lastName = "Thompson", age = 24),
            Person(id = 18, firstName = "Ryan", lastName = "Garcia", age = 39),
            Person(id = 19, firstName = "Sophia", lastName = "Martinez", age = 31),
            Person(id = 20, firstName = "Tom", lastName = "Robinson", age = 42)
        )
        return personList
    }
}