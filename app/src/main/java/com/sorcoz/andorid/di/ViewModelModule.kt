package com.sorcoz.andorid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sorcoz.andorid.addpost.AddPostViewModel
import com.sorcoz.andorid.login.LoginViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddPostViewModel::class)
    abstract fun bindBrowseProjecsViewModel(viewModel: AddPostViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)