package com.sorcoz.andorid.di

import com.sorcoz.andorid.DispatcherProvider
import com.sorcoz.domain.IDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class UIModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatcherProvider: DispatcherProvider): IDispatcherProvider
}