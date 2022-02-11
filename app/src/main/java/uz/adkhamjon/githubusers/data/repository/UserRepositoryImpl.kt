package uz.adkhamjon.githubusers.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.adkhamjon.githubusers.data.remote.UsersApi
import uz.adkhamjon.githubusers.data.remote.dto.GitUsers
import uz.adkhamjon.githubusers.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
   private val usersApi: UsersApi
): UserRepository {
    override suspend fun getUsers(): Flow<List<GitUsers>> = flow { emit(usersApi.getUsers()) }
}