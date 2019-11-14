package com.sorcoz.andorid.di

import com.sorcoz.andorid.SorCozApplication
import com.sorcoz.data.room.di.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RemoteModule::class,
        UIModule::class,
        ViewModelModule::class,
        ActivityBuildersModule::class,
        RoomModule::class
    ]
)
interface AppComponent : AndroidInjector<SorCozApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SorCozApplication): Builder

        fun build(): AppComponent
    }
}