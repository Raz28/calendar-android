package com.gsv28rus.calendar.di

import androidx.lifecycle.ViewModel
import com.gsv28rus.calendar.event.EventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    abstract fun articleViewModel(eventViewModel: EventViewModel): ViewModel
}