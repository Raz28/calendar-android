package com.gsv28rus.calendar.di

import com.gsv28rus.calendar.event.EventListActivity
import com.gsv28rus.calendar.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeEventListActivity(): EventListActivity
}