package org.wit.barber.models

import timber.log.Timber.i

class BarberMemStore : BarberStore {

    private val barbers = ArrayList<BarberModel>()

    override fun findAll(): List<BarberModel> {
        return barbers
    }

    override fun create(barber: BarberModel) {
        barbers.add(barber)
        logAll()
    }

    private fun logAll() {
        barbers.forEach { i("$it") }
    }
}
