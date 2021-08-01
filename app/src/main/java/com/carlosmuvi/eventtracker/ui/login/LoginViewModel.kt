package com.carlosmuvi.eventtracker.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmuvi.eventtracker.data.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    fun createAccount(loginForm: LoginForm, navigateToHome: () -> Unit) {
        viewModelScope.launch {
            runCatching { accountRepository.createAccount(loginForm.user, loginForm.password) }
                .onSuccess { navigateToHome() }
                .onFailure { Log.e("ERROR", "Error logging in", it) }
        }
    }
}


