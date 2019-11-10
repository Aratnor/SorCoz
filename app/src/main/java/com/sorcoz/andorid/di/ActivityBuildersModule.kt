package com.sorcoz.andorid.di

import com.sorcoz.andorid.addpost.AddPostActivity
import com.sorcoz.andorid.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity
    @ContributesAndroidInjector
    abstract fun contributeAddPostActivity(): AddPostActivity
}