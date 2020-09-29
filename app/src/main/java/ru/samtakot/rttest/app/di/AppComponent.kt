package ru.samtakot.rttest.app.di

import dagger.Component
import ru.samtakot.rttest.presentation.details.DetailsFragment
import ru.samtakot.rttest.presentation.list.ListFragment
import javax.inject.Singleton


@Component
@Singleton
interface AppComponent {


    abstract fun inject(f: DetailsFragment)
    abstract fun inject(f: ListFragment)


}