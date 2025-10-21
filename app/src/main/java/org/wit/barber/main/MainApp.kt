package org.wit.barber.main

import android.app.Application
import org.wit.barber.models.BarberMemStore
import org.wit.barber.models.BarberModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val barbers = BarberMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Barber app started")

        barbers.create(BarberModel("John", "Fade Specialist"))
        barbers.create(BarberModel("Sarah", "Stylist"))
        barbers.create(BarberModel("Mike", "Beard Expert"))
    }
}
