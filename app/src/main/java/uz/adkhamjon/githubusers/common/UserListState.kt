package uz.adkhamjon.githubusers.common

import uz.adkhamjon.githubusers.domain.model.GitUserModel


data class UserListState(
    val isLoading:Boolean=false,
    val users:List<GitUserModel> = emptyList(),
    val error:String = ""
)
