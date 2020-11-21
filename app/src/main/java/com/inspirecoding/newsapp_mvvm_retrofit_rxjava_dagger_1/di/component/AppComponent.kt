package com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.component

import android.app.Application
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul.ActivityBuilderModule
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul.RetrofitModule
import com.inspirecoding.newsapp_mvvm_retrofit_rxjava_dagger_1.di.modul.TopHeadlinesRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component (
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        RetrofitModule::class,
        TopHeadlinesRepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application (application: Application) : Builder
        fun build() : AppComponent
    }

    override fun inject(instance: DaggerApplication?) {
        TODO("Not yet implemented")
    }
}