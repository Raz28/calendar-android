package com.gsv28rus.calendar.di

import com.google.gson.Gson
import com.gsv28rus.calendar.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun getGson(): Gson
    fun inject(app: App)
}