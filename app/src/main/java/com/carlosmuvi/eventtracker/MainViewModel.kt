package com.carlosmuvi.eventtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    var firstDestination = MutableStateFlow<MainDestinations?>(null)

    init {
        viewModelScope.launch {
            runCatching { accountRepository.getUser() }
                .onSuccess { user ->
                    firstDestination.emit(
                        if (user != null) MainDestinations.HOME_ROUTE else MainDestinations.LOGIN
                    )
                }.onFailure {
                    firstDestination.emit(MainDestinations.LOGIN)
                }

        }
    }
}