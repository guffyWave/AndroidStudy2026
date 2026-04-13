package com.example.hiltstudy

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.NavHostFragment
import com.example.hiltstudy.composables.AdventureViewItem
import com.example.hiltstudy.databinding.ActivityGreatAdventureBinding
import com.example.hiltstudy.helpers.BikeAdventure
import com.example.hiltstudy.helpers.GenericAdventureInfo
import com.example.hiltstudy.management.InstalledPackagesHelper
import com.example.hiltstudy.management.TAG
import com.example.hiltstudy.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class GreatAdventureActivity : AppCompatActivity() {

    @Inject
    lateinit var genericAdventureInfo: GenericAdventureInfo

    @Inject
    @Named("canada")
    lateinit var bikeAdventure: BikeAdventure

    @Inject
    lateinit var packagesHelper: InstalledPackagesHelper

//    @Inject
//    lateinit var mainViewModel: MainViewModel --- >> will not work
    // Hilt forbids injecting ViewModel using @Inject
    //ViewModels must be created using a ViewModelProvider whose lifecycle is tied to Activity lifecycle , Compose composition , Fragment lifecycle

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityGreatAdventureBinding


    private val composeView: ComposeView by lazy {
        findViewById(R.id.composeView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGreatAdventureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_great_adventure) as NavHostFragment

//        Log.d(
//            TAG,
//            "onCreate: GreatAdventureActivity ---- genericAdventureInfo -- ${genericAdventureInfo.getAdventureLocation()}  "
//        )

        Log.d(
            TAG,
            "onCreate: GreatAdventureActivity ---- bikeAdventure -- ${bikeAdventure.getAdventurePlace()}  "
        )

        composeView.setContent {
            AdventureViewItem()
        }

        val packageInfo = packagesHelper.getPackageInfo()


//        Log.d(
//            TAG,
//            "onCreate: GreatAdventureActivity  packageInfo -- ${packageInfo}  "
//        )

        val infoFromViewModel = mainViewModel.getInfo()
        Log.d(
            TAG,
            "onCreate: GreatAdventureActivity  mainViewModel infoFromViewModel -- ${infoFromViewModel}  "
        )

    }


}