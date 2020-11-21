package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1

import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.component.AppComponent
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MyApp : DaggerApplication() {

    private val appInjector : AppComponent = DaggerAppComponent
                                                .builder()
                                                .application(this)
                                                .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appInjector
    }
}