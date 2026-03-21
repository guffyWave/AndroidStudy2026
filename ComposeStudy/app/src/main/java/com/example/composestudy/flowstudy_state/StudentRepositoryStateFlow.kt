package com.example.composestudy.flowstudy_state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class Student(var name: String, var age: Int, var marks: Float)

class StudentRepositoryStateFlow {
    // Make studentList private for encapsulation.
    private val _studentList = MutableStateFlow(
        listOf(
            Student("Alice", 22, 88.5f),
            Student("Bob", 25, 76.0f),
            Student("Charlie", 20, 91.2f),
            Student("Diana", 23, 84.3f),
        )
    )
    // MutableStateFlow can't have null
    // var abc = MutableStateFlow<String>(null)

    // Public immutable StateFlow
    //to access studentlist
    val studentList: StateFlow<List<Student>> = _studentList

    // fun fetchUsers(): List<Student> = studentList /// BAD

    //Adding legacy code
//    fun addUser(name: String, age: Int, marks: Float) {
//        studentList.add(Student(name, age, marks))
//    }

    // Add user by emitting a new list
    fun addUser(name: String, age: Int, marks: Float) {
        _studentList.value = _studentList.value + Student(name, age, marks)
    }

    // Update marks by creating a new list with updated user
    fun updateMarksForUser(name: String, marks: Float) {
        _studentList.value = _studentList.value.map {
            if (it.name == name) it.copy(marks = marks) else it
        }
    }

    //legacy updating marks
//    fun updateMarksForUser(name: String, marks: Float) {
//        studentList.map {
//            if (it.name == name) {
//                it.marks = marks
//            }
//            it
//        }
//    }
}