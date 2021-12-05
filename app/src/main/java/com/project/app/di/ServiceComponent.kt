package com.project.app.di

import com.project.app.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class])
interface ServiceComponent {
    fun inject(homeViewModel: HomeViewModel)
}