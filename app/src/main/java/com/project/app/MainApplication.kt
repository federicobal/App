package com.project.app

import android.app.Application
import android.os.UserManager
import com.project.app.di.DaggerServiceComponent
import com.project.app.di.ServiceComponent
import com.project.app.di.ServiceModule

class MainApplication: Application() {
    private lateinit var serviceComponent : ServiceComponent
    override fun onCreate() {
        super.onCreate()
        //este "DaggerServiceComponent" lo genera el Dagger cuando complila / Make Project / F7
        serviceComponent= DaggerServiceComponent.builder()
            .serviceModule(ServiceModule())
            .build()
    }
    fun getServiceComponent() :ServiceComponent{

        return serviceComponent
    }
}