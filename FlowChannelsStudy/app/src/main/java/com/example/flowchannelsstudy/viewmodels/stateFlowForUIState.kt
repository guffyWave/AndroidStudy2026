package com.example.flowchannelsstudy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//Your app loads user profile.
//UI must show:
//Loading indicator
//Success data
//Error message

data class UserProfileUIState(
    val isLoading: Boolean = false,
    val name: String? = "",
    val error: String? = ""
)


class UserProfileViewModel : ViewModel() {
    private val _userProfileUIState = MutableStateFlow<UserProfileUIState>(UserProfileUIState())
    val userProfileUIState = _userProfileUIState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            _userProfileUIState.value = UserProfileUIState(isLoading = true)
            //simulated delay
            delay(1000) // reading from cache
            _userProfileUIState.value =
                UserProfileUIState(name = "_Gufran Khurshid_", error = "")

            delay(3000) // dummy api call
            try {
                //fetch from repository
                _userProfileUIState.value =
                    UserProfileUIState(isLoading = false, name = "Gufran Khurshid", error = "")

            } catch (e: Exception) {
                _userProfileUIState.value =
                    UserProfileUIState(isLoading = false, name = "", error = e.message.toString())
            }

        }
    }
}