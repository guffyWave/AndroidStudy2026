package com.example.hiltstudy.management

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import com.example.hiltstudy.helpers.BikeAdventure
import com.example.hiltstudy.helpers.DivingAdventure
import com.example.hiltstudy.helpers.GenericAdventureInfo
import com.example.hiltstudy.helpers.HikingAdventure
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Named


val TAG = "GreatAdventureApplication"

@HiltAndroidApp
class GreatAdventureApplication : Application() {

    @Inject
    lateinit var genericAdventureInfo: GenericAdventureInfo

    @Inject
    @Named("vietnam_biking")
    lateinit var bikeAdventure: BikeAdventure

    @Inject
    lateinit var hikingAdventure: HikingAdventure

    @Inject
    lateinit var divingAdventure: DivingAdventure


    override fun onCreate() {
        super.onCreate()

        val adventureLocation = genericAdventureInfo.getAdventureLocation()
        //Log.d(TAG, "onCreate: -- adventureLocation --- ${adventureLocation} ")

        //bikeAdventure.performAdventure()
        //Log.d(TAG, "onCreate: --bikeAdventure at --- ${bikeAdventure.getAdventurePlace()} ")

//        val message = hikingAdventure.doHiking()
//        Log.d(TAG, "onCreate: -- hikingAdventure message  --- ${message}")

        val message = divingAdventure.doAdventure()

        Log.d(TAG, "onCreate: -- divingAdventure message  --- ${message}")

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }


}