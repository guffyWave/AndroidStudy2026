package com.example.hiltstudy.helpers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


data class DivingInstructor @Inject constructor(val name: String)

data class DivingSchool @Inject constructor(val name: String, val location: String)


class DivingAdventure @Inject constructor(
    val instructor: DivingInstructor,
    val school: DivingSchool
) {
    fun doAdventure(): String {
        return "Doing adventure after learning from ${instructor.name}  from school ${school.name} in ${school.location}"
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DivingModule {

    @Provides
    fun provideDivingSchool(): DivingSchool {
        return DivingSchool("Sunnyside Diving School", "Bali")
    }

    @Provides
    fun provideDivingInstructor(): DivingInstructor {
        return DivingInstructor("Arnold")
    }

    //no need to provide this
//    @Provides
//    fun provideDivingAdventure(
//        instructor: DivingInstructor,
//        school: DivingSchool
//    ): DivingAdventure {
//        return DivingAdventure(instructor, school)
//    }

}