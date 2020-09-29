package ru.samtakot.rttest.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.samtakot.rttest.app.di.module.ApiModule
import ru.samtakot.rttest.app.di.module.DataModule
import ru.samtakot.rttest.app.di.module.DomainModule
import ru.samtakot.rttest.presentation.details.DetailsFragment
import ru.samtakot.rttest.presentation.list.ListFragment
import javax.inject.Singleton


@Component(
    modules =
    [ApiModule::class, DataModule::class, DomainModule::class]
)
@Singleton
interface AppComponent {

    abstract fun inject(f: DetailsFragment)
    abstract fun inject(f: ListFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun context(context: Context): Builder
    }

}