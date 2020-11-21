package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

}