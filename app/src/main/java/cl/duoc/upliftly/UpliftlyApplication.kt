package cl.duoc.upliftly

import android.app.Application
import cl.duoc.upliftly.quotes.data.di.AppContainer
import cl.duoc.upliftly.quotes.data.di.DefaultAppContainer

class UpliftlyApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}