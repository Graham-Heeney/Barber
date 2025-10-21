package org.wit.barber.main

import android.app.Application
import org.wit.barber.models.BarberModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val barbers = ArrayList<BarberModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Barber App started")

        barbers.add(BarberModel("John's Barbers", "Classic haircuts"))
        barbers.add(BarberModel("Sharp Style", "Modern fades and trims"))
        barbers.add(BarberModel("Gentlemen's Den", "Traditional grooming"))
    }
}
