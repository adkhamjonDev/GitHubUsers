package uz.adkhamjon.githubusers.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.adkhamjon.githubusers.domain.use_cases.get_users.UserResource
import uz.adkhamjon.githubusers.data.remote.dto.toGitUser
import uz.adkhamjon.githubusers.data.repository.UserRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
):ViewModel() {
    val state=MutableStateFlow<UserResource>(UserResource.Loading)
    init {
        getUsers()
    }
    private fun getUsers(){
            viewModelScope.launch {
                userRepositoryImpl.getUsers()
                    .catch {
                        state.value = UserResource.Error(it.message ?: "Error occured")
                    }.collect {
                        val data=it.map { it.toGitUser() }
                        state.value = UserResource.Success(data)
                    }
            }
    }
    fun getStateFlow(): StateFlow<UserResource> {
        return state
    }
}