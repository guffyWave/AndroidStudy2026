package com.example.composestudy.flowstudy

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

data class User(var name: String, var age: Int, var marks: Float)

//A normal Flow example which emits only once
class UserRepository {
    // Below is not
    var userList = mutableListOf<User>(
        User("Alice", 22, 88.5f),
        User("Bob", 25, 76.0f),
        User("Charlie", 20, 91.2f),
        User("Diana", 23, 84.3f),
        User("Ethan", 21, 79.9f),
        User("Fiona", 22, 90.1f),
        User("George", 26, 67.4f),
        User("Hannah", 20, 95.0f),
        User("Ian", 23, 82.2f),
        User("Julia", 24, 89.9f),
        User("Kevin", 22, 76.5f),
        User("Laura", 21, 91.7f),
        User("Mike", 25, 69.3f),
        User("Nina", 23, 87.6f),
        User("Oscar", 24, 80.0f)
    )

    //current fetchUsers() only emits once per collection
    fun fetchUsers(start: Int = 0, pageSize: Int = 3): Flow<List<User>> = flow {
        emit(userList.subList(0, start + pageSize)) // loads new data each time collected
    }

    fun flowEmitTest(): Flow<String> = flow {
        emit("Apple")
        emit("Ball")
        emit("Cat")
        //emitAll()
    }

    fun getUsersLegacy(): List<User> = userList

    //Adding a new user will NOT emit  new data from Flow (we need to expose data as MutableStateFlow in order it to be observable)
    fun addUser(name: String, age: Int, marks: Float) {
        userList.add(User(name, age, marks))
    }

    fun updateMarksForUser(name: String, marks: Float) {
        userList.map {
            if (it.name == name) {
                it.marks = marks
            }
            it
        }
    }
}

