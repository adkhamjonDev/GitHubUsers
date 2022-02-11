package uz.adkhamjon.githubusers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.adkhamjon.githubusers.common.Constants
import uz.adkhamjon.githubusers.data.remote.UsersApi
import uz.adkhamjon.githubusers.data.repository.UserRepositoryImpl
import uz.adkhamjon.githubusers.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: UsersApi): UserRepository {
        return UserRepositoryImpl(api)
    }
}