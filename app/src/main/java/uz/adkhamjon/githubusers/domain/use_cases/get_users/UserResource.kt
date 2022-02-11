package uz.adkhamjon.githubusers.domain.use_cases.get_users

import uz.adkhamjon.githubusers.data.remote.dto.GitUsers
import uz.adkhamjon.githubusers.domain.model.GitUserModel

sealed class UserResource {

    object Loading : UserResource()

    data class Success(val list: List<GitUserModel>) : UserResource()

    data class Error(val message: String) : UserResource()
}