package com.gsv28rus.calendar.di

import com.gsv28rus.calendar.CalendarActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): CalendarActivity
}