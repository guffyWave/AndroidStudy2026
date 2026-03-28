package com.example.objectandcomposestudy.shared


// Shared Data
object AnimalsRepository {

    private val zooAnimalsList = mutableListOf<String>("Zebra", "Lion", "Girrafe", "Wild Boar")
        get() = field
    private val petAnimalsList = mutableListOf<String>("Cat", "Dog", "Parrot", "Rabbit")
    private val domesticAnimalsList = mutableListOf<String>("Hen", "Duck", "Cow", "Buffalo")


//    fun getZooAnimals(): List<String> {
//        return zooAnimalsList
//    }

    fun getPetAnimals(): List<String>? {
//        if (petAnimalsList == null) {
//            return null
//        }

//        if (petAnimalsList.isNullOrEmpty()) {
//            return null
//        }

        return petAnimalsList ?: emptyList()

        //  return petAnimalsList
    }
}

