package com.pixabay.di.modules

import com.pixabay.ui.dashboard.DashboardFragment
import com.pixabay.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment

}