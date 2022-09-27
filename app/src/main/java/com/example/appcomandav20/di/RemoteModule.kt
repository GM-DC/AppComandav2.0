package com.example.rickandmorty.di

import com.example.appcomandav20.data.source.remote.UserLoginApi
import com.example.appcomandav20.data.source.remote.UsuarioApi
import com.example.appcomandav20.util.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideUsuarioApi(): Retrofit {
        val retrofit: Retrofit
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES) // connect timeout **PRUEBA
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout **PRUEBA
            .readTimeout(5, TimeUnit.MINUTES) // read timeout **PRUEBA
            .addInterceptor(interceptor)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideUsuarioApiClient(retrofit: Retrofit): UsuarioApi {
        return retrofit.create(UsuarioApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginUserApiClient(retrofit: Retrofit): UserLoginApi {
        return retrofit.create(UserLoginApi::class.java)
    }

}