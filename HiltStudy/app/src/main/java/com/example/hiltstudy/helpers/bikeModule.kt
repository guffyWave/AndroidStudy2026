package com.example.hiltstudy.helpers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object BikeModule {

    @Provides
    @Named("vietnam_biking")
    fun provideBikeAdventure(): BikeAdventure {
        return BikeAdventure("Vietnam Bike Adventure")
    }

    @Provides
    @Named("canada")
    fun provideCanadaIceFieldBiking(): BikeAdventure {
        return BikeAdventure("Canada Icefield Bike Adventure")
    }

    //Similarly we can 'Provide' similar sibling classes by using module and provide

}