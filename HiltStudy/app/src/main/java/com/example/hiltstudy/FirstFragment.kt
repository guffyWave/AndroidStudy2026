package com.example.hiltstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hiltstudy.databinding.FragmentFirstBinding
import com.example.hiltstudy.helpers.BikeAdventure
import com.example.hiltstudy.helpers.GenericAdventureInfo
import com.example.hiltstudy.management.DialogHelper
import com.example.hiltstudy.management.TAG
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    @Inject
    lateinit var genericAdventureInfo: GenericAdventureInfo

    @Inject
    @Named("vietnam_biking")
    lateinit var bikeAdventure: BikeAdventure;

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Log.d(
//            TAG,
//            "onCreate: FirstFragment ---- genericAdventureInfo -- ${genericAdventureInfo.getAdventureLocation()}  "
//        )


        Log.d(TAG, "onCreate: Fragment bikeAdventure --- ${bikeAdventure.getAdventurePlace()} ")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
        34567890
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            dialogHelper.displayDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}