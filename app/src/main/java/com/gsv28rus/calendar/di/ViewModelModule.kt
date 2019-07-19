package com.gsv28rus.calendar.di

import androidx.lifecycle.ViewModel
import com.gsv28rus.calendar.event.EditEventViewModel
import com.gsv28rus.calendar.event.EventListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun articleViewModel(eventListViewModel: EventListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditEventViewModel::class)
    abstract fun sharedViewModel(editEventViewModel: EditEventViewModel): ViewModel
}