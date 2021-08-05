package com.carlosmuvi.eventtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.account.AccountRepository
import com.carlosmuvi.eventtracker.data.account.UserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    var firstDestination = MutableStateFlow<MainDestinations?>(null)

    init {
        viewModelScope.launch {
            runCatching { userManager.currentUser }
                .onSuccess { user ->
                    firstDestination.emit(
                        if (user != null) MainDestinations.HOME else MainDestinations.LOGIN
                    )
                }.onFailure {
                    firstDestination.emit(MainDestinations.LOGIN)
                }

        }
    }
}