package org.wit.barber.main

import android.app.Application
import org.wit.barber.models.BarberJSONStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var barbers: BarberJSONStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        barbers = BarberJSONStore(this)
        i("Barber app started")
        i("Loaded ${barbers.findAll().size} barbers from JSON")
    }
}
