package uz.adkhamjon.githubusers.data.remote

import retrofit2.http.GET
import uz.adkhamjon.githubusers.data.remote.dto.GitUsers


interface UsersApi {

    @GET("users")
    suspend fun getUsers():List<GitUsers>

}