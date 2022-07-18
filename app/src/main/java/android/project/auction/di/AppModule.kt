package android.project.auction.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.project.auction.common.Constants
import android.project.auction.data.remote.AuthAPI
import android.project.auction.data.repository.AuthRepositoryImpl
import android.project.auction.domain.repository.AuthRepository
import android.project.auction.domain.use_case.AuctionAuthUseCase
import android.project.auction.domain.use_case.auth.Authentication
import android.project.auction.domain.use_case.logout.Logout
import android.project.auction.domain.use_case.sign_in.SignIn
import android.project.auction.domain.use_case.sign_up.SignUp
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthAPI(): AuthAPI {

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5,TimeUnit.MINUTES)
            .build()



        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs",MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthAPI): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuctionAuthUseCases(repository: AuthRepository, preferences: SharedPreferences): AuctionAuthUseCase {
        return AuctionAuthUseCase(
            signIn = SignIn(repository = repository, preferences = preferences),
            signUp = SignUp(repository = repository),
            authentication = Authentication(repository = repository, preferences = preferences),
            logout = Logout(repository = repository, preferences = preferences)
        )
    }

}