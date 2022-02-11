package uz.adkhamjon.githubusers.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.adkhamjon.githubusers.data.remote.dto.GitUsers

interface UserRepository {
   suspend fun getUsers(): Flow<List<GitUsers>>
}