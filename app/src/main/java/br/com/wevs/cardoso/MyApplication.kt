package br.com.wevs.cardoso

import android.app.Application
import br.com.wevs.cardoso.di.AppModule
import br.com.wevs.cardoso.di.NetworkModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule, NetworkModule)
        }

    }

}