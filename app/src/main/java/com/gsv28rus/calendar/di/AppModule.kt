package com.gsv28rus.calendar.di

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gsv28rus.calendar.AppDatabase
import com.gsv28rus.calendar.common.schedulers.AppSchedulerProvider
import com.gsv28rus.calendar.common.schedulers.SchedulerProvider
import com.gsv28rus.calendar.event.EventApi
import com.gsv28rus.calendar.event.EventRepository
import com.gsv28rus.calendar.event.EventRepositoryImpl
import com.gsv28rus.calendar.user.UserApi
import com.gsv28rus.calendar.user.UserRepository
import com.gsv28rus.calendar.user.UserRepositoryImpl
import com.gsv28rus.calendar.utils.ZoneDateTimeDeserializer
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainExecutor(): Executor {
        return object : Executor {
            private val handler = Handler(Looper.getMainLooper())
            override fun execute(command: Runnable?) {
                handler.post(command)
            }
        }
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(ZonedDateTime::class.java, ZoneDateTimeDeserializer())
            .setLenient()
            .create()
    }

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideOkhttpClient(userRepository: Lazy<UserRepository>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                userRepository.get().getCurrentUser()?.firebaseIdToken.let {
                    requestBuilder.addHeader("X-Firebase-Auth", it!!)
                }
                return@addInterceptor chain.proceed(requestBuilder.build())
            }
            .build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl("http://10.0.2.2:8080/api/")
            .build()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    fun provideEventApi(retrofit: Retrofit): EventApi = retrofit.create(EventApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi, appDatabase: AppDatabase): UserRepository {
        return UserRepositoryImpl(userApi, appDatabase)
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventApi: EventApi): EventRepository {
        return EventRepositoryImpl(eventApi)
    }
}
