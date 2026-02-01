package com.example.confessit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.confessit.viewModel.AuthState
import com.example.confessit.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repo.login(email, password)

            _authState.value = result.fold(
                onSuccess = { AuthState.Success },
                onFailure = { AuthState.Error(it.message ?: "Login failed") }
            )
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repo.signUp(email, password)

            _authState.value = result.fold(
                onSuccess = { AuthState.Success },
                onFailure = { AuthState.Error(it.message ?: "Signup failed") }
            )
        }
    }

    fun logout() {
        repo.logout()
        _authState.value = AuthState.Idle
    }

    fun isUserLoggedIn(): Boolean {
        return repo.isUserLoggedIn()
    }
}