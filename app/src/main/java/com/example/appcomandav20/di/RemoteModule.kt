package com.example.appcomandav20.di

import android.content.Context
import androidx.room.Room
import com.example.appcomandav20.core.db.dao.DaoLoginExito
import com.example.appcomandav20.core.db.ComandaDB
import com.example.appcomandav20.features.orders.data.remote.*
import com.example.appcomandav20.features.passcode.data.remote.UserLoginApi
import com.example.appcomandav20.features.users.data.remote.UsuarioApi
import com.example.appcomandav20.features.zones.data.remote.TableApi
import com.example.appcomandav20.features.orders.data.remote.UpdateStateTableApi
import com.example.appcomandav20.features.zones.data.remote.ZoneApi
import com.example.appcomandav20.util.utils.Companion.PORT
import com.example.appcomandav20.util.utils.Companion.URLBASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("http://$URLBASE:$PORT/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

    private const val COMANDA_DATABASE_NAME = "ComandaDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): ComandaDB {
        return Room.databaseBuilder(context, ComandaDB::class.java, COMANDA_DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideLoginExitoRoom(db: ComandaDB): DaoLoginExito {
        return db.daoLoginExito()
    }

    @Singleton
    @Provides
    fun provideUsuarioApiClient(retrofit: Retrofit): UsuarioApi {
        return retrofit.create(UsuarioApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginUserApiClient(retrofit: Retrofit): UserLoginApi {
        return retrofit.create(UserLoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideZoneApiClient(retrofit: Retrofit): ZoneApi {
        return retrofit.create(ZoneApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTableApiClient(retrofit: Retrofit): TableApi {
        return retrofit.create(TableApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryApiClient(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDishApiClient(retrofit: Retrofit): DishApi {
        return retrofit.create(DishApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSendOrderApiClient(retrofit: Retrofit): SendOrdersApi {
        return retrofit.create(SendOrdersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrdersFulfilledApiClient(retrofit: Retrofit): OrdersFulfilledApi {
        return retrofit.create(OrdersFulfilledApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUpdateStateTable(retrofit: Retrofit): UpdateStateTableApi {
        return retrofit.create(UpdateStateTableApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrder(retrofit: Retrofit): OrderApi {
        return retrofit.create(OrderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUpdateColorOrder(retrofit: Retrofit): UpdateColorOrderApi {
        return retrofit.create(UpdateColorOrderApi::class.java)
    }


}