package com.gsv28rus.calendar.di

import com.gsv28rus.calendar.common.DatePickerFragment
import com.gsv28rus.calendar.common.TimePickerFragment
import com.gsv28rus.calendar.event.EditEventFragment
import com.gsv28rus.calendar.event.EventListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeEventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun contributeEditEventFragment(): EditEventFragment

    @ContributesAndroidInjector
    abstract fun contributeDatePickerFragment(): DatePickerFragment

    @ContributesAndroidInjector
    abstract fun contributTimePickerFragment(): TimePickerFragment
}