package com.pixabay.di.components

import android.app.Application
import com.pixabay.di.modules.*
import com.pixabay.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.pixabay.utils.MyApplication
import dagger.android.DaggerApplication

@Singleton
@Component(modules = [ContextModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class, ActivityModule::class, FragmentModule::class,RoomModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }
}